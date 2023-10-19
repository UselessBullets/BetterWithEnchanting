package googy.betterwithenchanting.utils;

import com.mojang.nbt.CompoundTag;
import com.mojang.nbt.ListTag;
import googy.betterwithenchanting.Global;
import googy.betterwithenchanting.enchantment.Enchantment;
import googy.betterwithenchanting.enchantment.EnchantmentData;
import googy.betterwithenchanting.enchantment.Enchantments;
import net.minecraft.core.entity.player.EntityPlayer;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.util.helper.MathHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class EnchantmentUtils
{
	public static final String ENCHANTMENT_DATA_KEY = "enchantmentData";
	public static final String ENCHANTMENT_LIST_KEY = "enchantments";
	private static final String ID_KEY = "id";
	private static final String LEVEL_KEY = "lvl";

	public static CompoundTag createEnchantmentTag(Enchantment enchantment, int level)
	{
		CompoundTag tag = new CompoundTag();
		tag.putShort(ID_KEY, (byte)enchantment.id);
		tag.putShort(LEVEL_KEY, (byte)level);
		return tag;
	}

	public static CompoundTag createEnchantmentTag(EnchantmentData data)
	{
		return createEnchantmentTag(data.enchantment, data.level);
	}

	public static CompoundTag createEnchantmentsTag(List<EnchantmentData> enchantments)
	{
		ListTag enchantList = new ListTag();

		for (EnchantmentData data : enchantments)
		{
			CompoundTag enchantTag = createEnchantmentTag(data);
			enchantList.addTag(enchantTag);
		}

		CompoundTag enchantmentsTag = new CompoundTag();
		enchantmentsTag.putList(ENCHANTMENT_LIST_KEY, enchantList);

		return enchantmentsTag;
	}

	public static void setEnchantmentsTag(ItemStack stack, List<EnchantmentData> enchantments)
	{
		CompoundTag enchantmentsTag = createEnchantmentsTag(enchantments);
		stack.getData().putCompound(ENCHANTMENT_DATA_KEY, enchantmentsTag);
	}

	public static void addEnchantment(ItemStack stack, Enchantment enchantment, int level)
	{
		if (containsEnchantment(stack, enchantment))
			return;

		CompoundTag enchantTag = createEnchantmentTag(enchantment, level);

		ListTag enchantmentList = getEnchantmentsList(stack);
		enchantmentList.addTag(enchantTag);

		CompoundTag enchantmentsTag = new CompoundTag();
		enchantmentsTag.putList(ENCHANTMENT_LIST_KEY, enchantmentList);

		stack.getData().putCompound(ENCHANTMENT_DATA_KEY, enchantmentsTag);
	}

	public static void addEnchantment(ItemStack stack, EnchantmentData enchantmentData)
	{
		addEnchantment(stack, enchantmentData.enchantment, enchantmentData.level);
	}


	public static void addEnchantments(ItemStack stack, List<EnchantmentData> enchantments)
	{
		for (EnchantmentData enchantment : enchantments)
			addEnchantment(stack, enchantment);
	}

	public static boolean containsEnchantment(ItemStack stack, Enchantment enchantment)
	{
		ListTag enchantList = getEnchantmentsList(stack);

		for (int i = 0; i < enchantList.tagCount(); i++)
		{
			CompoundTag enchantTag = (CompoundTag)enchantList.tagAt(i);
			int id = enchantTag.getShort(ID_KEY);

			if (id == enchantment.id) return true;
		}

		return false;
	}

	public static ListTag getEnchantmentsList(ItemStack stack)
	{
		CompoundTag enchantData = stack.getData().getCompound(ENCHANTMENT_DATA_KEY);
		return enchantData.getList(ENCHANTMENT_LIST_KEY);
	}

	public static List<EnchantmentData> getEnchantments(ItemStack stack)
	{
		List<EnchantmentData> enchantments = new ArrayList<>();

		ListTag enchantList = getEnchantmentsList(stack);

		for (int i = 0; i < enchantList.tagCount(); i++)
		{
			CompoundTag enchantTag = (CompoundTag)enchantList.tagAt(i);
			int id = enchantTag.getShort(ID_KEY);
			int level = enchantTag.getShort(LEVEL_KEY);

			enchantments.add(new EnchantmentData(id, level));
		}

		return enchantments;
	}

	public static int getLevel(ItemStack stack, Enchantment enchantment)
	{
		if (stack == null || stack.getItem() == null || stack.stackSize <= 0)
			return 0;

		ListTag enchantList = getEnchantmentsList(stack);

		for (int i = 0; i < enchantList.tagCount(); i++)
		{
			CompoundTag enchantTag = (CompoundTag)enchantList.tagAt(i);
			int id = enchantTag.getShort(ID_KEY);
			int level = enchantTag.getShort(LEVEL_KEY);

			if (id == enchantment.id)
				return level;
		}

		return 0;
	}


	public static List<EnchantmentData> generateEnchantmentsList(Random random, ItemStack stack, int cost)
	{
		List<EnchantmentData> enchantments = new ArrayList<>();
		List<Enchantment> pool = Enchantments.getPossible(stack.getItem());

		double costPercentage = (double) cost / Global.MAX_ENCHANTMENT_COST;

		int amount = random.nextInt((int) Math.ceil(Global.MAX_ENCHANTS_PER_ENCHANT * costPercentage)) + 1;
		for (int i = 0; i < amount; i++)
		{
			if (pool.isEmpty()) break;

			Enchantment randomEnchantment = pool.get(random.nextInt(pool.size()));
			pool.remove(randomEnchantment);

			int randomLevel = (int) Math.round(costPercentage * (randomEnchantment.getMaxLevel() - randomEnchantment.getMinLevel()) + randomEnchantment.getMinLevel());
			randomLevel += random.nextInt(3) - 1;

			randomLevel = MathHelper.clamp(randomLevel, randomEnchantment.getMinLevel(), randomEnchantment.getMaxLevel());

			enchantments.add(new EnchantmentData(randomEnchantment, randomLevel));
		}

		return enchantments;
	}

	public static int calcEnchantmentCost(int enchantOption, int bookshelfs)
	{
		double percentage = (bookshelfs + Global.START_COST_OFFSET) / (15.0 + Global.START_COST_OFFSET);
		percentage *= (enchantOption + 1) / 3.0;

		return (int) (Global.MAX_ENCHANTMENT_COST * percentage);
	}

}
