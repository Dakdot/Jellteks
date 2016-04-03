package net.maxipan.guild.commands;

import org.bukkit.entity.Player;

public class Motd extends SubCommand {

	@Override
	public void onCommand(Player p, String[] args) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String name() {
		// TODO Auto-generated method stub
		return "motd";
	}

	@Override
	public String info() {
		// TODO Auto-generated method stub
		return "Shows a guild's message of the day.";
	}

	@Override
	public String[] aliases() {
		return new String[] { "" };
	}

}
