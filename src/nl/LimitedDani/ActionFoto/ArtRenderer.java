package nl.LimitedDani.ActionFoto;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.ItemFrame;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.hanging.HangingBreakEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.map.MapCanvas;
import org.bukkit.map.MapRenderer;
import org.bukkit.map.MapView;
import org.bukkit.plugin.Plugin;

public class ArtRenderer
  implements Listener
{
  private Main plugin;
  @SuppressWarnings({ "unchecked", "rawtypes" })
private List<ItemFrame> frames = new ArrayList();
  @SuppressWarnings({ "unchecked", "rawtypes" })
private List<CustomMapRenderer> renderers = new ArrayList();
  	/*******************************************************
	 * Copyright (C) 2015-2017 Daníque de Jong danique.de.jong2000@gmail.com
	 * 
	 * This file is part of ActionPhoto.
	 * 
	 * ActionPhoto can only be used for personal use
	 * Its not allowed to repost this on any website
	 *******************************************************/
  public ArtRenderer(Main plugin)
  {
    this.plugin = plugin;
    Bukkit.getPluginManager().registerEvents(this, plugin);
  }
  
  public void reloadAll()
  {
    for (Player p : Bukkit.getOnlinePlayers())
    {
      reloadPlayer(p);
    }
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
  
  @SuppressWarnings("deprecation")
public void reloadPlayer(Player p)
  {
    for (ItemFrame frame : this.frames)
    {
      MapView map = Bukkit.getMap(frame.getItem().getDurability());
      p.sendMap(map);
    }
  }
  
  @SuppressWarnings("deprecation")
public void makeArt(Location location, BufferedImage image, String speler, String Attractie)
  {
    MapView map = Bukkit.createMap(location.getWorld());
    for (MapRenderer render : map.getRenderers()) {
      map.removeRenderer(render);
    }
    map.addRenderer(new CustomMapRenderer(image, this.plugin));
    
    ItemStack stack = new ItemStack(Material.MAP, 1, map.getId());
    ItemMeta meta = stack.getItemMeta();
    
    stack.setItemMeta(meta);
    ItemFrame frame = getFrame(location);
    if (frame != null) {
      frame.setItem(stack);
    }
    this.frames.add(frame);
  }
  
  @SuppressWarnings("deprecation")
public void makeArt1(Location location, BufferedImage image)
  {
    MapView map = this.plugin.getServer().createMap(location.getWorld());
    for (MapRenderer render : map.getRenderers()) {
      map.removeRenderer(render);
    }
    map.addRenderer(new CustomMapRenderer(image, this.plugin));
    
    ItemStack stack = new ItemStack(Material.MAP, 1, map.getId());
    ItemMeta meta = stack.getItemMeta();
    stack.setItemMeta(meta);
    ItemFrame frame = getFrame(location);
    if (frame != null) {
      frame.setItem(stack);
    }
    this.frames.add(frame);
  }
  
  public void removeArt(Location location1)
  {
    ItemStack stack1 = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)15);
    ItemMeta meta1 = stack1.getItemMeta();
    stack1.setItemMeta(meta1);
    ItemFrame frame1 = getFrame(location1);
    if (frame1 != null) {
      frame1.setItem(stack1);
    }
  }
  
  private ItemFrame getFrame(Location loc)
  {
    Entity[] arrayOfEntity;
    int j = (arrayOfEntity = loc.getChunk().getEntities()).length;
    for (int i = 0; i < j; i++)
    {
      Entity e = arrayOfEntity[i];
      if (((e instanceof ItemFrame)) && 
        (e.getLocation().getBlock().getLocation().distance(loc) <= 1.1D)) {
        return (ItemFrame)e;
      }
    }
    return null;
  }
  
  @EventHandler
  private void onBreak(HangingBreakEvent event)
  {
    if (this.frames.contains(event.getEntity()))
    {
      event.setCancelled(true);
      this.frames.remove(event.getEntity());
    }
  }
  
  @EventHandler
  private void onQuit(PlayerQuitEvent event)
  {
    Player p = event.getPlayer();
    for (CustomMapRenderer rendered : this.renderers) {
      if (rendered.rendered.contains(p.getName())) {
        rendered.rendered.remove(p.getName());
      }
    }
  }
  
  private class CustomMapRenderer
    extends MapRenderer
  {
    private BufferedImage image;
    @SuppressWarnings({ "rawtypes", "unchecked" })
	private List<String> rendered = new ArrayList();
    
    public CustomMapRenderer(BufferedImage image, Plugin plugin)
    {
      ArtRenderer.this.renderers.add(this);
      this.image = image;
    }
    
    public void render(MapView view, MapCanvas canvas, Player p)
    {
      if (this.rendered.contains(p.getName())) {
        return;
      }
      this.rendered.add(p.getName());
      
      canvas.drawImage(0, 0, this.image);
    }
  }
}
