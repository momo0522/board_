package com.example.simpleboardserver.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
public class BoardResponseListDto {
    private Long id;
    private String writer;
    private String title;
    private LocalDateTime createdAt;
}
