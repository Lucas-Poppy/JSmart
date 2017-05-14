                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                              <%@ page
	language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="session.SessionKanri"%>
<%@ page import="benefit.NullCheck"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		request.setCharacterEncoding("UTF-8");

		String strDepartmentSectionBeanList = (String) session.getAttribute("strDepartmentSectionBeanList");
		String optionDepartmentBeanList =(String) session.getAttribute("strDepartmentBeanList");

		String errorMessage1 =(String) session.getAttribute("errorInsertDeptName10Word");
		String errorMessage2 =(String) session.getAttribute("errorInsertSectionName10Word");
		String errorMessage3 =(String) session.getAttribute("errorInsertDepartmentSelected");
		String errorMessage4 =(String) session.getAttribute("errorInsertDepartmentExsists");
		String errorMessage5 =(String) session.getAttribute("errorInsertSectionExsists");
		//セッションをRemoveする
		SessionKanri.sessionRemoveAll(request);


	%>
	<form action="DepartmentInsertConfirmServlet" method="POST">
		<h2>新規部署を登録する</h2>
		<div class="errorMessage"><%= NullCheck.nullConvert(errorMessage1) %></div>
		<div class="errorMessage"><%= NullCheck.nullConvert(errorMessage4) %></div>

		<p>
			<input type="text" name="deptName" value="">部
			<input type="hidden" name="submitMethod" value="dept">
			<input type="submit" value="登録">
		</p>
	</form>

	<form action="DepartmentInsertConfirmServlet" method="POST">
		<h2>新規課を登録する</h2>
		<div class="errorMessage"><%=NullCheck.nullConvert(errorMessage2)%></div>
		<div class="errorMessage"><%=NullCheck.nullConvert(errorMessage3)%></div>
		<div class="errorMessage"><%=NullCheck.nullConvert(errorMessage5)%></div>
		<p>
			<select name="deptId">
				<option value="0,選択されていません">---------</option>
				<%= NullCheck.nullConvert(optionDepartmentBeanList) %>
			</select>部
			<input type="text" name="sectionName" value="">課
			<input type="hidden" name="submitMethod" value="section">
			<input type="submit" value="登録">
		</p>
	</form>

	<table border="1">
		<tr>
			<th>部署名</th>
			<th>課名</th>
		</tr>
		<%= NullCheck.nullConvert(strDepartmentSectionBeanList) %>
	</table>



</body>
</html>