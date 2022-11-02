package hu.transponder.model.domain;

public class Coordinate {

    private final int x;
    private final int y;

    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public double distance(Coordinate otherCoordinate) {
        return Math.sqrt(Math.pow(x - otherCoordinate.getX(), 2) + Math.pow(y - otherCoordinate.getY(), 2));
    }

    public int tenUnitDistanceCount(Coordinate otherCoordinate) {
        int diffX = Math.abs(this.x - otherCoordinate.getX());
        int diffY = Math.abs(this.y - otherCoordinate.getY());
        int diffMax = Math.max(diffX, diffY);
        return (diffMax - 1) / 10;
    }

    public String printFormattedCoordinate() {
        return String.format("x=%d y=%d", x, y);
    }

    @Override
    public String toString() {
        return x + " " + y;
    }
}
