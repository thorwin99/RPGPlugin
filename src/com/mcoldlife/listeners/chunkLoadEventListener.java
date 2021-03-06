package com.mcoldlife.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.world.ChunkLoadEvent;
import org.bukkit.event.world.ChunkUnloadEvent;

import com.mcoldlife.objects.OLChunk;
import com.mcoldlife.objects.RPGManager;

public class chunkLoadEventListener implements Listener{

	@EventHandler(priority=EventPriority.HIGHEST)
	public void onChunkLoad(ChunkLoadEvent e){
		OLChunk loadedChunk = new OLChunk(e.getChunk());
		RPGManager.addChunk(loadedChunk.getID(), loadedChunk);
	}
	
	@EventHandler
	public void onChunkUnLoad(ChunkUnloadEvent e){
		OLChunk unloadedChunk = new OLChunk(e.getChunk());
		RPGManager.getChunks().remove(unloadedChunk.getID());
	}
}