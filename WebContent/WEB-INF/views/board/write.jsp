<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<c:set var="context" value="<%=application.getContextPath() %>"/>
<!doctype html>
<html lang="en">
<head>
	<meta charset="UTF-8" />
	<title>글쓰기</title>
	<script src="${js}/jquery-3.1.1.js"></script>
	<style type="text/css">
	.wrapper{width:100%}
	.hanbit-write{
	margin: 20 auto;
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
    padding: 10px 18px;
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
    .cancle {background-color: #f44336;;} /* Blue */
    .cancle:hover {background: #0b7dda;}
    
	</style>
</head>
<body>
<div class="wrapper">
<form action="${context}/board.do">
<div style="position: relative;left: 37.5%;"><h1>작성 페이지</h1></div>
	<div class="hanbit-write">
	<input class="hanbit-title" type="text" name="writer" placeholder="작성자 이름" />
	<input class="hanbit-title" type="text" name="regiDate" placeholder="작성날짜" /><br />
	<input class="hanbit-title" type="text" name="title"  placeholder="제목" /><br />
	<textarea rows="10" cols="50" name="content" placeholder="작성글을 입력 하여 주세요"></textarea><br />
	<a href="${context}/board.do?action=move&pageName=main"><button class="hanbit-btn cancle">취소</button></a>
	<a href="${context}/board.do?action=write&pageName=detail"><input class="hanbit-btn submit" name="write" type="submit" value="작성 완료"/></a>
	<input type="hidden" name="action" value="write" />
	<input type="hidden" name="pageName" value="detail"/>
</div>
</form>
</div>

</body>
</html>