package project1;

import main.Node;
import main.Operator;
import main.State;

public class WestrosNode extends Node {
    public WestrosNode(WestrosNode parent, WestrosState state, Operator operator, String strategy) {
        super(parent, state, operator, strategy);
    }

    @Override
    public int heuristic() {
        if (super.strategy.endsWith("1"))
            return (int) (Math.ceil(((WestrosState)state).whiteWalkers)/4);
       
        if (super.strategy.endsWith("2")) {
        	int max_dist = 0;
        	byte [][] grid = ((WestrosState)state).grid;
        	for (int i = 0; i < grid.length; i++) {
				for (int j = 0; j < grid[0].length; j++) {
					if(grid[i][j] == 3) {
						int dist = Math.abs(((WestrosState)state).yJon - i) + Math.abs(((WestrosState)state).xJon - j) - 1;
						max_dist = Math.max(dist, max_dist);
					}
				}
			}
        	return (int) (Math.ceil(((WestrosState)state).whiteWalkers)/4) + (max_dist * 12);
        }
            
        return 0;
    }
}
