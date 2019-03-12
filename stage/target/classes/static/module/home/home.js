(function(){

	function fun(){};
	
	fun.start = function(){

		$(".me").click(function(){
			window.location.href = '/me';
		});
		
		$(".document").click(function(){
			window.location.href = '/document/view';
		});
		
		$(".sound").click(function(){
			window.location.href = '/sound/view';
		});
		
		$(".document_btn").click(function(){
			window.location.href = '/document/view';
		});
		
		$(".sound_btn").click(function(){
			window.location.href = '/sound/view';
		});
		
	}
	
	return window.fun = fun;
	
})();