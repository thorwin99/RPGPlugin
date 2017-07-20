package com.mcoldlife.objects.jobs;

import java.util.Arrays;
import java.util.List;

import org.bukkit.Material;

import com.mcoldlife.objects.OLJob;

public class Alchemist extends OLJob{

	public static final String NAME = "Alchemist";
	public static final List<Material> BUILD_BLOCKS = Arrays.asList(new Material[0]);
	public static final List<Material> BREAK_BLOCKS = Arrays.asList(new Material[0]);
	public static final List<Material> CRAFT_ITEMS = Arrays.asList(Material.BREWING_STAND, Material.BREWING_STAND_ITEM, Material.SULPHUR);
	
	public Alchemist() {
		super(NAME);
		create(BREAK_BLOCKS, BUILD_BLOCKS, CRAFT_ITEMS, false);
	}

}
