
!(function(){
    var envRoot = $("#envRoot").val();
    var picUploadedRoot = $("#picUploadedRoot").val() ;
	var userId = $("#userId").val();
	var self ;
    var index = {
		init:function(){
			self = this ;
			self._initTrickEvent() ;
			self._initQzoneEvent();
			self._initSocialEvent();
			self._initIntelIpt() ;
			$(document).trigger('resize');
			if(userId == undefined || userId == '' || userId == null || parseInt(userId) < 10000){
				if($("#suppressLogin").val() != 'true') {
					setTimeout(function(){
						$(document).trigger('relogin');
					} ,3000);
				}
			}
		},
		
		errorIpt:function(text){
			$("#trickForm .error-area").find('span').html(text) ;
			$("#trickForm .error-area").removeClass('dd-hide') ;
		} ,
		
		/**
		 * 
		 */
		_initTrickEvent:function(){
			var form = $("#trickForm") ;
			form.find(".submit").click(function(){
				var trickInputName = form.find("input[name=trickInputName]").val() ;
				var trickInputTaName = form.find("input[name=trickInputTaName]").val() ;
				if(trickInputName == ''){
					self.errorIpt('请输入你的姓名。') ;
					return ;
				}
				if(trickInputTaName == ''){
					self.errorIpt('请输入TA的姓名。') ;
					return ;
				}
				
				var url = envRoot + '/frame/namefate/remote/namefate_trick_ajax.htm' ;
				
				var formSerialize = decodeURIComponent(form.serialize(),true) ;
				
				$.ajax({
					url : url ,
					type : "POST" ,
					data : encodeURI(encodeURI(formSerialize)),
					success : function(json){
						var code = json.code ;
						var data = json.data ;
						if(code == 'success') {
							window.location.href = envRoot + '/frame/namefate/trick_result.htm?trickId=' + data ;
						} else {
							if(code == 'ill_args'){
								var detail = json.detail ;
								if(detail == 'namefate.trick.user.required'){
									self.errorIpt('你还没有登录！');
								} else if(detail == 'namefate.trick.ipt.name.required'){
									self.errorIpt('请输入你的姓名。') ;
								} else if(detail == 'namefate.trick.ipt.taname.required') {
									self.errorIpt('请输入TA的姓名。') ;
								} else {
									self.errorIpt('服务器正在开小差，请稍后再试！');
								}
							} else {
								self.errorIpt('服务器正在开小差，请稍后再试！');
							}
						}
					} ,
					error : function(data){
						self.errorIpt('服务器正在开小差，请稍后再试！');
					}
				}) ;
			}) ;
		} ,
		
		/**
		 * 智能输入
		 */
		_initIntelIpt:function(){
			$('.intel-ipt').each(function(){
				var _this = $(this) ;
				var label = _this.attr('data-inte-label') ;
				
				_this.focus(function(){
					_this.addClass('focus') ;
				}) ;
				
				_this.blur(function(){
					_this.removeClass('focus') ;
				}) ;

			}) ;
		} ,
	
		_initQzoneEvent: function() {
			
			var weiboframe = '<iframe src="http://follow.v.t.qq.com/index.php?c=follow&a=quick&name=doucomewang&style=4&t=1357101668474&f=0" frameborder="0" scrolling="auto" width="125" height="27" marginwidth="0" marginheight="0" allowtransparency="true"></iframe>' ;
			var qzoneframe = '<iframe class="follow-qzone" src="http://open.qzone.qq.com/like?url=http%3A%2F%2Fuser.qzone.qq.com%2F10507600&type=button&width=57&height=23&style=3" allowtransparency="true" scrolling="no" border="0" frameborder="0" style="width:57px;height:23px;margin-top: 5px; margin-left: 40px;border:none;overflow:hidden;"></iframe>' ; 
			
		    //空间关注按钮
			$.ajax({
			    url: envRoot + "/frame/namefate/remote/is_qzone_fans_ajax.htm",
				type: "POST",
				data: {},
				success: function(result) {
				    var json = result.json;
					if (json.code == 'success') {
					    var data = json.data;
						if (!data.isFans) {//没有关注空间
						    $('body').append('<div class="qzone" id="qzone"><div class="label">关注空间赢神秘礼物</div><div class="follow">' + qzoneframe + '</div></div>');
							//
							$(".follow-qzone").load(function() {
							    $.ajax({
								    url: envRoot + "/frame/q/remote/rest/follow_qzone_ajax.htm",
									type: "POST",
									data: {},
									success: function(result) {
									    var json = result.json;
										if (json.code == 'success') {
										    var _qzone = $(".qzone");
											_qzone.remove();
										}
									},
									error: function() {
									    alert("错误");
									}
								})
							});
						} else {
							//已经关注空间，看有没关注微薄
							$('body').append('<div class="qzone" id="qzone"><div class="label">收听官方微博</div><div class="follow weibo">' + weiboframe + '</div></div>');
						}
					}
				},
				error: function() {
				}
			});
			
		},
				
		_initSocialEvent: function() {
			
			$(".pic-list li").hover(function(){
				$(this).append('<div class="download"></div>')
			} , function(){
				$(this).find('.download').remove() ;
			}) ;
			
			$(".pic-list li").click(function(){
				var _this = $(this);
				var name = _this.attr('data-name') ;
				
				var msg = '我正在下载歪脖子头像，快来这里找找属于你的歪脖子头像，和我一起拯救地球。这时节，不歪脖子一下，都好像对地球缺少贡献！' ;
				if (msg.length > 120) {
				    msg = msg.substring(0, 110) + '...';
				}
				
				var imgUrl = _this.find('img').attr('src') ;
				
				$(document).trigger('sendDcStory', {
					summary: '歪脖子拯救地球',
					msg: msg,
					source: '',
					imgUrl: imgUrl ,
					onSuccess: function(opt) {
						window.location.href = envRoot + '/frame/wryneck/wryneck_pic_download.htm?name=' + name ;
					},
					onCancel: function(opt) {
					},
					onClose: function(opt) {
					}
				});
			}) ;
			
			$(".invite-btn").click(function() {
			    var _this = $(this);
			    var _fateContent = _this.closest(".fate-detail").find(".fate-content");
				if (_fateContent.size() == 0) {
				     return ;
				}
				$(document).trigger("inviteFriend", {
				    msg: "我找到了我的歪脖子头像，你也快来找找！",
					source: '',
					onSuccess: function(opt) {
					    
					},
					onClose: function(opt) {
					
					}
				});
			});
			$(".weibo .close-btn").click(function() {
			
			});
		},
		
		end:0
	};

	$(function(){
		index.init();
	});
})();