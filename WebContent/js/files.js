function uploadPic(source) {
    if (source.files && source.files[0]) {
        var reader = new FileReader();
        reader.onloadend = function (e) {
            $('#profile').attr('src', e.target.result);
            var base64result = (e.target.result).split(',')[1];
            document.getElementById("store_pic").value = base64result;
            $("#submit").trigger("click");
        }
        reader.readAsDataURL(source.files[0]);
    }
}
	
function uploadCv(source) {
    if (source.files && source.files[0]) {
        var reader = new FileReader();
	    reader.onloadend = function (e) {
	    	var base64result = (e.target.result).split(',')[1];
            document.getElementById("store_cv").value = base64result;
	        $("#submit2").trigger("click");
	    }
	    reader.readAsDataURL(input.files[0]);
	}
}


