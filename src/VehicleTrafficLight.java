import java.util.ArrayList;

public class VehicleTrafficLight extends TrafficLights{

    private double trafficCoeff;

    /**
     * @param isGreen
     * @param timeFrame gjatesia e intervalit kohor per nje frame
     * @param MIN_TIME_GREEN minimumi i frame-ave per te cilet semafori dueht te rri i hapur per te mos marre hard penalty
     * @param MAX_TIME_RED maksimumi i frame-ave per te cilet semafori guxon te rri i mbyllur pa marre hard penalty
     * @param PREF_MIN_TIME_GREEN numrat e frame-ave te rekomanduar nga user-i per te cilat semafori te qendroj i hapur
     * @param PREF_MAX_TIME_RED numrat e frame-ave te rekomanduar nga user-i per te cilat semafori te qendroj i mbyllur
     * @param trafficCoeff koeficienti i ngarkeses se trafikut
     */
    public VehicleTrafficLight(IntersectionType[] trafficLightsConstraint, int index, boolean isGreen, int timeFrame, int MIN_TIME_GREEN, int MAX_TIME_RED, int PREF_MIN_TIME_GREEN, int PREF_MAX_TIME_RED, double trafficCoeff) {
        super(trafficLightsConstraint, index,isGreen, timeFrame, MIN_TIME_GREEN, MAX_TIME_RED, PREF_MIN_TIME_GREEN, PREF_MAX_TIME_RED);
        this.trafficCoeff = trafficCoeff;
    }

    public VehicleTrafficLight(IntersectionType[] trafficLightsConstraint, int index,boolean isGreen, int timeFrame, double priority, int MIN_TIME_GREEN, int MAX_TIME_RED, int PREF_MIN_TIME_GREEN, int PREF_MAX_TIME_RED, double trafficCoeff) {
        super(trafficLightsConstraint, index, isGreen, timeFrame, priority, MIN_TIME_GREEN, MAX_TIME_RED, PREF_MIN_TIME_GREEN, PREF_MAX_TIME_RED);
        this.trafficCoeff = trafficCoeff;
    }

    @Override
    public double getScore(boolean[][] sequence) {
        return super.getScore(sequence) * trafficCoeff;
    }

    int MIN_TIME_GREEN = this.getMinTimeGreen();
    int MAX_TIME_RED = this.getMaxTimeRed();
    int PREF_MIN_TIME_GREEN  = this.getPrefMinTimeGreen();
    int PREF_MAX_TIME_RED =  this.getPrefMaxTimeRed();

    /**
     * @inheritDoc
     */
    @Override
    public double timeConstrainScore(boolean[] sequence){
        double score =0;
        ArrayList<Integer> opened = new ArrayList<>();
        ArrayList<Integer> closed = new ArrayList<>();
        int timesTrue=0;
        while(true){
            int count =0;
            for (int i = timesTrue; i < sequence.length; i++) {
                if(sequence[i]){
                    count++;
                    timesTrue++;
                }
                else{
                    timesTrue++;
                    break;
                }
            }
            if(count!=0){
                opened.add(count);
            }
            if(timesTrue== sequence.length){
                break;
            }
        }
        int timesFalse=0;
        while(true){
            int count =0;
            for (int i = timesFalse; i < sequence.length; i++) {
                if(!sequence[i]){
                    count++;
                    timesFalse++;
                }
                else{
                    timesFalse++;
                    break;
                }
            }
            if(count!=0){
                closed.add(count);
            }
            if(timesFalse== sequence.length){
                break;
            }
        }

        for (int i = 0; i < opened.size(); i++) {
            if(opened.get(i)<minTimeGreen){
                score+=-50000;
                //System.out.println(i+" ma i vogel se mintime green "+ opened.get(i));
            }
            if(minTimeGreen<=opened.get(i) && opened.get(i)<prefMinTimeGreen){
                score+=500;
                //System.out.println(i+" nmes pref time edhe mintime green "+ opened.get(i));
            }
            if(opened.get(i)>=prefMinTimeGreen){
                score+=1000;
                //System.out.println(i+" ma e madhe se pref time green "+ opened.get(i));
            }
        }
        for (int i = 0; i < closed.size(); i++) {
            if(closed.get(i)>maxTimeRed){
                score+=-50000;
                //System.out.println(i+" ma e madhe se max time red "+ closed.get(i));
            }
            if(prefMaxTimeRed<closed.get(i) && closed.get(i)<=maxTimeRed){
                score+=500;
                //System.out.println(i+" nmes red edhe pref edhe max "+ closed.get(i));
            }
            if(closed.get(i)<=prefMaxTimeRed) {
                score += 1000;
                //System.out.println(i+" ma i vogel se pref time red " + closed.get(i));
            }
        }
        return score;
    }


//    /**
//     * @param args - testim
//     */
//    public static void main(String[] args) {
//        boolean[] sequence= {false, true ,true, true, false, false, true, true, true, false, false, true, true};
//        System.out.println(timeConstrainScore(sequence, 2, 3, 3, 1));
//    }

}
