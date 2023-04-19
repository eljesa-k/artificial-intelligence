import java.util.ArrayList;
import java.util.List;

public interface Graph<V> {
    public List<Node> getShortestPath(Node initial);

    /** Return the vertices in the graph */
    public java.util.List<V> getNodes();

    /** Return the object for the specified vertex index */
    public Node getNode(int index);

    /** Return the index for the specified vertex object */
    public int getIndex(Node n);

    /** Clear the graph */
    public void clear();

    /** Add a vertex to the graph */
    public boolean addVertex(Node n);

    /** Remove a vertex v from the graph, return true if successful */
    public boolean remove(V v);
}


