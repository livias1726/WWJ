$("#change").click(function (){
	document.getElementById("save").style.display = "block";
	document.getElementById("new_branch").style.display = "block";
	document.getElementById("change").style.display = "none";
	
	document.getElementById("name").readOnly = false;
	document.getElementById("description").readOnly = false;
})
	    		
function addBranch(){
	var table, row, cell, i, input, temp;
	var listVal = ["country", "state", "city", "street", "number", "zip"];
	var listNam = ["r_country", "r_state", "r_city", "r_street", "r_number", "r_zip", "r_id"];
	
	table = document.getElementById("table");
	row = table.insertRow(-1);

	for(i=0; i<6; i++){
		cell = row.insertCell(i);
		
		input = document.createElement("input");
		input.type = "hidden";
		input.value = document.getElementById(listVal[i]).value;
		input.setAttribute("name", listNam[i]);
		
		cell.appendChild(input);
		
		temp = cell.innerHTML + document.getElementById(listVal[i]).value;
		
		cell.innerHTML = temp;
	}
	
	cell = row.insertCell(6);
		
	var input = document.createElement("input");
	input.type = "hidden";
	input.value = "0";
	input.setAttribute("name", listNam[6]); 
	
	cell.appendChild(input);
	
	cell.style.display = "none";
}    		