package googy.betterwithenchanting.mixin;

import googy.betterwithenchanting.block.entity.TileEntityEnchantmentTable;
import googy.betterwithenchanting.gui.GuiEnchantmentTable;
import googy.betterwithenchanting.interfaces.mixins.IEntityPlayer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.EntityPlayerSP;
import net.minecraft.core.player.inventory.InventoryPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(value = EntityPlayerSP.class, remap = false)
public class EntityPlayerSPMixin implements IEntityPlayer
{

	@Shadow
	protected Minecraft mc;

	@Override
	public void displayGUIEnchantmentTable(TileEntityEnchantmentTable enchantmentTable)
	{
		mc.displayGuiScreen(new GuiEnchantmentTable(this.mc.thePlayer.inventory, enchantmentTable));
	}
}
