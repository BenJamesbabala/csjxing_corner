!(function($){
	$.namespace("DD.My");
	
	var self = DD.My;
	
	var ddzRoot	= $("#ddzRoot").val() ;

	$.extend(DD.My,{
		
		Email_Regex : /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/,
		Mobile_Regex : /^1[3458]\d{9}$/,
		
		init:function(){
			String.prototype.trim = function(){
			    return this.replace(/(^\s*)|(\s*$)/g, "");
			}
			self._initConfirmAlipay();
			
			self._initReSettle() ;
		},
		
		/**
		 * 对于失败的重新进行结算
		 */
		_initReSettle:function(){
			
			$(".resettle-btn").click(function(){
				var _this = $(this) ;
				var settleId = _this.attr('data-settle-id') ;
				if(settleId == ''){
					return ;
				}
				
				if(!confirm('确定要重新打款')){
					return ;
				}
				
				var url = ddzRoot + '/zhe/remote/rest/resettle_ajax.htm' ;
				
				$.ajax({
					url : url ,
					data : {settleId:settleId} , 
					type : "POST" ,
					success : function(data){
						try {
							var code = data.json.code ;
							var detail = data.json.detail ;
							if(code == 'success'){
								window.location.href = ddzRoot + '/zhe/my.htm?type=drawing' ;
							} else {
								if(detail = 'ddz.settle.user.required'){
									alert('您还没有登陆');
								} else if(detail = 'ddz.settle.id.required'){
									alert('系统忙，请稍后再试！');
								} else {
									alert('系统忙，请稍后再试！');
								}
							}
						}catch(e){
							alert('系统忙，请稍后再试！');
						}
						
					} ,
					error : function(data){
						alert('系统忙，请稍后再试！');
					}
				});
				
			}) ;
		} ,
		
		_initConfirmAlipay:function(){
			
			//关闭对话框
			$("#xbox-mock .close").click(function(){
				self.hideConfirmAlipayDialog() ;
			});
			
			$(".myhome .change-bind").click(function(){
				self.showConfirmAlipayDialog() ;
			});
			
			//提现
			$(".myhome .drawout").click(function(){
				var verifyAlipayId = $("#verifyAlipayId").val() ;
				var confirmStr = "确定要提现到支付宝吗？" ;
				if(verifyAlipayId != undefined && verifyAlipayId != ''){
					confirmStr = "确定要提现到支付宝【" + verifyAlipayId + "】吗？" ;
				}
				if(!confirm(confirmStr)){
					return ;
				}
				$.ajax({
					url : ddzRoot + '/zhe/remote/rest/draw_out_ajax.htm',
					data : {} , 
					type : "POST" ,
					success : function(data){
						try {
							var code = data.json.code ;
							var detail = data.json.detail ;
							if(code == 'success'){
								alert('您的提现申请已经提交，预计一工作日内会打入您的支付宝！');
								window.location.href = ddzRoot + '/zhe/my.htm?type=txz' ;
							} else if(code == 'ill_args'){
								if(detail == 'ddz.settle.manual.noSufficient'){
									alert('没有可提现的记录！');
								}
							} else {
								alert('提现失败，请稍后再试！');
							}
						}catch(e){
							alert('提现失败，请稍后再试！');
						}
						
					} ,
					error : function(data){
						alert('系统忙，请稍后再试！');
					}
				});
			});
			
			$("#xbox-mock .submit").click(function(){
				var alipayId = $("#confirmAlipay").val() ;
				if(alipayId == ''){
					alert('支付宝不能为空') ;
					return ;
				}
				
				var isEmail = alipayId.match(self.Email_Regex) ;
				var isMobile = alipayId.match(self.Mobile_Regex) ;
				
				if(!isEmail && !isMobile){
					alert('输入的支付宝格式错误，请检查是否为Email地址或手机号') ;
					return ;
				}
				
				$.ajax({
					url : ddzRoot + '/zhe/remote/rest/confirm_login_alipay_ajax.htm',
					data : {bindAlipayAccount:alipayId.trim()} , 
					type : "POST" ,
					success : function(data){
						try {
							var code = data.json.code ;
							var detail = data.json.detail ;
							if(code == 'success'){
								alert('成功！');
								window.location.reload() ;
							} else {
								if(detail == "ddz.confirm.alipay.modification.maxCount"){
									alert('您的账号已经被锁定，请联系客服。') ;
								} else {
									alert('绑定失败') ;
								}
							}
						}catch(e){
							alert('绑定失败！');
						}
						
					} ,
					error : function(data){
						alert('绑定失败！');
					}
				});
				
			}) ;
			//end $("#confirmAlipaySubmit").click(function(){
			
			//查询有几笔可以提现的款
//			$.ajax({
//				url : ddzRoot + '/zhe/remote/rest/query_manual_settle_ajax.htm',
//				data : {} , 
//				type : "POST" ,
//				success : function(data){
//					try {
//						var code = data.json.code ;
//						if(code == 'success'){
//							var resl = data.json.data ;
//							var count = resl.count ;
//							
//							if(count == 0){
//								$(".myhome .drawout").hide() ;
//								$(".myhome .user-draw").html('您没有可提现的返利  &nbsp; <a class="med-button-s-disabled drawout" href="javascript:;" id="J_withdraw"><span>提现到支付宝</span></a>');
//								$(".myhome .user-draw").removeClass('dd-hide') ;
//							} else {
//								$(".myhome .count").html(count) ;
//								$(".myhome .amount").html(resl.amount) ;
//								$(".myhome .user-draw").removeClass('dd-hide') ;
//							}
//						} else {
//							
//						}
//					}catch(e){
//						
//					}
//					
//				} ,
//				error : function(data){
//					
//				}
//			});
		},
		
		/**
		 * 
		 */
		showConfirmAlipayDialog:function(){
			$("#xbox-mock").removeClass('dd-hide') ;
			$("#xbox-overlay").removeClass('dd-hide') ;
		} ,
		
		/**
		 * 
		 */
		hideConfirmAlipayDialog:function(){
			$("#xbox-mock").addClass('dd-hide') ;
			$("#xbox-overlay").addClass('dd-hide') ;
		} ,
		
		end:0
	});
})(jQuery);

jQuery(document).ready(function(){
	DD.My.init();
});
