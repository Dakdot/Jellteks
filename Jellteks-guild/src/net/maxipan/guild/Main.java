package net.maxipan.guild;

import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import com.sk89q.worldedit.bukkit.WorldEditPlugin;

import net.milkbowl.vault.economy.Economy;

public class Main extends JavaPlugin {
	
	public static Main plugin;
	
	public Logger log;
	
	private ConfigurationManager manager;
	public Configuration config;
	public Configuration guilds;
	public WorldEditPlugin worldEdit;
	
	public Economy econ = null;
	
	public void onEnable() {
		
		plugin = this;
		
		log = getLogger();
		
		CommandManager cm = new CommandManager();
		cm.setup();
		getCommand("guild").setExecutor(cm);
		
		if (getWorldEdit() == null) {
			log.severe("EMERGENCY! No WorldEdit plugin was found; it is required for this plugin to operate correctly. Please install it.");
			Bukkit.getPluginManager().disablePlugin(this);
		} else {
			log.info("WorldEdit plugin instance found, continuing startup.");
		}
		worldEdit = getWorldEdit();
		
		if (!setupEconomy() ) {
			log.severe("EMERGENCY! No Vault plugin was found; it is required for this plugin to operate correctly. Please install it.");;
            getServer().getPluginManager().disablePlugin(this);
            return;
        } else {
        	log.info("Vault plugin instance found, continuing startup.");
        }
		
		manager = new ConfigurationManager(this);
		config = manager.getNewConfig("config.yml", new String[] {
				"Jellteks Guilds Global Configuration File",
				"This configuration file will apply to all",
				"arenas."
		});
		guilds = manager.getNewConfig("guilds.yml", new String[] {
				"Jellteks Guild Settings Storage File",
				"DO NOT MODIFY UNLESS YOU KNOW WHAT YOU ARE",
				"DOING."
		});
	}
	
	public void onDisable() {
		
		plugin = null;
		
	}
	
	public WorldEditPlugin getWorldEdit() {
		Plugin p = Bukkit.getServer().getPluginManager().getPlugin("WorldEdit");
		
		if (p instanceof WorldEditPlugin) return (WorldEditPlugin) p;
		else return null;
	}
	
	private boolean setupEconomy() {
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        econ = rsp.getProvider();
        return econ != null;
    }
}
