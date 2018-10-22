package com.disastermoo.rpgsystem.client.gui;

import java.io.IOException;

import com.disastermoo.rpgsystem.client.util.ModResource;
import com.disastermoo.rpgsystem.client.util.RenderRotation;
import com.disastermoo.rpgsystem.core.config.RPGConfig.Constants;

public class GuiStatus extends GuiBase {
	
	public GuiStatus()
	{
	}
	
	@Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) 
	{
		drawMainBackground();
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
}
