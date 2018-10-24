package com.disastermoo.rpgsystem.core.capabilities;

import com.disastermoo.rpgsystem.core.RegistryHandler;
import com.disastermoo.rpgsystem.core.system.Class;
import com.disastermoo.rpgsystem.core.system.EntityInfo;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;

public class RPGInfoStorage implements IStorage<IRPGInfo>{

	@Override
	public NBTBase writeNBT(Capability<IRPGInfo> capability, IRPGInfo instance, EnumFacing side) {
		NBTTagCompound rpgData = new NBTTagCompound();
		for(int i = 0; i < 7; i++)
		{
			if(instance.getInfo().isMateriaSlotEnabled(i)) {
				ItemStack inSlot = instance.getInfo().getMateriaInSlot(i);
				if(inSlot != null)
					rpgData.setInteger("MateriaSlot" + (i+1), instance.getInfo().getMateriaInSlot(i).getMetadata());
				else
					rpgData.setInteger("MateriaSlot" + (i+1), 0);
			}else {
				rpgData.setInteger("MateriaSlot" + (i+1), -1);
			}
		}
		rpgData.setInteger("STR", instance.getInfo().getAttributes().getSTR());
		rpgData.setInteger("AGI", instance.getInfo().getAttributes().getAGI());
		rpgData.setInteger("CON", instance.getInfo().getAttributes().getCON());
		rpgData.setInteger("INT", instance.getInfo().getAttributes().getINT());
		rpgData.setInteger("WIS", instance.getInfo().getAttributes().getWIS());
		rpgData.setInteger("LCK", instance.getInfo().getAttributes().getLCK());
		int classID = 0;
		if(instance.getInfo().getClassType() != null)classID = instance.getInfo().getClassType().getID();
		rpgData.setInteger("Class", classID);
		return rpgData;
	}

	@Override
	public void readNBT(Capability<IRPGInfo> capability, IRPGInfo instance, EnumFacing side, NBTBase nbt) {
		NBTTagCompound rpgData = (NBTTagCompound)nbt;
		if(instance.getInfo() == null) {
			instance.setInfo(new EntityInfo());
		}
		for(int i = 0; i < 7; i++)
		{
			int meta = rpgData.getInteger("MateriaSlot" + (i+1));
			if(meta == -1) {
				instance.getInfo().setMateriaSlotEnabled(i, false);
				instance.getInfo().setMateriaInSlot(i, null);
			}else if(meta == 0) {
				instance.getInfo().setMateriaSlotEnabled(i, true);
				instance.getInfo().setMateriaInSlot(i, null);
			}else {
				instance.getInfo().setMateriaSlotEnabled(i, true);
				instance.getInfo().setMateriaInSlot(i, new ItemStack(RegistryHandler.INSTANCE.items.materia, 1, meta));
			}
		}
		
		instance.getInfo().getAttributes().setSTR(rpgData.getInteger("STR"));
		instance.getInfo().getAttributes().setAGI(rpgData.getInteger("AGI"));
		instance.getInfo().getAttributes().setCON(rpgData.getInteger("CON"));
		instance.getInfo().getAttributes().setINT(rpgData.getInteger("INT"));
		instance.getInfo().getAttributes().setWIS(rpgData.getInteger("WIS"));
		instance.getInfo().getAttributes().setLCK(rpgData.getInteger("LCK"));
		instance.getInfo().setClassType(Class.Type.getTypeByID(rpgData.getInteger("Class")));
	}

}
