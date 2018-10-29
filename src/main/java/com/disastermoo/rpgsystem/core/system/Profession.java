package com.disastermoo.rpgsystem.core.system;

import java.util.ArrayList;

import com.disastermoo.rpgsystem.core.config.RPGConfig;

public abstract class Profession {
	public enum Type{
		NONE(1, 0, 0, Attribute.Type.STR, Attribute.Type.STR, 0, 0),
		Fighter(2, 15, 0, Attribute.Type.STR, Attribute.Type.AGI, 1, 10),
		Champion(3, 35, 15, Attribute.Type.STR, Attribute.Type.AGI, 2, 100),
		Thief(4, 10, 5, Attribute.Type.AGI, Attribute.Type.LCK, 1, 10),
		Assassin(5, 30, 20, Attribute.Type.AGI, Attribute.Type.LCK, 4, 100),
		Magus(6, 15, 0, Attribute.Type.INT, Attribute.Type.WIS, 1, 10),
		Invoker(7, 35, 15, Attribute.Type.INT, Attribute.Type.WIS, 6, 100),
		Archer(8, 15, 0, Attribute.Type.AGI, Attribute.Type.STR, 1, 10),
		Hunter(9, 35, 15, Attribute.Type.AGI, Attribute.Type.STR, 8, 100),
		Barbarian(10, 10, 5, Attribute.Type.STR, Attribute.Type.CON, 1, 10),
		Berserker(11, 35, 15, Attribute.Type.STR, Attribute.Type.CON, 10, 100);
		
		
		private int id;
		private int priBonus, secBonus;
		private int idUpgrade;
		private Attribute.Type priAtt, secAtt;
		private int lvls;
		Type(int id, int primary, int secondary, Attribute.Type primaryAtt, Attribute.Type secondaryAtt, int idUpgrade, int lvls)
		{
			this.id = id;
			this.priBonus = primary;
			this.secBonus = secondary;
			this.priAtt = primaryAtt;
			this.secAtt = secondaryAtt;
			this.idUpgrade = idUpgrade;
			this.lvls = lvls;
		}
		
		public static final Type getTypeByID(int id)
		{
			switch(id)
			{
			case 1:
				return NONE;
			case 2:
				return Fighter;
			case 3:
				return Champion;
			case 4:
				return Thief;
			case 5:
				return Assassin;
			case 6:
				return Magus;
			case 7:
				return Invoker;
			case 8:
				return Archer;
			case 9:
				return Hunter;
			case 10:
				return Barbarian;
			case 11:
				return Berserker;
			default:
					return null;
			}
		}
		
		public int getID()
		{
			return this.id;
		}
		
		public int getPrimaryBonus()
		{
			return this.priBonus;
		}
		
		public int getSecondaryBonus()
		{
			return this.secBonus;
		}
		
		public Attribute.Type getPrimaryAttribute()
		{
			return this.priAtt;
		}
		
		public Attribute.Type getSecondaryAttribute()
		{
			return this.secAtt;
		}
		
		public int getLevelsNeeded()
		{
			return this.lvls;
		}
		
		public boolean getUpgradeFrom(Profession.Type upgradeFrom)
		{
			if(this.idUpgrade == upgradeFrom.getID())return true;
			return false;
		}
		
		public ArrayList<Profession.Type> getUpgrades()
		{
			ArrayList<Profession.Type> upgrades = new ArrayList<Profession.Type>();
			for(int i = 2; i <= 11; i++) {
				Profession.Type prof = Profession.Type.getTypeByID(i);
				if(prof.getUpgradeFrom(this))upgrades.add(prof);
			}
			return upgrades;
		}
		
		public static String getProfessionAsset(int id)
		{
			switch(id)
			{
			case 1:
				return RPGConfig.Constants.ASSET_PROFESSION_NONE;
			case 2:
				return RPGConfig.Constants.ASSET_PROFESSION_FIGHTER;
			case 3:
				return RPGConfig.Constants.ASSET_PROFESSION_CHAMPION;
			case 4:
				return RPGConfig.Constants.ASSET_PROFESSION_THIEF;
			case 5:
				return RPGConfig.Constants.ASSET_PROFESSION_ASSASSIN;
			case 6:
				return RPGConfig.Constants.ASSET_PROFESSION_MAGUS;
			case 7:
				return RPGConfig.Constants.ASSET_PROFESSION_INVOKER;
			case 8:
				return RPGConfig.Constants.ASSET_PROFESSION_ARCHER;
			case 9:
				return RPGConfig.Constants.ASSET_PROFESSION_HUNTER;
			case 10:
				return RPGConfig.Constants.ASSET_PROFESSION_BARBARIAN;
			case 11:
				return RPGConfig.Constants.ASSET_PROFESSION_BERSERKER;
			default:
					return null;
			}
		}
		
		public String getProfessionAsset()
		{
			return Profession.Type.getProfessionAsset(this.id);
		}
	}
}
