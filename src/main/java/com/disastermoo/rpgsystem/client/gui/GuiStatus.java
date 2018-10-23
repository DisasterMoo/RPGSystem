package com.disastermoo.rpgsystem.client.gui;

import net.minecraft.client.Minecraft;

import java.io.IOException;

import com.disastermoo.rpgsystem.client.util.ModResource;
import com.disastermoo.rpgsystem.client.util.RenderRotation;
import com.disastermoo.rpgsystem.core.config.RPGConfig.Constants;
import com.disastermoo.rpgsystem.core.system.Attribute;
import com.disastermoo.rpgsystem.core.system.EntityInfo;

public class GuiStatus extends GuiBase {
	
	public EntityInfo player;
	
	public GuiStatus()
	{
		player = new EntityInfo();
	}
	
	@Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) 
	{
		drawMainBackground();
		drawAttribute(player);
		drawStatus(player);
        super.drawScreen(mouseX, mouseY, partialTicks);
	}
	
    @Override
    public boolean doesGuiPauseGame()
    {
        return true;
    }
    
    protected void drawMainBackground()
    {
    	left = (width - Constants.SIZE_X_MAIN_BACKGROUND * 2) / 2;
        top = (height - Constants.SIZE_Y_MAIN_BACKGROUND) / 2;
    	bindTexture(new ModResource(Constants.ASSET_MAIN_BACKGROUND));
    	drawRect(0, 0, 0, 0, Constants.SIZE_X_MAIN_BACKGROUND, Constants.SIZE_Y_MAIN_BACKGROUND);
    	drawRect(Constants.SIZE_X_MAIN_BACKGROUND, 0, 0, 0, Constants.SIZE_X_MAIN_BACKGROUND, Constants.SIZE_Y_MAIN_BACKGROUND, RenderRotation.FLIP_HORIZONTAL);
    }

    @Override
    protected void mouseClicked(int x0, int y0, int button) throws IOException {
    	super.mouseClicked(x0, y0, button);
    	int x = x0 - left;
    	int y = y0 - top;
    }
    public void drawAttribute(EntityInfo player)
    {
    	this.drawString("STR: " + player.getAttributes().getSTR() + " STR Final: " + player.getFinalSTR(), 25, 150, 0);
    	this.drawString("CON: " + player.getAttributes().getCON() + " CON Final: " + player.getFinalCON(), 25, 160, 0);
    	this.drawString("AGI: " + player.getAttributes().getAGI() + " AGI Final: " + player.getFinalAGI(), 25, 170, 0);
    	this.drawString("INT: " + player.getAttributes().getINT() + " INT Final: " + player.getFinalINT(), 25, 180, 0);
    	this.drawString("WIS: " + player.getAttributes().getWIS() + " WIS Final: " + player.getFinalWIS(), 25, 190, 0);
    	this.drawString("LCK: " + player.getAttributes().getLCK() + " LCK Final: " + player.getFinalSTR(), 25, 200, 0);
    }    
    public void drawStatus(EntityInfo player)
    {
    	this.drawString("Class: " + player.getClassType(), 25, 15, 0);
    	this.drawString("Bonus HP: " + player.getHealthBonus(), 25, 25, 0);
    	this.drawString("P.Dmg: " + player.getPhysicalDamageMultiplier(), 25, 35, 0);
    	this.drawString("P.CChance: " + player.getPhysicalCritChance(), 25, 45, 0);
    	this.drawString("P.CMulti: " + player.getPhysicalCritMultiplier(), 25, 55, 0);
    	this.drawString("M.Dmg: " + player.getMagicalDamageMultiplier(), 25, 65, 0);
    	this.drawString("M.CChance: " + player.getMagicalCritChance(), 25, 75, 0);
    	this.drawString("M.CMulti: " + player.getMagicalDamageMultiplier(), 25, 85, 0);
    	this.drawString("Drop Bonus: " + player.getDropBonus(), 25, 95, 0);
    	this.drawString("MoveSpeed: " + player.getMovSpeed(), 25, 105, 0);
    	this.drawString("AttackSpeed: " + player.getAttackSpeedMultiplier(), 25, 115, 0);
    	this.drawString("KBack Resistance: " + player.getKnockbackResistance(), 25, 125, 0);
    }
}
