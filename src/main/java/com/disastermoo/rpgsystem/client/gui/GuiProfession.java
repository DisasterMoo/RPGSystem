package com.disastermoo.rpgsystem.client.gui;

import java.util.ArrayList;

import com.disastermoo.rpgsystem.RPGSystem;
import com.disastermoo.rpgsystem.client.util.ModResource;
import com.disastermoo.rpgsystem.core.GuiHandler;
import com.disastermoo.rpgsystem.core.ProxyClient;
import com.disastermoo.rpgsystem.core.ProxyCommon;
import com.disastermoo.rpgsystem.core.RegistryHandler;
import com.disastermoo.rpgsystem.core.capabilities.network.RPGUpgradeMessage;
import com.disastermoo.rpgsystem.core.capabilities.network.RPGUpgradeMessage.UpgradeValues;
import com.disastermoo.rpgsystem.core.config.RPGConfig.Constants;
import com.disastermoo.rpgsystem.core.system.EntityInfo;
import com.disastermoo.rpgsystem.core.system.Profession;
import com.disastermoo.rpgsystem.core.util.RPGUtils;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.item.ItemStack;

public class GuiProfession extends GuiBase{
	
	private GuiButton btnSave, btnCancel, btnChange;
	private int saveX, saveY, cancelX, cancelY, changeX, changeY;
	private EntityInfo player;
	private ArrayList<Profession.Type> upgrades;
	private int ID;
	
	public GuiProfession()
	{
		player = new EntityInfo();
		EntityInfo cloning = ((ProxyClient)RPGSystem.proxy).getClientInfo();
		upgrades = cloning.getProfession().getUpgrades();
		ID = 0;
		player.setClassType(upgrades.get(ID));
		player.getAttributes().setSTR(cloning.getAttributes().getSTR());
		player.getAttributes().setAGI(cloning.getAttributes().getAGI());
		player.getAttributes().setCON(cloning.getAttributes().getCON());
		player.getAttributes().setINT(cloning.getAttributes().getINT());
		player.getAttributes().setWIS(cloning.getAttributes().getWIS());
		player.getAttributes().setLCK(cloning.getAttributes().getLCK());
		for(int i = 0; i < 7; i++)
		{
			player.setMateriaSlotEnabled(i, cloning.isMateriaSlotEnabled(i));
			player.setMateriaInSlot(i, cloning.getMateriaInSlot(i));
		}
	}
	
	@Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) 
	{
		drawMainBackground();
		drawStatus();
		drawCharacter(mouseX, mouseY);
		drawProfession();
        super.drawScreen(mouseX, mouseY, partialTicks);
	}
	
	@Override
	public void initGui()
	{
		saveX = 18;
		saveY = 165;
		cancelX = 93;
		cancelY = 165;
		changeX = 217;
		changeY = 165;
		btnChange = new GuiButton(1, changeX + left, changeY + top, 70, 20, "Change");
		btnSave = new GuiButton(2, cancelX + left, cancelY + top, 70, 20, "Save");
		btnCancel = new GuiButton(3, saveX + left, saveY + top, 70, 20, "Cancel");
		
		this.buttonList.add(btnChange);
		this.buttonList.add(btnSave);
		this.buttonList.add(btnCancel);
		checkEnabled();
	}
	
	@Override
	public void updateScreen()
	{
		left = (width - Constants.SIZE_X_GUI_STATUS_BACKGROUND * 2) / 2;
        top = (height - Constants.SIZE_Y_GUI_STATUS_BACKGROUND) / 2;
		btnSave.x = saveX + left;
		btnSave.y = saveY + top;
		btnCancel.x = cancelX + left;
		btnCancel.y = cancelY + top;
		btnChange.x = changeX + left;
		btnChange.y = changeY + top;
	}
	
	public void addButton(int id, int x, int y, int width, int height, String text) {
		GuiButton btn = new GuiButton(id, (int) ((x + left)), (int) ((y + top)), width, height, text);
        this.buttonList.add(btn);
    }
	
	public void checkEnabled()
	 {
		 int level = upgrades.get(ID).getLevelsNeeded();
		 if(level > Minecraft.getMinecraft().player.experienceLevel) {
			 btnSave.enabled = false;
		 }else {
			 btnSave.enabled = true;
		 }
	 }
	
	 @Override
	 protected void actionPerformed(GuiButton btn)
	 {
		 switch(btn.id)
		 {
		 	case 1:
		 		ID++;
		 		if(ID >= upgrades.size())ID = 0;
		 		player.setClassType(upgrades.get(ID));
		 		checkEnabled();
		 		break;
		 	case 2:
		 		UpgradeValues value = new UpgradeValues();
		 		value.upgradeClass = player.getProfession().getID();
		 		ProxyCommon.NETWORK_INSTANCE.sendToServer(new RPGUpgradeMessage(value));
		 		((ProxyClient)RPGSystem.proxy).markUpdate(true);
		 		Minecraft.getMinecraft().player.openGui(RPGSystem.INSTANCE, GuiHandler.GUI_STATUS, 
		    			Minecraft.getMinecraft().player.world, (int)Minecraft.getMinecraft().player.posX, (int)Minecraft.getMinecraft().player.posY, (int)Minecraft.getMinecraft().player.posZ);
		 		break;
		 	case 3:
		 		Minecraft.getMinecraft().player.openGui(RPGSystem.INSTANCE, GuiHandler.GUI_STATUS, 
		    			Minecraft.getMinecraft().player.world, (int)Minecraft.getMinecraft().player.posX, (int)Minecraft.getMinecraft().player.posY, (int)Minecraft.getMinecraft().player.posZ);
		 		break;
			 default:
				 break;
		 }
	 }
	
    @Override
    public boolean doesGuiPauseGame()
    {
        return false;
    }
    
    protected void drawMainBackground()
    {
    	bindTexture(new ModResource(Constants.ASSET_GUI_STATUS_LEFT_BACKGROUND));
    	drawRect(0, 0, 0, 0, Constants.SIZE_X_GUI_STATUS_BACKGROUND, Constants.SIZE_Y_GUI_STATUS_BACKGROUND);
    	bindTexture(new ModResource(Constants.ASSET_GUI_STATUS_RIGHT_BACKGROUND));
    	drawRect(Constants.SIZE_X_GUI_STATUS_BACKGROUND, 0, 0, 0, Constants.SIZE_X_GUI_STATUS_BACKGROUND, Constants.SIZE_Y_GUI_STATUS_BACKGROUND);
    }
    
    public void drawCharacter(float mouseX, float mouseY)
    {
    	int posX, posY;
		posX = left + 52;
		posY = top + 90;
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		GuiInventory.drawEntityOnScreen(posX, posY, 30, posX - mouseX, posY - 50 - mouseY, this.mc.player);
    }
    
    protected void drawProfession()
    {   	
    	bindTexture(new ModResource(player.getProfession().getProfessionAsset()));
    	drawRect(217, 21, Constants.SIZE_X_PROFESSION, Constants.SIZE_Y_PROFESSION);
    	this.drawCenteredString(upgrades.get(ID).name(), 252, 150, 0.8f, 0);
    	String exib = "";
    	if(player.getProfession().getPrimaryBonus() > 0)exib += player.getProfession().getPrimaryAttribute().name() + ": +" + player.getProfession().getPrimaryBonus() + "%";
    	if(player.getProfession().getSecondaryBonus() > 0)exib += " & " + player.getProfession().getSecondaryAttribute().name() + ": +" + player.getProfession().getSecondaryBonus() + "%";
    	if(!exib.equals(""))this.drawCenteredString(exib, 252, 130, 0.8f, 0);
    	this.drawCenteredString("Required Levels: " + player.getProfession().getLevelsNeeded(), 252, 110, 0.8f, 0);
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
