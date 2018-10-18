package com.disastermoo.rpgsystem.player.skilltree;

import com.disastermoo.rpgsystem.player.Attribute;

public class AttributeSkill extends Skill{
	
	private Attribute.Type attributeType;
	
	public AttributeSkill(String icon, Attribute.Type attributeType)
	{
		super(attributeType.name() + "+", 1, icon);
		this.attributeType = attributeType;
	}
	
	public Attribute.Type getAttributeType() 
	{
		return this.attributeType;
	}
	
	private void setName()
	{
		String plus = "+";
		int i;
		for(i=0; i < this.learn && i < 4; i++) {
			plus += "+";
		}
		super.setSkillName(this.attributeType.name() + plus);
	}
	
	@Override
	public void learnSkill()
	{
		super.learnSkill();
		setName();
		super.setRequiredLevels(this.learn + 1);
	}
	
	@Override
	public void unlearnSkill()
	{
		super.learnSkill();
		setName();
		super.setRequiredLevels(this.learn + 1);
	}
}
