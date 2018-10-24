package com.disastermoo.rpgsystem.core;

import com.disastermoo.rpgsystem.RPGSystem;
import com.disastermoo.rpgsystem.core.capabilities.IRPGInfo;
import com.disastermoo.rpgsystem.core.capabilities.RPGInfoProvider;
import com.disastermoo.rpgsystem.core.config.RPGConfig.Constants;
import com.disastermoo.rpgsystem.core.item.ItemBase;
import com.disastermoo.rpgsystem.core.system.EntityHandler;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
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
	
	public static final ResourceLocation RPGINFO = new ResourceLocation(Constants.MODID, "RPGSystem");
	
	@SubscribeEvent
    public static void onConfigChangedEvent(OnConfigChangedEvent event)
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
	
	@SubscribeEvent
	public static void onPlayerClone(PlayerEvent.Clone event)
	{
		EntityPlayer player = event.getEntityPlayer();
		IRPGInfo newData = player.getCapability(RPGInfoProvider.RPGINFO_CAP, null);
		IRPGInfo oldData = event.getOriginal().getCapability(RPGInfoProvider.RPGINFO_CAP, null);
	
		newData.setInfo(oldData.getInfo());
	}
	
	@SubscribeEvent
    public static void attachCapability(AttachCapabilitiesEvent<Entity> event)
    {
        if (!(event.getObject() instanceof EntityLiving) && !(event.getObject() instanceof EntityPlayer)) return;
        event.addCapability(RPGINFO, new RPGInfoProvider());
    }
	
	@SubscribeEvent
	public static void onEntityDeath(LivingDeathEvent event){
		if(!event.getEntity().world.isRemote && !(event.getEntity() instanceof EntityPlayer))EntityHandler.onDeath(event.getEntity());
	}
	
	@SubscribeEvent
	public static void onEntityDrop(LivingDropsEvent event)
	{
		if(!event.getEntity().world.isRemote && !(event.getEntity() instanceof EntityPlayer))EntityHandler.onDrop(event);
	}
	
	@SubscribeEvent
	public static void onPlayerAttack(LivingHurtEvent event){
		if(!event.getEntity().world.isRemote)EntityHandler.onPlayerAttack(event);
	}
	
	@SubscribeEvent(priority=EventPriority.LOW, receiveCanceled=false)
	public static void spawnCreature(EntityJoinWorldEvent event)
	{
		if(!event.getEntity().world.isRemote)EntityHandler.onSpawn(event.getEntity());
	}
	
	@SideOnly(Side.CLIENT)
	@SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
	public static void keyInput(KeyInputEvent event)
	{
		KeyBinding[] keys = ((ProxyClient)RPGSystem.proxy).getKeyBiding();
	    if (keys[0].isPressed()) 
	    {
	    	Minecraft.getMinecraft().player.openGui(RPGSystem.INSTANCE, GuiHandler.GUI_STATUS, 
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
