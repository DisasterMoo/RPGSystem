package com.disastermoo.rpgsystem.client.gui;

import net.minecraft.client.Minecraft;

import java.io.IOException;

import com.disastermoo.rpgsystem.client.util.ModResource;
import com.disastermoo.rpgsystem.client.util.RenderRotation;
import com.disastermoo.rpgsystem.core.RPGConfig.Constants;
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
    	this.drawString("STR: " + player.getAttributes().getSTR(), 25, 150, 0);
    	this.drawString("CON: " + player.getAttributes().getCON(), 25, 160, 0);
    	this.drawString("AGI: " + player.getAttributes().getAGI(), 25, 170, 0);
    	this.drawString("INT: " + player.getAttributes().getINT(), 25, 180, 0);
    	this.drawString("WIS: " + player.getAttributes().getWIS(), 25, 190, 0);
    	this.drawString("LCK: " + player.getAttributes().getLCK(), 25, 200, 0);
    }
}
