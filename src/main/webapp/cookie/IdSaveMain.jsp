<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import ="utils.CookieManager" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>cookie를 사용해서 아이디 저장하기</title>
</head>
<body>
	
<%
	String loginId = CookieManager.readCookie(request, "loginId");
	String cookiCheck = "";
	
	if(!loginId.equals("")){
		cookiCheck = "checked";
	}


%>


	<h2>로그인 페이지</h2>
	
	<form action = "IdSaveProcess.jsp" method = "post">
		<p> 아이디 : <input type="text" name = "id" value = "<%= loginId %>">
			아이디 저장<input type = "checkbox"  name = "save_check" value = "Y" <%= cookiCheck %> >
		<p> 비밀번호 : <input type = "password" name = "pwd">
		
		<p> <input type = "submit" value = "로그인">
	
	</form>

</body>
</html>