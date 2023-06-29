package com.example.board.controller;

import com.example.board.dto.BoardResponseDto;
import com.example.board.dto.BoardSaveDto;
import com.example.board.dto.BoardUpdateDto;
import com.example.board.entity.Board;
import com.example.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/board")
public class BoardController {

    private final BoardService boardService;

    //전체 글 조회
    @GetMapping("")
    public List<BoardResponseDto> findAllBaord(){

        return boardService.findAllBaord();
    }

    // 글 등록
    @PostMapping("")
    public String save(@RequestBody BoardSaveDto saveDto) {

        return boardService.save(saveDto);
    }

    // 글 수정
    @PutMapping("/{id}")
    public String update(@PathVariable Long id,
                         @RequestBody BoardUpdateDto dto) {
        return boardService.update(id, dto);
    }

    // 글 하나 조회
    @GetMapping("/{id}")
    public BoardResponseDto findById(@PathVariable Long id) {
        BoardResponseDto responseDto = boardService.findById(id);
        int countVisit = responseDto.getCountVisit() +1;
        responseDto.setCountVisit(countVisit);
        boardService.updateVisit(id, countVisit);
        return responseDto;
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {

        return boardService.delete(id);
    }

}
