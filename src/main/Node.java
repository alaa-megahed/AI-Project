package main;

/**
 * A class for general search-tree nodes.
 * A node has knowledge of 
 * (1) the state to which the node corresponds,
 * (2) the parent node,
 * (3) the operator applied to generate this node,
 * (4) the depth of the node in the tree,
 * (5) the path cost from the root.
 * In addition to the search strategy.
 *
 */

public class Node implements Comparable<Node>{

    public Node parent;
    public State state;
    public Operator operator;
    int depth;
    public int path_cost;
    int heuristic;
    public String strategy;

    
    public Node(Node parent, State state, Operator operator, String strategy) {
        this.parent = parent;
        // if root
        if (parent == null) {
            depth = 0;
            path_cost = 0;
        } else {
            depth = parent.depth + 1;
            path_cost = parent.path_cost + operator.cost;
        }
        this.state = state;
        this.operator = operator;
        this.strategy = strategy;
        this.heuristic = 0;
    }

    
    /*
     * Only the greedy search compares the nodes using the values of the heuristic function,
     * otherwise, nodes are compared by the addition of path cost and heuristic value,
     * given that the heuristic function evaluates to 0 in case of uninformed search strategies.
     */
    @Override
    public int compareTo(Node node) {
        if (strategy.startsWith("GR")) {
            return heuristic - node.heuristic;
        }
        return (path_cost + heuristic) - (node.path_cost + node.heuristic);
    }

    public void set_heuristic(int heuristic) {
    	this.heuristic = heuristic;
    }
    
    @Override
    public String toString(){
    	String res = "depth = " + depth + "\n";
    	res += "path cost = " + path_cost;
    	return res;
    }


}
