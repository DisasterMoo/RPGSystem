package com.disastermoo.rpgsystem.core.system;

import java.util.Random;

import com.disastermoo.rpgsystem.core.ProxyCommon;
import com.disastermoo.rpgsystem.core.RegistryHandler;
import com.disastermoo.rpgsystem.core.capabilities.RPGInfoProvider;
import com.disastermoo.rpgsystem.core.capabilities.network.RPGInfoMessage;
import com.disastermoo.rpgsystem.core.config.RPGData;
import com.disastermoo.rpgsystem.core.config.RPGData.MobInfo;
import com.disastermoo.rpgsystem.core.system.Attribute.Type;
import com.disastermoo.rpgsystem.core.util.RPGUtils;
import com.disastermoo.rpgsystem.core.capabilities.IRPGInfo;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

public abstract class EntityHandler {
	
	private static Random rd = new Random();
	
	
	/*
	 * TODO: ARRUMAR PROBLEMA NA GUI: SO MOSTRA QUANDO O MAIOR ATTRIBUTO PUDER SER CLICADO(TO COM LEVEL MAS NAO MOSTRA OS BOTAO)
	 * VER SE É POSSIVEL ARRUMAR O CLOUD DAMAGE DO THAUMCRAFT(super op)
	 */
	
	public static void onSpawn(Entity ent)
	{
		if(ent instanceof EntityPlayer) {
			playerUpdateStats((EntityPlayer) ent);
			return;
		}
		if(!(ent instanceof EntityLiving))return;
		EntityLiving living = ((EntityLiving)ent);
		MobInfo mobInfo = RPGUtils.getMobInfo(ent.getClass().getTypeName());
		if(mobInfo == null) {
			RPGData.INSTANCE.addNewMob(ent.getClass().getTypeName(), 1, 0, null);
			mobInfo = RPGData.INSTANCE.getMobInfo(ent.getClass().getTypeName());
			RPGData.INSTANCE.save();
		}
		EntityInfo enInfo = RPGUtils.getRPGInfo(living).getInfo();
		if(enInfo.getClassType() == null) {
			enInfo.setClassType(Class.Type.Fighter);
			int range = (int)(mobInfo.mobLevel * 0.3);
			if(range < 1)range = 1;
			if(range > 8)range = 8;
			int useLevel = mobInfo.mobLevel + rd.nextInt(range) - rd.nextInt(range);
			if(mobInfo.mobCategory == 3)useLevel = mobInfo.mobLevel;
			if(useLevel < 0)useLevel = 1;
			enInfo.getAttributes().setSTR((int)(getBonus(Type.STR, mobInfo.mobCategory) * getAttributeGain(useLevel)));
			enInfo.getAttributes().setAGI((int)(getBonus(Type.AGI, mobInfo.mobCategory) * getAttributeGain(useLevel)));
			enInfo.getAttributes().setCON((int)(getBonus(Type.CON, mobInfo.mobCategory) * getAttributeGain(useLevel)));
			enInfo.getAttributes().setINT((int)(getBonus(Type.INT, mobInfo.mobCategory) * getAttributeGain(useLevel)));
			enInfo.getAttributes().setWIS((int)(getBonus(Type.WIS, mobInfo.mobCategory) * getAttributeGain(useLevel)));
			enInfo.getAttributes().setLCK(25);
			
			living.setCustomNameTag("Lv. " + useLevel);
			try {
				living.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(enInfo.getHealthBonus() * 3);
				living.setHealth((float)living.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).getBaseValue());
				
				living.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(enInfo.getPhysicalDamageMultiplier() * living.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).getBaseValue());
				living.getEntityAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(enInfo.getKnockbackResistance());
				living.getEntityAttribute(SharedMonsterAttributes.ATTACK_SPEED).setBaseValue(enInfo.getAttackSpeedMultiplier() * living.getEntityAttribute(SharedMonsterAttributes.ATTACK_SPEED).getBaseValue());
			}catch(Exception e) {
				/* ERROR SETTING ATTRIBUTE */
			}
		}
	}
	
	public static void playerUpdateStats(EntityPlayer player)
	{
		EntityInfo enInfo = RPGUtils.getRPGInfo(player).getInfo();
		if(enInfo.getClassType() == null) {
			enInfo.setClassType(Class.Type.NONE);
		}
		player.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(enInfo.getHealthBonus());
		player.getEntityAttribute(SharedMonsterAttributes.ATTACK_SPEED).setBaseValue(4 * enInfo.getAttackSpeedMultiplier());
		int luck = (int)enInfo.getFinalLCK();
		if(luck > 1024)luck = 1024;
		player.getEntityAttribute(SharedMonsterAttributes.LUCK).setBaseValue(luck);
		player.getEntityAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(enInfo.getKnockbackResistance());
		player.capabilities.setPlayerWalkSpeed(enInfo.getMovSpeed());
		ProxyCommon.NETWORK_INSTANCE.sendTo(new RPGInfoMessage(enInfo), (EntityPlayerMP)player);
	}
	
	public static void onDrop(LivingDropsEvent event)
	{
		try {
			if(!(event.getEntity() instanceof EntityLiving))return;
			if(!(event.getSource().getTrueSource() instanceof EntityPlayer))return;
			EntityPlayer player = (EntityPlayer)event.getSource().getTrueSource();
			EntityInfo infoPlayer = RPGUtils.getRPGInfo(player).getInfo();
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
		if(!(ent instanceof EntityLiving))return;
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
		if(mobInfo != null && mobInfo.mobCategory == 3) {
			level *= 5;
			for(int i = 0; i < 9; i++)
				living.world.spawnEntity(new EntityXPOrb(living.world, living.posX + (rd.nextInt(7) - 3), living.posY, living.posZ + (rd.nextInt(7) - 3), level));
		}
		living.world.spawnEntity(new EntityXPOrb(living.world, living.posX, living.posY, living.posZ, level));
	}
	
	public static void onAttackReceived(LivingHurtEvent event)
	{
		EntityLivingBase receivingEntity = event.getEntityLiving();
		if(!(event.getSource().getTrueSource() instanceof EntityLivingBase)) {
			MobInfo mobInfo = RPGData.INSTANCE.getMobInfo(event.getEntity().getClass().getTypeName());
			System.out.println(event.getSource().getImmediateSource());
			if(event.getSource() == DamageSource.WITHER) {
				if(mobInfo != null && mobInfo.mobCategory == 3) {
					event.setAmount(10);
				}else {
					event.setAmount(10 + receivingEntity.getMaxHealth() * 0.02f);
				}
			}else
			if(event.getSource() == DamageSource.LAVA) {
				event.setAmount(50);
			}else
			if(event.getSource().isFireDamage() || event.getSource().isMagicDamage()) {
				if(mobInfo != null && mobInfo.mobCategory == 3) {
					event.setAmount(5);
				}else {
					event.setAmount(5 + receivingEntity.getMaxHealth() * 0.01f);
				}
			}else
				event.setAmount(event.getAmount() * 3);
		}else {
			EntityLivingBase sourceEntity = (EntityLivingBase)event.getSource().getTrueSource();
			boolean isPlayer = sourceEntity instanceof EntityPlayer;
			EntityInfo infoSource = RPGUtils.getRPGInfo(sourceEntity).getInfo();
			float amount = 0;
			boolean crit = false;
			if(event.getSource().isMagicDamage()) {
				amount = infoSource.getMagicalDamageMultiplier() * event.getAmount();
				if(rd.nextFloat() < infoSource.getMagicalCritChance()) {
					if(isPlayer)
						amount *= infoSource.getMagicalCritMultiplier();
					else
						amount *= 2;
					crit = true;
				}
			}else {
				amount = infoSource.getPhysicalDamageMultiplier() * event.getAmount();
				if(rd.nextFloat() < infoSource.getPhysicalCritChance()) {
					if(isPlayer)
						amount *= infoSource.getPhysicalCritMultiplier();
					else
						amount *= 2;
					crit = true;
				}
			}
			event.setAmount(amount);
			if(crit) {
				if(isPlayer)
					((EntityPlayer)sourceEntity).sendMessage(new TextComponentTranslation("rpgsystem.message.critical"));
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
			break;
		case 2:
			if(attType == Attribute.Type.CON || attType == Attribute.Type.STR)return 0.7;
			if(attType == Attribute.Type.AGI || attType == Attribute.Type.INT)return 1.3;
			break;
		case 3:
			if(attType == Attribute.Type.CON)return 10;
			return 1.5;
		default:
			return 1;
		}
		return 1;
	}
}
