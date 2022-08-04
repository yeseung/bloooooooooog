package com.example.bloooooooooog.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

//import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
//import org.hibernate.annotations.DynamicInsert;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
//@DynamicInsert //insert할때 null 인 필드 제외
@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
    
    @Column(nullable = false, length = 100, unique = true)
	private String username;
    
    @Column(nullable = false, length = 100)
	private String password;
    
    @Column(nullable = false, length = 50)
	private String email;
    
    //@ColumnDefault("'user'")
    @Enumerated(EnumType.STRING)
    private RoleType role;
    
    private String oauth;
    
    @CreationTimestamp
	private Timestamp createDate;

	/*@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", email=" + email + ", role="
				+ role + ", createDate=" + createDate + "]";
	}*/

	
    
    
	
	
}
