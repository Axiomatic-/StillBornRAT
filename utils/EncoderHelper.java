/*   1:    */ package utils;
/*   2:    */ 
/*   3:    */ import java.io.ByteArrayInputStream;
/*   4:    */ import java.io.ByteArrayOutputStream;
/*   5:    */ import java.io.IOException;
/*   6:    */ import java.io.ObjectInputStream;
/*   7:    */ import java.io.ObjectOutputStream;
/*   8:    */ import java.nio.ByteBuffer;
/*   9:    */ import java.util.ArrayList;
/*  10:    */ import java.util.HashMap;
/*  11:    */ import java.util.HashSet;
/*  12:    */ 
/*  13:    */ public class EncoderHelper
/*  14:    */ {
/*  15:    */   public static byte[] encodeHashMap(HashMap<String, String> h)
/*  16:    */   {
/*  17:    */     try
/*  18:    */     {
/*  19: 36 */       ByteArrayOutputStream bos = new ByteArrayOutputStream();
/*  20: 37 */       ObjectOutputStream out = new ObjectOutputStream(bos);
/*  21: 38 */       out.writeObject(h);
/*  22: 39 */       return bos.toByteArray();
/*  23:    */     }
/*  24:    */     catch (IOException e) {}
/*  25: 41 */     return null;
/*  26:    */   }
/*  27:    */   
/*  28:    */   public static HashMap<String, String> decodeHashMap(byte[] data)
/*  29:    */   {
/*  30: 46 */     ByteArrayInputStream bis = new ByteArrayInputStream(data);
/*  31:    */     try
/*  32:    */     {
/*  33: 49 */       ObjectInputStream in = new ObjectInputStream(bis);
/*  34: 50 */       return (HashMap)in.readObject();
/*  35:    */     }
/*  36:    */     catch (Exception e) {}
/*  37: 52 */     return null;
/*  38:    */   }
/*  39:    */   
/*  40:    */   public static byte[] encodeArrayList(ArrayList<String> l)
/*  41:    */   {
/*  42:    */     try
/*  43:    */     {
/*  44: 58 */       ByteArrayOutputStream bos = new ByteArrayOutputStream();
/*  45: 59 */       ObjectOutputStream out = new ObjectOutputStream(bos);
/*  46: 60 */       out.writeObject(l);
/*  47: 61 */       return bos.toByteArray();
/*  48:    */     }
/*  49:    */     catch (IOException e) {}
/*  50: 63 */     return null;
/*  51:    */   }
/*  52:    */   
/*  53:    */   public static ArrayList<String> decodeArrayList(byte[] data)
/*  54:    */   {
/*  55: 68 */     ByteArrayInputStream bis = new ByteArrayInputStream(data);
/*  56:    */     try
/*  57:    */     {
/*  58: 71 */       ObjectInputStream in = new ObjectInputStream(bis);
/*  59: 72 */       return (ArrayList)in.readObject();
/*  60:    */     }
/*  61:    */     catch (Exception e) {}
/*  62: 74 */     return null;
/*  63:    */   }
/*  64:    */   
/*  65:    */   public static byte[] encodeHashSet(HashSet<String> l)
/*  66:    */   {
/*  67: 79 */     if (l == null) {
/*  68: 80 */       return null;
/*  69:    */     }
/*  70:    */     try
/*  71:    */     {
/*  72: 82 */       ByteArrayOutputStream bos = new ByteArrayOutputStream();
/*  73: 83 */       ObjectOutputStream out = new ObjectOutputStream(bos);
/*  74: 84 */       out.writeObject(l);
/*  75: 85 */       return bos.toByteArray();
/*  76:    */     }
/*  77:    */     catch (IOException e) {}
/*  78: 87 */     return null;
/*  79:    */   }
/*  80:    */   
/*  81:    */   public static HashSet<String> decodeHashSet(byte[] data)
/*  82:    */   {
/*  83: 92 */     ByteArrayInputStream bis = new ByteArrayInputStream(data);
/*  84:    */     try
/*  85:    */     {
/*  86: 95 */       ObjectInputStream in = new ObjectInputStream(bis);
/*  87: 96 */       return (HashSet)in.readObject();
/*  88:    */     }
/*  89:    */     catch (Exception e) {}
/*  90: 98 */     return null;
/*  91:    */   }
/*  92:    */   
/*  93:    */   public static byte[] encodeLong(long l)
/*  94:    */   {
/*  95:103 */     ByteBuffer b = ByteBuffer.allocate(8);
/*  96:104 */     b.putLong(l);
/*  97:105 */     return b.array();
/*  98:    */   }
/*  99:    */ }


/* Location:           C:\Users\SmileyFacedHacker\Downloads\StillBorn RAT (Android)\StillBorn RAT (Android)\StillBornRAT.jar
 * Qualified Name:     utils.EncoderHelper
 * JD-Core Version:    0.7.0.1
 */