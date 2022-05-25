<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<%
	// 방법1 : 회원 인증 정보 
	session.removeAttribute("id");
	session.removeAttribute("name");

	// 방법 2 : 모든속성 한꺼번에 삭제
	session.invalidate();
	
	// 세션 정보 모두 삭제 이후 페이지 이동
	response.sendRedirect("LoginForm.jsp");

%>

</body>
</html>