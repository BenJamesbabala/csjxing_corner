!(function($){
	$.namespace("Dcome.Bops");
	
	var self = Dcome.Bops;
	
	$.extend(Dcome.Bops,{
		
		init:function(){
			self._initItemOperate();
		},
		/**
		 * 淘客报表结算
		 */
		_initItemOperate:function(){
			var ddzBopsRoot = $("#ddzBopsRoot").val();
			
			
			$(".ui-customize-select-list li").mouseenter(function(){
				$(this).parent().find("li").removeClass("active");
				if($(this).attr("dead") != "true"){
					$(this).addClass("active");
					if($(this).attr("tips") != ""){
						$("#J-select-tips-container").find(".content").html($(this).attr("tips"));
						$("#J-select-tips-container").removeClass("dd-hide") ;
						var targetLeft = $(this).offset().left - 260 ;
						var targetTop = $(this).offset().top - 5 ;
						$("#J-select-tips-container").css({left:targetLeft , top:targetTop});
					}
				}
			}) ;
			
			$(".ui-customize-select-list li").mouseleave(function(){
				$("#J-select-tips-container").addClass("dd-hide") ;
			});
			
			$(".current-item").click(function(e){
				e.stopPropagation(); 
				$(".ui-customize-select-list").addClass("dd-hide") ;
				var op = $(this).find(".ui-customize-select-list") ;
				op.removeClass("dd-hide") ;	
				var trIndex  = $(this).attr("data-trIndex") ;
				//当前行改变背景
				$(".settleTableTr").removeClass("highlightBk") ;
				$(".settleTableTr").each(function(){
					if($(this).attr("data-trIndex") == trIndex){
						$(this).addClass("highlightBk") ;
					}
				});
			});
			
			$(".normal-list #diableItem").click(function(e){
				e.stopPropagation(); 
				if(!confirm("确认下架商品？")) {
				    return;
				}
				var url = $('#ddzBopsRoot').val() + '/bops/dcome/qq/remote/reset_item_status_ajax.htm';
				var itemId = $(this).attr("data-itemId");
				var status = $(this).attr("data-status");
				$.ajax({
					url : url ,
					type : "POST" ,
					data : {"itemId":itemId, "itemStatus": status},
					success : function(data) {
					    var json = data.json;
						if (json.code = "success") {
						    var exp = 'table tr[data-index="' + itemId + '"]';
							$(exp).find(".dc-item-status").html("已下架");
						} else {
						    alert("更新失败，请联系管理员。");
						}
					},
					error: function(data) {
					    alert("internal error");
					}
				});
			});
			$(document).click(function(e){
				var e = $(this) ;
				$(".ui-customize-select-list").addClass("dd-hide") ;
				$(".settleTableTr").removeClass("highlightBk") ;
				$("#alipay_detail_tips").addClass("dd-hide");
			}); 
		} , 
		
		end:0
	});
})(jQuery);

jQuery(document).ready(function(){
	Dcome.Bops.init();
});
