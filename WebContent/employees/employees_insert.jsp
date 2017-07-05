<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="session.SessionKanri"%>
<%@ page import="benefit.NullCheck"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="js/jquery-3.1.1.min.js"></script>
<script src="js/jquery.autoKana.js" language="javascript" type="text/javascript"></script>

<title>Insert title here</title>
</head>

<%
request.setCharacterEncoding("UTF-8");

String optionDepartmentBeanList =(String) session.getAttribute("strDepartmentBeanList");
String optionPositionBeanList = (String) session.getAttribute("strPositionBeanList");
String optionLicenseBeanList = (String) session.getAttribute("strLicenseBeanList");

String insertInfo =(String) session.getAttribute("insertEmployee");
SessionKanri.sessionRemoveAll(request);
%>
<body>

 	<form action="EmployeesInsertServlet" method="POST">
 	<input type="hidden" id="mode" value="insert" >

 		姓<input type = "text" name ="seiKanzi" id="seiKanzi">名<input type="text" name="meiKanzi" id="meiKanzi"><br>
 		セイ<input type = "text" name ="seiKana" id="seiKana">メイ<input type="text" name="meiKana" id="meiKana"><br>

 		部署
 		<select name="dept" id="dept">
	 		<%= NullCheck.nullConvert(optionDepartmentBeanList) %>
 		</select><br>


 		課
 		<select name="section" id="section">
	 		<div id="sectionList">
	 		</div>
 		</select><br>

 		役職
 		<select name="position">
 			<%= NullCheck.nullConvert(optionPositionBeanList) %>
 		</select><br>
 		資格
 		<select name="license">
 			<%= NullCheck.nullConvert(optionLicenseBeanList) %>
 		</select><br>
 		性別<input type="radio" name ="sex" value="男" checked >男<input type="radio" name="sex" value="女">女<br>
		生年月日<input type="date" name="birth"><br>
		郵便番号〒<input type="number" name="addressNumber" id="addressNumber">
		<input class="studentEnrollment_button" type="button" value="住所を自動で入力する" onClick="setState()" />
		<br>
		都道府県<input type="text" name="state" id="state"><br>
		市区村長<input type="text" name="city" id="city"><br>
		番地<input type="text" name="address1" id="address1"><br>
		建物名<input type="text" name="address2" id="address2"><br>
		電話番号<input type="number" name="phoneNumber"><br>
		入社日<input type="month" name="dateOfEntering"><br>

		<input type="submit" value="登録">

 	</form>
 	<%=NullCheck.nullConvert(insertInfo) %>
</body>

<script>
  // 郵便番号から住所を取得
  function setState() {
	  console.log("自動入力");
    var zip = $('#addressNumber').val();

    // ここでzipのバリデーションを行ってください

    $.ajax({
      type : 'get',
      url : 'https://maps.googleapis.com/maps/api/geocode/json',
      crossDomain : true,
      dataType : 'json',
      data : {
        address : zip,
        language : 'ja',
        sensor : false
      },
      success : function(resp){
        if(resp.status == "OK"){
          // APIのレスポンスから住所情報を取得
          var obj = resp.results[0].address_components;
          if (obj.length < 5) {
            alert('正しい郵便番号を入力してください');
            return false;
          }
          //$('#country').val(obj[4]['long_name']); // 国
          $('#state').val(obj[3]['long_name']); // 都道府県
          $('#city').val(obj[2]['long_name']);  // 市区町村
          $('#address1').val(obj[1]['long_name']); // 番地
        }else{
          alert('住所情報が取得できませんでした');
          return false;
        }
      }
    });
  }


  $(document).ready(
      function() {
          $.fn.autoKana('#seiKanzi', '#seiKana', {
              katakana : true  //true：カタカナ、false：ひらがな（デフォルト）
      });
          $.fn.autoKana('#meiKanzi', '#meiKana', {
              katakana : true  //true：カタカナ、false：ひらがな（デフォルト）
      });
  });
</script>
<script type="text/javascript" src="js/app.js"></script>

</html>