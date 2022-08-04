package com.example.bloooooooooog.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Reply {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne //Many = Board, One = Reply
    @JoinColumn(name = "BoardId")
    private Board board;
	
	@ManyToOne //Many = Board, One = User
    @JoinColumn(name = "userId")
    private User user;
	
    
    @Column(nullable = false, length = 200)
	private String content;
    
    
    @CreationTimestamp
	private Timestamp createDate;
    
    public void update(User user, Board board, String content) {
    	setUser(user);
    	setBoard(board);
    	setContent(content);
    }

}
