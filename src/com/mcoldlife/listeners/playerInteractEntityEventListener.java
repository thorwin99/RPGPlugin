package com.mcoldlife.listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;

import com.mcoldlife.events.PlayerStealEvent;
import com.mcoldlife.objects.RPGManager;
import com.mcoldlife.objects.RPPlayer;
import com.mcoldlife.objects.jobs.Dieb;

public class playerInteractEntityEventListener implements Listener{

	public void onInteractWithEntity(PlayerInteractEntityEvent e){
		Player exec = e.getPlayer();
		RPPlayer rpExec = RPGManager.getPlayer(exec);
		
		if(rpExec.get_job().getName() == Dieb.NAME){
			if(e.getRightClicked().getType() == EntityType.PLAYER){
				Player stolen = (Player) e.getRightClicked();
				RPPlayer rpStolen = RPGManager.getPlayer(stolen);
				Bukkit.getServer().getPluginManager().callEvent(new PlayerStealEvent(rpExec, rpStolen));
			}
		}
	}
}
