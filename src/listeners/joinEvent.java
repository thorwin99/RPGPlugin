package listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import com.essentials.mcoldlife.main.CustomConfig;
import com.essentials.mcoldlife.main.Reference;
import com.mcoldlife.rpg.reference;

public class joinEvent implements Listener{
	
	@EventHandler
	public void onjoin(PlayerJoinEvent e){
		Player p = e.getPlayer();
		String fileName = p.getUniqueId().toString() + ".yml";
		if(!CustomConfig.exists(fileName, Reference.FOLDER_PLAYERS.toString())){
			CustomConfig.create(fileName, Reference.FOLDER_PLAYERS.toString());
			CustomConfig.set(fileName, reference.FOLDER_PLAYERS.toString(), reference.PATH_PLAYER_LAND, null);
			CustomConfig.set(fileName, reference.FOLDER_PLAYERS.toString(), reference.PATH_PLAYER_CITY, null);
		}
		
	}
	

}
