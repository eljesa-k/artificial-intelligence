public class Test {
    public static void main(String[] args) {

        Graph graph = new PuzzleGraph();

//        System.out.println(graph.uniformCost1("123456708").getStates());
//        System.out.println(graph.aStar("431207856").getStates()); sbon
//        System.out.println(graph.aStar("812354670").getStates());
//        System.out.println(graph.aStar("241386075").getStates());
//        System.out.println(graph.aStar("034561278").getStates());
//        System.out.println(graph.aStar("182043765").getStates());
//        System.out.println(graph.getShortestPath(new PuzzleState("123485706", 362_880)));
//        System.out.println(graph.getShortestPath(new PuzzleState("123456708", 362_880)));
        System.out.println(graph.getShortestPath(new PuzzleState("812354670", 362_880)));
//        System.out.println(graph.aStar("812043765").getStates()); sbon
//        System.out.println(graph.aStar("430561278").getStates()); sbon


//        System.out.println(graph.aStar("123450786").getStates());


//        char[] a = graph.vertices[46233].states;
//        for(char c: a)
//            System.out.print(c);
    }
}