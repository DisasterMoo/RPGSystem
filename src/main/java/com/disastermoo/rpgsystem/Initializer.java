package com.disastermoo.rpgsystem;

import java.util.ArrayList;
import java.util.List;

import com.disastermoo.rpgsystem.player.Attribute;
import com.disastermoo.rpgsystem.player.skilltree.AttributeSkill;
import com.disastermoo.rpgsystem.player.skilltree.ClassSkill;
import com.disastermoo.rpgsystem.player.skilltree.Skill;

public abstract class Initializer {

	private static final String ICONSWORD = "SWORD";
	private static final String ICONDEFENDER = "DEFENDER";
	private static final String ICONMARKSMAN = "MARKSMAN";
	private static final String ICONCLERIC = "CLERIC";
	private static final String ICONMAGUS = "MAGUS";
	private static final String ICONSTR = "STR";
	private static final String ICONAGI = "AGI";
	private static final String ICONINT = "INT";
	private static final String ICONCON = "CON";
	private static final String ICONALL = "ALL";
	
	public static List<Skill> skillList;
	public static void initializeSkills()
	{
		/* ADD ROOT ALL SKILL */
		Skill rootSkill = new AttributeSkill(ICONALL, Attribute.Type.ALL);
		rootSkill.setPosition(0, 0);
		skillList.add(rootSkill);
		
		initializeSwordfighterPath(rootSkill);
		initializeDefenderPath(rootSkill);
		initializeMarksmanPath(rootSkill);
		initializeClericPath(rootSkill);
		initializeMagusPath(rootSkill);
	}
	
	@SuppressWarnings("unchecked")
	public static void initializeSwordfighterPath(Skill root)
	{
		ArrayList<Skill> auxList = new ArrayList<Skill>();
		Skill addSkill;
		
		auxList.add(root);
		addSkill = new ClassSkill(ICONSWORD, ClassSkill.Type.Swordfighter, 1).setRequirements((List<Skill>)auxList.clone());
		addSkill.setPosition(0, 0);
		auxList.clear();
		skillList.add(addSkill);
		
		auxList.add(addSkill);
		addSkill = new AttributeSkill(ICONSTR, Attribute.Type.STR).setRequirements((List<Skill>)auxList.clone());
		addSkill.setPosition(0, 0);
		auxList.clear();
		skillList.add(addSkill);
		
		auxList.add(addSkill);
		addSkill = new AttributeSkill(ICONCON, Attribute.Type.CON).setRequirements((List<Skill>)auxList.clone());
		addSkill.setPosition(0, 0);
		auxList.clear();
		skillList.add(addSkill);
		
		auxList.add(addSkill);
		addSkill = new ClassSkill(ICONSWORD, ClassSkill.Type.Swordfighter, 2).setRequirements((List<Skill>)auxList.clone());
		addSkill.setPosition(0, 0);
		auxList.clear();
		skillList.add(addSkill);
		
		auxList.add(addSkill);
		addSkill = new AttributeSkill(ICONAGI, Attribute.Type.AGI).setRequirements((List<Skill>)auxList.clone());
		addSkill.setPosition(0, 0);
		auxList.clear();
		skillList.add(addSkill);
		
		auxList.add(addSkill);
		addSkill = new AttributeSkill(ICONCON, Attribute.Type.CON).setRequirements((List<Skill>)auxList.clone());
		addSkill.setPosition(0, 0);
		auxList.clear();
		skillList.add(addSkill);
		
		auxList.add(addSkill);
		addSkill = new ClassSkill(ICONSWORD, ClassSkill.Type.Swordfighter, 3).setRequirements((List<Skill>)auxList.clone());
		addSkill.setPosition(0, 0);
		auxList.clear();
		skillList.add(addSkill);
		
		auxList.add(addSkill);
		addSkill = new AttributeSkill(ICONCON, Attribute.Type.CON).setRequirements((List<Skill>)auxList.clone());
		addSkill.setPosition(0, 0);
		auxList.clear();
		skillList.add(addSkill);
		
		auxList.add(addSkill);
		addSkill = new AttributeSkill(ICONSTR, Attribute.Type.STR).setRequirements((List<Skill>)auxList.clone());
		addSkill.setPosition(0, 0);
		auxList.clear();
		skillList.add(addSkill);
		
		auxList.add(addSkill);
		addSkill = new ClassSkill(ICONSWORD, ClassSkill.Type.Swordfighter, 4).setRequirements((List<Skill>)auxList.clone());
		addSkill.setPosition(0, 0);
		auxList.clear();
		skillList.add(addSkill);
		
		auxList.add(addSkill);
		addSkill = new AttributeSkill(ICONSTR, Attribute.Type.STR).setRequirements((List<Skill>)auxList.clone());
		addSkill.setPosition(1, 1);
		auxList.clear();
		skillList.add(addSkill);
		
		auxList.add(addSkill);
		addSkill = new AttributeSkill(ICONAGI, Attribute.Type.AGI).setRequirements((List<Skill>)auxList.clone());
		addSkill.setPosition(0, 0);
		auxList.clear();
		skillList.add(addSkill);
		
		auxList.add(addSkill);
		addSkill = new ClassSkill(ICONSWORD, ClassSkill.Type.Swordfighter, 5).setRequirements((List<Skill>)auxList.clone());
		addSkill.setPosition(0, 0);
		auxList.clear();
		skillList.add(addSkill);
		
		auxList.add(addSkill);
		addSkill = new AttributeSkill(ICONALL, Attribute.Type.ALL).setRequirements((List<Skill>)auxList.clone());
		addSkill.setPosition(0, 0);
		auxList.clear();
		skillList.add(addSkill);
	}
	
	@SuppressWarnings("unchecked")
	public static void initializeDefenderPath(Skill root)
	{
		ArrayList<Skill> auxList = new ArrayList<Skill>();
		Skill addSkill;
		
		
		auxList.add(root);
		addSkill = new ClassSkill(ICONDEFENDER, ClassSkill.Type.Defender, 1).setRequirements((List<Skill>)auxList.clone());
		addSkill.setPosition(0, 0);
		auxList.clear();
		skillList.add(addSkill);
		
		auxList.add(addSkill);
		addSkill = new AttributeSkill(ICONCON, Attribute.Type.CON).setRequirements((List<Skill>)auxList.clone());
		addSkill.setPosition(0, 0);
		auxList.clear();
		skillList.add(addSkill);
		
		auxList.add(addSkill);
		addSkill = new AttributeSkill(ICONSTR, Attribute.Type.STR).setRequirements((List<Skill>)auxList.clone());
		addSkill.setPosition(0, 0);
		auxList.clear();
		skillList.add(addSkill);
		
		auxList.add(addSkill);
		addSkill = new ClassSkill(ICONDEFENDER, ClassSkill.Type.Defender, 2).setRequirements((List<Skill>)auxList.clone());
		addSkill.setPosition(0, 0);
		auxList.clear();
		skillList.add(addSkill);
		
		auxList.add(addSkill);
		addSkill = new AttributeSkill(ICONCON, Attribute.Type.CON).setRequirements((List<Skill>)auxList.clone());
		addSkill.setPosition(0, 0);
		auxList.clear();
		skillList.add(addSkill);
		
		auxList.add(addSkill);
		addSkill = new AttributeSkill(ICONCON, Attribute.Type.CON).setRequirements((List<Skill>)auxList.clone());
		addSkill.setPosition(0, 0);
		auxList.clear();
		skillList.add(addSkill);
		
		auxList.add(addSkill);
		addSkill = new ClassSkill(ICONDEFENDER, ClassSkill.Type.Defender, 3).setRequirements((List<Skill>)auxList.clone());
		addSkill.setPosition(0, 0);
		auxList.clear();
		skillList.add(addSkill);
		
		auxList.add(addSkill);
		addSkill = new AttributeSkill(ICONINT, Attribute.Type.INT).setRequirements((List<Skill>)auxList.clone());
		addSkill.setPosition(0, 0);
		auxList.clear();
		skillList.add(addSkill);
		
		auxList.add(addSkill);
		addSkill = new AttributeSkill(ICONCON, Attribute.Type.CON).setRequirements((List<Skill>)auxList.clone());
		addSkill.setPosition(0, 0);
		auxList.clear();
		skillList.add(addSkill);
		
		auxList.add(addSkill);
		addSkill = new ClassSkill(ICONDEFENDER, ClassSkill.Type.Defender, 4).setRequirements((List<Skill>)auxList.clone());
		addSkill.setPosition(0, 0);
		auxList.clear();
		skillList.add(addSkill);
		
		auxList.add(addSkill);
		addSkill = new AttributeSkill(ICONSTR, Attribute.Type.STR).setRequirements((List<Skill>)auxList.clone());
		addSkill.setPosition(1, 1);
		auxList.clear();
		skillList.add(addSkill);
		
		auxList.add(addSkill);
		addSkill = new AttributeSkill(ICONCON, Attribute.Type.CON).setRequirements((List<Skill>)auxList.clone());
		addSkill.setPosition(0, 0);
		auxList.clear();
		skillList.add(addSkill);
		
		auxList.add(addSkill);
		addSkill = new ClassSkill(ICONDEFENDER, ClassSkill.Type.Defender, 5).setRequirements((List<Skill>)auxList.clone());
		addSkill.setPosition(0, 0);
		auxList.clear();
		skillList.add(addSkill);
		
		auxList.add(addSkill);
		addSkill = new AttributeSkill(ICONALL, Attribute.Type.ALL).setRequirements((List<Skill>)auxList.clone());
		addSkill.setPosition(0, 0);
		auxList.clear();
		skillList.add(addSkill);
	}
	
	@SuppressWarnings("unchecked")
	public static void initializeMarksmanPath(Skill root)
	{
		ArrayList<Skill> auxList = new ArrayList<Skill>();
		Skill addSkill;
		
		
		auxList.add(root);
		addSkill = new ClassSkill(ICONMARKSMAN, ClassSkill.Type.Marksman, 1).setRequirements((List<Skill>)auxList.clone());
		addSkill.setPosition(0, 0);
		auxList.clear();
		skillList.add(addSkill);
		
		auxList.add(addSkill);
		addSkill = new AttributeSkill(ICONAGI, Attribute.Type.AGI).setRequirements((List<Skill>)auxList.clone());
		addSkill.setPosition(0, 0);
		auxList.clear();
		skillList.add(addSkill);
		
		auxList.add(addSkill);
		addSkill = new AttributeSkill(ICONAGI, Attribute.Type.AGI).setRequirements((List<Skill>)auxList.clone());
		addSkill.setPosition(0, 0);
		auxList.clear();
		skillList.add(addSkill);
		
		auxList.add(addSkill);
		addSkill = new ClassSkill(ICONMARKSMAN, ClassSkill.Type.Marksman, 2).setRequirements((List<Skill>)auxList.clone());
		addSkill.setPosition(0, 0);
		auxList.clear();
		skillList.add(addSkill);
		
		auxList.add(addSkill);
		addSkill = new AttributeSkill(ICONAGI, Attribute.Type.AGI).setRequirements((List<Skill>)auxList.clone());
		addSkill.setPosition(0, 0);
		auxList.clear();
		skillList.add(addSkill);
		
		auxList.add(addSkill);
		addSkill = new AttributeSkill(ICONCON, Attribute.Type.CON).setRequirements((List<Skill>)auxList.clone());
		addSkill.setPosition(0, 0);
		auxList.clear();
		skillList.add(addSkill);
		
		auxList.add(addSkill);
		addSkill = new ClassSkill(ICONMARKSMAN, ClassSkill.Type.Marksman, 3).setRequirements((List<Skill>)auxList.clone());
		addSkill.setPosition(0, 0);
		auxList.clear();
		skillList.add(addSkill);
		
		auxList.add(addSkill);
		addSkill = new AttributeSkill(ICONAGI, Attribute.Type.AGI).setRequirements((List<Skill>)auxList.clone());
		addSkill.setPosition(0, 0);
		auxList.clear();
		skillList.add(addSkill);
		
		auxList.add(addSkill);
		addSkill = new AttributeSkill(ICONCON, Attribute.Type.CON).setRequirements((List<Skill>)auxList.clone());
		addSkill.setPosition(0, 0);
		auxList.clear();
		skillList.add(addSkill);
		
		auxList.add(addSkill);
		addSkill = new ClassSkill(ICONMARKSMAN, ClassSkill.Type.Marksman, 4).setRequirements((List<Skill>)auxList.clone());
		addSkill.setPosition(0, 0);
		auxList.clear();
		skillList.add(addSkill);
		
		auxList.add(addSkill);
		addSkill = new AttributeSkill(ICONINT, Attribute.Type.INT).setRequirements((List<Skill>)auxList.clone());
		addSkill.setPosition(1, 1);
		auxList.clear();
		skillList.add(addSkill);
		
		auxList.add(addSkill);
		addSkill = new AttributeSkill(ICONAGI, Attribute.Type.AGI).setRequirements((List<Skill>)auxList.clone());
		addSkill.setPosition(0, 0);
		auxList.clear();
		skillList.add(addSkill);
		
		auxList.add(addSkill);
		addSkill = new ClassSkill(ICONMARKSMAN, ClassSkill.Type.Marksman, 5).setRequirements((List<Skill>)auxList.clone());
		addSkill.setPosition(0, 0);
		auxList.clear();
		skillList.add(addSkill);
		
		auxList.add(addSkill);
		addSkill = new AttributeSkill(ICONALL, Attribute.Type.ALL).setRequirements((List<Skill>)auxList.clone());
		addSkill.setPosition(0, 0);
		auxList.clear();
		skillList.add(addSkill);
	}
	
	@SuppressWarnings("unchecked")
	public static void initializeClericPath(Skill root)
	{
		ArrayList<Skill> auxList = new ArrayList<Skill>();
		Skill addSkill;
		
		
		auxList.add(root);
		addSkill = new ClassSkill(ICONCLERIC, ClassSkill.Type.Cleric, 1).setRequirements((List<Skill>)auxList.clone());
		addSkill.setPosition(0, 0);
		auxList.clear();
		skillList.add(addSkill);
		
		auxList.add(addSkill);
		addSkill = new AttributeSkill(ICONINT, Attribute.Type.INT).setRequirements((List<Skill>)auxList.clone());
		addSkill.setPosition(0, 0);
		auxList.clear();
		skillList.add(addSkill);
		
		auxList.add(addSkill);
		addSkill = new AttributeSkill(ICONCON, Attribute.Type.CON).setRequirements((List<Skill>)auxList.clone());
		addSkill.setPosition(0, 0);
		auxList.clear();
		skillList.add(addSkill);
		
		auxList.add(addSkill);
		addSkill = new ClassSkill(ICONCLERIC, ClassSkill.Type.Cleric, 2).setRequirements((List<Skill>)auxList.clone());
		addSkill.setPosition(0, 0);
		auxList.clear();
		skillList.add(addSkill);
		
		auxList.add(addSkill);
		addSkill = new AttributeSkill(ICONINT, Attribute.Type.INT).setRequirements((List<Skill>)auxList.clone());
		addSkill.setPosition(0, 0);
		auxList.clear();
		skillList.add(addSkill);
		
		auxList.add(addSkill);
		addSkill = new AttributeSkill(ICONCON, Attribute.Type.CON).setRequirements((List<Skill>)auxList.clone());
		addSkill.setPosition(0, 0);
		auxList.clear();
		skillList.add(addSkill);
		
		auxList.add(addSkill);
		addSkill = new ClassSkill(ICONCLERIC, ClassSkill.Type.Cleric, 3).setRequirements((List<Skill>)auxList.clone());
		addSkill.setPosition(0, 0);
		auxList.clear();
		skillList.add(addSkill);
		
		auxList.add(addSkill);
		addSkill = new AttributeSkill(ICONINT, Attribute.Type.INT).setRequirements((List<Skill>)auxList.clone());
		addSkill.setPosition(0, 0);
		auxList.clear();
		skillList.add(addSkill);
		
		auxList.add(addSkill);
		addSkill = new AttributeSkill(ICONSTR, Attribute.Type.STR).setRequirements((List<Skill>)auxList.clone());
		addSkill.setPosition(0, 0);
		auxList.clear();
		skillList.add(addSkill);
		
		auxList.add(addSkill);
		addSkill = new ClassSkill(ICONCLERIC, ClassSkill.Type.Cleric, 4).setRequirements((List<Skill>)auxList.clone());
		addSkill.setPosition(0, 0);
		auxList.clear();
		skillList.add(addSkill);
		
		auxList.add(addSkill);
		addSkill = new AttributeSkill(ICONSTR, Attribute.Type.STR).setRequirements((List<Skill>)auxList.clone());
		addSkill.setPosition(1, 1);
		auxList.clear();
		skillList.add(addSkill);
		
		auxList.add(addSkill);
		addSkill = new AttributeSkill(ICONINT, Attribute.Type.INT).setRequirements((List<Skill>)auxList.clone());
		addSkill.setPosition(0, 0);
		auxList.clear();
		skillList.add(addSkill);
		
		auxList.add(addSkill);
		addSkill = new ClassSkill(ICONCLERIC, ClassSkill.Type.Cleric, 5).setRequirements((List<Skill>)auxList.clone());
		addSkill.setPosition(0, 0);
		auxList.clear();
		skillList.add(addSkill);
		
		auxList.add(addSkill);
		addSkill = new AttributeSkill(ICONALL, Attribute.Type.ALL).setRequirements((List<Skill>)auxList.clone());
		addSkill.setPosition(0, 0);
		auxList.clear();
		skillList.add(addSkill);
	}
	
	@SuppressWarnings("unchecked")
	public static void initializeMagusPath(Skill root)
	{
		ArrayList<Skill> auxList = new ArrayList<Skill>();
		Skill addSkill;
		
		auxList.add(root);
		addSkill = new ClassSkill(ICONMAGUS, ClassSkill.Type.Magus, 1).setRequirements((List<Skill>)auxList.clone());
		addSkill.setPosition(0, 0);
		auxList.clear();
		skillList.add(addSkill);
		
		auxList.add(addSkill);
		addSkill = new AttributeSkill(ICONINT, Attribute.Type.INT).setRequirements((List<Skill>)auxList.clone());
		addSkill.setPosition(0, 0);
		auxList.clear();
		skillList.add(addSkill);
		
		auxList.add(addSkill);
		addSkill = new AttributeSkill(ICONCON, Attribute.Type.AGI).setRequirements((List<Skill>)auxList.clone());
		addSkill.setPosition(0, 0);
		auxList.clear();
		skillList.add(addSkill);
		
		auxList.add(addSkill);
		addSkill = new ClassSkill(ICONMAGUS, ClassSkill.Type.Magus, 2).setRequirements((List<Skill>)auxList.clone());
		addSkill.setPosition(0, 0);
		auxList.clear();
		skillList.add(addSkill);
		
		auxList.add(addSkill);
		addSkill = new AttributeSkill(ICONINT, Attribute.Type.INT).setRequirements((List<Skill>)auxList.clone());
		addSkill.setPosition(0, 0);
		auxList.clear();
		skillList.add(addSkill);
		
		auxList.add(addSkill);
		addSkill = new AttributeSkill(ICONCON, Attribute.Type.INT).setRequirements((List<Skill>)auxList.clone());
		addSkill.setPosition(0, 0);
		auxList.clear();
		skillList.add(addSkill);
		
		auxList.add(addSkill);
		addSkill = new ClassSkill(ICONMAGUS, ClassSkill.Type.Magus, 3).setRequirements((List<Skill>)auxList.clone());
		addSkill.setPosition(0, 0);
		auxList.clear();
		skillList.add(addSkill);
		
		auxList.add(addSkill);
		addSkill = new AttributeSkill(ICONINT, Attribute.Type.INT).setRequirements((List<Skill>)auxList.clone());
		addSkill.setPosition(0, 0);
		auxList.clear();
		skillList.add(addSkill);
		
		auxList.add(addSkill);
		addSkill = new AttributeSkill(ICONSTR, Attribute.Type.CON).setRequirements((List<Skill>)auxList.clone());
		addSkill.setPosition(0, 0);
		auxList.clear();
		skillList.add(addSkill);
		
		auxList.add(addSkill);
		addSkill = new ClassSkill(ICONMAGUS, ClassSkill.Type.Magus, 4).setRequirements((List<Skill>)auxList.clone());
		addSkill.setPosition(0, 0);
		auxList.clear();
		skillList.add(addSkill);
		
		auxList.add(addSkill);
		addSkill = new AttributeSkill(ICONSTR, Attribute.Type.AGI).setRequirements((List<Skill>)auxList.clone());
		addSkill.setPosition(1, 1);
		auxList.clear();
		skillList.add(addSkill);
		
		auxList.add(addSkill);
		addSkill = new AttributeSkill(ICONINT, Attribute.Type.INT).setRequirements((List<Skill>)auxList.clone());
		addSkill.setPosition(0, 0);
		auxList.clear();
		skillList.add(addSkill);
		
		auxList.add(addSkill);
		addSkill = new ClassSkill(ICONMAGUS, ClassSkill.Type.Magus, 5).setRequirements((List<Skill>)auxList.clone());
		addSkill.setPosition(0, 0);
		auxList.clear();
		skillList.add(addSkill);
		
		auxList.add(addSkill);
		addSkill = new AttributeSkill(ICONALL, Attribute.Type.ALL).setRequirements((List<Skill>)auxList.clone());
		addSkill.setPosition(0, 0);
		auxList.clear();
		skillList.add(addSkill);
	}
}
