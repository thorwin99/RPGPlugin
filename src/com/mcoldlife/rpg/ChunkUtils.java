package com.mcoldlife.rpg;

import org.bukkit.Chunk;
import org.bukkit.World;

public class ChunkUtils {

	/**Generates an ID for the Chunk
	 * @param chunk Chunk to generate ID for
	 * @return the ID as String
	 */
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
	
	/**Generates an ID for the Chunk
	 * @param chunkX X coordinate of the chunk
	 * @param chunkZ Z coordinate of the Chunk
	 * @return the ID as String
	 */
	public static String generateId(int chunkX, int chunkZ){
		
		int x = chunkX;
		int z = chunkZ;
		
		String id = "";
		
		StringBuilder builder = new StringBuilder(id);
		builder.append("X");
		builder.append(x);
		builder.append("Z");
		builder.append(z);
		
		return builder.toString();
		
	}
	
	
	/**Returns an Array of the Chunk coordinates decoded from ID
	 * @param id ID to decode
	 * @return an int[] with the coordinates of the Chunk int[0] = X int[1] = Z
	 */
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
public static Chunk getChunk(String id, World world){
		int[] coords = decodeId(id);
		return world.getChunkAt(coords[0], coords[1]);
		
	}
}
