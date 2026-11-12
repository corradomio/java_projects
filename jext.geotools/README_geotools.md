# Corridor Generator

Algorithm implemented:

let **polygons** a list of polygons representing civil parcels

1. remove the polygons having an area too small.
   Note: this filter must be improved. A traffic island could have a large surface
2. aggregate the polygon in clusters: 2 polygons are in the same cluster if they distances are less than a cluster tolerance
3. create the boundary for each cluster. The boundary is a line composed by multiple segments
4. for each segment of each boundary, compute the outward direction, located at the center of the segment
5. for each segment of each boundary, compute the intersection with segments of other boundaries using as starting point the centers and outward directions computed in 4. The intersections are filtered using 2 rules:
   1. if the intersection distance is greater than a maximum distance
   2. if the intersection angle differ more than max angle from 90 degree: 10 means the valid angles are [80-90]
6. for each boundary, assign the offset to all segments, following the rules
   Note: for all boundaries, all boundary segments have an outward direction **BUT NOT ALL** segments have an intersection. 
   Note: the segments analysis follow the counter-clockwise order (right hand rule)
   1. for each segment having an intersection, to compute the offset based on the offset table
   2. for each segment without an offset **but collinear** with a segment with offset, it is used the same offset already selected.
      Note: this step doesn't consider not collinear segments
   3. for each segment without offset (and not collinear), we identify the segments that precede and follow the current segment and it is used the max/min of the two offsets
   4. if all segments of a boundary are without intersections, it is used a default value 
7. expand the boundary of an offset depending on intersection distance
   



# jext.geotools



|               | class                    | description                                                  |
| ------------- | ------------------------ | ------------------------------------------------------------ |
|               |                          |                                                              |
| jext.geo      | LatLonUtils              | Geographic distances                                         |
|               | TileUtils                | Utilities to GPS coordinates in the correspondent OpenStreepMap tile or, from the tile, to the coordinates of the tile's center |
| jext.geotools | CorridorsGenerator       |                                                              |
|               | BoundaryExpander         |                                                              |
|               | BoundaryIntersection     |                                                              |
|               | BoundaryOffsets          |                                                              |
|               | BoundarySaver            |                                                              |
|               | ClusterBoundaryGenerator |                                                              |
|               | ExpanderOffsets          |                                                              |
|               | GeometrySelector         |                                                              |
|               | GeometryType             |                                                              |
|               | MultiLineStringMerger    |                                                              |
|               | OutwardDirection         |                                                              |
|               | PolygonClusterer         |                                                              |
|               | PolygonSelector          |                                                              |
|               | RayBoundaryIntersector   |                                                              |
|               | ShapeFileToImage         |                                                              |
|               | ShapeFileType            |                                                              |
|               | ShapeUtils               |                                                              |

