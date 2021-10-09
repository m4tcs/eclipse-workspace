package com.servebeer.quake.events;

import org.bukkit.block.Block;

import java.util.EnumSet;
import java.util.Set;

import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import com.servebeer.quake.Main;

public class OnPlayerBlockInteract implements Listener {
	/*
	private Plugin plugin = Main.getPlugin(Main.class);
	FileConfiguration config = plugin.getConfig();
	
	private static final Set<Material> BEDS = EnumSet.of(
		    Material.BLACK_BED, Material.BLUE_BED, Material.BROWN_BED,
		    Material.CYAN_BED, Material.GRAY_BED, Material.GREEN_BED,
		    Material.LIGHT_BLUE_BED, Material.LIGHT_GRAY_BED, Material.LIME_BED,
		    Material.MAGENTA_BED, Material.ORANGE_BED, Material.PINK_BED,
		    Material.PURPLE_BED, Material.RED_BED, Material.WHITE_BED,
		    Material.YELLOW_BED
		);
	
	public void onPlayerBlockInteract(PlayerInteractEvent event) {
		Player player = event.getPlayer();
		System.out.println(player.getDisplayName() + " is sleeping");
		Block block = event.getClickedBlock();
		
		new BukkitRunnable() {

			@Override
			public void run() {
				if (BEDS.contains(block.getType()) && player.isSleeping() && config.getBoolean("allow-night-skip")) {
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
	*/
}
