package com.mcoldlife.objects;

import java.util.HashMap;
import java.util.UUID;

import org.bukkit.Chunk;

import com.essentials.mcoldlife.main.CustomConfig;
import com.mcoldlife.rpg.ChunkUtils;
import com.mcoldlife.rpg.reference;
import com.mcoldlife.rpg.rpg;

public class OLCity {

	private HashMap<String, OLChunk> _chunks = new HashMap<>();
	private UUID _owner;
	private String _name;
	private String _land;
	private OLLand _olLand = null;
	private String _fileName;
	private int _tax;
	private int _money;
	private boolean _isCreated;
	private HashMap<String, OLPlot> _plots = new HashMap<>();
	private static final String _folder = reference.FOLDER_CITYS.toString();
	
	/**
	 * Loads city from file. If file doesn't exist, new City file gets created.
	 * 
	 * @param name Name of the city
	 */
	public OLCity(String name){
		
		_name = name;
		_fileName = _name + ".yml";
		
		if(CustomConfig.exists(_fileName, _folder)){
			
			_isCreated = (boolean) CustomConfig.get(_fileName, _folder, reference.PATH_CITY_CREATED);
			if(_isCreated){
				_owner = UUID.fromString((String) CustomConfig.get(_fileName, _folder, reference.PATH_CITY_OWNER));
				_land = (String) CustomConfig.get(_fileName, _folder, reference.PATH_CITY_LAND);
				_tax = Integer.parseInt((String) CustomConfig.get(_fileName, _folder, reference.PATH_CITY_TAXES));
				_money = Integer.parseInt((String) CustomConfig.get(_fileName, _folder, reference.PATH_CITY_MONEY));
				reloadLists();
			}else{
				_owner = null;
				_land = null;
				_tax = 100;
				_money = 0;
			}
		}else{
			_isCreated = false;
			_owner = null;
			_land = null;
			_tax = 100;
			_money = 0;
			CustomConfig.create(_fileName, _folder);
			CustomConfig.set(_fileName, _folder, reference.PATH_CITY_OWNER, null);
			CustomConfig.set(_fileName, _folder, reference.PATH_CITY_TAXES, _tax);
			CustomConfig.set(_fileName, _folder, reference.PATH_CITY_LAND, null);
			CustomConfig.set(_fileName, _folder, reference.PATH_CITY_MONEY, _money);
			CustomConfig.set(_fileName, _folder, reference.PATH_CITY_CREATED, false);
			CustomConfig.set(_fileName, _folder, reference.PATH_CITY_CHUNKS, new String[]{});
		}
		
	}
	
	/**This Creates a new City if the city is not created. This Function 
	 * Ignores if the owner already is part of a city or an
	 * Owner of a City.
	 * @param owner The Player who will later own the city.
	 * @param baseChunk The Chunk of the base city.
	 * @return
	 */
	public boolean createCity(RPPlayer owner, OLChunk baseChunk){
		
		if(_isCreated) return false;
		
		//Create this City
		CustomConfig.set(_fileName, _folder, reference.PATH_CITY_OWNER, owner.getBukkitPlayer().getUniqueId());
		CustomConfig.set(_fileName, _folder, reference.PATH_CITY_CREATED, true);
		CustomConfig.set(_fileName, _folder, reference.PATH_CITY_LAND, owner.getLand().getName());
		_owner = owner.getBukkitPlayer().getUniqueId();
		_land = owner.getLand().getName();
		_olLand = owner.getLand();
		
		for(int x = -2; x <= 2; x++){
			for(int z = -2; z <= 2; z++){
				
				int normX = baseChunk.getX();
				int normZ = baseChunk.getZ();
				
				String ID = ChunkUtils.generateId(x + normX, z + normZ);
				if(_olLand.get_chunks().containsKey(ID)){
					addChunk(_olLand.get_chunks().get(ID));
				}else{
					OLChunk chunk = new OLChunk(ChunkUtils.getChunk(ID, reference.RPG_WORLD));
					chunk.setCity(getName());
					chunk.setLand(getLand().getName());
				}
				
			}
		}
		
		return true;
		
	}
	
	/**Reloads Chunk and Plot list
	 * 
	 */
	private void reloadLists(){
		
		_chunks.clear();
		_plots.clear();
		
		//Loads chunks
		String[] chunkIDs = (String[]) CustomConfig.getArray(_fileName, _folder, reference.PATH_CITY_CHUNKS);
		
		for(String id : chunkIDs){
			
			if(rpg.getRPGManager().getChunks().containsKey(id)){
				addChunk(rpg.getRPGManager().getChunks().get(id));
			}else{
				Chunk c = ChunkUtils.getChunk(id, reference.RPG_WORLD);
				OLChunk ch = new OLChunk(c);

				_chunks.put(id, ch);
			}
			
		}
		//Plots
		String[] plots = CustomConfig.getSection(_fileName, _folder, reference.PATH_CITY_PLOTS.substring(0, reference.PATH_CITY_PLOTS.length() - 2));
		
		for(String plot : plots){
			
			_plots.put(plot, new OLPlot(plot, _fileName));
			
		}
	}

	/**
	 * @return the land
	 */
	public OLLand getLand() {
		return _olLand;
	}

	/**
	 * @param land the land to set
	 */
	public void setLand(OLLand land) {
		this._olLand = land;
		CustomConfig.set(_fileName, _folder, reference.PATH_CITY_LAND, land.getName());
	}

	/**
	 * @return the tax
	 */
	public int getTax() {
		return _tax;
	}

	/**
	 * @param tax the tax to set
	 */
	public void setTax(int tax) {
		this._tax = tax;
		CustomConfig.set(_fileName, _folder, reference.PATH_CITY_TAXES, tax);
	}

	/**
	 * @return the Owner
	 */
	public UUID getOwner() {
		return _owner;
	}

	/**
	 * @return the Name of the City
	 */
	public String getName() {
		return _name;
	}
	
	/**
	 * Adds new chunk to the City if possible
	 * @param chunk
	 */
	public void addChunk(OLChunk chunk){
		
		if(_olLand == null){
			if(RPGManager.lands.containsKey(_land)){
				_olLand = RPGManager.lands.get(_land);
			}
		}
		
		if(chunk.getLand() == null){
			getLand().addChunk(chunk);
		}
		
		if(chunk.getLand() == _olLand.getName() && !_chunks.containsKey(chunk.getID()) && chunk.getCity() == null){
			for(String id : _chunks.keySet()){
				if(chunk.isNextTo(_chunks.get(id))){
					if(chunk.getLand() == null) {
						chunk.setLand(_olLand.getName());
						_olLand.addChunk(chunk);
					}
					chunk.setCity(_name);
					_chunks.put(chunk.getID(), chunk);
					CustomConfig.addToArray(_fileName, _folder, reference.PATH_CITY_CHUNKS, new String[]{chunk.getID()});
					break;
				}
			}
		}
		
	}
	
	/**Creates Plot if possible
	 * @param name Name of plot
	 * @param one First Corner Vector2D
	 * @param two Second Corner Vector2D
	 * @return true if plot is created
	 */
	public boolean makePlot(String name, Vector2D one, Vector2D two, int price){
		
		if(inPlot(one) != null || inPlot(two)!= null)return false;
		
		String plotPath = reference.PATH_CITY_PLOTS + name;
		if(!CustomConfig.contains(_fileName, _folder, plotPath)){
			
			CustomConfig.set(_fileName, _folder, plotPath + reference.PATH_CITY_PLOTS_OWNER, null);
			CustomConfig.set(_fileName, _folder, plotPath + reference.PATH_CITY_PLOTS_PRICE, price);
			CustomConfig.set(_fileName, _folder, plotPath + reference.PATH_CITY_PLOTS_COORDS[0][0], one.x);
			CustomConfig.set(_fileName, _folder, plotPath + reference.PATH_CITY_PLOTS_COORDS[0][1], one.y);
			
			CustomConfig.set(_fileName, _folder, plotPath + reference.PATH_CITY_PLOTS_COORDS[1][0], two.x);
			CustomConfig.set(_fileName, _folder, plotPath + reference.PATH_CITY_PLOTS_COORDS[1][1], two.y);
			
			OLPlot plot = new OLPlot(name, _fileName);
			_plots.put(name, plot);
			
			return true;
		}
		return false;
	}
	
	/**Checks if the given coordinate is already inside another plot
	 * @param coord Coordinate to test
	 * @return The plot if it is inside a Plot or null if not.
	 */
	public OLPlot inPlot(Vector2D coord){
		
		for(String key : _plots.keySet()){
			OLPlot p = _plots.get(key);
			if(coord.betweenVectors(p.getCorners()[0], p.getCorners()[1])){
				return p;
			}
		}
		
		return null;
	}

	/**Gets the City money
	 * @return the money of the city
	 */
	public int get_money() {
		return _money;
	}

	/**Sets the city money
	 * @param money The money to set the city money to
	 */
	public void set_money(int money) {
		this._money = money;
		CustomConfig.set(_fileName, _folder, reference.PATH_CITY_MONEY, money);
	}
	
	/**Adds money to City
	 * @param amount Amount of money to add.
	 */
	public void addMoney(int amount){
		this._money = get_money() + amount;
	}
	
	/**Checks if a Plot already exists with that name.
	 * @param plotName Name of the Plot
	 * @return true if exists
	 */
	public boolean plotExists(String plotName){
		
		if(_plots.containsKey(plotName)){
			return true;
		}
		return false;
	}

	/**Checks if Plot is owned.
	 * @param plotName Name of plot
	 * @return true if claimed.
	 */
	public boolean plotOwned(String plotName){
		
		if(_plots.containsKey(plotName)){
			if(_plots.get(plotName).getOwner() != null){
				return true;
			}
		}
		return false;
	}
	
	/**Deletes a Plot
	 * @param plotName Name of the Plot
	 * @return true if deleted.
	 */
	public boolean deletePlot(String plotName){
		
		if(plotExists(plotName)){
			
			_plots.remove(plotName);
			
			CustomConfig.remove(_fileName, _folder, reference.PATH_CITY_PLOTS + plotName);
			
			return true;
			
		}
		
		return false;
	}

	/**Gets the Plot
	 * @param name Name of the Plot
	 * @return The Plot or null if not existed
	 */
	public OLPlot getPlot(String name){
		if(plotExists(name)){
			return _plots.get(name);
		}else{
			return null;
		}
	}
	
	/**Gets the Plot of an Player.
	 * @param player Owner of the Plot.
	 * @return The Plot of the Player or null
	 */
	public OLPlot getPlot(RPPlayer player){
		
		if(player.get_city() == this){
			for(String p : _plots.keySet()){
				
				if(_plots.get(p).getOwner() == player.getBukkitPlayer().getUniqueId()){
					return _plots.get(p);
				}
				
			}
		}
		
		
		return null;
		
	}

	/**Gets an Array of all Plots
	 * @return Array of Plots
	 */
	public OLPlot[] getPlots(){
		OLPlot[] plots = new OLPlot[_plots.size()];	
		int i = 0;
		for(String name : _plots.keySet()){
			plots[i] = _plots.get(name);
			i++;
		}
		return plots;
	}
}