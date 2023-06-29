package com.example.board.service;

import com.example.board.dto.BoardResponseDto;
import com.example.board.dto.BoardSaveDto;
import com.example.board.dto.BoardUpdateDto;
import com.example.board.entity.Board;
import com.example.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.w3c.dom.stylesheets.LinkStyle;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class BoardService {
    private final BoardRepository boardRepository;

    public String save(BoardSaveDto saveDto) {
        Board newBoard = boardRepository.save(saveDto.toEntity());
        return newBoard.getId() + " : 글이 등록되었습니다.";
    }

    public String update(Long id, BoardUpdateDto updateDto) {
        Board board = boardRepository.findById(id).orElseThrow
                (() -> new IllegalArgumentException("해당 게시글이 없습니다. id="+id));

        board.update(updateDto);

        return id + " : 글이 수정되었습니다.";
    }
    public void updateVisit(Long id, int count) {
        Board board = boardRepository.findById(id).orElseThrow
                (() -> new IllegalArgumentException("해당 게시글이 없습니다. id="+id));
        log.info("조회수 증가 시작");
        log.info("원래 조회수 : " + board.getCountVisit());
        board.updateVisit(count);

        log.info("결과 : " + board.getCountVisit());
    }

    public String delete(Long id) {
        Board board = boardRepository.findById(id).orElseThrow
                (() -> new IllegalArgumentException("해당 게시글이 없습니다. id="+id));
        boardRepository.delete(board);

        return id + " : 해당 글을 삭제했습니다.";
    }

    public BoardResponseDto findById(Long id) {
        Board board = boardRepository.findById(id).orElseThrow
                (() -> new IllegalArgumentException("해당 게시글이 없습니다. id="+id));

        return new BoardResponseDto(board);
    }

    public List<BoardResponseDto> findAllBaord() {
        try {
            List<Board> boards = boardRepository.findAll();
            List<BoardResponseDto> responseDtos = new ArrayList<>();
            for(Board board : boards) {
                responseDtos.add(new BoardResponseDto(board));
            }
            return responseDtos;
        } catch (Exception e) {
        }
        return null;
    }
}
