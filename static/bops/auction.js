!(function($){
	$.namespace("Dcome.Bops.Auction");
	
	var self = Dcome.Bops.Auction;
	
	$.extend(Dcome.Bops.Auction,{
		
		init:function(){
			self._initUpdateButton();
			self._initKaijiangButton();
		},
		/**
		 * 初始化更新按钮
		 */
		_initUpdateButton:function(){
		  	$('#auctionItemTable').delegate(".auction-update-button", "click", function(){
				var auctionTr = $(this).closest("tr"),
				baseIntegral = auctionTr.find("[name='baseIntegral']").val(),
				integralPer = auctionTr.find("[name='integralPer']").val(),
				gmtStart = auctionTr.find("[name='gmtStart']").val(),
				gmtEnd = auctionTr.find("[name='gmtEnd']").val(),
				auctionid = auctionTr.data("auctionid"),
				param,url;
				if(baseIntegral===""||integralPer===""||gmtStart===""||gmtEnd===""||auctionid===""){
					alert("请把数据填写完整");
					return;
				}
				url = $('#ddzBopsRoot').val() + '/bops/dcome/qq/remote/update_auction_item_ajax.htm';
				param = {"baseIntegral":baseIntegral,
						"integralPer":integralPer,
						"gmtStart":gmtStart,
						"gmtEnd":gmtEnd,
						"auctionId":auctionid
				},
				$.ajax({
					url : url ,
					type : "POST" ,
					data : param,
					success : function(data) {
					    var json = data.json;
						if (json.code === "success") {
						    alert("更新成功");
						} else {
						    alert("更新失败，请联系管理员。");
						}
					},
					error: function(data) {
					    alert("update error");
					}
				});
			});
		}, 
		/**
		 * 初始化开奖按钮
		 */
		_initKaijiangButton:function(){
		  	$('#auctionItemTable').delegate(".auction-kaijiang-button", "click", function(e){
		  		e.stopPropagation(); 
				if(!confirm("确认开奖？")) {
				    return;
				}
				var auctionTr = $(this).closest("tr"),
				auctionid = auctionTr.data("auctionid"),
				param,url;
				if(auctionid===""){
					alert("开奖失败，找不到auctionid");
					return;
				}
				url = $('#ddzBopsRoot').val() + '/bops/dcome/qq/remote/auction_kaijiang_ajax.htm';
				param = {"auctionId":auctionid},
				$.ajax({
					url : url ,
					type : "POST" ,
					data : param,
					success : function(data) {
					    var json = data.json;
						if (json.code === "success") {
						    alert("开奖成功");
						    window.location.reload();
						} else {
						    alert("开奖失败，请联系管理员。");
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
