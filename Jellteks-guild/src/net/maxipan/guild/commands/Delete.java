package net.maxipan.guild.commands;

import org.bukkit.entity.Player;

public class Delete extends SubCommand {

	@Override
	public void onCommand(Player p, String[] args) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String name() {
		return "delete";
	}

	@Override
	public String info() {
		return "Removes a guild's setting, all of it's players and disables it's use.";
	}

	@Override
	public String[] aliases() {
		return new String[] { "remove", "disband" };
	}

}
