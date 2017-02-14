package com.mcoldlife.rpg;

import java.nio.file.Path;

import org.bukkit.plugin.PluginManager;

public class reference {

	//Configuration variables
	public static String PATH_CITY_CONFIG = "Property.City";
	public static String PATH_CITY_PRICE = PATH_CITY_CONFIG + ".Price";
	public static String PATH_CITY_EXPAND_PRICE = PATH_CITY_CONFIG + ".ExpandPrice";
	public static String PATH_CITY_EXPAND_CHUNK = PATH_CITY_CONFIG + ".ExpandChunk";
	public static String PATH_JOBS_CONFIG = "Property.Jobs";
	public static String PATH_JOBS_CHANGE_PRICE = PATH_JOBS_CONFIG + ".changePrice";
	public static String PATH_LANDS_CHUNKS = "Region.Chunks";
	public static String PATH_LANDS_NAME = "Name";
	public static String PATH_LANDS_CITYS = "Region.Citys";
	public static String PATH_LANDS_PLAYERS = "Region.Players";
	public static String PATH_LANDS_OWNER = "Ruler";
	public static String PATH_CITY_CHUNKS = "Chunks";
	public static String PATH_PLAYER_LAND = "Land";
	public static String PATH_PLAYER_CITY = "City";
	public static String PATH_PLAYER_JOB = "Job";
	public static String PATH_CHUNK_ID = "Id";
	public static String PATH_CHUNK_LAND = "Land";
	public static String PATH_CHUNK_CITY = "City";
	public static Path FOLDER_CLASSES = null;
	public static Path FOLDER_PLAYERS = null;
	public static Path FOLDER_LANDS = null;
	public static Path FOLDER_CHUNKS = null;
	
	//Codes
	public static int CODE_CHUNK_OWNED_BY_OTHER_LAND = 399;
	public static int CODE_EXISTING = 565;
	public static int CODE_PLAYER_NOT_OWNER = 534;
	public static int CODE_CHUNK_NOT_CONNECTED = 234;
	
	public static void initReferences(PluginManager pm, rpg plugin){
		
		FOLDER_CLASSES = plugin.getDataFolder().toPath().resolve("Jobs");
		FOLDER_PLAYERS = plugin.getDataFolder().toPath().resolve("Players");
		FOLDER_LANDS = plugin.getDataFolder().toPath().resolve("Lands");
		FOLDER_CHUNKS = plugin.getDataFolder().toPath().resolve("Chunks");
	}
}
