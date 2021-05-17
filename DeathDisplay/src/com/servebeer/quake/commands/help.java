package com.servebeer.quake.commands;

import org.bukkit.command.*;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginDescriptionFile;

import com.servebeer.quake.Main;

public class help implements CommandExecutor{

	FileConfiguration config = Main.getPlugin(Main.class).getConfig();
	PluginDescriptionFile PDF = Main.getPlugin(Main.class).getDescription();
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		
		if (sender instanceof Player)
		{
			Player player = (Player) sender;
			player.sendMessage("Commands for DeathDisplay:");
			for (Object comm : PDF.getCommands().entrySet())
			{
				Command comma = (Command) comm;
				player.sendMessage(comma.getLabel() + " - " + comma.getDescription());
			}
			return true;
		}
		else
		{
			return false;
		}
	}
}
