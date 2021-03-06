package com.mcoldlife.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.essentials.mcoldlife.main.Reference;
import com.mcoldlife.items.Conquester;
import com.mcoldlife.objects.OLCity;
import com.mcoldlife.objects.OLLand;
import com.mcoldlife.objects.RPGManager;
import com.mcoldlife.objects.RPPlayer;
import com.mcoldlife.rpg.lands;
import com.mcoldlife.rpg.pMsg;

public class land implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(!label.equalsIgnoreCase("land"))return false;
		if(sender instanceof Player){
			Player p = (Player) sender;
			if(args.length >= 1){
				switch(args[0].toLowerCase()){
				case "create":
					executeSubCreate(args, p);
					break;
				case "join":
					executeSubJoin(args, p);
					break;
				case "list":
					executeSubList(sender, args, p);
					break;
				case "conquester":
					executeSubConquester(p);
					break;
				default:
					p.sendMessage(Reference.CHAT_PREFIX + pMsg.ERR_CMD_USAGE_LAND_CREATE);
					break;
				}
			}else{
				//TODO: Add menu to player
				p.sendMessage(Reference.CHAT_PREFIX + pMsg.ERR_CMD_USAGE_LAND);
			}
		}else{
			sender.sendMessage(Reference.CHAT_PREFIX + pMsg.ERR_CMD_SENDER_NOT_PLAYER);
		}
		
		return false;
	}

	private void executeSubConquester(Player p) {
		Conquester c = new Conquester();
		if(!p.getInventory().contains(c.getItemStack())) {
			p.getInventory().addItem(c.getItemStack());
		}else{
			p.sendMessage(Reference.CHAT_PREFIX + pMsg.ERR_CMD_USAGE_LAND_CONQUESTER);
		}
	}

	private void executeSubList(CommandSender sender, String[] args, Player p) {
		if(args.length == 1){
			printLands(sender);
		}else if(args.length == 2){
			switch(args[1]){
			case "citys":
				printCitys(p);
				break;
			default:
				p.sendMessage(Reference.CHAT_PREFIX + pMsg.ERR_CMD_USAGE_LAND_LIST);
				break;
			}
		}else{
			p.sendMessage(Reference.CHAT_PREFIX + pMsg.ERR_CMD_USAGE_LAND_LIST);
		}
	}

	/**Excecute's sub command join
	 * @param args Command arguments
	 * @param p command sender Player
	 */
	private void executeSubJoin(String[] args, Player p) {
		if(args.length == 2){
			RPPlayer player = RPGManager.getPlayer(p);
			String landName = args[1];
			lands.joinLand(player, landName);
		}else{
			p.sendMessage(Reference.CHAT_PREFIX + pMsg.ERR_CMD_USAGE_LAND_JOIN);
		}
	}

	/**Excecute's Sub command create
	 * @param args Command arguments
	 * @param p command sender Player
	 */
	private void executeSubCreate(String[] args, Player p) {
		if(args.length == 2){
			String landName = args[1];
			RPPlayer founder = RPGManager.getPlayer(p);
			if(lands.createLand(landName, founder)){
				p.sendMessage(Reference.CHAT_PREFIX + pMsg.MSG_LAND_CREATED.replace("{land}", landName));
			}
		}else{
			p.sendMessage(Reference.CHAT_PREFIX + pMsg.ERR_CMD_USAGE_LAND_CREATE);
		}
	}
	
	/**Prints city's to player
	 * @param p CommandSender 
	 */
	private void printCitys(Player player){
		RPPlayer p = RPGManager.getPlayer(player);
		OLLand land = p.getLand();
		final String list = "�8----------- �aCitys of your Land�8-----------";
		player.sendMessage(list);
		for(OLCity city : land.getCitys()){
			player.sendMessage("�8- �a" + city.getName());
		}
	}
	
	/**Prints all lands to CommandSender p
	 * @param p CommandSender
	 */
	private void printLands(CommandSender p){
		
		final String list = "�8----------- �aLands �8-----------";
		p.sendMessage(list);
		for(OLLand land : RPGManager.getLands().values()){
			p.sendMessage("�8- �a" + land.getName());
		}
		
	}

}
