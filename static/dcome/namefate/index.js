
!(function(){
    var envRoot = $("#envRoot").val();
    var picUploadedRoot = $("#picUploadedRoot").val() ;
	var userId = $("#userId").val();
	var self ;
    var index = {
		init:function(){
			self = this ;
			self._initTrickEvent() ;
			self._initQzoneEvent();
			self._initSocialEvent();
			self._initIntelIpt() ;
			$(document).trigger('resize');
			if(userId == undefined || userId == '' || userId == null || parseInt(userId) < 10000){
				if($("#suppressLogin").val() != 'true') {
					setTimeout(function(){
						$(document).trigger('relogin');
					} ,3000);
				}
			}
		},
		
		errorIpt:function(text){
			$("#trickForm .error-area").find('span').html(text) ;
			$("#trickForm .error-area").removeClass('dd-hide') ;
		} ,
		
		/**
		 * 
		 */
		_initTrickEvent:function(){
			var form = $("#trickForm") ;
			form.find(".submit").click(function(){
				var trickInputName = form.find("input[name=trickInputName]").val() ;
				var trickInputTaName = form.find("input[name=trickInputTaName]").val() ;
				if(trickInputName == ''){
					self.errorIpt('���������������') ;
					return ;
				}
				if(trickInputTaName == ''){
					self.errorIpt('������TA��������') ;
					return ;
				}
				
				var url = envRoot + '/frame/namefate/remote/namefate_trick_ajax.htm' ;
				
				var formSerialize = decodeURIComponent(form.serialize(),true) ;
				
				$.ajax({
					url : url ,
					type : "POST" ,
					data : encodeURI(encodeURI(formSerialize)),
					success : function(json){
						var code = json.code ;
						var data = json.data ;
						if(code == 'success') {
							window.location.href = envRoot + '/frame/namefate/trick_result.htm?trickId=' + data ;
						} else {
							if(code == 'ill_args'){
								var detail = json.detail ;
								if(detail == 'namefate.trick.user.required'){
									self.errorIpt('�㻹û�е�¼��');
								} else if(detail == 'namefate.trick.ipt.name.required'){
									self.errorIpt('���������������') ;
								} else if(detail == 'namefate.trick.ipt.taname.required') {
									self.errorIpt('������TA��������') ;
								} else {
									self.errorIpt('���������ڿ�С����Ժ����ԣ�');
								}
							} else {
								self.errorIpt('���������ڿ�С����Ժ����ԣ�');
							}
						}
					} ,
					error : function(data){
						self.errorIpt('���������ڿ�С����Ժ����ԣ�');
					}
				}) ;
			}) ;
		} ,
		
		/**
		 * ��������
		 */
		_initIntelIpt:function(){
			$('.intel-ipt').each(function(){
				var _this = $(this) ;
				var label = _this.attr('data-inte-label') ;
				
				_this.focus(function(){
					_this.addClass('focus') ;
				}) ;
				
				_this.blur(function(){
					_this.removeClass('focus') ;
				}) ;

			}) ;
		} ,
	
		_initQzoneEvent: function() {
			
			var weiboframe = '<iframe src="http://follow.v.t.qq.com/index.php?c=follow&a=quick&name=doucomewang&style=4&t=1357101668474&f=0" frameborder="0" scrolling="auto" width="125" height="27" marginwidth="0" marginheight="0" allowtransparency="true"></iframe>' ;
			var qzoneframe = '<iframe class="follow-qzone" src="http://open.qzone.qq.com/like?url=http%3A%2F%2Fuser.qzone.qq.com%2F10507600&type=button&width=57&height=23&style=3" allowtransparency="true" scrolling="no" border="0" frameborder="0" style="width:57px;height:23px;margin-top: 5px; margin-left: 40px;border:none;overflow:hidden;"></iframe>' ; 
			
		    //�ռ��ע��ť
			$.ajax({
			    url: envRoot + "/frame/namefate/remote/is_qzone_fans_ajax.htm",
				type: "POST",
				data: {},
				success: function(result) {
				    var json = result.json;
					if (json.code == 'success') {
					    var data = json.data;
						if (!data.isFans) {//û�й�ע�ռ�
						    $('body').append('<div class="qzone" id="qzone"><div class="label">��ע�ռ�Ӯ��������</div><div class="follow">' + qzoneframe + '</div></div>');
							//
							$(".follow-qzone").load(function() {
							    $.ajax({
								    url: envRoot + "/frame/q/remote/rest/follow_qzone_ajax.htm",
									type: "POST",
									data: {},
									success: function(result) {
									    var json = result.json;
										if (json.code == 'success') {
										    var _qzone = $(".qzone");
											_qzone.remove();
										}
									},
									error: function() {
									    alert("����");
									}
								})
							});
						} else {
							//�Ѿ���ע�ռ䣬����û��ע΢��
							$('body').append('<div class="qzone" id="qzone"><div class="label">�����ٷ�΢��</div><div class="follow weibo">' + weiboframe + '</div></div>');
						}
					}
				},
				error: function() {
				}
			});
			
		},
				
		_initSocialEvent: function() {
			
			$(".pic-list li").hover(function(){
				$(this).append('<div class="download"></div>')
			} , function(){
				$(this).find('.download').remove() ;
			}) ;
			
			$(".pic-list li").click(function(){
				var _this = $(this);
				var name = _this.attr('data-name') ;
				
				var msg = '�����������Ჱ��ͷ�񣬿�������������������Ჱ��ͷ�񣬺���һ�����ȵ�����ʱ�ڣ����Ჱ��һ�£�������Ե���ȱ�ٹ��ף�' ;
				if (msg.length > 120) {
				    msg = msg.substring(0, 110) + '...';
				}
				
				var imgUrl = _this.find('img').attr('src') ;
				
				$(document).trigger('sendDcStory', {
					summary: '�Ჱ�����ȵ���',
					msg: msg,
					source: '',
					imgUrl: imgUrl ,
					onSuccess: function(opt) {
						window.location.href = envRoot + '/frame/wryneck/wryneck_pic_download.htm?name=' + name ;
					},
					onCancel: function(opt) {
					},
					onClose: function(opt) {
					}
				});
			}) ;
			
			$(".invite-btn").click(function() {
			    var _this = $(this);
			    var _fateContent = _this.closest(".fate-detail").find(".fate-content");
				if (_fateContent.size() == 0) {
				     return ;
				}
				$(document).trigger("inviteFriend", {
				    msg: "���ҵ����ҵ��Ჱ��ͷ����Ҳ�������ң�",
					source: '',
					onSuccess: function(opt) {
					    
					},
					onClose: function(opt) {
					
					}
				});
			});
			$(".weibo .close-btn").click(function() {
			
			});
		},
		
		end:0
	};

	$(function(){
		index.init();
	});
})();