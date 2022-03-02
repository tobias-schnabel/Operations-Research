import java.util.ArrayList;
import java.util.LinkedList;

public class Graph {

    private final int numVertices;
    private final ArrayList<Arc> arcList;
    int[][] adjMatrix;

    public Graph(int numVertices, ArrayList<Arc> arcList) {
        this.numVertices = numVertices;
        this.arcList = arcList;
    }

    int[][] getMatrix() {
        int size = this.numVertices;
        int[][] matrix = new int[size][size];

        for (int i =0; i < size; i++){
            for (int j = 0; j< size; j++){
                for (Arc arc : arcList) {

                    if (arc.getOrigin() == i && arc.getDestination() ==j){
                        matrix[i][j] = arc.getWeight();
                        break;
                    } else {
                        matrix[i][j] = 0;
                    }
                } //innermost
            } //inner
        } //outer

    return matrix;
    }

    public int getNumVertices() {
        return numVertices;
    }
} //close class

