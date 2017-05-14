<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
	request.setCharacterEncoding("UTF-8");
	String result = (String)session.getAttribute("deptInsertResult");

	%>
	<h2>完了画面</h2>
	<div>
		<%= result %>
	</div>
	<form action="DepartmentInsertTopServlet" method="POST">
		<input type="submit" value="登録画面に戻る">
	</form>


</body>
</html>