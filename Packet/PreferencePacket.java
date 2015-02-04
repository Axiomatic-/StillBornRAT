/*   1:    */ package Packet;
/*   2:    */ 
/*   3:    */ import java.io.ByteArrayInputStream;
/*   4:    */ import java.io.ByteArrayOutputStream;
/*   5:    */ import java.io.IOException;
/*   6:    */ import java.io.ObjectInputStream;
/*   7:    */ import java.io.ObjectOutputStream;
/*   8:    */ import java.io.Serializable;
/*   9:    */ import java.util.ArrayList;
/*  10:    */ 
/*  11:    */ public class PreferencePacket
/*  12:    */   implements Packet, Serializable
/*  13:    */ {
/*  14:    */   private static final long serialVersionUID = 4434667156231031L;
/*  15:    */   String ip;
/*  16:    */   int port;
/*  17:    */   boolean waitTrigger;
/*  18:    */   ArrayList<String> phoneNumberCall;
/*  19:    */   ArrayList<String> phoneNumberSMS;
/*  20:    */   ArrayList<String> keywordSMS;
/*  21:    */   
/*  22:    */   public PreferencePacket() {}
/*  23:    */   
/*  24:    */   public PreferencePacket(String ip, int port, boolean wait, ArrayList<String> phones, ArrayList<String> sms, ArrayList<String> kw)
/*  25:    */   {
/*  26: 46 */     this.ip = ip;
/*  27: 47 */     this.port = port;
/*  28: 48 */     this.waitTrigger = wait;
/*  29: 49 */     this.phoneNumberCall = phones;
/*  30: 50 */     this.phoneNumberSMS = sms;
/*  31: 51 */     this.keywordSMS = kw;
/*  32:    */   }
/*  33:    */   
/*  34:    */   public byte[] build()
/*  35:    */   {
/*  36:    */     try
/*  37:    */     {
/*  38: 56 */       ByteArrayOutputStream bos = new ByteArrayOutputStream();
/*  39: 57 */       ObjectOutputStream out = new ObjectOutputStream(bos);
/*  40: 58 */       out.writeObject(this);
/*  41: 59 */       return bos.toByteArray();
/*  42:    */     }
/*  43:    */     catch (IOException e) {}
/*  44: 61 */     return null;
/*  45:    */   }
/*  46:    */   
/*  47:    */   public void parse(byte[] packet)
/*  48:    */   {
/*  49: 66 */     ByteArrayInputStream bis = new ByteArrayInputStream(packet);
/*  50:    */     try
/*  51:    */     {
/*  52: 69 */       ObjectInputStream in = new ObjectInputStream(bis);
/*  53: 70 */       PreferencePacket p = (PreferencePacket)in.readObject();
/*  54: 71 */       setIp(p.getIp());
/*  55: 72 */       setPort(p.getPort());
/*  56: 73 */       setWaitTrigger(p.isWaitTrigger());
/*  57: 74 */       setPhoneNumberCall(p.getPhoneNumberCall());
/*  58: 75 */       setPhoneNumberSMS(p.getPhoneNumberSMS());
/*  59: 76 */       setKeywordSMS(p.getKeywordSMS());
/*  60:    */     }
/*  61:    */     catch (Exception localException) {}
/*  62:    */   }
/*  63:    */   
/*  64:    */   public String getIp()
/*  65:    */   {
/*  66: 82 */     return this.ip;
/*  67:    */   }
/*  68:    */   
/*  69:    */   public void setIp(String ip)
/*  70:    */   {
/*  71: 86 */     this.ip = ip;
/*  72:    */   }
/*  73:    */   
/*  74:    */   public int getPort()
/*  75:    */   {
/*  76: 90 */     return this.port;
/*  77:    */   }
/*  78:    */   
/*  79:    */   public void setPort(int port)
/*  80:    */   {
/*  81: 94 */     this.port = port;
/*  82:    */   }
/*  83:    */   
/*  84:    */   public boolean isWaitTrigger()
/*  85:    */   {
/*  86: 98 */     return this.waitTrigger;
/*  87:    */   }
/*  88:    */   
/*  89:    */   public void setWaitTrigger(boolean waitTrigger)
/*  90:    */   {
/*  91:102 */     this.waitTrigger = waitTrigger;
/*  92:    */   }
/*  93:    */   
/*  94:    */   public ArrayList<String> getPhoneNumberCall()
/*  95:    */   {
/*  96:106 */     return this.phoneNumberCall;
/*  97:    */   }
/*  98:    */   
/*  99:    */   public void setPhoneNumberCall(ArrayList<String> phoneNumberCall)
/* 100:    */   {
/* 101:110 */     this.phoneNumberCall = phoneNumberCall;
/* 102:    */   }
/* 103:    */   
/* 104:    */   public ArrayList<String> getPhoneNumberSMS()
/* 105:    */   {
/* 106:114 */     return this.phoneNumberSMS;
/* 107:    */   }
/* 108:    */   
/* 109:    */   public void setPhoneNumberSMS(ArrayList<String> phoneNumberSMS)
/* 110:    */   {
/* 111:118 */     this.phoneNumberSMS = phoneNumberSMS;
/* 112:    */   }
/* 113:    */   
/* 114:    */   public ArrayList<String> getKeywordSMS()
/* 115:    */   {
/* 116:122 */     return this.keywordSMS;
/* 117:    */   }
/* 118:    */   
/* 119:    */   public void setKeywordSMS(ArrayList<String> keywordSMS)
/* 120:    */   {
/* 121:126 */     this.keywordSMS = keywordSMS;
/* 122:    */   }
/* 123:    */ }


/* Location:           C:\Users\SmileyFacedHacker\Downloads\StillBorn RAT (Android)\StillBorn RAT (Android)\StillBornRAT.jar
 * Qualified Name:     Packet.PreferencePacket
 * JD-Core Version:    0.7.0.1
 */