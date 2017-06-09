package com.mcoldlife.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import com.mcoldlife.events.EnterCityEvent;
import com.mcoldlife.events.EnterLandEvent;
import com.mcoldlife.events.ExitCityEvent;
import com.mcoldlife.events.ExitLandEvent;
import com.mcoldlife.rpg.pMsg;

public class landEventListeners implements Listener {

	@EventHandler
	public void enterCity(EnterCityEvent e){
		Player p = e.getPlayer().getBukkitPlayer();
		p.sendTitle(pMsg.TITLE_ENTER_CITY, pMsg.STITLE_ENTER_CITY.replace("{city}", e.getCity().getName()), 10, 70, 20);
		
	}
	
	@EventHandler
	public void exitCity(ExitCityEvent e){
		
	}
	
	@EventHandler
	public void enterLand(EnterLandEvent e){
		Player p = e.getPlayer().getBukkitPlayer();
		p.sendTitle(pMsg.TITLE_ENTER_LAND, pMsg.STITLE_ENTER_LAND.replace("{land}", e.getLand().getName()), 10, 70, 20);
	}
	
	@EventHandler
	public void exitLand(ExitLandEvent e){
		
	}
}
