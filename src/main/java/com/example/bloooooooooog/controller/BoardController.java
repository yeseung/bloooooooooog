package com.example.bloooooooooog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.bloooooooooog.config.auth.PrincipalDetail;
import com.example.bloooooooooog.model.Board;
import com.example.bloooooooooog.model.User;
import com.example.bloooooooooog.repository.BoardRepository;
import com.example.bloooooooooog.service.BoardService;
import com.example.bloooooooooog.service.UserService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class BoardController {
	
	private final BoardService boardService;
	//private final BoardRepository boardRepository;
	
	
	/*@GetMapping({"", "/"})
	public String index(@AuthenticationPrincipal PrincipalDetail principal) {
		System.out.println("11111111 : " + principal.getUsername());
		return "index";
	}*/
	
	
	@GetMapping({"", "/"})
	public String index(Model model,
			@PageableDefault(size=3, sort="id", direction = Sort.Direction.DESC) Pageable pageable) {
		
		Page<Board> p = boardService.글목록(pageable);
		model.addAttribute("boards", p);
		return "index";
		
	}
	
	
	@GetMapping("/board/saveForm")
	public String saveForm() {
		return "board/saveForm";
	}
	
	
	@GetMapping("/board/{id}")
	public String findById(@PathVariable int id, Model model) {
		Board board = boardService.글상세보기(id);
		
		//Board board = boardRepository.findById(id).get();
		
		model.addAttribute("board", board);
		
		System.out.println(board);
	
		return "board/view";
	}
	
	@GetMapping("/board/{id}/updateForm")
	public String updateForm(@PathVariable int id, Model model) {
		model.addAttribute("board", boardService.글상세보기(id));
		return "board/updateFrom";
	}
	
	


}
