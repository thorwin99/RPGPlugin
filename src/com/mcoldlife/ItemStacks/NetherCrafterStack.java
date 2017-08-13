package com.mcoldlife.ItemStacks;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class NetherCrafterStack extends ItemStack {

	public static final String NAME = ItemStackAttr.NAME_NETHER_CRAFTER;
	
	public NetherCrafterStack(){
		this.setType(Material.BREWING_STAND_ITEM);
		this.setAmount(1);
		ItemMeta meta = this.getItemMeta();
		meta.setDisplayName(NAME);
		
		this.setItemMeta(meta);
	}
}
