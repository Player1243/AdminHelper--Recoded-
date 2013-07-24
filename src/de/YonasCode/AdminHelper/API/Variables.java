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
package de.YonasCode.AdminHelper.API;

/**
 * 
 * @author Jan Krüger
 * 
 */

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
	
	public boolean antiadvertisingbycommands() {
		return Booleans.ANTIADVERTISING_BY_COMMANDS;
	}
	
	public boolean antibadword() {
		return Booleans.ANTIBADWORD;
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