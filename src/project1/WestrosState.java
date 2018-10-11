package project1;

import main.Operator;
import main.State;

public class WestrosState extends State {
//
//    enum Grid {
//        EMPTY, JON, DRAGON, WHITEWALKER, OBSTACLE;
//    }
    // 0: empty, 1: jon, 2: dragonstone, 3: white walker, 4: obstacle

    public byte grid[][];
    public byte xJon, yJon, dragonGlasses, whiteWalkers;
    public static byte maxDragonglasses = 1;

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


}
