package com.pzg.www.zombieai.main;

import java.lang.reflect.Field;

import net.minecraft.server.v1_11_R1.PathfinderGoalSelector;

public class Utils {

	public static Object getPrivateField(String fieldName, Class<PathfinderGoalSelector> clazz, Object object) {
		Field field;
		Object o = null;
		try {
			field = clazz.getDeclaredField(fieldName);
			field.setAccessible(true);
			o = field.get(object);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return o;
	}
}