package googy.betterwithenchanting.block;

import googy.betterwithenchanting.BetterWithEnchanting;
import googy.betterwithenchanting.block.entity.TileEntityEnchantmentTable;
import googy.betterwithenchanting.gui.GuiEnchantmentTable;
import net.minecraft.client.Minecraft;
import net.minecraft.core.block.Block;
import net.minecraft.core.block.BlockTileEntity;
import net.minecraft.core.block.entity.TileEntity;
import net.minecraft.core.block.entity.TileEntityFurnace;
import net.minecraft.core.block.entity.TileEntityTrommel;
import net.minecraft.core.block.material.Material;
import net.minecraft.core.entity.player.EntityPlayer;
import net.minecraft.core.world.World;

public class BlockEnchantmentTable extends BlockTileEntity
{

	public BlockEnchantmentTable(String key, int id)
	{
		super(key, id, Material.stone);
		setBlockBounds(0, 0, 0, 1, 12f / 16, 1);
	}

	@Override
	public boolean isOpaqueCube()
	{
		return false;
	}

	@Override
	public boolean blockActivated(World world, int x, int y, int z, EntityPlayer player)
	{
		Minecraft mc = Minecraft.getMinecraft(Minecraft.class);

		if (!world.isClientSide) {
			TileEntityEnchantmentTable tileEntity = (TileEntityEnchantmentTable)world.getBlockTileEntity(x, y, z);
			mc.displayGuiScreen(new GuiEnchantmentTable(player.inventory, tileEntity));
		}

		return true;
	}

	@Override
	protected TileEntity getNewBlockEntity()
	{
		return new TileEntityEnchantmentTable();
	}
}
