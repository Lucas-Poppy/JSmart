<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="session.SessionKanri"%>
<%@ page import="benefit.NullCheck"%>

<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.List"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="js/jquery-3.1.1.min.js"></script>
<script src="js/jquery.autoKana.js" language="javascript" type="text/javascript"></script>
</head>
<body>
<%
request.setCharacterEncoding("UTF-8");

String optionDepartmentBeanList =(String) session.getAttribute("strDepartmentBeanList");
String optionPositionBeanList = (String) session.getAttribute("strPositionBeanList");
String sectionOptionMenu = (String) session.getAttribute("sectionOptionMenu");
List<String> UpdateInputList = (ArrayList<String>) session.getAttribute("UpdateInputList");

String insertInfo =(String) session.getAttribute("insertEmployee");
SessionKanri.sessionRemoveAll(request);

String men = "";
String women="";
if(NullCheck.nullConvert(UpdateInputList.get(4)).equals("男")){
	men="checked";
}else{
	women="checked";

}
%>
 	<form action="EmployeesUpdateResultServlet" method="POST">
 		  <input type="hidden" name="empId" value="<%= NullCheck.nullConvert(UpdateInputList.get(13)) %>">

 		姓<input type = "text" name ="seiKanzi" id="seiKanzi" value="<%= NullCheck.nullConvert(UpdateInputList.get(0)) %>">名<input type="text" name="meiKanzi" id="meiKanzi" value="<%= NullCheck.nullConvert(UpdateInputList.get(1)) %>"><br>
 		セイ<input type = "text" name ="seiKana" id="seiKana" value="<%= NullCheck.nullConvert(UpdateInputList.get(2)) %>">メイ<input type="text" name="meiKana" id="meiKana" value="<%= NullCheck.nullConvert(UpdateInputList.get(3)) %>"><br>

 		部署
 		<select name="dept" id="dept">
	 		<%= NullCheck.nullConvert(optionDepartmentBeanList) %>
 		</select><br>


 		課
 		<select name="section" id="section">
	 		<div id="sectionList">
	 		<%= NullCheck.nullConvert(sectionOptionMenu) %>
	 		</div>
 		</select><br>

 		役職
 		<select name="position">
 			<option value='0'>---選択してください---</option>
 			<%= NullCheck.nullConvert(optionPositionBeanList) %>
 		</select><br>

 		性別<input type="radio" name ="sex" value="男" <%=men %> >男<input type="radio" name="sex" value="女" <%=women %>>女<br>
		郵便番号〒<input type="number" name="addressNumber" id="addressNumber" value="<%= NullCheck.nullConvert(UpdateInputList.get(5)) %>">
		<input class="studentEnrollment_button" type="button" value="住所を自動で入力する" onClick="setState()" />
		<br>
		都道府県<input type="text" name="state" id="state" value="<%= NullCheck.nullConvert(UpdateInputList.get(6)) %>"><br>
		市区村長<input type="text" name="city" id="city" value="<%= NullCheck.nullConvert(UpdateInputList.get(7)) %>"><br>
		番地<input type="text" name="address1" id="address1" value="<%= NullCheck.nullConvert(UpdateInputList.get(8)) %>"><br>
		建物名<input type="text" name="address2" id="address2" value="<%= NullCheck.nullConvert(UpdateInputList.get(9)) %>"><br>
		電話番号<input type="number" name="phoneNumber" value="<%= NullCheck.nullConvert(UpdateInputList.get(10)) %>"><br>
		入社日<input type="month" name="dateOfEntering" value="<%= NullCheck.nullConvert(UpdateInputList.get(11)) %>"><br>
		退社日<input type="month" name="dateOfRetire" value="<%= NullCheck.nullConvert(UpdateInputList.get(12)) %>"><br>

		<input type="submit" value="変更">

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