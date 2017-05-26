<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="context" value="<%=application.getContextPath()%>" />
<!doctype html>
<html lang="en">
<head>
<title>수정페이지</title>
<jsp:include page="header.jsp" /></head>
<body>
	<div class="container-fluid" style="width: 80%">
		<jsp:include page="navbar.jsp" />
		<h1>ARTICLE UPDATE</h1>
		<form action="${context}/board.do">
			<input class="hanbit-title" type="text" name="title" placeholder="제목" /><br />
		 <div class="row">
		    <div style="width:97%; margin:10px auto" >
	      		<textarea class="form-control" rows="20" id="comment"></textarea>
		    </div>
	    </div>
	    
	    
	   	<a href="${context}/board.do?action=move&pageName=detail"><input type="submit" class="btn btn-danger" style="float:right;width:100px;margin-right:10px" value="CANCEL"/></a> 
		<a href="${context}/board.do?action=update&pageName=detail"><input type="submit" class="btn btn-primary" style="float:right;width:100px;margin-right:10px" value="SUBMIT"/></a>						
		
		</form>

	</div>

</body>
</html>