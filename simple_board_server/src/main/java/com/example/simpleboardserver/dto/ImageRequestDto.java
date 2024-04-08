package com.example.simpleboardserver.dto;

import lombok.Data;


@Data
public class ImageRequestDto {
    private Long boardId;
    private String writer;
    private byte[] image;
}
