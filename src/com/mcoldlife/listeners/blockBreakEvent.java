package com.mcoldlife.listeners;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import com.mcoldlife.objects.OLChunk;
import com.mcoldlife.objects.RPGManager;
import com.mcoldlife.objects.RPPlayer;
import com.mcoldlife.rpg.ChunkUtils;
import com.mcoldlife.rpg.lands;

public class blockBreakEvent implements Listener{

	
	public void onBlockBreak(BlockBreakEvent e){
		Player p = e.getPlayer();
		RPPlayer player = RPGManager.getPlayer(p);
		OLChunk chunk = RPGManager.getChunk(ChunkUtils.generateId(e.getBlock().getLocation().getChunk()));
		Block clickedBlock = e.getBlock();
		if(player.getLand() == null){
			e.setCancelled(true);
			return;
		}
		if(player.getLand().getName() == chunk.getLand()){
			if(lands.isLandOnwer(player)){
				if(player.get_city() == null){
					
				}
				
				if(chunk.getCity() == player.get_city())
			}else{
				
			}
		}
	}
}
