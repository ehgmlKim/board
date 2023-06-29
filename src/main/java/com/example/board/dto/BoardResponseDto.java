package com.example.board.dto;

import com.example.board.entity.Board;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class BoardResponseDto {
    private Long id;
    private String title;
    private String content;
    private int countVisit;
    private LocalDateTime lastModifiedDate;

    public BoardResponseDto(Board board) {
        this.id = board.getId();
        this.title = board.getTitle();
        this.content = board.getContent();
        this.lastModifiedDate = board.getLastModifiedDate();
        this.countVisit = board.getCountVisit();
    }
}
