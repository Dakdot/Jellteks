package net.maxipan.guild.commands;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import net.maxipan.guild.Main;
import net.maxipan.guild.MessageManager;

public class Create extends SubCommand {

	@Override
	public void onCommand(Player p, String[] args) {
		if (args.length < 1) {
			MessageManager.getInstance().severe(p, "Usage: /guild create <guild name>", true);
			return;
		}

		if (args.length > 1) {
			MessageManager.getInstance().severe(p, "Usage: /guild create <guild name>", true);
			return;
		}

		if (!Main.plugin.econ.has(p, 1000.0)) {
			MessageManager.getInstance().severe(p,
					"You do not have sufficient funds to create a guild. You need $" + ChatColor.BOLD + "1000.0" + ".",
					true);
			return;
		}
		
		if (Main.plugin.guilds.getConfigurationSection("").contains(args[0])) {
			MessageManager.getInstance().severe(p, "This guild name is already taken.", true);
			return;
		}

		MessageManager.getInstance().info(p, "Created new guild: " + args[0], true);
		Main.plugin.econ.withdrawPlayer(p, 1000.0);
		MessageManager.getInstance().info(p,
				"You were charged $" + ChatColor.BOLD + "1000.0" +  ChatColor.GRAY + " for creating your guild.", false);
		MessageManager.getInstance().info(p, "Saved guild spawn as current location.", false);

		createGuild(args[0], p);
	}

	@Override
	public String name() {
		return "create";
	}

	@Override
	public String info() {
		return "Creates a guild and awaits for setup.";
	}

	@Override
	public String[] aliases() {
		return new String[] { "make", "new" };
	}

	public void createGuild(String name, Player p) {
		try {
			Main.plugin.log.info("Beginning creation of guild " + name + ", created by: " + p.getName());
			Main.plugin.guilds.set(name + ".belongsTo", p.getName());
			Main.plugin.guilds.set(name + ".x", p.getLocation().getX());
			Main.plugin.guilds.set(name + ".y", p.getLocation().getY());
			Main.plugin.guilds.set(name + ".z", p.getLocation().getZ());
			Main.plugin.guilds.set(name + ".world", p.getLocation().getWorld().getName());
			Main.plugin.guilds.set("players." + p.getName(), name);
			Main.plugin.guilds.saveConfig();
			Main.plugin.log.info("Wrote guild settings successfully!");
		} catch (Exception e) {
			Main.plugin.log.severe("Error writing guild settings to disk.");
			MessageManager.getInstance().severe(p, "There was an error writing the guild settings to disk. Contact an administrator for assistance.", true);
			e.printStackTrace();
		}
	}

}
