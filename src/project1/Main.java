package project1;

import main.Node;
import main.SearchProblem;

public class Main {

    public static byte [][] GenGrid() {
        byte [][] grid = new byte [][] {
                {0, 0, 0, 3},
                {0, 3, 4, 2},
                {3, 0, 3, 0},
                {1, 0, 0, 0}
        };

        return grid;

    }

    public static Node Search(byte grid [][], String strategy, boolean visualize) {

        WestrosState initial = new WestrosState(grid, (byte) 0, (byte) 3, (byte) 1, (byte) 4);
        SearchProblem problem = new SaveWestros(initial);

        Node result = problem.search(strategy);
        System.out.println("total expanded nodes = " + problem.count_expanded_nodes());
        return result;
    }
    public static void main(String [] args) {
    	Node result = Search(GenGrid(), "GR1", false);
        Node result1 = Search(GenGrid(), "A*1", false);
        
        System.out.println(result);
    	System.out.println(result1);
    }
}
