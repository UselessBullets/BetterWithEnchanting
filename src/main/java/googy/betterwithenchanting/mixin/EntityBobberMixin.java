package googy.betterwithenchanting.mixin;

import googy.betterwithenchanting.BetterWithEnchanting;
import googy.betterwithenchanting.enchantment.Enchantments;
import googy.betterwithenchanting.utils.EnchantmentUtils;
import net.minecraft.core.block.Block;
import net.minecraft.core.entity.Entity;
import net.minecraft.core.entity.EntityBobber;
import net.minecraft.core.entity.player.EntityPlayer;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.util.helper.MathHelper;
import net.minecraft.core.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(value = EntityBobber.class, remap = false)
public abstract class EntityBobberMixin extends Entity
{
	public EntityBobberMixin(World world)
	{
		super(world);
	}


	@ModifyVariable(method = "tick", at = @At("STORE"), ordinal = 1)
	int getCatchRate(int catchRate)
	{
		if (catchRate != 500) return catchRate;

		EntityBobber thisBobber = (EntityBobber) (Object) this;

		ItemStack stack = thisBobber.angler.getCurrentEquippedItem();
		int baitLevel = EnchantmentUtils.getLevel(stack, Enchantments.bait);

		int rate = catchRate - (baitLevel * 100);

		boolean rainBonus = thisBobber.world.canBlockBeRainedOn(MathHelper.floor_double(thisBobber.x), MathHelper.floor_double(thisBobber.y) + 1, MathHelper.floor_double(thisBobber.z));
		boolean algaeRate = thisBobber.world.getBlockId(MathHelper.floor_double(thisBobber.x), MathHelper.floor_double(thisBobber.y) + 1, MathHelper.floor_double(thisBobber.z)) == Block.algae.id;

		int limit = 50; // smallest possible catchRate value
		if (rainBonus) limit += 200;
		if (algaeRate) limit += 100;

		rate = Math.max(rate, limit);

		return rate;
	}
}
