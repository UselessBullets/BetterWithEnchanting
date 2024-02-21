package googy.betterwithenchanting.mixin;

import googy.betterwithenchanting.BetterWithEnchanting;
import googy.betterwithenchanting.enchantment.Enchantments;
import googy.betterwithenchanting.utils.EnchantmentUtils;
import net.minecraft.core.entity.Entity;
import net.minecraft.core.entity.EntityLiving;
import net.minecraft.core.entity.player.EntityPlayer;
import net.minecraft.core.util.helper.DamageType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = EntityLiving.class, remap = false)
public class EntityLivingMixin
{
	@Inject(method = "hurt", at = @At(value = "RETURN"))
	public void getHeartsFlashTime(Entity attacker, int damage, DamageType type, CallbackInfoReturnable<Boolean> info)
	{
		if (!(attacker instanceof EntityPlayer)) return;
		EntityPlayer player = (EntityPlayer)attacker;

		EntityLiving thisLiving = (EntityLiving) (Object) this;

		BetterWithEnchanting.LOG.info(String.valueOf(thisLiving.heartsFlashTime));

		int quickstrikeLevel = EnchantmentUtils.getLevel(player.getHeldItem(), Enchantments.quickstrike);
		if (quickstrikeLevel <= 0) return;

		if (thisLiving.heartsFlashTime == thisLiving.heartsHalvesLife)
			thisLiving.heartsFlashTime = (int) (thisLiving.heartsHalvesLife * 0.75);

	}
}
