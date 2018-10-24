package com.disastermoo.rpgsystem.core.container;

import com.disastermoo.rpgsystem.core.RegistryHandler;
import com.disastermoo.rpgsystem.core.item.Materia;
import com.disastermoo.rpgsystem.core.system.EntityInfo;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;

public class InventoryRPG implements IInventory
{
	EntityInfo rpgData;
	String name;

	public InventoryRPG(EntityInfo rpgData)
	{
		this.rpgData = rpgData;
		this.name = "RPGInventory";
	}

	@Override
	public String getName()
	{
		return this.name;
	}

	@Override
	public boolean hasCustomName()
	{
		return false;
	}

	@Override
	public ITextComponent getDisplayName()
	{
		return new TextComponentString(this.name);
	}

	@Override
	public int getSizeInventory()
	{
		return 7;
	}

	@Override
	public boolean isEmpty()
	{
		for(int i = 0; i <= 6; i++)if(rpgData.getMateriaInSlot(i) != null)return false;
		return true;
	}

	@Override
	public ItemStack getStackInSlot(int index)
	{
		ItemStack ret = rpgData.getMateriaInSlot(index);
		if(ret == null)ret = ItemStack.EMPTY;
		return ret;
	}

	@Override
	public ItemStack decrStackSize(int index, int count)
	{
		ItemStack stack = getStackInSlot(index);
		if(!stack.isEmpty())
			if(stack.getCount() <= count)
				rpgData.setMateriaInSlot(index, null);
			else
			{
				stack = stack.splitStack(count);
				if(stack.getCount()==0)
					rpgData.setMateriaInSlot(index, null);
			}
		return stack;
	}

	@Override
	public ItemStack removeStackFromSlot(int index)
	{
		ItemStack ret =  getStackInSlot(index).copy();
		setInventorySlotContents(index, ItemStack.EMPTY);
		return ret;
	}

	@Override
	public void setInventorySlotContents(int index, ItemStack stack)
	{
		if(stack.equals(stack.EMPTY)) {
			rpgData.setMateriaInSlot(index, null);
		}else {
			rpgData.setMateriaInSlot(index, stack);
		}
	}

	@Override
	public int getInventoryStackLimit()
	{
		return 1;
	}

	@Override
	public void markDirty()
	{
		/* NOT NEEDED */
	}

	@Override
	public boolean isUsableByPlayer(EntityPlayer player)
	{
		return true;
	}

	@Override
	public void openInventory(EntityPlayer player)
	{
		/* NOT NEEDED */
	}

	@Override
	public void closeInventory(EntityPlayer player)
	{
		/* NOT NEEDED */
	}

	@Override
	public boolean isItemValidForSlot(int index, ItemStack stack)
	{
		return (stack.getItem() instanceof Materia);
	}

	@Override
	public int getField(int id)
	{
		return 0;
	}

	@Override
	public void setField(int id, int value)
	{
	}

	@Override
	public int getFieldCount()
	{
		return 0;
	}

	@Override
	public void clear()
	{
		for(int i = 0; i <= 6; i++)
			rpgData.setMateriaInSlot(i, null);
	}

}