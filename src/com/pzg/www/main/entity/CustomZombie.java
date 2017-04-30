package com.pzg.www.main.entity;

import java.util.List;

import org.bukkit.World;
import org.bukkit.craftbukkit.v1_11_R1.CraftWorld;

import com.pzg.www.main.PluginMain;
import com.pzg.www.main.utils.Utils;

import net.minecraft.server.v1_11_R1.EntityHuman;
import net.minecraft.server.v1_11_R1.EntityZombie;
import net.minecraft.server.v1_11_R1.GenericAttributes;
import net.minecraft.server.v1_11_R1.PathfinderGoalFloat;
import net.minecraft.server.v1_11_R1.PathfinderGoalHurtByTarget;
import net.minecraft.server.v1_11_R1.PathfinderGoalLookAtPlayer;
import net.minecraft.server.v1_11_R1.PathfinderGoalNearestAttackableTarget;
import net.minecraft.server.v1_11_R1.PathfinderGoalRandomLookaround;
import net.minecraft.server.v1_11_R1.PathfinderGoalRandomStroll;
import net.minecraft.server.v1_11_R1.PathfinderGoalSelector;

public class CustomZombie extends EntityZombie {

	public CustomZombie(World world) {
		super(((CraftWorld) world).getHandle());
		
		
//		Clear Zombie AI
		List<?> goalB = (List<?>)Utils.getPrivateField("b", PathfinderGoalSelector.class, goalSelector);
		goalB.clear();
		List<?> goalC = (List<?>)Utils.getPrivateField("b", PathfinderGoalSelector.class, goalSelector);
		goalC.clear();
		List<?> targetB = (List<?>)Utils.getPrivateField("b", PathfinderGoalSelector.class, targetSelector);
		targetB.clear();
		List<?> targetC = (List<?>)Utils.getPrivateField("b", PathfinderGoalSelector.class, targetSelector);
		targetC.clear();
		
		
//		Set Zombie AI
		this.goalSelector.a(0, new PathfinderGoalFloat(this));
		
		
//		Zombie walks randomly if no player in sight
		this.goalSelector.a(7, new PathfinderGoalRandomStroll(this, 1.0D));
		this.goalSelector.a(8, new PathfinderGoalRandomLookaround(this));
		
		
//		Zombie takes damage
		this.targetSelector.a(1, new PathfinderGoalHurtByTarget(this, true));
		
		
//		Zombie tracks player
		this.targetSelector.a(2, new PathfinderGoalNearestAttackableTarget<EntityHuman>(this, EntityHuman.class, true));
		this.goalSelector.a(8, new PathfinderGoalLookAtPlayer(this, EntityHuman.class, 8.0F));
		
		
//		Set Zombie Range
		this.getAttributeInstance(GenericAttributes.FOLLOW_RANGE).setValue(PluginMain.zombieRange);
	}
}