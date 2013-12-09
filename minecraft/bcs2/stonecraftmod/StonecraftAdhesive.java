package bcs2.stonecraftmod;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class StonecraftAdhesive extends Item {

	public StonecraftAdhesive(int id) {
		super(id);
		
		setMaxStackSize(16);
		setCreativeTab(CreativeTabs.tabMisc);
		setUnlocalizedName("stonecraftAdhesive");
		setTextureName("stonecraftmod:bucket_raw_adhesive");
		



	}

}
