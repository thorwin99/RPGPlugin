package com.mcoldlife.listeners;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BrewingStand;
import org.bukkit.craftbukkit.v1_12_R1.block.CraftBrewingStand;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import com.mcoldlife.ItemStacks.ItemStackAttr;
import com.mcoldlife.items.Conquester;
import com.mcoldlife.items.NetherCrafter;
import com.mcoldlife.items.PositionStick;
import com.mcoldlife.objects.OLChunk;
import com.mcoldlife.objects.OLCity;
import com.mcoldlife.objects.OLPlot;
import com.mcoldlife.objects.RPGManager;
import com.mcoldlife.objects.RPPlayer;
import com.mcoldlife.objects.Vector2D;
import com.mcoldlife.rpg.ChunkUtils;

import net.minecraft.server.v1_12_R1.TileEntityBrewingStand;

public class playerInteractEvent implements Listener{

	@EventHandler
	public void onInteract(PlayerInteractEvent e){
		Player p = e.getPlayer();
		RPPlayer player = RPGManager.getPlayer(p);
		if(e.getClickedBlock() == null)return;

		OLChunk chunk = RPGManager.getChunk(ChunkUtils.generateId(e.getClickedBlock().getLocation().getChunk()));
		Block clickedBlock = e.getClickedBlock();
		ItemStack item = e.getItem();
		runItemEvents(item, e);//All Item interact events which aren't bound to restrictions go here

		//TODO Cancel only if restricted intractable
		if(!RPGManager.restrictedInteractItems.contains(clickedBlock.getType()) || clickedBlock.getType() == Material.BREWING_STAND)return;
		if(e.getAction() != Action.RIGHT_CLICK_BLOCK)return;
		if(player.getLand() == null){//TODO: is not important. only for name...
			e.setCancelled(true);
			return;
		}
		if(chunk.getLand() == player.getLand().getName()){
			if(chunk.getCity() != null){
				if(chunk.getCity().equals(player.get_city().getName())){
					OLCity city = RPGManager.getCity(chunk.getCity());
					OLPlot plot = city.inPlot((new Vector2D(p.getLocation())));
					if(plot != null){
						if(plot.getOwner() == p.getUniqueId()){//Player CAN Interact
							clickNetherCrafter(e, player, clickedBlock);
						}else{
							e.setCancelled(true);
							return;
						}
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
	}

	private void clickNetherCrafter(PlayerInteractEvent e, RPPlayer player, Block clickedBlock) {
		if(clickedBlock.getType() == Material.BREWING_STAND){
			TileEntityBrewingStand stand = ((CraftBrewingStand)((BrewingStand)clickedBlock.getState())).getTileEntity();
			if(stand.getName() == ItemStackAttr.NAME_NETHER_CRAFTER){
				NetherCrafter crafter = new NetherCrafter();
				if(crafter.isTrue(player.get_job())){
					crafter.interact(player, e.getAction(), null);
				}
			}
		}
	}

	/**Runs Item events
	 * @param item Item of playerInteractEvent
	 */
	private void runItemEvents(ItemStack item, PlayerInteractEvent e) {
		RPPlayer player = RPGManager.getPlayer(e.getPlayer());
		if(item == null)return;
		if(item.getItemMeta() == null)return;
		if(item.getItemMeta().getDisplayName() == null)return;
		switch(item.getItemMeta().getDisplayName()){
		case ItemStackAttr.NAME_POS_STICK:
			(new PositionStick()).interact(player, e.getAction(), e);
			break;
		case ItemStackAttr.NAME_CONQUESTER:
			(new Conquester()).interact(player, e.getAction(), e);
			break;
		default:
			break;
		
		}
	}
	
}
