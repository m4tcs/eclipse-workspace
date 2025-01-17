package com.servebeer.quake;

import java.io.File;

import org.bukkit.configuration.file.FileConfiguration;

import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
	
	FileConfiguration config = this.getConfig();
	
	public void onEnable()
	{
	}
	
	public void onDisable()
	{
		this.saveDefaultConfig();
	}
	
	public void configRegister()
	{
		System.out.println("DEBUGGING: configRegister()");
		if (config.getDouble("version") != 1.0)
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