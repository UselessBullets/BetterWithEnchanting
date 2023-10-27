package googy.betterwithenchanting.interfaces.mixins;

import googy.betterwithenchanting.block.entity.TileEntityEnchantmentTable;
import net.minecraft.core.player.inventory.IInventory;

public interface IEntityPlayer
{
	void displayGUIEnchantmentTable(TileEntityEnchantmentTable enchantmentTable);
}
