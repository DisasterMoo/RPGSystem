package com.disastermoo.rpgsystem.core.proxy;


import org.lwjgl.input.Keyboard;

import com.disastermoo.rpgsystem.core.EventHandler;

import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent.KeyInputEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ProxyClient extends ProxyCommon {
	//TODO CLIENT ONLY REGISTER THINGS!
	
	private static KeyBinding[] keys;
	@Override
	public void preInit(FMLPreInitializationEvent event)
    {
    	super.preInit(event);
    }

	@Override
    public void init(FMLInitializationEvent event)
    {
    	super.init(event);
    	keys = new KeyBinding[1];
    	keys[0] = new KeyBinding("Open skill tree", Keyboard.KEY_K, "RPG System");
    	for (int i = 0; i < keys.length; ++i) 
    	{
    	    ClientRegistry.registerKeyBinding(keys[i]);
    	}
    }
    
	@Override
    public void postInit(FMLPostInitializationEvent event) {
    	super.postInit(event);
    }
	

	public KeyBinding[] getKeyBiding()
	{
		return this.keys;
	}
	


}
