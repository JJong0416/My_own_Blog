<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">
<head>
<title>��α�</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<!--  ��ũ��Ʈ�� �ٵ� ���ܿ� �ִ� ������  JS�� ���������� ����̱� ������, ������ �ΰ�.-->
</head>
<body>

	<nav class="navbar navbar-expand-md bg-dark navbar-dark">
		<a class="navbar-brand" href="/blog">��α�</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#collapsibleNavbar">
			<span class="navbar-toggler-icon"></span>
		</button>

		<div class="collapse navbar-collapse" id="collapsibleNavbar">
			<c:choose>
				<c:when test="${empty sessionScope.principal}">
					<ul class="navbar-nav">
						<li class="nav-item"><a class="nav-link" href="/blog/user/loginForm">�α���</a></li>
						<li class="nav-item"><a class="nav-link" href="/blog/user/joinForm">ȸ������</a></li>
					</ul>
				</c:when>
				<c:otherwise>
					<ul class="navbar-nav">
						<li class="nav-item"><a class="nav-link" href="/blog/board/writeForm">�۾���</a></li>
						<li class="nav-item"><a class="nav-link" href="/blog/user/userForm">ȸ������</a></li>
						<li class="nav-item"><a class="nav-link" href="/blog/user/logout">�α׾ƿ�</a></li>
					</ul>
				</c:otherwise>
			</c:choose>


		</div>
	</nav>
	<br>
	<br />