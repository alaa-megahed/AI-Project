package project1;

import main.Operator;
import main.State;

public class GridState extends State {
//
//    enum Grid {
//        EMPTY, JON, DRAGON, WHITEWALKER, OBSTACLE;
//    }
    // 0: empty, 1: jon, 2: dragon, 3: white walker, 4: obstacle

    byte grid[][];
    byte xJon, yJon;
    byte dragonGlasses;
    public byte whiteWalkers;

    public GridState(byte grid[][], byte xJon, byte yJon, byte dragonGlasses, byte whiteWalkers) {
        this.grid = grid;
        this.xJon = xJon;
        this.yJon = yJon;
        this.dragonGlasses = dragonGlasses;
        this.whiteWalkers = whiteWalkers;
    }


}
