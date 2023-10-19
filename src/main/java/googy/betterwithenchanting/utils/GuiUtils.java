package googy.betterwithenchanting.utils;

import googy.betterwithenchanting.BetterWithEnchanting;
import net.minecraft.client.Minecraft;

public class GuiUtils
{
	public static int getGuiTexture(String texture)
	{
		Minecraft mc = Minecraft.getMinecraft(Minecraft.class);
		return mc.renderEngine.getTexture("/assets/" + BetterWithEnchanting.MOD_ID + "/gui/" + texture);
	}

}
