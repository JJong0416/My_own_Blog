<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>Bootstrap Example</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</head>
<body>

	<nav class="navbar navbar-expand-md bg-dark navbar-dark">
		<a class="navbar-brand" href="/blog">블로그</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#collapsibleNavbar">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="collapsibleNavbar">
			<ul class="navbar-nav">
				<li class="nav-item"><a class="nav-link" href="/user/loign">로그인</a></li>
				<li class="nav-item"><a class="nav-link" href="/user/join">회원가입</a></li>
			</ul>
		</div>
	</nav>
	<br>
	
	<div class="container">
		<div class="card m-4" >
			<div class="card-body">
				<h4 class="card-title">제목 적는 부분</h4>
				<p class="card-text">내용 적는 부분</p>
				<a href="#" class="btn btn-primary">상세 보기</a>
			</div>
		</div>
	</div>
	
		<div class="container">
		<div class="card m-4" >
			<div class="card-body">
				<h4 class="card-title">제목 적는 부분</h4>
				<p class="card-text">내용 적는 부분</p>
				<a href="#" class="btn btn-primary">상세 보기</a>
			</div>
		</div>
	</div>
	
		<div class="container">
		<div class="card m-4" >
			<div class="card-body">
				<h4 class="card-title">제목 적는 부분</h4>
				<p class="card-text">내용 적는 부분</p>
				<a href="#" class="btn btn-primary">상세 보기</a>
			</div>
		</div>
	</div>
	
	

	<div class="jumbotron text-center" style="margin-bottom: 0">
		<!-- 모든 홈페이지에는 footer가 들어가야한다  -->
		<p>Create By Park Jong Chan</p>
		<p>Phone : 010-xxxx-xxxx</p>
		<p>서울시 영등포구 대방천로</p>

	</div>

</body>
</html>
