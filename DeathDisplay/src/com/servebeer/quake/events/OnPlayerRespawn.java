package com.servebeer.quake.events;

import org.bukkit.ChatColor;
import org.bukkit.Statistic;
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
			public void run() {
				
				//Checks to see if config.yml wants deaths displayed in player list.
				if (config.getBoolean("display-server-deaths"))
				{
					System.out.println(player.getDisplayName() + "'s deaths have been updated.");
					
					int deaths;
					deaths = player.getStatistic(Statistic.DEATHS);
					String space = "";
					if (deaths > 9999)
					{
						deaths = 9999;
						player.setPlayerListName(ChatColor.GOLD + " " + deaths + " " + ChatColor.WHITE + player.getDisplayName());
					}
					else if (deaths < 10)
					{
						player.setPlayerListName(ChatColor.GOLD + " " + deaths + "    " + ChatColor.WHITE + player.getDisplayName());
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
						player.setPlayerListName(ChatColor.GOLD + " " + deaths + space + ChatColor.WHITE + player.getDisplayName());
					}
				}
			}
		}.runTaskLater(plugin, 2);
	}
}
