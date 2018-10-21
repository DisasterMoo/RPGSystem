package com.disastermoo.rpgsystem.core.system;

import com.disastermoo.rpgsystem.core.RegistryHandler;
import com.disastermoo.rpgsystem.core.item.Materia;

import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;


public class EntityInfo {
	private ItemStack[] materiaSlot;
	private Attribute attributes;
	private boolean isMateriaSlotEnabled[] = {false, false, false, false, false, false, false};
	
	public boolean isNew;
	
	private EntityInfo()
	{
		materiaSlot = new ItemStack[7];
		attributes = new Attribute();
		isNew = false;
	}
	
	public ItemStack getMateriaInSlot(int i)
	{
		return materiaSlot[i];
	}
	
	public Attribute getAttributes()
	{
		return attributes;
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
	
	public static EntityInfo loadFromNBT(Entity ent)
	{
		EntityInfo ret = new EntityInfo();
		if (ent.getEntityData().getInteger("rpgsystem.str") == 0)ret.isNew = true;
		for(int i = 0; i < 7; i++)
		{
			ret.setMateriaSlotEnabled(i, ent.getEntityData().getBoolean("rpgsystem.materiaslot" + (i+1))); 
			int mat = ent.getEntityData().getInteger("rpgsystem.materiaslot" + (i+1));
			if(mat > 0) {
				ItemStack materia = new ItemStack(RegistryHandler.INSTANCE.items.materia, 1, mat);
				ret.setMateriaInSlot(i, materia);
			}else {
				ret.setMateriaInSlot(i, null);
			}
		}
		ret.getAttributes().setSTR(ent.getEntityData().getInteger("rpgsystem.str"));
		ret.getAttributes().setAGI(ent.getEntityData().getInteger("rpgsystem.agi"));
		ret.getAttributes().setCON(ent.getEntityData().getInteger("rpgsystem.con"));
		ret.getAttributes().setINT(ent.getEntityData().getInteger("rpgsystem.int"));
		ret.getAttributes().setWIS(ent.getEntityData().getInteger("rpgsystem.wis"));
		ret.getAttributes().setLCK(ent.getEntityData().getInteger("rpgsystem.lck"));
		return ret;
	}
	
	public void saveToNBT(Entity ent)
	{
		for(int i = 0; i < 7; i++)
		{
			ent.getEntityData().setBoolean("rpgsystem.materiaslot" + (i+1), this.isMateriaSlotEnabled(i));
			ItemStack inSlot = this.getMateriaInSlot(i);
			if(inSlot != null)
				ent.getEntityData().setInteger("rpgsystem.materiaslot" + (i+1), this.getMateriaInSlot(i).getMetadata());
			else
				ent.getEntityData().setInteger("rpgsystem.materiaslot" + (i+1), 0);
		}
		ent.getEntityData().setInteger("rpgsystem.str", this.getAttributes().getSTR());
		ent.getEntityData().setInteger("rpgsystem.agi", this.getAttributes().getAGI());
		ent.getEntityData().setInteger("rpgsystem.con", this.getAttributes().getCON());
		ent.getEntityData().setInteger("rpgsystem.int", this.getAttributes().getINT());
		ent.getEntityData().setInteger("rpgsystem.wis", this.getAttributes().getWIS());
		ent.getEntityData().setInteger("rpgsystem.lck", this.getAttributes().getLCK());
	}
	
	public float getHealthBonus()
	{
		return (this.getAttributes().getCON() - 10) / 3;
	}
	
	public float getPhysicalDamageMultiplier()
	{
		return 1 + this.getAttributes().getSTR() / 30 - 0.333f;
	}
	
	public float getPhysicalCritChance()
	{
		return 0.02f + ((this.getAttributes().getLCK() - 10) / 500);
	}
	
	public float getPhysicalCritMultiplier()
	{
		return 1.8f + this.getAttributes().getAGI() / 50;
	}
	
	public float getMagicalDamageMultiplier()
	{
		return 1 + this.getAttributes().getINT() / 30 - 0.333f;
	}
	
	public float getMagicalCritMultiplier()
	{
		return 1.8f + this.getAttributes().getWIS() / 50;
	}
	
	public float getMagicalCritChance()
	{
		return 0.02f + ((this.getAttributes().getLCK() - 10) / 500);
	}
	
	public float getDropBonus()
	{
		return 1.0f + (this.getAttributes().getLCK() / 50);
	}
}
