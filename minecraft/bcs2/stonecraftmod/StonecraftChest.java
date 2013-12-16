package bcs2.stonecraftmod;

import java.util.Random;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraft.nbt.NBTTagCompound;

public class StonecraftChest extends BlockContainer {

	protected StonecraftChest(int id, Material par2Material) {
		super(id, par2Material);


	}

	@Override
	public TileEntity createNewTileEntity(World world) {


		return null;
	}
	
	// Drop items contained in the chest
	@Override
	public void breakBlock(World world, int x, int y, int z, int par5, int par6) {
		dropItems(world, x, y, z);
		super.breakBlock(world, x, y, z, par5, par6);
	}

	private void dropItems(World world, int x, int y, int z) {
		Random rand = new Random();
		TileEntity tileEntity = world.getBlockTileEntity(x,  y,  z);
		
		if (!(tileEntity instanceof IInventory)) {
			return;
		}
		
		IInventory inventory = (IInventory)tileEntity;
		
		for (int i = 0; i < inventory.getSizeInventory(); i++) {
			ItemStack item = inventory.getStackInSlot(i);
			
			if (item != null && item.stackSize > 0) {
				float rx = rand.nextFloat() * 0.8f + 0.1f;
				float ry = rand.nextFloat() * 0.8f + 0.1f;
				float rz = rand.nextFloat() * 0.8f + 0.1f;
				
				EntityItem entityItem = new EntityItem(world, x + rx, y + ry, z + rz, new ItemStack(item.itemID, item.stackSize, item.getItemDamage()));
				
				if (item.hasTagCompound()) {
					entityItem.getEntityItem().setTagCompound((NBTTagCompound)item.getTagCompound().copy());
				}
				
				float factor = 0.05f;
				entityItem.motionX = rand.nextGaussian() * factor;
				entityItem.motionY = rand.nextGaussian() * factor + 0.2f;
				entityItem.motionZ = rand.nextGaussian() * factor;
				world.spawnEntityInWorld(entityItem);
				item.stackSize = 0;
				
			}
		}
	}
	
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int metadata, float what, float these, float are) {
		TileEntity tileEntity = world.getBlockTileEntity(x, y, z);
		
		if (tileEntity == null || player.isSneaking()) {
			return false;
		}
		player.openGui(StonecraftMod.instance, 0, world, x, y, z);
		return true;
	}

}
