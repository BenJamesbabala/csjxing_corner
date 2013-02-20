
!(function(){
    var envRoot = $("#envRoot").val();
	var userId = $("#userId").val();
    var index = {
		init:function(){
		    this._initUserEvent();
			this._initSocialEvent();
			$(document).trigger('resize');
			if(userId == undefined || userId == '' || userId == null || parseInt(userId) < 10000){
				setTimeout(function(){
					$(document).trigger('relogin');
				} ,3000);
			}
		},
		
		_initUserEvent: function() {
		    $.ajax({
			    url: envRoot + "/frame/hs/remote/qzone_fans_ajax.htm",
				type: "post",
				data: {},
				success: function(result) {
				    var json = result.json;
					$(".qzone-btn").remove();
					/**
					if (json.code == 'success') {
					    if (json.data == true) {
						    $(".qzone-btn").remove();
						} else {
							var _mainBody = $(".main-body");
							_mainBody.append('<div class="qzone dd-r" id="qzone"><a class="close-btn" href="javascript:"></a><iframe class="qzone-btn" src="http://open.qzone.qq.com/like?url=http%3A%2F%2Fuser.qzone.qq.com%2F10507600&type=button&width=65&height=40&style=3" allowtransparency="true" scrolling="no" border="0" frameborder="0"></iframe></div>');
							var _qzone = $(".qzone");
							_qzone.find(".close-btn").click(function() {
								_qzone.hide();
							});
							$(".qzone-btn").load(function() {
								$.ajax({
									url: envRoot + "/frame/hs/remote/follow_qzone_ajax.htm",
									type: "post",
									data: {},
									success: function(result) {
										var json = result.json;
										if (json.code == 'success') {
											_qzone.remove();
										}
									},
									error: function() {
									
									}
								});
							});
						}
					} else {
					    if (json.detail == 'no.login') {
						    $(document).trigger('relogin');
						} else {
						    
						}
					}**/
				},
				error: function() {
				
				}
			})
		},
		
		_initSocialEvent: function() {
		    $(".fate-list").find("li .text").hover(function() {
			    var _this = $(this);
				_this.find(".toolbar").show();
			}, function() {
			    var _this = $(this);
				_this.find(".toolbar").hide();
			});
			$(".fate-detail .share-btn").click(function() {
			    var _this = $(this);
			    var _fateContent = _this.closest(".fate-detail").find(".fate-content");
				if (_fateContent.size() == 0) {
				     return ;
				}
				var msg = _fateContent.html();
				if (msg.length > 120) {
				    msg = msg.substring(0, 110) + '...';
				}
				$(document).trigger('sendDcStory', {
					summary: '',
					msg: msg,
					source: '',
					imgUrl: '',
					onSuccess: function(opt) {
						
					},
					onCancel: function(opt) {
					},
					onClose: function(opt) {
					}
				});
			});
			$(".topic .share-btn").click(function() {
			    var _this = $(this);
				var _topic = _this.closest(".topic");
				var text = _topic.find(".text").html();
				if (text.length > 120) {
				    text = text.subString(0, 110) + '...';
				}
				var _picture = _topic.find(".picture");
				var imgUrl;
				if (_picture.size() > 0) {
				    imgUrl = _picture.attr("data-share-pic");
				}
				$(document).trigger("sendDcStory", {
				    summary: '',
					msg: text,
					source: '',
					imgUrl: imgUrl,
					onSuccess: function(opt) {
						
					},
					onCancel: function(opt) {
					},
					onClose: function(opt) {
					}
				})
			});
			$(".invite-btn").click(function() {
			    var _this = $(this);
				/**
			    var _fateContent = _this.closest(".fate-detail").find(".fate-content");
				if (_fateContent.size() == 0) {
				     return ;
				}**/
				$(document).trigger("inviteFriend", {
				    msg: "我正在看我的专属星座哎，你也快来瞧瞧...",
					source: '',
					onSuccess: function(opt) {
					    
					},
					onClose: function(opt) {
					
					}
				});
			});
		},
		
		end:0
	};

	$(function(){
		index.init();
	});
})();