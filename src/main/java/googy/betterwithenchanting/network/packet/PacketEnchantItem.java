package googy.betterwithenchanting.network.packet;

import googy.betterwithenchanting.interfaces.mixins.INetServerHandler;
import net.minecraft.core.net.handler.NetHandler;
import net.minecraft.core.net.packet.Packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class PacketEnchantItem extends Packet
{
	public int windowId;
	public int enchantmentOption;

	public PacketEnchantItem()
	{
	}

	public PacketEnchantItem(int windowId, int enchantmentOption)
	{
		this.windowId = windowId;
		this.enchantmentOption = enchantmentOption;
	}


	@Override
	public void readPacketData(DataInputStream dis) throws IOException
	{
		this.windowId = dis.readByte();
		this.enchantmentOption = dis.readByte();
	}

	@Override
	public void writePacketData(DataOutputStream dos) throws IOException
	{
		dos.writeByte(this.windowId);
		dos.writeByte(this.enchantmentOption);
	}

	@Override
	public void processPacket(NetHandler netHandler)
	{
		((INetServerHandler)netHandler).handleEnchantItem(this);
	}

	@Override
	public int getPacketSize()
	{
		return 2;
	}
}
