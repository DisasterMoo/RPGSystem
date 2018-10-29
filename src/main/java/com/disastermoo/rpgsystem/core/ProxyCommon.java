package com.disastermoo.rpgsystem.core;

import org.apache.logging.log4j.Logger;

import com.disastermoo.rpgsystem.RPGSystem;
import com.disastermoo.rpgsystem.core.config.RPGData;
import com.disastermoo.rpgsystem.core.capabilities.IRPGInfo;
import com.disastermoo.rpgsystem.core.capabilities.RPGInfoProvider;
import com.disastermoo.rpgsystem.core.capabilities.RPGInfoStorage;
import com.disastermoo.rpgsystem.core.capabilities.network.RPGInfoMessage;
import com.disastermoo.rpgsystem.core.capabilities.network.RPGUpgradeMessage;
import com.disastermoo.rpgsystem.core.config.RPGConfig.Constants;
import com.disastermoo.rpgsystem.core.util.RPGUtils;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraft.entity.EntityList;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.config.Config.Type;
import net.minecraftforge.common.config.ConfigManager;

public class ProxyCommon {
	
	public static final SimpleNetworkWrapper NETWORK_INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel(Constants.MODID);
	
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
    	CapabilityManager.INSTANCE.register(IRPGInfo.class, new RPGInfoStorage(), new RPGInfoProvider.Factory());
    	NETWORK_INSTANCE.registerMessage(RPGUpgradeMessage.RPGUpgradeMessageHandler.class, RPGUpgradeMessage.class, 1, Side.SERVER);
    	NETWORK_INSTANCE.registerMessage(RPGInfoMessage.RPGInfoMessageHandler.class, RPGInfoMessage.class, 0, Side.CLIENT);
    }
    
    public void postInit(FMLPostInitializationEvent event) {
    	RPGUtils.registerAllEntities();
    }
}
