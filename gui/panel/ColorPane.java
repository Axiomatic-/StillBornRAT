/*  1:   */ package gui.panel;
/*  2:   */ 
/*  3:   */ import java.awt.Color;
/*  4:   */ import javax.swing.JTextPane;
/*  5:   */ import javax.swing.text.AttributeSet;
/*  6:   */ import javax.swing.text.Document;
/*  7:   */ import javax.swing.text.SimpleAttributeSet;
/*  8:   */ import javax.swing.text.StyleConstants;
/*  9:   */ import javax.swing.text.StyleContext;
/* 10:   */ 
/* 11:   */ public class ColorPane
/* 12:   */   extends JTextPane
/* 13:   */ {
/* 14:   */   public void append(Color c, String s)
/* 15:   */   {
/* 16:29 */     StyleContext sc = StyleContext.getDefaultStyleContext();
/* 17:30 */     AttributeSet aset = sc.addAttribute(SimpleAttributeSet.EMPTY, StyleConstants.Foreground, c);
/* 18:   */     
/* 19:32 */     int len = getDocument().getLength();
/* 20:33 */     setCaretPosition(len);
/* 21:34 */     setCharacterAttributes(aset, false);
/* 22:35 */     replaceSelection(s);
/* 23:   */   }
/* 24:   */ }


/* Location:           C:\Users\SmileyFacedHacker\Downloads\StillBorn RAT (Android)\StillBorn RAT (Android)\StillBornRAT.jar
 * Qualified Name:     gui.panel.ColorPane
 * JD-Core Version:    0.7.0.1
 */