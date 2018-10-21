package com.disastermoo.rpgsystem.core.item;

import java.util.List;

import javax.annotation.Nullable;

import com.disastermoo.rpgsystem.core.RPGConfig.Constants;
import com.disastermoo.rpgsystem.core.system.Attribute;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class Materia extends ItemBase{
	public enum Type {
		Boost, Enhancement, Special, Support;
	}
	
	public Materia()
	{
		super("materia", CreativeTabs.BREWING);
		setHasSubtypes(true);
		setMaxStackSize(1);
		setMaxDamage(0);
	}
	
	@Override
	public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items)
    {
		for(int i = 1; i <= Constants.TOTAL_MATERIA; i++) {
	        if (this.isInCreativeTab(tab))
	        {
	            items.add(new ItemStack(this, 1, i));
	        }
		}
    }
	
	@Override
	public int getMetadata(int damage)
	{
		return damage;
	}
	
	@Override
	public void initModel(){
		/* Register materia models */
		int start, finish;
		start = 1;
		finish = Constants.NUMBER_OF_BOOST_MATERIA;
		for(int i = start; i <= finish; i++) {
			ModelLoader.setCustomModelResourceLocation(this, i, new ModelResourceLocation(Constants.MODID + ":materia_boost"));
		}
		
		start = finish + 1;
		finish = finish + Constants.NUMBER_OF_ENHANCEMENT_MATERIA;
		for(int i = start; i <= finish; i++) {
			ModelLoader.setCustomModelResourceLocation(this, i, new ModelResourceLocation(Constants.MODID + ":materia_enhancement"));
		}
		
		start = finish + 1;
		finish = finish + Constants.NUMBER_OF_SPECIAL_MATERIA;
		for(int i = start; i <= finish; i++) {
			ModelLoader.setCustomModelResourceLocation(this, i, new ModelResourceLocation(Constants.MODID + ":materia_special"));
		}
		
		start = finish + 1;
		finish = finish + Constants.NUMBER_OF_SUPPORT_MATERIA;
		for(int i = start; i <= finish; i++) {
			ModelLoader.setCustomModelResourceLocation(this, i, new ModelResourceLocation(Constants.MODID + ":materia_support"));
		}
	}
	
	@Override
    public String getUnlocalizedName(ItemStack stack) 
    {
        if (stack.getMetadata() > 0) 
        {
        	return "rpgsystem.materia." + stack.getMetadata();
        }
        return "ERROR NULL MATERIA";
     }
	
	@SideOnly(Side.CLIENT)
	@Override
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn)
	{
		tooltip.add(I18n.format("rpgsystem.tooltip.materia." + stack.getMetadata()));
	}
		
	public static final Type getMateriaType(int meta)
	{
		if(meta <= Constants.NUMBER_OF_BOOST_MATERIA)return Type.Boost;
		if(meta <= Constants.NUMBER_OF_BOOST_MATERIA + Constants.NUMBER_OF_ENHANCEMENT_MATERIA)return Type.Enhancement;
		if(meta <= Constants.NUMBER_OF_BOOST_MATERIA + Constants.NUMBER_OF_ENHANCEMENT_MATERIA + Constants.NUMBER_OF_SPECIAL_MATERIA)return Type.Special;
		if(meta <= Constants.TOTAL_MATERIA)return Type.Support;
		return null;
	}
}
