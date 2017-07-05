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
	<script type="text/javascript">
	function enter(){
		if(window.event.keyCode==13){
			var licenseLank=$('#licenseLankInput').val();
			var licenseId=$('#licenseLankInput').parent().data('id');
			console.log("資格ランク"+licenseLank);
			console.log("資格ID"+licenseId);
			$.ajax({
		    	url: 'LicenseUpdateServlet',
		    	type: 'POST',
		    	dataType: 'json',
		    	data: {
		    			licenseId:licenseId,
		    			licenseLank:licenseLank,
		    			method:'UP',
		    			},
		    })
		    .done(function() {

		    })
		    .fail(function() {
		    	console.log("error");
		    })
		    .always(function(result) {
		    	console.log("complete");
		    	document.getElementById("licenseList").innerHTML=result;
		    	$("#licenseLankInput").focus()
		    });
		}
	}

	$("#licenseList").on("blur","#licenseLankInput",function(){
		var licenseLank=$('#licenseLankInput').val();
		var licenseId=$('#licenseLankInput').parent().data('id');
		console.log("資格ランク"+licenseLank);
		console.log("資格ID"+licenseId);
		$.ajax({
	    	url: 'LicenseUpdateServlet',
	    	type: 'POST',
	    	dataType: 'json',
	    	data: {
	    			licenseId:licenseId,
	    			licenseLank:licenseLank,
	    			method:'UP',
	    			},
	    })
	    .done(function() {

	    })
	    .fail(function() {
	    	console.log("error");
	    })
	    .always(function(result) {
	    	console.log("complete");
	    	document.getElementById("licenseList").innerHTML=result;
	    	$("#licenseLankInput").focus()
	    });
	});

	</script>
    <script type="text/javascript" src="js/license.js"></script>
</html>