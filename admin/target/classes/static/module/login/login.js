(function(){

	function fun(){};
	
	fun.signIn = function(){
		
		var _ready = function(){
			var username = $("input[name='username']").val();
			var password = $("input[name='password']").val();
			if(username == null || username == '' || password == null || password == ''){
				hijklmn.notice.current("用户名和密码不能为空！");
			}else{
				hijklmn.ajax({
					url:'/user/signin',
					data:{"username":username,"password":password},
					backCall:function(resp){
						if(resp.code != 1000){
							hijklmn.notice.current(resp.reason);
						}else{
							location.href = '/home';
						}
					}
				});
			}
		}
		
		var _submit = function(){
			_ready();
		}
		
		return {
			submit : _submit,
		}
		
	}();
	
	fun.start = function(){
		$(".signin").click(function(){
			fun.signIn.submit();
		});
	}
	
	return window.fun = fun;
	
})();