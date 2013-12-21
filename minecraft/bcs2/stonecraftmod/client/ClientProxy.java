package bcs2.stonecraftmod.client;

import net.minecraftforge.client.MinecraftForgeClient;
import bcs2.stonecraftmod.CommonProxy;
import bcs2.stonecraftmod.ItemStonecraftChestRenderer;
import bcs2.stonecraftmod.StonecraftMod;
import bcs2.stonecraftmod.TileEntityStonecraftChest;
import bcs2.stonecraftmod.TileEntityStonecraftChestRenderer;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;


public class ClientProxy extends CommonProxy {

	@Override
	public void registerRenderers() {
		// This is for rendering entities and so forth later on
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityStonecraftChest.class, new TileEntityStonecraftChestRenderer());
		//MinecraftForgeClient.registerItemRenderer(StonecraftMod.stonecraftChest.blockID, new ItemStonecraftChestRenderer());
	}
	
	public int addArmor(String armor) {
		
		return RenderingRegistry.addNewArmourRendererPrefix(armor);
	
	}
}