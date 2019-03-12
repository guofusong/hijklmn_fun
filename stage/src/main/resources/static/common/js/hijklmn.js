(function(window,hijklmn) {

	hijklmn.date  = {
		addDay:function(date,day){
			var d = new Date( date.getTime())
			d.setDate(d.getDate() + day); 
			return d;
		},
		addMonth:function(date,num){
			var d = new Date( date.getTime())
			d.setMonth(d.getMonth() + num); 
			return d;
		},
		addHour:function(date,hour){
			var d = date.getTime();
			return new Date( d + (hour*60*60*1000));
		},
		format : function(date,format){
			 var o = {
		        "M+": date.getMonth() + 1, //月份
		        "d+": date.getDate(), //日
		        "D+": date.getDate(), //日
		        "h+": date.getHours(), //小时
		        "m+": date.getMinutes(), //分
		        "s+": date.getSeconds(), //秒
		        "q+": Math.floor((date.getMonth() + 3) / 3), //季度
		        "S": date.getMilliseconds() //毫秒
		    };
		    if (/(y+)/.test(format)) format = format.replace(RegExp.$1, (date.getFullYear() + "").substr(4 - RegExp.$1.length));
		    for (var k in o)
		    if (new RegExp("(" + k + ")").test(format)) format = format.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
		    return format;
		},
		strToDate : function(dateStr){
			 var date = eval('new Date(' + dateStr.replace(/\d+(?=-[^-]+$)/, 
			   function (a) { return parseInt(a, 10) - 1; }).match(/\d+/g) + ')');
			  return date;
		}
	}
	
    hijklmn.process = {
        wait:function(options){
            switch(options){
                case 'show':
                    if(new RegExp('modal-backdrop').test($("#waitModalWindow").next().attr("class"))){
                        $("#waitModalWindow").next().remove();
                    }

                    $("#waitModalWindow").remove();

                    var con = '<div id="waitModalWindow" class="modal fade bs-example-modal-sm" tabindex="-1" role="dialog" aria-hidden="true" data-backdrop="static" keyboard="false">'
                    	+'<div class="modal-dialog modal-dialog-centered modal-sm" role="document">'
                    	+'<div class="modal-body" style="text-align:center;">'
                    	+'<img src="/common/image/loading.gif"/>'
                    	+'</div>'
                    	+'</div>'
                    	+'</div>'

                    $("body").append(con);

                    $("#waitModalWindow").modal({ keyboard: false });  

                    $('#waitModalWindow').on('hide.bs.modal', function () {
                        $("#waitModalWindow").remove();
                    });
                break;

                case 'hide':
                    $("#waitModalWindow").modal("hide");  
                break;
                default:
                break;

            }
        },
    }
	
    hijklmn.notice = {

        current:function(content){

            if(new RegExp('modal-backdrop').test($("#noticeModalWindow").next().attr("class"))){
                $("#noticeModalWindow").next().remove();
            }

            $("#noticeModalWindow").remove();

            var con = '<div id="noticeModalWindow" class="modal fade" tabindex="-1" role="dialog">'
              +'<div class="modal-dialog modal-dialog-centered" role="document">'
                +'<div class="modal-content">'
                  +'<div class="modal-header">'
                    +'<h5 class="modal-title"><label>Notice</label></h5>'
                    +'<button type="button" class="close" data-dismiss="modal" aria-label="Close">'
                      +'<span aria-hidden="true">&times;</span>'
                    +'</button>'
                  +'</div>'
                  +'<div class="modal-body">'
                    +'<p>'+content+'</p>'
                  +'</div>'
                  +'<div class="modal-footer">'
                    +'<button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>'
                  +'</div>'
                +'</div>'
              +'</div>'
            +'</div>'

            $("body").append(con);

            $("#noticeModalWindow").modal("show");  

            $('#noticeModalWindow').on('hide.bs.modal', function () {
                $("#noticeModalWindow").remove();
            });

        },

        confirm:function(content,fun){

            if(new RegExp('modal-backdrop').test($("#confirmModalWindow").next().attr("class"))){
                $("#confirmModalWindow").next().remove();
            }

            $("#confirmModalWindow").remove();

            var con = '<div id="confirmModalWindow" class="modal fade" tabindex="-1" role="dialog">'
              +'<div class="modal-dialog modal-dialog-centered" role="document">'
                +'<div class="modal-content">'
                  +'<div class="modal-header">'
                    +'<h5 class="modal-title"><label>Confirm</label></h5>'
                    +'<button type="button" class="close" data-dismiss="modal" aria-label="Close">'
                      +'<span aria-hidden="true">&times;</span>'
                    +'</button>'
                  +'</div>'
                  +'<div class="modal-body">'
                    +'<p>'+content+'</p>'
                  +'</div>'
                  +'<div class="modal-footer">'
                    +'<button type="button" class="btn btn-secondary" data-dismiss="modal">Cancel</button>'
                    +'<button type="button" class="btn btn-primary confirmModalWindowOk">Ok</button>'
                  +'</div>'
                +'</div>'
              +'</div>'
            +'</div>'

            $("body").append(con);

            $(".confirmModalWindowOk").click(function(){
                if($.isFunction( fun ))  fun.call( this);
                $("#confirmModalWindow").modal("hide");
            });

            $("#confirmModalWindow").modal("show");  

            $('#confirmModalWindow').on('hide.bs.modal', function () {
                $("#confirmModalWindow").remove();
            });

        },

        sessionExpired:function(){

            if(new RegExp('modal-backdrop').test($("#sessionExpiredModalWindow").next().attr("class"))){
                $("#sessionExpiredModalWindow").next().remove();
            }

            $("#sessionExpiredModalWindow").remove();

            var con = '<div id="sessionExpiredModalWindow" class="modal fade" tabindex="-1" role="dialog">'
              +'<div class="modal-dialog modal-dialog-centered" role="document">'
                +'<div class="modal-content">'
                  +'<div class="modal-header">'
                    +'<h5 class="modal-title"><label>Session Expired</label></h5>'
                    +'<button type="button" class="close" data-dismiss="modal" aria-label="Close">'
                      +'<span aria-hidden="true">&times;</span>'
                    +'</button>'
                  +'</div>'
                  +'<div class="modal-body">'
                    +'<p onclick="window.location.href=\'\/\';">点击登录</p>'
                  +'</div>'
                  +'<div class="modal-footer">'
                    +'<button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>'
                  +'</div>'
                +'</div>'
              +'</div>'
            +'</div>'

            $("body").append(con);

            $("#sessionExpiredModalWindow").modal("show"); 

            $('#sessionExpiredModalWindow').on('hide.bs.modal', function () {
                $("#sessionExpiredModalWindow").remove();
            });

        },

        permissionDenied:function(){

            if(new RegExp('modal-backdrop').test($("#permissionDeniedModalWindow").next().attr("class"))){
                $("#permissionDeniedModalWindow").next().remove();
            }

            $("#permissionDeniedModalWindow").remove();

            var con = '<div id="permissionDeniedModalWindow" class="modal fade" tabindex="-1" role="dialog">'
              +'<div class="modal-dialog modal-dialog-centered" role="document">'
                +'<div class="modal-content">'
                  +'<div class="modal-header">'
                    +'<h5 class="modal-title"><label>Permission Denied</label></h5>'
                    +'<button type="button" class="close" data-dismiss="modal" aria-label="Close">'
                      +'<span aria-hidden="true">&times;</span>'
                    +'</button>'
                  +'</div>'
                  +'<div class="modal-body">'
                    +'<span onclick="window.location.href=\'/\';">首页</span>&nbsp;&nbsp;\\&nbsp;&nbsp;<span onclick="window.location.href=\'/signout\';">退出</span>'
                  +'</div>'
                  +'<div class="modal-footer">'
                    +'<button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>'
                  +'</div>'
                +'</div>'
              +'</div>'
            +'</div>'

            $("body").append(con);

            $("#permissionDeniedModalWindow").modal("show");  

            $('#permissionDeniedModalWindow').on('hide.bs.modal', function () {
                $("#permissionDeniedModalWindow").remove();
            });            

        },

        self:function(resp){

            if(resp.code == -1000){
                sessionExpired();
            }else if(resp.code == -1100){
                permissionDenied();
            }else{
                return true;
            }
            return false;

        }

    }

    hijklmn.ajax = function(options){

        var defaultOptions = {
            url : '',
            type : 'post',
            async : true,
            data : {},
            dataType : 'json',
            selfNotice : false
        }

        var newOptions = $.extend(defaultOptions,options)

        newOptions = $.extend(newOptions,{

            success:function(resp){
            	hijklmn.process.wait("hide");
                if(options.selfNotice){
                    if(hijklmn.notice.self(resp)){
                        if($.isFunction(options.backCall)){
                            options.backCall.call(this,resp);
                        }
                    }
                }else{
                    if($.isFunction(options.backCall)){
                        options.backCall.call(this,resp);
                    }                    
                }
            },

            error:function(){
            	hijklmn.process.wait("hide");
                hijklmn.notice.current("系统错误！");
            }
        });

        $.ajax(newOptions);

    }

    hijklmn.formData = {

        get:function(formId){

            var editForm = formId || "#editForm";            
            var dataArray = $(editForm).find("input,select,textarea,div").serializeArray();
            var tmpMap = new Map();
            var object = {};

            $.each(dataArray,function(i,item){
                if(item.value){
                    var tmpArray = tmpMap.get(item.name);
                    if(!tmpArray){
                        tmpArray = new Array();
                    }
                    tmpArray.push(item.value);
                    tmpMap.set(item.name,tmpArray);
                }
            });

            for(var [key,value] of tmpMap){
                object[key] = value.join();
            }

            return object;

        },

        fill:function(formId,data){

            data = data || {};

            $.each(data,function(id,value){                
                
                var ele = $(formId).find("[name='"+id+"']");               
                
                if(ele.length>0){
                    
                    var tagName = ele[0].tagName;
                    var type = $(ele[0]).attr("type");                                       
                    
                    if(tagName == "div" || tagName=="DIV" || tagName == "span" || tagName == "SPAN" ){                        
                        ele.text(value);
                    }else if(tagName == "select" || tagName=="SELECT"){                        
                        ele.val(value);
                    }else if(type == "radio" || type=="RADIO" || type == "checkbox" || type=="CHECKBOX" ){
                        var tmpArr = value.split(',');
                        var el = $("input[name='"+id+"']");
                        for(var item of el){
                            if(tmpArr.indexOf($(item).val()) > -1 ){
                                $(item)[0].checked = true;
                            }else{
                                $(item)[0].checked = false;
                            }
                        }
                    }else{                        
                        ele.val(value);
                    }                                   
                }
            });
        }
    }

})(window,(function() {
	if (window.hijklmn)
		return false;
	window.hijklmn = {};
	return window.hijklmn;
})());