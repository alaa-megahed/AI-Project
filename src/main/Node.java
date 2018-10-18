package main;

/**
 * A class for general search-tree nodes.
 * A node has knowledge of 
 * (1) the state to which the node corresponds,
 * (2) the parent node,
 * (3) the operator applied to generate this node,
 * (4) the depth of the node in the tree,
 * (5) the path cost from the root.
 * In addition to "evaluate" variable, that is used to compare nodes.
 *
 */

public class Node implements Comparable<Node>{

    public Node parent;
    public State state;
    public Operator operator;
    int depth;
    public int path_cost;
    int evaluate;

    
    public Node(Node parent, State state, Operator operator) {
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
    }

    public void set_evaluate(int evaluate) {
    	this.evaluate = evaluate;
    }
    
    /*
     * Compares the nodes by the value of "evaluate".
     * Only in the greedy search "evaluate" is set to the value of the heuristic function,
     * otherwise, nodes are compared by the sum of path cost and heuristic value,
     * given that the heuristic function evaluates to 0 in case of uninformed search strategies.
     * The conflicts are resolved by comparing the depth and returning the one with the less depth.
     */
    @Override
    public int compareTo(Node node) {
    	if(evaluate == node.evaluate)
    		return depth - node.depth;
    	return evaluate - node.evaluate;
    }

    
    @Override
    public String toString(){
    	String res = "depth = " + depth + "\n";
    	res += "path cost = " + path_cost;
    	return res;
    }


}
