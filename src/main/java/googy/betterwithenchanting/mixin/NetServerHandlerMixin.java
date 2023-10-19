package googy.betterwithenchanting.mixin;

import googy.betterwithenchanting.network.packet.PacketEnchantItem;
import googy.betterwithenchanting.player.inventory.ContainerEnchantmentTable;
import net.minecraft.core.player.inventory.Container;
import net.minecraft.server.entity.player.EntityPlayerMP;
import net.minecraft.server.net.handler.NetServerHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(value = NetServerHandler.class, remap = false)
public class NetServerHandlerMixin implements INetServerHandler
{
	@Shadow private EntityPlayerMP playerEntity;


	@Override
	public void handleEnchantItem(PacketEnchantItem packet)
	{
		Container container = playerEntity.craftingInventory;

		if (!(container instanceof ContainerEnchantmentTable)) return;
		if (container.windowId != packet.windowId) return;

		ContainerEnchantmentTable enchantment = (ContainerEnchantmentTable) container;

		enchantment.enchantItem(playerEntity, packet.enchantmentOption);
	}
}
