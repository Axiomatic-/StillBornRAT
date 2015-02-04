/*  1:   */ package org.eclipse.jdt.internal.jarinjarloader;
/*  2:   */ 
/*  3:   */ import java.io.IOException;
/*  4:   */ import java.io.InputStream;
/*  5:   */ import java.net.MalformedURLException;
/*  6:   */ import java.net.URL;
/*  7:   */ import java.net.URLConnection;
/*  8:   */ import java.net.URLDecoder;
/*  9:   */ 
/* 10:   */ public class RsrcURLConnection
/* 11:   */   extends URLConnection
/* 12:   */ {
/* 13:   */   private ClassLoader classLoader;
/* 14:   */   
/* 15:   */   public RsrcURLConnection(URL url, ClassLoader classLoader)
/* 16:   */   {
/* 17:35 */     super(url);
/* 18:36 */     this.classLoader = classLoader;
/* 19:   */   }
/* 20:   */   
/* 21:   */   public void connect()
/* 22:   */     throws IOException
/* 23:   */   {}
/* 24:   */   
/* 25:   */   public InputStream getInputStream()
/* 26:   */     throws IOException
/* 27:   */   {
/* 28:43 */     String file = URLDecoder.decode(this.url.getFile(), "UTF-8");
/* 29:44 */     InputStream result = this.classLoader.getResourceAsStream(file);
/* 30:45 */     if (result == null) {
/* 31:46 */       throw new MalformedURLException("Could not open InputStream for URL '" + this.url + "'");
/* 32:   */     }
/* 33:48 */     return result;
/* 34:   */   }
/* 35:   */ }


/* Location:           C:\Users\SmileyFacedHacker\Downloads\StillBorn RAT (Android)\StillBorn RAT (Android)\StillBornRAT.jar
 * Qualified Name:     org.eclipse.jdt.internal.jarinjarloader.RsrcURLConnection
 * JD-Core Version:    0.7.0.1
 */