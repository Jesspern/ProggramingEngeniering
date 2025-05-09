package org.example.printer.state.imp;

import org.example.printer.state.in.PrinterState;

public class Photo10X15State implements PrinterState {
    @Override
    public void setup() {
        System.out.println("[Настройка принтера: формат фото 10x15]");
    }

    @Override
    public void print(String printerName) {
        System.out.println(printerName + " печатает фото 10x15...");
        simulatePrinting();
    }

    private void simulatePrinting() {
        try {
            Thread.sleep(1200);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        System.out.println("Фото 10x15 напечатано.");
    }
}