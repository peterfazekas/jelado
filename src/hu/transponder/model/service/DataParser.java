package hu.transponder.model.service;

import hu.transponder.model.domain.Coordinate;
import hu.transponder.model.domain.Signal;
import hu.transponder.model.domain.Time;

import java.util.List;
import java.util.stream.Collectors;

public class DataParser {

    private int id;

    public List<Signal> parse(List<String> lines) {
        return lines.stream()
                .map(this::createSignal)
                .collect(Collectors.toList());
    }

    private Signal createSignal(String line) {
        var items = line.split(" ");
        var time = new Time(getValue(items[0]), getValue(items[1]), getValue(items[2]));
        var coordinate = new Coordinate(getValue(items[3]), getValue(items[4]));
        return new Signal(++id, coordinate, time);
    }

    private int getValue(String text) {
        return Integer.parseInt(text);
    }
}
