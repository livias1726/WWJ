function showPwd() {
	var input = document.getElementById('pwd');
	if (input.type == "password") {
		input.setAttribute("type", "text");
	} else {
		input.setAttribute("type", "password");
	}
}

$("#change").click(function (){
	document.getElementById("save").style.display = "block";
	
	document.getElementById("firstName").readOnly = false;
	document.getElementById("birthDate").readOnly = false;
	document.getElementById("email").readOnly = false;
	document.getElementById("lastName").readOnly = false;
	document.getElementById("city").readOnly = false;
	document.getElementById("pwd").readOnly = false;
})