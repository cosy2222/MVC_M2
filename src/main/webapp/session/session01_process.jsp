<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>session ��ü�� ������ ���� �Ҵ� </title>
</head>
<body>

<%	// client ������ ID , password
	String id = request.getParameter("id");
    String pwd = request.getParameter("pwd");
    
    //DB���� ������ �� 
    if(id.equals("admin") && pwd.equals("1234")){
    	// ���ǿ� ���Ǻ����� ���� �Ҵ�
    	session.setAttribute("id", id);
    	session.setAttribute("pwd", pwd);
    	
    	out.println("���Ǽ����� ���� �߽��ϴ�");
    	out.println(id + "�� ����");
    	
    }else{
    	out.println("���������� ���� �߽��ϴ�");
    }





%>


</body>
</html>