package project1;

import main.Node;
import main.Operator;
import main.SearchProblem;

import java.util.ArrayList;

public class SaveWestros extends SearchProblem {


    public SaveWestros(WestrosState initial) {

        Operator operators[] = new Operator[]{
                new Operator(1, 1), // move up
                new Operator(1, 2), //move down
                new Operator(1, 3), // move left
                new Operator(1, 4), // move right
                new Operator(20, 5), // kill adjacent
                new Operator(0, 6) // pick up dragon glass
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
    public ArrayList<Node> expand(Node node) {
        WestrosState state = ((WestrosState)node.state);
        ArrayList<Node> result = new ArrayList<>();

        for (Operator op: operators) {
            // deepcopy grid array
            byte [][] grid = new byte[state.grid.length][state.grid[0].length];
            for (int i = 0; i < grid.length; i++)
                for(int j = 0; j < grid[0].length; j++)
                    grid[i][j] = state.grid[i][j];
            //initialize parameters for new state
            byte yJon_new = state.yJon;
            byte xJon_new = state.xJon;
            byte dragonGlasses_new = state.dragonGlasses;
            byte whiteWalkers_new = state.whiteWalkers;
            boolean add = false;
            boolean steppedOnStone = false; //indicates whether Jon has been in a dragonstone cell
            switch (op.id) {
                case 1: {
                    //move is valid
                    if (valid(grid, ((byte)(yJon_new - 1)), xJon_new)) {
                        //if Jon was in a dragonstone cell, return it to its original state
                        if (steppedOnStone) {
                            grid[yJon_new][xJon_new] = 2;
                            steppedOnStone = false;
                        } else {
                            //if Jon is an empty cell, change it back to empty
                            grid[yJon_new][xJon_new] = 0;
                        }
                        yJon_new--;
                        //make the move
                        grid[yJon_new][xJon_new] = 1;
                        //if the new cell is a dragonstone cell, set the flag to true again
                        if(grid[yJon_new][xJon_new] == 2) {
                            steppedOnStone = true;
                        }
                        add = true;
                    }
                    break;
                }
                case 2: {
                    if (valid(grid, ((byte)(yJon_new + 1)), xJon_new )) {
                        if (steppedOnStone) {
                            grid[yJon_new][xJon_new] = 2;
                            steppedOnStone = false;
                        } else {
                            grid[yJon_new][xJon_new] = 0;
                        }
                        yJon_new++;
                        grid[yJon_new][xJon_new] = 1;
                        if(grid[yJon_new][xJon_new] == 2) {
                            steppedOnStone = true;
                        }
                        add = true;
                    }
                    break;
                }
                case 3: {
                    if (valid(grid, yJon_new, ((byte) (xJon_new - 1)))) {
                        if (steppedOnStone) {
                            grid[yJon_new][xJon_new] = 2;
                            steppedOnStone = false;
                        } else {
                            grid[yJon_new][xJon_new] = 0;
                        }
                        xJon_new--;
                        grid[yJon_new][xJon_new] = 1;
                        if(grid[yJon_new][xJon_new] == 2) {
                            steppedOnStone = true;
                        }
                        add = true;
                    }
                    break;
                }
                case 4: {

                    if (valid(grid, yJon_new, ((byte) (xJon_new + 1)))) {
                        if (steppedOnStone) {
                            grid[yJon_new][xJon_new] = 2;
                            steppedOnStone = false;
                        } else {
                            grid[yJon_new][xJon_new] = 0;
                        }
                        xJon_new++;
                        grid[yJon_new][xJon_new] = 1;
                        if(grid[yJon_new][xJon_new] == 2) {
                            steppedOnStone = true;
                        }
                        add = true;
                    }
                    break;
                }
                case 5: {
                    byte dx [] = {0, 0, -1, 1};
                    byte dy [] = {1, -1, 0, 0};
                    for (int i = 0; i < dx.length; i++) {
                        byte x = (byte) (xJon_new + dx[i]);
                        byte y = (byte) (yJon_new + dy[i]);

                        if ( x >= 0 && x < grid[0].length && y >= 0 && y < grid.length && state.dragonGlasses > 0) {
                            if (grid[y][x] == 3) {
                                add = true;
                                grid[y][x] = 0;
                                whiteWalkers_new--;
                            }
                        }

                    }
                    break;
                }
                case 6: {
                    if(grid[yJon_new][xJon_new] == 2) {
                        dragonGlasses_new  = WestrosState.maxDragonglasses;
                        add = true;
                    }
                    break;
                }
                default:
                    break;
            }

            if(add) {
                WestrosState new_state = new WestrosState(grid, xJon_new, yJon_new, dragonGlasses_new, whiteWalkers_new);
                Node new_node = new Node(node, new_state, op, 0);
                result.add(new_node);
            }
        }
        return result;
     }

     public static boolean valid(byte [][] grid, byte x, byte y) {
            return x >= 0 && x < grid[0].length && y >= 0 && y < grid.length && grid[x][y] != 3 && grid[x][y] != 4;
     }

}
