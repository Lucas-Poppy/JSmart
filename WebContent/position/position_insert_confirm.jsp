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
	String confirm = (String)request.getAttribute("confirm");

	%>
	<h2>確認画面</h2>
	<div>
		以下の内容で登録しますがよろしいですか？<br>
		<%= confirm %>
	</div>
	<form action="PositionInsertTopServlet" method="POST">
		<input type="submit" value="戻る">
	</form>
	<form action="PositionInsertResultServlet" method="POST">
		<input type="submit" value="登録する">
	</form>

</body>
</html>