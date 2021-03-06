package com.mcoldlife.events;

import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import com.mcoldlife.interfaces.conditional;
import com.mcoldlife.objects.RPPlayer;
import com.mcoldlife.objects.jobs.Dieb;

public class PlayerStealEvent extends Event implements Cancellable, conditional<RPPlayer>{

	private static final HandlerList handlers = new HandlerList();
	RPPlayer executor;
	RPPlayer stolen;
	private boolean cancelled;
	
	public PlayerStealEvent(RPPlayer executor, RPPlayer stolen){
		this.stolen = stolen;
		this.executor = executor;
	}
	
	@Override
	public boolean isCancelled() {
		return cancelled;
	}

	@Override
	public void setCancelled(boolean arg0) {
		this.cancelled = arg0;
		
	}

	@Override
	public HandlerList getHandlers() {
		return handlers;
	}

	@Override
	public boolean isTrue(RPPlayer argument) {
		if(argument.get_job().getName() == Dieb.NAME){
			return true;
		}
		return false;
	}

	/**
	 * @return the executor
	 */
	public RPPlayer getExecutor() {
		return executor;
	}

	/**
	 * @return the stolen
	 */
	public RPPlayer getStolen() {
		return stolen;
	}
	
	public static HandlerList getHandlerList() {
	    return handlers;
	}

}
