<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글 삭제</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css">
</head>
<body>
<div class="page-main">
	<h2>글 삭제</h2>
	<form:form action="delete.do" modelAttribute="boardVO">		<%-- modelAttribute로 유효성체크 --%>
	<form:hidden path="num" /> 									<%-- boardVO에 담긴 num값을 받아옴 --%>
	<ul>
		<li>
			<form:label path="passwd">비밀번호</form:label>
			<form:password path="passwd" />
			<form:errors path="passwd" cssClass="error-color" />
		</li>
	</ul>
	<div class="align-center">
		<input type="submit" value="삭제">
		<input type="button" value="홈으로" onclick="location.href='list.do'">
	</div>
	</form:form>
</div>
</body>
</html>