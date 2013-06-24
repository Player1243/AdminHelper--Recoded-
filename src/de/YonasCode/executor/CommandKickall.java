package de.YonasCode.executor;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.YonasCode.AdminHelper.Main;
import de.YonasCode.AdminHelper.Message;
import de.YonasCode.AdminHelper.Permission;

public class CommandKickall implements CommandExecutor {

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if(sender instanceof Player) {
			Player pl = (Player)sender;
			if(cmd.getName().equalsIgnoreCase("kickall")) {
				if(pl.hasPermission(Permission.KICKALL_USE)) {
					if(args.length >= 1) {
						StringBuilder message = new StringBuilder();
						for(int i = 0; i < args.length; i++) {
							message.append(args[i]).append(" ");
						}
						for(Player p : Bukkit.getOnlinePlayers()) {
							if(!(p.hasPermission(Permission.KICKALL_IGNORE)))
								p.kickPlayer(ChatColor.translateAlternateColorCodes('&', message.toString()));
						}
						Bukkit.broadcastMessage(ChatColor.GREEN + "All players are successfully kicked with the reason: " + ChatColor.GOLD + message.toString());
					} else {
						pl.sendMessage(Message.MOREARGUMENTS);
					}
				} else {
					pl.sendMessage(Message.NO_PERMISSIONS);
				}
			}
			return true;
		} else {
			if(cmd.getName().equalsIgnoreCase("kickall")) {
				if(args.length >= 1) {
					StringBuilder message = new StringBuilder();
					for(int i = 0; i < args.length; i++) {
						message.append(args[i]).append(" ");
					}
					for(Player p : Bukkit.getOnlinePlayers()) {
						if(!(p.hasPermission(Permission.KICKALL_IGNORE)))
							p.kickPlayer(ChatColor.translateAlternateColorCodes('&', message.toString()));
					}
					Bukkit.broadcastMessage(ChatColor.GREEN + "All players are successfully kicked with the reason: " + ChatColor.GOLD + message.toString());
				} else {
					Main.LOG.info(Message.MOREARGUMENTS);
				}
			}
			return true;
		}
		
	}

}
