package com.disastermoo.rpgsystem.core;


import org.lwjgl.input.Keyboard;

import com.disastermoo.rpgsystem.core.capabilities.network.RPGInfoMessage;
import com.disastermoo.rpgsystem.core.system.EntityInfo;

import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;

public class ProxyClient extends ProxyCommon {
	private EntityInfo clientInfo;
	private boolean markUpdate;
	
	private static KeyBinding[] keys;
	@Override
	public void preInit(FMLPreInitializationEvent event)
    {
    	super.preInit(event);
    	clientInfo = null;
    	markUpdate = true;
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
    	NETWORK_INSTANCE.registerMessage(RPGInfoMessage.RPGInfoMessageHandler.class, RPGInfoMessage.class, 0, Side.CLIENT);
    }
    
	@Override
    public void postInit(FMLPostInitializationEvent event) {
    	super.postInit(event);
    }
	

	public KeyBinding[] getKeyBiding()
	{
		return this.keys;
	}
	
	public EntityInfo getClientInfo()
	{
		return this.clientInfo;
	}
	
	public void setClientInfo(EntityInfo info)
	{
		this.clientInfo = info;
	}
	
	public void markUpdate(boolean value)
	{
		this.markUpdate = value;
	}
	
	public boolean isMarkedForUpdate()
	{
		return this.markUpdate;
	}

}
