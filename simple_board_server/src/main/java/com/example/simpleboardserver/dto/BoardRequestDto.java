package com.example.simpleboardserver.dto;

import com.example.simpleboardserver.domain.Board;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BoardRequestDto {
    String writer;
    String title;
    String content;

    public Board toBoard(){
        return Board.builder()
                .createdAt(LocalDateTime.now())
                .writer(writer)
                .title(title)
                .content(content)
                .build();
    }
}
