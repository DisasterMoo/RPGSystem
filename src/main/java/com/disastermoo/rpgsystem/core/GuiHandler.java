package com.disastermoo.rpgsystem.core;

import com.disastermoo.rpgsystem.client.gui.GuiSkillTree;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler{

	public static final int GUI_SKILL_TREE = 1;
	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		if(ID == GUI_SKILL_TREE)return new GuiSkillTree();
		return null;
	}

}
