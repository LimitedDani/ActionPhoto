package nl.LimitedDani.ActionFoto;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;

import javax.imageio.ImageIO;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

public class ActionCommands
  implements CommandExecutor, Listener
{
  public static Main plugin;
  
  public ActionCommands(Main main)
  {
    plugin = main;
  }
	/*******************************************************
	 * Copyright (C) 2015-2017 Daníque de Jong danique.de.jong2000@gmail.com
	 * 
	 * This file is part of ActionPhoto.
	 * 
	 * ActionPhoto can only be used for personal use
	 * Its not allowed to repost this on any website
	 *******************************************************/
  public BufferedImage getSkinByName(String string)
  {
    BufferedImage img = null;
    String strurl = "http://www.minecraft-skin-viewer.net/3d.php?layers=true&aa=true&a=0&w=330&wt=10&abg=230&abd=120&ajg=60&ajd=80&ratio=1&format=png&login=" + string + "&headOnly=false&displayHairs=true&randomness=474";
    if (img == null) {
      try
      {
        URL url = new URL(strurl);
        img = ImageIO.read(url);
      }
      catch (Exception localException) {}
    }
    if (img == null) {
      try
      {
        img = ImageIO.read(new URL("http://s3.amazonaws.com/MinecraftSkins/MHF_Question.png"));
      }
      catch (Exception localException1) {}
    }
    return img;
  }
  
  public Image getImageFromURL(String url)
  {
    try
    {
      return ImageIO.read(new URL(url));
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    return null;
  }
  
  @SuppressWarnings({ "deprecation", "rawtypes", "unchecked" })
public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args)
  {
    if (commandLabel.equalsIgnoreCase("AF"))
    {
      if (!sender.isOp()) {
        return true;
      }
      if (args.length == 0)
      {
        sender.sendMessage(ChatColor.RED + " Gebruik: /AF Action Make AF(1-9) <Players or @a>");
        sender.sendMessage(ChatColor.RED + " Gebruik: /AF Action Reset <AF(1-9) of all>");
        sender.sendMessage(ChatColor.RED + " Gebruik: /AF Action Frame AF(1-9) <0-12>");
        return true;
      }
      if (args[0].equalsIgnoreCase("Action"))
      {
        if (args.length < 3)
        {
          sender.sendMessage(ChatColor.RED + " Gebruik: /AF Action Make <AttrAction> <Spelers of @a>");
          sender.sendMessage(ChatColor.RED + " Gebruik: /AF Action Reset <AttrAction of all>");
          sender.sendMessage(ChatColor.RED + " Gebruik: /AF Action Frame <AttrAction> <0-12>");
          return true;
        }
        if (args[1].equalsIgnoreCase("Make"))
        {
          if (args.length != 4) {
            return true;
          }
          Player t = Bukkit.getPlayerExact(args[3]);
          if (t == null)
          {
            sender.sendMessage(ChatColor.RED + " These players are not online!");
            return true;
          }
          if (args[2].equalsIgnoreCase("AF1"))
          {
            Object AF1 = new ArrayList();
            ((ArrayList)AF1).add(t);
            int i1 = plugin.ActionData.getInt("AF1Players.count");
            for (int i = 0; i < ((ArrayList)AF1).size(); i++)
            {
              Location location = new Location(Bukkit.getServer().getWorld(plugin.ActionData.getString("Action.AF1" + i1 + ".world")), plugin.ActionData.getDouble("Action.AF1" + i1 + ".x"), plugin.ActionData.getDouble("Action.AF1" + i1 + ".y"), plugin.ActionData.getDouble("Action.AF1" + i1 + ".z"));
              String speler = ((Player)((ArrayList)AF1).get(i)).getPlayer().getName();
              String AttrAction = "AF1";
              BufferedImage image = new BufferedImage(150, 150, 2);
              Graphics m0 = image.createGraphics();
              m0.drawImage(plugin.artRenderer.getImageFromURL(Main.plugin.getConfig().getString("ActionFoto.Foto1.PictureBackgroundUrl")), -12, -10, 150, 150, null);
              m0.drawImage(getSkinByName(((Player)((ArrayList)AF1).get(i)).getPlayer().getName()), 50, 50, 40, 40, null);
              m0.dispose();
              Graphics g = image.createGraphics();
              g.drawString(speler, 1, 10);
              m0.dispose();
              plugin.artRenderer.makeArt(location, image, speler, AttrAction);
              i1++;
              plugin.ActionData.set("AF1Players.count", Integer.valueOf(i1));
              plugin.saveAfFile();
            }
            t.sendMessage(Main.plugin.getConfig().getString("ActionFoto.Foto1.MessageOnTake"));
            ((ArrayList)AF1).clear();
            ((ArrayList)AF1).remove(t);
            Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable()
            {
              public void run()
              {
                ActionCommands.plugin.ActionData.set("AF1Players.count", Integer.valueOf(0));
                ActionCommands.plugin.saveAfFile();
              }
            }, 0L, 40L);
            return true;
          }
          if (args[2].equalsIgnoreCase("AF2"))
          {
            Object AF2 = new ArrayList();
            ((ArrayList)AF2).add(t);
            int i1 = plugin.ActionData.getInt("AF2Players.count");
            for (int i = 0; i < ((ArrayList)AF2).size(); i++)
            {
              Location location = new Location(Bukkit.getServer().getWorld(plugin.ActionData.getString("Action.AF2" + i1 + ".world")), plugin.ActionData.getDouble("Action.AF2" + i1 + ".x"), plugin.ActionData.getDouble("Action.AF2" + i1 + ".y"), plugin.ActionData.getDouble("Action.AF2" + i1 + ".z"));
              String speler = ((Player)((ArrayList)AF2).get(i)).getPlayer().getName();
              String AttrAction = "AF2";
              BufferedImage image = new BufferedImage(150, 150, 2);
              Graphics m0 = image.createGraphics();
              m0.drawImage(plugin.artRenderer.getImageFromURL(Main.plugin.getConfig().getString("ActionFoto.Foto2.PictureBackgroundUrl")), -12, -10, 150, 150, null);
              m0.drawImage(getSkinByName(((Player)((ArrayList)AF2).get(i)).getPlayer().getName()), 50, 50, 40, 40, null);
              m0.dispose();
              Graphics g = image.createGraphics();
              g.drawString(speler, 1, 10);
              m0.dispose();
              plugin.artRenderer.makeArt(location, image, speler, AttrAction);
              i1++;
              plugin.ActionData.set("AF2Players.count", Integer.valueOf(i1));
              plugin.saveAfFile();
            }
            t.sendMessage(Main.plugin.getConfig().getString("ActionFoto.Foto2.MessageOnTake"));
            ((ArrayList)AF2).clear();
            ((ArrayList)AF2).remove(t);
            Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable()
            {
              public void run()
              {
                ActionCommands.plugin.ActionData.set("AF2Players.count", Integer.valueOf(0));
                ActionCommands.plugin.saveAfFile();
              }
            }, 0L, 40L);
            return true;
          }
          if (args[2].equalsIgnoreCase("AF3"))
          {
            Object AF3 = new ArrayList();
            ((ArrayList)AF3).add(t);
            int i1 = plugin.ActionData.getInt("AF3Players.count");
            for (int i = 0; i < ((ArrayList)AF3).size(); i++)
            {
              Location location = new Location(Bukkit.getServer().getWorld(plugin.ActionData.getString("Action.AF3" + i1 + ".world")), plugin.ActionData.getDouble("Action.AF3" + i1 + ".x"), plugin.ActionData.getDouble("Action.AF3" + i1 + ".y"), plugin.ActionData.getDouble("Action.AF3" + i1 + ".z"));
              String speler = ((Player)((ArrayList)AF3).get(i)).getPlayer().getName();
              String AttrAction = "AF3";
              BufferedImage image = new BufferedImage(150, 150, 2);
              Graphics m0 = image.createGraphics();
              m0.drawImage(plugin.artRenderer.getImageFromURL(Main.plugin.getConfig().getString("ActionFoto.Foto3.PictureBackgroundUrl")), -12, -10, 150, 150, null);
              m0.drawImage(getSkinByName(((Player)((ArrayList)AF3).get(i)).getPlayer().getName()), 50, 50, 40, 40, null);
              m0.dispose();
              Graphics g = image.createGraphics();
              g.drawString(speler, 1, 10);
              m0.dispose();
              plugin.artRenderer.makeArt(location, image, speler, AttrAction);
              i1++;
              plugin.ActionData.set("AF3Players.count", Integer.valueOf(i1));
              plugin.saveAfFile();
            }
            t.sendMessage(Main.plugin.getConfig().getString("ActionFoto.Foto3.MessageOnTake"));
            ((ArrayList)AF3).clear();
            ((ArrayList)AF3).remove(t);
            Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable()
            {
              public void run()
              {
                ActionCommands.plugin.ActionData.set("AF3Players.count", Integer.valueOf(0));
                ActionCommands.plugin.saveAfFile();
              }
            }, 0L, 40L);
            return true;
          }
          if (args[2].equalsIgnoreCase("AF4"))
          {
            Object AF4 = new ArrayList();
            ((ArrayList)AF4).add(t);
            int i1 = plugin.ActionData.getInt("AF4Players.count");
            for (int i = 0; i < ((ArrayList)AF4).size(); i++)
            {
              Location location = new Location(Bukkit.getServer().getWorld(plugin.ActionData.getString("Action.AF4" + i1 + ".world")), plugin.ActionData.getDouble("Action.AF4" + i1 + ".x"), plugin.ActionData.getDouble("Action.AF4" + i1 + ".y"), plugin.ActionData.getDouble("Action.AF4" + i1 + ".z"));
              String speler = ((Player)((ArrayList)AF4).get(i)).getPlayer().getName();
              String AttrAction = "AF4";
              BufferedImage image = new BufferedImage(150, 150, 2);
              Graphics m0 = image.createGraphics();
              m0.drawImage(plugin.artRenderer.getImageFromURL(Main.plugin.getConfig().getString("ActionFoto.Foto4.PictureBackgroundUrl")), -12, -10, 150, 150, null);
              m0.drawImage(getSkinByName(((Player)((ArrayList)AF4).get(i)).getPlayer().getName()), 50, 50, 40, 40, null);
              m0.dispose();
              Graphics g = image.createGraphics();
              g.drawString(speler, 1, 10);
              m0.dispose();
              plugin.artRenderer.makeArt(location, image, speler, AttrAction);
              i1++;
              plugin.ActionData.set("AF4Players.count", Integer.valueOf(i1));
              plugin.saveAfFile();
            }
            t.sendMessage(Main.plugin.getConfig().getString("ActionFoto.Foto4.MessageOnTake"));
            ((ArrayList)AF4).clear();
            ((ArrayList)AF4).remove(t);
            Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable()
            {
              public void run()
              {
                ActionCommands.plugin.ActionData.set("AF4Players.count", Integer.valueOf(0));
                ActionCommands.plugin.saveAfFile();
              }
            }, 0L, 40L);
            return true;
          }
          if (args[2].equalsIgnoreCase("AF5"))
          {
            Object AF5 = new ArrayList();
            ((ArrayList)AF5).add(t);
            int i1 = plugin.ActionData.getInt("AF5Players.count");
            for (int i = 0; i < ((ArrayList)AF5).size(); i++)
            {
              Location location = new Location(Bukkit.getServer().getWorld(plugin.ActionData.getString("Action.AF5" + i1 + ".world")), plugin.ActionData.getDouble("Action.AF5" + i1 + ".x"), plugin.ActionData.getDouble("Action.AF5" + i1 + ".y"), plugin.ActionData.getDouble("Action.AF5" + i1 + ".z"));
              String speler = ((Player)((ArrayList)AF5).get(i)).getPlayer().getName();
              String AttrAction = "AF5";
              BufferedImage image = new BufferedImage(150, 150, 2);
              Graphics m0 = image.createGraphics();
              m0.drawImage(plugin.artRenderer.getImageFromURL(Main.plugin.getConfig().getString("ActionFoto.Foto5.PictureBackgroundUrl")), -12, -10, 150, 150, null);
              m0.drawImage(getSkinByName(((Player)((ArrayList)AF5).get(i)).getPlayer().getName()), 50, 50, 40, 40, null);
              m0.dispose();
              Graphics g = image.createGraphics();
              g.drawString(speler, 1, 10);
              m0.dispose();
              plugin.artRenderer.makeArt(location, image, speler, AttrAction);
              i1++;
              plugin.ActionData.set("AF5Players.count", Integer.valueOf(i1));
              plugin.saveAfFile();
            }
            t.sendMessage(Main.plugin.getConfig().getString("ActionFoto.Foto5.MessageOnTake"));
            ((ArrayList)AF5).clear();
            ((ArrayList)AF5).remove(t);
            Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable()
            {
              public void run()
              {
                ActionCommands.plugin.ActionData.set("AF5Players.count", Integer.valueOf(0));
                ActionCommands.plugin.saveAfFile();
              }
            }, 0L, 40L);
            return true;
          }
          if (args[2].equalsIgnoreCase("AF6"))
          {
            Object AF6 = new ArrayList();
            ((ArrayList)AF6).add(t);
            int i1 = plugin.ActionData.getInt("AF6Players.count");
            for (int i = 0; i < ((ArrayList)AF6).size(); i++)
            {
              Location location = new Location(Bukkit.getServer().getWorld(plugin.ActionData.getString("Action.AF6" + i1 + ".world")), plugin.ActionData.getDouble("Action.AF6" + i1 + ".x"), plugin.ActionData.getDouble("Action.AF6" + i1 + ".y"), plugin.ActionData.getDouble("Action.AF6" + i1 + ".z"));
              String speler = ((Player)((ArrayList)AF6).get(i)).getPlayer().getName();
              String AttrAction = "AF6";
              BufferedImage image = new BufferedImage(150, 150, 2);
              Graphics m0 = image.createGraphics();
              m0.drawImage(plugin.artRenderer.getImageFromURL(Main.plugin.getConfig().getString("ActionFoto.Foto6.PictureBackgroundUrl")), -12, -10, 150, 150, null);
              m0.drawImage(getSkinByName(((Player)((ArrayList)AF6).get(i)).getPlayer().getName()), 50, 50, 40, 40, null);
              m0.dispose();
              Graphics g = image.createGraphics();
              g.drawString(speler, 1, 10);
              m0.dispose();
              plugin.artRenderer.makeArt(location, image, speler, AttrAction);
              i1++;
              plugin.ActionData.set("AF6Players.count", Integer.valueOf(i1));
              plugin.saveAfFile();
            }
            t.sendMessage(Main.plugin.getConfig().getString("ActionFoto.Foto6.MessageOnTake"));
            ((ArrayList)AF6).clear();
            ((ArrayList)AF6).remove(t);
            Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable()
            {
              public void run()
              {
                ActionCommands.plugin.ActionData.set("AF6Players.count", Integer.valueOf(0));
                ActionCommands.plugin.saveAfFile();
              }
            }, 0L, 40L);
            return true;
          }
          if (args[2].equalsIgnoreCase("AF7"))
          {
            Object AF7 = new ArrayList();
            ((ArrayList)AF7).add(t);
            int i1 = plugin.ActionData.getInt("AF7Players.count");
            for (int i = 0; i < ((ArrayList)AF7).size(); i++)
            {
              Location location = new Location(Bukkit.getServer().getWorld(plugin.ActionData.getString("Action.AF7" + i1 + ".world")), plugin.ActionData.getDouble("Action.AF7" + i1 + ".x"), plugin.ActionData.getDouble("Action.AF7" + i1 + ".y"), plugin.ActionData.getDouble("Action.AF7" + i1 + ".z"));
              String speler = ((Player)((ArrayList)AF7).get(i)).getPlayer().getName();
              String AttrAction = "AF7";
              BufferedImage image = new BufferedImage(150, 150, 2);
              Graphics m0 = image.createGraphics();
              m0.drawImage(plugin.artRenderer.getImageFromURL(Main.plugin.getConfig().getString("ActionFoto.Foto7.PictureBackgroundUrl")), -12, -10, 150, 150, null);
              m0.drawImage(getSkinByName(((Player)((ArrayList)AF7).get(i)).getPlayer().getName()), 50, 50, 40, 40, null);
              m0.dispose();
              Graphics g = image.createGraphics();
              g.drawString(speler, 1, 10);
              m0.dispose();
              plugin.artRenderer.makeArt(location, image, speler, AttrAction);
              i1++;
              plugin.ActionData.set("AF7Players.count", Integer.valueOf(i1));
              plugin.saveAfFile();
            }
            t.sendMessage(Main.plugin.getConfig().getString("ActionFoto.Foto7.MessageOnTake"));
            ((ArrayList)AF7).clear();
            ((ArrayList)AF7).remove(t);
            Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable()
            {
              public void run()
              {
                ActionCommands.plugin.ActionData.set("AF7Players.count", Integer.valueOf(0));
                ActionCommands.plugin.saveAfFile();
              }
            }, 0L, 40L);
            return true;
          }
          if (args[2].equalsIgnoreCase("AF8"))
          {
            Object AF8 = new ArrayList();
            ((ArrayList)AF8).add(t);
            int i1 = plugin.ActionData.getInt("AF8Players.count");
            for (int i = 0; i < ((ArrayList)AF8).size(); i++)
            {
              Location location = new Location(Bukkit.getServer().getWorld(plugin.ActionData.getString("Action.AF8" + i1 + ".world")), plugin.ActionData.getDouble("Action.AF8" + i1 + ".x"), plugin.ActionData.getDouble("Action.AF8" + i1 + ".y"), plugin.ActionData.getDouble("Action.AF8" + i1 + ".z"));
              String speler = ((Player)((ArrayList)AF8).get(i)).getPlayer().getName();
              String AttrAction = "AF8";
              BufferedImage image = new BufferedImage(150, 150, 2);
              Graphics m0 = image.createGraphics();
              m0.drawImage(plugin.artRenderer.getImageFromURL(Main.plugin.getConfig().getString("ActionFoto.Foto8.PictureBackgroundUrl")), -12, -10, 150, 150, null);
              m0.drawImage(getSkinByName(((Player)((ArrayList)AF8).get(i)).getPlayer().getName()), 50, 50, 40, 40, null);
              m0.dispose();
              Graphics g = image.createGraphics();
              g.drawString(speler, 1, 10);
              m0.dispose();
              plugin.artRenderer.makeArt(location, image, speler, AttrAction);
              i1++;
              plugin.ActionData.set("AF8Players.count", Integer.valueOf(i1));
              plugin.saveAfFile();
            }
            t.sendMessage(Main.plugin.getConfig().getString("ActionFoto.Foto8.MessageOnTake"));
            ((ArrayList)AF8).clear();
            ((ArrayList)AF8).remove(t);
            Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable()
            {
              public void run()
              {
                ActionCommands.plugin.ActionData.set("AF8Players.count", Integer.valueOf(0));
                ActionCommands.plugin.saveAfFile();
              }
            }, 0L, 40L);
            return true;
          }
          if (args[2].equalsIgnoreCase("AF9"))
          {
            Object AF9 = new ArrayList();
            ((ArrayList)AF9).add(t);
            int i1 = plugin.ActionData.getInt("AF9Players.count");
            for (int i = 0; i < ((ArrayList)AF9).size(); i++)
            {
              Location location = new Location(Bukkit.getServer().getWorld(plugin.ActionData.getString("Action.AF9" + i1 + ".world")), plugin.ActionData.getDouble("Action.AF9" + i1 + ".x"), plugin.ActionData.getDouble("Action.AF9" + i1 + ".y"), plugin.ActionData.getDouble("Action.AF9" + i1 + ".z"));
              String speler = ((Player)((ArrayList)AF9).get(i)).getPlayer().getName();
              String AttrAction = "AF9";
              BufferedImage image = new BufferedImage(150, 150, 2);
              Graphics m0 = image.createGraphics();
              m0.drawImage(plugin.artRenderer.getImageFromURL(Main.plugin.getConfig().getString("ActionFoto.Foto9.PictureBackgroundUrl")), -12, -10, 150, 150, null);
              m0.drawImage(getSkinByName(((Player)((ArrayList)AF9).get(i)).getPlayer().getName()), 50, 50, 40, 40, null);
              m0.dispose();
              Graphics g = image.createGraphics();
              g.drawString(speler, 1, 10);
              m0.dispose();
              plugin.artRenderer.makeArt(location, image, speler, AttrAction);
              i1++;
              plugin.ActionData.set("AF9Players.count", Integer.valueOf(i1));
              plugin.saveAfFile();
            }
            t.sendMessage(Main.plugin.getConfig().getString("ActionFoto.Foto9.MessageOnTake"));
            ((ArrayList)AF9).clear();
            ((ArrayList)AF9).remove(t);
            Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable()
            {
              public void run()
              {
                ActionCommands.plugin.ActionData.set("AF9Players.count", Integer.valueOf(0));
                ActionCommands.plugin.saveAfFile();
              }
            }, 0L, 40L);
            return true;
          }
        }
        if (args[1].equalsIgnoreCase("Reset"))
        {
          if (args.length != 3)
          {
            sender.sendMessage("�3Use:�7 /AF Action Reset AF(1-9)");
            return true;
          }
          if (args[2].equalsIgnoreCase("all"))
          {
            sender.sendMessage("The ActionFoto's are reset!");
            Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "AF Action Reset AF1");
            Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "AF Action Reset AF2");
            Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "AF Action Reset AF3");
            Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "AF Action Reset AF4");
            Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "AF Action Reset AF5");
            Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "AF Action Reset AF6");
            Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "AF Action Reset AF7");
            Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "AF Action Reset AF8");
            Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "AF Action Reset AF9");
          }
          if (args[2].equalsIgnoreCase("AF1"))
          {
            int i2 = plugin.ActionData.getInt("AF1Reset.count");
            for (int i = 0; i < 12; i++)
            {
              Location location = new Location(Bukkit.getServer().getWorld(plugin.ActionData.getString("Action.AF1" + i2 + ".world")), plugin.ActionData.getDouble("Action.AF1" + i2 + ".x"), plugin.ActionData.getDouble("Action.AF1" + i2 + ".y"), plugin.ActionData.getDouble("Action.AF1" + i2 + ".z"));
              BufferedImage image = new BufferedImage(150, 150, 2);
              Object m0 = image.createGraphics();
              ((Graphics)m0).drawImage(plugin.artRenderer.getImageFromURL(Main.plugin.getConfig().getString("ActionFoto.Foto1.ResetBackgroundUrl")), -12, -10, 150, 150, null);
              ((Graphics)m0).dispose();
              Object g = image.createGraphics();
              ((Graphics)g).drawString("", 15, 10);
              ((Graphics)m0).dispose();
              plugin.artRenderer.makeArt1(location, image);
              i2++;
              plugin.ActionData.set("AF1Players.count", Integer.valueOf(i2));
              plugin.saveAfFile();
            }
            sender.sendMessage("�7The ActoinFoto of AF1 are reset!");
            Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable()
            {
              public void run()
              {
                ActionCommands.plugin.ActionData.set("AF1Reset.count", Integer.valueOf(0));
                ActionCommands.plugin.ActionData.set("AF1Players.count", Integer.valueOf(0));
                ActionCommands.plugin.saveAfFile();
              }
            }, 0L, 40L);
            return true;
          }
          if (args[2].equalsIgnoreCase("AF2"))
          {
            int i2 = plugin.ActionData.getInt("AF2Reset.count");
            for (int i = 0; i < 12; i++)
            {
              Location location = new Location(Bukkit.getServer().getWorld(plugin.ActionData.getString("Action.AF2" + i2 + ".world")), plugin.ActionData.getDouble("Action.AF2" + i2 + ".x"), plugin.ActionData.getDouble("Action.AF2" + i2 + ".y"), plugin.ActionData.getDouble("Action.AF2" + i2 + ".z"));
              BufferedImage image = new BufferedImage(150, 150, 2);
              Object m0 = image.createGraphics();
              ((Graphics)m0).drawImage(plugin.artRenderer.getImageFromURL(Main.plugin.getConfig().getString("ActionFoto.Foto2.ResetBackgroundUrl")), -12, -10, 150, 150, null);
              ((Graphics)m0).dispose();
              Object g = image.createGraphics();
              ((Graphics)g).drawString("", 15, 10);
              ((Graphics)m0).dispose();
              plugin.artRenderer.makeArt1(location, image);
              i2++;
              plugin.ActionData.set("AF2Players.count", Integer.valueOf(i2));
              plugin.saveAfFile();
            }
            sender.sendMessage("�7The ActoinFoto of AF2 are reset!");
            Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable()
            {
              public void run()
              {
                ActionCommands.plugin.ActionData.set("AF2Reset.count", Integer.valueOf(0));
                ActionCommands.plugin.ActionData.set("AF2Players.count", Integer.valueOf(0));
                ActionCommands.plugin.saveAfFile();
              }
            }, 0L, 40L);
            return true;
          }
          if (args[2].equalsIgnoreCase("AF3"))
          {
            int i2 = plugin.ActionData.getInt("AF3Reset.count");
            for (int i = 0; i < 12; i++)
            {
              Location location = new Location(Bukkit.getServer().getWorld(plugin.ActionData.getString("Action.AF3" + i2 + ".world")), plugin.ActionData.getDouble("Action.AF3" + i2 + ".x"), plugin.ActionData.getDouble("Action.AF3" + i2 + ".y"), plugin.ActionData.getDouble("Action.AF3" + i2 + ".z"));
              BufferedImage image = new BufferedImage(150, 150, 2);
              Object m0 = image.createGraphics();
              ((Graphics)m0).drawImage(plugin.artRenderer.getImageFromURL(Main.plugin.getConfig().getString("ActionFoto.Foto3.ResetBackgroundUrl")), -12, -10, 150, 150, null);
              ((Graphics)m0).dispose();
              Object g = image.createGraphics();
              ((Graphics)g).drawString("", 15, 10);
              ((Graphics)m0).dispose();
              plugin.artRenderer.makeArt1(location, image);
              i2++;
              plugin.ActionData.set("AF3Players.count", Integer.valueOf(i2));
              plugin.saveAfFile();
            }
            sender.sendMessage("�7The ActoinFoto of AF3 are reset!");
            Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable()
            {
              public void run()
              {
                ActionCommands.plugin.ActionData.set("AF3Reset.count", Integer.valueOf(0));
                ActionCommands.plugin.ActionData.set("AF3Players.count", Integer.valueOf(0));
                ActionCommands.plugin.saveAfFile();
              }
            }, 0L, 40L);
            return true;
          }
          if (args[2].equalsIgnoreCase("AF4"))
          {
            int i2 = plugin.ActionData.getInt("AF4Reset.count");
            for (int i = 0; i < 12; i++)
            {
              Location location = new Location(Bukkit.getServer().getWorld(plugin.ActionData.getString("Action.AF4" + i2 + ".world")), plugin.ActionData.getDouble("Action.AF4" + i2 + ".x"), plugin.ActionData.getDouble("Action.AF4" + i2 + ".y"), plugin.ActionData.getDouble("Action.AF4" + i2 + ".z"));
              BufferedImage image = new BufferedImage(150, 150, 2);
              Object m0 = image.createGraphics();
              ((Graphics)m0).drawImage(plugin.artRenderer.getImageFromURL(Main.plugin.getConfig().getString("ActionFoto.Foto4.ResetBackgroundUrl")), -12, -10, 150, 150, null);
              ((Graphics)m0).dispose();
              Object g = image.createGraphics();
              ((Graphics)g).drawString("", 15, 10);
              ((Graphics)m0).dispose();
              plugin.artRenderer.makeArt1(location, image);
              i2++;
              plugin.ActionData.set("AF4Players.count", Integer.valueOf(i2));
              plugin.saveAfFile();
            }
            sender.sendMessage("�7The ActoinFoto of AF4 are reset!");
            Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable()
            {
              public void run()
              {
                ActionCommands.plugin.ActionData.set("AF4Reset.count", Integer.valueOf(0));
                ActionCommands.plugin.ActionData.set("AF4Players.count", Integer.valueOf(0));
                ActionCommands.plugin.saveAfFile();
              }
            }, 0L, 40L);
            return true;
          }
          if (args[2].equalsIgnoreCase("AF5"))
          {
            int i2 = plugin.ActionData.getInt("AF5Reset.count");
            for (int i = 0; i < 12; i++)
            {
              Location location = new Location(Bukkit.getServer().getWorld(plugin.ActionData.getString("Action.AF5" + i2 + ".world")), plugin.ActionData.getDouble("Action.AF5" + i2 + ".x"), plugin.ActionData.getDouble("Action.AF5" + i2 + ".y"), plugin.ActionData.getDouble("Action.AF5" + i2 + ".z"));
              BufferedImage image = new BufferedImage(150, 150, 2);
              Object m0 = image.createGraphics();
              ((Graphics)m0).drawImage(plugin.artRenderer.getImageFromURL(Main.plugin.getConfig().getString("ActionFoto.Foto5.ResetBackgroundUrl")), -12, -10, 150, 150, null);
              ((Graphics)m0).dispose();
              Object g = image.createGraphics();
              ((Graphics)g).drawString("", 15, 10);
              ((Graphics)m0).dispose();
              plugin.artRenderer.makeArt1(location, image);
              i2++;
              plugin.ActionData.set("AF5Players.count", Integer.valueOf(i2));
              plugin.saveAfFile();
            }
            sender.sendMessage("�7The ActoinFoto of AF5 are reset!");
            Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable()
            {
              public void run()
              {
                ActionCommands.plugin.ActionData.set("AF5Reset.count", Integer.valueOf(0));
                ActionCommands.plugin.ActionData.set("AF5Players.count", Integer.valueOf(0));
                ActionCommands.plugin.saveAfFile();
              }
            }, 0L, 40L);
            return true;
          }
          if (args[2].equalsIgnoreCase("AF6"))
          {
            int i2 = plugin.ActionData.getInt("AF6Reset.count");
            for (int i = 0; i < 12; i++)
            {
              Location location = new Location(Bukkit.getServer().getWorld(plugin.ActionData.getString("Action.AF6" + i2 + ".world")), plugin.ActionData.getDouble("Action.AF6" + i2 + ".x"), plugin.ActionData.getDouble("Action.AF6" + i2 + ".y"), plugin.ActionData.getDouble("Action.AF6" + i2 + ".z"));
              BufferedImage image = new BufferedImage(150, 150, 2);
              Object m0 = image.createGraphics();
              ((Graphics)m0).drawImage(plugin.artRenderer.getImageFromURL(Main.plugin.getConfig().getString("ActionFoto.Foto6.ResetBackgroundUrl")), -12, -10, 150, 150, null);
              ((Graphics)m0).dispose();
              Object g = image.createGraphics();
              ((Graphics)g).drawString("", 15, 10);
              ((Graphics)m0).dispose();
              plugin.artRenderer.makeArt1(location, image);
              i2++;
              plugin.ActionData.set("AF6Players.count", Integer.valueOf(i2));
              plugin.saveAfFile();
            }
            sender.sendMessage("�7The ActoinFoto of AF6 are reset!");
            Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable()
            {
              public void run()
              {
                ActionCommands.plugin.ActionData.set("AF6Reset.count", Integer.valueOf(0));
                ActionCommands.plugin.ActionData.set("AF6Players.count", Integer.valueOf(0));
                ActionCommands.plugin.saveAfFile();
              }
            }, 0L, 40L);
            return true;
          }
          if (args[2].equalsIgnoreCase("AF7"))
          {
            int i2 = plugin.ActionData.getInt("AF7Reset.count");
            for (int i = 0; i < 12; i++)
            {
              Location location = new Location(Bukkit.getServer().getWorld(plugin.ActionData.getString("Action.AF7" + i2 + ".world")), plugin.ActionData.getDouble("Action.AF7" + i2 + ".x"), plugin.ActionData.getDouble("Action.AF7" + i2 + ".y"), plugin.ActionData.getDouble("Action.AF7" + i2 + ".z"));
              BufferedImage image = new BufferedImage(150, 150, 2);
              Object m0 = image.createGraphics();
              ((Graphics)m0).drawImage(plugin.artRenderer.getImageFromURL(Main.plugin.getConfig().getString("ActionFoto.Foto7.ResetBackgroundUrl")), -12, -10, 150, 150, null);
              ((Graphics)m0).dispose();
              Object g = image.createGraphics();
              ((Graphics)g).drawString("", 15, 10);
              ((Graphics)m0).dispose();
              plugin.artRenderer.makeArt1(location, image);
              i2++;
              plugin.ActionData.set("AF7Players.count", Integer.valueOf(i2));
              plugin.saveAfFile();
            }
            sender.sendMessage("�7The ActoinFoto of AF7 are reset!");
            Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable()
            {
              public void run()
              {
                ActionCommands.plugin.ActionData.set("AF7Reset.count", Integer.valueOf(0));
                ActionCommands.plugin.ActionData.set("AF7Players.count", Integer.valueOf(0));
                ActionCommands.plugin.saveAfFile();
              }
            }, 0L, 40L);
            return true;
          }
          if (args[2].equalsIgnoreCase("AF8"))
          {
            int i2 = plugin.ActionData.getInt("AF8Reset.count");
            for (int i = 0; i < 12; i++)
            {
              Location location = new Location(Bukkit.getServer().getWorld(plugin.ActionData.getString("Action.AF8" + i2 + ".world")), plugin.ActionData.getDouble("Action.AF8" + i2 + ".x"), plugin.ActionData.getDouble("Action.AF8" + i2 + ".y"), plugin.ActionData.getDouble("Action.AF8" + i2 + ".z"));
              BufferedImage image = new BufferedImage(150, 150, 2);
              Object m0 = image.createGraphics();
              ((Graphics)m0).drawImage(plugin.artRenderer.getImageFromURL(Main.plugin.getConfig().getString("ActionFoto.Foto8.ResetBackgroundUrl")), -12, -10, 150, 150, null);
              ((Graphics)m0).dispose();
              Object g = image.createGraphics();
              ((Graphics)g).drawString("", 15, 10);
              ((Graphics)m0).dispose();
              plugin.artRenderer.makeArt1(location, image);
              i2++;
              plugin.ActionData.set("AF8Players.count", Integer.valueOf(i2));
              plugin.saveAfFile();
            }
            sender.sendMessage("�7The ActoinFoto of AF8 are reset!");
            Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable()
            {
              public void run()
              {
                ActionCommands.plugin.ActionData.set("AF8Reset.count", Integer.valueOf(0));
                ActionCommands.plugin.ActionData.set("AF8Players.count", Integer.valueOf(0));
                ActionCommands.plugin.saveAfFile();
              }
            }, 0L, 40L);
            return true;
          }
          if (args[2].equalsIgnoreCase("AF9"))
          {
            int i2 = plugin.ActionData.getInt("AF9Reset.count");
            for (int i = 0; i < 12; i++)
            {
              Location location = new Location(Bukkit.getServer().getWorld(plugin.ActionData.getString("Action.AF9" + i2 + ".world")), plugin.ActionData.getDouble("Action.AF9" + i2 + ".x"), plugin.ActionData.getDouble("Action.AF9" + i2 + ".y"), plugin.ActionData.getDouble("Action.AF9" + i2 + ".z"));
              BufferedImage image = new BufferedImage(150, 150, 2);
              Object m0 = image.createGraphics();
              ((Graphics)m0).drawImage(plugin.artRenderer.getImageFromURL(Main.plugin.getConfig().getString("ActionFoto.Foto9.ResetBackgroundUrl")), -12, -10, 150, 150, null);
              ((Graphics)m0).dispose();
              Object g = image.createGraphics();
              ((Graphics)g).drawString("", 15, 10);
              ((Graphics)m0).dispose();
              plugin.artRenderer.makeArt1(location, image);
              i2++;
              plugin.ActionData.set("AF9Players.count", Integer.valueOf(i2));
              plugin.saveAfFile();
            }
            sender.sendMessage("�7The ActoinFoto of AF9 are reset!");
            Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable()
            {
              public void run()
              {
                ActionCommands.plugin.ActionData.set("AF9Reset.count", Integer.valueOf(0));
                ActionCommands.plugin.ActionData.set("AF9Players.count", Integer.valueOf(0));
                ActionCommands.plugin.saveAfFile();
              }
            }, 0L, 40L);
            return true;
          }
        }
        if (args[1].equalsIgnoreCase("Frame"))
        {
          Player player = (Player)sender;
          int maxDistance = 50;
          Block target = player.getTargetBlock((HashSet<Byte>)null, maxDistance);
          if (args.length != 4)
          {
            sender.sendMessage("�3Use /AF Action Frame AF(1-9) <1/12>");
            return true;
          }
          if (target.getType() != Material.AIR)
          {
            plugin.ActionData.set("Action." + args[2] + args[3] + ".world", target.getWorld().getName());
            plugin.ActionData.set("Action." + args[2] + args[3] + ".x", Double.valueOf(target.getLocation().getX()));
            plugin.ActionData.set("Action." + args[2] + args[3] + ".y", Double.valueOf(target.getLocation().getY()));
            plugin.ActionData.set("Action." + args[2] + args[3] + ".z", Double.valueOf(target.getLocation().getZ()));
            plugin.saveAfFile();
            sender.sendMessage("�7You have the frame number �c" + args[3] + "�7 of �c" + args[2] + "�7 added!");
            return true;
          }
        }
      }
    }
    return true;
  }
}
