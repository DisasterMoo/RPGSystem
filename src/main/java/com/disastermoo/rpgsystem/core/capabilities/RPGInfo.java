package com.disastermoo.rpgsystem.core.capabilities;

import com.disastermoo.rpgsystem.core.capabilities.network.RPGUpgradeMessage.UpgradeValues;
import com.disastermoo.rpgsystem.core.system.EntityInfo;
import com.disastermoo.rpgsystem.core.system.Profession;
import com.disastermoo.rpgsystem.core.util.RPGUtils;

import net.minecraft.entity.player.EntityPlayer;

public class RPGInfo implements IRPGInfo{

	private EntityInfo enInfo = null;
	
	@Override
	public void setInfo(EntityInfo info)
	{
		this.enInfo = info;
	}
	
	@Override
	public EntityInfo getInfo() {
		return enInfo;
	}
	
	public boolean Upgrade(UpgradeValues values, EntityPlayer player)
	{	
		if(values.upgradeClass > 0 && !Profession.Type.getTypeByID(values.upgradeClass).getUpgradeFrom(this.enInfo.getProfession()))return false;
		int levels = RPGUtils.upgradeLevelsNeeded(values, this.enInfo);
		if(levels > player.experienceLevel)return false;
		player.addExperienceLevel(-levels);
		this.enInfo.getAttributes().addSTR(values.pointsSTR);
		this.enInfo.getAttributes().addAGI(values.pointsAGI);
		this.enInfo.getAttributes().addCON(values.pointsCON);
		this.enInfo.getAttributes().addINT(values.pointsINT);
		this.enInfo.getAttributes().addWIS(values.pointsWIS);
		this.enInfo.getAttributes().addLCK(values.pointsLCK);
		if(values.upgradeClass > 0)this.enInfo.setClassType(Profession.Type.getTypeByID(values.upgradeClass));
		if(values.buySlot > 0)this.enInfo.setMateriaSlotEnabled(values.buySlot - 1, true);
		return true;
	}
	
	

}
