package com.servebeer.quake.events;

import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Sound;
import org.bukkit.scheduler.BukkitRunnable;

import com.servebeer.quake.Main;

public class OnDeath implements Listener{
	
	private Plugin plugin = Main.getPlugin(Main.class);
	FileConfiguration config = plugin.getConfig();
	
	@EventHandler
	public void onPlayerDeath(PlayerDeathEvent event)
	{
		//Fetches console.
		ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
		
		if (plugin.getServer().getDefaultGameMode() != GameMode.SPECTATOR && config.getBoolean("life-linked-hardcore"))
		{
			plugin.getServer().setDefaultGameMode(GameMode.SPECTATOR);
			event.setDeathMessage(ChatColor.GOLD + event.getEntity().getName().toString() + " has died!");
			for (Player p : plugin.getServer().getOnlinePlayers())
			{
				p.playSound(p.getLocation(), Sound.ITEM_TOTEM_USE, 10, 0);
				p.setHealth(0);
			}
			
			Bukkit.dispatchCommand(console, "gamemode spectator @a");
		
			new BukkitRunnable()
			{
				@Override
				public void run() {
					//Checks to see if config wants all players to die upon a single player death.
					Bukkit.broadcastMessage(ChatColor.AQUA + "This world is now lost...");
					for (Player p : plugin.getServer().getOnlinePlayers())
					{
						p.playSound(p.getLocation(), Sound.ENTITY_WITHER_SPAWN, 10, 0.1f);
					}
				}
			}.runTaskLater(plugin, 40);
		}
		
		if (config.getBoolean("life-linked") && !config.getBoolean("life-linked-hardcore") && plugin.getServer().getDefaultGameMode() != GameMode.ADVENTURE)
		{
			plugin.getServer().setDefaultGameMode(GameMode.ADVENTURE);
			event.setDeathMessage(ChatColor.GOLD + event.getEntity().getName().toString() + " has died!");
			for (Player p : plugin.getServer().getOnlinePlayers())
			{
				p.playSound(p.getLocation(), Sound.ITEM_TOTEM_USE, 10, 0);
				p.setHealth(0);
			}
			new BukkitRunnable()
			{
				@Override
				public void run()
				{
					plugin.getServer().setDefaultGameMode(GameMode.SURVIVAL);
					Bukkit.dispatchCommand(console, "gamemode survival @a");
				}
			}.runTaskLater(plugin, 5);
		}
	}
}
