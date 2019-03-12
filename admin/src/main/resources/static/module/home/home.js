(function(){
	
	function hijklmn(){}
	
	hijklmn.start = function(){
		
		$(".signout").click(function(){
			window.location.href = "/user/signout";
		});
		
	}
	
	return window.hijklmn = hijklmn;
	
})();