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

		//セッションをRemoveする
		SessionKanri.sessionRemoveAll(request);


	%>
	<form action="InsertKakuninServlet">
		<h2>新規部署を登録する</h2>
		<p>
			<input type="text" name="deptName" value="">部
			<input type="hidden" name="submitMethod" value="dept">
			<input type="submit" value="登録">
		</p>
	</form>

	<form action="InsertKakuninServlet" method="POST">
		<h2>新規課を登録する</h2>
		<p>
			<select>
				<option value="0">---------</option>
				<%= NullCheck.nullConvert(optionDepartmentBeanList) %>
			</select>部
			<input type="text" name="sectionName" value="">課
			<input type="hidden" name="submitMethod" value="section">
			<input type="submit" value="登録">
		</p>
	</form>

	<table>
		<tr>
			<th>部署名</th>
			<th>課名</th>
		</tr>
		<%= NullCheck.nullConvert(strDepartmentSectionBeanList) %>
	</table>



</body>
</html>