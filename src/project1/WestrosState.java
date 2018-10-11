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


}
