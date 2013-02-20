!(function($){
	
	var self ;
	
	var envRoot = $('#envRoot').val() ;
	
	var index = {
				
		init:function(){
			self = this ;
			
			self._loadBanner() ;
			
		},
		
		/**
		 * 初始化banner
		 */
		_loadBanner:function(){
			var bannerList = $(".banner-config-init") ;
			var bannerIds = '' ;
			bannerList.each(function(){
				var _this = $(this) ;
				var bannerId = _this.attr('data-banner-id') ;
				if(bannerId != undefined && bannerId != ''){
					bannerIds += bannerId + ',' ;
				}
			}) ;
			
			if(bannerIds == ''){
				return ;
			}
			
			$.ajax({
				url: envRoot + "/home/remote/rest/query_banner_ajax.htm",
				type: "post",
				data: {bannerIds:bannerIds},
				success: function(result) {
				    var json = result.json;
					if (json.code == 'success') {
					    
						//解析banner
						var bannerMap = json.data ;
						
						bannerList.each(function(){
							var _this = $(this) ;
							var bannerId = _this.attr('data-banner-id') ;
							if(bannerId != undefined && bannerId != ''){
								var banner = bannerMap[bannerId] ;
								if(banner != undefined) {
									var bindEvent = banner.bindEvent ;
									var bindEventFunction = banner.bindEventFunction ;
									var target = banner.targetBlank == 'T' ? '_blank' : '' ;
									var html = '<a href="' + banner.targetUrl + '" target="' + target + '" gtrack="/virtual/banner?tracelog=' + bannerId + '">' ;  
									html += '<img src="" />'
									html += '</a>' ;
									_this.html(html) ;
									_this.find('img').bind('load', function(){
										$(document).trigger('resize');
									}) ;
									if(bindEvent != '' && bindEventFunction != ''){
										_this.bind(bindEvent , function(){
											eval(bindEventFunction) ;
										}) ;
									}
									_this.find('img').attr('src',banner.bannerPicUrl) ;
								}
							}
							
						}) ;
						
						self._initGtrack() ;
						
					} else {
						//加载banner错误
					}
					
					bannerList.each(function(){
						$(this).removeClass('dd-hide') ;
					}) ;
					
//					//resize
//					setTimeout(function(){
//						$(document).trigger('resize');
//					} , 1000) ;
					
					
				},
				error: function() {
				    //加载banner出现错误
					bannerList.each(function(){
						$(this).removeClass('dd-hide') ;
					}) ;
				}
			}) ;
		} ,
		
		/**
		 * google track
		 */
		_initGtrack:function(){
			if(typeof(_gaq) == 'undefined'){
				return ;
			}
			$("[gtrack]").each(function(){
				var _this = $(this) ;
				var isGtrackInit = $(this).attr('gtrack-init') ;
				if(isGtrackInit == undefined || isGtrackInit == ''){
					_this.click(function(){
						var gtrack = _this.attr('gtrack') ;
						if(gtrack != null){
							//alert(gtrack) ;
							_gaq.push(['_trackPageview',gtrack]); 
						}
					}) ;
					_this.attr('gtrack-init','y') ;
				}
			}) ;
			
						
		},
		
		/**
		 * 居中
		 */
		centerPoint:function(selector){
			if(selector == null || selector == undefined){
				return  ;
			}
			
			if(typeof(fusion2) != 'undefined') {
				fusion2.canvas.getClientRect({
				    onSuccess: function(rect) {
				    	
				    	var bodyClientLeft = $('#dc-body').offset().left ;
				    	var bodyClientRight = $('#dc-body').offset().left + $('#dc-body').width();
				    	
						var left = bodyClientLeft  + (bodyClientRight - bodyClientLeft) / 2 - $(selector).width()/2;
						var top = rect.top + (rect.bottom - rect.top - selector.height())/2;
						if (top <= 10) {
						    top = 20;
						}
						selector.css({
							left:left,top:top
						}) ;
					}
				});
			}
		},
		
		end:0
	};
	
	

	$(function(){
		index.init();
	});
})(jQuery);