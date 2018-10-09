package project1;

import main.Node;
import main.Operator;
import main.SearchProblem;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

abstract public class GridSearch extends SearchProblem {


    public GridSearch(GridState initial) {

        Operator operators[] = new Operator[]{
                new Operator(1, 1), // move up
                new Operator(1, 2), //move down
                new Operator(1, 3), // move left
                new Operator(1, 4), // move right
                new Operator(0, 5), // kill adjacent
                new Operator(0, 6) // pick up dragon glass
        };

        this.operators = operators;
        this.state = initial;
    }

    @Override
    public boolean goal_test(Node node) {
        return ((GridState) this.state).whiteWalkers == 0;
    }

    @Override
    public int path_cost(Node node) {
        return node.path_cost;
    }

    class GridSearchBFS extends GridSearch {

        public Queue<Node> queue;

        public GridSearchBFS(GridState initial) {
            super(initial);
            queue = new LinkedList<Node>();
            Node first = new Node(null, initial, null, 0);
            queue.add(first);
        }

        @Override
        public Node getNext() {
            return queue.poll();
        }

        @Override
        public void expand(Node node) {
            for (Operator op: operators) {

            }
        }
    }

}
