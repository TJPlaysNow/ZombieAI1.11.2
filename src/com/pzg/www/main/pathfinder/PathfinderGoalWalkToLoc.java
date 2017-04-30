package com.pzg.www.main.pathfinder;

import org.bukkit.Location;

import net.minecraft.server.v1_11_R1.EntityInsentient;
import net.minecraft.server.v1_11_R1.Navigation;
import net.minecraft.server.v1_11_R1.PathEntity;
import net.minecraft.server.v1_11_R1.PathfinderGoal;

public class PathfinderGoalWalkToLoc extends PathfinderGoal {
	
	private double speed;
	private EntityInsentient entity;
	private Location loc;
	private Navigation navigation;
	
	public PathfinderGoalWalkToLoc(double speed, EntityInsentient entity, Location loc) {
		this.speed = speed;
		this.entity = entity;
		this.loc = loc;
		this.navigation = (Navigation) this.entity.getNavigation();
	}
	
	public boolean playerVisible = true;
	
//	onStart
	@Override
	public void c() {
		PathEntity pathEntity = this.navigation.a(loc.getBlockX(), loc.getBlockY(), loc.getBlockZ());
		this.navigation.a(pathEntity, speed);
		if (entity.getGoalTarget() == null) playerVisible = false;
	}
	
//	shouldStart
	@Override
	public boolean a() {
		return playerVisible;
	}
}