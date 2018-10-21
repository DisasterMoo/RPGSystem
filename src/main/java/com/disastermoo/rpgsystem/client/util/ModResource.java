package com.disastermoo.rpgsystem.client.util;

import com.disastermoo.rpgsystem.core.RPGConfig.Constants;

import net.minecraft.util.ResourceLocation;

public class ModResource extends ResourceLocation {
	public ModResource(String path) 
	{
			super(Constants.MODID, path);
	}
}
