package com.mcoldlife.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import com.mcoldlife.rpg.rpg;

public class leaveEvent implements Listener{

	@EventHandler
	public void onQuit(PlayerQuitEvent e){
		Player p = e.getPlayer();
		
		if(rpg.getRPGManager().getOnlinePlayers().containsKey(p.getUniqueId().toString())){
			rpg.getRPGManager().getOnlinePlayers().remove(p.getUniqueId().toString());
		}
	}
	
}
