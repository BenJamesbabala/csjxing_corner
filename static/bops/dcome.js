!(function($){
	$.namespace("DD.BopsDcome");
	
	var self = DD.BopsDcome;
	
	var ddzBopsRoot = $("#ddzBopsRoot").val() ; ;
	var bopsRoot = $("#bopsRoot").val() ; ;
	
	$.extend(DD.BopsDcome,{
		
		init:function(){
			String.prototype.replaceAll = function(s1,s2){   
				return this.replace(new RegExp(s1,"gm"),s2);   
			}
			self._initDcAddSceneItem() ;
			self._initDcSceneDetail();
			self._initDcItemList() ;
			self._initDcSceneList() ;
			self._initDcCreateScene() ;
			self._initDcCommentList();
			self._initDcSystemOptions() ;
			self._initDcCategoriesOptions() ;
			self._initValidator() ;
			self._initDatePicker();
			self._initDcPromotion();
			self._initDcAwardList() ;
			self._initBatchAddItem() ;
			self._initSpiderItemList() ;
			self._initVoteTrack() ;
			self._exchangeDetial();
			self._initExchangeApproveList() ;
		},
		
		_initExchangeApproveList:function(){
			if($("#bopsPage").val() == "dcExchangeApproveList") {
				
				//
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
				$(".ui-customize-select .current-item").click(function(e){
					var _this = $(this) ;
					e.stopPropagation(); 
					$(".ui-customize-select-list").addClass("dd-hide") ;
					var op = $(this).find(".ui-customize-select-list") ;
					op.removeClass("dd-hide") ;	
					
					//��ǰ�иı䱳��
					$("#exchangeTable tr").removeClass("hlight-bk-yellow") ;
					_this.closest('tr').addClass("hlight-bk-yellow") ;
				}) ;
				
				//��������״̬
				$("[data-to-status]").click(function(){
					var toStatus = $(this).attr("data-to-status") ;
					var approveId = $(this).attr("data-id") ;
					var toStatusDesc = $(this).find("span").html();
					var exAddMemoDialog = $(".exchange-add-memo-dialog") ;
					exAddMemoDialog.attr('data-to-status' , toStatus) ;
					exAddMemoDialog.attr('data-id' , approveId) ;
					exAddMemoDialog.attr('data-to-status-desc' , toStatusDesc) ;
					exAddMemoDialog.find('.status-desc').html(toStatusDesc) ;
					exAddMemoDialog.removeClass('dd-hide') ;
					exAddMemoDialog.find('.memo input').val('') ;
					if(toStatus == 'D') {
						exAddMemoDialog.find('.memo').removeClass('dd-hide') ;
					} else {
						exAddMemoDialog.find('.memo').addClass('dd-hide') ;
					}
					
					self.centerPoint(exAddMemoDialog) ;
					$("#shadow_bg").removeClass('dd-hide') ;
					
				});
				//end $("[data-toStatus]").click(function(){
				
				//��ʾ��ע
			    $("#exchangeTable [data-memo]").hover(function(){
			    	var memo = $(this).attr("data-memo") ;
			    	if(memo != ''){
				    	$("#memo-tips-container").css({left:$(this).offset().left - 20,top:$(this).offset().top + 25}) ;
				    	$("#memo-tips-container").find(".content").html($(this).attr("data-memo"));
				    	$("#memo-tips-container").removeClass("dd-hide") ;
			    	}
			    } , function(){
			    	$("#memo-tips-container").delay(300).addClass("dd-hide") ;
			    }) ;
			    //end $("#settleTable .memo-icon").hover(function(){
				
			    
			    $(".exchange-add-memo-dialog").each(function(){
			    	var _this = $(this) ;
			    	//�ر�
			    	_this.find('.close').click(function(){
			    		_this.addClass('dd-hide') ;
			    		$("#shadow_bg").addClass('dd-hide') ;
			    	}) ;
			    	
			    	//�޸�״̬
			    	_this.find('.submit').click(function(){
			    		
			    		var toStatus = _this.attr("data-to-status") ;
						var approveId = _this.attr("data-id") ;
			    		
						if(toStatus == '' || approveId == ''){
							alert('���ݳ���,��ˢ��ҳ�棡') ;
							return ;
						}
						
						var userMemo = '' ;
						
						if(!_this.find('.memo').hasClass('dd-hide')){
							userMemo = _this.find('.memo input').val() ;
							if(!userMemo || userMemo == ''){
								alert('����д��ע��') ;
								return ;
							}
							userMemo = encodeURI(userMemo) ;
						}
						
			    		var url = bopsRoot + "/bops/dcome/qq/remote/update_user_exchange_approve_status_ajax.htm";
			    		
			    		$.ajax({
							url: url ,
							type : "POST" ,
							data : { toStatus: toStatus , approveId: approveId , userMemo:userMemo },
							success : function(data){
								var json = data.json ;
								var code = json.code ;
								var data = json.data ;
								var detail = json.detail ;
								_this.find('.close').trigger('click') ;
								if(code == 'success') { 
									alert('�޸�״̬�ɹ�����ˢ��ҳ��');
									window.location.reload() ;
								} else {
									alert('�޸�״̬ʧ��. ԭ��' + detail ) ;
								}
							} , 
							error : function(data){
								alert('error ! ' + data) ;
							}
						}) ;
						
			    	}) ;
			    	//end  _this.find('.submit').click(function(){
			    	
			    	
			    	$(".fake-select").each(function(){
			    		var _this = $(this) ;
			    		
			    		var hasOneCheck = function(){
			    			var chkList = _this.find('input[type=checkbox]') ;
			    			var hasCk = false ;
			    			chkList.each(function(){
			    				if($(this).attr('checked')){
			    					hasCk = true ;
			    				}
			    			}) ;
			    			return hasCk ;
			    		}
			    		
			    		if(hasOneCheck()){
			    			_this.find('.ck-all').removeClass('checked') ;
			    		}
			    		
			    		_this.find('.ck-all a').click(function(){
			    			_this.find('.ck-all').addClass('checked') ;
			    			_this.find('input[type=checkbox]').removeAttr('checked') ;
			    		}) ;
			    		
			    		_this.find("input[type=checkbox]").each(function(){
			    			var chk = $(this) ;
			    			if(chk.attr('checked')){
			    				_this.find('.toselect').removeClass('dd-hide') ;
			    			}
			    			
			    			chk.click(function(){
			    				var hasChecked =  hasOneCheck() ;
			    				if(hasChecked){
			    					_this.find('.ck-all').removeClass('checked') ;
			    				} else {
			    					_this.find('.ck-all').addClass('checked') ;
			    				}
			    			}) ;
			    		}) ;
			    		
			    		_this.hover(function(){
			    			_this.find('.toselect').removeClass('dd-hide') ;
			    		} , function(){
			    			var hasChecked =  hasOneCheck() ;
			    			if(!hasChecked) {
			    				_this.find('.toselect').addClass('dd-hide') ;
			    			}
			    		}) ;
			    	}) ;
			    }) ;
 			    
			    
				//
				$(document).click(function(e){
					var e = $(this) ;
			    	$(".ui-customize-select-list").addClass("dd-hide") ;
			    	$("#exchangeTable tr").removeClass("hlight-bk-yellow") ;
			    }) ;
			}
		} ,
		
		/**
		 * 
		 */
		_initVoteTrack:function(){
			
			$(".change-user-nick-click").click(function(){
				$(".change-rate-count").find(".input-box").addClass('dd-hide') ;
				$(".change-user-nick").find(".input-box").removeClass('dd-hide') ;
			}) ;
			//end $(".change-user-nick-click").click(function(){
			
			$(".change-rate-count-click").click(function(){
				$(".change-user-nick").find(".input-box").addClass('dd-hide') ;
				$(".change-rate-count").find(".input-box").removeClass('dd-hide') ;
			}) ;
			//end $(".change-rate-count-click").click(function(){
			
			$(".change-user-nick").find('.submit').click(function(){
				var userNick = $(".change-user-nick").find('.input').val() ;
				if(userNick == ''){
					alert('�������ǳ�');
					return ;
				}
				var userId = $(this).attr('data-user-id') ;
				var promItemId = $(this).attr("data-prom-item-id") ;
				if(userId == ''){
					alert('�û�����')
					return ;
				}
				if(promItemId == ''){
					alert('promItemId error��')
					return ;
				}
				
				//�޸�Ʊ��
				var url = bopsRoot + '/bops/dcome/qq/remote/update_nick_ajax.htm' ;
				$.ajax({
					url : url ,
					type : "POST" ,
					data : {promItemId:promItemId,userId:userId,userNick:encodeURI(userNick)},
					success : function(data){
						var code = data.json.code ;
						if(code == 'success'){
							window.location.reload();
						} else if(code == 'ill_args'){
							var detail = data.json.detail ;
							if(detail == 'dcome.user.nick.duplicate'){
								alert('�ǳ��Ѿ�����') ;
							} else {
								alert('err:' + detail ) ;
							}
						}
					} ,
					error : function(data){
						alert('err:' + data ) ;
					}
				}) ;
			}) ;
			
			$(".change-rate-count").find(".submit").click(function(){
				var rateCount = $(".change-rate-count").find(".input").val() ;
				var promItemId = $(this).attr("data-prom-item-id") ;
				var userId = $(this).attr('data-user-id') ;
				if(rateCount == '' || isNaN(rateCount)){
					alert('���������֡�') ;
					return ;
				}
				
				if(promItemId == ''){
					alert('promItemId error��')
					return ;
				}
				
				//�޸�Ʊ��
				var url = bopsRoot + '/bops/dcome/qq/remote/update_promotion_rate_ajax.htm' ;
				$.ajax({
					url : url ,
					type : "POST" ,
					data : {promItemId:promItemId,rateCount:rateCount},
					success : function(data){
						var code = data.json.code ;
						if(code == 'success'){
							window.location.reload();
						} else if(code == 'ill_args'){
							var detail = data.json.detail ;
							alert('err:' + detail ) ;
						}
					} ,
					error : function(data){
						alert('err:' + data ) ;
					}
				}) ;
				
			}) ;
			//end $(".change-rate-count").find(".submit").click(function(){
			
			$("#wish-item-box .W_close_color").click(function(){
				$("#wish-item-box").addClass('dd-hide') ;
			}) ;
			
			$(document).delegate('#wish-item-box .wish-click','click',function(){
				var _this = $(this) ;
				var url = bopsRoot + '/bops/dcome/qq/remote/takein_promotion_ajax.htm' ;
				var userId = _this.attr('data-user-id') ;
				var itemId = _this.attr('data-item-id') ;
				$.ajax({
					url : url ,
					type : "POST" ,
					data : {userId:userId,itemId:itemId},
					success : function(data){
						var code = data.json.code ;
						if(code == 'success'){
							window.location.reload();
						} else if(code == 'ill_args'){
							var detail = data.json.detail ;
							alert('err:' + detail ) ;
						}
					} ,
					error : function(data){
						alert('err:' + data ) ;
					}
				}) ;
			}) ;
			
			$(".free-wish-click").click(function(){
				$("#wish-item-box").removeClass('dd-hide') ;
				var tableSize = $("#wish-item-box .wish-item-table").find("tr").size() ;
				if(tableSize < 2){
					$("#wish-item-box .more").trigger('click') ;
				}
			}) ;
			//end $(".free-wish-click").click(function(){
			
			$("#wish-item-box .more").click(function(){
				var _this = $(this) ;
				var url = bopsRoot + '/bops/dcome/qq/remote/query_most_wish_ajax.htm' ;
				var promotionId = _this.attr('data-promotion-id') ;
				var userId = _this.attr('data-user-id') ;
				var page = parseInt(_this.attr('data-next-page')) ;
				var table = $("#wish-item-box .wish-item-table") ;
				$.ajax({
					url : url ,
					type : "POST" ,
					data : {promotionId:promotionId,page:page},
					success : function(data){
						var code = data.json.code ;
						if(code == 'success'){
							var itemList = data.json.data;
							if(itemList.length > 0){
								for(var i=0 ;i<itemList.length;i++){
									var item = itemList[i] ;
									var tr = '<tr><td><div class="title">' + item.itemTitle + '</div></td><td>&yen;' + item.itemPrice + '</td><td>' + item.wishCount + '</td><td><a href="javascript:;" class="wish-click" data-user-id="' + userId + '" data-item-id="' + item.itemId + '">�����ȡ</a></td></tr>'
									table.append(tr) ;
								}
							}
							
							_this.attr('data-next-page' , page + 1) ;
						} else if(code == 'ill_args'){
							var detail = data.json.detail ;
							alert('err:' + detail ) ;
						}
					} ,
					error : function(data){
						alert('err:' + data ) ;
					}
				}) ;
				
			}) ;
			//end $("#wish-item-box .more").click(function(){
			
			//����
			$(".calculate-rate-click").click(function(){
				if(!confirm('ȷ��������')){
					return ;
				}
				var url = bopsRoot + '/bops/dcome/qq/remote/calculate_promotion_rate_ajax.htm' ;
				$.ajax({
					url : url ,
					type : "POST" ,
					data : {},
					success : function(data){
						var code = data.json.code ;
						if(code == 'success'){
							alert('�Ѿ�������������н���¼��') ;
						} else if(code == 'ill_args'){
							var detail = data.json.detail ;
							alert('err:' + detail ) ;
						}
					} ,
					error : function(data){
						alert('err:' + data ) ;
					}
				}) ;
			});
			$(".send-message-btn").click(function() {
			    var userId = $("input.user-id").val();
				var message = $("input.message").val();
				if (isNaN(userId) || userId == '' || message == undefined || message == '') {
				    alert("����д�û�id����Ϣ����");
					return ;
				}
				$.ajax({
					url: bopsRoot + '/bops/dcome/qq/remote/prom/send_message_ajax.htm',
					type: "post",
					data: {userId: userId, message: encodeURI(message)},
					success: function(result) {
						var json = result.json;
						if (json.code == 'success') {
							alert("���ͳɹ�");
						} else {
							alert("����ʧ��");
						}
					},
					error: function() {
					
					}
				});
			});
		} ,
		
		_initSpiderItemList:function(){
			if($("#bopsPage").val() == "dcSpiderItemList") {
				$(".check-all-click").click(function(){
					var isChecked = $(this).attr('checked') != undefined;
					var allCheckbox = $("#itemTable").find("[type=checkbox]") ;
					if(isChecked){
						allCheckbox.attr('checked','checked') ;
					} else {
						allCheckbox.removeAttr('checked') ;
					}
				}) ;
				//end $(".check-all-click").click(function(){
				
				var allCheckbox = $("#itemTable").find("[type=checkbox]") ;
				if(allCheckbox.size() > 0){
					var nativeIds = "" ;
					allCheckbox.each(function(){
						var nativeId = $(this).val() ;
						if(nativeId != ''){
							nativeIds += nativeId + ',' ;
						}
					}) ;
					
					var url = bopsRoot + "/bops/dcome/qq/remote/check_item_exists_by_native_id_ajax.htm";
					
					$.ajax({
						url : url ,
						type : "POST" ,
						data : {nativeIds:nativeIds},
						success : function(data){
							var map = data.json.data ;
							allCheckbox.each(function(){
								var nativeId = $(this).val() ;
								if(map[nativeId] == true){
									$(this).remove() ;
								}
							}) ;
						} ,
						error : function(data){
							
						}
					}) ;
				}
				
			}
		} ,
		
		_initBatchAddItem:function(){
			if($("#bopsPage").val() == "dcBatchAddItem") {
				
				$(".remove-item-click").click(function(){
					$(this).closest("tr").remove();
				}) ;
				
				var numIidList = $('.taobao-num-iid') ;
				var url = bopsRoot + "/bops/dcome/qq/remote/check_item_exists_by_native_id_ajax.htm";
				var nativeIds = "" ;
				numIidList.each(function(){
					var nativeId = $(this).val() ;
					if(nativeId != ''){
						nativeIds += nativeId + ',' ;
					}
				}) ;
				
				$.ajax({
					url : url ,
					type : "POST" ,
					data : {nativeIds:nativeIds},
					success : function(data){
						var map = data.json.data ;
						var list = $(".taobao-item-exists") ;
						list.each(function(){
							var nativeId = $(this).attr('data-num-iid') ;
							if(map[nativeId] == true){
								$(this).removeClass('dd-hide') ;
							}
						}) ;
					} ,
					error : function(data){
						
					}
				}) ;
			}
		} ,
		
		_initDcAwardList:function(){
			if($("#ddzBopsPage").val() == "dcPromotionAwardList") {
				$('.award-send-click').click(function(){
					if(!confirm('ȷ����Ʒ�Ѿ����ţ�')){
						return ;
					}
					var url = ddzBopsRoot + "/bops/dcome/qq/remote/update_prom_award_send_status_ajax.htm";
					var id = $(this).attr('data-award-id') ;
					$.ajax({
			    		url : url ,
						type : "POST" ,
						data : {id:id , status:'S'},
						success : function(data){
							var json = data.json ;
							var code = json.code ;
							var data = json.data ;
							var detail = json.detail ;
							if(code == 'success') { 
								alert('��Ʒ�ѷ���') ;
								window.location.reload() ;
							} else {
								alert('����ʧ�ܣ� ' + detail ) ;
							}
						} ,
						error : function(data){
							alert('����ʧ�� ��' + data ) ;
						}
					});
				});
				//end $('.award-send-click').click(function(){
				
				$('.award-cheat-click').click(function(){
					var _this = $(this) ;
					var treatDetailDialog = $("#treatDetailDialog") ;
					
					var promotionId = $(this).attr('data-promotion-id') ;
					var userId = $(this).attr('data-user-id') ;
					var url = ddzBopsRoot + "/bops/dcome/qq/remote/prom/scan_award_user_cheat_ajax.htm";
					$.ajax({
			    		url : url ,
						type : "POST" ,
						data : {promotionId:promotionId,userId:userId},
						success : function(data){
							
							treatDetailDialog.find('.treat-ip-list').empty() ;
							treatDetailDialog.find('.treat-ubid-list').empty();
							treatDetailDialog.find('.treat-award-history-list').empty() ;
							
							var json = data.json ;
							var code = json.code ;
							var data = json.data ;
							var detail = json.detail ;
							if(code == 'success') { 
								var treatLoginIpList = data.treatLoginIpList ;
								var treatUbidList = data.treatUbidList ;
								var awardHistoryList = data.awardHistoryList ;
								var user = data.user ;
								
								if(treatLoginIpList.length > 0){
									for(var i=0 ;i<treatLoginIpList.length;i++){
										var treatLoginIp = treatLoginIpList[i] ;
										var treat_ip_template = treatDetailDialog.find('.treat-ip-template').html() ;
										treat_ip_template = treat_ip_template.replaceAll('@ip-user-name@' , treatLoginIp.nick) ;
										treat_ip_template = treat_ip_template.replaceAll('@ip-user-id@' , treatLoginIp.userId) ;
										treat_ip_template = treat_ip_template.replaceAll('@ip-gmt-create@' , treatLoginIp.gmtCreateFormat) ;
										treat_ip_template = treat_ip_template.replaceAll('@ip-ubid@' , treatLoginIp.ubid) ;
										treat_ip_template = treat_ip_template.replaceAll('@ip-agent@' , treatLoginIp.clientAgent) ;
										treat_ip_template = treat_ip_template.replaceAll('@ip-login-ip@' , treatLoginIp.loginIpStr) ;
										
										treatDetailDialog.find('.treat-ip-list').append(treat_ip_template) ;
									}
								} else {
									treatDetailDialog.find('.treat-ip-list').append('<li>��</li>') ;
								}
								
								if(treatUbidList.length > 0){
									for(var i=0 ;i<treatUbidList.length;i++){
										var treatUbid = treatUbidList[i] ;
										var treat_ubid_template = treatDetailDialog.find('.treat-ubid-template').html() ;
										treat_ubid_template = treat_ubid_template.replaceAll('@ubid-user-name@' , treatUbid.nick) ;
										treat_ubid_template = treat_ubid_template.replaceAll('@ubid-user-id@' , treatUbid.userId) ;
										treat_ubid_template = treat_ubid_template.replaceAll('@ubid-gmt-create@' , treatUbid.gmtCreateFormat) ;
										treat_ubid_template = treat_ubid_template.replaceAll('@ubid-ubid@' , treatUbid.ubid) ;
										treat_ubid_template = treat_ubid_template.replaceAll('@ubid-agent@' , treatUbid.clientAgent) ;
										treat_ubid_template = treat_ubid_template.replaceAll('@ubid-login-ip@' , treatUbid.loginIpStr) ;
										treatDetailDialog.find('.treat-ubid-list').append(treat_ubid_template) ;
									}
								} else {
									treatDetailDialog.find('.treat-ubid-list').append('<li>��</li>') ;
								}
								
								if(awardHistoryList.length > 0){
									for(var i=0 ;i<awardHistoryList.length;i++){
										var awardHistory = awardHistoryList[i] ;
										var treat_award_history_template = treatDetailDialog.find('.treat-award-history-template').html() ;
										treat_award_history_template = treat_award_history_template.replaceAll('@award-history-gmt-create@' , awardHistory.gmtCreateFormat) ;
										treat_award_history_template = treat_award_history_template.replaceAll('@award-history-item-id@' , awardHistory.itemId) ;
										treatDetailDialog.find('.treat-award-history-list').append(treat_award_history_template) ;
									}
								} else {
									treatDetailDialog.find('.treat-award-history-list').append('<li>��</li>') ;
								}
								if(user != undefined){
									treatDetailDialog.find('.user').html(user.nick + '(' + user.userId + ')&nbsp;ע��ʱ�䣺' + user.gmtCreateFormat) ;
								} else {
									treatDetailDialog.find('.user').html('�û�������...') ;
								}
								var treatDetailDialogLeft = _this.offset().left - treatDetailDialog.width() ;
								var treatDetailDialogTop = _this.offset().top ;
								treatDetailDialog.css({left:treatDetailDialogLeft, top : treatDetailDialogTop}) ;
								treatDetailDialog.removeClass('dd-hide') ;
								
							} else {
								alert('����ʧ�ܣ� ' + detail ) ;
							}
						} ,
						error : function(data){
							alert('����ʧ�� ��' + data ) ;
						}
					});
				}) ;
				//end $('.award-cheat-click').click(function(){
				
				$("#treatDetailDialog .close").click(function(){
					$("#treatDetailDialog").addClass('dd-hide') ;
				}) ;
				
				//
				$('.award-review-click').click(function(){
					var _thisReviewBtn = $(this) ;
					if(!confirm('��Ǹü�¼Ϊ���ͨ���������û������н�֪ͨ��')){
						return ;
					}
					var reviewUrl = ddzBopsRoot + '/bops/dcome/qq/remote/update_prom_award_status_ajax.htm' ;
					var awardId = $(this).attr('data-award-id') ;
					var status = 'S' ;
					
					$.ajax({
						url:reviewUrl , 
						type: "POST",
						data: {id: awardId , status:status},
						success:function(data){
							var result = data.json;
							if (result.code == "success") {
								_thisReviewBtn.addClass('dd-hide') ;
								alert('�����ɹ���') ;
							}else{
								alert("error: " + result.detail);
							}
						} ,
						error:function(data){
							alert('error��' + data);
						}
					});
				}) ;
				//end $('.award-review-click').click(function(){
				
				$('.award-delivery-click').click(function(e){
					e.stopPropagation(); 
					$(this).closest('table').find('tr').removeClass('highlight-bk') ;
					$(this).closest('table').find('.user-delivery-dialog').addClass('dd-hide') ;
					$(this).parent().find('.user-delivery-dialog').removeClass('dd-hide') ;
					$(this).closest('tr').addClass('highlight-bk') ;
				}) ;
				
				$(".send-btn").click(function() {
				    var _this = $(this);
					var _container = _this.closest("td");
					var userId = _this.attr("data-user-id");
					var message = _container.find(".message").val();
					if (message == undefined || message == '' || isNaN(userId)) {
					    alert("����д��Ϣ����");
						return;
					}
					$.ajax({
					    url: ddzBopsRoot + '/bops/dcome/qq/remote/prom/send_message_ajax.htm',
						type: "post",
						data: {userId: userId, message: encodeURI(message)},
						success: function(result) {
						    var json = result.json;
							if (json.code == 'success') {
							    alert("���ͳɹ�");
							} else {
							    alert("����ʧ��");
							}
						},
						error: function() {
						
						}
					});
				});
				
				$(".user-delivery-dialog").each(function(){
					var _this = $(this) ;
					$(this).find('.W_close_color').click(function(){
						_this.addClass('dd-hide') ;
						_this.closest('tr').removeClass('highlight-bk') ;
					}) ;
					
				}) ;
				
//				$(document).click(function(e){
//					$('.prom-award-table').find('tr').removeClass('highlight-bk') ;
//					$('.prom-award-table').find('.user-delivery-dialog').addClass('dd-hide') ;
//			    }) ;
			}
		} ,
		
		/**
		 * �����Ʒ������
		 */
		_initDcAddSceneItem:function(){
			if($("#ddzBopsPage").val() == "dcAddSceneItem") {
				var exclusionIds = $("#dcSceneDetails").val() ;
				if(exclusionIds != ''){
					var exclusionIdList = exclusionIds.split(","); 
					//��ʼ�����������Ѿ���ӵ�ȥ��
					$("[data-init-add-scene-item]").each(function(){
						var _this = $(this) ;
						var itemId = _this.attr("data-item-id") ;
						if(itemId != ''){
							for(var i = 0 ;i<exclusionIdList.length;i++){
								if(exclusionIdList[i] == itemId){
									_this.addClass("dd-hide") ;
									_this.parent().find("[data-init-remove-scene-item]").removeClass("dd-hide") ;
									break ;
								}
							}
						}
					});
				}
				//end $("[data-init-addSceneItem-itemId]").each(function(){
				
				//Ϊ  ���add�¼�
				$("[data-init-add-scene-item]").click(function(){
					if(confirm('��ӣ�')){
						var url = ddzBopsRoot + "/bops/dcome/qq/remote/add_scene_item_ajax.htm";
						var _this = $(this) ;
						var itemId = _this.attr("data-item-id") ;
						var sceneId = _this.attr("data-scene-id") ;
						$.ajax({
				    		url : url ,
							type : "POST" ,
							data : {itemId:itemId , sceneId:sceneId},
							success : function(data){
								var json = data.json ;
								var code = json.code ;
								var data = json.data ;
								var detail = json.detail ;
								if(code == 'success') { 
									_this.addClass("dd-hide") ;
									_this.parent().find("[data-init-remove-scene-item]").removeClass("dd-hide") ;
								} else {
									if(detail == 'dcome.addSceneItem.category.maxItemSize'){
										alert('����ʧ�ܣ�����Ŀ���Ѿ��ﵽ��Ʒ���ޣ�');
										return ;
									}
									alert('����ʧ�ܣ� ' + detail ) ;
								}
							} ,
							error : function(data){
								alert('����ʧ�� ��' + data ) ;
							}
						});
					}
				});
				//end $("[data-init-add-scene-item]").find("a").click(function(){
				
				//���remove�¼�
				$("[data-init-remove-scene-item]").click(function(){
					if(confirm('�Ƴ���')){
						var url = ddzBopsRoot + "/bops/dcome/qq/remote/remove_scene_item_ajax.htm";
						var _this = $(this) ;
						var itemId = _this.attr("data-item-id") ;
						var sceneId = _this.attr("data-scene-id") ;
						$.ajax({
				    		url : url ,
							type : "POST" ,
							data : {itemId:itemId , sceneId:sceneId},
							success : function(data){
								var json = data.json ;
								var code = json.code ;
								var data = json.data ;
								var detail = json.detail ;
								if(code == 'success') { 
									_this.addClass("dd-hide") ;
									_this.parent().find("[data-init-add-scene-item]").removeClass("dd-hide") ;
								} else {
									alert('����ʧ�ܣ� ' + detail ) ;
								}
							} ,
							error : function(data){
								alert('����ʧ�� ��' + data ) ;
							}
						});
					}
				});
				//end $("[data-init-remove-scene-item]").find("a").click(function(){
				
				/**
				 * ͳ��ÿ����Ŀ�ж�����Ʒ
				 */
				var _initCatItemCount = $("[data-init-cat-item-count]") ; 
				if(_initCatItemCount.length > 0){
					var sceneId = $("#sceneId").val() ;
					var url = ddzBopsRoot + "/bops/dcome/qq/remote/group_scene_cats_ajax.htm";
					$.ajax({
			    		url : url ,
						type : "POST" ,
						data : {sceneId:sceneId},
						success : function(data){
							var json = data.json ;
							var code = json.code ;
							var data = json.data ;
							var detail = json.detail ;
							if(code == 'success') { 
								$("[data-init-cat-item-count]").each(function(){
									var __this = $(this) ;
									var catId = __this.attr("data-cat-id") ;
									if(data.length > 0){
										for(var i=0 ;i<data.length;i++){
											var _catid = data[i].categoryId ;
											var _itemCount = data[i].itemCount ;
											if(_catid == catId){
												__this.html(_itemCount) ;
												break ;
											}
										}
									}
									if(__this.val() == ''){
										__this.val(0) ;
									}
								}) ;
							} else {
								alert('��ȡ��Ŀ��Ϣʧ�ܣ� ' + detail ) ;
							}
						} ,
						error : function(data){
							alert('��ȡ��Ŀ��Ϣʧ�� ��' + data ) ;
						}
					});
				}
				//end if(_initCatItemCount.length > 0){
			}
		} ,
		
		/**
		 * �����б�
		 */
		_initDcCommentList:function(){
			if($("#ddzBopsPage").val() == "dcCommentList") {
				$("[data-comment-status]").click(function(){
					var _this = $(this);
					
					var status = _this.attr("data-comment-status");
					var commentId = _this.attr("data-comment-id") ;
					var url = ddzBopsRoot + "/bops/dcome/qq/remote/set_comment_status_ajax.htm";
					if(status != '' && commentId != ''){
						
						$.ajax({
				    		url : url ,
							type : "POST" ,
							data : {commentId:commentId , status:status},
							success : function(data){
								var json = data.json ;
								var code = json.code ;
								var data = json.data ;
								var detail = json.detail ;
								if(code == 'success') { 
									alert("�������۳ɹ�");
									window.location.reload();
								} else {
									alert('��������״̬ʧ�ܣ� ' + detail ) ;
								}
							} ,
							error : function(data){
								alert('��������״̬ʧ�� ��' + data ) ;
							}
						});
					}
					
				});
				//end $("[data-commentStatus]").click(function(){
				
				//����...
				$("[data-toggle-id]").click(function(){
					var id = $(this).attr("data-toggle-id") ;
					var toggle = $("#"+id) ;
					if(toggle.hasClass("dd-hide")){
						toggle.removeClass("dd-hide") ;
					}else{
						toggle.addClass("dd-hide") ;
					}
				});
				//end $("[data-toggle-id]").click(function(){
			}
		} ,
		
		
		/**
		 * �����б�
		 */
		_initDcSceneList:function(){
			if($("#ddzBopsPage").val() == "dcSceneList") {
				self.activeScene() ;
			}
		} ,
		
		/**
		 * ����detailҳ
		 */
		_initDcSceneDetail:function(){
			if($("#ddzBopsPage").val() == "dcSceneDetail") {
				
				//ɾ������
				$("[data-removeItemId]").click(function(){
					if(!confirm('ɾ�������¸���Ʒ��')){
						return ;
					}
					var _this = $(this) ;
					var itemId = $(this).attr('data-removeItemId') ;
					var sceneId = $(this).attr('data-sceneId') ;
					var ddzBopsRoot = $("#ddzBopsRoot").val() ; ;
					var url = ddzBopsRoot + "/bops/dcome/qq/remote/remove_scene_item_ajax.htm";
					$.ajax({
			    		url : url ,
						type : "POST" ,
						data : {itemId:itemId , sceneId:sceneId},
						success : function(data){
							var json = data.json ;
							var code = json.code ;
							var data = json.data ;
							var detail = json.detail ;
							if(code == 'success') { 
								alert('�����ɹ�');
								window.location.reload();
							} else {
								alert('����ʧ��. ԭ��' + detail ) ;
							}
						} ,
						error : function(data){
							alert('����ʧ��. ԭ��' + data ) ;
						}
					});
				}) ;
				//end $("[data-removeItemId]").click(function(){
				
				self.activeScene() ;
			}
		} ,
		
		/**
		 * ��Ʒ�б�ҳ | ���������Ʒҳ
		 */
		_initDcItemList:function(){
			if($("#ddzBopsPage").val() == "dcItemList") {
				
				//�����Ʒ������
				$("[data-addSceneItemId]").click(function(){
					if(!confirm('�����Ʒ��������')){
						return ;
					}
					var _this = $(this) ;
					var itemId = $(this).attr('data-addSceneItemId') ;
					var sceneId = $(this).attr('data-sceneId') ;
					var ddzBopsRoot = $("#ddzBopsRoot").val() ; ;
					var url = ddzBopsRoot + "/bops/dcome/qq/remote/add_scene_item_ajax.htm";
					$.ajax({
			    		url : url ,
						type : "POST" ,
						data : {itemId:itemId , sceneId:sceneId},
						success : function(data){
							var json = data.json ;
							var code = json.code ;
							var data = json.data ;
							var detail = json.detail ;
							if(code == 'success') { 
								alert('�����ɹ�');
								window.location.reload();
							} else {
								alert('����ʧ��. ԭ��' + detail ) ;
							}
						} ,
						error : function(data){
							alert('����ʧ��. ԭ��' + data ) ;
						}
					});
				}) ;
				//end $("[data-addSceneItemId]").click(function(){
				
				$(".limit-buy-click").click(function(){
					var _this = $(this) ;
					var recommType = $(this).attr('data-recomm-type') ;
					if(recommType == 'LB'){
						if(!confirm('ȡ����ʱ������')){
							return ;
						}
					} else {
						if(!confirm('�����ʱ������')){
							return ;
						}
					}
					
					var itemId = $(this).attr('data-item-id') ;
					var ddzBopsRoot = $("#ddzBopsRoot").val() ; ;
					var url = ddzBopsRoot + "/bops/dcome/qq/remote/update_item_recomm_type_ajax.htm";
					if(recommType == 'LB'){
						recommType = '' ;
					} else {
						recommType = 'LB' ;
					}
					$.ajax({
			    		url : url ,
						type : "POST" ,
						data : {itemId:itemId , recommType:recommType},
						success : function(data){
							var json = data.json ;
							var code = json.code ;
							var data = json.data ;
							var detail = json.detail ;
							if(code == 'success') { 
								alert('�����ɹ�');
								window.location.reload();
							} else {
								alert('����ʧ��. ԭ��' + detail ) ;
							}
						} ,
						error : function(data){
							alert('����ʧ��. ԭ��' + data ) ;
						}
					});
				});
				$(".huodong-buy-click").click(function(){
				    var _this = $(this) ;
					var recommType = $(this).attr('data-recomm-type') ;
					if(recommType == 'HD'){
						if(!confirm('�ӻ��ɾ������Ʒ��')){
							return ;
						}
						recommType = '' ;
					} else {
						if(!confirm('�����Ʒ�����')){
							return ;
						}
						recommType = 'HD' ;
					}
					
					var itemId = $(this).attr('data-item-id') ;
					var ddzBopsRoot = $("#ddzBopsRoot").val() ; ;
					var url = ddzBopsRoot + "/bops/dcome/qq/remote/update_item_recomm_type_ajax.htm";
					$.ajax({
			    		url : url ,
						type : "POST" ,
						data : {itemId:itemId , recommType:recommType},
						success : function(data){
							var json = data.json ;
							var code = json.code ;
							var data = json.data ;
							var detail = json.detail ;
							if(code == 'success') { 
								alert('�����ɹ�');
								window.location.reload();
							} else {
								alert('����ʧ��. ԭ��' + detail ) ;
							}
						} ,
						error : function(data){
							alert('����ʧ��. ԭ��' + data ) ;
						}
					});
				});
				
				$(".postal-click").click(function(){
					var _this = $(this);
					var postalEnable = _this.attr("data-postal-enable");
					if (postalEnable == "Y") {
					    postalEnable = "N";
					} else {
					    postalEnable = "Y";
					}
					if(postalEnable == 'N'){
						if(!confirm('�޸���ƷΪ�ʷ�����')){
							return ;
						}
					} else {
						if(!confirm('�޸���ƷΪ���ʣ�')){
							return ;
						}
					}
					
					var itemId = _this.attr("data-item-id");
					
					
					$.ajax({
			    		url : ddzBopsRoot + "/bops/dcome/qq/remote/update_item_postal_ajax.htm",
						type : "POST" ,
						data : {itemId:itemId , postalEnable:postalEnable},
						success : function(result){
						    var json = result.json;
							if (json.code == 'success') {
							    alert('�����ɹ�');
								window.location.reload();
							} else {
							    alert('����ʧ��. ԭ��' + json.detail) ;
							}
						},
						error: function() {
						    alert('����ʧ��. ԭ��' + data ) ;
						}
					});
				});
				//end $(".postal-click").click(function(){
				
				$(".sell-status-click").click(function(e){
					var itemId = $(this).attr("data-item-id");
					var status = $(this).attr("data-item-status");
					var toStatus = null ;
					if(status == 'D'){
						toStatus = 'N' ;
					} else {
						toStatus = 'D' ;
					}
					if(toStatus == 'D'){
						if(!confirm("ȷ���¼���Ʒ��")) {
						    return;
						}
					} else {
						if(!confirm("ȷ���ϼ���Ʒ��")) {
						    return;
						}
					}
					
					var url = $('#ddzBopsRoot').val() + '/bops/dcome/qq/remote/update_item_status_ajax.htm';
					
					$.ajax({
						url : url ,
						type : "POST" ,
						data : {"itemId":itemId, "itemStatus": toStatus},
						success : function(data) {
						    var json = data.json;
							window.location.reload();
						},
						error: function(data) {
						    alert("internal error" + data);
						}
					});
				});
				//end $(".sell-status-click").click(function(e){
				
				//����
				$(".add-exchange-click").click(function(e){
					if(!confirm('ȷ����Ӷһ���')){
						return ;
					}
					var url = $('#ddzBopsRoot').val() + '/bops/dcome/qq/remote/add_exchange_item_ajax.htm';
					var itemId = $(this).attr("data-item-id");
					$.ajax({
						url : url ,
						type : "POST" ,
						data : {"itemId":itemId},
						success : function(data) {
						    var json = data.json;
							if (json.code = "success") {
								 alert("��ӳɹ���");
							} else {
							    alert("����ʧ�ܣ�����ϵ����Ա��");
							}
						},
						error: function(data) {
						    alert("internal error");
						}
					});
				});
				//end $(".normal-list .add-auction-click").click(function(e){
				
				//
				$(".update-display-order-click").click(function(){
//					if(!confirm('����ǰ��')){
//						return ;
//					}
					var url = $('#ddzBopsRoot').val() + '/bops/dcome/qq/remote/update_item_display_order_ajax.htm';
					var itemId = $(this).attr("data-item-id");
					$.ajax({
						url : url ,
						type : "POST" ,
						data : {"itemId":itemId},
						success : function(data) {
						    var json = data.json;
							if (json.code = "success") {
								 alert("�ɹ���");
								 window.location.reload();
							} else {
							    alert("����ʧ�ܣ�����ϵ����Ա��");
							}
						},
						error: function(data) {
						    alert("internal error");
						}
					});
				}) ;
				
				$(".accept-pgc-click").click(function() {
				    var _this = $(this);
					var itemId = _this.attr("data-item-id");
					if (itemId == '' || isNaN(itemId)) {
					    alert("��Ʒid��������");
						return;
					}
					if(!confirm('��ȷ����Ʒ�Ļ�۸񣬷������ͻ��ָ��û���')){
					    return;
					}
					$.ajax({
					    url: $('#ddzBopsRoot').val() + '/bops/dcome/qq/remote/accept_ugc_ajax.htm',
						type: "post",
						data: {itemId: itemId},
						success: function(result) {
						    var json = result.json;
							if (json.code == 'success') {
							    alert("���³ɹ��������û�" + json.data + '����');
								window.location.href = window.location.href;
							} else {
							    alert("����ʧ��");
							}
						},
						error: function() {
						    alert("����ʧ��");
						}
					});
				});
				
				//������������¼ܡ�
				$(".batch-onoff-sale-click").click(function(){
					$(".batch-onoff-sale-display").toggle() ;
					$(".batch-onoff-sale-click").addClass('dd-hide') ;
				}) ;
				//end $(".batch-onoff-sale-click").click(function(){
				
				$(".batch-onoff-sale-cancel").click(function(){
					$(".batch-onoff-sale-display").hide();
					$(".batch-onoff-sale-click").removeClass('dd-hide') ;
				}) ;
				
				//���¼�ajax
				$(".batch-onoff-sale-operate-click").click(function(){
					var checkedList = $(".batch-onoff-sale-check-list").find("input:checked") ;
					if(checkedList.size() <= 0){
						alert('��ѡ��Ҫ�������¼ܵ���Ʒ');
						return ;
					}
					
					var toStatus = $(this).attr('data-to-status') ;
					if(toStatus == 'N'){
						if(!confirm('ȷ�������ϼ�ѡ�е�' + checkedList.size() + '����Ʒ��')){
							return ;
						}
					} else if (toStatus == 'D'){
						if(!confirm('ȷ�������¼�ѡ�е�' + checkedList.size() + '����Ʒ��')){
							return ;
						}
					} else {
						return ;
					}
					var itemIdList = '' ;
					checkedList.each(function(){
						var itemId = $(this).attr('data-item-id') ;
						if(itemId != ''){
							itemIdList += itemId + ',';
						}
					}) ;
					
					if(itemIdList == ''){
						alert('û��ѡ����Ʒ��');
						return ;
					}
					
					var url = $('#ddzBopsRoot').val() + '/bops/dcome/qq/remote/update_item_status_ajax.htm';
					
					$.ajax({
						url : url ,
						type : "POST" ,
						data : {"itemIds":itemIdList, "itemStatus": toStatus},
						success : function(data) {
						    var json = data.json;
						    alert('�����ɹ���');
							window.location.reload();
						},
						error: function(data) {
						    alert("internal error" + data);
						}
					});
				}) ;
				//end $(".batch-onoff-sale-operate-click").click(function(){
				
				$(".batch-onoff-sale-check-all").click(function(){
					var isChecked = $(this).attr('checked') != undefined;
						$(".batch-onoff-sale-check-list").find("input").each(function(){
							if($(this).attr('type') == 'checkbox'){
								if(isChecked){
									$(this).attr('checked','checked') ;
								} else {
									$(this).removeAttr('checked') ;
								}
							}
						}) ;
					
				}) ;
				
				//end �������¼�
				
				$(document).click(function(e){
					var e = $(this) ;
					$(".ui-customize-select-list").addClass("dd-hide") ;
					$(".settleTableTr").removeClass("highlightBk") ;
					$("#alipay_detail_tips").addClass("dd-hide");
				}); 
				
				$("#settleIptMoreBtn").click(function(){
					$("#settleIptMore").toggle() ;
				});
				
				//��ʼ������
				$( "#gmtCreateStart" ).datepicker({ dateFormat: "yy-mm-dd" ,changeYear:true});
				$( "#gmtCreateEnd" ).datepicker({ dateFormat: "yy-mm-dd" ,changeYear:true});
				$( "#gmtModifiedStart" ).datepicker({ dateFormat: "yy-mm-dd" ,changeYear:true});
				$( "#gmtModifiedEnd" ).datepicker({ dateFormat: "yy-mm-dd" ,changeYear:true});
			}
		} ,
		
		/**
		 * ����|�޸ĳ���
		 */
		_initDcCreateScene:function(){
			if($("#ddzBopsPage").val() == "dcCreateScene") {
				self.activeScene() ;
				
				var catIds = $("#categoryIds").val() ;
				if(catIds == ''){
					return ;
				}
				var catList = catIds.split(",");
				$("[data-init-categoryIds]").each(function(){
					var _this = $(this) ;
					for(var i = 0 ;i<catList.length;i++){
						if(catList[i] == _this.val()){
							_this.attr("checked",true) ;
							break ;
						}
					}
				});
			}
		},
		
		
		/**
		 * �����
		 */
		activeScene:function(){
			$("[data-sceneActive]").click(function(){
				if(!confirm('ȷ���޸ļ���״̬��')){
					return ;
				}
				var _this = $(this) ;
				var active = $(this).attr('data-sceneActive') ;
				var sceneId = $(this).attr('data-sceneId') ;
				var ddzBopsRoot = $("#ddzBopsRoot").val() ; ;
				var url = ddzBopsRoot + "/bops/dcome/qq/remote/set_scene_active_ajax.htm";
				$.ajax({
		    		url : url ,
					type : "POST" ,
					data : {active:active , sceneId:sceneId},
					success : function(data){
						var json = data.json ;
						var code = json.code ;
						var data = json.data ;
						var detail = json.detail ;
						if(code == 'success') { 
							alert('�����ɹ�');
							window.location.reload();
						} else {
							alert('����ʧ��. ԭ��' + detail ) ;
						}
					} ,
					error : function(data){
						alert('����ʧ��. ԭ��' + data ) ;
					}
				});
			}) ;
		} ,
		
		/**
		 * JSУ��
		 */
		_initValidator:function(){
			//����������
			$("[data-validator-number]").each(function(){
				var _this = $(this) ;
				$(this).on("keyup",function(){
					var _val = _this.val() ;
					if(_val != ''){
						$(this).val(_val.replace(/[^\d\.]/g,'')) ;
					}
				});
				$(this).on("afterpaste",function(){
					var _val = _this.val() ;
					if(_val != ''){
						$(this).val(_val.replace(/[^\d\.]/g,'')) ;
					}
				}) ;
			});
		} ,
		
		/**
		 * ��ʼ�����ڿؼ�
		 */
		_initDatePicker:function(){
			$("[data-init-datepicker]").each(function(){
				var format = $(this).attr("data-init-datepicker") ;
				if(format == ''){
					format = 'yy-mm-dd' ;
				}
				$(this).datepicker({ dateFormat: format  ,changeYear:true});
			});
			
		} ,
		
		/**
		 * ��ʼ�� ��Ʒ��Ŀ����
		 */
		_initDcCategoriesOptions:function(){
			$("[data-init-dcCategorySelect]").each(function(){
				var url = ddzBopsRoot + "/bops/dcome/qq/remote/query_categories_ajax.htm";
				var _this = $(this) ;
				var _catLevel = _this.attr("catLevel") ;
				var _exclutionIds = _this.attr("exclutionIds") ;
				_this.empty() ;
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
							if (_this.attr("include-all") == "true") {
							    _this.append("<option value=''>ȫ��</option>");
							}
							if(data.length > 0){
								for(var i=0 ;i<data.length;i++){
									var cat = data[i] ;
									_this.append("<option value='" + cat.id + "'>" + cat.name +"</option>");
								}
								if(_this.attr("data-selCatId") != ''){
									_this.val(_this.attr("data-selCatId"));
								}
							}
							
							
						} else {
							alert('��ʼ����Ŀʧ�ܣ�' + detail ) ;
						}
					} ,
					error : function(data){
						alert('��ʼ����Ŀʧ�ܣ�' + data ) ;
					}
				});
				
			}) ;
			//end $("[data-init-dcCategorySelect]").each(function(){
			
			$("[data-init-dc-category-val]").each(function(){
				var url = ddzBopsRoot + "/bops/dcome/qq/remote/query_categories_ajax.htm";
				var _this = $(this) ;
				var _catLevel = _this.attr("catLevel") ;
				var _catId = _this.attr("data-cat-id") ;
				
				$.ajax({
		    		url : url ,
					type : "POST" ,
					data : {catId:_catId},
					success : function(data){
						var json = data.json ;
						var code = json.code ;
						var data = json.data ;
						var detail = json.detail ;
						if(code == 'success') { 
							
							if(data.length > 0){
								for(var i=0 ;i<data.length;i++){
									var cat = data[i] ;
									_this.html(cat.name) ;
									break ;
								}
							}
							
						} else {
							alert('��ʼ����Ŀʧ�ܣ�' + detail ) ;
						}
					} ,
					error : function(data){
						alert('��ʼ����Ŀʧ�ܣ�' + data ) ;
					}
				});
				
			}) ;
		} ,
		
		/**
		 * ��ʼ��ϵͳAPP select ����
		 * @data-parentCatId ������Ŀ
		 * @data-catLevel ��Ŀ����
		 * @data-selCatId ѡ�е���Ŀ
		 */
		_initDcSystemOptions:function(){
			$("[data-init-dcSystemSelect]").each(function(){
				var ddzBopsRoot = $("#ddzBopsRoot").val() ; ;
				var url = ddzBopsRoot + "/bops/dcome/qq/remote/query_systems_ajax.htm";
				var _this = $(this) ;
				var type = _this.attr("data-init-dcSystemSelect");
				var systemId = _this.attr("systemId") ;
				
				if(type == 'select'){
					_this.empty() ;
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
								
								if(data.length > 0){
									for(var i=0 ;i<data.length;i++){
										var sys = data[i] ;
										_this.append("<option value='" + sys.id + "'>" + sys.name +"</option>");
									}
									if(_this.attr("data-selSysId") != ''){
										_this.val(_this.attr("data-selSysId"));
									}
								}
								
								
							} else {
								alert('��ʼ��ϵͳAPPʧ�ܣ�' + detail ) ;
							}
						} ,
						error : function(data){
							alert('��ʼ��ϵͳAPPʧ�ܣ�' + data ) ;
						}
					});
				} // end type=='select'
				
				if(type == 'name'){
					$.ajax({
			    		url : url ,
						type : "POST" ,
						data : {systemId:systemId},
						success : function(data){
							var json = data.json ;
							var code = json.code ;
							var data = json.data ;
							var detail = json.detail ;
							if(code == 'success') { 
								
								if(data.length > 0){
									for(var i=0 ;i<data.length;i++){
										var sys = data[i] ;
										_this.html(sys.name) ;
										break ;
									}
								}
								
								
							} else {
								alert('��ʼ��ϵͳAPPʧ�ܣ�' + detail ) ;
							}
						} ,
						error : function(data){
							alert('��ʼ��ϵͳAPPʧ�ܣ�' + data ) ;
						}
					});
				}
			});
		} ,
		
		_initDcPromotion: function() {
			if($("#ddzBopsPage").val() == "dcPromotionList") {
				$(".promDetail").click(function() {
					var url = ddzBopsRoot + "/bops/dcome/qq/remote/prom/get_prom_awards.htm";
					var promId = $(this).attr("data-promid");
					var promDetailTr = $("tr[data-keepid='" + promId + "']");
					if (promDetailTr == null) {
						return ;
					}
					if (!promDetailTr.hasClass("dd-hide")) {
						promDetailTr.addClass("dd-hide");
						$(this).find("span").removeClass("icon-pull-up");
						$(this).find("span").addClass("icon-pull-down");
						return;
					}
					promDetailTr.removeClass("dd-hide");
					$(this).find("span").removeClass("icon-pull-down");
					$(this).find("span").addClass("icon-pull-up");
					
					if (promDetailTr.find("table tr").size() > 1) {
						return;
					}
					$.ajax({
						url:url,
						type: "POST",
						data: {promotionId: promId},
						success: function(data) {
							var result = data.promAwards;
							if (result.code == "success") {
								var promAwards = result.data;
								//var tbHtml = '' ;
								var detailTable = promDetailTr.find("table") ;
								if (promAwards.length > 0) {
									//tbHtml = '<table><tr><td>����ID</td><td>�ID</td><td>�н���ƷID</td><td>�н��û�ID</td><td>Ʊ��</td><td>�ջ�������</td><td>��ϵ�绰</td><td>��ַ</td><td>��Ʒ�������</td><tr>';
									var trStr = '' ;
									for (var i = 0; i < promAwards.length; i++) {
										var award = promAwards[i] ;
//										tbHtml = tbHtml + '<tr><td>' + promAwards[i].id + '</td><td>' + promAwards[i].promotionId + '</td><td>' + promAwards[i].promotionItemId + '</td><td>' + 
//										    promAwards[i].userId + '</td><td>' + promAwards[i].rateCount + '</td><td>' + promAwards[i].delName + '</td><td>' + promAwards[i].delMobile + '</td><td>' +
//										    promAwards[i].delAddr + '</td><td>' + promAwards[i].sendStatus + '</td></tr>';
										var template = $('#promDetailTrTemplate table').find('tbody').html() ;
										template = template.replaceAll('@id@' , award.id) ;
										template = template.replaceAll('@promotionId@' , award.promotionId) ;
										template = template.replaceAll('@promotionItemId@' , award.promotionItemId) ;
										template = template.replaceAll('@item_name@' , award.dcItemDTO.itemTitle) ;
										template = template.replaceAll('@item_id@' , award.dcItemDTO.id) ;
										template = template.replaceAll('@userId@' , award.userId) ;
										template = template.replaceAll('@userNick@' , award.userNick) ;
										template = template.replaceAll('@rateCount@' , award.rateCount) ;
										template = template.replaceAll('@delName@' , award.delName) ;
										template = template.replaceAll('@delMobile@' , award.delMobile) ;
										template = template.replaceAll('@delAddr@' , award.delAddr) ;
										template = template.replaceAll('@sendStatus@' , award.sendStatus) ;
										template = template.replaceAll('@reviewStatus@' , award.reviewStatus) ;
										template = template.replaceAll('@checkCode@' , award.checkCode) ;
										template = template.replaceAll('@rank@' , (i+1)) ;
										if(award.reviewStatus == 'S'){
											template = template.replaceAll('@award-review-display@' , 'dd-hide') ;
										}
											
										trStr += template ;
										
									}
									
									detailTable.find('tbody').append(trStr) ;
									detailTable.find('.award-review-click').click(function(){
										var _thisReviewBtn = $(this) ;
										if(!confirm('��Ǹü�¼Ϊ���ͨ�������ҷ����н�֪ͨ��')){
											return ;
										}
										var reviewUrl = ddzBopsRoot + '/bops/dcome/qq/remote/update_prom_award_status_ajax.htm' ;
										var awardId = $(this).attr('data-award-id') ;
										var status = 'S' ;
										
										$.ajax({
											url:reviewUrl , 
											type: "POST",
											data: {id: awardId , status:status},
											success:function(data){
												var result = data.json;
												if (result.code == "success") {
													_thisReviewBtn.addClass('dd-hide') ;
													alert('�����ɹ���') ;
												}else{
													alert("error: " + result.detail);
												}
											} ,
											error:function(data){
												alert('error��' + data);
											}
										});
									}) ;
									//tbHtml = tbHtml + '</tr>';
								} else {
									//tbHtml = "δ��ѯ����Ŀ�����¼"
									detailTable.find('tbody').append('<tr><td colspan=' + 13 + '>û�в�ѯ����¼</td></tr>') ;
								}
								//promDetailTr.find("td").html(tbHtml);
							} else {
								alert("error: " + result.detail);
							}
						},
						error: function(data) {
							alert('error ! ' + data) ;
						}
					});
				});
			}
		},
		
		_exchangeDetial: function() {
		    if ($("#ddzBopsPage").val() != "exchangeItemList") {
			    return;
			}
			$('#exchangeItemTable').find("tr").each(function() {
			    var _this = $(this);
				var exchangeId = _this.attr("data-exchange-id");
				if (!isNaN(exchangeId)) {
				    $.ajax({
					    url: ddzBopsRoot + "/bops/dcome/qq/remote/exchange_detail_ajax.htm",
						type: "post",
						data: {exchangeId: exchangeId},
						success: function(result) {
						    var json = result.json;
							if (json.code == 'success') {
							    var data = json.data;
							    var _detail = _this.find(".detail");
								_detail.html(_detail.html() + "/" + data.unReviewCount + "/" + data.sendCount);
							}
						},
						error: function() {
						
						}
					});
				}
			});
		},
		
		/**
		 * ����
		 */
		centerPoint:function(selector){
	
			if(selector == null || selector == undefined){
				return  ;
			}
			
			var clientLeft =  $(window).scrollLeft()  ;
			var clientTop =  $(window).scrollTop()  ;
			var clientWidth = $(window).width() ;
			var clientHeight = $(window).height() ;
			
			var left = clientLeft + (clientWidth - selector.width()) / 2 ;
			var top = clientTop + (clientHeight - selector.height()) / 2 ;
			selector.css({
				left:left,top:top
			}) ;
			
		},
		
		
		end:0
	});
})(jQuery);

jQuery(document).ready(function(){
	DD.BopsDcome.init();
});
