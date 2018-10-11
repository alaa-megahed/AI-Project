package project1;

import main.SearchProblem;

public class Main {

    public static byte [][] GenGrid() {
        byte [][] grid = new byte [][] {
                {0, 0, 0, 0},
                {0, 3, 4, 0},
                {3, 0, 3, 0},
                {1, 2, 2, 2}
        };

        return grid;

    }

    public static void Search(byte grid [][], String strategy, boolean visualize) {

        WestrosState initial = new WestrosState(grid, (byte) 0, (byte) 3, (byte) 1, (byte) 3);
        SearchProblem problem = new SaveWestros(initial);
        problem.search("BFS");
    }
    public static void main(String [] args) {

    }
}
