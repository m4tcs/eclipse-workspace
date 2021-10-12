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

public class OnPlayerEnterBed implements Listener {
	
	private Plugin plugin = Main.getPlugin(Main.class);
	FileConfiguration config = plugin.getConfig();
	ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
	private boolean isSkipped = false;
	
	@EventHandler
	public void onPlayerEnterBed(PlayerBedEnterEvent event) {
		Player player = event.getPlayer();
		
		new BukkitRunnable() {

			@Override
			public void run() {
				if (player.isSleeping()) {
					Bukkit.broadcastMessage(player.getDisplayName() + " is sleeping.");
					System.out.println("[NightSkip] " + player.getDisplayName() + " is sleeping.");
				}
			}
		}.runTaskLater(plugin, 2);
		
		new BukkitRunnable() {

			@Override
			public void run() {
				if (player.isSleeping() && config.getBoolean("allow-night-skip")) {
					player.getWorld().setTime(23999);
					isSkipped = true;
				}
			}
			
		}.runTaskLater(plugin, 100);
		
		new BukkitRunnable() {

			@Override
			public void run() {
				if (isSkipped) {
					player.getWorld().setTime(0);
					System.out.println("[NightSkip] " + "Night has been skipped.");
					Bukkit.broadcastMessage("Night has been skipped.");
					isSkipped = false;
				}
			}
			
		}.runTaskLater(plugin, 102);
	}
}
