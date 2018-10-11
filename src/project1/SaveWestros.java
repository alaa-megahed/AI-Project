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
                new Operator(2, 5), // kill adjacent
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
        byte [][] grid = state.grid.clone();
        byte yJon_new = state.yJon;
        byte xJon_new = state.xJon;
        byte dragonGlasses_new = state.dragonGlasses;
        byte whiteWalkers_new = state.whiteWalkers;
        boolean add = false;
        for (Operator op: operators) {
            switch (op.id) {
                case 1: {
                    if (state.yJon > 0) {
                        grid[yJon_new][xJon_new] = 0;
                        yJon_new--;
                        grid[yJon_new][xJon_new] = 1;
                        add = true;
                    }
                }
                case 2: {
                    if (state.yJon < grid.length - 1) {
                        grid[yJon_new][xJon_new] = 0;
                        yJon_new++;
                        grid[yJon_new][xJon_new] = 1;
                        add = true;
                    }
                }
                case 3: {
                    if (state.xJon > 0) {
                        grid[yJon_new][xJon_new] = 0;
                        xJon_new--;
                        grid[yJon_new][xJon_new] = 1;
                        add = true;
                    }
                }
                case 4: {

                    if (state.xJon < grid[0].length - 1) {
                        grid[yJon_new][xJon_new] = 0;
                        xJon_new++;
                        grid[yJon_new][xJon_new] = 1;
                        add = true;
                    }
                }
                case 5: {
                    byte dx [] = {0, 0, -1, 1};
                    byte dy [] = {1, -1, 0, 0};
                    for (int i = 0; i < dx.length; i++) {
                        byte x = (byte) (xJon_new + dx[i]);
                        byte y = (byte) (yJon_new + dy[i]);

                        if ( x > 0 && x < grid[0].length && y > 0 && y < grid.length) {
                            if (grid[x][y] == 3) {
                                add = true;
                                grid[x][y] = 0;
                                whiteWalkers_new--;
                            }
                        }

                    }
                }
                case 6: {
                    if(grid[state.yJon][state.xJon] == 2) {
                        dragonGlasses_new  = WestrosState.maxDragonglasses;
                        add = true;
                    }
                }
                default:
                    break;
            }

            WestrosState new_state = new WestrosState(grid, xJon_new, yJon_new, dragonGlasses_new, whiteWalkers_new);
            Node new_node = new Node(node, new_state, op, 0);
            result.add(new_node);
        }
        return result;
     }

}
