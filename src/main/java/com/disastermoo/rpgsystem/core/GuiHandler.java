package com.disastermoo.rpgsystem.core;

import com.disastermoo.rpgsystem.client.gui.GuiProfession;
import com.disastermoo.rpgsystem.client.gui.GuiStatus;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler{

	public static final int GUI_STATUS = 1, GUI_PROFESSION = 2;
	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		//if(ID == GUI_SKILL_TREE)return new ContainerGuiStatus(player);
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		if(ID == GUI_STATUS)return new GuiStatus();
		if(ID == GUI_PROFESSION)return new GuiProfession();
		return null;
	}
}
