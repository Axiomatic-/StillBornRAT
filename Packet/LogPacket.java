/*  1:   */ package Packet;
/*  2:   */ 
/*  3:   */ import java.nio.ByteBuffer;
/*  4:   */ 
/*  5:   */ public class LogPacket
/*  6:   */   implements Packet
/*  7:   */ {
/*  8:   */   long date;
/*  9:   */   byte type;
/* 10:   */   String message;
/* 11:   */   
/* 12:   */   public LogPacket() {}
/* 13:   */   
/* 14:   */   public LogPacket(long date, byte type, String message)
/* 15:   */   {
/* 16:35 */     this.date = date;
/* 17:36 */     this.type = type;
/* 18:37 */     this.message = message;
/* 19:   */   }
/* 20:   */   
/* 21:   */   public byte[] build()
/* 22:   */   {
/* 23:41 */     ByteBuffer b = ByteBuffer.allocate(9 + this.message.length());
/* 24:42 */     b.putLong(this.date);
/* 25:43 */     b.put(this.type);
/* 26:44 */     b.put(this.message.getBytes());
/* 27:45 */     return b.array();
/* 28:   */   }
/* 29:   */   
/* 30:   */   public void parse(byte[] packet)
/* 31:   */   {
/* 32:49 */     ByteBuffer b = ByteBuffer.wrap(packet);
/* 33:50 */     this.date = b.getLong();
/* 34:51 */     this.type = b.get();
/* 35:52 */     byte[] tmp = new byte[b.remaining()];
/* 36:53 */     b.get(tmp);
/* 37:54 */     this.message = new String(tmp);
/* 38:   */   }
/* 39:   */   
/* 40:   */   public long getDate()
/* 41:   */   {
/* 42:58 */     return this.date;
/* 43:   */   }
/* 44:   */   
/* 45:   */   public byte getType()
/* 46:   */   {
/* 47:62 */     return this.type;
/* 48:   */   }
/* 49:   */   
/* 50:   */   public String getMessage()
/* 51:   */   {
/* 52:66 */     return this.message;
/* 53:   */   }
/* 54:   */ }


/* Location:           C:\Users\SmileyFacedHacker\Downloads\StillBorn RAT (Android)\StillBorn RAT (Android)\StillBornRAT.jar
 * Qualified Name:     Packet.LogPacket
 * JD-Core Version:    0.7.0.1
 */