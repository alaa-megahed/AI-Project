package main;

abstract public class SearchProblem {

    public Operator [] operators;
    public State state;
    public abstract boolean goal_test(Node node);
    public abstract int path_cost(Node node);
    public abstract Node getNext();
    public abstract void expand (Node node);

    public boolean search() {
        while(true) {
            Node node = getNext();
            if (node == null)
                return false;
            if(goal_test(node))
                return true;
            expand(node);

        }
    }

}
