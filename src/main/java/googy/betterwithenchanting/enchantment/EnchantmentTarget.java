package googy.betterwithenchanting.enchantment;

import net.minecraft.core.item.Item;
import net.minecraft.core.item.ItemArmor;
import net.minecraft.core.item.ItemBow;
import net.minecraft.core.item.ItemFishingRod;
import net.minecraft.core.item.tool.*;

public class EnchantmentTarget
{
	public static final Armor ARMOR = new Armor();
	public static final ArmorHead ARMOR_HEAD = new ArmorHead();
	public static final ArmorChest ARMOR_CHEST = new ArmorChest();
	public static final ArmorLegs ARMOR_LEGS = new ArmorLegs();
	public static final ArmorFeet ARMOR_FEET = new ArmorFeet();
	public static final Digger DIGGER = new Digger();
	public static final Sword SWORD = new Sword();
	public static final Bow BOW = new Bow();
	public static final FishingRod FISHING_ROD = new FishingRod();
	public static final Breakable BREAKABLE = new Breakable();


	public boolean canEnchant(Item item)
	{
		return false;
	}

	public static class Armor extends EnchantmentTarget
	{
		public boolean canEnchant(Item item)
		{
			return item instanceof ItemArmor;
		}
	}

	public static class ArmorHead extends Armor
	{
		public boolean canEnchant(Item item)
		{
			return ((ItemArmor)item).armorPiece == 0;
		}
	}

	public static class ArmorChest extends Armor
	{
		public boolean canEnchant(Item item)
		{
			return ((ItemArmor)item).armorPiece == 1;
		}
	}

	public static class ArmorLegs extends Armor
	{
		public boolean canEnchant(Item item)
		{
			return ((ItemArmor)item).armorPiece == 2;
		}
	}

	public static class ArmorFeet extends Armor
	{
		public boolean canEnchant(Item item)
		{
			return ((ItemArmor)item).armorPiece == 3;
		}
	}

	public static class Digger extends EnchantmentTarget
	{
		public boolean canEnchant(Item item)
		{
			return item instanceof ItemToolPickaxe ||
				item instanceof ItemToolAxe ||
				item instanceof ItemToolShovel;
		}
	}

	public static class Sword extends EnchantmentTarget
	{
		public boolean canEnchant(Item item)
		{
			return item instanceof ItemToolSword;
		}
	}

	public static class Bow extends EnchantmentTarget
	{
		public boolean canEnchant(Item item)
		{
			return item instanceof ItemBow;
		}
	}

	public static class FishingRod extends EnchantmentTarget
	{
		public boolean canEnchant(Item item)
		{
			return item instanceof ItemFishingRod;
		}
	}

	public static class Breakable extends EnchantmentTarget
	{
		public boolean canEnchant(Item item)
		{
			return item.isDamagable();
		}
	}
}
