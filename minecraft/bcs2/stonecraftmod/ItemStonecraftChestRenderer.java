package bcs2.stonecraftmod;

import net.minecraft.client.model.ModelChest;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;

public class ItemStonecraftChestRenderer implements IItemRenderer {

	private ModelChest chestModel;
	
	public ItemStonecraftChestRenderer() {
		chestModel = new ModelChest();
	}
	
	@Override
	public boolean handleRenderType(ItemStack item, ItemRenderType type) {
		// TODO Auto-generated method stub
		
		return true;
	}

	@Override
	public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item,
			ItemRendererHelper helper) {
		// TODO Auto-generated method stub
		
		return true;
	}

	@Override
	public void renderItem(ItemRenderType type, ItemStack item, Object... data) {
		// Renders the held, inventory and after block break versions of block using entity image.  
		// Deep Details on this at (http://greyminecraftcoder.blogspot.com/2013/09/custom-item-rendering-using.html)
		TileEntityRenderer.instance.renderTileEntityAt(new TileEntityStonecraftChest(), 0.0D, 0.0D, 0.0D, 0.0F);
		
	}

}
