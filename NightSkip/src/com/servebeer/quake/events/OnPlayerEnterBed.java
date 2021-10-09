package com.servebeer.quake.events;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerBedEnterEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import com.servebeer.quake.Main;

public class OnPlayerEnterBed implements Listener {
	
	private Plugin plugin = Main.getPlugin(Main.class);
	FileConfiguration config = plugin.getConfig();
	
	public void onPlayerEnterBed(PlayerBedEnterEvent event) {
		Player player = event.getPlayer();
		System.out.println(player.getDisplayName() + " is sleeping");
		
		new BukkitRunnable() {

			@Override
			public void run() {
				if (player.isSleeping() && config.getBoolean("allow-night-skip")) {
					player.getWorld().setTime(23999);
					System.out.println("time should be changed");
				}
				
			}
			
		}.runTaskLater(plugin, 100);
		
		new BukkitRunnable() {

			@Override
			public void run() {
				if (player.isSleeping() && config.getBoolean("allow-night-skip")) {
					player.getWorld().setTime(1000);
					System.out.println("should be day");
				}
				
			}
			
		}.runTaskLater(plugin, 102);
	}
}
