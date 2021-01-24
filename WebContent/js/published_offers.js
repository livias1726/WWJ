function filterOffers(source){
	var table, rows, d, i, date;
	
	table = document.getElementById("row");
	rows = table.getElementsByTagName("tr");
	
	for(i=1; i<rows.length; i++){
		rows[i].style.visibility = "initial";
	}
		
	if(source.id == "act"){
		for(i=1; i<rows.length; i++){
			d = rows[i].getElementsByTagName("td");
			date = new Date(d[3].innerText);
			
			if(date < Date.now()){
				rows[i].style.visibility = "hidden";
			}
		}
		
	}else if(source.id == "exp"){
		for(i=1; i<rows.length; i++){
			d = rows[i].getElementsByTagName("td");
			date = new Date(d[3].innerText);
			
			if(date > Date.now()){
				rows[i].style.visibility = "hidden";
			}
		}
			
	}else{
		/**/
	}
}