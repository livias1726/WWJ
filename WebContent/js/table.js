function selectAll(source){
	var i, check, arg, temp;
	check = document.getElementsByName("sel");
	
	for(i = 0; i < check.length; i++){
		check[i].checked = source.checked;
	}
	
	temp = "";
	if(source.checked){
		for(i = 0; i < check.length; i++){
			arg = temp;
			if(arg == ""){
				temp = check[i].value;
			}else{
				temp = arg + "&" + check[i].value;
			}
		}
	}
	
	$("#delete").val(temp);
}

function selectElem(source){
	var arg = $("#delete").val();
	var check = document.getElementsByName("sel");
	
	if(source.checked){
		if(arg == ""){
			$("#delete").val(source.value);
		}else{
			$("#delete").val(arg + "&" + source.value);
		}
	}else{
		var temp="";
		for(i = 0; i < check.length; i++){
			arg = temp;
			if(arg == ""){
				temp = check[i].value;
			}else{
				temp = arg + "&" + check[i].value;
			}
		}
		$("#delete").val(temp);
	}
}