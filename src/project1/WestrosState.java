package project1;

import main.Operator;
import main.State;

/**
 * A subclass from the abstract State class.
 * It contains all the information that describes a state in the SaveWestros search problem.
 * A state is represented by a grid, where each cell has a value in [0:4],
 * (0 => empty, 1 => Jon, 2 => dragonstone, 3 => white walker, 4 => obstacle),
 * The state also has the number of remaining dragonglasses, the alive white walkers,
 *  and the maximum number of dragonglasses allowed to Jon.
 */
public class WestrosState extends State {

    public byte grid[][];
    public byte xJon, yJon, dragonGlasses, whiteWalkers;
    public byte maxDragonglasses;

    public WestrosState(byte grid[][], byte xJon, byte yJon, byte dragonGlasses, byte whiteWalkers) {
        this.grid = grid;
        this.xJon = xJon;
        this.yJon = yJon;
        this.dragonGlasses = dragonGlasses;
        this.whiteWalkers = whiteWalkers;
    }
    
    public String toString(){
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
    	res += "dragonglasses = " + dragonGlasses +"\n";
    	res += "whiteWalkers = " + whiteWalkers + "\n";
    	res += "================";
    	return res;
    }

    @Override
    public boolean equals(Object o) {
        WestrosState state = (WestrosState) o;
        boolean equal = true;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] != state.grid[i][j])
                    equal = false;
            }

        }
        return equal && this.dragonGlasses == state.dragonGlasses;
    }

    public byte get_maxDragonglasses() {
    	return maxDragonglasses;
    }
    public void set_maxDragonglasses(byte maxDragonglasses) {
    	this.maxDragonglasses = maxDragonglasses;
    }

}
