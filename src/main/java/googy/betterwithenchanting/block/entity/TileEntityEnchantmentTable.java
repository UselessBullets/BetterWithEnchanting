package googy.betterwithenchanting.block.entity;

import com.mojang.nbt.CompoundTag;
import com.mojang.nbt.ListTag;
import googy.betterwithenchanting.Global;
import net.minecraft.core.block.entity.TileEntity;
import net.minecraft.core.entity.player.EntityPlayer;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.player.inventory.IInventory;

public class TileEntityEnchantmentTable extends TileEntity implements IInventory
{
	protected ItemStack[] items = new ItemStack[2];

	@Override
	public void readFromNBT(CompoundTag tagCompound)
	{
		super.readFromNBT(tagCompound);

		ListTag itemList = tagCompound.getList("Items");
		for (int i = 0; i < itemList.tagCount(); i++) {

			CompoundTag itemTag = (CompoundTag)itemList.tagAt(i);
			byte slot = itemTag.getByte("Slot");

			if (slot >= 0 && slot < items.length)
				items[slot] = ItemStack.readItemStackFromNbt(itemTag);
		}
	}

	@Override
	public void writeToNBT(CompoundTag tagCompound)
	{
		super.writeToNBT(tagCompound);

		ListTag itemsTag = new ListTag();
		for (int i = 0; i < items.length; i++) {
			if (items[i] == null) continue;

			CompoundTag itemTag = new CompoundTag();
			itemTag.putByte("Slot", (byte)i);

			items[i].writeToNBT(itemTag);
			itemsTag.addTag(itemTag);
		}
		tagCompound.put("Items", itemsTag);
	}

	@Override
	public int getSizeInventory()
	{
		return 2;
	}

	@Override
	public ItemStack getStackInSlot(int i)
	{
		return items[i];
	}

	@Override
	public ItemStack decrStackSize(int i, int amount)
	{
		if (items[i] == null) return null;

		if (items[i].stackSize <= amount)
		{
			ItemStack itemstack = this.items[i];
			items[i] = null;
			return itemstack;
		}

		ItemStack itemstack = items[i].splitStack(amount);
		if (items[i].stackSize <= 0) {
			items[i] = null;
		}

		return itemstack;
	}

	@Override
	public void setInventorySlotContents(int slot, ItemStack stack)
	{
		items[slot] = stack;
		if (stack != null && stack.stackSize > getInventoryStackLimit()) {
			stack.stackSize = getInventoryStackLimit();
		}
	}

	@Override
	public String getInvName()
	{
		return Global.ENCHANTMENT_TABLE_NAME;
	}

	@Override
	public int getInventoryStackLimit()
	{
		return 64;
	}

	@Override
	public boolean canInteractWith(EntityPlayer player)
	{
		if (worldObj.getBlockTileEntity(x, y, z) != this) {
			return false;
		}

		return player.distanceToSqr(x + 0.5, y + 0.5, z + 0.5) <= 64.0;
	}

	@Override
	public void sortInventory() {

	}
}
