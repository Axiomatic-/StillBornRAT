/*   1:    */ package gui.panel;
/*   2:    */ 
/*   3:    */ import com.sun.jna.Native;
/*   4:    */ import com.sun.jna.NativeLibrary;
/*   5:    */ import gui.GUI;
/*   6:    */ import gui.UserGUI;
/*   7:    */ import java.awt.BorderLayout;
/*   8:    */ import java.awt.Canvas;
/*   9:    */ import java.awt.Color;
/*  10:    */ import java.awt.Component;
/*  11:    */ import java.awt.Image;
/*  12:    */ import java.awt.Toolkit;
/*  13:    */ import java.awt.event.ActionEvent;
/*  14:    */ import java.awt.event.ActionListener;
/*  15:    */ import java.awt.event.MouseAdapter;
/*  16:    */ import java.awt.event.MouseEvent;
/*  17:    */ import java.awt.event.WindowAdapter;
/*  18:    */ import java.awt.event.WindowEvent;
/*  19:    */ import java.io.File;
/*  20:    */ import java.io.FileNotFoundException;
/*  21:    */ import java.io.FileOutputStream;
/*  22:    */ import java.io.IOException;
/*  23:    */ import java.util.Date;
/*  24:    */ import javax.swing.Box;
/*  25:    */ import javax.swing.BoxLayout;
/*  26:    */ import javax.swing.DefaultListCellRenderer;
/*  27:    */ import javax.swing.ImageIcon;
/*  28:    */ import javax.swing.JButton;
/*  29:    */ import javax.swing.JComboBox;
/*  30:    */ import javax.swing.JFrame;
/*  31:    */ import javax.swing.JLabel;
/*  32:    */ import javax.swing.JList;
/*  33:    */ import javax.swing.JPanel;
/*  34:    */ import javax.swing.border.CompoundBorder;
/*  35:    */ import javax.swing.border.EmptyBorder;
/*  36:    */ import javax.swing.border.LineBorder;
/*  37:    */ import uk.co.caprica.vlcj.binding.LibVlc;
/*  38:    */ import uk.co.caprica.vlcj.player.MediaPlayerFactory;
/*  39:    */ import uk.co.caprica.vlcj.player.embedded.EmbeddedMediaPlayer;
/*  40:    */ import uk.co.caprica.vlcj.player.embedded.videosurface.CanvasVideoSurface;
/*  41:    */ import uk.co.caprica.vlcj.runtime.RuntimeUtil;
/*  42:    */ 
/*  43:    */ public class VideoPanel
/*  44:    */   extends JPanel
/*  45:    */ {
/*  46: 88 */   private static final String[][] ASPECTS = {
/*  47: 89 */     { "<choose...>" }, 
/*  48: 90 */     { "16:10", "16:10" }, 
/*  49: 91 */     { "16:9", "16:9" }, 
/*  50: 92 */     { "1.85:1", "185:100" }, 
/*  51: 93 */     { "2.21:1", "221:100" }, 
/*  52: 94 */     { "2.35:1", "235:100" }, 
/*  53: 95 */     { "2.39:1", "239:100" }, 
/*  54: 96 */     { "5:3", "5:3" }, 
/*  55: 97 */     { "4:3", "4:3" }, 
/*  56: 98 */     { "5:4", "5:4" }, 
/*  57: 99 */     { "1:1", "1:1" } };
/*  58:    */   private MediaPlayerFactory factory;
/*  59:    */   private EmbeddedMediaPlayer mediaPlayer;
/*  60:    */   private CanvasVideoSurface videoSurface;
/*  61:    */   private JFrame frame;
/*  62:    */   private JPanel contentPane;
/*  63:    */   private JPanel videoPane;
/*  64:    */   private Canvas videoCanvas;
/*  65:    */   private JPanel controlsPane;
/*  66:    */   private JPanel allPanel;
/*  67:    */   private JLabel standardAspectLabel;
/*  68:    */   private JComboBox standardAspectComboBox;
/*  69:    */   private JLabel lblStart;
/*  70:    */   private JLabel lblPause;
/*  71:    */   private JLabel lblStop;
/*  72:    */   private JButton btnStartStream;
/*  73:117 */   private boolean streaming = false;
/*  74:118 */   private boolean playing = false;
/*  75:    */   FileOutputStream fout;
/*  76:    */   private UserGUI gui;
/*  77:    */   String filename;
/*  78:    */   
/*  79:    */   public VideoPanel(UserGUI gui)
/*  80:    */   {
/*  81:128 */     NativeLibrary.addSearchPath(RuntimeUtil.getLibVlcLibraryName(), "D://Util/VLC");
/*  82:129 */     Native.loadLibrary(RuntimeUtil.getLibVlcLibraryName(), LibVlc.class);
/*  83:130 */     this.gui = gui;
/*  84:131 */     this.factory = new MediaPlayerFactory(new String[] { "--no-video-title-show" });
/*  85:132 */     this.mediaPlayer = this.factory.newEmbeddedMediaPlayer();
/*  86:    */     
/*  87:134 */     this.videoPane = new JPanel();
/*  88:135 */     this.videoPane.setBorder(new CompoundBorder(new LineBorder(Color.black, 1), new EmptyBorder(0, 0, 0, 0)));
/*  89:136 */     this.videoPane.setLayout(new BorderLayout());
/*  90:137 */     this.videoPane.setBackground(Color.white);
/*  91:    */     
/*  92:139 */     this.videoCanvas = new Canvas();
/*  93:140 */     this.videoCanvas.setBackground(Color.white);
/*  94:141 */     this.videoCanvas.setSize(720, 350);
/*  95:    */     
/*  96:    */ 
/*  97:144 */     this.videoPane.add(this.videoCanvas, "Center");
/*  98:    */     
/*  99:146 */     this.videoSurface = this.factory.newVideoSurface(this.videoCanvas);
/* 100:    */     
/* 101:148 */     this.mediaPlayer.setVideoSurface(this.videoSurface);
/* 102:    */     
/* 103:150 */     this.standardAspectLabel = new JLabel("Standard açı:");
/* 104:151 */     this.standardAspectLabel.setDisplayedMnemonic('s');
/* 105:    */     
/* 106:153 */     this.standardAspectComboBox = new JComboBox(ASPECTS);
/* 107:154 */     this.standardAspectComboBox.setEditable(false);
/* 108:155 */     this.standardAspectComboBox.setRenderer(new DefaultListCellRenderer()
/* 109:    */     {
/* 110:    */       public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus)
/* 111:    */       {
/* 112:158 */         JLabel l = (JLabel)super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
/* 113:159 */         String[] val = (String[])value;
/* 114:160 */         l.setText(val[0]);
/* 115:161 */         return l;
/* 116:    */       }
/* 117:164 */     });
/* 118:165 */     this.standardAspectLabel.setLabelFor(this.standardAspectComboBox);
/* 119:    */     
/* 120:167 */     this.lblPause = new JLabel(reziseImage("/gui/res/gtk-media-pause.png"));
/* 121:    */     
/* 122:169 */     this.lblStart = new JLabel(reziseImage("/gui/res/gtk-media-play-ltr.png"));
/* 123:    */     
/* 124:    */ 
/* 125:172 */     this.lblStop = new JLabel(reziseImage("/gui/res/gtk-media-stop.png"));
/* 126:173 */     this.lblStop.setEnabled(false);
/* 127:174 */     this.btnStartStream = new JButton("Yayın başlat");
/* 128:    */     
/* 129:176 */     this.controlsPane = new JPanel();
/* 130:177 */     this.controlsPane.setLayout(new BoxLayout(this.controlsPane, 0));
/* 131:178 */     this.controlsPane.add(this.standardAspectLabel);
/* 132:179 */     this.controlsPane.add(Box.createHorizontalStrut(4));
/* 133:180 */     this.controlsPane.add(this.standardAspectComboBox);
/* 134:181 */     this.controlsPane.add(Box.createHorizontalStrut(5));
/* 135:182 */     this.controlsPane.add(this.btnStartStream);
/* 136:183 */     this.controlsPane.add(this.lblStart);
/* 137:184 */     this.controlsPane.add(Box.createHorizontalStrut(4));
/* 138:185 */     this.controlsPane.add(this.lblStop);
/* 139:186 */     this.controlsPane.add(Box.createHorizontalStrut(4));
/* 140:187 */     this.controlsPane.add(this.lblPause);
/* 141:    */     
/* 142:    */ 
/* 143:190 */     this.contentPane = new JPanel();
/* 144:191 */     this.contentPane.setBorder(new EmptyBorder(16, 16, 16, 16));
/* 145:192 */     this.contentPane.setLayout(new BorderLayout(16, 16));
/* 146:193 */     this.contentPane.add(this.videoPane, "Center");
/* 147:194 */     this.contentPane.add(this.controlsPane, "South");
/* 148:    */     
/* 149:196 */     this.frame = new JFrame("Video yayını");
/* 150:197 */     this.frame.setContentPane(this.contentPane);
/* 151:198 */     this.frame.setDefaultCloseOperation(2);
/* 152:    */     
/* 153:200 */     this.frame.addWindowListener(new WindowAdapter()
/* 154:    */     {
/* 155:    */       public void windowClosed(WindowEvent evt)
/* 156:    */       {
/* 157:202 */         VideoPanel.this.formWindowClosed(evt);
/* 158:    */       }
/* 159:205 */     });
/* 160:206 */     this.frame.pack();
/* 161:207 */     this.frame.setVisible(true);
/* 162:    */     
/* 163:    */ 
/* 164:    */ 
/* 165:    */ 
/* 166:    */ 
/* 167:    */ 
/* 168:    */ 
/* 169:215 */     this.standardAspectComboBox.addActionListener(new ActionListener()
/* 170:    */     {
/* 171:    */       public void actionPerformed(ActionEvent e)
/* 172:    */       {
/* 173:218 */         Object selectedItem = VideoPanel.this.standardAspectComboBox.getSelectedItem();
/* 174:219 */         if (selectedItem != null)
/* 175:    */         {
/* 176:220 */           String[] value = (String[])selectedItem;
/* 177:221 */           VideoPanel.this.mediaPlayer.setAspectRatio(value[1]);
/* 178:    */         }
/* 179:    */       }
/* 180:225 */     });
/* 181:226 */     this.btnStartStream.addActionListener(new ActionListener()
/* 182:    */     {
/* 183:    */       public void actionPerformed(ActionEvent arg0)
/* 184:    */       {
/* 185:228 */         VideoPanel.this.fireButtonStartStreaming();
/* 186:    */       }
/* 187:231 */     });
/* 188:232 */     this.lblStart.addMouseListener(new MouseAdapter()
/* 189:    */     {
/* 190:    */       public void mouseClicked(MouseEvent arg0)
/* 191:    */       {
/* 192:234 */         VideoPanel.this.fireButtonPlay();
/* 193:    */       }
/* 194:237 */     });
/* 195:238 */     this.lblStop.addMouseListener(new MouseAdapter()
/* 196:    */     {
/* 197:    */       public void mouseClicked(MouseEvent arg0)
/* 198:    */       {
/* 199:240 */         VideoPanel.this.fireButtonPlay();
/* 200:    */       }
/* 201:243 */     });
/* 202:244 */     this.lblPause.addMouseListener(new MouseAdapter()
/* 203:    */     {
/* 204:    */       public void mouseClicked(MouseEvent arg0)
/* 205:    */       {
/* 206:246 */         VideoPanel.this.mediaPlayer.pause();
/* 207:    */       }
/* 208:    */     });
/* 209:    */   }
/* 210:    */   
/* 211:    */   private void formWindowClosed(WindowEvent evt)
/* 212:    */   {
/* 213:254 */     this.frame = null;
/* 214:255 */     this.gui.fireButtonCloseTab();
/* 215:    */   }
/* 216:    */   
/* 217:    */   private ImageIcon reziseImage(String path)
/* 218:    */   {
/* 219:263 */     ImageIcon getImg = new ImageIcon(Toolkit.getDefaultToolkit().getImage(UserGUI.class.getResource(path)));
/* 220:264 */     Image img = getImg.getImage();
/* 221:265 */     Image newimg = img.getScaledInstance(32, 32, 4);
/* 222:266 */     return new ImageIcon(newimg);
/* 223:    */   }
/* 224:    */   
/* 225:    */   public boolean getStreaming()
/* 226:    */   {
/* 227:270 */     return this.streaming;
/* 228:    */   }
/* 229:    */   
/* 230:    */   public void fireButtonStartStreaming()
/* 231:    */   {
/* 232:275 */     if (!this.streaming)
/* 233:    */     {
/* 234:277 */       this.filename = (new Date(System.currentTimeMillis()).toString().replaceAll(" ", "_") + ".mp4");
/* 235:    */       try
/* 236:    */       {
/* 237:280 */         this.fout = new FileOutputStream(new File(this.filename));
/* 238:281 */         this.gui.fireStartVideoStream();
/* 239:282 */         this.btnStartStream.setText("Yayını durdur");
/* 240:283 */         this.streaming = true;
/* 241:    */       }
/* 242:    */       catch (FileNotFoundException e)
/* 243:    */       {
/* 244:286 */         this.gui.getGUI().logErrTxt("Kayıt edilemiyor");
/* 245:    */       }
/* 246:    */     }
/* 247:    */     else
/* 248:    */     {
/* 249:290 */       this.gui.fireStopVideoStream();
/* 250:291 */       this.streaming = false;
/* 251:    */     }
/* 252:    */   }
/* 253:    */   
/* 254:    */   public void fireButtonPlay()
/* 255:    */   {
/* 256:297 */     if (!this.playing)
/* 257:    */     {
/* 258:298 */       this.mediaPlayer.playMedia(this.filename, new String[0]);
/* 259:299 */       this.lblStart.setEnabled(false);
/* 260:300 */       this.lblStop.setEnabled(true);
/* 261:301 */       this.playing = true;
/* 262:    */     }
/* 263:    */     else
/* 264:    */     {
/* 265:304 */       this.mediaPlayer.stop();
/* 266:305 */       this.lblStart.setEnabled(true);
/* 267:306 */       this.lblStop.setEnabled(false);
/* 268:307 */       this.playing = false;
/* 269:    */     }
/* 270:    */   }
/* 271:    */   
/* 272:    */   public void addVideoBytes(byte[] data)
/* 273:    */   {
/* 274:    */     try
/* 275:    */     {
/* 276:316 */       this.fout.write(data);
/* 277:    */     }
/* 278:    */     catch (IOException e)
/* 279:    */     {
/* 280:319 */       this.gui.getGUI().logErrTxt("Video dosyası yazılırken hata oluştu");
/* 281:    */     }
/* 282:    */   }
/* 283:    */ }


/* Location:           C:\Users\SmileyFacedHacker\Downloads\StillBorn RAT (Android)\StillBorn RAT (Android)\StillBornRAT.jar
 * Qualified Name:     gui.panel.VideoPanel
 * JD-Core Version:    0.7.0.1
 */