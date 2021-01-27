$('#order_bus').change(function() {
	var list, i, switching, b, shouldSwitch, button1, button2;
	
	list = document.getElementById("res");
	switching = true;

	while (switching) {
	    switching = false;
	    b = list.getElementsByTagName("li");

	    for (i = 0; i < b.length - 1; i++) {
	    	button1 = b[i].getElementsByTagName("button");
	    	button2 = b[i+1].getElementsByTagName("button");
	    	shouldSwitch = false;
	    	
			if($("#order_bus option:selected").text() == "Earnings"){
		      	if (button1[2].getAttribute("value") < button2[2].getAttribute("value")) {
		        	shouldSwitch = true;
		        	break;
		      	}
	    	}else{
	    		if (button1[1].getAttribute("value") < button2[1].getAttribute("value")) {
		        	shouldSwitch = true;
		        	break;
		      	}
	    	}
	    }
    
	    if (shouldSwitch) {
	      	b[i].parentNode.insertBefore(b[i + 1], b[i]);
	      	switching = true;
	    }
  	}
});

$('#order_off').change(function() {
	var list, i, switching, b, shouldSwitch, button1, button2, date1, date2;
	
	list = document.getElementById("res");
	switching = true;

	while (switching) {
	    switching = false;
	    b = list.getElementsByTagName("li");

	    for (i = 0; i < b.length - 1; i++) {
	    	button1 = b[i].getElementsByTagName("button");
	    	button2 = b[i+1].getElementsByTagName("button");
	    	shouldSwitch = false;

			if($("#order_off option:selected").text() == "Expiration"){
				date1 = new Date(button1[2].getAttribute("value"));
				date2 = new Date(button2[2].getAttribute("value"));
				
		      	if (date1 > date2) {
		        	shouldSwitch = true;
		        	break;
		      	}
	    	}else{
	    		date1 = new Date(button1[1].getAttribute("value"));
				date2 = new Date(button2[1].getAttribute("value"));
				
	    		if (date1 > date2) {
		        	shouldSwitch = true;
		        	break;
		      	}
	    	}
	    }
    
	    if (shouldSwitch) {
	      	b[i].parentNode.insertBefore(b[i + 1], b[i]);
	      	switching = true;
	    }
  	}
});

function filterResults(){
	var list, list2, b, checkBtn, i, j, count;
	
	list = document.getElementById("res");
	b = list.getElementsByTagName("li");
	
	list2 = document.getElementById("filters");
	checkBtn = list2.getElementsByTagName("input");

	count = 0;
	for (i = 0; i < checkBtn.length; i++) {  	
		if(!checkBtn[i].checked){		
	      	for(j = 0; j < b.length; j++){
	      		button = b[j].getElementsByTagName("button");
	 			if(button[3].getAttribute("value") == checkBtn[i].getAttribute("value")){
	 				b[j].style.visibility = "hidden";
	 			}
	 		}
	 		count++;
    	}
     }
     
     if(count == checkBtn.length || count == 0){	 
     	for(j = 0; j < b.length; j++){
      		b[j].style.visibility = "initial";
 		}
     }
}
