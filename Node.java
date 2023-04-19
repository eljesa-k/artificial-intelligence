public abstract class Node {
    private Object state;
    private int distance;
    private int parentIndex;

    public Node(Object state, int distance){
        this.state = state;
        this.distance = distance;
    }

    abstract public int heuristic();
    abstract public int[] getEdges();

    public Object getState() {
        return state;
    }

    public void setState(Object state) {
        this.state = state;
    }

    public int getDistance() {
        return distance;
    }

    public int getParentIndex() {
        return parentIndex;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public void setDistance(int distance, int parentIndex) {
        this.distance = distance;
        this.parentIndex = parentIndex;
    }
}
