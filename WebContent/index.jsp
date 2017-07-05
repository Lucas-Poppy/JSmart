<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@ page import="session.SessionKanri"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
request.setCharacterEncoding("UTF-8");
//セッションをRemoveする
SessionKanri.sessionRemoveAll(request);

%>
	<a href="PositionInsertTopServlet">役職登録・変更</a><br>
	<a href="DepartmentInsertTopServlet">部署・課 登録</a><br>
	<a href="employees/employees_top.jsp">社員検索</a>
</body>
</html>