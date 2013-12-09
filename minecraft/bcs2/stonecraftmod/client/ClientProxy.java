package bcs2.stonecraftmod.client;

import cpw.mods.fml.client.registry.RenderingRegistry;
import bcs2.stonecraftmod.CommonProxy;


public class ClientProxy extends CommonProxy {

	@Override
	public void registerRenderers() {
		// This is for rendering entities and so forth later on
	}
	
	public int addArmor(String armor) {
		
		return RenderingRegistry.addNewArmourRendererPrefix(armor);
	
	}
}