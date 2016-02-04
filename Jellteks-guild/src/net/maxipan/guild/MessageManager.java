package net.maxipan.guild;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class MessageManager {
	
	private MessageManager() { } 
	
	private static MessageManager instance = new MessageManager();
	
	public static MessageManager getInstance() {
		return instance;
	}
	
	private String[] prefix = {
			ChatColor.DARK_GREEN + "[" + ChatColor.GREEN + "Jellteks Guilds" + ChatColor.DARK_GREEN + "] " + ChatColor.RESET,
			ChatColor.GOLD + "[" + ChatColor.GREEN + "Jellteks Guilds" + ChatColor.GOLD + "] " + ChatColor.RESET,
			ChatColor.DARK_RED + "[" + ChatColor.RED + "Jellteks Guilds" + ChatColor.DARK_RED + "] " + ChatColor.RESET
	};
	
	public void good(Player p, String msg, boolean sound) {
		if (sound) {
			p.sendMessage(prefix[0] + msg);
			p.playSound(p.getLocation(), "random.levelup", 100, 100);
		} else {
			p.sendMessage(prefix[0] + msg);
		}
	}
	
	public void info(Player p, String msg, boolean sound) {
		if (sound) {
			p.sendMessage(prefix[1] + msg);
			p.playSound(p.getLocation(), "random.levelup", 100, 100);
		} else {
			p.sendMessage(prefix[1] + msg);
		}
	}
	
	public void severe(Player p, String msg, boolean sound) {
		if (sound) {
			p.sendMessage(prefix[2] + msg);
			p.playSound(p.getLocation(), "note.bassattack", 100, 100);
		} else {
			p.sendMessage(prefix[2] + msg);
		}
	}

}
