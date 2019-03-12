(function(){
	
	function fun(){}

	var contentCache = new Map();
	
	fun.start = function(){

		fun.initTable();
		
		$(".signout").click(function(){
			window.location.href = "/user/signout";
		});
		
		$('#example').on('draw.dt', function(){
			$(".detail").off('click');
			$(".detail").on('click',function(){
				var souId = $(this).attr('data-souId');
				window.location.href = '/sound/detail?souId='+souId;
			});
			
			$(".preview").off('click');
			$(".preview").on('click',function(){
				var souId = $(this).attr('data-souId');
				fun.preview(contentCache.get(souId));
			});
			
			$(".delete").off('click');
			$(".delete").on('click',function(){
				var souId = $(this).attr('data-souId');
				hijklmn.ajax({
					type:'post',
					url:'/sound/delete',
					data:{'souId':souId},
					selfNotice:true,
					backCall:function(resp){
						if(resp.code == 1000){
							fun.initTable();
						}else{
							hijklmn.notice.current(resp.reason);
						}
					}
				});
			});
			
		});
		
	}
	
	// 初始化表格
	fun.initTable = function(){

		$("#example").dataTable( {
			
			responsive: true,
			bStateSave: true,
			bProcessing: true,
			destroy: true,
	  		
			ajax : {
				url: '/sound/queryData',
				type: 'post',
				dataSrc: 'result',
			},
			
			columnDefs: [
				{
	                "render": function ( data, type, row ) {
	                	contentCache.set(row['souId'],row['content']);
	                    return '<span class="btn btn-primary btn-flat margin detail" data-souId="'+row['souId']+'">详情</span><span class="btn btn-success btn-flat margin preview" data-souId="'+row['souId']+'">预览</span><span class="btn btn-danger btn-flat margin delete" data-souId="'+row['souId']+'">删除</span>';
	                },
	                "sort":false,
	                "targets": 5
	            },
	            {
	                "render": function ( data, type, row ) {
	                	return '<span>'+hijklmn.date.format(new Date(row['onlineTime']),'yyyy-MM-dd hh:mm:ss')+'</span>';
	                },
	                "targets": 4
	            },
			],
	        columns: [
			    { data: "soundName" },
			    { data: "author" },
			    { data: "source" },
			    { data: "soundType" },
			    { data: "onlineTime" },
			    { data: "" }
	  		],
	  		
	  		oLanguage : {
	  			sLengthMenu: "每页显示 _MENU_ 条记录",
	  			sZeroRecords: "对不起，没有匹配的数据",
	  			sInfo: "第 _START_ - _END_ 条 / 共 _TOTAL_ 条记录",
	  			sInfoEmpty: "对不起，没有匹配的数据",
	  			sInfoFiltered: "(数据表中共 _MAX_ 条记录)",
	  			sProcessing: "正在加载中...",
	  			sSearch: "搜索：",
	  			oPaginate: {
	  				sFirst: "首页",
	  				sPrevious: " 上一页 ",
	  				sNext: " 下一页 ",
	  				sLast: " 尾页 "
		  		}			
	  		}
	        
		})
		
	}
	
	fun
	
	// 预览
	fun.preview = function(content){
		
        if(new RegExp('modal-backdrop').test($("#previewModalWindow").next().attr("class"))){
            $("#previewModalWindow").next().remove();
        }

        $("#previewModalWindow").remove();
		var con = '<div id="previewModalWindow" class="modal modal-success fade bd-example-modal-lg" tabindex="-1" role="dialog" aria-hidden="true">'
		  +'<div class="modal-dialog modal-lg">'
		  +'<div class="modal-content">'
          +'<div class="modal-header">'
          +'<h5 class="modal-title"><label>Document Preview</label></h5>'
          +'<button type="button" class="close" data-dismiss="modal" aria-label="Close">'
            +'<span aria-hidden="true">&times;</span>'
          +'</button>'
	        +'</div>'
	        +'<div class="modal-body">'
	        +'<div class="ck-content" style="dispaly:block; overflow:auto;">'
	        +content
	        +'</div>'
	        +'</div>'
	        +'<div class="modal-footer">'
	          +'<button type="button" class="btn btn-outline pull-right" data-dismiss="modal">Close</button>'
	        +'</div>'
		  +'</div>'
		  +'</div>'
		  +'</div>'
		  
		  $("body").append(con);
          $("#previewModalWindow").modal();  
        $('#previewModalWindow').on('hide.bs.modal', function () {
            $("#previewModalWindow").remove();
        });
		
	}
	
	return window.fun = fun;
	
})();