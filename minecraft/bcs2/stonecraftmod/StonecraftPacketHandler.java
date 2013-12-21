package bcs2.stonecraftmod;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;

import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;
import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.Player;

public class StonecraftPacketHandler implements IPacketHandler{

	@Override
	public void onPacketData(INetworkManager manager, Packet250CustomPayload packet, Player player) {
		if (packet.channel.equals("StonecraftMod")) {
			handlePacket(packet);
		}
		
	}
	
	public void handlePacket(Packet250CustomPayload packet) {
		DataInputStream inputStream = new DataInputStream(new ByteArrayInputStream(packet.data));
		
		int randomint1 = 0;
		int randomint2 = 0;
		
		try {
			randomint1 = inputStream.readInt();
			randomint2 = inputStream.readInt();
		} 
		catch(IOException ex) {
			ex.printStackTrace();
		}
		
		System.out.println(randomint1 + " " + randomint2);
	}

}
