package com.disastermoo.rpgsystem;

import java.util.ArrayList;
import java.util.List;

import com.disastermoo.rpgsystem.player.skilltree.Skill;

public abstract class Initializer {

	public static List<Skill> skillList;
	public static void initializeSkills()
	{
		List<Skill> listSkill = new ArrayList<Skill>();
		skillList = listSkill;
	}
}
