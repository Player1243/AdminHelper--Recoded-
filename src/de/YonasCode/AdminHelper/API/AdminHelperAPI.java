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
	
	/**
	 * @author Jan Krüger
	 * @version 1.5 
	 */
	
	private Variables v = new Variables();
	
	/**
	 * @return the FireWork API
	 */
	public AdminHelperFireWork FireworkAPI() {
		return new AdminHelperFireWork();
	}
	
	/**
	 * @return the BugFixing API
	 */
	public AdminHelperBugFixing BugFixingAPI() {
		return new AdminHelperBugFixing();
	}
	
	/**
	 * @return the AntiSpam API
	 */
	public AdminHelperAntiSpamAPI AntiSpamAPI() {
		return new AdminHelperAntiSpamAPI();
	}
	
	/**
	 * @return the Item API
	 */
	public AdminHelperItemAPI ItemAPI() {
		return new AdminHelperItemAPI();
	}
	
	/**
	 * @return the AdminHelper Plugin Version
	 */
	public String getVersion() {
		return this.v.getVersion();
	}
	
	/**
	 * @return all Authors of the Plugin AdminHelper
	 */
	public List<String> getAuthors() {
		return this.v.getAuthors();
	}
	
	/**
	 * @return all Permissions that the Plugin contains
	 */
	public List<String> getPermissions() { 
		return this.v.allPermissions();
	}
	
	/**
	 * @return the boolean if the opt-out function enabled
	 */
	public boolean OPTOUT() {
		return this.v.optout();
	}
	
	/**
	 * @return the boolean if a new update available
	 */
	public boolean UPDATE_NEEDED() {
		return Main.UPDATEALERT.updateNeeded();
	}
	
	/**
	 * @retun the latest version id 
	 */
	public String LATEST_VERSION_ID() {
		return Main.UPDATEALERT.getVersion();
	}
	
	/**
	 * @return the link from the latest version
	 */
	public String LATEST_VERSION_LINK() { 
		return Main.UPDATEALERT.getLink();
	}
	
	/**
	 * @return return a boolean if the globalmute active
	 */
	public boolean isGlobalmute() {
		return this.v.globalmute();
	}
	
	/**
	 * @return return a boolean if the antiseven active
	 */
	public boolean isAntiSeven() { 
		return this.v.antiseven();
	}
	
	/**
	 * @return return a boolean if the antiseven active
	 */
	public boolean isAntiSpam() {
		return this.v.antispam();
	}
	
	/**
	 * @return return a boolean if the antiadvertising active
	 */
	public boolean isAntiAdvertising() {
		return this.v.antiadvertisting();
	}
	
	/**
	 * @return return the boolean if the anticaps active
	 */
	public boolean isAntiCaps() { 
		return this.v.anticaps();
	}
	
	/**
	 * @return return the total allowed caps per message
	 */
	public int AntiCapsTotalAllowed() {
		return this.v.anticapstotal();
	}
	
	/**
	 * @return return the maximal memory
	 */
	public long RAM_MAX() {
		return this.v.maxmemory();
	}
	
	/**
	 * @return return the free memory
	 */
	public long RAM_FREE() {
		return this.v.freememory();
	}
	
	/**
	 * @return return the total memory
	 */
	public long RAM_TOTAL() {
		return this.v.totalmemory();
	}
	
	/**
	 * @return the number of available proccessors
	 */
	public long PROCESSORS_AVAILABLE() {
		return this.v.availableproccessors();
	}
	
	/**
	 * @return nothing, updated only the update informations
	 */
	public void updateUpdateInformation() {
	    Main.UPDATEALERT.updateInformation();
	}
	
}
