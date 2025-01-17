package com.servebeer.quake.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import com.servebeer.quake.Main;

import org.bukkit.configuration.file.*;

public class OnPlayerRespawn implements Listener {
	
	private Plugin plugin = Main.getPlugin(Main.class);
	FileConfiguration config = plugin.getConfig();
	
	@EventHandler
	public void onPlayerRespawn(PlayerRespawnEvent event)
	{
		Player player = event.getPlayer();
		new BukkitRunnable()
		{
			@Override
			public void run()
			{
				if (config.getBoolean("life-linked") || config.getBoolean("life-linked-hardcore"))
				{
					player.setGameMode(plugin.getServer().getDefaultGameMode());
				}
			}
		}.runTaskLater(plugin, 2);
	}
}
