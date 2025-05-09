package org.example.printer.color.implemention;

import org.example.entity.PrintRequest;
import org.example.enumEntities.ColorType;
import org.example.printer.Printer;
import org.example.printer.color.abstr.PrintHandler;

public class BlackWhitePrinter extends PrintHandler {

    @Override
    public void handle(PrintRequest request) {
        if (request.getColorType() == ColorType.BLACK_WHITE) {
            System.out.println("BlackWritePrinter: обработка запроса...");
            Printer printer = new Printer("Black & White Printer");
            printer.print(request);
        } else if (next != null) {
            System.out.println("BlackWhitePrinter: передаёт запрос дальше...");
            next.handle(request);
        } else {
            System.out.println("BlackWhitePrinter: не может обработать запрос и нет следующего обработчика.");
        }
    }
}
