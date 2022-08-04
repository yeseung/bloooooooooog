package com.example.bloooooooooog.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.bloooooooooog.dto.ReplySaveRequestDto;
import com.example.bloooooooooog.model.Board;
import com.example.bloooooooooog.model.Reply;
import com.example.bloooooooooog.model.RoleType;
import com.example.bloooooooooog.model.User;
import com.example.bloooooooooog.repository.BoardRepository;
import com.example.bloooooooooog.repository.ReplyRepository;
import com.example.bloooooooooog.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardService {

	private final BoardRepository boardRepository;
	private final ReplyRepository replyRepository;
	private final UserRepository userRepository;
	
	@Transactional
	public void 글쓰기(Board board, User user) {

		board.setCount(1);
		board.setUser(user);
		
		boardRepository.save(board);
	}

	@Transactional(readOnly = true)
	public Page<Board> 글목록(Pageable pageable) {
		return boardRepository.findAll(pageable);
	}

	@Transactional(readOnly = true)
	public Board 글상세보기(int id) {
		/*Board board = boardRepository.findById(id)
				.orElseThrow(()->{
					return new IllegalArgumentException("글 상세보기 실패 : " + id);
				});*/
		
		Board board = boardRepository.findById(id).get();
		
		//System.out.println("_______" + boardRepository.boardCnt(id));
		
		//board.setCount(boardRepository.boardCnt(id) + 1); //
		//boardRepository.save(board);
		
		return board;

		
	}

	@Transactional
	public void 삭제하기(int id) {
		System.out.println("_________삭제 : " + id);
		boardRepository.deleteById(id);
	}

	@Transactional
	public void 글수정하기(int id, Board requestBoard) {
		Board board = boardRepository.findById(id)
			.orElseThrow(()->{
				return new IllegalArgumentException("글 찾기 실패 : " + id);
			}); //영속화 완료
		
		board.setTitle(requestBoard.getTitle());
		board.setContent(requestBoard.getContent());
		//더티체킹 - 자동 업데이크 db flush
		
		

		
		
	}

	@Transactional
	public void 댓글쓰기(User user, int boardId, Reply requestReply) {
		
		
		Board board = boardRepository.findById(boardId).orElseThrow(()->{
			return new IllegalArgumentException("댓글 쓰기 실패 - 게시글 id를 찾을 수 없습니다. : " + boardId);
		}); //영속화 완료
				
		requestReply.setUser(user);
		requestReply.setBoard(board);
		
		replyRepository.save(requestReply);
		
		
	}

	@Transactional
	public void 댓글쓰기1(ReplySaveRequestDto replySaveRequestDto) {
		
		User user = userRepository.findById(replySaveRequestDto.getUserId()).orElseThrow(()->{
			return new IllegalArgumentException("댓글 쓰기 실패 - 유저 id를 찾을 수 없습니다. : " + replySaveRequestDto.getUserId());
		}); //영속화 완료
		
		
		Board board = boardRepository.findById(replySaveRequestDto.getBoardId()).orElseThrow(()->{
			return new IllegalArgumentException("댓글 쓰기 실패 - 게시글 id를 찾을 수 없습니다. : " + replySaveRequestDto.getBoardId());
		}); //영속화 완료
		
		/*Reply reply = Reply.builder()
				.user(user)
				.board(board)
				.content(replySaveRequestDto.getContent())
				.build();*/
		
		Reply reply = new Reply();
		reply.update(user, board, replySaveRequestDto.getContent());
		
		
		
		replyRepository.save(reply);
		
		// TODO Auto-generated method stub
		
	}
	
	@Transactional
	public void 댓글쓰기2(ReplySaveRequestDto replySaveRequestDto) {
	
		System.out.println("==============" + replySaveRequestDto);
		int result = replyRepository.mSave(replySaveRequestDto.getUserId(), replySaveRequestDto.getBoardId(), replySaveRequestDto.getContent());
		System.out.println(";;;;;;;;;;;;;;;;" + result);
		System.out.println("--------------------" + replySaveRequestDto);
		
	}

	@Transactional
	public void 댓글삭제(int replyId) {
		replyRepository.deleteById(replyId);
	}
}
