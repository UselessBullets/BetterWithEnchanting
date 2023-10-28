package googy.betterwithenchanting;

import turniplabs.halplibe.util.ConfigHandler;

import java.util.Properties;

public class Global
{
	// Max enchanting cost
	public static int MAX_ENCHANTMENT_COST = 12000;

	public static int START_COST_OFFSET = 5;

	public static int ENCHANTMENT_WINDOW_TYPE_ID = 24;

	public static String ENCHANTMENT_TABLE_NAME = "Enchantment Table";

	public static int PACKET_ENCHANT_ID = 190;

	public static final ConfigHandler config;
	static {
		Properties prop = new Properties();
		prop.setProperty("expensive_crafting", "true");
		prop.setProperty("enchantment_table_id", "116");
		prop.setProperty("default_item_enchantability", "15");

		config = new ConfigHandler(BetterWithEnchanting.MOD_ID, prop);
	}
}
