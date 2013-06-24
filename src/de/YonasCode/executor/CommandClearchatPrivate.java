package de.YonasCode.executor;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.YonasCode.AdminHelper.Message;
import de.YonasCode.AdminHelper.Permission;

public class CommandClearchatPrivate implements CommandExecutor {

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if(sender instanceof Player) {
			Player pl = (Player)sender;
			if(cmd.getName().equalsIgnoreCase("pcc")) {
				if(pl.hasPermission(Permission.CLEARCHAT_PRIVATE)) {
					StringBuilder message = new StringBuilder();
					for(int i = 0; i < 300; i++) {
						message.append(" ");
						pl.sendMessage(message.toString());
					}
					pl.sendMessage(Message.CLEARCHAT_PRIVATED);
				}
			}
			return true;
		}
		
		return false;
	}

}
