package com.mcoldlife.objects;

import java.util.UUID;

import org.bukkit.Location;

import com.essentials.mcoldlife.main.CustomConfig;
import com.mcoldlife.rpg.reference;

/**
 * @author Thorwin
 *
 */
public class OLPlot {

	private Vector2D[] _corners;
	private boolean _isCreated;
	private UUID _owner;
	private int _price;
	private String _name;
	private String _fileName;
	private String _plotPath;
	private static final String _folder = reference.FOLDER_CITYS.toString();
	
	/**Loads Plot from File or creates empty if not possible
	 * @param name Name of the Plot
	 * @param cityFile City file of the plot
	 */
	public OLPlot(String name, String cityFile){
		
		_fileName = cityFile;
		_name = name;
		_plotPath = reference.PATH_CITY_PLOTS + _name;
		if(CustomConfig.contains(_fileName, _folder, _plotPath)){
			_isCreated = true;
			setOwner(UUID.fromString((String) CustomConfig.get(_fileName, _folder,_plotPath + reference.PATH_CITY_PLOTS_OWNER)));
			setPrice(Integer.parseInt((String) CustomConfig.get(_fileName, _folder, _plotPath + reference.PATH_CITY_PLOTS_PRICE)));
			loadCorners();
		}else{
			_isCreated = false;
			_owner = null;
			_price = -1;
		}
	}
	/**Creates the Plot and its Configuration Section
	 * @param corners Corners of the Plot
	 * @param price Price of the Plot
	 * @return true if created.
	 */
	public boolean createPlot(Vector2D[] corners, int price){
		if(_isCreated)return false;
		
		setPrice(price);
		
		for(int i = 0; i < 2; i++){
			CustomConfig.set(_fileName, _folder, _plotPath + reference.PATH_CITY_PLOTS_COORDS[i][0], _corners[i].getX());
			CustomConfig.set(_fileName, _folder, _plotPath + reference.PATH_CITY_PLOTS_COORDS[i][1], _corners[i].getY());
		}
		return true;
	}
	/**Creates the Plot and its Configuration Section
	 * @param corners Corners of the Plot
	 * @param price Price of the Plot
	 * @return true if created.
	 */
	public boolean createPlot(Location[] corners, int price){
		
		Vector2D[] corners2d = new Vector2D[2];
		for(int i = 0; i < 2; i++){
			corners2d[i] = new Vector2D(corners[i].getBlockX(), corners[i].getBlockZ());
		}
		return createPlot(corners2d, price);
	}
	
	/**Loads the Corners of the Plot
	 * 
	 */
	private void loadCorners(){
		_corners = new Vector2D[2];
		
		int loc[][] = new int[2][2];
		
		for(int x = 0; x < 2; x++){
			for(int y = 0; y < 2; y++){
				loc[x][y] = Integer.parseInt((String) CustomConfig.get(_fileName, _folder, _plotPath + reference.PATH_CITY_PLOTS_COORDS[x][y]));
			}
		}
		for(int i = 0; i < 2; i++){
			_corners[i] = new Vector2D(loc[i][0], loc[i][1]);
		}
	}

	/**Gets the Plot owner
	 * @return the Owner of the Plot as UUID
	 */
	public UUID getOwner() {
		return _owner;
	}

	/**Sets the Owner of the Plot
	 * @param owner UUID of the OWner
	 */
	public void setOwner(UUID owner) {
		this._owner = owner;
		CustomConfig.set(_fileName, _folder, _plotPath + reference.PATH_CITY_PLOTS_OWNER, _owner);
	}

	/**Gets the Price for the Plot
	 * @return The Price of the Plot as int
	 */
	public int getPrice() {
		return _price;
	}

	/**Sets the Price for the Plot
	 * @param price The Price for the Plot as int
	 */
	public void setPrice(int price) {
		this._price = price;
		CustomConfig.set(_fileName, _folder, _plotPath + reference.PATH_CITY_PLOTS_PRICE, _price);
	}
	
	/**Checks if the given Location is inside this Plot
	 * @param loc Location to check
	 * @return true if it is inside this Plot
	 */
	public boolean locationinPlot(Location loc){
		
		int x = loc.getBlockX();
		int z = loc.getBlockZ();
		
		if(integerBetween(x, _corners[0].getX(), _corners[1].getX())){
			if(integerBetween(z, _corners[0].getY(), _corners[1].getY())){
				return true;
			}
		}
		
		return false;
	}
	
	/**Checks if an Integer is between two others
	 * @param base Integer to Check
	 * @param range1 first Border
	 * @param range2 second Border
	 * @return true if it is between those two
	 */
	private boolean integerBetween(int base, int range1, int range2){
		
		if(range1 > range2){
			if(base <= range1 && base >= range2){
				return true;
			}
		}else if(range2 > range1){
			if(base <= range2 && base >= range1){
				return true;
			}
			
		}
		return false;
		
	}

	/**
	 * @return the corners
	 */
	public Vector2D[] getCorners() {
		return _corners;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return _name;
	}

	
}
