package com.disastermoo.rpgsystem.client.gui;

import java.io.IOException;

import com.disastermoo.rpgsystem.core.config.Config.Constants;
import com.disastermoo.rpgsystem.core.player.skilltree.Skill;
import com.disastermoo.rpgsystem.core.util.ModResource;
import com.disastermoo.rpgsystem.core.util.RenderRotation;

public class GuiSkillTree extends GuiBase {
	
	
	int movingX, movingY;
	
	public GuiSkillTree()
	{
		movingX = 0;
		movingY = 0;
	}
	
	@Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) 
	{
		drawMainBackground();
        drawSkills();
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
    
    private void drawSkills()
    {
    	bindTexture(new ModResource(Constants.ASSET_LEFT_SIDE_BACKGROUND));
    	drawRect(20, 38, 0, 0, 149, 145);
    	bindTexture(new ModResource(Constants.ASSET_RIGHT_SIDE_BACKGROUND));
    	drawRect(169, 38, 0, 0, 149, 145);
    	drawSkill(null, 169, 76);
    }
    
    private void drawSkill(Skill skill, int posX, int posY)
    {
    	bindTexture(new ModResource(Constants.ASSET_ICON_BACKGROUND));
    	drawRect(posX, posY, 0, 0, 32, 32);
    	drawRect(posX, posY, 32, 0, 32, 32);
    }
    
    @Override
    protected void mouseClicked(int x0, int y0, int button) throws IOException {
    	super.mouseClicked(x0, y0, button);
    	int x = x0 - left;
    	int y = y0 - top;
    }
}
