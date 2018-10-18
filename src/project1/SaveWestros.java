package project1;

import main.Node;
import main.Operator;
import main.SearchProblem;
import main.State;

import java.util.ArrayList;

/**
 * A subclass from the SearchProblem class, that tackles SaveWestros search problem.
 *
 */
public class SaveWestros extends SearchProblem {

	
	
    /**
     * @param initial
     * The constructor takes the initial state as input. 
     * It also initializes the list of operators, along with their costs.
     */
    public SaveWestros(WestrosState initial) {

        Operator operators[] = new Operator[]{

                new Operator(1, 1, "Move up"), // move up
                new Operator(1, 2, "Move down"), //move down
                new Operator(1, 3, "Move left"), // move left
                new Operator(1, 4, "Move right"), // move right
                new Operator(8, 5, "Kill adjacent Whitewalkers"), // kill all adjacent 
                new Operator(0, 6, "Pick up dragon glasses") // pick up dragon glass
        };

        this.operators = operators;
        this.initial = initial;
    }

    /* 
     * The goal is reached if all white walkers in the grid are killed.
     */
    @Override
    public boolean goal_test(Node node) {
        return ((WestrosState)node.state).whiteWalkers == 0;
    }

    /*
     * Path cost is already saved in each node, as it depends on the previously defined costs of operators.
     */
    @Override
    public int path_cost(Node node) {
        return node.path_cost;
    }
      
	/* 
	 * Calculates the estimated cost to reach the goal from node.
	 * Two admissible heuristic functions are defined.  
	 */
	@Override
	public int heuristic(Node node, String strategy) {
		/*
    	 * First admissible heuristic function is defined as follows:
    	 * h(n) = ⌈the number of remaining white walkers / 3⌉ * 8.
    	 */
        if (strategy.endsWith("1"))
            return (int) (Math.ceil(((WestrosState)node.state).whiteWalkers)/3) * 8;
       
        /*
    	 * Second admissible heuristic function is defined as follows:
    	 * h(n) = ⌈the number of remaining white walkers / 3⌉ * 8
    	 * 		 + (the Manhattan distance between Jon and the farthest alive white walker - 1). 
    	 */
        if (strategy.endsWith("2")) {
        	int max_dist = 0;
        	byte [][] grid = ((WestrosState)node.state).grid;
        	for (int i = 0; i < grid.length; i++) {
				for (int j = 0; j < grid[0].length; j++) {
					if(grid[i][j] == 3) {
						int dist = Math.abs(((WestrosState)node.state).yJon - i) + Math.abs(((WestrosState)node.state).xJon - j) - 1;
						max_dist = Math.max(dist, max_dist);
					}
				}
			}
        	return (int) (Math.ceil(((WestrosState)node.state).whiteWalkers)/3)*8 + (max_dist);
        }
        
        /* In case of using uninformed search strategies */     
        return 0;	
	}
	
    /* 
     * Returns the a list of new nodes resulting from expanding a node.
     * It defines the preconditions and consequences of applying each operator.
     */
    @Override
    public ArrayList<Node> expand(Node node, String strategy) {
        WestrosState state = ((WestrosState)node.state);
        ArrayList<Node> result = new ArrayList<>();
        // Loop on all operators
        for (Operator op: operators) {
        	// Starting with the parent's state
            byte [][] grid = copy(state.grid);
            byte yJon_new = state.yJon;
            byte xJon_new = state.xJon;
            byte dragonGlasses_new = state.dragonGlasses;
            byte whiteWalkers_new = state.whiteWalkers;
            boolean add = false;
            /*
             * Cases 1:4 represent movements. 
             * Check the validity of the cell to which Jon is moving.
             * It has to be inside the grid borders, and it is either empty or Dragonstone.
             * Update the grid accordingly if the movement is valid.
             */
            switch (op.id) {
                case 1: {
                    if (state.yJon > 0 && (grid[yJon_new - 1][xJon_new] == 0 ||  grid[yJon_new - 1][xJon_new] == 2)) {
                    	grid[yJon_new][xJon_new] = (grid[yJon_new][xJon_new] == 1) ? (byte) 0 : 2;
                        yJon_new--;
                        grid[yJon_new][xJon_new] = (grid[yJon_new][xJon_new] == 0) ? (byte) 1 : 2;
                        add = true;
                    }
                } break;
                case 2: {
                    if (state.yJon < grid.length - 1 && (grid[yJon_new + 1][xJon_new] == 0 ||  grid[yJon_new + 1][xJon_new] == 2)) {
                    	grid[yJon_new][xJon_new] = (grid[yJon_new][xJon_new] == 1) ? (byte) 0 : 2;
                        yJon_new++;
                        grid[yJon_new][xJon_new] = (grid[yJon_new][xJon_new] == 0) ? (byte) 1 : 2;
                        add = true;
                    }
                } break;
                case 3: {
                    if (state.xJon > 0 && (grid[yJon_new][xJon_new - 1] == 0 ||  grid[yJon_new][xJon_new - 1] == 2)) {
                    	grid[yJon_new][xJon_new] = (grid[yJon_new][xJon_new] == 1) ? (byte) 0 : 2;
                        xJon_new--;
                        grid[yJon_new][xJon_new] = (grid[yJon_new][xJon_new] == 0) ? (byte) 1 : 2;
                        add = true;
                    }
                } break;
                case 4: {

                    if ( state.xJon < grid[0].length - 1 && (grid[yJon_new][xJon_new + 1] == 0 ||  grid[yJon_new][xJon_new + 1] == 2)) {
                    	grid[yJon_new][xJon_new] = (grid[yJon_new][xJon_new] == 1) ? (byte) 0 : 2;
                        xJon_new++;
                        grid[yJon_new][xJon_new] = (grid[yJon_new][xJon_new] == 0) ? (byte) 1 : 2;
                        add = true;
                    }
                } break;
                /*
                 * Case 5 (kill all adjacent white walkers if there is any and Jon holds at least one dragonglass).
                 * If applied, update the number of remaining white walkers and dragon glasses.
                 */
                case 5: {
                	if(dragonGlasses_new > 0){
                        byte dx [] = {0, 0, -1, 1};
                        byte dy [] = {1, -1, 0, 0};
                        for (int i = 0; i < dx.length; i++) {
                            byte x = (byte) (xJon_new + dx[i]);
                            byte y = (byte) (yJon_new + dy[i]);
                            if ( x >= 0 && x < grid[0].length && y >= 0 && y < grid.length) {
                                if (grid[y][x] == 3) {
                                    add = true;
                                    grid[y][x] = 0;
                                    whiteWalkers_new--;
                                }
                            }
                        }
                        if(add)
                        	dragonGlasses_new--;
                	}
        
                } break;
                /*
                 * Case 6 (pickup dragon galss).
                 * Applicable only if Jon is standing at the cell of dragon stone, 
                 * and not holding the maximum number of dragonglass pieces allowed for him.
                 * If applied, update the number of dragon glasses to be the maximum allowed for Jon.
                 */
                case 6: {
                	byte maxDragonglasses = ((WestrosState)node.state).get_maxDragonglasses();
                    if(dragonGlasses_new < maxDragonglasses && grid[state.yJon][state.xJon] == 2) {
                        dragonGlasses_new  = maxDragonglasses;
                        add = true;
                    }
                }
                default:
                    break;
            }
            // If the node is to be added, i.e., the operator was valid, then add new node to the list of children.
            if(add){
            	WestrosState new_state = new WestrosState(grid, xJon_new, yJon_new, dragonGlasses_new, whiteWalkers_new);
                new_state.set_maxDragonglasses(((WestrosState)node.state).get_maxDragonglasses());	
                Node new_node = new Node(node, new_state, op);
                int heuristic = heuristic(new_node, strategy);
                if(strategy.startsWith("GR"))
                	new_node.set_evaluate(heuristic);
                else
                	new_node.set_evaluate(heuristic + new_node.path_cost);
                result.add(new_node);
            }
            
        }
        // Return a list of children nodes.
        return result;
     }


    public static byte [][] copy (byte [][] grid) {
        byte [][] result = new byte[grid.length][grid[0].length];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                result[i][j] = grid[i][j];
            }
        }
        return result;
    }

}
