package com.example.board.entity;

import com.example.board.dto.BoardUpdateDto;
import lombok.*;

import javax.persistence.*;
@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Board extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="board_id")
    private Long id;
    private String title;
    private String content;
    private String createdBy;
    @Column(columnDefinition = "integer default 0", nullable = false)
    private int countVisit;     // 조회수

    public void update(BoardUpdateDto updateDto) {
        this.title = updateDto.getTitle();
        this.content = updateDto.getContent();
    }

    public void updateVisit(int countVisit) {
        this.countVisit = countVisit;
    }
}
