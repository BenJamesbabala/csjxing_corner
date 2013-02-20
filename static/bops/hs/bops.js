!(function($){
	$.namespace("horosocpe.Bops");
	
	var self = horosocpe.Bops;
	var hsBopsRoot = $("#hsBopsRoot").val();
	
	$.extend(horosocpe.Bops,{
		
		init:function(){
			self._initHsFateOperate();
			self._initHsTopicOperate();
		},
		
		_initHsFateOperate:function(){
			$(".hs-table .desc").hover(
			    function() {
					var _this = $(this);
					var _detail = _this.closest("td").find(".detail");
					if (_detail.html() == '') {
						return;
					}
					_detail.css({left: _this.width()});
					_detail.removeClass("dd-hide");
				},
				function() {
					var _this = $(this);
					_this.closest("td").find(".detail").addClass("dd-hide");
				}
			);
		},
		_initHsTopicOperate: function() {
		    $("#hsTopicTable").find(".delete-topic-btn").click(function() {
			    var _this = $(this);
				var _tr = _this.closest("tr");
				var topicId = _tr.attr("data-index");
				if (topicId == '' || topicId == undefined || isNaN(topicId)) {
				    alert("无效的topicId");
					return;
				}
				$.ajax({
				    url: hsBopsRoot + "/bops/dcome/hs/remote/delete_hs_topic_ajax.htm",
					type: "post",
					data: {topicId: topicId},
					success: function(result) {
					    var json = result.json;
						if (json.success) {
						    window.location.href = window.location.href;
						} else {
						    alert("更新失败");
						}
					},
					error: function() {
					    alert("更新失败");
					}
				});
			});
		},
		
		end:0
	});
})(jQuery);

jQuery(document).ready(function(){
	horosocpe.Bops.init();
});
