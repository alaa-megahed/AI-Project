package main;

import java.util.*;

abstract public class SearchProblem {

    public Operator [] operators;
    public State initial;
    public abstract boolean goal_test(Node node);
    public abstract int path_cost(Node node);
    public abstract ArrayList<Node> expand(Node node, String strategy);
    public abstract Node createNode(State state, String strategy);
    
    private int expanded_nodes = 0;

    public Node search(String strategy) {
        int result [] = new int[3];
        SearchQueue q = new SearchQueue(strategy);
        q.add(createNode(initial, strategy));

        while(!q.isEmpty()) {
            Node current = q.removeFirst();
            if(goal_test(current))
                return current;
            expanded_nodes++;
            ArrayList<Node> next = expand(current, strategy);
            for (Node node: next) {
                q.add(node);
                System.out.println(node.state);
                System.out.println("-------");
            }
        }

        return null;
    }

    public int count_expanded_nodes(){
    	return expanded_nodes;
    }

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
}
