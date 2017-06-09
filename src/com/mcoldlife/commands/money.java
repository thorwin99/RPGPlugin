package com.mcoldlife.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import com.essentials.mcoldlife.main.Reference;
import com.mcoldlife.rpg.Money;
import com.mcoldlife.rpg.rpg;

public class money implements CommandExecutor{

	rpg plugin;
	public money(rpg r){
		this.plugin = r;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if(sender instanceof ConsoleCommandSender) plugin.getLogger().info("Command has to be excecuted by player");
		
		Player p = (Player) sender;
		
		if(label.equalsIgnoreCase("money"))
			p.sendMessage(Reference.CHAT_PREFIX + "§aDu hast §6" + Money.getMoney(p) + " Gold.");
		
		return false;
	}

}
