package com.mcoldlife.objects.jobs;

import java.util.Arrays;
import java.util.List;

import org.bukkit.Material;

import com.mcoldlife.objects.OLJob;

public class Miner extends OLJob{

	public static final String NAME = "Miner";
	public static final List<Material> BUILD_BLOCKS = Arrays.asList();
	public static final List<Material> BREAK_BLOCKS = Arrays.asList(Material.COAL_ORE, Material.DIAMOND_ORE, Material.EMERALD_ORE, Material.GOLD_ORE, Material.IRON_ORE, Material.GLOWING_REDSTONE_ORE, Material.REDSTONE_ORE, Material.LAPIS_ORE, Material.QUARTZ_ORE);
	public static final List<Material> CRAFT_ITEMS = Arrays.asList();
	
	public Miner() {
		super(NAME);
		create(BREAK_BLOCKS, BUILD_BLOCKS, CRAFT_ITEMS, true);
	}

	
	
}
