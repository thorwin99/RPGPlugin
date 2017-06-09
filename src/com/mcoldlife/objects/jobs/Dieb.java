package com.mcoldlife.objects.jobs;

import java.util.Arrays;
import java.util.List;

import org.bukkit.Material;

import com.mcoldlife.objects.OLJob;

public class Dieb extends OLJob{

	public static final String NAME = "Dieb";
	public static final List<Material> BUILD_BLOCKS = Arrays.asList();
	public static final List<Material> BREAK_BLOCKS = Arrays.asList();
	public static final List<Material> CRAFT_ITEMS = Arrays.asList();
	
	public Dieb() {
		super(NAME);
		create(BREAK_BLOCKS, BUILD_BLOCKS, CRAFT_ITEMS, false);
	}

}
