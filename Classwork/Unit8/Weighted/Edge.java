package Weighted;

public class Edge implements Comparable<Edge> {
    Celest A;
    Celest B;
    double cost;

    public Edge(Celest a, Celest b, double cost) {
        A = a;
        B = b;
        this.cost = cost;
    }

    @Override
    public String toString() {
        return "Edge " + A + ", " + B + " has cost=" + cost ;
    }

    @Override
    public int compareTo(Edge other) {
        return Double.compare(this.cost, other.cost);
    }
}
