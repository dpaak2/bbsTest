<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<c:set var="context" value="<%=application.getContextPath() %>"/>
<!doctype html>
<html lang="en">
<head>
	<title>Search Result</title>
		<meta charset="UTF-8" />
	 	<meta name="viewport" content="width=device-width, initial-scale=1">
  		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
  		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">
	<div style="width:90%;margin:20px auto;">
		<select class="form-control"  style="width:20%;float:left;margin-right:36px">
			<option value="writer">작성자</option>
			<option value="title">제목</option>
		</select>
		<div class="input-group" style="width:60%;float:left;margin-right:30px">
		    <span class="input-group-addon">SEARCH</span>
		    <input id="msg" type="text" class="form-control" style="width:100%" name="msg" placeholder="Additional Info" >
		</div>
		<a href="${context}/board.do?action=search&pageName=result"><button type="button" class="btn btn-primary" style="width:100px">SEARCH</button></a>
		<div style="margin:20px 0" >
			<span> 총게시글수 ${requestScope.count}</span>
			<a href="${context}/board.do?action=write&pageName=write"><button class="btn btn-danger" style="float:right;width:100px">글쓰기</button></a>
		</div>
	</div>
	<%-- <a href="${context}/board.do?action=move&pageName=write"> --%>
	 <table class="table table-hover" style="width:90%;margin:0 auto;">
		<tr class="hanbit-table tr">
			<td >NO</td>
			<td >제 목</td>
			<td >내 용</td>
			<td>작성자</td>
			<td>등록일</td>
			<td>조회수</td>
		</tr>
		
	<c:forEach var="searchList" items="${requestScope.searchList}">
	
		<tr>
			<td>${searchList.seqNo}</td>   
			<td>${searchList.title}</td>
			<td>${searchList.content}</td>
			<td>${searchList.writer}</td>
			<td>${searchList.regiDate}</td>
			<td>${searchList.hitCounts}</td>
		</tr>
	</c:forEach>
	</table>
	<nav style="width:30%;margin:0 auto">
		<ul class="pagination">
		<c:if test="${requestScope.prevBlock  gt 0}">
		<li>
			<a href="${context}/board.do?action=list&pageName=main&pageNumber=${requestScope.prevBlock}">&laquo;</a>
		</li>
		</c:if>
		<c:forEach varStatus="i" begin="${requestScope.startPage}" end="${requestScope.endPage}"
			 step="1" >
			<li>
			<c:choose>
				<c:when test="${i.index eq pageNumber}">
					<a href="#"><font style="color:red">${i.index}</font></a>
				</c:when>
				<c:otherwise>
					<a href="${context}/board.do?action=list&pageName=main&pageNumber=${i.index}">${i.index}</a>
				</c:otherwise>
			</c:choose>
			</li>
		</c:forEach>
		<c:if test="${requestScope.nextBlock  le theNumberOfPages}">
		<li>
			<a href="${context}/board.do?action=list&pageName=main&pageNumber=${requestScope.nextBlock}">&raquo;</a>
		</li>
		</c:if>
		</ul>
	</nav> 
</div>


</body>
<!-- <script>
var pagination= "";
</script> -->
</html>