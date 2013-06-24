package de.YonasCode.executor;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.YonasCode.AdminHelper.Main;
import de.YonasCode.AdminHelper.Message;
import de.YonasCode.AdminHelper.Permission;

public class CommandGamemodeall implements CommandExecutor {

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if(sender instanceof Player) {
			Player pl = (Player)sender;
			if(cmd.getName().equalsIgnoreCase("gamemodeall")) {
				if(pl.hasPermission(Permission.GAMEMODEALL_USE)) {
					if(args.length >= 1) {
						GameMode mode = null;
			            if ((args[0].equalsIgnoreCase("survival")) || (args[0].equalsIgnoreCase("0")))
			            	mode = GameMode.SURVIVAL;
			            else if ((args[0].equalsIgnoreCase("creative")) || (args[0].equalsIgnoreCase("1")))
			            	mode = GameMode.CREATIVE;
			            else if ((args[0].equalsIgnoreCase("adventure")) || (args[0].equalsIgnoreCase("2")))
			            	mode = GameMode.ADVENTURE;
			            else
			            	mode = GameMode.SURVIVAL;
			            for(Player p : Bukkit.getOnlinePlayers()) {
			            	p.setGameMode(mode);
			            	p.sendMessage(ChatColor.GREEN + "Your gamemode has changed to " + ChatColor.GOLD + mode.toString() + ChatColor.GREEN + ".");
			            }
					} else {
						pl.sendMessage(Message.MOREARGUMENTS);
					}
				} else {
					pl.sendMessage(Message.NO_PERMISSIONS);
				}
			}
			
			return true;
		} else {
			if(cmd.getName().equalsIgnoreCase("gamemodeall")) {
				if(args.length >= 1) {
					GameMode mode = null;
					if ((args[0].equalsIgnoreCase("survival")) || (args[0].equalsIgnoreCase("0")))
						mode = GameMode.SURVIVAL;
			        else if ((args[0].equalsIgnoreCase("creative")) || (args[0].equalsIgnoreCase("1")))
			        	mode = GameMode.CREATIVE;
			        else if ((args[0].equalsIgnoreCase("adventure")) || (args[0].equalsIgnoreCase("2")))
			        	mode = GameMode.ADVENTURE;
			        else
			        	mode = GameMode.SURVIVAL;
			        for(Player p : Bukkit.getOnlinePlayers()) {
			        	p.setGameMode(mode);
			        	p.sendMessage(ChatColor.GREEN + "Your gamemode has changed to " + ChatColor.GOLD + mode.toString() + ChatColor.GREEN + ".");
			        }
			        Main.LOG.info("Command was successfully used.");
				} else {
					Main.LOG.info(Message.MOREARGUMENTS);
				}
			}
			return true;
		}
		
	}

}
