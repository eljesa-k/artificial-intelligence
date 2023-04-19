public abstract class Node {
    private String state;
    private int distance;
    private int parentIndex;

    public Node(String state, int distance){
        this.state = state;
        this.distance = distance;
    }

    abstract public int heuristic();
    abstract public int[] getEdges();
    abstract public boolean isSolution();

    public String getState() {
        return state;
    }

    public void setState(String state) {
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
