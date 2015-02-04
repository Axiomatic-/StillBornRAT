/*   1:    */ package out;
/*   2:    */ 
/*   3:    */ import Packet.TransportPacket;
/*   4:    */ import java.io.DataOutputStream;
/*   5:    */ import java.io.PrintStream;
/*   6:    */ 
/*   7:    */ public class Mux
/*   8:    */ {
/*   9:    */   Sender sender;
/*  10:    */   
/*  11:    */   public Mux(DataOutputStream out)
/*  12:    */   {
/*  13: 35 */     this.sender = new Sender(out);
/*  14:    */   }
/*  15:    */   
/*  16:    */   public void send(int chan, byte[] data)
/*  17:    */   {
/*  18:    */     try
/*  19:    */     {
/*  20: 45 */       boolean last = false;
/*  21: 46 */       boolean envoieTotal = false;
/*  22: 47 */       int pointeurData = 0;
/*  23: 48 */       short numSeq = 0;
/*  24: 51 */       while (!envoieTotal)
/*  25:    */       {
/*  26: 56 */         if ((last) || (15 + data.length < 2048))
/*  27:    */         {
/*  28: 58 */           byte[] dataToSend = new byte[15 + (data.length - pointeurData)];
/*  29: 59 */           last = true;
/*  30: 60 */           envoieTotal = true;
/*  31:    */         }
/*  32:    */         else
/*  33:    */         {
/*  34: 63 */           dataToSend = new byte[2048];
/*  35:    */         }
/*  36: 67 */         int actualLenght = dataToSend.length - 15;
/*  37:    */         
/*  38:    */ 
/*  39: 70 */         byte[] fragData = new byte[dataToSend.length - 15];
/*  40: 71 */         System.arraycopy(data, pointeurData, fragData, 0, fragData.length);
/*  41: 72 */         TransportPacket tp = new TransportPacket(data.length, actualLenght, chan, last, numSeq, fragData);
/*  42: 73 */         byte[] dataToSend = tp.build();
/*  43:    */         
/*  44:    */ 
/*  45:    */ 
/*  46:    */ 
/*  47:    */ 
/*  48:    */ 
/*  49:    */ 
/*  50:    */ 
/*  51:    */ 
/*  52:    */ 
/*  53:    */ 
/*  54:    */ 
/*  55:    */ 
/*  56:    */ 
/*  57:    */ 
/*  58:    */ 
/*  59: 90 */         pointeurData += actualLenght;
/*  60: 91 */         numSeq = (short)(numSeq + 1);
/*  61: 92 */         if (data.length - pointeurData <= 2033) {
/*  62: 94 */           last = true;
/*  63:    */         }
/*  64: 97 */         this.sender.send(dataToSend);
/*  65:    */       }
/*  66:    */     }
/*  67:    */     catch (NullPointerException e)
/*  68:    */     {
/*  69:103 */       System.out.println("Ce channel n'est pas indexï¿½");
/*  70:104 */       e.printStackTrace();
/*  71:    */     }
/*  72:    */   }
/*  73:    */ }


/* Location:           C:\Users\SmileyFacedHacker\Downloads\StillBorn RAT (Android)\StillBorn RAT (Android)\StillBornRAT.jar
 * Qualified Name:     out.Mux
 * JD-Core Version:    0.7.0.1
 */