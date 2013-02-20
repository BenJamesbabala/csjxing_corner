!(function($){
	$.namespace("Dcome.Bops.SearchLog");
	
	var self = Dcome.Bops.SearchLog;
	var bopsRoot = $('#ddzBopsRoot').val();
	
	$.extend(Dcome.Bops.SearchLog,{
		init:function(){
		    self.initUserInfo();
		    self.initItemInfo();
			self.initBtnEvent();
		},
		
		initUserInfo: function() {
		    var userIds = [];
			var i = 0;
			$('#searchLogTable').find(".user-id").each(function() {
			    var _this = $(this);
				var userId = _this.attr("data-userid");
				if (userId != undefined && !isNaN(userId) && userId != '1') {
				    userIds[i] = userId;
					i++;
				}
			});
			if (userIds.length == 0) {
				return;
			}
			$.ajax({
				url: $('#ddzBopsRoot').val() + '/bops/dcome/qq/remote/query_user_info_ajax.htm',
				type: "post",
				data: {userIds: userIds.join(), includeComm: false},
				async: false,
				success: function(result) {
					var json = result.json;
					if (json.success) {
						var data = json.data;
						$('#searchLogTable').find(".user-id").each(function() {
							var _this = $(this);
							var _td = _this.closest("td");
							var userId = _this.attr("data-userid");
							if (_td.find(".user-info").size() == 0) {
								for (var i = 0; i < data.length; i++) {
									if(data[i].userId == userId) {
										_td.append('<div class="user-info">昵称:' + data[i].userNick + "</div>");
										_td.append('<div class="user-info">积分:' + data[i].integralCount + "</div>");
										_td.append('<div class="user-info">注册:' + data[i].gmtCreateFmt + "</div>");
										return;
									}
								}
							}
						});
					}
					
				},
				error: function() {
				
				}
			});
		},
		
		initItemInfo: function() {
		    var nativeIds = [];
			var i = 0;
		    $("#searchLogTable").find("tr").each(function() {
			     var _this = $(this);
				 var nativeId = _this.attr("data-native-id");
				 if (nativeId == undefined || isNaN(nativeId) || _this.find(".item-title").html() != '') {
				     return;
				 }
				nativeIds[i] = nativeId;
				i++;
			});
			if (nativeIds.length == 0) {
			    return;
			}
			$.ajax({
			    url: bopsRoot + '/bops/dcome/qq/remote/prom/query_item_from_tb_ajax.htm',
				type: "post",
				data: {nativeIds: nativeIds.join()},
				success: function(result) {
				    var json = result.json;
					if (json.success) {
					    var data = json.data;
						$("#searchLogTable").find("tr").each(function() {
						    var _this = $(this);
							 var nativeId = _this.attr("data-native-id");
							 if (nativeId == undefined || isNaN(nativeId)) {
								 return;
							 }
							 for (var i = 0; i < data.length; i++) {
							     if (nativeId == data[i].nativeId) {
								     _this.find(".item-title").html(data[i].itemTitle);
									 if (_this.find(".picture img").size() == 0) {
										_this.find(".picture").append('<img src="' + data[i].picUrl80x80 + '" width="60" height="60" />');
									 }
									 if (_this.find(".com-rate").html() == '') {
									     _this.find(".com-rate").html(data[i].commissionRate);
									 }
									 return;;
								 }
							 }
						});
					} else {
					    alert("获取商品信息出错");
					}
				},
				error: function() {
				    alert("获取商品信息出错");
				}
			});
		},
		
		initBtnEvent: function() {
		    $("#searchLogTable").find(".add-exchange").click(function() {
			    if(!confirm("确认添加？")) {
				    return;
				}
			    var _this = $(this);
				var _tr = _this.closest("tr");
				var nativeId = _this.attr("data-native-id");
				var userId = _this.attr("data-user-id");
				var price = _tr.find(".price").val();
				var postalFee = _tr.find(".postal-fee").val();
				var itemType = _tr.find("[name='itemType']").val();
				if (nativeId == undefined || isNaN(nativeId) || userId == undefined || isNaN(userId)) {
				    alert("数据有误，请检查");
					return;
				}
				$.ajax({
				    url: bopsRoot + '/bops/dcome/qq/remote/prom/add_ugc_to_exchange_ajax.htm',
					type: "post",
					data: {nativeIds: nativeId, userId: userId, price: price, postalFee: postalFee, itemType: itemType},
					success: function(result) {
					    var json = result.json;
						if (json.success) {
						    window.location.href = window.location.href;
						} else {
						    var detail = json.detail;
							if (detail == 'userid.invalid') {
							    alert("userId有误，请检查");
							} else if (detail == 'url.invalid') {
							    alert("商品链接有误，请检查");
							} else if (detail == 'item.not.exist') {
							    alert("商品不存在，请检查");
							} else if (detail == 'internal.error') {
							    alert("服务器内部错误，请稍后再试");
							} else {
							    alert("未知错误");
							}
							window.location.href = window.location.href;
						}
						
					},
					error: function() {
					    alert("添加错误");
					}
				});
			});
		},
		
		end:0
	});
})(jQuery);

jQuery(document).ready(function(){
	Dcome.Bops.SearchLog.init();
});
