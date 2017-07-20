package com.mcoldlife.items;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
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
import com.mcoldlife.rpg.reference;

public class NetherCrafter implements OLItem, conditional<OLJob>{

	private OLJob requiredJob = new Foerster();
	public static final String DNAME = NetherCrafterStack.NAME;
	private ItemStack itemStack = new NetherCrafterStack();
 	private static CraftingMenu craftingMenu;
	
 	public NetherCrafter(){
 		List<CraftingMenuItemStack> items = new ArrayList<>();
 
 		items.add(new CraftingMenuItemStack(Material.NETHER_BRICK_ITEM, 1,"§cNether Brick", Arrays.asList(new ItemStack(Material.CLAY_BRICK, 1), new ItemStack(Material.LAVA_BUCKET, 1))));
 		items.add(new CraftingMenuItemStack(Material.NETHER_BRICK, 1,"§cNether Brick Block", Arrays.asList(new ItemStack(Material.NETHER_BRICK_ITEM, 4))));
 		items.add(new CraftingMenuItemStack(Material.NETHER_BRICK_STAIRS, 4,"§cNether Brick Stairs", Arrays.asList(new ItemStack(Material.NETHER_BRICK, 6))));
 		items.add(new CraftingMenuItemStack(Material.NETHER_FENCE, 4,"§cNether Fence", Arrays.asList(new ItemStack(Material.NETHER_BRICK_ITEM, 6))));
 		items.add(new CraftingMenuItemStack(Material.NETHER_STALK, 4,"§cNether Warts", Arrays.asList(new ItemStack(Material.SEEDS, 2), new ItemStack(Material.LAVA_BUCKET, 1))));
 		items.add(new CraftingMenuItemStack(Material.SOUL_SAND, 1,"§cSoul Sand", Arrays.asList(new ItemStack(Material.SAND, 1), new ItemStack(Material.BONE, 2))));
 		items.add(new CraftingMenuItemStack(Material.BLAZE_ROD, 1,"§cBlaze Rod", Arrays.asList(new ItemStack(Material.STICK, 2), new ItemStack(Material.LAVA_BUCKET, 1))));
 		items.add(new CraftingMenuItemStack(Material.NETHER_STAR, 1,"§cNether Star", Arrays.asList(new ItemStack(Material.DIAMOND, 32), new ItemStack(Material.BLAZE_POWDER, 5))));
 		items.add(new CraftingMenuItemStack(Material.QUARTZ_ORE, 1,"§cNether Quartz Ore", Arrays.asList(new ItemStack(Material.WATER_BUCKET, 1), new ItemStack(Material.LAVA_BUCKET, 1))));
 	
 		craftingMenu = new CraftingMenu(DNAME, 1, items);
 	}
 	
 	/**Shows crafting View to player
 	 * @param p Player
 	 */
 	public void showCraftingView(Player p){
 		craftingMenu.show(p);
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
		ShapedRecipe nc = new ShapedRecipe(new NamespacedKey(reference.PLUGIN_REFERENCE, "NETHER_CRAFTER"), new NetherCrafterStack());
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
