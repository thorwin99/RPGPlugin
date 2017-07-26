package com.mcoldlife.objects;

import java.util.HashMap;
import java.util.UUID;

import org.bukkit.Chunk;

import com.essentials.mcoldlife.main.CustomConfig;
import com.mcoldlife.rpg.ChunkUtils;
import com.mcoldlife.rpg.reference;

public class OLLand {

	private String _name;
	private boolean _isCreated;
	private String _fileName;
	private UUID _owner;
	private HashMap<String, OLCity> _citys = new HashMap<>();;
	private HashMap<String, OLChunk> _chunks = new HashMap<>();;
	private static final String _folder = reference.FOLDER_LANDS.toString();
	
	/**Loads a Land from configuration if possible or creates a new empty one
	 * @param name Name for the Land to load or create
	 */
	public OLLand(String name){
		
		_name = name;
		_fileName = _name + ".yml";
		
		if(CustomConfig.exists(_fileName, _folder)){
			_isCreated = (boolean) CustomConfig.get(_fileName, _folder, reference.PATH_LAND_CREATED);
			if(_isCreated){
				_owner = UUID.fromString((String) CustomConfig.get(_fileName, _folder, reference.PATH_LAND_OWNER));
				reloadLists();
			}else{
				_owner = null;
			}
		}else{
			_isCreated = false;
			_owner = null;
			CustomConfig.set(_fileName, _folder, reference.PATH_LAND_OWNER, null);
			CustomConfig.set(_fileName, _folder, reference.PATH_LAND_CREATED, _isCreated);
			CustomConfig.set(_fileName, _folder, reference.PATH_LAND_CHUNKS, new String[]{});
			CustomConfig.set(_fileName, _folder, reference.PATH_LAND_CITYS, new String[]{});
		}
		
	}
	
	public boolean createLand(RPPlayer owner, OLChunk baseChunk){
		
		if(_isCreated) return false;
		
		_isCreated = true;
		
		CustomConfig.addToArray(reference.FILE_LANDS, reference.CONFIG_FOLDER.toString(), reference.PATH_LANDS, new String[]{_name});
		
		//Create this City
		CustomConfig.set(_fileName, _folder, reference.PATH_LAND_OWNER, owner.getBukkitPlayer().getUniqueId().toString());
		CustomConfig.set(_fileName, _folder, reference.PATH_LAND_CREATED, true);
		_owner = owner.getBukkitPlayer().getUniqueId();
		
		for(int x = -5; x <= 5; x++){
			for(int z = -5; z <= 5; z++){
				
				int normX = baseChunk.getX();
				int normZ = baseChunk.getZ();
				
				String ID = ChunkUtils.generateId(x + normX, z + normZ);
				if(!get_chunks().containsKey(ID)){
					
					if(RPGManager.chunks.containsKey(ID)){
						RPGManager.chunks.get(ID).setLand(getName());
						addChunk(RPGManager.chunks.get(ID));
					}else{
						OLChunk chunk = new OLChunk(ChunkUtils.getChunk(ID, reference.RPG_WORLD));
						chunk.setLand(getName());
						addChunk(chunk);
					}
					
				}
				
			}
		}
		return true;
	}

	/**Gets the Name of the Land
	 * @return
	 */
	public String getName() {
		return _name;
	}
	
	/**Reloads the lists of chunks and citys
	 * 
	 */
	private void reloadLists(){
		
		_chunks.clear();
		_citys.clear();
		
		//Loads chunks
		Object[] chunkIDs = CustomConfig.getArray(_fileName, _folder, reference.PATH_LAND_CHUNKS);
		
		for(Object oId : chunkIDs){
			
			String id = oId.toString();
			
			if(RPGManager.getChunks().containsKey(id)){
				addChunk(RPGManager.getChunks().get(id));
			}else{
				Chunk c = ChunkUtils.getChunk(id, reference.RPG_WORLD);
				OLChunk ch = new OLChunk(c);

				_chunks.put(id, ch);
			}
			
		}
		//Loads and set City's
		Object[] cityNames = CustomConfig.getArray(_fileName, _folder, reference.PATH_LAND_CITYS);
		
		for(Object oName : cityNames){
			
			String name = oName.toString();
			
			if(!_citys.containsKey(name)){
				
				if(RPGManager.citys.containsKey(name)){
					_citys.put(name, RPGManager.citys.get(name));
				}else{
					_citys.put(name, new OLCity(name));
					RPGManager.citys.put(name, _citys.get(name));
				}
			}
		}
	}

	/**
	 * @return the Owner of the land
	 */
	public UUID getOwner() {
		return _owner;
	}

	/**
	 * @param _owner The owner to set for the land
	 */
	public void setOwner(UUID owner) {
		this._owner = owner;
		CustomConfig.set(_fileName, _folder, reference.PATH_LAND_OWNER, owner);
	}

	/**
	 * @return the City's of the land
	 */
	public HashMap<String, OLCity> get_citys() {
		return _citys;
	}

	/**
	 * @return the Chunks of the land
	 */
	public HashMap<String, OLChunk> get_chunks() {
		return _chunks;
	}
	
	/**
	 * Adds chunk to the Land if possible
	 * @param chunk
	 */
	public void addChunk(OLChunk chunk){
		
		if(chunk.getLand() == null) {
			chunk.setLand(getName());
			CustomConfig.addToArray(_fileName, _folder, reference.PATH_LAND_CHUNKS, new String[]{chunk.getID()});
		}
		
		if(chunk.getLand() == getName() && !_chunks.containsKey(chunk.getID())){
			if(!_chunks.containsKey(chunk.getID())){
				_chunks.put(chunk.getID(), chunk);
			}
		}
		
	}
	
	/**
	 * Adds City to the Land if possible
	 * @param city City to add
	 */
	public boolean addCity(OLCity city){
		
		if(city.getLand() == this || city.getLand().getName() == getName()){
			
			if(!_citys.containsKey(city.getName())){
				_citys.put(city.getName(), city);
				CustomConfig.addToArray(_fileName, _folder, reference.PATH_LAND_CITYS, new String[]{city.getName()});
			}
			
		}
		return false;
		
	}
	
	/**Adds Player to land
	 * @param player Player to add
	 * @return true if added.
	 */
	public boolean addPlayer(RPPlayer player){
		
		if(player.getLand() == null){
			player.setLand(this);
			return true;
		}
		
		return false;
		
	}
	
	/**Gets all city's of land
	 * @return OLCity[]
	 */
	public OLCity[] getCitys(){
		OLCity[] citys = new OLCity[_citys.size()];
		int i = 0;
		for(String name : _citys.keySet()){
			citys[i] = _citys.get(name);
			i++;
		}
		return citys;
	}
}
