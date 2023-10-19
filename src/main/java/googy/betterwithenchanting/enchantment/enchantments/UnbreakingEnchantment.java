package googy.betterwithenchanting.enchantment.enchantments;

import googy.betterwithenchanting.enchantment.Enchantment;
import googy.betterwithenchanting.enchantment.EnchantmentTarget;

public class UnbreakingEnchantment extends Enchantment
{

	public UnbreakingEnchantment(String name, int id, Rarity weight, EnchantmentTarget target)
	{
		super(name, id, weight, target);
	}

	@Override
	public int getMinLevel()
	{
		return 1;
	}

	@Override
	public int getMaxLevel()
	{
		return 3;
	}
}
