<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>

<%   // 쿠키 삭제 : cookie.setMaxAge(0);

	Cookie[] cookies = request.getCookies();
	
	for(int i = 0 ; i <cookies.length ; i++){
		cookies[i].setPath(request.getContextPath());
		cookies[i].setMaxAge(0);  // 모든 쿠키의 정보를 삭제
		
		// 클라이언트에 전송 : response.addCookie(cookies[i]);
		response.addCookie(cookies[i]);
	}
	
	
	//페이지 이동
	response.sendRedirect("cookie02.jsp");


%>

</body>
</html>