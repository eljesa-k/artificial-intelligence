import java.util.*;

public abstract class InformedGraph implements Graph{
    List<Node> nodes;
    Node solution;
    public InformedGraph(Node solution){
        this.solution = solution;
        nodes = new ArrayList<>();
    }

    /**
     *  Finds the shortest path using A* algorithm
     * @return the List with nodes that form the path
     */
    @Override
    public List<Node> getShortestPath(Node initial) {
        Node solution = this.aStar(initial);

        // TODO: implement backward search
        return null;
    }

    /**
     * Finds the solutions and marks who are the parents
     * @param node_start the node from wich we start searching
     * @return the solution node
     */
    private Node aStar(Node node_start){
        if(Objects.equals(node_start.getState(), solution.getState())) {
            return node_start;
        }
        List<Node> closed = new ArrayList<Node>();
        Queue<Node> open = new PriorityQueue<Node>(Comparator.comparingInt((Node v) -> v.getDistance() + v.heuristic()));

        node_start.setDistance(0);
        open.offer(node_start);

        while (!open.isEmpty()){
            Node node_current = open.poll();
            if(Objects.equals(node_current.getState(), solution.getState())) {
                return node_current;
            }
            System.out.println(node_current.getState());
            int successor_current_cost = node_current.getDistance() + 1;
            int parent_index = this.getIndex(node_current);

            for(int i : node_current.getEdges()){
                Node node_successor = this.getNode(i);
                if(!open.contains(node_successor) && !closed.contains(node_successor)){
                    node_successor.setDistance(successor_current_cost, parent_index);
                    open.add(node_successor);
                }else if(node_successor.getDistance() > successor_current_cost){
                    node_successor.setDistance(successor_current_cost, parent_index);
                    if(closed.contains(node_successor)){
                        open.offer(node_successor);
                        closed.remove(node_successor);
                    }
                }
            }
            closed.add(node_current);
        }
        return null;
    }

    /** @inheritDoc */
    @Override
    public List getNodes() {
        // TODO implement
        return null;
    }

    /** @inheritDoc */
    @Override
    public Node getNode(int index) {
        return this.nodes.get(index);
    }

    /** @inheritDoc */
    @Override
    public int getIndex(Node n) {
        return this.nodes.indexOf(n);
    }

    /** @inheritDoc */
    @Override
    public void clear() {
        this.nodes = new ArrayList<>();
    }

    /** @inheritDoc */
    @Override
    public boolean addVertex(Node n) {
        return this.nodes.add(n);
    }

    /** @inheritDoc */
    @Override
    public boolean remove(Object o) {
        return this.nodes.remove(o);
    }
}
