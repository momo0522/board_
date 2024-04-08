package com.example.simpleboardserver.dto;

import com.example.simpleboardserver.domain.Board;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class BoardRequestDto {
    private String writer;
    private String title;
    private String content;

    public Board toBoard(){
        return Board.builder()
                .writer(writer)
                .createdAt(LocalDateTime.now())
                .title(title)
                .content(content)
                .build();
    }
}
