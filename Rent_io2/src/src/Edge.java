package src;

public class Edge implements Comparable<Edge> { 

    private final int v;
    private final int w;
    private final double weight;
    
    //createds a new weighted edge
    public Edge(int v, int w, double weight) {
        this.v = v;
        this.w = w;
        this.weight = weight;
    }
    
    //retunrs the weight of the edge
    public double weight() {
        return weight;
    }
    
    //returns one of the vertex's of the edge
    public int either() {
        return v;
    }
    
    //returns the value of the vertex opposite to the vertex given.
    public int other(int vertex) {
        if      (vertex == v) return w;
        else if (vertex == w) return v;
        else throw new IllegalArgumentException("Illegal endpoint");
    }
}
