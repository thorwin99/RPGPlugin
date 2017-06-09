package com.mcoldlife.ItemStacks;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.essentials.mcoldlife.main.Reference;
import com.mcoldlife.objects.RPGManager;
import com.mcoldlife.objects.RPPlayer;
import com.mcoldlife.rpg.lands;
import com.mcoldlife.rpg.pMsg;

public class PositionStickStack extends ItemStack {
	
	public PositionStickStack(){
		setType(Material.STICK);
		setAmount(1);
		ItemMeta meta = this.getItemMeta();
		
		meta.setDisplayName(ItemStackAttr.NAME_POS_STICK);
		meta.setLore(ItemStackAttr.LORE_POS_STICK);
		meta.addEnchant(Enchantment.DURABILITY, 1, true);
		meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
		meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		this.setItemMeta(meta);
	}
	
	/**Excecute's interact function
	 * @param e PlayerInteractEvent
	 */
	public static void interact(PlayerInteractEvent e){
		RPPlayer player = RPGManager.getPlayer(e.getPlayer());
		final String prefix = Reference.CHAT_PREFIX;
		switch(e.getAction()){
			case RIGHT_CLICK_BLOCK:
				if(lands.addPlotCorner(player, 0, e.getClickedBlock().getLocation())){
					e.getPlayer().sendMessage(prefix + pMsg.MSG_PLOT_POS_SET.replace("{pos}", "1"));
				}
				break;
			case LEFT_CLICK_BLOCK:
				if(lands.addPlotCorner(player, 1, e.getClickedBlock().getLocation())){
					e.getPlayer().sendMessage(prefix + pMsg.MSG_PLOT_POS_SET.replace("{pos}", "2"));
				}
				break;
			default:
				break;
		}
	}
	
}