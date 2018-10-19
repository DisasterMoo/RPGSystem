package com.disastermoo.rpgsystem;


import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import com.disastermoo.rpgsystem.core.proxy.ProxyCommon;
import com.disastermoo.rpgsystem.core.config.Config.Constants;

@Mod(modid = Constants.MODID, name = Constants.NAME, version = Constants.VERSION)
public class RPGSystem
{
	@SidedProxy(clientSide = "com.disastermoo.rpgsystem.core.proxy.ProxyClient", serverSide = "com.disastermoo.rpgsystem.core.proxy.ProxyCommon")
	public static ProxyCommon proxy;
	
	@Instance
	public static RPGSystem INSTANCE = new RPGSystem();

    @EventHandler
    public void preInit(FMLPreInitializationEvent e)
    {
    	proxy.preInit(e);
    }

    @EventHandler
    public void init(FMLInitializationEvent e)
    {
    	proxy.init(e);
    }
    
    @EventHandler
    public void postInit(FMLPostInitializationEvent e) {
    	proxy.postInit(e);
    }
}
