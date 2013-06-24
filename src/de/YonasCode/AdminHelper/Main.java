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
