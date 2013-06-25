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
package de.YonasCode.listener;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import de.YonasCode.AdminHelper.Booleans;
import de.YonasCode.AdminHelper.Main;
import de.YonasCode.AdminHelper.Message;
import de.YonasCode.AdminHelper.Permission;

public class JoinListener implements Listener {

	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {
		if(Booleans.OPT_OUT) {
			Main.UPDATEALERT.updateInformations();
			if(Main.UPDATEALERT.updateNeeded()) {
				if(event.getPlayer().hasPermission(Permission.ADMINHELPER_UPDATE)) {
			        event.getPlayer().sendMessage(Message.TAG + ChatColor.RED + "A new version is available.");
			        event.getPlayer().sendMessage(ChatColor.GOLD + "If you want to update the plugin follow the instructions.");
			        event.getPlayer().sendMessage(ChatColor.GOLD + "1)" + ChatColor.GREEN + "Type:  /adminhelper update");
			        event.getPlayer().sendMessage(ChatColor.GOLD + "2)" + ChatColor.GREEN + "Ready to use it. I hope it was very easy ;) .");
				}
			}
		}
	}
	
}
