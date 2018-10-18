package com.disastermoo.rpgsystem;

import net.minecraft.init.Blocks;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import java.io.File;

import org.apache.logging.log4j.Logger;

@Mod(modid = RPGSystem.MODID, name = RPGSystem.NAME, version = RPGSystem.VERSION)
public class RPGSystem
{
    public static final String MODID = "rpgsystem";
    public static final String NAME = "RPG System";
    public static final String VERSION = "0.1";

    public static Logger logger;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
    	/*File directory = event.getModConfigurationDirectory();
        config = new Configuration(new File(directory.getPath(), "rpgsystem.cfg"));
        Config.readConfig();*/
        logger = event.getModLog();
    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
    	logger.info("Creating skill list...");
        Initializer.initializeSkills();
        logger.info("Skill list created.");
        
    }
    
    public void postInit(FMLPostInitializationEvent e) {
        /*if (config.hasChanged()) {
            config.save();
        }*/
    }
}
