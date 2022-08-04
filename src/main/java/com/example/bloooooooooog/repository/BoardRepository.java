package com.example.bloooooooooog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.bloooooooooog.model.Board;

public interface BoardRepository extends JpaRepository<Board, Integer> {

	
	@Query(value="SELECT count FROM board WHERE id = ?", nativeQuery=true)
	int boardCnt(int id);
}
