package net.maxipan.guild.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import net.maxipan.guild.MessageManager;

public class Setup extends SubCommand {
	
	ItemStack[] menuItem = { new ItemStack(Material.BARRIER), new ItemStack(Material.BOOK) };
	ItemMeta[] menuItemMeta = { menuItem[0].getItemMeta(), menuItem[1].getItemMeta() };
	
	public void itemSetup() {
		menuItemMeta[0].setDisplayName("Exit Options");
		menuItem[0].setItemMeta(menuItemMeta[0]);
	}

	@Override
	public void onCommand(Player p, String[] args) {
		
		/*
		 * if (player ain't in a guild || doesn't have guild permissions) {
		 * 	tell 'em
		 * }
		 * 
		 * this is commented because we still have to develop a system for storing guilds and the players in them
		 */
		
		MessageManager.getInstance().good(p, "Opened guild setup dialog for guild: " + ChatColor.YELLOW + ChatColor.BOLD + "<guild name here>", true);
		
		Inventory menu = Bukkit.createInventory(null, 18, ChatColor.YELLOW + "GUILD OPTIONS");
		menu.setItem(17, menuItem[0]);
		
		p.openInventory(menu);
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
