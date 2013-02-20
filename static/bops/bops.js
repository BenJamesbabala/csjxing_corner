!(function($){
	$.namespace("DD.Bops");
	
	var self = DD.Bops;
	
	var bopsRoot = $("#bopsRoot").val() ;
	
	$.extend(DD.Bops,{
		
		init:function(){
			self._initAccountList();
			self._initBuyingRecommend() ;
			self._initTaokeReportSettle() ;
			self._initCacheManagement() ;
			self._initTaokeReport() ;
			self._initBannerManagement() ;
			self._initJfbSettleRecordList() ;
		},
		
		/**
		 * ���ֱ����ż�¼
		 */
		_initJfbSettleRecordList:function(){
			if($("#bopsPage").val() == 'jfbSettleRecordList'){
				$(".jfb-settle-csv-upload .all-successful-click").click(function(){
					var ck = $(this) ;
					var checked = ck.attr('checked') ;
					var uploadFile = $(".jfb-settle-csv-upload .upload-file") ;
					if(checked && checked != true){
						uploadFile.addClass('dd-hide') ;
					} else {
						uploadFile.removeClass('dd-hide') ;
					}
				}) ;
				
				$(".display-upload-jfb-settle-click").click(function(){
					$(".jfb-settle-csv-upload").addClass('dd-hide') ;
					$("#tb-jfbSettleRecord tr").removeClass('hlight-bk-yellow') ;
					$(this).closest('td').find(".jfb-settle-csv-upload").removeClass('dd-hide') ;
					$(this).closest('tr').addClass('hlight-bk-yellow') ;
				}) ;
				
				$(".jfb-settle-csv-upload .W_close_color").click(function(){
					$(this).closest(".jfb-settle-csv-upload").addClass('dd-hide') ;
					$(this).closest('tr').removeClass('hlight-bk-yellow') ;
				}) ;
				
				$(".jfb-settle-csv-upload form").submit(function(){
					var _this = $(this) ;
					if(_this.find("[name=tradeNo]").val() == ''){
						alert('���׺ű�����д.') ;
						return false ;
					}
					var allSuccessfulChecked = _this.find("[name=allSuccessful]").attr('checked') ;
					if(!allSuccessfulChecked){
						if(_this.find('[name=successFile]').val() == ''){
							alert("��ѡ����Ҫ���ĳɹ���������������CSV��ʽ��");
							return false ;
						}
					}
				}) ;
			}
		} ,
		
		_initCacheManagement:function(){
			if($("#bopsPage").val() == 'cacheManagement'){
				var _genCacheKey = function(){
					var selVal = $("#regionName").val() ;
					var paramVal = $("#keyParam").val() ;
					var val = selVal ;
					if(paramVal != ''){
						val += '_' + paramVal ;
					}
					$("#cacheKey").val(val) ;		
				} ;

				$("#regionName").change(function(){
					_genCacheKey() ;
				}) ;
				
				$("#keyParam").keyup(function(){
					_genCacheKey() ;
				}) ;
				
				$("#deleteCache").click(function(){
					if(!confirm('ȷ��Ҫɾ��������')){
						return ;
					}
					var url = bopsRoot + '/bops/remote/rest/cache_management_delete_ajax.htm' ;
					var cacheKey = $(this).attr('data-delete');
					$.ajax({
			    		url : url ,
						type : "POST" ,
						data : {cacheKey:cacheKey},
						success : function(data){
							var json = data.json ;
							var code = json.code ;
							var data = json.data ;
							var detail = json.detail ;
							if(code == 'success') { 
								if(data == true){
									alert('�ɹ�ɾ������');
								}else{
									alert('��������ʧ�ܣ�');
								}
							} else {
								alert('����ʧ�ܣ� ' + detail ) ;
							}
						} ,
						error : function(data){
							alert('����ʧ�� ��' + data ) ;
						}
					});
				});
				if($("#cacheKey").val() == ''){
					_genCacheKey() ;
				}
			}
		} ,
		
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
		 * �Կͱ���
		 */
		_initTaokeReport:function(){
			if($("#ddzBopsPage").val() == "taokeReportList") {
				
				$(".report-outcode-click").click(function(){
					$(this).parent().find('.report-outcode').removeClass('dd-hide') ; 
				}) ;
				
				$('#outCode_detail_tips .layer_tips_close').click(function(){
					$('#outCode_detail_tips').addClass('dd-hide') ;
				});
				
				$("[data-outCode]").click(function(){
					var _this = $(this) ;
					var outCode = _this.attr('data-outCode') ;
					if(outCode != ''){
						var url = bopsRoot + '/bops/dcome/qq/remote/prom/query_prom_outcode.htm' ;
						$.ajax({
							url: url ,
			    			type : "POST" ,
			    			data : { outcode: outCode },
			    			success : function(data){
			    				var json = data.json ;
			    				var code = json.code ;
			    				var data = json.data ;
			    				var detail = json.detail ;
			    				if(code == 'success') {
			    					var tip = $('#outCode_detail_tips') ;
			    					tip.find('#outCode').html(outCode) ;
			    					tip.find('#itemId').html(data.item.id);
			    					tip.find('#itemTitle').html('<a href="http://item.taobao.com/item.htm?id=' + data.item.nativeId + '" target="_blank" >' + data.item.itemTitle + '</a>');
			    					tip.find('#user').html(data.user.nick + '(' + data.user.userId + ')') ;
			    					tip.find('#integral').html() ;
			    					tip.css({top:_this.offset().top - 80, left:_this.offset().left + 35}) ;
			    					tip.removeClass('dd-hide') ;
			    				} else {
			    					alert('error' + detail);
			    				}
			    			},
			    			error:function(data){
			    				alert('error' + data) ;
			    			}
						});
					}
				}) ;
				//end $("[data-outCode]").click(function(){
				
				$('.account-refund-click').click(function(){
					if(!confirm('ȷ��Ҫ��Ǹü�¼Ϊ�˿�άȨ��')){
						return ;
					}
					var _this = $(this) ;
					var reportId = _this.attr('data-report-id') ;
					var url = bopsRoot + '/bops/zhe/remote/account_refund_ajax.htm' ;
					$.ajax({
						url: url ,
		    			type : "POST" ,
		    			data : { reportId: reportId , isRefund:'T' },
		    			success : function(dataObj){
		    				var json = dataObj.json ;
		    				var code = json.code ;
		    				var data = json.data ;
		    				var detail = json.detail ;
		    				if(code == 'success') {
		    					alert('��¼άȨ��¼�ɹ�!');
		    				} else {
		    					alert('error' + detail);
		    				}
		    			},
		    			error:function(dataObj){
		    				alert('error' + dataObj) ;
		    			}
					});
				}) ;
				//end $('.account-refund-click').click(function(){
				
				//ͳ���ܽ��
				$("#calcTotalCommission .calcTotalAmount").click(function(){
					$("#calcTotalCommission .calcTotalAmount").addClass('dd-hide') ;
					$("#calcTotalCommission .totalSettleAmount").html('������ .........') ;
					var form = $("#reportForm") ;
					var children = form.children() ;
					var url = bopsRoot + "/bops/zhe/remote/calc_report_settle_amount_ajax.htm";
					$.ajax({
						url : url ,
						type : "POST" ,
		    			data : 'sumType=COMMISSION&' + form.serialize() ,
		    			success : function(dataObj){
		    				var code = dataObj.code ;
		    				var data = dataObj.data ;
		    				var detail = dataObj.detail ;
		    				if(code == 'success'){
		    					$("#calcTotalCommission .calcTotalAmount").removeClass('dd-hide') ;
		    					$("#calcTotalCommission .totalSettleAmount").html(data + ' Ԫ') ;
		    				}
		    			} ,
		    			error : function(dataObj){
		    				alert('����Ӷ�����' + dataObj);
		    			}
					});
				});
				
				//ͳ���û�����Ӷ��
				$("#calcTotalSettleFee .calcTotalAmount").click(function(){
					$("#calcTotalSettleFee .calcTotalAmount").addClass('dd-hide') ;
					$("#calcTotalSettleFee .totalSettleAmount").html('������ .........') ;
					var form = $("#reportForm") ;
					var children = form.children() ;
					var url = bopsRoot + "/bops/zhe/remote/calc_report_settle_amount_ajax.htm";
					$.ajax({
						url : url ,
						type : "POST" ,
		    			data : 'sumType=SETTLE_FEE&' + form.serialize() ,
		    			success : function(dataObj){
		    				var code = dataObj.code ;
		    				var data = dataObj.data ;
		    				var detail = dataObj.detail ;
		    				if(code == 'success'){
		    					$("#calcTotalSettleFee .calcTotalAmount").removeClass('dd-hide') ;
		    					$("#calcTotalSettleFee .totalSettleAmount").html(data + ' Ԫ') ;
		    				}
		    			} ,
		    			error : function(dataObj){
		    				alert('����Ӷ�����' + dataObj);
		    			}
					});
				});
				
				$("#settleIptMoreBtn").click(function(){
					$("#settleIptMore").toggle() ;
				});
				//end $("#settleIptMoreBtn").click(function(){
				
				//��ʼ������
				$( "#gmtCreateStart" ).datepicker({ dateFormat: "yy-mm-dd" ,changeYear:true});
				$( "#gmtCreateEnd" ).datepicker({ dateFormat: "yy-mm-dd" ,changeYear:true});
				$( "#gmtSettledStart" ).datepicker({ dateFormat: "yy-mm-dd" ,changeYear:true});
				$( "#gmtSettledEnd" ).datepicker({ dateFormat: "yy-mm-dd" ,changeYear:true});
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
				                            tbHtml = '<table><tr><td>���</td><td>����</td><td>outCode</td><td>�ɽ��۸�</td><td>����</td><td>����Ӷ��</td><td>�û�Ӷ��</td><td>����</td><td>�����ǳ�</td><td>���׺�</td><td>����ʱ��</td><td>άȨ</td><tr>'
				    						
				    						for(var i=0 ;i<data.length;i++){
				    							var report = data[i] ;
				    							var refundStatus = report.refundStatus ;
				    							var settleStauts = report.settleStatus ;
				    							var refundComment = '��' ;
				    							if(refundStatus == 'T'){
				    								refundComment = 'άȨ' ;
				    							} else if(refundStatus == 'B'){
				    								refundComment = 'άȨ����';
				    							}
				        						tbHtml = tbHtml + '<tr class="report-refund-' + settleStauts + '"><td>' + (i+1) + '</td><td><a href="http://item.taobao.com/item.htm?id=' + report.numIid + '" target="_blank">' +  report.itemTitle + '</a></td><td>' + report.outCode + '</td><td>' + report.realPayFee + 
				    								'</td><td align="center">' + report.itemNum + '</td><td>' + report.commission + '<span class=F10> (' + report.commissionRate*100 + '%) </span>' +  
													'</td><td>' + report.userCommission + '<span class=F10> (' +  report.formatUserCommissionRate + '%) </span></td><td>' + report.settleFee + '</td><td>' + report.sellerNick + '</td><td>' + report.tradeId + '</td><td>' + report.gmtCreateFormatYMD + '</td><td>' + refundComment + '</td></tr>' ;
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
				    }) ;
				    
				    ////��ע��ʼ
				    
				    //��ʾ��ע
				    $("#settleTable .memo-icon").hover(function(){
				    	var memo = $(this).attr("data-memo") ;
				    	if(memo != ''){
					    	$("#settle-memo-tips-container").css({left:$(this).offset().left - 20,top:$(this).offset().top + 25}) ;
					    	$("#settle-memo-tips-container").find(".content").html($(this).attr("data-memo"));
					    	$("#settle-memo-tips-container").removeClass("dd-hide") ;
				    	}
				    } , function(){
				    	$("#settle-memo-tips-container").delay(300).addClass("dd-hide") ;
				    }) ;
					
					//�������ӱ�ע������ʾ��ע����
					$(".add-memo-click").click(function(){
						var settleId = $(this).attr("data-settle-id") ;
						$("#add-settle-memo-dialog").attr("data-settle-id" , settleId) ;
						$("#add-settle-memo-dialog").css({left:$(this).offset().left - 150,top:$(this).offset().top + 19}) ;
						$("#add-settle-memo-dialog").find(".content").val($(this).attr("data-memo"));
						$("#add-settle-memo-dialog").find(".msg").html("") ;
						$("#add-settle-memo-dialog").removeClass("dd-hide") ;
					}) ;
					
					//����ر�
					$("#add-settle-memo-dialog .W_close_color").click(function(){
						$("#add-settle-memo-dialog").addClass("dd-hide") ;
					});
					
					//�ύ��ע
					$("#add-settle-memo-dialog .submit").click(function(){
						var url = ddzBopsRoot + "/bops/zhe/remote/add_settle_memo_ajax.htm";
						var msgSelector = $("#add-settle-memo-dialog").find(".msg") ;
						var settleId = $("#add-settle-memo-dialog").attr("data-settle-id") ;
						var content = $("#add-settle-memo-dialog").find(".content").val() ;
						if(content == undefined || content == ''){
							msgSelector.html("�����뱸ע����") ;
							return ;
						}
						//�ύ��ע
						$.ajax({
							url : url ,
							type : "POST" ,
							data : { id: settleId , memo : encodeURI(content) },
							success : function(data){
								var json = data.json ;
								var code = json.code ;
								var data = json.data ;
								var detail = json.detail ;
								if(code == 'success') { 
									msgSelector.html("��ע���³ɹ���") ;
									$("#add-settle-memo-dialog").delay(500).addClass("dd-hide") ;			
									
								} else {
									msgSelector.html("���±�עʧ�ܣ�" + detail) ;
								}
							} ,
							error : function(data) {
								msgSelector.html("���±�עʧ�ܣ�" + data) ;
							}
						}) ;
					}) ;
					
					////��ע����
					
					
					//���ֱ�
					$(".jfb-settle-statistic-click").click(function(){
						var url = bopsRoot + '/bops/zhe/remote/statistic_jfb_settle_ajax.htm' ;
						$.ajax({
							url : url ,
							type : "POST" ,
							data : {},
							success : function(data){
								var json = data.json ;
								var code = json.code ;
								var data = json.data ;
								var detail = json.detail ;
								if(code == 'success') { 
									var jfbCount = data.jfbCount ;
									var alipayCount = data.alipayCount ;
									$('.jfb-settle-csv-export').find('.jfb-count').html(jfbCount) ;
									$('.jfb-settle-csv-export').find('.alipay-count').html(alipayCount) ;
									$('.jfb-settle-csv-export').removeClass('dd-hide') ;
									if(jfbCount <= 0 || alipayCount <= 0){
										$('.jfb-settle-csv-export .jfb-settle-csv-export-click').addClass('dd-hide') ;
									} else {
										$('.jfb-settle-csv-export .jfb-settle-csv-export-click').removeClass('dd-hide') ;
									}
								} else {
									alert('error') ;
								}
							} , 
							error : function(data){
								alert('error:' + data) ;
							}
						});
					}) ;
					
					//��ʼ������
					$( "#gmtCreateStart" ).datepicker({ dateFormat: "yy-mm-dd" ,changeYear:true});
					$( "#gmtCreateEnd" ).datepicker({ dateFormat: "yy-mm-dd" ,changeYear:true});
					$( "#gmtSettledStart" ).datepicker({ dateFormat: "yy-mm-dd" ,changeYear:true});
					$( "#gmtSettledEnd" ).datepicker({ dateFormat: "yy-mm-dd" ,changeYear:true});
			}
		} , 
		
		_initBannerManagement:function(){
			if($("#bopsPage").val() == "bannerManagement") {
				
				var updateBannerDialog = $("#updateBannerDialog") ;
				
				var updateBannerBindEventDialog = $("#updateBannerBindEventDialog") ;
				
				$(".edit-banner-click").click(function(){
					var _this = $(this) ;
					var bannerId = _this.attr("data-banner-id") ;
					if(bannerId == ''){
						return ;
					}
					$.ajax({
						url : bopsRoot + "/bops/remote/rest/query_banner_ajax.htm"  ,
						type : "POST" ,
						data : { bannerId: bannerId },
						success : function(data){
							var json = data.json ;
							var code = json.code ;
							var data = json.data ;
							var detail = json.detail ;
							if(code == 'success') { 
								var bannerConfig = data ;
								updateBannerDialog.attr('data-banner-id' , bannerConfig.bannerId) ;
								updateBannerDialog.find('.bannerId').html(bannerConfig.bannerId) ;
								updateBannerDialog.find('.bannerPicUrl').val(bannerConfig.bannerPicUrl) ;
								updateBannerDialog.find('.targetUrl').val(bannerConfig.targetUrl) ;
								updateBannerDialog.find('.targetBlank').val(bannerConfig.targetBlank) ;
								//var top = _this.offset().top + 40 ;
								//var left = _this.offset().left - updateBannerDialog.width() ; 
								//updateBannerDialog.css({top:top , left:left}) ;
								updateBannerDialog.removeClass('dd-hide') ;
							} else {
								alert("��ȡ��Ϣʧ�ܣ�" + detail) ;
							}
						} ,
						error : function(data) {
							alert("��ȡ��Ϣʧ�ܣ�" + data) ;
						}
					}) ;
				}) ;
				//end $(".edit-banner-click").click(function(){
				
				$(".edit-banner-event-click").click(function(){
					var _this = $(this) ;
					var bannerId = _this.attr("data-banner-id") ;
					if(bannerId == ''){
						return ;
					}
					$.ajax({
						url : bopsRoot + "/bops/remote/rest/query_banner_ajax.htm"  ,
						type : "POST" ,
						data : { bannerId: bannerId },
						success : function(data){
							var json = data.json ;
							var code = json.code ;
							var data = json.data ;
							var detail = json.detail ;
							if(code == 'success') { 
								var bannerConfig = data ;
								updateBannerBindEventDialog.attr('data-banner-id' , bannerConfig.bannerId) ;
								updateBannerBindEventDialog.find('.bannerId').html(bannerConfig.bannerId) ;
								updateBannerBindEventDialog.find('.bindEvent').val(bannerConfig.bindEvent) ;
								updateBannerBindEventDialog.find('.bindEventFunction').val(bannerConfig.bindEventFunction) ;
								
								updateBannerBindEventDialog.removeClass('dd-hide') ;
							} else {
								alert("��ȡ��Ϣʧ�ܣ�" + detail) ;
							}
						} ,
						error : function(data) {
							alert("��ȡ��Ϣʧ�ܣ�" + data) ;
						}
					}) ;
				}) ;
				
				updateBannerDialog.find(".W_close_color").click(function(){
					updateBannerDialog.addClass('dd-hide') ;
				}) ;
				//end updateBannerDialog.find(".W_close_color").click(function(){
				
				updateBannerBindEventDialog.find(".W_close_color").click(function(){
					updateBannerBindEventDialog.addClass('dd-hide') ;
				}) ;
				//end updateBannerDialog.find(".W_close_color").click(function(){
				
				updateBannerDialog.find(".submit").click(function(){
					if(!confirm('ȷ��������Banner��')){
						return ;
					}
					var bannerId = updateBannerDialog.attr('data-banner-id') ;
					var bannerPicUrl = updateBannerDialog.find(".bannerPicUrl").val() ;
					var targetUrl = updateBannerDialog.find(".targetUrl").val() ;
					var targetBlank = updateBannerDialog.find(".targetBlank").val() ;
					$.ajax({
						url : bopsRoot + "/bops/remote/rest/update_banner_ajax.htm"  ,
						type : "POST" ,
						data : { bannerId: bannerId, bannerPicUrl:bannerPicUrl,targetUrl:targetUrl,targetBlank:targetBlank},
						success : function(result){
							var code = result.code ;
							var data = result.data ;
							var detail = result.detail ;
							if(code == 'success') { 
								alert("�����ɹ���") ;
								window.location.reload() ;
							} else {
								alert("����ʧ�ܣ�" + detail) ;
							}
						} ,
						error : function(data) {
							alert("����ʧ�ܣ�" + data) ;
						}
					}) ;
				});
				//end updateBannerDialog.find(".submit").click(function(){
				
				//
				updateBannerBindEventDialog.find(".submit").click(function(){
					if(!confirm('ȷ�����Banner���¼���')){
						return ;
					}
					var bannerId = updateBannerBindEventDialog.attr('data-banner-id') ;
					var bindEvent = updateBannerBindEventDialog.find(".bindEvent").val() ;
					var bindEventFunction = updateBannerBindEventDialog.find(".bindEventFunction").val() ;
					$.ajax({
						url : bopsRoot + "/bops/remote/rest/update_banner_bind_event_ajax.htm"  ,
						type : "POST" ,
						data : { bannerId: bannerId, bindEvent:bindEvent,bindEventFunction:bindEventFunction },
						success : function(result){
							var code = result.code ;
							var data = result.data ;
							var detail = result.detail ;
							if(code == 'success') { 
								alert("���³ɹ���") ;
								window.location.reload() ;
							} else {
								alert("����ʧ�ܣ�" + detail) ;
							}
						} ,
						error : function(data) {
							alert("����ʧ�ܣ�" + data) ;
						}
					}) ;
				}) ;
				//end 
				
				$(".edit-banner-status-click").click(function(){
					if(!confirm('ȷ������״̬��')){
						return ;
					}
					var _this = $(this) ;
					var bannerId = _this.attr('data-banner-id') ;
					var status = _this.attr('data-status') ;
					$.ajax({
						url : bopsRoot + "/bops/remote/rest/update_banner_status_ajax.htm"  ,
						type : "POST" ,
						data : { bannerId: bannerId, status:status},
						success : function(result){
							var code = result.code ;
							var data = result.data ;
							var detail = result.detail ;
							if(code == 'success') { 
								alert("����״̬�ɹ���") ;
								window.location.reload() ;
							} else {
								alert("����ʧ�ܣ�" + detail) ;
							}
						} ,
						error : function(data) {
							alert("����ʧ�ܣ�" + data) ;
						}
					}) ;
				}) ;
				//end $(".edit-banner-status-click").click(function(){
				
				
			}
			
		} ,
		
		end:0
	});
})(jQuery);

jQuery(document).ready(function(){
	DD.Bops.init();
});
