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


});