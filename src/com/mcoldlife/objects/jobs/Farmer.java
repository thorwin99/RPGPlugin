package com.mcoldlife.objects.jobs;

import java.util.Arrays;
import java.util.List;

import org.bukkit.Material;

import com.mcoldlife.objects.OLJob;

public class Farmer extends OLJob{

	public static final String NAME = "Farmer";
	public static final List<Material> BUILD_BLOCKS = Arrays.asList(Material.WHEAT, Material.CARROT, Material.POTATO, Material.MELON, Material.MELON_SEEDS, Material.SEEDS, Material.PUMPKIN, Material.PUMPKIN_SEEDS, Material.BEETROOT_SEEDS);
	public static final List<Material> BREAK_BLOCKS = Arrays.asList(Material.WHEAT, Material.CARROT, Material.POTATO, Material.MELON, Material.MELON_SEEDS, Material.SEEDS, Material.PUMPKIN, Material.PUMPKIN_SEEDS, Material.BEETROOT_SEEDS);
	public static final List<Material> CRAFT_ITEMS = Arrays.asList(new Material[0]);
	
	public Farmer() {
		super(NAME);
		create(BREAK_BLOCKS, BUILD_BLOCKS, CRAFT_ITEMS, false);//TODO: Maybe change later
	}

}
