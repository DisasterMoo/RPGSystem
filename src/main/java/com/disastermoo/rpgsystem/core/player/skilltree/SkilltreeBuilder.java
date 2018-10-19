package com.disastermoo.rpgsystem.core.player.skilltree;

import java.util.ArrayList;
import java.util.List;

import com.disastermoo.rpgsystem.core.player.Attribute;

public abstract class SkilltreeBuilder {
	/* Individual Skill Trees */
	public static List<Skill> swordfighterSkillTree, defenderSkilltree, marksmanSkilltree, clericSkilltree, magusSkilltree;
	
	/* Linker(MAIN) Skill Tree */
	public static List<Skill> linkerSkilltree;
	
	public static List<List<Skill>> orderList;
	
	public static void initializeSkills()
	{
		/* Initialize all arraylists */
		linkerSkilltree = new ArrayList<Skill>();
		swordfighterSkillTree = new ArrayList<Skill>();
		defenderSkilltree = new ArrayList<Skill>();
		marksmanSkilltree = new ArrayList<Skill>();
		clericSkilltree = new ArrayList<Skill>();
		magusSkilltree = new ArrayList<Skill>();
		
		/* Create order list (starting from top clock-wise)*/
		orderList = new ArrayList<List<Skill>>();
		orderList.add(swordfighterSkillTree);
		orderList.add(defenderSkilltree);
		orderList.add(clericSkilltree);
		orderList.add(magusSkilltree);
		orderList.add(marksmanSkilltree);
		
		/* ADD ROOT (ALL) SKILL */
		Skill rootSkill = new AttributeSkill(Attribute.Type.ALL);
		linkerSkilltree.add(rootSkill);
		
		/* Build all individual skill trees (order by clock-wise)*/
		initializeSwordfighterPath(rootSkill);
		initializeDefenderPath(rootSkill);
		initializeClericPath(rootSkill);
		initializeMagusPath(rootSkill);
		initializeMarksmanPath(rootSkill);
		/* TODO Create skills linking paths (clock-wise) */
	}
	
	@SuppressWarnings("unchecked")
	public static void initializeSwordfighterPath(Skill root)
	{
		ArrayList<Skill> auxList = new ArrayList<Skill>();
		Skill addSkill;
		
		auxList.add(root);
		addSkill = new ClassSkill(ClassSkill.Type.Swordfighter, 1).setRequirements((List<Skill>)auxList.clone());
		auxList.clear();
		swordfighterSkillTree.add(addSkill);
		
		auxList.add(addSkill);
		addSkill = new AttributeSkill(Attribute.Type.STR).setRequirements((List<Skill>)auxList.clone());
		auxList.clear();
		swordfighterSkillTree.add(addSkill);
		
		auxList.add(addSkill);
		addSkill = new AttributeSkill(Attribute.Type.CON).setRequirements((List<Skill>)auxList.clone());
		auxList.clear();
		swordfighterSkillTree.add(addSkill);
		
		auxList.add(addSkill);
		addSkill = new ClassSkill(ClassSkill.Type.Swordfighter, 2).setRequirements((List<Skill>)auxList.clone());
		auxList.clear();
		swordfighterSkillTree.add(addSkill);
		
		auxList.add(addSkill);
		addSkill = new AttributeSkill(Attribute.Type.AGI).setRequirements((List<Skill>)auxList.clone());
		auxList.clear();
		swordfighterSkillTree.add(addSkill);
		
		auxList.add(addSkill);
		addSkill = new AttributeSkill(Attribute.Type.CON).setRequirements((List<Skill>)auxList.clone());
		auxList.clear();
		swordfighterSkillTree.add(addSkill);
		
		auxList.add(addSkill);
		addSkill = new ClassSkill(ClassSkill.Type.Swordfighter, 3).setRequirements((List<Skill>)auxList.clone());
		auxList.clear();
		swordfighterSkillTree.add(addSkill);
		
		auxList.add(addSkill);
		addSkill = new AttributeSkill(Attribute.Type.CON).setRequirements((List<Skill>)auxList.clone());
		auxList.clear();
		swordfighterSkillTree.add(addSkill);
		
		auxList.add(addSkill);
		addSkill = new AttributeSkill(Attribute.Type.STR).setRequirements((List<Skill>)auxList.clone());
		auxList.clear();
		swordfighterSkillTree.add(addSkill);
		
		auxList.add(addSkill);
		addSkill = new ClassSkill(ClassSkill.Type.Swordfighter, 4).setRequirements((List<Skill>)auxList.clone());
		auxList.clear();
		swordfighterSkillTree.add(addSkill);
		
		auxList.add(addSkill);
		addSkill = new AttributeSkill(Attribute.Type.STR).setRequirements((List<Skill>)auxList.clone());
		auxList.clear();
		swordfighterSkillTree.add(addSkill);
		
		auxList.add(addSkill);
		addSkill = new AttributeSkill(Attribute.Type.AGI).setRequirements((List<Skill>)auxList.clone());
		auxList.clear();
		swordfighterSkillTree.add(addSkill);
		
		auxList.add(addSkill);
		addSkill = new ClassSkill(ClassSkill.Type.Swordfighter, 5).setRequirements((List<Skill>)auxList.clone());
		auxList.clear();
		swordfighterSkillTree.add(addSkill);
		
		auxList.add(addSkill);
		addSkill = new AttributeSkill(Attribute.Type.ALL).setRequirements((List<Skill>)auxList.clone());
		auxList.clear();
		swordfighterSkillTree.add(addSkill);
	}
	
	@SuppressWarnings("unchecked")
	public static void initializeDefenderPath(Skill root)
	{
		ArrayList<Skill> auxList = new ArrayList<Skill>();
		Skill addSkill;
		
		
		auxList.add(root);
		addSkill = new ClassSkill(ClassSkill.Type.Defender, 1).setRequirements((List<Skill>)auxList.clone());
		auxList.clear();
		defenderSkilltree.add(addSkill);
		
		auxList.add(addSkill);
		addSkill = new AttributeSkill(Attribute.Type.CON).setRequirements((List<Skill>)auxList.clone());
		auxList.clear();
		defenderSkilltree.add(addSkill);
		
		auxList.add(addSkill);
		addSkill = new AttributeSkill(Attribute.Type.STR).setRequirements((List<Skill>)auxList.clone());
		auxList.clear();
		defenderSkilltree.add(addSkill);
		
		auxList.add(addSkill);
		addSkill = new ClassSkill(ClassSkill.Type.Defender, 2).setRequirements((List<Skill>)auxList.clone());
		auxList.clear();
		defenderSkilltree.add(addSkill);
		
		auxList.add(addSkill);
		addSkill = new AttributeSkill(Attribute.Type.CON).setRequirements((List<Skill>)auxList.clone());
		
		auxList.clear();
		defenderSkilltree.add(addSkill);
		
		auxList.add(addSkill);
		addSkill = new AttributeSkill(Attribute.Type.CON).setRequirements((List<Skill>)auxList.clone());
		
		auxList.clear();
		defenderSkilltree.add(addSkill);
		
		auxList.add(addSkill);
		addSkill = new ClassSkill(ClassSkill.Type.Defender, 3).setRequirements((List<Skill>)auxList.clone());
		
		auxList.clear();
		defenderSkilltree.add(addSkill);
		
		auxList.add(addSkill);
		addSkill = new AttributeSkill(Attribute.Type.INT).setRequirements((List<Skill>)auxList.clone());
		
		auxList.clear();
		defenderSkilltree.add(addSkill);
		
		auxList.add(addSkill);
		addSkill = new AttributeSkill(Attribute.Type.CON).setRequirements((List<Skill>)auxList.clone());
		
		auxList.clear();
		defenderSkilltree.add(addSkill);
		
		auxList.add(addSkill);
		addSkill = new ClassSkill(ClassSkill.Type.Defender, 4).setRequirements((List<Skill>)auxList.clone());
		
		auxList.clear();
		defenderSkilltree.add(addSkill);
		
		auxList.add(addSkill);
		addSkill = new AttributeSkill(Attribute.Type.STR).setRequirements((List<Skill>)auxList.clone());
		auxList.clear();
		defenderSkilltree.add(addSkill);
		
		auxList.add(addSkill);
		addSkill = new AttributeSkill(Attribute.Type.CON).setRequirements((List<Skill>)auxList.clone());
		
		auxList.clear();
		defenderSkilltree.add(addSkill);
		
		auxList.add(addSkill);
		addSkill = new ClassSkill(ClassSkill.Type.Defender, 5).setRequirements((List<Skill>)auxList.clone());
		
		auxList.clear();
		defenderSkilltree.add(addSkill);
		
		auxList.add(addSkill);
		addSkill = new AttributeSkill(Attribute.Type.ALL).setRequirements((List<Skill>)auxList.clone());
		
		auxList.clear();
		defenderSkilltree.add(addSkill);
	}
	
	@SuppressWarnings("unchecked")
	public static void initializeMarksmanPath(Skill root)
	{
		ArrayList<Skill> auxList = new ArrayList<Skill>();
		Skill addSkill;
		
		
		auxList.add(root);
		addSkill = new ClassSkill(ClassSkill.Type.Marksman, 1).setRequirements((List<Skill>)auxList.clone());
		
		auxList.clear();
		marksmanSkilltree.add(addSkill);
		
		auxList.add(addSkill);
		addSkill = new AttributeSkill(Attribute.Type.AGI).setRequirements((List<Skill>)auxList.clone());
		
		auxList.clear();
		marksmanSkilltree.add(addSkill);
		
		auxList.add(addSkill);
		addSkill = new AttributeSkill(Attribute.Type.AGI).setRequirements((List<Skill>)auxList.clone());
		
		auxList.clear();
		marksmanSkilltree.add(addSkill);
		
		auxList.add(addSkill);
		addSkill = new ClassSkill(ClassSkill.Type.Marksman, 2).setRequirements((List<Skill>)auxList.clone());
		
		auxList.clear();
		marksmanSkilltree.add(addSkill);
		
		auxList.add(addSkill);
		addSkill = new AttributeSkill(Attribute.Type.AGI).setRequirements((List<Skill>)auxList.clone());
		
		auxList.clear();
		marksmanSkilltree.add(addSkill);
		
		auxList.add(addSkill);
		addSkill = new AttributeSkill(Attribute.Type.CON).setRequirements((List<Skill>)auxList.clone());
		
		auxList.clear();
		marksmanSkilltree.add(addSkill);
		
		auxList.add(addSkill);
		addSkill = new ClassSkill(ClassSkill.Type.Marksman, 3).setRequirements((List<Skill>)auxList.clone());
		
		auxList.clear();
		marksmanSkilltree.add(addSkill);
		
		auxList.add(addSkill);
		addSkill = new AttributeSkill(Attribute.Type.AGI).setRequirements((List<Skill>)auxList.clone());
		
		auxList.clear();
		marksmanSkilltree.add(addSkill);
		
		auxList.add(addSkill);
		addSkill = new AttributeSkill(Attribute.Type.CON).setRequirements((List<Skill>)auxList.clone());
		
		auxList.clear();
		marksmanSkilltree.add(addSkill);
		
		auxList.add(addSkill);
		addSkill = new ClassSkill(ClassSkill.Type.Marksman, 4).setRequirements((List<Skill>)auxList.clone());
		
		auxList.clear();
		marksmanSkilltree.add(addSkill);
		
		auxList.add(addSkill);
		addSkill = new AttributeSkill(Attribute.Type.INT).setRequirements((List<Skill>)auxList.clone());
		auxList.clear();
		marksmanSkilltree.add(addSkill);
		
		auxList.add(addSkill);
		addSkill = new AttributeSkill(Attribute.Type.AGI).setRequirements((List<Skill>)auxList.clone());
		
		auxList.clear();
		marksmanSkilltree.add(addSkill);
		
		auxList.add(addSkill);
		addSkill = new ClassSkill(ClassSkill.Type.Marksman, 5).setRequirements((List<Skill>)auxList.clone());
		
		auxList.clear();
		marksmanSkilltree.add(addSkill);
		
		auxList.add(addSkill);
		addSkill = new AttributeSkill(Attribute.Type.ALL).setRequirements((List<Skill>)auxList.clone());
		
		auxList.clear();
		marksmanSkilltree.add(addSkill);
	}
	
	@SuppressWarnings("unchecked")
	public static void initializeClericPath(Skill root)
	{
		ArrayList<Skill> auxList = new ArrayList<Skill>();
		Skill addSkill;
		
		
		auxList.add(root);
		addSkill = new ClassSkill(ClassSkill.Type.Cleric, 1).setRequirements((List<Skill>)auxList.clone());
		
		auxList.clear();
		clericSkilltree.add(addSkill);
		
		auxList.add(addSkill);
		addSkill = new AttributeSkill(Attribute.Type.INT).setRequirements((List<Skill>)auxList.clone());
		
		auxList.clear();
		clericSkilltree.add(addSkill);
		
		auxList.add(addSkill);
		addSkill = new AttributeSkill(Attribute.Type.CON).setRequirements((List<Skill>)auxList.clone());
		
		auxList.clear();
		clericSkilltree.add(addSkill);
		
		auxList.add(addSkill);
		addSkill = new ClassSkill(ClassSkill.Type.Cleric, 2).setRequirements((List<Skill>)auxList.clone());
		
		auxList.clear();
		clericSkilltree.add(addSkill);
		
		auxList.add(addSkill);
		addSkill = new AttributeSkill(Attribute.Type.INT).setRequirements((List<Skill>)auxList.clone());
		
		auxList.clear();
		clericSkilltree.add(addSkill);
		
		auxList.add(addSkill);
		addSkill = new AttributeSkill(Attribute.Type.CON).setRequirements((List<Skill>)auxList.clone());
		
		auxList.clear();
		clericSkilltree.add(addSkill);
		
		auxList.add(addSkill);
		addSkill = new ClassSkill(ClassSkill.Type.Cleric, 3).setRequirements((List<Skill>)auxList.clone());
		
		auxList.clear();
		clericSkilltree.add(addSkill);
		
		auxList.add(addSkill);
		addSkill = new AttributeSkill(Attribute.Type.INT).setRequirements((List<Skill>)auxList.clone());
		
		auxList.clear();
		clericSkilltree.add(addSkill);
		
		auxList.add(addSkill);
		addSkill = new AttributeSkill(Attribute.Type.STR).setRequirements((List<Skill>)auxList.clone());
		
		auxList.clear();
		clericSkilltree.add(addSkill);
		
		auxList.add(addSkill);
		addSkill = new ClassSkill(ClassSkill.Type.Cleric, 4).setRequirements((List<Skill>)auxList.clone());
		
		auxList.clear();
		clericSkilltree.add(addSkill);
		
		auxList.add(addSkill);
		addSkill = new AttributeSkill(Attribute.Type.STR).setRequirements((List<Skill>)auxList.clone());
		auxList.clear();
		clericSkilltree.add(addSkill);
		
		auxList.add(addSkill);
		addSkill = new AttributeSkill(Attribute.Type.INT).setRequirements((List<Skill>)auxList.clone());
		
		auxList.clear();
		clericSkilltree.add(addSkill);
		
		auxList.add(addSkill);
		addSkill = new ClassSkill(ClassSkill.Type.Cleric, 5).setRequirements((List<Skill>)auxList.clone());
		
		auxList.clear();
		clericSkilltree.add(addSkill);
		
		auxList.add(addSkill);
		addSkill = new AttributeSkill(Attribute.Type.ALL).setRequirements((List<Skill>)auxList.clone());
		
		auxList.clear();
		clericSkilltree.add(addSkill);
	}
	
	@SuppressWarnings("unchecked")
	public static void initializeMagusPath(Skill root)
	{
		ArrayList<Skill> auxList = new ArrayList<Skill>();
		Skill addSkill;
		
		auxList.add(root);
		addSkill = new ClassSkill(ClassSkill.Type.Magus, 1).setRequirements((List<Skill>)auxList.clone());
		
		auxList.clear();
		magusSkilltree.add(addSkill);
		
		auxList.add(addSkill);
		addSkill = new AttributeSkill(Attribute.Type.INT).setRequirements((List<Skill>)auxList.clone());
		
		auxList.clear();
		magusSkilltree.add(addSkill);
		
		auxList.add(addSkill);
		addSkill = new AttributeSkill(Attribute.Type.AGI).setRequirements((List<Skill>)auxList.clone());
		
		auxList.clear();
		magusSkilltree.add(addSkill);
		
		auxList.add(addSkill);
		addSkill = new ClassSkill(ClassSkill.Type.Magus, 2).setRequirements((List<Skill>)auxList.clone());
		
		auxList.clear();
		magusSkilltree.add(addSkill);
		
		auxList.add(addSkill);
		addSkill = new AttributeSkill(Attribute.Type.INT).setRequirements((List<Skill>)auxList.clone());
		
		auxList.clear();
		magusSkilltree.add(addSkill);
		
		auxList.add(addSkill);
		addSkill = new AttributeSkill(Attribute.Type.INT).setRequirements((List<Skill>)auxList.clone());
		
		auxList.clear();
		magusSkilltree.add(addSkill);
		
		auxList.add(addSkill);
		addSkill = new ClassSkill(ClassSkill.Type.Magus, 3).setRequirements((List<Skill>)auxList.clone());
		
		auxList.clear();
		magusSkilltree.add(addSkill);
		
		auxList.add(addSkill);
		addSkill = new AttributeSkill(Attribute.Type.INT).setRequirements((List<Skill>)auxList.clone());
		
		auxList.clear();
		magusSkilltree.add(addSkill);
		
		auxList.add(addSkill);
		addSkill = new AttributeSkill(Attribute.Type.CON).setRequirements((List<Skill>)auxList.clone());
		
		auxList.clear();
		magusSkilltree.add(addSkill);
		
		auxList.add(addSkill);
		addSkill = new ClassSkill(ClassSkill.Type.Magus, 4).setRequirements((List<Skill>)auxList.clone());
		
		auxList.clear();
		magusSkilltree.add(addSkill);
		
		auxList.add(addSkill);
		addSkill = new AttributeSkill(Attribute.Type.AGI).setRequirements((List<Skill>)auxList.clone());
		auxList.clear();
		magusSkilltree.add(addSkill);
		
		auxList.add(addSkill);
		addSkill = new AttributeSkill(Attribute.Type.INT).setRequirements((List<Skill>)auxList.clone());
		
		auxList.clear();
		magusSkilltree.add(addSkill);
		
		auxList.add(addSkill);
		addSkill = new ClassSkill(ClassSkill.Type.Magus, 5).setRequirements((List<Skill>)auxList.clone());
		
		auxList.clear();
		magusSkilltree.add(addSkill);
		
		auxList.add(addSkill);
		addSkill = new AttributeSkill(Attribute.Type.ALL).setRequirements((List<Skill>)auxList.clone());
		
		auxList.clear();
		magusSkilltree.add(addSkill);
	}
}
