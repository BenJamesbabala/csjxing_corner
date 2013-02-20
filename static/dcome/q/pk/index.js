
!(function(){
	var envRoot = $("#envRoot").val();
	var itemUploadedRoot = $("#itemUploadedRoot").val();
	var errorAvatarUrl = $("#picUploadedRoot").val() + "/avatar/0/0/0/0/0.jpg" ;
	var userId = $("#userId").val() ;
	var myPromotionItemId = $("#myPromotionItemId").val() ;
	var bodyClientLeft = $('#dc-body').offset().left ;
	var bodyClientRight = $('#dc-body').offset().left + $('#dc-body').width();
	var guideMap = null ;
	var promotionItemGuideMap = null ;
		
	var dlgTimer = null ;
	//tip��ʾ
	var tipDialog = $("#tipDialog") , tipDialog_timer = null;
	var rollupTimer = null, ugcTimer = null;
	var waterfallHandler = null;
	//����Ը������ʾ
	var wishTipDialog = $("#wishTipDialog") , wishTipDialog_timer = null ;
	
	var wishDialog = $("#wishDialog");
	
	var shadowBg = $("#shadowBg");
	
	var wishGuide = $("#wishGuide");
	
	var integralGuide = $("#integralGuide");
		
	var self ;
	
	var index = {
				
		init:function(){
			
			try {
				guideMap = eval('('+$('#newGuideJson').val()+')') ;
				
			} catch(e){
				guideMap = eval('({})');
			}
			
			try {
				promotionItemGuideMap = eval('('+$('#promotionItemNewGuideJson').val()+')') ;
			} catch(e){
				promotionItemGuideMap = eval('({})');
			}
						
			
			self = this ;
				
			self._initCommonEvents() ;
			
			self._initAppAwards() ;
			////////////////////////////////////////////this._initDetailPage();
			self._initPubWishEvent();
			self._initNewGuide();
			self._initUgcRelatedEvent();
			self._pageScrollEvent();
			self._initQzoneEvent();
			self._initItemShare();
//			//��ʼ��Ӧ��header���ֵ����ݺ��¼�
			self._initHeader();
			self._initExchangeEvent();
			self._initLayerEvent();
			if(userId == undefined || userId == '' || userId == null || parseInt(userId) < 10000){
				setTimeout(function(){
					$(document).trigger('relogin') ;
				} ,3000);
			}
			$(document).trigger('resize');
		},
		
		/**
		 * ͨ���¼�
		 * 1.ͶƱ
		 * 2.�ٲ�
		 * 3.�ʣ��ʱ��
		 */
		_initCommonEvents:function(){
			String.prototype.replaceAll = function(s1,s2){   
				return this.replace(new RegExp(s1,"gm"),s2);   
			}
			
			self._initWaterfall();
			//��ȡԸ��ֵ
			//self._initPromDrawRateEvent() ;
			//�ҵ��н���Ϣ
			//self._initMyAward() ;
			
			//����ͨ���¼�
			self._initGtrack();
			self._initPlaceholder() ;
			self._initUserAvatar() ;
			//��ʼ���ҵ�Ը������¼�������
			self._initIwish();
		},
		
		_initWaterfall: function() {
		    //jsӦ�÷���
		    if (window.location.href.indexOf("pgcs.htm") != -1) {
				self._initItemTabEvent();
			    if (window.location.href.indexOf("#ugc") != -1) {
					$(".item-tab .ugc-tab").trigger("click");
				} else {
				    $(".item-tab .pgc-tab").trigger("click");
				}
			}
		},
		
		_resizeWaterfall: function(options) {
		    var waterfallHandler = options.container.find('li');
			waterfallHandler.wookmark(options);
			$(document).trigger("resize");
		},
		
		_pageScrollEvent: function() {
		    fusion2.iface.updateClientRect(function (rect) {
			    //�����˵�����
				var _menuNav = $(".menu-nav");
				var _firstLi = $("#tiles").find("li:first");
				if (_menuNav.length > 0) {
					if (rect.top > 298 || (_firstLi.length > 0 && rect.top > _firstLi.offset().top)) {
						_menuNav.css({"position": "fixed", "top": rect.top});
						$("#qzone").css({"top": rect.top + 200});
					} else {
						_menuNav.css({"position": "absolute", "top": 120});
						$("#qzone").css({"top": 340});
					}
				};
				//��Ʒ�ٲ�����
					
				if (window.location.href.indexOf("pgcs.htm") != -1) {
					var _lastLi = $("#waterfall li:last");
					if (rect.top + 250 > _lastLi.offset().top) {
						if ($(".item-tab .ugc-tab").closest("li").hasClass("active")) {
							if (!$("#waterfallUgcMore").hasClass("dd-hide")) {
								$("#waterfallUgcMore").trigger("click");
							}
						} else {
							if (!$("#waterfallMore").hasClass("dd-hide")) {
								$("#waterfallMore").trigger("click");
							}
						}
					}
				} else if (window.location.href.indexOf("promotion.htm") != -1) {
				    var _lastLi = $("#tiles li:last");
					if (rect.top + 250 > _lastLi.offset().top) {
						if (!$("#promotionWaterfallMore").hasClass("dd-hide")) {
							$("#promotionWaterfallMore").trigger("click");
						}
					}
				}
			});
		},
		
		_ancestor: function(curObj, name) {
			var tempObj = curObj.parent();
			if (tempObj == undefined) {
				return nil;
			} else if (tempObj.is(name)) {
				return tempObj;
			} else {
				return self._ancestor(tempObj, name);
			}
		},
		
		_initQzoneEvent: function() {
		    //�ռ��ע��ť
			$.ajax({
			    url: envRoot + "/frame/q/remote/rest/is_qzone_fans_ajax.htm",
				type: "post",
				data: {},
				success: function(result) {
				    var json = result.json;
					if (json.code == 'success') {
					    var data = json.data;
						if (!data.isFans) {//û�й�ע�ռ�
						    var _container = $('.cnt-pgc .item-head .other');
						    $('body').append('<div class="qzone" id="qzone"><iframe class="follow-qzone" src="http://open.qzone.qq.com/like?url=http%3A%2F%2Fuser.qzone.qq.com%2F10507600&type=button&width=57&height=23&style=3" allowtransparency="true" scrolling="no" border="0" frameborder="0" style="width:57px;height:23px;margin-top: 5px; margin-left: 40px;border:none;overflow:hidden;"></iframe></div>');
							//��ʼ��׬�����еĹ�ע��ť.
							integralGuide.find(".qzone-l").html('');
							integralGuide.find(".qzone-l").append('<div class="qzone-wrap"><iframe class="follow-qzone" src="http://open.qzone.qq.com/like?url=http%3A%2F%2Fuser.qzone.qq.com%2F10507600&type=button&width=57&height=33&style=3" allowtransparency="true" scrolling="no" border="0" frameborder="0" style="width:57px;height:23px;border:none;overflow:hidden;"></iframe></div>');
							$(".follow-qzone").load(function() {
							    $.ajax({
								    url: envRoot + "/frame/q/remote/rest/follow_qzone_ajax.htm",
									type: "post",
									data: {},
									success: function(result) {
									    var json = result.json;
										if (json.code == 'success') {
										    var _qzone = $(".qzone");
											var integral = json.data.integral;
											if (integral > 0) {
												self.showTipDialog({parent:_qzone , msg:'��ע�ɹ�������' + integral + '����'});
												self.modifyUserIntegral({integral:integral}) ;
											}
											integralGuide.find(".qzone-l").html('');
											integralGuide.find(".qzone-l").append('<div class="done"></div>')
											_qzone.remove();
										}
									},
									error: function() {
									}
								})
							});
						} else {
							//�Ѿ���ע�ռ䣬����û��ע΢��
							$(".menu-nav .weibo").removeClass('dd-hide') ;
						}
					}
				},
				error: function() {
				}
			});
		},
		
		_initHeader: function() {
		    //��Ϸ����
			//self._initPkRuleEvent();
		    //�˵��л�
			var _menuNav = $(".menu-nav");
			_menuNav.find(".menu-list li").hover(function() {
			    var _this = $(this);
				if (!_this.hasClass("cur") && _this.find(".ugc-btn").length == 0) {
				    _this.addClass("on");
				}
			}, function() {
			    var _this = $(this);
				_this.removeClass("on");
			});
			//����ʱ,Ը��ǽ�ͻ��־���
			//self._initMenuCDTimer();
			//�н����
			self._bindAwardEvent();
			//�û���Ϣ
			self._initUserMessage();
			//ǩ��
			self._initDailyCheckin();
			self._initInviteFriend();
		},
		_initInviteFriend: function() {
		    $(".invite-btn").click(function() {
			    var _this = $(this);
			    var customParma = '{"shareUserId":' + userId + ',"shareContent":"app"}';
				$(document).trigger("inviteFriend", {
				    msg: "�ҵ�һ���������ܸ�ʡǮ��Ӧ�ã�Ҳ��������",
					source: customParma,
					onSuccess: function(opt) {
					    if (opt.invitees.length == 0) {
						    self.showTipDialog({parent:_this , msg:'��û����������Ŷ~~'});
							return ;
						}
					    $.ajax({
							url: envRoot + "/frame/q/remote/rest/send_invite_ajax.htm",
							type: "POST",
							data: {inviteeOpenIds: opt.invitees.join(",")},
							success: function(result) {
							    self.showTipDialog({parent:_this , msg:'����ɹ������ѵ�½��ϵͳ������10����'});
							    /**
								var json = result.json;
								if (json.code == 'success') {
								    if (json.data > 0) {
										//���»���
										self.modifyUserIntegral({integral:json.data});
										self.showTipDialog({parent:_this , msg:'����ɹ�������' + json.data + '����'});
									} else {
									    self.showTipDialog({parent:_this , msg:'���Ѿ��ܾ�û��������������Ŷ����Ϣһ�°ɣ�'});
									}
									self.refreshIntegralBtn("invite-btn", json.data);
								} else {
								    if (json.detail == 'no.invitee') {
									    self.showTipDialog({parent:_this , msg:'��û����������Ŷ~~'});
									} else {
									    self.showTipDialog({parent:_this , msg:'����ʧ��~~'});
									}
								}
								**/
							},
							error: function() {
								alert("�ص�ʧ��");
							}
						});
					},
					onClose: function(opt) {
					
					}
				});
			});
		},
		//�û���Ϣ����¼�
		_initUserMessage: function() {
		    var _topNav = $(".top-nav");
		    _topNav.find(".message-area").hover(function() {
				var messages = $(this).find(".messages");
				if (messages.length > 0) {
				    $(this).css({"background-color": "#ffffff"});
				    messages.show();
				}
			}, function() {
			    var messages = $(this).find(".messages");
				if (messages.length > 0) {
				    $(this).removeAttr("style");
				    messages.hide();
				}
			});
			$.ajax({
			    url: envRoot + "/frame/q/remote/rest/count_user_message_ajax.htm",
				type: "post",
				data: {},
				success: function(result) {
				    var json = result.json;
					if (json.code == 'success') {
					    var data = json.data;
						var _messageArea = _topNav.find(".message-area");
						var num = data.totalCount;
						if (num > 0) {
						    _messageArea.append('<span class="total-num">' + num + '</span>');
							_messageArea.find(".messages .normal-title").append('<span class="normal-num num">' + num + '</span>');
						}
						num = data.award;
						var contents = _messageArea.find(".content-list");
						if (num > 0) {
						    contents.append('<li class="award">�㻹��<a class="my-award high-light" href="javascript:;">' + num + '����Ʒ</a>û����ȡŶ</li>');
						}
						var message = data.message;
						if (message != undefined) {
						    for (var i = 0; i < message.length; i++) {
								if (message[i].source == 'NOTI') {
									contents.append('<li class="message">' + message[i].integralDescStr + '��<a class="message-read high-light" href="javascript:;" data-id="' + message[i].id + '">֪����</a></li>');
								} else if (message[i].source == 'UGC') {
									contents.append('<li class="message">���Ƽ�����Ʒ��ϵͳ����' + message[i].integralCount + '���֡�<a class="message-read high-light" href="javascript:;" data-id="' + message[i].id + '">֪����</a></li>');
								} else if (message[i].source == 'AUGC') {
									contents.append('<li class="message">ϵͳ���������Ƽ�����Ʒ������' + message[i].integralCount + '���֡�<a class="message-read high-light" href="javascript:;" data-id="' + message[i].id + '">֪����</a></li>');
								} else if (message[i].source == 'BUGC') {
									contents.append('<li class="message">���˹��������Ƽ�����Ʒ��ϵͳ����' + message[i].integralCount + '���֡�<a class="message-read high-light" href="javascript:;" data-id="' + message[i].id + '">֪����</a></li>');
								}
							}
							contents.find(".message-read").click(function() {
							    var _this = $(this);
							    $.ajax({
								    url: envRoot + '/frame/q/remote/rest/read_message_ajax.htm',
									type: "POST",
									data: {messageId: _this.attr("data-id")},
									success: function(result) {
									    if (result.json.code == 'success') {
										    _this.closest(".message").remove();
											var _num = _messageArea.find(".normal-num");
											if (!isNaN(_num.html())) {
											    var num = parseInt(_num.html()) - 1;
												_num.html(num)
											    _messageArea.find(".total-num").html(num);
											}
										}
									},
									error: function() {
									
									}
								});
							})
						}
						contents.append('<li>�Ա���Ʒ���ܷ�����Ŷ������<a class="ugc-btn high-light" href="javascript:;" title="�Ա����ﷵ����" gtrack="/virtual/qq/ugc?message">�Ա����ﷵ����</a>��</li>');
						contents.find(".none-message").remove();
						self._initGtrack();
					}
				},
				error: function() {
				}
			});
		},
		//�н���Ϣ����¼�
		_bindAwardEvent: function(){
			var _awardDialog = $('#awardDialog');
			$(document).undelegate('.my-award', 'click');
			$(document).delegate('.my-award', 'click', function(){
				_awardDialog.hide();
				self.centerPoint(_awardDialog);
				
				if(_awardDialog.find('.main').find('dl dd').size() >= 1) {
				    _awardDialog.show(800);
				    return;
				}
				$.ajax({
					url : envRoot + "/frame/q/remote/rest/my_message_ajax.htm",
					type:"POST" ,
					data:{clear:'y'} ,
					success : function(data){
						var json = data.json ;
						var code = json.code ;
						var data = json.data ;
						var detail = json.detail ;
						if(code == 'success') {
							var appendHtml = '' ;
							if(data.length > 0){
								for(var i=0 ;i<data.length;i++){
									var item = data[i] ;
									var template = _awardDialog.find(".template").html() ;
									try {
										template = template.replaceAll('@time@' , item.gmtCreateFormat) ;
										if (item.promotionType == 'PK') {
											template = template.replaceAll('@type@' , 'Ը����PK');
										} else if (item.promotionType == 'AU') {
											template = template.replaceAll('@type@' , '���־���');
										} else if (item.promotionType == 'EX') {
											template = template.replaceAll('@type@' , '���ֶһ�');
										} else {
											template = template.replaceAll('@type@' , '');
										}
										if (item.sendStatus == 'S') {
										    template = template.replaceAll('@status@' , '���ͳ�');
										} else {
										    var temp = 'δ�ͳ�/';
											if (item.delName == undefined || item.delMobile == undefined) {
											    temp = temp + 'δ�ύ�ҽ���Ϣ';
											} else {
											    temp = temp + '���ύ�ҽ���Ϣ';
											}
										    template = template.replaceAll('@status@' , temp);
										}
										if (item.shareStatus == 'Y') {
											template = template.replaceAll('@code@' , '<a class="addr-info" href="javascript:;" title="�ҵ��ջ���Ϣ" data-id="'
											  + item.id + '" data-item-id="' + item.itemId + '" data-item-title="' + item.itemTitle + '" data-item-pic="'
											  + item.itemPic160x160 + '" data-item-price="' + item.itemPriceFmt +'">�ҵ��ջ���Ϣ</a>');
										} else {
											template = template.replaceAll('@code@', '<a class="share-award" data-id="'
											  + item.id + '" data-item-id="' + item.itemId + '" data-item-pic="' + item.itemPic160x160
											  +'" data-item-title="' + item.itemTitle + '" data-item-price="' + item.itemPriceFmt
											  + '" title="�������Ѻ���ȡ��Ʒ"  href="javascript:;">����3�����Ѻ���ȡ��Ʒ</a>');
										}
										template = template.replaceAll('@name@' , item.dcItemDTO.itemTitle) ;
									}catch(e){}
									appendHtml += template ;
								}
								_awardDialog.find('.foot').removeClass('dd-hide') ;
							} else {
								appendHtml = _awardDialog.find('.nodata-template').html() ;
							}
							_awardDialog.find('dl').append(appendHtml) ;
							$('#imsgDialog').addClass('dd-hide') ;
						} else {
							_awardDialog.find('dl').append('<dd>��ȡ�н���Ϣ����</dd>');
						}
						_awardDialog.show(800);
					} , 
					error:function(data){
						_awardDialog.find('dl').append('<dd>��ȡ�н���Ϣ����</dd>');
						_awardDialog.show(800);
					}
				});
			}) ;
			
			_awardDialog.delegate('.close-icon', 'click');
			_awardDialog.delegate('.close-icon', 'click', function(){
				_awardDialog.hide(600);
			});
			_awardDialog.undelegate("a.share-award", 'click');
			_awardDialog.delegate("a.share-award", "click", function() {
			    var _this = $(this);
				var id = _this.attr("data-id");
				var itemId = _this.attr("data-item-id");
				var itemPic = _this.attr("data-item-pic");
				var itemTitle = _this.attr("data-item-title");
				var itemPrice = _this.attr("data-item-price");
				if (id == undefined || itemId == undefined) {
				    return ;
				}
				var customParma = '{"shareUserId":' + userId + ',"shareContent":"award","shareObjId":' + id + '}';
				$(document).trigger('inviteFriend',
				   {
					msg:"���ڡ��������������û��ֶһ��ˡ�" + itemTitle + "��������������",
					source: customParma,
					onSuccess: function(opt) {
					    $.ajax({
						    url: envRoot + "/frame/q/remote/rest/share_award_ajax.htm",
							type: "post",
							data: {awardId: id, inviteeOpenIds: opt.invitees.join(",")},
							success: function(result) {
							    var json = result.json;
								if (json.code == 'success') {
								    var _parent = _this.parent();
									_this.remove();
								    _parent.append('<a class="addr-info" href="javascript:;" data-id="' + id + '" data-item-id="' + itemId
									  + '" data-item-title="' + itemTitle + '" data-item-pic="' + itemPic + '" data-item-price="' + itemPrice + '">�ҵ��ջ���Ϣ</a>');
									_parent.find(".addr-info").trigger("click");
								} else {
								    var detail = json.detail;
									if (detail == 'no.award') {
									    self.showTipDialog({parent:_this , msg:'û�в鵽�����н���¼~~'});
									} else {
									    self.showTipDialog({parent:_this , msg:'û�в�ѯ�������н���¼~~'});
									}
								}
							},
							error: function() {
							    self.showTipDialog({parent:_this , msg:'Ӧ��ϵͳ�쳣�����Ժ�����~~'});
							}
						});
					},
					onCancel: function(opt) {
					
					},
					onClose: function(opt) {
					
					}
				});
			});
			
			var winnerLayer = $(".winner-layer");
			_awardDialog.delegate("a.addr-info", "click", function() {
			    var _this = $(this);
				var awardId = _this.data("id");
				if (isNaN(awardId)) {
				    alert("��Ч���콱ID����ˢ��ҳ��");
				}
				winnerLayer.find(".tab-wrap .item-tab").trigger("click");
				$.ajax({
				    url: envRoot + "/frame/q/remote/rest/query_award_addr_ajax.htm",
					type: "post",
					data: {awardId: awardId},
					success: function(result) {
					    var json = result.json;
					    if (json.code == 'success') {
						    var data = json.data;
							var _subBtn = winnerLayer.find(".sub-btn");
							if (data.sendStatus != 'U') {
							    _subBtn.hide();
							} else {
							    _subBtn.show();
								_subBtn.attr("data-id", data.id);
							}
							winnerLayer.find(".user-name").val(data.delName != undefined ? data.delName: '');
							winnerLayer.find(".award-code").html(data.checkCode);
							if (data.itemType == 'V') {
							    winnerLayer.find(".tab-wrap .virtual-tab").trigger("click");
							    winnerLayer.find(".item-price").html(data.itemPrice);
								winnerLayer.find(".account").val(data.delMobile != undefined ? data.delMobile: '');
							} else {
							    winnerLayer.find(".item-price").html(data.itemPrice);
								winnerLayer.find(".item-size").val(data.itemSize != undefined ? data.itemSize: '');
								winnerLayer.find(".item-color").val(data.itemColor != undefined ? data.itemColor: '');
								winnerLayer.find(".user-mobile").val(data.delMobile != undefined ? data.delMobile : '');
								winnerLayer.find(".address").val(data.delAddr != undefined ? data.delAddr: '');
								winnerLayer.find(".zipcode").val(data.delZipcode != undefined ? data.delZipcode: '');
								winnerLayer.find(".other").val(data.delOther != undefined ? data.delOther: '');
							}
						}
					},
					error: function(result) {
					
					}
				});
				winnerLayer.find(".item-pic").attr("src", _this.data("item-pic"));
				var _picWrap = winnerLayer.find(".pic-wrap");
				_picWrap.attr("taoke-href", envRoot + "/frame/q/remote/rest/redirect_item.htm?itemId=" + _this.data("item-id"));
				_picWrap.attr("data-item-id", _this.data("item-id"));
				var _itemTitle = winnerLayer.find(".item-title");
				_itemTitle.html(_this.data("item-title"));
				_itemTitle.attr("taoke-href", envRoot + "/frame/q/remote/rest/redirect_item.htm?itemId=" + _this.data("item-id"));
				_itemTitle.attr("data-item-id", _this.data("item-id"));
				winnerLayer.find(".item-price").html(_this.data("item-price"));
				winnerLayer.find("input").val('');
				winnerLayer.find("textarea").val('');
				winnerLayer.find(".sub-btn").attr("data-id", awardId);
				winnerLayer.find(".id").val(awardId);
				self.centerPoint(winnerLayer);
				winnerLayer.show();
			});
			winnerLayer.find(".hide-btn").click(function() {
			    winnerLayer.hide();
			});
			winnerLayer.find(".tab-wrap a").click(function() {
			    var _this = $(this);
				if (_this.hasClass("active")) {
				    return ;
				}
				_this.parent().find(".active").removeClass("active");
				_this.addClass("active");
				winnerLayer.find(".tab-wrap a").hide();
				_this.show();
				if (_this.hasClass("item-tab")) {
				    winnerLayer.find(".content .item").show();
					winnerLayer.find(".content .virtual-item").hide();
					winnerLayer.find(".sub-btn").attr("data-item-type", "N");
				}
				if (_this.hasClass("virtual-tab")) {
					winnerLayer.find(".content .virtual-item").show();
				    winnerLayer.find(".content .item").hide();
					winnerLayer.find(".sub-btn").attr("data-item-type", "V");
				}
			});
			winnerLayer.find(".sub-btn").click(function() {
			    var _this = $(this);
				var _actTab = winnerLayer.find(".tab-wrap .active");
				if (_actTab.size == 0) {
				    alert("��ѡ����Ʒ���");
				}
				var id = _this.attr("data-id");
				var type = _this.attr("data-item-type");
				var itemSize = '', itemColor = '', delName = '', delMobile = '', delAddr = '', delOther = '';
				var temp;
				if (type == 'N') {
					itemSize = winnerLayer.find(".item .item-size").val();
					itemColor = winnerLayer.find(".item .item-color").val();
					delName = winnerLayer.find(".item .user-name").val();
					delMobile = winnerLayer.find(".item .user-mobile").val();
					delAddr = winnerLayer.find(".item .address").val();
					delOther = winnerLayer.find(".item .other").val();
					if (delName == undefined || delName == '') {
					    alert("����д�ջ�������");
						return;
					}
					if (delMobile == undefined || delMobile == '') {
					    alert("����д�ջ�����ϵ�绰");
						return;
					}
					if (delAddr == undefined || delAddr == '') {
					    alert("����д�ջ��˵�ַ");
						return;
					}
				} else if (type == 'V') {
				    temp = decodeURIComponent($("#virtualItemForm").serialize(), true);
					delName = winnerLayer.find(".virtual-item .user-name").val();
					delMobile = winnerLayer.find(".virtual-item .account").val();
					if (delName == undefined || delName == '') {
					    alert("����д�ջ�������");
						return;
					}
					if (delMobile == undefined || delMobile == '') {
					    alert("����д��ֵ����");
						return;
					}
				} else {
				    alert("����������ˢ��ҳ��");
					return;
				}
				$.ajax({
				    url: envRoot + "/frame/q/remote/rest/update_award_addr_ajax.htm",
					type: "get",
					data: {itemSize: encodeURI(itemSize), itemColor: encodeURI(itemColor), delName: encodeURI(delName), delMobile: encodeURI(delMobile),
					       delAddr: encodeURI(delAddr), delOther: encodeURI(delOther), id: id},
					success: function(result) {
					    var json = result.json;
						if (json.code == 'success') {
						    self.showTipDialog({parent:_this , msg:'�ύ�ɹ���ϵͳ����1�����������ͳ���Ľ�Ʒ'}) ;
							setTimeout(function() {winnerLayer.hide()}, 1500);
						} else {
						    alert("ϵͳ�������Ժ�����");
						}
					},
					error: function(result) {
					    alert("�ύʧ�ܣ����Ժ�����");
					}
				});
			});
		} ,
		
		/**
		 * ÿ��ǩ��
		 */
		_initDailyCheckin:function(){
			//ÿ��ǩ��
			$(".dailycheckin-click").click(function(){
				var url = envRoot + "/frame/q/remote/rest/user_daily_checkin_ajax.htm" ;
				var _this = $(this);
				if(_this.hasClass("checkin-on")){
					self.showTipDialog({parent:_this , msg:'�����Ѿ�ǩ��������'}) ;
				}
				$.ajax({
					url:url , 
					type:"POST" ,
					data : {},
					success : function(data){
						var json = data.json ;
						var code = json.code ;
						var data = json.data ;
						var detail = json.detail ;
						if(code == 'success') { 
							self.showTipDialog({parent:_this , msg:'ǩ���ɹ������' + data + '����'}) ;
							$(".dailycheckin-click").removeClass("checkin");
							$(".dailycheckin-click").addClass("checkin-on");
							self.modifyUserIntegral({integral:data});
							//����׬���ָ��㰴ť״̬
							integralGuide.find(".dailycheckin-click").removeClass("checkin-on");
							self.refreshIntegralBtn("dailycheckin-click", data);
						} else if(code == 'ill_args'){
							if(detail == 'dcome.userTask.userId.required'){
								self.showTipDialog({parent:_this , msg:'�㻹û�е�½Ŷ��'}) ;
							}
							if(detail == 'dcome.userTask.dailyCheckin.duplicate'){
								self.showTipDialog({parent:_this , msg:'�����Ѿ�ǩ��������'}) ;
							}
						} else {
							self.showTipDialog({parent:_this , msg:'ǩ��ʧ�ܣ���ˢ��ҳ�棡'}) ;
						}
					} , 
					error:function(data){
						self.showTipDialog({parent:_this , msg:'ǩ��ʧ�ܣ���ˢ��ҳ�棡'}) ;
					}
				});
			});
		},
		//�˵�����ʱ��ʼ��
		_initMenuCDTimer: function() {
		    $.ajax({
			    url: envRoot + "/frame/q/remote/rest/menu_cd_time_ajax.htm",
				type: "post",
				data: {},
				success: function(result) {
				    var json = result.json;
					if (json.code ==  'success') {
					    var data = json.data;
						var _menuNav = $("#menuNav")
						if (data.pk != undefined) {
						    _menuNav.find("li.pk").append('<div class="countdown-time" data-time="' + data.pk + '"></div>');
						}
						if (data.auction != undefined) {
						    _menuNav.find("li.auction").append('<div class="countdown-time" data-time="' + data.auction + '"></div>');
						}
						_menuNav.find(".countdown-time").each(function() {
						    var _this = $(this);
							_this.css({left: _this.parent().width() / 2});
							var cdTimeSec = parseInt(parseInt(_this.attr("data-time")) / 1000);
							if(cdTimeSec < 0){
								_this.remove();
								return;
							}
							var int = setInterval(function(){
								var cdTimeFmt = self.formatSecondsHHMMSS(cdTimeSec) ;
								_this.html(cdTimeFmt) ;
								cdTimeSec = cdTimeSec - 1 ;
								if(cdTimeSec <= 0){
									clearInterval(int) ;
									_this.remove();
								}
							} , 1000);
						});
					} else {
					    
					}
				},
				error: function() {
				
				}
			});
		},
				
		_initAppAwards:function(){
			//Ӧ�ý�Ʒ��̬
			$(".dynamic-rolling").each(function(){
				var _this = $(this);
				var _awardList = _this.find(".award-list")
				var _awardTemplate = _this.find(".awardTemplate");
				$.ajax({
					url: envRoot + "/frame/q/remote/rest/query_awards_ajax.htm",
					type: "post",
					data: {},
					success: function(result) {
						_awardList.find("li").remove();
						var json = result.json;
						if (json.code == 'success') {
							var data = json.data;
							if (data.length > 0) {
								var awardHtml;
								for (var i = 0; i < data.length; i++) {
									awardHtml = _awardTemplate.html();
									awardHtml = awardHtml.replaceAll("@user-avatar@", data[i].userAvatar50x50);
									awardHtml = awardHtml.replaceAll("@user-nick@", data[i].userNick);
									awardHtml = awardHtml.replaceAll("@item-id@", data[i].itemId);
									awardHtml = awardHtml.replaceAll("@item-title@", data[i].itemTitle);
									awardHtml = awardHtml.replaceAll("@item-price@", data[i].itemPriceFmt);
									_awardList.append(awardHtml);
								}
								self._initUserAvatar();
								self._rollingUlUp({container: _awardList, rollDelay: 2000, dispearDelay: 800, rollLimitSize: 3});
							} else {
								_awardList.append('<li class="no-award">������ѽ�Ʒ����ʧ�ܣ����Ժ�����...</li>');
							}
						} else {
							_awardList.append('<li class="no-award">������ѽ�Ʒ����ʧ�ܣ����Ժ�����...</li>');
						}
					},
					error: function() {
						_awardList.find("li").remove();
						_awardList.append('<li class="no-award">������ѽ�Ʒ����ʧ�ܣ����Ժ�����...</li>');
					}
				});	
			});
		} ,
		
		/**
		 * ���Ϲ���ul����.
		 * container��ul����;rollLimitSize��������Ϣ��Ҫ��li����С������
		 * rollDelay���������,Ĭ��3000; dispearDelay��Ԫ�ص�����ʱ��Ĭ��800;
		 **/
		_rollingUlUp: function(config) {
		    if (rollupTimer != null) {
			    clearInterval(rollupTimer);
			}
		    var _ulContainer = config.container;
			if (_ulContainer.find("li").size() <= config.rollLimitSize) {
			    return;
			}
			var rollDelay = config.rollDelay;
			if (rollDelay == undefined) {
			    rollDelay = 2000;
			}
			var dispearDelay = config.dispearDelay;
			if (dispearDelay ==  undefined) {
			    dispearDelay = 800;
			}
			rollupTimer = setInterval(function() {
				//3s��0.8s�Ĺ�����ʱ�����ܻ����animate��functionδִ�У�
				//�ֿ�ʼ��һ�ζ�ʱ�����������ظ������.
				_ulContainer.find("li.remove-a").remove();
				var firstLi = _ulContainer.find("li:first");
				_ulContainer.append(firstLi.clone());
				//��ʾ�Ƴ�.
				firstLi.addClass("remove-a");
				firstLi.animate({height: '0px'}, dispearDelay, function(){
					firstLi.remove();
				});
			}, rollDelay);
		},
		
		/**
		 * placeholder
		 */
		_initPlaceholder:function(){
			$("[placeholder]").each(function(){
				var text = $(this).attr("placeholder");
				if(text != ''){
					$(this).val(text) ;
					$(this).addClass("placeholder") ;
				}
				$(this).on('focus',function(){
					if($(this).val() == text){
						$(this).removeClass("placeholder") ;
						$(this).val("");
					}
				});

				$(this).on('blur',function(){
					if($(this).val() === ""){
						$(this).val(text);
						$(this).addClass("placeholder") ;
					}
				});
			});
		},
		
		_initItemShare: function() {
		    $('body').undelegate('#tiles li .share-btn','click');
			$('body').delegate('#tiles li .share-btn','click', function(){
				var _this = $(this);
				var _itemEle = self._ancestor(_this, 'li');
				var itemTitle = _itemEle.find(".title").html();
				var itemId = _itemEle.find(".title").attr("data-item-id");
				var promPrice = _itemEle.find(".prom-price").html();
				var itemPic = _itemEle.find(".picture").attr("src");
				var sellCount = _itemEle.find(".sell-count").html();
				var msg = "�ף��������ˣ�ֻҪ" + promPrice;
				if (_itemEle.find(".postal").length == 1) {
					msg = msg + "�������ʵ�...";
				} else {
					msg = msg + "...";
				}
				var customParma = '{"shareUserId":' + userId + ',"shareContent":"hot","shareObjId":' + itemId + '}';
				$(document).trigger('sendDcStory', {
					summary:"",
					msg: msg,
					source: customParma,
					imgUrl: itemPic,
					onSuccess: function(opt) {
						$.ajax({
							url: envRoot + "/frame/q/remote/rest/share_dcitem_ajax.htm",
							type: "POST",
							data: {id: itemId},
							success: function(result) {
								var json = result.json;
								if (json.code == 'success') {
									//���»���
									self.modifyUserIntegral({integral: json.data});
									if (json.data == 0) {
										
									}
									self.refreshIntegralBtn("share-item", json.data);
								} else {
									alert("����ص�ʧ��");
								}
							},
							error: function() {
								alert("intenal.error");
							}
						});
					},
					onCancel: function(opt) {
					},
					onClose: function(opt) {
					}
				});
			});
		},
		
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
		
		_initItemTabEvent: function() {
			var _cateList = $(".cnt-pgc .cate-list");
		    var _itemTab = $(".cnt-pgc .item-tab");
		    var pgcTable = _itemTab.find(".pgc-tab");
			if (pgcTable.length > 0) {
				pgcTable.click(function() {
					var _this = $(this);
					var _parent = _this.parent();
					$('#waterfallMore').removeClass('dd-hide');
					$('#waterfallUgcMore').addClass('dd-hide');
					_cateList.removeClass("dd-hide");
					if (_parent.hasClass("active")) {
						return;
					}
					_cateList.find("a.selected").removeClass("selected");
					if (_itemTab.offset().top > 298) {
					    $(document).trigger("setScroll", {scrollTop:394});
					}
					_parent.parent().find('.active').removeClass('active');
					_parent.addClass('active');
					$('.cnt-pgc .feedwall-container').find('li').remove();
					self._initPGCWaterfall({});
				});
				_cateList.find("a.category").click(function() {
				    var _this = $(this);
					var categoryId;
					if (_this.hasClass("selected")) {
					    _this.removeClass("selected");
					} else {
						_this.parent().find("a.selected").removeClass("selected");
						_this.addClass("selected");
						var categoryId = _this.attr("data-category-id");
						if (categoryId == undefined || categoryId == '' || isNaN(categoryId)) {
							alert("��Ŀid������ˢ��ҳ��");
							return;
						}
					}
					self._initPGCWaterfall({categoryId: categoryId});
				});
			}
			var ugcTab = _itemTab.find(".ugc-tab");
			if (ugcTab.length > 0) {
				ugcTab.click(function() {
					var _this = $(this);
					var _parent = _this.parent();
					$('#waterfallUgcMore').removeClass('dd-hide');
					$('#waterfallMore').addClass('dd-hide');
					_cateList.addClass("dd-hide");
					if (_parent.hasClass("active")) {
						return;
					}
					if (_itemTab.offset().top > 298) {
					    $(document).trigger("setScroll", {scrollTop: 394});
					}
					_parent.parent().find('.active').removeClass('active');
					_parent.addClass('active');
					$('.cnt-pgc .feedwall-container').find('li').remove();
					self._initUGCWaterfall();
				});
			}
		},
		
		/**
		 * PGC waterfall
		 */
		_initPGCWaterfall:function(config){
			if($('#waterfall').size() > 0 ){
				var url = envRoot + '/frame/q/remote/rest/query_items_ajax.htm' ;				
				var page = config.page == undefined? 1: config.page;
				var size = config.page == undefined? 15: config.page;
				var recommType = config.recommType == undefined? 'NULL': config.recommType;
				var genWay = config.genWay == undefined? 'P': config.genWay;
				var categoryId = config.categoryId == undefined? '': config.categoryId;
				var isLoading = false;
				var PGCContainer = $("#waterfall");
				PGCContainer.find("li").remove();
			    $("#waterfallMore").unbind('click').click(function(){
			    	if(isLoading){
			    		return ;
			    	}
			    	isLoading = true ;
			    	//����.....
					$.ajax({
						url : url ,
						type : "POST" ,
						data : {page: page, size: size, categoryId: categoryId},
						success : function(data){
							var json = data.json ;
							var code = json.code ;
							var data = json.data ;
							var detail = json.detail ;
							var itemSize = data.length ;
							if(code == 'success') {
								var html = '' ;
								if(data.length > 0){
									for(var i=0 ;i<data.length;i++){
										var item = data[i] ;
										try {
											var template = $("#pgcItemTemplate").html() ;
											template = template.replaceAll('@item_id@',item.id ) ;
											template = template.replaceAll('@item_title@',item.itemTitle ) ;
											template = template.replaceAll('@src@',"src") ;
											template = template.replaceAll('@item_pic_url@',item.picModel220x000.url) ;
											template = template.replaceAll('@item_pic_height@',item.picModel220x000.height) ;
											template = template.replaceAll('@item_price@',item.displayPriceFormat ) ;
											template = template.replaceAll('@comment_count@',item.comments ) ;
											template = template.replaceAll('@integralCount@',item.integralCount ) ;
											template = template.replaceAll('@gtrack@','gtrack') ;
											if (item.integralCount > 0){
												template = template.replaceAll('@integral-display@','') ;
											} else {
												template = template.replaceAll('@integral-display@','dd-hide') ;
											}
											if (item.postalEnable == 'Y') {
											    template = template.replaceAll('@postal_enable@','<span class="postal-enable lbl">����</span>');
											} else {
											    template = template.replaceAll('@postal_enable@','');
											}
											if (item.sells > 100) {
											    template = template.replaceAll('@sells@','<span class="sell-count lbl">��' + item.sells + '</span>');
											} else {
											    template = template.replaceAll('@sells@','');
											}
											//if (item.itemDesc == '' || item.itemDesc == undefined) {
											    template = template.replaceAll('@recom_reason@', '');
											//} else {
											  //   template = template.replaceAll('@recom_reason@', '<span class="lbl1">�Ƽ����ɣ�</span>' + item.itemDesc);
											//}
											var price = item.itemPrice;
																						
											//�����۸�
											var itemPromPrice = item.itemPromPrice; 
											if($(itemPromPrice).length > 0){ 
												var pp = itemPromPrice.toString().split('.') ;
												if($(pp).length > 0){
													template = template.replaceAll('@promotion-price-integer@',pp[0]) ;
													if($(pp).length > 1){
														template = template.replaceAll('@promotion-price-fraction@',pp[1]) ;
													} else {
														template = template.replaceAll('@promotion-price-fraction@','00') ;
													}
													template = template.replaceAll('@promtion-price-display@','') ;
													template = template.replaceAll('@price-through@','dd-through') ;
												}
												price = itemPromPrice ;
											} 
											template = template.replaceAll('@promtion-price-display@','dd-hide') ;
											template = template.replaceAll('@price-through@','') ;
											template = template.replaceAll('@ugc-content-display@' , 'dd-hide');
											
											if(price > 50){
												template = template.replaceAll('@pub-wish-display@','dd-hide');
												template = template.replaceAll('@integral-less@','');
												template = template.replaceAll('@integral-less-lbl@','���ֲ�����������ȡŶ~');
											} else {
											    template = template.replaceAll('@pub-wish-display@','');
												template = template.replaceAll('@integral-less@','dd-hide');
												template = template.replaceAll('@integral-less-lbl@','');
											}
											
											html += template  ;
										}catch(e){}
										
										
									}
								}
								
								PGCContainer.find('ul').append(html);
								PGCContainer.find('ul').find(".earn-integral-btn").unbind("click").click(function() {
									self._showIntegralGuide();
								});
						        self._initGtrack();
								self._resizeWaterfall({autoResize: false, container: PGCContainer, offset: 10});
						        page ++ ;
						        
							}
					        isLoading = false ;
					        $(document).trigger("resize");
					        if(itemSize < size){ //���һҳ
					        	$("#waterfallMore").addClass("dd-hide");
					        }
						} ,
						error : function(data){
							isLoading = false ;
						}
					});
			    });
			    $("#waterfallMore").trigger('click');
			}
		} ,
		
		_initUGCWaterfall:function(){
			if($('#waterfall').size() == 0 ){
			    return;
			}
			var UGCContainer = $('#waterfall');
			var ugcItemTemplate = $("#pgcItemTemplate");
			if(UGCContainer.size() == 0 || ugcItemTemplate.size() == 0) {
			    return;
			}
			UGCContainer.find("li").remove();
			$('body').undelegate('.waterfall li','mouseenter');
			$('body').delegate('.waterfall li','mouseenter' , function(){
				$(this).addClass("cur") ;
			}) ;
			$('body').undelegate('.waterfall li','mouseleave');
			$('body').delegate('.waterfall li','mouseleave' , function(){
				$(this).removeClass("cur") ;
			}) ;
			
			var url = envRoot + '/frame/q/remote/rest/query_ugc_items_ajax.htm';
			var page = 1;
			var size = 15;
			var isLoading = false ;
			var timeStamp = null;
			$("#waterfallUgcMore").unbind('click').click(function(){
				if(isLoading){
					return ;
				}
				isLoading = true ;
				$.ajax({
					url : url ,
					type : "POST" ,
					data : {page:page, timeStamp: timeStamp},
					success : function(result){
						var json = result.json;
						timeStamp = result.timeStamp;
						var code = json.code;
						var data = json.data;
						var detail = json.detail;
						if(code == 'success') { 
							var html = '';
							if(data.length > 0){
								for(var i=0 ;i<data.length;i++){
									var item = data[i];
									try {
										var template = ugcItemTemplate.html();
										template = template.replaceAll('@item_id@',item.id );
										template = template.replaceAll('@item_title@',item.itemTitle );
										template = template.replaceAll('@src@',"src");
										template = template.replaceAll('@item_pic_url@',item.picUrl220x220) ;
										template = template.replaceAll('@item_pic_height@',220);
										template = template.replaceAll('@item_price@',item.itemPriceFormat );
										template = template.replaceAll('@comment_count@',item.comments );
										template = template.replaceAll('@integralCount@',item.integralCount );
										template = template.replaceAll('@gtrack@','gtrack');
										template = template.replaceAll('@creator_nick@',item.creatorNick);
										template = template.replaceAll('@creator_nick@',item.creatorNick);
										template = template.replaceAll('@avatar_url@',item.avatarUrl30x30);
										template = template.replaceAll('@avatar_url@',item.avatarUrl30x30);
										if (item.postalEnable == 'Y') {
											template = template.replaceAll('@postal_enable@','<span class="postal-enable lbl">����</span>');
										} else {
											template = template.replaceAll('@postal_enable@','');
										}
										if (item.sells > 100) {
											template = template.replaceAll('@sells@','<span class="sell-count lbl">��' + item.sells + '</span>');
										} else {
											template = template.replaceAll('@sells@','');
										}
										if (item.ownerSign == 1) {
										    template = template.replaceAll('@color_pink@', 'color-pink');
										} else {
										    template = template.replaceAll('@color_pink@', '');
										}
										if (item.integralCount > 0){
											template = template.replaceAll('@integral-display@','');
										} else {
											template = template.replaceAll('@integral-display@','dd-hide');
										}
										template = template.replaceAll('@recom_reason@', '');
										template = template.replaceAll('@pub-wish-display@','dd-hide');
										var price = item.itemPrice;
																					
										//�����۸�
										var itemPromPrice = item.itemPromPrice; 
										if($(itemPromPrice).length > 0){ 
											var pp = itemPromPrice.toString().split('.');
											if($(pp).length > 0){
												template = template.replaceAll('@promotion-price-integer@',pp[0]);
												if($(pp).length > 1){
													template = template.replaceAll('@promotion-price-fraction@',pp[1]);
												} else {
													template = template.replaceAll('@promotion-price-fraction@','00');
												}
												template = template.replaceAll('@promtion-price-display@','');
												template = template.replaceAll('@price-through@','dd-through');
											}
											price = itemPromPrice;
										} 
										template = template.replaceAll('@promtion-price-display@','dd-hide');
										template = template.replaceAll('@price-through@','');
										template = template.replaceAll('@ugc-content-display@' , '');
										html += template  ;
									}catch(e){}
								}
								
							}
							// Get the first then items from the grid, clone them, and add them to the bottom of the grid.
							//var items = $('#tiles li');
							UGCContainer.find('ul').append(html);
							self._initGtrack();
							self._initUserAvatar();
							self._resizeWaterfall({autoResize: false, container: UGCContainer, offset: 10});
							page++;
							if(data.length != 15){
								$("#waterfallUgcMore").addClass("dd-hide");
							}
						}
						isLoading = false ;
						$(document).trigger("resize");
					} ,
					error : function(data){
						isLoading = false ;
					}
				});
			});
			
			if($('.feedwall-container li').size()<1){
				$("#waterfallUgcMore").trigger('click') ;
			}
		},
		
		/**
		 * �ͶƱ
		 */
		_initPromRateEvent:function(){
			
			//��һ��
			$(".rate-btn-click").unbind('hover') ;
			$(".rate-btn-click").hover(function(){
				$(this).removeClass("rate-btn") ;
				$(this).addClass("rate-btn-on") ;
				$(this).find(".msg").html($(this).attr("data-msg")) ;
			},function(){
				$(this).removeClass("rate-btn-on") ;
				$(this).addClass("rate-btn") ;
				$(this).find(".msg").html($(this).attr("data-rate")) ;
			});
						
			var url = envRoot + "/frame/q/remote/rest/add_promotion_rate_ajax.htm"
			$('.rate-btn-click').unbind('click') ;
			$('.rate-btn-click').click(function(){
				var _this = $(this) ;
				var promotionItemId = _this.attr("data-promotion-item-id") ;
				var promotionId = _this.attr("data-promotion-id") ;
				if(_this.attr("data-has-rate") == 'true'){ ////�ظ�ϲ��
					self.showTipDialog({parent:_this , msg:'���Ѿ�Ͷ��Ʊ��~'}) ;
					return ;
				}
				$.ajax({
		    		url : url ,
					type : "POST" ,
					data : {promItemId:promotionItemId,promotionId:promotionId},
					success : function(data){
						var json = data.json ;
						var code = json.code ;
						var data = json.data ;
						var detail = json.detail ;
						if(code == 'success') { 
							_this.attr('data-has-rate',"true") ;
							self.showTipDialog({parent:_this , msg:'ͶƱ�ɹ�'}) ; 
							var rateCount = isNaN(_this.find(".rate-count").html()) ? 0 : parseInt(_this.find(".rate-count").html()) ;
							var curCount = rateCount ;
							_this.find(".rate-count").html(curCount+1) ; 
							_this.attr("data-rate" , curCount+1 ) ;
							self.modifyUserIntegral({integral:data}) ;
						} else {
							if(code == 'ill_args'){
								if(detail == 'dcome.addRate.itemId.duplicate') { //�ظ�ͶƱ
									_this.attr('data-has-rate',"true") ;
									self.showTipDialog({parent:_this , msg:'���Ѿ�Ͷ��Ʊ��~'}) ;
								} else if(detail == 'dcome.addRate.itemId.required') {
									self.showTipDialog({parent:_this , msg:'ͶƱʧ�ܣ���ˢ��ҳ��'}) ;
								} else if(detail == 'dcome.addRate.userId.required') {
									self.showTipDialog({parent:_this , msg:'ͶƱʧ�ܣ���ˢ��ҳ��'}) ;
								}
							} else {
								self.showTipDialog({parent:_this , msg:'ͶƱʧ�ܣ���ˢ��ҳ��'}) ;
							}
						}
					} ,
					error : function(data){
						self.showTipDialog({parent:_this , msg:'ͶƱʧ�ܣ���ˢ��ҳ��'}) ;
					}
				});
			});
		} ,
		
		/**
		 * �������
		 * config :
		 * config.integralCount �޸Ļ�����
		 * config.frozenIntegralCount �޸Ķ���Ļ�����
		 */
		modifyUserIntegral:function(config){
			var integralCount = isNaN($(".integral-count").html()) ? 0 : parseInt($(".integral-count").html()) ;
			var frozenIntegralCount = isNaN($(".frozen-integral-count").html()) ? 0 : parseInt($(".frozen-integral-count").html()) ;
			var integral = config.integral;
			var frozenIntegral = config.frozenIntegral ;
			
			if(integral){
				var newIntegralCount = integralCount + integral ;
				self.annimatTextJump({selector:$(".integral-count") , newText:newIntegralCount }) ;
			}
			
			if(frozenIntegral){
			    var newIntegralCount, newFrozenIntegralCount;
				//�ۼ�.
				if (config.frozenStatus == 'add') {
				    newIntegralCount = integralCount - frozenIntegral;
					newFrozenIntegralCount = frozenIntegralCount + frozenIntegral;
				} else {
				    //����
				    newIntegralCount = integralCount + frozenIntegralCount - frozenIntegral;
					newFrozenIntegralCount = frozenIntegral;
				}
				
				self.annimatTextJump({selector:$(".frozen-integral-count") , newText:newFrozenIntegralCount }) ;
				self.annimatTextJump({selector:$(".integral-count") , newText:newIntegralCount }) ;
				/**
				if(newFrozenIntegralCount > 0){
					$("#header .integral").find(".active").removeClass("none-frozen") ;
					$("#header .integral").find(".frozen").removeClass("dd-hide") ;
				} else {
					$("#header .integral").find(".active").addClass("none-frozen") ;
					$("#header .integral").find(".frozen").addClass("dd-hide") ;
				}
				**/
			}
		} ,
		
		modifyUserWishStar: function(count) {
		    $(".my-rate-count").each(function(){
				var myRateCount = $(this).html() ;
				myRateCount = isNaN(myRateCount) ? 0 : parseInt(myRateCount) ;
				myRateCount += count ;
				self.annimatTextJump({selector:$(this),newText:myRateCount}) ;
			}) ;
		},
				
		/**
		 * ��Ը���ǳɹ���ʾ
		 */
		showWishTipDialog:function(config){
			if(wishTipDialog_timer != null){ //�ϴε�ʱ�ӻ���
				 clearTimeout(wishTipDialog_timer) ;
				 wishTipDialog.addClass("dd-hide") ;
			}
			if($(config).size() > 0){
				var msg = config.msg ;
				var parent = config.parent ;
				var integral = config.integral ;
				var top = parent.offset().top - wishTipDialog.height() - 10  ;
				var left = parent.offset().left - 90  ;
				var bottom = top + wishTipDialog.height() ;
				var right = left + wishTipDialog.width() ;
				wishTipDialog.css("top" , top) ;
				wishTipDialog.css("left" , left) ;
				wishTipDialog.find(".content").html(msg) ;
				if(integral){
					wishTipDialog.find('.desc').removeClass('dd-hide') ;
					wishTipDialog.find('.integral').html(integral) ;
				} else {
					wishTipDialog.find('.desc').addClass('dd-hide') ;
				}
			}
			
			wishTipDialog.removeClass("dd-hide") ;
			
			wishTipDialog_timer = setTimeout(function(){
				
				wishTipDialog.delay(300).animate({opacity:0},500,function(){
					$(this).addClass("dd-hide") ;
					$(this).css("opacity",100) ;
			  	});
				
			} , 2000) ;
		} ,
		/**
		 * ��ʾ��Ϣ
		 */
		showTipDialog:function(config){
			if(tipDialog_timer != null){ //�ϴε�ʱ�ӻ���
				 clearTimeout(tipDialog_timer) ;
				 tipDialog.addClass("dd-hide") ;
			}
			if($(config).size() > 0){
				var msg = config.msg ;
				var parent = config.parent ;
				
				var top = parent.offset().top - tipDialog.height() - 10;
				if (top <= 0) {
				    top = parent.offset().top + tipDialog.height() + 10;
				}
				var left = parent.offset().left - 50  ;
				if(left > (bodyClientRight - tipDialog.width() - 20)){
					left = (bodyClientRight - tipDialog.width() - 20) ;
				}
				var bottom = top + tipDialog.height() ;
				var right = left + tipDialog.width() ;
				tipDialog.css("top" , top) ;
				tipDialog.css("left" , left) ;
				tipDialog.find(".content").html(msg) ;
				
			}
			
			tipDialog.removeClass("dd-hide") ;
			
			tipDialog_timer = setTimeout(function(){
				
				tipDialog.delay(300).animate({opacity:0},500,function(){
					$(this).addClass("dd-hide") ;
					$(this).css("opacity",100) ;
			  	});
				
			} , 2000) ;
		} ,
		
		/**
		 * �����ʽ����hh:mm:ss
		 */
		formatSecondsHHMMSS:function(seconds2format){
			var format00 = function(num){
				if(isNaN(num)){
					return '00' ;
				}
				num = parseInt(num) ;
				if(num < 10){
					return '0' + num ;
				}
				return '' + num ;
			} ;
			
			var hours = format00(parseInt(seconds2format / 3600)) ;
			var minutes =  format00(parseInt((seconds2format % 3600) / 60)) ;
			var seconds = format00(seconds2format % 60) ;
			var str = hours + ":" + minutes + ":" + seconds ;
			return str ;
		} ,
		
		/**
		 * ��Ч��ʾ���İ�
		 */
		annimatTextJump:function(config){
			if($(config).size() < 1){
				return ;
			}
			var newText = config.newText ;
			var selector = config.selector ;
			var callback = config.callback ;
			if(newText == undefined){
				return ;
			}
			if(selector == undefined) {
				return ;
			}
			selector.animate({opacity:0},300 , function(){
				
			}) ;
			
			selector.html(newText) ;
			
			if($(callback).size() > 0){
				callback() ;
			}
			
			selector.delay(300).animate({opacity:100},300 , function(){
				selector.css({'opacity':100}) ;
			});
			
		} ,
		
		/**
		 * ͷ����ز���ʱ��ֹstack overflow
		 */
		_initUserAvatar:function(){
			$("[data-avatar-src]").each(function(){
				var _this = $(this) ;
				if(_this.attr('avatar-init') != 'y'){
					_this.bind('error',function(){
						var size = _this.attr("data-avatar-size") ;
						if(size != ''){
							_this.attr("src",errorAvatarUrl + "_" + size + '.jpg') ;
						}else{
							_this.attr("src",errorAvatarUrl) ;
						}
						_this.unbind("error") ;
					}) ;
					
					var src =  _this.attr('data-avatar-src') ;
					if(src != '' && src.indexOf('@') == -1){
						_this.attr('src',src) ;
						_this.attr('avatar-init','y') ;
					}
				}
			}) ;
		} ,
		
		_initPubWishClick: function() {
		    var _pubWish = $(".pub-wish-click");
		    if (_pubWish.length == 0) {
			    return;
			}
			
		    $('body').delegate('.pub-wish-click' , 'click' , function() {
				var _this = $(this);
				var itemEle = self._ancestor(_this, "li");
				var itemId = _this.attr("data-item-id");
				var itemName = itemEle.find(".title").text();
				var itemPrice = itemEle.find(".price").html();
				var itemImg = itemEle.find(".item-pic").attr("src");
				showWishDialog({"itemId": itemId, "itemName": itemName,
				    "itemPrice": itemPrice, "itemImg": itemImg, "parent": itemEle});
			}) ;
			
			function showWishDialog(config) {
				//������Ϣ
				wishDialog.find(".wish-info").html("");
				wishDialog.find(".loadding32x32").hide();
				//�ж��û��Ƿ����ظ���Ը
				$.ajax({
					url: envRoot + "/frame/q/remote/rest/is_takein_prom_ajax.htm",
					type: "POST",
					success: function(result) {
					    var json = result.json;
						if (json.code == 'success') {
						    wishDialog.find(".wish-info").html("���Ѿ�������Ը�����Ͽ�ȥΪ���Ը���ռ�Ը���ǰ�");
							wishDialog.find(".wish-info").show();
							wishDialog.find(".item-info").hide();
							wishDialog.find(".collect-star").show();
						}
					}, 
					error: function() {
					    
					}
				});
				var itemNameA = wishDialog.find(".item-name");
				wishDialog.find(".item-img").attr("src", config.itemImg);
				itemNameA.html(config.itemName);
				itemNameA.attr("title", config.itemName);
				//itemNameA.attr("taoke-href", envRoot +'/frame/q/remote/rest/redirect_item.htm?itemId=' + config.itemId);
				wishDialog.find(".item-price").html(config.itemPrice);
				wishDialog.find(".wish-confirm").attr("data-item-id", config.itemId);
			    self.centerPoint(wishDialog);
				wishDialog.show();
				wishDialog.find("a").attr("data-item-id", config.itemId);
				//��ʼ���¼�.
				self._initWishLayerEvent();
				shadowBg.show();
			};
		},
		
		_initWishLayerEvent: function() {
		    var _wishConfirm = wishDialog.find("#wishConfirm");
			if (_wishConfirm.length == 0) {
			    return;
			}
			_wishConfirm.html("��   ȡ");
			_wishConfirm.show();
			var hideWishConfirm = function() {
				shadowBg.hide();
				wishDialog.hide();
				wishDialog.find(".wish-info").html("");
				wishDialog.find(".loadding32x32").hide();
			};
			var wishHide = wishDialog.find(".wish-hide");
			wishHide.unbind("click").click(hideWishConfirm);
			var shutdownBtn = wishDialog.find(".shutdown-btn");
			shutdownBtn.hide();
			shutdownBtn.unbind("click").click(hideWishConfirm);
			var showWishInfo = function(message) {
				wishDialog.find(".loadding32x32").hide();
				wishDialog.find(".wish-info").html(message);
				wishDialog.find(".wish-info").show();
			}
			_wishConfirm.unbind("click").click(function() {
				if (_wishConfirm.attr("data-submit") == 1) {
				    return;
				}
				_wishConfirm.attr("data-submit", 1);
				var itemId = $(this).attr("data-item-id");
				if (itemId == undefined || itemId == '') {
				    showWishInfo("�޷���ȡ�������ȡ��Ʒ����Ʒ��Ϣ�����Ժ�����...");
					return ;
				}
				//��ʾ�ύ��Ը����Ϣ
				wishDialog.find(".loadding32x32").show();
				_wishConfirm.html("��ȡ��...");
				$.ajax({
					url: envRoot + '/frame/q/remote/rest/dcitem_takein_prom_ajax.htm',
					type: "POST",
					data: {'itemId': itemId},
					success: function(result) {
						_wishConfirm.attr("data-submit", 0);
						var json = result.json;
						if (json.code != 'success') {
							var detail = json.detail;
							_wishConfirm.hide();
							if (detail == 'promotion.ended') {
							    //�����,�����ɷ�����,���ݿ�ʱ�Ӳ�һ��һ��
								showWishInfo("���ڻ�ѽ�����Ŷ�������ڴ�����");
								shutdownBtn.show();
							} else if (detail == 'promotion.not.start') {
							    //�δ��ʼ,�����ɷ�����,���ݿ�ʱ�Ӳ�һ������
								showWishInfo("���δ��ʼ�������ڴ�");
								shutdownBtn.show();
							} else if (detail == 'takein.repeat') {
								var id = json.data;//dc_promotion_item.id
								wishDialog.find(".item-info").hide();
							    wishDialog.find(".collect-star").show();
								showWishInfo("���Ѿ�����Ը���Ͽ�ȥΪ����Ը���ռ�Ը���ǰ�");
							} else if (detail == 'item.not.exist') {
								showWishInfo("�޷���ȡ�������ȡ��Ʒ����Ʒ��Ϣ����ѡ������Ʒ...");
								shutdownBtn.show();
							} else if (detail == 'itemprice.more') {
								showWishInfo("��Ŀǰ����ֻ�������ȡ��100���ڵ���Ʒ");
								shutdownBtn.show();
							} else if (detail == 'internal.error') {
								showWishInfo("�������ڲ��������Ժ�����...");
								shutdownBtn.show();
							} else {
								showWishInfo("���δ��ʼ�������ڴ�");
								shutdownBtn.show();
							}
						} else {
						    var itemName = wishDialog.find(".item-name").html();
							var imgUrl = wishDialog.find(".item-img").attr("src");
							var customParma = '{"shareUserId":' + userId + ',"shareContent":"prom","shareObjId":' + json.data + '}';
							wishDialog.hide();
							$(document).trigger('sendDcStory', {
							    summary:"ǩ�������Ҹ����������������Ʒ��",
								msg:"������������������������������������ö����������£����������~~",
								source: customParma,
								imgUrl: imgUrl,
								onSuccess: function(opt) {
								    $.ajax({
										url: envRoot + "/frame/q/remote/rest/share_promotion_ajax.htm",
										type: "POST",
										data: {id: json.data},
										success: function(result) {
										
										},
										error: function() {
										
										}
					                });
									window.location.href = envRoot + "/frame/q/promotion.htm";
								},
								onCancel: function(opt) {
								
								},
								onClose: function(opt) {
								    window.location.href = envRoot + "/frame/q/promotion.htm";
								}
							});
						}
					},
					error: function(result) {
					    _wishConfirm.attr("data-submit", 0);
					    showWishInfo("�޷���ȡ�������ȡ��Ʒ����Ʒ��Ϣ�����Ժ�����...");
					}
				});
			});
		},
		
		//��Ը����¼�.
		_initPubWishEvent: function() {
			if (window.location.href.indexOf("#scroll") != -1) {
			    var firstItem = $('#waterfall li:first');
				if (firstItem.length > 0) {
				    fusion2.canvas.setScroll({top: firstItem.offset().top - 50});
				} else {
				    fusion2.canvas.setScroll({top: "285"});
				}
			}
			if ($('.cnt-top .unwish-btn').size() == 0) {
			    return;
			}
			$('.cnt-top .unwish-btn').click(function() {
			    var location = window.location.href;
				if (location.indexOf("pgcs.htm") == -1) {
				    window.location.href = envRoot + "/frame/q/pgcs.htm#scroll";
					return;
				}
				$(".item-tab .pgc-tab").trigger("click");
				var firstItem = $('#waterfall li:first');
				fusion2.canvas.setScroll({top: "285"});
			});
			self._initPubWishClick();
		},
		
		_initIwish: function() {
		    var _iwish = $("#iwish");
			//��ʼ����������
			var promItemId = $("#myPromotionItemId").val();
			if (promItemId == undefined || promItemId == '') {
			    return ;
			}
			$.ajax({
				url: envRoot + "/frame/q/remote/rest/prom_item_rank_ajax.htm",
				type: "POST",
				data: {promItemId: promItemId},
				success: function(result) {
					var json = result.json;
					if (json.code == 'success') {
					    var _info = _iwish.find(".top-cover .info");
						_info.append('/��<span class="rank color-orange">' + json.data + '</span>��');
					}
				},
				errro: function(result) {
				
				}
			});
			//��ʼ���ҽ�����
			$.ajax({
				url: envRoot + "/frame/q/remote/rest/game_data_ajax.htm",
				type: "post",
				data: {game: 'egg'},
				success: function(result) {
					var json = result.json;
					if (json.code == 'success') {
						var data = json.data;
						if (!data.playCountOutOfLimit) {
							var _cover = $("#iwish .bottom-cover");
							$('.egg-play-count').html(data.canPlayCount) ;
							$('.egg-btn-click').attr('data-play-count' , data.canPlayCount) ;
//							if(data.canPlayCount > 0) {
//							    _cover.append('<a class="egg-btn dd-r" data-play-count="' + data.canPlayCount + '" href="javascript:;" title="�������' + data.canPlayCount +'�ν�Ŷ"></a>');
//							} else {
//							    _cover.append('<a class="egg-btn dd-r" data-play-count="' + data.canPlayCount + '" href="javascript:;" title="�㻹û���ۻ��㹻����Ʒ��������������ҽ�Ŷ"></a>');
//							}
						}
					} else {
						var detail = json.detail;
					}
				},
				error: function() {
					var tempObj = _eggGame.find("li.egg-info");
					tempObj.find('.text-info').html('��ʱ�����ҽ�Ŷ~~~');
				}
			});
			
			$('#caidan-dialog .close').click(function(){
				$('#caidan-dialog').hide();
			}) ;
			
			$(".show-caidan-dlg-click").click(function(){
				$('#caidan-dialog').show();
				self.centerPoint($('#caidan-dialog')) ;
			}) ;
			
			$(document).delegate(".egg-btn-click", "click", function() {
			    var _this = $(this);
				var numObj = _this.attr('data-play-count');
                var num = parseInt(numObj);
				if (num == NaN || num == undefined || num < 1) {
				    self.showTipDialog({parent:_this , msg:'�㻹û���ۻ��㹻����Ʒ��������������ҽ�Ŷ~~~'});
					return ;
				}
				var changeToBrowse = function() {
					_this.attr("src", envRoot + "/frame/q/index.htm");
				}
				$.ajax({
				    url: envRoot + '/frame/q/remote/rest/smash_egg_ajax.htm',
					type: "post",
					data: {},
					success: function(result) {
					    var json = result.json;
						if (json.code == 'success') {
						    var data = json.data;
							if (data.award == 'INTE') {
							    self.modifyUserIntegral({integral:data.amount});
								self.showTipDialog({parent:_this , msg:'��ϲ�㣬�����'
								  + data.amount + '����~~~'});
							} else if (data.award == 'STAR') {
							    self.modifyUserWishStar(data.amount);
								self.showTipDialog({parent:_this , msg:'��ϲ�㣬�����'
								  + data.amount + '��Ը����~~~'});
							} else if (data.award == 'NO') {
							    self.showTipDialog({parent:_this , msg:'���ź���ʲô��û����~~~'});
							}
							if (num > 1) {
							    //_this.attr("title", '�㻹������' + (num - 1) + '�ν�Ŷ');
								$('.egg-play-count').html((num-1)) ;
								$('.egg-btn-click').attr('data-play-count',(num-1)) ;
                           } else {
                        	   $('.egg-play-count').html(0) ;
                        	   $('.egg-btn-click').attr('data-play-count',0) ;
						        changeToBrowse();
							}
						} else {
						    if (json.detail == "play.limited") {
							    self.showTipDialog({parent:_this , msg:'���Ѿ�����10�ν�������Ϣһ�°�~~~'});
							} else {
								self.showTipDialog({parent:_this , msg:'�����ҽ�Ŷ~~~'});
							}
							changeToBrowse();
						}
					},
					error: function() {
					    self.showTipDialog({parent:_this , msg:'�����Ҳʵ�Ŷ~~~'});
					}
				});
			});
			self._initIwishEvent();
		},
		
		
		/**
		 * ��ȡԸ��ֵ
		 */
		_initIwishEvent:function(){
		    var _iwish = $('#iwish');
			var starMoveTimer = null;
		    _iwish.find('.wish-content').hover(function(){
			    var _this = $(this);
				_this.find(".top-cover .browse-wrap").removeClass("dd-hide");
				_this.find(".top-cover .info-wrap").addClass("dd-hide");
			}, function() {
			    var _this = $(this);
				_this.find(".top-cover .info-wrap").removeClass("dd-hide");
				_this.find(".top-cover .browse-wrap").addClass("dd-hide");
			});
			
			var getNextDrawText = function(time){
				var date = new Date() ;
				date.setTime(time) ;
				var nextHour = date.getHours() + 1;
				if(nextHour >= 10 && nextHour < 22){
					return '�´���ȡ&nbsp;' + nextHour + ':00&nbsp;' ;
				}
				return '�´���ȡ&nbsp;����' + 10 + ':00&nbsp;' ;
			}
			
			//tip��ʾ��Ը����
			$(".drawRate-btn-click").hover(function(){
				$("#drawRateTip").animate({opacity:100},500,function(){
					$(this).removeClass("dd-hide") ;
				}) ;
			} ,function(){
				$("#drawRateTip").delay(300).animate({opacity:0},500,function(){
					
				}) ;
			}) ;
			
			$(".show-lingqu-dlg-click").click(function(){
				$("#lingqu-dialog").show() ;
				self.centerPoint($("#lingqu-dialog")) ;
			}) ;
			
			$("#lingqu-dialog .close").click(function(){
				$("#lingqu-dialog").hide() ;
			}) ;
			
			$('.drawRate-btn-click').each(function(){
				var _this = $(this);
				if(_this.hasClass('disabled')){
					_this.find('.enable').addClass('dd-hide') ;
					_this.find('.disable').removeClass('dd-hide');
					var nextDrawText = getNextDrawText($('#sysTimeMills').val()) ;
					$('.drawRate-countdown').html(nextDrawText) ;
				}
				
				_this.unbind('click') ;
				
				_this.click(function(){
					if(_this.hasClass('disabled')){
						self.showTipDialog({parent:_this , msg:'�����¸�����ǰ����ȡ��'}) ; 
						return ;
					}
					var promotionId = $("#promotionId").val() ; //�ID
					var myPromItemId = $("#myPromotionItemId").val() ;
					if(myPromItemId == '' || myPromItemId == undefined){
						self.showTipDialog({parent:_this , msg:'����û�вμӻ��'}) ; 
						return ;
					}
					
					$.ajax({
			    		url : envRoot + "/frame/q/remote/rest/draw_promotion_rate_ajax.htm",
						type : "POST" ,
						data : {promotionId:promotionId},
						success : function(data){
							var json = data.json ;
							var code = json.code ;
							var data = json.data ;
							var detail = json.detail ;
							if(code == 'success') { 
								var drawRateCount = data.growRateCount ; 
								var sysTimeMillis = data.sysTimeMillis ;
								var rateCount = data.rateCount ;
								self.showTipDialog({parent:_this, msg:'��ȡ�ɹ�'});
								var _bottomCover = $('#iwish .bottom-cover');
								_bottomCover.find(".draw-wrap").remove();
								_bottomCover.find(".info-wrap").show();
								_this.addClass('disabled') ;
								self.annimatTextJump({selector:$("#iwish .my-rate-count") , newText:rateCount});
								//_this.find('.enable').addClass('dd-hide') ;
								//_this.find('.disable').removeClass('dd-hide') ;
								//�´���ȡ
								var nextDrawText = getNextDrawText(sysTimeMillis) ;
								$('.drawRate-countdown').html(nextDrawText); 						
							} else {
								if(code == 'ill_args'){
									if(detail == 'dcome.user.integral.notenough') { //���ֲ���
										self.showTipDialog({parent:_this , msg:'���Ļ��ֲ��㣡'}) ;
									} else if(detail == 'dcome.promotion.required') {
										self.showTipDialog({parent:_this , msg:'���û�п�ʼ��'}) ;
									} else if(detail == 'dcome.promotion.user.required') {
										self.showTipDialog({parent:_this , msg:'��ȡԸ��ֵʧ�ܣ���ˢ��ҳ��'}) ;
									} else if(detail == 'dcome.draw.rate.low'){
										self.showTipDialog({parent:_this , msg:'������û�п�����ȡ��Ը����Ŷ'}) ;
										_this.addClass('disabled') ;
									} else {
										self.showTipDialog({parent:_this , msg:'��ȡԸ��ֵʧ�ܣ���ˢ��ҳ��'}) ;
									}
								} else {
									self.showTipDialog({parent:_this , msg:'��ȡԸ��ֵʧ�ܣ���ˢ��ҳ��'}) ;
								}
							}
						} ,
						error : function(data){
							self.showTipDialog({parent:_this , msg:'��ȡԸ��ֵʧ�ܣ���ˢ��ҳ��'}) ;
						}
					});
				});
			});
			
		},
		
		/**
		 * ��ʾloadingЧ��
		 */
		loadingGif:function(config){
			if($(config).length <= 0){
				return ;
			}
			var _this = $("#loadingGif") ;
			var left = config.left ;
			var top = config.top ;
			var msg = config.msg ;
			var size = config.size ;
			var closeBottom = config.closeBottom ;
			if($(msg).size() > 0){
				_this.find('.msg').html(msg) ;
			} else {
				_this.find('.msg').html('') ;
			}
			var loadClazz = 'loadding18x18' ;
			if($(size).size() > 0 && size == 32){
				clazz = 'loadding32x32' ;
			}
			_this.find('.loading').removeClass('loadding18x18') ;
			_this.find('.loading').removeClass('loadding32x32') ;
			_this.find('.loading').addClass(loadClazz) ;
			if($(left).size() > 0){
				_this.css({left : left + 'px'}) ;
			}
			if($(top).size() > 0){
				_this.css({top:top+'px'}) ;
			}
			if($(closeBottom).size() > 0){
				if(closeBottom == true){
					var domWidth =  $(document).width() ;
					var domHeight = $(window).scrollTop()+$(window).height() - 30;
					var top = domHeight - 30;
					var left = domWidth / 2  ; 
					_this.css({top:top+'px',left:left+'px'}) ;
				}
			}
			
			_this.removeClass('dd-hide') ;
			
		} ,
		
		unloadingGif:function(config){
			var _this = $("#loadingGif") ;
			_this.addClass('dd-hide') ;
		},
		
		newGuideTo:function(config){
			var _this = $("#onlyNewGuide") ;
			var toElement = config.element ;
			var guide = config.guide ;
			
			if(guide == 'prom_iwish') {
				toElement = $('#waterfall li').eq(1).find('.pub-wish-click');
				
				_this.find('.guide-end').attr('data-click' , 'close') ;
			} else if(guide == 'prom_draw_rate'){
				toElement = $('#receiveStar') ;
				_this.find('.guide-end').attr('data-click' , 'prom_steal_rate') ;
			} else if(guide = 'prom_steal_rate'){
				if($('.promotion-waterfall li').size() > 2){
					toElement = $('.promotion-waterfall li').eq(2).find('.stealRate-btn-click') ;
				} else {
					toElement = $('.promotion-waterfall li').eq(1).find('.stealRate-btn-click') ;
				}
				_this.find('.guide-end').attr('data-click' , 'close') ;
			} else {
				
				return ;
			}
			
			if($(toElement).size() == 0){
				$("#onlyNewGuide").addClass('dd-hide') ;
				return ;
			}
			
			_this.find('.guide-part').addClass('dd-hide') ;
			
			var left = toElement.offset().left  ;
			var top = toElement.offset().top ;
			
			var bubble = _this.find("." + guide) ;
			
			bubble.css({left:left - bubble.width() + 50,top:top - bubble.height()}) ;
			bubble.removeClass('dd-hide') ;
			_this.find('.' + guide + '_highlight').css({left:left ,top:top});
			_this.find('.' + guide + '_highlight').removeClass('dd-hide') ;
			
			var buttonLeft = toElement.offset().left + 100 ;
			if(buttonLeft + _this.find('.guide-end').width() > bodyClientRight - 10){
				buttonLeft = bodyClientRight - _this.find('.guide-end').width() - 10 ;
			}
			
			_this.find('.guide-end').css({left:buttonLeft,top:toElement.offset().top + 50}) ;
			_this.find('.guide-end').attr('data-guide-name' , guide) ;
			_this.find('.guide-end').removeClass('dd-hide') ;
			var _body = (window.opera) ? (document.compatMode == "CSS1Compat" ? $('html') : $('body')):$('html,body');
			
			var toScrollTop = toElement.offset().top / 2 ;
			
			_body.animate({scrollTop: toScrollTop}, 1000, function() {
				
				var shadowHeight =  $(window).height() + $(window).scrollTop();
				
				_this.find('.shadow').css({height:$(document).height()}) ; //�ɲ�
				//$('#shadowBg').show() ;
				_this.removeClass('dd-hide') ;
			
			});
			
			$(document).trigger('setScroll',{scrollTop:toScrollTop}) ;

		} ,
		
		/**
		 * �����������������֪���ˡ���
		 */
		_initNewGuide:function(){
			$("#onlyNewGuide .guide-end").unbind('click') ;			
			 $("#onlyNewGuide .guide-end").click(function(){
				 var guideName = $(this).attr('data-guide-name') ;
				 if(guideName == ''){
					 return ;
				 }
				 var click = $(this).attr('data-click') ;
				 if(click == 'close'){
					 $("#onlyNewGuide").addClass('dd-hide') ;
				 } else if(click != ''){
					 self.newGuideTo({guide:click}) ;
				 }
				 var url = envRoot + "/frame/q/remote/rest/add_user_new_guide_ajax.htm" ;
				 $.ajax({
					 url : url , 
					 method: 'POST' ,
					 data: {guideName: guideName},
					 success: function(data) {
					    
					 },
					 error: function(result) {
					
					 }
				 }) ;
			 });
		} ,
		
		_showIntegralGuide: function() {
		    integralGuide.hide();
			self.centerPoint(integralGuide);
			integralGuide.css({opacity: 0});
			shadowBg.show();
			integralGuide.show();
			integralGuide.animate({opacity: 1}, 700);
		},
		
		_initUgcRelatedEvent: function() {
		    var _ugcLayer = $("#ugcLayer");
			var _exSearchLayer = $("#exSearchLayer");
			//var _ugcContent = _ugcLayer.find(".ugc-content");
			//var _ugcBuy = _ugcContent.find(".ugc-buy");
			var taokeHrefBase = "/frame/q/remote/rest/redirect_item.htm?itemId=";
			var gtrackBase = "/virtual/qq/item?tracelog=ugc-offer-img&id=";
			var showOperInfo = function(_obj, info) {
			    _obj.html(info);
				_obj.show();
			};
			var clearBuyBtnInfo = function(_buyBtn) {
				_buyBtn.removeAttr("taoke-href",'');
				_buyBtn.removeAttr("data-item-id",'');
				_buyBtn.removeAttr("target-url",'');
				_buyBtn.attr("data-ugc-url",'');
			};
			var clearUgcLayer = function() {
			    _ugcLayer.find(".query-btn").removeClass("disable");
			    _ugcLayer.find(".item-url").val('');
				_ugcLayer.find('.info').html('');
				_ugcLayer.find('.info').hide();
				_ugcLayer.find(".loadding18x18").hide();
				clearBuyBtnInfo(_ugcLayer.find(".ugc-buy"));
				_ugcLayer.find(".ugc-content").hide();
				if(_ugcLayer.attr("data-ugc-done")  == 'y') {
				    _ugcLayer.find(".head .title").html("�����ͻ���");
				} else {
				    _ugcLayer.find(".head .title").html("�Ƽ���Ʒ�ͻ���");
				}
			};
		    $(document).delegate(".ugc-btn", "click", function() {
			    clearTimeout(ugcTimer);
		    	$('#integralGuide .hide-btn').trigger('click') ;
			    clearUgcLayer();
				self.centerPoint(_ugcLayer);
				shadowBg.show();
				_ugcLayer.show();
				//Ĭ����ʾ����pgc��Ʒ�������û�
				if (_ugcLayer.attr("data-ugc-done") == 'y' && _ugcLayer.attr("data-example") == 'y') {
					$.ajax({
						url: envRoot + "/frame/q/remote/rest/query_ugc_example_ajax.htm",
						type: "post",
						data: {},
						success: function(result) {
						    if (_ugcLayer.find(".query-btn").hasClass("disable")) {
							    return;
							}
							var json = result.json;
							if (json.success) {
								var data = json.data;
								_ugcLayer.find(".item-url").val(data.srcUrl);
								initUgcItemInfo(data);
								var _buyBtn = $(".ugc-buy");
								_buyBtn.removeAttr("target");
								_buyBtn.html("ȥ�����û���");
								_buyBtn.attr("href", "javascript:;");
								var itemId = data.id == undefined? -1: data.id;
								initBtnBuyDirect(_buyBtn, data.id);
								//Ĭ�����ݣ�������
								showUgcShareToApp(false);
							}
						},
						error: function() {
						
						}
					});
				}
				var _content = _ugcLayer.find(".award-list");
				//��ʼ��ugc��صĻ��ֽ�����ϸ.
				if (_content.find("li:first").hasClass("loadding")) {
				    $.ajax({
					    url: envRoot + "/frame/q/remote/rest/query_ugc_awards_ajax.htm",
						type: "post",
						data: {},
						success: function(result) {
						    _content.find("li").remove();
							var json = result.json;
							if (json.success) {
							    var data = json.data;
								if (data.length > 0) {
								    var liContent, bgColor, itemName, userName, itemUrl;
									for (var i = 0 ; i < data.length; i++) {
									    bgColor = i % 2 == 1? "gray": "";
										itemName = data[i].fromObjName == undefined? '��Ʒ': '<div class="item-name text-ellipsis dd-l">' + data[i].fromObjName + '</div>';
										userName = data[i].toObjName == undefined? '�û�': data[i].toObjName;
										itemUrl = data[i].fromObjId == -1? itemName: '<a class="" taoke-href="' + envRoot + '/frame/q/remote/rest/redirect_item.htm?itemId=' + data[i].fromObjId + 
											     '" data-item-id="' + data[i].fromObjId + '" gtrack="/virtual/qq/item?tracelog=ugc-award-item&id=' + data[i].fromObjId + '">' + itemName + '</a>'
									    if (data[i].source == 'UGC') {
										    liContent = '<li class="text-ellipsis ' + bgColor + '"><div class="dd-l">' + userName + '�Ƽ���</div>' +
						                       itemUrl + '������<span class="red">' + data[i].integralCount + '</span>����</li>';
										} else if (data[i].source == 'AUGC') {
										    liContent = '<li class="text-ellipsis ' + bgColor + '"><div class="dd-l">ϵͳ������' + userName + '�Ƽ���</div>' +
						                        itemUrl + '������<span class="red">' + data[i].integralCount + '</span>����</li>';
										} else if (data[i].source == 'BUGC') {
										    liContent = '<li class="text-ellipsis ' + bgColor + '"><div class="dd-l">�û�������' + userName + '�Ƽ���</div>' +
						                        itemUrl + '������' + userName + '<span class="red">' + data[i].integralCount + '</span>����</li>';
										} else {
										     liContent = '<li class="text-ellipsis ' + bgColor + '">δ֪�Ļ��ֽ���</li>';
										}
										_content.append(liContent);
									}
									self._initGtrack();
									self._rollingUlUp({container: _content, rollDelay: 2000, dispearDelay: 800, rollLimitSize: 6});
								}
							}
						},
						error: function() {
						    _content.find("li").remove();
						}
					});
				}
			});
			_ugcLayer.find('.close-btn').click(function() {
				clearUgcLayer();
				_ugcLayer.hide();
				shadowBg.hide();
			});
			var showUgcShareToApp = function(isShow) {
			    if (isShow) {
					_ugcLayer.find(".share").show();
					_ugcLayer.find(".share-to-app").attr("checked", true);
				} else {
				    _ugcLayer.find(".share").hide();
					_ugcLayer.find(".share-to-app").attr("checked", false);
				}
			};
			var hideQueryInfo = function() {
			    _ugcLayer.find(".info").hide();
				_ugcLayer.find(".loadding18x18").hide();
				showUgcShareToApp(false);
				_ugcLayer.find('.ugc-content').hide();
			}
			var initBtnBuyDirect = function(_buyBtn, itemId) {
			    _buyBtn.removeAttr("data-ugc-url");
				_buyBtn.removeAttr("target-url");
				_buyBtn.attr("taoke-href", envRoot + taokeHrefBase + itemId);
				_buyBtn.attr("data-item-id", itemId);
			};
			var initBtnBuyWithCreate = function(_buyBtn, itemUrl) {
			    _buyBtn.removeAttr("taoke-href");
				_buyBtn.removeAttr("data-item-id");
				_buyBtn.removeAttr("target-url");
				_buyBtn.attr("data-ugc-url", itemUrl);
			};
			var initUgcItemInfo = function(item) {
			    var _ugcContent = _ugcLayer.find('.ugc-content');
			    _ugcContent.find(".item-pic").remove();
				_ugcContent.find(".pic-wrap").append('<img class="item-pic" src="' + item.picUrl80x80 + '"/>');
				_ugcContent.find(".title").html(item.itemTitle);
				_ugcContent.find(".ori-price").html(item.itemPromPriceFormat);
				_ugcContent.find(".integral").html(item.integralCount);
				_ugcContent.find(".discount").html('&yen;' + item.userCommmission);
				_ugcContent.show();
			};
			var initUgcBuyBtn = function(_buyBtn, item) {
			    if (item.integralCount > 0) {
					_buyBtn.removeAttr("target");
					_buyBtn.html("ȥ�����û���");
					_buyBtn.attr("href", "javascript:;");
					var itemId = item.id == undefined? -1: item.id;
					//�û�δugc������Ʒ
					if (item.ownerSign == 3) {
						initBtnBuyWithCreate(_buyBtn, item.srcUrl);
						if (_buyBtn.attr("data-ugc-done") == 'n') {
							_buyBtn.html("�Ƽ�");
						}
					} else {
						initBtnBuyDirect(_buyBtn, item.id);
						if (_buyBtn.attr("data-ugc-done") == 'n') {
							self.showTipDialog({parent: _buyBtn, msg: '���Ѿ��Ƽ��������Ʒ������һ���Ƽ���~~'});
						}
					}
				} else {
				    if (_buyBtn.attr('data-continue') == 's8') {
						_buyBtn.html('��ͬ��');
						_buyBtn.attr("href", "http://s8.taobao.com/search?cat=16&tab=mall&pid=mm_30820461_0_0&commend=all&unid=keyword&&mode=23&commend=1%2C2&filter=reserve_price[20%2C99]&fs=1&q=" + item.itemTitle);
						_buyBtn.attr("target", "_blank");
						_buyBtn.removeAttr("data-ugc-url");
						_buyBtn.removeAttr("taoke-href");
					} else {
					    _buyBtn.addClass('dd-hide');
						_buyBtn.parent().addClass("dd-hide");
					}
				}
			};
			
			_ugcLayer.find('.query-btn').click(function() {
			    var _this = $(this);
				if (_this.hasClass("disable")) {
				    return ;
				}
				hideQueryInfo();
			    var itemUrl = _ugcLayer.find(".item-url").val();
				if (!self.isValidItemUrl(itemUrl)) {
				    showOperInfo(_ugcLayer.find(".query-info"), "��ʱֻ�ܲ�ѯ�Ա�����è�̳Ǳ���Ŷ~~~");
					return ;
				}
				_this.addClass("disable");
				_ugcLayer.find(".query-load").show();
				$.ajax({
				    url: envRoot + "/frame/q/remote/rest/query_ugc_ajax.htm",
					type: "post",
					data: {itemUrl: itemUrl},
					success: function(result) {
					    _this.removeClass("disable");
					    _ugcLayer.find(".query-load").hide();
					    var json = result.json;
						if (json.code == 'success') {
							var data = json.data;
							initUgcItemInfo(data);
							var _buyBtn = _ugcLayer.find(".ugc-buy");
							_buyBtn.attr("data-continue", 's8');
							initUgcBuyBtn(_buyBtn, data);
							//����Ʒ�����ַ���ť
							showUgcShareToApp(data.ownerSign == 3 && data.integralCount > 0);
						} else {
						    var detail = json.detail;
							if (detail == 'url.invalid') {
							    showOperInfo(_ugcLayer.find(".query-info"), "��ʱֻ�ܹ����Ա�����è�̳Ǳ���Ŷ~~~");
							} else if (detail == 'userid.invalid') {
							    showOperInfo(_ugcLayer.find(".query-info"), "�㻹û�е�¼Ŷ~~~");
							} else {
							    showOperInfo(_ugcLayer.find(".query-info"), "ץȡ��Ʒ��Ϣʧ�ܣ�������~~~");
							}
						}
					},
					error: function() {
					    _this.removeClass("disable");
					    _ugcLayer.find(".query-load").hide();
					    showOperInfo(_ugcLayer.find(".query-info"), "ץȡ��Ʒ��Ϣʧ�ܣ�������~~~");
					}
				});
			});
			_exSearchLayer.find(".query-btn").click(function() {
			    var _this = $(this);
				if (_this.hasClass("disable")) {
				    return ;
				}
				_exSearchLayer.find(".ugc-content").hide();
				_exSearchLayer.find(".info").hide();
				_exSearchLayer.find(".ex-info").hide();
				clearExSubInfo();
			    var itemUrl = _exSearchLayer.find(".item-url").val();
				if (!self.isValidItemUrl(itemUrl)) {
				    showOperInfo(_exSearchLayer.find(".query-info"), "��ʱֻ�ܶһ��Ա�����è�̳Ǳ���Ŷ~~~");
					return ;
				}
				_this.addClass("disable");
				_exSearchLayer.find(".query-load").show();
				$.ajax({
				    url: envRoot + "/frame/q/remote/rest/query_ugc_ajax.htm",
					type: "post",
					data: {itemUrl: itemUrl},
					success: function(result) {
					    var json = result.json;
						_exSearchLayer.find(".query-load").hide();
						_this.removeClass("disable");
						if (json.success) {
							var data = json.data;
							var _ugcContent = _exSearchLayer.find(".ugc-content");
							_ugcContent.find(".item-pic").remove();
				            _ugcContent.find(".pic-wrap").append('<img class="item-pic" src="' + data.picUrl80x80 + '"/>');
							_ugcContent.find(".title").html(data.itemTitle);
							_ugcContent.find(".ori-price").html("&yen;" + data.itemPromPriceFormat);
							_ugcContent.find(".ex-integral").html(data.exchangeModel.exIntegral);
							_ugcContent.find(".integral").html(data.integralCount);
							initUgcBuyBtn(_exSearchLayer.find(".ugc-buy"), data);
							_ugcContent.show();
						} else {
						    showOperInfo(_exSearchLayer.find(".query-info"), "ץȡ��Ʒ��Ϣʧ�ܣ�������~~~");
						}
					}, 
					error: function() {
						_exSearchLayer.find(".query-load").hide();
						_this.removeClass("disable");
						showOperInfo(_exSearchLayer.find(".query-info"), "ץȡ��Ʒ��Ϣʧ�ܣ�������~~~");
					}
				});
			});
			var clearExSubInfo = function() {
			    var _exInfo = _exSearchLayer.find(".ex-info");
				_exInfo.find(".item-size").val('');
				_exInfo.find(".item-color").val('');
				_exInfo.find(".item-price").val('');
				_exInfo.find(".postal-fee").val('');
				_exInfo.find(".chk-info").html('');
				_exInfo.find('.ugc-ex-submit').html('�ύ���');
			};
			_exSearchLayer.find(".close-btn").click(function() {
				_exSearchLayer.find(".query-btn").removeClass("disable");
				_exSearchLayer.find(".query-load").hide();
				_exSearchLayer.find(".ugc-content").hide();
				_exSearchLayer.find(".info").hide();
				clearExSubInfo();
				_exSearchLayer.find(".ex-info").hide();
				_exSearchLayer.hide();
			});
			_exSearchLayer.find(".ugc-ex").click(function() {
			    var _exInfo = _exSearchLayer.find(".ex-info");
				clearExSubInfo();
				_exInfo.show();
			});
			_exSearchLayer.find(".ex-info .item-price").change(function() {
			    var _this = $(this);
				var _exInfo = _exSearchLayer.find(".ex-info");
				var _exSubmit = _exInfo.find(".ugc-ex-submit");
				var reg = /^[0-9]+.?[0-9]*$/;
				var price = _this.val();
				if (!reg.test(price)) {
				    showOperInfo(_exInfo.find(".chk-info"), "��������ȷ����Ʒ�۸�");
					_exSubmit.attr("data-yes", 'n');
					return;
				}
				var postalFee = _exInfo.find(".postal-fee").val();
				if (postalFee != '' && !reg.test(postalFee)) {
				    showOperInfo(_exInfo.find(".chk-info"), "��������ȷ���ʷѡ�");
					_exSubmit.attr("data-yes", 'n');
					return;
				}
				price = parseFloat(price);
				if (postalFee != '') {
				    price = price + parseFloat(postalFee);
				}
				var exIntegral = parseInt(price * 100);
				var userIntegral = parseInt($(".header .integral-count").html());
				if (exIntegral > userIntegral) {
				    showOperInfo(_exInfo.find(".chk-info"), "��Ļ��ֲ��㡣�һ�����Ʒ��Ҫ" + exIntegral + "���֡�");
					_exSubmit.attr("data-yes", 'n');
					return;
				}
				showOperInfo(_exInfo.find(".chk-info"), "�һ�����Ʒ��Ҫ" + exIntegral + "���֡�");
				_exSubmit.attr("data-yes", 'y');
			});
			_exSearchLayer.find(".ex-info .postal-fee").change(function() {
			    _exSearchLayer.find(".ex-info .item-price").trigger("change");
			});
			var _resultMessage = $("#resultMessage");
			_exSearchLayer.find(".ex-info .ugc-ex-submit").click(function() {
			    var _this = $(this);
				if (_this.hasClass("disable")) {
				    return ;
				}
				var _exInfo = _exSearchLayer.find(".ex-info");
				_exInfo.find(".item-price").trigger("change");
				if(_this.attr("data-yes") == 'n') {
				    return;
				}
				var itemUrl = _exSearchLayer.find(".item-url").val();
				var itemSize = _exInfo.find(".item-size").val();
				var itemColor = _exInfo.find(".item-color").val();
				var itemPrice = _exInfo.find(".item-price").val();
				var postalFee = _exInfo.find(".postal-fee").val();
				var memo = _exInfo.find(".memo").val();
				_this.addClass("disable");
				_this.html('�ύ��...');
				$.ajax({
				    url: envRoot + '/frame/q/remote/rest/ugc_ex_submit_ajax.htm',
					type: "post",
					data: {itemUrl: encodeURI(itemUrl),itemSize: encodeURI(itemSize), itemColor: encodeURI(itemColor),
					       itemPrice: encodeURI(itemPrice), postalFee: encodeURI(postalFee), memo: encodeURI(memo)},
					success: function(result) {
					    _this.removeClass("disable");
						_this.html('�ύ���');
						var json = result.json;
						if (json.success) {
						    _exSearchLayer.find(".close-btn").trigger("click");
							self.centerPoint(_resultMessage);
							shadowBg.show();
							_resultMessage.show();
						} else {
						    var detail = json.detail;
				            var _info = _exInfo.find(".chk-info");
							if (detail == 'itemprice.error') {
							    showOperInfo(_info, "��������ȷ����Ʒ�۸�");
							} else if (detail == 'integral.less') {
							    showOperInfo(_info, "���Ļ��ֲ������һ�����Ʒ��Ҫ" + json.data + "���֡�");
							} else if (detail == 'unknown.item') {
							    showOperInfo(_info, "�޷�ʶ�����Ʒ����ȷ�����ύ�ĵ��Ա�����Ʒ��");
							} else {
							    showOperInfo(_info, "ϵͳ�ڲ��������Ժ����ԡ�");
							}
						}
					},
					error: function() {
					    showOperInfo(_info, "ϵͳ�ڲ��������Ժ����ԡ�");
					    _this.removeClass("disable");
						_this.html('�ύ���');
					}
				});
			});
			_resultMessage.find(".close-btn").click(function() {
			    shadowBg.hide();
			    _resultMessage.hide();
			});
			
			var _header = $(".header");
			_header.find(".integral-tab").click(function() {
			    var _this = $(this);
				if (!_this.hasClass("on")) {
				    _header.find(".search-tabs .on").removeClass("on");
				    _this.addClass("on");
					_header.find(".search").addClass("dd-hide");
					_header.find(".integral-search").removeClass("dd-hide");
				}
			});
			_header.find(".exchange-tab").click(function() {
			    var _this = $(this);
				if (!_this.hasClass("on")) {
				    _header.find(".search-tabs .on").removeClass("on");
				    _this.addClass("on");
					_header.find(".search").addClass("dd-hide");
					_header.find(".exchange-search").removeClass("dd-hide");
				}
			});
			$(".integral-search .search-btn").click(function() {
			    var _taokeSearch = $(".integral-search");
				var itemUrl = _taokeSearch.find(".item-url").val();
				if (!self.isValidItemUrl(itemUrl)) {
				    alert("Ҫ�����Ա���Ʒ��ַŶ~~~");
					return ;
				}
				_ugcLayer.attr("data-example", 'n');
				$(".menu-nav .ugc-btn").trigger("click");
				_ugcLayer.find(".item-url").val(itemUrl);
				_ugcLayer.find('.query-btn').trigger("click");
			});
			$(".exchange-search .search-btn").click(function() {
			    var _taokeSearch = $(".exchange-search");
				var itemUrl = _taokeSearch.find(".item-url").val();
				if (!self.isValidItemUrl(itemUrl)) {
				    alert("Ҫ�����Ա���Ʒ��ַŶ~~~");
					return ;
				}
				_exSearchLayer.find(".item-url").val(itemUrl);
				_exSearchLayer.find(".query-btn").trigger("click");
				self.centerPoint(_exSearchLayer);
				_exSearchLayer.show();
			});
			
			$("#tiles").find(".auto-exchange .ex-url").focus(function() {
			    var _this = $(this);
				var _li = _this.closest("li");
				_li.find(".wrap").animate({width: 388}, 600);
				_li.find(".ex-url").animate({width: 347}, 600);
			});
			$("#tiles").find(".auto-exchange .ex-url").blur(function() {
			    var _this = $(this);
				var _li = _this.closest("li");
			    _li.find(".wrap").animate({width: 231}, 600);
				_li.find(".ex-url").animate({width: 190}, 600);
			})
			
			var showBuyInfo = function(_infoContainer, info) {
			    _infoContainer.find(".buy-info").html(info);
				_infoContainer.find(".buy-info").show();
			};
			$(document).delegate('[data-ugc-url]', 'click', function(e) {
			    var _this = $(this);
				if (_this.hasClass("disable")) {
				    return;
				}
			    var _container = _this.closest(".ugc-content");
				var itemUrl = _this.attr("data-ugc-url");
				if (itemUrl == undefined || !self.isValidItemUrl(itemUrl)) {
				    showBuyInfo(_container, "��Ʒ��ַ�������Ժ�����~~");
					return ;
				}
				_this.addClass("disable");
				_container.find(".buy-load").show();
				var shareTo = false;
				var _shareToApp = _container.find(".share-to-app");
				shareTo = _shareToApp.size() > 0 && _shareToApp.attr("checked") == 'checked';
				$.ajax({
				   url: envRoot + "/frame/q/remote/rest/sub_ugc_ajax.htm",
                   type: "post",
                   data: {itemUrl: itemUrl, shareTo: shareTo},
				   async: false ,
				   success: function(result) {
				       _this.removeClass("disable");
				       _container.find(".buy-load").hide();
				       var json = result.json;
					   if (json.code == 'success') {
					       _this.removeAttr("data-ugc-url");
						   _this.attr("taoke-href", '');
						   _this.attr("target-url", json.data);
						   _this.html("ȥ�����û���");
						   _ugcLayer.attr("data-ugc-done", 'y');
						   if (_this.attr("data-ugc-done") == 'n' && json.detail == 'ugc.done') {
							   e.stopPropagation();
							   self.modifyUserIntegral({integral: 15});
							   self.showTipDialog({parent: _this, msg:'�Ƽ��ɹ���ϵͳ����15���֡�'});
						   } else {
						       if (json.detail == 'ugc.done') {
							       showBuyInfo(_container, "��һ�ι���������15����~~");
							   }
							   fusion2.nav.open({
								   url : json.data
							   });
						   }
					   } else {
					       var detail = json.detail;
						   if (detail == 'url.invalid' || detail == 'item.not.exist') {
						       showBuyInfo(_container, "û���ҵ���Ʒ����ʱֻ�ܷ����Ա�����è�̳���ƷŶ~~");
						   } else if (detail == 'userid.invalid') {
						       showBuyInfo(_container, "����û�е�¼��~~");
						   } else {
						       showBuyInfo(_container, "ѽ��ϵͳ���������������Ժ�����~~");
						   }
					   }
				   },
				   error: function() {
				       _this.removeClass("disable");
				       _this.parent().find(".buy-load").hide();
				       showBuyInfo(_container, "ѽ��ϵͳ���������������Ժ�����~~");
				   }
				});
			});
		},
		
		/**
		 * ����
		 */
		centerPoint:function(selector){
			if(selector == null || selector == undefined){
				return  ;
			}
			fusion2.canvas.getClientRect({
			    onSuccess: function(rect) {
					var left = bodyClientLeft + (bodyClientRight - bodyClientLeft) / 2 - $(selector).width()/2;
					var top = rect.top + (rect.bottom - rect.top - selector.height())/2;
					if (top <= 10) {
					    top = 20;
					}
					selector.css({
						left:left,top:top
					}) ;
				}
			});
		},
		
		refreshIntegralBtn: function(className, integral) {
			var integralBtn = integralGuide.find("." + className);
			integralBtn.unbind("click");
			if (integralBtn.size() > 0) {
				integralBtn.removeClass(className);
				integralBtn.addClass("done");
				integralBtn.after("<div class='done'></div>");
			}
			var _messageArea = $(".menu-nav .message-area");
			var _unintegral = _messageArea.find(".unintegral");
			var _taskCount = _messageArea.find(".task-count");
			if (_unintegral.size() > 0 && integral > 0) {
			    var temp = parseInt(_unintegral.html()) - integral;
				_unintegral.html(temp);
			}
			if (_taskCount.size() > 0 && integral > 0) {
			    var temp = parseInt(_taskCount.html()) - 1;
				_taskCount.html(temp);
				if (temp == 0) {
				    _messageArea.find("li.integral").remove();
					var _num = _messageArea.find(".total-num");
					var temp = parseInt(_num.html()) - 1;
					if (temp > 0) {
					    _num.html(temp);
						_messageArea.find(".normal-num").html(temp);
					}
					if (temp == 0) {
					    _num.remove();
						_messageArea.find(".normal-num").remove();
					}
				}
			}
		},
		_initExchangeEvent: function() {
		    if (window.location.href.indexOf("index") == -1) {
			    return;
			}
		    $("li.main").each(function() {
			    var _this = $(this);
				var exIntegral = parseInt(_this.find(".ex-integral").attr("data-exintegral"));
				if (exIntegral == undefined || isNaN(exIntegral)) {
				    return;
				}
				var _integralWrap = _this.find(".integral-wrap");
				_integralWrap.append('<span class="more"></span>');
				var moreHtml = '<div class="integral-more dd-hide"><ul>';
				var discounts = new Array(1,0.95,0.90,0.85,0.80);
				for (var i = 0; i < discounts.length; i++) {
				    moreHtml += '<li><span class="user-level' + i + ' dd-l"></span><span>�û�:<span class="color1">' + parseInt(exIntegral * discounts[i]) + '</span>����</span></li>'
				}
				moreHtml += '</ul></div>';
				_integralWrap.append(moreHtml);
				_integralWrap.hover(function() {
				    _integralWrap.find(".integral-more").removeClass("dd-hide");
					_integralWrap.find(".more").addClass("more-down");
					_integralWrap.find(".integral-more").css({width: _integralWrap.width() + 10});
				}, function() {
				    _integralWrap.removeClass("integral-drop");
				    _integralWrap.find(".integral-more").addClass("dd-hide");
					_integralWrap.find(".more").removeClass("more-down");
				});
			});
		    var _exchangeLi;
			var _exchangeLayer = $(".ex-layer");
			var _confirmBtn = _exchangeLayer.find(".confirm-btn");
			var _exchangeInfo = _exchangeLayer.find(".info");
			var exchangeEnd = function(config) {
			    var _restNum = _exchangeLayer.find(".rest-num");
			    var restNum = parseInt(_restNum.html());
				if (config.exCount != undefined) {
				    restNum = restNum - config.exCount;
				}
				_restNum.html(restNum);
				_exchangeLi.find(".rest-num").html(restNum);
				_exchangeInfo.html(config.message);
			}
			$(".exchange-list .exchange-btn").click(function() {
			    var _this = $(this);
				_exchangeLi = _this.closest("li");
				_exchangeLayer.find(".ex-num").val("1");
				var userIntegral = parseInt($(".menu-nav .integral-count").html());
				var exIntegral = parseInt(_this.data("exintegral"));
				if (!isNaN(userIntegral) && !isNaN(exIntegral) && userIntegral < exIntegral) {
				    _exchangeInfo.html("��Ļ��ֲ���Ŷ~~");
				    _confirmBtn.addClass("disable-btn");
				} else {
				    _exchangeInfo.html("");
				    _confirmBtn.removeClass("disable-btn");
				}
				_confirmBtn.attr("data-exintegral", _this.data("exintegral"));
				_confirmBtn.attr("data-exchange-id", _this.data("exchange-id"));
				_exchangeLayer.find(".rest-num").html(_exchangeLi.find(".rest-num").html());
				_confirmBtn.show();
				var top = _this.offset().top - 190;
				var left = _this.offset().left - 43;
				_exchangeLayer.css({top: top, left: left});
				_exchangeLayer.show();
			});
			_exchangeLayer.find(".ex-num").keyup(function() {
			    var _this = $(this);
				var num = parseInt(_this.val());
				var restNum = parseInt(_exchangeLayer.find(".rest-num").html());
				if (isNaN(num) || num <= 0) {
				    _exchangeInfo.html('��������Ч�Ķһ�����~~');
					_confirmBtn.addClass("disable-btn");
					return ;
				} else if (num > restNum) {
				    _exchangeInfo.html('û����ô����Ʒ��~~');
					_confirmBtn.addClass("disable-btn");
					return ;
				} else {
				    _exchangeInfo.html('');
					_confirmBtn.removeClass("disable-btn");
				}
				var userIntegral = parseInt($(".menu-nav .integral-count").html());
				var exIntegral = parseInt(_confirmBtn.attr("data-exintegral")) * num;
				if (isNaN(userIntegral) || isNaN(exIntegral)) {
				    _exchangeInfo.html("�һ������е����⣬ˢ��һ�°�~~");
				    _confirmBtn.addClass("disable-btn");
					return ;
				} else if (userIntegral < exIntegral) {
				    _exchangeInfo.html("��Ļ��ֲ���Ŷ~~");
				    _confirmBtn.addClass("disable-btn");
					return ;
				} else {
				   _confirmBtn.removeClass("disable-btn");
				}
			});
			_exchangeLayer.find(".confirm-btn").click(function() {
			    var _this = $(this);
				if (_this.hasClass("disable-btn") || _this.hasClass("disable")) {
				    return;
				}
				var exCount = _exchangeLayer.find(".ex-num").val();
				var restNum = parseInt(_exchangeLayer.find(".rest-num").html());
				var exchangeId = _this.data('exchange-id');
				if (isNaN(exCount) || isNaN(exchangeId)) {
				    _exchangeInfo.html("�һ������е����⣬ˢ��һ�°�~~");
				    _confirmBtn.addClass("disable-btn");
					return ;
				}
				_this.addClass("disable");
				$.ajax({
				    url: envRoot + "/frame/q/remote/rest/exchange_item_ajax.htm",
					type: "post",
					data: {exchangeId: exchangeId, exCount: exCount},
					success: function(result) {
					    var json = result.json;
						var data = json.data;
						if (json.code == 'success') {
							self.modifyUserIntegral({integral: -1 * data});
						    exchangeEnd({exCount: parseInt(exCount), message: "�һ��ɹ���ϵͳ����У����ע�����Ϣ"});
							_confirmBtn.hide();
						} else {
						    if (json.detail == 'excount.more') {
								exchangeEnd({exCount: restNum - json.data, message: "�һ�ʧ�ܣ�û����ô����Ʒ��~~"});
							} else if (json.detail == 'integral.less') {
								exchangeEnd({exCount: 0, message: "�һ�ʧ�ܣ���Ļ��ֲ���Ŷ~~"});
							} else if (json.detail == 'exchange.ended') {
							    exchangeEnd({exCount: 0, message: "�һ�ʧ�ܣ���Ʒ�ոձ�������~~"});
							}  else if (json.detail == 'invalid.excount') {
							    exchangeEnd({exCount: 0, message: "�һ�ʧ�ܣ��һ���������~~"});
							} else {
							    exchangeEnd({exCount: 0, message: "�һ�ʧ�ܣ�ˢ��һ�°�~~"});
							}
						}
						_this.removeClass("disable");
					},
					error: function() {
					    exchangeEnd({exCount: 0, message: "�һ�ʧ�ܣ�ˢ��һ�°�~~"});
					}
				});
			});
			_exchangeLayer.find(".close-btn").click(function() {
			    _exchangeLayer.hide();
			});
			
			$("#tiles").find(".auto-exchange .ex-url").focus(function() {
			    var _this = $(this);
				var _li = _this.closest("li");
				_li.find(".wrap").animate({width: 388}, 600);
				_li.find(".ex-url").animate({width: 347}, 600);
			});
			$("#tiles").find(".auto-exchange .ex-url").blur(function() {
			    var _this = $(this);
				var _li = _this.closest("li");
			    _li.find(".wrap").animate({width: 231}, 600);
				_li.find(".ex-url").animate({width: 190}, 600);
			});
			$("#tiles").find(".auto-exchange .ex-search-btn").click(function() {
			    var _this = $(this);
				var _li = _this.closest("li");
				var itemUrl = _li.find(".ex-url").val();
				if (!self.isValidItemUrl(itemUrl)) {
				    var _exSearchGuide = $('#exSearchGuide');
					if (_exSearchGuide.size() > 0) {
					    self.centerPoint(_exSearchGuide);
					    _exSearchGuide.show();
					} else {
					    alert("��������Ա���Ʒ��ַ����Ŷ~~~");
					}
					return ;
				}
				var _exSearch = $(".header .exchange-search");
				_exSearch.find(".ex-url").val(itemUrl);
				_exSearch.find(".search-btn").trigger("click");
			});
		},
		
		_initLayerEvent: function() {
		    if ((window.location.href.indexOf("best_sale") != -1 || window.location.href.indexOf("pgcs") != -1)) {
				$.ajax({
					url: envRoot + "/frame/q/remote/rest/query_flayer_show_ajax.htm",
					type: "post",
					data: {},
					success: function(result) {
						var json = result.json;
						if (!json.success) {
							return;
						}
						var data = json.data;
						if (data.ugc) {
							ugcTimer = setTimeout(function() {
								$(".menu-nav .ugc-btn").trigger("click");
							}, 4000);
							return ;
						}
					},
					error: function() {
					
					}
				});
			}
			var _exSearchGuide = $('#exSearchGuide');
			if (_exSearchGuide.size() > 0) {
			    _exSearchGuide.find(".next-btn").click(function() {
				    var _this = $(this);
					var index = parseInt(_this.attr("data-index"));
				    var _steps = _exSearchGuide.find(".steps");
					if (index < 3) {
					    _steps.animate({left: -1 * index * 520}, 600);
						_this.parent().find("a").attr("data-index", index + 1);
						_this.parent().find(".prev-btn").addClass("prev-btn-enable");
						if (index == 2) {
					        _this.removeClass("next-btn-enable");
						}
					}
				});
				_exSearchGuide.find(".prev-btn").click(function() {
				    var _this = $(this);
					var index = parseInt(_this.attr("data-index"));
				    var _steps = _exSearchGuide.find(".steps");
					if (index > 1) {
					    _steps.animate({left: -1 * (index - 2) * 520}, 600);
						_this.parent().find("a").attr("data-index", index - 1);
						_this.parent().find(".next-btn").addClass("next-btn-enable");
						if (index == 2) {
						    _this.removeClass("prev-btn-enable");
						}
					}
				});
				_exSearchGuide.find(".read").click(function() {
				    $.ajax({
					    url: envRoot + "/frame/q/remote/rest/mark_guide_done_ajax.htm",
						type: 'post',
						data: {guideStr: 'ex_s'},
						success: function(result) {
						
						},
						error: function() {
						
						}
					});
					_exSearchGuide.remove();
				});
				_exSearchGuide.find(".close-btn").click(function() {
				    _exSearchGuide.hide();
				});
			}
		},
		
		isValidItemUrl: function(itemUrl) {
			if(itemUrl.indexOf('taobao.com') != -1 || itemUrl.indexOf("tmall.com") != -1 ) {
				var itemIdArray = ['id','itemid','item_id','mallstitemid','default_item_id'] ;
				for(var i = 0 ; i < itemIdArray.length ; i++){
					var id = itemIdArray[i] + '=';
					if(itemUrl.indexOf(id) != -1){
						isTaobaoItemUrl = true ;
						return true;
					}
				}
			}
			return false;
		},
		
		end:0
	};

	$(function(){
		
	});
	//fix ����IE���޷�����
	index.init();
})();


