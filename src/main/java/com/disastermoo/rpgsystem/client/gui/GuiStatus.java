package com.disastermoo.rpgsystem.client.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayerMP;

import java.io.IOException;

import com.disastermoo.rpgsystem.RPGSystem;
import com.disastermoo.rpgsystem.core.ProxyClient;
import com.disastermoo.rpgsystem.core.ProxyCommon;
import com.disastermoo.rpgsystem.core.capabilities.network.RPGInfoMessage;
import com.disastermoo.rpgsystem.core.capabilities.network.RPGUpgradeMessage;
import com.disastermoo.rpgsystem.core.capabilities.network.RPGUpgradeMessage.UpgradeValues;
import com.disastermoo.rpgsystem.client.util.ModResource;
import com.disastermoo.rpgsystem.core.config.RPGConfig.Constants;
import com.disastermoo.rpgsystem.core.system.EntityInfo;
import com.disastermoo.rpgsystem.core.util.RPGUtils;

import io.netty.handler.codec.http.HttpHeaders.Values;

public class GuiStatus extends GuiBase {
	
	public EntityInfo player;
	private UpgradeValues value;
	private int auxlvl;
	private GuiButton btnSave, btnCancel;
	private boolean markDirty;
	
	public GuiStatus()
	{
		player = ((ProxyClient)RPGSystem.proxy).getClientInfo();
		value = new UpgradeValues();
		auxlvl = 0;
		markDirty = false;
	}
	
	@Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) 
	{
		drawMainBackground();
		drawAttribute();
		drawStatus(); 
		drawCharacter(mouseX, mouseY);
		if(this.buttonList.get(0).visible)drawUpgradeValues();
        super.drawScreen(mouseX, mouseY, partialTicks);
	}
	
	@Override
	public void initGui()
	{
		int x, y, space;
		x = 15;
		y = 165;
		space = 25;
		for(int i = 1; i <= 6; i++) {
			addButton(i, x + (i-1) * space, y, 20, 20, "+");
		}
		btnSave = new GuiButton(7, x + 6 * space + left, 200 + top, 20, 20, "O");
		btnCancel = new GuiButton(8, x + 7 * space + left, 200 + top, 20, 20, "X");
		this.buttonList.add(btnSave);
		this.buttonList.add(btnCancel);
		checkVisibility();
		checkEnabled();
	}
	
	public void addButton(int id, int x, int y, int width, int height, String text) {
		GuiButton btn = new GuiButton(id, (int) ((x + left)), (int) ((y + top)), width, height, text);
        this.buttonList.add(btn);
    }
	
	 @Override
	public void updateScreen()
	{
		 if(this.markDirty) {
			 if(!((ProxyClient)RPGSystem.proxy).isMarkedForUpdate()) {
				 player = ((ProxyClient)RPGSystem.proxy).getClientInfo();
					value = new UpgradeValues();
					auxlvl = 0;
					markDirty = false;
					checkVisibility();
					checkEnabled();
			 }
		 }else {
			 /* Update button location */
			int x, y, space;
			x = 15;
			y = 165;
			space = 25;
			for(int i = 0; i < 6; i++) {
				GuiButton btn = this.buttonList.get(i);
				btn.x = x + i * space + left;
				btn.y = y + top;
			}
			btnSave.x = x + 4 * space + left;
			btnSave.y = 200 + top;
			btnCancel.x = x + 5 * space + left;
			btnCancel.y = 200 + top;
		 }
	}
	 
	 public void checkVisibility()
	 {
		 if(Minecraft.getMinecraft().player.experienceLevel < RPGUtils.minLevel(player)) {
			 for(int i = 0; i < 8; i++)this.buttonList.get(i).visible = false;
		 }else {
			 for(int i = 0; i < 8; i++)this.buttonList.get(i).visible = true;
		 }
	 }
	 
	 public void checkEnabled()
	 {
		 int finalLevel = Minecraft.getMinecraft().player.experienceLevel - auxlvl;
		 for(int i = 0; i < 8; i++)this.buttonList.get(i).enabled = true;
		 if(finalLevel < (player.getAttributes().getSTR() + value.pointsSTR) / 10)this.buttonList.get(0).enabled = false;
		 if(finalLevel < (player.getAttributes().getAGI() + value.pointsAGI)  / 10)this.buttonList.get(1).enabled = false;
		 if(finalLevel < (player.getAttributes().getCON() + value.pointsCON)  / 10)this.buttonList.get(2).enabled = false;
		 if(finalLevel < (player.getAttributes().getINT() + value.pointsINT)  / 10)this.buttonList.get(3).enabled = false;
		 if(finalLevel < (player.getAttributes().getWIS() + value.pointsWIS)  / 10)this.buttonList.get(4).enabled = false;
		 if(finalLevel < (player.getAttributes().getLCK() + value.pointsLCK)  / 10)this.buttonList.get(5).enabled = false;
		 if(auxlvl > 0) {
			 btnSave.enabled = true;
			 btnCancel.enabled = true;
		 }else {
			 btnSave.enabled = false;
			 btnCancel.enabled = false;
		 }
	 }
	
	 @Override
	 protected void actionPerformed(GuiButton btn)
	 {
		 switch(btn.id)
		 {
		 	case 1:
		 		value.pointsSTR++;
		 		break;
		 	case 2:
		 		value.pointsAGI++;
		 		break;
		 	case 3:
		 		value.pointsCON++;
		 		break;
		 	case 4:
		 		value.pointsINT++;
		 		break;
		 	case 5:
		 		value.pointsWIS++;
		 		break;
		 	case 6:
		 		value.pointsLCK++;
		 		break;
		 	case 7:
		 		ProxyCommon.NETWORK_INSTANCE.sendToServer(new RPGUpgradeMessage(value));
		 		this.markDirty = true;
		 		((ProxyClient)RPGSystem.proxy).markUpdate(true);
		 		break;
		 	case 8:
		 		value.pointsSTR = 0;
		 		value.pointsAGI = 0;
		 		value.pointsCON = 0;
		 		value.pointsINT = 0;
		 		value.pointsWIS = 0;
		 		value.pointsLCK = 0;
		 		break;
			 default:
				 break;
		 }
		 auxlvl = RPGUtils.upgradeLevelsNeeded(value, player);
		 checkEnabled();
	 }
	
    @Override
    public boolean doesGuiPauseGame()
    {
        return false;
    }
    
    protected void drawMainBackground()
    {
    	left = (width - Constants.SIZE_X_GUI_STATUS_BACKGROUND * 2) / 2;
        top = (height - Constants.SIZE_Y_GUI_STATUS_BACKGROUND) / 2;
    	bindTexture(new ModResource(Constants.ASSET_GUI_STATUS_LEFT_BACKGROUND));
    	drawRect(0, 0, 0, 0, Constants.SIZE_X_GUI_STATUS_BACKGROUND, Constants.SIZE_Y_GUI_STATUS_BACKGROUND);
    	bindTexture(new ModResource(Constants.ASSET_GUI_STATUS_RIGHT_BACKGROUND));
    	drawRect(Constants.SIZE_X_GUI_STATUS_BACKGROUND, 0, 0, 0, Constants.SIZE_X_GUI_STATUS_BACKGROUND, Constants.SIZE_Y_GUI_STATUS_BACKGROUND);
    }

    @Override
    protected void mouseClicked(int x0, int y0, int button) throws IOException {
    	super.mouseClicked(x0, y0, button);
    	int x = x0 - left;
    	int y = y0 - top;
    }
    
    public void drawUpgradeValues()
    {
    	this.drawCenteredString(String.valueOf(value.pointsSTR), 25, 190, 0.8f, 0);
    	this.drawCenteredString(String.valueOf(value.pointsAGI), 50, 190, 0.8f, 0);
    	this.drawCenteredString(String.valueOf(value.pointsCON), 75, 190, 0.8f, 0);
    	this.drawCenteredString(String.valueOf(value.pointsINT), 100, 190, 0.8f, 0);
    	this.drawCenteredString(String.valueOf(value.pointsWIS), 125, 190, 0.8f, 0);
    	this.drawCenteredString(String.valueOf(value.pointsLCK), 150, 190, 0.8f, 0);
    	
    	this.drawString("Levels needed: " + auxlvl, 28, 210, 0.8f, 0);
    }
    
    public void drawAttribute()
    {
    	this.drawCenteredString("STR", 25, 140, 0.5f, 0);
    	this.drawCenteredString(String.valueOf(player.getAttributes().getSTR()), 25, 146, 0.5f, 0);
    	this.drawCenteredString("Final", 25, 152, 0.5f, 0);
    	this.drawCenteredString(RPGUtils.formatString(player.getFinalSTR()), 25, 158, 0.5f, 0);
    	
    	this.drawCenteredString("AGI", 50, 140, 0.5f, 0);
    	this.drawCenteredString(String.valueOf(player.getAttributes().getAGI()), 50, 146, 0.5f, 0);
    	this.drawCenteredString("Final", 50, 152, 0.5f, 0);
    	this.drawCenteredString(RPGUtils.formatString(player.getFinalAGI()), 50, 158, 0.5f, 0);
    	
    	this.drawCenteredString("CON", 75, 140, 0.5f, 0);
    	this.drawCenteredString(String.valueOf(player.getAttributes().getCON()), 75, 146, 0.5f, 0);
    	this.drawCenteredString("Final", 75, 152, 0.5f, 0);
    	this.drawCenteredString(RPGUtils.formatString(player.getFinalCON()), 75, 158, 0.5f, 0);
    	
    	this.drawCenteredString("INT", 100, 140, 0.5f, 0);
    	this.drawCenteredString(String.valueOf(player.getAttributes().getINT()), 100, 146, 0.5f, 0);
    	this.drawCenteredString("Final", 100, 152, 0.5f, 0);
    	this.drawCenteredString(RPGUtils.formatString(player.getFinalINT()), 100, 158, 0.5f, 0);
    	
    	this.drawCenteredString("WIS", 125, 140, 0.5f, 0);
    	this.drawCenteredString(String.valueOf(player.getAttributes().getWIS()), 125, 146, 0.5f, 0);
    	this.drawCenteredString("Final", 125, 152, 0.5f, 0);
    	this.drawCenteredString(RPGUtils.formatString(player.getFinalWIS()), 125, 158, 0.5f, 0);
    	
    	this.drawCenteredString("LCK", 150, 140, 0.5f, 0);
    	this.drawCenteredString(String.valueOf(player.getAttributes().getLCK()), 150, 146, 0.5f, 0);
    	this.drawCenteredString("Final", 150, 152, 0.5f, 0);
    	this.drawCenteredString(RPGUtils.formatString(player.getFinalLCK()), 150, 158, 0.5f, 0);
    }
    
    public void drawCharacter(float mouseX, float mouseY)
    {
    	int posX, posY;
		posX = left + 52;
		posY = top + 90;
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		GuiInventory.drawEntityOnScreen(posX, posY, 30, posX - mouseX, posY - 50 - mouseY, this.mc.player);
    }
    
    public void drawStatus()
    {
    	int x = 85;
    	this.drawString("P. Dmg: " + RPGUtils.formatString(player.getPhysicalDamageMultiplier()) + "x", x, 20, 0.5f, 0);
    	this.drawString("P. Crit. %: " + RPGUtils.formatString(player.getPhysicalCritChance() * 100 ) + "%", x, 30, 0.5f, 0);
    	this.drawString("P. Crit.: " + RPGUtils.formatString(player.getPhysicalCritMultiplier()) + "x", x, 40, 0.5f, 0);
    	this.drawString("Health: " + RPGUtils.formatString(player.getHealthBonus()), x, 50, 0.5f, 0);
    	this.drawString("Kb. Res: " + RPGUtils.formatString(player.getKnockbackResistance() * 100 ) + "%", x, 60, 0.5f, 0);
    	this.drawString("Mv. Spd: " + RPGUtils.formatString(player.getMovSpeed() * 10) + "x", x, 70, 0.5f, 0);
    	this.drawString("M. Dmg: " + RPGUtils.formatString(player.getMagicalDamageMultiplier()) + "x", x, 80, 0.5f, 0);
    	this.drawString("M. Crit. %: " + RPGUtils.formatString(player.getMagicalCritChance() * 100 ) + "%", x, 90, 0.5f, 0);
    	this.drawString("M. Crit.: " + RPGUtils.formatString(player.getMagicalCritMultiplier()) + "x", x, 100, 0.5f, 0);    	
    }
}
