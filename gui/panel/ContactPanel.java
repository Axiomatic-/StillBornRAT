/*   1:    */ package gui.panel;
/*   2:    */ 
/*   3:    */ import gui.AdvContactGUI;
/*   4:    */ import gui.UserGUI;
/*   5:    */ import java.awt.Color;
/*   6:    */ import java.awt.Component;
/*   7:    */ import java.awt.FlowLayout;
/*   8:    */ import java.awt.Image;
/*   9:    */ import java.awt.Toolkit;
/*  10:    */ import java.awt.event.ActionEvent;
/*  11:    */ import java.awt.event.ActionListener;
/*  12:    */ import java.awt.event.MouseAdapter;
/*  13:    */ import java.awt.event.MouseEvent;
/*  14:    */ import java.util.ArrayList;
/*  15:    */ import java.util.HashMap;
/*  16:    */ import javax.swing.GroupLayout;
/*  17:    */ import javax.swing.GroupLayout.Alignment;
/*  18:    */ import javax.swing.GroupLayout.ParallelGroup;
/*  19:    */ import javax.swing.GroupLayout.SequentialGroup;
/*  20:    */ import javax.swing.ImageIcon;
/*  21:    */ import javax.swing.JButton;
/*  22:    */ import javax.swing.JLabel;
/*  23:    */ import javax.swing.JList;
/*  24:    */ import javax.swing.JOptionPane;
/*  25:    */ import javax.swing.JPanel;
/*  26:    */ import javax.swing.JScrollPane;
/*  27:    */ import javax.swing.JSplitPane;
/*  28:    */ import javax.swing.JTextArea;
/*  29:    */ import javax.swing.LayoutStyle.ComponentPlacement;
/*  30:    */ import javax.swing.ListCellRenderer;
/*  31:    */ import javax.swing.border.TitledBorder;
/*  32:    */ import utils.Contact;
/*  33:    */ 
/*  34:    */ public class ContactPanel
/*  35:    */   extends JPanel
/*  36:    */ {
/*  37:    */   private JList list;
/*  38:    */   private JLabel lblValId;
/*  39:    */   private JLabel lblValname;
/*  40:    */   private JLabel lblValnumber;
/*  41:    */   private JTextArea txtrValaddr;
/*  42:    */   private JLabel lblValemail;
/*  43:    */   private JButton btnCall;
/*  44:    */   private JButton btnSms;
/*  45:    */   private JButton btnMoreInformations;
/*  46:    */   private UserGUI gui;
/*  47:    */   private HashMap<Integer, Contact> contactMap;
/*  48:    */   private JSplitPane splitPane;
/*  49:    */   
/*  50:    */   public ContactPanel(UserGUI gui)
/*  51:    */   {
/*  52: 83 */     this.gui = gui;
/*  53:    */     
/*  54: 85 */     this.splitPane = new JSplitPane();
/*  55: 86 */     this.splitPane.setResizeWeight(1.0D);
/*  56: 87 */     GroupLayout groupLayout = new GroupLayout(this);
/*  57: 88 */     groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(
/*  58: 89 */       GroupLayout.Alignment.LEADING).addGroup(
/*  59: 90 */       groupLayout
/*  60: 91 */       .createSequentialGroup()
/*  61: 92 */       .addContainerGap()
/*  62: 93 */       .addComponent(this.splitPane, -1, 552, 
/*  63: 94 */       32767).addContainerGap()));
/*  64: 95 */     groupLayout.setVerticalGroup(groupLayout.createParallelGroup(
/*  65: 96 */       GroupLayout.Alignment.LEADING).addGroup(
/*  66: 97 */       groupLayout
/*  67: 98 */       .createSequentialGroup()
/*  68: 99 */       .addContainerGap()
/*  69:100 */       .addComponent(this.splitPane, -1, 452, 
/*  70:101 */       32767).addGap(10)));
/*  71:    */     
/*  72:103 */     JScrollPane scrollPane = new JScrollPane();
/*  73:104 */     this.splitPane.setLeftComponent(scrollPane);
/*  74:    */     
/*  75:106 */     this.list = new JList();
/*  76:107 */     this.list.addMouseListener(new MouseAdapter()
/*  77:    */     {
/*  78:    */       public void mouseClicked(MouseEvent e)
/*  79:    */       {
/*  80:110 */         ContactPanel.this.contactListMouseClicked();
/*  81:    */       }
/*  82:112 */     });
/*  83:113 */     scrollPane.setViewportView(this.list);
/*  84:    */     
/*  85:115 */     JPanel panel_2 = new JPanel();
/*  86:116 */     this.splitPane.setRightComponent(panel_2);
/*  87:    */     
/*  88:118 */     JPanel panel = new JPanel();
/*  89:119 */     panel.setBorder(new TitledBorder(null, "Bilgiler", 
/*  90:120 */       4, 2, null, null));
/*  91:    */     
/*  92:122 */     this.btnCall = new JButton("Ara");
/*  93:123 */     this.btnCall.setEnabled(false);
/*  94:124 */     this.btnCall.addActionListener(new ActionListener()
/*  95:    */     {
/*  96:    */       public void actionPerformed(ActionEvent e)
/*  97:    */       {
/*  98:127 */         ContactPanel.this.fireButtonCall();
/*  99:    */       }
/* 100:130 */     });
/* 101:131 */     this.btnSms = new JButton("SMS");
/* 102:132 */     this.btnSms.setEnabled(false);
/* 103:133 */     this.btnSms.addActionListener(new ActionListener()
/* 104:    */     {
/* 105:    */       public void actionPerformed(ActionEvent e)
/* 106:    */       {
/* 107:136 */         ContactPanel.this.fireButtonSMS();
/* 108:    */       }
/* 109:139 */     });
/* 110:140 */     this.btnMoreInformations = new JButton("Ayrıntılı Bilgi");
/* 111:141 */     this.btnMoreInformations.setEnabled(false);
/* 112:142 */     this.btnMoreInformations.addActionListener(new ActionListener()
/* 113:    */     {
/* 114:    */       public void actionPerformed(ActionEvent e)
/* 115:    */       {
/* 116:145 */         ContactPanel.this.fireButtonMoreInfo();
/* 117:    */       }
/* 118:148 */     });
/* 119:149 */     JLabel lblId = new JLabel("No :");
/* 120:    */     
/* 121:151 */     this.lblValId = new JLabel("bilinmiyor");
/* 122:    */     
/* 123:153 */     JLabel lblName = new JLabel("İsim :");
/* 124:    */     
/* 125:155 */     this.lblValname = new JLabel("bilinmiyor");
/* 126:    */     
/* 127:157 */     JLabel lblNumber = new JLabel("Tel. No. :");
/* 128:    */     
/* 129:159 */     this.lblValnumber = new JLabel("bilinmiyor");
/* 130:    */     
/* 131:161 */     JLabel lblAddress = new JLabel("Adres :");
/* 132:    */     
/* 133:163 */     this.txtrValaddr = new JTextArea();
/* 134:164 */     this.txtrValaddr.setText("bilinmiyor");
/* 135:    */     
/* 136:166 */     JLabel lblEmail = new JLabel("Email :");
/* 137:    */     
/* 138:168 */     this.lblValemail = new JLabel("bilinmiyor");
/* 139:169 */     GroupLayout gl_panel = new GroupLayout(panel);
/* 140:170 */     gl_panel.setHorizontalGroup(gl_panel
/* 141:171 */       .createParallelGroup(GroupLayout.Alignment.LEADING)
/* 142:172 */       .addGroup(
/* 143:173 */       gl_panel.createSequentialGroup()
/* 144:174 */       .addContainerGap()
/* 145:175 */       .addGroup(
/* 146:176 */       gl_panel.createParallelGroup(
/* 147:177 */       GroupLayout.Alignment.LEADING)
/* 148:178 */       .addComponent(
/* 149:179 */       this.txtrValaddr, 
/* 150:180 */       -1, 
/* 151:181 */       197, 32767)
/* 152:182 */       .addComponent(
/* 153:183 */       this.btnMoreInformations, 
/* 154:184 */       -1, 
/* 155:185 */       197, 32767)
/* 156:186 */       .addGroup(
/* 157:187 */       gl_panel.createSequentialGroup()
/* 158:188 */       .addComponent(
/* 159:189 */       this.btnCall, 
/* 160:190 */       -1, 
/* 161:191 */       95, 
/* 162:192 */       32767)
/* 163:193 */       .addPreferredGap(
/* 164:194 */       LayoutStyle.ComponentPlacement.RELATED)
/* 165:195 */       .addComponent(
/* 166:196 */       this.btnSms, 
/* 167:197 */       -1, 
/* 168:198 */       96, 
/* 169:199 */       32767))
/* 170:200 */       .addGroup(
/* 171:201 */       gl_panel.createSequentialGroup()
/* 172:202 */       .addGroup(
/* 173:203 */       gl_panel.createParallelGroup(
/* 174:204 */       GroupLayout.Alignment.LEADING)
/* 175:205 */       .addComponent(
/* 176:206 */       lblId)
/* 177:207 */       .addComponent(
/* 178:208 */       lblName)
/* 179:209 */       .addComponent(
/* 180:210 */       lblNumber)
/* 181:211 */       .addComponent(
/* 182:212 */       lblAddress))
/* 183:213 */       .addPreferredGap(
/* 184:214 */       LayoutStyle.ComponentPlacement.UNRELATED)
/* 185:215 */       .addGroup(
/* 186:216 */       gl_panel.createParallelGroup(
/* 187:217 */       GroupLayout.Alignment.LEADING)
/* 188:218 */       .addComponent(
/* 189:219 */       this.lblValnumber)
/* 190:220 */       .addComponent(
/* 191:221 */       this.lblValname)
/* 192:222 */       .addComponent(
/* 193:223 */       this.lblValId)))
/* 194:224 */       .addGroup(
/* 195:225 */       gl_panel.createSequentialGroup()
/* 196:226 */       .addComponent(
/* 197:227 */       lblEmail)
/* 198:228 */       .addPreferredGap(
/* 199:229 */       LayoutStyle.ComponentPlacement.UNRELATED)
/* 200:230 */       .addComponent(
/* 201:231 */       this.lblValemail)))
/* 202:232 */       .addContainerGap()));
/* 203:233 */     gl_panel.setVerticalGroup(gl_panel.createParallelGroup(
/* 204:234 */       GroupLayout.Alignment.TRAILING)
/* 205:235 */       .addGroup(
/* 206:236 */       gl_panel.createSequentialGroup()
/* 207:237 */       .addContainerGap()
/* 208:238 */       .addGroup(
/* 209:239 */       gl_panel.createParallelGroup(
/* 210:240 */       GroupLayout.Alignment.BASELINE)
/* 211:241 */       .addComponent(lblId)
/* 212:242 */       .addComponent(this.lblValId))
/* 213:243 */       .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
/* 214:244 */       .addGroup(
/* 215:245 */       gl_panel.createParallelGroup(
/* 216:246 */       GroupLayout.Alignment.BASELINE)
/* 217:247 */       .addComponent(lblName)
/* 218:248 */       .addComponent(this.lblValname))
/* 219:249 */       .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
/* 220:250 */       .addGroup(
/* 221:251 */       gl_panel.createParallelGroup(
/* 222:252 */       GroupLayout.Alignment.BASELINE)
/* 223:253 */       .addComponent(lblNumber)
/* 224:254 */       .addComponent(this.lblValnumber))
/* 225:255 */       .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
/* 226:256 */       .addComponent(lblAddress)
/* 227:257 */       .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 228:258 */       .addComponent(this.txtrValaddr, 
/* 229:259 */       -2, 42, 
/* 230:260 */       -2)
/* 231:261 */       .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
/* 232:262 */       .addGroup(
/* 233:263 */       gl_panel.createParallelGroup(
/* 234:264 */       GroupLayout.Alignment.BASELINE)
/* 235:265 */       .addComponent(lblEmail)
/* 236:266 */       .addComponent(this.lblValemail))
/* 237:267 */       .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 
/* 238:268 */       191, 32767)
/* 239:269 */       .addComponent(this.btnMoreInformations)
/* 240:270 */       .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 241:271 */       .addGroup(
/* 242:272 */       gl_panel.createParallelGroup(
/* 243:273 */       GroupLayout.Alignment.BASELINE)
/* 244:274 */       .addComponent(this.btnCall)
/* 245:275 */       .addComponent(this.btnSms))
/* 246:276 */       .addContainerGap()));
/* 247:277 */     panel.setLayout(gl_panel);
/* 248:    */     
/* 249:279 */     JPanel panel_1 = new JPanel();
/* 250:280 */     panel_1.setBorder(new TitledBorder(null, "Genel Ayarlar", 
/* 251:281 */       4, 2, null, null));
/* 252:    */     
/* 253:283 */     JButton btnRefreshList = new JButton("Listeyi yenile");
/* 254:284 */     btnRefreshList.addActionListener(new ActionListener()
/* 255:    */     {
/* 256:    */       public void actionPerformed(ActionEvent e)
/* 257:    */       {
/* 258:287 */         ContactPanel.this.fireRefreshList();
/* 259:    */       }
/* 260:289 */     });
/* 261:290 */     panel_1.add(btnRefreshList);
/* 262:291 */     GroupLayout gl_panel_2 = new GroupLayout(panel_2);
/* 263:292 */     gl_panel_2.setHorizontalGroup(gl_panel_2.createParallelGroup(
/* 264:293 */       GroupLayout.Alignment.TRAILING).addGroup(
/* 265:294 */       gl_panel_2
/* 266:295 */       .createSequentialGroup()
/* 267:296 */       .addContainerGap()
/* 268:297 */       .addGroup(
/* 269:298 */       gl_panel_2
/* 270:299 */       .createParallelGroup(GroupLayout.Alignment.LEADING)
/* 271:300 */       .addComponent(panel, 
/* 272:301 */       -1, 219, 
/* 273:302 */       32767)
/* 274:303 */       .addComponent(panel_1, 
/* 275:304 */       GroupLayout.Alignment.TRAILING, 
/* 276:305 */       -1, 219, 
/* 277:306 */       32767))
/* 278:307 */       .addContainerGap()));
/* 279:308 */     gl_panel_2.setVerticalGroup(gl_panel_2.createParallelGroup(
/* 280:309 */       GroupLayout.Alignment.TRAILING).addGroup(
/* 281:310 */       gl_panel_2
/* 282:311 */       .createSequentialGroup()
/* 283:312 */       .addContainerGap()
/* 284:313 */       .addComponent(panel, -1, 365, 
/* 285:314 */       32767)
/* 286:315 */       .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 287:316 */       .addComponent(panel_1, -2, 
/* 288:317 */       -1, 
/* 289:318 */       -2).addContainerGap()));
/* 290:319 */     panel_2.setLayout(gl_panel_2);
/* 291:320 */     setLayout(groupLayout);
/* 292:    */   }
/* 293:    */   
/* 294:    */   private void contactListMouseClicked()
/* 295:    */   {
/* 296:324 */     int selected = this.list.getSelectedIndex();
/* 297:325 */     Contact contact = (Contact)this.contactMap.get(Integer.valueOf(selected));
/* 298:326 */     if (contact != null)
/* 299:    */     {
/* 300:327 */       this.lblValId.setText(String.valueOf(contact.getId()));
/* 301:328 */       if (contact.getDisplay_name() != null) {
/* 302:329 */         this.lblValname.setText(contact.getDisplay_name());
/* 303:    */       }
/* 304:330 */       if (contact.getPhones() != null) {
/* 305:331 */         this.lblValnumber.setText((String)contact.getPhones().get(0));
/* 306:    */       }
/* 307:332 */       if (contact.getEmails() != null) {
/* 308:333 */         this.lblValemail.setText((String)contact.getEmails().get(0));
/* 309:    */       }
/* 310:335 */       String txtAddr = "";
/* 311:336 */       if (contact.getStreet() != null) {
/* 312:337 */         txtAddr = txtAddr + contact.getStreet() + "\n";
/* 313:    */       }
/* 314:338 */       if (contact.getCity() != null) {
/* 315:339 */         txtAddr = txtAddr + contact.getCity() + "\n";
/* 316:    */       }
/* 317:340 */       if (contact.getRegion() != null) {
/* 318:341 */         txtAddr = txtAddr + contact.getRegion() + " ";
/* 319:    */       }
/* 320:342 */       if (contact.getCountry() != null) {
/* 321:343 */         txtAddr = txtAddr + contact.getCountry();
/* 322:    */       }
/* 323:344 */       this.txtrValaddr.setText(txtAddr);
/* 324:    */       
/* 325:346 */       this.btnCall.setEnabled(true);
/* 326:347 */       this.btnMoreInformations.setEnabled(true);
/* 327:348 */       this.btnSms.setEnabled(true);
/* 328:    */     }
/* 329:    */     else
/* 330:    */     {
/* 331:350 */       this.lblValId.setText("bilinmiyor");
/* 332:351 */       this.lblValname.setText("bilinmiyor");
/* 333:352 */       this.lblValnumber.setText("bilinmiyor");
/* 334:353 */       this.lblValemail.setText("bilinmiyor");
/* 335:354 */       this.txtrValaddr.setText("bilinmiyor");
/* 336:    */       
/* 337:356 */       this.btnCall.setEnabled(false);
/* 338:357 */       this.btnMoreInformations.setEnabled(false);
/* 339:358 */       this.btnSms.setEnabled(false);
/* 340:    */     }
/* 341:    */   }
/* 342:    */   
/* 343:    */   private void fireRefreshList()
/* 344:    */   {
/* 345:363 */     this.gui.fireGetContacts();
/* 346:    */   }
/* 347:    */   
/* 348:    */   private void fireButtonCall()
/* 349:    */   {
/* 350:367 */     if (!this.lblValnumber.getText().equals("n/a")) {
/* 351:368 */       this.gui.fireGiveCall(this.lblValnumber.getText());
/* 352:    */     }
/* 353:    */   }
/* 354:    */   
/* 355:    */   private void fireButtonSMS()
/* 356:    */   {
/* 357:372 */     String txt = JOptionPane.showInputDialog(this, "Metni girin :");
/* 358:373 */     if (!this.lblValnumber.getText().equals("n/a")) {
/* 359:374 */       this.gui.fireSendSMS(this.lblValnumber.getText(), txt);
/* 360:    */     }
/* 361:    */   }
/* 362:    */   
/* 363:    */   private void fireButtonMoreInfo()
/* 364:    */   {
/* 365:378 */     int selected = this.list.getSelectedIndex();
/* 366:379 */     Contact contact = (Contact)this.contactMap.get(Integer.valueOf(selected));
/* 367:380 */     new AdvContactGUI(contact);
/* 368:    */   }
/* 369:    */   
/* 370:    */   public void updateContactList(ArrayList<Contact> contacts)
/* 371:    */   {
/* 372:409 */     this.contactMap = new HashMap();
/* 373:410 */     String[] values = new String[contacts.size()];
/* 374:411 */     int ptr = 0;
/* 375:412 */     this.list.setCellRenderer(new ImageListCellRenderer());
/* 376:    */     
/* 377:414 */     Object[] panels = new Object[contacts.size()];
/* 378:416 */     for (Contact contact : contacts) {
/* 379:418 */       if (contact.getDisplay_name() != null)
/* 380:    */       {
/* 381:420 */         this.contactMap.put(Integer.valueOf(ptr), contact);
/* 382:421 */         byte[] im = contact.getPhoto();
/* 383:    */         ImageIcon imgResize;
/* 384:    */         ImageIcon imgResize;
/* 385:423 */         if (im == null)
/* 386:    */         {
/* 387:424 */           ImageIcon getImg = new ImageIcon(Toolkit.getDefaultToolkit().getImage(UserGUI.class.getResource("/gui/res/People.png")));
/* 388:425 */           Image img = getImg.getImage();
/* 389:426 */           Image newimg = img.getScaledInstance(64, 64, 4);
/* 390:427 */           imgResize = new ImageIcon(newimg);
/* 391:    */         }
/* 392:    */         else
/* 393:    */         {
/* 394:430 */           ImageIcon getImg = new ImageIcon(contact.getPhoto());
/* 395:431 */           Image img = getImg.getImage();
/* 396:432 */           Image newimg = img.getScaledInstance(64, 64, 4);
/* 397:433 */           imgResize = new ImageIcon(newimg);
/* 398:    */         }
/* 399:437 */         if (contact.getPhones() == null)
/* 400:    */         {
/* 401:439 */           JLabel imgLabel = new JLabel("tel. yok - " + contact.getDisplay_name() + " (no:" + contact.getId() + ")", imgResize, 2);
/* 402:440 */           JPanel imgPanel = new JPanel(new FlowLayout(0));
/* 403:441 */           imgPanel.add(imgLabel);
/* 404:    */           
/* 405:443 */           panels[ptr] = imgPanel;
/* 406:    */         }
/* 407:    */         else
/* 408:    */         {
/* 409:447 */           JLabel imgLabel = new JLabel((String)contact.getPhones().get(0) + " - " + contact.getDisplay_name() + " (no:" + contact.getId() + ")", imgResize, 2);
/* 410:448 */           JPanel imgPanel = new JPanel(new FlowLayout(0));
/* 411:449 */           imgPanel.add(imgLabel);
/* 412:    */           
/* 413:451 */           panels[ptr] = imgPanel;
/* 414:    */         }
/* 415:453 */         ptr++;
/* 416:    */       }
/* 417:    */     }
/* 418:457 */     this.list.setListData(panels);
/* 419:    */   }
/* 420:    */   
/* 421:    */   class ImageListCellRenderer
/* 422:    */     implements ListCellRenderer
/* 423:    */   {
/* 424:    */     ImageListCellRenderer() {}
/* 425:    */     
/* 426:    */     public Component getListCellRendererComponent(JList jlist, Object value, int cellIndex, boolean isSelected, boolean cellHasFocus)
/* 427:    */     {
/* 428:477 */       if ((value instanceof JPanel))
/* 429:    */       {
/* 430:479 */         Component component = (Component)value;
/* 431:    */         
/* 432:481 */         component.setForeground(Color.white);
/* 433:482 */         component.setBackground(isSelected ? Color.lightGray : Color.white);
/* 434:483 */         return component;
/* 435:    */       }
/* 436:488 */       return new JLabel("???");
/* 437:    */     }
/* 438:    */   }
/* 439:    */ }


/* Location:           C:\Users\SmileyFacedHacker\Downloads\StillBorn RAT (Android)\StillBorn RAT (Android)\StillBornRAT.jar
 * Qualified Name:     gui.panel.ContactPanel
 * JD-Core Version:    0.7.0.1
 */