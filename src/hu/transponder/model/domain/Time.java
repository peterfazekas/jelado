package hu.transponder.model.domain;

public class Time {

    private final int hour;
    private final int minute;
    private final int second;

    public Time(int hour, int minute, int second) {
        this.hour = hour;
        this.minute = minute;
        this.second = second;
    }

    private int toSeconds() {
        return hour * 60 * 60 + minute * 60 + second;
    }

    public String diff(Time otherTime) {
        return toTime(eltelt(otherTime)).printFormattedTime();
    }

    public int fiveMinutesCount(Time otherTime) {
        int secondsSpent = eltelt(otherTime);
        return secondsSpent > 300 ? (secondsSpent - 1) / 300 : 0;
    }

    public Time toTime(int seconds) {
        int hour = seconds / 3600;
        int minute = (seconds % 3600) / 60;
        int second = (seconds % 3600) % 60;
        return new Time(hour, minute, second);
    }

    private int eltelt(Time otherTime) {
        return Math.abs(this.toSeconds() - otherTime.toSeconds());
    }

    public String printFormattedTime() {
        return String.format("%02d:%02d:%02d", hour, minute, second);
    }

    @Override
    public String toString() {
        return hour + " " + minute + " " + second;
    }
}
