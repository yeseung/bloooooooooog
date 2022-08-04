package com.example.bloooooooooog.test;

//import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
//@AllArgsConstructor
@NoArgsConstructor
public class Member {

	private int id;
	private String name;
	private String pass;
	private String email;
	
	@Builder
	public Member(int id, String name, String pass, String email) {
		this.id = id;
		this.name = name;
		this.pass = pass;
		this.email = email;
	}
	
	

}
