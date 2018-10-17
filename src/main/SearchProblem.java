package main;

import java.util.*;

import javax.xml.stream.events.StartDocument;

import main.Node;

/**
 * An abstract class for general search problems.
 * For each problem, the following need to be defined:
 * (1) the initial state,
 * (2) a list of all possible operators,
 * (3) a path cost function,
 * (4) a heuristic evaluation function, (as for the uninformed search strategies, it should return 0),
 * (5) a goal test, and
 * (6) a function that expands a node and returns a list of the resulting nodes.
 *
 */
abstract public class SearchProblem {

    public Operator [] operators;
    public State initial;
    public abstract boolean goal_test(Node node);
    public abstract int path_cost(Node node);
    public abstract int heuristic(Node node);
    public abstract ArrayList<Node> expand(Node node);
    final static int INF = (int) 1e7;
    private int expanded_nodes = 0;

    public Result search(String strategy) {
    	expanded_nodes = 0;
    	// Iterative deepening 
    	if(strategy.equals("ID")) {
    		for (int i = 0; i <= 1000; i++) {
				Result result = generalSearch(strategy, i);
				if(result != null)
					return result;
			}
    		return null;
    	}
    	// All other search strategies
        return generalSearch(strategy, INF);
    }
    
    /*
     * General search procedure.
     */
    public Result generalSearch(String strategy, int max_depth) {
    	SearchQueue q = new SearchQueue(strategy);
    	Node root = new Node(null, initial, null, strategy);
    	root.set_heuristic(heuristic(root));
        q.add(root);

        while(!q.isEmpty()) {
            Node current = q.removeFirst();
            if(goal_test(current))
                return new Result(current, expanded_nodes);
            expanded_nodes++;
            ArrayList<Node> next = expand(current);
            for (Node node: next) {
                if(node.depth <= max_depth && !isRepeated(node, node.parent))
                	q.add(node);
            }
        }

        return null;
    }

    public int count_expanded_nodes(){
    	return expanded_nodes;
    }

    /*
     * Returns whether the node is repeated in its branch or not, i.e., 
     * whether one of its ancestors has the same state.
     */
    public boolean isRepeated(Node node, Node ancestor) {
    	if(ancestor == null)
    		return false;
    	if(node.state.equals(ancestor.state))
    		return true;
    	return isRepeated(node, ancestor.parent);
    }
    
    /**
     * The search queue datastructure, where we can keep track of the search-tree nodes.
     * Three operations can be performed: add node, remove first node, and check whether the queue is empty.
     * In each problem, It uses one of the three built-in datastructures: Queue, Stack, or PriorityQueue,
     * depending on the search strategy.
     *
     */
    static class SearchQueue {

        private Queue<Node> q;
        private Stack<Node> s;
        private PriorityQueue<Node> pq;
        private String strategy;
        public SearchQueue(String strategy) {
            if (strategy.equals("BFS")) {
                q = new LinkedList<>();
            } else if (strategy.equals("DFS") || strategy.equals("ID")) {
                s = new Stack<>();
            } else if (strategy.equals("UC") || strategy.startsWith("GR") || strategy.startsWith("A*")) {
                pq = new PriorityQueue<>();
            }
            this.strategy = strategy;
        }

        public void add(Node node) {
            if (strategy.equals("BFS")) {
                q.add(node);
            } else if (strategy.equals("DFS") || strategy.equals("ID")) {
                s.push(node);
            } else if (strategy.equals("UC") || strategy.startsWith("GR") || strategy.startsWith("A*")) {
                pq.add(node);
            }
        }

        public Node removeFirst() {
            if (strategy.equals("BFS")) {
                return q.remove();
            } else if (strategy.equals("DFS") || strategy.equals("ID")) {
                return s.pop();
            } else if (strategy.equals("UC") || strategy.startsWith("GR") || strategy.startsWith("A*")) {
                return pq.remove();
            }
            return null;
        }

        public boolean isEmpty() {
            if (strategy.equals("BFS")) {
                return q.isEmpty();
            } else if (strategy.equals("DFS") || strategy.equals("ID")) {
                return s.isEmpty();
            } else if (strategy.equals("UC") || strategy.startsWith("GR") || strategy.startsWith("A*")) {
                return pq.isEmpty();
            }
            return false;
        }

    }
    
    /**
     * A class for the result.
     * Given a node that passes the goal test, it computes all the steps performed to reach the goal
     * using backtracking. Then we can display these steps and an evaluation of the search, e.g., 
     * the cost, and the number of expanded nodes.
     *
     */
    public static class Result {
        public int expanded_nodes, cost, depth;
        public Stack<Node> resultStack;
        public Node [] resultArray;
        public String [] steps;

        public Result(Node resultNode, int expanded_nodes) {
            this.expanded_nodes = expanded_nodes;
            this.cost = resultNode.path_cost;
            this.depth = resultNode.depth;
            resultStack = new Stack<>();
            Node cur = resultNode;
            while(cur != null) {
                resultStack.push(cur);
                cur = cur.parent;
            }
            resultArray = new Node[resultStack.size()];
            int j = 0;
            while(!resultStack.isEmpty()) {resultArray[j++] = resultStack.pop();}
            steps = new String[resultArray.length - 1];
            for(int i = 1; i < resultArray.length; i++) {
                steps[i - 1] = resultArray[i].operator.name;
            }
        }

        @Override
        public String toString() {
            String result = "STEPS:\n";
            for (String s: steps) {
                result += s;
                result += "\n";
            }
            result += "\n";
            result += "EVALUATION\n";
            result += "Cost = " + cost + "\n";
            result += "Expanded nodes: ";
            result += expanded_nodes + "\n";
            result += "Depth = " + depth + "\n";
            return result;
        }
    }

  
}
