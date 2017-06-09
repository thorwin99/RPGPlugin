package com.mcoldlife.objects.jobs;

import java.util.Arrays;
import java.util.List;

import org.bukkit.Material;

import com.mcoldlife.objects.OLJob;

public class Schmied extends OLJob{

	public static final String NAME = "Schmied";
	public static final List<Material> BUILD_BLOCKS = Arrays.asList();
	public static final List<Material> BREAK_BLOCKS = Arrays.asList();
	public static final List<Material> CRAFT_ITEMS = Arrays.asList(Material.IRON_AXE, Material.IRON_BLOCK, Material.IRON_DOOR, Material.IRON_FENCE, Material.IRON_HOE, Material.IRON_PICKAXE, Material.IRON_SPADE, Material.IRON_SWORD, Material.IRON_HELMET, Material.IRON_CHESTPLATE, Material.IRON_LEGGINGS, Material.IRON_BOOTS, Material.IRON_TRAPDOOR, Material.IRON_BARDING, Material.DIAMOND_AXE, Material.DIAMOND_PICKAXE, Material.DIAMOND_HOE, Material.DIAMOND_BLOCK, Material.DIAMOND_SPADE, Material.DIAMOND_SWORD, Material.DIAMOND_BLOCK, Material.DIAMOND_HELMET, Material.DIAMOND_CHESTPLATE, Material.DIAMOND_LEGGINGS, Material.DIAMOND_BOOTS, Material.GOLD_AXE, Material.GOLD_PICKAXE, Material.GOLD_SPADE, Material.GOLD_HOE, Material.GOLD_SWORD, Material.GOLD_HELMET, Material.GOLD_CHESTPLATE, Material.GOLD_LEGGINGS, Material.GOLD_BOOTS, Material.GOLD_BARDING, Material.GOLD_BLOCK, Material.ANVIL, Material.CHAINMAIL_BOOTS, Material.CHAINMAIL_CHESTPLATE, Material.CHAINMAIL_HELMET, Material.CHAINMAIL_LEGGINGS, Material.SHIELD, Material.SHEARS, Material.SADDLE);
	
	public Schmied() {
		super(NAME);
		create(BREAK_BLOCKS, BUILD_BLOCKS, CRAFT_ITEMS, true);
	}

	
	
}
