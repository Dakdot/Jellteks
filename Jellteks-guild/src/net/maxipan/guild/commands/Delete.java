package net.maxipan.guild.commands;

import org.bukkit.entity.Player;

import net.maxipan.guild.MessageManager;

public class Delete extends SubCommand {

	@Override
	public void onCommand(Player p, String[] args) {
		/*
		 * if (player DOES NOT HAVE permission) {
		 * 	tell them they can't delete a guild;
		 * 	return;
		 * }
		 */
		
		MessageManager.getInstance().severe(p, "Are you SURE you want to delete the guild: ", true);
		MessageManager.getInstance().severe(p, "Run the command again to confirm.", true);
		
	}

	@Override
	public String name() {
		return "delete";
	}

	@Override
	public String info() {
		return "Removes a guild's settings file, all of it's players and disables it's use.";
	}

	@Override
	public String[] aliases() {
		return new String[] { "remove", "disband" };
	}

}
