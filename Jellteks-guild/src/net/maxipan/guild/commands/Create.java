package net.maxipan.guild.commands;

import org.bukkit.entity.Player;

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
		
		MessageManager.getInstance().good(p, "Created new guild: " + args[0], true);
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
