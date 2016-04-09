package net.maxipan.guild.commands;

import org.bukkit.entity.Player;

import net.maxipan.guild.Main;
import net.maxipan.guild.MessageManager;

public class Motd extends SubCommand {
	
	String motd;

	@Override
	public void onCommand(Player p, String[] args) {
		if (Main.plugin.guilds.getString("players." + p.getName()) == null) {
			MessageManager.getInstance().severe(p, "You are not in a guild.", true);
			return;
		}
		
		motd = Main.plugin.guilds.getString(Main.plugin.guilds.getString("players." + p.getName()) + ".motd");
		MessageManager.getInstance().info(p, "Guild MOTD: " + motd, false);
	}

	@Override
	public String name() {
		return "motd";
	}

	@Override
	public String info() {
		return "Shows a guild's message of the day.";
	}

	@Override
	public String[] aliases() {
		return new String[] { "" };
	}

}
