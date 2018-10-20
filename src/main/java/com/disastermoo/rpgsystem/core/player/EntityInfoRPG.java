package com.disastermoo.rpgsystem.core.player;

import com.disastermoo.rpgsystem.core.system.Attribute;
import com.disastermoo.rpgsystem.core.system.Materia;

import net.minecraft.entity.Entity;


public class EntityInfoRPG {
	private Materia[] materiaSlot;
	private Attribute attributes;
	private boolean isMateriaSlotEnabled[] = {false, false, false, false, false, false, false};
	
	private EntityInfoRPG()
	{
		materiaSlot = new Materia[7];
		attributes = new Attribute();
	}
	
	public Materia getMateriaInSlot(int i)
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
	
	public void setMateriaInSlot(int i, Materia materia)
	{
		materiaSlot[i] = materia;
	}
	
	public void setMateriaSlotEnabled(int i, boolean value)
	{
		isMateriaSlotEnabled[i] = value;
	}
	
	public static EntityInfoRPG loadFromNBT(Entity ent)
	{
		EntityInfoRPG ret = new EntityInfoRPG();
		for(int i = 0; i < 7; i++)
		{
			ret.setMateriaSlotEnabled(i, ent.getEntityData().getBoolean("rpgsystem.materiaslot" + (i+1))); 
			Materia matx = new Materia(ent.getEntityData().getInteger("rpgsystem.materiaslot" + (i+1)));
			if(matx.getEffectID() > 0) {
				ret.setMateriaInSlot(i, matx);
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
			Materia inSlot = this.getMateriaInSlot(i);
			if(inSlot != null)
				ent.getEntityData().setInteger("rpgsystem.materiaslot" + (i+1), this.getMateriaInSlot(i).getEffectID());
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
}
