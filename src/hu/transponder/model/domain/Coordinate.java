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

    public String printCoordinate() {
        return String.format("x=%d y=%d", x, y);
    }
    @Override
    public String toString() {
        return x + " " + y;
    }
}
