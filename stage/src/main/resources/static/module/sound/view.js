(function(){

	function fun(){};
	
	var index = 0 , size = 10 , total = 0;
	
	var queryTimes = 0;
	
	var mediaConfData = {};
	
	fun.start = function(){
		
		if(sessionStorage.clickrReturn && sessionStorage.clickrReturn == 'true'){
			sessionStorage.clickrReturn = false;
			var tempTimes = sessionStorage.queryTimes;
			for(var i=0; i<tempTimes; i++){
				fun.loadData();
			}
		}else{
			fun.loadData();
		}
		

		$(".home").click(function(){
			window.location.href= '/';
		});

		$(".document").click(function(){
			window.location.href= '/document/view';
		});
		
		$(".me").click(function(){
			window.location.href= '/me';
		});
		
	}
	
	fun.loadData = function(){
		var params = {'index':index,'size':size,'sort':'online_time desc'};
		hijklmn.ajax({
			url:'/sound/queryData',
			data:params,
			backCall:function(resp){
				if(resp.code == 1000){
					queryTimes ++;
					sessionStorage.queryTimes = queryTimes;
					$("#loadMore").remove();
					index = ((index + 1) * size);
					total = resp.gridData.total;
					var items = resp.gridData.items;
					if(!mediaConfData['imageUrl']){
						mediaConfData['imageUrl'] = resp['result']['imageUrl'];
						mediaConfData['soundUrl'] = resp['result']['soundUrl'];
					}
					fun.fillData(items);
				}
			}
		});
	}
	
	fun.fillData = function(data){
		
		if(data){
			$(data).each(function(i,item){
				var temp = $("#itemTemplate").clone();
				$(temp).removeAttr('id');
				$(temp).css('display','block');
				if(item['soundTopPic']){
					$(temp).find('img').attr('src',mediaConfData['imageUrl']+item['soundTopPic']);
					$(temp).find('img').click(function(){
						var oUrl = $("#miniPlayer").attr('src');
						var nUrl = mediaConfData['soundUrl']+'/'+item['soundUrl'];
						
						if(oUrl == nUrl){
							var audio = $("#miniPlayer")[0];
							if(audio.paused){
								$("#player_info").removeClass("mini_player");
								$("#player_info").addClass("mini_player_active");
								audio.play();
							}else{
								$("#player_info").removeClass("mini_player_active");
								$("#player_info").addClass("mini_player");
								audio.pause();
							}
						}else{
							$("#miniPlayer").attr('src',nUrl);
							$("#player_info").find('img').attr('src',mediaConfData['imageUrl']+'/'+item['soundTopPic']);
							$("#player_info").removeClass("mini_player");
							$("#player_info").addClass("mini_player_active");
							var audio = $("#miniPlayer")[0];
							audio.load();
							audio.play();
						}
					});
				}
				$(temp).find('.sound_name').html(item['soundName']);
				$(temp).find('.sound_author').html(item['author']);
				$(temp).data('souId',item['souId']);
				$("#content").append(temp);
				$(temp).dblclick(function(){
					var souId = $(temp).data('souId');
					//fun.detail(souId);
				});
			});
		}
		
		if(index < total){
			var temp = $("#loadMoreTemplate").clone();
			$(temp).attr('id','loadMore');
			$(temp).css('display','block');
			$("#content").append(temp);
		}
		
		$("#loadMore").click(function(){
			fun.loadData();		
		});
		
	}
	
	//查看详细信息
	fun.detail = function(souId){
		if(souId){
			window.location.href = '/sound/detail?souId='+souId;			
		}
	}
	
	return window.fun = fun;
	
})();