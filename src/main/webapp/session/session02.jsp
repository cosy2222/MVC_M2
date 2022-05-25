<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<h2> session 객체의 변수에 할당된 값 가져오기</h2>
<% 
	// Session 객체의 값을 가져올떄 Object 타입이므로 다운 캐스팅이 필요함
	
		// 모든 페이지에서 세션의 변수에 들어간 값을 가져와서 null일 경우 : 로그인이 안된 상태 >
		// getAttribute <== 세션객체에 담긴 하나의 변수의 값을 가져올떄
	String id = (String) session.getAttribute("id");
	String pwd = (String) session.getAttribute("pwd");
	
	out.println("<p> 설정된 세션의 속성값 1 :" + id);
	out.println("<p> 설정된 세션의 속성값 2 :" + pwd);

%>
</body>
</html>