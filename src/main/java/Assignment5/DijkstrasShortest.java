package Assignment5;

import java.util.Scanner;
import java.util.stream.StreamSupport;

public class DijkstrasShortest {
    public static void main(String[] args) {
        //   a  b  c  d  e  f  g  h   i  j
        int[][] adjacencyMatrix = {{0, 0, 0, 0, 1, 0, 0, 10, 0, 0},   // a
                {0, 0, 2, 0, 0, 0, 0, 0, 0, 0},   // b
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},   // c
                {4, 0, 0, 0, 0, 0, 0, 1, 0, 0},   // d
                {0, 0, 0, 0, 0, 3, 0, 0, 0, 0},   // e
                {0, 1, 3, 0, 0, 0, 7, 0, 1, 0},   // f
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},   // g
                {0, 0, 0, 0, 5, 0, 0, 0, 9, 0},   // h
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 2},   // i
                {0, 0, 0, 0, 0, 0, 1, 0, 0, 0}}; // j

        boolean cont;
        do {
            Scanner input = new Scanner(System.in);
            System.out.print("Enter source node: ");
            String startNode = input.nextLine();

            if (!validateInput(startNode, 'a', 'j')) {
                cont = true;
                System.out.println("Please enter a valid source node.");
            } else {
                cont = false;
            }
        } while (cont);
        dijkstraAlgorithm(adjacencyMatrix, "a");
    }


    /**
     * validates user input is between the available nodes
     *
     * @param sourceNode - user input, selected source node
     * @param startNode  - node char range starts at ..
     * @param endNode    - node char range ends at (inclusive)..
     * @return - the source node selected is within the range available (truth value)
     */
    private static boolean validateInput(String sourceNode, char startNode, char endNode) {
        if (sourceNode.length() != 1) {
            return false;
        } else {
            return sourceNode.toLowerCase().charAt(0) >= startNode && sourceNode.toLowerCase().charAt(0) <= endNode;
        }
    }

    private static void printArr(Vertice[] vertices){
        System.out.print("[");
        for (int i = 0; i < vertices.length; i++){
            System.out.print("(" + vertices[i].name + ": " + vertices[i].distSrc + "), ");
        }
        System.out.println("]");
    }

    private static void dijkstraAlgorithm(int[][] adjacencyMatrix, String source) {
        Vertice[] vertices = new Vertice[adjacencyMatrix.length]; // current distance of a vertice from the source
        String str = "a";
        for (int i = 0; i < vertices.length; i++) {
            vertices[i] = new Vertice(str);
            str = Character.toString(str.charAt(0) + 1);
            
        }
        vertices[getSourceIndex(source)].distSrc = 0;
        Vertice[] toBeChecked = vertices;

        while (toBeChecked.length > 0) {
            printArr(toBeChecked);
            //smallest vertex value?
            int minVertex = getMinVertice(toBeChecked);
            Vertice currentNode = toBeChecked[minVertex];    // current node
            toBeChecked = deletedNodeArray(toBeChecked, minVertex);//leftover nodes after initial is removed
            for (int i = 0; i < adjacencyMatrix[minVertex].length; i++) { // all the nodes in the row of the node we are checking
                if (adjacencyMatrix[minVertex][i] > 0) { // if the node is adjacent to the current node
                    if (vertices[i].distSrc > currentNode.distSrc + adjacencyMatrix[minVertex][i]) {
                        vertices[i].distSrc = currentNode.distSrc + adjacencyMatrix[minVertex][i];
                        vertices[i].predecessor = currentNode;
                    }
                }
            }
            adjacencyMatrix=deletedMatrixArray(adjacencyMatrix,minVertex);
        }

        printResults(vertices, source);
    }

    private static void printResults(Vertice[] vertices, String source) {
        System.out.println("|---------------------------------|");
        for (int i = 0; i < vertices.length; i++) {
            System.out.println("| " + vertices[i].name + " | " + getPath(vertices[i], i, source));
        }
        System.out.println("|---------------------------------|");
    }

    private static String getPath(Vertice vertex, int index, String source) {
        System.err.println(vertex.name + ": " + vertex.distSrc);
        String path = "";
        String deliminator = " -> ";
        Vertice current = vertex;
        while (current.predecessor != null) {
            path = deliminator + current.name + " " + path;
            current = current.predecessor;
        }
        return source + path;
    }

    private static Vertice[] deletedNodeArray(Vertice[] vertices, int index) {
        if (vertices.length >= 1) {
            Vertice[] newVertices = new Vertice[vertices.length - 1];
            for (int i = 0, k = 0; i < vertices.length; i++) {
                if (i != index) {
                    newVertices[k] = vertices[i];
                    k++;
                }
            }
            return newVertices;
        } else return new Vertice[0];
    }
    
    //new method to remove a row of the adjacency matrix once it has been used
    private static int[][] deletedMatrixArray(int[][] adjacencyMatrix, int index) {
        if (adjacencyMatrix.length >= 1) {
            int[][] newAdjacencyMatrix = new int[adjacencyMatrix.length-1][10];
            for (int i = 0, k = 0; i < adjacencyMatrix.length; i++) {
                if (i != index) {
                    newAdjacencyMatrix[k] = adjacencyMatrix[i];
                    k++;
                }
            }
            return newAdjacencyMatrix;
        }else return new int[0][0];
    }
    
    private static int getMinVertice(Vertice[] vertices) {
        //initializing new variable
        int min_value=vertices[0].distSrc;
        int min_index=0;
        for (int i = 1; i < vertices.length; i++) {
            if (vertices[i].distSrc < min_value){
                min_value=vertices[i].distSrc;
                min_index=i;
            }
        }
        System.out.println("Min is " + min_index);
        return min_index;
    }

    /**
     * return the source node's index in the digraph
     *
     * @param source source node
     * @return source index
     */
    private static int getSourceIndex(String source) {
        return switch (source.toLowerCase()) {
            case "a" -> 0;
            case "b" -> 1;
            case "c" -> 2;
            case "d" -> 3;
            case "e" -> 4;
            case "f" -> 5;
            case "g" -> 6;
            case "h" -> 7;
            case "i" -> 8;
            case "j" -> 9;
            case "k" -> 10;
            case "l" -> 11;
            case "m" -> 12;
            case "n" -> 13;
            case "o" -> 14;
            case "p" -> 15;
            case "q" -> 16;
            case "r" -> 17;
            case "s" -> 18;
            case "t" -> 19;
            case "u" -> 20;
            case "v" -> 21;
            case "w" -> 22;
            case "x" -> 23;
            case "y" -> 24;
            case "z" -> 25;
            default -> -1;
        };
    }

    private static String getSourceName(int index) {
        return switch (index) {
            case 0 -> "a";
            case 1 -> "b";
            case 2 -> "c";
            case 3 -> "d";
            case 4 -> "e";
            case 5 -> "f";
            case 6 -> "g";
            case 7 -> "h";
            case 8 -> "i";
            case 9 -> "j";
            case 10 -> "k";
            case 11 -> "l";
            case 12 -> "m";
            case 13 -> "n";
            case 14 -> "o";
            case 15 -> "p";
            case 16 -> "q";
            case 17 -> "r";
            case 18 -> "s";
            case 19 -> "t";
            case 20 -> "u";
            case 21 -> "v";
            case 22 -> "w";
            case 23 -> "x";
            case 24 -> "y";
            case 25 -> "z";
            default -> "";
        };
    }
}
