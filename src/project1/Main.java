package project1;

import main.Node;
import main.SearchProblem;

import java.util.Stack;
import java.util.concurrent.ThreadLocalRandom;

public class Main {

    public static byte [][] GenGrid() {
//        byte [][] grid = new byte [][] {
//                {0, 0, 0, 3},
//                {0, 3, 4, 2},
//                {3, 0, 3, 0},
//                {1, 0, 0, 0}
//        };
        
        int n = ThreadLocalRandom.current().nextInt(4, 5);
        int m = ThreadLocalRandom.current().nextInt(4, 5);
        byte [][] grid = new byte[n][m];
        grid[n - 1][m - 1] = 1;
        while(true)
        {
        	int stoney = ThreadLocalRandom.current().nextInt(0, n);
            int stonex = ThreadLocalRandom.current().nextInt(0, m);
            if(stoney != n - 1 || stonex != m - 1) {
            	grid[stoney][stonex] = 2;
            	break;
            }
            	
        }
        
        int whitewalkers = ThreadLocalRandom.current().nextInt(3, 5);
        int obstacles = ThreadLocalRandom.current().nextInt(1, 3);
        while(whitewalkers > 0 || obstacles > 0)
        {
        	int random = ThreadLocalRandom.current().nextInt(0, 3);
        	int y = ThreadLocalRandom.current().nextInt(0, n);
            int x = ThreadLocalRandom.current().nextInt(0, m);
            if(grid[y][x] == 0) {
            	if(random == 1 && whitewalkers > 0) {
            		grid[y][x] = 3;
            		whitewalkers--;
            	}
            	else if(random == 2 && obstacles > 0) {
            		grid[y][x] = 4;
            		obstacles--;
            	}
            }
        }
        
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

    public static SearchProblem.Result Search(byte grid [][], String strategy, boolean visualize) {
    	
    	byte whitewalkers = count_whitewalkers(grid);
    	byte maxDragongalsses = (byte) ThreadLocalRandom.current().nextInt(1, 4);
        WestrosState initial = new WestrosState(grid, (byte) (grid[0].length - 1), (byte) (grid.length - 1), maxDragongalsses, whitewalkers);
        initial.set_maxDragonglasses(maxDragongalsses);
        System.out.println("maxDragongalsses = " + maxDragongalsses + "\n");
        SearchProblem problem = new SaveWestros(initial);

        SearchProblem.Result result = problem.search(strategy);
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
        SearchProblem.Result result = Search(grid, "A*2", true);
        System.out.println(result);
    }

}
