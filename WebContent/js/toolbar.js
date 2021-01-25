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

function adPopUp(){
	switch(Math.floor(Math.random()*3)) {
		case 0:
			window.open("https://freedesignfile.com/upload/2019/07/Healthy-eating-fruit-background-advertisement-vector.jpg");
			break;
		case 1:
			window.open("https://5.imimg.com/data5/MK/VP/TO/SELLER-2650952/commercial-advertisement-service-500x500.jpg");
			break;
		case 2:
			window.open("https://www.greentechmedia.com/assets/content/cache/made/assets/content/cache/remote/https_assets.greentechmedia.com/content/images/articles/green_advertisement_500_500_80_s_c1.jpg");
			break;
		default:
			;
	}
}