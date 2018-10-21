package com.disastermoo.rpgsystem.core;

import com.disastermoo.rpgsystem.RPGSystem;
import com.disastermoo.rpgsystem.core.RPGConfig.Constants;
import com.disastermoo.rpgsystem.core.item.ItemBase;
import com.disastermoo.rpgsystem.core.item.Materia;
import com.disastermoo.rpgsystem.core.system.EntityHandler;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingSpawnEvent;
import net.minecraftforge.fml.client.event.ConfigChangedEvent.OnConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent.KeyInputEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.common.config.Config;

@Mod.EventBusSubscriber(modid = Constants.MODID)
public class EventHandler {
	
	private static EventHandler instance = null;
	
	public static EventHandler getInstance()
    {
		if(instance == null)instance = new EventHandler();
    	return instance;
    }
	
	@SubscribeEvent
    public void onConfigChangedEvent(OnConfigChangedEvent event)
    {
        if (event.getModID().equals(Constants.MODID))
        {
            ConfigManager.sync(Constants.MODID, Config.Type.INSTANCE);
        }
    }
	
	
	@SubscribeEvent
	public static void registerItems(RegistryEvent.Register<Item> event) {
		for(ItemBase it : RegistryHandler.INSTANCE.items.listAll)
		{
			event.getRegistry().register(it);
		}
	}
	
	/*
	public static void onAttack(LivingAttackEvent event)
	{
		net.minecraftforge.event.entity.living.LivingDamageEvent ev;
		ev.
	}*/
	
	
	@SideOnly(Side.SERVER)
	@SubscribeEvent(priority=EventPriority.LOW, receiveCanceled=false)
	public static void spawnCreature(LivingSpawnEvent event)
	{
		EntityHandler.onSpawn(event.getEntity());
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
	
	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public static void registerModels(ModelRegistryEvent event)
	{
		for(ItemBase it : RegistryHandler.INSTANCE.items.listAll)
		{
			it.initModel();
		}
	}
}
