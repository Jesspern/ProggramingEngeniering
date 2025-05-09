package org.example.printer;

import org.example.entity.PrintRequest;
import org.example.enumEntities.DocumentType;
import org.example.enumEntities.PhotoFormat;
import org.example.printer.state.imp.A4State;
import org.example.printer.state.imp.Photo10X15State;
import org.example.printer.state.imp.Photo15X20State;
import org.example.printer.state.in.PrinterState;

public class Printer {
    private final String name;
    private PrinterState printerState;

    public Printer(String name) {
        this.name = name;
    }

    public void print(PrintRequest request) {
        if (request.getDocumentType() == DocumentType.A4_DOCUMENT) {
            this.printerState = new A4State();
        } else if (request.getPhotoFormat() == PhotoFormat.PHOTO_10X15) {
            this.printerState = new Photo10X15State();
        } else if (request.getPhotoFormat() == PhotoFormat.PHOTO_15X20) {
            this.printerState = new Photo15X20State();
        } else {
            System.out.println("Неизвестный формат. Печать невозможна.");
            return;
        }

        printerState.setup();
        printerState.print(name);
    }
}
