package googy.betterwithenchanting.mixin;

import googy.betterwithenchanting.enchantment.EnchantmentData;
import googy.betterwithenchanting.utils.EnchantmentUtils;
import googy.betterwithenchanting.utils.TextUtils;
import net.minecraft.client.gui.GuiTooltip;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.lang.I18n;
import net.minecraft.core.net.command.TextFormatting;
import net.minecraft.core.player.inventory.slot.Slot;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

@Mixin(value = GuiTooltip.class, remap = false)
public class GuiTooltipMixin
{
	@Inject(at = @At("TAIL"), method = "getTooltipText(Lnet/minecraft/core/item/ItemStack;ZLnet/minecraft/core/player/inventory/slot/Slot;)Ljava/lang/String;", cancellable = true)
	public void onGetTooltipText(ItemStack stack, boolean showDescription, Slot slot, CallbackInfoReturnable<String> info)
	{
		String toolTip = info.getReturnValue();

		StringBuilder enchantmentText = new StringBuilder();

		List<EnchantmentData> enchantmentsData = EnchantmentUtils.getEnchantments(stack);

		for (EnchantmentData enchantData : enchantmentsData)
		{
			String enchantName = enchantData.enchantment == null ? "Unknown" : TextUtils.format(enchantData.enchantment.getName(), TextFormatting.LIGHT_GRAY);
			String enchantLevel = TextUtils.format(String.valueOf(enchantData.level), TextFormatting.YELLOW);
			enchantmentText.append(enchantName + " " + enchantLevel + "\n");
		}

		toolTip += "\n";
		toolTip += enchantmentText;

		info.setReturnValue(toolTip);
	}
}
