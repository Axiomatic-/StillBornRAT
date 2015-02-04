/*   1:    */ package handler;
/*   2:    */ 
/*   3:    */ import Packet.TransportPacket;
/*   4:    */ import java.io.PrintStream;
/*   5:    */ import java.util.ArrayList;
/*   6:    */ 
/*   7:    */ public class TemporaryStorage
/*   8:    */ {
/*   9:    */   private ArrayList<byte[]> data_temp;
/*  10:    */   private byte[] final_data;
/*  11:    */   private int total_length;
/*  12:    */   private int last_packet_position;
/*  13:    */   private int size_counter;
/*  14:    */   private boolean end;
/*  15:    */   private short data_type;
/*  16:    */   
/*  17:    */   public TemporaryStorage()
/*  18:    */   {
/*  19: 62 */     this.data_temp = new ArrayList();
/*  20: 63 */     this.last_packet_position = -1;
/*  21: 64 */     this.size_counter = 0;
/*  22:    */   }
/*  23:    */   
/*  24:    */   public void reset()
/*  25:    */   {
/*  26: 69 */     this.data_temp = new ArrayList();
/*  27: 70 */     this.last_packet_position = -1;
/*  28: 71 */     this.size_counter = 0;
/*  29: 72 */     this.end = false;
/*  30:    */   }
/*  31:    */   
/*  32:    */   public short addPacket(TransportPacket packet)
/*  33:    */   {
/*  34: 80 */     if (packet.getNumSeq() != this.last_packet_position + 1) {
/*  35: 81 */       return 0;
/*  36:    */     }
/*  37: 85 */     if (!this.end)
/*  38:    */     {
/*  39: 89 */       this.total_length = packet.getTotalLength();
/*  40: 90 */       this.end = packet.isLast();
/*  41: 91 */       this.size_counter += packet.getLocalLength();
/*  42: 92 */       this.data_temp.add(packet.getData());
/*  43: 95 */       if (!this.end)
/*  44:    */       {
/*  45: 97 */         System.out.println("Paquet " + packet.getNumSeq());
/*  46: 98 */         this.last_packet_position += 1;
/*  47: 99 */         return 4;
/*  48:    */       }
/*  49:104 */       if (this.size_counter != this.total_length) {
/*  50:105 */         return 2;
/*  51:    */       }
/*  52:107 */       int i = 0;
/*  53:108 */       this.final_data = new byte[this.total_length];
/*  54:109 */       for (int n = 0; n < this.data_temp.size(); n++) {
/*  55:110 */         for (int p = 0; p < ((byte[])this.data_temp.get(n)).length; i++)
/*  56:    */         {
/*  57:111 */           this.final_data[i] = ((byte[])this.data_temp.get(n))[p];p++;
/*  58:    */         }
/*  59:    */       }
/*  60:113 */       return 3;
/*  61:    */     }
/*  62:119 */     return 1;
/*  63:    */   }
/*  64:    */   
/*  65:    */   public ArrayList<byte[]> getByteData()
/*  66:    */   {
/*  67:126 */     return this.data_temp;
/*  68:    */   }
/*  69:    */   
/*  70:    */   public byte[] getFinalData()
/*  71:    */   {
/*  72:131 */     return this.final_data;
/*  73:    */   }
/*  74:    */   
/*  75:    */   public int getLastPacketPositionReceived()
/*  76:    */   {
/*  77:136 */     return this.last_packet_position;
/*  78:    */   }
/*  79:    */   
/*  80:    */   public int getTotalSize()
/*  81:    */   {
/*  82:141 */     return this.total_length;
/*  83:    */   }
/*  84:    */ }


/* Location:           C:\Users\SmileyFacedHacker\Downloads\StillBorn RAT (Android)\StillBorn RAT (Android)\StillBornRAT.jar
 * Qualified Name:     handler.TemporaryStorage
 * JD-Core Version:    0.7.0.1
 */