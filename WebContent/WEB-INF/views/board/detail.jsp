<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<c:set var="context" value="<%=application.getContextPath() %>"/>
<!doctype html>
<html lang="en">
<head>
	<meta charset="UTF-8" />
	<title>디테일</title>
	<script src="${js}/jquery-3.1.1.js"></script>
	<style type="text/css">
	.wrapper{width:100%}
	.hanbit-write{
	margin: 0 auto;
	width: 23.5%;
	}
	.hanbit-title{
	 margin-top:20px;
	 width: 36%;
	 margin-bottom: 10px;
	}
	
		.hanbit-btn {
    border: none;
    color: white;
    padding: 5px 50px;
    font-size: 16px;
    cursor: pointer;}
       
    .submit{
    float: right;	
    background-color: #4CAF50;
    }
	
	.hanbit-btn {
    border: none;
    color: white;
      padding: 5px 40px;
    font-size: 16px;
    cursor: pointer;}
       
	.submit {background-color: #4CAF50;}
    .submit :hover {background-color: #46a049;}
    .delete {background-color: #fcac0c;} /* Blue */
    .delete:hover {background: #e09f1d;}
	</style>
</head>
<body>
<form action="${context}/board.do">
<div class="wrapper">
<div style="position: relative;left: 37.5%;"><h1>디테일 페이지</h1></div>
	<div class="hanbit-write">
	<input class="hanbit-title" type="text" value="${requestScope.writer}"/>
	<input class="hanbit-title" type="text" value="${requestScope.regiDate}" /><br />
	<input class="hanbit-title" type="text" value="${requestScope.title}" /><br />
	<textarea rows="10" cols="50">${requestScope.content}</textarea><br />
	<a href="${context}/board.do?action=delete&pageName=main"><button class="hanbit-btn delete">삭제</button><input name="deleteObject" placeholder="지울 seq_no" type="text" /></a>
	<a href="${context}/board.do?action=move&pageName=update"><input class="hanbit-btn submit" type="button" value="수정하기"/></a>
</div>
</div>
</form>
</body>
<script>
</script>
</html>