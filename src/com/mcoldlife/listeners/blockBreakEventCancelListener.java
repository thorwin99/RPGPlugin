package com.mcoldlife.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import com.mcoldlife.rpg.Permissions;
import com.mcoldlife.rpg.reference;

public class blockBreakEventCancelListener implements Listener{

	@EventHandler(priority=EventPriority.HIGHEST)
	public void breakCancelled(BlockBreakEvent e){
		if(e.isCancelled()){
			if(e.getPlayer().hasPermission(Permissions.PERM_BUILD_ANYWHERE) && e.getPlayer().isOp() && reference.B_ENABLED_PERM_BUILD_ANYWHERE) {
				e.setCancelled(false);
				return;
			}
		}
	}
}
