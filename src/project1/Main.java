package project1;

import main.Node;
import main.SearchProblem;

import java.util.Stack;

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

    public static SearchProblem.Result Search(byte grid [][], String strategy, boolean visualize) {

        WestrosState initial = new WestrosState(grid, (byte) 0, (byte) 3, (byte) 1, (byte) 4);
        SearchProblem problem = new SaveWestros(initial);

        SearchProblem.Result result = problem.search(strategy);
        if(visualize) {
            Node[] resultArray = result.resultArray;
            for (Node node: resultArray) {
                System.out.println(node.state);
            }
        }
        return result;
    }
    public static void main(String [] args) {
        SearchProblem.Result resultBFS = Search(GenGrid(), "BFS", false);
        SearchProblem.Result resultGR = Search(GenGrid(), "GR1", true);
        System.out.println(resultGR);
    }

}
