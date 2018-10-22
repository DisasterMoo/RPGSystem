package com.disastermoo.rpgsystem.core.system;

public abstract class Class {
	public enum Type{
		Fighter(1, 15, 0, Attribute.Type.STR, Attribute.Type.AGI, 2),
		Champion(2, 35, 15, Attribute.Type.STR, Attribute.Type.AGI, 0),
		Thief(3, 10, 5, Attribute.Type.AGI, Attribute.Type.LCK, 4),
		Assassin(4, 30, 20, Attribute.Type.AGI, Attribute.Type.LCK, 0),
		Magus(5, 15, 0, Attribute.Type.INT, Attribute.Type.WIS, 6),
		Invoker(6, 35, 15, Attribute.Type.INT, Attribute.Type.WIS, 0),
		Archer(7, 15, 0, Attribute.Type.AGI, Attribute.Type.STR, 8),
		Hunter(8, 35, 15, Attribute.Type.AGI, Attribute.Type.STR, 0),
		Barbarian(9, 10, 5, Attribute.Type.STR, Attribute.Type.CON, 10),
		Berserker(10, 35, 15, Attribute.Type.STR, Attribute.Type.CON, 0);
		
		private int id;
		private int priBonus, secBonus;
		private int idUpgrade;
		private Attribute.Type priAtt, secAtt;
		Type(int id, int primary, int secondary, Attribute.Type primaryAtt, Attribute.Type secondaryAtt, int idUpgrade)
		{
			this.id = id;
			this.priBonus = primary;
			this.secBonus = secondary;
			this.priAtt = primaryAtt;
			this.secAtt = secondaryAtt;
			this.idUpgrade = idUpgrade;
		}
		
		public static final Type getTypeByID(int id)
		{
			switch(id)
			{
			case 1:
				return Fighter;
			case 2:
				return Champion;
			case 3:
				return Thief;
			case 4:
				return Assassin;
			case 5:
				return Magus;
			case 6:
				return Invoker;
			case 7:
				return Archer;
			case 8:
				return Hunter;
			case 9:
				return Barbarian;
			case 10:
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
		
		public Class.Type getUpgrade()
		{
			if(idUpgrade == 0)return null;
			return Class.Type.getTypeByID(idUpgrade);
		}
	}
}
