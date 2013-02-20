var errorAvatarUrl = $("#picUploadedRoot").val() + "/avatar/0/0/0/0/0.jpg" ;

var defaultAvatar = function(size){
	alert(1) ;
}

!(function(){
	var envRoot = $("#envRoot").val() ;
		
	var self ;
	
	var index = {
				
		init:function(){
			self = this ;
			this._initEvent();
			this._initDetailEvent() ;
			this._initAddLove() ;
			this._initAddComment();
			this._initPlaceholder() ;
			this._initIndexBanner() ;
			this._initAvatar($('.init-avatar')) ;
			this._initGtrack();
		},
		
		/**
		 * autoplay:false,
				autoplayInitialDelay:2000,
				autoplayDuration:2000,
				autoplayPauseOnHover:true,
		 */
		_initIndexBanner:function(){
			var roundabout = $('#index-main ul').roundabout({
				autoplayInitialDelay:4000,
				autoplayDuration:4000,
				autoplayPauseOnHover:true
			});
			
			$('#index-main ul').bind('animationEnd',function(e){
				var focusIndex = $('#index-main ul').roundabout("getChildInFocus") ;
			});
			
		} ,

		_initEvent:function(){
			$('li','#content-list').hover(function(){
				$(this).addClass('hover');
			},function(){
				$(this).removeClass('hover');
			});
			
			$('dd','#cat-list').hover(function(){
				var _this = $(this) ;
				$('dd','#cat-list').each(function(){
					$(this).removeClass("on") ;
				});
				_this.addClass("on") ;
				
				var selCatId = _this.attr("data-cat-id") ;				
				$("#content-list ul").each(function(){
					$(this).addClass("dd-hide") ;
					if(selCatId == $(this).attr("data-cat-id")){
						$(this).removeClass("dd-hide") ;
					}
				});
				
			},function(){
				
			});
		} ,
		
		_initDetailEvent:function(){
			$('.detail-main .image-other li').bind('mouseenter',function(){
				$('#show-big-image').attr('src',$(this).data('src'));
			});
			$('#detailLike').hover(function(){
				$(this).removeClass("like") ;
				$(this).addClass("detail-like") ;
				$(this).find("a").addClass("dd-hide");
			},function(){
				$(this).removeClass("detail-like") ;
				$(this).addClass("like") ;
				$(this).find("a").removeClass("dd-hide");
			}) ;
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
				var top = parentElement.offset().top - 10 ;
				var left = parentElement.offset().left ;
				var bottom = top + $("#ilikeDialog").height() ;
				var right = left + $("#ilikeDialog").width() ;
				if(bottom > 1300){
					top = 1300 - $("#ilikeDialog").height() - 20 ;
				}
				if(right > 760){
					left = 760 -  $("#ilikeDialog").width() - 20 ;
				}
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
			
			var url = envRoot + "/frame/q/remote/rest/add_love_ajax.htm"
			
			$('.dc-op-ilike').click(function(){
				var _this = $(this) ;
				var itemId = _this.attr("data-item-id") ;
				$("#commentDialog").attr("data-item-id" , itemId) ;
				if(_this.attr("data-loved") == 'true'){ ////重复喜欢
					showLoveDialog(_this , '已添加喜欢') ;
					return ;
				}
				$.ajax({
		    		url : url ,
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
		
		/**
		 * 添加评论
		 */
		_initAddComment:function(){
			
			var _this = $("#commentDialog") ;
			
			var url = envRoot + "/frame/q/remote/rest/add_comment_ajax.htm"
			
			var error = function(text){
				_this.find(".info").html("") ;
				_this.find(".error").html(text);
			} ;
			
			var info = function(text){
				_this.find(".error").html('');
				_this.find(".info").html(text) ;
			} ;
			
			var lihtml = function(comment){
				var li = '<li class="dd-clr"><img alt="' + comment.userNick + '" data-src="' + $("#picUploadedRoot").val() + comment.userAvatar30x30 + '" class="avatar init-avatar" data-init-avatar="30x30" /><span class="list-content">' + comment.contentEsc + '</span></li>' ;
				return li ;
			}
			
			var commentLen = function(){
				return _this.find(".comment").val().length;
			} ;
			
			var maxlen = parseInt(_this.find(".comment").attr("data-maxlen")) ;
			
			$(".dc-op-comment").click(function(){
				var top = $("#ilikeDialog").offset().top ;
				var left = $("#ilikeDialog").offset().left;
				var bottom = top + _this.height() ;
				var right = left + _this.width() ;
				if(bottom > 1300){
					top = 1300 - _this.height() - 20 ;
				}
				if(right > 760){
					left = 760 -  _this.width() - 20 ;
				}
				_this.css("top" , top) ;
				_this.css("left" , left) ;
				_this.find(".comment").val('');
				$("#ilikeDialog").addClass("dd-hide") ;
				_this.removeClass("dd-hide") ;
				info("请输入评论内容");
				
			});
			 
			//关闭
			_this.find(".close").click(function(){
				_this.addClass("dd-hide") ;
			}) ;
			
			//提交评论
			_this.find(".submit").click(function(){
				var content = _this.find(".comment").val() ;
				
				if(content == '' || content == _this.find(".comment").attr('placeholder')){
					error("评论内容不能为空哦~") ;
					return ;
				}
				
				if(commentLen() > maxlen){
					return ;
				}
				
				var itemId = _this.attr("data-item-id") ;
				//发送请求
				$.ajax({
					url : url ,
					type : "POST" ,
					data : {content:encodeURI(content) , itemId:itemId},
					success : function(data){
						var json = data.json ;
						var code = json.code ;
						var data = json.data ;
						var detail = json.detail ;
						if(code == 'success') { 
							error('评论成功！');
							//隐藏对话框
							_this.delay(500).animate({opacity:0},500,function(){
						  		_this.addClass("dd-hide") ;
						  		_this.css("opacity",100) ;
						  	});
							//添加到当前页的评论列表中
							var retComment = data ; //处理过的评论内容
							if($(retComment).size() > 0){
								var li = lihtml(retComment) ; 
								var liList = $("#commentInfo").find(".comment-detail-list") ; 
								if(liList.find("li").size() > 4){
									liList.find("li:gt(3)").remove() ;
								}
								
								liList.prepend(li) ; //添加最新评论
								self._initAvatar($("#commentInfo").find('.init-avatar')) ;
							}
							
						
						} else {
							if(code == 'ill_args'){
								if(detail == 'dcome.addComment.content.required') {
									error('评论内容不能为空哦~！');
								} else if(detail == 'dcome.addComment.content.maxLength') {
									error('评论内容太长啦~！');
								} else if(detail == 'dcome.addComment.userId.required') {
									error('评论失败，请刷新页面') ;
								} else if(detail == 'dcome.addComment.itemId.required') {
									error('评论失败，请刷新页面') ;
								}
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
			
			
			/**
			 * 初始化评论分页
			 */
			
			//上一页
			var _thisPage = $("#commentPage") ;
			var pageSize = parseInt(_thisPage.attr("data-page-size")) ; 
			var totalRecords = parseInt(_thisPage.attr("data-comment-count")) ;
			var itemId = _thisPage.attr("data-item-id") ;
			var totalPages = (totalRecords + pageSize - 1) / pageSize ;
			
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
									var li = lihtml(comment) ;
									$("#commentInfo").find(".comment-detail-list").append(li) ;
								}
								self._initAvatar($("#commentInfo").find('.init-avatar')) ;
							}
							
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
				//end $.ajax({ 
			}) ;
			
			initCommentPages() ;
		} ,
		//end  初始化 评论
		
		
		
		/**
		 * placeholder
		 */
		_initPlaceholder:function(){
			$("[placeholder]").each(function(){
				var text = $(this).attr("placeholder");
				if(text != ''){
					$(this).val(text) ;
					$(this).addClass("placeholder") ;
				}
				$(this).on('focus',function(){
					if($(this).val() == text){
						$(this).removeClass("placeholder") ;
						$(this).val("");
					}
				});

				$(this).on('blur',function(){
					if($(this).val() === ""){
						$(this).val(text);
						$(this).addClass("placeholder") ;
					}
				});
			});
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
			
		} ,
		
		/**
		 * 我的活动页.
		 */
		__initMyPromotion: function {
			if ($("pageName") == "myPromotion") {
				$(".operation").click(function() {
					var _this = $(this);
			    	if (_this.hasClass("active")) {
			    		return ;
			    	}
			    	_this.parent().find(".active").removeClass("active");
			    	_this.addClass("active");
				});
			}
		},
		
		/**
		 * google track
		 */
		_initGtrack:function(){
			
			$("[gtrack]").bind('click',function(){
				var gtrack = $(this).attr('gtrack') ;
				if(gtrack != null){
					//alert(gtrack) ;
					if(typeof(_gaq) != 'undefined'){
						_gaq.push(['_trackPageview',gtrack]); 
					}
				}
			}) ;
						
		},
		
		end:0
	};
	
	

	$(function(){
		index.init();
	});
})();


