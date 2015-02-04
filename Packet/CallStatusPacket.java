/*  1:   */ package Packet;
/*  2:   */ 
/*  3:   */ import java.nio.ByteBuffer;
/*  4:   */ 
/*  5:   */ public class CallStatusPacket
/*  6:   */   implements Packet
/*  7:   */ {
/*  8:   */   int type;
/*  9:   */   String phonenumber;
/* 10:   */   
/* 11:   */   public CallStatusPacket() {}
/* 12:   */   
/* 13:   */   public CallStatusPacket(int type, String phone)
/* 14:   */   {
/* 15:42 */     this.type = type;
/* 16:43 */     this.phonenumber = phone;
/* 17:   */   }
/* 18:   */   
/* 19:   */   public byte[] build()
/* 20:   */   {
/* 21:   */     ByteBuffer b;
/* 22:48 */     if (this.phonenumber == null)
/* 23:   */     {
/* 24:49 */       ByteBuffer b = ByteBuffer.allocate(4);
/* 25:50 */       b.putInt(this.type);
/* 26:   */     }
/* 27:   */     else
/* 28:   */     {
/* 29:53 */       b = ByteBuffer.allocate(4 + this.phonenumber.length());
/* 30:54 */       b.putInt(this.type);
/* 31:55 */       b.put(this.phonenumber.getBytes());
/* 32:   */     }
/* 33:57 */     return b.array();
/* 34:   */   }
/* 35:   */   
/* 36:   */   public void parse(byte[] packet)
/* 37:   */   {
/* 38:61 */     ByteBuffer b = ByteBuffer.wrap(packet);
/* 39:62 */     this.type = b.getInt();
/* 40:63 */     if (b.hasRemaining())
/* 41:   */     {
/* 42:64 */       byte[] tmp = new byte[b.remaining()];
/* 43:65 */       b.get(tmp);
/* 44:66 */       this.phonenumber = new String(tmp);
/* 45:   */     }
/* 46:   */     else
/* 47:   */     {
/* 48:69 */       this.phonenumber = null;
/* 49:   */     }
/* 50:   */   }
/* 51:   */   
/* 52:   */   public int getType()
/* 53:   */   {
/* 54:73 */     return this.type;
/* 55:   */   }
/* 56:   */   
/* 57:   */   public String getPhonenumber()
/* 58:   */   {
/* 59:77 */     return this.phonenumber;
/* 60:   */   }
/* 61:   */ }


/* Location:           C:\Users\SmileyFacedHacker\Downloads\StillBorn RAT (Android)\StillBorn RAT (Android)\StillBornRAT.jar
 * Qualified Name:     Packet.CallStatusPacket
 * JD-Core Version:    0.7.0.1
 */