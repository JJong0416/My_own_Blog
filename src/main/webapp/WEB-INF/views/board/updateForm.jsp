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
		<button id="btn-update" class="btn btn-primary">�ۼ��� �Ϸ�</button>
</div>

<script>
      $('.summernote').summernote({
        tabsize: 2,
        height: 300
      });
</script>
<script src = "/js/board.js"></script> 
<%@ include file="../layout/footer.jsp"%>