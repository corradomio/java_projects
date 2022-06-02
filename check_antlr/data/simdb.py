from typing import Tuple, Set, List, Optional, Dict, Union

import psycopg2
import loggingx as logging
import numpy as np
import pandas as pd


OSM_DATABASE = "osm"
OSM_USER = "osmuser"
OSM_PASSWORD = "osmuser"
OSM_HOST = "localhost"

TIME_SLOTS = 288

ONE_DEGREE = 111319     # 1 deg -> 111319 meters ~ 111 km
SIDE = 2/ONE_DEGREE
COORDS_RANGE = ((54.29649, 24.39213), (54.48875, 24.51839))
LON_MIN, LAT_MIN = (54.29649, 24.39213)

ILON_RANGE = (0, 10701)
ILAT_RANGE = (0, 7028)

#
# Abu Dhabi port
#
# lat     lon
# 24.4645,54.3010
# 24.4586,54.3114
#                 lon               lat
# coodinate porto 54.31023	        24.45903            ->  SRID 4326
#                 6045787.14846557	2809443.51136976    ->  SRID 3857

#
#                      lon               lat
ABU_DHABI_PORT_4326 = (54.31023,         24.45903)
ABU_DHABI_PORT_3857 = (6045787.14846557, 2809443.51136976)


# ---------------------------------------------------------------------------
# Simdb
# ---------------------------------------------------------------------------

class Simdb:

    def __init__(self):
        self._conn = None
        """:type: "Connection"""

        self._agents: Dict[int, List[int]] = dict()
        self._location_range = [[0, 0], [0, 0]]
        self._n_agents: Dict[int, int] = dict()

        self._log = logging.get_logger("Simdb")
    # end

    def __enter__(self):
        return self.connect()
    # end

    def __exit__(self, exc_type, exc_val, exc_tb):
        return self.close()
    # end

    def connect(self):
        """Create the database connection"""
        if self._conn is not None:
            self._log.warn("Already connect")
            return self

        self._log.info("Connect")
        self._conn = psycopg2.connect(
            host=OSM_HOST,
            dbname=OSM_DATABASE,
            user=OSM_USER,
            password=OSM_PASSWORD)
        return self
    # end

    def close(self):
        """Close the database connection"""
        if self._conn is not None:
            self._conn.close()
        self._conn = None
    # end

    def get_agents(self, sim: int) -> List[int]:
        """List of agents"""
        if sim in self._agents:
            return self._agents[sim]

        self._log.info(f"... get_agents({sim})")

        with self._conn.cursor() as curs:
            curs.execute("SELECT DISTINCT uid "
                         "FROM abu_dhabi_agents_state "
                         "WHERE sim=%s AND tid=0 ", (sim,))
            result = curs.fetchall()
        agents = [r[0] for r in result]
        self._agents[sim] = agents
        return self._agents[sim]
    # end

    def get_num_of_agents(self, sim: int) -> int:
        """Number of agents. 'id' in range [1,n]"""
        if sim in self._n_agents:
            return self._n_agents[sim]

        self._log.info(f"... get_num_of_agents({sim})")
        with self._conn.cursor() as curs:
            curs.execute("SELECT COUNT(uid) "
                         "FROM abu_dhabi_agents_state "
                         "WHERE sim=%s "
                         "  AND tid=0 "
                         "  AND bt=true AND i0=false ", (sim,))
            self._n_agents[sim] = curs.fetchall()[0][0]
        return self._n_agents[sim]
    # end

    def get_infection_range(self, sim: int, day: int = -1) -> Tuple[float, float]:
        """
        Minimum & maximum infection probability excluding 0 and 1
        :param sim: simulation id
        :return: min/max infection probability
        """
        self._log.info(f"... get_infection_range({sim},{day})")
        with self._conn.cursor() as curs:
            if day == -1:
                curs.execute("""
                SELECT MIN(iprob), MAX(iprob) 
                FROM abu_dhabi_agents_state 
                WHERE sim=%s 
                  AND iprob > 0 AND iprob < 1 
                """, (sim,))
                rng = curs.fetchall()[0]
            else:
                curs.execute("""
                SELECT MIN(iprob), MAX(iprob)
                FROM abu_dhabi_agents_state
                WHERE sim=%s
                  AND tid=%s
                  AND iprob > 0 AND iprob < 1
                """, (sim, day))
                rng = curs.fetchall()[0]
            if rng == (None, None):
                rng = (0, 0)
        self._log.info(f"...     {rng}")
        return rng
    # end

    def get_infected_count_by_day(self, sim: int, day: int, iprob: float, delta: float) -> int:
        self._log.info(f"... get_infected_count_by_day({sim},{day},{iprob:.1f})")
        with self._conn.cursor() as curs:
            curs.execute("SELECT COUNT(1) "
                         "FROM abu_dhabi_agents_state "
                         "WHERE sim=%s "
                         "  AND tid=%s "
                         "  AND iprob >= %s AND iprob < %s ", (sim, day, iprob, iprob+delta))
            count = curs.fetchall()[0][0]
        return count
    # end

    def get_infected_count(self, sim: int, iprob: float = 0.) -> np.ndarray:
        # n_agents = self.get_num_of_agents(sim)

        self._log.info(f"... get_infected_count({sim},{iprob:.1f})")
        with self._conn.cursor() as curs:
            curs.execute("SELECT tid, count(iprob) "
                         "FROM abu_dhabi_agents_state "
                         "WHERE sim=%s "
                         "  AND iprob > %s "
                         "  AND bt=true "
                         "  AND i0=false "
                         "GROUP BY tid "
                         "ORDER BY tid ", (sim, iprob))
            data = curs.fetchall()
        infected = np.array(data, dtype=float)

        return infected
    # end

    def get_infected_sum(self, sim: int) -> np.ndarray:
        # n_agents = self.get_num_of_agents(sim)

        self._log.info(f"... get_infected_sum({sim})")
        with self._conn.cursor() as curs:
            curs.execute("SELECT tid, sum(iprob) "
                         "FROM abu_dhabi_agents_state "
                         "WHERE sim=%s "
                         "  AND bt=true "
                         "  AND i0=false "
                         "GROUP BY tid "
                         "ORDER BY tid ", (sim,))
            data = curs.fetchall()
        infected = np.array(data, dtype=float)

        return infected
    # end

    def get_probability(self, sim: int, field: str = 'iprob') -> np.ndarray:
        # n_agents = self.get_num_of_agents(sim)
        self._log.info(f"... get_probability({sim})")
        with self._conn.cursor() as curs:
            curs.execute(f"SELECT tid, avg({field}) "
                         "FROM abu_dhabi_agents_state "
                         "WHERE sim=%s "
                         "GROUP BY tid "
                         "ORDER BY tid ", (sim, ))
            data = curs.fetchall()
        if len(data) == 0:
            return np.zeros((0, 2))
        infected = np.array(data, dtype=float)
        # infected[:, 1] = infected[:, 1]/n_agents
        return infected
    # end

    def get_day_propabilities(self, sim: int, tid: int) -> np.ndarray:
        self._log.info(f"... get_day_propabilities({sim},{tid})")
        with self._conn.cursor() as curs:
            curs.execute("SELECT uid, sprob, iprob, rprob "
                         "FROM abu_dhabi_agents_state "
                         "WHERE sim=%s "
                         "  AND tid=%s ", (sim, tid))
            data = curs.fetchall()
        probs = np.array(data, dtype=float)
        return probs

    def get_probabilities(self, sim: int) -> np.ndarray:
        # day, avg[s],avg[i],avg[r], sdev[s], sdev[i], sdev[r]
        # s: susceptible
        # i: infected
        # r: recovered
        self._log.info(f"... get_probabilities({sim})")
        with self._conn.cursor() as curs:
            curs.execute("SELECT tid, "
                         "avg(sprob) as sprob, "
                         "avg(iprob) as iprob, "
                         "avg(rprob) as rprob, "
                         "stddev(sprob) as ssdev, "
                         "stddev(iprob) as isdev, "
                         "stddev(rprob) as rsdev  "
                         "FROM abu_dhabi_agents_state "
                         "WHERE sim=%s AND bt=true AND i0=false "
                         "GROUP BY tid "
                         "ORDER BY tid ", (sim, ))
            data = curs.fetchall()
        if len(data) == 0:
            return np.zeros((0, 7))
        probs = np.array(data, dtype=float)
        return probs
    # end

    def get_quantiles(self, sim: int, ndays: int = 91) -> np.ndarray:
        # day, q2[s], q2[i], q2[r], q1[s], q1[i], q1[r], q3[s], q3[i], q3[r]
        # s: susceptible
        # i: infected
        # r: recovered
        # q1,q2,q3: 1^, 2^, 3^ quartile
        self._log.info(f"... get_quantiles({sim})")
        days = []
        sq1 = []
        sq2 = []
        sq3 = []
        iq1 = []
        iq2 = []
        iq3 = []
        rq1 = []
        rq2 = []
        rq3 = []
        for tid in range(ndays):
            self._log.info(f"... ... ({sim},{tid})")
            s = self._get_quantiles_sdc(sim, tid, 'sprob')
            i = self._get_quantiles_sdc(sim, tid, 'iprob')
            r = self._get_quantiles_sdc(sim, tid, 'rprob')

            days.append(tid)
            sq1.append(s[0])
            sq2.append(s[1])
            sq3.append(s[2])

            iq1.append(i[0])
            iq2.append(i[1])
            iq3.append(i[2])

            rq1.append(r[0])
            rq2.append(r[1])
            rq3.append(r[2])
        # end
        return np.array([days, sq2, iq2, rq2, sq1, iq1, rq1, sq3, iq3, rq3]).T
    # end

    def _get_quantiles_sdc(self, sim: int, tid: int, col: str) -> np.ndarray:
        # -> mean, q1, q3
        # self._log.info(f"... ... get_quantiles({sim},{tid},{col})")
        with self._conn.cursor() as curs:
            curs.execute(f"""
            SELECT 
              percentile_disc(0.25) within group (order by abu_dhabi_agents_state.{col}) AS q1,
              percentile_disc(0.5)  within group (order by abu_dhabi_agents_state.{col}) AS mean,
              percentile_disc(0.75) within group (order by abu_dhabi_agents_state.{col}) AS q3
            FROM abu_dhabi_agents_state
            WHERE sim={sim}
              AND tid={tid}
              AND bt=true AND i0=false
            """)
            data = curs.fetchall()
        return data[0]
    # end

    def get_probabilities_filtered(self, sim: int, p: float) -> np.ndarray:
        n_agents = self.get_num_of_agents(sim)
        f_agents = int(n_agents*p)

        self._log.info(f"... get_probabilities_filtered({sim},{f_agents})")
        with self._conn.cursor() as curs:
            curs.execute(f"SELECT tid, sum(sprob) as sprob, sum(iprob) as iprob, sum(rprob) as rprob "
                         "FROM abu_dhabi_agents_state "
                         "WHERE sim=%s "
                         "  AND uid <= %s "
                         "GROUP BY tid "
                         "ORDER BY tid ", (sim, f_agents))
            data = curs.fetchall()
        if len(data) == 0:
            return np.zeros((0, 4))
        infected = np.array(data, dtype=float)
        infected[:, 1] = infected[:, 1] / n_agents
        infected[:, 2] = infected[:, 2] / n_agents
        infected[:, 3] = infected[:, 3] / n_agents
        return infected
    # end

    def get_infected_agents(self, sim: int, select: Optional[Set[int]] = None) -> pd.DataFrame:
        def _len(s):
            return 0 if s is None else len(s)
        # tid, uid, iprob
        self._log.info(f"... get_infected_agents({sim},#{_len(select)})")
        with self._conn.cursor() as curs:
            curs.execute("SELECT tid,uid,iprob "
                         "FROM abu_dhabi_agents_state "
                         "WHERE sim=%s AND iprob > 0 AND iprob < 1 "
                         "ORDER BY tid,uid ", (sim, ))
            data = curs.fetchall()
        df = pd.DataFrame(data, columns=['tid', 'uid', 'iprob'])
        if select is not None:
            df = df[df["uid"].isin(select)]
        return df
    # end

    def get_infected_agents_by_day(self, sim: int, day: int, select: Optional[Set[int]] = None) -> pd.DataFrame:
        # tid, uid, iprob
        self._log.info(f"... get_infected_agents_by_day({sim},{day})")
        with self._conn.cursor() as curs:
            curs.execute("SELECT tid,uid,iprob "
                         "FROM abu_dhabi_agents_state "
                         "WHERE sim=%s AND tid=%s AND iprob > 0 AND iprob < 1 "
                         "ORDER BY tid,uid ", (sim, day))
            data = curs.fetchall()
        df = pd.DataFrame(data, columns=['tid', 'uid', 'iprob'])
        if select is not None:
            df = df[df["uid"].isin(select)]
        return df
    # end

    def get_infected_agents_count(self, sim: int, iprob: Tuple[float, float], day: int = -1) -> Union[int, np.ndarray]:
        ipmin, ipmax = iprob
        self._log.info(f"... get_infected_agents_count({sim},{ipmin:.1f}, {ipmax:.1f}, {day})")

        if day == -1:
            with self._conn.cursor() as curs:
                curs.execute("SELECT tid, SUM(uid) "
                             "FROM abu_dhabi_agents_state "
                             "WHERE sim=%s AND iprob >= %s AND iprob < %s "
                             "GROUP BY tid "
                             "ORDER BY tid ", (sim, ipmin, ipmax))
                data = curs.fetchall()
            return np.array(data)
        else:
            with self._conn.cursor() as curs:
                curs.execute("SELECT tid, COUNT(uid) "
                             "FROM abu_dhabi_agents_state "
                             "WHERE sim=%s AND iprob >= %s AND iprob < %s "
                             "  AND tid = %s "
                             "GROUP BY tid "
                             "ORDER BY tid", (sim, ipmin, ipmax, day))
                data = curs.fetchone()
            return data[1] if data is not None else 0
        #end
    # end

    def get_location_range(self, border: float = 0., border_m: float = 0.) -> Tuple[Tuple[float, float], Tuple[float, float]]:
        """
        Retrieve the rectangle containing all locations

        @:param border: add an extra 'border', in degrees, to the coordinates
        @:param border_m: add an extra 'border', in meters, to the coordinates
        @:return ((minlon, minlat),(maxlon, maxlat))
        """
        self._log.info("... get_location_range()")
        if self._location_range[0][0] == 0:
            self._log.info("... get_location_range")
            with self._conn.cursor() as curs:
                curs.execute("SELECT MIN(ilon), MIN(ilat), MAX(ilon), MAX(ilat) "
                             "FROM abu_dhabi_tracks_2 "
                             # "WHERE ilon BETWEEN %s AND %s "
                             # "  AND ilat BETWEEN %s AND %s",
                             # ILON_RANGE + ILAT_RANGE
                 )

                minilon, minilat, maxilon, maxilat = curs.fetchone()

            minlon = LON_MIN + (SIDE*minilon)
            minlat = LAT_MIN + (SIDE*minilat)
            maxlon = LON_MIN + (SIDE*maxilon)
            maxlat = LAT_MIN + (SIDE*maxilat)
            self._location_range = (minlon, minlat), (maxlon, maxlat)
            self._log.info(f"... ... {self._location_range}")
        # end
        if border_m != 0:
            border = border_m/ONE_DEGREE

        (minlon, minlat), (maxlon, maxlat) = self._location_range
        return (minlon - border, minlat - border), (maxlon + border, maxlat + border)
    # end

    def get_infected_locations_by_day(self, sim: int, iprob: float, day: int, mod: int = 1) -> np.ndarray:
        """
        Retrieve the number of infected agents by location

        :param sim: simulation id
        :param iprob: minimum probability
        :param day: selected day
        :return: (lon, lat, count)
        """
        self._log.info(f"... get_infected_locations_by_day({iprob:.1f}, {sim},{day})")
        with self._conn.cursor() as curs:
            curs.execute("""
            select t.ilon, t.ilat, count(1) as c
            from abu_dhabi_agents_state as s, abu_dhabi_tracks_2 as t
            where s.sim = %s
              and s.tid = %s
              and s.uid %% %s = 0
              and s.iprob > %s
              and s.uid = t.uid
              and s.tid*288 = t.tid
            group by t.ilon, t.ilat
            """, (sim, day, mod, iprob))
            data = curs.fetchall()
        # df = pd.DataFrame(data, columns=['lon', 'lat', 'count'])
        # df["lon"] = df["lon"].astype(float).apply(lambda x: x*SIDE + LON_MIN)
        # df["lat"] = df["lat"].astype(float).apply(lambda x: x*SIDE + LAT_MIN)
        # return df
        counts = np.array(data, dtype=float)
        if len(counts) == 0:
            counts = counts.reshape((0, 3))
        counts[:, 0] = counts[:, 0] * SIDE + LON_MIN
        counts[:, 1] = counts[:, 1] * SIDE + LAT_MIN
        return counts
    # end

    def get_locations(self, mod: int = 1) -> np.ndarray:
        self._log.info(f"... get_locations({mod})")
        if mod == 1:
            with self._conn.cursor() as curs:
                curs.execute("select distinct ilon, ilat from abu_dhabi_tracks_2 ")
                data = curs.fetchall()
        else:
            with self._conn.cursor() as curs:
                curs.execute("select distinct ilon, ilat from abu_dhabi_tracks_2 "
                             "where ilon %% %s = 0 "
                             "  and ilat %% %s = 0 ", (mod, mod))
                data = curs.fetchall()
        coords = np.array(data, dtype=float)
        coords[:, 0] = coords[:, 0]*SIDE + LON_MIN
        coords[:, 1] = coords[:, 1]*SIDE + LAT_MIN
        return coords
    # end

    def get_agent_locations(self, uid: int, days: Tuple[int, int]) -> np.ndarray:
        self._log.info(f"... get_agent_locations({uid},{days})")
        with self._conn.cursor() as curs:
            curs.execute("""
                select distinct ilon, ilat from abu_dhabi_tracks_2
                where uid=%s
                and tid between %s and %s
                """, (uid, days[0]*288, days[1]*288))
            data = curs.fetchall()
        coords = np.array(data, dtype=float)
        coords[:, 0] = coords[:, 0] * SIDE + LON_MIN
        coords[:, 1] = coords[:, 1] * SIDE + LAT_MIN
        return coords
    # end

    def get_iprob_count(self, sim: int) -> np.ndarray:  # [(day,count),...]
        self._log.info(f"... get_iprob_count({sim})")
        with self._conn.cursor() as curs:
            curs.execute("""
            select tid, count(distinct iprob)
            from abu_dhabi_agents_state 
            where sim=%s
              and iprob > 0 and iprob < 1
            group by tid
            order by tid
            """, (sim, ))
            data = curs.fetchall()
        return np.array(data)
    # end

    def get_top_agents_infected(self, sim: int, tid: int, k: int = 1000) -> List[int]:
        self._log.info(f"... get_top_agents_infected({sim},{tid},{k})")
        if k > 0:
            with self._conn.cursor() as curs:
                curs.execute("""
                select uid
                from abu_dhabi_agents_state
                where sim=%s
                  and tid=%s
                  and iprob > 0 and iprob < 1
                order by iprob asc, uid
                limit %s
                """, (sim, tid, k))
                data = list(map(lambda r: r[0], curs.fetchall()))
        else:
            with self._conn.cursor() as curs:
                curs.execute("""
                select uid
                from abu_dhabi_agents_state
                where sim=%s
                  and tid=%s
                  and iprob > 0 and iprob < 1
                order by iprob desc, uid
                limit %s
                """, (sim, tid, -k))
                data = list(map(lambda r: r[0], curs.fetchall()))

        return data
    # end

    def get_occupancy_histo(self, sim: int, tid: int = -1) -> np.ndarray:
        self._log.info(f"... get_occupancy_histo({sim},{tid})")
        if tid != -1:
            with self._conn.cursor() as curs:
                curs.execute("""
                select c,count(1) from abu_dhabi_agents_state
                where sim=%s and tid=%s
                group by c
                order by c
                """, (sim, tid))
                data = curs.fetchall()
        else:
            with self._conn.cursor() as curs:
                curs.execute("""
                select c,count(1) from abu_dhabi_agents_state
                where sim=%s
                group by c
                order by c
                """, (sim, ))
                data = curs.fetchall()
        data = np.array(data, dtype=int)
        if tid == -1:
            data[:, 1] = data[:, 1]/90
        return data
    # end

    def scan_tracks(self, callback, limit=0, uorder=True):
        self._log.info(f"... scan_tracks")
        with self._conn.cursor() as curs:
            if limit == 0:
                if uorder:
                    curs.execute(f"SELECT tid, uid, ilon, ilat FROM abu_dhabi_tracks_2 ORDER BY tid, uid")
                else:
                    curs.execute(f"SELECT tid, uid, ilon, ilat FROM abu_dhabi_tracks_2 ORDER BY tid")
            else:
                if uorder:
                    curs.execute(f"SELECT tid, uid, ilon, ilat FROM abu_dhabi_tracks_2 ORDER BY tid, uid LIMIT {limit}")
                else:
                    curs.execute(f"SELECT tid, uid, ilon, ilat FROM abu_dhabi_tracks_2 ORDER BY tid LIMIT {limit}")
            for r in curs:
                callback.eval(r)
    # end

    def count_contacts(self, tid: int):
        self._log.info(f"... count_contacts({tid})")
        with self._conn.cursor() as curs:
            curs.execute(f"""
            select t.c, count(t.c) from (
                select ilon,ilat,count(uid) as c
                from abu_dhabi_tracks_2
                where tid={tid}
                group by ilon,ilat
            ) as t
            group by t.c
            order by t.c
            """)
            data = curs.fetchall()
        return data
    # end

    def count_cell_contacts(self, tid: int, side: int = 1) -> np.ndarray:
        """

        :param tid:
        :return:  [[ilon, ilat, count],...]
        """
        ratio = side//2
        self._log.info(f"... count_cell_contacts({tid})")
        if ratio == 1:
            with self._conn.cursor() as curs:
                curs.execute(f"""
                select ilon as ilon, ilat as ilat, count(uid)
                from abu_dhabi_tracks_2
                where tid={tid}
                group by tid, ilon, ilat
                """)
                data = curs.fetchall()
        else:
            with self._conn.cursor() as curs:
                curs.execute(f"""
                select ilon/{ratio} as ilon, ilat/{ratio} as ilat, count(uid)
                from abu_dhabi_tracks_2
                where tid={tid}
                group by tid, ilon/{ratio}, ilat/{ratio}
                """)
                data = curs.fetchall()
        return data
    # end
# end

# ---------------------------------------------------------------------------
# End
# ---------------------------------------------------------------------------
