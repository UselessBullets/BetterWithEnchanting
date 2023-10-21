package googy.betterwithenchanting.mixin;

import googy.betterwithenchanting.enchantment.Enchantments;
import googy.betterwithenchanting.enchantment.enchantments.UnbreakingEnchantment;
import googy.betterwithenchanting.utils.EnchantmentUtils;
import net.minecraft.core.block.Block;
import net.minecraft.core.entity.Entity;
import net.minecraft.core.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
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

	@Inject(method = "damageItem", at = @At("HEAD"))
	public void damageItem(int damage, Entity entity, CallbackInfo info)
	{
		if (damage <= 0) return;

		int unbreakingLevel = EnchantmentUtils.getLevel((ItemStack)(Object)this, Enchantments.unbreaking);
		if (unbreakingLevel <= 0) return;

		for (int i = 0; i < damage; i++)
		{
			if (UnbreakingEnchantment.shouldNegateDamage((ItemStack)(Object)this, unbreakingLevel))
				damage--;
		}

		if (damage < 0)
			damage = 0;
	}

}
