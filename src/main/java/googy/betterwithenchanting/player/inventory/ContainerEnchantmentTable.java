package googy.betterwithenchanting.player.inventory;

import googy.betterwithenchanting.BetterWithEnchanting;
import googy.betterwithenchanting.block.entity.TileEntityEnchantmentTable;
import googy.betterwithenchanting.enchantment.Enchantment;
import googy.betterwithenchanting.enchantment.EnchantmentData;
import googy.betterwithenchanting.enchantment.Enchantments;
import googy.betterwithenchanting.player.inventory.slot.EnchantFuelSlot;
import googy.betterwithenchanting.utils.EnchantmentUtils;
import net.minecraft.core.InventoryAction;
import net.minecraft.core.block.Block;
import net.minecraft.core.entity.player.EntityPlayer;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.player.gamemode.Gamemode;
import net.minecraft.core.player.inventory.Container;
import net.minecraft.core.player.inventory.IInventory;
import net.minecraft.core.player.inventory.InventoryPlayer;
import net.minecraft.core.player.inventory.slot.Slot;
import net.minecraft.core.world.World;

import java.util.List;
import java.util.Random;

public class ContainerEnchantmentTable extends Container
{
	public TileEntityEnchantmentTable enchantmentTable;
	public int[] enchantCost = new int[3];

	private Random random = new Random();

	public ContainerEnchantmentTable(InventoryPlayer inventoryplayer, TileEntityEnchantmentTable enchantmentTable)
	{
		this.enchantmentTable = enchantmentTable;

		addSlot(new Slot(enchantmentTable, 0, 15, 47));
		addSlot(new EnchantFuelSlot(enchantmentTable, 1, 35, 47));

		for (int i = 0; i < 3; ++i) {
			for (int j = 0; j < 9; ++j) {
				this.addSlot(new Slot(inventoryplayer, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
			}
		}

		for (int i = 0; i < 9; ++i) {
			this.addSlot(new Slot(inventoryplayer, i, 8 + i * 18, 142));
		}
	}


	public boolean enchantItem(EntityPlayer player, int enchantOption)
	{
		if (!playerCanEnchant(player, enchantOption)) return false;

		int cost = enchantCost[enchantOption];
		if (player.gamemode.id != Gamemode.creative.id)
			player.score -= cost;

		ItemStack stack = getSlot(0).getStack();
		List<EnchantmentData> enchantments = EnchantmentUtils.generateEnchantmentsList(random, stack, cost);

		EnchantmentUtils.addEnchantments(stack, enchantments);
		updateInventory();

		return true;
	}

	@Override
	public void onCraftMatrixChanged(IInventory iinventory)
	{
		ItemStack stack = getSlot(0).getStack();
		if (stack == null) return;

		List<Enchantment> pool = Enchantments.getPossible(stack.getItem());
		if (pool.isEmpty()) return;

		World world = enchantmentTable.worldObj;

		int posX = enchantmentTable.xCoord;
		int posY = enchantmentTable.yCoord;
		int posZ = enchantmentTable.zCoord;

		int bookshelfs = 0;

		for (int x = -1; x <= 1; x++) {
			for (int z = -1; z <= 1; z++) {

				if (x == 0 && z == 0) continue;

				if (!world.isAirBlock(posX + x, posY, posZ + z) || !world.isAirBlock(posX + x, posY + 1, posZ + z)) continue; // something obstructing the bookshelf

				int cornerBottom = world.getBlockId(posX + x * 2, posY, posZ + z * 2);
				int cornerTop = world.getBlockId(posX + x * 2, posY + 1, posZ + z * 2);

				if (cornerBottom == Block.bookshelfPlanksOak.id) bookshelfs++;
				if (cornerTop == Block.bookshelfPlanksOak.id) bookshelfs++;

				if (x == 0 || z == 0) continue;

				int sideXBottom = world.getBlockId(posX + x * 2, posY, posZ + z);
				int sideXTop = world.getBlockId(posX + x * 2, posY + 1, posZ + z);
				int sideZBottom = world.getBlockId(posX + x, posY, posZ + z * 2);
				int sideZTop = world.getBlockId(posX + x, posY + 1, posZ + z * 2);

				if (sideZBottom == Block.bookshelfPlanksOak.id) bookshelfs++;
				if (sideZTop == Block.bookshelfPlanksOak.id) bookshelfs++;
				if (sideXBottom == Block.bookshelfPlanksOak.id) bookshelfs++;
				if (sideXTop == Block.bookshelfPlanksOak.id) bookshelfs++;
			}
		}

		if (bookshelfs > 15)
			bookshelfs = 15;

		BetterWithEnchanting.LOG.info("bookshelfs: " + bookshelfs);
		for (int i = 0; i < 3; i++)
		{
			enchantCost[i] = EnchantmentUtils.calcEnchantmentCost(i, bookshelfs);
			BetterWithEnchanting.LOG.info(i + " = " + enchantCost[i]);
		}
		BetterWithEnchanting.LOG.info("==============================");

	}

	public boolean playerCanEnchant(EntityPlayer player, int option)
	{
		return getSlot(0).hasStack() &&
			EnchantmentUtils.getEnchantments(getSlot(0).getStack()).isEmpty() &&
			(player.score >= enchantCost[option] || player.gamemode.id == Gamemode.creative.id);
	}

	@Override
	public List<Integer> getMoveSlots(InventoryAction inventoryAction, Slot slot, int i, EntityPlayer entityPlayer)
	{
		return null;
	}

	@Override
	public List<Integer> getTargetSlots(InventoryAction inventoryAction, Slot slot, int i, EntityPlayer entityPlayer)
	{
		return null;
	}

	@Override
	public boolean isUsableByPlayer(EntityPlayer player)
	{
		return enchantmentTable.canInteractWith(player);
	}
}
