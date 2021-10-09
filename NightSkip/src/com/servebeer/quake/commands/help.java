package com.servebeer.quake.commands;

import java.util.Map;
import java.util.Map.Entry;

import org.bukkit.command.*;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.ChatColor;
import com.servebeer.quake.Main;

public class help implements CommandExecutor{

	FileConfiguration config = Main.getPlugin(Main.class).getConfig();
	PluginDescriptionFile PDF = Main.getPlugin(Main.class).getDescription();
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		
		if (sender instanceof Player)
		{
			Player player = (Player) sender;
			player.sendMessage(ChatColor.AQUA + "" + ChatColor.BOLD + "-----");
			player.sendMessage(ChatColor.GOLD + "" + ChatColor.BOLD + "Commands for NightSkip:");
			for (Entry<String, Map<String, Object>> comm : PDF.getCommands().entrySet())
			{
				String comma = comm.getKey();
				player.sendMessage(ChatColor.GOLD + "  /" + comma);
			}
			player.sendMessage(ChatColor.AQUA + "" + ChatColor.BOLD + "-----");
			return true;
		}
		else
		{
			return false;
		}
	}
}
