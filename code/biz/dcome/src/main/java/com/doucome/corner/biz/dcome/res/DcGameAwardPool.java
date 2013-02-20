package com.doucome.corner.biz.dcome.res;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.doucome.corner.biz.dcome.enums.DcGameAwardEnum;

/**
 * 
 * @author ze2200
 *
 */
public enum DcGameAwardPool {
	POOL;
	
	private List<DcGameAwardEnum> gameAwards = new ArrayList<DcGameAwardEnum>();
	
	private DcGameAwardPool() {
		gameAwards.add(DcGameAwardEnum.INTEGRAL);
		gameAwards.add(DcGameAwardEnum.WISH_STAR);
	}
	
	public DcGameAwardEnum getSmashEggAward() {
		Random rand = new Random(System.currentTimeMillis());
		int index = rand.nextInt(10);
		
		if (index < 8) {
			//70%¼¸ÂÊËÍ½ðµ°
			return gameAwards.get(1);
		} else {
			return gameAwards.get(0);
		}
	}
}
