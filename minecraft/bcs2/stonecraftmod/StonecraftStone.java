package bcs2.stonecraftmod;

import net.minecraft.item.Item;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.client.renderer.texture.IconRegister;


public class StonecraftStone extends Item {

	public StonecraftStone(int id) {
		super(id);
		
		setMaxStackSize(64);
		setCreativeTab(CreativeTabs.tabMisc);
		setUnlocalizedName("stonecraftStone");
		setTextureName("stonecraftmod:condensed_stone");
		
	}

}
