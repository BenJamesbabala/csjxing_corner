!(function($){
	$.namespace("DD.Index");
	var taobaoRecommend = 'http://item.taobao.com/item.htm?id=20138548730' ; //�Ա��Ƽ�
	var self = DD.Index;

	$.extend(DD.Index,{
		Email_Regex : /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/,
		Mobile_Regex : /^1\d{10}$/,
		init:function(){
			self._initSearchEvent();
			//self._initPaging();
			self._initCarousel();
			self._initChangeAlipay();
			self._initSubmitAlipay() ;
			self._initNewGuide();
			self._initSaveDiandianzhe() ;
			//��ʼ�� google track
			self._initGtrack();
			//��ѯ�����۸�
			self._initQueryPromotion();
			
			/**
			 * ���ֹ����޸�֪ͨ
			 */
			self._initTixian();
			
			self._initVideoEvent() ;
			
			//self._initShareAwardTips();
			self._initRecommend();
			self._initDdDialog();
			self._initPlaceholder();
		},
		
		/**
		 * 
		 */
		_initVideoEvent:function(){
			$('.ui-video-xbox .close-flash-xbox').click(function(){
				$(".ui-video-xbox").addClass('dd-hide') ;
				$("#shadow-overlay").addClass('dd-hide') ;
			}) ;
			
			//���ֱ���Ƶ
			$(".dd-item-main .jfb-study-video").click(function(){
				$('.ui-video-xbox .flash-src').attr('src','https://hi.alipay.com/cms/huohaier/jfb_flash.swf');
				$('.ui-video-xbox .flash-src').attr('value','https://hi.alipay.com/cms/huohaier/jfb_flash.swf');
				$('.ui-video-xbox').removeClass('dd-hide');
				$('#shadow-overlay').removeClass('dd-hide');
			}) ;
		} ,
		
		/**
		 * ���ֹ����޸�֪ͨ
		 */
		_initTixian:function(){
			var isNotify = $("#isNotify").val() ;
			if(isNotify == 'true'){
				$('#shadow-overlay').removeClass('dd-hide') ;
				$('#tixian-notify-dialog').removeClass('dd-hide') ;
			}
			
			$("#tixian-tip").css({opacity: 0});
			$("#tixian-tip").removeClass('dd-hide') ;
			$("#tixian-tip").delay(800).animate({opacity:1} , 1000) ;
			
			$("#tixian-notify-dialog .tixian-ikonw-click").click(function(){
				$('#shadow-overlay').addClass('dd-hide') ;
				$('#tixian-notify-dialog').addClass('dd-hide') ;
				var ddzRoot	= $("#ddzRoot").val() ;
				$.ajax({
					url : ddzRoot + '/zhe/remote/rest/decr_account_notify_ajax.htm',
					data : {} , 
					type : "POST" ,
					success:function(data){
						var code = data.json.code ;
					} ,
					error:function(data){
						var error = data;
					}
				});
			});
			
			$("#header .login-tip").delay(500)
				.animate({top:'-=8'},200, "linear").animate({top:'+=8'},200, "linear").animate({top:'-=8'},200, "linear").animate({top:'+=8'},200, "linear")
				.animate({top:'-=8'},200, "linear").animate({top:'+=8'},200, "linear");
		} ,
		
		_initRecommend:function(){
			
			var ddzRoot	= $("#ddzRoot").val() ;
			if($("#ddRecomends").is(":visible")) {
				$("[data-imgsrc]").each(function(){
					var imgSrc = $(this).data("imgsrc") ;
					if(imgSrc != ''){
						$(this).attr("src",imgSrc) ;
					}
				});
			}
			
			//����֧����
			$("#changeBrandsAlipay").click(function(){
				self.ddDialog("ddRedirectDialog",false) ;
				self.ddDialog("ddInputDialog",true) ;
			});
						
			//����click�¼�
			$("[data-brandsRecommend]").click(function(){
				var _alipayAccount = $("#alipayAccount").val() ;
				var shopId = $(this).attr("data-shopId") ;
				$(".brandsShopId").val(shopId) ;
				if(_alipayAccount == ''){ //֧����Ϊ��
					self.ddDialog("ddInputDialog",true) ;
				}else{
					//ֱ����ת
					self.ddDialog("ddRedirectDialog",true) ;
				}
				return false ;
			});
			
			$('#ddBrandsIptAlipayForm').on('submit',function(){
				var alipayInput = $("#brandsAlipayInput").val();
				if(!self.submitAlipay("brandsAlipayInput")){
					return false ;
				}
				var setAlipaySuccess = false ;
				//����֧����
				$.ajax({
					url : ddzRoot + '/zhe/remote/rest/setAlipay.htm',
					data : {alipayId:alipayInput} , 
					async : false ,
					type : "POST" ,
					timeout : 500,
					success : function(data){
						try {
							var code = data.json.code ;
							if(code == 'success'){
								setAlipaySuccess = true ;
							} else {
								alert('ϵͳ��æ�����Ժ�����') ;
							}
						}catch(e){
							alert('ϵͳ��æ�����Ժ�����') ;
						}
					} ,
					error : function(data){
						alert('ϵͳ��æ�����Ժ�����') ;
					}
				});//end ajax
				if(setAlipaySuccess){
					self.ddDialog("ddInputDialog",false) ;
					$("#shopForm").submit() ;
					$("#alipayAccount").val(alipayInput) ;
					$("#brandsAlipay").html(alipayInput) ;
					return true ;
				}
				return false ;
			}); //end $('#shopForm').on('submit',function(){
			
			$("#ddBrandsRedirectForm").on('submit',function(){
				self.ddDialog("ddRedirectDialog",false) ;
				return true ;
			}) ;
			
			$("#ddRedirectDialog").find(".dd-dialog-submit").click(function(){
				$("#ddBrandsRedirectForm").submit() ;
			});
			
			$("#ddInputDialog").find(".dd-dialog-submit").click(function(){
				$("#ddBrandsIptAlipayForm").submit() ;
			});//end $("#ddInputDialog").find(".dd-dialog-submit").click(function(){
			
			var lineHeight = 79 ;
			var buyingsScroll = function(){
				//�ж��Ƿ񵽴ﶥ��
			  	var marginTop = parseInt($('.dd-buyings-scroll').css('margin-top')) ;
			  	if(marginTop > 0){
			  		var buyItemCount = $(".dd-buyings-list").size() ;
			  		var margin = (lineHeight*(buyItemCount-5)) + 78 ;
			  		$('.dd-buyings-scroll').css("margin-top","-" + margin + "px") ;
			  	}
			} ;
			
			setInterval(function(){
					$('.dd-buyings-scroll').animate({marginTop:'+='+lineHeight},800, buyingsScroll()) ;
			} , 3000);
			
		},
		
		/**
		 * �Ի���
		 */
		_initDdDialog:function(){
			//�Ի���ر��¼�
			$("[data-dialogClose]").click(function(){
				var dialog = $(this).attr("data-dialogClose");
				self.ddDialog(dialog,false) ;
			});
			
			$("#ddDialogOverlay").css("height",$(document).height());
		} ,
		
		ddDialog:function(id,isShow){
			if(isShow == false){
				$("#"+id).addClass("dd-hide") ;
				$("#ddDialogOverlay").addClass("dd-hide") ;
			}else{
				
				var winWidth = $("#"+id).width();
				var winHeight = $("#"+id).height() ;
				var clientWidth = $(window).width();
				var clientHeight = $(window).height();
				var top =  (clientHeight - winHeight)/2 + $(document).scrollTop() ;
				var left = (clientWidth - winWidth)/2 + $(document).scrollLeft() ;
				$("#"+id).css("top" , top < 0 ? 0 : top );
				$("#"+id).css("left", left < 0 ? 0 : left  );
				
				$("#"+id).removeClass("dd-hide") ;
				$("#ddDialogOverlay").removeClass("dd-hide") ;
				
				
				
			}
		},
		
		/**
		 * ��ѯ�����۸�
		 */
		_initQueryPromotion:function(){
			var itemId = $("#itemId").val() ;
			var userCommissionRate = $("#userCommissionRate").val() ;
			var ddzRoot = $("#ddzRoot").val() ;
			if(typeof(itemId) != 'undefined' && itemId != '' &&
					typeof(userCommissionRate) != 'undefined' && userCommissionRate != '' && ddzRoot != ''){
				$.ajax({
						url : ddzRoot + '/zhe/remote/rest/query_promotion.htm',
						data : {userCommissionRate:userCommissionRate,id:itemId} , 
						type : "POST" ,
						success : function(data){
							try {
								var code = data.json.code ;
								if(code == 'success'){
									var result = data.json.data ; 
									if(result.hasPromotion){
										$("[data-originPrice]").html(result.promotionPriceFormat) ;
										$("[data-userCommission]").html(result.userJfbByMoneyFormat);
										$(".item-price-view .rumour").hide() ;
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
		
		_initShareAwardTips:function(){
			if($('#userGuide').val() != "true") { //û������ָ����ʱ��
			  	$('#social_button_tips').fadeIn(1500).removeClass("dd-hide");
			}
			$("#item-price-discount-allowance-gift").hover(
					function(){
						$('#allowance_gift_tips').fadeIn(1000).removeClass("dd-hide");
					},
					function(){
						$('#allowance_gift_tips').hide();
					}
			);
		},
		_initChangeAlipay:function(){
			$("#change_alipay").on('click',function(){
				$("#item-show").removeClass() ;
				$("#item-show").addClass("main-result-ipt") ;
			}) ;
		},
		_initSubmitAlipay:function(){
			$('#alipay_form').on('submit',function(){
				if(!self.submitAlipay("alipayInput")){
					return false ;
				}
			});
			$('#alipaySubmit').on('click',function(){
				$('#alipay_form').submit();
			});
		},
		_initSearchEvent:function(){
			
			var defaultText = $("#search_content").attr("placeholder") ;
			
			$('#search_form').on('submit',function(){
				var q = $("#search_content"),
					u = $("#submit").data("url"),
					wd = q.val() ;
				//����Ϊ��
				if(wd == '' || wd == defaultText){
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
					//��ʾ�����Ա�URLָ��
					
					$('#search_content').val(" ");
					$('#errurlPrompt').html("");
					$('#errurlPrompt').fadeIn(1000).append('<div class="step-url" id="step-url">' + taobaoRecommend + '</div>');//���Ƽ�����Ʒ��ʾ��ͼƬ��URL��
					$('#ddRecomends').addClass("dd-hide"); //����б���������Ļ�����
					$('#item-show').addClass("dd-hide") ;//�������Ʒ�����ʾ������
					$('#newGuide').addClass("dd-hide") ;//��������������ť
					$('#errurlPrompt').bind('click',function(){
						$('#step-url').animate({
						    left: '-=88',
						    top:'-=98'
						  }, 800, function() {
						  	$('#step-url').remove();
						  	$('#errurlPrompt').delay(500).animate({opacity:0},500,function(){
						  		$('#errurlPrompt').hide() ;//Ϊ��ȥ��display:blockЧ��
						  		self._newGuideTwo();
						  	})
						  	$('#search_content').val(taobaoRecommend);

						    // Animation complete.
						  });
					});
					$('#search_form').append('<input type="hidden" name="userGuide" value="true" />') ;
					return false ;
				}
				
				return true ;
			}) ;
		},
		
		/**
		 * placeholder
		 */
		_initPlaceholder:function(){
			$("[placeholder]").each(function(){
				var text = $(this).attr("placeholder");
				$(this).val(text) ;
				$(this).on('focus',function(){
					if($(this).val() == text){
						$(this).val("");
					}
				});

				$(this).on('blur',function(){
					if($(this).val() === ""){
						$(this).val(text);
					}
				});
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
										alert('����������ղظ�ҳ��û�гɹ����볢��' + ctrl + '+D�����ղ�');
									}
								}
							} else if (window.sidebar) {
								try {
									window.sidebar.addPanel(document.title, window.location, '_self');
								} catch (e) {
									alert('����������ղظ�ҳ��û�гɹ����볢��' + ctrl + '+D�����ղ�');
								}
							} else {
								alert('����������ղظ�ҳ��û�гɹ����볢��' + ctrl + '+D�����ղ�');
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
			
			$("[gtrack]").bind('click',function(){
				var gtrack = $(this).attr('gtrack') ;
				if(gtrack != null){
					//alert(gtrack) ;
					if(typeof(_gaq) != 'undefined'){
						_gaq.push(['_trackPageview',gtrack]); 
					}
				}
			}) ;
						
		},	
		/**
		 * check alipay account
		 */
		submitAlipay:function(elementId){
			var alipayId = $('#'+elementId).val() ;
			if(alipayId == ''){
				alert('������֧���������˺ţ���������') ;
				return false ;
			}
			var isEmail = alipayId.match(self.Email_Regex) ;
			var isMobile = alipayId.match(self.Mobile_Regex) ;
			if(!isEmail && !isMobile){
				alert('�����֧������ʽ���������Ƿ�ΪEmail��ַ���ֻ���') ;
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
								alert('�������֧���������ڣ����������Ƿ���ȷ');
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
						if ($('#item-show').length>0) {
						    $('#item-show').addClass("dd-hide");
						}
						$('#ddRecomends').addClass("dd-hide"); //����б���������Ļ�����
						
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
			$('#step1').fadeIn(1000).append('<div class="step-url" id="step-url">' + taobaoRecommend + '</div>');

			$('#step1').bind('click',function(){
				$('#step-url').animate({
				    left: '-=88',
				    top:'-=98'
				  }, 800, function() {
				  	$('#step-url').remove();
				  	$('#step1').delay(500).animate({opacity:0},500,function(){
				  		self._newGuideTwo();
				  	})
				  	$('#search_content').val(taobaoRecommend);

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
				$('#step5').fadeOut(600); //����tip
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
