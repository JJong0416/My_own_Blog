package com.cos.blog.config.auth;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.cos.blog.model.User;

import lombok.Data;
import lombok.Getter;

// 스프링 시큐리티가 로그인 요청을 가로채서 로그인을 진행하고 완료가 되면, UserDetails 타입의 오브젝트를
// 스프링 시큐리티의 고유한 세션저장소에 저장을 해준다.
// 그때 저장되는게 UserDetails 타입의 PrincipalDetail가 저장이 된다.
@Data
public class PrincipalDetail implements UserDetails {
	private User user;
	

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

	// 계정이 만료되지 않았는지 리턴 (true : 만료 안됨)
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	// 계정이 잠겨있는지 아닌지 리턴 (true : 만료 안됨)
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	// 비밀번호가 만료되지 않았는지 리턴한다. (true : 만료 안됨)
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	// 계정활성화(사용가능)인지 리턴한다. (true: 활성화)
	@Override
	public boolean isEnabled() {
		return true;
	}
	
	// 계정이 어떤 권한을 가졌는지 리턴한다.
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<GrantedAuthority> collectors =  new ArrayList<>();
		
		collectors.add(()->{ return "ROLE_"+user.getRole();}); // 람다식 쓸 수 있는 이유는 GrantedAuthority 안에 메서드가 하나밖에 없어서 사용 가능
		return collectors;
	}
	
}

/*
 * collectors.add(new GrantedAuthority() {
 * 
 * @Override // ROLE_ 이건 스프링 규칙이야! 꼭 알아둬 public String getAuthority() { return
 * "ROLE_"+user.getRole(); // "ROLE_USER" 리턴! } });
 */