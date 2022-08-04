package com.example.bloooooooooog.model;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


//@Data

@ToString(exclude={"replys"})
@Getter
@Setter

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity

public class Board {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
    
    @Column(nullable = false, length = 100)
	private String title;
    
    @Lob
	private String content;
    
    //@ColumnDefault("0")
    private int count;
    
    @ManyToOne(fetch = FetchType.EAGER) //Many = Board, One = User
    @JoinColumn(name = "userId")
    private User user;
    
    @JsonIgnoreProperties({"board"})
    @OneToMany(mappedBy = "board", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE) //FetchType.LAZY
    //연관관계의 주인이 아니다 (난 FK가 아니에요)
    @OrderBy("id desc")
    private List<Reply> replys;
    
    @CreationTimestamp
	private Timestamp createDate;

	/*@Override
	public String toString() {
		return "Board [id=" + id + ", title=" + title + ", content=" + content + ", count=" + count + ", user=" + user
				+ ", replys=" + replys + ", createDate=" + createDate + "]";
	}

	@Override
	public String toString() {
		return "Board [id=" + id + ", title=" + title + ", content=" + content + ", count=" + count + ", user=" + user
				+ ", createDate=" + createDate + "]";
	}*/
    
    
	
	
	

    
    
    

}
