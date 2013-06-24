package de.YonasCode.AdminHelper.API;

import de.YonasCode.AdminHelper.Booleans;
import de.YonasCode.AdminHelper.Integers;
import de.YonasCode.AdminHelper.Main;
import de.YonasCode.AdminHelper.Permission;
import java.util.List;

public class Variables {


	@SuppressWarnings("null")
	public List<String> allPermissions() {
		List<String> ALLPERMISSIONS = null;
		ALLPERMISSIONS.add(Permission.ADMINHELPER_HELP);
		ALLPERMISSIONS.add(Permission.ADMINHELPER_RELOAD);
		ALLPERMISSIONS.add(Permission.ANTICAPS_IGNORE);
		ALLPERMISSIONS.add(Permission.ANTISEVEN_IGNORE);
		ALLPERMISSIONS.add(Permission.ANTISPAM_IGNORE);
		ALLPERMISSIONS.add(Permission.ANTIADVERTISING_IGNORE);
		ALLPERMISSIONS.add(Permission.CLEARCHAT_IGNORE);
		ALLPERMISSIONS.add(Permission.CLEARCHAT_PRIVATE);
		ALLPERMISSIONS.add(Permission.CLEARCHAT_USE);
		ALLPERMISSIONS.add(Permission.GLOBALMUTE_IGNORE);
		ALLPERMISSIONS.add(Permission.GLOBALMUTE_USE);
		ALLPERMISSIONS.add(Permission.RAM_USE);
		ALLPERMISSIONS.add(Permission.ANTIADVERTISING_MESSAGE);
		return ALLPERMISSIONS;
	}
	
	public List<String> getAuthors() {
		return Main.INSTANCE.getDescription().getAuthors();
	}
	
	public String getVersion() {
		return Main.INSTANCE.getDescription().getVersion();
	}
	
	public boolean optout() {
		return Main.CONFIG.getBoolean("opt-out");
	}
	
	public boolean globalmute() {
		return Booleans.GLOBALMUTE;
	}

	public boolean antiseven() {
		return Booleans.ANTISEVEN;
	}

	public boolean antispam() {
		return Booleans.ANTISPAM;
	}

	public boolean antiadvertisting() {
		return Booleans.ANTIADVERTISING;
	}
	
	public boolean anticaps() {
		return Booleans.ANTICAPS;
	}
	
	public int anticapstotal() {
		return Integers.ANTICAPS_TOTAL;
	}

	public long maxmemory() {
		return Main.RUNTIME.maxMemory();
	}

	public long freememory() {
		return Main.RUNTIME.freeMemory();
	}

	public long totalmemory() {
		return Main.RUNTIME.totalMemory();
	}

	public int availableproccessors() {
		return Main.RUNTIME.availableProcessors();
	}
	
}
