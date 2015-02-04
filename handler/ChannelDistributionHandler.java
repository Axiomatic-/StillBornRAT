/*   1:    */ package handler;
/*   2:    */ 
/*   3:    */ import Packet.CommandPacket;
/*   4:    */ import Packet.Packet;
/*   5:    */ import java.util.HashMap;
/*   6:    */ import java.util.Map;
/*   7:    */ 
/*   8:    */ public class ChannelDistributionHandler
/*   9:    */ {
/*  10:    */   private HashMap<Integer, Packet> packetMap;
/*  11:    */   private Map<Integer, TemporaryStorage> tempDataMap;
/*  12:    */   private Map<Integer, PacketHandler> packetHandlerMap;
/*  13:    */   
/*  14:    */   public ChannelDistributionHandler()
/*  15:    */   {
/*  16: 44 */     this.packetMap = new HashMap();
/*  17: 45 */     this.tempDataMap = new HashMap();
/*  18: 46 */     this.packetHandlerMap = new HashMap();
/*  19:    */     
/*  20:    */ 
/*  21: 49 */     registerListener(0, new CommandPacket());
/*  22: 50 */     this.tempDataMap.put(Integer.valueOf(0), new TemporaryStorage());
/*  23: 51 */     this.packetHandlerMap.put(Integer.valueOf(0), new CommandHandler());
/*  24:    */   }
/*  25:    */   
/*  26:    */   public boolean registerListener(int chan, Packet packet)
/*  27:    */   {
/*  28: 59 */     if (!this.packetMap.containsKey(Integer.valueOf(chan)))
/*  29:    */     {
/*  30: 61 */       this.packetMap.put(Integer.valueOf(chan), packet);
/*  31: 62 */       this.tempDataMap.put(Integer.valueOf(chan), new TemporaryStorage());
/*  32: 63 */       return true;
/*  33:    */     }
/*  34: 66 */     return false;
/*  35:    */   }
/*  36:    */   
/*  37:    */   public boolean registerHandler(int chan, PacketHandler han)
/*  38:    */   {
/*  39: 70 */     if (!this.packetHandlerMap.containsKey(Integer.valueOf(chan)))
/*  40:    */     {
/*  41: 72 */       this.packetHandlerMap.put(Integer.valueOf(chan), han);
/*  42: 73 */       return true;
/*  43:    */     }
/*  44: 75 */     return false;
/*  45:    */   }
/*  46:    */   
/*  47:    */   public boolean removeListener(int chan)
/*  48:    */   {
/*  49:    */     try
/*  50:    */     {
/*  51: 82 */       if (this.packetMap.containsKey(Integer.valueOf(chan)))
/*  52:    */       {
/*  53: 84 */         this.packetMap.remove(Integer.valueOf(chan));
/*  54: 85 */         this.tempDataMap.remove(Integer.valueOf(chan));
/*  55: 86 */         this.packetHandlerMap.remove(Integer.valueOf(chan));
/*  56: 87 */         return true;
/*  57:    */       }
/*  58:    */     }
/*  59:    */     catch (NullPointerException e)
/*  60:    */     {
/*  61: 91 */       return false;
/*  62:    */     }
/*  63: 93 */     return false;
/*  64:    */   }
/*  65:    */   
/*  66:    */   public int getFreeChannel()
/*  67:    */   {
/*  68: 99 */     int i = (int)(Math.random() * 1000.0D);
/*  69:100 */     while (this.packetMap.containsKey(Integer.valueOf(i))) {
/*  70:102 */       i = (int)(Math.random() * 1000.0D);
/*  71:    */     }
/*  72:104 */     return i;
/*  73:    */   }
/*  74:    */   
/*  75:    */   public Packet getPacketMap(int chan)
/*  76:    */   {
/*  77:109 */     return (Packet)this.packetMap.get(Integer.valueOf(chan));
/*  78:    */   }
/*  79:    */   
/*  80:    */   public PacketHandler getPacketHandlerMap(int chan)
/*  81:    */   {
/*  82:114 */     return (PacketHandler)this.packetHandlerMap.get(Integer.valueOf(chan));
/*  83:    */   }
/*  84:    */   
/*  85:    */   public TemporaryStorage getStorage(int chan)
/*  86:    */   {
/*  87:119 */     return (TemporaryStorage)this.tempDataMap.get(Integer.valueOf(chan));
/*  88:    */   }
/*  89:    */ }


/* Location:           C:\Users\SmileyFacedHacker\Downloads\StillBorn RAT (Android)\StillBorn RAT (Android)\StillBornRAT.jar
 * Qualified Name:     handler.ChannelDistributionHandler
 * JD-Core Version:    0.7.0.1
 */