package com.mcoldlife.items;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.block.Action;
import org.bukkit.inventory.ShapedRecipe;

import com.mcoldlife.ItemStacks.NetherCrafterStack;
import com.mcoldlife.interfaces.OLItem;
import com.mcoldlife.interfaces.conditional;
import com.mcoldlife.objects.OLJob;
import com.mcoldlife.objects.RPPlayer;
import com.mcoldlife.objects.jobs.Foerster;

public class NetherCrafter implements OLItem, conditional<OLJob>{

	OLJob requiredJob = new Foerster();
	
	
	@Override
	public boolean interact(RPPlayer p, Action action, Object argument) {
		
		return false;
	}

	@Override
	public boolean isTrue(OLJob argument) {
		
		if(argument.getName() == requiredJob.getName()){
			return true;
		}
		return false;
	}

	@Override
	public void addRecipe() {
		ShapedRecipe nc = new ShapedRecipe(new NetherCrafterStack());
		nc.shape("OOO", "SGS", "SLS");
		nc.setIngredient('L', Material.LAVA_BUCKET);
		nc.setIngredient('S', Material.STONE);
		nc.setIngredient('G', Material.GLASS);
		nc.setIngredient('O', Material.OBSIDIAN);
		Bukkit.getServer().addRecipe(nc);
	}
}
