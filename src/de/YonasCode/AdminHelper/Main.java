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

import de.YonasCode.AdminHelper.API.AdminHelperAntiSpamAPI;
import de.YonasCode.AdminHelper.UpdateSystem.FileUpdate;
import de.YonasCode.AdminHelper.UpdateSystem.UpdateAlert;
import java.util.logging.Logger;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

	public static Main INSTANCE;
	public static AdminHelper ADMINHELPER;
	public static UpdateAlert UPDATEALERT;
	public static AdminHelperAntiSpamAPI ANTISPAM;
	public static FileUpdate FILEUPDATE;
	public static FileConfiguration CONFIG;
	public static Logger LOG = Logger.getLogger("Minecraft");

	public static Runtime RUNTIME = Runtime.getRuntime();
	
	@Override
	public void onEnable() {
		INSTANCE 					= this;
		CONFIG 						= getConfig();
		ADMINHELPER 				= new AdminHelper();
		ANTISPAM					= new AdminHelperAntiSpamAPI();
		
		if(Booleans.OPT_OUT) {
		      UPDATEALERT = new UpdateAlert("http://dev.bukkit.org/server-mods/adminhelper/files.rss");
		      UPDATEALERT.updateInformations();
		      FILEUPDATE = new FileUpdate();
		      
		      if (UPDATEALERT.updateNeeded()) {
		          LOG.info("The version " + UPDATEALERT.getVersion() + " is now available on " + UPDATEALERT.getLink());
		      }
		}
		
	    ADMINHELPER.loadConfig();
	    ADMINHELPER.loadCommands();
	    ADMINHELPER.loadListeners();
	    ADMINHELPER.loadMetrics();
	}
	
}
