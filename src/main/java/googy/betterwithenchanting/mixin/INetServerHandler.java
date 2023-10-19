package googy.betterwithenchanting.mixin;

import googy.betterwithenchanting.network.packet.PacketEnchantItem;

public interface INetServerHandler
{
	void handleEnchantItem(PacketEnchantItem packet);
}
