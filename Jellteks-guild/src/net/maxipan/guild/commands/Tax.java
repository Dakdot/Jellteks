package net.maxipan.guild.commands;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import net.maxipan.guild.MessageManager;

public class Tax extends SubCommand {
	
	private double taxTotal;

	@Override
	public void onCommand(Player p, String[] args) {
		if (args[0] == "collect") {
			MessageManager.getInstance().info(p, "Taxes collected. You were paid $" + ChatColor.BOLD + taxTotal, true);
			return;
		}
		
		if (args[0] == "set") {
			if (args.length != 2) {
				MessageManager.getInstance().severe(p, "", true);
			}
		}
		
		MessageManager.getInstance().info(p, "--------------Displaying Commands--------------", true);
		p.sendMessage(ChatColor.YELLOW + "/guild tax collect " + ChatColor.GOLD + "Collects taxes from players in your guild.");
		p.sendMessage(ChatColor.YELLOW + "/guild tax set <amount> " + ChatColor.GOLD + "Sets how much a member will pay every ten minutes.");
		
	}

	@Override
	public String name() {
		return "tax";
	}

	@Override
	public String info() {
		return "Guild tax commands. Run for more info.";
	}

	@Override
	public String[] aliases() {
		return new String[] { "" };
	}

}
