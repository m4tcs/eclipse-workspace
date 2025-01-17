package com.servebeer.quake;

import java.io.File;

import org.bukkit.configuration.file.FileConfiguration;

import org.bukkit.plugin.java.JavaPlugin;

import com.servebeer.quake.commands.cfg;
import com.servebeer.quake.commands.help;
import com.servebeer.quake.events.OnPlayerJoin;
import com.servebeer.quake.events.OnPlayerRespawn;

public class Main extends JavaPlugin {
	
	FileConfiguration config = this.getConfig();
	
	public void onEnable()
	{
		configRegister();
		getServer().getPluginManager().registerEvents(new OnPlayerJoin(), this);
		getServer().getPluginManager().registerEvents(new OnPlayerRespawn(), this);
		this.getCommand("ddhelp").setExecutor(new help());
		this.getCommand("ddcfg").setExecutor(new cfg());
	}
	
	public void onDisable()
	{
		this.saveDefaultConfig();
	}
	
	public void configRegister()
	{
		System.out.println("DEBUGGING: configRegister()");
		if (config.getDouble("version") != 1.3)
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