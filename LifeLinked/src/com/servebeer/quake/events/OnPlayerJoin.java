package com.servebeer.quake.events;

import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.Plugin;

import com.servebeer.quake.Main;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.entity.Player;
import org.bukkit.configuration.file.*;

public class OnPlayerJoin implements Listener {
	
	private Plugin plugin = Main.getPlugin(Main.class);
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event)
	{
		//Assigns fields.
		Player player = event.getPlayer();
		FileConfiguration config = plugin.getConfig();
		
		if (config.getBoolean("life-linked")  || config.getBoolean("life-linked-hardcore"))
		{
			player.setGameMode(plugin.getServer().getDefaultGameMode());
		}
	}
}
