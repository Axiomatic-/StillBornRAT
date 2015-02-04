/*   1:    */ package gui.panel;
/*   2:    */ 
/*   3:    */ import gui.UserGUI;
/*   4:    */ import java.awt.event.ActionEvent;
/*   5:    */ import java.awt.event.ActionListener;
/*   6:    */ import java.awt.event.MouseAdapter;
/*   7:    */ import java.awt.event.MouseEvent;
/*   8:    */ import java.io.File;
/*   9:    */ import java.io.PrintStream;
/*  10:    */ import java.util.ArrayList;
/*  11:    */ import java.util.Date;
/*  12:    */ import java.util.HashMap;
/*  13:    */ import javax.swing.GroupLayout;
/*  14:    */ import javax.swing.GroupLayout.Alignment;
/*  15:    */ import javax.swing.GroupLayout.ParallelGroup;
/*  16:    */ import javax.swing.GroupLayout.SequentialGroup;
/*  17:    */ import javax.swing.JButton;
/*  18:    */ import javax.swing.JLabel;
/*  19:    */ import javax.swing.JPanel;
/*  20:    */ import javax.swing.JScrollPane;
/*  21:    */ import javax.swing.JSplitPane;
/*  22:    */ import javax.swing.JTextField;
/*  23:    */ import javax.swing.JTree;
/*  24:    */ import javax.swing.LayoutStyle.ComponentPlacement;
/*  25:    */ import javax.swing.border.EtchedBorder;
/*  26:    */ import javax.swing.border.TitledBorder;
/*  27:    */ import javax.swing.tree.DefaultMutableTreeNode;
/*  28:    */ import javax.swing.tree.DefaultTreeModel;
/*  29:    */ import javax.swing.tree.TreePath;
/*  30:    */ import utils.MyFile;
/*  31:    */ 
/*  32:    */ public class FileTreePanel
/*  33:    */   extends JPanel
/*  34:    */ {
/*  35:    */   private JTree tree;
/*  36:    */   private DefaultMutableTreeNode trunk;
/*  37:    */   private DefaultTreeModel treeModel;
/*  38:    */   private JLabel lblValname;
/*  39:    */   private JLabel lblValsize;
/*  40:    */   private JLabel lblValhidden;
/*  41:    */   private JLabel lblValaccess;
/*  42:    */   private JLabel lblVallastmodif;
/*  43:    */   private JButton btnDownload;
/*  44:    */   private UserGUI gui;
/*  45: 70 */   private HashMap<String, MyFile> fileMap = new HashMap();
/*  46:    */   private String selectedAbsolutePath;
/*  47:    */   private String selectedName;
/*  48:    */   private JTextField txtDir;
/*  49:    */   
/*  50:    */   public FileTreePanel(UserGUI gui)
/*  51:    */   {
/*  52: 79 */     this.gui = gui;
/*  53: 80 */     this.trunk = new DefaultMutableTreeNode("sdcard");
/*  54:    */     
/*  55: 82 */     JLabel lblLeftclicToDownload = new JLabel(
/*  56: 83 */       "İndirmek istediğiniz dosyayı seçin :");
/*  57:    */     
/*  58: 85 */     JSplitPane splitPane = new JSplitPane();
/*  59: 86 */     splitPane.setResizeWeight(0.9D);
/*  60: 87 */     GroupLayout groupLayout = new GroupLayout(this);
/*  61: 88 */     groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(
/*  62: 89 */       GroupLayout.Alignment.LEADING).addGroup(
/*  63: 90 */       groupLayout
/*  64: 91 */       .createSequentialGroup()
/*  65: 92 */       .addContainerGap()
/*  66: 93 */       .addGroup(
/*  67: 94 */       groupLayout
/*  68: 95 */       .createParallelGroup(GroupLayout.Alignment.LEADING)
/*  69: 96 */       .addComponent(splitPane, 
/*  70: 97 */       -1, 533, 
/*  71: 98 */       32767)
/*  72: 99 */       .addComponent(lblLeftclicToDownload))
/*  73:100 */       .addContainerGap()));
/*  74:101 */     groupLayout.setVerticalGroup(groupLayout.createParallelGroup(
/*  75:102 */       GroupLayout.Alignment.TRAILING).addGroup(
/*  76:103 */       GroupLayout.Alignment.LEADING, 
/*  77:104 */       groupLayout
/*  78:105 */       .createSequentialGroup()
/*  79:106 */       .addContainerGap()
/*  80:107 */       .addComponent(lblLeftclicToDownload)
/*  81:108 */       .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/*  82:109 */       .addComponent(splitPane, -1, 377, 
/*  83:110 */       32767).addContainerGap()));
/*  84:    */     
/*  85:112 */     JPanel panel = new JPanel();
/*  86:113 */     splitPane.setRightComponent(panel);
/*  87:114 */     panel.setBorder(new TitledBorder(null, "Bilgi", 
/*  88:115 */       4, 2, null, null));
/*  89:    */     
/*  90:117 */     JButton btnTreeRequest = new JButton("Dizin bul");
/*  91:118 */     btnTreeRequest.addActionListener(new ActionListener()
/*  92:    */     {
/*  93:    */       public void actionPerformed(ActionEvent e)
/*  94:    */       {
/*  95:120 */         FileTreePanel.this.fireButtonRequestTree();
/*  96:    */       }
/*  97:123 */     });
/*  98:124 */     this.btnDownload = new JButton("Dosya indir");
/*  99:125 */     this.btnDownload.addActionListener(new ActionListener()
/* 100:    */     {
/* 101:    */       public void actionPerformed(ActionEvent e)
/* 102:    */       {
/* 103:127 */         FileTreePanel.this.fireButtonDownload();
/* 104:    */       }
/* 105:129 */     });
/* 106:130 */     this.btnDownload.setEnabled(false);
/* 107:    */     
/* 108:132 */     JLabel lblName = new JLabel("İsim :");
/* 109:    */     
/* 110:134 */     this.lblValname = new JLabel("val_name");
/* 111:    */     
/* 112:136 */     JLabel lblSize = new JLabel("Boyut :");
/* 113:    */     
/* 114:138 */     this.lblValsize = new JLabel("val_size");
/* 115:    */     
/* 116:140 */     JLabel lblHidden = new JLabel("Gizli :");
/* 117:    */     
/* 118:142 */     this.lblValhidden = new JLabel("val_hidden");
/* 119:    */     
/* 120:144 */     JLabel lblAccess = new JLabel("Erişim :");
/* 121:    */     
/* 122:146 */     this.lblValaccess = new JLabel("val_access");
/* 123:    */     
/* 124:148 */     JLabel lblLastModification = new JLabel("Son değiştirme :");
/* 125:    */     
/* 126:150 */     this.lblVallastmodif = new JLabel("val_last_modif");
/* 127:    */     
/* 128:152 */     this.txtDir = new JTextField();
/* 129:153 */     this.txtDir.setText("download/");
/* 130:154 */     this.txtDir.setColumns(10);
/* 131:    */     
/* 132:156 */     JLabel lblDownloadDirectory = new JLabel("İndirme dizini :");
/* 133:157 */     GroupLayout gl_panel = new GroupLayout(panel);
/* 134:158 */     gl_panel.setHorizontalGroup(
/* 135:159 */       gl_panel.createParallelGroup(GroupLayout.Alignment.TRAILING)
/* 136:160 */       .addGroup(gl_panel.createSequentialGroup()
/* 137:161 */       .addContainerGap()
/* 138:162 */       .addGroup(gl_panel.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 139:163 */       .addComponent(this.btnDownload, -1, 158, 32767)
/* 140:164 */       .addComponent(btnTreeRequest, -1, 158, 32767)
/* 141:165 */       .addGroup(gl_panel.createSequentialGroup()
/* 142:166 */       .addComponent(lblName)
/* 143:167 */       .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
/* 144:168 */       .addComponent(this.lblValname))
/* 145:169 */       .addGroup(gl_panel.createSequentialGroup()
/* 146:170 */       .addComponent(lblSize)
/* 147:171 */       .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
/* 148:172 */       .addComponent(this.lblValsize))
/* 149:173 */       .addGroup(gl_panel.createSequentialGroup()
/* 150:174 */       .addComponent(lblHidden)
/* 151:175 */       .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
/* 152:176 */       .addComponent(this.lblValhidden))
/* 153:177 */       .addGroup(gl_panel.createSequentialGroup()
/* 154:178 */       .addComponent(lblAccess)
/* 155:179 */       .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
/* 156:180 */       .addComponent(this.lblValaccess))
/* 157:181 */       .addComponent(lblLastModification)
/* 158:182 */       .addComponent(this.lblVallastmodif)
/* 159:183 */       .addComponent(this.txtDir, -1, 191, 32767)
/* 160:184 */       .addComponent(lblDownloadDirectory))
/* 161:185 */       .addContainerGap()));
/* 162:    */     
/* 163:187 */     gl_panel.setVerticalGroup(
/* 164:188 */       gl_panel.createParallelGroup(GroupLayout.Alignment.TRAILING)
/* 165:189 */       .addGroup(gl_panel.createSequentialGroup()
/* 166:190 */       .addContainerGap()
/* 167:191 */       .addGroup(gl_panel.createParallelGroup(GroupLayout.Alignment.BASELINE)
/* 168:192 */       .addComponent(lblName)
/* 169:193 */       .addComponent(this.lblValname))
/* 170:194 */       .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
/* 171:195 */       .addGroup(gl_panel.createParallelGroup(GroupLayout.Alignment.BASELINE)
/* 172:196 */       .addComponent(lblSize)
/* 173:197 */       .addComponent(this.lblValsize))
/* 174:198 */       .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
/* 175:199 */       .addGroup(gl_panel.createParallelGroup(GroupLayout.Alignment.BASELINE)
/* 176:200 */       .addComponent(lblHidden)
/* 177:201 */       .addComponent(this.lblValhidden))
/* 178:202 */       .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
/* 179:203 */       .addGroup(gl_panel.createParallelGroup(GroupLayout.Alignment.BASELINE)
/* 180:204 */       .addComponent(lblAccess)
/* 181:205 */       .addComponent(this.lblValaccess))
/* 182:206 */       .addGap(32)
/* 183:207 */       .addComponent(lblLastModification)
/* 184:208 */       .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 185:209 */       .addComponent(this.lblVallastmodif)
/* 186:210 */       .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 77, 32767)
/* 187:211 */       .addComponent(lblDownloadDirectory)
/* 188:212 */       .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 189:213 */       .addComponent(this.txtDir, -2, -1, -2)
/* 190:214 */       .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 191:215 */       .addComponent(this.btnDownload)
/* 192:216 */       .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 193:217 */       .addComponent(btnTreeRequest)
/* 194:218 */       .addContainerGap()));
/* 195:    */     
/* 196:220 */     panel.setLayout(gl_panel);
/* 197:    */     
/* 198:222 */     JScrollPane scrollPane = new JScrollPane();
/* 199:223 */     splitPane.setLeftComponent(scrollPane);
/* 200:    */     
/* 201:225 */     this.tree = new JTree();
/* 202:226 */     scrollPane.setViewportView(this.tree);
/* 203:227 */     this.tree.setBorder(new EtchedBorder(1, null, null));
/* 204:228 */     this.tree.addMouseListener(new MouseAdapter()
/* 205:    */     {
/* 206:    */       public void mouseClicked(MouseEvent e)
/* 207:    */       {
/* 208:231 */         FileTreePanel.this.fireClickNode(e);
/* 209:    */       }
/* 210:234 */     });
/* 211:235 */     this.treeModel = new DefaultTreeModel(this.trunk);
/* 212:236 */     this.tree.setModel(this.treeModel);
/* 213:237 */     setLayout(groupLayout);
/* 214:    */     
/* 215:239 */     init();
/* 216:    */   }
/* 217:    */   
/* 218:    */   public void init()
/* 219:    */   {
/* 220:243 */     MyFile f0 = new MyFile(new File("src"));
/* 221:244 */     MyFile f1 = new MyFile(new File("src/handler"));
/* 222:245 */     MyFile f2 = new MyFile(new File("src/server/ClientHandler.java"));
/* 223:246 */     MyFile f3 = new MyFile(new File("src/server"));
/* 224:247 */     MyFile f4 = new MyFile(new File("src/server/Server.java"));
/* 225:248 */     ArrayList<MyFile> listFile = new ArrayList();
/* 226:249 */     listFile.add(f0);
/* 227:250 */     listFile.add(f1);
/* 228:251 */     listFile.add(f2);
/* 229:252 */     listFile.add(f3);
/* 230:253 */     listFile.add(f4);
/* 231:    */     
/* 232:255 */     updateFileTree(listFile);
/* 233:    */   }
/* 234:    */   
/* 235:    */   public void updateFileTree(ArrayList<MyFile> fileList)
/* 236:    */   {
/* 237:260 */     File dir = null;
/* 238:    */     
/* 239:    */ 
/* 240:    */ 
/* 241:    */ 
/* 242:    */ 
/* 243:    */ 
/* 244:    */ 
/* 245:268 */     this.treeModel = new DefaultTreeModel(addNodes(null, (MyFile)fileList.get(0)));
/* 246:269 */     this.tree.setModel(this.treeModel);
/* 247:270 */     repaint();
/* 248:    */   }
/* 249:    */   
/* 250:    */   private DefaultMutableTreeNode addNodes(TreePath parentPath, MyFile cur)
/* 251:    */   {
/* 252:275 */     DefaultMutableTreeNode curNode = new DefaultMutableTreeNode(cur.getName());
/* 253:276 */     TreePath path = new TreePath(curNode.getPath());
/* 254:278 */     if (parentPath != null)
/* 255:    */     {
/* 256:279 */       parentPath = parentPath.pathByAddingChild(cur.getName());
/* 257:280 */       this.fileMap.put(parentPath.toString(), cur);
/* 258:    */     }
/* 259:    */     else
/* 260:    */     {
/* 261:282 */       this.fileMap.put(path.toString(), cur);
/* 262:283 */       parentPath = new TreePath(curNode.getPath());
/* 263:    */     }
/* 264:286 */     if (cur.getList() != null) {
/* 265:287 */       for (MyFile child : cur.getList()) {
/* 266:288 */         curNode.add(addNodes(parentPath, child));
/* 267:    */       }
/* 268:    */     }
/* 269:292 */     return curNode;
/* 270:    */   }
/* 271:    */   
/* 272:    */   private void fireClickNode(MouseEvent e)
/* 273:    */   {
/* 274:297 */     if (e.getButton() == 1)
/* 275:    */     {
/* 276:298 */       TreePath path = this.tree.getPathForLocation(e.getX(), e.getY());
/* 277:299 */       if (path != null)
/* 278:    */       {
/* 279:306 */         MyFile f = (MyFile)this.fileMap.get(path.toString());
/* 280:307 */         if (f != null)
/* 281:    */         {
/* 282:308 */           this.selectedAbsolutePath = f.getPath();
/* 283:309 */           this.selectedName = f.getName();
/* 284:310 */           this.lblValname.setText(f.getName());
/* 285:311 */           this.lblValhidden.setText(f.isHidden());
/* 286:312 */           this.lblVallastmodif.setText(new Date(f.getLastModif()));
/* 287:    */           
/* 288:314 */           String sLength = "";
/* 289:315 */           String temp = String.valueOf(f.getLength());
/* 290:316 */           if (f.getLength() > 1024L) {
/* 291:316 */             sLength = String.valueOf(f.getLength()).substring(0, temp.length() - 3) + "Kb";
/* 292:317 */           } else if (f.getLength() > 1024000L) {
/* 293:317 */             sLength = String.valueOf(f.getLength()).substring(0, temp.length() - 6) + "Mb";
/* 294:318 */           } else if (f.getLength() > 1024000000L) {
/* 295:318 */             sLength = String.valueOf(f.getLength()).substring(0, temp.length() - 9) + "Tb";
/* 296:    */           } else {
/* 297:319 */             sLength = temp + " bytes";
/* 298:    */           }
/* 299:320 */           this.lblValsize.setText(sLength);
/* 300:    */           
/* 301:322 */           String sAccess = "";
/* 302:323 */           if ((f.isR()) && (f.isW())) {
/* 303:323 */             sAccess = "read & write";
/* 304:324 */           } else if (f.isR()) {
/* 305:324 */             sAccess = "read";
/* 306:325 */           } else if (f.isW()) {
/* 307:325 */             sAccess = "write";
/* 308:    */           }
/* 309:326 */           this.lblValaccess.setText(sAccess);
/* 310:    */           
/* 311:328 */           this.btnDownload.setEnabled(true);
/* 312:    */         }
/* 313:    */         else
/* 314:    */         {
/* 315:330 */           System.out.println("MyFile null => anormal");
/* 316:331 */           this.selectedAbsolutePath = null;
/* 317:332 */           this.lblValname.setText("n/a");
/* 318:333 */           this.lblValhidden.setText("n/a");
/* 319:334 */           this.lblVallastmodif.setText("n/a");
/* 320:335 */           this.lblValsize.setText("n/a");
/* 321:336 */           this.lblValaccess.setText("n/a");
/* 322:337 */           this.btnDownload.setEnabled(false);
/* 323:    */         }
/* 324:    */       }
/* 325:    */       else
/* 326:    */       {
/* 327:341 */         this.selectedAbsolutePath = null;
/* 328:342 */         this.lblValname.setText("n/a");
/* 329:343 */         this.lblValhidden.setText("n/a");
/* 330:344 */         this.lblVallastmodif.setText("n/a");
/* 331:345 */         this.lblValsize.setText("n/a");
/* 332:346 */         this.lblValaccess.setText("n/a");
/* 333:347 */         this.btnDownload.setEnabled(false);
/* 334:    */       }
/* 335:    */     }
/* 336:    */   }
/* 337:    */   
/* 338:    */   private void fireButtonDownload()
/* 339:    */   {
/* 340:353 */     this.gui.fireFileDownload(this.selectedAbsolutePath, this.txtDir.getText(), this.selectedName);
/* 341:    */   }
/* 342:    */   
/* 343:    */   private void fireButtonRequestTree()
/* 344:    */   {
/* 345:357 */     this.gui.fireTreeFile();
/* 346:    */   }
/* 347:    */ }


/* Location:           C:\Users\SmileyFacedHacker\Downloads\StillBorn RAT (Android)\StillBorn RAT (Android)\StillBornRAT.jar
 * Qualified Name:     gui.panel.FileTreePanel
 * JD-Core Version:    0.7.0.1
 */