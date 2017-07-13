package com.mcoldlife.listeners;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import com.mcoldlife.objects.OLChunk;
import com.mcoldlife.objects.OLCity;
import com.mcoldlife.objects.OLPlot;
import com.mcoldlife.objects.RPGManager;
import com.mcoldlife.objects.RPPlayer;
import com.mcoldlife.objects.Vector2D;
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
			if(chunk.getCity() != null){
				if(chunk.getCity() == player.get_city().getName()){
					OLCity city = player.get_city();
					Vector2D vec = new Vector2D(clickedBlock.getLocation());
					OLPlot plot =  city.inPlot(vec);
					if(city.getPlot(player) != null && plot != null && plot == city.getPlot(player)) {
						couldBreak(clickedBlock, player, e);
					}else{
						if(lands.isCityOnwer(player)){
							if(plot != null && !city.plotOwned(plot.getName())){
								couldBreak(clickedBlock, player, e);
							}else{
								e.setCancelled(true);
								return;
							}
						}else{
							e.setCancelled(true);
							return;
						}
					}
				}else{
					e.setCancelled(true);
					return;
				}
			}else{
				couldBreak(clickedBlock, player, e);
			}
		}
	}
	
	private void couldBreak(Block block, RPPlayer player, BlockBreakEvent e){
		
		if(RPGManager.restrictedBreakBlocks.contains(block.getType())){
			if(!player.get_job().containsBreakMaterial(block.getType())){
				e.setCancelled(true);
				return;
			}
		}
		
	}
}
