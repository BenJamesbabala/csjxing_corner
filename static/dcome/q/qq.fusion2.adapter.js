$(function(){
	
	if(typeof(fusion2) != 'undefined'){ //����QQƽ̨
	
		//����ƽ̨Ӧ�ø߶�
		fusion2.canvas.setHeight({height : 1300});
		
		fusion2.canvas.setScroll({top:50}) ;
		
		//����
		$("a").each(function(){
			var url = $(this).attr('data-href') ;
			if(typeof(url) != 'undefined' && url != ''){
				$(this).click(function(){
					fusion2.nav.open({url : url}) ;
				}) ;
			}
		});
	} else { //û�н���QQ
		//����
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