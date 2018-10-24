package com.disastermoo.rpgsystem.core.container;

import com.disastermoo.rpgsystem.core.util.RPGUtils;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;

public class ContainerGuiStatus extends Container{
	
	public IInventory inv;
	
	public ContainerGuiStatus(EntityPlayer player)
	{
		for (int y = 0; y < 4; y++) {
            for (int x = 0; x < 9; x++) {
                addSlotToContainer(new Slot(player.inventory, y * 9 + x, x * 18 + 54, y * 18 + 177));
            }
        }
		InventoryRPG inv = new InventoryRPG(RPGUtils.getRPGInfo(player).getInfo());
		/* ADD MATERIA SLOT */
		addSlotToContainer(new Slot(inv, 0, 173, 95));
		addSlotToContainer(new Slot(inv, 1, 192, 93));
		addSlotToContainer(new Slot(inv, 2, 202, 108));
		addSlotToContainer(new Slot(inv, 3, 194, 125));
		addSlotToContainer(new Slot(inv, 4, 175, 128));
		addSlotToContainer(new Slot(inv, 5, 165, 112));
		addSlotToContainer(new Slot(inv, 6, 183, 110));
	}

	@Override
	public boolean canInteractWith(EntityPlayer playerIn) {
		return true;
	}
}
