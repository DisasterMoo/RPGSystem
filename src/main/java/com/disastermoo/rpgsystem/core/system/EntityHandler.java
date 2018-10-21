package com.disastermoo.rpgsystem.core.system;

import java.util.Random;

import com.disastermoo.rpgsystem.core.RPGConfig;
import com.disastermoo.rpgsystem.core.RPGConfig.MobInfo;
import com.disastermoo.rpgsystem.core.system.Attribute.Type;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.SharedMonsterAttributes;

public abstract class EntityHandler {
	
	public static void onSpawn(Entity ent)
	{
		MobInfo mobInfo = RPGConfig.MobInfo.getMobInfo(ent.getClass().getTypeName());
		if(mobInfo != null) {
			EntityInfo enInfo = EntityInfo.loadFromNBT(ent);
			if(enInfo.isNew) {
				Random rd = new Random();
				enInfo.isNew = false;
				enInfo.getAttributes().setSTR((int)(getBonus(Type.STR, mobInfo.currentCategory) * getAttributeGain(mobInfo.currentLevel)));
				enInfo.getAttributes().setAGI((int)(getBonus(Type.AGI, mobInfo.currentCategory) * getAttributeGain(mobInfo.currentLevel)));
				enInfo.getAttributes().setCON((int)(getBonus(Type.CON, mobInfo.currentCategory) * getAttributeGain(mobInfo.currentLevel)));
				enInfo.getAttributes().setINT((int)(getBonus(Type.INT, mobInfo.currentCategory) * getAttributeGain(mobInfo.currentLevel)));
				enInfo.getAttributes().setWIS((int)(getBonus(Type.WIS, mobInfo.currentCategory) * getAttributeGain(mobInfo.currentLevel)));
				enInfo.getAttributes().setLCK((int)(getBonus(Type.LCK, mobInfo.currentCategory) * getAttributeGain(mobInfo.currentLevel)));
				enInfo.saveToNBT(ent);
				((EntityLiving)ent).getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(enInfo.getHealthBonus() + ((EntityLiving)ent).getMaxHealth());
				((EntityLiving)ent).setHealth(enInfo.getHealthBonus() + ((EntityLiving)ent).getMaxHealth());
				System.out.println(((EntityLiving)ent).getHealth());
			}
		}
	}
	
	
	
	private static int getAttributeGain(int level)
	{
		int base = 10;
		int bonus = (int)(Math.pow(level, 0.5714d) * 7);
		return base + bonus;
	}
	
	private static double getBonus(Attribute.Type attType, int category)
	{
		switch(category)
		{
		
		case 1:
			if(attType == Attribute.Type.CON || attType == Attribute.Type.STR)return 1.3;
			if(attType == Attribute.Type.AGI || attType == Attribute.Type.INT)return 0.7;
		case 2:
			if(attType == Attribute.Type.CON || attType == Attribute.Type.STR)return 0.7;
			if(attType == Attribute.Type.AGI || attType == Attribute.Type.INT)return 1.3;
		case 3:
			return 3;
		default:
			return 1;
		}
	}
}
