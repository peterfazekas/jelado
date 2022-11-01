package hu.transponder.model.domain;

public class Signal {

    private final int id;
    private final Coordinate coordinate;
    private final Time time;

    public Signal(int id, Coordinate coordinate, Time time) {
        this.id = id;
        this.coordinate = coordinate;
        this.time = time;
    }

    public int getX() {
        return coordinate.getX();
    }

    public int getY() {
        return coordinate.getY();
    }

    public boolean isSignal(int id) {
        return this.id == id;
    }

    public String getCoordinate() {
        return coordinate.printFormattedCoordinate();
    }

    public Time getTime() {
        return time;
    }

    public String timeSpent(Signal otherSignal) {
        return time.diff(otherSignal.getTime());
    }

    public double distance(Signal otherSignal) {
        return coordinate.distance(otherSignal.coordinate);
    }

    public int tenUnitDistanceCount(Signal otherSignal) {
        return coordinate.tenUnitDistanceCount(otherSignal.coordinate);
    }

    public int fiveMinutesCount(Signal otherSignal) {
        return time.fiveMinutesCount(otherSignal.time);
    }
}
