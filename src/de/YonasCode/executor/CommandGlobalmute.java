package de.YonasCode.executor;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.YonasCode.AdminHelper.Booleans;
import de.YonasCode.AdminHelper.Message;
import de.YonasCode.AdminHelper.Permission;

public class CommandGlobalmute implements CommandExecutor {

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if(sender instanceof Player) {
			Player pl = (Player)sender;
			if(cmd.getName().equalsIgnoreCase("globalmute")) {
				if(pl.hasPermission(Permission.GLOBALMUTE_USE)) {
					if(Booleans.GLOBALMUTE) {
			            Bukkit.broadcastMessage(Message.GLOBALMUTE_DISABLED);
			            Booleans.GLOBALMUTE = false;
					} else {
			            Bukkit.broadcastMessage(Message.GLOBALMUTE_ENABLED);
			            Booleans.GLOBALMUTE = true;
					}
				} else {
					pl.sendMessage(Message.NO_PERMISSIONS);
				}
			}
			return true;
		} else {
			if(cmd.getName().equalsIgnoreCase("globalmute")) {
				if(Booleans.GLOBALMUTE) {
					Bukkit.broadcastMessage(Message.GLOBALMUTE_DISABLED);
					Booleans.GLOBALMUTE = false;
				} else {
					Bukkit.broadcastMessage(Message.GLOBALMUTE_ENABLED);
					Booleans.GLOBALMUTE = true;
				}
			}
			return true;
		}
		
	}

}
