package googy.betterwithenchanting.enchantment;

import googy.betterwithenchanting.enchantment.enchantments.HasteEnchantment;
import googy.betterwithenchanting.enchantment.enchantments.UnbreakingEnchantment;
import net.minecraft.core.item.Item;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Enchantments
{
	public static final Map<String, Integer> nameToIdMap = new HashMap<String, Integer>();

	public static Enchantment[] enchantmentList = new Enchantment[128];

	public static Enchantment getById(int i)
	{
		return enchantmentList[i];
	}

	public static List<Enchantment> getPossible(Item item)
	{
		List<Enchantment> enchantments = new ArrayList<>();

		for (Enchantment enchant : enchantmentList)
		{
			if (enchant == null) continue;
			if (enchant.canEnchant(item)) enchantments.add(enchant);
		}

		return enchantments;
	}

	public static Enchantment haste = new HasteEnchantment("haste", 1, Enchantment.Rarity.RARE, EnchantmentTarget.DIGGER);
	public static Enchantment unbreaking = new UnbreakingEnchantment("unbreaking", 2, Enchantment.Rarity.COMMON, EnchantmentTarget.BREAKABLE);

}
