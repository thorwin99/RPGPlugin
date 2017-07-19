package com.mcoldlife.objects;

import org.bukkit.Chunk;
import org.bukkit.ChunkSnapshot;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.entity.Entity;

import com.essentials.mcoldlife.main.CustomConfig;
import com.mcoldlife.rpg.ChunkUtils;
import com.mcoldlife.rpg.reference;

public class OLChunk implements Chunk{

	private Chunk _bc;
	private String _id;
	private String _city = null;
	private String _land = null;
	private String _file;
	
	/**Creates a new Chunk Object from an existing Chunk
	 * @param bukkitChunk
	 */
	public OLChunk(Chunk bukkitChunk){
		this._bc = bukkitChunk;
		this._id = ChunkUtils.generateId(_bc);
		this._file = _id + ".yml";
		
		if(!CustomConfig.exists(_file, reference.FOLDER_CHUNKS.toString())){
			CustomConfig.create(_file, reference.FOLDER_CHUNKS.toString());
			CustomConfig.set(_file, reference.FOLDER_CHUNKS.toString(), reference.PATH_CHUNK_ID, _id);
			CustomConfig.set(_file, reference.FOLDER_CHUNKS.toString(), reference.PATH_CHUNK_CITY, _city);
			CustomConfig.set(_file, reference.FOLDER_CHUNKS.toString(), reference.PATH_CHUNK_LAND, _land);
		}else{
			_city = (String) CustomConfig.get(_file, reference.FOLDER_CHUNKS.toString(), reference.PATH_CHUNK_CITY);
			_land = (String) CustomConfig.get(_file, reference.FOLDER_CHUNKS.toString(), reference.PATH_CHUNK_LAND);
		}
	}
	
	
	//Interface methods
	@Override
	public Block getBlock(int arg0, int arg1, int arg2) {
		
		return _bc.getBlock(arg0, arg1, arg2);
	}
	@Override
	public ChunkSnapshot getChunkSnapshot() {
		
		return _bc.getChunkSnapshot();
	}
	@Override
	public ChunkSnapshot getChunkSnapshot(boolean arg0, boolean arg1, boolean arg2) {

		return _bc.getChunkSnapshot(arg0, arg1, arg2);
	}
	@Override
	public Entity[] getEntities() {

		return _bc.getEntities();
	}
	@Override
	public BlockState[] getTileEntities() {
		
		return _bc.getTileEntities();
	}
	@Override
	public World getWorld() {

		return _bc.getWorld();
	}
	@Override
	public int getX() {

		return _bc.getX();
	}
	@Override
	public int getZ() {

		return _bc.getZ();
	}
	@Override
	public boolean isLoaded() {

		return _bc.isLoaded();
	}
	@Override
	public boolean load() {

		return _bc.load();
	}
	@Override
	public boolean load(boolean arg0) {

		return _bc.load(arg0);
	}
	@Override
	public boolean unload() {

		return _bc.unload();
	}
	@Override
	public boolean unload(boolean arg0) {

		return _bc.unload(arg0);
	}
	@Deprecated
	@Override
	public boolean unload(boolean arg0, boolean arg1) {

		return _bc.unload(arg0, arg1);
	}



	//ToString & ToYML functions
	/**
	 * @return Returns the Chunks ID
	 */
	@Override
	public String toString() {
		return _id;
	}


	/**
	 * @return the city
	 */
	public String getCity() {
		return _city;
	}


	/**
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this._city = city;
		
		CustomConfig.set(_file, reference.FOLDER_CHUNKS.toString(), reference.PATH_CHUNK_CITY, _city);
	}


	/**
	 * @return the land
	 */
	public String getLand() {
		return _land;
	}


	/**
	 * @param land the land to set
	 */
	public void setLand(String land) {
		this._land = land;
		
		CustomConfig.set(_file, reference.FOLDER_CHUNKS.toString(), reference.PATH_CHUNK_LAND, _land);
		
	}


	/**
	 * @return the id
	 */
	public String getID() {
		return _id;
	}
	
	/**Checks if Chunk is next to this Chunk
	 * @param chunk
	 * @return
	 */
	public boolean isNextTo(OLChunk chunk){
		
		int x = chunk.getX();
		int z = chunk.getZ();
		
		if(getX() + 1 == x || getX() == x || getX() - 1 == x){
			if(getZ() + 1 == z || getZ() == z || getZ() - 1 == z){
				return true;
			}
		}
		
		return false;
	}


	@Override
	public boolean isSlimeChunk() {
		return _bc.isSlimeChunk();
	}

}
