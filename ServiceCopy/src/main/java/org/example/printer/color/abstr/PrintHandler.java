package org.example.printer.color.abstr;

import org.example.entity.PrintRequest;

public abstract class PrintHandler {
    protected PrintHandler next;

    public void setNext(PrintHandler next) {
        this.next = next;
    }

    public abstract void handle(PrintRequest request);
}
