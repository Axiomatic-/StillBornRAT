/*   1:    */ package gui;
/*   2:    */ 
/*   3:    */ public class User
/*   4:    */ {
/*   5:    */   private String imei;
/*   6:    */   private String countryCode;
/*   7:    */   private String telNumber;
/*   8:    */   private String operator;
/*   9:    */   private String simCountryCode;
/*  10:    */   private String simOperator;
/*  11:    */   private String simSerial;
/*  12:    */   private String image;
/*  13:    */   
/*  14:    */   public User(String img, String i, String cc, String tn, String op, String simcc, String simop, String simserial)
/*  15:    */   {
/*  16: 58 */     this.image = img;
/*  17: 59 */     this.imei = i;
/*  18: 60 */     this.countryCode = cc;
/*  19: 61 */     this.telNumber = tn;
/*  20: 62 */     this.operator = op;
/*  21: 63 */     this.simCountryCode = simcc;
/*  22: 64 */     this.simOperator = simop;
/*  23: 65 */     this.simSerial = simserial;
/*  24:    */   }
/*  25:    */   
/*  26:    */   public String getImage()
/*  27:    */   {
/*  28: 70 */     return this.image;
/*  29:    */   }
/*  30:    */   
/*  31:    */   public void setImage(String image)
/*  32:    */   {
/*  33: 75 */     this.image = image;
/*  34:    */   }
/*  35:    */   
/*  36:    */   public void setCountryCode(String countryCode)
/*  37:    */   {
/*  38: 79 */     this.countryCode = countryCode;
/*  39:    */   }
/*  40:    */   
/*  41:    */   public void setImei(String imei)
/*  42:    */   {
/*  43: 83 */     this.imei = imei;
/*  44:    */   }
/*  45:    */   
/*  46:    */   public void setOperator(String operator)
/*  47:    */   {
/*  48: 87 */     this.operator = operator;
/*  49:    */   }
/*  50:    */   
/*  51:    */   public void setSimCountryCode(String simCountryCode)
/*  52:    */   {
/*  53: 91 */     this.simCountryCode = simCountryCode;
/*  54:    */   }
/*  55:    */   
/*  56:    */   public void setSimOperator(String simOperator)
/*  57:    */   {
/*  58: 95 */     this.simOperator = simOperator;
/*  59:    */   }
/*  60:    */   
/*  61:    */   public void setSimSerial(String simSerial)
/*  62:    */   {
/*  63: 99 */     this.simSerial = simSerial;
/*  64:    */   }
/*  65:    */   
/*  66:    */   public void setTelNumber(String telNumber)
/*  67:    */   {
/*  68:103 */     this.telNumber = telNumber;
/*  69:    */   }
/*  70:    */   
/*  71:    */   public String getCountryCode()
/*  72:    */   {
/*  73:107 */     return this.countryCode;
/*  74:    */   }
/*  75:    */   
/*  76:    */   public String getImei()
/*  77:    */   {
/*  78:111 */     return this.imei;
/*  79:    */   }
/*  80:    */   
/*  81:    */   public String getOperator()
/*  82:    */   {
/*  83:115 */     return this.operator;
/*  84:    */   }
/*  85:    */   
/*  86:    */   public String getSimCountryCode()
/*  87:    */   {
/*  88:119 */     return this.simCountryCode;
/*  89:    */   }
/*  90:    */   
/*  91:    */   public String getSimOperator()
/*  92:    */   {
/*  93:123 */     return this.simOperator;
/*  94:    */   }
/*  95:    */   
/*  96:    */   public String getSimSerial()
/*  97:    */   {
/*  98:127 */     return this.simSerial;
/*  99:    */   }
/* 100:    */   
/* 101:    */   public String getTelNumber()
/* 102:    */   {
/* 103:131 */     return this.telNumber;
/* 104:    */   }
/* 105:    */ }


/* Location:           C:\Users\SmileyFacedHacker\Downloads\StillBorn RAT (Android)\StillBorn RAT (Android)\StillBornRAT.jar
 * Qualified Name:     gui.User
 * JD-Core Version:    0.7.0.1
 */