package com.mcoldlife.listeners;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.mcoldlife.ItemStacks.ItemStackAttr;
import com.mcoldlife.objects.RPGManager;
import com.mcoldlife.objects.RPPlayer;

public class playerCraftEventListener implements Listener{

	@EventHandler
	public void onCraft(PrepareItemCraftEvent e){
		Player p = (Player) e.getViewers().get(0);
		RPPlayer rpp = RPGManager.getPlayer(p);
		ItemStack craftedItem = e.getRecipe().getResult();
		ItemMeta meta = craftedItem.getItemMeta();
		if(RPGManager.restrictedCraftItems.contains(craftedItem.getType())){
			if(rpp.get_job() == null){
				e.getInventory().setResult(new ItemStack(Material.AIR, 1));
				return;
			}
			if(!rpp.get_job().get_craftItems().contains(craftedItem.getType())){
				e.getInventory().setResult(new ItemStack(Material.AIR, 1));
				return;
			}else{
				checkSpecialItems(rpp, craftedItem, meta);
			}
		}
	}
	public void checkSpecialItems(RPPlayer p, ItemStack result, ItemMeta meta){
		
		switch(meta.getDisplayName()){
		case ItemStackAttr.NAME_NETHER_CRAFTER:
			break;
		}
	}
}
