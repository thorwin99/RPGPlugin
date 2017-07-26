package com.mcoldlife.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import com.mcoldlife.objects.RPGManager;
import com.mcoldlife.objects.RPPlayer;

public class joinEvent implements Listener{
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e){
		Player p = e.getPlayer();
		RPPlayer op = new RPPlayer(p);
		if(!RPGManager.getOnlinePlayers().containsKey(p.getUniqueId().toString())){
			RPGManager.getOnlinePlayers().put(p.getUniqueId().toString(), op);
		}
	}
	
}
