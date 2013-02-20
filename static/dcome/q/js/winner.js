!(function(){
	
	var envRoot = $("#envRoot").val() ;
	
	var _betIntegral = 5 ;
	
	var self ;
	
	var timer_winCurrentBetBox = null ;
	
	var index = {
				
		init:function(){		
			self = this ;
			self._initBetMoney() ;
			self._initBetStart() ;
			self._initGameRule() ;
			self.reset() ;
		},
		
		_initGameRule:function(){
			$(".winner-game .game-rule").html('&nbsp;') ;
		} ,
		
		/**
		 * 开始
		 */
		_initBetStart:function(){
			$('.bet-start-click').click(function(){
				if(self.isRunning()){
					return ;
				}
				
				var totalBetMoney = 0 ;
				var betParam = "" ;
				$(".bet-box .bet-btn").find('li').each(function(){
					var _this = $(this) ;
					var betCardName = _this.attr('data-bet') ;
					var betCount =  self.parseInt(_this.find(".bet-count span").html()) ;
					if(betCount > 0){
						betParam += betCardName + ":" + betCount + "," ;
					}
					totalBetMoney += betCount ;
				}) ;
				
				if(totalBetMoney <= 0){
					//('尚未下注，请先下注！');
					//出提示
					self.startGuide() ;
					return ;
				}
				
				if(self.isStartDisabled()){
					return ;
				}
				
				self.setRunning(true) ;
				self.disableStart() ;
				self.clearWinBetCard() ;
				
				
				//如果上次游戏结束后，直接按开始，扔按照上次的投注
				if($('.winner-game').attr('data-reset') == 'false'){
					var totalBetIntegral = _betIntegral * totalBetMoney ;
					if(!self.decrCreditCount(totalBetIntegral)){
						alert('积分不足') ;
					}
				}
				
				//先将获得的分数计算入积分
				self.setCreditCount(self.getWinScore() + self.getCreditCount()) ;
				self.setWinScore(0) ;
				
				var url = envRoot + '/frame/q/remote/rest/winner_game_start_ajax.htm' ;
				
				$.ajax({
		    		url : url ,
					type : "POST" ,
					data : {betParam:betParam},
					success : function(data){
						var json = data.json ;
						var code = json.code ;
						var data = json.data ;
						var detail = json.detail ;
						if(code == 'success'){
							var gameResult = data ;
							var cardName = gameResult.winCardName ;
							var winScore = gameResult.winScore ;
							var userIntegralCount = gameResult.userIntegralCount ;
							
							var startIndex = self.parseInt($('.play-box').attr('data-current-index')) ;
							var endIndex = self.getWinCardIndex(cardName) ;
							var stepCount = (endIndex - startIndex) > 0 ? endIndex - startIndex : 24 - startIndex + endIndex;
							var totalJumpCount = 24 * 4 + stepCount; ///计算总共需要跳多少格，至少转4圈
							$('.play-box').attr('data-current-index' , endIndex) ;
							
							//开始跑圈
							var config = {}  ;
							config.jumpIndex = 0 ;
							config.totalJumpCount = totalJumpCount ;
							config.showCardList = [startIndex] ;
							var timer = null ;
							var interval = 500 ;
							
							var lightGroup1 = $('.winner-game').find(".bet-btn .light-group1") ;
							var lightGroup2 = $('.winner-game').find(".bet-btn .light-group2") ;
							
							var timerDO = function() {
								//切换显示亮灯
								lightGroup1.removeClass('light-active') ;
								lightGroup2.removeClass('light-active') ;
								var rd = self.random(0,2) ;
								lightGroup1.eq(rd).addClass('light-active') ;
								rd = self.random(0,2) ;
								lightGroup2.eq(rd).addClass('light-active') ;
								self.calcIntervalAndShowCard(config);
								interval = config.interval ;
								self.setCurrentCard(config.showCardList) ;
					            config.jumpIndex ++;
					            if (config.jumpIndex >= config.totalJumpCount) {
					                clearTimeout(timer);
					                startIndex = endIndex ;
					                setTimeout(function() { 
					                	//出结果
					                	self.setCreditCount(userIntegralCount) ;
					                	self.setWinScore(winScore) ;
					                	
					                	self.setRunning(false) ;
					                	
					                	self.toggleWinBetCard(cardName) ;
					                	if(winScore <= 0){
					                		self.leiben() ;
					                	} else {
					                		
					                	}
					                	$('.winner-game').attr('data-reset','false') ;
					                	self.enableStart() ;
					                }, 200);
					            } else {
					                timer = setTimeout(timerDO, interval);
					            }
					        } ;
					        
					        //启动定时
					        timerDO() ;
							
						} else {
							if(code == 'ill_args') {
								if(detail == 'dcome.winner.config.error'){
									// 配置问题
									alert('配置异常,请稍后再试！') ;
								} else if(detail == 'dcome.winner.config.betParam.error') {
									alert('尚未下注，请先下注！') ;
								} else if(detail == 'dcome.winner.user.integral.notEnough'){
									alert('积分不足') ;
								} else if(detail == 'dcome.winner.play.limit'){
									alert('你今天已经玩了很多次了，休息下明天再来吧。') ;
								}						
							} else {
								alert('系统忙,请稍后再试!') ;
							}
							self.setRunning(false) ;
							self.enableStart() ;
						}
						
					} ,
					error : function(data){
						self.setRunning(false) ;
						self.enableStart() ;
					}
				});
				
				
				
			}) ;
			//end $('.bet-start-click').click(function(){
			var startCount = 0 ;
			var inPage_start_timer = setInterval(function(){
				var start = $(".winner-game .winner-start").find('a') ;
				if(start.hasClass('current')){
					start.removeClass('current') ;
				} else {
					start.addClass('current') ;
				}
				startCount ++ ;
				if(startCount > 4 ){
					clearInterval(inPage_start_timer) ;
					start.removeClass('current') ;
				}
				
			}, 100 );
			
			
			
		} ,
		
		/**
		 * 押注
		 */
		_initBetMoney:function(id){
			
			$(".bet-box .bet-btn").find('li').click(function(){
				var _this = $(this) ;
				if(self.isRunning()){
					return ;
				}
				if($('.winner-game').attr('data-reset') == 'false'){
					self.reset() ;
					$('.winner-game').removeAttr('data-reset') ;
				}
				self.enableStart() ;
				var bet = _this.attr('data-bet') ;
				var originBetCount =  self.parseInt(_this.find(".bet-count span").html()) ;
				var nextBetCount = originBetCount + 1 ;
				if(nextBetCount > 9){
					return ;
				}
				
				var totalCreditCount = self.getCreditCount() ;
				if(!self.decrCreditCount(_betIntegral)){
					return ;
				}
				
				_this.find(".bet-count span").html(nextBetCount) ;
			}) ;
		} ,
		
		/**
		 * 重置
		 */
		reset:function(){
			if(!self.isRunning()){
				self.disableStart() ;
				$('.winner-game .bet-box').find('li .bet-count').find('span').html('') ;
				self.clearWinBetCard() ;
				
			}
		} ,
		
		disableStart:function(){
			$('.bet-start-click').addClass('disable') ;
		} ,
		
		enableStart:function(){
			$('.bet-start-click').removeClass('disable') ;
		} ,
		
		isStartDisabled:function(){
			return $('.bet-start-click').hasClass('disable') ;
		} ,
		
		isRunning:function(){
			return $(".winner-game .play-box").attr('data-running') == 'true' ;
		} ,
		
		setRunning:function(isRun){
			if(isRun){
				$(".winner-game .play-box").attr('data-running' , 'true') ;
			} else {
				$(".winner-game .play-box").attr('data-running' , 'false') ;
			}
		} ,
		
		leiben:function(){
			var biaoqing = $(".winner-game .bet-box").find(".biaoqing") ;
			biaoqing.css('opacity','100') ;
			var leibenImg = biaoqing.attr('data-leiben') ;
			var img = biaoqing.find('img') ;
			img.css('left','100px') ;
			img.attr('src' , leibenImg) ;
			biaoqing.removeClass('dd-hide') ;
			img.animate({left: '0px'}, 2000, function(){
				biaoqing.animate({opacity:0},500 , function(){
					biaoqing.addClass('dd-hide') ;
				});
			});
		} ,
		
		clearWinBetCard:function(){
			if(timer_winCurrentBetBox != null){
				clearInterval(timer_winCurrentBetBox) ;
			}
			$('.winner-game .bet-btn').find('li').removeClass('current') ;
		} ,
		
		startGuide:function(){
			self.toggleWinBetCard('cattle');
			$('.winner-game .start-guide-dialog').removeClass('dd-hide') ;
			setTimeout(function(){
				
				self.clearWinBetCard() ;
				
				$('.winner-game .start-guide-dialog').animate({opacity:0} , 1000 , function(){
					$(this).addClass('dd-hide') ;
					$(this).css('opacity' , 100) ;
				}) ;
				
			} , 1000) ;
			
			
		} ,
		
		toggleWinBetCard:function(cardName){
			self.clearWinBetCard() ;
			var cardElement = null ;
			$('.winner-game .bet-btn').find('li').each(function(){
				if($(this).attr('data-bet') == cardName){
					cardElement = $(this) ;
				}
			}) ;
			
			if(cardElement == null){
				return ;
			}
			
			timer_winCurrentBetBox = setInterval(function(){
				if(cardElement.hasClass('current')){
					cardElement.removeClass('current') ;
				} else {
					cardElement.addClass('current') ;
				}
			} , 50) ;
		} ,
		
		/**
		 * 
		 */
		setCurrentCard:function(indexList){
			if(indexList.length == 0){
				return ;
			}
			var cardList = $('.play-box li') ;
			cardList.find('div').removeClass('current') ;
			for(var i=0 ;i<indexList.length ;i++){
				var currentIndex = indexList[i] - 1;
				cardList.eq(currentIndex).find('div').addClass('current') ;
			}
		} ,
		
		/**
		 * 计算转动时定时的时长和显示的方框
		 * @config.jumpIndex : index
		 * @config.totalJumpCount : 总共要跳的格子数
		 * @config.showCardList : in , out 
		 */
		calcIntervalAndShowCard:function(config){
			
			var jumpIndex = config.jumpIndex ;
			var totalJumpCount = config.totalJumpCount ;
			var showCardList = config.showCardList ;
			
			var i,
            len = showCardList.length,
            jumpmax = totalJumpCount ;
	        switch (jumpIndex) {
	            case 0:
	                var v = showCardList[0] + 1;
	                showCardList.length = 0;
	                v = v > 24 ? v - 24 : v;
	                showCardList[0] = v;
	                config.interval = 400;
	                break ;
	            case 1:
	                if (len == 1) {
	                    var v = showCardList[0] + 1;
	                    v = v > 24 ? v - 24 : v;
	                    showCardList.push(v);
	                }
	                config.interval = 350;
	                break ;
	            case 2:
	                if (len == 2) {
	                    var v = showCardList[1] + 1;
	                    v = v > 24 ? v - 24 : v;
	                    showCardList.push(v);
	                }
	                config.interval = 300;
	                break ;
	            case 3:
	                if (len == 3) {
	                    var v = showCardList[2] + 1;
	                    v = v > 24 ? v - 24 : v;
	                    showCardList.push(v);
	                }
	                config.interval = 200;
	                break ;
	            case jumpmax - 1:
	                var v = showCardList[0] + 1;
	                showCardList.length = 0;
	                v = v > 24 ? v - 24 : v;
	                showCardList[0] = v;
	                config.interval = 800;
	                break ;
	            case jumpmax - 2:
	                var v = showCardList[0] + 1;
	                showCardList.length = 0;
	                v = v > 24 ? v - 24 : v;
	                showCardList[0] = v;
	                config.interval = 700;
	                break ;
	            case jumpmax - 3:
	                var v = showCardList[0] + 1;
	                showCardList.length = 0;
	                v = v > 24 ? v - 24 : v;
	                showCardList[0] = v;
	                config.interval = 600;
	                break ;
	            case jumpmax - 4:
	                var v = showCardList[0] + 1;
	                showCardList.length = 0;
	                v = v > 24 ? v - 24 : v;
	                showCardList[0] = v;
	                config.interval = 400;
	                break ;
	            case jumpmax - 5:
	                var v = showCardList[0] + 1;
	                showCardList.length = 0;
	                v = v > 24 ? v - 24 : v;
	                showCardList[0] = v;
	                config.interval = 300;
	                break ;
	            case jumpmax - 6:
	                var v = showCardList[1] + 1;
	                showCardList.length = 0;
	                v = v > 24 ? v - 24 : v;
	                showCardList[0] = v;
	                config.interval = 200;
	                break ;
	            case jumpmax - 7:
	                var v1 = showCardList[1] + 1;
	                var v2 = showCardList[2] + 1;
	                showCardList.length = 0;
	                v1 = v1 > 24 ? v1 - 24 : v1;
	                v2 = v2 > 24 ? v2 - 24 : v2;
	                showCardList[0] = v1;
	                showCardList[1] = v2;
	                config.interval = 100;
	                break ;
	            case jumpmax - 8:
	                var v1 = showCardList[1] + 1;
	                var v2 = showCardList[2] + 1;
	                var v3 = showCardList[3] + 1;
	                showCardList.length = 0;
	                v1 = v1 > 24 ? v1 - 24 : v1;
	                v2 = v2 > 24 ? v2 - 24 : v2;
	                v3 = v3 > 24 ? v3 - 24 : v3;
	                showCardList[0] = v1;
	                showCardList[1] = v2;
	                showCardList[2] = v3;
	                config.interval = 50;
	                break ;
	            default:
	            	{
		                for (i = 0; i < len; i++) {
		                	showCardList[i]++;
		                    if (showCardList[i] > 24) {
		                    	showCardList[i] -= 24;
		                    }
		                }
		            	config.interval = 30;
	            	}
	            break ;
	        }
	        //end switch
		} ,
		
		/**
		 * credit总数
		 */
		getCreditCount:function(){
			var credit = $(".bet-box .credit-count") ;
			var creditCount = credit.find("span").html() ;
			return self.parseInt(creditCount) ;
		} ,
		
		decrCreditCount:function(count){
			var creditCount = self.getCreditCount() ;
			if(creditCount < count){
				return false ;
			}
			creditCount = creditCount - count ;
			self.setCreditCount(creditCount) ;
			return true ;
		} ,
		
		setCreditCount:function(count){
			var credit = $(".bet-box .credit-count") ;
			var currentCreditCount = self.parseInt(credit.find("span").html()) ;
			var newCreditCount = self.parseInt(count) ;
			var modifyCount = newCreditCount - currentCreditCount ;  
			if(modifyCount != 0){
				var modifyTipStr = modifyCount > 0 ? "+" + modifyCount : '' + modifyCount;
				credit.find("span").html(count) ;
				var addStyle = modifyCount > 0 ? 'integral-tip-add' : '' ;
				//动画效果
				var animateHtml = $('<div class="integral-tip ' + addStyle + '">' + modifyTipStr + '</div>') ;
				animateHtml.appendTo($('.winner-game .score-box')) ;
				var left = animateHtml.css('left') ;
				left = self.parseInt(left.replace('px','')) + 30;
				animateHtml.animate({opacity:0,left:left + 'px'} , 2000 , function(){
					animateHtml.remove() ;
				});
			}
			return true ;
		} ,
		
		setWinScore:function(count){
			var winScore = $(".bet-box .bonus-win-count") ;
			var oldCount = self.parseInt(winScore.find('span').html()) ;
			winScore.find('span').html(count) ;
			return true ;
		} ,
		
		getWinScore:function(){
			var winScore = $(".bet-box .bonus-win-count") ;
			return self.parseInt(winScore.find('span').html()) ;
		} ,
		
		/**
		 * 根据中奖，显示对应的选中
		 */
		getWinCardIndex:function(cardName){
			var idx = 2 ;
			if(cardName == 'barbarbar'){
				idx = 24 ;
			} else if(cardName == 'barbar'){
				idx = 23 ;
			} else if(cardName == 'sevenseven') {
				idx = 12 ;
			} else if(cardName == 'starstar') {
				idx = 16 ;
			} else if(cardName == 'watermelons') {
				idx = 4 ;
			} else if(cardName == 'alarm') {
				// 铃铛
	            var ar = self.random(1, 2);
	            switch (ar) {
	                case 1:
	                    idx = 22;
	                    break;
	                case 2:
	                	idx = 10;
	                    break;
	            }
			} else if(cardName == 'coconut') {
				//椰子
				var ar = self.random(1, 2);
	            switch (ar) {
	                case 1:
	                	idx = 3;
	                    break;
	                case 2:
	                	idx = 15;
	                    break;
	            }
			} else if(cardName == 'orange'){
				// 大桔子1 10/100
	            var ar = self.random(1, 2);
	            switch (ar) {
	                case 1:
	                	idx = 21;
	                    break;
	                case 2:
	                	idx = 9;
	                    break;
	            }
			}else if(cardName == 'apple') {
				var ar = self.random(1, 4);
	            switch (ar) {
	                case 1:
	                	idx = 1;
	                    break;
	                case 2:
	                	idx = 7;
	                    break;
	                case 3:
	                	idx = 13;
	                    break;
	                case 4:
	                	idx = 19;
	                    break;
	            }
			} else if(cardName == 'oncemore'){
				var ar = self.random(1, 2);
	            switch (ar) {
	                case 1:
	                	idx = 6;
	                    break;
	                case 2:
	                	idx = 18;
	                    break;
	            }
			} else {
				var ar = self.random(1, 7);
	            switch (ar) {
	                case 1:
	                	idx = 2;
	                    break;
	                case 2:
	                	idx = 5;
	                    break;
	                case 3:
	                	idx = 8;
	                    break;
	                case 4:
	                	idx = 11;
	                    break;
	                case 5:
	                	idx = 14;
	                    break;
	                case 6:
	                	idx = 17;
	                    break;
	                case 7:
	                	idx = 20;
	                    break;
	            }
			}
			
			return idx  ;
			
		} ,
		
		/**
		 * 生成随机数
		 */
	    random:function(min, max) {
	        return self.parseInt(Math.random() * (max - min + 1) + min);
	    } ,
	    
	    parseInt:function(num) {
	    	if(num == ''){
	    		return 0 ;
	    	}
	    	
	    	if(isNaN(num)){
	    		return 0 ;
	    	}
	    	return parseInt(num) ;
	    } ,
		
		end:0
	};

	$(function(){
		index.init();
	});
})();