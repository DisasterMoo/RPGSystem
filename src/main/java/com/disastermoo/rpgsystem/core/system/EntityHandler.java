package com.disastermoo.rpgsystem.core.system;

import java.util.Random;

import com.disastermoo.rpgsystem.core.RegistryHandler;
import com.disastermoo.rpgsystem.core.config.RPGData;
import com.disastermoo.rpgsystem.core.config.RPGData.MobInfo;
import com.disastermoo.rpgsystem.core.system.Attribute.Type;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

public abstract class EntityHandler {
	
	private static Random rd = new Random();
	
	public static void onSpawn(Entity ent)
	{
		if(ent instanceof EntityPlayer) {
			onPlayerSpawn((EntityPlayer) ent);
			return;
		}
		if(!(ent instanceof EntityLiving))return;
		MobInfo mobInfo = RPGData.INSTANCE.getMobInfo(ent.getClass().getTypeName());
		if(mobInfo == null) {
			RPGData.INSTANCE.addNewMob(ent.getClass().getTypeName(), 1, 0, null);
			mobInfo = RPGData.INSTANCE.getMobInfo(ent.getClass().getTypeName());
			RPGData.INSTANCE.save();
		}
		if(mobInfo != null) {
			EntityInfo enInfo = EntityInfo.loadFromNBT(ent);
			if(enInfo.isNew) {
				EntityLiving living = ((EntityLiving)ent);
				enInfo.isNew = false;
				int range = (int)(mobInfo.mobLevel * 0.3);
				if(range < 1)range = 1;
				int useLevel = mobInfo.mobLevel + rd.nextInt(range) - rd.nextInt(range);
				if(useLevel < 0)useLevel = 1;
				enInfo.getAttributes().setSTR((int)(getBonus(Type.STR, mobInfo.mobCategory) * getAttributeGain(useLevel)));
				enInfo.getAttributes().setAGI((int)(getBonus(Type.AGI, mobInfo.mobCategory) * getAttributeGain(useLevel)));
				enInfo.getAttributes().setCON((int)(getBonus(Type.CON, mobInfo.mobCategory) * getAttributeGain(useLevel)));
				enInfo.getAttributes().setINT((int)(getBonus(Type.INT, mobInfo.mobCategory) * getAttributeGain(useLevel)));
				enInfo.getAttributes().setWIS((int)(getBonus(Type.WIS, mobInfo.mobCategory) * getAttributeGain(useLevel)));
				enInfo.getAttributes().setLCK((int)(getBonus(Type.LCK, mobInfo.mobCategory) * getAttributeGain(useLevel)));
				enInfo.saveToNBT(ent);
				living.setAlwaysRenderNameTag(true);
				living.setCustomNameTag("Lv. " + useLevel) ;
				try {
					living.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue((enInfo.getHealthBonus() + living.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).getBaseValue()) * 3);
					living.setHealth((float)living.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).getBaseValue());
					
					living.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(enInfo.getPhysicalDamageMultiplier() * living.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).getBaseValue());
					living.getEntityAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(enInfo.getKnockbackResistance());
					living.getEntityAttribute(SharedMonsterAttributes.ATTACK_SPEED).setBaseValue(enInfo.getAttackSpeedMultiplier() * living.getEntityAttribute(SharedMonsterAttributes.ATTACK_SPEED).getBaseValue());
				}catch(Exception e) {
					/* ERROR SETTING ATTRIBUTE */
				}
			}
		}
	}
	
	public static void onPlayerSpawn(EntityPlayer player)
	{
		EntityInfo enInfo = EntityInfo.loadFromNBT(player);
		if(enInfo.isNew) {
			enInfo.getAttributes().setSTR(100);
			enInfo.getAttributes().setAGI(100);
			enInfo.getAttributes().setCON(100);
			enInfo.getAttributes().setINT(100);
			enInfo.getAttributes().setWIS(100);
			enInfo.getAttributes().setLCK(1000);
			enInfo.isNew =false;
			enInfo.saveToNBT(player);
		}
		player.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(20 + enInfo.getHealthBonus());
		player.getEntityAttribute(SharedMonsterAttributes.ATTACK_SPEED).setBaseValue(4 * enInfo.getAttackSpeedMultiplier());
		int luck = (int)enInfo.getFinalLCK();
		if(luck > 1024)luck = 1024;
		player.getEntityAttribute(SharedMonsterAttributes.LUCK).setBaseValue(luck);
		player.getEntityAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(enInfo.getKnockbackResistance());
		player.capabilities.setPlayerWalkSpeed(enInfo.getMovSpeed());
	}
	
	public static void onDrop(LivingDropsEvent event)
	{
		try {
			if(!(event.getEntity() instanceof EntityLiving))return;
			if(!(event.getSource().getTrueSource() instanceof EntityPlayer))return;
			EntityPlayer player = (EntityPlayer)event.getSource().getTrueSource();
			EntityInfo infoPlayer = EntityInfo.loadFromNBT(player);
			EntityLiving liv = (EntityLiving)event.getEntity();
			MobInfo mobInfo = RPGData.INSTANCE.getMobInfo(event.getEntity().getClass().getTypeName());
			if(mobInfo.mobDrops != null) {
				for(MobInfo.Drop drop : mobInfo.mobDrops) {
					if(rd.nextFloat() < (drop.chance * infoPlayer.getDropBonus())) {
						event.getDrops().add(new EntityItem(liv.world, liv.posX, liv.posY, liv.posZ, new ItemStack(RegistryHandler.INSTANCE.items.materia, 1, drop.dropId)));
					}
				}
			}
		}catch(Exception e) {
			/* Cannot process, ignore */
		}
	}

	public static void onDeath(Entity ent){
		EntityInfo enInfo = EntityInfo.loadFromNBT(ent);
		if(!enInfo.isNew) {
			EntityLiving living = ((EntityLiving)ent);
			int level;
			try{
				level = Integer.parseInt(living.getCustomNameTag().split("Lv. ")[1]);
			}catch (Exception e) {
				level = 2;
			}
			level = (int)(level / 2.0f);
			if(level < 1)level = 1;
			MobInfo mobInfo = RPGData.INSTANCE.getMobInfo(ent.getClass().getTypeName());
			if(mobInfo != null && mobInfo.mobCategory == 3)level *= 5;
			living.world.spawnEntity(new EntityXPOrb(living.world, living.posX, living.posY, living.posZ, level));
		}
	}
	
	public static void onPlayerAttack(LivingHurtEvent event)
	{
		if(!(event.getSource().getTrueSource() instanceof EntityPlayer))return;
		EntityPlayer player = (EntityPlayer)event.getSource().getTrueSource();
		EntityInfo infoPlayer = EntityInfo.loadFromNBT(player);
		float amount = 0;
		boolean crit = false;
		if(event.getSource().isMagicDamage()) {
			amount = infoPlayer.getMagicalDamageMultiplier() * event.getAmount();
			if(rd.nextFloat() < infoPlayer.getMagicalCritChance()) {
				amount *= infoPlayer.getMagicalCritMultiplier();
				crit = true;
			}
		}else {
			amount = infoPlayer.getPhysicalDamageMultiplier() * event.getAmount();
			if(rd.nextFloat() < infoPlayer.getPhysicalCritChance()) {
				amount *= infoPlayer.getPhysicalCritMultiplier();
				crit = true;
			}
		}
		event.setAmount(amount);
		if(crit) {
			player.sendMessage(new TextComponentTranslation("rpgsystem.message.critical"));
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
