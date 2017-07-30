package com.mcoldlife.events;

import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import com.mcoldlife.objects.OLLand;
import com.mcoldlife.objects.RPPlayer;

public class ExitLandEvent extends Event implements Cancellable{

	private static final HandlerList handlers = new HandlerList();
	private RPPlayer player;
	private OLLand land;
	private boolean cancelled;
	
	public ExitLandEvent(RPPlayer player, OLLand land) {
		super();
		this.player = player;
		this.land = land;
	}

	@Override
	public boolean isCancelled() {
		return cancelled;
	}

	@Override
	public void setCancelled(boolean arg0) {
		cancelled = arg0;
		
	}

	@Override
	public HandlerList getHandlers() {
		return handlers;
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
	
	public static HandlerList getHandlerList() {
	    return handlers;
	}

}