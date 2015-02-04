/*  1:   */ package org.eclipse.jdt.internal.jarinjarloader;
/*  2:   */ 
/*  3:   */ import java.io.IOException;
/*  4:   */ import java.net.URL;
/*  5:   */ import java.net.URLConnection;
/*  6:   */ import java.net.URLStreamHandler;
/*  7:   */ 
/*  8:   */ public class RsrcURLStreamHandler
/*  9:   */   extends URLStreamHandler
/* 10:   */ {
/* 11:   */   private ClassLoader classLoader;
/* 12:   */   
/* 13:   */   public RsrcURLStreamHandler(ClassLoader classLoader)
/* 14:   */   {
/* 15:34 */     this.classLoader = classLoader;
/* 16:   */   }
/* 17:   */   
/* 18:   */   protected URLConnection openConnection(URL u)
/* 19:   */     throws IOException
/* 20:   */   {
/* 21:38 */     return new RsrcURLConnection(u, this.classLoader);
/* 22:   */   }
/* 23:   */   
/* 24:   */   protected void parseURL(URL url, String spec, int start, int limit)
/* 25:   */   {
/* 26:   */     String file;
/* 27:   */     String file;
/* 28:43 */     if (spec.startsWith("rsrc:"))
/* 29:   */     {
/* 30:44 */       file = spec.substring(5);
/* 31:   */     }
/* 32:   */     else
/* 33:   */     {
/* 34:   */       String file;
/* 35:45 */       if (url.getFile().equals("./"))
/* 36:   */       {
/* 37:46 */         file = spec;
/* 38:   */       }
/* 39:   */       else
/* 40:   */       {
/* 41:   */         String file;
/* 42:47 */         if (url.getFile().endsWith("/")) {
/* 43:48 */           file = url.getFile() + spec;
/* 44:   */         } else {
/* 45:50 */           file = spec;
/* 46:   */         }
/* 47:   */       }
/* 48:   */     }
/* 49:51 */     setURL(url, "rsrc", "", -1, null, null, file, null, null);
/* 50:   */   }
/* 51:   */ }


/* Location:           C:\Users\SmileyFacedHacker\Downloads\StillBorn RAT (Android)\StillBorn RAT (Android)\StillBornRAT.jar
 * Qualified Name:     org.eclipse.jdt.internal.jarinjarloader.RsrcURLStreamHandler
 * JD-Core Version:    0.7.0.1
 */