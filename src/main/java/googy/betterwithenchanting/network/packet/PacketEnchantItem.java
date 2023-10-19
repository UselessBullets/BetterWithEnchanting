package googy.betterwithenchanting.network.packet;

import net.minecraft.core.net.handler.NetHandler;
import net.minecraft.core.net.packet.Packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class PacketEnchantItem extends Packet
{
	public int windowId;
	public int enchantmentOption;

	public PacketEnchantItem(int windowId, int enchantmentOption)
	{
		this.windowId = windowId;
		this.enchantmentOption = enchantmentOption;
	}


	@Override
	public void readPacketData(DataInputStream dis) throws IOException
	{
		this.windowId = dis.readInt();
		this.enchantmentOption = dis.readInt();
	}

	@Override
	public void writePacketData(DataOutputStream dos) throws IOException
	{
		dos.writeInt(this.windowId);
		dos.writeInt(this.enchantmentOption);
	}

	@Override
	public void processPacket(NetHandler netHandler)
	{

	}

	@Override
	public int getPacketSize()
	{
		return 8;
	}
}
