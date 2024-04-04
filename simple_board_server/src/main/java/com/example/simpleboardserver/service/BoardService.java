package com.example.simpleboardserver.service;

import com.example.simpleboardserver.domain.Board;
import com.example.simpleboardserver.dto.BoardRequestDto;
import com.example.simpleboardserver.dto.BoardResponseDto;
import com.example.simpleboardserver.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;

    // 글 작성
    public String writing(BoardRequestDto dto) {
        boardRepository.save(dto.toBoard());
        return "글 작성 완료!";
    }

    // 모든 글 조회 (내용 빼고)
    public ArrayList<BoardResponseDto> getBoards() {
        List<Board> boardList = boardRepository.findAll();
        ArrayList<BoardResponseDto> boardResponseDtoList = new ArrayList<>();

        for (Board board : boardList) {
            boardResponseDtoList.add(board.of());
        }

        return boardResponseDtoList;
    }

    // 특정 글 조회 (by id)
    public Board getBoard(Long id) {
        return boardRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("해당 글은 없습니다"));
    }

    // 글 수정
    public String updateBoard(Long id, BoardRequestDto dto) {
        Board board = boardRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("해당 글은 없습니다"));
        boardRepository.save(board.update(dto));
        return "수정 완료";
    }
    // 글 삭제
    public String deleteBoard(Long id) {
        boardRepository.deleteById(id);
        return "삭제 성공";
    }
}
