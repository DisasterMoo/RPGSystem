package com.disastermoo.rpgsystem.core.player.skilltree;

import java.awt.geom.Point2D;
import java.util.List;

public abstract class Skill {
	private String skillName;
	private int requiredLevels;
	protected int learn;
	private List<Skill> requirements;
	
	protected Skill(String skillName, int requiredLevels)
	{
		this.skillName = skillName;
		this.requiredLevels = requiredLevels;
		learn = 0;
		requirements = null;
	}
	
	public abstract Point2D getIconLocation();
	
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
