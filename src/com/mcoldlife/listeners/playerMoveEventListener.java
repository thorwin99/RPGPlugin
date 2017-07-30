package com.mcoldlife.listeners;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import com.mcoldlife.events.EnterCityEvent;
import com.mcoldlife.events.EnterLandEvent;
import com.mcoldlife.objects.OLChunk;
import com.mcoldlife.objects.RPGManager;
import com.mcoldlife.rpg.ChunkUtils;

public class playerMoveEventListener implements Listener{

	@EventHandler
	public void move(PlayerMoveEvent e){
		Player p = e.getPlayer();
		
		checkEnterCity(e, p);
		checkEnterLand(e, p);
	}

	private void checkEnterCity(PlayerMoveEvent e, Player p) {
		Location f = e.getFrom();
		Location t = e.getTo();
		
		if(f.getChunk() != t.getChunk()){

			String fId = ChunkUtils.generateId(f.getChunk());
			String tId = ChunkUtils.generateId(t.getChunk());
			
			if(!fId.equals(tId)){
				
				OLChunk tCh = new OLChunk(t.getChunk());
				if(tCh.getCity() != null){
					OLChunk fCh = new OLChunk(f.getChunk());
					if(fCh.getCity() != tCh.getCity()){
						
						Bukkit.getServer().getPluginManager().callEvent(new EnterCityEvent(RPGManager.getPlayer(p), RPGManager.getCity(tCh.getCity())));
					}
				}
				
			}
		}
	}
	
	private void checkEnterLand(PlayerMoveEvent e, Player p) {
		Location f = e.getFrom();
		Location t = e.getTo();
		
		if(f.getChunk() != t.getChunk()){

			String fId = ChunkUtils.generateId(f.getChunk());
			String tId = ChunkUtils.generateId(t.getChunk());
			
			if(!fId.equals(tId)){
				
				OLChunk tCh = new OLChunk(t.getChunk());
				if(tCh.getLand() != null){
					OLChunk fCh = new OLChunk(f.getChunk());
					if(fCh.getLand() != tCh.getLand()){
						
						Bukkit.getServer().getPluginManager().callEvent(new EnterLandEvent(RPGManager.getPlayer(p), RPGManager.getLand(tCh.getLand())));
						
					}
				}
				
			}
		}
	}
	
}
