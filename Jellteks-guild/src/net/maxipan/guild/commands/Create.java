package net.maxipan.guild.commands;

import org.bukkit.entity.Player;

public class Create extends SubCommand {

	@Override
	public void onCommand(Player p, String[] args) {
		
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

}
