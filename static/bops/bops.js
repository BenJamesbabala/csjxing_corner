!(function($){
	$.namespace("DD.Bops");
	
	var self = DD.Bops;
	
	$.extend(DD.Bops,{
		
		init:function(){
			self._initAccountList();
			self._initBuyingRecommend() ;
			self._initTaokeReportSettle() ;
		},
		
		//账号信息页
		_initAccountList:function(){
			if($("#ddzBopsPage").val() == "accountList") {
				var ddzBopsRoot = $("#ddzBopsRoot").val() ; ;
				var url = ddzBopsRoot + "/bops/zhe/remote/alipay_detail_ajax.htm";
				$("[data-alipay]").click(function(){
					var thisElement = $(this) ;
					var alipayId = thisElement.attr("data-alipay") ;
					
					//获取支付宝详细信息
					$.ajax({
			    		url : url ,
						type : "POST" ,
						data : { alipayId: alipayId },
						success : function(data){
							var json = data.json ;
							var code = json.code ;
							var data = json.data ;
							var detail = json.detail ;
							if(code == 'success') { 
								$("#alipayAccount").html(data.alipayId);
								$("#createDate").html(data.formatGmtCreate) ;
								$("#settleCount").html(data.settleCount) ;
								$("#totalSettleFee").html(data.totalSettleFee) ;
								var left = thisElement.offset().left + 25  ;
								var top = thisElement.offset().top - 58 ;
								$("#alipay_detail_tips").removeClass("dd-hide");
								$("#alipay_detail_tips").offset({top:top});
								$("#alipay_detail_tips").offset({left:left});
								
							} else {
								alert('状态改变失败. 原因：' + detail ) ;
							}
						} ,
						error : function(data) {
							alert("获取详情失败") ;
						}
			    	}) ;
					
				}) ;
				// end $("[data-alipay]").click(function(){
				
				//关闭tip
				$(".layer_tips_close").click(function(){
					var id = $(this).attr("data-id") ;
					$("#" + id).addClass("dd-hide");
				});
				//end $(".layer_tips_close").click(function(){
				
				$( "#gmtCreateStart" ).datepicker({ dateFormat: "yy-mm-dd" ,changeYear:true});
				$( "#gmtCreateEnd" ).datepicker({ dateFormat: "yy-mm-dd" ,changeYear:true});
			}
		},
		
		/**
		 * 别人正在买
		 */
		_initBuyingRecommend:function(){
			if($("#ddzBopsPage").val() == "buyingRecommend") {
				var ddzBopsRoot = $("#ddzBopsRoot").val() ; ;
				
				
				$("[data-display]").click(function(){
					var thisElement = $(this) ;
					var url = ddzBopsRoot + "/bops/zhe/remote/buying_recomm_display_ajax.htm";
					var id = $(this).attr("data-id") ;
					var isDisplay = $(this).attr("data-display") ;
						$.ajax({
							url: url ,
							type : "POST" ,
							data : { id: id , display: isDisplay },
							success : function(data){
								var json = data.json ;
								var code = json.code ;
								var data = json.data ;
								var detail = json.detail ;
								if(code == 'success') { 
									thisElement.html("已修改");
								} else {
									alert('失败. 原因：' + detail ) ;
								}
							} , 
							error : function(data){
								alert('error ! ' + data) ;
							}
						}) ;
				}) ;
				
				$("#refreshCacheBtn").click(function(){
					if(!confirm('确认要重置缓存数据吗？')){
						return ;
					}
					
					var url = ddzBopsRoot + "/bops/zhe/remote/buying_recommend_refreshCache_ajax.htm";
					
					$.ajax({
						url: url ,
						type : "POST" ,
						data : {},
						success : function(data){
							var json = data.json ;
							var code = json.code ;
							var data = json.data ;
							var detail = json.detail ;
							if(code == 'success') { 
								alert('缓存数据已更新') ;
							} else {
								alert('失败. 原因：' + detail ) ;
							}
						} , 
						error : function(data){
							alert('error ! ' + data) ;
						}
					}) ;
				}) ;
			}
		} ,
		
		/**
		 * 淘客报表结算
		 */
		_initTaokeReportSettle:function(){
			if($("#ddzBopsPage").val() == "taokeReportSettle") {
					var ddzBopsRoot = $("#ddzBopsRoot").val() ; 
					
					
					$("[data-settleDetail]").click(function(){
						var url = ddzBopsRoot + "/bops/zhe/remote/taoke_report_ajax.htm";
						var settleId = $(this).attr("data-settleId");
						var trIndex = $(this).attr("data-trIndex") ;
						var settleTr = null ;
						
						$("[data-keepTrIndex]").each(function(){
							if($(this).attr("data-keepTrIndex") == trIndex) {
								settleTr = $(this) ;
							}
						});
						
						if(settleTr == null){
							return ;
						}

						if(!settleTr.hasClass("dd-hide")){
							settleTr.addClass("dd-hide");
							$(this).find("span").removeClass("icon-pull-up");
							$(this).find("span").addClass("icon-pull-down");
							return ;
						}
						
						$(this).find("span").removeClass("icon-pull-down");
						$(this).find("span").addClass("icon-pull-up");
						settleTr.removeClass("dd-hide");
						if(settleTr.find("td").html() != ''){
							return ;
						}
						
						$.ajax(
							{
								url: url ,
				    			type : "POST" ,
				    			data : { settleId: settleId },
				    			success : function(data){
				    				var json = data.json ;
				    				var code = json.code ;
				    				var data = json.data ;
				    				var detail = json.detail ;
									
				    				if(code == 'success') {
										var tbHtml = '' ;
										if(data.length > 0){
				                            tbHtml = '<table><tr><td>序号</td><td>标题</td><td>outCode</td><td>成交价格</td><td>数量</td><td>报表佣金</td><td>用户佣金</td><td>卖家昵称</td><td>交易号</td><tr>'
				    						
				    						for(var i=0 ;i<data.length;i++){
				    							var report = data[i] ;
				        						tbHtml = tbHtml + '<tr><td>' + (i+1) + '</td><td><a href="http://item.taobao.com/item.htm?id=' + report.numIid + '" target="_blank">' +  report.itemTitle + '</a></td><td>' + report.outCode + '</td><td>' + report.realPayFee + 
				    								'</td><td align="center">' + report.itemNum + '</td><td>' + report.commission + '<span class=F10> (' + report.commissionRate*100 + '%) </span>' +  
													'</td><td>' + report.userCommission + '<span class=F10> (' +  report.formatUserCommissionRate + '%) </span></td><td>' + report.sellerNick + '</td><td>' + report.tradeId + '</td></tr>' ;
				    						}
				    						
				        					
				    						tbHtml = tbHtml + '</table>' ;
										}else{
											tbHtml = '没有符合条件的记录.' ;
										}
										settleTr.find("td").html(tbHtml);
										
				    				} else {
				    					alert('查询失败. 原因：' + detail ) ;
				    				}
				    			} , 
				    			error : function(data){
				    				alert('error ! ' + data) ;
				    			}
							}
						);
					});
					//end $("[data-settleDetail]").click(function(){
					
					
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
					//end $(".ui-customize-select-list li").mouseenter(function(){
					
					$(".ui-customize-select-list li").mouseleave(function(){
						$("#J-select-tips-container").addClass("dd-hide") ;
					});
					
					//点击[订正]
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
					}) ;
										
					//订正结算状态
					$("[data-settleTo]").click(function(){
						var settleTo = $(this).attr("data-settleTo") ;
						var settleId = $(this).attr("data-settleId") ;
						var settleToDesc = $(this).find("span").html();
						if(confirm("确定要改变结算状态为 [" + settleToDesc + "] ？")){
							var url = ddzBopsRoot + "/bops/zhe/remote/settle_report_ajax.htm";
							$.ajax({
								url: url ,
								type : "POST" ,
								data : { ids: settleId , settleTo: settleTo },
								success : function(data){
									var json = data.json ;
									var code = json.code ;
									var data = json.data ;
									var detail = json.detail ;
									if(code == 'success') { 
										alert('修改结算状态成功，请刷新页面');
										window.location.reload() ;
									} else {
										alert('修改结算状态失败. 原因：' + detail ) ;
									}
								} , 
								error : function(data){
									alert('error ! ' + data) ;
								}
							}) ;
						}
					});
					//end $("[data-settleTo]").click(function(){
					
					$("[data-alipay]").click(function(e){
						e.stopPropagation(); 
						var url = ddzBopsRoot + "/bops/zhe/remote/alipay_detail_ajax.htm";
						var thisElement = $(this) ;
						var alipayId = thisElement.attr("data-alipay") ;
						
						//获取支付宝详细信息
						$.ajax({
				    		url : url ,
							type : "POST" ,
							data : { alipayId: alipayId },
							success : function(data){
								var json = data.json ;
								var code = json.code ;
								var data = json.data ;
								var detail = json.detail ;
								if(code == 'success') { 
									$("#alipayAccount").html(data.alipayId);
									$("#createDate").html(data.formatGmtCreate) ;
									$("#settleCount").html(data.settleCount) ;
									$("#totalSettleFee").html(data.totalSettleFee) ;
									var left = thisElement.offset().left + 25  ;
									var top = thisElement.offset().top - 58 ;
									$("#alipay_detail_tips").removeClass("dd-hide");
									$("#alipay_detail_tips").offset({top:top});
									$("#alipay_detail_tips").offset({left:left});
									
								} else {
									alert('获取详情失败. 原因：' + detail ) ;
								}
							} ,
							error : function(data) {
								alert("获取详情失败") ;
							}
				    	}) ;
						
					}) ;
					//end $("[data-alipay]").click(function(){
					
					$("#settleIptMoreBtn").click(function(){
						$("#settleIptMore").toggle() ;
					});
					//end $("#settleIptMoreBtn").click(function(){
					
					$("#exportSmsForm").submit(function(){
						if(confirm('导出短信模板，是否继续？')){
							return true ;
						}else{
							return false ;
						}
					});
					//end $("#exportSmsForm").submit(function(){
					
					//关闭tip
					$(".layer_tips_close").click(function(){
						var id = $(this).attr("data-id") ;
						$("#" + id).addClass("dd-hide");
					});
					//end $(".layer_tips_close").click(function(){
					
					//
					$(document).click(function(e){
						var e = $(this) ;
				    	$(".ui-customize-select-list").addClass("dd-hide") ;
						$(".settleTableTr").removeClass("highlightBk") ;
						$("#alipay_detail_tips").addClass("dd-hide");
				    }) 
					
					//初始化日历
					$( "#gmtCreateStart" ).datepicker({ dateFormat: "yy-mm-dd" ,changeYear:true});
					$( "#gmtCreateEnd" ).datepicker({ dateFormat: "yy-mm-dd" ,changeYear:true});
					$( "#gmtSettledStart" ).datepicker({ dateFormat: "yy-mm-dd" ,changeYear:true});
					$( "#gmtSettledEnd" ).datepicker({ dateFormat: "yy-mm-dd" ,changeYear:true});
			}
		} , 
		
		end:0
	});
})(jQuery);

jQuery(document).ready(function(){
	DD.Bops.init();
});
