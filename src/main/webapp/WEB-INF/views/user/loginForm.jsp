 <%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>

<div class="container">
	<form action ="/auth/loginProc" method = "post">

		<div class="form-group">
			<label for="username">Username</label> 
			<input type="text" name = "username" class="form-control" placeholder="Enter username" id="username">
		</div>

		<div class="form-group">
			<label for="password">Password</label> 
			<input type="password" name = "password" class="form-control" placeholder="Enter password" id="password">
		</div>

	<button id="btn-login" class="btn btn-primary">로그인</button>
	<a href="https://kauth.kakao.com/oauth/authorize?client_id=052b189ac4466ca21788954d51f47345&redirect_uri=http://localhost:7070/auth/kakao/callback&response_type=code
"><img height = "38px" src="/image/kakao_login_button.png"/></a>
	</form>
</div>

<%@ include file="../layout/footer.jsp"%>
