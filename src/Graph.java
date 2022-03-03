import java.util.ArrayList;

public class Graph {
    private final int numVertices;
    private final ArrayList<Arc> arcList;


    public Graph(int numVertices, ArrayList<Arc> arcList) {
        this.numVertices = numVertices;
        this.arcList = arcList;
    }

    public int[][] asMatrix() {
        int size = this.numVertices;
        int[][] matrix = new int[size][size];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (i == j) {
                    matrix[i][j] = 0;
                } else {
                    for (Arc arc : arcList) {
                        if (arc.getOrigin() == i && arc.getDestination() == j) {
                            matrix[i][j] = arc.getWeight();
                            break;
                        } else {
                            matrix[i][j] = 0;
                        }
                    } //innermost
                }
            } //inner
        } //outer

        return matrix;
    } //method

    public void printMatrix(int[][] matrix){
        for (int i = 0; i < this.numVertices; i++) {
            int lineBreakCounter = 0;
            for (int j = 0; j < this.numVertices; j++) {
                System.out.print(matrix[i][j] + " ");
                lineBreakCounter ++;
                if (lineBreakCounter == this.numVertices) {
                    System.out.print("\n");
                    lineBreakCounter = 0;
                }
            } //inner for
        } // outer for
        System.out.print("\n");
    } //close method

} //close class
