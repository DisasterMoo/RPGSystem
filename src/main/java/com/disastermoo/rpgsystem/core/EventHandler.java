package com.disastermoo.rpgsystem.core;

import com.disastermoo.rpgsystem.RPGSystem;
import com.disastermoo.rpgsystem.core.config.Config.Constants;
import com.disastermoo.rpgsystem.core.proxy.ProxyClient;
import com.disastermoo.rpgsystem.core.proxy.ProxyCommon;
import com.disastermoo.rpgsystem.core.system.Materia;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.living.LivingSpawnEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent.KeyInputEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@Mod.EventBusSubscriber(modid = Constants.MODID)
public class EventHandler {
	
	private static EventHandler instance = null;
	
	public static EventHandler getInstance()
    {
		if(instance == null)instance = new EventHandler();
    	return instance;
    }
	
	
	@SubscribeEvent
	public static void registerItems(RegistryEvent.Register<Item> event) {
		ProxyCommon.logger.info("Registering materia...");
		for(int i = 1; i <= Constants.TOTAL_MATERIA; i++)
		{
			Item im = new Materia(i);
			event.getRegistry().register(im);
			break;
		}
		ProxyCommon.logger.info("All Materia registered.");
	}
	
	
	@SideOnly(Side.CLIENT)
	@SubscribeEvent(priority=EventPriority.LOW, receiveCanceled=false)
	public static void spawnCreature(LivingSpawnEvent event)
	{
		//System.out.println(event.getEntity().getEntityId());
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
	     for(int i = 1; i <= Constants.TOTAL_MATERIA; i++)
	     {
	    	 Item it = new Materia(i);
	    	 ModelLoader.setCustomModelResourceLocation(it, i, new ModelResourceLocation(it.getRegistryName(), "inventory")); 
	     }
	 }
}
