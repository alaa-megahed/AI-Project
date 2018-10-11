package main;

public class Node implements Comparable<Node>{

    Node parent;
    public State state;
    public Operator operator;
    int depth;
    public int path_cost;
    int heuristic;

    public Node(Node parent, State state, Operator operator, int heuristic) {
        this.parent = parent;
        if (parent == null) {
            depth = 0;
            path_cost = 0;
        } else {
            depth = parent.depth + 1;
            path_cost = parent.path_cost + operator.cost;
        }
        this.state = state;
        this.operator = operator;
        this.heuristic = heuristic;
    }

    @Override
    public int compareTo(Node node) {
        return (path_cost + heuristic) - (node.path_cost + node.heuristic);
    }

    @Override
    public String toString() {
        return state.toString();
    }
}
