package com.mcoldlife.items;

import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import com.mcoldlife.ItemStacks.PositionStickStack;
import com.mcoldlife.interfaces.OLItem;
import com.mcoldlife.objects.RPPlayer;

public class PositionStick implements OLItem{

	PositionStickStack itemStack;
	
	public PositionStick(){
		
		itemStack = new PositionStickStack();
		
	}
	
	@Override
	public boolean interact(RPPlayer p, Action action, Object argument) {
		if(argument instanceof PlayerInteractEvent){
			PlayerInteractEvent e = (PlayerInteractEvent) argument;
			PositionStickStack.interact(e);
			return false;
		}
		return true;
	}

	/**
	 * @return the itemStack
	 */
	public PositionStickStack getItemStack() {
		return itemStack;
	}

	@Override
	public void addRecipe() {
		// TODO Auto-generated method stub
		
	}

}
