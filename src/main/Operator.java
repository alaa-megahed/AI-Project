package main;

public class Operator {
    public int cost;
    public int id;
    public String name;
    public Operator(int cost, int id, String name) {
        this.cost = cost;
        this.id = id;
        this.name = name;
    }

    public String toString() {
        return name;
    }
}
