package com.disastermoo.rpgsystem.player.skilltree;

public abstract class Skill {
	private String skillName;
	private int requiredLevels;
	private boolean learn;
	
	protected Skill(String skillName, int requiredLevels)
	{
		this.skillName = skillName;
		this.requiredLevels = requiredLevels;
		learn = false;
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
}
