package bcs2.stonecraftmod;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.ItemHoe;

public class StonecraftHoe extends ItemHoe {

	public StonecraftHoe(int par1, EnumToolMaterial par2EnumToolMaterial) {
		super(par1, par2EnumToolMaterial);
		
		//setTextureName("stonecraft:hardenedstone_hoe");
	}
	
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister par1) {
		this.itemIcon = par1.registerIcon("stonecraftmod:hardenedstone_hoe");
	}	

}
