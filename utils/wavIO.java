/*   1:    */ package utils;
/*   2:    */ 
/*   3:    */ import java.io.DataInputStream;
/*   4:    */ import java.io.DataOutputStream;
/*   5:    */ import java.io.File;
/*   6:    */ import java.io.FileInputStream;
/*   7:    */ import java.io.FileOutputStream;
/*   8:    */ import java.io.PrintStream;
/*   9:    */ 
/*  10:    */ public class wavIO
/*  11:    */ {
/*  12:    */   private String myPath;
/*  13:    */   private long myChunkSize;
/*  14:    */   private long mySubChunk1Size;
/*  15:    */   private int myFormat;
/*  16:    */   private long myChannels;
/*  17:    */   private long mySampleRate;
/*  18:    */   private long myByteRate;
/*  19:    */   private int myBlockAlign;
/*  20:    */   private int myBitsPerSample;
/*  21:    */   private long myDataSize;
/*  22:    */   public byte[] myData;
/*  23:    */   
/*  24:    */   public String getPath()
/*  25:    */   {
/*  26:116 */     return this.myPath;
/*  27:    */   }
/*  28:    */   
/*  29:    */   public void setPath(String newPath)
/*  30:    */   {
/*  31:120 */     this.myPath = newPath;
/*  32:    */   }
/*  33:    */   
/*  34:    */   public wavIO()
/*  35:    */   {
/*  36:126 */     this.myPath = "";
/*  37:    */   }
/*  38:    */   
/*  39:    */   public wavIO(String tmpPath)
/*  40:    */   {
/*  41:132 */     this.myPath = tmpPath;
/*  42:    */   }
/*  43:    */   
/*  44:    */   public void readRaw()
/*  45:    */   {
/*  46:    */     try
/*  47:    */     {
/*  48:137 */       File f = new File(this.myPath);
/*  49:138 */       DataInputStream inFile = new DataInputStream(new FileInputStream(f));
/*  50:139 */       this.myDataSize = ((int)f.length());
/*  51:140 */       this.myData = new byte[(int)this.myDataSize];
/*  52:141 */       inFile.read(this.myData);
/*  53:142 */       inFile.close();
/*  54:    */     }
/*  55:    */     catch (Exception localException) {}
/*  56:    */   }
/*  57:    */   
/*  58:    */   public boolean read()
/*  59:    */   {
/*  60:151 */     DataInputStream inFile = null;
/*  61:152 */     this.myData = null;
/*  62:153 */     byte[] tmpLong = new byte[4];
/*  63:154 */     byte[] tmpInt = new byte[2];
/*  64:    */     try
/*  65:    */     {
/*  66:158 */       inFile = new DataInputStream(new FileInputStream(this.myPath));
/*  67:    */       
/*  68:    */ 
/*  69:    */ 
/*  70:162 */       String chunkID = (char)inFile.readByte() + (char)inFile.readByte() + (char)inFile.readByte() + (char)inFile.readByte();
/*  71:    */       
/*  72:164 */       inFile.read(tmpLong);
/*  73:165 */       this.myChunkSize = byteArrayToLong(tmpLong);
/*  74:    */       
/*  75:167 */       String format = (char)inFile.readByte() + (char)inFile.readByte() + (char)inFile.readByte() + (char)inFile.readByte();
/*  76:    */       
/*  77:    */ 
/*  78:    */ 
/*  79:    */ 
/*  80:    */ 
/*  81:    */ 
/*  82:174 */       String subChunk1ID = (char)inFile.readByte() + (char)inFile.readByte() + (char)inFile.readByte() + (char)inFile.readByte();
/*  83:    */       
/*  84:176 */       inFile.read(tmpLong);
/*  85:177 */       this.mySubChunk1Size = byteArrayToLong(tmpLong);
/*  86:    */       
/*  87:179 */       inFile.read(tmpInt);
/*  88:180 */       this.myFormat = byteArrayToInt(tmpInt);
/*  89:    */       
/*  90:182 */       inFile.read(tmpInt);
/*  91:183 */       this.myChannels = byteArrayToInt(tmpInt);
/*  92:    */       
/*  93:185 */       inFile.read(tmpLong);
/*  94:186 */       this.mySampleRate = byteArrayToLong(tmpLong);
/*  95:    */       
/*  96:188 */       inFile.read(tmpLong);
/*  97:189 */       this.myByteRate = byteArrayToLong(tmpLong);
/*  98:    */       
/*  99:191 */       inFile.read(tmpInt);
/* 100:192 */       this.myBlockAlign = byteArrayToInt(tmpInt);
/* 101:    */       
/* 102:194 */       inFile.read(tmpInt);
/* 103:195 */       this.myBitsPerSample = byteArrayToInt(tmpInt);
/* 104:    */       
/* 105:    */ 
/* 106:    */ 
/* 107:    */ 
/* 108:    */ 
/* 109:    */ 
/* 110:202 */       String dataChunkID = (char)inFile.readByte() + (char)inFile.readByte() + (char)inFile.readByte() + (char)inFile.readByte();
/* 111:    */       
/* 112:204 */       inFile.read(tmpLong);
/* 113:205 */       this.myDataSize = byteArrayToLong(tmpLong);
/* 114:    */       
/* 115:    */ 
/* 116:    */ 
/* 117:209 */       this.myData = new byte[(int)this.myDataSize];
/* 118:210 */       inFile.read(this.myData);
/* 119:    */       
/* 120:    */ 
/* 121:213 */       inFile.close();
/* 122:    */     }
/* 123:    */     catch (Exception e)
/* 124:    */     {
/* 125:217 */       return false;
/* 126:    */     }
/* 127:220 */     return true;
/* 128:    */   }
/* 129:    */   
/* 130:    */   public void setHeaders()
/* 131:    */   {
/* 132:224 */     this.myChunkSize = (this.myDataSize + 36L);
/* 133:225 */     this.mySubChunk1Size = 16L;
/* 134:226 */     this.myFormat = 1;
/* 135:227 */     this.myChannels = 1L;
/* 136:228 */     this.mySampleRate = 11025L;
/* 137:229 */     this.myByteRate = 20050L;
/* 138:230 */     this.myBlockAlign = 2;
/* 139:231 */     this.myBitsPerSample = 16;
/* 140:    */   }
/* 141:    */   
/* 142:    */   public boolean save()
/* 143:    */   {
/* 144:    */     try
/* 145:    */     {
/* 146:239 */       DataOutputStream outFile = new DataOutputStream(new FileOutputStream(this.myPath));
/* 147:    */       
/* 148:    */ 
/* 149:242 */       outFile.writeBytes("RIFF");
/* 150:243 */       outFile.write(intToByteArray((int)this.myChunkSize), 0, 4);
/* 151:244 */       outFile.writeBytes("WAVE");
/* 152:245 */       outFile.writeBytes("fmt ");
/* 153:246 */       outFile.write(intToByteArray((int)this.mySubChunk1Size), 0, 4);
/* 154:247 */       outFile.write(shortToByteArray((short)this.myFormat), 0, 2);
/* 155:248 */       outFile.write(shortToByteArray((short)(int)this.myChannels), 0, 2);
/* 156:249 */       outFile.write(intToByteArray((int)this.mySampleRate), 0, 4);
/* 157:250 */       outFile.write(intToByteArray((int)this.myByteRate), 0, 4);
/* 158:251 */       outFile.write(shortToByteArray((short)this.myBlockAlign), 0, 2);
/* 159:252 */       outFile.write(shortToByteArray((short)this.myBitsPerSample), 0, 2);
/* 160:253 */       outFile.writeBytes("data");
/* 161:254 */       outFile.write(intToByteArray((int)this.myDataSize), 0, 4);
/* 162:255 */       outFile.write(this.myData);
/* 163:    */     }
/* 164:    */     catch (Exception e)
/* 165:    */     {
/* 166:259 */       System.out.println(e.getMessage());
/* 167:260 */       return false;
/* 168:    */     }
/* 169:263 */     return true;
/* 170:    */   }
/* 171:    */   
/* 172:    */   public boolean save2()
/* 173:    */   {
/* 174:    */     try
/* 175:    */     {
/* 176:271 */       DataOutputStream outFile = new DataOutputStream(new FileOutputStream(this.myPath));
/* 177:    */       
/* 178:    */ 
/* 179:274 */       outFile.writeBytes("RIFF");
/* 180:275 */       outFile.writeInt(Integer.reverseBytes((int)this.myChunkSize));
/* 181:276 */       outFile.writeBytes("WAVE");
/* 182:277 */       outFile.writeBytes("fmt ");
/* 183:278 */       outFile.writeInt(Integer.reverseBytes((int)this.mySubChunk1Size));
/* 184:279 */       outFile.writeShort(Short.reverseBytes((short)this.myFormat));
/* 185:280 */       outFile.writeShort(Short.reverseBytes((short)(int)this.myChannels));
/* 186:281 */       outFile.writeInt(Integer.reverseBytes((int)this.mySampleRate));
/* 187:282 */       outFile.writeInt(Integer.reverseBytes((int)this.myByteRate));
/* 188:283 */       outFile.writeShort(Short.reverseBytes((short)this.myBlockAlign));
/* 189:284 */       outFile.writeShort(Short.reverseBytes((short)this.myBitsPerSample));
/* 190:285 */       outFile.writeBytes("data");
/* 191:286 */       outFile.writeInt(Integer.reverseBytes((int)this.myDataSize));
/* 192:287 */       outFile.write(this.myData);
/* 193:    */     }
/* 194:    */     catch (Exception e)
/* 195:    */     {
/* 196:291 */       System.out.println(e.getMessage());
/* 197:292 */       return false;
/* 198:    */     }
/* 199:295 */     return true;
/* 200:    */   }
/* 201:    */   
/* 202:    */   public String getSummary()
/* 203:    */   {
/* 204:304 */     String newline = "<br>";
/* 205:305 */     String summary = "<html>Format: " + this.myFormat + newline + "Channels: " + this.myChannels + newline + "SampleRate: " + this.mySampleRate + newline + "ByteRate: " + this.myByteRate + newline + "BlockAlign: " + this.myBlockAlign + newline + "BitsPerSample: " + this.myBitsPerSample + newline + "DataSize: " + this.myDataSize + "</html>";
/* 206:306 */     return summary;
/* 207:    */   }
/* 208:    */   
/* 209:    */   public static int byteArrayToInt(byte[] b)
/* 210:    */   {
/* 211:317 */     int start = 0;
/* 212:318 */     int low = b[start] & 0xFF;
/* 213:319 */     int high = b[(start + 1)] & 0xFF;
/* 214:320 */     return high << 8 | low;
/* 215:    */   }
/* 216:    */   
/* 217:    */   public static long byteArrayToLong(byte[] b)
/* 218:    */   {
/* 219:327 */     int start = 0;
/* 220:328 */     int i = 0;
/* 221:329 */     int len = 4;
/* 222:330 */     int cnt = 0;
/* 223:331 */     byte[] tmp = new byte[len];
/* 224:332 */     for (i = start; i < start + len; i++)
/* 225:    */     {
/* 226:334 */       tmp[cnt] = b[i];
/* 227:335 */       cnt++;
/* 228:    */     }
/* 229:337 */     long accum = 0L;
/* 230:338 */     i = 0;
/* 231:339 */     for (int shiftBy = 0; shiftBy < 32; shiftBy += 8)
/* 232:    */     {
/* 233:341 */       accum |= (tmp[i] & 0xFF) << shiftBy;
/* 234:342 */       i++;
/* 235:    */     }
/* 236:344 */     return accum;
/* 237:    */   }
/* 238:    */   
/* 239:    */   private static byte[] intToByteArray(int i)
/* 240:    */   {
/* 241:354 */     byte[] b = new byte[4];
/* 242:355 */     b[0] = ((byte)(i & 0xFF));
/* 243:356 */     b[1] = ((byte)(i >> 8 & 0xFF));
/* 244:357 */     b[2] = ((byte)(i >> 16 & 0xFF));
/* 245:358 */     b[3] = ((byte)(i >> 24 & 0xFF));
/* 246:359 */     return b;
/* 247:    */   }
/* 248:    */   
/* 249:    */   public static byte[] shortToByteArray(short data)
/* 250:    */   {
/* 251:365 */     return new byte[] { (byte)(data & 0xFF), (byte)(data >>> 8 & 0xFF) };
/* 252:    */   }
/* 253:    */ }


/* Location:           C:\Users\SmileyFacedHacker\Downloads\StillBorn RAT (Android)\StillBorn RAT (Android)\StillBornRAT.jar
 * Qualified Name:     utils.wavIO
 * JD-Core Version:    0.7.0.1
 */