public abstract class TrafficLights {
    private boolean isGreen;
    private int timeOpen;
    private int timeToWait;
    private int[] sequence;

    protected int MIN_TIME_GREEN;
    protected int MAX_TIME_RED;
    protected int PREF_MIN_TIME_GREEN;
    protected int PREF_MAX_TIME_RED;

    public TrafficLights(boolean isGreen, int timeOpen, int timeToWait, int[] sequence) {
        this.isGreen = isGreen;
        this.timeOpen = timeOpen;
        this.timeToWait = timeToWait;
        this.sequence = sequence;
    }

    public abstract double getScore();

}
