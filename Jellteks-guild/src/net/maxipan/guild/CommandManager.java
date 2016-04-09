package net.maxipan.guild;

import java.util.ArrayList;
import java.util.Arrays;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.maxipan.guild.commands.Create;
import net.maxipan.guild.commands.Motd;
import net.maxipan.guild.commands.Setup;
import net.maxipan.guild.commands.SubCommand;
import net.maxipan.guild.commands.Tax;

public class CommandManager implements CommandExecutor {
	
private ArrayList<SubCommand> commands = new ArrayList<SubCommand>();
	
	public void setup() {
		commands.add(new Create());
		commands.add(new Setup());
		commands.add(new Motd());
		commands.add(new Tax());
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		
		if (!(sender instanceof Player)) {
			sender.sendMessage(ChatColor.RED + "Only players can use Jellteks Guild right now, sorry.");
			return true;
		}
		
		Player p = (Player) sender;
		
		if (cmd.getName().equalsIgnoreCase("guild")) {
			if (args.length == 0) {
				MessageManager.getInstance().info(p, "--------------Displaying Commands--------------", true);
				for (SubCommand c : commands) {
					p.sendMessage(ChatColor.YELLOW + "/guild " + c.name() + " (" + aliases(c) + ") " + ChatColor.GOLD + c.info());
				}
				return true;
			}
			
			SubCommand target = get(args[0]);
			
			if (target == null) {
				MessageManager.getInstance().severe(p, "/guild " + args[0] + " is not a valid subcommand!", true);
				return true;
			}
			
			ArrayList<String> a = new ArrayList<String>();
			a.addAll(Arrays.asList(args));
			a.remove(0);
			args = a.toArray(new String[a.size()]);
			
			try {
				target.onCommand(p, args);
			}
			
			catch (Exception e) {
				MessageManager.getInstance().severe(p, "An error has occured: " + e.getCause(), true);
				e.printStackTrace();
			}
		}
		
		return true;
	}
	
	private String aliases(SubCommand cmd) {
		String fin = "";
		
		for (String a : cmd.aliases()) {
			fin += a + " | ";
		}
		
		return fin.substring(0, fin.lastIndexOf(" | "));
	}
	
	private SubCommand get(String name) {
		for (SubCommand cmd : commands) {
			if (cmd.name().equalsIgnoreCase(name)) return cmd;
			for (String alias : cmd.aliases()) if (name.equalsIgnoreCase(alias)) return cmd;
		}
		return null;
	}

}
