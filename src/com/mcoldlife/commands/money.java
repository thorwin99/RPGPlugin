package com.mcoldlife.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import com.essentials.mcoldlife.main.Reference;
import com.mcoldlife.objects.RPGManager;
import com.mcoldlife.objects.RPPlayer;
import com.mcoldlife.rpg.pMsg;

public class money implements CommandExecutor{
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if(sender instanceof ConsoleCommandSender) {
			sender.sendMessage(Reference.CHAT_PREFIX + pMsg.ERR_CMD_SENDER_NOT_PLAYER);
			return false;
		}
		
		RPPlayer p = RPGManager.getPlayer((Player) sender);
		
		if(label.equalsIgnoreCase("money"))
			p.getBukkitPlayer().sendMessage(Reference.CHAT_PREFIX + "§aDu hast §6" + p.getMoney() + " Gold.");
		return false;
	}

}
