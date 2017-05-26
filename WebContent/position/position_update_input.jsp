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
		String name = (String) session.getAttribute("updateOldName");
		String inputName =(String) request.getAttribute("inputName");
		String allowance = (String) session.getAttribute("updateOldAllowance");
		String inputAllowance = (String)request.getAttribute("inputAllowance");

		String errorMessage1 =(String) session.getAttribute("errorInsertPositionName10Word");
		String errorMessage2 =(String) session.getAttribute("errorInsertPositionNameExsists");
		String errorMessage3 =(String) session.getAttribute("errorInsertPositionEmpty");

		String positionName = NullCheck.nullConvert(name);
		String positionAllowance = NullCheck.nullConvert(allowance);
		if(inputName!=null){
			positionName = inputName;
		}
		if(inputAllowance!=null){
			positionAllowance=inputAllowance;
		}

		//エラーメッセージのセッションをRemoveする
		SessionKanri.sessionRemoveError(request);
	%>
		<a href="index.jsp">TOPに戻る</a>
<form action="PositionUpdateResultServlet" method="POST">

	<div class="errorMessage"><%=NullCheck.nullConvert(errorMessage1) %></div>
	<div class="errorMessage"><%=NullCheck.nullConvert(errorMessage2) %></div>
	<div class="errorMessage"><%=NullCheck.nullConvert(errorMessage3) %></div>
	役職名　<input type="text" name="positionName" value="<%=positionName%>" style="width:80px;"><br>
	役職手当<input type="text" name="positionAllowance" value="<%=positionAllowance%>" style="width:80px; ">円<br>
	<input type="submit" value="変更">
</form>

<form action="PositionInsertTopServlet" method="POST">
		<input type="submit" value="戻る">
</form>
</body>
</html>