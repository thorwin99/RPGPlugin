package com.mcoldlife.objects.jobs;

import java.util.Arrays;
import java.util.List;

import org.bukkit.Material;

import com.mcoldlife.objects.OLJob;

public class Foerster extends OLJob{

	public static final String NAME = "Foerster";
	public static final List<Material> BUILD_BLOCKS = Arrays.asList(Material.LEAVES, Material.LEAVES_2, Material.SAPLING);
	public static final List<Material> BREAK_BLOCKS = Arrays.asList(Material.LOG, Material.LOG_2, Material.SAPLING);
	public static final List<Material> CRAFT_ITEMS = Arrays.asList(new Material[0]);
	
	public Foerster() {
		super(NAME);
		this.create(BREAK_BLOCKS, BUILD_BLOCKS, CRAFT_ITEMS, true);
	}

}
