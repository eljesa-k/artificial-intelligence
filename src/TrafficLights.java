public abstract class TrafficLights {
    public boolean isGreen;
    private int timeFrame;
    private double priority;

    protected int minTimeGreen;
    protected int maxTimeRed;
    protected int prefMinTimeGreen;
    protected int prefMaxTimeRed;

    /**
     * @param isGreen tregon a eshte i hapur per kalim apo jo semafori perkates
     * @param timeFrameLength in seconds psh 10 sek ni frame mu kon i gjate
     * @param minTimeGreen minimumi i frame-ave per te cilet semafori dueht te rri i hapur per te mos marre hard penalty
     * @param maxTimeRed maksimumi i frame-ave per te cilet semafori guxon te rri i mbyllur pa marre hard penalty
     * @param prefMinTimeGreen numrat e frame-ave te rekomanduar nga user-i per te cilat semafori te qendroj i hapur
     * @param prefMaxTimeRed numrat e frame-ave te rekomanduar nga user-i per te cilat semafori te qendroj i mbyllur
     */
    public TrafficLights(boolean isGreen, int timeFrameLength, int minTimeGreen, int maxTimeRed, int prefMinTimeGreen, int prefMaxTimeRed) {
        this.priority=1;
        this.isGreen = isGreen;
        this.timeFrame = timeFrameLength;
        this.minTimeGreen = minTimeGreen;
        this.maxTimeRed = maxTimeRed;
        this.prefMinTimeGreen = prefMinTimeGreen;
        this.prefMaxTimeRed = prefMaxTimeRed;
    }

    public TrafficLights(boolean isGreen, int timeFrame, double priority, int minTimeGreen, int maxTimeRed, int prefMinTimeGreen, int prefMaxTimeRed) {
        this.isGreen = isGreen;
        this.timeFrame = timeFrame;
        this.priority = priority;
        this.minTimeGreen = minTimeGreen;
        this.maxTimeRed = maxTimeRed;
        this.prefMinTimeGreen = prefMinTimeGreen;
        this.prefMaxTimeRed = prefMaxTimeRed;
    }

    public abstract double getScore(boolean[]sequence);

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
