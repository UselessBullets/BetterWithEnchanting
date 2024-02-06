package googy.betterwithenchanting.block;

import googy.betterwithenchanting.BetterWithEnchanting;
import googy.betterwithenchanting.block.entity.TileEntityEnchantmentTable;
import googy.betterwithenchanting.gui.GuiEnchantmentTable;
import googy.betterwithenchanting.interfaces.mixins.IEntityPlayer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.EntityPlayerSP;
import net.minecraft.core.block.Block;
import net.minecraft.core.block.BlockTileEntity;
import net.minecraft.core.block.entity.TileEntity;
import net.minecraft.core.block.entity.TileEntityFurnace;
import net.minecraft.core.block.entity.TileEntityTrommel;
import net.minecraft.core.block.material.Material;
import net.minecraft.core.entity.player.EntityPlayer;
import net.minecraft.core.world.World;
import net.minecraft.server.entity.player.EntityPlayerMP;

public class BlockEnchantmentTable extends BlockTileEntity
{

	public BlockEnchantmentTable(String key, int id)
	{
		super(key, id, Material.stone);
		setBlockBounds(0, 0, 0, 1, 12f / 16, 1);
	}

	@Override
	public boolean isSolidRender()
	{
		return false;
	}

	@Override
	public boolean blockActivated(World world, int x, int y, int z, EntityPlayer player)
	{
		if (world.isClientSide) return true;

		TileEntityEnchantmentTable tile = (TileEntityEnchantmentTable) world.getBlockTileEntity(x, y, z);
		if (tile != null)
			((IEntityPlayer)player).displayGUIEnchantmentTable(tile);

		return true;
	}

	@Override
	protected TileEntity getNewBlockEntity()
	{
		return new TileEntityEnchantmentTable();
	}
}
