package com.disastermoo.rpgsystem.core.config;

public class Config {
	public abstract class Constants {
		/* MOD INFO */
		public static final String MODID = "rpgsystem";
	    public static final String NAME = "RPG System";
	    public static final String VERSION = "0.1";
	    
	    /* ASSETS */
	    public static final String ASSET_MAIN_BACKGROUND = "textures/gui/mainbackground.png";
	    public static final String ASSET_LEFT_SIDE_BACKGROUND = "textures/gui/leftside.png";
	    public static final String ASSET_RIGHT_SIDE_BACKGROUND = "textures/gui/rightside.png";
	    public static final String ASSET_ICONMAP_CLASS = "textures/gui/classiconmap.png";
	    public static final String ASSET_ICONMAP_ATTRIBUTE = "textures/gui/attributeiconmap.png";
	    /* IMAGE ASSETS SIZE */
	    public static final int SIZE_X_MAIN_BACKGROUND = 169;
	    public static final int SIZE_Y_MAIN_BACKGROUND = 234;
	    
	    /* MATERIA */
	    public static final int NUMBER_OF_BOOST_MATERIA = 30;
	    public static final int NUMBER_OF_ENHANCEMENT_MATERIA = 18;
	    public static final int NUMBER_OF_SPECIAL_MATERIA = 9;
	    public static final int NUMBER_OF_SUPPORT_MATERIA = 3;
	    public static final int TOTAL_MATERIA = NUMBER_OF_BOOST_MATERIA + NUMBER_OF_ENHANCEMENT_MATERIA + NUMBER_OF_SPECIAL_MATERIA + NUMBER_OF_SUPPORT_MATERIA;
	}
}
