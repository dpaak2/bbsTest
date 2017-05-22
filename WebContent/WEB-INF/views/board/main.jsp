<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<c:set var="context" value="<%=application.getContextPath() %>"/>
<!doctype html>
<html lang="en">
<head>
	<meta charset="UTF-8" />
	<script src="${js}/jquery-3.1.1.js"></script>
	<title>Board</title>
	<style type="text/css">
	.hanbit-searh{
	position: relative;
	left: 30%;
	padding: 30px;
	
	}
	.hanbit-btn {
    border: none;
    color: white;
    padding: 5px 5px;
    font-size: 16px;
    cursor: pointer;}
    .write{background-color: #2196F3;} /* Blue */
    .write:hover {background: #0b7dda;}
	.hanbit-table{
	border-collapse: collapse;
	border:1px solid black;
	font-size:13px;
	position:relative;
	left:30%;
	height:40%;
	width:45%
	}
	.hanbit-table tr{
    border: 1px solid black;
}
	.hanbit-table td{
    border: 1px solid black;
}
.hanbit-pagination{
position:absolute;
top:50%;
left:40%;
display: inline-block;
}
.hanbit-pagination a{
    color: black;
    float: left;
    padding: 8px 16px;
    text-decoration: none;
}
.hanbit-pagination a.active {
    background-color: #4CAF50;
    color: white;
}
.hanbit-pagination a:hover:not(.active) {background-color: #d14747;}

	.totalNwrite{
	position: relative;
	left: 64%;
	margin-bottom: 20px;
	}
	</style>

</head>
<body>
<div class="hanbit-searh">
<select style="height: 22px;">
<option value="writer">작성자</option>
<option value="title">제목</option>
</select>
<input type="text" placeholder="검색"/> <button>search</button>
</div>
<div>
<div class="totalNwrite">
<span> 총게시글수 ${requestScope.count}</span>
<span><a href="${context}/board.do?action=move&pageName=write"><button class="hanbit-btn write">글쓰기</button></a></span>
</div>

<table class="hanbit-table">
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
</div>
<div id="pagination" class="hanbit-pagination">
<c:if test="${requestScope.prevBlock gt 0}">
  <a href="${context}/board.do?action=list&pageName=main&pageNumber=${requestScope.prevBlock}">&laquo;</a>

  <a href="#">&raquo;</a>
  </c:if>
  <c:forEach varStatus="i" begin="${requestScope.blockStart}" end="${requestScope.endPage}" step="1">
    <a href="#">
    <c:choose>
    <c:when test="${i.index eq pageNumber}">
        <a href="#">${i.index}</a>
    </c:when>
    <c:otherwise>
    	<a href="${context}/board.do?action=list&pageName=main&pageNumber=${i.index}"></a>
    </c:otherwise>
    </c:choose></a>

  </c:forEach>
</div>
</body>

</html>
