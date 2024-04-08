package com.example.simpleboardserver.service;

import com.example.simpleboardserver.domain.Board;
import com.example.simpleboardserver.domain.Image;
import com.example.simpleboardserver.dto.BoardRequestDto;
import com.example.simpleboardserver.dto.BoardResponseDto;
import com.example.simpleboardserver.dto.BoardResponseListDto;
import com.example.simpleboardserver.dto.ImageRequestDto;
import com.example.simpleboardserver.repository.BoardRepository;
import com.example.simpleboardserver.repository.ImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;
    private final ImageRepository imageRepository;

    // 글 작성
    public Long writing(BoardRequestDto dto){
        Board board = dto.toBoard();
        boardRepository.save(board);
        return board.getId();
    }

    // 모든 글 조회 (내용 빼고)
    public ArrayList<BoardResponseListDto> getBoards() {
        List<Board> boardList = boardRepository.findAll();
        ArrayList<BoardResponseListDto> boardResponseListDtoList = new ArrayList<>();

        for (Board board : boardList) {
            boardResponseListDtoList.add(board.of());
        }

        return boardResponseListDtoList;
    }

    // 특정 글 조회 (by id)
    public BoardResponseDto getBoard(Long id) {
        Board board = boardRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("해당 글은 없습니다"));
        Image image = imageRepository.findByBoardId(board.getId()).orElse(null);
        return buildBoardDto(board, image != null? image.getImage() : null);
    }

    private BoardResponseDto buildBoardDto(Board board, byte[] image) {
        return BoardResponseDto.builder()
                .createdAt(board.getCreatedAt())
                .content(board.getContent())
                .writer(board.getWriter())
                .title(board.getTitle())
                .image(image)
                .id(board.getId())
                .build();
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

    public String saveImage(Long boardId, MultipartFile image) throws IOException {

        imageRepository.save(new Image(boardId, image.getBytes()));
        return "저장 성공";
    }

    public Image getImage(Long boardId){
        return imageRepository.findByBoardId(boardId).orElse(null);
    }
}
