package com.disastermoo.rpgsystem.core;

import com.disastermoo.rpgsystem.RPGSystem;
import com.disastermoo.rpgsystem.core.proxy.ProxyClient;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent.KeyInputEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@Mod.EventBusSubscriber
public class EventHandler {
	
	private static EventHandler instance = null;
	
	public static EventHandler getInstance()
    {
		if(instance == null)instance = new EventHandler();
    	return instance;
    }
	
	@SideOnly(Side.CLIENT)
	@SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
	public static void keyInput(KeyInputEvent event)
	{
		KeyBinding[] keys = ((ProxyClient)RPGSystem.proxy).getKeyBiding();
	    if (keys[0].isPressed()) 
	    {
	    	Minecraft.getMinecraft().player.openGui(RPGSystem.INSTANCE, GuiHandler.GUI_SKILL_TREE, 
	    			Minecraft.getMinecraft().player.world, (int)Minecraft.getMinecraft().player.posX, (int)Minecraft.getMinecraft().player.posY, (int)Minecraft.getMinecraft().player.posZ);
	    }	
	}
}
