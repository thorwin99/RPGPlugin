package com.mcoldlife.rpg;

import org.bukkit.entity.Player;

import com.essentials.mcoldlife.main.CustomConfig;
import com.mcoldlife.objects.RPPlayer;

public class Money {

	//Player Functions
	/**Removes n Money from Player
	 * @param p Player to remove from
	 * @param n Amount to remove
	 * @return true if success, false if not enough money. 
	 */
	public static boolean pay(Player p, int n){
		
		int playerMoney = getMoney(p);
		int rest = playerMoney - n;
		if(rest >= 0){
			setMoney(p, playerMoney);
			return true;
		}
		return false;
		
	}
	
	/**Gets Money of Player
	 * @param p Player
	 * @return Money of the Player. -1 if player is unknown.
	 */
	public static int getMoney(Player p){
		String file = p.getUniqueId().toString() + ".yml";
		if(CustomConfig.exists(file, reference.FOLDER_PLAYERS.toString())) 
			return (int) CustomConfig.get(file, reference.FOLDER_PLAYERS.toString(), reference.PATH_PLAYER_MONEY);
		return -1;
	}
	
	/**Sets p Money to n Amount
	 * @param p Player
	 * @param n Money amount
	 */
	public static void setMoney(Player p, int n){
		String file = p.getUniqueId().toString() + ".yml";
		if(CustomConfig.exists(file, reference.FOLDER_PLAYERS.toString())){
			CustomConfig.set(file, reference.FOLDER_PLAYERS.toString(), reference.PATH_PLAYER_MONEY, n);
		}
		
	}
	
	/**Adds Money to players Money
	 * @param p Player to add Money to
	 * @param n Amount of Money to add.
	 */
	public static void addMoney(Player p, int n){
		
		int playerMoney = getMoney(p);
		setMoney(p, playerMoney + n);
	}
	
	/**Checks if Player has enough money
	 * @param p Player to check
	 * @param n Amount to check
	 * @return true if he has enough money.
	 */
	public static boolean hasEnoughMoney(Player p, int n){
		
		int playerMoney = getMoney(p);
		if(playerMoney - n >= 0){
			return true;
		}
		return false;
	}
	
	//RPPLayer Functions
	/**Removes n Money from Player
	 * @param p Player to remove from
	 * @param n Amount to remove
	 * @return true if success, false if not enough money. 
	 */
	public static boolean pay(RPPlayer p, int n){
		return pay(p.getBukkitPlayer(), n);
	}
	
	/**Gets Money of Player
	 * @param p Player
	 * @return Money of the Player. -1 if player is unknown.
	 */
	public static int getMoney(RPPlayer p){
		return getMoney(p.getBukkitPlayer());
	}
	
	/**Sets p Money to n Amount
	 * @param p Player
	 * @param n Money amount
	 */
	public static void setMoney(RPPlayer p, int n){
		setMoney(p.getBukkitPlayer(), n);
		
	}
	
	/**Adds Money to players Money
	 * @param p Player to add Money to
	 * @param n Amount of Money to add.
	 */
	public static void addMoney(RPPlayer p, int n){
		addMoney(p.getBukkitPlayer(), n);
	}

	/**Checks if Player has enough money
	 * @param p Player to check
	 * @param n Amount to check
	 * @return true if he has enough money.
	 */
	public static boolean hasEnoughMoney(RPPlayer p, int n){
		
		return hasEnoughMoney(p.getBukkitPlayer(), n);
	}
	
}
