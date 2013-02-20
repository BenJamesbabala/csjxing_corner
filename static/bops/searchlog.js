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
										_td.append('<div class="user-info">�ǳ�:' + data[i].userNick + "</div>");
										_td.append('<div class="user-info">����:' + data[i].integralCount + "</div>");
										_td.append('<div class="user-info">ע��:' + data[i].gmtCreateFmt + "</div>");
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
					    alert("��ȡ��Ʒ��Ϣ����");
					}
				},
				error: function() {
				    alert("��ȡ��Ʒ��Ϣ����");
				}
			});
		},
		
		initBtnEvent: function() {
		    $("#searchLogTable").find(".add-exchange").click(function() {
			    if(!confirm("ȷ����ӣ�")) {
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
				    alert("������������");
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
							    alert("userId��������");
							} else if (detail == 'url.invalid') {
							    alert("��Ʒ������������");
							} else if (detail == 'item.not.exist') {
							    alert("��Ʒ�����ڣ�����");
							} else if (detail == 'internal.error') {
							    alert("�������ڲ��������Ժ�����");
							} else {
							    alert("δ֪����");
							}
							window.location.href = window.location.href;
						}
						
					},
					error: function() {
					    alert("��Ӵ���");
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
