package com.servebeer.quake;

import java.io.File;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import com.servebeer.quake.events.OnDeath;

public class Main extends JavaPlugin {
	
	FileConfiguration config = this.getConfig();
	
	public void onEnable()
	{
		configRegister();
		getServer().getPluginManager().registerEvents(new OnDeath(), this);
	}
	
	public void onDisable()
	{
		
	}
	
	public void configRegister()
	{
		System.out.println("DEBUGGING: configRegister()");
		if (config.getDouble("version") != 1.1)
		{
			System.out.println("Plugin outdated. Generating new config.yml.");
			File f = new File(this.getDataFolder().getAbsolutePath()+"\\config.yml");
			f.delete();
			this.saveDefaultConfig();
		}
		else
		{
			this.saveDefaultConfig();
		}
	}
}