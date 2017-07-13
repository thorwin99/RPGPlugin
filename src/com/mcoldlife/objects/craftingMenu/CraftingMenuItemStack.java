package com.mcoldlife.objects.craftingMenu;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class CraftingMenuItemStack extends ItemStack{

	private String _displayName;
	private List<ItemStack> _ingredients;
	
	public CraftingMenuItemStack(String displayName, List<ItemStack> ingredients) {
		super();
		this._ingredients = ingredients;
		this._displayName = displayName;
		setMeta();
	}

	public CraftingMenuItemStack(ItemStack stack, String displayName, List<ItemStack> ingredients) throws IllegalArgumentException {
		super(stack);
		this._ingredients = ingredients;
		this._displayName = displayName;
		setMeta();
	}

	public CraftingMenuItemStack(Material type, int amount, short damage, String displayName, List<ItemStack> ingredients) {
		super(type, amount, damage);
		this._ingredients = ingredients;
		this._displayName = displayName;
		setMeta();
	}

	public CraftingMenuItemStack(Material type, int amount, String displayName, List<ItemStack> ingredients) {
		super(type, amount);
		this._ingredients = ingredients;
		this._displayName = displayName;
		setMeta();
	}

	public CraftingMenuItemStack(Material type, String displayName, List<ItemStack> ingredients) {
		super(type);
		this._ingredients = ingredients;
		this._displayName = displayName;
		setMeta();
	}

	/**
	 * @return the Ingredients
	 */
	public List<ItemStack> getIngredients() {
		return _ingredients;
	}

	/**
	 * @return the displayName
	 */
	public String getDisplayName() {
		return _displayName;
	}
	
	private void setMeta(){
		ItemMeta meta = this.getItemMeta();
		List<String> lore = new ArrayList<>();
		
		meta.setDisplayName(_displayName);
		
		for(ItemStack i : _ingredients){
			lore.add("§8" + i.getAmount() + " " + i.getItemMeta().getDisplayName());
		}
		meta.setLore(lore);
		this.setItemMeta(meta);
	}
}
