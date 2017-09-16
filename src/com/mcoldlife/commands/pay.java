package com.mcoldlife.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.essentials.mcoldlife.main.Reference;
import com.mcoldlife.objects.RPGManager;
import com.mcoldlife.objects.RPPlayer;
import com.mcoldlife.rpg.pMsg;

public class pay implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if(sender instanceof Player){
			RPPlayer p = RPGManager.getPlayer((Player) sender);
			if(label.equalsIgnoreCase("pay")){
				
				if(args.length == 2){
					int amount = 0;
					try{
						amount = Integer.parseInt(args[1]);
					}catch(NumberFormatException e) {
						p.getBukkitPlayer().sendMessage(Reference.CHAT_PREFIX + "§c/pay <empfaenger> <menge>");
						return false;
					}
					String otherPlayer = args[0];
					RPPlayer player = RPGManager.getPlayer(Bukkit.getPlayerExact(otherPlayer));
					if(player == null){
						p.getBukkitPlayer().sendMessage(Reference.CHAT_PREFIX + "§cDer Spieler §6" + otherPlayer + " §c ist nicht online.");
						return false;
					}
					if(p.hasEnoughMoney(amount)){
						
						p.pay(amount);
						player.addMoney(amount);
						p.getBukkitPlayer().sendMessage(Reference.CHAT_PREFIX + "§aDu hast §6" + amount + " Gold §a an §6" + otherPlayer + " §agezahlt.");
						player.getBukkitPlayer().sendMessage(Reference.CHAT_PREFIX + "§aDu hast §6" + amount + " Gold §a von §6" + p.getBukkitPlayer().getDisplayName() + " §abekommen.");
						
					}else{
						p.getBukkitPlayer().sendMessage(Reference.CHAT_PREFIX + "§cDu hast zu wenig Gold.");
					}
					
				}else{
					p.getBukkitPlayer().sendMessage(Reference.CHAT_PREFIX + "§c/pay <empfaenger> <menge>");
				}
				
			}
			
		}else{
			sender.sendMessage(Reference.CHAT_PREFIX + pMsg.ERR_CMD_SENDER_NOT_PLAYER);
		}
		
		return false;
	}

}
