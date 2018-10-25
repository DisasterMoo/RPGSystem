package com.disastermoo.rpgsystem.core.util;

import com.disastermoo.rpgsystem.core.capabilities.IRPGInfo;
import com.disastermoo.rpgsystem.core.capabilities.RPGInfoProvider;
import com.disastermoo.rpgsystem.core.capabilities.network.RPGUpgradeMessage.UpgradeValues;
import com.disastermoo.rpgsystem.core.config.RPGData;
import com.disastermoo.rpgsystem.core.config.RPGData.MobInfo;
import com.disastermoo.rpgsystem.core.system.EntityInfo;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;

public abstract class RPGUtils {

	public static final MobInfo getMobInfo(String name)
	{
		return RPGData.INSTANCE.getMobInfo(name);
	}
	
	public static final IRPGInfo getRPGInfo(Entity entity)
	{
		return ((IRPGInfo)entity.getCapability(RPGInfoProvider.RPGINFO_CAP, null));
	}
	
	public static final int upgradeLevelsNeeded(UpgradeValues values, EntityInfo upgrading)
	{
		int level = 0;
		int attStart = upgrading.getAttributes().getSTR();
		for(int i = 0; i < values.pointsSTR; i++)
		{
			level += attStart / 10;
			attStart += 1;
		}
		attStart = upgrading.getAttributes().getAGI();
		for(int i = 0; i < values.pointsAGI; i++)
		{
			level += attStart / 10;
			attStart += 1;
		}
		attStart = upgrading.getAttributes().getCON();
		for(int i = 0; i < values.pointsCON; i++)
		{
			level += attStart / 10;
			attStart += 1;
		}
		attStart = upgrading.getAttributes().getINT();
		for(int i = 0; i < values.pointsINT; i++)
		{
			level += attStart / 10;
			attStart += 1;
		}
		attStart = upgrading.getAttributes().getWIS();
		for(int i = 0; i < values.pointsWIS; i++)
		{
			level += attStart / 10;
			attStart += 1;
		}
		attStart = upgrading.getAttributes().getLCK();
		for(int i = 0; i < values.pointsLCK; i++)
		{
			level += attStart / 10;
			attStart += 1;
		}
		if(values.upgradeClass)level += 100;
		if(values.buySlot > 0) {
			if(values.buySlot % 2 == 0)level += 50;
			else level += 100;
			if(values.buySlot == 7)level += 200;
		}
		return level;
	}
	
	public static final int minLevel(EntityInfo info)
	{
		int minLevel = info.getAttributes().getSTR() / 10;
		if(minLevel > info.getAttributes().getAGI() / 10)minLevel = info.getAttributes().getAGI() / 10;
		if(minLevel > info.getAttributes().getCON() / 10)minLevel = info.getAttributes().getCON() / 10;
		if(minLevel > info.getAttributes().getINT() / 10)minLevel = info.getAttributes().getINT() / 10;
		if(minLevel > info.getAttributes().getWIS() / 10)minLevel = info.getAttributes().getWIS() / 10;
		if(minLevel > info.getAttributes().getLCK() / 10)minLevel = info.getAttributes().getLCK() / 10;
		return minLevel;
	}
	
	public static final String formatString(double d)
	{
	    if(d == (long) d)
	        return String.format("%d",(long)d);
	    else
	        return new java.text.DecimalFormat("#.##").format(d);
	}
	
	public static final void registerAllEntities()
	{
		for(ResourceLocation res : EntityList.getEntityNameList())
        {
        	Class<?> cl = EntityList.getClass(res);
        	if(cl != null && EntityLivingBase.class.isAssignableFrom(cl)) {
        		MobInfo mobInfo = RPGUtils.getMobInfo(cl.getTypeName());
        		if(mobInfo == null) {
        			RPGData.INSTANCE.addNewMob(cl.getTypeName(), 1, 0, null);
        			mobInfo = RPGData.INSTANCE.getMobInfo(cl.getTypeName());
        			RPGData.INSTANCE.save();
        		}
        	}
        }
	}
}
