package com.example.board.repository;

import com.example.board.dto.BoardResponseDto;
import com.example.board.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {
    @Query("SELECT b FROM Board b ORDER BY b.id DESC")
    List<BoardResponseDto> findAllDesc();
}
