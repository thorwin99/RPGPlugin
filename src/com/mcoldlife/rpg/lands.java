package com.mcoldlife.rpg;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import com.essentials.mcoldlife.main.CustomConfig;
import com.essentials.mcoldlife.main.Reference;
import com.mcoldlife.objects.OLChunk;
import com.mcoldlife.objects.OLCity;
import com.mcoldlife.objects.OLLand;
import com.mcoldlife.objects.OLPlot;
import com.mcoldlife.objects.RPGManager;
import com.mcoldlife.objects.RPPlayer;
import com.mcoldlife.objects.Vector2D;

public class lands {

	static String prefix = Reference.CHAT_PREFIX;
	
	/**Creates a new Land based on the name and founder. This function
	 * sends Messages to the Player except the created Message.
	 * @param landName The name for the Land
	 * @param founder Founder of the land and the owner of it.
	 * @return true if the Land was successfully created.
	 */
	public static boolean createLand(String landName, RPPlayer founder){
		Player p = founder.getBukkitPlayer();
		
		if(CustomConfig.exists(landName + ".yml", reference.FOLDER_LANDS.toString())){
			p.sendMessage(prefix + pMsg.ERR_LAND_EXISTS);
			return false;
		}
		
		if(founder.getLand() == null){
			OLChunk baseChunk = RPGManager.getChunk(founder.getBukkitPlayer().getLocation().getChunk());
			if(baseChunk.getLand() == null){
				baseChunk.setLand(landName);
				
				OLLand land = new OLLand(landName);
				RPGManager.addLand(landName, land);
				founder.setLand(RPGManager.lands.get(landName));
				return land.createLand(founder, baseChunk);
				
			}else{
				p.sendMessage(prefix + pMsg.ERR_CHUNK_HAS_LAND);
				return false;
			}
			
			
		}else{
			p.sendMessage(prefix + pMsg.ERR_PLAYER_IN_LAND);
			return false;
		}
	}
	
	/**Creates a new City based on the name and founder. This function
	 * sends Messages to the Player except the created Message.
	 * @param cityName The name for the City
	 * @param founder Founder of the City and the owner of it.
	 * @return true if the City was successfully created.
	 */
	public static boolean createCity(String cityName, RPPlayer founder){
		Player p = founder.getBukkitPlayer();
		
		if(CustomConfig.exists(cityName + ".yml", reference.FOLDER_CITYS.toString())){
			p.sendMessage(prefix + pMsg.ERR_CITY_EXISTS);
			return false;
		}
		
		if(founder.get_city() == null){
			OLChunk baseChunk = RPGManager.getChunk(founder.getBukkitPlayer().getLocation().getChunk());
			if(baseChunk.getCity() == null){
				if(baseChunk.getLand() == null){
					baseChunk.setLand(founder.getLand().getName());
				}
				if(baseChunk.getLand().equals(founder.getLand().getName())){
					OLCity city = new OLCity(cityName);
					RPGManager.citys.put(cityName, city);
					founder.setCity(city);
					return city.createCity(founder, baseChunk);
				}else{
					p.sendMessage(prefix + pMsg.ERR_CHUNK_OWNED_BY_OTHER_LAND);
					return false;
				}
				
			}else{
				p.sendMessage(prefix + pMsg.ERR_CHUNK_HAS_CITY);
				return false;
			}
			
			
		}else{
			p.sendMessage(prefix + pMsg.ERR_PLAYER_IN_CITY);
			return false;
		}
	}
	
	/**Creates a new Plot inside the City
	 * @param plotName Name of the Plot
	 * @param cityOwner Owner of the City
	 * @return
	 */
	public static boolean createPlot(String plotName, RPPlayer cityOwner, int price){
		Player p = cityOwner.getBukkitPlayer();
		OLCity city = cityOwner.get_city();
		
		if(city == null){
			p.sendMessage(prefix + pMsg.ERR_PLAYER_NOT_IN_CITY);
			return false;
		}
		
		//Checks if Player has Coordinates for the Plot.
		if(cityOwner.getPosPlotCoord(0) == null){
			p.sendMessage(prefix + pMsg.ERR_POS1_NOT_SET);
			return false;
		}else if(cityOwner.getPosPlotCoord(1) == null){
			p.sendMessage(prefix + pMsg.ERR_POS2_NOT_SET);
			return false;
		}
		
		if(isCityOnwer(cityOwner)){
			if(!city.plotExists(plotName)){
				//Make Plot
				return city.makePlot(plotName, new Vector2D(cityOwner.getPosPlotCoord(0)), new Vector2D(cityOwner.getPosPlotCoord(1)), price);
			}else{
				p.sendMessage(prefix + pMsg.ERR_PLOT_EXISTS);
			}
		}else{
			p.sendMessage(prefix + pMsg.ERR_PLAYER_NOT_OWNER_OF_CITY);
		}
		
		
		return false;
	}
	
	/**Adds a Corner of a Plot to the Player, to be used later on.
	 * @param cityOwner The Owner of the City, where the Coordinate gets added.
	 * @param index The index of the Corner. 0 is corner 1 and 1 is corner 2
	 * @param corner The Location of the Corner
	 * @return true if it gets added, false if not.
	 */
	public static boolean addPlotCorner(RPPlayer cityOwner, int index, Location corner){
		Player p = cityOwner.getBukkitPlayer();
		
		if(cityOwner.get_city() == null){
			p.sendMessage(prefix + pMsg.ERR_PLAYER_NOT_IN_CITY);
			return false;
		}
		
		OLChunk chunk = new OLChunk(corner.getChunk());
		
		if(chunk.getCity() == null) {
			p.sendMessage(prefix + pMsg.ERR_CHUNK_HAS_NO_CITY);
			return false;
		}
		
		if(chunk.getCity().equals(cityOwner.get_city().getName())){
			if(isCityOnwer(cityOwner)){
				if(cityOwner.get_city().inPlot(new Vector2D(corner)) == null){
					cityOwner.addPossPlotCoord(corner, index);
					return true;
				}else{
					p.sendMessage(prefix + pMsg.ERR_POS_IN_PLOT);
				}
			}else{
				p.sendMessage(prefix + pMsg.ERR_PLAYER_NOT_OWNER_OF_CITY);
			}
		}else{
			p.sendMessage(prefix + pMsg.ERR_POS_NOT_IN_CITY);
		}
		return false;
	}

	/**Deletes a Plot by given Name
	 * @param cityOwner Owner of the City and Command Executor
	 * @param name Name of the Plot
	 * @return true if deleted.				
	 */
	public static boolean deletePlot(RPPlayer cityOwner, String name){
		Player p = cityOwner.getBukkitPlayer();
		OLCity city = cityOwner.get_city();
		if(city == null){
			p.sendMessage(prefix + pMsg.ERR_PLAYER_NOT_IN_CITY);
			return false;
		}
		if(isCityOnwer(cityOwner)){
			if(city.plotExists(name)){
				if(!city.plotOwned(name)){
					city.deletePlot(name);
					return true;
				}else{
					p.sendMessage(prefix + pMsg.ERR_PLOT_OWNED);
				}
			}else{
				p.sendMessage(prefix + pMsg.ERR_PLOT_NOT_EXISTS);
			}
		}else{
			p.sendMessage(prefix + pMsg.ERR_PLAYER_NOT_OWNER_OF_CITY);
		}

		
		return false;
	}

	/**Claims Plot for Player if he doesn't own one
	 * @param player Player who claims
	 * @param name Name of the Plot. null if you want to use local position.
	 * @return true if claimed.
	 */
	public static boolean claimPlot(RPPlayer player, String name){
		Player p = player.getBukkitPlayer();
		OLCity city = player.get_city();
		if(city == null){
			if(name == null){
				OLChunk localChunk = RPGManager.getChunk(p.getLocation().getChunk());
				if(player.getLand() == null){
					player.setLand(RPGManager.lands.get(localChunk.getLand()));
				}
				player.setCity(RPGManager.citys.get(localChunk.getCity()));
			}
		}
		
		if(city.getPlot(player) != null){
			p.sendMessage(prefix + pMsg.ERR_PLAYER_OWNS_PLOT);
			return false;
		}
		if(!city.plotOwned(name)){
			if(name == null){
				OLPlot plot = city.inPlot(new Vector2D(p.getLocation()));
				if(plot == null){
					p.sendMessage(prefix + pMsg.ERR_NOT_INSIDE_PLOT);
				}else{
					plot.setOwner(p.getUniqueId());
					return true;
				}
			}else{
				if(!city.plotExists(name)){
					p.sendMessage(prefix + pMsg.ERR_PLOT_NOT_EXISTS);
				}else{
					OLPlot plot = city.getPlot(name);
					plot.setOwner(p.getUniqueId());
					return true;
				}
			}
		}else{
			p.sendMessage(prefix + pMsg.ERR_PLOT_OWNED);
		}
		
		return false;
	}
	
	/**Claims Chunk for city of Player if Player is owner
	 * @param player Owner of the city
	 * @return true if claimed
	 */
	public static boolean claimChunk(RPPlayer player){
		Player p = player.getBukkitPlayer();
		OLChunk chunk = RPGManager.getChunk(p.getLocation().getChunk());
		if(player.getLand() == null) {
			p.sendMessage(prefix + pMsg.ERR_PLAYER_NOT_IN_LAND);
			return false;
		}
		if(player.get_city() == null) {
			p.sendMessage(prefix + pMsg.ERR_PLAYER_NOT_IN_CITY);
			return false;
		}
		if(!player.get_city().getOwner().equals(p.getUniqueId())){
			p.sendMessage(prefix + pMsg.ERR_PLAYER_NOT_OWNER_OF_CITY);
			return false;
		}
		
		if(!player.get_city().getName().equals(chunk.getCity())){
			if(!player.getLand().getName().equals(chunk.getLand())){
				chunk.setCity(player.get_city().getName());
				player.get_city().addChunk(chunk);
				return true;
			}else{
				p.sendMessage(prefix + pMsg.ERR_CHUNK_OWNED_BY_OTHER_LAND);
			}
			
		}else{
			p.sendMessage(prefix + pMsg.ERR_CHUNK_OWNED_BY_SAME_CITY);
		}
		return false;
		
	}

	/**Claims Chunk for Land if Player is Owner
	 * @param player Owner of land
	 * @return True if claimed
	 */
	public static boolean contestChunk(RPPlayer player){
		Player p = player.getBukkitPlayer();
		OLLand land = player.getLand();
		OLChunk chunk = RPGManager.getChunk(p.getLocation().getChunk());
		
		if(land == null) {
			p.sendMessage(prefix + pMsg.ERR_PLAYER_NOT_IN_LAND);
			return false;
		}
		if(!land.getOwner().equals(p.getUniqueId())){//TODO: Soldiers can contest too
			p.sendMessage(prefix + pMsg.ERR_PLAYER_NOT_OWNER_OF_LAND);
			return false;
		}
		
		
		
		if(!land.getName().equals(chunk.getLand())){
			chunk.setLand(null);
			land.addChunk(chunk);
			return true;
		}else{
			p.sendMessage(prefix + pMsg.ERR_CHUNK_OWNED_BY_SAME_LAND);
		}
		
		return false;
	}

	/**Gets the City if player is inside one
	 * @param player Player to check
	 * @return The OLCity or null
	 */
	public static OLCity inCity(RPPlayer player){
		Player p = player.getBukkitPlayer();
		OLChunk chunk = RPGManager.getChunk(p.getLocation().getChunk());
		
		if(RPGManager.citys.containsKey(chunk.getCity()))
		return RPGManager.citys.get(chunk.getCity());
		
		return null;
	}
	
	/**Gets the Land if player is inside one
	 * @param player Player to check
	 * @return The OLLand or null
	 */
	public static OLLand inLand(RPPlayer player){
		Player p = player.getBukkitPlayer();
		OLChunk chunk = RPGManager.getChunk(p.getLocation().getChunk());
		
		if(RPGManager.lands.containsKey(chunk.getLand()))
		return RPGManager.lands.get(chunk.getLand());
		
		return null;
	}
	
	/**Gets the Land if player is inside one
	 * @param player Player to check
	 * @return The OLPlot or null
	 */
	public static OLPlot inPlot(RPPlayer player){
		OLCity city = inCity(player);
		
		if(city != null)
		return city.inPlot(new Vector2D(player.getBukkitPlayer().getLocation()));
		
		return null;
	}
	
	/**Sets the Price of a Plot
	 * @param player RPPlayer, owner of city
	 * @param price Price of Plot
	 * @param plotName Name of Plot
	 * @return
	 */
	public static boolean setPlotPrice(RPPlayer player, int price, String plotName){
		OLCity city = player.get_city();
		Player p = player.getBukkitPlayer();
		if(!isCityOnwer(player))
			p.sendMessage(prefix + pMsg.ERR_PLAYER_NOT_OWNER_OF_CITY);
		
		if(city.plotExists(plotName)){
			OLPlot plot = city.getPlot(plotName);
			if(plot == null) {
				p.sendMessage(prefix + pMsg.ERR_PLOT_NOT_EXISTS);
			}
			if(plot.getOwner() == null){
				city.getPlot(plotName).setPrice(price);
				return true;
			}else{
				p.sendMessage(prefix + pMsg.ERR_PLOT_OWNED);
			}
			
		}else{
			p.sendMessage(prefix + pMsg.ERR_PLOT_NOT_EXISTS);
		}
		
		
		return false;
	}

	/**Checks if Player is Owner of his city.
	 * Function doesnt send Player messages
	 * @param player RPPLayer to check
	 * @return true if player is Owner and False if not.
	 */
	public static boolean isCityOnwer(RPPlayer player){
		
		OLCity city = player.get_city();
		if(city != null){
			if(player.getBukkitPlayer().getUniqueId().equals(city.getOwner())){
				return true;
			}
		}				
		return false;
	}
	
	/**Checks if Player is Owner of his Land.
	 * Function doesn't send Player messages
	 * @param player RPPLayer to check
	 * @return true if player is Owner and False if not.
	 */
	public static boolean isLandOnwer(RPPlayer player){
		
		OLLand land = player.getLand();
		if(land != null){
			if(player.getBukkitPlayer().getUniqueId().equals(land.getOwner())){
				return true;
			}
		}				
		return false;
	}
	
	/**Checks if Player is Owner of his city.
	 * Function doesn't send Player messages
	 * @param player RPPLayer to check
	 * @return true if player is Owner and False if not.
	 */
	public static boolean isCityOnwer(RPPlayer player, OLCity city){
		if(city != null){
			if(player.getBukkitPlayer().getUniqueId().equals(city.getOwner())){
				return true;
			}
		}				
		return false;
	}
	
	/**Checks if Player is Owner of his Land.
	 * Function doesn't send Player messages
	 * @param player RPPLayer to check
	 * @return true if player is Owner and False if not.
	 */
	public static boolean isLandOnwer(RPPlayer player, OLLand land){
		if(land != null){
			if(player.getBukkitPlayer().getUniqueId().equals(land.getOwner())){
				return true;
			}
		}				
		return false;
	}
	
	
	/**Unclaim's Players Plot and adds Price/2 to his money.
	 * @param player
	 * @return
	 */
	public static boolean unClaimPlot(RPPlayer player){
		OLPlot plot = player.get_city().getPlot(player);
		Player p = player.getBukkitPlayer();
		if(plot != null){
			int price = plot.getPrice();
			plot.setOwner(null);
			Money.addMoney(player, price / 2);
			return true;
		}else{
			p.sendMessage(prefix + pMsg.ERR_PLAYER_OWNS_NO_PLOT);
		}
		return false;
		
	}

	/**Set's city taxes
	 * @param player Owner of city
	 * @param tax Tax to set
	 * @return true if set.
	 */ 
	public static boolean setCityTaxes(RPPlayer player, int tax){
		Player p = player.getBukkitPlayer();
		
		if(isCityOnwer(player)){
			player.get_city().setTax(tax);
		}else{
			p.sendMessage(prefix + pMsg.ERR_PLAYER_NOT_OWNER_OF_CITY);
			return false;
		}
		
		return true;
	}

	/**Adds Player to Land
	 * @param player Player to add
	 * @param landName name of land
	 * @return true if added
	 */
	public static boolean joinLand(RPPlayer player, String landName){
		
		if(!RPGManager.getLands().containsKey(landName)){
			player.getBukkitPlayer().sendMessage(prefix + pMsg.ERR_LAND_NOT_EXISTS);
		}
		
		if(player.getLand() == null){
			RPGManager.lands.get(landName).addPlayer(player);
			return true;
		}else{
			player.getBukkitPlayer().sendMessage(prefix + pMsg.ERR_PLAYER_IN_LAND);
		}
		return false;
	}

}
