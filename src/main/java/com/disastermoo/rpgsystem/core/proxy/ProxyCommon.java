package com.disastermoo.rpgsystem.core.proxy;

import org.apache.logging.log4j.Logger;

import com.disastermoo.rpgsystem.RPGSystem;
import com.disastermoo.rpgsystem.core.GuiHandler;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;

public class ProxyCommon {
	
	//TODO ALL SERVER + CLIENT THINGS
	public static Logger logger;
    public void preInit(FMLPreInitializationEvent event)
    {
    	logger = event.getModLog();
    }

    public void init(FMLInitializationEvent event)
    {
    	NetworkRegistry.INSTANCE.registerGuiHandler(RPGSystem.INSTANCE, new GuiHandler());
        
    }
    
    public void postInit(FMLPostInitializationEvent event) {
        
    }
}
