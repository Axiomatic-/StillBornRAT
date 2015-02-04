/*   1:    */ package Packet;
/*   2:    */ 
/*   3:    */ import inout.Protocol;
/*   4:    */ import java.nio.ByteBuffer;
/*   5:    */ 
/*   6:    */ public class TransportPacket
/*   7:    */   implements Packet
/*   8:    */ {
/*   9:    */   private int totalLength;
/*  10:    */   private int awaitedLength;
/*  11:    */   private int localLength;
/*  12:    */   private boolean last;
/*  13:    */   private short NumSeq;
/*  14:    */   private int channel;
/*  15:    */   private byte[] data;
/*  16:    */   private int fillingPosition;
/*  17:    */   
/*  18:    */   public TransportPacket()
/*  19:    */   {
/*  20: 39 */     this.awaitedLength = 0;
/*  21: 40 */     this.fillingPosition = 0;
/*  22:    */   }
/*  23:    */   
/*  24:    */   public TransportPacket(int tdl, int ll, int channel, boolean last, short nums, byte[] data)
/*  25:    */   {
/*  26: 46 */     this.totalLength = tdl;
/*  27: 47 */     this.channel = channel;
/*  28: 48 */     this.last = last;
/*  29: 49 */     this.data = data;
/*  30: 50 */     this.localLength = ll;
/*  31: 51 */     this.NumSeq = nums;
/*  32:    */   }
/*  33:    */   
/*  34:    */   public void parse(byte[] packet)
/*  35:    */   {
/*  36: 55 */     ByteBuffer b = ByteBuffer.wrap(packet);
/*  37:    */     
/*  38: 57 */     this.totalLength = b.getInt();
/*  39: 58 */     this.localLength = b.getInt();
/*  40:    */     
/*  41: 60 */     byte checkLast = b.get();
/*  42: 61 */     if (checkLast == 1) {
/*  43: 62 */       this.last = true;
/*  44:    */     } else {
/*  45: 64 */       this.last = false;
/*  46:    */     }
/*  47: 66 */     this.NumSeq = b.getShort();
/*  48: 67 */     this.channel = b.getInt();
/*  49: 68 */     this.data = new byte[b.remaining()];
/*  50: 69 */     b.get(this.data, 0, b.remaining());
/*  51:    */   }
/*  52:    */   
/*  53:    */   public boolean parse(ByteBuffer buffer)
/*  54:    */     throws Exception
/*  55:    */   {
/*  56: 75 */     this.totalLength = buffer.getInt();
/*  57: 76 */     this.localLength = buffer.getInt();
/*  58:    */     
/*  59: 78 */     byte lst = buffer.get();
/*  60: 79 */     if (lst == 1) {
/*  61: 80 */       this.last = true;
/*  62:    */     } else {
/*  63: 82 */       this.last = false;
/*  64:    */     }
/*  65: 84 */     this.NumSeq = buffer.getShort();
/*  66: 85 */     this.channel = buffer.getInt();
/*  67: 95 */     if (buffer.limit() - buffer.position() < this.localLength)
/*  68:    */     {
/*  69: 97 */       dataFilling(buffer, buffer.limit() - buffer.position());
/*  70:    */       
/*  71: 99 */       return true;
/*  72:    */     }
/*  73:105 */     this.data = new byte[this.localLength];
/*  74:106 */     buffer.get(this.data, 0, this.data.length);
/*  75:107 */     return false;
/*  76:    */   }
/*  77:    */   
/*  78:    */   public boolean parseCompleter(ByteBuffer buffer)
/*  79:    */     throws Exception
/*  80:    */   {
/*  81:117 */     if (buffer.limit() - buffer.position() < this.awaitedLength)
/*  82:    */     {
/*  83:120 */       dataFilling(buffer, buffer.limit() - buffer.position());
/*  84:121 */       return true;
/*  85:    */     }
/*  86:126 */     dataFilling(buffer, this.awaitedLength);
/*  87:127 */     return false;
/*  88:    */   }
/*  89:    */   
/*  90:    */   public void dataFilling(ByteBuffer buffer, int length)
/*  91:    */   {
/*  92:141 */     if (this.data == null) {
/*  93:141 */       this.data = new byte[this.localLength];
/*  94:    */     }
/*  95:143 */     buffer.get(this.data, this.fillingPosition, length);
/*  96:144 */     this.fillingPosition += length;
/*  97:145 */     this.awaitedLength = (this.localLength - this.fillingPosition);
/*  98:    */   }
/*  99:    */   
/* 100:    */   public byte[] build()
/* 101:    */   {
/* 102:150 */     byte[] cmdToSend = new byte[15 + this.data.length];
/* 103:151 */     byte[] header = Protocol.dataHeaderGenerator(this.totalLength, 
/* 104:152 */       this.localLength, this.last, this.NumSeq, this.channel);
/* 105:153 */     System.arraycopy(header, 0, cmdToSend, 0, header.length);
/* 106:154 */     System.arraycopy(this.data, 0, cmdToSend, header.length, this.data.length);
/* 107:    */     
/* 108:156 */     return cmdToSend;
/* 109:    */   }
/* 110:    */   
/* 111:    */   public int getTotalLength()
/* 112:    */   {
/* 113:160 */     return this.totalLength;
/* 114:    */   }
/* 115:    */   
/* 116:    */   public int getLocalLength()
/* 117:    */   {
/* 118:164 */     return this.localLength;
/* 119:    */   }
/* 120:    */   
/* 121:    */   public boolean isLast()
/* 122:    */   {
/* 123:168 */     return this.last;
/* 124:    */   }
/* 125:    */   
/* 126:    */   public short getNumSeq()
/* 127:    */   {
/* 128:172 */     return this.NumSeq;
/* 129:    */   }
/* 130:    */   
/* 131:    */   public int getChannel()
/* 132:    */   {
/* 133:176 */     return this.channel;
/* 134:    */   }
/* 135:    */   
/* 136:    */   public byte[] getData()
/* 137:    */   {
/* 138:180 */     return this.data;
/* 139:    */   }
/* 140:    */ }


/* Location:           C:\Users\SmileyFacedHacker\Downloads\StillBorn RAT (Android)\StillBorn RAT (Android)\StillBornRAT.jar
 * Qualified Name:     Packet.TransportPacket
 * JD-Core Version:    0.7.0.1
 */