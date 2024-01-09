<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글수정</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css">
</head>
<body>
<div class="page-main">
	<h2>글수정</h2>
	<form:form action="update.do" modelAttribute="boardVO">
		<form:hidden path="num" />
		<ul>
			<li>
				<form:label path="writer">작성자</form:label>
				<form:input path="writer"/>
				<form:errors path="writer" cssClass="error-color"/>
			</li>
			<li>
				<form:label path="title">제목</form:label>
				<form:input path="title"/>
				<form:errors path="title" cssClass="error-color"/>
			</li>
			<li>
				<form:label path="passwd">비밀번호</form:label>
				<form:password path="passwd"/>
				<form:errors path="passwd" cssClass="error-color"/>
			</li>
			<li>
				<form:label path="content">내용</form:label>
				<form:textarea path="content"/>
				<form:errors path="content" cssClass="error-color"/>
			</li>
		</ul>
		<div class="align-center">
			<input type="submit" value="수정">
			<input type="button" value="홈으로" onclick="location.href='list.do'">
		</div>
	</form:form>
</div>
</body>
</html>