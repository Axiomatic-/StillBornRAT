/*  1:   */ package Packet;
/*  2:   */ 
/*  3:   */ import java.io.ByteArrayInputStream;
/*  4:   */ import java.io.ByteArrayOutputStream;
/*  5:   */ import java.io.IOException;
/*  6:   */ import java.io.ObjectInputStream;
/*  7:   */ import java.io.ObjectOutputStream;
/*  8:   */ import java.util.ArrayList;
/*  9:   */ 
/* 10:   */ public class CallLogPacket
/* 11:   */   implements Packet
/* 12:   */ {
/* 13:   */   private ArrayList<CallPacket> list;
/* 14:   */   
/* 15:   */   public CallLogPacket() {}
/* 16:   */   
/* 17:   */   public CallLogPacket(ArrayList<CallPacket> ar)
/* 18:   */   {
/* 19:38 */     this.list = ar;
/* 20:   */   }
/* 21:   */   
/* 22:   */   public byte[] build()
/* 23:   */   {
/* 24:   */     try
/* 25:   */     {
/* 26:43 */       ByteArrayOutputStream bos = new ByteArrayOutputStream();
/* 27:44 */       ObjectOutputStream out = new ObjectOutputStream(bos);
/* 28:45 */       out.writeObject(this.list);
/* 29:46 */       return bos.toByteArray();
/* 30:   */     }
/* 31:   */     catch (IOException e) {}
/* 32:48 */     return null;
/* 33:   */   }
/* 34:   */   
/* 35:   */   public void parse(byte[] packet)
/* 36:   */   {
/* 37:53 */     ByteArrayInputStream bis = new ByteArrayInputStream(packet);
/* 38:   */     try
/* 39:   */     {
/* 40:56 */       ObjectInputStream in = new ObjectInputStream(bis);
/* 41:57 */       this.list = ((ArrayList)in.readObject());
/* 42:   */     }
/* 43:   */     catch (Exception localException) {}
/* 44:   */   }
/* 45:   */   
/* 46:   */   public ArrayList<CallPacket> getList()
/* 47:   */   {
/* 48:63 */     return this.list;
/* 49:   */   }
/* 50:   */ }


/* Location:           C:\Users\SmileyFacedHacker\Downloads\StillBorn RAT (Android)\StillBorn RAT (Android)\StillBornRAT.jar
 * Qualified Name:     Packet.CallLogPacket
 * JD-Core Version:    0.7.0.1
 */