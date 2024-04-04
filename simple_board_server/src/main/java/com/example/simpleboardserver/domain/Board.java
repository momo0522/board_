package com.example.simpleboardserver.domain;

import com.example.simpleboardserver.dto.BoardRequestDto;
import com.example.simpleboardserver.dto.BoardResponseDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String writer;
    private String title;
    @Column(length = 1000)
    private String content;
    private LocalDateTime createdAt;

    public BoardResponseDto of() {
        return BoardResponseDto.builder()
                .id(id)
                .writer(writer)
                .title(title)
                .createdAt(createdAt)
                .build();
    }

    public Board update(BoardRequestDto dto){
        writer = dto.getWriter();
        title = dto.getTitle();
        content = dto.getContent();

        return this;
    }
}
