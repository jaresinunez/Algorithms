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
        System.out.println("hello world");
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
}
