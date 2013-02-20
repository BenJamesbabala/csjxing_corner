$(function() {

	if (typeof (fusion2) != 'undefined') { // 接入QQ平台
		// 设置平台应用高度
		fusion2.canvas.setHeight({
			height : $(document).height() 
		});

		fusion2.canvas.setScroll({
			top : 0
		});

		fusion2.canvas.getClientRect({
			onSuccess : function(rect) {
				var i = 0 ;
			}
		});
		
		$(document).resize(function(){
		    var _footer = $(document).find(".footer");
		    if (_footer.length > 0) {
				fusion2.canvas.setHeight({
					height : _footer.offset().top + 20
				});
			} else {
			    fusion2.canvas.setHeight({
					height : $(document).height()
				});
			}
		});

		$(document).bind('setScroll' ,function(e,conf){
			var scrollTop = conf.scrollTop ;
			fusion2.canvas.setScroll({
				top : scrollTop - 100
			});
		});
		
		$(document).bind('relogin' , function(){
			fusion2.dialog.relogin() ;
		}) ;
		
	    $(document).bind("inviteFriend", function(e, config) {
			var img = config.img;
			if (img == undefined) {
			    img = 'http://y.photo.qq.com/img?s=5jMM7lLkZ&l=y.jpg';
			}
			fusion2.dialog.invite({
			    msg: config.msg,
				img: img,
				source: config.source,
				onSuccess: function (opt) {
				    config.onSuccess(opt);
				},
				onCancel: function (opt) {
				    
				},
				onClose: function(opt) {
				    config.onClose(opt);
				}
			});
		});
		
		$(document).bind("shareDcTo", function(e, config) {
		    var title = config.title;
			if (title == undefined || title == '') {
			    title = '《美丽帮你挑》——玩游戏，免费拿商品';
			}
			var imgUrl = config.imgUrl;
			if (imgUrl == undefined || imgUrl == '') {
			    imgUrl = "http://y.photo.qq.com/img?s=5jMM7lLkZ&l=y.jpg";
			}
		    fusion2.dialog.share ({
			    title: title,
				summary: config.summary,
				desc: config.msg,
				source: config.source,
				pics: imgUrl,
				onSuccess: function (opt) {
				    config.onSuccess(opt);
				},
				onCancel: function (opt) {
				    
				},
				onClose: function(opt) {
				    config.onClose(opt);
				}
			});
		}),
		
		$(document).bind("sendDcStory", function(e, config) {
		    var title = config.title;
			if (title == undefined) {
			    title = "《美丽帮你挑》——玩游戏，免费拿商品";
			}
			var imgUrl = config.imgUrl;
			if (imgUrl == undefined || imgUrl == '') {
			    imgUrl = "http://y.photo.qq.com/img?s=5jMM7lLkZ&l=y.jpg";
			}
			fusion2.dialog.sendStory ({
				title: title,
				summary: config.summary,
				msg: config.msg,
				img: imgUrl,
				button: "进入应用",
				source: config.source,
				onSuccess: function(opt) {
					config.onSuccess(opt);
				},
				onCancel: function(opt) {
				    
				},
				onClose: function(opt) {
					config.onClose(opt);
				}
			});
		});
		
		// 外链
		$("body").delegate("[taoke-href]" , 'click' ,function(e){
			
			e.preventDefault();
			
			var _this = $(this) ;
			
			var targetUrl = _this.attr('target-url') ;
			if(targetUrl != '' && targetUrl != undefined){
				fusion2.nav.open({
					url : targetUrl
				});
				return ;
			}
			
			var getUrl = _this.attr('taoke-href');
			
			var url = null ;
			
			$.ajax({
				url : getUrl ,
				type : "get" ,
				async : false ,
				data: {},
				success: function(data) {
					var json = data.json ;
					if(json == null){
						return ;
					}
					var code = json.code ;
					var data = json.data ;
					var detail = json.detail ;
					if (json.code == 'success') {
						url = json.data ;
						_this.attr('target-url',url) ;
					} else {
						alert("internal error.");
						return ;
					}
			        incUserBrowseCount(1);
				},
				error: function(e) {
					alert("internal error.");
					return ;
				}
			}) ;
			
			fusion2.nav.open({
				url : _this.attr('target-url')
			});
		}) ;
		

	} else { // 没有接入QQ
		// 外链
		$("a").each(function() {
			var url = $(this).attr('data-href');
			if (url != '') {
				$(this).attr('href', url);
				var target = $(this).attr('data-target');
				if (target != '') {
					$(this).attr('target', target);
				}
			}
		});
	}
	
	function incUserBrowseCount(count) {
	    var _browseNum = $('#iwish').find('.wish-egg .browse-num');
		if(_browseNum.length > 0) {
			var curNum =  isNaN(_browseNum.html()) ? 0: parseInt(_browseNum.html());
			curNum += count;
		    _browseNum.html(curNum);
		}
	}
});