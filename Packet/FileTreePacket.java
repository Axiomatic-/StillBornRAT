/*  1:   */ package Packet;
/*  2:   */ 
/*  3:   */ import java.io.ByteArrayInputStream;
/*  4:   */ import java.io.ByteArrayOutputStream;
/*  5:   */ import java.io.IOException;
/*  6:   */ import java.io.ObjectInputStream;
/*  7:   */ import java.io.ObjectOutputStream;
/*  8:   */ import java.util.ArrayList;
/*  9:   */ import utils.MyFile;
/* 10:   */ 
/* 11:   */ public class FileTreePacket
/* 12:   */   implements Packet
/* 13:   */ {
/* 14:   */   private ArrayList<MyFile> list;
/* 15:   */   
/* 16:   */   public FileTreePacket() {}
/* 17:   */   
/* 18:   */   public FileTreePacket(ArrayList<MyFile> ar)
/* 19:   */   {
/* 20:39 */     this.list = ar;
/* 21:   */   }
/* 22:   */   
/* 23:   */   public byte[] build()
/* 24:   */   {
/* 25:   */     try
/* 26:   */     {
/* 27:44 */       ByteArrayOutputStream bos = new ByteArrayOutputStream();
/* 28:45 */       ObjectOutputStream out = new ObjectOutputStream(bos);
/* 29:46 */       out.writeObject(this.list);
/* 30:47 */       return bos.toByteArray();
/* 31:   */     }
/* 32:   */     catch (IOException e) {}
/* 33:49 */     return null;
/* 34:   */   }
/* 35:   */   
/* 36:   */   public void parse(byte[] packet)
/* 37:   */   {
/* 38:54 */     ByteArrayInputStream bis = new ByteArrayInputStream(packet);
/* 39:   */     try
/* 40:   */     {
/* 41:57 */       ObjectInputStream in = new ObjectInputStream(bis);
/* 42:58 */       this.list = ((ArrayList)in.readObject());
/* 43:   */     }
/* 44:   */     catch (Exception localException) {}
/* 45:   */   }
/* 46:   */   
/* 47:   */   public ArrayList<MyFile> getList()
/* 48:   */   {
/* 49:64 */     return this.list;
/* 50:   */   }
/* 51:   */ }


/* Location:           C:\Users\SmileyFacedHacker\Downloads\StillBorn RAT (Android)\StillBorn RAT (Android)\StillBornRAT.jar
 * Qualified Name:     Packet.FileTreePacket
 * JD-Core Version:    0.7.0.1
 */