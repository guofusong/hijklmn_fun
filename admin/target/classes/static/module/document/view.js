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
				var docId = $(this).attr('data-docId');
				window.location.href = '/document/detail?docId='+docId;
			});
			
			$(".preview").off('click');
			$(".preview").on('click',function(){
				var docId = $(this).attr('data-docId');
				fun.preview(contentCache.get(docId));
			});
			
			$(".delete").off('click');
			$(".delete").on('click',function(){
				var docId = $(this).attr('data-docId');
				hijklmn.ajax({
					type:'post',
					url:'/document/delete',
					data:{'docId':docId},
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
				url: '/document/queryData',
				type: 'post',
				dataSrc: 'result',
			},
			
			columnDefs: [
				{
	                "render": function ( data, type, row ) {
	                	contentCache.set(row['docId'],row['content']);
	                    return '<span class="btn btn-primary btn-flat margin detail" data-docId="'+row['docId']+'">详情</span><span class="btn btn-success btn-flat margin preview" data-docId="'+row['docId']+'">预览</span><span class="btn btn-danger btn-flat margin delete" data-docId="'+row['docId']+'">删除</span>';
	                },
	                "sort":false,
	                "targets": 7
	            },
	            {
	                "render": function ( data, type, row ) {
	                	return '<span>'+hijklmn.date.format(new Date(row['onlineTime']),'yyyy-MM-dd hh:mm:ss')+'</span>';
	                },
	                "targets": 6
	            },
	            {
	                "render": function ( data, type, row ) {
	                	if(row['isBlog'] == 0){
	                		return '<span>文章</span>';
	                	}else if(row['isBlog'] == 1){
	                		return '<span>博客</span>';
	                	}else{
	                		return '<span>未知</span>';
	                	}
	                },
	                "targets": 3
	            },
			],
	        columns: [
			    { data: "title" },
			    { data: "subTitle" },
			    { data: "documentType" },
			    { data: "isBlog" },
			    { data: "author" },
			    { data: "source" },
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