 <%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>

<%@ include file="layout/header.jsp"%>
<!--  상대경로이므로 /붙이면 안된다. -->

<div class="container">

<c:forEach var="board"  items="${boards.content}">

	<div class="card m-4">
		<div class="card-body">
			<h4 class="card-title">${board.title }</h4>
			<a href="/board/${board.id}" class="btn btn-primary">상세 보기</a>
		</div>
	</div>

</c:forEach>
<ul class="pagination justify-content-center">
  <c:choose>
    
    <c:when test="${boards.first }">
        <li class="page-item disabled"><a class="page-link" href="?page=${boards.number-1 }">Previous</a></li>
    </c:when>
    <c:otherwise>
	      <li class="page-item"><a class="page-link" href="?page=${boards.number-1 }">Previous</a></li>
    </c:otherwise>
  </c:choose>
  
  <c:choose>
    <c:when test="${boards.last }">
  	<li class="page-item disabled"><a class="page-link" href="?page=${boards.number+1 }">Next</a></li>
    </c:when>
    <c:otherwise>
  <li class="page-item"><a class="page-link" href="?page=${boards.number+1 }">Next</a></li>
    </c:otherwise>
  </c:choose>
</ul>
</div>


<%@ include file="layout/footer.jsp"%>