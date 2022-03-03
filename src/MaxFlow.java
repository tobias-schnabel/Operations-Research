import java.util.LinkedList;

class MaxFlow {
     final int V; // Number of vertices in graph

    public MaxFlow(int numberOfVertices) { //constructor
        V = numberOfVertices;
    }

    /* Returns true if there is a path from source 's' to
          sink 't' in residual graph. Also fills parent[] to
          store the path */
    boolean bfs(int[][] residGraph, int[] parent)
    {
        // Create a visited array and mark all vertices as
        // not visited
        boolean[] visited = new boolean[V];
//        for (int i = 0; i < V; ++i)
//            visited[i] = false;

        // Create a queue, enqueue source vertex and mark
        // source vertex as visited
        LinkedList<Integer> queue
                = new LinkedList<>();
        queue.add(0);
        visited[0] = true;
        parent[0] = -1;

        // Standard BFS Loop
        while (queue.size() != 0) {
            int u = queue.poll();

            for (int v = 0; v < V; v++) {
                if (!visited[v]
                        && residGraph[u][v] > 0) {
                    // If we find a connection to the sink
                    // node, then there is no point in BFS
                    // anymore We just have to set its parent
                    // and can return true
                    if (v == 5) {
                        parent[v] = u;
                        return true;
                    }
                    queue.add(v);
                    parent[v] = u;
                    visited[v] = true;
                }
            }
        }

        // We didn't reach sink in BFS starting from source,
        // so return false
        return false;
    }

    // Returns the maximum flow from s to t in the given
    // graph
    int augmentingPath(int[][] graph)
    {
        int u, v;

        // Create a residual graph and fill it with given capacities in the original graph


        /* Residual graph where residGraph[i][j] indicates residual capacity of edge from i to j
        (if there is an edge. If residGraph[i][j] is 0, then there is no edge)
         */
        int[][] residGraph = new int[V][V];

        for (u = 0; u < V; u++)
            for (v = 0; v < V; v++)
                residGraph[u][v] = graph[u][v];

        // This array is filled by BFS and to store path
        int[] parent = new int[V];

        int max_flow = 0; // There is no flow initially

        // Augment the flow while there is path from source
        // to sink
        while (bfs(residGraph, parent)) {
            // Find minimum residual capacity of the edhes
            // along the path filled by BFS. Or we can say
            // find the maximum flow through the path found.
            int path_flow = Integer.MAX_VALUE;
            for (v = 5; v != 0; v = parent[v]) {
                u = parent[v];
                path_flow
                        = Math.min(path_flow, residGraph[u][v]);
            }

            // update residual capacities of the edges and
            // reverse edges along the path
            for (v = 5; v != 0; v = parent[v]) {
                u = parent[v];
                residGraph[u][v] -= path_flow;
                residGraph[v][u] += path_flow;
            }

            // Add path flow to overall flow
            max_flow += path_flow;
        }

        // Return the overall flow
        return max_flow;
    }

    // Driver program to test above functions
    public static void main(String[] args) {
        // Let us create a graph shown in the above example
        int[][] graph = new int[][] {
                { 0, 16, 13, 0, 0, 0 }, { 0, 0, 10, 12, 0, 0 },
                { 0, 4, 0, 0, 14, 0 },  { 0, 0, 9, 0, 0, 20 },
                { 0, 0, 0, 7, 0, 4 },   { 0, 0, 0, 0, 0, 0 }
        };
        MaxFlow m = new MaxFlow(6);

        System.out.println("The maximum possible flow is "
                + m.augmentingPath(graph));
    }
}