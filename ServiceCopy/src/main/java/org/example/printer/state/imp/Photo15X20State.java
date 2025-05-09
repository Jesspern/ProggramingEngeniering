package org.example.printer.state.imp;

import org.example.printer.state.in.PrinterState;

public class Photo15X20State implements PrinterState {
    @Override
    public void setup() {
        System.out.println("[Настройка принтера: формат фото 15x20]");
    }

    @Override
    public void print(String printerName) {
        System.out.println(printerName + " печатает фото 15x20...");
        simulatePrinting();
    }

    private void simulatePrinting() {
        try {
            Thread.sleep(1400);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        System.out.println("Фото 15x20 напечатано.");
    }
}
