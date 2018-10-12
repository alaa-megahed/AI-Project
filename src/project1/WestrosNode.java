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
        if (super.strategy.endsWith("2"))
            return (int) (Math.ceil(((WestrosState)state).whiteWalkers)/4);
        return 0;
    }
}
