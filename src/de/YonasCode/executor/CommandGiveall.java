package de.YonasCode.executor;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import de.YonasCode.AdminHelper.Main;
import de.YonasCode.AdminHelper.Message;
import de.YonasCode.AdminHelper.Permission;

public class CommandGiveall implements CommandExecutor {

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if(sender instanceof Player) {
			Player pl = (Player)sender;
			if(cmd.getName().equalsIgnoreCase("giveall")) {
				if(pl.hasPermission(Permission.GIVEALL_USE)) {
					try {
						if(args.length == 0)
							pl.sendMessage(Message.MOREARGUMENTS);
						else if(args.length == 1) 
							pl.sendMessage(Message.MOREARGUMENTS);
						else if(args.length >= 2) {
							if(args[0].contains(":")) {
								String[] splited = args[0].split(":");
								String id = splited[0];
								String meta = splited[1];
								ItemStack itemstack = new ItemStack(Integer.valueOf(id), Integer.valueOf(args[1]), Short.parseShort(meta));
								for(Player p : Bukkit.getOnlinePlayers()) {
									p.getInventory().addItem(itemstack);
									p.sendMessage(Message.GIVEALL.replaceAll("%player%", p.getName()).replaceAll("%amount%", args[1]).replaceAll("%item%", args[0])); 
								}
							} else {
								ItemStack itemstack = new ItemStack(Integer.valueOf(args[0]), Integer.valueOf(args[1]));
								for(Player p : Bukkit.getOnlinePlayers()) {
									p.getInventory().addItem(itemstack);
									p.sendMessage(Message.GIVEALL.replaceAll("%player%", p.getName()).replaceAll("%amount%", args[1]).replaceAll("%item%", args[0])); 
								}
							}
						}
					
					} catch(NumberFormatException e) {
						pl.sendMessage(ChatColor.RED + "Plese use only numbers.");
					} catch(ArrayIndexOutOfBoundsException e) {
						pl.sendMessage(ChatColor.RED + "Unknown Item-ID");
					}
				} else {
					pl.sendMessage(Message.NO_PERMISSIONS);
				}
			}
			return true;
		} else {
			if(cmd.getName().equalsIgnoreCase("giveall")) {
				try {
					if(args.length == 0)
						Main.LOG.info(Message.MOREARGUMENTS);
					else if(args.length == 1) 
						Main.LOG.info(Message.MOREARGUMENTS);
					else if(args.length >= 2) {
						if(args[0].contains(":")) {
							String[] splited = args[0].split(":");
							String id = splited[0];
							String meta = splited[1];
							ItemStack itemstack = new ItemStack(Integer.valueOf(id), Integer.valueOf(args[1]), Short.parseShort(meta));
							for(Player p : Bukkit.getOnlinePlayers()) {
								p.getInventory().addItem(itemstack);
								p.sendMessage(Message.GIVEALL.replaceAll("%player%", p.getName()).replaceAll("%amount%", args[1]).replaceAll("%item%", args[0])); 
							}
						} else {
							ItemStack itemstack = new ItemStack(Integer.valueOf(args[0]), Integer.valueOf(args[1]));
							for(Player p : Bukkit.getOnlinePlayers()) {
								p.getInventory().addItem(itemstack);
								p.sendMessage(Message.GIVEALL.replaceAll("%player%", p.getName()).replaceAll("%amount%", args[1]).replaceAll("%item%", args[0])); 
							}
						}
						Main.LOG.info("Command successfully used.");
					}
				
				} catch(NumberFormatException e) {
					Main.LOG.info(ChatColor.RED + "Plese use only numbers.");
				} catch(ArrayIndexOutOfBoundsException e) {
					Main.LOG.info(ChatColor.RED + "Unknown Item-ID");
				}
			}
			return true;
		}
		
	}

}
