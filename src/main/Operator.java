package main;

/**
 * A class for the operators.
 * Each operator has an id, a name and a cost.
 *
 */
public class Operator {
    public int cost;
    public int id;
    public String name;
    
    public Operator(int cost, int id, String name) {
        this.cost = cost;
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
