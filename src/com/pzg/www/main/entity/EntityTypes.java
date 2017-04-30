package com.pzg.www.main.entity;

import java.util.Map;

import org.bukkit.Location;
import org.bukkit.entity.Entity;

import com.pzg.www.main.utils.Utils;

public enum EntityTypes {
	@SuppressWarnings("unchecked")
	CUSTOM_ZOMBIE("Zombie", 54, (Class<? extends Entity>) CustomZombie.class);
	
	private EntityTypes(String name, int id, Class<? extends Entity> custom) {
		addToMaps(custom, name, id);
	}
	
	public static void spawnEntity(Entity entity, Location loc) {
		entity.teleport(loc);
		loc.getWorld().getEntities().add(entity);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private static void addToMaps(Class clazz, String name, int id) {
		
		
        ((Map<String, Class<CustomZombie>>)Utils.getPrivateField("c", org.bukkit.entity.EntityType.class, null)).put(name, clazz);
        
        
        ((Map<Class<CustomZombie>, String>)Utils.getPrivateField("d", org.bukkit.entity.EntityType.class, null)).put(clazz, name);
        
        
        ((Map<Class<CustomZombie>, Integer>)Utils.getPrivateField("f", org.bukkit.entity.EntityType.class, null)).put(clazz, Integer.valueOf(id));
	}
}