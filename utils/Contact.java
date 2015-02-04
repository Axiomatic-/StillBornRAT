/*   1:    */ package utils;
/*   2:    */ 
/*   3:    */ import java.io.Serializable;
/*   4:    */ import java.util.ArrayList;
/*   5:    */ 
/*   6:    */ public class Contact
/*   7:    */   implements Serializable
/*   8:    */ {
/*   9:    */   private static final long serialVersionUID = -744071613945933264L;
/*  10:    */   long id;
/*  11:    */   int times_contacted;
/*  12:    */   long last_time_contacted;
/*  13:    */   String display_name;
/*  14:    */   int starred;
/*  15:    */   ArrayList<String> phones;
/*  16:    */   ArrayList<String> emails;
/*  17:    */   ArrayList<String> notes;
/*  18:    */   String street;
/*  19:    */   String city;
/*  20:    */   String region;
/*  21:    */   String postalcode;
/*  22:    */   String country;
/*  23:    */   int type_addr;
/*  24:    */   ArrayList<String> messaging;
/*  25:    */   String OrganisationName;
/*  26:    */   String OrganisationStatus;
/*  27:    */   byte[] photo;
/*  28:    */   
/*  29:    */   public String getRegion()
/*  30:    */   {
/*  31: 52 */     return this.region;
/*  32:    */   }
/*  33:    */   
/*  34:    */   public void setRegion(String reg)
/*  35:    */   {
/*  36: 56 */     this.region = reg;
/*  37:    */   }
/*  38:    */   
/*  39:    */   public long getId()
/*  40:    */   {
/*  41: 59 */     return this.id;
/*  42:    */   }
/*  43:    */   
/*  44:    */   public void setId(long id)
/*  45:    */   {
/*  46: 63 */     this.id = id;
/*  47:    */   }
/*  48:    */   
/*  49:    */   public int getTimes_contacted()
/*  50:    */   {
/*  51: 67 */     return this.times_contacted;
/*  52:    */   }
/*  53:    */   
/*  54:    */   public void setTimes_contacted(int times_contacted)
/*  55:    */   {
/*  56: 71 */     this.times_contacted = times_contacted;
/*  57:    */   }
/*  58:    */   
/*  59:    */   public long getLast_time_contacted()
/*  60:    */   {
/*  61: 75 */     return this.last_time_contacted;
/*  62:    */   }
/*  63:    */   
/*  64:    */   public void setLast_time_contacted(long last_time_contacted)
/*  65:    */   {
/*  66: 79 */     this.last_time_contacted = last_time_contacted;
/*  67:    */   }
/*  68:    */   
/*  69:    */   public String getDisplay_name()
/*  70:    */   {
/*  71: 83 */     return this.display_name;
/*  72:    */   }
/*  73:    */   
/*  74:    */   public void setDisplay_name(String display_name)
/*  75:    */   {
/*  76: 87 */     this.display_name = display_name;
/*  77:    */   }
/*  78:    */   
/*  79:    */   public int getStarred()
/*  80:    */   {
/*  81: 91 */     return this.starred;
/*  82:    */   }
/*  83:    */   
/*  84:    */   public void setStarred(int starred)
/*  85:    */   {
/*  86: 95 */     this.starred = starred;
/*  87:    */   }
/*  88:    */   
/*  89:    */   public ArrayList<String> getPhones()
/*  90:    */   {
/*  91: 99 */     return this.phones;
/*  92:    */   }
/*  93:    */   
/*  94:    */   public void setPhones(ArrayList<String> phones)
/*  95:    */   {
/*  96:103 */     this.phones = phones;
/*  97:    */   }
/*  98:    */   
/*  99:    */   public ArrayList<String> getEmails()
/* 100:    */   {
/* 101:107 */     return this.emails;
/* 102:    */   }
/* 103:    */   
/* 104:    */   public void setEmails(ArrayList<String> emails)
/* 105:    */   {
/* 106:111 */     this.emails = emails;
/* 107:    */   }
/* 108:    */   
/* 109:    */   public ArrayList<String> getNotes()
/* 110:    */   {
/* 111:115 */     return this.notes;
/* 112:    */   }
/* 113:    */   
/* 114:    */   public void setNotes(ArrayList<String> notes)
/* 115:    */   {
/* 116:119 */     this.notes = notes;
/* 117:    */   }
/* 118:    */   
/* 119:    */   public String getStreet()
/* 120:    */   {
/* 121:123 */     return this.street;
/* 122:    */   }
/* 123:    */   
/* 124:    */   public void setStreet(String street)
/* 125:    */   {
/* 126:127 */     this.street = street;
/* 127:    */   }
/* 128:    */   
/* 129:    */   public String getCity()
/* 130:    */   {
/* 131:131 */     return this.city;
/* 132:    */   }
/* 133:    */   
/* 134:    */   public void setCity(String city)
/* 135:    */   {
/* 136:135 */     this.city = city;
/* 137:    */   }
/* 138:    */   
/* 139:    */   public String getPostalcode()
/* 140:    */   {
/* 141:139 */     return this.postalcode;
/* 142:    */   }
/* 143:    */   
/* 144:    */   public void setPostalcode(String postalcode)
/* 145:    */   {
/* 146:143 */     this.postalcode = postalcode;
/* 147:    */   }
/* 148:    */   
/* 149:    */   public String getCountry()
/* 150:    */   {
/* 151:147 */     return this.country;
/* 152:    */   }
/* 153:    */   
/* 154:    */   public void setCountry(String country)
/* 155:    */   {
/* 156:151 */     this.country = country;
/* 157:    */   }
/* 158:    */   
/* 159:    */   public int getType_addr()
/* 160:    */   {
/* 161:155 */     return this.type_addr;
/* 162:    */   }
/* 163:    */   
/* 164:    */   public void setType_addr(int type_addr)
/* 165:    */   {
/* 166:159 */     this.type_addr = type_addr;
/* 167:    */   }
/* 168:    */   
/* 169:    */   public ArrayList<String> getMessaging()
/* 170:    */   {
/* 171:163 */     return this.messaging;
/* 172:    */   }
/* 173:    */   
/* 174:    */   public void setMessaging(ArrayList<String> messaging)
/* 175:    */   {
/* 176:167 */     this.messaging = messaging;
/* 177:    */   }
/* 178:    */   
/* 179:    */   public String getOrganisationName()
/* 180:    */   {
/* 181:171 */     return this.OrganisationName;
/* 182:    */   }
/* 183:    */   
/* 184:    */   public void setOrganisationName(String organisationName)
/* 185:    */   {
/* 186:175 */     this.OrganisationName = organisationName;
/* 187:    */   }
/* 188:    */   
/* 189:    */   public String getOrganisationStatus()
/* 190:    */   {
/* 191:179 */     return this.OrganisationStatus;
/* 192:    */   }
/* 193:    */   
/* 194:    */   public void setOrganisationStatus(String organisationStatus)
/* 195:    */   {
/* 196:183 */     this.OrganisationStatus = organisationStatus;
/* 197:    */   }
/* 198:    */   
/* 199:    */   public byte[] getPhoto()
/* 200:    */   {
/* 201:187 */     return this.photo;
/* 202:    */   }
/* 203:    */   
/* 204:    */   public void setPhoto(byte[] photo)
/* 205:    */   {
/* 206:191 */     this.photo = photo;
/* 207:    */   }
/* 208:    */ }


/* Location:           C:\Users\SmileyFacedHacker\Downloads\StillBorn RAT (Android)\StillBorn RAT (Android)\StillBornRAT.jar
 * Qualified Name:     utils.Contact
 * JD-Core Version:    0.7.0.1
 */