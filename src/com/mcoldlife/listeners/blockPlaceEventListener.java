package com.mcoldlife.listeners;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

import com.mcoldlife.objects.OLChunk;
import com.mcoldlife.objects.OLCity;
import com.mcoldlife.objects.OLPlot;
import com.mcoldlife.objects.RPGManager;
import com.mcoldlife.objects.RPPlayer;
import com.mcoldlife.objects.Vector2D;
import com.mcoldlife.rpg.ChunkUtils;
import com.mcoldlife.rpg.lands;

public class blockPlaceEventListener implements Listener{

	@EventHandler(priority=EventPriority.HIGHEST)
	public void onBlockPlace(BlockPlaceEvent e){
		Player p = e.getPlayer();
		RPPlayer player = new RPPlayer(p);
		OLChunk chunk = RPGManager.getChunk(ChunkUtils.generateId(e.getBlock().getLocation().getChunk()));
		Block clickedBlock = e.getBlock();
		if(player.getLand() == null){
			e.setCancelled(true);
			return;
		}
		if(player.getLand().getName().equals(chunk.getLand())){
			if(chunk.getCity() != null){
				if(player.get_city().getName().equals(chunk.getCity())){
					OLCity city = player.get_city();
					Vector2D vec = new Vector2D(clickedBlock.getLocation());
					OLPlot plot =  city.inPlot(vec);
					if(city.getPlot(player) != null && plot != null && plot == city.getPlot(player)) {
						couldBuildRestricted(clickedBlock, player, e);
					}else{
						if(lands.isCityOnwer(player)){
							if(plot != null && !city.plotOwned(plot.getName())){
								couldBuildRestricted(clickedBlock, player, e);
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
				couldBuildUnrestricted(clickedBlock, player, e);
			}
		}else {
			e.setCancelled(true);
			return;
		}
	}
	
	private void couldBuildUnrestricted(Block block, RPPlayer player, BlockPlaceEvent e){
		if(!player.get_job().containsBuildMaterial(block.getType())){
			e.setCancelled(true);
		}
	}
	
	private void couldBuildRestricted(Block block, RPPlayer player, BlockPlaceEvent e){
		if(RPGManager.restrictedBuildBlocks.contains(block.getType())){
			couldBuildUnrestricted(block, player, e);
		}
	}
}
