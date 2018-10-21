package com.disastermoo.rpgsystem.core;

import java.util.ArrayList;
import java.util.List;

import com.disastermoo.rpgsystem.core.item.ItemBase;
import com.disastermoo.rpgsystem.core.item.Materia;


public class RegistryHandler {
	public static final RegistryHandler INSTANCE = new RegistryHandler();
	
	public ItemList items;
	
	
	private RegistryHandler()
	{
		items = new ItemList();
		items.listAll = new ArrayList<ItemBase>();
		registerAll();
	}
	
	private void registerAll()
	{
		items.materia = new Materia();
		items.listAll.add(items.materia);
	}
	
	
	public class ItemList
	{
		public List<ItemBase> listAll;
		public ItemBase materia;
	}
}
