package com.mcoldlife.events;

import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import com.mcoldlife.objects.OLLand;
import com.mcoldlife.objects.RPPlayer;

public class EnterLandEvent extends Event implements Cancellable{

	private RPPlayer player;
	private OLLand land;
	private boolean cancelled;
	
	public EnterLandEvent(String event, RPPlayer player, OLLand land) {
		super();
		this.player = player;
		this.land = land;
	}

	@Override
	public boolean isCancelled() {
		// TODO Auto-generated method stub
		return cancelled;
	}

	@Override
	public void setCancelled(boolean arg0) {
		cancelled = arg0;
		
	}

	@Override
	public HandlerList getHandlers() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @return the player
	 */
	public RPPlayer getPlayer() {
		return player;
	}

	/**
	 * @return the land
	 */
	public OLLand getLand() {
		return land;
	}

}