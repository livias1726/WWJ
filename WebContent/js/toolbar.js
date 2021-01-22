$('#notif').click(function() {

	 var x = document.getElementById("click");
	 
	 if(x.value == ""){
	 	$('#window').text($(this).find('button:first').next().text());
	 	$('#window').show();
	 	x.value = "1";
	 }else{
	 	$('#window').hide();
	 	x.value = "";
	 }
});