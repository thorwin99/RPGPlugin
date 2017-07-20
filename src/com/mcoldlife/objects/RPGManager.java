package com.mcoldlife.objects;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.entity.Player;

public class RPGManager {
	
	public static HashMap<String, OLChunk> chunks = new HashMap<>();;
	public static HashMap<String, OLLand> lands = new HashMap<>();
	public static HashMap<String, OLCity> citys = new HashMap<>();
	public static HashMap<String, RPPlayer> onlinePlayers = new HashMap<>();
	public static HashMap<String, OLJob> jobs = new HashMap<>();
	public static List<Material> restrictedBreakBlocks = new LinkedList<>();
	public static List<Material> restrictedBuildBlocks = new LinkedList<>();
	public static List<Material> restrictedCraftItems = new LinkedList<>(Arrays.asList(Material.NETHER_BRICK, Material.NETHER_BRICK_STAIRS, Material.NETHER_BRICK_ITEM, Material.NETHER_FENCE));
	
	public RPGManager(){
		
	}

	/**
	 * @return the chunks
	 */
	public HashMap<String, OLChunk> getChunks() {
		return chunks;
	}

	/**
	 * @return the lands
	 */
	public HashMap<String, OLLand> getLands() {
		return lands;
	}

	/**
	 * @return the city's
	 */
	public HashMap<String, OLCity> getCitys() {
		return citys;
	}

	/**
	 * @return the onlinePlayers
	 */
	public HashMap<String, RPPlayer> getOnlinePlayers() {
		return onlinePlayers;
	}
	
	/**Gets an Online RPPlayer
	 * @param p player to get
	 * @return RPPlayer or null
	 */
	public static RPPlayer getPlayer(Player p){
		if(onlinePlayers.containsKey(p.getUniqueId().toString())){
			return onlinePlayers.get(p.getUniqueId().toString());
		}
		return null;
	}
	
	/**Gets an City if available
	 * @param name Name of City
	 * @return OLCity or null
	 */
	public static OLCity getCity(String name){
		if(citys.containsKey(name)){
			return citys.get(name);
		}
		return null;
	}
	
	/**Gets Chunk by ID
	 * @param id Chunk id
	 * @return OLChunk if loaded
	 */
	public static OLChunk getChunk(String id){
		
		if(chunks.containsKey(id))
		return chunks.get(id);
		return null;
	}
	

	/**Gets an Land if available
	 * @param name Name of Land
	 * @return OLLand or null
	 */
	public static OLLand getLand(String name){
		if(lands.containsKey(name)){
			return lands.get(name);
		}
		return null;
	}
	
	/**Gets an Job if available
	 * @param name Name of Job
	 * @return OJob or null
	 */
	public static OLJob getJob(String name){
		if(jobs.containsKey(name)){
			return jobs.get(name);
		}
		return null;
	}
	
	/**Adds Job	if possible
	 * @param name Name of job
	 * @param job OLJob
	 */
	public static void addJob(String name, OLJob job){
		
		if(!jobs.containsKey(name)){
			jobs.put(name, job);
			for(Material m : job.get_craftItems()){
				if(!restrictedCraftItems.contains(m) && m != null){
					restrictedCraftItems.add(m);
				}
			}
			for(Material m : job.get_breakBlocks()){
				if(!restrictedBreakBlocks.contains(m) && m != null){
					restrictedBreakBlocks.add(m);
				}
			}
			for(Material m : job.get_buildBlocks()){
				if(!restrictedBuildBlocks.contains(m) && m != null){
					restrictedBuildBlocks.add(m);
				}
			}
			
		}
		
	}
	
	/**Adds Land	if possible
	 * @param name Name of Land
	 * @param land OLLand
	 */
	public static void addLand(String name, OLLand land){
		
		if(!lands.containsKey(name)){
			lands.put(name, land);
		}
	}
}
