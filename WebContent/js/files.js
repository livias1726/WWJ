function noResultDialog() {
    $("#main").dialog({
    	resizable: false,
      	height: "auto",
      	width: 400,
      	modal: true,
      	buttons: {
      		"Upload": function() {
      			$(this).dialog("close");
        		//OPEN FILE SYSTEM TO UPLOAD CV
        	}
    	}
    });
    
    $("#main").dialog("open");
}

function resultDialog() {
    $("#main").dialog({
    	resizable: false,
      	height: "auto",
      	width: 400,
      	modal: true,
      	buttons: {
      		"Change": function() {
      			$(this).dialog("close");
        		var reader = new FileReader();
        	},
        	
        	"Show": function() {
      			$(this).dialog("close");
      			window.open("Here Download PDF url", '_blank');
        	}
    	}
    });
    
    $("#main").dialog("open");
}

$("#file_selector").change(function(){
	var input = document.getElementById("file_selector");
	var img = document.getElementById("profile");
	      
	if (input.files && input.files[0]) {
		var reader = new FileReader();
		reader.onloadend = function (e) {
			img.style.backgroundImage = "url(" + e.target.result + ")";
      	};
	    reader.readAsDataURL(input.files[0]);
	    
	    var saver = new FileReader();
		saver.onloadend = function (e) {
			$("#pic_hid").val(e.target.result);
			$("#pic_hid").trigger("click");
      	};
	    saver.readAsArrayBuffer(input.files[0]);
	}
})
