package com.disastermoo.rpgsystem.core.config;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

import com.disastermoo.rpgsystem.core.ProxyCommon;

public class RPGData {
	public static final RPGData INSTANCE = new RPGData();
	
	private File rpgFile;
	private List<MobInfo> mobs;
	
	private RPGData()
	{
		mobs = null;
		rpgFile = null;
	}
	
	public void setFile(String path)
	{
		this.rpgFile = new File(path);
	}
	
	public boolean load()
	{
		try {
			@SuppressWarnings("resource")
			BufferedReader br = new BufferedReader(new FileReader(rpgFile)); 
			String st; 
			int ln = 1;
			List<MobInfo> mobs = new ArrayList<MobInfo>();
			while ((st = br.readLine()) != null) {
				while(st.startsWith(" "))st = st.substring(1);
				if(!st.equals("") && !st.startsWith("#")) {
					/* FORMAT = Class:Level:Category:Drops */
					String[] loadedMob = st.split(":");
					if(loadedMob.length != 4) throw new Exception("FORMAT ERROR: Could not load mob in line: " + ln);
					MobInfo mob = new MobInfo();
					mob.mobClass = loadedMob[0];
					mob.mobLevel = Integer.parseInt(loadedMob[1]);
					mob.mobCategory = Integer.parseInt(loadedMob[2]);
					if(loadedMob[3].equals("0")) {
						mob.mobDrops = null;
					}else {
						String[] loadedDrops = loadedMob[3].split(";");
						List<MobInfo.Drop> mobDrops = new ArrayList<MobInfo.Drop>();
						for(int i = 0; i < loadedDrops.length; i++)
						{
							String[] spt = loadedDrops[i].split("@");
							MobInfo.Drop mobDrop = new MobInfo.Drop();
							mobDrop.dropId = Integer.parseInt(spt[0]);
							mobDrop.chance = Float.parseFloat(spt[1]);
							mobDrops.add(mobDrop);
						}
						mob.mobDrops = mobDrops;
					}
					mobs.add(mob);
				}
				ln++;
			}
			this.mobs = mobs;
			br.close();
			return true;
		}catch (Exception e) {
			ProxyCommon.logger.error(e.getMessage());
		}
		return false;
	}
	
	public boolean save()
	{
		if(mobs == null)return false;
		try {
			String newline = System.getProperty("line.separator");
			rpgFile.delete();
			BufferedWriter writer = new BufferedWriter(new FileWriter(rpgFile));
			/* Write commented out for helping people */
			writer.write("# Use # for comments like this line" + newline);
			writer.write("# To manually add a monser to the list, use the following structure:"+ newline);
			writer.write("# Monster Class(Java file):Level:Category(0-NORMAL, 1-TANK, 2-SPEEDY, 3-BOSS):MateriaDrops(metadata@droprate;nextMeta@nextDrop...)"+ newline);
			writer.write("# Example: net.minecraft.entity.monster.EntityCreeper:50:2:1@0.05;7@0.03"+ newline);
			writer.write("# All detected spawns will be added automatically at level 1, category 0 and no drops"+ newline);
			for(MobInfo mob : mobs)
			{
				/* FORMAT = Class:Level:Category:Drops */
				String saveLine = mob.mobClass + ":" + mob.mobLevel + ":" + mob.mobCategory + ":";
				if(mob.mobDrops == null)
				{
					saveLine += "0";
				}else {
					String plusDrop = "";
					boolean first = true;
					for(MobInfo.Drop drop : mob.mobDrops) 
					{
						if(!first)plusDrop += ";";
						plusDrop += drop.dropId + "@" + String.valueOf(drop.chance);
						first = false;
					}
					saveLine += plusDrop;
				}
				writer.write(saveLine + newline);
			}
			writer.close();
			return true;
		}catch (Exception e) {
			ProxyCommon.logger.error(e.getMessage());
		}
		return false;
	}
	
	public MobInfo getMobInfo(String mobClass)
	{
		if(mobs == null)return null;
		for(MobInfo mob : mobs)
		{
			if(mob.mobClass.equalsIgnoreCase(mobClass))return mob;
		}
		return null;
	}
	
	public void addNewMob(String mobClass, int mobLevel, int mobCategory, List<MobInfo.Drop> mobDrops)
	{
		MobInfo newMob = new MobInfo();
		newMob.mobClass = mobClass;
		newMob.mobLevel = mobLevel;
		newMob.mobCategory = mobCategory;
		newMob.mobDrops = mobDrops;
		if(mobs == null)mobs = new ArrayList<MobInfo>();
		mobs.add(newMob);
	}
	
	public static class MobInfo
	{
		public String mobClass;
		public int mobLevel;
		public int mobCategory;
		public List<Drop> mobDrops;
		
		public static class Drop
		{
			public int dropId;
			public float chance;
		}
	}
}
