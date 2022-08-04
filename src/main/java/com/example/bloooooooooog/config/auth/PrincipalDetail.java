package com.example.bloooooooooog.config.auth;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.bloooooooooog.model.User;


import lombok.Getter;

@Getter
public class PrincipalDetail implements UserDetails {
	private User user; //콤포지션

	
	
	public PrincipalDetail(User user) {
		this.user = user;
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getUsername();
	}

	//계정 만료 리런 (true 만료안됨)
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	//계정 잠겨있지 않았는지 리턴 (true 잠기지 않음)
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	//비밀번호 만료 (true 만료안됨)
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	//활성화 (true 활성화)
	@Override
	public boolean isEnabled() {
		return true;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<GrantedAuthority> collectors = new ArrayList<>();
		/*collectors.add(new GrantedAuthority() {

			@Override
			public String getAuthority() {
				return "ROLE_" + user.getRole();
			}
			
		});*/
		collectors.add(()->{ return "ROLE_" + user.getRole(); });
		
		return collectors;
	}

}
