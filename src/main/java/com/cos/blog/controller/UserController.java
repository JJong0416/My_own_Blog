package com.cos.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

import com.cos.blog.model.KakaoProfile;
import com.cos.blog.model.OAuthToken;
import com.cos.blog.model.User;
import com.cos.blog.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

// 인증이 안된 사용자들이 출입할 수 있는 경로를 /auth를 표시해주고 허용한다.
// 그냥 주소가 /이면 index.jsp 허용
//static 이하 resource file (css,image,js)
//ResponseBody는 Data를 리턴해주는 컨트롤러 함수가 된다. return "카카오 인증 완료" 하면 html메세지가 뜬다

@Controller
public class UserController {

	@Value("${cos.key}") // yml 파일에서 값을 주입 받도록 할 수 있는 코드
	private String cosKey;
	
	@Autowired
	private AuthenticationManager authenticationManger;
	
	@Autowired
	private UserService userService;
	
		@GetMapping("/auth/joinForm")
		public String joinForm() {
			return "user/joinForm";
		}
		
		@GetMapping("/auth/loginForm")
		public String loginForm() {
			return "user/loginForm";
		}

		@GetMapping("/user/updateForm")
		public String updateForm() {
			return "user/updateForm";
		}
		
		@GetMapping("auth/kakao/callback")
		 public String kakaoCallback(String code) {  
			
			// Post 방식으로 key=value 타입으로 카카오쪽으로 4개의 데이터를 요청해야한다
			RestTemplate rt = new RestTemplate(); // HttpsURLConnection으로 안하고 RestTemplate 사용
			
			// HttpHeader 생성
			HttpHeaders headers = new HttpHeaders();
			headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8"); // Content타입을 담는다는 의미는 전송한 http바디 데이터가 key=value형태라고 헤더에 알려주는 것
			
			// HttpBody 오브젝트 생성
			MultiValueMap<String,String> params = new LinkedMultiValueMap<>();
			params.add("grant_type", "authorization_code");
			params.add("client_id", "052b189ac4466ca21788954d51f47345");
			params.add("redirect_uri", "http://localhost:7070/auth/kakao/callback");
			params.add("code", code);
			
			// HttpHeader와 HttpBody를 하나의 오브젝트로 담기
			HttpEntity<MultiValueMap<String,String>> kakaoTokenRequest = 
						new HttpEntity<>(params,headers);
			
			// Http 요청하기 - post 방식으로 - 그리고 Response 변수의 응답 받음.
			ResponseEntity<String> response = rt.exchange(
					"https://kauth.kakao.com/oauth/token",
					HttpMethod.POST,
					kakaoTokenRequest,
					String.class // 응답받을 
			); // response가 String으로 토큰을 받음
			
			// Gson, Json Simple, ObjectMapper 라이브러리를 사용
			ObjectMapper objectMapper = new ObjectMapper();
			OAuthToken oauthToken = null;
			
			try {
				oauthToken = objectMapper.readValue(response.getBody(), OAuthToken.class);
			} catch(JsonMappingException e) {
				e.printStackTrace();
			} catch(JsonProcessingException e) {
				e.printStackTrace();
			}
			
			System.out.println("카카오 엑세스 토큰 : "+oauthToken.getAccess_token());


			RestTemplate rt2 = new RestTemplate();
			

			HttpHeaders headers2 = new HttpHeaders();
			headers2.add("Authorization", "Bearer "+oauthToken.getAccess_token());
			headers2.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
			

			HttpEntity<MultiValueMap<String,String>> kakaoProfileRequest2 = 
						new HttpEntity<>(headers2);
			
			ResponseEntity<String> response2 = rt2.exchange(
					"https://kapi.kakao.com/v2/user/me",
					HttpMethod.POST,
					kakaoProfileRequest2,
					String.class 
			); 
			System.out.println(response2.getBody());
			
			ObjectMapper objectMapper2 = new ObjectMapper();
			KakaoProfile kakaoprofile = null;
			
			try {
				kakaoprofile = objectMapper2.readValue(response2.getBody(), KakaoProfile.class);
			} catch(JsonMappingException e) {
				e.printStackTrace();
			} catch(JsonProcessingException e) {
				e.printStackTrace();
			}
			
			User kakaoUser = User.builder()
					.username(kakaoprofile.getKakao_account().getEmail() + "_" +  kakaoprofile.getId())
					.password(cosKey)
					.email(kakaoprofile.getKakao_account().getEmail())
					.oauth("kakao")
					.build();
			
			// 가입자 & 비가입자 체크해서 처리
			User originUser = userService.회원찾기(kakaoUser.getUsername());
			
			if (originUser.getUsername() == null){
				userService.회원가입(kakaoUser);
			}
			
			// 로그인 처리 
			Authentication authentication = authenticationManger.authenticate(new UsernamePasswordAuthenticationToken(kakaoUser.getUsername(), cosKey));
			SecurityContextHolder.getContext().setAuthentication(authentication);			
			  
			return "redirect:/";
		}

}
