package com.disastermoo.rpgsystem.core.item;

import com.disastermoo.rpgsystem.core.config.RPGConfig.Constants;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;

public class ItemBase extends Item implements IModeledItem{
		
	public ItemBase(String name, CreativeTabs addToTab){
		super();
		setUnlocalizedName(name);
		setRegistryName(Constants.MODID, name);
		if (addToTab != null){
			setCreativeTab(addToTab);
		}
	}
	
	@Override
	public void initModel(){
		ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation(getRegistryName().toString()));
	}
	
}
