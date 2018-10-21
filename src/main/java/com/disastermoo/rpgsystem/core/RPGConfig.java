package com.disastermoo.rpgsystem.core;

import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.Config.Comment;
import net.minecraftforge.common.config.Config.Ignore;
import net.minecraftforge.common.config.Config.Name;

@Config(modid = RPGConfig.Constants.MODID, name = "RPGSystem")
public class RPGConfig {
	
	public static abstract class Constants {
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

	
	@Name("Mobs")
	public static MobInfo mobinfo = new MobInfo();
			
	public static class MobInfo
	{
		@Name("class")
		@Comment({"Mob class names (as seen in mod java files).", "Example: 'net.minecraft.entity.passive.EntitySheep'"})
		public String[] mobClass = new String[] {"net.minecraft.entity.passive.EntitySheep"};
		
		@Name("level")
		@Comment({"Mob level(one line for each mob class)."})
		public int[] levelValue = new int[] {1};
		
		@Name("materiadrop")
		@Comment({"Materia drop and chance, one line for each mob class.", "Use '0' for no drop and ';' for adding more than one materia.", "Example: 3|0.4, for materia with metadata 3 and 40% droprate."})
		public String[] materiaDrop = new String[] {"1|0.05"};
		
		@Name("category")
		@Comment({"Mob category, one line for each mob class.", "Categories: 0-normal, 1-tanker, 2-fast, 3-boss."})
		public int[] category = new int[] {0};
		
		@Ignore
		public String currentClass;
		
		@Ignore
		public int currentLevel;
		
		@Ignore
		public int[] currentDrops;
		
		@Ignore
		public double[] currentChances;
		
		@Ignore
		public int currentCategory;
		
		public static final MobInfo getMobInfo(String mobClass)
		{
			MobInfo ret = new MobInfo();
			ret.currentClass = mobClass;
			for(int i = 0; i < RPGConfig.mobinfo.mobClass.length; i++)
			{
				if(ret.currentClass.equalsIgnoreCase(RPGConfig.mobinfo.mobClass[i])) {
					ret.currentLevel = RPGConfig.mobinfo.levelValue[i];
					ret.currentCategory = RPGConfig.mobinfo.category[i];
					String drop = RPGConfig.mobinfo.materiaDrop[i];
					if(drop.equals("0")) {
						ret.currentDrops = new int[] {0};
						ret.currentChances = new double[] {0};
					}else {
						try {
							String[] div = drop.split(";");
							ret.currentDrops = new int[div.length];
							ret.currentChances = new double[div.length];
							for(int b = 0; b < div.length; b++)
							{
								String[] div2 = div[b].split("|");
								ret.currentDrops[b] = Integer.parseInt(div2[0]);
								ret.currentChances[b] = Double.parseDouble(div2[1]);
							}
						}catch (NumberFormatException ex){
							/* Catch Error in config */
							ret.currentDrops = new int[] {0};
							ret.currentChances = new double[] {0};
						}
					}
					return ret;
				}
			}
			return null;
		}
		
	}

}
