package com.mcoldlife.events;

import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import com.mcoldlife.objects.OLCity;
import com.mcoldlife.objects.RPPlayer;

public class ExitCityEvent extends Event implements Cancellable{

	private RPPlayer player;
	private OLCity city;
	private boolean cancelled;
	
	public ExitCityEvent(String event, RPPlayer player, OLCity city) {
		super();
		this.player = player;
		this.city = city;
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
		return null;
	}

	/**
	 * @return the player
	 */
	public RPPlayer getPlayer() {
		return player;
	}

	/**
	 * @return the city
	 */
	public OLCity getCity() {
		return city;
	}

}
