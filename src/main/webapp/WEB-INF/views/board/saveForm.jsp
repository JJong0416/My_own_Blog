 <%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>

<%@ include file="../layout/header.jsp"%>

<div class="container">
	<form>

		<div class="form-group">
			<input type="text" class="form-control" placeholder="Enter title" id="title">
		</div>

		<div class="form-group">
  			<textarea class="form-control summernote" rows="5" id="content"></textarea>
		</div>
	</form>	
		<button id="btn-save" class="btn btn-primary">글쓰기 완료</button>
</div>

<script>
      $('.summernote').summernote({
        tabsize: 2,
        height: 300
      });
</script>
<script src = "/js/board.js"></script>
<%@ include file="../layout/footer.jsp"%>