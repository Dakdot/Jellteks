package net.maxipan.guild;

import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
	
	public void onEnable() {
		CommandManager cm = new CommandManager();
		cm.setup();
		getCommand("guild").setExecutor(cm);
	}
	
	public void onDisable() {
		
	}

}
