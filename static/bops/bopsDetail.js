var errorAvatarUrl = $("#picUploadedRoot").val() + "/avatar/0/0/0/0/0.jpg" ;

!(function(){
    var envRoot = $("#envRoot").val() ;
	var index = {
	    init: function() {
		    self = this;
			this._initAddLove();
			this._initAddComment();
			this._initImageSlide();
			this._initAvatar($('.init-avatar'));
		},
		
		/**
		 * 添加喜欢
		 */
		_initAddLove:function(){
			
			var dlgTimer = null ;
			/**
			 * 显示喜欢对话框
			 */
			var showLoveDialog = function(parentElement , content){
				if(dlgTimer != null){ //上次的时钟还在
					 clearTimeout(dlgTimer) ;
					 $("#ilikeDialog").addClass("dd-hide") ;
				}
				var top = parentElement.offset().top - 8 ;
				var left = parentElement.offset().left ;
				$("#ilikeDialog").css("top" , top) ;
				$("#ilikeDialog").css("left" , left) ;
				$("#ilikeDialog").find(".content").html(content) ;
				$("#ilikeDialog").removeClass("dd-hide") ;
				$("#commentDialog").addClass("dd-hide") ;
				dlgTimer = setTimeout(function(){
					
					$("#ilikeDialog").delay(300).animate({opacity:0},500,function(){
						$(this).addClass("dd-hide") ;
						$(this).css("opacity",100) ;
				  	});
					
				} , 2000) ;
			} ;
			
			var loveUrl = envRoot + "/frame/q/remote/rest/add_love_ajax.htm"
			
			$('.dc-op-ilike').click(function(){
				var _this = $(this) ;
				var itemId = _this.attr("data-item-id") ;
				$.ajax({
		    		url : loveUrl ,
					type : "POST" ,
					data : {itemId:itemId},
					success : function(data){
						var json = data.json ;
						var code = json.code ;
						var data = json.data ;
						var detail = json.detail ;
						if(code == 'success') { 
							_this.attr('data-loved',"true") ;
							showLoveDialog(_this , '已添加喜欢') ; 
							var curCount = parseInt(_this.find(".loveCount").html()) ;
							_this.find(".loveCount").html(curCount+1) ; 
						} else {
							if(code == 'ill_args'){
								if(detail == 'dcome.addLove.itemId.duplicate') { //重复喜欢
									_this.attr('data-loved',"true") ;
									showLoveDialog(_this , '已添加喜欢') ;
									var curCount = parseInt(_this.find(".loveCount").html()) ;
								} else if(detail == 'dcome.addLove.itemId.required') {
									showLoveDialog(_this , '添加喜欢失败，请刷新页面') ;
								} else if(detail == 'dcome.addLove.userId.required') {
									showLoveDialog(_this , '添加喜欢失败，请刷新页面') ;
								}
							} else {
								showLoveDialog(_this , '添加喜欢失败，请刷新页面') ;
							}
						}
					} ,
					error : function(data){
						showLoveDialog(_this , '添加喜欢失败，请刷新页面') ;
					}
				});
			});
		} ,
		
		 _initAddComment: function() {
		    var _this = $("#commentDialog") ;
		    var info = function(text){
				_this.find(".error").html('');
				_this.find(".info").html(text) ;
			} ;
			var error = function(text){
				_this.find(".info").html("") ;
				_this.find(".error").html(text);
			} ;
			var commentLen = function(){
				return _this.find(".comment").val().length;
			} ;
			
			/**
			 * 初始化评论分页
			 */
			
			//上一页
			var _thisPage = $("#commentPage") ;
			var pageSize = parseInt(_thisPage.attr("data-page-size")) ; 
			var totalRecords = parseInt(_thisPage.attr("data-comment-count")) ;
			var itemId = _thisPage.attr("data-item-id") ;
			var totalPages = (totalRecords + pageSize - 1) / pageSize ;
			
			var disCommentUrl = envRoot + "/bops/dcome/qq/remote/set_comment_status_ajax.htm"
			
			var disableComment = function() {
			    var commentSelf = $(this);
			    var commentId = commentSelf.attr("data-comment-id");
				$.ajax({
					url : disCommentUrl ,
					type : "POST" ,
					data : {"commentId": commentId, "status": "D"},
					success : function(data){
					    var json = data.json;
						if (json.code == 'success') {
							commentSelf.parent().remove();
							totalRecords = totalRecords - 1;
							totalPages = (totalRecords + pageSize - 1) / pageSize ;
							var commentCount = $('.detail-other .comment');
							commentCount.html(parseInt(commentCount.html()) - 1);
						} else {
						    alert('禁止评论失败');
						}
					},
					error: function(data) {
					
					}
                });					
			};
			
			var initCommentPages = function(){
				var curPage = _thisPage.attr("data-cur-page"); 
				$("#commentPage").find(".comment-op-page").each(function(){
					var type = $(this).attr("data-type") ;
					if(type == 'pre'){
						if(curPage <= 1){
							$(this).addClass("dd-hide") ;
						}else{
							$(this).removeClass("dd-hide") ;
						}
					}
					if(type == 'back'){
						if(curPage >= totalPages){
							$(this).addClass("dd-hide") ;
						}else{
							$(this).removeClass("dd-hide") ;
						}
					}
				});
			}
			
			$(".comment-op-page").click(function(){
				var curPage = parseInt(_thisPage.attr("data-cur-page")); 
				var type = $(this).attr("data-type") ;
				var toPage = 0 ;
				if(type == 'pre'){
					toPage = curPage - 1 ;
				}else{
					toPage = curPage + 1;
				}
				
				if(toPage < 1 || toPage > totalPages){
					$(this).addClass("dd-hide") ;
					return ;
				}
				
				var url = envRoot + "/frame/q/remote/rest/query_comments_ajax.htm"
				
				//请求评论
				$.ajax({
					url : url ,
					type : "POST" ,
					data : {itemId:itemId,page:toPage},
					success : function(data){
						var json = data.json ;
						var code = json.code ;
						var data = json.data ;
						var detail = json.detail ;
						if(code == 'success') { 
							if(data.length > 0){
								$("#commentInfo").find(".comment-detail-list").html('');
								for(var i=0 ;i<data.length;i++){
									var comment = data[i] ;
									//<li class="dd-clr"><img alt="$!comment.userNick" src="$avatarUtils.getAvatarUrl($!comment.userId,'30x30')" class="avatar"/><span>$!comment.userNick:$stringUtils.substring($!comment.content,0,18)</span></li>
									var li = '<li class="dd-clr"><img alt="' + comment.userNick + 
										  '" data-src="' + $("#picUploadedRoot").val() + comment.userAvatar30x30 + 
										  '" class="avatar init-avatar" data-init-avatar="30x30" /><span class="list-content" style="width: 240px;">' + 
										  comment.userNick + ':' + comment.contentEsc + 
										  '</span><a href="javascript:;" title="禁止"  data-comment-id="' + data[i].id + '">禁止</a></li>' ;
									$("#commentInfo").find(".comment-detail-list").append(li) ;
								}
								self._initAvatar($("#commentInfo").find('.init-avatar')) ;
							}
							$('.comment-detail-list').find("a").click(disableComment);
							_thisPage.attr("data-cur-page" , toPage); 
							initCommentPages() ;
						} else {
							alert('获取评论失败！');
						}
					} ,
					error : function(data){
						alert('获取评论失败！');
					}
				}) ;
			}) ;
			
			initCommentPages();
			
			$('.comment-detail-list').find("a").click(disableComment);
			
			var maxlen = parseInt(_this.find(".comment").attr("data-maxlen")) ;
			
			var appendTbCommentFailInfo = function(message) {
				$(".taobao-comment").find("table").append('<tr><td class="content">' 
	    				+ message + '</td><td class="del-opt"></td></tr>');
			}
			
			var resetCommentDivHeight = function() {
				var tempHeight = $(".title").height() + $(".comment-category-title").height()
				  + $(".dcome-comment").height() + $(".comment-category-title").height()
				  + $(".taobao-comment").height() + $(".submit").height() + 60;
				_this.height(tempHeight);
			}
			
			var delTbComment = function() {
				var tempTr = $(this).parent().parent();
				tempTr.remove();
				resetCommentDivHeight();
			}
			
		    $(".dc-comment-add").click(function() {
		        var top = $(".detail-main").offset().top ;
			    var left = $(".dc-comment-add").offset().left;
			    var bottom = top + _this.height();
				var right = left + _this.width();
				if (bottom > 1300) {
				    top = 1300 - _this.height() - 20;
				}
				if (right > 760) {
				    left = 760 - _this.width() - 20;
				}
			    _this.css("top" , top) ;
			    _this.css("left" , left) ;
			    _this.find(".comment").val('');
			    $("#ilikeDialog").addClass("dd-hide") ;
			    _this.removeClass("dd-hide") ;
			    info("请输入评论内容");
			    
			    //请求淘宝评论
			    $(".taobao-comment table").find("tr").remove();
			    var taobaoCommentsUrl = envRoot + "/bops/dcome/qq/remote/feed_item_comments_ajax.htm"
			    var commentDiv = _this.find(".taobao-comment");
			    var nativeItemId = commentDiv.attr("data-native-id"), itemSource = commentDiv.attr("data-item-source"),
			         sellerUserId = commentDiv.attr("data-seller-id");
			    $.ajax({
			    	url : taobaoCommentsUrl ,
					type : "POST" ,
					data : {"nativeItemId": nativeItemId, "sellerUserId": sellerUserId, "itemSource": itemSource},
			    	success: function(data) {
			    	    var result = data.result;
			    	    if (result.code == "success") {
			    	    	var comments = result.data;
			    	    	if (comments.length > 0) {
			    	    		for (var i = 0; i < comments.length && i < 10; i++) {
			    	    			$(".taobao-comment").find("table").append('<tr><td class="content">' 
			    	    				+ comments[i].content + '</td><td class="del-opt"><a>删除</a></td></tr>');
			    	    		}
			    	    	} else {
			    	    		$(".taobao-comment").find("table").append('<tr><td>商品没有评论数据.</td><td></td></tr>');
			    	    	}
			    	    } else {
			    	    	if (result.detail == "invalid.request.param") {
			    	    		appendTbCommentFailInfo("加载淘宝评论数据失败,无法识别的请求参数");
			    	    	} else if (result.detail == "request.comment.failed") {
			    	    		appendTbCommentFailInfo("加载淘宝评论数据失败，请稍后重试");
			    	    	} else {
			    	    		appendTbCommentFailInfo("加载淘宝评论数据失败,服务端未知错误");
			    	    	}
			    	    }
			    	    //绑定删除事件
			    	    $(".taobao-comment .del-opt").find("a").click(delTbComment);
			    	    resetCommentDivHeight();
			    	},
			    	error: function(data) {
			    		appendTbCommentFailInfo("加载淘宝评论数据失败");
			    	}
			    });
		    }); 
            //关闭
			_this.find(".close").click(function(){
				_this.addClass("dd-hide") ;
			}) ;
			var commentUrl = envRoot + '/bops/dcome/qq/remote/add_bops_comment_ajax.htm';
			//提交评论
			_this.find(".submit").click(function(){
				var content = [];
				var dcContent = _this.find(".comment").val();
				content.push(dcContent);
				$(".taobao-comment").find(".content").each(function() {
					content.push($(this).html());
				});
				content = content.join(";");
				var itemId = $('.dc-op-ilike').attr("data-item-id");
				//发送请求
				$.ajax({
					url : commentUrl ,
					type : "POST" ,
					data : {content:encodeURI(content) , itemId:itemId},
					success : function(data){
						var json = data.comments ;
						var code = json.code ;
						var data = json.data ;
						if(code == 'success') { 
							error('评论成功！');
							if(data.length == 0) {
							    return;
							}
							var commentCount = $('.detail-other .comment');
							commentCount.html(parseInt(commentCount.html()) + data.length);
							for (var i = 0; i < data.length; i++) {
								$('#commentInfo .comment-detail-list').prepend(
								  '<li class="dd-clr">' +
									'<img alt="' + data[i].userNick + '" data-src="http://doucome-img-test.b0.upaiyun.com/avatar/1/0/0/0/10003.jpg_30x30.jpg"' +
										'class="avatar init-avatar" data-init-avatar="30x30" ' +
										'src="http://doucome-img-test.b0.upaiyun.com/avatar/0/0/0/0/0.jpg_30x30">' +
									'<span class="list-content" style="width: 240px;">' + data[i].userNick + ':' + data[i].contentEsc + '</span>' +
									'<a href="javascript:;" title="禁止"  data-comment-id="' + data[i].id +'">禁止</a>' +
								   '</li>');
							}
							$('.comment-detail-list').find("a").click(disableComment);
							//去掉超出页面的评论
							var exp = '#commentInfo .comment-detail-list li:gt(' + (pageSize - 1) + ')';
							$(exp).remove();
							//重新计算分页数据
							totalRecords = totalRecords + data.length;
							totalPages = (totalRecords + pageSize - 1) / pageSize ;
							//隐藏对话框
							_this.delay(500).animate({opacity:0},500,function(){
						  		_this.addClass("dd-hide") ;
						  		_this.css("opacity",100) ;
						  	});
						} else {
							var detail = json.detail;
							if(detail == 'content.required') {
								error('评论内容不能为空！');
							} else if(detail == 'server.error') {
								error('服务器内部错误！');
							} else if(detail == 'itemId.required') {
								error('评论失败，请刷新页面') ;
							} else {
								error('评论失败，请刷新页面') ;
							}
						}
					} ,
					error : function(data){
						error('评论失败，请刷新页面') ;
					}
				}) ;
			});
			
			_this.find(".comment").focus(function(){
				if(_this.find(".comment").val() == ''){
					info("请输入评论内容") ;
				}
			}) ;
			
			
			_this.keyup(function(){
				var content = _this.find(".comment").val() ;
				var len = commentLen() ;
				var canInput = maxlen - len ;
				if(canInput >= 0){
					info("还可以输入" + canInput + "字");
				}else{
					error("已超出" + -canInput + "字");
				}
			}) ;			
		},
		/**
		 * 头像加载不到时防止stack overflow
		 */
		_initAvatar:function(selector){
			selector.each(function(){
				$(this).error(function(){
					var _this = $(this) ;
					if(_this.is("img")){
						var size = _this.attr("data-init-avatar") ;
						if(size != ''){
							_this.attr("src",errorAvatarUrl + "_" + size) ;
						}else{
							_this.attr("src",errorAvatarUrl) ;
						}
						_this.unbind("error") ;
					}
				}) ;
				$(this).attr('src',$(this).attr('data-src')) ;
			}) ;
			
		},
		_initImageSlide: function() {
		    $('.detail-main .image-other li').bind('mouseenter',function(){
				$('#show-big-image').attr('src',$(this).data('src'));
			});
		},
		
		end: 0
	}
	
	$(function(){
		index.init();
	});
})();
