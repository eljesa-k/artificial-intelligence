import java.util.ArrayList;

public class PuzzleGraph extends InformedGraph{
    public PuzzleGraph(){
        super();
        Node solution = new PuzzleState("123456780", 362_880);
        this.setSolution(solution);
        String[] nodes = Utilities.permutationsWithRecursion("012345678");
        for (String node : nodes) {
            this.addNode(new PuzzleState(node, 362_880));
        }
    }
}
