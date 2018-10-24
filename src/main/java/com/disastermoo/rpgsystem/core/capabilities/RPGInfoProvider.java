package com.disastermoo.rpgsystem.core.capabilities;

import java.util.concurrent.Callable;

import com.disastermoo.rpgsystem.core.system.EntityInfo;

import net.minecraft.nbt.NBTBase;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

public class RPGInfoProvider implements ICapabilitySerializable<NBTBase> {
	
	@CapabilityInject(IRPGInfo.class)
	public static final Capability<IRPGInfo> RPGINFO_CAP = null; 
	private IRPGInfo instance = RPGINFO_CAP.getDefaultInstance(); 
	
	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
		return capability == RPGINFO_CAP;
	}

	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
		return capability == RPGINFO_CAP ? RPGINFO_CAP.<T> cast(this.instance) : null;
	}

	@Override
	public NBTBase serializeNBT() {
		return RPGINFO_CAP.getStorage().writeNBT(RPGINFO_CAP, this.instance, null); 
	}

	@Override
	public void deserializeNBT(NBTBase nbt) {
		RPGINFO_CAP.getStorage().readNBT(RPGINFO_CAP, this.instance, null, nbt); 
	}
	
	public static class Factory implements Callable<IRPGInfo> {

		  @Override
		  public IRPGInfo call() throws Exception {
			  IRPGInfo newObj = new RPGInfo();
			  newObj.setInfo(new EntityInfo());
		    return newObj;
		  }
	}

}
