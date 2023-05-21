package model.traffic;

import model.IntersectionType;

public class VehicleTrafficLight extends TrafficLight {

    private double trafficCoeff;

    /**
     * @param isGreen
     * @param timeFrame gjatesia e intervalit kohor per nje frame
     * @param MIN_TIME_GREEN minimumi i frame-ave per te cilet semafori dueht te rri i hapur per te mos marre hard penalty
     * @param MAX_TIME_RED maksimumi i frame-ave per te cilet semafori guxon te rri i mbyllur pa marre hard penalty
     * @param PREF_MIN_TIME_GREEN numrat e frame-ave te rekomanduar nga user-i per te cilat semafori te qendroj i hapur
     * @param PREF_MAX_TIME_RED numrat e frame-ave te rekomanduar nga user-i per te cilat semafori te qendroj i mbyllur
     */
    public VehicleTrafficLight(IntersectionType[] trafficLightsConstraint, int index, boolean isGreen, int timeFrame, int MIN_TIME_GREEN, int MAX_TIME_RED, int PREF_MIN_TIME_GREEN, int PREF_MAX_TIME_RED) {
        super(trafficLightsConstraint, index,isGreen, timeFrame, MIN_TIME_GREEN, MAX_TIME_RED, PREF_MIN_TIME_GREEN, PREF_MAX_TIME_RED);
        this.trafficCoeff = trafficCoeff;
    }
    /**
     * @param isGreen
     * @param timeFrame gjatesia e intervalit kohor per nje frame
     * @param MIN_TIME_GREEN minimumi i frame-ave per te cilet semafori dueht te rri i hapur per te mos marre hard penalty
     * @param MAX_TIME_RED maksimumi i frame-ave per te cilet semafori guxon te rri i mbyllur pa marre hard penalty
     * @param PREF_MIN_TIME_GREEN numrat e frame-ave te rekomanduar nga user-i per te cilat semafori te qendroj i hapur
     * @param PREF_MAX_TIME_RED numrat e frame-ave te rekomanduar nga user-i per te cilat semafori te qendroj i mbyllur
     * @param trafficCoeff koeficienti i ngarkeses se trafikut
     */
    public VehicleTrafficLight(IntersectionType[] trafficLightsConstraint, int index,boolean isGreen, int timeFrame, double priority, int MIN_TIME_GREEN, int MAX_TIME_RED, int PREF_MIN_TIME_GREEN, int PREF_MAX_TIME_RED, double trafficCoeff) {
        super(trafficLightsConstraint, index, isGreen, timeFrame, priority, MIN_TIME_GREEN, MAX_TIME_RED, PREF_MIN_TIME_GREEN, PREF_MAX_TIME_RED);
        this.trafficCoeff = trafficCoeff;
    }

    @Override
    public double getScore(boolean[][] sequence) {
        return super.getScore(sequence) * trafficCoeff;
    }

    /**
     * @inheritDoc
     * overriden method timeConstrainScore e cila shumezohet me koficientin e trafikut
     */
    @Override
    public double timeConstrainScore(boolean[] sequence){
        double score = super.timeConstrainScore(sequence);
        score*=trafficCoeff;
        return score;
    }

}
