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
