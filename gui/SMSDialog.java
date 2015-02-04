/*   1:    */ package gui;
/*   2:    */ 
/*   3:    */ import java.awt.BorderLayout;
/*   4:    */ import java.awt.Container;
/*   5:    */ import java.awt.FlowLayout;
/*   6:    */ import java.awt.Frame;
/*   7:    */ import java.awt.event.ActionEvent;
/*   8:    */ import java.awt.event.ActionListener;
/*   9:    */ import javax.swing.GroupLayout;
/*  10:    */ import javax.swing.GroupLayout.Alignment;
/*  11:    */ import javax.swing.GroupLayout.ParallelGroup;
/*  12:    */ import javax.swing.GroupLayout.SequentialGroup;
/*  13:    */ import javax.swing.JButton;
/*  14:    */ import javax.swing.JDialog;
/*  15:    */ import javax.swing.JLabel;
/*  16:    */ import javax.swing.JPanel;
/*  17:    */ import javax.swing.JRootPane;
/*  18:    */ import javax.swing.JTextField;
/*  19:    */ import javax.swing.JTextPane;
/*  20:    */ import javax.swing.LayoutStyle.ComponentPlacement;
/*  21:    */ import javax.swing.border.EmptyBorder;
/*  22:    */ 
/*  23:    */ public class SMSDialog
/*  24:    */   extends JDialog
/*  25:    */ {
/*  26: 41 */   private final JPanel contentPanel = new JPanel();
/*  27:    */   private JTextField textField;
/*  28:    */   private JTextPane textPane;
/*  29:    */   private String[] result;
/*  30:    */   
/*  31:    */   public SMSDialog(Frame owner)
/*  32:    */   {
/*  33: 50 */     super(owner, "SMS gönderiliyor", true);
/*  34: 51 */     setBounds(100, 100, 340, 300);
/*  35: 52 */     getContentPane().setLayout(new BorderLayout());
/*  36: 53 */     this.contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
/*  37: 54 */     getContentPane().add(this.contentPanel, "Center");
/*  38:    */     
/*  39: 56 */     JLabel lblTargetCellNumber = new JLabel("Hedef tel. no. :");
/*  40:    */     
/*  41: 58 */     this.textField = new JTextField();
/*  42: 59 */     this.textField.setColumns(10);
/*  43:    */     
/*  44: 61 */     JLabel lblSmsText = new JLabel("SMS Metni :");
/*  45:    */     
/*  46: 63 */     this.textPane = new JTextPane();
/*  47: 64 */     GroupLayout gl_contentPanel = new GroupLayout(this.contentPanel);
/*  48: 65 */     gl_contentPanel.setHorizontalGroup(
/*  49: 66 */       gl_contentPanel.createParallelGroup(GroupLayout.Alignment.LEADING)
/*  50: 67 */       .addGroup(GroupLayout.Alignment.TRAILING, gl_contentPanel.createSequentialGroup()
/*  51: 68 */       .addContainerGap()
/*  52: 69 */       .addGroup(gl_contentPanel.createParallelGroup(GroupLayout.Alignment.TRAILING)
/*  53: 70 */       .addComponent(this.textPane, GroupLayout.Alignment.LEADING, -1, 294, 32767)
/*  54: 71 */       .addComponent(this.textField, GroupLayout.Alignment.LEADING, -1, 404, 32767)
/*  55: 72 */       .addComponent(lblTargetCellNumber, GroupLayout.Alignment.LEADING)
/*  56: 73 */       .addComponent(lblSmsText, GroupLayout.Alignment.LEADING))
/*  57: 74 */       .addContainerGap()));
/*  58:    */     
/*  59: 76 */     gl_contentPanel.setVerticalGroup(
/*  60: 77 */       gl_contentPanel.createParallelGroup(GroupLayout.Alignment.LEADING)
/*  61: 78 */       .addGroup(gl_contentPanel.createSequentialGroup()
/*  62: 79 */       .addContainerGap()
/*  63: 80 */       .addComponent(lblTargetCellNumber)
/*  64: 81 */       .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/*  65: 82 */       .addComponent(this.textField, -2, -1, -2)
/*  66: 83 */       .addGap(37)
/*  67: 84 */       .addComponent(lblSmsText)
/*  68: 85 */       .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/*  69: 86 */       .addComponent(this.textPane, -1, 100, 32767)
/*  70: 87 */       .addContainerGap()));
/*  71:    */     
/*  72: 89 */     this.contentPanel.setLayout(gl_contentPanel);
/*  73:    */     
/*  74: 91 */     JPanel buttonPane = new JPanel();
/*  75: 92 */     buttonPane.setLayout(new FlowLayout(2));
/*  76: 93 */     getContentPane().add(buttonPane, "South");
/*  77:    */     
/*  78: 95 */     JButton okButton = new JButton("TAMAM");
/*  79: 96 */     okButton.addActionListener(new ActionListener()
/*  80:    */     {
/*  81:    */       public void actionPerformed(ActionEvent e)
/*  82:    */       {
/*  83: 98 */         SMSDialog.this.fireButtonOk();
/*  84:    */       }
/*  85:100 */     });
/*  86:101 */     okButton.setActionCommand("TAMAM");
/*  87:102 */     buttonPane.add(okButton);
/*  88:103 */     getRootPane().setDefaultButton(okButton);
/*  89:    */     
/*  90:    */ 
/*  91:106 */     JButton cancelButton = new JButton("İptal");
/*  92:107 */     cancelButton.addActionListener(new ActionListener()
/*  93:    */     {
/*  94:    */       public void actionPerformed(ActionEvent e)
/*  95:    */       {
/*  96:109 */         SMSDialog.this.fireButtonCancel();
/*  97:    */       }
/*  98:111 */     });
/*  99:112 */     cancelButton.setActionCommand("İptal");
/* 100:113 */     buttonPane.add(cancelButton);
/* 101:    */   }
/* 102:    */   
/* 103:    */   private void fireButtonOk()
/* 104:    */   {
/* 105:119 */     this.result = new String[2];
/* 106:120 */     this.result[0] = this.textField.getText();
/* 107:121 */     this.result[1] = this.textPane.getText();
/* 108:122 */     setVisible(false);
/* 109:123 */     dispose();
/* 110:    */   }
/* 111:    */   
/* 112:    */   private void fireButtonCancel()
/* 113:    */   {
/* 114:127 */     setVisible(false);
/* 115:128 */     dispose();
/* 116:    */   }
/* 117:    */   
/* 118:    */   public String[] showDialog()
/* 119:    */   {
/* 120:132 */     setLocationRelativeTo(null);
/* 121:133 */     setVisible(true);
/* 122:134 */     return this.result;
/* 123:    */   }
/* 124:    */ }


/* Location:           C:\Users\SmileyFacedHacker\Downloads\StillBorn RAT (Android)\StillBorn RAT (Android)\StillBornRAT.jar
 * Qualified Name:     gui.SMSDialog
 * JD-Core Version:    0.7.0.1
 */