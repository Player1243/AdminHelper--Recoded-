package de.YonasCode.AdminHelper;

public class Booleans {
	
	  public static boolean GLOBALMUTE = false;

	  public static boolean ANTISEVEN = Main.CONFIG.getBoolean("chat.function.antiseven.enabled");
	  public static boolean ANTICAPS = Main.CONFIG.getBoolean("chat.function.anticaps.enabled");
	  public static boolean ANTISPAM = Main.CONFIG.getBoolean("chat.function.antispam.enabled");
	  public static boolean ANTIADVERTISING = Main.CONFIG.getBoolean("chat.function.antiadvertising.enabled");
	  public static boolean ANTIADVERTISINGEXTREME = Main.CONFIG.getBoolean("chat.function.antiadvertising.extreme-mod");
	  public static boolean ANTIADVERTISINGMESSAGE = Main.CONFIG.getBoolean("chat.function.antiadvertising.warning-message");

	  public static boolean OPT_OUT = Main.CONFIG.getBoolean("opt-out");
	  public boolean UPDATE_NEEDED = Main.UPDATEALERT.updateNeeded();
	  public String LATEST_VERSION_ID = Main.UPDATEALERT.getVersion();
	  public String LATEST_VERSION_LINK = Main.UPDATEALERT.getLink();
	
}
