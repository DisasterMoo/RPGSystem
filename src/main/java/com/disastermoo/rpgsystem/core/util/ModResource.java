package com.disastermoo.rpgsystem.core.util;

import com.disastermoo.rpgsystem.core.config.Config.Constants;

import net.minecraft.util.ResourceLocation;

public class ModResource extends ResourceLocation {
	public ModResource(String path) 
	{
			super(Constants.MODID, path);
	}
}
