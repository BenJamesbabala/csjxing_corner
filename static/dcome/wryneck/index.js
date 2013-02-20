
!(function(){
    var envRoot = $("#envRoot").val();
    var picUploadedRoot = $("#picUploadedRoot").val() ;
	var userId = $("#userId").val();
	var self ;
    var index = {
		init:function(){
			self = this ;
		    this._initQzoneEvent();
			this._initSocialEvent();
			this._initWryneckTest() ;
			$(document).trigger('resize');
			if(userId == undefined || userId == '' || userId == null || parseInt(userId) < 10000){
				setTimeout(function(){
					$(document).trigger('relogin');
				} ,3000);
			}
		},
		
		/**
		 * ���²���Ჱ��״̬
		 */
		updateTestStatus:function(status){
			var testbtn = $(".test-btn-click") ;
			var btn = testbtn.find('b') ;
			if(status == 'testing'){
				testbtn.find('.label').html('') ;
				btn.html('������...') ;
			} else {
				testbtn.find('.label').html('������������Ჱ��') ;
				btn.html('��ʼ����') ;
				btn.css({right: '10px'}) ;
			}
		} ,
		
		/**
		 * ������������Ჱ��
		 */
		_initWryneckTest:function(){
			$(".test-btn-click").click(function(){
				
				var _this = $(this) ;
				
				self.updateTestStatus('testing') ;
				
				_this.find('b').animate({right: '240px'}, 2000 , function(){
					
					$.ajax({
						url: envRoot + "/frame/wryneck/remote/wryneck_test_ajax.htm",
						type: "POST",
						data: {},
						success:function(result){
							var json = result.json;
							if (json.code == 'success'){
								var resultModel = json.data ;
								var showText = resultModel.showText ;
								var name = resultModel.name ;
								
								//��������
								var msg = showText + '����������������Ჱ�ӣ�';
								if (msg.length > 120) {
								    msg = msg.substring(0, 110) + '...';
								}
								
								var imgUrl = picUploadedRoot + '/wryneck/' + name + '.jpg_80x80.jpg';
								
								$(document).trigger('sendDcStory', {
									summary: '�Ჱ�����ȵ���',
									msg: msg,
									source: '',
									imgUrl: imgUrl ,
									onSuccess: function(opt) {
										window.location.reload() ;
									},
									onCancel: function(opt) {
									},
									onClose: function(opt) {
									}
								});
								
							} else {
								var detail = json.detail ;
								if(detail == 'wryneck.user.notLogin'){
									alert('���ȵ�½') ; 
								} else {
									alert('����') ;
								}
							}
							
							self.updateTestStatus('finish') ;
						} ,
						error:function(result){
							alert('����');
							self.updateTestStatus('finish') ;
						}
					}) ;
					
				}) ;
				
			}) ;
		} ,
		
		_initQzoneEvent: function() {
			
			var weiboframe = '<iframe src="http://follow.v.t.qq.com/index.php?c=follow&a=quick&name=doucomewang&style=4&t=1357101668474&f=0" frameborder="0" scrolling="auto" width="125" height="27" marginwidth="0" marginheight="0" allowtransparency="true"></iframe>' ;
			var qzoneframe = '<iframe class="follow-qzone" src="http://open.qzone.qq.com/like?url=http%3A%2F%2Fuser.qzone.qq.com%2F10507600&type=button&width=57&height=23&style=3" allowtransparency="true" scrolling="no" border="0" frameborder="0" style="width:57px;height:23px;margin-top: 5px; margin-left: 40px;border:none;overflow:hidden;"></iframe>' ; 
			
		    //�ռ��ע��ť
			$.ajax({
			    url: envRoot + "/frame/wryneck/remote/is_qzone_fans_ajax.htm",
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