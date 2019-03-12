(function(){
	
	function fun(){}

	let editor;
	
	$("#editForm").validate( {
		rules: {
			title: {
				required:true,
			},
			subTitle: {
				required:true,
			},
			author: "required",
			source: "required",
			documentType: "required",
		},
		messages: {
			title: "标题不能为空",
			subTitle: "二级标题不能为空",
			author: "作者不能为空",
			source: "来源不能为空",
			documentType: "文档类型不能为空"
		},
		errorElement: "em",
		errorPlacement: function ( error, element ) {
			error.addClass( "help-block" );
			if ( element.prop( "type" ) === "checkbox" ) {
				error.insertAfter( element.parent( "label" ) );
			} else {
				error.insertAfter( element );
			}
		},
		highlight: function ( element, errorClass, validClass ) {
			$( element ).parents( ".col-md-11" ).addClass( "has-error" ).removeClass( "has-success" );
		},
		unhighlight: function (element, errorClass, validClass) {
			$( element ).parents( ".col-md-11" ).addClass( "has-success" ).removeClass( "has-error" );
		}
	} );	
	
	fun.start = function(){
		
		var doc = JSON.parse(data);
		
		// 初始化富文本框
		fun.initCKEditor(doc);
		
		if(doc){
			fun.fillData(doc);
		}
		
		$(".signout").click(function(){
			window.location.href = "/user/signout";
		});
		
		$(".saveBtn").click(function(){
			var isBlog = $(this).attr('data-blog');
			fun.save(isBlog);
		});
		
		$("#previewBtn").click(function(){
			fun.preview();
		});
		
	}
	
	fun.check = function(){
		 return $("#editForm").valid();
	}
	
	fun.fillData = function(data){
		data = data || {};
		hijklmn.formData.fill("#editForm",data);
	}
	
	fun.save = function(blog){
		if(fun.check()){
			var params = hijklmn.formData.get("#editForm");
			var content = editor.getData();
			hijklmn.process.wait("show");
			params['content'] = content;
			params['isBlog'] = blog;
			hijklmn.ajax({
				url:'/document/save',
				data:{'params':JSON.stringify(params)},
				selfNotice:true,
				backCall:function(resp){
					if(resp.code == 1000){
						window.location.href = '/document/view';
					}else{
						hijklmn.notice.current(resp.reason);
					}
				}
			});
		}
	}
	
	fun.preview = function(){
		
        if(new RegExp('modal-backdrop').test($("#previewModalWindow").next().attr("class"))){
            $("#previewModalWindow").next().remove();
        }

        var content = editor.getData();
        
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
	
	fun.initCKEditor = function(doc){
		
		ClassicEditor.create( document.querySelector( '#editor' ),
			{
			   ckfinder: {
		           uploadUrl: ckUploadReqUrl
		       }
			}	
		
		).then( newEditor => {
	        editor = newEditor;
	        if(doc){
	        	newEditor.setData(doc['content']);
	        }
	    } )
        .catch( error => {
        	hijklmn.notice.current( error );
        });
		
	}
	
	return window.fun = fun;
	
})();