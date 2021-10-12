package com.servebeer.quake.commands;

import java.io.File;

import org.bukkit.ChatColor;
import org.bukkit.Statistic;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import com.servebeer.quake.Main;

public class cfg implements CommandExecutor {

	FileConfiguration config = Main.getPlugin(Main.class).getConfig();
	private Plugin plugin = Main.getPlugin(Main.class);
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		Player player = (Player) sender;
		if (args.length == 1) {
			switch(args[0]) {
			case "servername":
				player.sendMessage(ChatColor.RED + "INVALID USAGE OF COMMAND");
				player.sendMessage(ChatColor.GRAY + "/ddcfg servername [name]");
				return true;
			}
		}
		if (args.length >= 2) {
			switch(args[0]) {
			case "displayname":
				switch(args[1]) {
				case "true":
					config.set("display-server-name", true);
					player.sendMessage("Server config successfully updated.");
					displaynameUpdate(true);
				case "false":
					config.set("display-server-name", false);
					player.sendMessage("Server config successfully updated.");
					displaynameUpdate(false);
				default:
					player.sendMessage(ChatColor.RED + "INVALID USAGE OF COMMAND");
					player.sendMessage(ChatColor.GRAY + "/ddcfg displayname [true | false]");
				}
				return true;
				
			case "displaydeaths":
				switch(args[1]) {
				case "true":
					config.set("display-server-deaths", true);
					player.sendMessage("Server config successfully updated.");
					displaydeathsUpdate(true);
				case "false":
					config.set("display-server-deaths", false);
					player.sendMessage("Server config successfully updated.");
					displaydeathsUpdate(false);
				default:
					player.sendMessage(ChatColor.RED + "INVALID USAGE OF COMMAND");
					player.sendMessage(ChatColor.GRAY + "/ddcfg displaydeaths [true | false]");
				}
				return true;
				
			case "servername":
				String leftover = "";
				for (int x = 1; x < args.length; x++)
					leftover += args[x] + " ";
				config.set("custom-server-name", leftover);
				for (Player p : plugin.getServer().getOnlinePlayers()) {
						p.setPlayerListHeader(leftover);
				}
				return true;
			}
		}
		player.sendMessage(ChatColor.RED + "INVALID USAGE OF COMMAND");
		player.sendMessage(ChatColor.GRAY + "/ddcfg [displayname | displaydeaths | servername]");
		return true;
	}
	
	public void displaynameUpdate(boolean cfg) {
		if (cfg) {
			for (Player p : plugin.getServer().getOnlinePlayers()) {
				p.setPlayerListHeader(config.getString("custom-server-name"));
			}
		}
		else {
			for (Player p : plugin.getServer().getOnlinePlayers()) {
				p.setPlayerListHeader(null);
			}
		}
	}
	
	public void displaydeathsUpdate(boolean cfg) {
		if (cfg) {
			for (Player p : plugin.getServer().getOnlinePlayers()) {
				int deaths;
				deaths = p.getStatistic(Statistic.DEATHS);
				String space = "";
				
				if (deaths > 9999)
				{
					deaths = 9999;
					p.setPlayerListName(ChatColor.GOLD + " " + deaths + " " + ChatColor.WHITE + p.getDisplayName());
				}
				else if (deaths < 10)
				{
					p.setPlayerListName(ChatColor.GOLD + " " + deaths + "    " + ChatColor.WHITE + p.getDisplayName());
				}
				else
				{
					space = space + " ";
					int y = 1000;
					while (deaths < y)
					{
						space = space + " ";
						y = y/10;
					}
					p.setPlayerListName(ChatColor.GOLD + " " + deaths + space + ChatColor.WHITE + p.getDisplayName());
				}
			}
		}
		else {
			for (Player p : plugin.getServer().getOnlinePlayers()) {
				p.setPlayerListName(p.getDisplayName());
			}
		}
	}
}
