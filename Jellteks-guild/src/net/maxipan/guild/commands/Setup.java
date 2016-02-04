package net.maxipan.guild.commands;

import org.bukkit.entity.Player;

import net.maxipan.guild.MessageManager;

public class Setup extends SubCommand {

	@Override
	public void onCommand(Player p, String[] args) {
		if (args.length != 0) {
			MessageManager.getInstance().severe(p, "This command does not take arguments, therefore you can't run it with them.", true);
			return;
		}
		
		/*
		 * if (player ain't in a guild || doesn't have guild permissions) {
		 * 	tell 'em
		 * }
		 * 
		 * this is commented because we still have to develop a system for storing guilds and the players in them
		 */
	}

	@Override
	public String name() {
		// TODO Auto-generated method stub
		return "setup";
	}

	@Override
	public String info() {
		// TODO Auto-generated method stub
		return "Opens the setup dialog that edits the properties of a guild.";
	}

	@Override
	public String[] aliases() {
		// TODO Auto-generated method stub
		return new String[] { "options", "settings" };
	}

}
