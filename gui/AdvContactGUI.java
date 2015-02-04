/*   1:    */ package gui;
/*   2:    */ 
/*   3:    */ import java.awt.Image;
/*   4:    */ import java.util.ArrayList;
/*   5:    */ import java.util.Date;
/*   6:    */ import java.util.Iterator;
/*   7:    */ import javax.swing.GroupLayout;
/*   8:    */ import javax.swing.GroupLayout.Alignment;
/*   9:    */ import javax.swing.GroupLayout.ParallelGroup;
/*  10:    */ import javax.swing.GroupLayout.SequentialGroup;
/*  11:    */ import javax.swing.ImageIcon;
/*  12:    */ import javax.swing.JFrame;
/*  13:    */ import javax.swing.JLabel;
/*  14:    */ import javax.swing.JPanel;
/*  15:    */ import javax.swing.JScrollPane;
/*  16:    */ import javax.swing.JTextArea;
/*  17:    */ import javax.swing.LayoutStyle.ComponentPlacement;
/*  18:    */ import javax.swing.border.EmptyBorder;
/*  19:    */ import javax.swing.border.EtchedBorder;
/*  20:    */ import utils.Contact;
/*  21:    */ 
/*  22:    */ public class AdvContactGUI
/*  23:    */   extends JFrame
/*  24:    */ {
/*  25:    */   private JLabel lblValname;
/*  26:    */   private JLabel lblValid;
/*  27:    */   private JLabel lblValorga;
/*  28:    */   private JLabel lblValstatus;
/*  29:    */   private JLabel lblValtimes;
/*  30:    */   private JLabel lblVallasttime;
/*  31:    */   private JLabel lblPicture;
/*  32:    */   private JTextArea areaPhones;
/*  33:    */   private JTextArea areaAdress;
/*  34:    */   private JTextArea areaEmails;
/*  35:    */   private JTextArea areaMessaging;
/*  36:    */   private JTextArea areaNotes;
/*  37:    */   private JPanel contentPane;
/*  38:    */   private Contact contact;
/*  39:    */   
/*  40:    */   public AdvContactGUI(Contact contact)
/*  41:    */   {
/*  42: 63 */     this.contact = contact;
/*  43:    */     
/*  44: 65 */     setVisible(true);
/*  45: 66 */     setLocationRelativeTo(null);
/*  46:    */     
/*  47: 68 */     setDefaultCloseOperation(2);
/*  48: 69 */     setBounds(100, 100, 500, 502);
/*  49: 70 */     this.contentPane = new JPanel();
/*  50: 71 */     this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
/*  51: 72 */     setContentPane(this.contentPane);
/*  52:    */     
/*  53: 74 */     JPanel panel_1 = new JPanel();
/*  54: 75 */     panel_1.setBorder(new EtchedBorder(1, null, null));
/*  55:    */     
/*  56: 77 */     JLabel lblName = new JLabel("İsim :");
/*  57:    */     
/*  58: 79 */     this.lblValname = new JLabel("val_name");
/*  59:    */     
/*  60: 81 */     JLabel lblPhoneNumber = new JLabel("Telefon Numarası :");
/*  61:    */     
/*  62: 83 */     JLabel lblAdress = new JLabel("Adres :");
/*  63:    */     
/*  64: 85 */     this.lblValid = new JLabel("val_id");
/*  65:    */     
/*  66: 87 */     JLabel lblId = new JLabel("No :");
/*  67:    */     
/*  68: 89 */     JLabel lblEmails = new JLabel("Email adresi :");
/*  69:    */     
/*  70: 91 */     JLabel lblMessaging = new JLabel("Mesajlaşma :");
/*  71:    */     
/*  72: 93 */     JLabel lblOrganisation = new JLabel("Şirket :");
/*  73:    */     
/*  74: 95 */     this.lblValorga = new JLabel("val_orga");
/*  75:    */     
/*  76: 97 */     JLabel lblOrganisationStatus = new JLabel("Şirket durumu :");
/*  77:    */     
/*  78: 99 */     this.lblValstatus = new JLabel("val_status");
/*  79:    */     
/*  80:101 */     JLabel lblTimesContacted = new JLabel("Görüşme sayısı :");
/*  81:    */     
/*  82:103 */     this.lblValtimes = new JLabel("val_times");
/*  83:    */     
/*  84:105 */     JLabel lblLastTimeContacted = new JLabel("En son görüşme :");
/*  85:    */     
/*  86:107 */     this.lblVallasttime = new JLabel("val_last_time");
/*  87:    */     
/*  88:109 */     JLabel lblNotes = new JLabel("Notlar :");
/*  89:    */     
/*  90:111 */     JScrollPane scrollPane = new JScrollPane();
/*  91:    */     
/*  92:113 */     JScrollPane scrollPane_1 = new JScrollPane();
/*  93:    */     
/*  94:115 */     JScrollPane scrollPane_2 = new JScrollPane();
/*  95:    */     
/*  96:117 */     JScrollPane scrollPane_3 = new JScrollPane();
/*  97:    */     
/*  98:119 */     JScrollPane scrollPane_4 = new JScrollPane();
/*  99:120 */     GroupLayout gl_contentPane = new GroupLayout(this.contentPane);
/* 100:121 */     gl_contentPane.setHorizontalGroup(
/* 101:122 */       gl_contentPane.createParallelGroup(GroupLayout.Alignment.TRAILING)
/* 102:123 */       .addGroup(gl_contentPane.createSequentialGroup()
/* 103:124 */       .addContainerGap()
/* 104:125 */       .addGroup(gl_contentPane.createParallelGroup(GroupLayout.Alignment.TRAILING)
/* 105:126 */       .addComponent(scrollPane_4, GroupLayout.Alignment.LEADING, -1, 454, 32767)
/* 106:127 */       .addComponent(scrollPane_3, GroupLayout.Alignment.LEADING, -1, 454, 32767)
/* 107:128 */       .addComponent(scrollPane_2, GroupLayout.Alignment.LEADING, -1, 454, 32767)
/* 108:129 */       .addGroup(GroupLayout.Alignment.LEADING, gl_contentPane.createSequentialGroup()
/* 109:130 */       .addComponent(panel_1, -2, 150, -2)
/* 110:131 */       .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
/* 111:132 */       .addGroup(gl_contentPane.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 112:133 */       .addComponent(scrollPane_1, -1, 294, 32767)
/* 113:134 */       .addComponent(lblAdress)
/* 114:135 */       .addComponent(lblPhoneNumber)
/* 115:136 */       .addGroup(GroupLayout.Alignment.TRAILING, gl_contentPane.createSequentialGroup()
/* 116:137 */       .addComponent(lblName)
/* 117:138 */       .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
/* 118:139 */       .addComponent(this.lblValname)
/* 119:140 */       .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 162, 32767)
/* 120:141 */       .addComponent(lblId)
/* 121:142 */       .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
/* 122:143 */       .addComponent(this.lblValid))
/* 123:144 */       .addComponent(scrollPane, -1, 294, 32767)))
/* 124:145 */       .addComponent(lblEmails, GroupLayout.Alignment.LEADING)
/* 125:146 */       .addComponent(lblMessaging, GroupLayout.Alignment.LEADING)
/* 126:147 */       .addGroup(GroupLayout.Alignment.LEADING, gl_contentPane.createSequentialGroup()
/* 127:148 */       .addComponent(lblOrganisation)
/* 128:149 */       .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
/* 129:150 */       .addComponent(this.lblValorga))
/* 130:151 */       .addGroup(GroupLayout.Alignment.LEADING, gl_contentPane.createSequentialGroup()
/* 131:152 */       .addComponent(lblOrganisationStatus)
/* 132:153 */       .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
/* 133:154 */       .addComponent(this.lblValstatus))
/* 134:155 */       .addGroup(GroupLayout.Alignment.LEADING, gl_contentPane.createSequentialGroup()
/* 135:156 */       .addComponent(lblTimesContacted)
/* 136:157 */       .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
/* 137:158 */       .addComponent(this.lblValtimes))
/* 138:159 */       .addGroup(GroupLayout.Alignment.LEADING, gl_contentPane.createSequentialGroup()
/* 139:160 */       .addComponent(lblLastTimeContacted)
/* 140:161 */       .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
/* 141:162 */       .addComponent(this.lblVallasttime))
/* 142:163 */       .addComponent(lblNotes, GroupLayout.Alignment.LEADING))
/* 143:164 */       .addContainerGap()));
/* 144:    */     
/* 145:166 */     gl_contentPane.setVerticalGroup(
/* 146:167 */       gl_contentPane.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 147:168 */       .addGroup(gl_contentPane.createSequentialGroup()
/* 148:169 */       .addContainerGap()
/* 149:170 */       .addGroup(gl_contentPane.createParallelGroup(GroupLayout.Alignment.LEADING, false)
/* 150:171 */       .addComponent(panel_1, -2, 150, -2)
/* 151:172 */       .addGroup(gl_contentPane.createSequentialGroup()
/* 152:173 */       .addGroup(gl_contentPane.createParallelGroup(GroupLayout.Alignment.BASELINE)
/* 153:174 */       .addComponent(lblName)
/* 154:175 */       .addComponent(this.lblValname)
/* 155:176 */       .addComponent(this.lblValid)
/* 156:177 */       .addComponent(lblId))
/* 157:178 */       .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
/* 158:179 */       .addComponent(lblPhoneNumber)
/* 159:180 */       .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 160:181 */       .addComponent(scrollPane, -2, 35, -2)
/* 161:182 */       .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 162:183 */       .addComponent(lblAdress)
/* 163:184 */       .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
/* 164:185 */       .addComponent(scrollPane_1)))
/* 165:186 */       .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
/* 166:187 */       .addComponent(lblEmails)
/* 167:188 */       .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 168:189 */       .addComponent(scrollPane_2, -2, 41, -2)
/* 169:190 */       .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 170:191 */       .addComponent(lblMessaging)
/* 171:192 */       .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 172:193 */       .addComponent(scrollPane_3, -2, 41, -2)
/* 173:194 */       .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 174:195 */       .addGroup(gl_contentPane.createParallelGroup(GroupLayout.Alignment.BASELINE)
/* 175:196 */       .addComponent(lblOrganisation)
/* 176:197 */       .addComponent(this.lblValorga))
/* 177:198 */       .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 178:199 */       .addGroup(gl_contentPane.createParallelGroup(GroupLayout.Alignment.BASELINE)
/* 179:200 */       .addComponent(lblOrganisationStatus)
/* 180:201 */       .addComponent(this.lblValstatus))
/* 181:202 */       .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 182:203 */       .addGroup(gl_contentPane.createParallelGroup(GroupLayout.Alignment.BASELINE)
/* 183:204 */       .addComponent(lblTimesContacted)
/* 184:205 */       .addComponent(this.lblValtimes))
/* 185:206 */       .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 186:207 */       .addGroup(gl_contentPane.createParallelGroup(GroupLayout.Alignment.BASELINE)
/* 187:208 */       .addComponent(lblLastTimeContacted)
/* 188:209 */       .addComponent(this.lblVallasttime))
/* 189:210 */       .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 190:211 */       .addComponent(lblNotes)
/* 191:212 */       .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 192:213 */       .addComponent(scrollPane_4, -1, 37, 32767)
/* 193:214 */       .addContainerGap()));
/* 194:    */     
/* 195:    */ 
/* 196:217 */     this.areaNotes = new JTextArea();
/* 197:218 */     scrollPane_4.setViewportView(this.areaNotes);
/* 198:    */     
/* 199:220 */     this.areaMessaging = new JTextArea();
/* 200:221 */     scrollPane_3.setViewportView(this.areaMessaging);
/* 201:    */     
/* 202:223 */     this.areaEmails = new JTextArea();
/* 203:224 */     scrollPane_2.setViewportView(this.areaEmails);
/* 204:    */     
/* 205:226 */     this.areaAdress = new JTextArea();
/* 206:227 */     scrollPane_1.setViewportView(this.areaAdress);
/* 207:    */     
/* 208:229 */     this.areaPhones = new JTextArea();
/* 209:230 */     scrollPane.setViewportView(this.areaPhones);
/* 210:    */     
/* 211:232 */     this.lblPicture = new JLabel("picture");
/* 212:233 */     GroupLayout gl_panel_1 = new GroupLayout(panel_1);
/* 213:234 */     gl_panel_1.setHorizontalGroup(
/* 214:235 */       gl_panel_1.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 215:236 */       .addGroup(gl_panel_1.createSequentialGroup()
/* 216:237 */       .addComponent(this.lblPicture)
/* 217:238 */       .addContainerGap(113, 32767)));
/* 218:    */     
/* 219:240 */     gl_panel_1.setVerticalGroup(
/* 220:241 */       gl_panel_1.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 221:242 */       .addGroup(gl_panel_1.createSequentialGroup()
/* 222:243 */       .addComponent(this.lblPicture)
/* 223:244 */       .addContainerGap(132, 32767)));
/* 224:    */     
/* 225:246 */     panel_1.setLayout(gl_panel_1);
/* 226:247 */     this.contentPane.setLayout(gl_contentPane);
/* 227:    */     
/* 228:249 */     initContactInfo();
/* 229:    */   }
/* 230:    */   
/* 231:    */   private void initContactInfo()
/* 232:    */   {
/* 233:253 */     this.lblValid.setText(this.contact.getId());
/* 234:254 */     this.lblVallasttime.setText(new Date(this.contact.getLast_time_contacted()));
/* 235:255 */     this.lblValname.setText(this.contact.getDisplay_name());
/* 236:256 */     if (this.contact.getOrganisationName() != null) {
/* 237:256 */       this.lblValorga.setText(this.contact.getOrganisationName());
/* 238:    */     }
/* 239:257 */     if (this.contact.getOrganisationStatus() != null) {
/* 240:257 */       this.lblValstatus.setText(this.contact.getOrganisationStatus());
/* 241:    */     }
/* 242:258 */     this.lblValtimes.setText(this.contact.getTimes_contacted());
/* 243:    */     
/* 244:260 */     String temp = "";
/* 245:    */     Iterator localIterator;
/* 246:261 */     if (this.contact.getEmails() != null)
/* 247:    */     {
/* 248:    */       String s;
/* 249:262 */       for (localIterator = this.contact.getEmails().iterator(); localIterator.hasNext(); temp = temp + s + "\n") {
/* 250:262 */         s = (String)localIterator.next();
/* 251:    */       }
/* 252:    */     }
/* 253:264 */     this.areaEmails.setText(temp);
/* 254:    */     
/* 255:266 */     temp = "";
/* 256:267 */     if (this.contact.getEmails() != null)
/* 257:    */     {
/* 258:    */       String s;
/* 259:268 */       for (localIterator = this.contact.getNotes().iterator(); localIterator.hasNext(); temp = temp + s + "\n") {
/* 260:268 */         s = (String)localIterator.next();
/* 261:    */       }
/* 262:    */     }
/* 263:270 */     this.areaNotes.setText(temp);
/* 264:    */     
/* 265:272 */     temp = "";
/* 266:273 */     if (this.contact.getEmails() != null)
/* 267:    */     {
/* 268:    */       String s;
/* 269:274 */       for (localIterator = this.contact.getMessaging().iterator(); localIterator.hasNext(); temp = temp + s + "\n") {
/* 270:274 */         s = (String)localIterator.next();
/* 271:    */       }
/* 272:    */     }
/* 273:276 */     this.areaMessaging.setText(temp);
/* 274:    */     
/* 275:278 */     temp = "";
/* 276:279 */     if (this.contact.getEmails() != null)
/* 277:    */     {
/* 278:    */       String s;
/* 279:280 */       for (localIterator = this.contact.getPhones().iterator(); localIterator.hasNext(); temp = temp + s + "\n") {
/* 280:280 */         s = (String)localIterator.next();
/* 281:    */       }
/* 282:    */     }
/* 283:282 */     this.areaPhones.setText(temp);
/* 284:    */     
/* 285:284 */     temp = "";
/* 286:285 */     if (this.contact.getStreet() != null) {
/* 287:285 */       temp = temp + this.contact.getStreet() + "\n";
/* 288:    */     }
/* 289:286 */     if (this.contact.getCity() != null) {
/* 290:286 */       temp = temp + this.contact.getCity() + "\n";
/* 291:    */     }
/* 292:287 */     if (this.contact.getRegion() != null) {
/* 293:287 */       temp = temp + this.contact.getRegion() + " ";
/* 294:    */     }
/* 295:288 */     if (this.contact.getCountry() != null) {
/* 296:288 */       temp = temp + this.contact.getCountry();
/* 297:    */     }
/* 298:289 */     this.areaAdress.setText(temp);
/* 299:291 */     if (this.contact.getPhoto() != null)
/* 300:    */     {
/* 301:292 */       ImageIcon image = new ImageIcon(this.contact.getPhoto());
/* 302:293 */       Image img = image.getImage();
/* 303:294 */       Image newimg = img.getScaledInstance(145, 145, 4);
/* 304:295 */       this.lblPicture.setIcon(new ImageIcon(newimg));
/* 305:296 */       this.lblPicture.setBounds(this.lblPicture.getX(), this.lblPicture.getY(), 145, 145);
/* 306:    */     }
/* 307:    */   }
/* 308:    */ }


/* Location:           C:\Users\SmileyFacedHacker\Downloads\StillBorn RAT (Android)\StillBorn RAT (Android)\StillBornRAT.jar
 * Qualified Name:     gui.AdvContactGUI
 * JD-Core Version:    0.7.0.1
 */