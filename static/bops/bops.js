!(function($){
	$.namespace("DD.Bops");
	
	var self = DD.Bops;
	
	$.extend(DD.Bops,{
		
		init:function(){
			self._initAccountList();
			self._initBuyingRecommend() ;
			self._initTaokeReportSettle() ;
		},
		
		//�˺���Ϣҳ
		_initAccountList:function(){
			if($("#ddzBopsPage").val() == "accountList") {
				var ddzBopsRoot = $("#ddzBopsRoot").val() ; ;
				var url = ddzBopsRoot + "/bops/zhe/remote/alipay_detail_ajax.htm";
				$("[data-alipay]").click(function(){
					var thisElement = $(this) ;
					var alipayId = thisElement.attr("data-alipay") ;
					
					//��ȡ֧������ϸ��Ϣ
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
								alert('״̬�ı�ʧ��. ԭ��' + detail ) ;
							}
						} ,
						error : function(data) {
							alert("��ȡ����ʧ��") ;
						}
			    	}) ;
					
				}) ;
				// end $("[data-alipay]").click(function(){
				
				//�ر�tip
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
		 * ����������
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
									thisElement.html("���޸�");
								} else {
									alert('ʧ��. ԭ��' + detail ) ;
								}
							} , 
							error : function(data){
								alert('error ! ' + data) ;
							}
						}) ;
				}) ;
				
				$("#refreshCacheBtn").click(function(){
					if(!confirm('ȷ��Ҫ���û���������')){
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
								alert('���������Ѹ���') ;
							} else {
								alert('ʧ��. ԭ��' + detail ) ;
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
		 * �Կͱ������
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
				                            tbHtml = '<table><tr><td>���</td><td>����</td><td>outCode</td><td>�ɽ��۸�</td><td>����</td><td>����Ӷ��</td><td>�û�Ӷ��</td><td>�����ǳ�</td><td>���׺�</td><tr>'
				    						
				    						for(var i=0 ;i<data.length;i++){
				    							var report = data[i] ;
				        						tbHtml = tbHtml + '<tr><td>' + (i+1) + '</td><td><a href="http://item.taobao.com/item.htm?id=' + report.numIid + '" target="_blank">' +  report.itemTitle + '</a></td><td>' + report.outCode + '</td><td>' + report.realPayFee + 
				    								'</td><td align="center">' + report.itemNum + '</td><td>' + report.commission + '<span class=F10> (' + report.commissionRate*100 + '%) </span>' +  
													'</td><td>' + report.userCommission + '<span class=F10> (' +  report.formatUserCommissionRate + '%) </span></td><td>' + report.sellerNick + '</td><td>' + report.tradeId + '</td></tr>' ;
				    						}
				    						
				        					
				    						tbHtml = tbHtml + '</table>' ;
										}else{
											tbHtml = 'û�з��������ļ�¼.' ;
										}
										settleTr.find("td").html(tbHtml);
										
				    				} else {
				    					alert('��ѯʧ��. ԭ��' + detail ) ;
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
					
					//���[����]
					$(".current-item").click(function(e){
						e.stopPropagation(); 
						$(".ui-customize-select-list").addClass("dd-hide") ;
						var op = $(this).find(".ui-customize-select-list") ;
						op.removeClass("dd-hide") ;	
						var trIndex  = $(this).attr("data-trIndex") ;
						//��ǰ�иı䱳��
						$(".settleTableTr").removeClass("highlightBk") ;
						$(".settleTableTr").each(function(){
							if($(this).attr("data-trIndex") == trIndex){
								$(this).addClass("highlightBk") ;
							}
						});
					}) ;
										
					//��������״̬
					$("[data-settleTo]").click(function(){
						var settleTo = $(this).attr("data-settleTo") ;
						var settleId = $(this).attr("data-settleId") ;
						var settleToDesc = $(this).find("span").html();
						if(confirm("ȷ��Ҫ�ı����״̬Ϊ [" + settleToDesc + "] ��")){
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
										alert('�޸Ľ���״̬�ɹ�����ˢ��ҳ��');
										window.location.reload() ;
									} else {
										alert('�޸Ľ���״̬ʧ��. ԭ��' + detail ) ;
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
						
						//��ȡ֧������ϸ��Ϣ
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
									alert('��ȡ����ʧ��. ԭ��' + detail ) ;
								}
							} ,
							error : function(data) {
								alert("��ȡ����ʧ��") ;
							}
				    	}) ;
						
					}) ;
					//end $("[data-alipay]").click(function(){
					
					$("#settleIptMoreBtn").click(function(){
						$("#settleIptMore").toggle() ;
					});
					//end $("#settleIptMoreBtn").click(function(){
					
					$("#exportSmsForm").submit(function(){
						if(confirm('��������ģ�壬�Ƿ������')){
							return true ;
						}else{
							return false ;
						}
					});
					//end $("#exportSmsForm").submit(function(){
					
					//�ر�tip
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
					
					//��ʼ������
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
