package com.mcoldlife.rpg;

import java.nio.file.Path;

import org.bukkit.NamespacedKey;
import org.bukkit.World;
import org.bukkit.plugin.PluginManager;

public class reference {

	//Configuration variables
	public static String PATH_CITY_CONFIG = "Property.City";
	public static String PATH_CITY_PRICE = PATH_CITY_CONFIG + ".Price";
	public static String PATH_LAND_CONFIG = "Property.Lands";
	public static String PATH_LAND_PRICE = PATH_LAND_CONFIG + ".Price";
	public static String PATH_CITY_EXPAND_PRICE = PATH_CITY_CONFIG + ".ExpandPrice";
	public static String PATH_WORLD_SETTINGS = "Property.World";
	public static String PATH_WORLD_NAME = PATH_WORLD_SETTINGS + ".Name";
	public static String PATH_JOBS_CONFIG = "Property.Jobs";
	public static String PATH_JOBS_CHANGE_PRICE = PATH_JOBS_CONFIG + ".changePrice";
	
	//Jobs config
	public static String PATH_JOB_NAME = "Name";
	public static String PATH_JOB_DESTROY_BLOCKS = "Blocks.Destroy";
	public static String PATH_JOB_BUILD_BLOCKS = "Blocks.Build";
	public static String PATH_JOB_CRAFT_ITEMS = "Blocks.Crafting";
	public static String PATH_JOB_BUILD_ANYWHERE = "canBuildAnywhere";
	
	//Land config fields
	public static String PATH_LAND_CHUNKS = "Region.Chunks";
	public static String PATH_LAND_NAME = "Name";
	public static String PATH_LAND_CITYS = "Region.Citys";
	public static String PATH_LAND_OWNER = "Ruler";
	public static String PATH_LAND_CREATED = "Created";
	public static String PATH_LANDS = "Lands";
	
	//City config fields
	public static String PATH_CITY_CHUNKS = "Chunks";
	public static String PATH_CITY_OWNER = "Owner";
	public static String PATH_CITY_TAXES = "Tax";
	public static String PATH_CITY_LAND = "Land";
	public static String PATH_CITY_PLOTS = "Plots.";
	public static String PATH_CITY_MONEY = "Money";
	public static String PATH_CITY_CREATED = "Created";
	
	public static String[][] PATH_CITY_PLOTS_COORDS = {{".X1", ".Y1"},{".X2", ".Y2"}};
	public static String PATH_CITY_PLOTS_OWNER = ".Owner";
	public static String PATH_CITY_PLOTS_PRICE = ".Price";
	
	//Player config fields
	public static String PATH_PLAYER_LAND = "Land";
	public static String PATH_PLAYER_CITY = "City";
	public static String PATH_PLAYER_JOB = "Job";
	public static String PATH_PLAYER_MONEY = "Money";
	
	//Chunk config fields
	public static String PATH_CHUNK_ID = "Id";
	public static String PATH_CHUNK_LAND = "Land";
	public static String PATH_CHUNK_CITY = "City";
	
	//Folders
	public static Path FOLDER_CLASSES = null;
	public static Path FOLDER_PLAYERS = null;
	public static Path FOLDER_LANDS = null;
	public static Path FOLDER_CHUNKS = null;
	public static Path FOLDER_CITYS = null;
	public static Path CONFIG_FOLDER = null;
	
	//Files
	public static String FILE_LANDS = "Lands.yml";
	
	//Worlds
	public static World RPG_WORLD = null;
	public static int JOB_CHANGE_PRICE = -1;
	
	//Plugin
	public static rpg PLUGIN_REFERENCE;
	public static NamespacedKey NAMESPACED_KEY;
	public static void initReferences(PluginManager pm, rpg plugin){
		
		PLUGIN_REFERENCE = plugin;
		NAMESPACED_KEY = new NamespacedKey(plugin, plugin.getDescription().getName());
		JOB_CHANGE_PRICE = plugin.getConfig().getInt(PATH_JOBS_CHANGE_PRICE);
		RPG_WORLD = plugin.getServer().getWorld(plugin.getConfig().getString(PATH_WORLD_NAME));
		FOLDER_CLASSES = plugin.getDataFolder().toPath().resolve("Jobs");
		FOLDER_PLAYERS = plugin.getDataFolder().toPath().resolve("Players");
		FOLDER_LANDS = plugin.getDataFolder().toPath().resolve("Lands");
		FOLDER_CHUNKS = plugin.getDataFolder().toPath().resolve("Chunks");
		FOLDER_CITYS = plugin.getDataFolder().toPath().resolve("Citys");
		CONFIG_FOLDER = plugin.getDataFolder().toPath();
	}
}
