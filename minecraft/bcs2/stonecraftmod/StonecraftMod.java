package bcs2.stonecraftmod;

import net.minecraft.block.Block;
import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.EnumHelper;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;


 
@Mod(modid="bcs2.stonecraftmod", name="Stonecraft", version="1.0.0")
@NetworkMod(clientSideRequired=true, serverSideRequired=false)
public class StonecraftMod {
 

	// Define items
	private static Item condensedStone;
	private static Item stonecraftRock;
	private static Item stickyRock;
	private static Item hardenedStone;
	private static Item rawAdhesive;
	private static Item cookedAdhesive;
	private int condensedStoneId;
	private int stonecraftRockId;
	private int stickyRockId;
	private int hardenedStoneId;
	private int rawAdhesiveId;
	private int cookedAdhesiveId;

	// Define material
	// Look at Enum class for description (<ctrl> + click)
	private static EnumArmorMaterial stonecraftArmor = EnumHelper.addArmorMaterial("Stonecraft", 100, new int[] {2, 5, 3, 1}, 25);
	private static EnumToolMaterial stonecraftTools = EnumHelper.addToolMaterial("Stonecraft", 2, 2000, 4.5f, 1.5f, 22);
	
	
	// Define armor (http://pastebin.com/FaL60zrf)
	private static Item stonecraftHelmet;
	private static Item stonecraftChestplate;
	private static Item stonecraftLeggings;
	private static Item stonecraftBoots;
	private int stonecraftHelmetId;
	private int stonecraftChestplateId;
	private int stonecraftLeggingsId;
	private int stonecraftBootsId;
	
	
	// Define tools
	private static Item stonecraftSword;
	private static Item stonecraftHoe;
	private static Item stonecraftPickaxe;
	private static Item stonecraftAxe;
	private static Item stonecraftShovel;
	private int stonecraftSwordId;
	private int stonecraftHoeId;
	private int stonecraftPickaxeId;
	private int stonecraftAxeId;
	private int stonecraftShovelId;
		
		
    // The instance of your mod that Forge uses.
    @Instance("StonecraftMod")
    public static StonecraftMod instance;
   
    // Says where the client and server 'proxy' code is loaded.
    @SidedProxy(clientSide="bcs2.stonecraftmod.client.ClientProxy", serverSide="bcs2.stonecraftmod.CommonProxy")
    public static CommonProxy proxy;
   
    
    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
    	Configuration config = new Configuration(event.getSuggestedConfigurationFile());
    	config.load();
    	
    	condensedStoneId = config.get("Item IDs", "Condensed Stone Id", 24001).getInt();
    	stonecraftRockId = config.get("Item IDs", "Stonecraft Rock Id", 24002).getInt();
    	stickyRockId = config.get("Item IDs", "Sticky Rock Id", 24003).getInt();
    	hardenedStoneId = config.get("Item IDs", "Hardened Stone Id", 24004).getInt();
    	rawAdhesiveId = config.get("Item IDs", "Raw Adhesive Id", 24005).getInt();
    	cookedAdhesiveId = config.get("Item IDs", "Cooked Adhesive Id", 24006).getInt();
    	
    	stonecraftHelmetId = config.get("Armor IDs", "Stonecraft Helmet Id", 24020).getInt();
    	stonecraftChestplateId = config.get("Armor IDs", "Stonecraft Chestplate Id", 24021).getInt(); 
    	stonecraftLeggingsId = config.get("Armor IDs", "Stonecraft Leggings Id", 24022).getInt();
    	stonecraftBootsId = config.get("Armor IDs", "Stonecraft Boots Id", 24023).getInt();
    	
    	stonecraftSwordId = config.get("Tool IDs", "Stonecraft Sword Id", 24024).getInt();
    	stonecraftHoeId = config.get("Tool IDs", "Stonecraft Hoe Id", 24025).getInt();
    	stonecraftPickaxeId = config.get("Tool IDs", "Stonecraft Pickaxe Id", 24026).getInt();
    	stonecraftAxeId = config.get("Tool IDs", "Stonecraft Axe Id", 24027).getInt();
    	stonecraftShovelId = config.get("Tool IDs", "Stonecraft Shovel Id", 24028).getInt();    	
    	
    	config.save();
    	
    }
   
    @EventHandler
    public void load(FMLInitializationEvent event) {
        proxy.registerRenderers();

        // Settings for items  
        condensedStone = new StonecraftStone(condensedStoneId).setUnlocalizedName("condensedStone");
        stonecraftRock = new StonecraftStone(stonecraftRockId).setUnlocalizedName("stonecraftRock").setTextureName("stonecraftmod:rock");
        stickyRock = new StonecraftStone(stickyRockId).setUnlocalizedName("stickyRock").setTextureName("stonecraftmod:sticky_rock");
        hardenedStone = new StonecraftStone(hardenedStoneId).setUnlocalizedName("hardenedStone").setTextureName("stonecraftmod:hardened_rock");
        rawAdhesive = new StonecraftAdhesive(rawAdhesiveId);
        cookedAdhesive = new StonecraftAdhesive(cookedAdhesiveId).setMaxStackSize(4).setUnlocalizedName("cookedAdhesive").setContainerItem(Item.bucketEmpty).setTextureName("stonecraftmod:bucket_adhesive");
        
        // settings for tools
        stonecraftSword = new StonecraftSword(stonecraftSwordId, stonecraftTools).setUnlocalizedName("stonecraftSword");
        stonecraftHoe = new StonecraftHoe(stonecraftHoeId, stonecraftTools).setUnlocalizedName("stonecraftHoe");
        stonecraftPickaxe = new StonecraftPickaxe(stonecraftPickaxeId, stonecraftTools).setUnlocalizedName("stonecraftPickAxe");
        stonecraftAxe = new StonecraftAxe(stonecraftAxeId, stonecraftTools).setUnlocalizedName("stonecraftAxe");
        stonecraftShovel = new StonecraftShovel(stonecraftShovelId, stonecraftTools).setUnlocalizedName("stonecraftShovel");
        
        // Settings for armor
        stonecraftHelmet = new StonecraftArmor(stonecraftHelmetId, stonecraftArmor, proxy.addArmor("StonecraftArmor") , 0).setUnlocalizedName("stonecraftHelmet");
        stonecraftChestplate = new StonecraftArmor(stonecraftChestplateId, stonecraftArmor, proxy.addArmor("StonecraftArmor"),1).setUnlocalizedName("stonecraftChestplate");
        stonecraftLeggings = new StonecraftArmor(stonecraftLeggingsId, stonecraftArmor, proxy.addArmor("StonecraftArmor"), 2).setUnlocalizedName("stonedCraftLeggings");
        stonecraftBoots = new StonecraftArmor(stonecraftBootsId, stonecraftArmor, proxy.addArmor("StonecraftArmor"), 3).setUnlocalizedName("stonecraftBoots");
                
        
        // Item names
        LanguageRegistry.addName(condensedStone, "Condensed Stone");
        LanguageRegistry.addName(stonecraftRock, "Rock");
        LanguageRegistry.addName(stickyRock, "Sticky Rock");
        LanguageRegistry.addName(hardenedStone, "Hardend Stone");
        LanguageRegistry.addName(rawAdhesive, "Raw Adhesive");
        LanguageRegistry.addName(cookedAdhesive, "Adhesive");
        
        // Armor names
        LanguageRegistry.addName(stonecraftHelmet, "Stonecraft Helmet");
        LanguageRegistry.addName(stonecraftChestplate, "Stonecraft Chestplate");
        LanguageRegistry.addName(stonecraftLeggings, "Stonecraft Leggings");
        LanguageRegistry.addName(stonecraftBoots, "Stonecraft Boots");
        
        // Tool names
        LanguageRegistry.addName(stonecraftSword, "Stonecaft Sword");
        LanguageRegistry.addName(stonecraftHoe, "Stonecaft Hoe");
        LanguageRegistry.addName(stonecraftPickaxe, "Stonecaft Pick");
        LanguageRegistry.addName(stonecraftAxe, "Stonecaft Axe");
        LanguageRegistry.addName(stonecraftShovel, "Stonecaft Shovel");
        
        // Item recipes 
        GameRegistry.addRecipe(new ItemStack(condensedStone), " s ", "sss", " s ", 's', Block.stone);
        GameRegistry.addRecipe(new ItemStack(stonecraftRock), " s ", "sss", " s ", 's', condensedStone);
        GameRegistry.addShapelessRecipe(new ItemStack(rawAdhesive), 
        		new ItemStack(Item.bucketWater), new ItemStack(Item.sugar), 
        		new ItemStack(Block.mushroomBrown), new ItemStack(Block.sapling, 1, 1));
        GameRegistry.addShapelessRecipe(new ItemStack(stickyRock), stonecraftRock, cookedAdhesive);
        GameRegistry.addSmelting(rawAdhesive.itemID, new ItemStack(cookedAdhesive), 0f);
        //FurnaceRecipes.smelting().addSmelting(new ItemStack(hardenedStone), stonecraftRock.itemID, 0.1f);
        GameRegistry.addSmelting(stickyRock.itemID,  new ItemStack(hardenedStone), 0.1f);
        
        // Armor recipes
        GameRegistry.addRecipe(new ItemStack(stonecraftHelmet), "sss", "s s", 's', hardenedStone);
        GameRegistry.addRecipe(new ItemStack(stonecraftChestplate), "s s", "sss", "sss", 's', hardenedStone);
        GameRegistry.addRecipe(new ItemStack(stonecraftLeggings), "sss", "s s", "s s", 's', hardenedStone);
        GameRegistry.addRecipe(new ItemStack(stonecraftBoots), "s s", "s s", 's', hardenedStone);
        
        // Tool recipes
        GameRegistry.addRecipe(new ItemStack(stonecraftSword), " s ", " s ", " w ", 's', hardenedStone, 'w', Item.stick);
        GameRegistry.addRecipe(new ItemStack(stonecraftHoe), "ss ", " w ", " w ", 's', hardenedStone, 'w', Item.stick);
        GameRegistry.addRecipe(new ItemStack(stonecraftPickaxe), "sss", " w ", " w ", 's', hardenedStone, 'w', Item.stick);
        GameRegistry.addRecipe(new ItemStack(stonecraftAxe), "ss ", "sw ", " w ", 's', hardenedStone, 'w', Item.stick);
        GameRegistry.addRecipe(new ItemStack(stonecraftShovel), " s ", " w ", " w ", 's', hardenedStone, 'w', Item.stick);
        
        //GameRegistry.addShapelessRecipe(new ItemStack(Block.sapling, 1, 1), new ItemStack(Block.dirt), new ItemStack(Block.dirt));
            

    
    }
    
    
    
    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {
            // Stub Method
    }
       
}
