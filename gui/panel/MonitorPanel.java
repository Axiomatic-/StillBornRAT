/*   1:    */ package gui.panel;
/*   2:    */ 
/*   3:    */ import gui.UserGUI;
/*   4:    */ import java.awt.Color;
/*   5:    */ import java.awt.event.ActionEvent;
/*   6:    */ import java.awt.event.ActionListener;
/*   7:    */ import java.io.PrintStream;
/*   8:    */ import java.util.Date;
/*   9:    */ import java.util.HashSet;
/*  10:    */ import javax.swing.GroupLayout;
/*  11:    */ import javax.swing.GroupLayout.Alignment;
/*  12:    */ import javax.swing.GroupLayout.ParallelGroup;
/*  13:    */ import javax.swing.GroupLayout.SequentialGroup;
/*  14:    */ import javax.swing.JButton;
/*  15:    */ import javax.swing.JLabel;
/*  16:    */ import javax.swing.JPanel;
/*  17:    */ import javax.swing.JScrollPane;
/*  18:    */ import javax.swing.JSplitPane;
/*  19:    */ import javax.swing.JTextArea;
/*  20:    */ import javax.swing.LayoutStyle.ComponentPlacement;
/*  21:    */ import javax.swing.UIManager;
/*  22:    */ import javax.swing.border.TitledBorder;
/*  23:    */ 
/*  24:    */ public class MonitorPanel
/*  25:    */   extends JPanel
/*  26:    */ {
/*  27:    */   private UserGUI gui;
/*  28: 47 */   private boolean monitoring = false;
/*  29:    */   private boolean callMonitor;
/*  30:    */   private JLabel lblReceived;
/*  31:    */   private JLabel lblNewLabel;
/*  32:    */   private JLabel lblNewLabel_1;
/*  33:    */   private JLabel lblNewLabel_2;
/*  34:    */   private JLabel lblNewLabel_3;
/*  35:    */   private JTextArea phoneNumbersTextArea;
/*  36:    */   private JButton btnStartMonitoring;
/*  37:    */   private ColorPane colorPane;
/*  38:    */   private JSplitPane splitPane;
/*  39:    */   
/*  40:    */   public MonitorPanel(UserGUI gui, boolean callMonitor)
/*  41:    */   {
/*  42: 65 */     this.gui = gui;
/*  43: 66 */     this.callMonitor = callMonitor;
/*  44:    */     
/*  45: 68 */     this.splitPane = new JSplitPane();
/*  46: 69 */     this.splitPane.setResizeWeight(1.0D);
/*  47: 70 */     GroupLayout groupLayout = new GroupLayout(this);
/*  48: 71 */     groupLayout.setHorizontalGroup(
/*  49: 72 */       groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
/*  50: 73 */       .addGroup(groupLayout.createSequentialGroup()
/*  51: 74 */       .addContainerGap()
/*  52: 75 */       .addComponent(this.splitPane, -1, 493, 32767)
/*  53: 76 */       .addContainerGap()));
/*  54:    */     
/*  55: 78 */     groupLayout.setVerticalGroup(
/*  56: 79 */       groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
/*  57: 80 */       .addGroup(groupLayout.createSequentialGroup()
/*  58: 81 */       .addContainerGap()
/*  59: 82 */       .addComponent(this.splitPane, -1, 392, 32767)
/*  60: 83 */       .addGap(9)));
/*  61:    */     
/*  62:    */ 
/*  63: 86 */     JPanel panel = new JPanel();
/*  64: 87 */     this.splitPane.setRightComponent(panel);
/*  65: 88 */     panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Opsiyonel filtreler", 4, 2, null, null));
/*  66:    */     
/*  67: 90 */     this.btnStartMonitoring = new JButton("Görüntülemeyi başlat");
/*  68: 91 */     this.btnStartMonitoring.addActionListener(new ActionListener()
/*  69:    */     {
/*  70:    */       public void actionPerformed(ActionEvent e)
/*  71:    */       {
/*  72: 93 */         MonitorPanel.this.fireButtonMonitoring();
/*  73:    */       }
/*  74: 96 */     });
/*  75: 97 */     JLabel lblTypeOfCall = new JLabel("Tel No.ları :");
/*  76:    */     
/*  77: 99 */     this.phoneNumbersTextArea = new JTextArea();
/*  78:    */     
/*  79:101 */     this.lblReceived = new JLabel("Gelen çağrı");
/*  80:102 */     this.lblReceived.setForeground(Color.DARK_GRAY);
/*  81:    */     
/*  82:104 */     this.lblNewLabel = new JLabel("Cevapsız çağrı");
/*  83:105 */     this.lblNewLabel.setForeground(Color.ORANGE);
/*  84:    */     
/*  85:107 */     this.lblNewLabel_1 = new JLabel("Cevaplanan çağrı");
/*  86:108 */     this.lblNewLabel_1.setForeground(Color.GREEN);
/*  87:    */     
/*  88:110 */     this.lblNewLabel_2 = new JLabel("Gönderilen çağrı");
/*  89:111 */     this.lblNewLabel_2.setForeground(Color.BLUE);
/*  90:    */     
/*  91:113 */     this.lblNewLabel_3 = new JLabel("Açılan çağrı");
/*  92:114 */     this.lblNewLabel_3.setForeground(Color.RED);
/*  93:115 */     GroupLayout gl_panel = new GroupLayout(panel);
/*  94:116 */     gl_panel.setHorizontalGroup(
/*  95:117 */       gl_panel.createParallelGroup(GroupLayout.Alignment.TRAILING)
/*  96:118 */       .addGroup(gl_panel.createSequentialGroup()
/*  97:119 */       .addContainerGap()
/*  98:120 */       .addGroup(gl_panel.createParallelGroup(GroupLayout.Alignment.LEADING)
/*  99:121 */       .addComponent(this.phoneNumbersTextArea, -1, 133, 32767)
/* 100:122 */       .addComponent(this.btnStartMonitoring, -1, 133, 32767)
/* 101:123 */       .addComponent(lblTypeOfCall)
/* 102:124 */       .addComponent(this.lblReceived)
/* 103:125 */       .addComponent(this.lblNewLabel)
/* 104:126 */       .addComponent(this.lblNewLabel_1)
/* 105:127 */       .addComponent(this.lblNewLabel_2)
/* 106:128 */       .addComponent(this.lblNewLabel_3))
/* 107:129 */       .addContainerGap()));
/* 108:    */     
/* 109:131 */     gl_panel.setVerticalGroup(
/* 110:132 */       gl_panel.createParallelGroup(GroupLayout.Alignment.TRAILING)
/* 111:133 */       .addGroup(gl_panel.createSequentialGroup()
/* 112:134 */       .addGap(23)
/* 113:135 */       .addComponent(lblTypeOfCall)
/* 114:136 */       .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 115:137 */       .addComponent(this.phoneNumbersTextArea, -1, 171, 32767)
/* 116:138 */       .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
/* 117:139 */       .addComponent(this.lblReceived)
/* 118:140 */       .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 119:141 */       .addComponent(this.lblNewLabel)
/* 120:142 */       .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 121:143 */       .addComponent(this.lblNewLabel_1)
/* 122:144 */       .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 123:145 */       .addComponent(this.lblNewLabel_2)
/* 124:146 */       .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 125:147 */       .addComponent(this.lblNewLabel_3)
/* 126:148 */       .addGap(12)
/* 127:149 */       .addComponent(this.btnStartMonitoring)
/* 128:150 */       .addContainerGap()));
/* 129:    */     
/* 130:152 */     panel.setLayout(gl_panel);
/* 131:    */     
/* 132:154 */     JScrollPane scrollPane = new JScrollPane();
/* 133:155 */     this.splitPane.setLeftComponent(scrollPane);
/* 134:    */     
/* 135:157 */     this.colorPane = new ColorPane();
/* 136:158 */     scrollPane.setViewportView(this.colorPane);
/* 137:159 */     setLayout(groupLayout);
/* 138:161 */     if (!callMonitor) {
/* 139:161 */       hideCallLabels();
/* 140:    */     }
/* 141:    */   }
/* 142:    */   
/* 143:    */   private void hideCallLabels()
/* 144:    */   {
/* 145:165 */     this.lblNewLabel.setVisible(false);
/* 146:166 */     this.lblNewLabel_1.setVisible(false);
/* 147:167 */     this.lblNewLabel_2.setVisible(false);
/* 148:168 */     this.lblNewLabel_3.setVisible(false);
/* 149:169 */     this.lblReceived.setVisible(false);
/* 150:    */   }
/* 151:    */   
/* 152:    */   private void fireButtonMonitoring()
/* 153:    */   {
/* 154:173 */     if (this.monitoring)
/* 155:    */     {
/* 156:174 */       this.btnStartMonitoring.setText("Görüntülemeyi başlat");
/* 157:175 */       this.monitoring = false;
/* 158:176 */       if (this.callMonitor) {
/* 159:176 */         this.gui.fireStopCallMonitoring();
/* 160:    */       } else {
/* 161:177 */         this.gui.fireStopSMSMonitoring();
/* 162:    */       }
/* 163:    */     }
/* 164:    */     else
/* 165:    */     {
/* 166:179 */       this.colorPane.setText("");
/* 167:180 */       this.btnStartMonitoring.setText("Görüntülemeyi durdur");
/* 168:181 */       this.monitoring = true;
/* 169:    */       HashSet<String> phoneNumbers;
/* 170:    */       HashSet<String> phoneNumbers;
/* 171:184 */       if (this.phoneNumbersTextArea.getText().equals(""))
/* 172:    */       {
/* 173:185 */         phoneNumbers = null;
/* 174:    */       }
/* 175:    */       else
/* 176:    */       {
/* 177:188 */         phoneNumbers = new HashSet();
/* 178:189 */         String[] phoneNbr = this.phoneNumbersTextArea.getText().split("\n");
/* 179:190 */         for (String phone : phoneNbr)
/* 180:    */         {
/* 181:191 */           System.out.println("Tel no. =" + phone + "!");
/* 182:192 */           phoneNumbers.add(phone);
/* 183:    */         }
/* 184:    */       }
/* 185:196 */       if (this.callMonitor) {
/* 186:197 */         this.gui.fireStartCallMonitoring(phoneNumbers);
/* 187:    */       } else {
/* 188:199 */         this.gui.fireStartSMSMonitoring(phoneNumbers);
/* 189:    */       }
/* 190:    */     }
/* 191:    */   }
/* 192:    */   
/* 193:    */   public void addMonitoredCall(int type, String phoneNumber)
/* 194:    */   {
/* 195:204 */     Color color = Color.darkGray;
/* 196:205 */     String message = "";
/* 197:206 */     if (phoneNumber == null) {
/* 198:207 */       phoneNumber = "";
/* 199:    */     }
/* 200:208 */     if (type == 1)
/* 201:    */     {
/* 202:209 */       message = "Gelen çağrı: ";
/* 203:    */     }
/* 204:211 */     else if (type == 2)
/* 205:    */     {
/* 206:212 */       color = Color.orange;
/* 207:213 */       message = "Cevapsız çağrı: ";
/* 208:    */     }
/* 209:215 */     else if (type == 3)
/* 210:    */     {
/* 211:216 */       color = Color.green;
/* 212:217 */       message = "Alınan çağrı: ";
/* 213:    */     }
/* 214:219 */     else if (type == 4)
/* 215:    */     {
/* 216:220 */       color = Color.blue;
/* 217:221 */       message = "Giden çağrı: ";
/* 218:    */     }
/* 219:223 */     else if (type == 5)
/* 220:    */     {
/* 221:224 */       color = Color.red;
/* 222:225 */       message = "Açılan çağrı";
/* 223:    */     }
/* 224:228 */     this.colorPane.append(color, message + phoneNumber + " at " + new Date(System.currentTimeMillis()).toString() + "\n");
/* 225:    */   }
/* 226:    */   
/* 227:    */   public void addMonitoredSMS(String addr, long date, String body)
/* 228:    */   {
/* 229:232 */     this.colorPane.append(Color.black, "Numara: " + addr + "\nBody:\n" + body + "\nSMS " + new Date(date).toString() + "\n\n");
/* 230:    */   }
/* 231:    */   
/* 232:    */   public boolean getMonitoring()
/* 233:    */   {
/* 234:236 */     return this.monitoring;
/* 235:    */   }
/* 236:    */   
/* 237:    */   public boolean getCallMonitor()
/* 238:    */   {
/* 239:240 */     return this.callMonitor;
/* 240:    */   }
/* 241:    */ }


/* Location:           C:\Users\SmileyFacedHacker\Downloads\StillBorn RAT (Android)\StillBorn RAT (Android)\StillBornRAT.jar
 * Qualified Name:     gui.panel.MonitorPanel
 * JD-Core Version:    0.7.0.1
 */