package com.example.simpleboardserver.domain;

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
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long boardId;

    @Lob
    private byte[] image;
    private LocalDateTime createdAt;

    public Image(Long boardId, byte[] image){
        this.boardId = boardId;
        this.image = image;
        this.createdAt = LocalDateTime.now();
    }
}
