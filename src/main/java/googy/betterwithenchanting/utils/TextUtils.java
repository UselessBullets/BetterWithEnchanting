package googy.betterwithenchanting.utils;

import net.minecraft.core.net.command.TextFormatting;

public class TextUtils
{
	public static String format(String text, TextFormatting formatting)
	{
		return formatting.toString() + text + TextFormatting.RESET.toString();
	}

	public static String format(String text, TextFormatting... formatting)
	{
		StringBuilder format = new StringBuilder();

		for (TextFormatting f : formatting)
		{
			format.append(f.toString());
		}

		return format + text + TextFormatting.RESET.toString();
	}
}
