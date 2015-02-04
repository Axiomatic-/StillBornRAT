/*   1:    */ package org.eclipse.jdt.internal.jarinjarloader;
/*   2:    */ 
/*   3:    */ import java.io.IOException;
/*   4:    */ import java.io.InputStream;
/*   5:    */ import java.io.PrintStream;
/*   6:    */ import java.lang.reflect.InvocationTargetException;
/*   7:    */ import java.lang.reflect.Method;
/*   8:    */ import java.net.URL;
/*   9:    */ import java.net.URLClassLoader;
/*  10:    */ import java.util.ArrayList;
/*  11:    */ import java.util.Enumeration;
/*  12:    */ import java.util.List;
/*  13:    */ import java.util.jar.Attributes;
/*  14:    */ import java.util.jar.Manifest;
/*  15:    */ 
/*  16:    */ public class JarRsrcLoader
/*  17:    */ {
/*  18:    */   private static class ManifestInfo
/*  19:    */   {
/*  20:    */     String rsrcMainClass;
/*  21:    */     String[] rsrcClassPath;
/*  22:    */     
/*  23:    */     private ManifestInfo() {}
/*  24:    */     
/*  25:    */     ManifestInfo(ManifestInfo paramManifestInfo)
/*  26:    */     {
/*  27: 37 */       this();
/*  28:    */     }
/*  29:    */   }
/*  30:    */   
/*  31:    */   public static void main(String[] args)
/*  32:    */     throws ClassNotFoundException, IllegalArgumentException, IllegalAccessException, InvocationTargetException, SecurityException, NoSuchMethodException, IOException
/*  33:    */   {
/*  34: 43 */     ManifestInfo mi = getManifestInfo();
/*  35: 44 */     ClassLoader cl = Thread.currentThread().getContextClassLoader();
/*  36: 45 */     URL.setURLStreamHandlerFactory(new RsrcURLStreamHandlerFactory(cl));
/*  37: 46 */     URL[] rsrcUrls = new URL[mi.rsrcClassPath.length];
/*  38: 47 */     for (int i = 0; i < mi.rsrcClassPath.length; i++)
/*  39:    */     {
/*  40: 48 */       String rsrcPath = mi.rsrcClassPath[i];
/*  41: 49 */       if (rsrcPath.endsWith("/")) {
/*  42: 50 */         rsrcUrls[i] = new URL("rsrc:" + rsrcPath);
/*  43:    */       } else {
/*  44: 52 */         rsrcUrls[i] = new URL("jar:rsrc:" + rsrcPath + "!/");
/*  45:    */       }
/*  46:    */     }
/*  47: 54 */     ClassLoader jceClassLoader = new URLClassLoader(rsrcUrls, null);
/*  48: 55 */     Thread.currentThread().setContextClassLoader(jceClassLoader);
/*  49: 56 */     Class c = Class.forName(mi.rsrcMainClass, true, jceClassLoader);
/*  50: 57 */     Method main = c.getMethod("main", new Class[] { args.getClass() });
/*  51: 58 */     main.invoke(null, new Object[] { args });
/*  52:    */   }
/*  53:    */   
/*  54:    */   private static ManifestInfo getManifestInfo()
/*  55:    */     throws IOException
/*  56:    */   {
/*  57: 63 */     Enumeration resEnum = Thread.currentThread().getContextClassLoader().getResources("META-INF/MANIFEST.MF");
/*  58: 64 */     while (resEnum.hasMoreElements()) {
/*  59:    */       try
/*  60:    */       {
/*  61: 66 */         URL url = (URL)resEnum.nextElement();
/*  62: 67 */         InputStream is = url.openStream();
/*  63: 68 */         if (is != null)
/*  64:    */         {
/*  65: 69 */           ManifestInfo result = new ManifestInfo(null);
/*  66: 70 */           Manifest manifest = new Manifest(is);
/*  67: 71 */           Attributes mainAttribs = manifest.getMainAttributes();
/*  68: 72 */           result.rsrcMainClass = mainAttribs.getValue("Rsrc-Main-Class");
/*  69: 73 */           String rsrcCP = mainAttribs.getValue("Rsrc-Class-Path");
/*  70: 74 */           if (rsrcCP == null) {
/*  71: 75 */             rsrcCP = "";
/*  72:    */           }
/*  73: 76 */           result.rsrcClassPath = splitSpaces(rsrcCP);
/*  74: 77 */           if ((result.rsrcMainClass != null) && (!result.rsrcMainClass.trim().equals(""))) {
/*  75: 78 */             return result;
/*  76:    */           }
/*  77:    */         }
/*  78:    */       }
/*  79:    */       catch (Exception localException) {}
/*  80:    */     }
/*  81: 85 */     System.err.println("Missing attributes for JarRsrcLoader in Manifest (Rsrc-Main-Class, Rsrc-Class-Path)");
/*  82: 86 */     return null;
/*  83:    */   }
/*  84:    */   
/*  85:    */   private static String[] splitSpaces(String line)
/*  86:    */   {
/*  87: 97 */     if (line == null) {
/*  88: 98 */       return null;
/*  89:    */     }
/*  90: 99 */     List result = new ArrayList();
/*  91:100 */     int firstPos = 0;
/*  92:101 */     while (firstPos < line.length())
/*  93:    */     {
/*  94:102 */       int lastPos = line.indexOf(' ', firstPos);
/*  95:103 */       if (lastPos == -1) {
/*  96:104 */         lastPos = line.length();
/*  97:    */       }
/*  98:105 */       if (lastPos > firstPos) {
/*  99:106 */         result.add(line.substring(firstPos, lastPos));
/* 100:    */       }
/* 101:108 */       firstPos = lastPos + 1;
/* 102:    */     }
/* 103:110 */     return (String[])result.toArray(new String[result.size()]);
/* 104:    */   }
/* 105:    */ }


/* Location:           C:\Users\SmileyFacedHacker\Downloads\StillBorn RAT (Android)\StillBorn RAT (Android)\StillBornRAT.jar
 * Qualified Name:     org.eclipse.jdt.internal.jarinjarloader.JarRsrcLoader
 * JD-Core Version:    0.7.0.1
 */