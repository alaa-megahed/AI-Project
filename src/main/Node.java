package main;

public class Node implements Comparable<Node>{

    public Node parent;
    public State state;
    public Operator operator;
    int depth;
    public int path_cost;
    int heuristic;
    boolean greedy;

    public Node(Node parent, State state, Operator operator, int heuristic, boolean greedy) {
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
        this.greedy = greedy;
    }

    public int compareTo(Node node) {
        if (greedy) {
            return heuristic - node.heuristic;
        }
        return (path_cost + heuristic) - (node.path_cost + node.heuristic);
    }


    public String toString(){
    	String res = "depth = " + depth + "\n";
    	res += "path cost = " + path_cost;
    	return res;
    }


}
