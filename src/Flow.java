import java.util.LinkedList;

public class Flow {
    final Graph graph;
    private final int[][] matrix;

    public Flow(Graph inputGraph) {
        this.graph = inputGraph;
        this.matrix = this.graph.getMatrix();
        int V = this.graph.getNumVertices();
    }

    //method to check whether there exists a directed path from s to t
    boolean findPath(int source, int sink, int[][] residGraph, int[] parent){
        //init
        int V = graph.getNumVertices();
        boolean[] visited = new boolean[V];
        // Create a queue, enqueue source vertex and mark source vertex as visited
        LinkedList<Integer> q = new LinkedList<>();
        q.add(source);
        visited[source] = true;
        parent[source] = -1;

        // Breadth-first search loop
        while (! q.isEmpty()) {
            int u = q.poll();

            for (int v = 0; v < V; v++) {
                if (!visited[v] && residGraph[u][v] > 0) {

                    if (v == sink) { //source and sink connected, set parent and ret true
                        parent[v] = u;
                        return true;
                    }

                    q.add(v);
                    parent[v] = u;
                    visited[v] = true;
                }
            } //close for
        }

        return false; //sink not reachable
    } //close method

    int augmentingPath(int source, int sink){
        int V = graph.getNumVertices();
        int[][] residGraph = new int[V][V];

        //make residual graph
        for (int u = 0; u < V; u++) System.arraycopy(this.matrix[u], 0, residGraph[u], 0, V);

        int[] parent = new int[V]; //array to store the path

        int maxFlow = 0; //init
        int u, v; //loop indices

        while(this.findPath(1, 5, residGraph, parent)){ //while a path exists
            int path_flow = Integer.MAX_VALUE;
            for (v = sink; v != source; v = parent[v]) {
                u = parent[v];
                path_flow = Math.min(path_flow, residGraph[u][v]);
            }

            // update residual capacities of the edges and reverse edges along the path
            for (v = sink; v != source; v = parent[v]) {
                u = parent[v];
                residGraph[u][v] -= path_flow;
                residGraph[v][u] += path_flow;
            } //close for

            // Add path flow to overall flow
            maxFlow += path_flow;
        } //close while

        // Return highest flow found
        return maxFlow;
    }


} //close class
