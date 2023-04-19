import java.util.Objects;

public class PuzzleState extends Node{
    public PuzzleState(String state, int distance){
        super(state, distance);
    }
    @Override
    public int heuristic() {
        int distance = 0;
        for(int i = 0; i < 9; i++){
            int current = ((String)getState()).charAt(i) - '0' - 1;
            if(current == -1) current = 8;  // for the empty space
            distance += Math.abs(current / 3 - i / 3);
            distance += Math.abs(current % 3 - i % 3);
        }
        return distance;
    }

    @Override
    public int[] getEdges(){
        String initialString = (String)this.getState();
        int x = initialString.indexOf('0');
        int[] connectedNodes;
        int lastFilled = -1;

        int nrOfEdges = 2;
        if(x==4) nrOfEdges = 4;
        else if(x%2 == 1) nrOfEdges = 3;
        connectedNodes = new int[nrOfEdges];

        int i = x/3;
        int j = x % 3;

        if(i-1 >= 0){
            String neighbor = initialString.substring(0,x-3)
                    + "0"
                    + initialString.substring(x-2, x)
                    + initialString.charAt(x-3)
                    + initialString.substring(x+1);
            connectedNodes[++lastFilled] = Utilities.permutationIndex(neighbor);
        }
        if(i+1 <= 2){
            String neighbor = initialString.substring(0,x)
                    + initialString.charAt(x+3)
                    + initialString.substring(x+1, x+3)
                    + "0"
                    + initialString.substring(x+4);
            connectedNodes[++lastFilled] = Utilities.permutationIndex(neighbor);
        }
        if(j+1 <= 2){
            String neighbor = initialString.substring(0,x)
                    + initialString.charAt(x+1)
                    + "0"
                    + initialString.substring(x+2);
            connectedNodes[++lastFilled] = Utilities.permutationIndex(neighbor);
        }
        if(j-1 >= 0){
            String neighbor = initialString.substring(0,x-1)
                    + "0"
                    + initialString.charAt(x-1)
                    + initialString.substring(x+1);
            connectedNodes[++lastFilled] = Utilities.permutationIndex(neighbor);
        }
        return connectedNodes;
    }

}
