package com.mcoldlife.rpg;

import java.util.Arrays;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class utils {

	public static boolean searchInventoryForItemStack(ItemStack s, Player p){
		ItemStack[] invContent = p.getInventory().getContents();
		boolean returnvar = false;
		for(ItemStack stack : invContent){
			if(stack != null){
				if(stack.getType() == s.getType()){
					if(stack.getAmount() >= s.getAmount()){
						returnvar = true;
						break;
					}
				}
			}
		}
		
		
		return returnvar;
	}
	public static void removeItem(ItemStack s, Player p){
		for(int i = 0; i<p.getInventory().getSize(); i ++){
			ItemStack stack = p.getInventory().getItem(i);
			if(stack != null){
				if(stack.getType() == s.getType()){
					if(stack.getAmount() >= s.getAmount()){
						stack.setAmount(stack.getAmount() - s.getAmount());
						p.getInventory().setItem(i, stack);
						break;
					}
				}
			}
		}
	}
	public static boolean arrayContains(Object[] array, Object item){
		boolean returnvar = false;
		
		for(Object o : array){
			if(o.equals(item)){
				if(o == item){
					returnvar = true;
					break;
				}
			}
		}
		return returnvar;
		
	}
	public static Material[] stringArrayToMaterials(String[] array){
		Material[] arr = new Material[array.length];
		int index = 0;
		for(String m : array){
			arr[index] = Material.getMaterial(m);
			index ++;
			
		}
		
		return arr;
	}
	public static String[] objectToString(Object[] o){
		String[] stringArray = Arrays.copyOf(o, o.length, String[].class);
		return stringArray;
	}

	
}
