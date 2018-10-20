package com.disastermoo.rpgsystem.core.system;

import java.util.List;

import javax.annotation.Nullable;

import com.disastermoo.rpgsystem.core.config.Config.Constants;

import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class Materia extends Item{
	public enum Type {
		Boost, Enhancement, Special, Support;
	}
	
	private int effectID;
	
	public Materia(int effectID)
	{
		this.effectID = effectID;
		setUnlocalizedName("item.materia." + effectID);
		setRegistryName(Constants.MODID, "itemmateria");
		setHasSubtypes(true);
		setMaxDamage(0);
		setMaxStackSize(1);
		//setCreativeTab(CreativeTabs.MISC);
	}
	
	@Override
	public int getMetadata(int damage) 
	{
	    return damage;
	}
	
	@Override
    public String getUnlocalizedName(ItemStack stack) 
    {
        if (stack.getItemDamage() > 0) 
        {
        	return "item.materia." + stack.getItemDamage();
        }
        return "ERROR NULL MATERIA";
     }
	
	@SideOnly(Side.CLIENT)
	@Override
	public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items) 
	{
		if(this.isInCreativeTab(tab)) {
			for (int pos = 1; pos <= Constants.TOTAL_MATERIA; pos++) 
	        {
				ItemStack stack = new ItemStack(new Materia(pos));
				stack.setItemDamage(pos);
				items.add(stack);
	        }
		}
    }
	
	@SideOnly(Side.CLIENT)
	@Override
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn)
	{
		tooltip.add(I18n.format("tooltip.materia." + stack.getItemDamage(), Constants.MODID));
	}
	
	public Type getMateriaType()
	{
		if(this.effectID <= Constants.NUMBER_OF_BOOST_MATERIA)return Type.Boost;
		if(this.effectID <= Constants.NUMBER_OF_BOOST_MATERIA + Constants.NUMBER_OF_ENHANCEMENT_MATERIA)return Type.Enhancement;
		if(this.effectID <= Constants.NUMBER_OF_BOOST_MATERIA + Constants.NUMBER_OF_ENHANCEMENT_MATERIA + Constants.NUMBER_OF_SPECIAL_MATERIA)return Type.Special;
		if(this.effectID <= Constants.TOTAL_MATERIA)return Type.Support;
		return null;
	}
	
	public int getEffectID()
	{
		return this.effectID;
	}
}
