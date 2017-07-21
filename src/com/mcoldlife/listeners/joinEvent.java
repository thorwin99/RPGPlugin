package com.mcoldlife.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import com.mcoldlife.objects.RPPlayer;
import com.mcoldlife.rpg.rpg;

public class joinEvent implements Listener{
	
	@EventHandler(priority=EventPriority.HIGHEST)
	public void onjoin(PlayerJoinEvent e){
		Player p = e.getPlayer();
		RPPlayer op = new RPPlayer(p);
		System.out.println("Player" + p.getName() + "joined");
		if(!rpg.getRPGManager().getOnlinePlayers().containsKey(p.getUniqueId().toString())){
			rpg.getRPGManager().getOnlinePlayers().put(p.getUniqueId().toString(), op);
		}
	}
	
}
