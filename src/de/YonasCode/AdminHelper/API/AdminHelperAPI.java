package de.YonasCode.AdminHelper.API;

import java.util.List;

import de.YonasCode.AdminHelper.Main;

public class AdminHelperAPI {

	private Variables v = new Variables();

	public AdminHelperFireWork FireworkAPI = new AdminHelperFireWork();
	public AdminHelperBugFixing BugFixingAPI = new AdminHelperBugFixing();
	public AdminHelperAntiSpamAPI AntiSpamAPI = new AdminHelperAntiSpamAPI();
	
	public String getVersion = this.v.getVersion();
	public List<String> getAuthors = this.v.getAuthors();
	public List<String> getPermissions = this.v.allPermissions();
	
	public boolean OPTOUT = this.v.optout();
	
	public boolean UPDATE_NEEDED = Main.UPDATEALERT.updateNeeded();
	public String LATEST_VERSION_ID = Main.UPDATEALERT.getVersion();
	public String LATEST_VERSION_LINK = Main.UPDATEALERT.getLink();
	
	public boolean isGlobalmute = this.v.globalmute();
	
	public boolean isAntiSeven = this.v.antiseven();
	
	public boolean isAntiSpam = this.v.antispam();

	public boolean isAntiAdvertising = this.v.antiadvertisting();
	
	public boolean isAntiCaps = this.v.anticaps();
	public int AntiCapsTotalAllowd = this.v.anticapstotal();

	public long RAM_MAX = this.v.maxmemory();
	public long RAM_FREE = this.v.freememory();
	public long RAM_TOTAL = this.v.totalmemory();
	public long PROCESSORS_AVAILABLE = this.v.availableproccessors();

	public void updateUpdateInformations() {
	    Main.UPDATEALERT.updateInformations();
	}
	
}
