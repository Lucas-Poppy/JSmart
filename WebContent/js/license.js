$(window).on('load', function () {
console.log("読み込み");
	$("#licenseList").on("dblclick",".textbox",function(){
		console.log("up");
		var licenseId = $(this).data('id');

		console.log(licenseId);
		$.ajax({
			    	url: 'LicenseUpdateTextBoxAjaxServlet',
			    	type: 'POST',
			    	dataType: 'json',
			    	data: {
			    			licenseId:licenseId,
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
			    	$("#licenseLankInput").focus().select();
			    });

	});


});