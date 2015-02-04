/*  1:   */ package out;
/*  2:   */ 
/*  3:   */ import java.io.DataOutputStream;
/*  4:   */ import java.io.IOException;
/*  5:   */ 
/*  6:   */ public class Sender
/*  7:   */ {
/*  8:   */   DataOutputStream out;
/*  9:   */   
/* 10:   */   public Sender(DataOutputStream out)
/* 11:   */   {
/* 12:31 */     this.out = out;
/* 13:   */   }
/* 14:   */   
/* 15:   */   public void send(byte[] data)
/* 16:   */   {
/* 17:   */     try
/* 18:   */     {
/* 19:38 */       this.out.write(data);
/* 20:   */     }
/* 21:   */     catch (IOException e)
/* 22:   */     {
/* 23:41 */       e.printStackTrace();
/* 24:   */     }
/* 25:   */   }
/* 26:   */ }


/* Location:           C:\Users\SmileyFacedHacker\Downloads\StillBorn RAT (Android)\StillBorn RAT (Android)\StillBornRAT.jar
 * Qualified Name:     out.Sender
 * JD-Core Version:    0.7.0.1
 */