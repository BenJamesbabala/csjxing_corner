!(function($){
	$.namespace("Dcome.Bops.Auction");
	
	var self = Dcome.Bops.Auction;
	
	$.extend(Dcome.Bops.Auction,{
		
		init:function(){
		    self._initUserInfo();
			self._initUpdateButton();
			//self._initKaijiangButton();
			self._initDelButton();
		},
		
		_initUserInfo: function() {
		    var userIds = [];
			var i = 0;
			$('#exchangeItemTable').find(".user-id").each(function() {
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
						$('#exchangeItemTable').find(".user-id").each(function() {
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
		/**
		 * ��ʼ�����°�ť
		 */
		_initUpdateButton:function(){
		  	$('#exchangeItemTable').find(".exchange-update-button").click(function(){
				var auctionTr = $(this).closest("tr"),
				exIntegral = auctionTr.find("[name='exIntegral']").val(),
				exCount = auctionTr.find("[name='exCount']").val(),
				exchangeId = auctionTr.data("exchange-id");
				itemType = auctionTr.find("[name='itemType']").val();
				if(isNaN(exIntegral) || isNaN(exCount)){
					alert("���������д����");
					return;
				}
				$.ajax({
					url : $('#ddzBopsRoot').val() + '/bops/dcome/qq/remote/update_exchange_item_ajax.htm',
					type : "POST" ,
					data : {"exIntegral":exIntegral, "exCount":exCount, "exchangeId":exchangeId, itemType: itemType},
					success : function(data) {
					    var json = data.json;
						if (json.code === "success") {
						    alert("���³ɹ�");
						} else {
						    alert("����ʧ�ܣ�����ϵ����Ա��");
						}
						window.location.href = window.location.href;
					},
					error: function(data) {
					    alert("update error");
					}
				});
			});
		}, 
		_initDelButton:function(){
		  	$('#exchangeItemTable').find(".exchange-del-button").click(function(e){
				if(!confirm("ȷ��ɾ����")) {
				    return;
				}
				var exchangeTr = $(this).closest("tr");
				var exchangeId = exchangeTr.data("exchange-id");
				if(exchangeId == undefined || isNaN(exchangeId)){
					alert("ɾ��ʧ�ܣ��Ҳ���exchangeId");
					return;
				}
				$.ajax({
					url :  $('#ddzBopsRoot').val() + '/bops/dcome/qq/remote/del_exchange_item_ajax.htm' ,
					type : "POST" ,
					data : {"exchangeId": exchangeId},
					success : function(data) {
					    var json = data.json;
						if (json.code === "success") {
						    alert("ɾ���ɹ�");
						    window.location.reload();
						} else {
						    alert("ɾ��ʧ�ܣ�����ϵ����Ա��");
						}
					},
					error: function(data) {
					    alert("error");
					}
				});
			});
		}, 
		end:0
	});
})(jQuery);

jQuery(document).ready(function(){
	Dcome.Bops.Auction.init();
});
