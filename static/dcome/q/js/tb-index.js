!(function(){
	
	var envRoot = $("#envRoot").val() ;
	var envStaticroot = $("#envStaticroot").val() ;
	var RegexEmail = /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/ ;
	var RegexMobile = /^1\d{10}$/ ;
	var newGuideUrl = "http://item.taobao.com/item.htm?id=9130136035" ;
	var self ;
	
	var index = {
				
		init:function(){	
			
			self = this ;
			self._initUserAccount() ;
			self._initIntegralPay() ;
			self._initSearchEvent() ;
			self._initSearchNewGuide() ;
			self._initIntelligentIpt() ;
			self._initQueryPromotion() ;
			self._initJfbStudyViedo() ;
			
		},
		
		/**
		 * ���ֱ���Ƶ
		 */
		_initJfbStudyViedo:function(){
			$(".jfb-study-video").click(function(){
				$('.ui-video-xbox .flash-src').attr('src','https://hi.alipay.com/cms/huohaier/jfb_flash.swf');
				$('.ui-video-xbox .flash-src').attr('value','https://hi.alipay.com/cms/huohaier/jfb_flash.swf');
				$('.ui-video-xbox').removeClass('dd-hide');
				$('#dcDialogOverlay').removeClass('dd-hide');
			}) ;
			
			$('.ui-video-xbox .close-flash-xbox').click(function(){
				$(".ui-video-xbox").addClass('dd-hide') ;
				$("#dcDialogOverlay").addClass('dd-hide') ;
			}) ;
		} ,
		
		/**
		 * ��ѯ�����۸�
		 */
		_initQueryPromotion:function(){
			var itemId = $("#itemId").val() ;
			var userCommissionRate = $("#userCommissionRate").val() ;
			if(typeof(itemId) != 'undefined' && itemId != '' &&
					typeof(userCommissionRate) != 'undefined' && userCommissionRate != ''){
				$.ajax({
						url : envRoot + '/frame/q/remote/rest/query_tb_promotion_price_ajax.htm',
						data : {userCommissionRate:userCommissionRate,id:itemId} , 
						type : "POST" ,
						success : function(data){
							try {
								var code = data.code ;
								if(code == 'success'){
									var result = data.data ; 
									if(result.hasPromotion){
										$(".tb-main .tb-item-price").html(result.promotionPriceFormat) ;
										$(".tb-main .tb-commission-integral").html(result.userJfb);
										$(".tb-main .tb-commission-integral-by-money").html(result.userJfbByMoneyFormat);
										//$(".item-price-view .rumour").hide() ;
									} else {
										//û�д�����
									}
								} else {
								    //$(".item-price-view").append('<div class="select-tips" style="opacity: 1; "><div class="content">����۸��Ե��̼۸�Ϊ׼Ŷ~</div><div class="angle-top" style="top: -7px;left:140px;"></div></div>');
								}
							}catch(e){
								
							}
							
						} ,
						error : function(data){
							
						}
					});
			}
		} ,
		
		/**
		 * ��������
		 */
		_initIntelligentIpt:function(){
			$('.intelligent-ipt').each(function(){
				var _this = $(this) ;
				var label = _this.attr('data-inte-label') ;
				var mag = $("#intIptMagnifier") ;
				var magnifier = function(){
					var val = _this.val() ;
					if(val != ''){
						var left = _this.offset().left ;
						var top = _this.offset().top + _this.height() + 11 ;
						mag.css({left:left , top:top}) ;
						mag.find('.mag-text').html(val) ;
						mag.find('.mag-explain').html(label) ;
						mag.removeClass('dd-hide') 
					} else {
						mag.addClass('dd-hide') ;
					}
				} ;
				
				_this.focus(function(){
					_this.addClass('focus') ;
					magnifier() ;
				}) ;
				
				_this.blur(function(){
					_this.removeClass('focus') ;
					mag.addClass('dd-hide') ;
				}) ;
				
				_this.keyup(function(){
					magnifier() ;
				}) ;
			}) ;
		} ,
		
		_initUserAccount:function(){
			$('.tb-account .tb-my-account').each(function(){
				var _this = $(this) ;
				_this.find('.integral-count').hover(function(){
					_this.find('.select-tips').removeClass('dd-hide') ;
				} , function(){
					_this.find('.select-tips').addClass('dd-hide') ;
				}) ;
			});
		} ,
		
		_initSearchNewGuide:function(){
			$(".tb-search .search-input").focus(function(){
				var isNewGuideDone = $('.tb-search .search-guide').attr('data-new-guide-done') ;
				if(isNewGuideDone == 'true'){
					return ;
				}
				$('.tb-search .search-guide').find('guide-titile').html('һ������������') ;
				self.searchNewGuide() ;
			}) ;
			
			var searchErrorCode = $("#tbSearchError").val() ;
			if(searchErrorCode && searchErrorCode != ''){
				var error = '' ;
				if(searchErrorCode == 'ddz.item.dangerKeyword'){
					error = '�Բ�������������Ʒ�����Ƿ���Ϣ��' ;
				} else if(searchErrorCode == 'ddz.item.notFound'){
					error = '�Բ�����������Ա���Ʒ���Ӵ���' ;
				} else {
					error = '�Բ�����������Ա���Ʒ���Ӵ���' ;
				}
				if(error != ''){
					$('.tb-search .search-guide').find('.guide-title span').html(error) ;
					self.searchNewGuide() ;
				}
			}
			
			//��ʼ������������Ʒ
			$('.tb-search .search-guide').find('.step-url').html(newGuideUrl) ;
		} ,
		
		_initSearchEvent:function(){
			
			//search submit 
			$(".tb-search form").submit(function(){
				var wd = $(this).find('input').val() ;
				if(wd == ''){
					alert('��ճ����Ҫ������Ա�������ַ') ;
					return false ;
				}
				//���URL��ʽ
				wd = wd.toLowerCase() ;
				var isTaobaoUrl = false  ;
				var itemIdArray = ['id','itemid','item_id','mallstitemid','default_item_id'] ;
				if(wd.indexOf('taobao.com') != -1 ||
						wd.indexOf("tmall.com") != -1 ){
					
					for(var i = 0 ; i < itemIdArray.length ; i++){
						var id = itemIdArray[i] + '=';
						if(wd.indexOf(id) != -1){
							isTaobaoUrl = true ;
							break ;
						}
					}
					
				} 				
				
				if(!isTaobaoUrl){
					$('.tb-search .search-guide').find('.guide-title span').html('���������ַ����') ;
					self.searchNewGuide() ;
					return false ;
				}
				
				return true ;
			}) ;
			
			if($('#search_content').size() > 0){
				$(".search-wrap").addClass('dd-hide') ;
			}
		
		} ,
		
		searchNewGuide:function(){
			
			var searchGuide = $('.tb-search .search-guide') ;
			
			searchGuide.animate({height:"160px"} , 500 , function(){
				var newGuide = $(this).find(".new-guide") ;
				newGuide.find(".prev-icon").addClass('disable') ;
				newGuide.removeClass('dd-hide') ;
			}) ;
			
			var ul = searchGuide.find('.new-guide ul') ;
						
			var resetPages = function(){
				var index = self.parseInt(ul.attr('data-idx')) ;
				
				if(index == 3){
					searchGuide.find('.step-url').removeClass('dd-hide') ;
				} else {
					searchGuide.find('.step-url').addClass('dd-hide') ;
				}
				
				if(index <= 0){
					searchGuide.find('.prev-icon').addClass('disable') ;
				} else {
					searchGuide.find('.prev-icon').removeClass('disable') ;
				}
				
				if(index >= ul.find('li').size() - 1) {
					searchGuide.find('.next-icon').addClass('disable') ;
				} else {
					searchGuide.find('.next-icon').removeClass('disable') ;
				}
				
				if(index == ul.find('li').size() - 1){
					searchGuide.find('.know').removeClass('dd-hide') ;
				} else {
					searchGuide.find('.know').addClass('dd-hide') ;
				}
				
				searchGuide.find('.page .num').html(index + 1) ;
				var li = ul.find('li').eq(index) ;
				searchGuide.find('.title').html(li.attr('data-title')) ;
				ul.css({marginLeft:-index*501}) ;
				ul.attr('data-pending' , 'f') ;
				
			}
			
			ul.attr('data-idx' , 0) ;
			ul.attr('data-pending' , 'f') ;
			resetPages() ;
			
			//��ҳ
			searchGuide.find('.prev-icon').click(function(){
				var isPending = ul.attr('data-pending') ;
				if(isPending == 't'){
					return ;
				}
				ul.attr('data-pending' , 't') ;
				var index = self.parseInt(ul.attr('data-idx')) ;
				if(index > 0){
					index = index - 1 ;
					ul.animate({marginLeft:-index*501} , 500 , function(){
						ul.attr('data-idx' , index) ;
						resetPages() ;
					}) ;
				}
			}) ;
			
			//��ҳ
			searchGuide.find('.next-icon').click(function(){
				var isPending = ul.attr('data-pending') ;
				if(isPending == 't'){
					return ;
				}
				ul.attr('data-pending' , 't') ;
				var index = self.parseInt(ul.attr('data-idx')) ;
				if(index < ul.find('li').size() - 1){
					index = index + 1 ;
					ul.animate({marginLeft:-index*501} , 500 , function(){
						ul.attr('data-idx' , index) ;
						resetPages() ;
					}) ;
				}
			}) ;
			
			//��֪����
			searchGuide.find('.know').click(function(){
				searchGuide.animate({height:"0"} , 500 , function(){
					var url = envRoot + "/frame/q/remote/rest/mark_guide_done_ajax.htm" ;
					$.ajax({
						url : url ,
						type:"POST" ,
						data:{guideStr:"idx_sch"} ,
						success : function(data){
							$('.tb-search .search-guide').attr('data-new-guide-done','true') ;
						} ,
						error : function(data){
							alert('error!') ;
						}
					}) ;
				}) ;
			}) ;
			
			//�Ա�URL����
			searchGuide.find('.step-url-btn').click(function(){
				var stepUrl = searchGuide.find('.step-url') ;
				stepUrl.animate({left: '15px',top:'-63px'} , 800 ,function(){
					$("#search_content").val(stepUrl.html()) ;
					stepUrl.remove() ;
					searchGuide.animate({height:"0"} , 500 , function(){
						
					}) ;
				}) ;
			}) ;
		} ,
		
		_initIntegralPay:function(){
			
			//li hover
			$(".integral-pay .pay-content-list").find("li").hover(function(){
				$(this).addClass('active') ;
			} ,function(){
				$(this).removeClass('active') ;
			});
			
			//�����һ��ı��
			var userIntegralCount = self.parseInt($("#userIntegralCount").val()) ;
			var canExchangeCount = $("#canExchangeCount").val() ;
			$(".integral-pay .pay-content-list").find("li").each(function(){
				
				if(self.parseInt(canExchangeCount) <= 0){
					$(this).find('.go').addClass('disable') ;
				}
				
				var needIntegral = $(this).attr('data-integral') ;
				if(needIntegral > userIntegralCount){
					$(this).find('.go').addClass('disable') ;
				}
			}) ;
			
			//����һ�
			$(".integral-pay .pay-content-list").find("li").click(function(){
				$('.exchange-dialog').addClass('dd-hide') ;
				var _this = $(this) ;
				var type = _this.attr('data-type') ;
				var imgSrc = _this.find('img').attr('src') ;
				var itemTitle = _this.find('.item-title').html() ;
				var integralUnit = _this.attr('data-integral') ;
				var exItemId = _this.attr('data-exchange-item-id') ;
				
				if(_this.find('.go').hasClass('disable')) {
					return ;
				}
				var exchangeDialog = $('#exchangeDialog') ;
				if(type == 'jfb'){
					//���ֱ�
					exchangeDialog = $("#jfbExchangeDialog") ;
					var alipayIpt = exchangeDialog.find('input[name=delAlipay]') ;
					var rmbAlipay = alipayIpt.attr('data-remeber-alipay') ;
					if(rmbAlipay && rmbAlipay != ''){
						alipayIpt.addClass('readonly') ;
						alipayIpt.attr('readonly','readonly') ;
						exchangeDialog.find('.change-alipay').removeClass('dd-hide') ;
						exchangeDialog.find('.repeat-container').addClass('dd-hide') ;
					} else {
						
					}
					
					
				} else if(type == 'qq'){
					//Q��
					exchangeDialog = $("#qqExchangeDialog") ;
					
				} else if(type == 'hf'){
					//����
					exchangeDialog = $("#hfExchangeDialog") ;
				}
				
				exchangeDialog.find('.title img').attr('src',imgSrc) ;
				exchangeDialog.find('.content .item-title').html(itemTitle) ;
				exchangeDialog.find('input[name=exItemId]').val(exItemId) ;
				exchangeDialog.find('.integral').html(integralUnit) ;
				
				
				self.centerPoint(exchangeDialog) ;
				$("#dcDialogOverlay").removeClass('dd-hide') ;
				exchangeDialog.removeClass('dd-hide') ;
				
			}) ;
			
			$('.exchange-dialog').each(function(){
				var _this = $(this) ;
				_this.find(".w_close_color").click(function(){
					_this.addClass('dd-hide') ;
					$("#dcDialogOverlay").addClass('dd-hide') ;
				}) ;
				
				_this.find('.submit').click(function(){
					var form = _this.find('form') ;
					var url = envRoot + "/frame/q/remote/rest/user_exchange_approve_ajax.htm" ;  
					var canSubmit = true ;
					
					//�б�����δ��д
					form.find('input[type=text]').each(function(){
						if($(this).is(":hidden")){
							return ;
						}
						var val = $(this).val() ;
						var label = $(this).attr('data-label') ;
						if(val == ''){
							alert(label + '����Ϊ�գ�') ;
							canSubmit = false ;
							return false ;
						}
					}) ;
					
					if(!canSubmit){
						return ;
					}
					
					//�ظ�����
					form.find('[data-repeat-to]').each(function(){
						var _this = $(this) ;
						if(_this.is(":hidden")){
							return ;
						}
						var name = _this.attr('data-repeat-to') ;
						if(!form.find('.repeat-container').hasClass('dd-hide')){
							var val = form.find('input[name=' + name + ']').val() ;
							if(val != _this.val()){
								alert('�������벻һ�£�') ;
								canSubmit = false ;
								return false ;
							}
						}
					}) ;
					
					if(!canSubmit){
						return ;
					}
					
					
					
					$.ajax({
						url : url ,
						type:"POST" ,
						data:$(form).serialize() ,
						success : function(json){
							var code = json.code ;
							if(code == 'success'){
								alert('�һ��ɹ�����ȴ��ͷ���Ա����');
								window.location.href = envRoot + '/frame/q/tb_my.htm' ;
							} else {
								if(code == 'ill_args'){
									var detail = json.detail ;
									if(detail == 'dcome.exchange.user.notLogin') {
										alert('���ȵ�½') ;
									} else if(detail == 'dcome.exchange.exItemId.required') {
										alert('�һ�ʧ�ܣ�') ;
									} else if(detail == 'dcome.exchange.exType.error') {
										alert('�һ�ʧ�ܣ�') ;
									} else if(detail == 'dcome.exchange.alipay.notCorrect') {
										alert('����д��ȷ��֧����') ;
									} else if(detail == 'dcome.exchange.qq.notCorrect') {
										alert('����д��ȷ��QQ��') ;
									} else if(detail == 'dcome.exchange.mobile.notCorrect') {
										alert('����д��ȷ���ֻ���') ;
									} else if(detail == 'dcome.exchange.integral.limit') {
										alert('�ɶһ��Ļ��ֲ��㣬������ѡ��һ���Ʒ') ;
									} else if(detail == 'dcome.exchange.exItemNum.error'){
										alert('�һ���������Ϊ������') ;
									} else if(detail == 'dcome.exchange.count.monthLimit'){
										alert('���¶һ������ﵽ���ޣ����¸����ٽ��жһ�') ;
									} else {
										alert('�һ�ʧ�ܣ����Ժ����ԣ�') ;
									}
								} else {
									alert('�һ�ʧ�ܣ����Ժ����ԣ�') ;
								}
							}
						} ,
						error : function(data){
							alert('�һ�ʧ�ܣ����Ժ����ԣ�');
						}
					}) ;
				}) ;
				//end submit
				
				_this.find('.change-alipay').click(function(){
					var alipayIpt = _this.find('input[name=delAlipay]') ;
					alipayIpt.removeClass('readonly') ;
					alipayIpt.removeAttr('readonly') ;
					_this.find('.change-alipay').addClass('dd-hide') ;
					_this.find('.repeat-container').removeClass('dd-hide') ;
					_this.find('.repeat-container input').val('') ;
				}) ;
			}) ;
		} ,
		
		parseInt:function(num) {
	    	if(num == ''){
	    		return 0 ;
	    	}
	    	
	    	if(isNaN(num)){
	    		return 0 ;
	    	}
	    	return parseInt(num) ;
	    } ,
	    
	    /**
		 * ����
		 */
		centerPoint:function(selector){
			
			var bodyClientLeft = $('.tb-main').offset().left ;
			var bodyClientRight = $('.tb-main').offset().left + $('.tb-main').width();
			
			if(selector == null || selector == undefined){
				return  ;
			}
			fusion2.canvas.getClientRect({
			    onSuccess: function(rect) {
					var left = bodyClientLeft + (bodyClientRight - bodyClientLeft) / 2 - $(selector).width()/2;
					var top = rect.top + (rect.bottom - rect.top - selector.height())/2;
					if (top <= 10) {
					    top = 20;
					}
					selector.css({
						left:left,top:top
					}) ;
				}
			});
		},
		
		end:0
	};

	$(function(){
		
	});
	//fix ����IE���޷�����
	index.init();
})();