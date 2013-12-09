package bcs2.stonecraftmod;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.ItemSword;

public class StonecraftSword extends ItemSword {

	public StonecraftSword(int id, EnumToolMaterial par2EnumToolMaterial) {
		super(id, par2EnumToolMaterial);

		setTextureName("stonecraftmod:hardenedstone_sword");
		setCreativeTab(CreativeTabs.tabCombat);
		
	}

}
