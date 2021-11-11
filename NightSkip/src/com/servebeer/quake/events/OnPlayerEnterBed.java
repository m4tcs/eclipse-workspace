package com.servebeer.quake.events;

import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerBedEnterEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import com.servebeer.quake.Main;

import net.md_5.bungee.api.ChatColor;

public class OnPlayerEnterBed implements Listener {
	
	private Plugin plugin = Main.getPlugin(Main.class);
	FileConfiguration config = plugin.getConfig();
	ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
	
	@EventHandler
	public void onPlayerEnterBed(PlayerBedEnterEvent event) {
		Player player = event.getPlayer();
		
		new BukkitRunnable() {

			@Override
			public void run() {
				if (player.isSleeping()) {
					Bukkit.broadcastMessage(ChatColor.GOLD + "" + ChatColor.BOLD + "[NightSkip] " + ChatColor.GOLD + player.getDisplayName() + " is sleeping.");
				}
			}
		}.runTaskLater(plugin, 2);
		
		new BukkitRunnable() {

			@Override
			public void run() {
				if (player.isSleeping() && config.getBoolean("allow-night-skip")) {
					player.getWorld().setTime(23999);
					player.getWorld().setWeatherDuration(0);
					Bukkit.broadcastMessage(ChatColor.GOLD + "" + ChatColor.BOLD + "[NightSkip] " + ChatColor.GOLD + "Night has been skipped.");
				}
			}
			
		}.runTaskLater(plugin, 100);
	}
}
