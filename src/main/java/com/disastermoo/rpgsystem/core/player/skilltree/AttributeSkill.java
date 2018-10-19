package com.disastermoo.rpgsystem.core.player.skilltree;

import java.awt.geom.Point2D;

import com.disastermoo.rpgsystem.core.player.Attribute;

public class AttributeSkill extends Skill{
	
	private Attribute.Type attributeType;
	
	public AttributeSkill(Attribute.Type attributeType)
	{
		super(attributeType.name() + "+", 1);
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

	@Override
	public Point2D getIconLocation() {
		switch(this.attributeType)
		{
		case AGI:
			return new Point2D.Double(0, 0);
		case ALL:
			return new Point2D.Double(0, 0);
		case CON:
			return new Point2D.Double(0, 0);
		case INT:
			return new Point2D.Double(0, 0);
		case STR:
			return new Point2D.Double(0, 0);
		default:
			break;
		}
		return null;
	}
}
