package com.disastermoo.rpgsystem.player.skilltree;

import java.awt.geom.Point2D;
import java.util.List;

public abstract class Skill {
	private String skillName;
	private int requiredLevels;
	protected int learn;
	private String icon;
	private List<Skill> requirements;
	private Point2D position;
	
	protected Skill(String skillName, int requiredLevels, String icon)
	{
		this.skillName = skillName;
		this.requiredLevels = requiredLevels;
		this.icon = icon;
		learn = 0;
		requirements = null;
		position.setLocation(0, 0);
	}
	
	public void setSkillName(String newName)
	{
		this.skillName = newName;
	}
	
	public String getSkillName()
	{
		return this.skillName;
	}
	
	public void setRequiredLevels(int newRequiredLevel)
	{
		this.requiredLevels = newRequiredLevel;
	}
	
	public int getRequiredLevels()
	{
		return this.requiredLevels;
	}
	
	public int isLearned()
	{
		return this.learn;
	}
	
	public void learnSkill()
	{
		this.learn++;
		if(this.learn > 5)this.learn = 5;
	}
	
	public void unlearnSkill()
	{
		this.learn--;
		if(this.learn < 0)this.learn = 0;
	}
	
	public void setPosition(double posX, double posY)
	{
		this.position.setLocation(posX, posY);
	}
	
	public Point2D getPosition()
	{
		return this.position;
	}
	
	public Skill setRequirements(List<Skill> requirements)
	{
		this.requirements = requirements;
		return this;
	}
	
	public List<Skill> getRequirements()
	{
		return this.requirements;
	}
}
