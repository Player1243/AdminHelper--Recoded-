package de.YonasCode.AdminHelper;

import org.bukkit.ChatColor;

public class Message {

	public static String TAG = ChatColor.GOLD + "[" + ChatColor.AQUA + "AdminHelper" + ChatColor.GOLD + "] ";
	public static String NO_PERMISSIONS = replaceColor(Main.CONFIG.getString("messages.noPermissions"));
	public static String MOREARGUMENTS = ChatColor.RED + "Please enter more arguments to use the command.";
	public static String CONSOLE_NAME = replaceColor(Main.CONFIG.getString("messages.consoleName"));
	
	public static String GLOBALMUTE_ENABLED = replaceColor(Main.CONFIG.getString("messages.globalmute.enabled"));
	public static String GLOBALMUTE_DISABLED = replaceColor(Main.CONFIG.getString("messages.globalmute.disabled"));
	public static String GLOBALMUTE_ACTIVE = replaceColor(Main.CONFIG.getString("messages.globalmute.active"));
	
	public static String GIVEALL = replaceColor(Main.CONFIG.getString("messages.giveall.message"));
	
	public static String CLEARCHAT_PUBLIC = replaceColor(Main.CONFIG.getString("messages.clearchat.public.message"));
	public static String CLEARCHAT_PRIVATED = replaceColor(Main.CONFIG.getString("messages.clearchat.private.message"));
	
	public static String ANTICAPS = replaceColor(Main.CONFIG.getString("messages.functions.noCaps"));
	public static String ANTISEVEN = replaceColor(Main.CONFIG.getString("messages.functions.noSeven"));
	public static String ANTISPAM = replaceColor(Main.CONFIG.getString("messages.functions.noSpam"));
	public static String ANTIADVERTISING = replaceColor(Main.CONFIG.getString("messages.functions.noAdvertising"));
	
	private static String replaceColor(String message) {
		return ChatColor.translateAlternateColorCodes('&', message);
	}

	public static String replacePlayer(String message, String variable, String name) {
	    return message.replaceAll(variable, name);
	}
	
}
