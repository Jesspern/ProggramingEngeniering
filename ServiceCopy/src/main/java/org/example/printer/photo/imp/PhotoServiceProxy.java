package org.example.printer.photo.imp;

import org.example.entity.PrintRequest;
import org.example.printer.photo.in.PhotoService;

public class PhotoServiceProxy implements PhotoService {
    private RealPhotoService realPhotoService;

    @Override
    public void takePhoto(PrintRequest request) {
        if (!request.isHasPhoto()) {
            System.out.println("Запрос без фотографии. Обращаемся к фото-сервису...");
            if (realPhotoService == null) {
                this.realPhotoService = new RealPhotoService();
            }
            realPhotoService.takePhoto(request);
        } else {
            System.out.println("Фото уже присутствует в запросе. Фото-сервис не требуется.");
        }
    }
}
