$(window).on('load', function () {
console.log("読み込み");

$(".UP").css('float','left');
$(".DOWN").css('float','right');

	$("#positionList").on("click",".UP",function(){
		console.log("up");
		var positionId = $(this).parent().parent().find(':hidden[name="positionId"]').val();
		console.log(positionId);

		$.ajax({
			    	url: 'PositionLankChangeServlet',
			    	type: 'POST',
			    	dataType: 'json',
			    	data: {
			    			positionId:positionId,
			    			method:'UP',
			    			},
			    })
			    .done(function() {

			    })
			    .fail(function() {
			    	console.log("error");
			    })
			    .always(function() {
			    	console.log("complete");
			    	window.setTimeout(reroad(),1000);
      			    window.setTimeout(reroad(),2000);
      			    window.setTimeout(reroad(),3000);
      			    window.setTimeout(reroad(),4000);
			    });

	});







	$("#positionList").on("click",".DOWN",function(){
		console.log("down");
		var positionId = $(this).parent().parent().find(':hidden[name="positionId"]').val();
		console.log(positionId);

				$.ajax({
			    	url: 'PositionLankChangeServlet',
			    	type: 'POST',
			    	dataType: 'json',
			    	data: {
			    			positionId:positionId,
			    			method:'DOWN',
			    			},
			    })
			    .done(function() {

			    })
			    .fail(function() {
			    	console.log("error");
			    })
			    .always(function() {
			    	console.log("complete");
			    	window.setTimeout(reroad(),1000);
      			    window.setTimeout(reroad(),2000);
      			    window.setTimeout(reroad(),3000);
      			    window.setTimeout(reroad(),4000);

			    });
			    
	});




	function reroad(){
    $.ajax({
    	url: 'PositionInsertTopServlet',
    	type: 'POST',
    	dataType: 'json',
    	data: {
			    ajaxFlag:"1",
			    		},
    })
    .done(function(result) {
    	console.log("Lankの入れ替え後の画面リロード");
    	document.getElementById("positionList").innerHTML=result;
    	$(".UP").css('float','left');
		$(".DOWN").css('float','right');
    })
    .fail(function() {
    	console.log("Lankの入れ替え後の画面リロード失敗");
    })
    .always(function() {
    	console.log("complete");
    });
  }

 $("#next").click(function(){
 	var page = parseInt($(':hidden[name="page"]').val());
 	console.log("next"+page);
 	page = page + 10;
 	$(':hidden[name="page"]').val(page);
 	console.log("next"+page);
 	$('#search_form').submit();
 });

  $("#prev").click(function(){
 	var page = parseInt($(':hidden[name="page"]').val());
 	console.log("prev"+page);
 	page = page - 10;
 	$(':hidden[name="page"]').val(page);
 	console.log("prev"+page);
 	$('#search_form').submit();
 });

  $("#search").click(function(){
  	console.log("search");
 	$(':hidden[name="page"]').val(0);
 	$('#search_form').submit();
 });

 $(".change").click(function(){
  	console.log('変更ボタン');
 	var $id=$(this).parent().parent().find('.empId').val();
 	console.log($id);
 	$('#search_form').attr('action', 'EmployeesUpdateServlet');
 	$("#emp_id").remove();
 	$("<input>", {
		type: 'hidden',
		id: 'emp_id',
		name:'emp_id',
		value: $id
		}).appendTo('#search_form');
 	$('#search_form').submit();
 });

var mode = $("#mode").val();
var deptVal = $("#dept option:selected").val();
if(deptVal!=null&&mode=='insert'){
	var dept = deptVal.split(",")
	var deptId = dept[0];


			$.ajax({
			    	url: 'EmployeesSectionGetServlet',
			    	type: 'POST',
			    	dataType: 'json',
			    	data: {
			    			deptId:deptId,
			    			},
			    })
			    .done(function(result) {
					document.getElementById("section").innerHTML=result;
			    })
			    .fail(function() {
			    })
			    .always(function() {

			    });

}
	



$("#dept").change(function(){
	console.log("課のチェンジ");
	var deptVal = $("#dept option:selected").val();
	var dept = deptVal.split(",")
	var deptId = dept[0];


			$.ajax({
			    	url: 'EmployeesSectionGetServlet',
			    	type: 'POST',
			    	dataType: 'json',
			    	data: {
			    			deptId:deptId,
			    			},
			    })
			    .done(function(result) {
					document.getElementById("section").innerHTML=result;
			    })
			    .fail(function() {
			    })
			    .always(function() {

			    });
	});




});