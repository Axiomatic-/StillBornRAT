package Packet;

public abstract interface Packet
{
  public abstract byte[] build();
  
  public abstract void parse(byte[] paramArrayOfByte);
}


/* Location:           C:\Users\SmileyFacedHacker\Downloads\StillBorn RAT (Android)\StillBorn RAT (Android)\StillBornRAT.jar
 * Qualified Name:     Packet.Packet
 * JD-Core Version:    0.7.0.1
 */