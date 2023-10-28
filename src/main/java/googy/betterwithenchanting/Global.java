package googy.betterwithenchanting;

import turniplabs.halplibe.util.ConfigHandler;

import java.util.Properties;

public class Global
{
	public static int START_COST_OFFSET = 5;

	public static String ENCHANTMENT_TABLE_NAME = "Enchantment Table";

	public static final ConfigHandler config;
	static {
		Properties prop = new Properties();
		prop.setProperty("max_enchantment_cost", "12000");
		prop.setProperty("enchantment_window_type_id", "24");
		prop.setProperty("packet_enchant_id", "190");
		prop.setProperty("enchantment_table_id", "116");

		prop.setProperty("expensive_crafting", "true");
		prop.setProperty("default_item_enchantability", "15");

		config = new ConfigHandler(BetterWithEnchanting.MOD_ID, prop);
	}
}
