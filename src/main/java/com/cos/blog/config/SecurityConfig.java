package com.cos.blog.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.cos.blog.config.auth.PrincipalDetailService;

@Configuration // 빈등록(IoC)
@EnableWebSecurity // 시큐리티 필터 추가 -> 모든 컨트롤러가 들어오기전에 SecurityConfig Filter를 거치고 가는 것.
@EnableGlobalMethodSecurity(prePostEnabled = true) // 특정 주소로 접근을 하면 권한 및 인증을 미리 체크하겠다는 뜻 
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired
	private PrincipalDetailService principalDetailService;
	
	@Bean // 빈 등록하면 return 값을 IOC 처리해준다.
	public BCryptPasswordEncoder encodePWD() {
		String encPassword =  new BCryptPasswordEncoder().encode("1234");
		return new BCryptPasswordEncoder();
	 }
	
	//시큐리티가 대신 로그인을 하는데, password를 가로채는데,
	// 해당 password가 뭘로 해쉬가 되서 회원가입이 되었는지 알아야
	// 같은 해쉬로 암호화해서 DB에 있는 해쉬랑 비교할 수 있다.
	// 얘 안만들면 패스워드 비교가 힘들어진다.
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(principalDetailService).passwordEncoder(encodePWD());
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http
			.csrf().disable() // csrf 토큰 비활성화 (테스트 시 걸어두는게 좋다 )  default는 csrf Token을 걸어두지 않으면, default로 걸려서 시큐리티가 실행된다.
			.authorizeRequests()
				.antMatchers("/","/auth/**","/js/**","/css/**","/image/**")
				// 어떤 Request가 /auth 하위 URI쪽으로 들어오면 모두 허가해주고, 이게 아닌 다른 모든 요청은 인증이 되어야 한다.
				.permitAll()
				.anyRequest()
				.authenticated()
			.and()
				// 인증이 필요한 곳으로 요청이 오면 loginForm을 호출한다는 뜻이다.
				.formLogin()
				.loginPage("/auth/loginForm")
				.loginProcessingUrl("/auth/loginProc") // 스프링 시큐리티가 해당 주소로 오는 데이터를 가로채서 대신 로그인 한다.
				.defaultSuccessUrl("/"); // 정상일때는 /로 간다.
	}
}
