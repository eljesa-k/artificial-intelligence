package model.traffic;

import model.IntersectionType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.IntStream;

public abstract class TrafficLight {
    public boolean isGreen;
    private int timeFrame;
    private double priority;
    private IntersectionType[] trafficLightsConstraint;
    private int index;

    protected int minTimeGreen;
    protected int maxTimeRed;
    protected int prefMinTimeGreen;
    protected int prefMaxTimeRed;

    protected int hard;
    protected int soft;
    protected int cool;
    {
        hard = TrafficLogicController.getHard();
        soft = TrafficLogicController.getSoft();
        cool = TrafficLogicController.getCool();

    }

    /**
     * @param isGreen          tregon a eshte i hapur per kalim apo jo semafori perkates
     * @param timeFrameLength  in seconds psh 10 sek ni frame mu kon i gjate
     * @param minTimeGreen     minimumi i frame-ave per te cilet semafori dueht te rri i hapur per te mos marre hard penalty
     * @param maxTimeRed       maksimumi i frame-ave per te cilet semafori guxon te rri i mbyllur pa marre hard penalty
     * @param prefMinTimeGreen numrat e frame-ave te rekomanduar nga user-i per te cilat semafori te qendroj i hapur
     * @param prefMaxTimeRed   numrat e frame-ave te rekomanduar nga user-i per te cilat semafori te qendroj i mbyllur
     */
    public TrafficLight(IntersectionType[] trafficLightsConstraint, int index, boolean isGreen, int timeFrameLength,
                        int minTimeGreen, int maxTimeRed, int prefMinTimeGreen, int prefMaxTimeRed) {
        this.priority = 1;
        this.index = index;
        this.isGreen = isGreen;
        this.timeFrame = timeFrameLength;
        this.minTimeGreen = minTimeGreen;
        this.maxTimeRed = maxTimeRed;
        this.prefMinTimeGreen = prefMinTimeGreen;
        this.prefMaxTimeRed = prefMaxTimeRed;
        this.trafficLightsConstraint = trafficLightsConstraint;

    }

    public TrafficLight(IntersectionType[] trafficLightsConstraint, int index,
                        boolean isGreen, int timeFrame, double priority, int minTimeGreen, int maxTimeRed,
                        int prefMinTimeGreen, int prefMaxTimeRed) {
        this.trafficLightsConstraint = trafficLightsConstraint;
        this.index = index;
        this.isGreen = isGreen;
        this.timeFrame = timeFrame;
        this.priority = priority;
        this.minTimeGreen = minTimeGreen;
        this.maxTimeRed = maxTimeRed;
        this.prefMinTimeGreen = prefMinTimeGreen;
        this.prefMaxTimeRed = prefMaxTimeRed;
    }

    public double getScore(boolean[][] sequence) {
        return Arrays.stream(sequence)
                .map(timeFrame -> timeConstrainScore(timeFrame) + compatibilityConstraintScore(timeFrame))
                .reduce(0.0, Double::sum);
    }

    /**
     * @param sequence nje rresht i timeframe si array
     * @return score-in i cili mund te jete pozitiv ose negativ varur nga penaltite qe ka marrur ky semafor ne timeframe-n perkates
     */
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
    private double compatibilityConstraintScore(boolean[] sequence){

        return IntStream.range(0, trafficLightsConstraint.length)
                .filter(i -> i != index)
                .mapToObj(i -> sequence[index] ?
                        checkCompatibilityForOpen(trafficLightsConstraint[i], sequence[i]) :
                        checkCompatibilityForClosed(trafficLightsConstraint[i], sequence[i]))
                .reduce(0.0, Double::sum);

//        double sumPoints = 0;
//        if(seq[index]) {
//            for (int i = 0; i < type.length; i++) {
//                if (index != i)
//                    sumPoints += checkCompatibilityForOpen(type[i], seq[i]);
//            }
//        }
//        else
//            for (int i = 0; i < type.length; i++) {
//                if(index != i)
//                    sumPoints += checkCompatibilityForClosed(type[i], seq[i]);
//            }
//        System.out.println(sumPoints);
//        return sumPoints;
    }
    private double checkCompatibilityForOpen(IntersectionType type, boolean affirmative){
        switch (type){
            case NEVER -> {
                return affirmative ? - hard : cool;
            }
            case UNPREFERRED-> {
                return affirmative ? - soft  : soft;
            }
            case ACCEPTABLE -> {
                return affirmative ? soft : - soft;
            }
            case ALWAYS -> {
                return affirmative ? cool : - hard;
            }
            default -> {
                return 0;
            }
        }
    }
    private double checkCompatibilityForClosed(IntersectionType type, boolean affirmative){
        switch (type){
            case NEVER -> {
                return affirmative ? soft : - soft;
            }
            case UNPREFERRED-> {
                return affirmative ? soft : - cool;
            }
            case ACCEPTABLE -> {
                return affirmative ? - soft : cool;
            }
            case ALWAYS -> {
                return affirmative ? - hard : cool;
            }
            default -> {
                return 0;
            }
        }
    }
    public int getMinTimeGreen() {
        return minTimeGreen;
    }

    public int getMaxTimeRed() {
        return maxTimeRed;
    }

    public int getPrefMinTimeGreen() {
        return prefMinTimeGreen;
    }

    public int getPrefMaxTimeRed() {
        return prefMaxTimeRed;
    }

}
