package com.mcoldlife.items;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.block.Action;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;

import com.mcoldlife.ItemStacks.NetherCrafterStack;
import com.mcoldlife.interfaces.OLItem;
import com.mcoldlife.interfaces.conditional;
import com.mcoldlife.objects.OLJob;
import com.mcoldlife.objects.RPPlayer;
import com.mcoldlife.objects.craftingMenu.CraftingMenu;
import com.mcoldlife.objects.craftingMenu.CraftingMenuItemStack;
import com.mcoldlife.objects.jobs.Foerster;

public class NetherCrafter implements OLItem, conditional<OLJob>{

	private OLJob requiredJob = new Foerster();
	public static final String DNAME = NetherCrafterStack.NAME;
	private ItemStack itemStack = new NetherCrafterStack();
 	private static CraftingMenu craftingMenu;
	
 	public NetherCrafter(){
 		List<CraftingMenuItemStack> items = new ArrayList<>();
 		
 		items.add(new CraftingMenuItemStack(Material.NETHER_BRICK, "§cNether Brick", )));
 	}
 	
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

	/**
	 * @return the itemStack
	 */
	public ItemStack getItemStack() {
		return itemStack;
	}
}
