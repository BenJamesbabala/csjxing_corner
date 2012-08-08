$(function(){
	
	if(typeof(fusion2) != 'undefined'){ //接入QQ平台
	
		//设置平台应用高度
		fusion2.canvas.setHeight({height : 1300});
		
		fusion2.canvas.setScroll({top:50}) ;
		
		//外链
		$("a").each(function(){
			var url = $(this).attr('data-href') ;
			if(typeof(url) != 'undefined' && url != ''){
				$(this).click(function(){
					fusion2.nav.open({url : url}) ;
				}) ;
			}
		});
	} else { //没有接入QQ
		//外链
		$("a").each(function(){
			var url = $(this).attr('data-href') ;
			if(url != ''){
				$(this).attr('href',url) ;
				var target = $(this).attr('data-target') ;
				if(target != ''){
					$(this).attr('target' , target) ;
				}
			}
		});
	}
	
});