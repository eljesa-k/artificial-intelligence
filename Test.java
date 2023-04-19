import java.awt.*;
import java.sql.SQLOutput;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Test {
    public static void main(String[] args) {

        Graph graph = new PuzzleGraph();

//        String initialString = "123456708";
        String initialString = "812354670";
//        String initialString = "241386075";
//        String initialString = "034561278";
//        String initialString = "182043765";
//
//        nuk punon
//        String initialString = "431207856";
//        String initialString = "812043765";
//        String initialString = "430561278";

        Node initialState = new PuzzleState(initialString, 362_880);
        List<Node> path = graph.getShortestPath(initialState);
//        path.stream().sorted(Collections.reverseOrder()).forEach(System.out::println);

        for(int i = path.size() -1; i >= 0; i--){
            String s = path.get(i).getState();
            for(int j = 0; j < 3; j++){
                System.out.print("[");
                for(int k = 0; k < 2; k++){
                    System.out.print(s.charAt(j*3+k) + ", ");
                }
                System.out.print(s.charAt(j*3+2));
                System.out.println("]");
            }
            System.out.println();
        }

    }
}