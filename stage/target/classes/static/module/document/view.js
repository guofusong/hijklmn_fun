(function(){

	function fun(){};
	
	var index = 0 , size = 10 , total = 0;
	
	var queryTimes = 0;
	
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
		
		$(".me").click(function(){
			window.location.href= '/me';
		});
		
	}
	
	fun.loadData = function(){
		var params = {'index':index,'size':size,'sort':'online_time desc'};
		hijklmn.ajax({
			url:'/document/queryData',
			data:params,
			backCall:function(resp){
				if(resp.code == 1000){
					$("#loadMore").remove();
					queryTimes ++;
					sessionStorage.queryTimes = queryTimes;
					index = ((index + 1) * size);
					total = resp.gridData.total;
					var items = resp.gridData.items;
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
				$(temp).find('.title').text(item['title']);
				$(temp).find('.sub_title').text(item['subTitle']);
				$(temp).find('.online_time').text(hijklmn.date.format(new Date(item['onlineTime']),'yyyy-MM-dd hh:mm:ss'));
				$(temp).find('.author').text(item['author']);
				$(temp).data('docId',item['docId']);
				$("#content").append(temp);
				$(temp).click(function(){
					var docId = $(temp).data('docId');
					fun.detail(docId);
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
	fun.detail = function(docId){
		if(docId){
			window.location.href = '/document/detail?docId='+docId;			
		}
	}
	
	return window.fun = fun;
	
})();