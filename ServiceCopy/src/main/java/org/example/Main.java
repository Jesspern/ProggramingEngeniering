package org.example;

import org.example.entity.PrintRequest;
import org.example.enumEntities.ColorType;
import org.example.enumEntities.DocumentType;
import org.example.enumEntities.PhotoFormat;
import org.example.printer.color.abstr.PrintHandler;
import org.example.printer.color.implemention.BlackWhitePrinter;
import org.example.printer.color.implemention.ColorPrinter;
import org.example.printer.photo.imp.PhotoServiceProxy;

import java.util.Scanner;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Main {
    public static void main(String[] args) {
        BlockingQueue<PrintRequest> requestQueue = new LinkedBlockingQueue<>();

        PrintHandler blackWhitePrinter = new BlackWhitePrinter();
        PrintHandler colorPrinter = new ColorPrinter();
        blackWhitePrinter.setNext(colorPrinter);

        PrinterWorker printerWorker = new PrinterWorker(requestQueue, blackWhitePrinter);
        Thread printerThread = new Thread(printerWorker);
        printerThread.start();

        Scanner scanner = new Scanner(System.in);

        while(true) {
            System.out.println("Создать новый запрос? (yes/no)");
            String response = scanner.nextLine().trim();

            if (response.equals("no")) {
                System.out.println("Выход из программы.");
                break;
            }

            if (response.equals("yes")) {
                DocumentType documentType = askForDocumentType(scanner);
//                PhotoFormat photoFormat = asdForPhotoFormat(scanner);
//                boolean hasPhoto = false;

                if (documentType == DocumentType.PHOTO) {
                    // Если документ - фото, обрабатываем только фото
                    PhotoFormat photoFormat = asdForPhotoFormat(scanner);
                    boolean hasPhoto = askIfHasPhoto(scanner);
                    ColorType colorType = askForColorType(scanner);
                    PrintRequest newRequest = new PrintRequest(documentType, photoFormat, colorType, hasPhoto);

                    try {
                        requestQueue.put(newRequest); // Добавляем запрос в очередь
                        System.out.println("Запрос добавлен в очередь: " + newRequest);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                } else if (documentType == DocumentType.A4_DOCUMENT) {
                    // Если документ - обычный, обрабатываем только A4
                    ColorType colorType = askForColorType(scanner);
                    PrintRequest newRequest = new PrintRequest(documentType, null, colorType, false);

                    try {
                        requestQueue.put(newRequest); // Добавляем запрос в очередь
                        System.out.println("Запрос добавлен в очередь: " + newRequest);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }

//                if (documentType == DocumentType.PHOTO) {
//                    photoFormat = asdForPhotoFormat(scanner);
//                    hasPhoto = askIfHasPhoto(scanner);
//                }
//
//                ColorType colorType = askForColorType(scanner);
//
//                PrintRequest newRequest = new PrintRequest(documentType, photoFormat, colorType, hasPhoto);

//                try {
//                    requestQueue.put(newRequest);
//                    System.out.println("Запрос добавлен в очередь: " + newRequest);
//                } catch (InterruptedException e) {
//                    throw new RuntimeException(e);
//                }
            } else {
                System.out.println("Неверный ввод. Пожалуйста, введите 'yes' или 'no'.");
            }
        }

        try {
            printerThread.interrupt();
            printerThread.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private static DocumentType askForDocumentType(Scanner scanner) {
        System.out.println("Выберите тип документа (1 - A4, Не 1 - Фото): ");
        int choice = Integer.parseInt(scanner.nextLine().trim());
        return (choice == 1) ? DocumentType.A4_DOCUMENT : DocumentType.PHOTO;
    }

    private static PhotoFormat asdForPhotoFormat(Scanner scanner) {
        System.out.println("Выберите формат фото (1 - 10x15, Не 1 - 15x20): ");
        int choice = Integer.parseInt(scanner.nextLine().trim());
        return (choice == 1) ? PhotoFormat.PHOTO_10X15 : PhotoFormat.PHOTO_15X20;
    }

    private static boolean askIfHasPhoto(Scanner scanner) {
        System.out.println("Есть ли фото? (yes/no): ");
        return scanner.nextLine().trim().equalsIgnoreCase("yes");
    }

    private static ColorType askForColorType(Scanner scanner) {
        System.out.println("Выберите тип печати (1 - Черно-белая, Не 1 - Цветная): ");
        int choice = Integer.parseInt(scanner.nextLine().trim());
        return (choice == 1) ? ColorType.BLACK_WHITE : ColorType.COLOR;
    }
}

class PrinterWorker implements Runnable {
    private final BlockingQueue<PrintRequest> requestsQueue;
    private final PrintHandler printerChain;

    public PrinterWorker(BlockingQueue<PrintRequest> requestsQueue, PrintHandler printerChain) {
        this.requestsQueue = requestsQueue;
        this.printerChain = printerChain;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                PrintRequest request = requestsQueue.take();
                if (request != null) {
                    System.out.println("\nОбработка запроса: " + request);
                    if (request.getDocumentType() == DocumentType.PHOTO) {
                        PhotoServiceProxy photoServiceProxy = new PhotoServiceProxy();
                        photoServiceProxy.takePhoto(request);
                    }
                    printerChain.handle(request);
                }
            } catch (InterruptedException e) {
                System.out.println("Печать остановлена. Завершаем поток.");
                Thread.currentThread().interrupt(); // важно: повторно установить флаг
            }
        }
    }
}