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

	private PermissionManager permManager;
	public WorldEditPlugin worldEdit;

	public Configuration config;
	public Configuration guilds;
	public Configuration permissions;
	public ConfigurationManager manager;

	public int vaultErrorType;

	public Economy econ = null;

	public void onEnable() {

		plugin = this;

		log = getLogger();
		
		TaxManager.getInstance().tax();

		CommandManager cm = new CommandManager();
		cm.setup();
		getCommand("guild").setExecutor(cm);

		if (getWorldEdit() == null) {
			log.severe(
					"No WorldEdit plugin was found; it is required for this plugin to operate correctly. Please install it.");
			Bukkit.getPluginManager().disablePlugin(this);
		} else {
			log.info("WorldEdit plugin instance found, continuing startup.");
		}
		worldEdit = getWorldEdit();

		if (!setupEconomy()) {
			if (vaultErrorType == 0)
				log.severe(
						"No Vault plugin was found; it is required for this plugin to operate correctly. Please install it.");
			if (vaultErrorType == 1)
				log.severe(
						"No economy plugin was found, therefore breaking the Vault plugin. Please install an economy plugin.");
			getServer().getPluginManager().disablePlugin(this);
			return;
		} else {
			log.info("Vault plugin instance found, continuing startup.");
		}
		
		manager = new ConfigurationManager(this);
		
		config = manager.getNewConfig("config.yml");
		guilds = manager.getNewConfig("guilds.yml");
		permissions = manager.getNewConfig("permissions.yml");
		
		permManager = new PermissionManager();
	}

	public void onDisable() {

		plugin = null;

	}

	//Quite obviously gets the WorldEdit plugin instance
	public WorldEditPlugin getWorldEdit() {
		Plugin p = Bukkit.getServer().getPluginManager().getPlugin("WorldEdit");

		if (p instanceof WorldEditPlugin)
			return (WorldEditPlugin) p;
		else
			return null;
	}

	//Sets up Vault Economy
	private boolean setupEconomy() {
		if (getServer().getPluginManager().getPlugin("Vault") == null) {
			vaultErrorType = 0;
			return false;
		}
		RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
		if (rsp == null) {
			vaultErrorType = 1;
			return false;
		}
		econ = rsp.getProvider();
		return econ != null;
	}

}
