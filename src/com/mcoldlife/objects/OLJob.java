package com.mcoldlife.objects;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;

import com.essentials.mcoldlife.main.CustomConfig;
import com.mcoldlife.rpg.reference;

public class OLJob {

	private String _name;
	private List<Material> _breakBlocks = new ArrayList<>();
	private List<Material> _buildBlocks = new ArrayList<>();
	private List<Material> _craftItems = new ArrayList<>();
	private boolean _isCreated;
	private boolean _canBuildAnywhere;
	private String _fileName;
	private static final String _folder = reference.FOLDER_CLASSES.toString();
	
	public OLJob(String name){
		this._name = name;
		this._fileName = name + ".yml";
		if(CustomConfig.exists(_fileName, _folder)){
			_canBuildAnywhere = Boolean.parseBoolean((String) CustomConfig.get(_fileName, _name, reference.PATH_JOB_BUILD_ANYWHERE));
			refreshBlocks();
			_isCreated = true;
		}else{
			_isCreated = false;
		}
		
	}

	/**Creates Class/Job
	 * @param breakBlocks All blocks the Player can break;
	 * @param buildBlocks All blocks the Player can Place
	 * @param craftItems TODO
	 */
	public boolean create(List<Material> breakBlocks, List<Material> buildBlocks, List<Material> craftItems, boolean canBuildAnywhere){
		if(!_isCreated){//TODO: maybe make lists to string lists
			this._buildBlocks = buildBlocks;
			this._breakBlocks = breakBlocks;
			this._craftItems = craftItems;
			this._canBuildAnywhere = canBuildAnywhere;
			CustomConfig.create(_fileName, _folder);
			CustomConfig.set(_fileName, _folder, reference.PATH_JOB_NAME, _name);
			CustomConfig.set(_fileName, _folder, reference.PATH_JOB_BUILD_ANYWHERE, canBuildAnywhere);
			CustomConfig.addToList(_fileName, _folder, reference.PATH_JOB_BUILD_BLOCKS, _buildBlocks);
			CustomConfig.addToList(_fileName, _folder, reference.PATH_JOB_DESTROY_BLOCKS, _breakBlocks);
			CustomConfig.addToList(_fileName, _folder, reference.PATH_JOB_CRAFT_ITEMS, _craftItems);
			_isCreated = true;
			return true;
		}
		return false;
	}
	
	/**Checks if job contains build material
	 * @param material Material to check
	 * @return true if it has it.
	 */
	public boolean containsBuildMaterial(Material material){
		if(_buildBlocks.contains(material)){
			return true;
		}
		return false;
	}
	
	/**Checks if job contains break material
	 * @param material Material to check
	 * @return true if it has it.
	 */
	public boolean containsBreakMaterial(Material material){
		if(_breakBlocks.contains(material)){
			return true;
		}
		return false;
	}
	

	/**Checks if job contains Craft material
	 * @param material Material to check
	 * @return true if it has it.
	 */
	public boolean containsCraftMaterial(Material material){
		if(_craftItems.contains(material)){
			return true;
		}
		return false;
	}
	
	/**Gets the name
	 * @return the name of the job
	 */
	public String getName(){
		
		return _name;
		
	}

	/**Reloads all Blocks
	 * 
	 */
	private void refreshBlocks() {
		String[] buildBlockNames = (String[]) CustomConfig.getArray(_name, _folder, reference.PATH_JOB_BUILD_BLOCKS);
		String[] destroyBlockNames = (String[]) CustomConfig.getArray(_name, _folder, reference.PATH_JOB_DESTROY_BLOCKS);

		for(String block : buildBlockNames){
			try{
				Material m = Material.valueOf(block);
				if(!_buildBlocks.contains(m)){
					_buildBlocks.add(m);
				}
			}catch(IllegalArgumentException e){
				
			}
		}
		for(String block : destroyBlockNames){
			try{
				Material m = Material.valueOf(block);
				if(!_breakBlocks.contains(m)){
					_breakBlocks.add(m);
				}
			}catch(IllegalArgumentException e){
				
			}
		}
	}

	/**
	 * @return the breakBlocks
	 */
	public List<Material> get_breakBlocks() {
		return _breakBlocks;
	}
	
	/**
	 * @return the buildBlocks
	 */
	public List<Material> get_buildBlocks() {
		return _buildBlocks;
	}

	/**
	 * @return true if user could build inside his land
	 */
	public boolean canBuildAnywhere() {
		return _canBuildAnywhere;
	}

	/**
	 * @return the _craftItems
	 */
	public List<Material> get_craftItems() {
		return _craftItems;
	}

}
