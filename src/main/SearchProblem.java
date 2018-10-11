package main;

import java.util.*;

abstract public class SearchProblem {

    public Operator [] operators;
    public State initial;
    public abstract boolean goal_test(Node node);
    public abstract int path_cost(Node node);
    public abstract ArrayList<Node> expand(Node node);
    private Queue<Node> q;
    private Stack<Node> s;
    private PriorityQueue<Node> pq;

    public Node search(String strategy) {
        int result [] = new int[3];
        if (strategy.equals("BFS")) {
            q = new LinkedList<>();
            q.add(new Node(null, initial, null, 0));
            while(!q.isEmpty()) {
                Node current = q.poll();
                if (goal_test(current))
                    return current;
                ArrayList<Node> next = expand(current);
                for (Node node: next)
                    q.add(node);
            }

            return null;
        } else if (strategy.equals("DFS")) {
            s = new Stack<>();
            s.add(new Node(null, initial, null, 0));
            while(!s.isEmpty()) {
                Node current = s.pop();
                if (goal_test(current))
                    return current;
                ArrayList<Node> next = expand(current);
                for (Node node: next)
                    s.add(node);
            }

            return null;

        } else if (strategy.equals("ID")) {
            for (int l = 0; l < 10000; l++) {
                s = new Stack<>();
                int d = 0;
                s.add(new Node(null, initial, null, 0));
                while(!s.isEmpty()) {
                    if( d > l)
                        break;
                    Node current = s.pop();
                    if (goal_test(current))
                        return current;
                    ArrayList<Node> next = expand(current);
                    for (Node node: next)
                        s.add(node);
                    d++;
                }
            }

            return null;

        } else if (strategy.equals("UC")) {

        } else if (strategy.equals("GR1")) {

        } else if (strategy.equals("GR2")) {

        } else if (strategy.equals("A*1")) {

        } else {

        }

        return null;
    }

}
