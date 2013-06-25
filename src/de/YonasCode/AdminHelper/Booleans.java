/*
* AdminHelper for Bukkit.
* Copyright (C) 2012-2013 AdminHelper Team
*
* This program is free software: you can redistribute it and/or modify
* it under the terms of the GNU General Public License as published by
* the Free Software Foundation, either version 3 of the License, or
* (at your option) any later version.
*
* This program is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
* GNU General Public License for more details.
*
* You should have received a copy of the GNU General Public License
* along with this program. If not, see <http://www.gnu.org/licenses/>.
*/
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
