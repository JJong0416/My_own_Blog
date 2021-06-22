 <%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>

<%@ include file="../layout/header.jsp"%>

<div class="container">

	<form>
		<input type ="hidden" id="id" value = "${board.id}"/>
		<div class="form-group">
			<input value = "${board.title}" type="text" class="form-control" placeholder="Enter title" id="title">
		</div>

		<div class="form-group">
  			<textarea class="form-control summernote" rows="5" id="content">${board.content}</textarea>
		</div>
	</form>	
		<button id="btn-update" class="btn btn-primary">글수정 완료</button> <!--  버튼을 Json으로 받기 위해 Form 밖으로 뺀다 -->
</div>

<script>
      $('.summernote').summernote({
        tabsize: 2,
        height: 300
      });
</script>
<script src = "/js/board.js"></script> <!--  여기서 글쓰기 버튼을 누르면 board.js로 먼저 넘어가고, title과 content를 ajax로 넘어가서 다시 Json형태로 다시 날린다. -->
<%@ include file="../layout/footer.jsp"%>