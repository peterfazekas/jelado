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

    public int getHour() {
        return hour;
    }

    public int getMinute() {
        return minute;
    }

    public int getSecond() {
        return second;
    }

    private int toSeconds() {
        return hour * 60 * 60 + minute * 60 + second;
    }

    public String diff(Time otherTime) {
        return eltelt(otherTime).toString();
    }

    public Time toTime(int seconds) {
        int hour = seconds / 3600;
        int minute = (seconds % 3600) / 60;
        int second = (seconds % 3600) % 60;
        return new Time(hour, minute, second);
    }

    private Time eltelt(Time otherTime) {
        return toTime(Math.abs(this.toSeconds() - otherTime.toSeconds()));
    }

    @Override
    public String toString() {
        return String.format("%02d:%02d:%02d", hour, minute, second);
    }
}
