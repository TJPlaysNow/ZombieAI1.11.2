package com.pzg.www.zombieai.main;

import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import com.pzg.www.zombieai.main.entity.CustomZombie;
import com.pzg.www.zombieai.main.entity.NMSUtil;

import net.minecraft.server.v1_11_R1.EntityZombie;

public class PluginMain extends JavaPlugin implements Listener {
	
	
//	Zombie AI Attributes.
	
//	Every 1 equals half a heart
	public static final double DAMAGE = 10.0;
	
//	Every 1 equals half a heart	
	public static final double HEALTH = 10.0;
	
//	The zombies custom name
	public static final String NAME = "";
	
//	Name visibility, default should be FALSE for it not being visible.
	public static final boolean NAME_VISIBLE = false;
	
//	Is the zombie a tiny zombie, default should be FALSE for no.
	public static final boolean IS_BABY = false;
	
//	Villager zombie, default should be FALSE however we may want to add different zombie era types later on.
	public static final boolean IS_VILLAGER = false;
	
//	The speed of the zombie, deffault is .0023 (I think...)
	public static final double SPEED = 1.0D;
	
	public static Plugin plugin;
	
	@Override
		public void onEnable() {
		plugin = this;
		
		NMSUtil nmsu = new NMSUtil();
		nmsu.registerEntity("Custom Zombie", 54, EntityZombie.class, CustomZombie.class);
	}
	
	
	
	
	
}