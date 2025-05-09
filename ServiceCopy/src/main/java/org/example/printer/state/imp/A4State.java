package org.example.printer.state.imp;

import org.example.printer.state.in.PrinterState;

public class A4State implements PrinterState {
    @Override
    public void setup() {
        System.out.println("[Настройка принтера: формат A4]");
    }

    @Override
    public void print(String printerName) {
        System.out.println(printerName + " печатает документ A4...");
        simulatePrinting();
    }

    private void simulatePrinting() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        System.out.println("Документ A4 напечатан.");
    }
}
