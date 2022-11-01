package hu.transponder;

import hu.transponder.controller.SignalService;
import hu.transponder.model.service.Console;
import hu.transponder.model.service.DataApi;
import hu.transponder.model.service.DataParser;
import hu.transponder.model.service.FileReader;
import hu.transponder.model.service.FileWriter;

import java.util.Scanner;

public class App {
    
    private final SignalService service;
    private final Console console;
    private final FileWriter writer;
    
    private App() {
        var dataApi = new DataApi(new FileReader(), new DataParser());
        service = new SignalService(dataApi.getData("jel.txt"));
        console = new Console(new Scanner(System.in));
        writer = new FileWriter("kimaradt.txt");
    }

    public static void main(String[] args) {
        new App().run();
    }

    private void run() {
        System.out.println("2. feladat");
        System.out.print("Adja meg a jel sorszámát: ");
        int id = console.read();
        System.out.println(service.getSignalCoordinateById(id));
        System.out.println("4. feladat");
        System.out.printf("Időtartam: %s\n", service.timeSpent());
        System.out.println("5. feladat");
        System.out.println(service.getBoundingRectangleCoordinates());
        System.out.println("6. feladat");
        System.out.printf("Elmozdulás: %.3f egység\n", service.getTotalDistance());
        writer.writeAll(service.getMissedSignals());
    }
}
