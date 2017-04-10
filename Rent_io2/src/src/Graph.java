package src;

public class Graph {
    private static final String NEWLINE = System.getProperty("line.separator");

    private final int V;
    private int E;
    private Bag<Edge>[] adj;
    
    //instantiates a new graph of with V verticies
    public Graph(int V) {
        if (V < 0) throw new IllegalArgumentException("Number of vertices must be nonnegative");
        this.V = V;
        this.E = 0;
        //create the adjancy list using the Bag ADT from the lags 4 website. This list will be used to store all adjacent
        //vertexe's for any given vertex
        adj = (Bag<Edge>[]) new Bag[V];
        //initalize each Bag in the list
        for (int v = 0; v < V; v++) {
            adj[v] = new Bag<Edge>();
        }
    }
    
    //retunr the number of vertex's in the graph
    public int V() {
        return V;
    }
    
    //return the number of edges in the graph
    public int E() {
        return E;
    }

    // verify that the vertex v exists inside the graph
    private void validateVertex(int v) {
        if (v < 0 || v >= V)
            throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V-1));
    }
    
    //add a new weighted edge to the graph
    public void addEdge(Edge e) {
        int v = e.either();
        int w = e.other(v);
        validateVertex(v);
        validateVertex(w);
        adj[v].add(e);
        adj[w].add(e);
        E++;
    }
    
    //retun all the edges stored in the bag  at the vertex v as an iterable
    public Iterable<Edge> adj(int v) {
        validateVertex(v);
        return adj[v];
    }
    
    //return the number of edges connected to the specified vertex v
    public int degree(int v) {
        validateVertex(v);
        return adj[v].size();
    }
    
    //return every edge in the graph as an iterable
    public Iterable<Edge> edges() {
        Bag<Edge> list = new Bag<Edge>();
        for (int v = 0; v < V; v++) {
            int selfLoops = 0;
            for (Edge e : adj(v)) {
                if (e.other(v) > v) {
                    list.add(e);
                }
                // only add one copy of each self loop (self loops will be consecutive)
                else if (e.other(v) == v) {
                    if (selfLoops % 2 == 0) list.add(e);
                    selfLoops++;
                }
            }
        }
        return list;
    }

}
