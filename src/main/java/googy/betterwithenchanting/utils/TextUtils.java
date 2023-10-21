package googy.betterwithenchanting.utils;

import net.minecraft.core.net.command.TextFormatting;

public class TextUtils
{
	public static String format(String text, TextFormatting formatting)
	{
		return formatting.toString() + text + TextFormatting.RESET.toString();
	}

	public static String format(String text, TextFormatting... formattings)
	{
		StringBuilder f = new StringBuilder();

		for (TextFormatting formatting : formattings)
		{
			f.append(formatting.toString());
		}

		return f + text + TextFormatting.RESET.toString();
	}
}
