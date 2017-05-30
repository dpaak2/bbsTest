<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="context" value="<%=application.getContextPath() %>"/>
<!doctype html>
<html lang="en">
<head>
<title>작성 페이지</title>
<jsp:include page="header.jsp"/></head>
<body>
<div class="container-fluid" style="width:80%">
<jsp:include page="navbar.jsp"/>
<h1>ARTICLE WRITING</h1>
	<form action="${context}/board.do">
<div style="width: 100%;"><h1>작성 페이지</h1></div>
	<input type="hidden" name="seqNo" value=""/>
	<input type="text" name="writer" style="margin-top:20px"  class="form-control" placeholder="작성자 이름" />
	<input type="text" name="title" style="margin-top:20px" class="form-control"  placeholder="제목" /><br />
	  <div class="row">
		    <div style="width:97%; margin:10px auto" >
	      		<textarea name="content" class="form-control" rows="20" id="comment"></textarea>
		    </div>
	    </div>
	  <input type="reset" class="btn btn-danger" style="float:right;width:100px;margin-right:10px" value="CANCEL"/>
	<input name="write" type="submit" class="btn btn-primary" style="float:right;width:100px;margin-right:10px" value="SUBMIT"/>
	<input type="hidden" name="action" value="write"/>
	<input type="hidden" name="pageName" value="detail" />
	
	 
</form>
</div>

</body>
<script>
$(function(){})
</script>
</html>