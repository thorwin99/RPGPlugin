package com.mcoldlife.objects;

import java.util.UUID;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import com.essentials.mcoldlife.main.CustomConfig;
import com.mcoldlife.rpg.Money;
import com.mcoldlife.rpg.reference;

public class RPPlayer{

	private UUID _id;
	private Player _bp;
	private OLLand _land;
	private OLCity _city;
	private OLJob _job;
	private String _fileName;
	private int _money;
	private Location[] _possPlotCoords = new Location[2];
	
	//TODO: Add Job
	private static final String _folder = reference.FOLDER_PLAYERS.toString();
	
	public RPPlayer(Player p){
		
		_bp = p;
		_id = p.getUniqueId();
		_fileName = _id.toString() + ".yml";
		
		if(CustomConfig.exists(_fileName, _folder)){
			
			String city = (String) CustomConfig.get(_fileName, _folder, reference.PATH_PLAYER_CITY);
			String land = (String) CustomConfig.get(_fileName, _folder, reference.PATH_PLAYER_LAND);
			String job = (String) CustomConfig.get(_fileName, _folder, reference.PATH_PLAYER_JOB);
			_city = (city != null && city != "null" && city != "NULL") ? RPGManager.citys.get(city) : null;
			_land = (land != null && land != "null" && land != "NULL") ? RPGManager.lands.get(land) : null;
			_job = (RPGManager.getJob(job) != null) ? RPGManager.getJob(job) : null;
			_money = Money.getMoney(_bp);
			if(_job == null){
				set_job(null);
			}
		}else{
			CustomConfig.create(_fileName, _folder);
			CustomConfig.set(_fileName, _folder, reference.PATH_PLAYER_CITY, null);
			CustomConfig.set(_fileName, _folder, reference.PATH_PLAYER_LAND, null);
			CustomConfig.set(_fileName, _folder, reference.PATH_PLAYER_JOB, null);
		}
	}

	/**
	 * @return the land
	 */
	public OLLand getLand() {
		return _land;
	}

	/**
	 * @param land to set
	 */
	public void setLand(OLLand _land) {
		this._land = _land;
		
		CustomConfig.set(_fileName, _folder, reference.PATH_PLAYER_LAND, _land.toString());
	}

	/**
	 * @return the city
	 */
	public OLCity get_city() {
		return _city;
	}

	/**
	 * @param city to set
	 */
	public void setCity(OLCity _city) {
		this._city = _city;
		
		CustomConfig.set(_fileName, _folder, reference.PATH_PLAYER_CITY, _city.getName());
	}

	/**
	 * @return the Bukkit Player
	 */
	public Player getBukkitPlayer() {
		return _bp;
	}
	
	/**Pays the taxes of the player to his city.
	 * 
	 */
	public void payTaxes(){
		
		int tax = get_city().getTax();
		
		if(Money.hasEnoughMoney(getBukkitPlayer(), tax)){
			Money.pay(getBukkitPlayer(), tax);
			get_city().set_money(get_city().get_money() + tax);
		}
		
	}

	/**
	 * @param index The index of the Plot coordinate ( 0, 1)
	 * @return The coordinate of a possible Plot.
	 */
	public Location getPosPlotCoord(int index) {
		return _possPlotCoords[index];
	}

	/**
	 * @param location the location to add to possPlotCoords
	 * @param index the Index of the Coordinate.
	 */
	public void addPossPlotCoord(Location location, int index) {
		this._possPlotCoords[index] = location;
	}

	/**
	 * @return the job
	 */
	public OLJob get_job() {
		return _job;
	}

	/**
	 * @param _job the OLJob to change to
	 */
	public boolean changeJob(OLJob _job) {
		int changePrice = reference.JOB_CHANGE_PRICE;
		if(changePrice >= 0){
			if(hasEnoughMoney(changePrice)){
				pay(changePrice);
				set_job(_job);
				return true;
			}
		}
		return false;
	}

	/**
	 * @param job the OLJob to set
	 */
	public void set_job(OLJob job) {
		this._job = job;
		CustomConfig.set(_fileName, _folder, reference.PATH_PLAYER_JOB, job);
	}

	/**
	 * @return the Money
	 */
	public int getMoney() {
		return _money;
	}
	
	/**Sets Money to n Amount
	 * @param n Money amount
	 */
	public void setMoney(int n){
		CustomConfig.set(_fileName, _folder, reference.PATH_PLAYER_MONEY, n);
		_money = n;
	}
	
	/**Adds Money to players Money
	 * @param n Amount of Money to add.
	 */
	public void addMoney(int n){
		
		setMoney(_money + n);
	}
	
	/**Checks if Player has enough money
	 * @param n Amount to check
	 * @return true if he has enough money.
	 */
	public boolean hasEnoughMoney(int n){
		if(_money - n >= 0){
			return true;
		}
		return false;
	}
	
	/**Removes n Money from Player
	 * @param n Amount to remove
	 * @return true if success, false if not enough money. 
	 */
	public boolean pay(int n){
		int rest = _money - n;
		if(rest >= 0){
			setMoney(_money);
			return true;
		}
		return false;
		
	}
}
