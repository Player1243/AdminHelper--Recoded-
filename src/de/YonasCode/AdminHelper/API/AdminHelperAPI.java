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
