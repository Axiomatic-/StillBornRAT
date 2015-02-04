/*  1:   */ package handler;
/*  2:   */ 
/*  3:   */ import Packet.LogPacket;
/*  4:   */ import Packet.Packet;
/*  5:   */ import gui.GUI;
/*  6:   */ import java.util.HashMap;
/*  7:   */ import server.Server;
/*  8:   */ 
/*  9:   */ public class ClientLogHandler
/* 10:   */   implements PacketHandler
/* 11:   */ {
/* 12:   */   private GUI gui;
/* 13:   */   private int channel;
/* 14:   */   private String imei;
/* 15:   */   
/* 16:   */   public ClientLogHandler(int channel, String imei, GUI gui)
/* 17:   */   {
/* 18:34 */     this.gui = gui;
/* 19:35 */     this.channel = channel;
/* 20:36 */     this.imei = imei;
/* 21:   */   }
/* 22:   */   
/* 23:   */   public void receive(Packet p, String imei) {}
/* 24:   */   
/* 25:   */   public void handlePacket(Packet p, String temp_imei, Server c)
/* 26:   */   {
/* 27:47 */     ((ChannelDistributionHandler)c.getChannelHandlerMap().get(this.imei)).getStorage(this.channel).reset();
/* 28:48 */     LogPacket packet = (LogPacket)p;
/* 29:49 */     if (packet.getType() == 0) {
/* 30:49 */       this.gui.clientLogTxt(this.imei, packet.getDate(), packet.getMessage());
/* 31:   */     } else {
/* 32:50 */       this.gui.clientErrLogTxt(this.imei, packet.getDate(), packet.getMessage());
/* 33:   */     }
/* 34:   */   }
/* 35:   */ }


/* Location:           C:\Users\SmileyFacedHacker\Downloads\StillBorn RAT (Android)\StillBorn RAT (Android)\StillBornRAT.jar
 * Qualified Name:     handler.ClientLogHandler
 * JD-Core Version:    0.7.0.1
 */