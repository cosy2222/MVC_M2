<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>


<%
	String chkval = request.getParameter("inactiveToday");
	
	if (chkval != null && chkval.equals("1")){
		Cookie cookie = new Cookie("PopupClose" , "off");   //��Ű ���� 
		cookie.setPath(request.getContextPath());  // ��� ���� (/MVC-M2)
		cookie.setMaxAge(60 * 60 *24);    //��Ű ����Ⱓ : �Ϸ�
		response.addCookie(cookie);		// client HDD�� ����
		out.println("��Ű : �Ϸ絿�� ������ ����");
	}

%>