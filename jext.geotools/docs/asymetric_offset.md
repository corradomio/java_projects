# Asymmetric offset

## Problem

Given a list of polygons, representing construction parcels, the problems to solve are:

1. parcels more near of a selected threshold (4 m) are collected in a single cluster
2. for each cluster, identify the borders, that is, a single closed line containing all polygons
3. from each border, to compute the distance of the nearest border
4. expand each border of an offset depending on the distance of the other border



Possible offsets

| Distance (m) | Offset (m) | Note    |
| ------------ | ---------- | ------- |
| 10           | 1          | [5,15]  |
| 20           | 2          | [15,25] |
| 30           | 3          | [25,35] |
| 40           | 3          | [35,+]  |
| unknown      | 2          |         |



## Note

When it is computed the distance from a segment (|---|) to the nearest border, we can consider 3 points:

1. first& last point of the segments
2. center of the segment

The directions to consider are:

1. the direction outward the segment
2. the middle direction between two segments



## Valid intersections

An intersection is valid **only if**:

1. the intersection point is not too far
2. the intersection angle between the segments is $90\pm 10^°$
3. 
