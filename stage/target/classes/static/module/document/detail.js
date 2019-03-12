(function(){

	function fun(){};
	
	if (window.history && window.history.pushState) {
        $(window).on('popstate', function () {
               window.history.pushState('forward', null, '#');
               window.history.forward(1);
               sessionStorage.clickrReturn = true;
        });
	}
	
	fun.start = function(){

		fun.fillData();
		
		$(".home").click(function(){
			window.location.href= '/';
		});
		
		$(".me").click(function(){
			window.location.href= '/me';
		});
		
		$(".return").click(function(){
			fun.back();
			sessionStorage.clickrReturn = true;
		});
		
		$(".user").click(function(){
			fun.showInfo();
		});	
		
	}
	
	fun.back = function(){
		history.back();
	}
	
	fun.fillData = function(){
		
		var doc = data;
		if(doc){
			var con = JSON.parse(doc);
			$("#content").append(con['content']);
		}
		
	}
	
	fun.showInfo = function(){
		var doc = data;
		if(doc){

			   var content = JSON.parse(doc);
			
		       if(new RegExp('modal-backdrop').test($("#infoModalWindow").next().attr("class"))){
		            $("#infoModalWindow").next().remove();
		        }

		        $("#previewModalWindow").remove();
				var con = '<div id="infoModalWindow" class="modal modal-success fade bd-example-modal-lg" tabindex="-1" role="dialog" aria-hidden="true">'
				  +'<div class="modal-dialog modal-dialog-centered modal-lg">'
				  +'<div class="modal-content">'
		          +'<div class="modal-header">'
		          +'<h5 class="modal-title"><label>Document Info</label></h5>'
		          +'<button type="button" class="close" data-dismiss="modal" aria-label="Close">'
		            +'<span aria-hidden="true">&times;</span>'
		          +'</button>'
			        +'</div>'
			        +'<div class="modal-body">'
			        +'<div class="ck-content" style="dispaly:block; overflow:auto;">'
			        +'<label>作者：'+content['author']+'</label><br/>'
			        +'<label>来源：'+content['source']+'</label><br/>'
			        +'<label>一级标题：'+content['title']+'</label><br/>'
			        +'<label>二级标题：'+content['subTitle']+'</label><br/>'
			        +'<label>文档类型：'+content['documentType']+'</label><br/>'
			        +'<label>上线时间：'+hijklmn.date.format(new Date(content['onlineTime']),'yyyy-MM-dd hh:mm:ss')+'</label><br/>'
			        +'</div>'
			        +'</div>'
				  +'</div>'
				  +'</div>'
				  +'</div>'
				  
				  $("body").append(con);
		          $("#infoModalWindow").modal();  
		        $('#infoModalWindow').on('hide.bs.modal', function () {
		            $("#infoModalWindow").remove();
		        });
			
		}
	}
	
	return window.fun = fun;
	
})();