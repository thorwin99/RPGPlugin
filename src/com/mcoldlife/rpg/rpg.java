package com.mcoldlife.rpg;

import java.util.logging.Logger;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.essentials.mcoldlife.main.CustomConfig;
public class rpg extends JavaPlugin{

	Logger log;
	
	@Override
	public void onEnable() {
		log = getLogger();
		log.info("Enabled");
		PluginManager pm = this.getServer().getPluginManager();
		reference.initReferences(pm, this);
		config();
	}
	
	@Override
	public void onDisable() {
		
	}
	
	public void config(){
		this.getConfig().addDefault(reference.PATH_CITY_EXPAND_PRICE, 2000);
		this.getConfig().addDefault(reference.PATH_CITY_EXPAND_CHUNK, 1);
		this.getConfig().addDefault(reference.PATH_CITY_EXPAND_PRICE, 500);
		this.getConfig().addDefault(reference.PATH_JOBS_CHANGE_PRICE, 3000);
		this.getConfig().options().copyDefaults(true);
		saveConfig();
		jobFolder();
		landFolder();
	}
	public void jobFolder(){
		
		CustomConfig.createFolder(reference.FOLDER_CLASSES.toString());
		CustomConfig.createFolder(reference.FOLDER_PLAYERS.toString());
		
		
	}
	public void landFolder(){
		
		CustomConfig.createFolder(reference.FOLDER_LANDS.toString());
		
		
	}
}
