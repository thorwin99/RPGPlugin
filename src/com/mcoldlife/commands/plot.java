package com.mcoldlife.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.essentials.mcoldlife.main.Reference;
import com.mcoldlife.ItemStacks.PositionStickStack;
import com.mcoldlife.objects.OLCity;
import com.mcoldlife.objects.OLPlot;
import com.mcoldlife.objects.RPGManager;
import com.mcoldlife.objects.RPPlayer;
import com.mcoldlife.rpg.lands;
import com.mcoldlife.rpg.pMsg;

public class plot implements CommandExecutor{

	String prefix = Reference.CHAT_PREFIX;
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(!label.equalsIgnoreCase("plot"))return false;
		if(sender instanceof Player){
			Player p = (Player) sender;
			if(args.length >= 1){
				switch(args[0].toLowerCase()){
				case "create":
					excecuteSubCreate(args, p);
					break;
				case "pos":
					excecuteSubPos(args, p);
					break;
				case "list":
					excecuteSubList(args, p);
					break;
				case "claim":
					excecuteSubClaim(args, p);
					break;
				case "unclaim":
					excecuteSubUnclaim(args, p);
					break;
				case "delete":
					excecuteSubDelete(args, p);
					break;
				case "set":
					excecuteSubSet(args, p);
					break;
				default:
					//TODO: print help
					break;
				}
			}
		}else{
			sender.sendMessage(prefix + pMsg.ERR_CMD_SENDER_NOT_PLAYER);
		}
		
		return false;
	}

	private void excecuteSubPos(String[] args, Player p) {
		RPPlayer player = RPGManager.getPlayer(p);
		if(args.length == 2){
			try{
			Integer.parseInt(args[1]);
			}catch(NumberFormatException e){
				p.sendMessage(prefix + pMsg.ERR_CMD_VALUE_NOT_INT);
				return;
			}
			switch(args[1]){
			case "1":
				if(lands.addPlotCorner(player, 0, p.getLocation())){
					p.sendMessage(prefix + pMsg.MSG_PLOT_POS_SET.replace("{pos}", "1"));
				}
				break;
			case "2":
				if(lands.addPlotCorner(player, 1, p.getLocation())){
					p.sendMessage(prefix + pMsg.MSG_PLOT_POS_SET.replace("{pos}", "2"));
				}
				break;
			default:
				p.sendMessage(prefix + pMsg.ERR_CMD_USAGE_PLOT_POS);
				break;
			}
		}else if(args.length == 1){
			PositionStickStack stick = new PositionStickStack();
			if(!p.getInventory().contains(stick)){
				p.getInventory().addItem(stick);
			}
		}else{
			p.sendMessage(prefix + pMsg.ERR_CMD_USAGE_PLOT_POS);
		}
	}

	private void excecuteSubUnclaim(String[] args, Player p) {
		RPPlayer player = RPGManager.getPlayer(p);
		if(args.length == 1){
			if(lands.unClaimPlot(player)){
				p.sendMessage(prefix + pMsg.MSG_PLOT_UNCLAIMED);
			}
		}else{
			p.sendMessage(prefix + pMsg.ERR_CMD_USAGE_PLOT_UNCLAIM);
		}
	}

	private void excecuteSubClaim(String[] args, Player p) {
		RPPlayer player = RPGManager.getPlayer(p);
		if(args.length == 2){
			String plotName = args[1];
			if(lands.claimPlot(player, plotName)){
				p.sendMessage(prefix + pMsg.MSG_PLOT_CLAIMED.replace("{plot}", plotName));
			}
		}else if(args.length == 1){
			if(lands.claimPlot(player, null)){
				String plotname = player.get_city().getPlot(player).getName();
				p.sendMessage(prefix + pMsg.MSG_PLOT_CLAIMED.replace("{plot}", plotname));
			}
		}else{
			p.sendMessage(prefix + pMsg.ERR_CMD_USAGE_PLOT_CLAIM);
		}
	}

	private void excecuteSubList(String[] args, Player p) {
		RPPlayer rp = RPGManager.getPlayer(p);
		final String list = "§8----------- §aPlots §8-----------";
		if(args.length == 2){
			if(RPGManager.citys.containsKey(args[1])){
				OLCity city = RPGManager.getCity(args[1]);
				p.sendMessage(list);
				for(OLPlot plot : city.getPlots()){
					if(plot.getOwner() == null){
						p.sendMessage("§8- §a" + plot.getName() + " §8: §6" + plot.getPrice() + " §6Gold");
					}else if(plot.getOwner() == p.getUniqueId()){
						p.sendMessage("§8- §c" + plot.getName() + " §8: §Gekauft");
					}else{
						p.sendMessage("§8- §c" + plot.getName() + " §8: §6Verkauft");
					}
				}
			}else{
				p.sendMessage(prefix + pMsg.ERR_CITY_NOT_EXISTS);
			}
		}else if(args.length == 1){
			if(rp.get_city() != null){
				OLCity city = rp.get_city();
				p.sendMessage(list);
				for(OLPlot plot : city.getPlots()){
					if(plot.getOwner() == null){
						p.sendMessage("§8- §a" + plot.getName() + " §8: §6" + plot.getPrice() + " §6Gold");
					}else if(plot.getOwner() == p.getUniqueId()){
						p.sendMessage("§8- §c" + plot.getName() + " §8: §Gekauft");
					}else{
						p.sendMessage("§8- §c" + plot.getName() + " §8: §6Verkauft");
					}
				}
			}else{
				p.sendMessage(prefix +pMsg.ERR_PLAYER_NOT_IN_CITY);
			}
		}else{
			p.sendMessage(prefix + pMsg.ERR_CMD_USAGE_PLOT_LIST);
		}
	}

	private void excecuteSubSet(String[] args, Player p) {
		if(args.length == 4){
			switch(args[2].toLowerCase()){
			case "price":
				RPPlayer player = RPGManager.getPlayer(p);
				int price = -1;
				try{
					price = Integer.parseUnsignedInt(args[3]);
				}catch(NumberFormatException e){
					p.sendMessage(prefix + pMsg.ERR_CMD_VALUE_NOT_INT);
					break;
				}
				if(price != -1){
					if(lands.setPlotPrice(player, price, args[1])){
						p.sendMessage(prefix + pMsg.MSG_PLOT_SET_PRICE.replace("{plot}", args[1]).replace("{price}", "" + price));
					}
				}
				break;
			default:
				p.sendMessage(prefix + pMsg.ERR_CMD_USAGE_CITY_SET);
				break;
			}
		}else{
			p.sendMessage(prefix + pMsg.ERR_CMD_USAGE_CITY_SET);
		}
	}

	private void excecuteSubDelete(String[] args, Player p) {
		if(args.length == 2){
			String plotName = args[1];
			RPPlayer cityOwner = RPGManager.getPlayer(p);
			if(lands.deletePlot(cityOwner, plotName)){
				p.sendMessage(prefix + pMsg.MSG_PLOT_DELETED.replace("{plot}", plotName));
			}
		}else{
			p.sendMessage(prefix + pMsg.ERR_CMD_USAGE_PLOT_DELETE);
		}
	}

	private void excecuteSubCreate(String[] args, Player p) {
		if(args.length == 3){
			String plotName = args[1];
			int price = -1;
			try{
				price = Integer.parseInt(args[2]);
			}catch(NumberFormatException e){
				p.sendMessage(prefix + pMsg.ERR_CMD_VALUE_NOT_INT);
				return;
			}
			RPPlayer cityOwner = RPGManager.getPlayer(p);
			if(lands.createPlot(plotName, cityOwner, price)){
				p.sendMessage(prefix + pMsg.MSG_PLOT_CREATED.replace("{plot}", plotName));
			}
		}else{
			p.sendMessage(prefix + pMsg.ERR_CMD_USAGE_PLOT_CREATE);
		}
	}

}
