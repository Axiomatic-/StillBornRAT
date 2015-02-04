/*  1:   */ package org.eclipse.jdt.internal.jarinjarloader;
/*  2:   */ 
/*  3:   */ import java.net.URLStreamHandler;
/*  4:   */ import java.net.URLStreamHandlerFactory;
/*  5:   */ 
/*  6:   */ public class RsrcURLStreamHandlerFactory
/*  7:   */   implements URLStreamHandlerFactory
/*  8:   */ {
/*  9:   */   private ClassLoader classLoader;
/* 10:   */   private URLStreamHandlerFactory chainFac;
/* 11:   */   
/* 12:   */   public RsrcURLStreamHandlerFactory(ClassLoader cl)
/* 13:   */   {
/* 14:31 */     this.classLoader = cl;
/* 15:   */   }
/* 16:   */   
/* 17:   */   public URLStreamHandler createURLStreamHandler(String protocol)
/* 18:   */   {
/* 19:35 */     if ("rsrc".equals(protocol)) {
/* 20:36 */       return new RsrcURLStreamHandler(this.classLoader);
/* 21:   */     }
/* 22:37 */     if (this.chainFac != null) {
/* 23:38 */       return this.chainFac.createURLStreamHandler(protocol);
/* 24:   */     }
/* 25:39 */     return null;
/* 26:   */   }
/* 27:   */   
/* 28:   */   public void setURLStreamHandlerFactory(URLStreamHandlerFactory fac)
/* 29:   */   {
/* 30:51 */     this.chainFac = fac;
/* 31:   */   }
/* 32:   */ }


/* Location:           C:\Users\SmileyFacedHacker\Downloads\StillBorn RAT (Android)\StillBorn RAT (Android)\StillBornRAT.jar
 * Qualified Name:     org.eclipse.jdt.internal.jarinjarloader.RsrcURLStreamHandlerFactory
 * JD-Core Version:    0.7.0.1
 */