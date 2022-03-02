import java.lang.reflect.Array;
import java.util.*;
import java.io.*;

        /*Authors: Tobias Schnabel, Raoul Dohmen, Obbe Pulles*/

public class Main {

    public static void main(String[] args) {
        //initialize
        ArrayList<Arc> arcs = new ArrayList<>();
        int NumberOfNodes;

        String filename = "P5 test instances/P5_5_7.txt";

        try{
            arcs = readArcList(filename);
            System.out.println("Arc list read succesfully.");
            NumberOfNodes = getNumNodes(filename);
            System.out.println("The graph has "+ NumberOfNodes + " Nodes.");
        }
        catch (FileNotFoundException e){
            e.printStackTrace();
        }

    } //close main


    public static ArrayList<Arc> readArcList(String filename)

        throws java.io.FileNotFoundException{
        File file = new File(filename);
        Scanner scanner = new Scanner(file);
        //get top line
        int numNodes = scanner.nextInt();
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
