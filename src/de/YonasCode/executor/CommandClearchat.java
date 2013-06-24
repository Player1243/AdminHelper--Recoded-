package de.YonasCode.executor;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.YonasCode.AdminHelper.Message;
import de.YonasCode.AdminHelper.Permission;

public class CommandClearchat implements CommandExecutor {

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if(sender instanceof Player) {
			Player pl = (Player)sender;
			if(cmd.getName().equalsIgnoreCase("clearchat")) {
				if(pl.hasPermission(Permission.CLEARCHAT_USE)) {
					for(Player p : Bukkit.getOnlinePlayers()) {
						StringBuilder message = new StringBuilder();
						for(int i = 0; i < 300; i++) {
							if(!(p.hasPermission(Permission.CLEARCHAT_IGNORE))) {
								message.append(" ");
								p.sendMessage(message.toString());
							}
						}
					}
					Bukkit.broadcastMessage(Message.replacePlayer(Message.CLEARCHAT_PUBLIC, "%sender%", pl.getName()));
				} else {
					pl.sendMessage(Message.NO_PERMISSIONS);
				}
			}
			return true;
		} else {
			if(cmd.getName().equalsIgnoreCase("clearchat")) {
				for(Player p : Bukkit.getOnlinePlayers()) {
					StringBuilder message = new StringBuilder();
					for(int i = 0; i < 300; i++) {
						if(!(p.hasPermission(Permission.CLEARCHAT_IGNORE))) {
							message.append(" ");
							p.sendMessage(message.toString());
						}
					}
				}
				Bukkit.broadcastMessage(Message.replacePlayer(Message.CLEARCHAT_PUBLIC, "%sender%", Message.CONSOLE_NAME));
			}
			return true;
		}
		
	}

}
