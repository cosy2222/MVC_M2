<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>

<%
	String id = request.getParameter("id");
	String pwd = request.getParameter("pwd");

	if(id.equals("admin") && pwd.equals("1234")){
		// ��Ű ����  , Ŭ���̾�Ʈ HDD�� ��Ű�� ���� ����
		
		// ��Ű�� �����ڿ��� ���� �̸��� ���� �Ҵ�
		Cookie cookie_id = new Cookie("userid" , id);
		Cookie cookie_pwd = new Cookie("userpwd" , pwd);
		
		//��Ű ����
		cookie_id.setPath(request.getContextPath());  //��Ű�� ����� ������ ��� ����
		cookie_id.setMaxAge(60 * 60);   // 3600�� == 1�ð�
		
		cookie_pwd.setPath(request.getContextPath());
		cookie_pwd.setMaxAge(60 * 60);
		
		//��Ű�� response��ü�� ����ؼ� ������ ��Ű�� Client HDD�� ���� 
		response.addCookie(cookie_id);
		response.addCookie(cookie_pwd);
		
		out.println("��Ű ������ ���� �߽��ϴ�");
		out.println(id + "�� ����");
	}else{
		out.println("��Ű ���� ����");
	}

%>

</body>
</html>