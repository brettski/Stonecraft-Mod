package bcs2.stonecraftmod;

import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import java.util.Calendar;

import net.minecraft.block.Block;
import net.minecraft.client.model.ModelChest;
import net.minecraft.client.model.ModelLargeChest;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

@SideOnly(Side.CLIENT)
public class TileEntityStonecraftChestRenderer extends TileEntitySpecialRenderer
{
    // Trapped chests not being used in Stonecraft
	private static final ResourceLocation RES_TRAPPED_DOUBLE = new ResourceLocation("stonecraftmod:textures/entity/chest/trapped_double.png");
    private static final ResourceLocation RES_CHRISTMAS_DOUBLE = new ResourceLocation("stonecraftmod:textures/entity/chest/double_chest_stonecraft.png");
    private static final ResourceLocation RES_NORMAL_DOUBLE = new ResourceLocation("stonecraftmod:textures/entity/chest/double_chest_stonecraft.png");
    private static final ResourceLocation RES_TRAPPED_SINGLE = new ResourceLocation("stonecraftmod:textures/entity/chest/trapped.png");
    private static final ResourceLocation RES_CHRISTMAS_SINGLE = new ResourceLocation("stonecraftmod:textures/entity/chest/normal_chest_stonecraft.png");
    private static final ResourceLocation RES_NORMAL_SINGLE = new ResourceLocation("stonecraftmod:textures/entity/chest/normal_chest_stonecraft.png");

    /** The normal small chest model. */
    private ModelChest chestModel = new ModelChest();

    /** The large double chest model. */
    private ModelChest largeChestModel = new ModelLargeChest();

    /** If true, chests will be rendered with the Christmas present textures. */
    private boolean isChristmas;

    public TileEntityStonecraftChestRenderer()
    {
        Calendar calendar = Calendar.getInstance();

        if (calendar.get(2) + 1 == 12 && calendar.get(5) >= 24 && calendar.get(5) <= 26)
        {
            this.isChristmas = true;
        }
    }

    /**
     * Renders the TileEntity for the chest at a position.
     */
    public void renderTileEntityStonecraftChestAt(TileEntityStonecraftChest par1TileEntityStonecraftChest, double par2, double par4, double par6, float par8)
    {
        int i;

        if (!par1TileEntityStonecraftChest.hasWorldObj())
        {
            i = 0;
        }
        else
        {
            Block block = par1TileEntityStonecraftChest.getBlockType();
            i = par1TileEntityStonecraftChest.getBlockMetadata();

            if (block instanceof BlockStonecraftChest && i == 0)
            {
                try
                {
                    ((BlockStonecraftChest)block).unifyAdjacentChests(par1TileEntityStonecraftChest.getWorldObj(), par1TileEntityStonecraftChest.xCoord, par1TileEntityStonecraftChest.yCoord, par1TileEntityStonecraftChest.zCoord);
                }
                catch (ClassCastException e)
                {
                    FMLLog.severe("Attempted to render a chest at %d,  %d, %d that was not a chest",
                            par1TileEntityStonecraftChest.xCoord, par1TileEntityStonecraftChest.yCoord, par1TileEntityStonecraftChest.zCoord);
                }
                i = par1TileEntityStonecraftChest.getBlockMetadata();
            }

            par1TileEntityStonecraftChest.checkForAdjacentChests();
        }

        if (par1TileEntityStonecraftChest.adjacentChestZNeg == null && par1TileEntityStonecraftChest.adjacentChestXNeg == null)
        {
            ModelChest modelchest;

            if (par1TileEntityStonecraftChest.adjacentChestXPos == null && par1TileEntityStonecraftChest.adjacentChestZPosition == null)
            {
                modelchest = this.chestModel;

                if (par1TileEntityStonecraftChest.getChestType() == 1)
                {
                    this.bindTexture(RES_TRAPPED_SINGLE);
                }
                else if (this.isChristmas)
                {
                    this.bindTexture(RES_CHRISTMAS_SINGLE);
                }
                else
                {
                    this.bindTexture(RES_NORMAL_SINGLE);
                }
            }
            else
            {
                modelchest = this.largeChestModel;

                if (par1TileEntityStonecraftChest.getChestType() == 1)
                {
                    this.bindTexture(RES_TRAPPED_DOUBLE);
                }
                else if (this.isChristmas)
                {
                    this.bindTexture(RES_CHRISTMAS_DOUBLE);
                }
                else
                {
                    this.bindTexture(RES_NORMAL_DOUBLE);
                }
            }

            GL11.glPushMatrix();
            GL11.glEnable(GL12.GL_RESCALE_NORMAL);
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            GL11.glTranslatef((float)par2, (float)par4 + 1.0F, (float)par6 + 1.0F);
            GL11.glScalef(1.0F, -1.0F, -1.0F);
            GL11.glTranslatef(0.5F, 0.5F, 0.5F);
            short short1 = 0;

            if (i == 2)
            {
                short1 = 180;
            }

            if (i == 3)
            {
                short1 = 0;
            }

            if (i == 4)
            {
                short1 = 90;
            }

            if (i == 5)
            {
                short1 = -90;
            }

            if (i == 2 && par1TileEntityStonecraftChest.adjacentChestXPos != null)
            {
                GL11.glTranslatef(1.0F, 0.0F, 0.0F);
            }

            if (i == 5 && par1TileEntityStonecraftChest.adjacentChestZPosition != null)
            {
                GL11.glTranslatef(0.0F, 0.0F, -1.0F);
            }

            GL11.glRotatef((float)short1, 0.0F, 1.0F, 0.0F);
            GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
            float f1 = par1TileEntityStonecraftChest.prevLidAngle + (par1TileEntityStonecraftChest.lidAngle - par1TileEntityStonecraftChest.prevLidAngle) * par8;
            float f2;

            if (par1TileEntityStonecraftChest.adjacentChestZNeg != null)
            {
                f2 = par1TileEntityStonecraftChest.adjacentChestZNeg.prevLidAngle + (par1TileEntityStonecraftChest.adjacentChestZNeg.lidAngle - par1TileEntityStonecraftChest.adjacentChestZNeg.prevLidAngle) * par8;

                if (f2 > f1)
                {
                    f1 = f2;
                }
            }

            if (par1TileEntityStonecraftChest.adjacentChestXNeg != null)
            {
                f2 = par1TileEntityStonecraftChest.adjacentChestXNeg.prevLidAngle + (par1TileEntityStonecraftChest.adjacentChestXNeg.lidAngle - par1TileEntityStonecraftChest.adjacentChestXNeg.prevLidAngle) * par8;

                if (f2 > f1)
                {
                    f1 = f2;
                }
            }

            f1 = 1.0F - f1;
            f1 = 1.0F - f1 * f1 * f1;
            modelchest.chestLid.rotateAngleX = -(f1 * (float)Math.PI / 2.0F);
            modelchest.renderAll();
            GL11.glDisable(GL12.GL_RESCALE_NORMAL);
            GL11.glPopMatrix();
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        }
    }

    public void renderTileEntityAt(TileEntity par1TileEntity, double par2, double par4, double par6, float par8)
    {
        this.renderTileEntityStonecraftChestAt((TileEntityStonecraftChest)par1TileEntity, par2, par4, par6, par8);
    }
}
