package hu.transponder.controller;

import hu.transponder.model.domain.Coordinate;
import hu.transponder.model.domain.Signal;

import java.util.List;
import java.util.Optional;
import java.util.function.ToIntFunction;
import java.util.stream.Collectors;
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
        var leftBottom = new Coordinate(getMin(Signal::getX), getMin(Signal::getY));
        var rightUp = new Coordinate(getMax(Signal::getX), getMax(Signal::getY));
        return String.format("Bal alsó: %s, jobb felső: %s", leftBottom, rightUp);
    }

    private int getMin(ToIntFunction<Signal> function) {
        return signals.stream()
                .mapToInt(function)
                .min()
                .orElseThrow(() -> new IllegalArgumentException("No coordinate!"));
    }

    private int getMax(ToIntFunction<Signal> function) {
        return signals.stream()
                .mapToInt(function)
                .max()
                .orElseThrow(() -> new IllegalArgumentException("No coordinate!"));
    }

    public double getTotalDistance() {
        return IntStream.range(1, signals.size())
                .mapToDouble(i -> signals.get(i).distance(signals.get(i - 1)))
                .sum();
    }

    public List<String> getMissedSignals() {
        return IntStream.range(1, signals.size())
                .mapToObj(this::collectMissedReport)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
    }

    private Optional<String> collectMissedReport(int index) {
        var actualSignal = signals.get(index);
        var prevSignal = signals.get(index - 1);
        int fiveMinutesCount = actualSignal.fiveMinutesCount(prevSignal);
        int tenUnitDistanceCount = actualSignal.tenUnitDistanceCount(prevSignal);
        Optional<String> optionalString;
        if (fiveMinutesCount + tenUnitDistanceCount == 0) {
            optionalString = Optional.empty();
        } else if (tenUnitDistanceCount > fiveMinutesCount) {
            optionalString = Optional.of(String.format("%s koordináta-eltérés %d", actualSignal.getTime(), tenUnitDistanceCount));
        } else {
            optionalString = Optional.of(String.format("%s időeltérés %d", actualSignal.getTime(), fiveMinutesCount));
        }
        return optionalString;
    }

    private Signal getSignalById(int id) {
        return signals.stream()
                .filter(signal -> signal.isSignal(id))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("No signal exist with id " + id));
    }
}
