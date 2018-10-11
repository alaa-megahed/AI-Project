package project1;

import main.Node;
import main.SearchProblem;

public class Main {

    public static byte [][] GenGrid() {
        byte [][] grid = new byte [][] {
                {0, 0, 0, 0},
                {0, 3, 4, 0},
                {3, 0, 3, 0},
                {1, 2, 0, 0}
        };

        return grid;

    }

    public static void Search(byte grid [][], String strategy, boolean visualize) {

        WestrosState initial = new WestrosState(grid, (byte) 0, (byte) 3, (byte) 1, (byte) 3);
        SearchProblem problem = new SaveWestros(initial);
        Node node = problem.search(strategy);
        System.out.println(node);
    }
    public static void main(String [] args) {
        byte [][] grid = GenGrid();
        Search(grid, "DFS", false);
    }
}
