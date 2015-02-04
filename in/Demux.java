/*  1:   */ package in;
/*  2:   */ 
/*  3:   */ import Packet.TransportPacket;
/*  4:   */ import inout.Controler;
/*  5:   */ import java.nio.ByteBuffer;
/*  6:   */ 
/*  7:   */ public class Demux
/*  8:   */ {
/*  9:   */   private Controler controler;
/* 10:   */   private TransportPacket p;
/* 11:   */   private String imei;
/* 12:   */   private ByteBuffer buffer;
/* 13:   */   private boolean partialDataExpected;
/* 14:   */   private boolean reading;
/* 15:   */   
/* 16:   */   public Demux(Controler s, String i)
/* 17:   */   {
/* 18:45 */     this.imei = i;
/* 19:46 */     this.controler = s;
/* 20:47 */     this.reading = true;
/* 21:48 */     this.partialDataExpected = false;
/* 22:   */   }
/* 23:   */   
/* 24:   */   public boolean receive(ByteBuffer buffer)
/* 25:   */     throws Exception
/* 26:   */   {
/* 27:55 */     while (this.reading)
/* 28:   */     {
/* 29:58 */       if (!this.partialDataExpected) {
/* 30:62 */         if (buffer.limit() - buffer.position() < 15) {
/* 31:65 */           return true;
/* 32:   */         }
/* 33:   */       }
/* 34:70 */       if (this.partialDataExpected)
/* 35:   */       {
/* 36:71 */         this.partialDataExpected = this.p.parseCompleter(buffer);
/* 37:   */       }
/* 38:   */       else
/* 39:   */       {
/* 40:74 */         this.p = new TransportPacket();
/* 41:75 */         this.partialDataExpected = this.p.parse(buffer);
/* 42:   */       }
/* 43:80 */       if (this.partialDataExpected) {
/* 44:81 */         return true;
/* 45:   */       }
/* 46:83 */       this.controler.Storage(this.p, this.imei);
/* 47:   */     }
/* 48:88 */     this.reading = true;
/* 49:89 */     return true;
/* 50:   */   }
/* 51:   */   
/* 52:   */   public void setImei(String i)
/* 53:   */   {
/* 54:93 */     this.imei = i;
/* 55:   */   }
/* 56:   */ }


/* Location:           C:\Users\SmileyFacedHacker\Downloads\StillBorn RAT (Android)\StillBorn RAT (Android)\StillBornRAT.jar
 * Qualified Name:     in.Demux
 * JD-Core Version:    0.7.0.1
 */