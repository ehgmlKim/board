package com.example.board.dto;

import com.example.board.entity.Board;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BoardSaveDto {
    private String title;
    private String content;
    private String createdBy;

    public Board toEntity() {
        return Board.builder()
                .title(title)
                .content(content)
                .createdBy(createdBy)
                .build();
    }
}
