!(function($){
	$.namespace("DD.Index");
	var self = DD.Index;

	$.extend(DD.Index,{
		Email_Regex : /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/,
		Mobile_Regex : /^1[3458]\d{9}$/,
		init:function(){
			self._initSearchEvent();
			//self._initPaging();
			self._initCarousel();
			self._initChangeAlipay();
			self._initSubmitAlipay() ;
			self._initKeywordSearchEvent();
			self._initNewGuide();
			self._initSaveDiandianzhe() ;
			//初始化 google track
			self._initGtrack();
			
			self._initS8ChangeAlipay();
			self._initS8SubmitAlipay();
		},
		_initChangeAlipay:function(){
			$("#change_alipay").on('click',function(){
				$("#item-show").removeClass() ;
				$("#item-show").addClass("main-result-ipt") ;
			}) ;
		},
		_initSubmitAlipay:function(){
			$('#alipay_form').on('submit',function(){
				if(!self.submitAlipay()){
					return false ;
				}
			});
			$('#alipaySubmit').on('click',function(){
				$('#alipay_form').submit();
			});
		},
		_initSearchEvent:function(){
			
			var defaultText = '粘贴:您想购买的淘宝宝贝名称或宝贝网址' ;
			
			$('#search_content').on('focus',function(){
				if($(this).val() == defaultText){
					$(this).val("");
				}
			});

			$('#search_content').on('blur',function(){
				if($(this).val() === ""){
					$(this).val(defaultText);
				}
			});
			
			$('#search_form').on('submit',function(){
				var q = $("#search_content"),
					u = $("#submit").data("url"),
					wd = q.val() ;
				if(wd == '' || wd == defaultText){
					alert('请粘贴:您想购买的淘宝宝贝名称或宝贝网址') ;
					return false ;
				}
				return true ;
			}) ;
		},
		_initS8ChangeAlipay:function(){
			$("#s8_change_alipay").on('click',function(){
				$("#s8_alipay_input").removeClass("dd-hide") ;
				$("#s8_alipay_buy").addClass("dd-hide") ;
			}) ;
			
			$("#s8_cancel_alipay").on('click',function(){
				$("#s8_alipay_buy").removeClass("dd-hide") ;
				$("#s8_alipay_input").addClass("dd-hide") ;
			}) ;
		} ,
		_initS8SubmitAlipay:function(){
			$('#s8_alipay_form').on('submit',function(){
				if(!self.submitAlipay()){
					return false ;
				}
				//ajax修改支付宝
				var ddzRoot = $("#ddzRoot").val() ;
				var alipayId = $('#alipayInput').val() ;
				if(typeof(ddzRoot) != 'undefined'){
					var url = ddzRoot + '/zhe/remote/rest/setAlipay.htm' ;
					$.ajax({
						url : url ,
						data : {alipayId:alipayId} , 
						async : true ,
						type : "POST" ,
						success : function(data){
							try {
								var code = data.json.code ;
								if(code == 'success'){
									//更新本地的支付宝
									window.location.reload() ;
								}
							}catch(e){
								alert('返回数据异常');
							}
							
						} ,
						error : function(data){
							alert("更新支付宝失败") ;
						}
					});
				}
			});
			$('#s8_alipaySubmit').on('click',function(){
				$('#s8_alipay_form').submit();
			});
		},
		/**
		 * 关键字搜索->子搜索
		 */
		_initKeywordSearchEvent:function(){
			
			var keyword = $("#keyword") ;
			var startPrice = $("#startPrice") ;
			var endPrice = $("#endPrice") ;

			if(typeof(keyword) == 'undefined' || typeof(startPrice) == 'undefined' || typeof(endPrice) == 'undefined' ){
				return ;
			}
			
			
			
			$('#keyword_filter_form').on('submit',function(){
				
				if(keyword.val()==null || keyword.val()==""){
					alert("请输入关键词");
					return false ;
				}
				
				var sPrice = startPrice.val() ;
				var ePrice = endPrice.val() ;
				if(sPrice=='' || isNaN(sPrice)){
					sPrice = null ;
				} else {
					sPrice = parseInt(sPrice);
				}
				
				
				if(ePrice=='' || isNaN(ePrice)){
					ePrice = null ;
				} else {
					ePrice = parseInt(ePrice);
				}
				
				if(sPrice != null && sPrice < 0){
					sPrice = sPrice * -1;
				}
				
				if(ePrice != null && ePrice < 0){
					ePrice = ePrice * -1;
				}
				
				if(sPrice != null && ePrice != null && sPrice > ePrice){
					var sPricetmp = sPrice ;
					sPrice = ePrice ;
					ePrice = sPricetmp ;
				}
				
				startPrice.val(sPrice);
				endPrice.val(ePrice) ;
				return true ;
			});
			
			$('#startPrice').on('keyup',function(){
				if(isNaN(startPrice.val())){
					startPrice.val(startPrice.val().replace(/\D/g,'')) ;
				}
			});
			
			$('#endPrice').on('keyup',function(){
				if(isNaN(endPrice.val())){
					endPrice.val(endPrice.val().replace(/\D/g,'')) ;
				}
			});
			
			var paginationJump = $('#paginationJump') ;
			$('#paginationJump').on('keyup',function(){
				if(isNaN(paginationJump.val())){
					paginationJump.val(paginationJump.val().replace(/\D/g,'')) ;
				}
			});
			
			
		},
		_initSaveDiandianzhe:function(){
			if($("#diandianFavorite").length){
				$("#diandianFavorite").click(
						function() {
							var ctrl = (navigator.userAgent.toLowerCase()).indexOf('mac') != -1 ? 'Command/Cmd' : 'Ctrl';
							if (document.all) {
								try {
									window.external.addFavorite( window.location, document.title);
								} catch (e) {
									try {
										window.sidebar.addPanel(document.title, window.location, '_self');
									} catch (e) {
										alert('您的浏览器收藏该页面没有成功，请尝试' + ctrl + '+D进行收藏');
									}
								}
							} else if (window.sidebar) {
								try {
									window.sidebar.addPanel(document.title, window.location, '_self');
								} catch (e) {
									alert('您的浏览器收藏该页面没有成功，请尝试' + ctrl + '+D进行收藏');
								}
							} else {
								alert('您的浏览器收藏该页面没有成功，请尝试' + ctrl + '+D进行收藏');
							}
						}
					)
			}
		},
		_initPaging:function(){
			if($('#pagination').length){
				$('#pagination a').on('click',function(){
					$('#page_num').val($(this).data('page'));
					$('#form_page').submit();
				});

				$('#jumpto').on('keyup',function(ev){
					if(ev.keyCode === 13){
						$('#form_page').submit();
					}
					var temp = $('#jumpto').val();
					if(temp.length){
						$('#jumpto').val(parseInt(temp));
						$('#page_num').val(parseInt(temp))
					}
				});
			}
		},
		_initCarousel:function(){
			
			
			if($.browser.msie && $.browser.version == '6.0'){
				$('#tdSuperPrice').addClass("dd-hide");
				return ;
			}
			
			if(jQuery('#mycarousel').length){
				jQuery('#mycarousel').jcarousel({
			        auto: 3,
			        scroll:3,
			        wrap: 'circular',
			        animation:2000,
			        initCallback: self.mycarousel_initCallback
			    });

			    $('#mycarousel .jcarousel-prev').append('<b></b>');
			    $('#mycarousel .jcarousel-next').append('<b></b>');
			}
			

		},
		/**
		 * google track
		 */
		_initGtrack:function(){
			$(".gtrack").on('click',function(){
				var gtrack = $(this).attr('gtrack') ;
				if(gtrack != null){
					if(typeof(_gaq) != 'undefined'){
						_gaq.push(['_trackPageview',gtrack]); 
					}
				}
			}) ;
		},	
		/**
		 * check alipay account
		 */
		submitAlipay:function(){
			var alipayId = $('#alipayInput').val() ;
			if(alipayId == ''){
				alert('请输入支付宝您的账号，无需密码') ;
				return false ;
			}
			var isEmail = alipayId.match(self.Email_Regex) ;
			var isMobile = alipayId.match(self.Mobile_Regex) ;
			if(!isEmail && !isMobile){
				alert('输入的支付宝格式错误，请检查是否为Email地址或手机号') ;
				return false ;
			}
			var returnVal = true ;
			var ddzRoot = $("#ddzRoot").val() ;
			$.ajax({
				url : ddzRoot + '/zhe/remote/rest/alipayAcctStatusCheck.htm',
				data : {acctname:alipayId} , 
				async : false ,
				type : "POST" ,
				timeout : 500,
				success : function(data){
					try {
						var code = data.json.code ;
						if(code == 'success'){
							var acctType = data.json.data.acctType ;
							var acctstatus = data.json.data.acctstatus ;
							var stat =  data.json.data.stat ;
							if(stat == 'ok' && acctstatus == 'available'){
								alert('您输入的支付宝不存在，请检查输入是否正确');
								returnVal = false ;
							}
						}
					}catch(e){
						returnVal = true ;
					}
					
				} ,
				error : function(data){
					returnVal = true ;
				}
			});
			return returnVal ;
		},
		mycarousel_initCallback:function(carousel)
		{
		    // Disable autoscrolling if the user clicks the prev or next button.
		    $('#carousel_left').bind('click', function() {
		        carousel.startAuto(0);
		    });

		    carousel.buttonPrev.bind('click', function() {
		        carousel.startAuto(0);
		    });

		    // Pause autoscrolling if the user moves with the cursor over the clip.
		    carousel.clip.hover(function() {
		        carousel.stopAuto();
		    }, function() {
		        carousel.startAuto();
		    });
		},
		_initNewGuide:function(){
			if(typeof zAccount === 'undefined') return;
			if(zAccount){
				
				if($('#newGuide').length){
					$('#newGuide').removeClass('dd-hide');
					$('#newGuide').bind('click',function(){
						
						$('#tdSuperPrice').addClass("dd-hide"); //如果有别人正在买的话隐藏
						
						self._newGuideOne();
						$('#newGuide').remove();
						$('#search_form').append('<input type="hidden" name="userGuide" value="true" />') ;
					});

				}

				if($('#step3').length && $('#step4').length){
					$('#alipayInput').bind('focusin',function(){
						$('#step3').fadeOut(1000);
					});
					$('#step3').slideDown(600,function(){
						$('#step4').slideDown(600);
					});
					
				}
			}
			
			
			if($('#step5').length && $('#step6').length && !zAccount){
				self._newGuideThree();
			}
		},
		_newGuideOne:function(){
			$('#search_content').val(" ");
			$('#step1').fadeIn(1000).append('<div class="step-url" id="step-url">http://item.taobao.com/item.html?id=14119585739</div>');

			$('#step1').bind('click',function(){
				$('#step-url').animate({
				    left: '-=88',
				    top:'-=98'
				  }, 800, function() {
				  	$('#step-url').remove();
				  	$('#step1').delay(500).animate({opacity:0},500,function(){
				  		self._newGuideTwo();
				  	})
				  	$('#search_content').val("http://item.taobao.com/item.html?id=14119585739");

				    // Animation complete.
				  });
			});

			$(window).unload(function(){ 
			});
		},
		_newGuideTwo:function(){
			$('#step2').show('show').animate({top:'-=4'},200, "linear").animate({top:'+=4'},200, "linear");
		},
		_newGuideThree:function(){
			$('#step5').show('show').animate({top:'-=4'},200, "linear").animate({top:'+=4'},200, "linear");
			$("#buy_go_url").bind('click',function(){
				$('#step5').fadeOut(600); //隐藏tip
				$('#step6').show('show').animate({top:'-=4'},200, "linear").animate({top:'+=4'},200, "linear");
				$('#step6').bind('click',function(){
					$('#step6').fadeOut(600);
				});
				$("#buy_go_url").unbind('click') ;
				return false ;
			}) ;
			
		},
		end:0
	});
})(jQuery);

jQuery(document).ready(function(){
	DD.Index.init();
});
