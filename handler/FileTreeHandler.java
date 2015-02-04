/*  1:   */ package handler;
/*  2:   */ 
/*  3:   */ import Packet.FileTreePacket;
/*  4:   */ import Packet.Packet;
/*  5:   */ import gui.GUI;
/*  6:   */ import java.util.HashMap;
/*  7:   */ import server.Server;
/*  8:   */ 
/*  9:   */ public class FileTreeHandler
/* 10:   */   implements PacketHandler
/* 11:   */ {
/* 12:   */   private GUI gui;
/* 13:   */   private int channel;
/* 14:   */   private String imei;
/* 15:   */   
/* 16:   */   public FileTreeHandler(int chan, String imei, GUI gui)
/* 17:   */   {
/* 18:38 */     this.channel = chan;
/* 19:39 */     this.imei = imei;
/* 20:40 */     this.gui = gui;
/* 21:   */   }
/* 22:   */   
/* 23:   */   public void receive(Packet p, String imei) {}
/* 24:   */   
/* 25:   */   public void handlePacket(Packet p, String temp_imei, Server c)
/* 26:   */   {
/* 27:50 */     this.gui.logTxt("Dosya dizin bilgisi alındı");
/* 28:51 */     ((ChannelDistributionHandler)c.getChannelHandlerMap().get(this.imei)).removeListener(this.channel);
/* 29:52 */     FileTreePacket packet = (FileTreePacket)p;
/* 30:   */     
/* 31:   */ 
/* 32:   */ 
/* 33:   */ 
/* 34:   */ 
/* 35:   */ 
/* 36:59 */     this.gui.updateFileTree(this.imei, packet.getList());
/* 37:   */   }
/* 38:   */ }


/* Location:           C:\Users\SmileyFacedHacker\Downloads\StillBorn RAT (Android)\StillBorn RAT (Android)\StillBornRAT.jar
 * Qualified Name:     handler.FileTreeHandler
 * JD-Core Version:    0.7.0.1
 */