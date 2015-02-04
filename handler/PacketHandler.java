package handler;

import Packet.Packet;
import server.Server;

public abstract interface PacketHandler
{
  public abstract void receive(Packet paramPacket, String paramString);
  
  public abstract void handlePacket(Packet paramPacket, String paramString, Server paramServer);
}


/* Location:           C:\Users\SmileyFacedHacker\Downloads\StillBorn RAT (Android)\StillBorn RAT (Android)\StillBornRAT.jar
 * Qualified Name:     handler.PacketHandler
 * JD-Core Version:    0.7.0.1
 */