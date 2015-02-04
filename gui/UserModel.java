/*   1:    */ package gui;
/*   2:    */ 
/*   3:    */ import java.util.ArrayList;
/*   4:    */ import java.util.List;
/*   5:    */ import javax.swing.table.AbstractTableModel;
/*   6:    */ 
/*   7:    */ public class UserModel
/*   8:    */   extends AbstractTableModel
/*   9:    */ {
/*  10: 87 */   private final List<User> users = new ArrayList();
/*  11: 88 */   private final String[] headers = { "Flag", "IMEI", "Location", "Phone Number", "Operator", "Country SIM", "Operator SIM", "Serial SIM" };
/*  12:    */   
/*  13:    */   public int getRowCount()
/*  14:    */   {
/*  15: 96 */     return this.users.size();
/*  16:    */   }
/*  17:    */   
/*  18:    */   public int getColumnCount()
/*  19:    */   {
/*  20:101 */     return this.headers.length;
/*  21:    */   }
/*  22:    */   
/*  23:    */   public String getColumnName(int columnIndex)
/*  24:    */   {
/*  25:106 */     return this.headers[columnIndex];
/*  26:    */   }
/*  27:    */   
/*  28:    */   public Object getValueAt(int rowIndex, int columnIndex)
/*  29:    */   {
/*  30:134 */     if (rowIndex < this.users.size())
/*  31:    */     {
/*  32:135 */       switch (columnIndex)
/*  33:    */       {
/*  34:    */       case 0: 
/*  35:137 */         return ((User)this.users.get(rowIndex)).getImage();
/*  36:    */       case 1: 
/*  37:139 */         return ((User)this.users.get(rowIndex)).getImei();
/*  38:    */       case 2: 
/*  39:141 */         return ((User)this.users.get(rowIndex)).getCountryCode();
/*  40:    */       case 3: 
/*  41:143 */         return ((User)this.users.get(rowIndex)).getTelNumber();
/*  42:    */       case 4: 
/*  43:145 */         return ((User)this.users.get(rowIndex)).getOperator();
/*  44:    */       case 5: 
/*  45:147 */         return ((User)this.users.get(rowIndex)).getSimCountryCode();
/*  46:    */       case 6: 
/*  47:149 */         return ((User)this.users.get(rowIndex)).getSimOperator();
/*  48:    */       case 7: 
/*  49:151 */         return ((User)this.users.get(rowIndex)).getSimSerial();
/*  50:    */       }
/*  51:153 */       return null;
/*  52:    */     }
/*  53:155 */     return null;
/*  54:    */   }
/*  55:    */   
/*  56:    */   public void addUser(User user)
/*  57:    */   {
/*  58:159 */     this.users.add(user);
/*  59:160 */     fireTableRowsInserted(this.users.size() - 1, this.users.size() - 1);
/*  60:    */   }
/*  61:    */   
/*  62:    */   public void removeUser(String imei)
/*  63:    */   {
/*  64:164 */     for (User user : this.users) {
/*  65:165 */       if (user.getImei().equals(imei))
/*  66:    */       {
/*  67:166 */         this.users.remove(user);
/*  68:167 */         return;
/*  69:    */       }
/*  70:    */     }
/*  71:    */   }
/*  72:    */ }


/* Location:           C:\Users\SmileyFacedHacker\Downloads\StillBorn RAT (Android)\StillBorn RAT (Android)\StillBornRAT.jar
 * Qualified Name:     gui.UserModel
 * JD-Core Version:    0.7.0.1
 */