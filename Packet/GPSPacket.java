/*  1:   */ package Packet;
/*  2:   */ 
/*  3:   */ import java.io.PrintStream;
/*  4:   */ import java.nio.ByteBuffer;
/*  5:   */ 
/*  6:   */ public class GPSPacket
/*  7:   */   implements Packet
/*  8:   */ {
/*  9:   */   private double longitude;
/* 10:   */   private double latitude;
/* 11:   */   private double altitude;
/* 12:   */   private float speed;
/* 13:   */   private float accuracy;
/* 14:   */   
/* 15:   */   public GPSPacket() {}
/* 16:   */   
/* 17:   */   public GPSPacket(double lat, double lon, double alt, float speed, float acc)
/* 18:   */   {
/* 19:41 */     this.latitude = lat;
/* 20:42 */     this.longitude = lon;
/* 21:43 */     this.altitude = alt;
/* 22:44 */     this.speed = speed;
/* 23:45 */     this.accuracy = acc;
/* 24:   */   }
/* 25:   */   
/* 26:   */   public byte[] build()
/* 27:   */   {
/* 28:50 */     ByteBuffer b = ByteBuffer.allocate(32);
/* 29:51 */     System.out.println("Longitude : " + this.longitude);
/* 30:52 */     b.putDouble(this.longitude);
/* 31:53 */     b.putDouble(this.latitude);
/* 32:54 */     b.putDouble(this.altitude);
/* 33:55 */     b.putFloat(this.speed);
/* 34:56 */     b.putFloat(this.accuracy);
/* 35:57 */     return b.array();
/* 36:   */   }
/* 37:   */   
/* 38:   */   public void parse(byte[] packet)
/* 39:   */   {
/* 40:62 */     ByteBuffer b = ByteBuffer.wrap(packet);
/* 41:63 */     this.longitude = b.getDouble();
/* 42:64 */     this.latitude = b.getDouble();
/* 43:65 */     this.altitude = b.getDouble();
/* 44:66 */     this.speed = b.getFloat();
/* 45:67 */     this.accuracy = b.getFloat();
/* 46:   */   }
/* 47:   */   
/* 48:   */   public double getLongitude()
/* 49:   */   {
/* 50:74 */     return this.longitude;
/* 51:   */   }
/* 52:   */   
/* 53:   */   public double getLatitude()
/* 54:   */   {
/* 55:79 */     return this.latitude;
/* 56:   */   }
/* 57:   */   
/* 58:   */   public double getAltitude()
/* 59:   */   {
/* 60:84 */     return this.altitude;
/* 61:   */   }
/* 62:   */   
/* 63:   */   public float getSpeed()
/* 64:   */   {
/* 65:89 */     return this.speed;
/* 66:   */   }
/* 67:   */   
/* 68:   */   public float getAccuracy()
/* 69:   */   {
/* 70:94 */     return this.accuracy;
/* 71:   */   }
/* 72:   */ }


/* Location:           C:\Users\SmileyFacedHacker\Downloads\StillBorn RAT (Android)\StillBorn RAT (Android)\StillBornRAT.jar
 * Qualified Name:     Packet.GPSPacket
 * JD-Core Version:    0.7.0.1
 */