!(function($){
	$.namespace("DD.BopsDcome");
	
	var self = DD.BopsDcome;
	
	var ddzBopsRoot = $("#ddzBopsRoot").val() ; ;
	
	$.extend(DD.BopsDcome,{
		
		init:function(){
			self._initDcAddSceneItem() ;
			self._initDcSceneDetail();
			self._initDcItemList() ;
			self._initDcSceneList() ;
			self._initDcCreateScene() ;
			self._initDcCommentList();
			self._initDcSystemOptions() ;
			self._initDcCategoriesOptions() ;
			self._initValidator() ;
			self._initDatePicker();
		},
		
		/**
		 * �����Ʒ������
		 */
		_initDcAddSceneItem:function(){
			if($("#ddzBopsPage").val() == "dcAddSceneItem") {
				var exclusionIds = $("#dcSceneDetails").val() ;
				if(exclusionIds != ''){
					var exclusionIdList = exclusionIds.split(","); 
					//��ʼ�����������Ѿ���ӵ�ȥ��
					$("[data-init-add-scene-item]").each(function(){
						var _this = $(this) ;
						var itemId = _this.attr("data-item-id") ;
						if(itemId != ''){
							for(var i = 0 ;i<exclusionIdList.length;i++){
								if(exclusionIdList[i] == itemId){
									_this.addClass("dd-hide") ;
									_this.parent().find("[data-init-remove-scene-item]").removeClass("dd-hide") ;
									break ;
								}
							}
						}
					});
				}
				//end $("[data-init-addSceneItem-itemId]").each(function(){
				
				//Ϊ  ���add�¼�
				$("[data-init-add-scene-item]").click(function(){
					if(confirm('��ӣ�')){
						var url = ddzBopsRoot + "/bops/dcome/qq/remote/add_scene_item_ajax.htm";
						var _this = $(this) ;
						var itemId = _this.attr("data-item-id") ;
						var sceneId = _this.attr("data-scene-id") ;
						$.ajax({
				    		url : url ,
							type : "POST" ,
							data : {itemId:itemId , sceneId:sceneId},
							success : function(data){
								var json = data.json ;
								var code = json.code ;
								var data = json.data ;
								var detail = json.detail ;
								if(code == 'success') { 
									_this.addClass("dd-hide") ;
									_this.parent().find("[data-init-remove-scene-item]").removeClass("dd-hide") ;
								} else {
									if(detail == 'dcome.addSceneItem.category.maxItemSize'){
										alert('����ʧ�ܣ�����Ŀ���Ѿ��ﵽ��Ʒ���ޣ�');
										return ;
									}
									alert('����ʧ�ܣ� ' + detail ) ;
								}
							} ,
							error : function(data){
								alert('����ʧ�� ��' + data ) ;
							}
						});
					}
				});
				//end $("[data-init-add-scene-item]").find("a").click(function(){
				
				//���remove�¼�
				$("[data-init-remove-scene-item]").click(function(){
					if(confirm('�Ƴ���')){
						var url = ddzBopsRoot + "/bops/dcome/qq/remote/remove_scene_item_ajax.htm";
						var _this = $(this) ;
						var itemId = _this.attr("data-item-id") ;
						var sceneId = _this.attr("data-scene-id") ;
						$.ajax({
				    		url : url ,
							type : "POST" ,
							data : {itemId:itemId , sceneId:sceneId},
							success : function(data){
								var json = data.json ;
								var code = json.code ;
								var data = json.data ;
								var detail = json.detail ;
								if(code == 'success') { 
									_this.addClass("dd-hide") ;
									_this.parent().find("[data-init-add-scene-item]").removeClass("dd-hide") ;
								} else {
									alert('����ʧ�ܣ� ' + detail ) ;
								}
							} ,
							error : function(data){
								alert('����ʧ�� ��' + data ) ;
							}
						});
					}
				});
				//end $("[data-init-remove-scene-item]").find("a").click(function(){
				
				/**
				 * ͳ��ÿ����Ŀ�ж�����Ʒ
				 */
				var _initCatItemCount = $("[data-init-cat-item-count]") ; 
				if(_initCatItemCount.length > 0){
					var sceneId = $("#sceneId").val() ;
					var url = ddzBopsRoot + "/bops/dcome/qq/remote/group_scene_cats_ajax.htm";
					$.ajax({
			    		url : url ,
						type : "POST" ,
						data : {sceneId:sceneId},
						success : function(data){
							var json = data.json ;
							var code = json.code ;
							var data = json.data ;
							var detail = json.detail ;
							if(code == 'success') { 
								$("[data-init-cat-item-count]").each(function(){
									var __this = $(this) ;
									var catId = __this.attr("data-cat-id") ;
									if(data.length > 0){
										for(var i=0 ;i<data.length;i++){
											var _catid = data[i].categoryId ;
											var _itemCount = data[i].itemCount ;
											if(_catid == catId){
												__this.html(_itemCount) ;
												break ;
											}
										}
									}
									if(__this.val() == ''){
										__this.val(0) ;
									}
								}) ;
							} else {
								alert('��ȡ��Ŀ��Ϣʧ�ܣ� ' + detail ) ;
							}
						} ,
						error : function(data){
							alert('��ȡ��Ŀ��Ϣʧ�� ��' + data ) ;
						}
					});
				}
				//end if(_initCatItemCount.length > 0){
			}
		} ,
		
		/**
		 * �����б�
		 */
		_initDcCommentList:function(){
			if($("#ddzBopsPage").val() == "dcCommentList") {
				$("[data-comment-status]").click(function(){
					var _this = $(this);
					
					var status = _this.attr("data-comment-status");
					var commentId = _this.attr("data-comment-id") ;
					var url = ddzBopsRoot + "/bops/dcome/qq/remote/set_comment_status_ajax.htm";
					if(status != '' && commentId != ''){
						
						$.ajax({
				    		url : url ,
							type : "POST" ,
							data : {commentId:commentId , status:status},
							success : function(data){
								var json = data.json ;
								var code = json.code ;
								var data = json.data ;
								var detail = json.detail ;
								if(code == 'success') { 
									alert("�������۳ɹ�");
									window.location.reload();
								} else {
									alert('��������״̬ʧ�ܣ� ' + detail ) ;
								}
							} ,
							error : function(data){
								alert('��������״̬ʧ�� ��' + data ) ;
							}
						});
					}
					
				});
				//end $("[data-commentStatus]").click(function(){
				
				//����...
				$("[data-toggle-id]").click(function(){
					var id = $(this).attr("data-toggle-id") ;
					var toggle = $("#"+id) ;
					if(toggle.hasClass("dd-hide")){
						toggle.removeClass("dd-hide") ;
					}else{
						toggle.addClass("dd-hide") ;
					}
				});
				//end $("[data-toggle-id]").click(function(){
			}
		} ,
		
		
		/**
		 * �����б�
		 */
		_initDcSceneList:function(){
			if($("#ddzBopsPage").val() == "dcSceneList") {
				self.activeScene() ;
			}
		} ,
		
		/**
		 * ����detailҳ
		 */
		_initDcSceneDetail:function(){
			if($("#ddzBopsPage").val() == "dcSceneDetail") {
				
				//ɾ������
				$("[data-removeItemId]").click(function(){
					if(!confirm('ɾ�������¸���Ʒ��')){
						return ;
					}
					var _this = $(this) ;
					var itemId = $(this).attr('data-removeItemId') ;
					var sceneId = $(this).attr('data-sceneId') ;
					var ddzBopsRoot = $("#ddzBopsRoot").val() ; ;
					var url = ddzBopsRoot + "/bops/dcome/qq/remote/remove_scene_item_ajax.htm";
					$.ajax({
			    		url : url ,
						type : "POST" ,
						data : {itemId:itemId , sceneId:sceneId},
						success : function(data){
							var json = data.json ;
							var code = json.code ;
							var data = json.data ;
							var detail = json.detail ;
							if(code == 'success') { 
								alert('�����ɹ�');
								window.location.reload();
							} else {
								alert('����ʧ��. ԭ��' + detail ) ;
							}
						} ,
						error : function(data){
							alert('����ʧ��. ԭ��' + data ) ;
						}
					});
				}) ;
				//end $("[data-removeItemId]").click(function(){
				
				self.activeScene() ;
			}
		} ,
		
		/**
		 * ��Ʒ�б�ҳ | ���������Ʒҳ
		 */
		_initDcItemList:function(){
			if($("#ddzBopsPage").val() == "dcItemList") {
				
				//�����Ʒ������
				$("[data-addSceneItemId]").click(function(){
					if(!confirm('�����Ʒ��������')){
						return ;
					}
					var _this = $(this) ;
					var itemId = $(this).attr('data-addSceneItemId') ;
					var sceneId = $(this).attr('data-sceneId') ;
					var ddzBopsRoot = $("#ddzBopsRoot").val() ; ;
					var url = ddzBopsRoot + "/bops/dcome/qq/remote/add_scene_item_ajax.htm";
					$.ajax({
			    		url : url ,
						type : "POST" ,
						data : {itemId:itemId , sceneId:sceneId},
						success : function(data){
							var json = data.json ;
							var code = json.code ;
							var data = json.data ;
							var detail = json.detail ;
							if(code == 'success') { 
								alert('�����ɹ�');
								window.location.reload();
							} else {
								alert('����ʧ��. ԭ��' + detail ) ;
							}
						} ,
						error : function(data){
							alert('����ʧ��. ԭ��' + data ) ;
						}
					});
				}) ;
				//end $("[data-addSceneItemId]").click(function(){
			}
		} ,
		
		/**
		 * ����|�޸ĳ���
		 */
		_initDcCreateScene:function(){
			if($("#ddzBopsPage").val() == "dcCreateScene") {
				self.activeScene() ;
				
				var catIds = $("#categoryIds").val() ;
				if(catIds == ''){
					return ;
				}
				var catList = catIds.split(",");
				$("[data-init-categoryIds]").each(function(){
					var _this = $(this) ;
					for(var i = 0 ;i<catList.length;i++){
						if(catList[i] == _this.val()){
							_this.attr("checked",true) ;
							break ;
						}
					}
				});
			}
		},
		
		
		/**
		 * �����
		 */
		activeScene:function(){
			$("[data-sceneActive]").click(function(){
				if(!confirm('ȷ���޸ļ���״̬��')){
					return ;
				}
				var _this = $(this) ;
				var active = $(this).attr('data-sceneActive') ;
				var sceneId = $(this).attr('data-sceneId') ;
				var ddzBopsRoot = $("#ddzBopsRoot").val() ; ;
				var url = ddzBopsRoot + "/bops/dcome/qq/remote/set_scene_active_ajax.htm";
				$.ajax({
		    		url : url ,
					type : "POST" ,
					data : {active:active , sceneId:sceneId},
					success : function(data){
						var json = data.json ;
						var code = json.code ;
						var data = json.data ;
						var detail = json.detail ;
						if(code == 'success') { 
							alert('�����ɹ�');
							window.location.reload();
						} else {
							alert('����ʧ��. ԭ��' + detail ) ;
						}
					} ,
					error : function(data){
						alert('����ʧ��. ԭ��' + data ) ;
					}
				});
			}) ;
		} ,
		
		/**
		 * JSУ��
		 */
		_initValidator:function(){
			//����������
			$("[data-validator-number]").each(function(){
				var _this = $(this) ;
				$(this).on("keyup",function(){
					var _val = _this.val() ;
					if(_val != ''){
						$(this).val(_val.replace(/[^\d\.]/g,'')) ;
					}
				});
				$(this).on("afterpaste",function(){
					var _val = _this.val() ;
					if(_val != ''){
						$(this).val(_val.replace(/[^\d\.]/g,'')) ;
					}
				}) ;
			});
		} ,
		
		/**
		 * ��ʼ�����ڿؼ�
		 */
		_initDatePicker:function(){
			$("[data-init-datepicker]").each(function(){
				var format = $(this).attr("data-init-datepicker") ;
				if(format == ''){
					format = 'yy-mm-dd' ;
				}
				$(this).datepicker({ dateFormat: format  ,changeYear:true});
			});
			
		} ,
		
		/**
		 * ��ʼ�� ��Ʒ��Ŀ����
		 */
		_initDcCategoriesOptions:function(){
			$("[data-init-dcCategorySelect]").each(function(){
				var url = ddzBopsRoot + "/bops/dcome/qq/remote/query_categories_ajax.htm";
				var _this = $(this) ;
				var _catLevel = _this.attr("catLevel") ;
				var _exclutionIds = _this.attr("exclutionIds") ;
				_this.empty() ;
				$.ajax({
		    		url : url ,
					type : "POST" ,
					data : {},
					success : function(data){
						var json = data.json ;
						var code = json.code ;
						var data = json.data ;
						var detail = json.detail ;
						if(code == 'success') {
							if (_this.attr("include-all") == "true") {
							    _this.append("<option value=''>ȫ��</option>");
							}
							if(data.length > 0){
								for(var i=0 ;i<data.length;i++){
									var cat = data[i] ;
									_this.append("<option value='" + cat.id + "'>" + cat.name +"</option>");
								}
								if(_this.attr("data-selCatId") != ''){
									_this.val(_this.attr("data-selCatId"));
								}
							}
							
							
						} else {
							alert('��ʼ����Ŀʧ�ܣ�' + detail ) ;
						}
					} ,
					error : function(data){
						alert('��ʼ����Ŀʧ�ܣ�' + data ) ;
					}
				});
				
			}) ;
			//end $("[data-init-dcCategorySelect]").each(function(){
			
			$("[data-init-dc-category-val]").each(function(){
				var url = ddzBopsRoot + "/bops/dcome/qq/remote/query_categories_ajax.htm";
				var _this = $(this) ;
				var _catLevel = _this.attr("catLevel") ;
				var _catId = _this.attr("data-cat-id") ;
				
				$.ajax({
		    		url : url ,
					type : "POST" ,
					data : {catId:_catId},
					success : function(data){
						var json = data.json ;
						var code = json.code ;
						var data = json.data ;
						var detail = json.detail ;
						if(code == 'success') { 
							
							if(data.length > 0){
								for(var i=0 ;i<data.length;i++){
									var cat = data[i] ;
									_this.html(cat.name) ;
									break ;
								}
							}
							
						} else {
							alert('��ʼ����Ŀʧ�ܣ�' + detail ) ;
						}
					} ,
					error : function(data){
						alert('��ʼ����Ŀʧ�ܣ�' + data ) ;
					}
				});
				
			}) ;
		} ,
		
		/**
		 * ��ʼ��ϵͳAPP select ����
		 * @data-parentCatId ������Ŀ
		 * @data-catLevel ��Ŀ����
		 * @data-selCatId ѡ�е���Ŀ
		 */
		_initDcSystemOptions:function(){
			$("[data-init-dcSystemSelect]").each(function(){
				var ddzBopsRoot = $("#ddzBopsRoot").val() ; ;
				var url = ddzBopsRoot + "/bops/dcome/qq/remote/query_systems_ajax.htm";
				var _this = $(this) ;
				var type = _this.attr("data-init-dcSystemSelect");
				var systemId = _this.attr("systemId") ;
				
				if(type == 'select'){
					_this.empty() ;
					$.ajax({
			    		url : url ,
						type : "POST" ,
						data : {},
						success : function(data){
							var json = data.json ;
							var code = json.code ;
							var data = json.data ;
							var detail = json.detail ;
							if(code == 'success') { 
								
								if(data.length > 0){
									for(var i=0 ;i<data.length;i++){
										var sys = data[i] ;
										_this.append("<option value='" + sys.id + "'>" + sys.name +"</option>");
									}
									if(_this.attr("data-selSysId") != ''){
										_this.val(_this.attr("data-selSysId"));
									}
								}
								
								
							} else {
								alert('��ʼ��ϵͳAPPʧ�ܣ�' + detail ) ;
							}
						} ,
						error : function(data){
							alert('��ʼ��ϵͳAPPʧ�ܣ�' + data ) ;
						}
					});
				} // end type=='select'
				
				if(type == 'name'){
					$.ajax({
			    		url : url ,
						type : "POST" ,
						data : {systemId:systemId},
						success : function(data){
							var json = data.json ;
							var code = json.code ;
							var data = json.data ;
							var detail = json.detail ;
							if(code == 'success') { 
								
								if(data.length > 0){
									for(var i=0 ;i<data.length;i++){
										var sys = data[i] ;
										_this.html(sys.name) ;
										break ;
									}
								}
								
								
							} else {
								alert('��ʼ��ϵͳAPPʧ�ܣ�' + detail ) ;
							}
						} ,
						error : function(data){
							alert('��ʼ��ϵͳAPPʧ�ܣ�' + data ) ;
						}
					});
				}
				
			
			});
		} ,
		
		
		end:0
	});
})(jQuery);

jQuery(document).ready(function(){
	DD.BopsDcome.init();
});
