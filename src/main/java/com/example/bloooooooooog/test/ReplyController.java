package com.example.bloooooooooog.test;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.bloooooooooog.model.Board;
import com.example.bloooooooooog.model.Reply;
import com.example.bloooooooooog.repository.*;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ReplyController {

	private final BoardRepository boardRepository;
	private final ReplyRepository replyRepository;
	
	
	@GetMapping("/test/board/{id}")
	public Board getBoard(@PathVariable int id) {
		/*return boardRepository.findById(id).orElseGet(()->{
			return new Board();
		});*/
		
		return boardRepository.findById(id).get(); //모델의 getter를 호출
	}
	
	
	@GetMapping("/test/reply")
	public List<Reply> getReply() {
		return replyRepository.findAll();
	}
	
		
}
