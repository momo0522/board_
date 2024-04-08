package com.example.simpleboardserver.controller;

import com.example.simpleboardserver.domain.Board;
import com.example.simpleboardserver.domain.Image;
import com.example.simpleboardserver.dto.BoardRequestDto;
import com.example.simpleboardserver.dto.BoardResponseDto;
import com.example.simpleboardserver.dto.ImageRequestDto;
import com.example.simpleboardserver.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;

    // 글 작성
    @PostMapping("/write")
    public ResponseEntity<String> writing(@RequestBody BoardRequestDto board) {
        return ResponseEntity.ok(boardService.writing(board));
    }

    // 모든 글 조회
    @GetMapping("/view")
    public ResponseEntity<ArrayList<BoardResponseDto>> getBoards() {
        return ResponseEntity.ok(boardService.getBoards());
    }

    // 특정 글 조회
    @GetMapping("/view/{id}")
    public ResponseEntity<Board> getBoard(@PathVariable Long id) {
        return ResponseEntity.ok(boardService.getBoard(id));
    }

    @DeleteMapping("/view/{id}")
    public ResponseEntity<String> deleteBoard(@PathVariable Long id){
        return ResponseEntity.ok(boardService.deleteBoard(id));
    }

    @PutMapping("/view/{id}")
    public ResponseEntity<String> updateBoard(@PathVariable Long id, @RequestBody BoardRequestDto dto){
        return ResponseEntity.ok(boardService.updateBoard(id, dto));
    }

    @PostMapping("/view/image")
    public ResponseEntity<String> saveImage(@RequestBody ImageRequestDto dto){
        return ResponseEntity.ok(boardService.saveImage(dto));
    }

    @GetMapping("/view/image/{boardId}")
    public ResponseEntity<Image> getImage(@PathVariable Long boardId){
        return ResponseEntity.ok(boardService.getImage(boardId));

    }
}
