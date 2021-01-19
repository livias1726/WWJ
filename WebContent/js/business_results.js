$('#order').change(function() {
	var list, i, switching, b, shouldSwitch;
	
	list = document.getElementById("res");
	switching = true;

	while (switching) {
	    switching = false;
	    b = list.getElementsByTagName("LI");

	    for (i = 0; i < (b.length - 1); i++) {
	    	shouldSwitch = false;
	    	
			if($("#order option:selected").text().equals("Earnings")){
		      	if (b[i].getAverageEarnings() > b[i + 1].getAverageEarnings()) {
		        	shouldSwitch = true;
		        	break;
		      	}
	    	}else{
	    		if (b[i].getAverageCost() > b[i + 1].getAverageCost()) {
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

}