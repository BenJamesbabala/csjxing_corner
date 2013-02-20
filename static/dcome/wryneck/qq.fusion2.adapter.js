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
		
		fusion2.iface.updateClientRect(function(rect){
			$(".qzone").css({"top": rect.top + 80});
		}) ;
		
		$(document).resize(function(){
		    var _footer = $(document).find(".foot");
		    if (_footer.length > 0) {
				fusion2.canvas.setHeight({
					height : _footer.offset().top + 30
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
			    img = 'http://b251.photo.store.qq.com/psb?/V11PV0YF03rbId/kyGTytFmE1KfcVhLuyZbRQEFvqlbaoAiM8BOeRDIPds!/b/dAClnpXxEQAA&bo=yADIAAAAAAADACU!';
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
		
		$(document).bind("sendDcStory", function(e, config) {
		    var title = config.title;
			if (title == undefined) {
			    title = "《歪脖子》——快来制作你的歪脖子头像";
			}
			var imgUrl = config.imgUrl;
			if (imgUrl == undefined || imgUrl == '') {
			    imgUrl = "http://b251.photo.store.qq.com/psb?/V11PV0YF03rbId/kyGTytFmE1KfcVhLuyZbRQEFvqlbaoAiM8BOeRDIPds!/b/dAClnpXxEQAA&bo=yADIAAAAAAADACU!";
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
		
		
	} else { // 没有接入QQ
		alert("请从qq空间或朋友网登陆！！！");
	}
});