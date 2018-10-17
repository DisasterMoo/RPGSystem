package com.disastermoo.rpgsystem.player;

public class Attribute {
	public enum Type {
		STR, AGI, INT, CON;
	}
	private int STR, AGI, INT, CON;
	
	public Attribute()
	{
		this.STR = 10;
		this.AGI = 10;
		this.INT = 10;
		this.CON = 10;
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
	
	public void setSTR(int value)
	{
		this.STR = value;
	}
	
	public void setAGI(int value)
	{
		this.AGI = value;
	}
	
	public void setINT(int value)
	{
		this.INT = value;
	}
	
	public void setCON(int value)
	{
		this.CON = value;
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
	
}
