package project1;

import main.Node;
import main.Operator;
import main.SearchProblem;
import main.State;

import java.util.ArrayList;

public class SaveWestros extends SearchProblem {


    public SaveWestros(WestrosState initial) {

        Operator operators[] = new Operator[]{

                new Operator(16, 1, "Move up"), // move up
                new Operator(16, 2, "Move down"), //move down
                new Operator(16, 3, "Move left"), // move left
                new Operator(16, 4, "Move right"), // move right
                new Operator(1, 5, "Kill adjacent Whitewalkers"), // kill adjacent
                new Operator(0, 6, "Pick up dragon glasses") // pick up dragon glass
        };

        this.operators = operators;
        this.initial = initial;
    }

    @Override
    public boolean goal_test(Node node) {
        return ((WestrosState)node.state).whiteWalkers == 0;
    }

    @Override
    public int path_cost(Node node) {
        return node.path_cost;
    }

    @Override
    public ArrayList<Node> expand(Node node, String strategy) {
        WestrosNode westrosNode = (WestrosNode) node;
        WestrosState state = ((WestrosState)node.state);
        ArrayList<Node> result = new ArrayList<>();
        for (Operator op: operators) {
            byte [][] grid = copy(state.grid);
            byte yJon_new = state.yJon;
            byte xJon_new = state.xJon;
            byte dragonGlasses_new = state.dragonGlasses;
            byte whiteWalkers_new = state.whiteWalkers;
            boolean add = false;
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
            if(add){
            	WestrosState new_state = new WestrosState(grid, xJon_new, yJon_new, dragonGlasses_new, whiteWalkers_new);
                new_state.set_maxDragonglasses(((WestrosState)node.state).get_maxDragonglasses());
            	WestrosNode new_node = new WestrosNode(westrosNode, new_state, op, strategy);
                result.add(new_node);
            }
            
        }
        return result;
     }

    @Override
    public Node createNode(State state, String strategy) {
        return new WestrosNode(null, (WestrosState) state, null, strategy);
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
