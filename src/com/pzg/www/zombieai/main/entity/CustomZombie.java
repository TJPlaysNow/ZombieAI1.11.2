package com.pzg.www.zombieai.main.entity;

import java.util.HashSet;

import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_11_R1.CraftWorld;
import org.bukkit.craftbukkit.v1_11_R1.entity.CraftLivingEntity;
import org.bukkit.entity.Zombie;
import org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason;

import com.pzg.www.zombieai.main.PluginMain;
import com.pzg.www.zombieai.main.Utils;
import com.pzg.www.zombieai.main.pathfinding.PathfinderGoalWalkToLoc;
import com.pzg.www.zombieai.main.pathfinding.PathfinderTargetPlayer;

import net.minecraft.server.v1_11_R1.Entity;
import net.minecraft.server.v1_11_R1.EntityCreature;
import net.minecraft.server.v1_11_R1.EntityHuman;
import net.minecraft.server.v1_11_R1.EntityZombie;
import net.minecraft.server.v1_11_R1.GenericAttributes;
import net.minecraft.server.v1_11_R1.Item;
import net.minecraft.server.v1_11_R1.PathfinderGoalFloat;
import net.minecraft.server.v1_11_R1.PathfinderGoalHurtByTarget;
import net.minecraft.server.v1_11_R1.PathfinderGoalLookAtPlayer;
import net.minecraft.server.v1_11_R1.PathfinderGoalMoveTowardsRestriction;
import net.minecraft.server.v1_11_R1.PathfinderGoalRandomLookaround;
import net.minecraft.server.v1_11_R1.PathfinderGoalRandomStroll;
import net.minecraft.server.v1_11_R1.PathfinderGoalSelector;
import net.minecraft.server.v1_11_R1.PathfinderGoalZombieAttack;
import net.minecraft.server.v1_11_R1.World;

public class CustomZombie extends EntityZombie {

	public PathfinderGoalWalkToLoc pathfinderGoalWalkToLoc;
	public PathfinderTargetPlayer pathfinderTargetPlayer;
	
	public CustomZombie(World world) {
		super(world);
	}
	
	@Override
	protected void initAttributes() {
		super.initAttributes();
		
		this.getAttributeInstance(GenericAttributes.ATTACK_DAMAGE).setValue(PluginMain.DAMAGE);
		this.getAttributeInstance(GenericAttributes.maxHealth).setValue(PluginMain.HEALTH);
		this.getAttributeInstance(GenericAttributes.MOVEMENT_SPEED).setValue(PluginMain.SPEED);
		this.setCustomName(PluginMain.NAME);
		this.setCustomNameVisible(PluginMain.NAME_VISIBLE);
		this.setBaby(PluginMain.IS_BABY);
		
		HashSet<?> goalB = (HashSet<?>)Utils.getPrivateField("b", PathfinderGoalSelector.class, goalSelector); goalB.clear();
        HashSet<?> goalC = (HashSet<?>)Utils.getPrivateField("c", PathfinderGoalSelector.class, goalSelector); goalC.clear();
        HashSet<?> targetB = (HashSet<?>)Utils.getPrivateField("b", PathfinderGoalSelector.class, targetSelector); targetB.clear();
        HashSet<?> targetC = (HashSet<?>)Utils.getPrivateField("c", PathfinderGoalSelector.class, targetSelector); targetC.clear();
		
		loadGoals();
	}
	
	public void loadGoals() {
		this.goalSelector.a(0, new PathfinderGoalFloat(this));
		this.goalSelector.a(2, new PathfinderGoalZombieAttack(this, 1.0D, false));
		this.goalSelector.a(5, new PathfinderGoalMoveTowardsRestriction(this, 1.0D));
		this.goalSelector.a(7, new PathfinderGoalRandomStroll(this, 1.0D));
		this.goalSelector.a(8, new PathfinderGoalLookAtPlayer(this, EntityHuman.class, 8.0F));
		this.goalSelector.a(8, new PathfinderGoalRandomLookaround(this));
		
		this.pathfinderGoalWalkToLoc = new PathfinderGoalWalkToLoc((EntityCreature) this, null, PluginMain.SPEED);
	    this.pathfinderTargetPlayer = new PathfinderTargetPlayer((EntityCreature) this, PluginMain.SPEED);
	     
	    this.targetSelector.a(1, this.pathfinderGoalWalkToLoc);
	    this.targetSelector.a(3, new PathfinderGoalHurtByTarget(this, true));
	}
	
	@Override
	protected Item getLoot() {
		return null;
	}
	
	@Override
	public boolean B (Entity entity) {
		return true;
	}
	
	public static Zombie spawn(Location location) {
		World mcWorld = ((CraftWorld) location.getWorld()).getHandle();
		final CustomZombie customEntity = new CustomZombie(mcWorld);
		customEntity.setLocation(location.getX(), location.getY(), location.getZ(), location.getYaw(), location.getPitch());
		((CraftLivingEntity) customEntity.getBukkitEntity()).setRemoveWhenFarAway(false);
		mcWorld.addEntity(customEntity, SpawnReason.CUSTOM);
		return (Zombie) customEntity.getBukkitEntity();
	}
}