package com.example.board.service;

import com.example.board.dto.BoardResponseDto;
import com.example.board.dto.BoardSaveDto;
import com.example.board.dto.BoardUpdateDto;
import com.example.board.entity.Board;
import com.example.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;

@Service
@RequiredArgsConstructor
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

    public String delete(Long id) {
        Board board = boardRepository.findById(id).orElseThrow
                (() -> new IllegalArgumentException("해당 게시글이 없습니다. id="+id));
        boardRepository.delete(board);

        return id + " : 해당 글을 삭제했습니다.";
    }

    public BoardResponseDto findById(Long id) {
        Board board = boardRepository.findById(id).orElseThrow
                (() -> new IllegalArgumentException("해당 게시글이 없습니다. id="+id));
        int countVisit = board.getCountVisit() + 1;

        board.updateVisit(countVisit);

        return new BoardResponseDto(board);
    }

    public List<BoardResponseDto> findAllBaord() {
        return boardRepository.findAllDesc();
    }
}
