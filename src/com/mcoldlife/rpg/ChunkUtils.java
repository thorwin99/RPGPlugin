package com.mcoldlife.rpg;

import org.bukkit.Chunk;

import com.essentials.mcoldlife.main.CustomConfig;

public class ChunkUtils {

	public static String generateId(Chunk chunk){
		
		int x = chunk.getX();
		int z = chunk.getZ();
		
		String id = "";
		
		StringBuilder builder = new StringBuilder(id);
		builder.append("X");
		builder.append(x);
		builder.append("Z");
		builder.append(z);
		
		return builder.toString();
		
	}
	
	public static int[] decodeId(String id){
		
		StringBuilder builder = new StringBuilder(id);
		int xIndex = builder.lastIndexOf("X");
		builder.deleteCharAt(xIndex);
		String xAndZ = builder.toString();
		String[] stringCoords = xAndZ.split("Z", 2);
		int[] coords = new int[]{0,0};
		
		for(int i = 0; i < 2; i++){
			coords[i] = Integer.parseInt(stringCoords[i]);
		}
		
		return coords;
		
	}
	
	public static boolean setLand(Chunk chunk, String land){
		
		String id = generateId(chunk);
		
		if(CustomConfig.exists(id + ".yml", reference.FOLDER_CHUNKS.toString())){
			CustomConfig.set(id + ".yml",  reference.FOLDER_CHUNKS.toString(), reference.PATH_CHUNK_LAND, land);
			return true;
		}else{
			return false;
		}
		
	}
	
	public static boolean setCity(Chunk chunk, String city){
		
		String id = generateId(chunk);
		
		if(CustomConfig.exists(id + ".yml", reference.FOLDER_CHUNKS.toString())){
			CustomConfig.set(id + ".yml",  reference.FOLDER_CHUNKS.toString(), reference.PATH_CHUNK_CITY, city);
			return true;
		}else{
			return false;
		}
		
	}
	
	public static String getLand(Chunk chunk){
		String id = generateId(chunk);

		if(CustomConfig.exists(id + ".yml", reference.FOLDER_CHUNKS.toString())){
			return CustomConfig.get(id + ".yml",  reference.FOLDER_CHUNKS.toString(), reference.PATH_CHUNK_LAND).toString();
		}else{
			return null;
		}
	}
	
	public static String getCity(Chunk chunk){
		String id = generateId(chunk);

		if(CustomConfig.exists(id + ".yml", reference.FOLDER_CHUNKS.toString())){
			return CustomConfig.get(id + ".yml",  reference.FOLDER_CHUNKS.toString(), reference.PATH_CHUNK_CITY).toString();
		}else{
			return null;
		}
	}
	
}
