!(function($){
	$.namespace("Dcome.Bops");
	
	var self = Dcome.Bops;
	
	var popupStatus = 0;
	
	var picUrl = "";
	
	var envRoot = $('#ddzBopsRoot').val();
	
	$.extend(Dcome.Bops,{
		init:function(){
			$("#search-form").on("submit", function() {
				var itemUrl = $('#wd').val();
				if (itemUrl == '') {
					alert('请粘贴你要购买的淘宝宝贝网址') ;
					return false;
				}
				itemUrl = itemUrl.toLowerCase() ;
				var isTaobaoItemUrl = false  ;
				var itemIdArray = ['id','itemid','item_id','mallstitemid','default_item_id'] ;
				if(itemUrl.indexOf('taobao.com') != -1 || itemUrl.indexOf("tmall.com") != -1 ){
					
					for(var i = 0 ; i < itemIdArray.length ; i++){
						var id = itemIdArray[i] + '=';
						if(itemUrl.indexOf(id) != -1){
							isTaobaoItemUrl = true ;
							return true;
						}
					}
					
				}
				return false;
			});
			$(".operate-list li").mouseenter(function() {
			    $(this).parent().find("li").removeClass("active");
				$(this).addClass("active");
			});
			$(".operate-list li").mouseleave(function() {
			    $(this).parent().find("li").removeClass("active");
			});
			$("#pic-list li").mouseenter(picListMouseenter);
			$("#pic-modify").click(function() {
			   centerPopup();
			   loadPopup();
			});
			$("#pic-delete").click(function() {
			   var curLi = $("li[cur-pic='true']");
			   var temp = curLi.next(".pic-s60");
			   if (temp.length == 1) {
			       temp.trigger("mouseenter");
			   } else {
			       temp = curLi.prev("[cur-pic]");
				   if (temp.length == 1) {
				       temp.trigger("mouseenter");
				   } else {
				       $("#cur-pic").attr("src", "#");
				   }
			   }
			   curLi.remove();
			});
			$("#pic-add").click(function() {
			    centerPopup();
			    loadPopup();
				$("li[cur-pic='true']").attr("cur-pic", "false");
				$("#pic-add").before('<li class="pic-s60 fd-hide" cur-pic="true">' +
						'<a href="#"></a>' +
						'<input type="hidden" name="picUrlList" value="" /></li>');
			    $("#pic-list li").mouseenter(picListMouseenter);
				$("#pic-list .pic-s60").click(picS60Click);
			});
			$(".pic-s60").click(picS60Click);
			$("#picUploadForm").on("submit", function() {
			    $("#picUploadForm").ajaxSubmit(function(message) {
				    var result = message.result;
					if (result.code=="success") {
					    var picRoot = $("#dcItemPicRoot").val();
					    var picUrl = result.data;
						$("#cur-pic").attr("src", picRoot + picUrl);
						$("#pic-list li").removeClass("active");
						$("#pic-list li").removeClass("fd-hide");
						$("li[cur-pic='true']").addClass("active");
						$("li[cur-pic='true']").find("input").val(picUrl);
						$(".error-info").addClass('fd-hide');
						disablePopup();
					} else {
						$("#upload-info").text(result.detail);
						$(".error-info").removeClass('fd-hide');
					}
				});
				return false;
			});
			$("#pic-cancel").click(function() {
				$(".error-info").addClass('fd-hide');
			    disablePopup();
				var temp = $(".pic-s60:last")
				if (temp.hasClass("fd-hide")) {
				    temp.remove();
				}
			});
			var taokeAjaxUrl = envRoot + "/bops/dcome/qq/remote/reset_taoke_url_ajax.htm";
			$(".item-taoke-url").find("a").click(function() {
			    var resetInfo = $('.item-taoke-url .click-info');
			    resetInfo.html("");
				resetInfo.addClass("fd-hide");
			    var itemUrl = $(".item-src-url").find("input").val();
				if (itemUrl == undefined) {
				    resetInfo.html("获取商品链接失败，无法生成淘客链接。");
					resetInfo.removeClass("fd-hide");
					return ;
				}
				$.ajax({
				    url: taokeAjaxUrl,
					type: "POST",
					data: {"itemUrl": itemUrl},
					success: function(data) {
					    var json = data.json;
						if (json.code == 'success') {
						    $('.item-taoke-url').find(".url").attr("href", json.data);
						    $('.item-taoke-url').find(".url").html(json.data);
							$('.item-taoke-url').find("input").val(json.data);
							resetInfo.html("操作成功");
							resetInfo.removeClass("fd-hide");
						} else {
						    resetInfo.html('重新生存淘客链接失败');
							resetInfo.removeClass("fd-hide");
						}
					},
					error: function(data) {
					    alert('服务器错误');
					}
				});
			});
			$("#item-form").on("submit", function() {
			    if ($('#itemTitle').val().length > 120) {
				    alert("标题太长，请限制在120字以内");
					return false;
				}
				if ($('#itemDesc').val().length > 255) {
				    alert("商品描述太长，请限制在255字以内。")
					return false;
				}
				var numberRe = /^[0-9]+.?[0-9]*$/;
				if (!numberRe.test($("#itemPrice").val())) {
				    alert("商品价格应为数字")
					return false;
				}
				return true;
			});
		}
	});
	function picListMouseenter() {
		$(this).parent().find("li").removeClass("active");
		$(this).addClass("active");
		if ($(this).hasClass("pic-s60")) {
			$(this).parent().find("li").attr("cur-pic", "false");	
		    $(this).attr("cur-pic", "true");
			var picUrl = $(this).find("input").val();
			if (picUrl.indexOf("http://") == -1) {
			   picUrl = $("#dcItemPicRoot").val() + picUrl;
			}
			$("#cur-pic").attr("src", picUrl);
		}
		
	};
	
	function picS60Click() {
	    centerPopup();
	    loadPopup();
	}
	
	function centerPopup(){
		//request data for centering
		var windowWidth = document.documentElement.clientWidth;
		var windowHeight = document.documentElement.clientHeight;
		var popupHeight = $("#upload-pic-div").height();
		var popupWidth = $("#upload-pic-div").width();
		//centering
		$("#upload-pic-div").css({
			"position": "absolute",
			"top": windowHeight/3-popupHeight/2,
			"left": windowWidth/2-popupWidth/2
		});	
	};
	function loadPopup(){
		//loads popup only if it is disabled
		if(popupStatus==0){
			$("#backgroundPopup").css({
				"opacity": "0.7"
			});
			$("#backgroundPopup").fadeIn("slow");
			$("#upload-pic-div").fadeIn("slow");
			popupStatus = 1;
		}
	};
	function disablePopup(){
		//disables popup only if it is enabled
		if(popupStatus==1){
			$("#backgroundPopup").fadeOut("slow");
			$("#upload-pic-div").fadeOut("slow");
			popupStatus = 0;
		}
	};
})(jQuery);

jQuery(document).ready(function(){
	Dcome.Bops.init();
});
