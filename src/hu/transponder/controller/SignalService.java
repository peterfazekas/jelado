package hu.transponder.controller;

import hu.transponder.model.domain.Coordinate;
import hu.transponder.model.domain.Signal;

import java.util.List;
import java.util.stream.IntStream;

public class SignalService {

    private final List<Signal> signals;

    public SignalService(List<Signal> signals) {
        this.signals = signals;
    }

    public String getSignalCoordinateById(int id) {
        return getSignalById(id).getCoordinate();
    }

    public String timeSpent() {
        var firstSignal = signals.get(0);
        var lastSignal = signals.get(signals.size() - 1);
        return firstSignal.timeSpent(lastSignal);
    }

    public String getBoundingRectangleCoordinates() {
        var leftBottom = new Coordinate(getMinX(), getMinY());
        var rightUp = new Coordinate(getMaxX(), getMaxY());
        return String.format("Bal alsó: %s, jobb felső: %s", leftBottom, rightUp);
    }

    private int getMinX() {
        return signals.stream()
                .mapToInt(Signal::getX)
                .min()
                .orElseThrow(() -> new IllegalArgumentException("No coordinate!"));
    }

    private int getMaxX() {
        return signals.stream()
                .mapToInt(Signal::getX)
                .max()
                .orElseThrow(() -> new IllegalArgumentException("No coordinate!"));
    }

    private int getMinY() {
        return signals.stream()
                .mapToInt(Signal::getY)
                .min()
                .orElseThrow(() -> new IllegalArgumentException("No coordinate!"));
    }

    private int getMaxY() {
        return signals.stream()
                .mapToInt(Signal::getY)
                .max()
                .orElseThrow(() -> new IllegalArgumentException("No coordinate!"));
    }

    public double getTotalDistance() {
        return IntStream.range(1, signals.size())
                .mapToDouble(i -> signals.get(i).distance(signals.get(i - 1)))
                .sum();
    }

    private Signal getSignalById(int id) {
        return signals.stream()
                .filter(signal -> signal.isSignal(id))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("No signal exist with id " + id));
    }
}
