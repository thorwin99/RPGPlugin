package com.mcoldlife.ItemStacks;

import java.util.List;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ConquesterItemStack extends ItemStack {

	public static final String NAME = ItemStackAttr.NAME_CONQUESTER;
	public static final List<String> LORE = ItemStackAttr.LORE_CONQUESTER;
	
	public ConquesterItemStack() {
		this.setType(Material.BLAZE_ROD);
		this.setAmount(1);
		ItemMeta meta  = this.getItemMeta();
		meta.setDisplayName(NAME);
		meta.setLore(LORE);
		this.setItemMeta(meta);
	}
	
}
