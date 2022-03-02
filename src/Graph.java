import java.util.ArrayList;
import java.util.LinkedList;

public class Graph {

    private final int numVertices;
    final ArrayList<Arc> arcList;
    int[][] adjMatrix;
    int[][] residMat;

    public Graph(int numVertices, ArrayList<Arc> arcList) {
        this.numVertices = numVertices;
        this.arcList = arcList;
    }

    public void init(){
        this.getMatrix();
        this.printMatrix();
    }
    int[][] getMatrix() {
        int size = this.numVertices + 1;
        this.adjMatrix = new int[size][size];

        for (int i = 0; i < size; i++){
            for (int j = 0; j < size; j++){
//                for(int n = 0 ; n < arcList.size() ; n++){
//                    if(i == j){
//                        this.adjMatrix[i][j] = 0;
//                    }
//                    else if(arcList.get(n).getOrigin() == i && arcList.get(n).getDestination() == j){ // if there exist an arc n which goes from i to j, add its weight to the matrix
//                        this.adjMatrix[i][j] = arcList.get(n).getWeight();
//                        break;
//                    }
//                    else{                                                                     // otherwise assign a maximum value
//                        this.adjMatrix[i][j] = 999;
//                    }
//                }
                if (i == j) {
                    this.adjMatrix[i][j] = 0;
                } else {
                    for (Arc arc : arcList) {
                        if (arc.getOrigin()-1 == i && arc.getDestination()-1 == j) { //-1 to account for array index shift
                            this.adjMatrix[i][j] = arc.getWeight();
                            break;
                        } else {
                            this.adjMatrix[i][j] = 0;
                        }
                } //innermost
                }
            } //inner
        } //outer

    return this.adjMatrix;
    }

    void setFinalFlows(int[][] finalresid, int[][] graph, ArrayList<Arc> arclist){
        //flow from u to v is graph[u][v]
        int u,v;
        int V = graph.length;

        for (u=0; u < V; u++) {
            for (v = 0; v < V; v++) {
                for (Arc arc : arcList) {
                    if (arc.getOrigin() == u && arc.getDestination() == v){
                        arc.setFlow(graph[u][v]);
                        break;
                    }
                }
            }
        }


    }

    int[][] getResid() {
        int u,v;
        int V = this.getNumVertices();
        this.residMat = new int[V][V];
        for (u = 0; u < V; u++)
            for (v = 0; v < V; v++)
                this.residMat[u][v] = this.adjMatrix[u][v];

        return this.residMat;
    }

    public int getNumVertices() {
        return numVertices;
    }

    public void printMatrix(){
        for (int i = 0; i < this.getNumVertices(); i++) {
            int lineBreakCounter = 0;
            for (int j = 0; j < this.getNumVertices(); j++) {
                System.out.print(this.adjMatrix[i][j] + " ");
                lineBreakCounter ++;
                if (lineBreakCounter == this.getNumVertices()) {
                    System.out.print("\n");
                    lineBreakCounter = 0;
                }
            } //inner for
        } // outer for
        System.out.print("\n");
    } //close method

} //close class

