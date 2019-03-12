(function(){
	
	function fun(){}

	let editor;
	
	$("#editForm").validate( {
		rules: {
			imageUrl: {
				required:true,
			},
			title: {
				required:true,
			},
			subTitle: {
				required:true,
			},
			imageTag: {
				required:true,
			},
			author: "required",
			source: "required",
			soundType: "required",
		},
		messages: {
			imageUrl: "图片路径不能为空",
			title: "标题不能为空",
			subTitle: "二级标题不能为空",
			imageTag: "标签不能为空",
			author: "作者",
			source: "来源不能为空",
			soundType: "图片类型不能为空"
				
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
		
		var image = JSON.parse(data);
		
		// 初始化富文本框
		fun.initCKEditor(image);
		
		if(image){
			
			fun.fillData(image);
			
			if(image['imageUrl']){
				$("#picPreview").attr("src",imageUrl +'/'+ image['imageUrl']);
			}
			
		}
		
		$(".signout").click(function(){
			window.location.href = "/user/signout";
		});
		
		$("#saveBtn").click(function(){
			fun.save();
		});
		
		$("#previewBtn").click(function(){
			fun.preview();
		});
		
		$(".selectPic").click(function(){
			$("#picForm").click();
		});			
		
		$("#picForm").change(function(){
			var file = document.getElementById('picForm').files[0];
			var fileReader = new FileReader();
            fileReader.onloadend = function () {
                if (fileReader.readyState == fileReader.DONE) {
                    document.getElementById('picPreview').setAttribute('src', fileReader.result);
                }
            }
            fileReader.readAsDataURL(file);
		});
		
		$(".uploadPic").click(function(){
			fun.uploadImage();
		});
		
	}
	
	fun.check = function(){
		 return $("#editForm").valid();
	}
	
	fun.fillData = function(data){
		data = data || {};
		hijklmn.formData.fill("#editForm",data);
	}
	
	fun.save = function(){
		if(fun.check()){
			var params = hijklmn.formData.get("#editForm");
			var content = editor.getData();
			hijklmn.process.wait("show");
			params['content'] = content;
			hijklmn.ajax({
				url:'/image/save',
				data:{'params':JSON.stringify(params)},
				selfNotice:true,
				backCall:function(resp){
					if(resp.code == 1000){
						window.location.href = '/image/view';
					}else{
						hijklmn.notice.current(resp['reason']);
					}
				}
			});
		}
	}

	fun.uploadImage = function(){
		
		var file = document.getElementById('picForm').files[0];
		
		if(file == null || file == ''){
			hijklmn.notice.current("请选择图片");
			return;
		}
		
		var formData = new FormData();
		
		formData.append("upload",file);
		
		hijklmn.ajax({
			url:'/image/upload',
			data:formData,
			cache: false,
            processData: false,
            contentType: false,
            selfNotice:true,
			backCall:function(resp){
				if(resp.code == 1000){
					$("#imageUrl").val(resp['result']);
				}else{
					hijklmn.notice.current(resp['reason']);
				}
			}
		});
		
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
          +'<h5 class="modal-title"><label>Content Preview</label></h5>'
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
	
	fun.initCKEditor = function(image){
		
		ClassicEditor.create( document.querySelector( '#editor' ),
			{
			   ckfinder: {
		           uploadUrl: ckUploadReqUrl
		       }
			}	
		
		).then( newEditor => {
	        editor = newEditor;
	        if(image){
	        	newEditor.setData(image['content']);
	        }
	    } )
        .catch( error => {
        	hijklmn.notice.current( error );
        });
		
	}
	
	return window.fun = fun;
	
})();