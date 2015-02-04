/*  1:   */ package Packet;
/*  2:   */ 
/*  3:   */ import java.nio.ByteBuffer;
/*  4:   */ 
/*  5:   */ public class ShortSMSPacket
/*  6:   */   implements Packet
/*  7:   */ {
/*  8:   */   private int address_size;
/*  9:   */   private String address;
/* 10:   */   private long date;
/* 11:   */   private int body_size;
/* 12:   */   private String body;
/* 13:   */   
/* 14:   */   public ShortSMSPacket() {}
/* 15:   */   
/* 16:   */   public ShortSMSPacket(String ad, long dat, String body)
/* 17:   */   {
/* 18:37 */     this.address = ad;
/* 19:38 */     this.address_size = ad.length();
/* 20:39 */     this.date = dat;
/* 21:40 */     this.body = body;
/* 22:41 */     this.body_size = this.body.length();
/* 23:   */   }
/* 24:   */   
/* 25:   */   public byte[] build()
/* 26:   */   {
/* 27:46 */     ByteBuffer b = ByteBuffer.allocate(8 + this.address.length() + 4 + 4 + 8 + 4 + this.body.length() + 4);
/* 28:47 */     b.putInt(this.address_size);
/* 29:48 */     b.put(this.address.getBytes());
/* 30:49 */     b.putLong(this.date);
/* 31:50 */     b.putInt(this.body_size);
/* 32:51 */     b.put(this.body.getBytes());
/* 33:52 */     return b.array();
/* 34:   */   }
/* 35:   */   
/* 36:   */   public void parse(byte[] packet)
/* 37:   */   {
/* 38:56 */     ByteBuffer b = ByteBuffer.wrap(packet);
/* 39:57 */     this.address_size = b.getInt();
/* 40:58 */     byte[] tmp = new byte[this.address_size];
/* 41:59 */     b.get(tmp);
/* 42:60 */     this.address = new String(tmp);
/* 43:61 */     this.date = b.getLong();
/* 44:62 */     this.body_size = b.getInt();
/* 45:63 */     tmp = new byte[this.body_size];
/* 46:64 */     b.get(tmp);
/* 47:65 */     this.body = new String(tmp);
/* 48:   */   }
/* 49:   */   
/* 50:   */   public String getAddress()
/* 51:   */   {
/* 52:69 */     return this.address;
/* 53:   */   }
/* 54:   */   
/* 55:   */   public long getDate()
/* 56:   */   {
/* 57:73 */     return this.date;
/* 58:   */   }
/* 59:   */   
/* 60:   */   public String getBody()
/* 61:   */   {
/* 62:77 */     return this.body;
/* 63:   */   }
/* 64:   */ }


/* Location:           C:\Users\SmileyFacedHacker\Downloads\StillBorn RAT (Android)\StillBorn RAT (Android)\StillBornRAT.jar
 * Qualified Name:     Packet.ShortSMSPacket
 * JD-Core Version:    0.7.0.1
 */