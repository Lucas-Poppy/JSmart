<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="session.SessionKanri"%>
<%@ page import="benefit.NullCheck"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="js/jquery-3.1.1.min.js"></script>
<title>Insert title here</title>
</head>
<body>
<%
request.setCharacterEncoding("UTF-8");
String strPositionBeanList = (String) session.getAttribute("strPositionBeanList");
String strPositionLankList =(String) session.getAttribute("strPositionLankList");
String textBoxPositionName =(String) session.getAttribute("textBoxPositionName");
String textBoxPositionAllowance =(String) session.getAttribute("textBoxPositionAllowance");


String errorMessage1 =(String) session.getAttribute("errorInsertPositionName10Word");
String errorMessage2 =(String) session.getAttribute("errorInsertPositionNameExsists");
String errorMessage3 =(String) session.getAttribute("errorInsertPositionEmpty");
//セッションをRemoveする
SessionKanri.sessionRemoveAll(request);


%>
	<a href="index.jsp">TOPに戻る</a>


	<form action="PositionInsertConfirmServlet" method="POST">
		<h2>役職を登録する</h2>
		<div class="errorMessage"><%=NullCheck.nullConvert(errorMessage1) %></div>
		<div class="errorMessage"><%=NullCheck.nullConvert(errorMessage2) %></div>
		<div class="errorMessage"><%=NullCheck.nullConvert(errorMessage3) %></div>
			役職名<input type="text" name="positionName" value="<%=NullCheck.nullConvert(textBoxPositionName)%>"><br>
			役職ランク<select name="positionLank">
			<%=NullCheck.nullConvert(strPositionLankList)%>
						</select>※上から何番目かを選択<br>
			役職手当<input type="number" name="positionAllowance" value="<%=NullCheck.nullConvert(textBoxPositionAllowance)%>">円<br>
			<input type="submit" value="登録">
	</form>
	<table border="1">
		<thead>
		<tr>
			<th>役職名</th>
			<th>役職ランク</th>
			<th>役職手当</th>
			<th>ランク入れ替え</th>
			<th>変更</th>
		</tr>
		</thead>
		<tbody id="positionList">
		<%=strPositionBeanList %>
		</tbody>
	</table>







    <script type="text/javascript" src="js/app.js"></script>

</body>
</html>