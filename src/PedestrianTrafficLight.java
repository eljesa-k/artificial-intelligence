import java.util.ArrayList;

public class PedestrianTrafficLight extends TrafficLights{

    public PedestrianTrafficLight(boolean isGreen, int timeFrame, int MIN_TIME_GREEN, int MAX_TIME_RED, int PREF_MIN_TIME_GREEN, int PREF_MAX_TIME_RED) {
        super(isGreen, timeFrame, MIN_TIME_GREEN, MAX_TIME_RED, PREF_MIN_TIME_GREEN, PREF_MAX_TIME_RED);
    }

    public PedestrianTrafficLight(boolean isGreen, int timeFrame, double priority, int MIN_TIME_GREEN, int MAX_TIME_RED, int PREF_MIN_TIME_GREEN, int PREF_MAX_TIME_RED) {
        super(isGreen, timeFrame, priority, MIN_TIME_GREEN, MAX_TIME_RED, PREF_MIN_TIME_GREEN, PREF_MAX_TIME_RED);
    }

    @Override
    public double getScore(boolean[] sequence) {
        return 0;
    }

    /**
     * @param sequence e merr nje rresht te timeframe si array
     * @param minTimeGreen minimumi i frame-ave per te cilet semafori dueht te rri i hapur per te mos marre hard penalty
     * @param maxTimeRed maksimumi i frame-ave per te cilet semafori guxon te rri i mbyllur pa marre hard penalty
     * @param prefMinTimeGreen numrat e frame-ave te rekomanduar nga user-i per te cilat semafori te qendroj i hapur
     * @param prefMaxTimeRed numrat e frame-ave te rekomanduar nga user-i per te cilat semafori te qendroj i mbyllur
     * @return score-in i cili mund te jete pozitiv ose negativ varur nga penaltite qe ka marrur ky semafor ne timeframe-n perkates
     */
    public static double timeConstrainScore(boolean[] sequence, int minTimeGreen, int maxTimeRed, int prefMinTimeGreen, int prefMaxTimeRed){
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
            }
            if(minTimeGreen<=opened.get(i) && opened.get(i)<prefMinTimeGreen){
                score+=500;
            }
            if(opened.get(i)>=prefMinTimeGreen){
                score+=1000;
            }
        }
        for (int i = 0; i < closed.size(); i++) {
            if(closed.get(i)>maxTimeRed){
                score+=-50000;
            }
            if(prefMaxTimeRed<closed.get(i) && closed.get(i)<=maxTimeRed){
                score+=500;
            }
            if(closed.get(i)<=prefMaxTimeRed) {
                score += 1000;
            }
        }
        return score;
    }

//    this is to delete se osht bo infuse te ^
//    public double checkConsecutives(boolean[] sequence){
//        //TODO check sa jon consecutrive tu i kqyr min edhe preferred
//        return 0;
//    }
}
