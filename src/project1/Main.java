package project1;

import main.Node;
import main.SearchProblem;

import java.util.Stack;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Main class where GenGrid() and Search(...) methods are implemented and tested in the main method. 
 *
 */
public class Main {

    /**
     * Generates a grid randomly where:
     * the dimensions n*m range from 4*4 to 5*5
     * the number of white walkers varies between 1:7
     * the number of obstacles varies between 0:3
     * the positions of white walkers, obstacles, and dragon stone are generated randomly.
     */
    public static byte [][] GenGrid() {
        byte [][] grid = new byte [][] {
                {0, 0, 0, 3},
                {0, 3, 0, 3},
                {0, 0, 3, 0},
                {0, 0, 2, 1}
        };
        
//        int n = ThreadLocalRandom.current().nextInt(4, 6);
//        int m = ThreadLocalRandom.current().nextInt(4, 6);
//        byte [][] grid = new byte[n][m];
//        grid[n - 1][m - 1] = 1;
//        while(true)
//        {
//        	int stoney = ThreadLocalRandom.current().nextInt(0, n);
//            int stonex = ThreadLocalRandom.current().nextInt(0, m);
//            if(stoney != n - 1 || stonex != m - 1) {
//            	grid[stoney][stonex] = 2;
//            	break;
//            }
//            	
//        }
//        
//        int whitewalkers = ThreadLocalRandom.current().nextInt(1, 8);
//        int obstacles = ThreadLocalRandom.current().nextInt(0, 4);
//        while(whitewalkers > 0 || obstacles > 0)
//        {
//        	int random = ThreadLocalRandom.current().nextInt(0, 3);
//        	int y = ThreadLocalRandom.current().nextInt(0, n);
//            int x = ThreadLocalRandom.current().nextInt(0, m);
//            if(grid[y][x] == 0) {
//            	if(random == 1 && whitewalkers > 0) {
//            		grid[y][x] = 3;
//            		whitewalkers--;
//            	}
//            	else if(random == 2 && obstacles > 0) {
//            		grid[y][x] = 4;
//            		obstacles--;
//            	}
//            }
//        }
        
        return grid;

    }
    
    public static void print_grid(byte[][] grid) {
    	String res = "";
    	for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[0].length; j++) {
				switch(grid[i][j]){
				case 0: res += "- "; break;
				case 1: res+= "J "; break;
				case 2: res += "S "; break;
				case 3: res += "W "; break;
				case 4: res += "O "; break;
				}
			}
			res += "\n";
		}
    	System.out.println(res);
    }
    public static byte count_whitewalkers(byte[][] grid) {
    	byte count = 0;
    	for (int i = 0; i < grid.length; i++) 
			for (int j = 0; j < grid[0].length; j++) 
				if(grid[i][j] == 3)
					count++;
    	return count;
    }

    /**
     * @param grid: [][]byte, representing the initial state grid
     * @param strategy: String, one of the search strategies [BFS, DFS, ID, UC, GR1, GR2, A*1, A*2]
     * @param visualize: boolean, if set to true, visualize the steps of path to the final node.
     * Instantiates an initial WestrosState and passes it to a SaveWestros instance,
     *  then returns the result of the search. 
     */
    public static SearchProblem.Result Search(byte grid [][], String strategy, boolean visualize) {
    	
    	byte whitewalkers = count_whitewalkers(grid);
    	byte maxDragongalsses = (byte) ThreadLocalRandom.current().nextInt(1, 4);
        maxDragongalsses = 3;
    	WestrosState initial = new WestrosState(grid, (byte) (grid[0].length - 1), (byte) (grid.length - 1), (byte) 0, whitewalkers);
        initial.set_maxDragonglasses(maxDragongalsses);
        System.out.println("maxDragongalsses = " + maxDragongalsses + "\n");
        
        SearchProblem problem = new SaveWestros(initial);
        SearchProblem.Result result = problem.search(strategy);
        
        /*
         *  If visualize is set to true, show the states of all nodes in the path
         *  from the initial state to the final node in the discovered solution.
         */  
        if(visualize && result != null) {
            Node[] resultArray = result.resultArray;
            for (Node node: resultArray) {
                System.out.println(node.state);
            }
        }
        return result;
    }
    
    public static void main(String [] args) {
    	byte [][] grid = GenGrid();
    	print_grid(grid);
    	
        SearchProblem.Result result = Search(grid, "A*1", true);
        if(result == null)
        	System.out.println("No solution!");
        else
        	System.out.println(result);
    }

}
