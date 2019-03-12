(function(){

	function fun(){};
	
	fun.start = function(){
		
		$(".home").click(function(){
			window.location.href= '/';
		});
		
		$(".document").click(function(){
			window.location.href= '/document/view';
		});
		
	}
	
	return window.fun = fun;
	
})();