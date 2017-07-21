package com.mcoldlife.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.essentials.mcoldlife.main.CustomConfig;
import com.essentials.mcoldlife.main.Reference;
import com.mcoldlife.objects.OLCity;
import com.mcoldlife.objects.OLLand;
import com.mcoldlife.objects.RPGManager;
import com.mcoldlife.objects.RPPlayer;
import com.mcoldlife.rpg.lands;
import com.mcoldlife.rpg.pMsg;
import com.mcoldlife.rpg.reference;

public class land implements CommandExecutor{

	String prefix = Reference.CHAT_PREFIX;
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(!label.equalsIgnoreCase("land"))return false;
		if(sender instanceof Player){
			Player p = (Player) sender;
			if(args.length >= 1){
				switch(args[0].toLowerCase()){
				case "create":
					excecuteSubCreate(args, p);
					break;
				case "join":
					excecuteSubJoin(args, p);
					break;
				case "list":
					if(args.length == 1){
						printLands(sender);
					}else if(args.length == 2){
						switch(args[1]){
						case "citys":
							printCitys(p);
							break;
						default:
							p.sendMessage(prefix + pMsg.ERR_CMD_USAGE_LAND_LIST);
							break;
						}
					}else{
						p.sendMessage(prefix + pMsg.ERR_CMD_USAGE_CITY_LIST);
					}
					break;
				default:
					p.sendMessage(prefix + pMsg.ERR_CMD_USAGE_LAND_CREATE);
					break;
				}
			}else{
				//TODO: Add menu to player
				p.sendMessage(prefix + pMsg.ERR_CMD_USAGE_LAND_CREATE);
			}
		}else{
			sender.sendMessage(Reference.CHAT_PREFIX + pMsg.ERR_CMD_SENDER_NOT_PLAYER);
		}
		
		return false;
	}

	/**Excecute's sub command join
	 * @param args Command arguments
	 * @param p command sender Player
	 */
	private void excecuteSubJoin(String[] args, Player p) {
		if(args.length == 2){
			RPPlayer player = RPGManager.getPlayer(p);
			String landName = args[1];
			lands.joinLand(player, landName);
		}else{
			p.sendMessage(prefix + pMsg.ERR_CMD_USAGE_LAND_JOIN);
		}
	}

	/**Excecute's Sub command create
	 * @param args Command arguments
	 * @param p command sender Player
	 */
	private void excecuteSubCreate(String[] args, Player p) {
		if(args.length == 2){
			String landName = args[1];
			RPPlayer founder = RPGManager.getPlayer(p);
			if(lands.createLand(landName, founder)){
				p.sendMessage(prefix + pMsg.MSG_LAND_CREATED.replace("{land}", landName));
			}
		}else{
			p.sendMessage(prefix + pMsg.ERR_CMD_USAGE_LAND_CREATE);
		}
	}
	
	/**Prints city's to player
	 * @param p CommandSender 
	 */
	private void printCitys(Player player){
		RPPlayer p = RPGManager.getPlayer(player);
		OLLand land = p.getLand();
		final String list = "§8----------- §aCitys §8-----------";
		player.sendMessage(list);
		for(OLCity city : land.getCitys()){
			player.sendMessage("§8- §a" + city.getName());
		}
	}
	
	/**Prints all lands to CommandSender p
	 * @param p CommandSender
	 */
	private void printLands(CommandSender p){
		String[] lands = CustomConfig.getFilesInFolder(reference.FOLDER_CITYS.toString());
		final String list = "§8----------- §Lands §8-----------";
		p.sendMessage(list);
		for(String land : lands){
			p.sendMessage("§8- §a" + land);
		}
		
	}

}
