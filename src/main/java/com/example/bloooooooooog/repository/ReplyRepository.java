package com.example.bloooooooooog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.example.bloooooooooog.model.Reply;

public interface ReplyRepository extends JpaRepository<Reply, Integer> {
	
	//@Transactional
	@Modifying
	@Query(value="INSERT INTO reply(userId, BoardId, content, createDate) "
			+ "VALUES(?1, ?2, ?3, now())", nativeQuery=true)
	int mSave(int userId, int boardId, String content); //업데이트된 행의 개수를 리턴
}
