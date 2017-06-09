package com.mcoldlife.objects.jobs;

import java.util.Arrays;
import java.util.List;

import org.bukkit.Material;

import com.mcoldlife.objects.OLJob;

public class Fischer extends OLJob{

	public static final String NAME = "Fischer";
	public static final List<Material> BUILD_BLOCKS = Arrays.asList();
	public static final List<Material> BREAK_BLOCKS = Arrays.asList();
	public static final List<Material> CRAFT_ITEMS = Arrays.asList(Material.FISHING_ROD);
	
	public Fischer() {
		super(NAME);
		create(BREAK_BLOCKS, BUILD_BLOCKS, CRAFT_ITEMS, true);
	}
	
}
