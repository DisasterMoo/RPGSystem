package com.disastermoo.rpgsystem.core.proxy;

import org.apache.logging.log4j.Logger;

import com.disastermoo.rpgsystem.RPGSystem;
import com.disastermoo.rpgsystem.core.EventHandler;
import com.disastermoo.rpgsystem.core.GuiHandler;
import com.disastermoo.rpgsystem.core.player.skilltree.SkilltreeBuilder;

import net.minecraftforge.common.MinecraftForge;
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
    	logger.info("Creating skill list...");
        SkilltreeBuilder.initializeSkills();
        logger.info("Skill list created.");
        
    }
    
    public void postInit(FMLPostInitializationEvent event) {
        
    }
}
