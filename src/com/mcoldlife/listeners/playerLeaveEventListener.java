package com.mcoldlife.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import com.mcoldlife.objects.RPGManager;

public class playerLeaveEventListener implements Listener{

	@EventHandler
	public void onQuit(PlayerQuitEvent e){
		Player p = e.getPlayer();
		
		if(RPGManager.getOnlinePlayers().containsKey(p.getUniqueId().toString())){
			RPGManager.getOnlinePlayers().remove(p.getUniqueId().toString());
		}
	}
	
}
