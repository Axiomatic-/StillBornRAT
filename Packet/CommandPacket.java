/*  1:   */ package Packet;
/*  2:   */ 
/*  3:   */ import java.nio.ByteBuffer;
/*  4:   */ 
/*  5:   */ public class CommandPacket
/*  6:   */   implements Packet
/*  7:   */ {
/*  8:   */   private short commande;
/*  9:   */   private int targetChannel;
/* 10:   */   private byte[] argument;
/* 11:   */   
/* 12:   */   public CommandPacket() {}
/* 13:   */   
/* 14:   */   public CommandPacket(short cmd, int targetChannel, byte[] arg)
/* 15:   */   {
/* 16:34 */     this.commande = cmd;
/* 17:35 */     this.argument = arg;
/* 18:36 */     this.targetChannel = targetChannel;
/* 19:   */   }
/* 20:   */   
/* 21:   */   public void parse(byte[] packet)
/* 22:   */   {
/* 23:40 */     ByteBuffer b = ByteBuffer.wrap(packet);
/* 24:41 */     this.commande = b.getShort();
/* 25:42 */     this.targetChannel = b.getInt();
/* 26:43 */     this.argument = new byte[b.remaining()];
/* 27:44 */     b.get(this.argument, 0, b.remaining());
/* 28:   */   }
/* 29:   */   
/* 30:   */   public void parse(ByteBuffer b)
/* 31:   */   {
/* 32:48 */     this.commande = b.getShort();
/* 33:49 */     this.targetChannel = b.getInt();
/* 34:50 */     this.argument = new byte[b.remaining()];
/* 35:51 */     b.get(this.argument, 0, b.remaining());
/* 36:   */   }
/* 37:   */   
/* 38:   */   public byte[] build()
/* 39:   */   {
/* 40:55 */     byte[] byteCmd = ByteBuffer.allocate(2).putShort(this.commande).array();
/* 41:56 */     byte[] byteTargChan = ByteBuffer.allocate(4).putInt(this.targetChannel).array();
/* 42:57 */     byte[] cmdToSend = new byte[byteCmd.length + byteTargChan.length + this.argument.length];
/* 43:   */     
/* 44:59 */     System.arraycopy(byteCmd, 0, cmdToSend, 0, byteCmd.length);
/* 45:60 */     System.arraycopy(byteTargChan, 0, cmdToSend, byteCmd.length, byteTargChan.length);
/* 46:61 */     System.arraycopy(this.argument, 0, cmdToSend, byteCmd.length + byteTargChan.length, this.argument.length);
/* 47:   */     
/* 48:63 */     return cmdToSend;
/* 49:   */   }
/* 50:   */   
/* 51:   */   public short getCommand()
/* 52:   */   {
/* 53:67 */     return this.commande;
/* 54:   */   }
/* 55:   */   
/* 56:   */   public byte[] getArguments()
/* 57:   */   {
/* 58:71 */     return this.argument;
/* 59:   */   }
/* 60:   */   
/* 61:   */   public int getTargetChannel()
/* 62:   */   {
/* 63:75 */     return this.targetChannel;
/* 64:   */   }
/* 65:   */ }


/* Location:           C:\Users\SmileyFacedHacker\Downloads\StillBorn RAT (Android)\StillBorn RAT (Android)\StillBornRAT.jar
 * Qualified Name:     Packet.CommandPacket
 * JD-Core Version:    0.7.0.1
 */