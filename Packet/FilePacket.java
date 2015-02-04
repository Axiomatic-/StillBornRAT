/*  1:   */ package Packet;
/*  2:   */ 
/*  3:   */ import java.nio.ByteBuffer;
/*  4:   */ 
/*  5:   */ public class FilePacket
/*  6:   */   implements Packet
/*  7:   */ {
/*  8:   */   byte[] data;
/*  9:   */   byte mf;
/* 10:   */   short numSeq;
/* 11:   */   
/* 12:   */   public FilePacket() {}
/* 13:   */   
/* 14:   */   public FilePacket(short num, byte mf, byte[] data)
/* 15:   */   {
/* 16:35 */     this.data = data;
/* 17:36 */     this.numSeq = num;
/* 18:37 */     this.mf = mf;
/* 19:   */   }
/* 20:   */   
/* 21:   */   public byte[] build()
/* 22:   */   {
/* 23:41 */     ByteBuffer b = ByteBuffer.allocate(this.data.length + 3);
/* 24:42 */     b.putShort(this.numSeq);
/* 25:43 */     b.put(this.mf);
/* 26:44 */     b.put(this.data);
/* 27:45 */     return b.array();
/* 28:   */   }
/* 29:   */   
/* 30:   */   public void parse(byte[] packet)
/* 31:   */   {
/* 32:49 */     ByteBuffer b = ByteBuffer.wrap(packet);
/* 33:   */     
/* 34:51 */     this.numSeq = b.getShort();
/* 35:52 */     this.mf = b.get();
/* 36:53 */     this.data = new byte[b.remaining()];
/* 37:54 */     b.get(this.data, 0, b.remaining());
/* 38:   */   }
/* 39:   */   
/* 40:   */   public byte[] getData()
/* 41:   */   {
/* 42:58 */     return this.data;
/* 43:   */   }
/* 44:   */   
/* 45:   */   public byte getMf()
/* 46:   */   {
/* 47:62 */     return this.mf;
/* 48:   */   }
/* 49:   */   
/* 50:   */   public short getNumSeq()
/* 51:   */   {
/* 52:66 */     return this.numSeq;
/* 53:   */   }
/* 54:   */ }


/* Location:           C:\Users\SmileyFacedHacker\Downloads\StillBorn RAT (Android)\StillBorn RAT (Android)\StillBornRAT.jar
 * Qualified Name:     Packet.FilePacket
 * JD-Core Version:    0.7.0.1
 */