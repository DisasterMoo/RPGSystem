package com.disastermoo.rpgsystem.player.skilltree;

import com.disastermoo.rpgsystem.player.Attribute;

public class ClassSkill extends Skill{
	public enum Type {
		Swordfighter, Defender, Magus, Cleric, Marksman; 
	}
	
	private Type classType;
	private int level;
	
	public ClassSkill(String icon, Type classType, int level)
	{
		super(classType.name(), (level - 1) * 2 + 1, icon);
		this.classType = classType;
		this.level = level;
	}
	
	public Attribute.Type[] getBonusAttributes()
	{
		Attribute.Type[] bonus = new Attribute.Type[2];
		if(this.classType == Type.Swordfighter)
		{
			bonus[0] = Attribute.Type.STR;
			bonus[1] = Attribute.Type.AGI;
		}
		if(this.classType == Type.Defender)
		{
			bonus[0] = Attribute.Type.CON;
			bonus[1] = Attribute.Type.STR;
		}
		if(this.classType == Type.Magus)
		{
			bonus[0] = Attribute.Type.INT;
			bonus[1] = Attribute.Type.AGI;
		}
		if(this.classType == Type.Cleric)
		{
			bonus[0] = Attribute.Type.INT;
			bonus[1] = Attribute.Type.CON;
		}
		if(this.classType == Type.Marksman)
		{
			bonus[0] = Attribute.Type.AGI;
			bonus[1] = Attribute.Type.INT;
		}
		return bonus;
	}
	
	public int getLevel()
	{
		return level;
	}
	
	public Type getClassType()
	{
		return this.classType;
	}
	
	@Override
	public void learnSkill()
	{
		this.level = 1;
	}
	
	@Override
	public void unlearnSkill()
	{
		this.level = 0;
	}
}
