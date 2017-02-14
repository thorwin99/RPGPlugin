package com.mcoldlife.rpg;

import java.util.Arrays;

import org.bukkit.Chunk;
import org.bukkit.entity.Player;

import com.essentials.mcoldlife.main.CustomConfig;

import ReturnBundle.ReturnBundle;

public class lands {

	public static ReturnBundle<Boolean> createLand(String name, Player founder){
		
		Chunk landBaseChunk = founder.getLocation().getChunk();
		String chunkID = ChunkUtils.generateId(landBaseChunk);
		if()
		
	}
	
	
	
	public static ReturnBundle<Boolean> _createLand(String name, Player founder){
		Chunk landBaseChunk = founder.getWorld().getChunkAt(founder.getLocation());
		String chunkID = ChunkUtils.generateId(landBaseChunk);
		if(chunkConquered(landBaseChunk)){
			return (new ReturnBundle<>(false)).setMessageCode(reference.CODE_CHUNK_OWNED_BY_OTHER_LAND);
		}else if(createLandsConfig(name, founder, landBaseChunk)){
			String fileName = name + ".yml";
			CustomConfig.addToArray(fileName, reference.FOLDER_LANDS.toString(), reference.PATH_LANDS_CHUNKS, new String[]{chunkID});
			CustomConfig.set(fileName, reference.FOLDER_LANDS.toString(), reference.PATH_LANDS_OWNER, founder.getUniqueId().toString());
			for(int x = -1; x <=1; x++){
				for(int z = -1; z <= 1; z++){
					if(x == 0 && z == 0)continue;
					Chunk currentChunk = founder.getWorld().getChunkAt(landBaseChunk.getX() + x, landBaseChunk.getZ() + z);
					if(!chunkConquered(currentChunk)){
						String id = ChunkUtils.generateId(currentChunk);
						CustomConfig.addToArray(fileName, reference.FOLDER_LANDS.toString(), reference.PATH_LANDS_CHUNKS, new String[]{id});
					}
				}
			}
			return (new ReturnBundle<>(true));
		}else{
			return (new ReturnBundle<>(true)).setMessageCode(reference.CODE_EXISTING);
		}
		
	}
	
	public static ReturnBundle<Boolean> expandLand(Player founder, Chunk chunk){
		
		String chunkId = ChunkUtils.generateId(chunk);
		String[] lands = CustomConfig.getFilesInFolder(reference.FOLDER_LANDS.toString());
		if(getChunkLand(chunk) != null)return (new ReturnBundle<>(false).setMessageCode(reference.CODE_CHUNK_OWNED_BY_OTHER_LAND));
		
		for(String land : lands){
			
			if(CustomConfig.get(land, reference.FOLDER_LANDS.toString(), reference.PATH_LANDS_OWNER).equals(founder.getUniqueId().toString())){
				String[] chunks = (String[]) CustomConfig.getArray(land, reference.FOLDER_LANDS.toString(), reference.PATH_LANDS_CHUNKS);
				for(String chunkID : chunks){
					for(int x = -1; x <= 1; x++){
						for(int y = -1; y <= 1; y++){
							
							Chunk currentChunk = founder.getWorld().getChunkAt(x, y);
							String id = ChunkUtils.generateId(currentChunk);
							
							if(chunkID.equalsIgnoreCase(id) && !chunkID.equalsIgnoreCase(chunkId)){
								CustomConfig.addToArray(land, reference.FOLDER_LANDS.toString(), reference.PATH_LANDS_CHUNKS, new String[]{chunkId});
								return (new ReturnBundle<>(true));
							}
							
						}
					}
				}
				return (new ReturnBundle<>(false).setMessageCode(reference.CODE_CHUNK_NOT_CONNECTED));
				
			}
		}
		return (new ReturnBundle<>(false).setMessageCode(reference.CODE_PLAYER_NOT_OWNER));
		
	}
	
	public static boolean chunkConquered(Chunk chunk){
		
		boolean exists = false;
		String id = ChunkUtils.generateId(chunk);
		String[] lands = CustomConfig.getFilesInFolder(reference.FOLDER_LANDS.toString());
		
		for(String land : lands){
			
			String[] chunkIds = (String[]) CustomConfig.getArray(land, reference.FOLDER_LANDS.toString(), reference.PATH_LANDS_CHUNKS);
			for(String chunkId : chunkIds){
				if(chunkId.equalsIgnoreCase(id)){
					exists = true;
					break;
				}
			}
			
		}
		
		return exists;
	}
	
	public static boolean createLandsConfig(String name, Player founder, Chunk landBaseChunk){
		String fileName = name + ".yml";
		if(CustomConfig.exists(fileName, reference.FOLDER_LANDS.toString())){
			return false;
		}else{
			CustomConfig.create(name + ".yml", reference.FOLDER_LANDS.toString());
			CustomConfig.addToArray(fileName, reference.FOLDER_LANDS.toString(), reference.PATH_LANDS_CHUNKS, new String[]{null});
			CustomConfig.addToArray(fileName, reference.FOLDER_LANDS.toString(), reference.PATH_LANDS_CITYS, new String[]{null});
			CustomConfig.addToArray(fileName, reference.FOLDER_LANDS.toString(), reference.PATH_LANDS_PLAYERS, new String[]{null});
			CustomConfig.removeFromArray(fileName, reference.FOLDER_LANDS.toString(), reference.PATH_LANDS_CHUNKS, new String[]{null});
			CustomConfig.removeFromArray(fileName, reference.FOLDER_LANDS.toString(), reference.PATH_LANDS_CITYS, new String[]{null});
			CustomConfig.removeFromArray(fileName, reference.FOLDER_LANDS.toString(), reference.PATH_LANDS_PLAYERS, new String[]{null});
			CustomConfig.set(fileName, reference.FOLDER_LANDS.toString(), reference.PATH_LANDS_NAME, name);
			
			CustomConfig.createFolder(reference.FOLDER_LANDS.resolve(name).toString());
			
			return true;
		}
	}
	
	public static boolean addPlayerToLand(Player founder, Player child){
		
		String[] lands = CustomConfig.getFilesInFolder(reference.FOLDER_LANDS.toString());
		for(String land : lands){
			
			String landOwner = (String) CustomConfig.get(land, reference.FOLDER_LANDS.toString(), reference.PATH_LANDS_OWNER);
			if(landOwner.equals(founder.getUniqueId().toString())){
				CustomConfig.set(child.getUniqueId().toString() + ".yml", reference.FOLDER_PLAYERS.toString(), reference.PATH_PLAYER_LAND, land);
				CustomConfig.set(child.getUniqueId().toString() + ".yml", reference.FOLDER_PLAYERS.toString(), reference.PATH_PLAYER_CITY, null);
			}
			
		}
		
		
		return false;
		
	}
	
	public static boolean removePlayerFromLand(Player founder, Player child){
		String[] lands = CustomConfig.getFilesInFolder(reference.FOLDER_LANDS.toString());
		for(String land : lands){
			
			String landOwner = (String) CustomConfig.get(land, reference.FOLDER_LANDS.toString(), reference.PATH_LANDS_OWNER);
			if(landOwner.equals(founder.getUniqueId().toString())){
				String[] landPlayers = (String[]) CustomConfig.getArray(land, reference.FOLDER_LANDS.toString(), reference.PATH_LANDS_PLAYERS);
				if(Arrays.asList(landPlayers).contains(child.getUniqueId().toString())){
					
					CustomConfig.removeFromArray(land, reference.FOLDER_LANDS.toString(), reference.PATH_LANDS_PLAYERS, new String[]{child.getUniqueId().toString()});
					return true;
					
				}
			}
			
		}
		return false;
	}
	
	public static String getChunkLand(Chunk chunk){
		
		return ChunkUtils.getLand(chunk);
	}
	
	public static String getPlayerLand(Player player){
		String id = player.getUniqueId().toString();
		
		String land = CustomConfig.get(id + ".yml", reference.FOLDER_PLAYERS.toString(), reference.PATH_PLAYER_LAND).toString();
		return land;
	}
}
