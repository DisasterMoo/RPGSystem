package com.disastermoo.rpgsystem.player.skilltree;

import com.disastermoo.rpgsystem.player.Attribute;

public class AttributeSkill extends Skill{
	
	private Attribute.Type attributeType;
	private int value;
	
	public AttributeSkill(String skillName, int requiredLevels, Attribute.Type attributeType, int value)
	{
		super(skillName, requiredLevels);
		this.attributeType = attributeType;
		this.value = value;
	}
	
	public Attribute.Type getAttributeType() {
		return this.attributeType;
	}
	
	public int getValue()
	{
		return this.value;
	}
}
