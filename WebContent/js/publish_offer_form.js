function addNewJob(){
	document.getElementById("new_job").style.display = "none";
	document.getElementById("new").style.display = "block";
}

function addRequirement(){
	var list, elem, text, reqs, input;
	
	list = document.getElementById("req_list");
	elem = document.createElement("li");
	text = document.getElementsByName("requirement");
	input = document.getElementById("req_hid");
	elem.innerText = text[0].value;
	
	reqs = input.value;
	if(reqs == ""){
		input.value = text[0].value;
	}else{
		input.value = reqs + "&" + text[0].value;
	}
	
	list.append(elem);
}

$('#position').change(function() {
	var str, publish;
	
	publish = document.getElementById("publish");
	
	str = $("#position option:selected").attr('id') + "&" + $("#company option:selected").attr('id');
	
	publish.value = str;
});

$('#company').change(function() {
	var str, publish;
	
	publish = document.getElementById("publish");
	
	str = $("#position option:selected").attr('id') + "&" + $("#company option:selected").attr('id');
	
	publish.value = str;
});