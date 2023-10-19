package googy.betterwithenchanting.mixin;

import googy.betterwithenchanting.enchantment.Enchantments;
import googy.betterwithenchanting.utils.EnchantmentUtils;
import net.minecraft.core.block.Block;
import net.minecraft.core.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = ItemStack.class, remap = false)
public class ItemStackMixin
{
	@Inject(method = "getStrVsBlock", at = @At("TAIL"), cancellable = true)
	public void getStrVsBlock(Block block, CallbackInfoReturnable<Float> info)
	{
		int hasteLevel = EnchantmentUtils.getLevel((ItemStack)(Object)this, Enchantments.haste);

		info.setReturnValue(info.getReturnValue() + (hasteLevel * hasteLevel + 1));
	}
}
