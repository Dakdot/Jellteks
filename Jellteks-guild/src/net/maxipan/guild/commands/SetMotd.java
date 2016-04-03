package net.maxipan.guild.commands;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import net.maxipan.guild.Main;
import net.maxipan.guild.MessageManager;

public class SetMotd extends SubCommand {

	String motd = "";

	@Override
	public void onCommand(Player p, String[] args) {
		if (Main.plugin.guilds.getString("players." + p.getName()) == "0") {
			MessageManager.getInstance().severe(p, "You are not in a guild.", true);
			return;
		}

		/*
		 * if (no perms) { tell them; return; }
		 */

		motd = "";

		for (int i = 0; i < args.length; i++) { // loop threw all the
												// arguments
			String arg = args[i] + " "; // get the argument, and add a space
										// so that the words get spaced out
			motd = motd + arg; // add the argument to myString
		}

		if (motd == "" || motd == " ") {
			MessageManager.getInstance().severe(p, "The guild MOTD cannot be nothing.", true);
			return;
		}

		Main.plugin.guilds.set(Main.plugin.guilds.getString("players." + p.getName()) + ".motd", motd);
		Main.plugin.guilds.saveConfig();
		MessageManager.getInstance().info(p, "Set guild MOTD to: " + ChatColor.BOLD + motd, true);
	}

	@Override
	public String name() {
		return "setmotd";
	}

	@Override
	public String info() {
		return "Sets the guild's message of the day.";
	}

	@Override
	public String[] aliases() {
		return new String[] { "" };
	}

}
