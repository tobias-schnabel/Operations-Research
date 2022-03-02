import java.util.*;
import java.io.*;

        /*Authors: Tobias Schnabel, Raoul Dohmen, Obbe Pulles*/

public class Main {

    public static void main(String[] args) {

        String filename = "P5 test instances/P5_5_7.txt";

        try{
            //edges = readEdgeList(filename);
            System.out.println("Edges list has been read.");

        }
        catch (FileNotFoundException e){
            e.printStackTrace();
        }

    } //close main


//    public static ArrayList<ArrayList<Edge>> readEdgeList(String filename)
//
//            throws java.io.FileNotFoundException{
//        File file = new File(filename);
//        Scanner input = new Scanner (file);
//        int v = input.nextInt();
//        int e = input.nextInt();
//
//        ArrayList<ArrayList<Edge>> startEdges = new ArrayList<>();
//
//        for(int i = 0; i < v; i++){
//            ArrayList<Edge> endEdges = new ArrayList<>();
//            startEdges.add(endEdges);
//        }
//
//        for(int i = 0; i < e; i++){
//            ArrayList<Edge> endEdges = new ArrayList<>();
//
//            int from = input.nextInt();
//            int whereTo = input.nextInt();
//            int weight = input.nextInt();
//
//           // Edge edge = new Edge(whereTo, weight);
//            endEdges = startEdges.get(from - 1);
//            endEdges.add(edge);
//            startEdges.set((from - 1), endEdges);
//
//        }
//
//        input.close();
//
//        return startEdges;
//    }
} //close class
