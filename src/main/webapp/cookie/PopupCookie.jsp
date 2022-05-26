<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>


<%
	String chkval = request.getParameter("inactiveToday");
	
	if (chkval != null && chkval.equals("1")){
		Cookie cookie = new Cookie("PopupClose" , "off");   //쿠키 생성 
		cookie.setPath(request.getContextPath());  // 경로 설정 (/MVC-M2)
		cookie.setMaxAge(60 * 60 *24);    //쿠키 저장기간 : 하루
		response.addCookie(cookie);		// client HDD에 저장
		out.println("쿠키 : 하루동안 열리지 않음");
	}

%>