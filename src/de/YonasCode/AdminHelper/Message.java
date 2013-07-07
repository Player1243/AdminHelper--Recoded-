/*
* Copyright 2011-2013 Jan Krüger. All rights reserved.
*
* Redistribution and use in source and binary forms, with or without modification, are
* permitted provided that the following conditions are met:
*
* 1. Redistributions of source code must retain the above copyright notice, this list of
* conditions and the following disclaimer.
*
* 2. Redistributions in binary form must reproduce the above copyright notice, this list
* of conditions and the following disclaimer in the documentation and/or other materials
* provided with the distribution.
*
* THIS SOFTWARE IS PROVIDED BY THE AUTHOR ''AS IS'' AND ANY EXPRESS OR IMPLIED
* WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND
* FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE AUTHOR OR
* CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
* CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
* SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON
* ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
* NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF
* ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
*
* The views and conclusions contained in the software and documentation are those of the
* authors and contributors and should not be interpreted as representing official policies,
* either expressed or implied, of anybody else.
*/
package de.YonasCode.AdminHelper;

import org.bukkit.ChatColor;

public class Message {

	public static String TAG = ChatColor.GOLD + "[" + ChatColor.AQUA + "AdminHelper" + ChatColor.GOLD + "]";
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
