!(function($){
	$.namespace("DD.Index");
	var self = DD.Index;

	$.extend(DD.Index,{
		Email_Regex : /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/,
		Mobile_Regex : /^((\(\d{2,3}\))|(\d{3}\-))?13\d{9}$/,
		init:function(){
			self._initSearchEvent();
			//self._initPaging();
			self._initCarousel();
			self._initChangeAlipay();
			self._initSubmitAlipay() ;
		},
		_initChangeAlipay:function(){
			$("#change_alipay").on('click',function(){
				$("#discount_url").addClass("dd-hide") ;
				$("#alipay_input").removeClass("dd-hide") ;
			}) ;
		},
		_initSubmitAlipay:function(){
			$('#alipaySubmit').on('click',function(){
				self.submitAlipay() ;
				return false ;
			});
		},
		_initSearchEvent:function(){
			$('#submit').on('click',function(){
				self.submit();
				return false ;
			});

			$('#search_content').on('focus',function(){
				if($(this).val() == "ճ��:���빺����Ա��������ƻ򱦱���ַ"){
					$(this).val("");
				}
			});

			$('#search_content').on('blur',function(){
				if($(this).val() === ""){
					$(this).val("ճ��:���빺����Ա��������ƻ򱦱���ַ");
				}
			});
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
//			jQuery('#mycarousel').jcarousel({
//		        auto: 2,
//		        wrap: 'last',
//		        initCallback: self.mycarousel_initCallback
//		    });
		},
		submit:function(){
			var q = $("#search_content"),
				u = $("#submit").data("url"),
				wd = q.val() ;
			if(wd == '' || wd == 'ճ��:���빺����Ա��������ƻ򱦱���ַ'){
				alert('��ճ��:���빺����Ա��������ƻ򱦱���ַ') ;
				return false ;
			}
			$('#search_form').submit();
		},
		/**
		 * check alipay account
		 */
		submitAlipay:function(){
			var alipayId = $('#alipayInput').val() ;
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
			$('#alipay_form').submit();
			return true ;
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
		end:0
	});
})(jQuery);

jQuery(document).ready(function(){
	DD.Index.init();
});
