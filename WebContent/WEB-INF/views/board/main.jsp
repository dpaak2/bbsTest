<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<c:set var="context" value="<%=application.getContextPath() %>"/>
<!doctype html>
<html lang="en">
<head>
	<title>Board</title>
		<meta charset="UTF-8" />
	 	<meta name="viewport" content="width=device-width, initial-scale=1">
  		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
  		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
<form action="${context}/board.do">
<div class="container">
	<div style="width:90%;margin:20px auto;">
		<select id="searchOption" class="form-control" name="searchOption" style="width:20%;float:left;margin-right:36px">
			<option value="searchByName" >작성자</option>
			<option value="searchByTitle">제목</option>
		</select>
		<div class="input-group" style="width:60%;float:left;margin-right:30px">
		    <span class="input-group-addon">SEARCH</span>
		    <input id="msg" type="text" class="form-control" style="width:100%" name="searchWord" placeholder="작성자 나 제목을  검색하여 주세요" >
		</div>
		<input class="btn btn-primary" style="width:100px" name="search" type="submit"  value="SEARCH"/>
		<input type="hidden" name="action" value="search"/>
		<input type="hidden" name="pageName" value="main" />
		<input type="hidden" name="pageNumber" value="1" />
		</div>
		</div>
	</form>	
		<div style="margin:20px 0" >
			<span> 총게시글수 ${requestScope.count}</span>
			<a href="${context}/board.do?action=write&pageName=write"><input class="btn btn-danger" style="float:right;width:100px" type="button" name="write" value="글쓰기"/></a>
		</div>

	 <table class="table table-hover" style="width:90%;margin:0 auto;">
		<tr class="hanbit-table tr">
			<td >NO</td>
			<td >제 목</td>
			<td >내 용</td>
			<td>작성자</td>
			<td>등록일</td>
			<td>조회수</td>
		</tr>
	<c:forEach var="article" items="${requestScope.list}">
		<tr>
			<td>${article.seqNo}</td>
			<td>${article.title}</td>
			<td>${article.content}</td>
			<td>${article.writer}</td>
			<td>${article.regiDate}</td>
			<td>${article.hitCount}</td>
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

<span>pagesPerOneBlock: ${requestScope.pagesPerOneBlock}</span><br />
<span>rowsPerOnePage: ${requestScope.rowsPerOnePage}</span><br />
<span>theNumberOfRows: ${requestScope.theNumberOfRows}</span><br />
<span>theNumberOfPages: ${requestScope.theNumberOfPages}</span><br />
<span>pageNumber: ${requestScope.pageNumber}</span><br />
<span>startPage: ${requestScope.startPage}</span><br />
<span>endPage: ${requestScope.endPage}</span><br />
<span>startRow: ${requestScope.startRow}</span><br />
<span>endRow: ${requestScope.endRow}</span><br />
<span>prevBlock: ${requestScope.prevBlock}</span><br />




</body>
<script>
var optoinSelected=$('#searchOption option:selected').val();

 $("#searchOption").val();
$(document).ready(function(){
	
}); 
</script>
</html>