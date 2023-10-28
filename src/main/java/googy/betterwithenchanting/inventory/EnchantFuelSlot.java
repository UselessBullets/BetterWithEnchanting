package googy.betterwithenchanting.inventory;

import net.minecraft.core.item.ItemDye;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.player.inventory.IInventory;
import net.minecraft.core.player.inventory.slot.Slot;

public class EnchantFuelSlot extends Slot
{
	public EnchantFuelSlot(IInventory inventory, int id, int x, int y)
	{
		super(inventory, id, x, y);
	}

	@Override
	public boolean canPutStackInSlot(ItemStack itemstack)
	{
		return itemstack != null &&
			itemstack.getItem() != null &&
			isLapis(itemstack);
	}

	boolean isLapis(ItemStack stack)
	{
		return stack.getItem().id == ItemDye.dye.id && stack.getMetadata() == 4;
	}
}
