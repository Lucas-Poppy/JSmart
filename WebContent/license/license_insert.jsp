<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="session.SessionKanri"%>
<%@ page import="benefit.NullCheck"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<%
request.setCharacterEncoding("UTF-8");
String licenseList = (String) session.getAttribute("licenseList");
%>
<body>

	<form action="LicenseInsertServlet" method="POST">
		<h2>資格を登録する</h2>
			資格名　　<input type="text" name="licenseName" value="" style="width:80px;"><br>
			役職ランク<input type="number" name="licenseLank" value="" style="width:80px;">
			<input type="submit" value="登録">
	</form>
	<table border="1">
		<thead>
		<tr>
			<th>資格名</th>
			<th>資格ランク</th>
		</tr>
		</thead>
		<tbody id="licenseList">
		<%=NullCheck.nullConvert(licenseList) %>
		</tbody>
	</table>

</body>
</html>