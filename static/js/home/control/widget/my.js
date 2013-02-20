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
		 * ����ʧ�ܵ����½��н���
		 */
		_initReSettle:function(){
			
			$(".resettle-btn").click(function(){
				var _this = $(this) ;
				var settleId = _this.attr('data-settle-id') ;
				if(settleId == ''){
					return ;
				}
				
				if(!confirm('ȷ��Ҫ���´��')){
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
									alert('����û�е�½');
								} else if(detail = 'ddz.settle.id.required'){
									alert('ϵͳæ�����Ժ����ԣ�');
								} else {
									alert('ϵͳæ�����Ժ����ԣ�');
								}
							}
						}catch(e){
							alert('ϵͳæ�����Ժ����ԣ�');
						}
						
					} ,
					error : function(data){
						alert('ϵͳæ�����Ժ����ԣ�');
					}
				});
				
			}) ;
		} ,
		
		_initConfirmAlipay:function(){
			
			//�رնԻ���
			$("#xbox-mock .close").click(function(){
				self.hideConfirmAlipayDialog() ;
			});
			
			$(".myhome .change-bind").click(function(){
				self.showConfirmAlipayDialog() ;
			});
			
			//����
			$(".myhome .drawout").click(function(){
				var verifyAlipayId = $("#verifyAlipayId").val() ;
				var confirmStr = "ȷ��Ҫ���ֵ�֧������" ;
				if(verifyAlipayId != undefined && verifyAlipayId != ''){
					confirmStr = "ȷ��Ҫ���ֵ�֧������" + verifyAlipayId + "����" ;
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
								alert('�������������Ѿ��ύ��Ԥ��һ�������ڻ��������֧������');
								window.location.href = ddzRoot + '/zhe/my.htm?type=txz' ;
							} else if(code == 'ill_args'){
								if(detail == 'ddz.settle.manual.noSufficient'){
									alert('û�п����ֵļ�¼��');
								}
							} else {
								alert('����ʧ�ܣ����Ժ����ԣ�');
							}
						}catch(e){
							alert('����ʧ�ܣ����Ժ����ԣ�');
						}
						
					} ,
					error : function(data){
						alert('ϵͳæ�����Ժ����ԣ�');
					}
				});
			});
			
			$("#xbox-mock .submit").click(function(){
				var alipayId = $("#confirmAlipay").val() ;
				if(alipayId == ''){
					alert('֧��������Ϊ��') ;
					return ;
				}
				
				var isEmail = alipayId.match(self.Email_Regex) ;
				var isMobile = alipayId.match(self.Mobile_Regex) ;
				
				if(!isEmail && !isMobile){
					alert('�����֧������ʽ���������Ƿ�ΪEmail��ַ���ֻ���') ;
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
								alert('�ɹ���');
								window.location.reload() ;
							} else {
								if(detail == "ddz.confirm.alipay.modification.maxCount"){
									alert('�����˺��Ѿ�������������ϵ�ͷ���') ;
								} else {
									alert('��ʧ��') ;
								}
							}
						}catch(e){
							alert('��ʧ�ܣ�');
						}
						
					} ,
					error : function(data){
						alert('��ʧ�ܣ�');
					}
				});
				
			}) ;
			//end $("#confirmAlipaySubmit").click(function(){
			
			//��ѯ�м��ʿ������ֵĿ�
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
//								$(".myhome .user-draw").html('��û�п����ֵķ���  &nbsp; <a class="med-button-s-disabled drawout" href="javascript:;" id="J_withdraw"><span>���ֵ�֧����</span></a>');
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
