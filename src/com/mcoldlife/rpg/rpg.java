package com.mcoldlife.rpg;

import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.Material;
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
import com.mcoldlife.objects.OLJob;
import com.mcoldlife.objects.OLLand;
import com.mcoldlife.objects.RPGManager;
import com.mcoldlife.objects.jobs.Alchemist;
import com.mcoldlife.objects.jobs.Dieb;
import com.mcoldlife.objects.jobs.Farmer;
import com.mcoldlife.objects.jobs.Fischer;
import com.mcoldlife.objects.jobs.Foerster;
import com.mcoldlife.objects.jobs.Miner;
import com.mcoldlife.objects.jobs.Schmied;
import com.mcoldlife.objects.jobs.Weber;
public class rpg extends JavaPlugin{

	private static RPGManager rpgManager = new RPGManager();
	
	Logger log;
	
	@Override
	public void onEnable() {
		log = getLogger();
		log.info("Enabled");
		PluginManager pm = this.getServer().getPluginManager();
		config();
		reference.initReferences(pm, this);
		//Register Commands
		registerCommands();
		//Now enable everything else
		loadJobs();
		registerRecipes();
		//Load Chunks, Citys, Lands, Plots
		loadLands();
	}

	private void loadLands() {
		String[] lands = (String[]) CustomConfig.getArray(reference.FILE_LANDS, reference.CONFIG_FOLDER.toString(), reference.PATH_LANDS);
		for(String land : lands){
			OLLand l = new OLLand(land);
			RPGManager.addLand(land, l);
		}
		
	}

	private void registerCommands() {
		this.getCommand("city").setExecutor(new city());
		this.getCommand("job").setExecutor(new job());
		this.getCommand("land").setExecutor(new land());
		this.getCommand("money").setExecutor(new money(this));
		this.getCommand("pay").setExecutor(new pay());
		this.getCommand("plot").setExecutor(new plot());
	}

	@Override
	public void onDisable() {
		
	}
	
	public void config(){
		this.getConfig().addDefault(reference.PATH_LAND_PRICE, 10000);
		this.getConfig().addDefault(reference.PATH_CITY_PRICE, 2000);
		this.getConfig().addDefault(reference.PATH_CITY_EXPAND_PRICE, 500);
		this.getConfig().addDefault(reference.PATH_JOBS_CHANGE_PRICE, 3000);
		this.getConfig().addDefault(reference.PATH_WORLD_NAME, "world");
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
		
		CustomConfig.create(reference.FILE_LANDS, reference.CONFIG_FOLDER.toString());
		CustomConfig.createFolder(reference.FOLDER_LANDS.toString());
		CustomConfig.createFolder(reference.FOLDER_CITYS.toString());
		CustomConfig.createFolder(reference.FOLDER_CHUNKS.toString());
	}

	/**
	 * @return the rpgManager
	 */
	public static RPGManager getRPGManager() {
		return rpgManager;
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
	
	private void registerRecipes() {
		(new NetherCrafter()).addRecipe();
		createChainArmor();
		createHorseArmor();
	}
	
	private void createChainArmor(){
		ItemStack head = new ItemStack(Material.CHAINMAIL_HELMET);
		ShapedRecipe h = new ShapedRecipe(reference.NAMESPACED_KEY, head);
		h.shape("CIC","I I","   ");
		h.setIngredient('I', Material.IRON_INGOT);
		h.setIngredient('C', Material.COAL);
		
		ItemStack boot = new ItemStack(Material.CHAINMAIL_BOOTS);
		ShapedRecipe b = new ShapedRecipe(reference.NAMESPACED_KEY, boot);
		b.shape("   ","I I","C C");
		b.setIngredient('I', Material.IRON_INGOT);
		b.setIngredient('C', Material.COAL);
		
		ItemStack chest = new ItemStack(Material.CHAINMAIL_CHESTPLATE);
		ShapedRecipe c = new ShapedRecipe(reference.NAMESPACED_KEY, chest);
		c.shape("I I","CIC","ICI");
		c.setIngredient('I', Material.IRON_INGOT);
		c.setIngredient('C', Material.COAL);
		
		ItemStack leg = new ItemStack(Material.CHAINMAIL_LEGGINGS);
		ShapedRecipe l = new ShapedRecipe(reference.NAMESPACED_KEY, leg);
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
		ShapedRecipe i = new ShapedRecipe(reference.NAMESPACED_KEY, iron);
		i.shape("I  "," II"," II");
		i.setIngredient('I', Material.IRON_INGOT);
		Bukkit.addRecipe(i);
		
		ItemStack gold = new ItemStack(Material.IRON_BARDING);
		ShapedRecipe g = new ShapedRecipe(reference.NAMESPACED_KEY, gold);
		g.shape("I  "," II"," II");
		g.setIngredient('I', Material.GOLD_INGOT);
		Bukkit.addRecipe(g);
		
		ItemStack dia = new ItemStack(Material.IRON_BARDING);
		ShapedRecipe d = new ShapedRecipe(reference.NAMESPACED_KEY, dia);
		d.shape("I  "," II"," II");
		d.setIngredient('I', Material.DIAMOND);
		Bukkit.addRecipe(g);
		
		ItemStack saddle = new ItemStack(Material.SADDLE);
		ShapedRecipe s = new ShapedRecipe(reference.NAMESPACED_KEY, saddle);
		s.shape("lll"," I ","   ");
		s.setIngredient('I', Material.IRON_INGOT);
		s.setIngredient('l', Material.LEATHER);
		Bukkit.addRecipe(g);
	}
}
