package com.mcoldlife.items;

import org.bukkit.event.block.Action;

import com.mcoldlife.interfaces.OLItem;
import com.mcoldlife.objects.RPPlayer;

public class Conquester implements OLItem{

	@Override
	public boolean interact(RPPlayer p, Action action, Object argument) {

		return false;
	}

	@Override
	public void addRecipe() {
		
	}

}
