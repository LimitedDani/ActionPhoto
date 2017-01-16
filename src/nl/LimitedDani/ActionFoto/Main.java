package nl.LimitedDani.ActionFoto;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Main
  extends JavaPlugin
  implements CommandExecutor, Listener
{
	/*******************************************************
	 * Copyright (C) 2015-2017 Daníque de Jong danique.de.jong2000@gmail.com
	 * 
	 * This file is part of ActionPhoto.
	 * 
	 * ActionPhoto can only be used for personal use
	 * Its not allowed to repost this on any website
	 *******************************************************/
  public static Main plugin;
  public ArtRenderer artRenderer;
  FileConfiguration config;
  public File ActionFile = new File(getDataFolder() + "/Actionfoto.yml");
  public FileConfiguration ActionData = YamlConfiguration.loadConfiguration(this.ActionFile);
  
  public void onEnable()
  {
    plugin = this;
    saveConfig();
    saveAfFile();
    this.config = getConfig();
    getConfig().options().copyDefaults(true);
    if (!this.config.contains("ActionFoto"))
    {
      this.config.set("ActionFoto.Foto1.PictureBackgroundUrl", "puctureurl");
      this.config.set("ActionFoto.Foto1.ResetBackgroundUrl", "reseturl");
      this.config.set("ActionFoto.Foto1.MessageOnTake", "Here the message :D");
      this.config.set("ActionFoto.Foto2.PictureBackgroundUrl", "puctureurl");
      this.config.set("ActionFoto.Foto2.ResetBackgroundUrl", "reseturl");
      this.config.set("ActionFoto.Foto2.MessageOnTake", "Here the message :D");
      this.config.set("ActionFoto.Foto3.PictureBackgroundUrl", "puctureurl");
      this.config.set("ActionFoto.Foto3.ResetBackgroundUrl", "reseturl");
      this.config.set("ActionFoto.Foto3.MessageOnTake", "Here the message :D");
      this.config.set("ActionFoto.Foto4.PictureBackgroundUrl", "puctureurl");
      this.config.set("ActionFoto.Foto4.ResetBackgroundUrl", "reseturl");
      this.config.set("ActionFoto.Foto4.MessageOnTake", "Here the message :D");
      this.config.set("ActionFoto.Foto5.PictureBackgroundUrl", "puctureurl");
      this.config.set("ActionFoto.Foto5.ResetBackgroundUrl", "reseturl");
      this.config.set("ActionFoto.Foto5.MessageOnTake", "Here the message :D");
      this.config.set("ActionFoto.Foto6.PictureBackgroundUrl", "puctureurl");
      this.config.set("ActionFoto.Foto6.ResetBackgroundUrl", "reseturl");
      this.config.set("ActionFoto.Foto6.MessageOnTake", "Here the message :D");
      this.config.set("ActionFoto.Foto7.PictureBackgroundUrl", "puctureurl");
      this.config.set("ActionFoto.Foto7.ResetBackgroundUrl", "reseturl");
      this.config.set("ActionFoto.Foto7.MessageOnTake", "Here the message :D");
      this.config.set("ActionFoto.Foto8.PictureBackgroundUrl", "puctureurl");
      this.config.set("ActionFoto.Foto8.ResetBackgroundUrl", "reseturl");
      this.config.set("ActionFoto.Foto8.MessageOnTake", "Here the message :D");
      this.config.set("ActionFoto.Foto9.PictureBackgroundUrl", "puctureurl");
      this.config.set("ActionFoto.Foto9.ResetBackgroundUrl", "reseturl");
      this.config.set("ActionFoto.Foto9.MessageOnTake", "Here the message :D");
      saveConfig();
      saveAfFile();
    }
    System.out.println("ActionFoto Started Up");
    PluginManager pm = Bukkit.getServer().getPluginManager();
    pm.registerEvents(this, this);
    this.artRenderer = new ArtRenderer(this);
    getCommand("AF").setExecutor(new ActionCommands(this));
    Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "AF action reset all");
  }
  
  public void onDisable()
  {
    System.out.println("ActionFoto has stopped");
  }
  
  public static Plugin getInstance()
  {
    return plugin;
  }
  
  public void saveAfFile()
  {
    try
    {
      this.ActionData.save(this.ActionFile);
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }
}
