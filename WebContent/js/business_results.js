$('#order').change(function sortList() {
	var list, i, switching, b, shouldSwitch;
	  list = document.getElementById("res");
	  switching = true;

	if($("#order option:selected").text().equals("Earnings")){
		while (switching) {
		    switching = false;
		    b = list.getElementsByTagName("LI");

		    for (i = 0; i < (b.length - 1); i++) {
		      shouldSwitch = false;
		      
		      if (b[i] > b[i + 1]) {
		        shouldSwitch = true;
		        break;
		      }
		    }
	    
		    if (shouldSwitch) {
		      b[i].parentNode.insertBefore(b[i + 1], b[i]);
		      switching = true;
		    }
  		}
	}else{
	
	}
  
  
 
});