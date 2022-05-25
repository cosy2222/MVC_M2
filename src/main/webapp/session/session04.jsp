<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<h2> session 변수의 특정 속성(필드)의 값을 삭제 ( removeAttribute() )</h2>
	
<h4> ===== 세션 정보를 삭제하기 전 =====</h4>
<%
	String id = (String)session.getAttribute("id");
	String pwd = (String)session.getAttribute("pwd");
	
	out.println("설정된 세션 이름 id :" + id);
	out.println("<p> 설정된 세션 이름 pwd :" + pwd);
	
	//session 객체에 저장된 특정 속성(필드)를 제거 (하나만 제거)
	// 장바구니에 넣은 값을 session에 저장후 특정 값을 삭제할떄 
	session.removeAttribute("id");

%>

<h4> ===== 세션 정보를 삭제후 =====</h4>
<%
	id = (String)session.getAttribute("id");
	pwd = (String)session.getAttribute("pwd");
	
	out.println("설정된 세션 이름 id :" + id);
	out.println("<p> 설정된 세션 이름 pwd :" + pwd);
	
%>


</body>
</html>