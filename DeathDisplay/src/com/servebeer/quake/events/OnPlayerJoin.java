package com.servebeer.quake.events;

import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.Plugin;

import com.servebeer.quake.Main;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.entity.Player;
import org.bukkit.Statistic;
import org.bukkit.World;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.*;

public class OnPlayerJoin implements Listener {
	
	private Plugin plugin = Main.getPlugin(Main.class);
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event)
	{
		//Assigns fields.
		Player player = event.getPlayer();
		FileConfiguration config = plugin.getConfig();
		
		World playerDimension = player.getWorld();
		System.out.println(playerDimension);
		
		//Checks to see if config.yml wants deaths displayed in player list.
		if (config.getBoolean("display-server-deaths"))
		{
			System.out.println(player.getDisplayName() + "'s deaths are being displayed.");
			
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
		
		//Checks to see if config.yml wants a custom server name displayed in player list.
		if (config.getBoolean("display-server-name"))
		{
			player.setPlayerListHeader(config.getString("custom-server-name"));
		}
	}
}
