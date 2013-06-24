package de.YonasCode.executor;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.YonasCode.AdminHelper.Main;
import de.YonasCode.AdminHelper.Message;
import de.YonasCode.AdminHelper.Permission;

public class CommandRam implements CommandExecutor {

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if(sender instanceof Player) {
			Player pl = (Player)sender;
			if(cmd.getName().equalsIgnoreCase("ram")) {
				if(pl.hasPermission(Permission.RAM_USE)) {
					pl.sendMessage(ChatColor.AQUA + "----" + ChatColor.GOLD + "Server" + ChatColor.AQUA + "----");
					pl.sendMessage(ChatColor.AQUA + "Free-RAM: " + ChatColor.GOLD + (Main.RUNTIME.maxMemory() - Main.RUNTIME.totalMemory()) / 1048576L + "MB");
					pl.sendMessage(ChatColor.AQUA + "Used-RAM: " + ChatColor.GOLD + Main.RUNTIME.totalMemory() / 1048576L + "MB");
					pl.sendMessage(ChatColor.AQUA + "Maxi-RAM: " + ChatColor.GOLD + Main.RUNTIME.maxMemory() / 1048576L + "MB");
					pl.sendMessage(ChatColor.AQUA + "Utilization: " + ramCheck() + usedMemory() + "%");
				} else {
					pl.sendMessage(Message.NO_PERMISSIONS);
				}
			}
			return true;
		} else {
			if(cmd.getName().equalsIgnoreCase("ram")) {
				Main.LOG.info(ChatColor.AQUA + "----" + ChatColor.GOLD + "Server" + ChatColor.AQUA + "----");
				Main.LOG.info(ChatColor.AQUA + "Free-RAM: " + ChatColor.GOLD + (Main.RUNTIME.maxMemory() - Main.RUNTIME.totalMemory()) / 1048576L + "MB");
				Main.LOG.info(ChatColor.AQUA + "Used-RAM: " + ChatColor.GOLD + Main.RUNTIME.totalMemory() / 1048576L + "MB");
				Main.LOG.info(ChatColor.AQUA + "Maxi-RAM: " + ChatColor.GOLD + Main.RUNTIME.maxMemory() / 1048576L + "MB");
				Main.LOG.info(ChatColor.AQUA + "Utilization: " + ramCheck() + usedMemory() + "%");
			}
			return true;
		}
	
	}
	
	private ChatColor ramCheck() {
		double raminpro = usedMemory();
		if(raminpro <= 50.0D)
			return ChatColor.GREEN;
		else if((raminpro >= 50.1D) && (raminpro <= 80.0D)) 
			return ChatColor.YELLOW;
		else if((raminpro >= 80.1D) && (raminpro <= 100.0D))
			return ChatColor.DARK_RED;
		else
			return ChatColor.WHITE;
	}
	
	private double usedMemory() {
		double maxmemory = Main.RUNTIME.maxMemory();
	    double usedmemory = Main.RUNTIME.totalMemory();
	    double maxm = maxmemory / 1048576.0D;
	    maxm /= 100.0D;
	    double usedm = usedmemory / 1048576.0D;
	    double auslastung = usedm / maxm;
	    auslastung *= 100.0D;
	    auslastung = Math.round(auslastung);
	    auslastung /= 100.0D;
	    return auslastung;
	}

}
