package googy.betterwithenchanting;

import googy.betterwithenchanting.block.BlockEnchantmentTable;
import googy.betterwithenchanting.block.entity.TileEntityEnchantmentTable;
import googy.betterwithenchanting.network.packet.PacketEnchantItem;
import net.fabricmc.api.ModInitializer;
import net.minecraft.core.block.Block;
import net.minecraft.core.block.tag.BlockTags;
import net.minecraft.core.item.Item;
import net.minecraft.core.sound.BlockSound;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import turniplabs.halplibe.helper.BlockBuilder;
import turniplabs.halplibe.helper.EntityHelper;
import turniplabs.halplibe.helper.NetworkHelper;
import turniplabs.halplibe.helper.RecipeBuilder;
import turniplabs.halplibe.util.GameStartEntrypoint;
import turniplabs.halplibe.util.RecipeEntrypoint;


public class BetterWithEnchanting implements ModInitializer, GameStartEntrypoint, RecipeEntrypoint {
    public static final String MOD_ID = "betterwithenchanting";
    public static final Logger LOG = LoggerFactory.getLogger(MOD_ID);

	public static final Block enchantmentTable = new BlockBuilder(MOD_ID)
		.setBlockSound(new BlockSound("step.stone", "step.stone", 1.0f, 1.0f))
		.setHardness(5)
		.setResistance(1200)
		.setLuminance(7)
		.setTags(BlockTags.MINEABLE_BY_PICKAXE)
		.setBottomTexture(MOD_ID + ":block/enchantment_table/bottom")
		.setSideTextures(MOD_ID + ":block/enchantment_table/side")
		.setTopTexture(MOD_ID + ":block/enchantment_table/top")
		.build(new BlockEnchantmentTable("enchantmenttable", Global.config.getInt("enchantment_table_id")));

    @Override
    public void onInitialize() {
		NetworkHelper.register(PacketEnchantItem.class, true, false);
		LOG.info("BetterWithEnchanting initialized!");
    }

	@Override
	public void onRecipesReady() {
		RecipeBuilder.Shaped(MOD_ID)
			.setShape(
				" B ",
				"DCD",
				"CCC")
			.addInput('B', Item.book)
			.addInput('C', Block.cobbleStone)
			.addInput('D', Global.config.getBoolean("expensive_crafting") ? Block.blockDiamond : Item.diamond)
			.create("enchantment_table", enchantmentTable.getDefaultStack());
	}

	@Override
	public void initNamespaces() {
		RecipeBuilder.initNameSpace(MOD_ID);
	}

	@Override
	public void beforeGameStart() {
		EntityHelper.createTileEntity(TileEntityEnchantmentTable.class, "EnchantmentTable");
	}

	@Override
	public void afterGameStart() {

	}
}
