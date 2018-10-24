package com.disastermoo.rpgsystem.core.system;

public class Attribute {
	public enum Type {
		STR, AGI, CON, INT, WIS, LCK;
	}
	/* Attributes
	 * STR - Damage, CritDmg bonus
	 * AGI - Attack speed, Move speed
	 * CON - Health bonus, Life Regen
	 * INT - Magical Damage
	 * WIS - Magical Crit Dmg, Mana regen
	 * LCK - Critical chance, drop chance bonus
	 * */
	private int STR, AGI, CON, INT, WIS, LCK;
	
	public Attribute()
	{
		this.STR = 10;
		this.AGI = 10;
		this.CON = 10;
		this.INT = 10;
		this.WIS = 10;
		this.LCK = 10;
	}
	
	public int getSTR()
	{
		return this.STR;
	}
	
	public int getAGI()
	{
		return this.AGI;
	}
	
	public int getINT()
	{
		return this.INT;
	}
	
	public int getCON()
	{
		return this.CON;
	}
	
	public int getWIS()
	{
		return this.WIS;
	}
	
	public int getLCK()
	{
		return this.LCK;
	}
	
	public void setSTR(int value)
	{
		if(value >= 10)this.STR = value;
	}
	
	public void setAGI(int value)
	{
		if(value >= 10)this.AGI = value;
	}
	
	public void setINT(int value)
	{
		if(value >= 10)this.INT = value;
	}
	
	public void setCON(int value)
	{
		if(value >= 10)this.CON = value;
	}
	
	public void setWIS(int value)
	{
		if(value >= 10)this.WIS = value;
	}
	
	public void setLCK(int value)
	{
		if(value >= 10)this.LCK = value;
	}
	
	public void addSTR(int value)
	{
		this.STR += value;
	}
	
	public void addAGI(int value)
	{
		this.AGI += value;
	}
	
	public void addINT(int value)
	{
		this.INT += value;
	}
	
	public void addCON(int value)
	{
		this.CON += value;
	}
	
	public void addWIS(int value)
	{
		this.WIS += value;
	}
	
	public void addLCK(int value)
	{
		this.LCK += value;
	}
}
