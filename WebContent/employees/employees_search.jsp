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

String optionDepartmentBeanList =(String) session.getAttribute("strDepartmentBeanList");
String optionPositionBeanList = (String) session.getAttribute("strPositionBeanList");
String employeesTableList = (String)session.getAttribute("strEmployeesBeanList");
String seniorityFirst = (String) session.getAttribute("seniorityFirst");
String senioritySecond = (String)session.getAttribute("senioritySecond");



String deptCheked=(String)session.getAttribute("deptCheck");
String positionCheked=(String)session.getAttribute("positionCheck");
String seniorityCheked=(String)session.getAttribute("seniorityCheck");
String pageNum = (String)session.getAttribute("page");
if(pageNum==null){
	pageNum="0";
}
SessionKanri.sessionRemoveAll(request);
%>

 <form id="search_form" action="EmployeesSearchServlet" method="POST">
 	<input type="checkbox" name="searchConditions" value="dept" <%=NullCheck.nullConvert(deptCheked) %>>
 	部署
 	<select name="dept">
 		<option value=0>---選択してください---</option>
 		<%= NullCheck.nullConvert(optionDepartmentBeanList) %>
 	</select>
 	<br>
 	<input type="checkbox" name="searchConditions" value="position" <%=NullCheck.nullConvert(positionCheked) %>>
 	役職
 	<select name="position">
 		<option value=0>---選択してください---</option>
 		<%= NullCheck.nullConvert(optionPositionBeanList) %>
 	</select>
 	<br>
 	<input type="checkbox" name="searchConditions" value="seniority" <%=NullCheck.nullConvert(seniorityCheked) %>>
 	勤務年数
 	<input type="number" name="seniorityFirst" value="<%=NullCheck.nullConvert(seniorityFirst) %>">年目～<input type="number" name="senioritySecond" value="<%=NullCheck.nullConvert(senioritySecond) %>">年目
 	<br>
 	<input type="hidden" name="page" id="page" value="<%=pageNum %>">
	<Button type="button" id="search">検索</Button>



 <table border="1">
 	<tr>
 		<th>名前</th>
 		<th>かな</th>
 		<th>部署名</th>
 		<th>課名</th>
 		<th>役職名</th>
 		<th>性別</th>
 		<th>生年月日</th>
 		<th>郵便番号</th>
 		<th>住所</th>
 		<th>電話番号</th>
 		<th>入社日</th>
 		<th>退社日</th>
 		<th>変更</th>
 	</tr>
 	<%=NullCheck.nullConvert(employeesTableList) %>
 </table>


<Button type="button" id="prev">前の10件</Button><Button type="button" id="next">次の10件</Button>
</form>


<script type="text/javascript" src="js/app.js"></script>
</body>
</html>