package com.mcoldlife.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.player.PlayerDropItemEvent;

import com.mcoldlife.ItemStacks.ConquesterItemStack;

public class playerDropItemEventListener {
	
	@EventHandler(priority=EventPriority.HIGHEST)
	public void placeCancelled(PlayerDropItemEvent e){
		itemEvents(e);
	}
	
	private void itemEvents(PlayerDropItemEvent e) {
		
		switch(e.getItemDrop().getItemStack().getItemMeta().getDisplayName()) {
			case(ConquesterItemStack.NAME):
				e.setCancelled(true);
				e.getPlayer().getInventory().remove(new ConquesterItemStack());
				break;
			default:
				break;
		}
	}
}
