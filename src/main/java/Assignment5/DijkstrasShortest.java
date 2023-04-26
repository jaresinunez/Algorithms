package Assignment5;

import java.util.Scanner;

public class DijkstrasShortest {
    public static void main(String[] args) {
                                //   a  b  c  d  e  f  g  h   i  j
        int [][] adjacencyMatrix = {{0, 0, 0, 0, 1, 0, 0, 10, 0, 0},   // a
                                    {0, 0, 2, 0, 0, 0, 0, 0,  0, 0},   // b
                                    {0, 0, 0, 0, 0, 0, 0, 0,  0, 0},   // c
                                    {4, 0, 0, 0, 0, 0, 0, 1,  0, 0},   // d
                                    {0, 0, 0, 0, 0, 3, 0, 0,  0, 0},   // e
                                    {0, 1, 3, 0, 0, 0, 7, 0,  1, 0},   // f
                                    {0, 0, 0, 0, 0, 0, 0, 0,  0, 0},   // g
                                    {0, 0, 0, 0, 5, 0, 0, 0,  9, 0},   // h
                                    {0, 0, 0, 0, 0, 0, 0, 0,  0, 2},   // i
                                    {0, 0, 0, 0, 0, 0, 1, 0,  0, 0} }; // j

        boolean cont;
        do {
            Scanner input = new Scanner(System.in);
            System.out.print("Enter source node: ");
            String startNode = input.nextLine();

            if (!validateInput(startNode, 'a', 'j')){
                cont = true;
                System.out.println("Please enter a valid source node.");
            } else {
                cont = false;
            }
        } while (cont);
    }


    /**
     * validates user input is between the available nodes
     * @param sourceNode - user input, selected source node
     * @param startNode - node char range starts at ..
     * @param endNode - node char range ends at (inclusive)..
     * @return - the source node selected is within the range available (truth value)
     */
    private static boolean validateInput(String sourceNode, char startNode, char endNode) {
        if (sourceNode.length() != 1){
            return false;
        } else {
            return sourceNode.toLowerCase().charAt(0) >= startNode && sourceNode.toLowerCase().charAt(0) <= endNode;
        }
    }

    private static void dijkstraAlgorithm(int[][] digraph, String source){
        Vertice[] vertices = new Vertice[digraph.length]; // current distance of a vertice from the source
        for (int i = 0; i < vertices.length; i++) { vertices[i] = new Vertice(); }
        vertices[getSourceIndex(source)].distSrc = 0;

        int count = 0;
        while(count < vertices.length){
            Vertice v = vertices[getMinVertice(vertices)];

            count++;
        }

    }

    private static int getMinVertice(Vertice[] vertices){
        int min = 0;
        for (int i = 0; i < vertices.length-2; i++) {
            if (vertices[i].distSrc < vertices[i+1].distSrc)
                min = i;
        }
        return min;
    }

    /**
     * return the source node's index in the digraph
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
}
