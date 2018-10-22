package com.disastermoo.rpgsystem.core;

import org.apache.logging.log4j.Logger;

import com.disastermoo.rpgsystem.RPGSystem;
import com.disastermoo.rpgsystem.core.config.RPGData;
import com.disastermoo.rpgsystem.core.config.RPGConfig.Constants;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.common.config.Config.Type;
import net.minecraftforge.common.config.ConfigManager;

public class ProxyCommon {
	
	//TODO ALL SERVER + CLIENT THINGS
	public static Logger logger;
    public void preInit(FMLPreInitializationEvent event)
    {
    	logger = event.getModLog();
    	RPGData.INSTANCE.setFile(event.getModConfigurationDirectory().getAbsolutePath() + "/RPGSystem/monster.list");
    	RPGData.INSTANCE.load();
    }

    public void init(FMLInitializationEvent event)
    {
    	NetworkRegistry.INSTANCE.registerGuiHandler(RPGSystem.INSTANCE, new GuiHandler());
    	ConfigManager.sync(Constants.MODID, Type.INSTANCE);
    }
    
    public void postInit(FMLPostInitializationEvent event) {
        
    }
}
