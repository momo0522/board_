package com.example.simpleboardserver.dto;

import com.example.simpleboardserver.domain.Image;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ImageRequestDto {
    private Long id;
    private String writer;
    private byte[] image;

    public Image toImage(){
        return Image.builder()
                .createdAt(LocalDateTime.now())
                .image(image)
                .boardId(id)
                .writer(writer)
                .build();
    }
}
