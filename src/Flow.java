import java.util.ArrayList;
import java.util.LinkedList;

public class Flow {
    private final int V; //number of Vertices
    private  int source;
    private  int sink;
    private final int[][] graph;
    private int max_flow;
    private final int[][] flows;

    public Flow(int numVert, int[][] graph) {
        V = numVert;
        this.source = 0;
        this.sink = V -1;
        this.graph = graph;
        this.max_flow = 0;
        this.flows = new int[V][V];
    }

    public void solve(ArrayList<Arc> arcList){
        this.max_flow = maxFlow();
        System.out.println(this);

        printMatrix(augmentingPath());
        this.relayFlows(arcList);
        System.out.println("Individual Arc flows:");
    }

    public void solveNoPrint(ArrayList<Arc> arcList){
        this.max_flow = maxFlow();

        source += 1;
        sink += 1;

        System.out.println(this);

        this.relayFlows(arcList);
    }

    @Override
    public String toString() {
        return "Flow " +
                "from " + source +
                " to " + sink +
                " has value of " + max_flow +
                " .";
    }

    public void relayFlows(ArrayList<Arc> arcs){
        int i,j;

        for (i = 0; i < this.V; i++){
            for (j = 0; j < this.V; j++){
                for (Arc arc : arcs){
                    if (i == arc.getOrigin() && arc.getDestination() == j){
                        arc.setFlow(this.flows[i][j]);
                    }
                }
            }
        }

    }

    int[][] augmentingPath() {

        int u,v;
        int[][] resid = this.makeResidGraph(); //init

        int[] predecessor = new int[this.V]; //to keep track of path taken

        while(isPath(resid, predecessor)) {
            int path_flow = Integer.MAX_VALUE; //init
            for(v = sink; v != source; v= predecessor[v]){
                u = predecessor[v];
                path_flow = Math.min(path_flow, resid[u][v]); //recursion
            }

            for(v = sink; v != source; v= predecessor[v]){
                u = predecessor[v];
                resid[u][v] -= path_flow; //adjust forward arc
                this.flows[u][v] += path_flow;
                resid[v][u] += path_flow; //adjust backward arc
//                this.flows[v][u] += path_flow;
            }

        }
        return this.flows;
    }

    int maxFlow() {

        int u,v;
        int max_flow = 0;       
        int[][] resid = this.makeResidGraph(); //init
        
        int[] predecessor = new int[this.V]; //to keep track of path taken

        while(isPath(resid, predecessor)) {
            int path_flow = Integer.MAX_VALUE; //init
            for(v = sink; v != source; v= predecessor[v]){
                u = predecessor[v];
                path_flow = Math.min(path_flow, resid[u][v]); //recursion
            }

            for(v = sink; v != source; v= predecessor[v]){
                u = predecessor[v];
                resid[u][v] -= path_flow; //adjust forward arc
                resid[v][u] += path_flow; //adjust backward arc
            }

            max_flow += path_flow;
        }
        return max_flow;
    }

    int[][] makeResidGraph(){
        int u,v;
        int[][] residGraph = new int[V][V];

        for (u = 0; u < V; u++)
            for (v = 0; v < V; v++)
                residGraph[u][v] = this.graph[u][v];
        
        return residGraph;
    }
    
    boolean isPath(int[][] residGraph, int[] predecessor) {
        boolean[] visited = new boolean[V]; // Create a visited array (all vertices not visited so false)

        // Create a queue, enqueue source vertex and mark
        // source vertex as visited
        LinkedList<Integer> queue = new LinkedList<>(); // Create a queue
        queue.add(0); //enqueue source vertex
        visited[0] = true; //mark source vertex as visited
        predecessor[0] = -1; //no node before the source

        // Standard BFS Loop (see geeksforgeeks)
        while (queue.size() != 0) {
            int u = queue.poll();

            for (int v = 0; v < V; v++) {
                if (!visited[v] && residGraph[u][v] > 0) {
                    /*
                     If we find a connection to the sink
                     node, then there is no point in BFS
                     anymore We just have to set its predecessor
                     and can return true
                    */
                    if (v == sink) {
                        predecessor[v] = u;
                        return true;
                    }
                    queue.add(v);
                    predecessor[v] = u;
                    visited[v] = true;
                }
            }
        }

        // We didn't reach sink in BFS starting from source,
        // so return false
        return false;
    }

    public void printMatrix(int[][] matrix){
        System.out.println("Final Graph:");
        for (int i = 0; i < this.V; i++) {
            int lineBreakCounter = 0;
            for (int j = 0; j < this.V; j++) {
                System.out.print(matrix[i][j] + " ");
                lineBreakCounter ++;
                if (lineBreakCounter == this.V) {
                    System.out.print("\n");
                    lineBreakCounter = 0;
                }
            } //inner for
        } // outer for
        System.out.print("\n");
    } //close method




} //close class
