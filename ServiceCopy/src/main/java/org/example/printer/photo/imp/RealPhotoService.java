package org.example.printer.photo.imp;

import org.example.entity.PrintRequest;
import org.example.printer.photo.in.PhotoService;

public class RealPhotoService implements PhotoService {

    @Override
    public void takePhoto(PrintRequest request) {
        System.out.println("[Фотограф делает снимок клиента...]");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        request.setHasPhoto(true);
        System.out.println("Фото готово и добавлено в запрос.");
    }
}
