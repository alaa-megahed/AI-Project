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
    public static byte maxDragonglasses;

    public WestrosState(byte grid[][], byte xJon, byte yJon, byte dragonGlasses, byte whiteWalkers) {
        this.grid = grid;
        this.xJon = xJon;
        this.yJon = yJon;
        this.dragonGlasses = dragonGlasses;
        this.whiteWalkers = whiteWalkers;
    }

    @Override
    public String toString() {

        String s = "";
        for (int i = 0; i < grid.length; i++) {
            for(int j = 0; j < grid[i].length; j++) {
                switch (grid[i][j]) {
                    case 0: s += ". "; break;
                    case 1: s += "J "; break;
                    case 2: s += "D "; break;
                    case 3: s += "W "; break;
                    case 4: s += "O "; break;
                }
            }
            s += "\n";
        }

        return s;

    }


}
