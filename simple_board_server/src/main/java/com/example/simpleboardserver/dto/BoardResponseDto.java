package com.example.simpleboardserver.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
public class BoardResponseDto {
    private Long id;
    private String writer;
    private String title;
    private String content;
    private LocalDateTime createdAt;
    private byte[] image;
}
