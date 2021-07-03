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

@Controller
public class UserController {

	@Value("${cos.key}") 
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
			
			RestTemplate rt = new RestTemplate(); 
			

			HttpHeaders headers = new HttpHeaders();
			headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
			

			MultiValueMap<String,String> params = new LinkedMultiValueMap<>();
			params.add("grant_type", "authorization_code");
			params.add("client_id", "052b189ac4466ca21788954d51f47345");
			params.add("redirect_uri", "http://localhost:7070/auth/kakao/callback");
			params.add("code", code);
			

			HttpEntity<MultiValueMap<String,String>> kakaoTokenRequest = 
						new HttpEntity<>(params,headers);
			
			ResponseEntity<String> response = rt.exchange(
					"https://kauth.kakao.com/oauth/token",
					HttpMethod.POST,
					kakaoTokenRequest,
					String.class // 응답받을 
			); 
			
			ObjectMapper objectMapper = new ObjectMapper();
			OAuthToken oauthToken = null;
			
			try {
				oauthToken = objectMapper.readValue(response.getBody(), OAuthToken.class);
			} catch(JsonMappingException e) {
				e.printStackTrace();
			} catch(JsonProcessingException e) {
				e.printStackTrace();
			}

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
			

			User originUser = userService.회원찾기(kakaoUser.getUsername());
			
			if (originUser.getUsername() == null){
				userService.회원가입(kakaoUser);
			}
			

			Authentication authentication = authenticationManger.authenticate(new UsernamePasswordAuthenticationToken(kakaoUser.getUsername(), cosKey));
			SecurityContextHolder.getContext().setAuthentication(authentication);			
			  
			return "redirect:/";
		}

}
