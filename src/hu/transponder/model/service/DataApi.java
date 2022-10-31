package hu.transponder.model.service;

import hu.transponder.model.domain.Signal;

import java.util.List;

public class DataApi {

    private final FileReader reader;
    private final DataParser parser;

    public DataApi(FileReader reader, DataParser parser) {
        this.reader = reader;
        this.parser = parser;
    }

    public List<Signal> getData(String filename) {
        return parser.parse(reader.read(filename));
    }
}
