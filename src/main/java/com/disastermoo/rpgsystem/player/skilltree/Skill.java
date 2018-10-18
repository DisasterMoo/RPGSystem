package com.disastermoo.rpgsystem.player.skilltree;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

public abstract class Skill {
	private String skillName;
	private int requiredLevels;
	private boolean learn;
	private String icon;
	private List<Skill> requirements;
	private Point2D position;
	
	protected Skill(String skillName, int requiredLevels, String icon)
	{
		this.skillName = skillName;
		this.requiredLevels = requiredLevels;
		this.icon = icon;
		learn = false;
		requirements = null;
		position.setLocation(0, 0);
	}
	
	public String getSkillName()
	{
		return this.skillName;
	}
	
	public int getRequiredLevels()
	{
		return this.requiredLevels;
	}
	
	public boolean isLearned()
	{
		return this.learn;
	}
	
	public void learnSkill()
	{
		this.learn = true;
	}
	
	public void unlearnSkill()
	{
		this.learn = false;
	}
	
	public void setPosition(double posX, double posY)
	{
		this.position.setLocation(posX, posY);
	}
	
	public Point2D getPosition()
	{
		return this.position;
	}
	
	public void setRequirements(List<Skill> requirements)
	{
		this.requirements = requirements;
	}
	
	public List<Skill> getRequirements()
	{
		return this.requirements;
	}
}
