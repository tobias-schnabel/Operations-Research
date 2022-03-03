import java.util.*;
import java.io.*;

        /*Authors: Tobias Schnabel, Raoul Dohmen, Obbe Pulles*/

public class Main {

    private static String filename;
    private static int numNodes = -1;

    public static void main(String[] args) {
        //initialize
        ArrayList<Arc> arcs = new ArrayList<>();

         filename = "P5 test instances/TEST.txt";

        try{
            arcs = readArcList(filename);
            System.out.println("Arc list read succesfully.");
            numNodes = getNumNodes(filename);
            System.out.println("The graph has "+ numNodes + " Nodes.");
            System.out.println("Original Graph:");
        }
        catch (FileNotFoundException e){
            e.printStackTrace();
        }

        Graph graph = new Graph(numNodes, arcs);
        graph.printMatrix(graph.asMatrix());

        Flow flow = new Flow(numNodes, graph.asMatrix());
        flow.solveExtensive(arcs);
        printArcList(arcs);


        test();

    } //close main

    public static void test(){
        ArrayList<Arc> arcs = new ArrayList<>();
        int numNodes = -1;

        String[] testList = {"P5 test instances/P5_5_7.txt", "P5 test instances/P5_6_9.txt", "P5 test instances/P5_7_11.txt", "P5 test instances/P5_11_18.txt", "P5 test instances/P5_12_21.txt", "P5 test instances/P5_96_187.txt", "P5 test instances/P5_96_528.txt", "P5 test instances/P5_160_285.txt", "P5 test instances/P5_160_912.txt", "P5 test instances/P5_200_483.txt"};
        for (String test : testList) {
            filename = test;
            System.out.print('\n');
            try {
                arcs = readArcList(filename);
                System.out.println("Arc list read succesfully.");
                numNodes = getNumNodes(filename);
                System.out.println("The graph has " + numNodes + " Nodes.");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            transformDown(arcs);

            Graph graph = new Graph(numNodes, arcs);
            Flow flow = new Flow(numNodes, graph.asMatrix());
            flow.solve(arcs);
            System.out.print('\n');

        }

    } //close test method

    public static void printArcList(ArrayList<Arc> arcList) {
        for (Arc arc : arcList) {
            System.out.println(arc);
        }
    }

    public static void transformDown(ArrayList<Arc> arcArrayList){
        for (Arc arc: arcArrayList){
            int from = arc.getOrigin();
            int to = arc.getDestination();
            arc.setOrigin(from - 1);
            arc.setDestination(to - 1);
        }
    }
    public static ArrayList<Arc> readArcList(String filename)

        throws java.io.FileNotFoundException{
        File file = new File(filename);
        Scanner scanner = new Scanner(file);
        //get top line
        int numArcs = scanner.nextInt();
        //declare list
        ArrayList<Arc> arcList = new ArrayList<>();

        for (int i = 0; i < numArcs; i++) {
            int origin = scanner.nextInt();
            int destination = scanner.nextInt();
            int weight = scanner.nextInt();
            Arc newArc = new Arc(origin, destination, weight);
            arcList.add(newArc);
        }

        scanner.close();

        return arcList;
    } //close exception



    public static int getNumNodes(String filename)

            throws java.io.FileNotFoundException{
            File file = new File(filename);
            Scanner scanner = new Scanner(file);
            //get top line
            int numNodes = scanner.nextInt();

            scanner.close();

            return numNodes;
        } //close exception

    } //close class
