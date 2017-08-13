package com.mcoldlife.items;

import org.bukkit.event.block.Action;

import com.mcoldlife.interfaces.OLItem;
import com.mcoldlife.objects.RPPlayer;

public class Conquester implements OLItem{

	@Override
	public boolean interact(RPPlayer p, Action action, Object argument) {
		switch(action) {
		case LEFT_CLICK_BLOCK:
			//Claim for city
			break;
		case RIGHT_CLICK_BLOCK:
			//Claim for land
			break;
		default:
			break;
			
		}
		return false;
	}

	@Override
	public void addRecipe() {
		
	}

}
