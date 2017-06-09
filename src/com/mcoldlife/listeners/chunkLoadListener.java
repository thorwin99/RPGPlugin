package com.mcoldlife.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.ChunkLoadEvent;
import org.bukkit.event.world.ChunkUnloadEvent;

import com.mcoldlife.objects.OLChunk;
import com.mcoldlife.rpg.rpg;

public class chunkLoadListener implements Listener{

	@EventHandler
	public void onChunkLoad(ChunkLoadEvent e){
		
		OLChunk loadedChunk = new OLChunk(e.getChunk());
		
		if(!rpg.getRPGManager().getChunks().containsKey(loadedChunk.getID())) 
			rpg.getRPGManager().getChunks().put(loadedChunk.getID(), loadedChunk);
	}
	
	@EventHandler
	public void onChunkLoad(ChunkUnloadEvent e){
		
		OLChunk unloadedChunk = new OLChunk(e.getChunk());
		
		if(!rpg.getRPGManager().getChunks().containsKey(unloadedChunk.getID())) 
			rpg.getRPGManager().getChunks().remove(unloadedChunk.getID());
	}
	
}
