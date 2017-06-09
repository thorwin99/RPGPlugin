package com.mcoldlife.objects.jobs;

import java.util.Arrays;
import java.util.List;

import org.bukkit.Material;

import com.mcoldlife.objects.OLJob;

public class Weber extends OLJob{

	public static final String NAME = "Weber";
	public static final List<Material> BUILD_BLOCKS = Arrays.asList();
	public static final List<Material> BREAK_BLOCKS = Arrays.asList(Material.WOOL);
	public static final List<Material> CRAFT_ITEMS = Arrays.asList(Material.WOOL, Material.BED, Material.BANNER, Material.CARPET, Material.LEATHER_BOOTS, Material.LEATHER_CHESTPLATE, Material.LEATHER_HELMET, Material.LEATHER_LEGGINGS);
	
	public Weber() {
		super(NAME);
		create(BREAK_BLOCKS, BUILD_BLOCKS, CRAFT_ITEMS, true);
	}

	
	
}
