package com.mcoldlife.items;

import org.bukkit.event.block.Action;
import org.bukkit.inventory.ItemStack;

import com.mcoldlife.ItemStacks.ConquesterItemStack;
import com.mcoldlife.interfaces.OLItem;
import com.mcoldlife.objects.RPPlayer;
import com.mcoldlife.rpg.lands;

public class Conquester implements OLItem{

	private ItemStack itemStack = new ConquesterItemStack();
	
	public Conquester() {
		
	}
	
	@Override
	public boolean interact(RPPlayer p, Action action, Object argument) {
		switch(action) {
		case LEFT_CLICK_BLOCK:
			//Claim for city
			lands.claimChunk(p);
			break;
		case RIGHT_CLICK_BLOCK:
			//Claim for land
			lands.contestChunk(p);
			break;
		default:
			break;
			
		}
		return false;
	}

	@Override
	public void addRecipe() {
		
	}

	public ItemStack getItemStack() {
		return itemStack;
	}

}
