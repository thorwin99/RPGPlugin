package com.mcoldlife.interfaces;

import org.bukkit.event.block.Action;

import com.mcoldlife.objects.RPPlayer;

public interface OLItem {

	public boolean interact(RPPlayer p, Action action, Object argument);
	
	public void addRecipe();
}
