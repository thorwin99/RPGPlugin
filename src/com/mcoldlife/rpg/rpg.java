package com.mcoldlife.rpg;

import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.essentials.mcoldlife.main.CustomConfig;
import com.mcoldlife.commands.city;
import com.mcoldlife.commands.job;
import com.mcoldlife.commands.land;
import com.mcoldlife.commands.money;
import com.mcoldlife.commands.pay;
import com.mcoldlife.commands.plot;
import com.mcoldlife.items.NetherCrafter;
import com.mcoldlife.listeners.blockBreakEventCancelListener;
import com.mcoldlife.listeners.blockBreakEventListener;
import com.mcoldlife.listeners.blockPlaceEventCancelListener;
import com.mcoldlife.listeners.blockPlaceEventListener;
import com.mcoldlife.listeners.chunkLoadEventListener;
import com.mcoldlife.listeners.landEventListeners;
import com.mcoldlife.listeners.playerCraftEventListener;
import com.mcoldlife.listeners.playerInteractEntityEventListener;
import com.mcoldlife.listeners.playerInteractEvent;
import com.mcoldlife.listeners.playerJoinEventListener;
import com.mcoldlife.listeners.playerLeaveEventListener;
import com.mcoldlife.listeners.playerMoveEventListener;
import com.mcoldlife.listeners.playerStealEventListener;
import com.mcoldlife.objects.OLJob;
import com.mcoldlife.objects.OLLand;
import com.mcoldlife.objects.RPGManager;
import com.mcoldlife.objects.RPPlayer;
import com.mcoldlife.objects.jobs.Alchemist;
import com.mcoldlife.objects.jobs.Dieb;
import com.mcoldlife.objects.jobs.Farmer;
import com.mcoldlife.objects.jobs.Fischer;
import com.mcoldlife.objects.jobs.Foerster;
import com.mcoldlife.objects.jobs.Miner;
import com.mcoldlife.objects.jobs.Schmied;
import com.mcoldlife.objects.jobs.Weber;
public class rpg extends JavaPlugin{
	Logger log;
	
	@Override
	public void onEnable() {
		log = getLogger();
		log.info("Enabled");
		PluginManager pm = this.getServer().getPluginManager();
		config();
		registerEvents();
		reference.initReferences(pm, this);
		jobFolder();
		landFolder();
		//Register Commands
		registerCommands();
		//Now enable everything else
		loadJobs();
		registerRecipes();
		//Load Chunks, City's, Lands, Plots
		loadLands();
		loadOnlinePlayers();
		
		RPGManager.reloadFromConfig();
	}

	private void registerEvents() {
		PluginManager pm = this.getServer().getPluginManager();
		pm.registerEvents(new blockBreakEventListener(), this);
		pm.registerEvents(new blockPlaceEventListener(), this);
		pm.registerEvents(new blockPlaceEventCancelListener(), this);
		pm.registerEvents(new blockBreakEventCancelListener(), this);
		pm.registerEvents(new chunkLoadEventListener(), this);
		pm.registerEvents(new playerCraftEventListener(), this);
		pm.registerEvents(new playerLeaveEventListener(), this);
		pm.registerEvents(new playerJoinEventListener(), this);
		pm.registerEvents(new playerInteractEntityEventListener(), this);
		pm.registerEvents(new playerInteractEvent(), this);
		pm.registerEvents(new playerMoveEventListener(), this);
		pm.registerEvents(new landEventListeners(), this);
		pm.registerEvents(new playerStealEventListener(), this);
	}

	private void loadLands() {
		Object[] lands = CustomConfig.getArray(reference.FILE_LANDS, reference.CONFIG_FOLDER.toString(), reference.PATH_LANDS);
		if(lands != null) {
			for(Object land : lands){
				OLLand l = new OLLand(land.toString());
				RPGManager.addLand(land.toString(), l);
			}
		}
	}

	private void registerCommands() {
		this.getCommand("city").setExecutor(new city());
		this.getCommand("job").setExecutor(new job());
		this.getCommand("land").setExecutor(new land());
		this.getCommand("money").setExecutor(new money());
		this.getCommand("pay").setExecutor(new pay());
		this.getCommand("plot").setExecutor(new plot());
	}

	@Override
	public void onDisable() {
		RPGManager.clear();
		log.info("Disabled");
	}
	
	public void config(){
		List<String> restrictedCraftItems = new LinkedList<>(Arrays.asList("NETHER_BRICK", "NETHER_BRICK_STAIRS", "NETHER_BRICK_ITEM", "NETHER_FENCE"));
		List<String> restrictedInteractItems = new LinkedList<>(Arrays.asList("ACACIA_DOOR", "IRON_TRAPDOOR", "BIRCH_DOOR", "DARK_OAK_DOOR", "IRON_DOOR", "JUNGLE_DOOR", "SPRUCE_DOOR", "TRAP_DOOR", "WOOD_DOOR", "WOODEN_DOOR", "FENCE_GATE", "STONE_BUTTON", "WOOD_BUTTON", "LEVER", "CHEST"));
		
		this.getConfig().addDefault(reference.PATH_LAND_PRICE, 10000);
		this.getConfig().addDefault(reference.PATH_CITY_PRICE, 2000);
		this.getConfig().addDefault(reference.PATH_CITY_EXPAND_PRICE, 500);
		this.getConfig().addDefault(reference.PATH_JOBS_CHANGE_PRICE, 3000);
		this.getConfig().addDefault(reference.PATH_WORLD_NAME, "world");
		this.getConfig().addDefault(reference.PATH_RESTRICTED_CRAFTABLE, restrictedCraftItems);
		this.getConfig().addDefault(reference.PATH_RESTRICTED_INTERACT, restrictedInteractItems);	
		this.getConfig().options().copyDefaults(true);
		saveConfig();
	}
	public void jobFolder(){
		
		CustomConfig.createFolder(reference.FOLDER_CLASSES.toString());
		CustomConfig.createFolder(reference.FOLDER_PLAYERS.toString());
		
		
	}
	public void landFolder(){
		
		CustomConfig.create(reference.FILE_LANDS, reference.CONFIG_FOLDER.toString());
		CustomConfig.createFolder(reference.FOLDER_LANDS.toString());
		CustomConfig.createFolder(reference.FOLDER_CITYS.toString());
		CustomConfig.createFolder(reference.FOLDER_CHUNKS.toString());
	}
	
	private void loadJobs(){
		RPGManager.addJob(Alchemist.NAME, new Alchemist());
		RPGManager.addJob(Dieb.NAME, new Dieb());
		RPGManager.addJob(Farmer.NAME, new Farmer());
		RPGManager.addJob(Fischer.NAME, new Fischer());
		RPGManager.addJob(Foerster.NAME, new Foerster());
		RPGManager.addJob(Miner.NAME, new Miner());
		RPGManager.addJob(Schmied.NAME, new Schmied());
		RPGManager.addJob(Weber.NAME, new Weber());
		
		String[] classes = CustomConfig.getFilesInFolder(reference.FOLDER_CLASSES.toString());
		for(String className : classes){
			String name = (String) CustomConfig.get(className, reference.FOLDER_CLASSES.toString(), reference.PATH_JOB_NAME);
			RPGManager.addJob(name, new OLJob(name));
		}
		
	}
	
	private void loadOnlinePlayers() {
		Collection<? extends Player> onlinePlayers = Bukkit.getServer().getOnlinePlayers();
		
		for(Player p : onlinePlayers) {
			
			RPGManager.onlinePlayers.put(p.getUniqueId().toString(), new RPPlayer(p));
			
		}
	}
	
	private void registerRecipes() {
		(new NetherCrafter()).addRecipe();
		createChainArmor();
		createHorseArmor();
	}
	
	private void createChainArmor(){
		ItemStack head = new ItemStack(Material.CHAINMAIL_HELMET);
		ShapedRecipe h = new ShapedRecipe(new NamespacedKey(this, "CHAIN_HELMET"), head);
		h.shape("CIC","I I","   ");
		h.setIngredient('I', Material.IRON_INGOT);
		h.setIngredient('C', Material.COAL);
		
		ItemStack boot = new ItemStack(Material.CHAINMAIL_BOOTS);
		ShapedRecipe b = new ShapedRecipe(new NamespacedKey(this, "CHAIN_BOOTS"), boot);
		b.shape("   ","I I","C C");
		b.setIngredient('I', Material.IRON_INGOT);
		b.setIngredient('C', Material.COAL);
		
		ItemStack chest = new ItemStack(Material.CHAINMAIL_CHESTPLATE);
		ShapedRecipe c = new ShapedRecipe(new NamespacedKey(this, "CHAIN_CHESTPLATE"), chest);
		c.shape("I I","CIC","ICI");
		c.setIngredient('I', Material.IRON_INGOT);
		c.setIngredient('C', Material.COAL);
		
		ItemStack leg = new ItemStack(Material.CHAINMAIL_LEGGINGS);
		ShapedRecipe l = new ShapedRecipe(new NamespacedKey(this, "CHAIN_LEG"), leg);
		l.shape("ICI","C C","I I");
		l.setIngredient('I', Material.IRON_INGOT);
		l.setIngredient('C', Material.COAL);
		Bukkit.addRecipe(h);
		Bukkit.addRecipe(b);
		Bukkit.addRecipe(c);
		Bukkit.addRecipe(l);
	}

	private void createHorseArmor(){
		ItemStack iron = new ItemStack(Material.IRON_BARDING);
		ShapedRecipe i = new ShapedRecipe(new NamespacedKey(this, "IRON_HORSE_ARMOR"), iron);
		i.shape("I  "," II"," II");
		i.setIngredient('I', Material.IRON_INGOT);
		Bukkit.addRecipe(i);
		
		ItemStack gold = new ItemStack(Material.GOLD_BARDING);
		ShapedRecipe g = new ShapedRecipe(new NamespacedKey(this, "GOLD_HORSE_ARMOR"), gold);
		g.shape("I  "," II"," II");
		g.setIngredient('I', Material.GOLD_INGOT);
		Bukkit.addRecipe(g);
		
		ItemStack dia = new ItemStack(Material.DIAMOND_BARDING);
		ShapedRecipe d = new ShapedRecipe(new NamespacedKey(this, "DIAMOND_HORSE_ARMOR"), dia);
		d.shape("I  "," II"," II");
		d.setIngredient('I', Material.DIAMOND);
		Bukkit.addRecipe(d);
		
		ItemStack saddle = new ItemStack(Material.SADDLE);
		ShapedRecipe s = new ShapedRecipe(new NamespacedKey(this, "SADDLE"), saddle);
		s.shape("lll"," I ","   ");
		s.setIngredient('I', Material.IRON_INGOT);
		s.setIngredient('l', Material.LEATHER);
		Bukkit.addRecipe(s);
	}
}
