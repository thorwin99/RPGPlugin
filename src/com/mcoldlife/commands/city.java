package com.mcoldlife.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.essentials.mcoldlife.main.CustomConfig;
import com.essentials.mcoldlife.main.Reference;
import com.mcoldlife.objects.OLCity;
import com.mcoldlife.objects.OLPlot;
import com.mcoldlife.objects.RPGManager;
import com.mcoldlife.objects.RPPlayer;
import com.mcoldlife.rpg.lands;
import com.mcoldlife.rpg.pMsg;
import com.mcoldlife.rpg.reference;

public class city implements CommandExecutor{
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(!label.equalsIgnoreCase("city"))return false;
		if(sender instanceof Player){
			Player p = (Player) sender;
			if(args.length >= 1){
				switch(args[0].toLowerCase()){	
				case "list":
					excecuteSubList(sender, args, p);
					break;
				case "create":
					excecuteSubCreate(args, p);
					break;
				case "set":
					excecuteSubSet(args, p);
					break;
				case "money":
					excecuteSubMoney(args, p);
					break;
				default:
					p.sendMessage(Reference.CHAT_PREFIX + pMsg.ERR_CMD_USAGE_CITY);
					break;
				}
			}else{
				//TODO add menu with settings to Player screen
				p.sendMessage(Reference.CHAT_PREFIX + pMsg.ERR_CMD_USAGE_CITY);
				showMenu(p);
			}
		}else{
		}
		return false;
	}

	/**Excecute's City sub command set
	 * @param args Command arguments
	 * @param p Player executor
	 */
	private void excecuteSubSet(String[] args, Player p) {
		if(args.length == 3){
			switch(args[1].toLowerCase()){
			case "tax":
				RPPlayer player = RPGManager.getPlayer(p);
				OLCity city = player.get_city();
				if(city.getOwner() != p.getUniqueId()){
					p.sendMessage(Reference.CHAT_PREFIX + pMsg.ERR_PLAYER_NOT_OWNER_OF_CITY);
					break;
				}
				int tax = -1;
				try{
					tax = Integer.parseUnsignedInt(args[2]);
				}catch(NumberFormatException e){
					p.sendMessage(Reference.CHAT_PREFIX + pMsg.ERR_CMD_VALUE_NOT_INT);
					break;
				}
				if(tax != -1){
					city.setTax(tax);
					p.sendMessage(Reference.CHAT_PREFIX + pMsg.MSG_CITY_SET_TAX.replace("{city}", city.getName()).replace("{tax}", "" + tax));
				}
				break;
			default:
				p.sendMessage(Reference.CHAT_PREFIX + pMsg.ERR_CMD_USAGE_CITY_SET);
				break;
			}
			
			
		}else{
			p.sendMessage(Reference.CHAT_PREFIX + pMsg.ERR_CMD_USAGE_CITY_SET);
		}
	}
	
	/**Excecute's City sub command money
	 * @param args command arguments
	 * @param p Player executer
	 */
	private void excecuteSubMoney(String[] args, Player p) {
		if(args.length == 1){
			RPPlayer player = RPGManager.getPlayer(p);
			if(player.get_city().getOwner() == p.getUniqueId()){
				p.sendMessage(Reference.CHAT_PREFIX + pMsg.MSG_CITY_MONEY.replace("{city}", player.get_city().getName()).replace("{money}", (new Integer(player.get_city().get_money()).toString())));
			}else{
				p.sendMessage(Reference.CHAT_PREFIX + pMsg.ERR_PLAYER_NOT_OWNER_OF_CITY);
			}
		}else{
			p.sendMessage(Reference.CHAT_PREFIX + pMsg.ERR_CMD_USAGE_CITY_MONEY);
		}
	}
	
	/**Excecute's City sub command create
	 * @param args Command arguments
	 * @param p Player executer
	 */
	private void excecuteSubCreate(String[] args, Player p) {
		if(args.length == 2){
			String cityName = args[1];
			RPPlayer founder = RPGManager.getPlayer(p);
			if(lands.createCity(cityName, founder)){
				p.sendMessage(Reference.CHAT_PREFIX + pMsg.MSG_CITY_CREATED.replace("{city}", cityName));
			}
		}else{
			p.sendMessage(Reference.CHAT_PREFIX + pMsg.ERR_CMD_USAGE_CITY_CREATE);
		}
	}
	
	/**Excecute's City sub command List
	 * @param sender CommandSender
	 * @param args Command arguments
	 * @param p Player executer
	 */
	private void excecuteSubList(CommandSender sender, String[] args, Player p) {
		if(args.length == 1){
			printCitys(sender);
		}else if(args.length == 2){
			switch(args[1].toLowerCase()){
			case "plots":
				printPlots(p);
				break;
			default:
				p.sendMessage(Reference.CHAT_PREFIX + pMsg.ERR_CMD_USAGE_CITY_LIST);
				break;
			}
		}else{
		}
	}
	
	/**Prints city's to player
	 * @param p CommandSender 
	 */
	private void printCitys(CommandSender p){
		String[] citys = CustomConfig.getFilesInFolder(reference.FOLDER_CITYS.toString());
		final String list = "§8----------- §aCitys §8-----------";
		p.sendMessage(list);
		for(String city : citys){
			p.sendMessage("§8- §a" + city);
		}
	}

	/**Prints city Plots to City Owner
	 * @param p City Owner
	 */
	private void printPlots(Player player){
		RPPlayer p = RPGManager.getPlayer(player);
		OLCity city = p.get_city();
		final String list = "§8----------- §aCitys §8-----------";
		player.sendMessage(list);
		for(OLPlot plot : city.getPlots()){
			player.sendMessage("§8- §a" + plot.getName());
		}
	}

	private void showMenu(Player p){
		
	}
}
