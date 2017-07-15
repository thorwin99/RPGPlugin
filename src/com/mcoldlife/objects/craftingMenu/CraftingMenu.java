package com.mcoldlife.objects.craftingMenu;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import com.mcoldlife.objects.RPPlayer;
import com.mcoldlife.rpg.reference;

public class CraftingMenu implements Listener{
	
	private Inventory _baseInventory;
	private String _title;
	private int _size;
	private List<CraftingMenuItemStack> _items;
	
	public CraftingMenu(String title, int rows){
		this._size = rows * 9;
		this._title = title;
		_baseInventory = Bukkit.getServer().createInventory(null, _size, _title);
		refreshInventory();
		Bukkit.getServer().getPluginManager().registerEvents(this, reference.PLUGIN_REFERENCE);
	}
	
	public CraftingMenu(String title, int rows, List<CraftingMenuItemStack> items){
		this._size = rows * 9;
		this._title = title;
		this._items = items;
		_baseInventory = Bukkit.getServer().createInventory(null, _size, _title);
		refreshInventory();
	}
	
	/**Show this craftingInventory to the Player
	 * @param p Player
	 */
	public void show(Player p){
		p.openInventory(_baseInventory);
	}
	
	/**Show this craftingInventory to the Player
	 * @param p Player
	 */
	public void show(RPPlayer p){
		this.show(p.getBukkitPlayer());
	}

	/**
	 * @return the items
	 */
	public List<CraftingMenuItemStack> getItems() {
		return _items;
	}

	/**
	 * @param _items the items to set for the view
	 */
	public void setItems(List<CraftingMenuItemStack> items) {
		this._items = items;
	}
	
	private void refreshInventory(){
		for(int i = 0; i < _size; i++){
			_baseInventory.addItem(_items.get(i));
		}
	}
	
	/**Crafts Selected Item
	 * @param p Player
	 * @param item Item to craft
	 */
	private void craftItem(Player p, CraftingMenuItemStack item){
		for(ItemStack i : item.getIngredients()){
			p.getInventory().remove(i);
			if(i.getType() == Material.LAVA_BUCKET || i.getType() == Material.WATER_BUCKET){
				p.getInventory().addItem(new ItemStack(Material.BUCKET, 1));
			}
		}
		p.getInventory().addItem(item);
		p.updateInventory();
		p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 0.75f, 0);
	}
	
	@EventHandler
	public void onItemClickEvent(InventoryClickEvent e){
		e.setCancelled(true);
		
		Player p = (Player) e.getWhoClicked();
		int slot = e.getSlot();
		CraftingMenuItemStack item = _items.get(slot);
		boolean allItems = true;
		for(ItemStack i : item.getIngredients()){
			if(!p.getInventory().containsAtLeast(i, i.getAmount())){
				allItems = false;
				break;
			}
		}
		if(allItems) craftItem(p, item);
	}
}
