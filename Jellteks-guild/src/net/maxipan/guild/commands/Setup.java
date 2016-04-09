package net.maxipan.guild.commands;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import net.maxipan.guild.Main;
import net.maxipan.guild.MessageManager;

public class Setup extends SubCommand {

	String tag;
	String motd;

	@Override
	public void onCommand(Player p, String[] args) {

		/*
		 * if (doesn't have guild permissions) { tell 'em }
		 * 
		 * Ranks still need to be implemented
		 */

		if (args[0] == "sethome") {
			if (Main.plugin.guilds.getString("players." + p.getName()) == null) {
				MessageManager.getInstance().severe(p, "You are not in a guild.", true);
				return;
			}

			motd = "";

			for (int i = 0; i < args.length; i++) { // loop through all the
													// arguments
				String arg = args[i] + " "; // get the argument, and add a space
											// so that the words get spaced out
				motd = motd + arg; // add the argument to motd
			}

			if (motd == "" || motd == " ") {
				MessageManager.getInstance().severe(p, "Usage: /guild setmotd <new motd>", true);
				return;
			}

			if (Main.plugin.config.getBoolean("guildMotd.allowColours")) {
				Main.plugin.guilds.set(Main.plugin.guilds.getString("players." + p.getName()) + ".tag", motd);
				Main.plugin.guilds.saveConfig();
				MessageManager.getInstance().info(p, "Set guild tag to: " + motd.replaceAll("(&([a-f0-9]))", "\u00A7$2")
						.replaceAll("($([a-f0-9]))", "\u00A7$2").replaceAll("(%([a-f0-9]))", "\u00A7$2"), true);
				return;
			}

			Main.plugin.guilds.set(Main.plugin.guilds.getString("players." + p.getName()) + ".tag", motd);
			Main.plugin.guilds.saveConfig();
			MessageManager.getInstance().info(p, "Set guild tag to: " + motd, true);
		}

		if (args[0] == "settag") {
			if (Main.plugin.guilds.getString("players." + p.getName()) == null) {
				MessageManager.getInstance().severe(p, "You are not in a guild.", true);
				return;
			}

			Main.plugin.guilds.set(Main.plugin.guilds.getString("players." + p.getName()) + ".x",
					p.getLocation().getX());
			Main.plugin.guilds.set(Main.plugin.guilds.getString("players." + p.getName()) + ".y",
					p.getLocation().getY());
			Main.plugin.guilds.set(Main.plugin.guilds.getString("players." + p.getName()) + ".z",
					p.getLocation().getZ());
			Main.plugin.guilds.set(Main.plugin.guilds.getString("players." + p.getName()) + ".world",
					p.getLocation().getWorld().getName());
		}

		if (args[0] == "setmotd") {
			if (Main.plugin.guilds.getString("players." + p.getName()) == null) {
				MessageManager.getInstance().severe(p, "You are not in a guild.", true);
				return;
			}

			motd = "";

			for (int i = 1; i < args.length; i++) { // loop through all the
													// arguments
				String arg = args[i] + " "; // get the argument, and add a space
											// so that the words get spaced out
				motd = motd + arg; // add the argument to motd
			}

			if (motd == "" || motd == " ") {
				MessageManager.getInstance().severe(p, "Usage: /guild setmotd <new motd>", true);
				return;
			}

			if (Main.plugin.config.getBoolean("guildMotd.allowColours")) {
				Main.plugin.guilds.set(Main.plugin.guilds.getString("players." + p.getName()) + ".tag", motd);
				Main.plugin.guilds.saveConfig();
				MessageManager.getInstance().info(p, "Set guild tag to: " + motd.replaceAll("(&([a-f0-9]))", "\u00A7$2")
						.replaceAll("($([a-f0-9]))", "\u00A7$2").replaceAll("(%([a-f0-9]))", "\u00A7$2"), true);
				return;
			}

			Main.plugin.guilds.set(Main.plugin.guilds.getString("players." + p.getName()) + ".tag", motd);
			Main.plugin.guilds.saveConfig();
			MessageManager.getInstance().info(p, "Set guild tag to: " + motd, true);
		}

		if (args[0] == "settag") {
			if (Main.plugin.guilds.getString("players." + p.getName()) == null) {
				MessageManager.getInstance().severe(p, "You are not in a guild.", true);
				return;
			}

			if (!Main.plugin.econ.has(p, Main.plugin.config.getDouble("guildTags.chargeAmount"))) {
				MessageManager
						.getInstance().severe(
								p, "You do not have sufficient funds to change the guild tag. You need $"
										+ ChatColor.BOLD + Main.plugin.config.getDouble("guildTags.chargeAmount") + ".",
								true);
				return;
			}

			if (args.length > Main.plugin.config.getInt("guildTags.wordLimit")) {
				MessageManager.getInstance().severe(p, "Guild tags can't be more than "
						+ Main.plugin.config.getInt("guildTags.wordLimit") + " words long.", true);
				return;
			}

			tag = "";

			for (int i = 0; i < args.length; i++) { // loop through all the
													// arguments
				String arg = args[i] + " "; // get the argument, and add a space
											// so that the words get spaced out
				tag = tag + arg; // add the argument to tag
			}

			if (tag == "" || tag == " ") {
				MessageManager.getInstance().severe(p, "Usage: /guild settag <new tag>", true);
				return;
			}

			if (Main.plugin.config.getBoolean("guildTags.allowColours")) {
				Main.plugin.guilds.set(Main.plugin.guilds.getString("players." + p.getName()) + ".tag", tag);
				Main.plugin.guilds.saveConfig();
				MessageManager.getInstance().info(p, "Set guild tag to: " + tag.replaceAll("(&([a-f0-9]))", "\u00A7$2")
						.replaceAll("($([a-f0-9]))", "\u00A7$2").replaceAll("(%([a-f0-9]))", "\u00A7$2"), true);

				Main.plugin.econ.withdrawPlayer(p, Main.plugin.config.getDouble("guildTags.chargeAmount"));
				MessageManager.getInstance()
						.info(p, "You were charged $" + ChatColor.BOLD
								+ Main.plugin.config.getDouble("guildTags.chargeAmount") + ChatColor.GRAY
								+ " for changing the guild tag.", false);
				return;
			}

			Main.plugin.guilds.set(Main.plugin.guilds.getString("players." + p.getName()) + ".tag", tag);
			Main.plugin.guilds.saveConfig();
			MessageManager.getInstance().info(p, "Set guild tag to: " + tag, true);

			Main.plugin.econ.withdrawPlayer(p, Main.plugin.config.getDouble("guildTags.chargeAmount"));
			MessageManager.getInstance()
					.info(p, "You were charged $" + ChatColor.BOLD
							+ Main.plugin.config.getDouble("guildTags.chargeAmount") + ChatColor.GRAY
							+ " for chaning the guild tag.", false);
		}
	}

	@Override
	public String name() {
		return "setup";
	}

	@Override
	public String info() {
		return "Commands that allow edits to the properties of a guild.";
	}

	@Override
	public String[] aliases() {
		return new String[] { "options", "settings" };
	}

}
