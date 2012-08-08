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
		 * 添加商品到场景
		 */
		_initDcAddSceneItem:function(){
			if($("#ddzBopsPage").val() == "dcAddSceneItem") {
				var exclusionIds = $("#dcSceneDetails").val() ;
				if(exclusionIds != ''){
					var exclusionIdList = exclusionIds.split(","); 
					//初始化，把所有已经添加的去掉
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
				
				//为  添加add事件
				$("[data-init-add-scene-item]").click(function(){
					if(confirm('添加？')){
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
										alert('操作失败！该类目下已经达到商品上限！');
										return ;
									}
									alert('操作失败： ' + detail ) ;
								}
							} ,
							error : function(data){
								alert('操作失败 ：' + data ) ;
							}
						});
					}
				});
				//end $("[data-init-add-scene-item]").find("a").click(function(){
				
				//添加remove事件
				$("[data-init-remove-scene-item]").click(function(){
					if(confirm('移除？')){
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
									alert('操作失败： ' + detail ) ;
								}
							} ,
							error : function(data){
								alert('操作失败 ：' + data ) ;
							}
						});
					}
				});
				//end $("[data-init-remove-scene-item]").find("a").click(function(){
				
				/**
				 * 统计每个类目有多少商品
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
								alert('获取类目信息失败： ' + detail ) ;
							}
						} ,
						error : function(data){
							alert('获取类目信息失败 ：' + data ) ;
						}
					});
				}
				//end if(_initCatItemCount.length > 0){
			}
		} ,
		
		/**
		 * 评论列表
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
									alert("更新评论成功");
									window.location.reload();
								} else {
									alert('更新评论状态失败： ' + detail ) ;
								}
							} ,
							error : function(data){
								alert('更新评论状态失败 ：' + data ) ;
							}
						});
					}
					
				});
				//end $("[data-commentStatus]").click(function(){
				
				//更多...
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
		 * 场景列表
		 */
		_initDcSceneList:function(){
			if($("#ddzBopsPage").val() == "dcSceneList") {
				self.activeScene() ;
			}
		} ,
		
		/**
		 * 场景detail页
		 */
		_initDcSceneDetail:function(){
			if($("#ddzBopsPage").val() == "dcSceneDetail") {
				
				//删除场景
				$("[data-removeItemId]").click(function(){
					if(!confirm('删除场景下该商品？')){
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
								alert('操作成功');
								window.location.reload();
							} else {
								alert('操作失败. 原因：' + detail ) ;
							}
						} ,
						error : function(data){
							alert('操作失败. 原因：' + data ) ;
						}
					});
				}) ;
				//end $("[data-removeItemId]").click(function(){
				
				self.activeScene() ;
			}
		} ,
		
		/**
		 * 商品列表页 | 场景添加商品页
		 */
		_initDcItemList:function(){
			if($("#ddzBopsPage").val() == "dcItemList") {
				
				//添加商品到场景
				$("[data-addSceneItemId]").click(function(){
					if(!confirm('添加商品到场景？')){
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
								alert('操作成功');
								window.location.reload();
							} else {
								alert('操作失败. 原因：' + detail ) ;
							}
						} ,
						error : function(data){
							alert('操作失败. 原因：' + data ) ;
						}
					});
				}) ;
				//end $("[data-addSceneItemId]").click(function(){
			}
		} ,
		
		/**
		 * 创建|修改场景
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
		 * 激活场景
		 */
		activeScene:function(){
			$("[data-sceneActive]").click(function(){
				if(!confirm('确定修改激活状态？')){
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
							alert('操作成功');
							window.location.reload();
						} else {
							alert('操作失败. 原因：' + detail ) ;
						}
					} ,
					error : function(data){
						alert('操作失败. 原因：' + data ) ;
					}
				});
			}) ;
		} ,
		
		/**
		 * JS校验
		 */
		_initValidator:function(){
			//数字输入检查
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
		 * 初始化日期控件
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
		 * 初始化 商品类目下拉
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
							    _this.append("<option value=''>全部</option>");
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
							alert('初始化类目失败：' + detail ) ;
						}
					} ,
					error : function(data){
						alert('初始化类目失败：' + data ) ;
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
							alert('初始化类目失败：' + detail ) ;
						}
					} ,
					error : function(data){
						alert('初始化类目失败：' + data ) ;
					}
				});
				
			}) ;
		} ,
		
		/**
		 * 初始化系统APP select 下拉
		 * @data-parentCatId 父级类目
		 * @data-catLevel 类目级别
		 * @data-selCatId 选中的类目
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
								alert('初始化系统APP失败：' + detail ) ;
							}
						} ,
						error : function(data){
							alert('初始化系统APP失败：' + data ) ;
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
								alert('初始化系统APP失败：' + detail ) ;
							}
						} ,
						error : function(data){
							alert('初始化系统APP失败：' + data ) ;
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
