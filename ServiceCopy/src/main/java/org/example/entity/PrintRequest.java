package org.example.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.enumEntities.ColorType;
import org.example.enumEntities.DocumentType;
import org.example.enumEntities.PhotoFormat;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PrintRequest {

    private DocumentType documentType;
    private PhotoFormat photoFormat;
    private ColorType colorType;
    private boolean hasPhoto;

}
