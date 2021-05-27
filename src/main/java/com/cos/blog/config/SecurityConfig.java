package com.cos.blog.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration // 빈등록(IoC)
@EnableWebSecurity // 시큐리티 필터 추가 -> 모든 컨트롤러가 들어오기전에 SecurityConfig Filter를 거치고 가는 것.
@EnableGlobalMethodSecurity(prePostEnabled = true) // 특정 주소로 접근을 하면 권한 및 인증을 미리 체크하겠다는 뜻 
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http 
			.authorizeRequests()
				.antMatchers("/auth/**")
				// 어떤 Request가 /auth 하위 URI쪽으로 들어오면 모두 허가해주고, 이게 아닌 다른 모든 요청은 인증이 되어야 한다.
				.permitAll()
				.anyRequest()
				.authenticated()
			.and()
				// 인증이 필요한 곳으로 요청이 오면 loginForm을 호출한다는 뜻이다.
				.formLogin()
				.loginPage("/auth/loginForm");
	}	
}
