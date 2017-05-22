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
	<title>update-수정 페이지</title>
	<style type="text/css">
	.wrapper{
	  width: 100%;
	}
	.hanbit-write{
	margin: 0 auto;
	width: 23.5%;
	}
	.hanbit-title{
	margin-bottom: 10px;
	width: 99%;
	}
	.hanbit-btn {
    border: none;
    color: white;
    padding: 5px 40px;
    font-size: 16px;
    cursor: pointer;}
       
	.submit {background-color: #4CAF50;}
    .submit:hover {background-color: #46a049;}
    .cancle {background-color: #f44336;;} /* Blue */
    .cancle:hover {background: #da190b;}
    .cancleBtn{
    float: left;}
    .submitBtn{
    float: right;}
	</style>
</head>
<body>
<form action="${context}/board.do">
<div style="position: relative;left: 37.5%;"><h1>수정 페이지</h1></div>
<div class="wrapper">
<div class="hanbit-write">
    <input class="hanbit-title" type="text" name="title" placeholder="제목" /><br />
	<textarea rows="10" cols="50" name="content"  placeholder="작성글을 입력 하여 주세요"></textarea><br />
	<span class="cancleBtn"><a href="${context}/board.do?action=move&pageName=detail"><button class="hanbit-btn cancle">취소</button></a></span>
	<span class="submitBtn"><a href="${context}/board.do?action=update&pageName=detail">
	<input class="hanbit-btn submit" name="update" type="submit" value="수정 완료" /></a>
	<input type="hidden" name="action" value="update" />
	<input type="hidden" name="page" value="detail" />
</span>
	
</div>
</div>

</form>

</body>
</html>