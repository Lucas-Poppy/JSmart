<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        <%@ page import="session.SessionKanri"%>
<%@ page import="benefit.NullCheck"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
request.setCharacterEncoding("UTF-8");
String oldPosition = (String) session.getAttribute("oldPosition");
String newPosition = (String) request.getAttribute("result");
SessionKanri.sessionRemoveAll(request);

%>
	<a href="index.jsp">TOPに戻る</a>
<h2>完了画面</h2>
	<div>
		<%= oldPosition %>
		<p>変更後↓</p>
		<%= newPosition %>
	</div>
	<form action="PositionInsertTopServlet" method="POST">
		<input type="submit" value="登録画面に戻る">
	</form>
</body>
</html>