<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="context" value="<%=application.getContextPath() %>"/>
<!doctype html>
<html lang="en">

<head><jsp:include page="header.jsp"/>
	<title>디테일 페이지</title>
</head>
<body>
<div class="container-fluid" style="width:80%">
<jsp:include page="navbar.jsp"/>
<h1>ARTICLE DETAIL</h1>
	
	<input type="text" name="writer" style="margin-top:20px" class="form-control" value="${requestScope.writer}" />
	<input type="text" name="title" style="margin-top:20px" class="form-control" value="${requestScope.title}"/><br />
	    <div class="row">
		    <div style="width:97%; margin:10px auto" >
	      		<textarea name="content" class="form-control" rows="20" id="comment">${requestScope.content}</textarea>
		    </div>
	    </div>
	    <form action="${context}/board.do">
	    	<input type="submit" name="deleteObject" class="btn btn-danger" style="float:right;width:100px;margin-right:10px" value="DELETE"/>
	    	<input type="hidden" name="action" value="delete" />
	    	<input type="hidden" name="pageName" value="list"/>
	    	<input type="hidden" name="seqNo" value="${seqNo}"/>
	    	</form>
		<a href="${context}/board.do?action=update&pageName=upload"><input type="button" class="btn btn-warning" style="float:right;width:100px;margin-right:10px" value="UPLOAD"/></a>
	<a href="${context}/board.do?action=update&pageName=update"><input type="button" class="btn btn-success" style="float:right;width:100px;margin-right:10px" value="UPDATE"/></a>
</div>

</body>

</html>
	    	