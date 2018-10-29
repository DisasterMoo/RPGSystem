package com.disastermoo.rpgsystem.core.config;

import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.Config.Comment;
import net.minecraftforge.common.config.Config.Ignore;
import net.minecraftforge.common.config.Config.Name;

@Config(modid = RPGConfig.Constants.MODID, name = "RPGSystem/config")
public class RPGConfig {
	
	public static abstract class Constants {
		/* MOD INFO */
		public static final String MODID = "rpgsystem";
	    public static final String NAME = "RPG System";
	    public static final String VERSION = "0.1";
	    
	    /* ASSETS */
	    public static final String ASSET_GUI_STATUS_LEFT_BACKGROUND = "textures/gui/statusleft.png";
	    public static final String ASSET_GUI_STATUS_RIGHT_BACKGROUND = "textures/gui/statusright.png";
	    /* PROFESSIONS */
	    public static final String ASSET_PROFESSION_NONE = "textures/gui/profession/none.png";
	    public static final String ASSET_PROFESSION_FIGHTER = "textures/gui/profession/fighter.png";
	    public static final String ASSET_PROFESSION_CHAMPION = "textures/gui/profession/fighter.png";
	    public static final String ASSET_PROFESSION_THIEF = "textures/gui/profession/fighter.png";
	    public static final String ASSET_PROFESSION_ASSASSIN = "textures/gui/profession/fighter.png";
	    public static final String ASSET_PROFESSION_MAGUS = "textures/gui/profession/fighter.png";
	    public static final String ASSET_PROFESSION_INVOKER = "textures/gui/profession/fighter.png";
	    public static final String ASSET_PROFESSION_ARCHER = "textures/gui/profession/fighter.png";
	    public static final String ASSET_PROFESSION_HUNTER = "textures/gui/profession/fighter.png";
	    public static final String ASSET_PROFESSION_BARBARIAN = "textures/gui/profession/fighter.png";
	    public static final String ASSET_PROFESSION_BERSERKER = "textures/gui/profession/fighter.png";
	    
	    /* IMAGE ASSETS SIZE */
	    public static final int SIZE_X_GUI_STATUS_BACKGROUND = 170;
	    public static final int SIZE_Y_GUI_STATUS_BACKGROUND = 234;
	    public static final int SIZE_X_PROFESSION = 65;
	    public static final int SIZE_Y_PROFESSION = 80;
	    
	    /* MATERIA */
	    public static final int NUMBER_OF_BOOST_MATERIA = 30;
	    public static final int NUMBER_OF_ENHANCEMENT_MATERIA = 18;
	    public static final int NUMBER_OF_SPECIAL_MATERIA = 9;
	    public static final int NUMBER_OF_SUPPORT_MATERIA = 3;
	    public static final int TOTAL_MATERIA = NUMBER_OF_BOOST_MATERIA + NUMBER_OF_ENHANCEMENT_MATERIA + NUMBER_OF_SPECIAL_MATERIA + NUMBER_OF_SUPPORT_MATERIA;
	}

	
	@Name("NewGame")
	@Comment("Defines if the game is a new game plus(all mobs will receive level boosts)")
	public static int gameplus = 0;

}
