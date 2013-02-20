!(function($){
	
	var self ;
	
	var taobaoDefaultRate = 10 ;
	var ddzEatRate = 8 ;
	
	var taobaoWidget = {
				
		init:function(){
			self = this ;
			
			var taokeItemList = $(".taobaoke-item") ;
			self._initCommonFunctions() ;
			$(document).bind('reloadTaoke' , function(){
				self._initTaokeCommission({
					success:function(){
						$("#ddzShadow").animate({opacity:0},500,function(){
							$("#ddzShadow").addClass('dd-hide');
						}) ;
					} ,
					error:function(){
						$("#ddzShadow").find('.loading-text').html('查询返利出错，请刷新页面重试');
					}
				});
			}) ;
			
			$(document).trigger('reloadTaoke') ;
			
		},
		
		_initCommonFunctions:function(){
			String.prototype.trim = function(){
			    return this.replace(/(^\s*)|(\s*$)/g, "");
			} 
			
			/**
			 * n:小数位数
			 */
			Number.prototype.format = function(format) {
				//return $.formatNumber(this , {format:format}) ;
			}
		} ,
		
		/**
		 * attribute:
		 * @data-num-iid : 商品ID
		 * @data-outcode : 自定义参数
		 * @data-prom-price : 促销价（可无）
		 * @data-model : ddz | dcome 
		 */
		_initTaokeCommission:function(settings){
			var taokeItemList = $(".taobaoke-item") ;
			//先单独请求
			taokeItemList.each(function(){
				var _this = $(this) ;
				var numIid = _this.attr('data-num-iid') ;
				var outCode = _this.attr('data-outcode') ;
				var model = _this.attr('data-model') ;
				var promPrice = _this.attr('data-prom-price') ;
				if(model != 'ddz' && model != 'dcome' && model != 'bopsDcome'){
					return ;
				}
				if(numIid != '' && !isNaN(numIid)){
					numIid = parseInt(numIid) ;
					self.widgetConventItem(numIid , outCode ,{
						success:function(resp){
							//成功调用
							var taobaoke_item = {} ;
							var totalResults = resp.total_results ;
							if(totalResults <= 0){
								//无返利 或 商品不存在
								taobaoke_item.commission_rate = 0.00 ;
								taobaoke_item.commission = 0.00 ;
								taobaoke_item.click_url = null ;
							} else {
								taobaoke_item = resp.taobaoke_items.taobaoke_item[0] ;
								if(promPrice != ''){
									taobaoke_item.prom_price = promPrice ;
								}
								if(model == 'ddz'){
									taobaoke_item = self.calcDdzCommissions(taobaoke_item) ;
								} else if(model == 'dcome'){
									taobaoke_item = self.calcDcomeCommissions(taobaoke_item) ;
								} else if(model == 'bopsDcome') {
									taobaoke_item = self.calcBopsDcomeCommissions(taobaoke_item) ;
								} else {
									taobaoke_item = null ;
								}
							}
							
							if(taobaoke_item != null){
								var commissionElement = _this.find('.commission') ;
								self.autoFillingValue(commissionElement , self.formatNumber(taobaoke_item.commission,2)) ;
								var commissionRateElement = _this.find('.commission-rate') ;
								self.autoFillingValue(commissionRateElement , self.formatNumber((taobaoke_item.commission_rate / 100.0) , 2)) ;
								var clickUrlElement = _this.find('.click-url') ;
								self.autoFillingValue(clickUrlElement , taobaoke_item.click_url) ;
								var integralElement = _this.find('.integral') ;
								self.autoFillingValue(integralElement , taobaoke_item.integral) ;
								
							}
							
							if(settings.success){
								settings.success(taobaoke_item) ;
							}
						} , 
						error:function(error){
							//查询返利出错
							if(settings.error){
								settings.error(error) ;
							}
						}
					}) ;
				}
			}) ;
			
		} ,
		
		/**
		 * 点点折佣金计算
		 */
		calcDdzCommissions:function(taobaoke_item){
			var commission_rate = taobaoke_item.commission_rate ;
			var price = taobaoke_item.price ;
			if(taobaoke_item.prom_price && taobaoke_item.prom_price != null){
				price  = taobaoke_item.prom_price ;
			}
			
			var userRatePercent = (100 - taobaoDefaultRate - ddzEatRate) / 100.0 ;
			var userCommissionRate = taobaoke_item.commission_rate * userRatePercent ;
			
			var userCommission = price * (userCommissionRate / 10000.0) ;
			
			taobaoke_item.commission_rate = userCommissionRate ;
			taobaoke_item.commission = userCommission ;
			return taobaoke_item ;
		} ,
		
		/**
		 * BOPS佣金计算
		 */
		calcBopsDcomeCommissions:function(taobaoke_item){
			var commission_rate = taobaoke_item.commission_rate ;
			var price = taobaoke_item.price ;
			if(taobaoke_item.prom_price && taobaoke_item.prom_price != null){
				price  = taobaoke_item.prom_price ;
			}
			
			var commission = price * (commission_rate / 10000.0) ;
			
			taobaoke_item.commission = commission ;
			return taobaoke_item ;
		} ,
		
		/**
		 * 豆蔻积分计算
		 */
		calcDcomeCommissions:function(taobaoke_item){
			
			var commission_rate = taobaoke_item.commission_rate ;
			var price = taobaoke_item.price ;
			if(taobaoke_item.prom_price && taobaoke_item.prom_price != null){
				price  = taobaoke_item.prom_price ;
			}
			
			var userRatePercent = (100 - taobaoDefaultRate) / 100.0 ;
			var userCommissionRate = taobaoke_item.commission_rate * userRatePercent ;
			
			var userCommission =  price * (userCommissionRate / 10000.0) ; 
			
			var integralExchangeRate = 50 ;
			var maxIntegral = 500 ;
			var minIntegral = 100 ;
			var integral = parseInt( userCommission * integralExchangeRate ) ;
			
			if(integral > maxIntegral){
				integral = maxIntegral ;
			} else if(integral < minIntegral){
				integral = minIntegral ;
			}
			
			taobaoke_item.integral = integral ;
			
			return taobaoke_item ;
		} , 
		
		/**
		 * 组件转换商品折扣
		 */
		widgetConventItem:function(num_iids , outcode , settings ){
			var resultModel = {} ;
			TOP.api('rest', 'post',{
				method:'taobao.taobaoke.widget.items.convert',
				nick:'doucome2012',
				num_iids:num_iids,
				outer_code:outcode,
				fields:'track_iid,click_url,commission,commission_rate,pic_url,title,num_iid,seller_credit_score,price'
			},function(resp){
				
				if(resp.error_response){
					if(settings.error){
						settings.error(resp.error_response) ;
					}
					return ;
				}
				
				var totalResults = resp.total_results ;
				if(settings.success){
					settings.success(resp) ;
				}
			}) ;
			
		} ,
		
		autoFillingValue:function(element , newValue){
			if(newValue == null || typeof(newValue) == 'undefined'){
				return ;
			}
			element.each(function(){
				var _this = $(this) ;
				if(_this.is('input') || _this.is('textarea')){
					_this.val(newValue) ;
				} else if(_this.is('a')){
					_this.attr('href' , newValue) ;
				} else {
					_this.html(newValue) ;
				}
			}) ;
			
		} ,
		
		formatNumber:function(decimal , scale){
			decimal = Number(decimal) ;
			return decimal.toFixed(scale) ;
		} ,
		
		end:0
	};
	
	

	$(function(){
		taobaoWidget.init();
	});
})(jQuery);