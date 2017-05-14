<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
	request.setCharacterEncoding("UTF-8");
	String confirm = (String)request.getAttribute("confirm");

	%>
	<h2>確認画面</h2>
	<div>
		以下の内容で登録しますがよろしいですか？<br>
		<%= confirm %>
	</div>
	<form action="DepartmentInsertTopServlet" method="POST">
		<input type="submit" value="戻る">
	</form>
	<form action="DepartmentInsertResultServlet" method="POST">
		<input type="submit" value="登録する">
	</form>


</body>
</html>