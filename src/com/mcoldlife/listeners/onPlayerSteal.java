package com.mcoldlife.listeners;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;

import com.mcoldlife.events.PlayerStealEvent;
import com.mcoldlife.objects.RPPlayer;
import com.mcoldlife.rpg.pMsg;

public class onPlayerSteal implements Listener{
	
	@EventHandler
	public void stealPlayer(PlayerStealEvent e){
		RPPlayer a = e.getExecutor();
		RPPlayer b = e.getStolen();
		final String invName = pMsg.CTITLE_PLAYER_INVENTORY.replace("{player}", b.getBukkitPlayer().getDisplayName());
		
		ItemStack[] stolenInv = b.getBukkitPlayer().getInventory().getStorageContents();
		Inventory stealView = Bukkit.getServer().createInventory(b.getBukkitPlayer(), 27, invName);
		stealView.setContents(stolenInv);
		a.getBukkitPlayer().openInventory(stealView);
		b.getBukkitPlayer().playSound(b.getBukkitPlayer().getLocation(), Sound.ENTITY_GHAST_SCREAM, 10, 1);
		b.getBukkitPlayer().sendMessage(pMsg.MSG_EVENT_STOLEN);
	}
	
	@EventHandler
	public void onItemClick(InventoryClickEvent e){
		Player p = (Player) e.getWhoClicked();
		
		Inventory inv = e.getInventory();
		if(inv.getTitle().contains("§6Diebstahl")){
			if(e.getCurrentItem() != null){
				e.setCancelled(true);
				InventoryHolder holder = e.getInventory().getHolder();
				ItemStack item = e.getCurrentItem();
				if(holder instanceof Player){
					Player stolen = (Player) holder;
					Random rnd = new Random();
					int amount = rnd.nextInt(item.getAmount()/2);
					item.setAmount(amount);
					
					p.getInventory().addItem(item);
					
					stolen.getInventory().remove(item);
					
					p.closeInventory();
					p.setHealth(p.getHealth()/2);
				}
			}
		}
		
	}
	
}
