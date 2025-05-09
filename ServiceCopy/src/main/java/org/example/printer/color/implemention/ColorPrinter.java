package org.example.printer.color.implemention;

import org.example.entity.PrintRequest;
import org.example.enumEntities.ColorType;
import org.example.printer.Printer;
import org.example.printer.color.abstr.PrintHandler;

public class ColorPrinter extends PrintHandler {
    @Override
    public void handle(PrintRequest request) {
        if (request.getColorType() == ColorType.COLOR) {
            System.out.println("ColorPrinter: обработка запроса...");
            Printer printer = new Printer("Color Printer");
            printer.print(request);
        } else {
            System.out.println("ColorPrinter: не может обработать запрос.");
        }
    }
}
