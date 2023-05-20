public abstract class TrafficLights {
    public boolean isGreen;
    private int timeFrame;
    private double priority;

    protected int MIN_TIME_GREEN;
    protected int MAX_TIME_RED;
    protected int PREF_MIN_TIME_GREEN;
    protected int PREF_MAX_TIME_RED;

    public TrafficLights(boolean isGreen, int timeFrame, int MIN_TIME_GREEN, int MAX_TIME_RED, int PREF_MIN_TIME_GREEN, int PREF_MAX_TIME_RED) {
        this.priority=1;
        this.isGreen = isGreen;
        this.timeFrame = timeFrame;
        this.MIN_TIME_GREEN = MIN_TIME_GREEN;
        this.MAX_TIME_RED = MAX_TIME_RED;
        this.PREF_MIN_TIME_GREEN = PREF_MIN_TIME_GREEN;
        this.PREF_MAX_TIME_RED = PREF_MAX_TIME_RED;
    }

    public TrafficLights(boolean isGreen, int timeFrame, double priority, int MIN_TIME_GREEN, int MAX_TIME_RED, int PREF_MIN_TIME_GREEN, int PREF_MAX_TIME_RED) {
        this.isGreen = isGreen;
        this.timeFrame = timeFrame;
        this.priority = priority;
        this.MIN_TIME_GREEN = MIN_TIME_GREEN;
        this.MAX_TIME_RED = MAX_TIME_RED;
        this.PREF_MIN_TIME_GREEN = PREF_MIN_TIME_GREEN;
        this.PREF_MAX_TIME_RED = PREF_MAX_TIME_RED;
    }

    public abstract double getScore(boolean[]sequence);
}
