// package jext.igrapht;
//
// import org.jgrapht.Graph;
// import org.jgrapht.GraphPath;
// import org.jgrapht.alg.connectivity.ConnectivityInspector;
// import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
// import org.jgrapht.graph.DefaultGraphType;
// import org.jgrapht.graph.DefaultWeightedEdge;
// import org.junit.jupiter.api.Test;
//
// import java.util.Arrays;
// import java.util.List;
// import java.util.Set;
// import java.util.concurrent.atomic.AtomicInteger;
//
// import static org.junit.jupiter.api.Assertions.*;
//
// class IGraphTest {
//
//     static {
//         System.setProperty("igraph.library", "D:\\Projects\\cpp_projects\\graph\\jext.igrapht\\win32-x86-64\\libigraph.dll");
//     }
//     @Test
//     void nativeAndJGraphTAlgorithmsShareTheSameWeightedGraph() {
//         AtomicInteger ids = new AtomicInteger();
//         Graph<String, DefaultWeightedEdge> jgraphtView;
//         try (IGraph<String, DefaultWeightedEdge> graph = new IGraph<>(
//             true, () -> "v" + ids.getAndIncrement(), DefaultWeightedEdge::new)) {
//             jgraphtView = graph;
//             graph.addVertex("a");
//             graph.addVertex("b");
//             graph.addVertex("c");
//             assertEquals("v0", graph.addVertex());
//
//             DefaultWeightedEdge ab = graph.addEdge("a", "b");
//             DefaultWeightedEdge bc = graph.addEdge("b", "c");
//             DefaultWeightedEdge ac = graph.addEdge("a", "c");
//             graph.setEdgeWeight(ab, 2.0);
//             graph.setEdgeWeight(bc, 2.0);
//             graph.setEdgeWeight(ac, 10.0);
//
//             GraphPath<String, DefaultWeightedEdge> nativePath = graph.shortestPath("a", "c");
//             assertNotNull(nativePath);
//             assertEquals(Arrays.asList("a", "b", "c"), nativePath.getVertexList());
//             assertEquals(Arrays.asList(ab, bc), nativePath.getEdgeList());
//             assertEquals(4.0, nativePath.getWeight());
//
//             GraphPath<String, DefaultWeightedEdge> jgraphtPath =
//                 DijkstraShortestPath.findPathBetween(jgraphtView, "a", "c");
//             assertNotNull(jgraphtPath);
//             assertEquals(nativePath.getVertexList(), jgraphtPath.getVertexList());
//             assertEquals(nativePath.getWeight(), jgraphtPath.getWeight());
//
//             GraphPath<String, DefaultWeightedEdge> selfPath = graph.shortestPath("a", "a");
//             assertNotNull(selfPath);
//             assertEquals(List.of("a"), selfPath.getVertexList());
//             assertEquals(List.of(), selfPath.getEdgeList());
//             assertEquals(0.0, selfPath.getWeight());
//
//             assertEquals(2, graph.degreeOf("a"));
//             assertEquals(2, graph.outDegreeOf("a"));
//             assertEquals(0, graph.inDegreeOf("a"));
//             assertFalse(graph.isConnected());
//             assertFalse(graph.isConnected(true));
//             assertEquals(2, new ConnectivityInspector<>(graph).connectedSets().size());
//             assertNull(graph.shortestPath("c", "a"));
//         }
//     }
//
//     @Test
//     void vertexRemovalSynchronizesRenumberedNativeIds() {
//         try (IGraph<String, DefaultWeightedEdge> graph = new IGraph<>(
//             true, null, DefaultWeightedEdge::new)) {
//             for (String vertex : Arrays.asList("a", "b", "c", "d")) {
//                 graph.addVertex(vertex);
//             }
//             graph.addEdge("a", "b");
//             graph.addEdge("b", "c");
//             DefaultWeightedEdge cd = graph.addEdge("c", "d");
//             DefaultWeightedEdge ad = graph.addEdge("a", "d");
//             graph.setEdgeWeight(cd, 3.0);
//             graph.setEdgeWeight(ad, 4.0);
//
//             assertTrue(graph.removeVertex("b"));
//             assertEquals(Arrays.asList("a", "c", "d"), List.copyOf(graph.vertexSet()));
//             assertEquals(2, graph.edgeSet().size());
//             assertNull(graph.getEdge("a", "b"));
//             assertNull(graph.getEdge("b", "c"));
//             assertSame(ad, graph.getEdge("a", "d"));
//             assertEquals(3.0, graph.getEdgeWeight(cd));
//             assertEquals(4.0, graph.getEdgeWeight(ad));
//             assertEquals("c", graph.getEdgeSource(graph.getEdge("c", "d")));
//             assertEquals("d", graph.getEdgeTarget(graph.getEdge("c", "d")));
//             assertEquals(Arrays.asList("a", "d"), graph.shortestPath("a", "d").getVertexList());
//             assertEquals(1, graph.outDegreeOf("c"));
//         }
//     }
//
//     @Test
//     void undirectedSelfLoopDegreeMatchesJGraphT() {
//         try (IGraph<String, DefaultWeightedEdge> graph = new IGraph<>(
//             false, null, DefaultWeightedEdge::new)) {
//             assertTrue(graph.getType().isUndirected());
//             graph.addVertex("a");
//             graph.addVertex("b");
//             DefaultWeightedEdge loop = graph.addEdge("a", "a");
//             DefaultWeightedEdge ab = graph.addEdge("a", "b");
//
//             assertEquals(3, graph.degreeOf("a"));
//             assertEquals(3, graph.inDegreeOf("a"));
//             assertEquals(3, graph.outDegreeOf("a"));
//             assertEquals(Set.of(loop, ab), graph.edgesOf("a"));
//             assertEquals(1, graph.degreeOf("b"));
//         }
//     }
//
//     @Test
//     void graphTypeRestrictionsAndLiveSetViewsFollowJGraphT() {
//         DefaultGraphType type = new DefaultGraphType.Builder()
//             .directed()
//             .allowMultipleEdges(false)
//             .allowSelfLoops(false)
//             .weighted(false)
//             .build();
//         try (IGraph<String, String> graph = new IGraph<>(type)) {
//             Set<String> vertices = graph.vertexSet();
//             Set<String> edges = graph.edgeSet();
//             graph.addVertex("a");
//             graph.addVertex("b");
//             assertEquals(Set.of("a", "b"), vertices);
//
//             assertTrue(graph.addEdge("a", "b", "edge"));
//             assertFalse(graph.addEdge("a", "b", "edge"));
//             assertFalse(graph.addEdge("a", "b", "parallel"));
//             assertThrows(IllegalArgumentException.class, () -> graph.addEdge("a", "a", "loop"));
//             assertNull(graph.getEdge("missing", "a"));
//             assertNull(graph.getAllEdges("missing", "a"));
//             assertEquals(Set.of("edge"), edges);
//             assertEquals(1.0, graph.getEdgeWeight("edge"));
//             assertThrows(UnsupportedOperationException.class,
//                 () -> graph.setEdgeWeight("edge", 2.0));
//
//             assertTrue(edges.remove("edge"));
//             assertTrue(graph.edgeSet().isEmpty());
//             assertTrue(vertices.remove("b"));
//             assertFalse(graph.containsVertex("b"));
//         }
//     }
//
//     @Test
//     void shortestPathCanUseANonFirstParallelEdge() {
//         DefaultGraphType type = new DefaultGraphType.Builder().directed().weighted(true).build();
//         try (IGraph<String, String> graph = new IGraph<>(type)) {
//             graph.addVertex("a");
//             graph.addVertex("b");
//             assertTrue(graph.addEdge("a", "b", "expensive"));
//             assertTrue(graph.addEdge("a", "b", "cheap"));
//             graph.setEdgeWeight("expensive", 10.0);
//             graph.setEdgeWeight("cheap", 1.0);
//
//             GraphPath<String, String> path = graph.shortestPath("a", "b");
//             assertNotNull(path);
//             assertEquals(List.of("cheap"), path.getEdgeList());
//             assertEquals(1.0, path.getWeight());
//         }
//     }
//
//     @Test
//     void suppliersAndCloseAreSafe() {
//         AtomicInteger vertexIds = new AtomicInteger();
//         IGraph<Integer, DefaultWeightedEdge> graph = new IGraph<>(
//             false, vertexIds::getAndIncrement, DefaultWeightedEdge::new);
//         Integer first = graph.addVertex();
//         Integer second = graph.addVertex();
//         assertNotNull(graph.addEdge(first, second));
//         graph.close();
//         graph.close();
//         assertThrows(IllegalStateException.class, () -> graph.degreeOf(first));
//     }
// }
