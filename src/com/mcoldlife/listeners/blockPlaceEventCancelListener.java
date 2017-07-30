package com.mcoldlife.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;

public class blockPlaceEventCancelListener implements Listener{

	@EventHandler(priority=EventPriority.HIGHEST)
	public void placeCancelled(BlockPlaceEvent e){
		if(e.isCancelled()){
			ItemStack item = e.getItemInHand();
			item.setAmount(1);
			e.getPlayer().getInventory().addItem(item);
		}
	}
	
}
