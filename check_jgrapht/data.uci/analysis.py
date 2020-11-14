from typing import List, Union

import numpy as np
import pandas as pd
import pandasx as pdx
from pprint import pprint

# id,r,
# insideProb,betweenProb,
# communityWeightsMean,communityWeightsSdev,
# betweenWeightsMean,betweenWeightsSdev,
# weighMode,
# weighType,
# threshold,
# order,
# minVertexDegree,maxVertexDegree,meanVertexDegree,sdevVertexDegree,
# size,
# graphWeight,
# minEdgeWeight,maxEdgeWeight,meanEdgeWeight,sdevEdgeWeight,
# numComponents,
# density,
# numClusters,
# minClusterSize,maxClusterSize,meanClusterSize,sdevClusterSize,
# insideWeight/sim,betweenWeight/sim,
# insideWeight/disim,betweenWeight/disim,
# unbalancingIndex,purity,giniIndex,entropy,randIndex,adjustedRandIndex,fowlkesMallowsIndex,jaccardIndex,normalizedGamma,
# modularity/sim,louvainModularity/sim,dunnIndex/disim,daviesBouldinIndex/disim


def clean_file(name):
    print("clean_file", name)
    id = ""         # graph id
    c = ""          # n clusters
    e = 0          # n edges
    pm = 0.
    plm = 0.
    pdi = 0.
    pdbi = 0.
    count = 1
    with open("{}-stats.csv".format(name)) as fin:
        with open("{}-stats-clean.csv".format(name), mode="w") as fout:
            h = next(fin)
            fout.writelines(h.strip() + ",c,e,m_delta,lm_delta,di_delta,dbi_delta\n")
            for line in fin:

                count += 1
                if count%5000 == 0: print("...", count)

                if line.startswith("--"):
                    continue
                parts = line.split(",")

                # new graph id
                if parts[0] != id:
                    id = parts[0]
                    c = parts[24]
                    e = parts[16]
                    pm = 0.
                    plm = 0.
                    pdi = 0.
                    pdbi = 0.
                # 43, 44, 45
                cm = float(parts[42])
                clm = float(parts[43])
                cdi = float(parts[44])
                cdbi = float(parts[45])

                extras = ",{},{},{:.6},{:.6},{:.6},{:.6}\n".format(c, e, cm-pm, clm-plm, cdi-pdi, cdbi-pdbi)
                fout.writelines(line.strip() + extras)

                plm = clm
                pdi = cdi
                pdbi = cdbi
            # end
        # end
    # end
    print("done")
# end


COLUMNS = ["id", "r", "c", "e",
           "betweenProb",
           "communityWeightsMean", "communityWeightsSdev",
           "betweenWeightsMean", "betweenWeightsSdev",
           "weightMode",
           # ----
           "numClusters",
           # "louvainModularity/sim", "dunnIndex/disim", "daviesBouldinIndex/disim"
           "m_delta", "lm_delta", "di_delta", "dbi_delta"
        ]

ERR = .5


def predicted_clusters(df: pd.DataFrame, col: str) -> np.ndarray:
    ids = sorted(set(df["id"].to_list()))
    r_s = sorted(set(df["r"].to_list()))

    nc_list = []
    for id in ids:
        for r in r_s:
            # select the group of simulations with different thresholds
            # skip the first row because it contains the ground truth
            idr = df[(df["id"] == id) & (df["r"] == r)][1:]
            nc = pdx.series_argmax(idr, "numClusters", col)
            nc_list.append(nc)
        # end
    # end
    # number of clusters
    return np.array(nc_list).mean()


def mean_clusters(df: pd.DataFrame, c: int, e: float):
    dc = int(e*c)           # delta clusters
    minc = max(1, c-dc)     # min clusters
    maxc = c+dc             # max clusters

    dfc = df[df["c"] == c]
    dfc_stripped = dfc[(dfc["numClusters"] >= minc) & (dfc["numClusters"] <= maxc)]

    m_c = predicted_clusters(dfc_stripped, "m_delta")
    lm_c = predicted_clusters(dfc_stripped, "lm_delta")
    di_c = predicted_clusters(dfc_stripped, "di_delta")
    dbi_c = predicted_clusters(dfc_stripped, "dbi_delta")

    return {"c": c, "m": m_c, "lm": lm_c, "di": di_c, "dbi": dbi_c}


def _predict_on(df: pd.DataFrame, nclusters: int, cols: List[str], on: tuple = (), e: float=ERR):
    if cols is None or len(cols) == 0:
        print("predict_on", on + (("c", nclusters),))
        pn = mean_clusters(df, nclusters, e)
        return {
            on + (("c", nclusters),): pn
        }

    col = cols[0]
    rest = cols[1:]
    res = dict()
    vals = sorted(set(df[col].to_list()))
    for v in vals:
        k = ((col, v),)
        dfv = df[df[col] == v]

        res.update(_predict_on(dfv, nclusters, rest, on + k, e))
    return res


def predict_on(df: pd.DataFrame, nclusters: int, cols: Union[str, List[str]], e: float=ERR):
    if type(cols) not in [list, tuple]:
        cols = [cols]
    return _predict_on(df, nclusters, cols, (), e)


def save_results(name, results):
    kset = []
    for res in results:
        for keys in res:
            for key in keys:
                if key[0] not in kset:
                    kset.append(key[0])

    data = []
    for res in results:
        for keys in res:
            rec = [0] + ["ALL"] + [0.]*((len(kset) - 2) + 4)
            for key in keys:
                k = key[0]
                v = key[1]
                i = kset.index(k)
                rec[i] = v
            rec[-4] = res[keys]["m"]
            rec[-3] = res[keys]["lm"]
            rec[-2] = res[keys]["di"]
            rec[-1] = res[keys]["dbi"]
            data.append(rec)

    data.sort(key=lambda x: x[0:-4])

    with open("{}-results.csv".format(name), mode="w") as fw:
        fw.writelines(",".join(kset + ["m", "lm", "di", "dbi"])+"\n")
        for rec in data:
            rec = ",".join(list(map(str, rec))) + "\n"
            fw.writelines(rec)


def process_results(name, nclusters, e=ERR):
    clean_file(name)
    # return

    df = pdx.load_data("{}-stats-clean.csv".format(name), dtype=[float] * 8 + [enumerate] * 2 + [float] * 42)
    # print(df.dtypes)

    df = df[COLUMNS].astype({"c": int, "id": int, "r": int, "e": int, "numClusters": int})

    save_results(name, [
        predict_on(df, nclusters, [], e)
    ])


def main():
    process_results("auto", 6, .75)
    process_results("disease", 2, .75)
    process_results("wine", 3, .75)
    process_results("zoo", 7, .75)

# end


if __name__ == "__main__":
    main()
