!(function(){	
	var self ;
	var common = {	
		init:function(){
			self = this ;
			//¹È¸è´òµã
			self._initGtrack();
		},
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
		end:0
	};
	$(function(){
		common.init();
	});
})();


