!(function($){
	$.namespace("Dcome.Bops");
	
	var self = Dcome.Bops;
	var bopsRoot = $('#ddzBopsRoot').val();
	
	$.extend(Dcome.Bops,{
		
		init:function(){
			self._initItemOperate();
		},
		
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
			var selectedIndex;
			$("#itemTable").find(".category").click(function() {
			    selectedIndex = $(this).get(0).selectedIndex;
			});
			$("#itemTable").find(".category").change(function() {
			    var _this = $(this);
				if(!confirm("确认更新？")) {
				    _this.get(0).selectedIndex = selectedIndex;
				    return;
				}
				var categoryId = _this.children("option:selected").val();
				if (categoryId == undefined || categoryId == '' || isNaN(categoryId)) {
				    alert("类目id有误，请刷新页面");
					return ;
				}
				var _tr = _this.closest("tr");
				var itemId = _tr.attr("data-index");
				$.ajax({
				    url: bopsRoot + '/bops/dcome/qq/remote/update_item_category_ajax.htm',
					type: "post",
					data: {itemId: itemId, categoryId: categoryId},
					success: function(result) {
					    var json = result.json;
						if (json.success) {
						    window.location.href = window.location.href;
						} else {
						    if (json.detail == 'no.item') {
						        alert("更新商品类目ID失败，无法找到对应的商品");
							} else {
							    alert("更新商品类目ID失败，未知错误");
							}
						}
					},
					error: function() {
					
					}
				});
			});
			$("#itemTable").find("td .userid").click(function() {
			    var _this = $(this);
				var _td = _this.closest("td");
				var userId = _this.html();
				if (_this.attr("data-init") != 'y') {
				    $.ajax({
					    url: bopsRoot + '/bops/dcome/qq/remote/query_user_info_ajax.htm',
						type: "post",
						data: {userIds: userId, includeComm: true},
						async: false,
						success: function(result) {
						    var json = result.json;
							if (json.success) {
							    var data = json.data;
								var userInfo = data[0]
								var info = '<div style="position: relative"><ul class="user-info dd-hide"><li><a class="w_close_color" href="javascript:;"></a></li>' +
								      '<li><span>用户名：</span><span>' + userInfo.userNick +'</span></li><li><span>积分：</span><span>' + userInfo.integralCount +'</span></li>' +
									  '<li><span>返利：</span><span>' + userInfo.commission +'</span></li><li><span>注册：</span><span>' + userInfo.gmtCreateFmt +'</span></li></ul></div>';
							     _td.append(info);
								 var _userInfo = _td.find(".user-info");
								 _userInfo.find(".w_close_color").click(function() {
								     _userInfo.addClass("dd-hide");
								 });
								 _this.attr("data-init", 'y');
							}
							
						},
						error: function() {
						
						}
					});
				}
				$(".user-info").addClass("dd-hide");
				_td.find(".user-info").removeClass("dd-hide");
			});
		} , 
		
		end:0
	});
})(jQuery);

jQuery(document).ready(function(){
	Dcome.Bops.init();
});
