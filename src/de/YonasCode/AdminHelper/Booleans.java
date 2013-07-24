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

public class Booleans {

	  public static boolean GLOBALMUTE = false;

	  public static boolean ANTISEVEN = Main.CONFIG.getBoolean("chat.function.antiseven.enabled");
	  public static boolean ANTICAPS = Main.CONFIG.getBoolean("chat.function.anticaps.enabled");
	  public static boolean ANTISPAM = Main.CONFIG.getBoolean("chat.function.antispam.enabled");
	  public static boolean ANTIADVERTISING = Main.CONFIG.getBoolean("chat.function.antiadvertising.enabled");
	  public static boolean ANTIADVERTISING_BY_COMMANDS	= Main.CONFIG.getBoolean("chat.function.antiadvertising.bycommands");
	  public static boolean ANTIADVERTISINGEXTREME = Main.CONFIG.getBoolean("chat.function.antiadvertising.extreme-mod");
	  public static boolean ANTIADVERTISINGMESSAGE = Main.CONFIG.getBoolean("chat.function.antiadvertising.warning-message");
	  
	  public static boolean ANTIBADWORD	= Main.CONFIG.getBoolean("chat.function.antibadword.enabled"); //not in config file
	  public static boolean ANTIBADWORDMESSAGE = Main.CONFIG.getBoolean("chat.function.antibadword.warning-message"); //not in config file
	  
	  public static boolean OPT_OUT = Main.CONFIG.getBoolean("enabled-update-alert");
	  public boolean UPDATE_NEEDED = Main.UPDATEALERT.updateNeeded();
	  public String LATEST_VERSION_ID = Main.UPDATEALERT.getVersion();
	  public String LATEST_VERSION_LINK = Main.UPDATEALERT.getLink();
	
}