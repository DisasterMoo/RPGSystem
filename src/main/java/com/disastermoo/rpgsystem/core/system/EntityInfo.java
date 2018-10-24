package com.disastermoo.rpgsystem.core.system;

import com.disastermoo.rpgsystem.core.RegistryHandler;
import com.disastermoo.rpgsystem.core.item.Materia;

import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;


public class EntityInfo {
	private ItemStack[] materiaSlot;
	private Attribute attributes;
	private Class.Type classType;
	private boolean isMateriaSlotEnabled[] = {false, false, false, false, false, false, false};
	
	public EntityInfo()
	{
		materiaSlot = new ItemStack[7];
		attributes = new Attribute();
		classType = null;
	}
	
	public ItemStack getMateriaInSlot(int i)
	{
		return materiaSlot[i];
	}
	
	public Attribute getAttributes()
	{
		return attributes;
	}
	
	public Class.Type getClassType()
	{
		return classType;
	}
	
	public void setClassType(Class.Type classType)
	{
		this.classType = classType;
	}
	
	public boolean isMateriaSlotEnabled(int i)
	{
		return isMateriaSlotEnabled[i];
	}
	
	public void setMateriaInSlot(int i, ItemStack materia)
	{
		materiaSlot[i] = materia;
	}
	
	public void setMateriaSlotEnabled(int i, boolean value)
	{
		isMateriaSlotEnabled[i] = value;
	}
	
	public float getFinalSTR()
	{
		float finalSTR = this.getAttributes().getSTR();
		float bonusMult = 1.0f;
		if(this.getClassType().getPrimaryAttribute() == Attribute.Type.STR) {
			bonusMult += (this.getClassType().getPrimaryBonus() / 100.0f);
		}
		if(this.getClassType().getSecondaryAttribute() == Attribute.Type.STR) {
			bonusMult += (this.getClassType().getSecondaryBonus() / 100.0f);
		}
		return finalSTR * bonusMult;
	}
	
	public float getFinalAGI()
	{
		float finalAGI = this.getAttributes().getAGI();
		float bonusMult = 1.0f;
		if(this.getClassType().getPrimaryAttribute() == Attribute.Type.AGI) {
			bonusMult += (this.getClassType().getPrimaryBonus() / 100.0f);
		}
		if(this.getClassType().getSecondaryAttribute() == Attribute.Type.AGI) {
			bonusMult += (this.getClassType().getSecondaryBonus() / 100.0f);
		}
		return finalAGI * bonusMult;
	}
	
	public float getFinalCON()
	{
		float finalCON = this.getAttributes().getCON();
		float bonusMult = 1.0f;
		if(this.getClassType().getPrimaryAttribute() == Attribute.Type.CON) {
			bonusMult += (this.getClassType().getPrimaryBonus() / 100.0f);
		}
		if(this.getClassType().getSecondaryAttribute() == Attribute.Type.CON) {
			bonusMult += (this.getClassType().getSecondaryBonus() / 100.0f);
		}
		return finalCON * bonusMult;
	}
	
	public float getFinalINT()
	{
		float finalINT = this.getAttributes().getINT();
		float bonusMult = 1.0f;
		if(this.getClassType().getPrimaryAttribute() == Attribute.Type.INT) {
			bonusMult += (this.getClassType().getPrimaryBonus() / 100.0f);
		}
		if(this.getClassType().getSecondaryAttribute() == Attribute.Type.INT) {
			bonusMult += (this.getClassType().getSecondaryBonus() / 100.0f);
		}
		return finalINT * bonusMult;
	}
	
	public float getFinalWIS()
	{
		float finalWIS = this.getAttributes().getWIS();
		float bonusMult = 1.0f;
		if(this.getClassType().getPrimaryAttribute() == Attribute.Type.WIS) {
			bonusMult += (this.getClassType().getPrimaryBonus() / 100.0f);
		}
		if(this.getClassType().getSecondaryAttribute() == Attribute.Type.WIS) {
			bonusMult += (this.getClassType().getSecondaryBonus() / 100.0f);
		}
		return finalWIS * bonusMult;
	}
	
	public float getFinalLCK()
	{
		float finalLCK = this.getAttributes().getLCK();
		float bonusMult = 1.0f;
		if(this.getClassType().getPrimaryAttribute() == Attribute.Type.LCK) {
			bonusMult += (this.getClassType().getPrimaryBonus() / 100.0f);
		}
		if(this.getClassType().getSecondaryAttribute() == Attribute.Type.LCK) {
			bonusMult += (this.getClassType().getSecondaryBonus() / 100.0f);
		}
		return finalLCK * bonusMult;
	}
	
	public float getHealthBonus()
	{
		
		return 5 + this.getFinalCON() / 1.5f;
	}
	
	public float getAttackSpeedMultiplier()
	{
		return 1 + (this.getFinalAGI() - 10) / 30;
	}
	
	public float getPhysicalDamageMultiplier()
	{
		return 1 + this.getFinalSTR() / 30 - 0.333f;
	}
	
	public float getPhysicalCritChance()
	{
		float ret = 0.02f + ((this.getFinalLCK() - 10) / 500);
		if(ret > 1F)ret = 1F;
		return ret;
	}
	
	public float getPhysicalCritMultiplier()
	{
		return 1.8f + this.getFinalAGI() / 50;
	}
	
	public float getMagicalDamageMultiplier()
	{
		return 1 + this.getFinalINT() / 30 - 0.333f;
	}
	
	public float getMagicalCritMultiplier()
	{
		return 1.8f + this.getFinalWIS() / 50;
	}
	
	public float getMagicalCritChance()
	{
		float ret = 0.02f + ((this.getFinalLCK() - 10) / 500);
		if(ret > 1F)ret = 1F;
		return ret;
	}
	
	public float getDropBonus()
	{
		return 1.0f + (this.getFinalLCK() / 50);
	}
	
	public float getKnockbackResistance()
	{
		float value = this.getFinalCON() / 300.0f;
		if(value > 1.0f)value = 1.0f;
		return value;
	}
	
	public float getMovSpeed()
	{
		float value = 0.1f + this.getFinalAGI() / 1600.0f;
		if(value > 0.25f)value = 0.25f;
		return value;
	}
}
