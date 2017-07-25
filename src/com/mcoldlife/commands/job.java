package com.mcoldlife.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.essentials.mcoldlife.main.Reference;
import com.mcoldlife.objects.RPGManager;
import com.mcoldlife.objects.RPPlayer;
import com.mcoldlife.rpg.pMsg;
import com.mcoldlife.rpg.reference;

public class job implements CommandExecutor{
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(!label.equalsIgnoreCase("job"))return false;
		if(sender instanceof Player){
			Player p = (Player) sender;
			if(args.length >= 1){
				switch(args[0].toLowerCase()){	
				case "list":
					excecuteSubList(p);
					break;
				case "change":
					excecuteSubChange(args, p);
					break;
				case "info":
					p.sendMessage(Reference.CHAT_PREFIX + pMsg.ERR_NOT_IMPLEMENTED);
					break;
				default:
					p.sendMessage(Reference.CHAT_PREFIX + pMsg.ERR_CMD_USAGE_JOB);
					break;
				}
			}else{
				//TODO add menu with settings to Player screen
				p.sendMessage(Reference.CHAT_PREFIX + pMsg.ERR_CMD_USAGE_JOB);
			}
		}else{
			sender.sendMessage(Reference.CHAT_PREFIX + pMsg.ERR_CMD_SENDER_NOT_PLAYER);
		}
		return false;
	}

	private void excecuteSubChange(String[] args, Player p) {
		RPPlayer player = RPGManager.getPlayer(p);
		if(args.length == 2){
			String jobName = args[1];
			if(RPGManager.getJob(jobName) != null){
				if(player.changeJob(RPGManager.getJob(jobName))){
					p.sendMessage(Reference.CHAT_PREFIX + pMsg.MSG_JOB_CHANGED.replace("{job}", args[1]));
				}else{
					p.sendMessage(Reference.CHAT_PREFIX + pMsg.ERR_MONEY_NOT_ENOUGHT + pMsg.ERR_MONEY_NEEDED.replace("{money}", reference.JOB_CHANGE_PRICE + ""));
				}
			}else{
				p.sendMessage(Reference.CHAT_PREFIX + pMsg.ERR_CMD_USAGE_JOB_NOT_FOUND.replace("{job}", args[1]));
			}
			
		}else{
			p.sendMessage(Reference.CHAT_PREFIX + pMsg.ERR_CMD_USAGE_JOB_LIST);
		}
	}

	private void excecuteSubList(Player p) {
		final String list = "§8----------- §aJobs §8-----------";
		p.sendMessage(list);
		for(String job : RPGManager.jobs.keySet()){
			p.sendMessage("§8- §a" + job);
		}
	}

}
