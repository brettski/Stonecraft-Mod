package bcs2.stonecraftmod;

// create armor reference>(https://www.youtube.com/watch?v=XhN49r5XwOc)

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.Item;import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;

public class StonecraftArmor extends ItemArmor  {
	
	int ArmorTypeId;

	public StonecraftArmor(int id, EnumArmorMaterial par2EnumArmorMaterial,
			int par3, int par4) {
		super(id, par2EnumArmorMaterial, par3, par4);
		ArmorTypeId = par4;
		
		setCreativeTab(CreativeTabs.tabCombat);
		
	}
	
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister par1) {
		this.itemIcon = par1.registerIcon("stonecraftmod:stone_helmet");
		
		switch (ArmorTypeId) {
		case 0:	this.itemIcon = par1.registerIcon("stonecraftmod:stone_helmet");
				break;
		case 1: this.itemIcon = par1.registerIcon("stonecraftmod:stone_chestplate");
				break;
		case 2: this.itemIcon = par1.registerIcon("stonecraftmod:stone_leggings");
				break;
		case 3: this.itemIcon = par1.registerIcon("stonecraftmod:stone_boots");
				break;
		}
	}
	
	public String getArmorTexture(ItemStack stack, Entity entity, int slot, int layer) {
		
		if (ArmorTypeId == 2) {
			
			return "stonecraftmod:textures/models/armor/stone_armor_2.png";
		}
				
		return "stonecraftmod:textures/models/armor/stone_armor.png";
		//return "minecraft:textures/armor/Stone_Armor.png";
		
	}

}
