package com.mcoldlife.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.essentials.mcoldlife.main.Reference;
import com.mcoldlife.rpg.Money;

public class pay implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if(sender instanceof Player){
			Player p = (Player) sender;
			
			if(label.equalsIgnoreCase("pay")){
				
				if(args.length == 2){
					int amount = 0;
					try{
						amount = Integer.parseInt(args[1]);
					}catch(NumberFormatException e) {
						p.sendMessage(Reference.CHAT_PREFIX + "§c/pay <empfaenger> <menge>");
						return false;
					}
					String otherPlayer = args[0];
					Player player = Bukkit.getPlayerExact(otherPlayer);
					if(player == null){
						p.sendMessage(Reference.CHAT_PREFIX + "§cDer Spieler §6" + otherPlayer + " §c ist nicht online.");
						return false;
					}
					if(Money.hasEnoughMoney(p, amount)){
						
						Money.pay(p, amount);
						Money.addMoney(player, amount);
						p.sendMessage(Reference.CHAT_PREFIX + "§aDu hast §6" + amount + " Gold §a an §6" + otherPlayer + " §agezahlt.");
						player.sendMessage(Reference.CHAT_PREFIX + "§aDu hast §6" + amount + " Gold §a von §6" + p.getDisplayName() + " §abekommen.");
						
					}else{
						p.sendMessage(Reference.CHAT_PREFIX + "§cDu hast zu wenig Gold.");
					}
					
				}else{
					p.sendMessage(Reference.CHAT_PREFIX + "§c/pay <empfaenger> <menge>");
				}
				
			}
			
		}
		
		return false;
	}

}
