package googy.betterwithenchanting.player.inventory.slot;

import net.minecraft.core.item.Item;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.player.inventory.IInventory;
import net.minecraft.core.player.inventory.slot.Slot;
import java.util.stream.IntStream;

public class EnchantFuelSlot extends Slot
{
	int[] fuelIds = {
		Item.olivine.id
	};

	public EnchantFuelSlot(IInventory inventory, int id, int x, int y)
	{
		super(inventory, id, x, y);
	}

	@Override
	public boolean canPutStackInSlot(ItemStack itemstack)
	{
		return itemstack != null &&
			itemstack.getItem() != null &&
			IntStream.of(fuelIds).anyMatch(id -> itemstack.getItem().id == id);

	}
}
